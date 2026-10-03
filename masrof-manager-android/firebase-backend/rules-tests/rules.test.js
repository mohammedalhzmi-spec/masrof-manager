'use strict';

const fs = require('node:fs');
const path = require('node:path');
const { after, before, beforeEach, test } = require('node:test');
const {
  assertFails,
  assertSucceeds,
  initializeTestEnvironment,
} = require('@firebase/rules-unit-testing');
const {
  collection,
  deleteDoc,
  doc,
  getDoc,
  getDocs,
  setDoc,
  updateDoc,
} = require('firebase/firestore');

const PROJECT_ID = 'demo-masrof-manager';
const DEVICE_ID = 'android-test-device';
let environment;

before(async () => {
  const rules = fs.readFileSync(path.join(__dirname, '..', 'firestore.rules'), 'utf8');
  environment = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: { rules },
  });
});

after(async () => {
  await environment?.cleanup();
});

beforeEach(async () => {
  await environment.clearFirestore();
});

async function seedProfile(uid, { active = true, role = 'USER', deviceStatus = 'APPROVED', publicKeyHash = `key-${uid}` } = {}) {
  await environment.withSecurityRulesDisabled(async (context) => {
    const db = context.firestore();
    await setDoc(doc(db, 'users', uid), { active, role, email: `${uid}@example.invalid` });
    await setDoc(doc(db, 'devices', `${uid}_${DEVICE_ID}`), {
      userId: uid,
      deviceId: DEVICE_ID,
      status: deviceStatus,
      approved: deviceStatus === 'APPROVED',
      publicKeyHash,
    });
  });
}

function clientDb(uid, { role = 'USER', deviceApproved = true, keyHash = `key-${uid}` } = {}) {
  return environment.authenticatedContext(uid, {
    app_role: role,
    device_id: DEVICE_ID,
    device_key: keyHash,
    device_approved: deviceApproved,
  }).firestore();
}

async function seedSharedDocument() {
  await environment.withSecurityRulesDisabled(async (context) => {
    await setDoc(doc(context.firestore(), 'documents', 'shared-document'), {
      id: 'shared-document',
      type: 'REQUEST',
      status: 'SUBMITTED',
      createdByUid: 'owner-account',
      updatedAt: 100,
    });
  });
}

test('active accounts on approved devices can read the organization-wide shared ledger', async () => {
  await seedProfile('reader-account');
  await seedSharedDocument();
  const db = clientDb('reader-account');
  await assertSucceeds(getDoc(doc(db, 'documents', 'shared-document')));
  await assertSucceeds(getDocs(collection(db, 'documents')));
});

test('unapproved, inactive, or key-mismatched devices cannot read shared documents', async () => {
  await seedSharedDocument();
  await seedProfile('pending-account', { deviceStatus: 'PENDING' });
  await seedProfile('inactive-account', { active: false });
  await seedProfile('mismatch-account', { publicKeyHash: 'stored-key' });

  await assertFails(getDoc(doc(clientDb('pending-account', { deviceApproved: false }), 'documents', 'shared-document')));
  await assertFails(getDoc(doc(clientDb('pending-account'), 'documents', 'shared-document')));
  await assertFails(getDoc(doc(clientDb('inactive-account'), 'documents', 'shared-document')));
  await assertFails(getDoc(doc(clientDb('mismatch-account', { keyHash: 'different-key' }), 'documents', 'shared-document')));
});

test('approved users can create their own document but cannot claim another owner or delete records', async () => {
  await seedProfile('creator-account');
  const db = clientDb('creator-account');
  await assertSucceeds(setDoc(doc(db, 'documents', 'new-document'), {
    id: 'new-document',
    type: 'REQUEST',
    status: 'SUBMITTED',
    createdByUid: 'creator-account',
  }));
  await assertFails(setDoc(doc(db, 'documents', 'forged-document'), {
    id: 'forged-document',
    type: 'REQUEST',
    status: 'SUBMITTED',
    createdByUid: 'someone-else',
  }));
  await assertFails(setDoc(doc(db, 'documents', 'self-approved-document'), {
    id: 'self-approved-document',
    type: 'REQUEST',
    status: 'APPROVED',
    createdByUid: 'creator-account',
  }));
  await assertFails(setDoc(doc(db, 'documents', 'self-paid-document'), {
    id: 'self-paid-document',
    type: 'REQUEST',
    status: 'PAID',
    createdByUid: 'creator-account',
  }));
  await assertFails(deleteDoc(doc(db, 'documents', 'new-document')));
});

test('owners may edit and submit drafts but cannot approve or reject their own documents', async () => {
  await seedProfile('draft-owner');
  await environment.withSecurityRulesDisabled(async (context) => {
    await setDoc(doc(context.firestore(), 'documents', 'owner-draft'), {
      id: 'owner-draft',
      type: 'REQUEST',
      status: 'DRAFT',
      createdByUid: 'draft-owner',
    });
  });

  const owner = clientDb('draft-owner');
  await assertSucceeds(updateDoc(doc(owner, 'documents', 'owner-draft'), { notes: 'edited' }));
  await assertSucceeds(updateDoc(doc(owner, 'documents', 'owner-draft'), { status: 'SUBMITTED' }));
  await assertFails(updateDoc(doc(owner, 'documents', 'owner-draft'), { status: 'APPROVED' }));
});

test('branch asset rows use the existing shared ledger owner and no-delete rules', async () => {
  await seedProfile('asset-owner');
  await seedProfile('asset-reader');
  const owner = clientDb('asset-owner');
  const reader = clientDb('asset-reader');
  const asset = doc(owner, 'documents', 'branch-equipment-1');
  await assertSucceeds(setDoc(asset, {
    id: 'branch-equipment-1',
    type: 'BOOK',
    documentNumber: 'BA-EQ-test',
    status: 'DRAFT',
    details: 'MASROF_BRANCH_ASSET_V1|E|encoded-equipment-fields',
    createdByUid: 'asset-owner',
  }));
  await assertSucceeds(getDoc(doc(reader, 'documents', 'branch-equipment-1')));
  await assertSucceeds(updateDoc(asset, { details: 'MASROF_BRANCH_ASSET_V1|E|updated-fields' }));
  await assertFails(updateDoc(doc(reader, 'documents', 'branch-equipment-1'), { details: 'forged' }));
  await assertFails(deleteDoc(asset));
});

test('normal users cannot change another owner document; finance admins may edit but cannot reassign ownership', async () => {
  await seedProfile('ordinary-account');
  await seedProfile('finance-account', { role: 'FINANCE_DIRECTOR' });
  await seedSharedDocument();

  const ordinary = clientDb('ordinary-account');
  await assertFails(updateDoc(doc(ordinary, 'documents', 'shared-document'), { notes: 'unauthorized' }));

  const finance = clientDb('finance-account', { role: 'FINANCE_DIRECTOR' });
  await assertSucceeds(updateDoc(doc(finance, 'documents', 'shared-document'), { notes: 'reviewed' }));
  await assertSucceeds(updateDoc(doc(finance, 'documents', 'shared-document'), { status: 'APPROVED' }));
  await assertFails(updateDoc(doc(finance, 'documents', 'shared-document'), { createdByUid: 'finance-account' }));
});

test('username aliases, access requests, and device records are not client writable/readable as queues', async () => {
  await seedProfile('private-account');
  const db = clientDb('private-account');
  await environment.withSecurityRulesDisabled(async (context) => {
    await setDoc(doc(context.firestore(), 'authAliases', 'private-name'), {
      username: 'private-name',
      authEmail: 'hidden@example.invalid',
      uid: 'private-account',
    });
  });
  await assertFails(getDoc(doc(db, 'authAliases', 'private-name')));
  await assertFails(setDoc(doc(db, 'accessRequests', 'forged-request'), { status: 'PENDING' }));
  await assertFails(updateDoc(doc(db, 'devices', `private-account_${DEVICE_ID}`), { status: 'APPROVED' }));
});
