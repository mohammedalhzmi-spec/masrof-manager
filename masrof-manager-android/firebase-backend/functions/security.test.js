'use strict';

const assert = require('node:assert/strict');
const crypto = require('node:crypto');
const test = require('node:test');
const {
  aliasDocumentIds,
  buildChallenge,
  isValidDeviceId,
  normalizeIdentifier,
  publicKeyInfo,
  verifyChallengeSignature,
} = require('./security');

test('normalizes login identifiers and preserves legacy alias document variants', () => {
  assert.equal(normalizeIdentifier('  Manager/West  '), 'manager/west');
  assert.deepEqual(aliasDocumentIds(' User@Domain '), ['user@domain', 'user_domain']);
  assert.deepEqual(aliasDocumentIds('user:west'), ['user:west', 'user_west']);
});

test('accepts Android P-256 device keys and derives a stable public-key hash', () => {
  const pair = crypto.generateKeyPairSync('ec', { namedCurve: 'prime256v1' });
  const publicKey = pair.publicKey.export({ format: 'der', type: 'spki' }).toString('base64');
  const first = publicKeyInfo(publicKey);
  const second = publicKeyInfo(publicKey);
  assert.equal(first.hash, second.hash);
  assert.equal(first.keyObject.asymmetricKeyType, 'ec');
});

test('verifies a signed, one-time challenge and rejects a modified challenge', () => {
  const pair = crypto.generateKeyPairSync('ec', { namedCurve: 'prime256v1' });
  const publicKey = pair.publicKey.export({ format: 'der', type: 'spki' }).toString('base64');
  const challenge = buildChallenge({ uid: 'uid-1', deviceId: 'android-id', publicKeyHash: publicKeyInfo(publicKey).hash, expiresAtMs: 1234, nonce: 'random' });
  const challengeBase64 = challenge.toString('base64');
  const signature = crypto.sign('sha256', challenge, pair.privateKey).toString('base64');
  assert.equal(verifyChallengeSignature(publicKey, challengeBase64, signature), true);
  assert.equal(verifyChallengeSignature(publicKey, Buffer.from('different').toString('base64'), signature), false);
});

test('rejects malformed keys, signatures, and unsafe Firestore device IDs', () => {
  assert.throws(() => publicKeyInfo('not-base64'));
  assert.equal(verifyChallengeSignature('bad', 'YQ==', 'Yg=='), false);
  assert.equal(isValidDeviceId('a1b2c3'), true);
  assert.equal(isValidDeviceId('device/id'), false);
  assert.equal(isValidDeviceId(''), false);
});
