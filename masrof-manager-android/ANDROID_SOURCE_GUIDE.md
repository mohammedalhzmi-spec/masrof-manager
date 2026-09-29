# Android source and compatibility guide

## Source of truth

The maintained Android application is `masrof-manager-android/app/src/main`. The original APK reconstruction is retained only as a behavior reference in [`reference/original-release/`](reference/original-release/README.md); it is outside Gradle source sets and must not be compiled or copied wholesale into the app. Decompiled output can contain errors.

The original release APK was version `1.1.0` (versionCode `2`). Keep the same application ID and original signing certificate for update compatibility.

## Document type parity

The original persisted enum contains these 14 values:

`REQUEST`, `ORDER`, `RECEIPT`, `RECEIPT_PAPER`, `FINANCIAL_MEMO`, `PURCHASE_ORDER`, `SUPPLY_PERMIT`, `RECEIPT_MINUTES`, `FINANCIAL_CLAIM`, `CUSTODY_SETTLEMENT`, `ADVANCE_PERMIT`, `EXPENSE_STATEMENT`, `OFFICIAL_FINANCIAL_LETTER`, `BOOK`.

The Android chooser exposes the original createable types plus `VIOLATION_REPORT`. `BOOK` remains a separate document-book workflow. `EXPENSE_REPORT` is retained only as a compatibility alias for records created by an earlier feature build; new monthly statements use `EXPENSE_STATEMENT`.

## Four-chapter monthly expense book

`EXPENSE_STATEMENT` is presented to users as **دفتر مصروفات** and prints/export as four A4 pages (printed pages 3–6). Chapter totals are entered on the Android form; the general total is the sum of chapters 1–3. The fixed comparison amount is 952,000 YER, and only an excess above that amount is added as a separate debt row in chapter four, labelled with the report month. The fourth-page base debt rows begin with the reference values (230,000, 83,000, 617,000) and remain editable.

The chapter totals and debt amounts are stored in a versioned payload inside the existing `Document.details` field, so no Room schema migration is needed and the existing Firestore mapper syncs them with the document. When an older expense statement has an amount but no chapter breakdown, that prior amount is preserved and is not reclassified as new debt; entering chapter totals replaces the legacy fallback.

## Local data

The active local database is Room (`MasrofDatabase`, schema version 13). The v11-to-v12 migration adds `cloudId` and violation-report fields only when missing; v12-to-v13 adds `createdByUid` with an empty default. Both migrations are additive and preserve existing records. Before any future schema change, add and test a non-destructive migration; never use destructive fallback for user data.

## Android cloud sync and secure username flow

The Android implementation is in `app/src/main/java/.../cloud/`:

- `FirebaseCloudSyncService.kt` owns explicit/manual sync and callable-function access.
- `CloudDocumentMapper.kt` preserves original Firestore field names and legacy records.
- `AndroidCloudDeviceIdentity.kt` creates a P-256 signing key in Android Keystore; the private key never leaves the device.

The login callables resolve the existing `authAliases` username mapping only on the server, verify the supplied password with Firebase Auth, discard the REST ID/refresh tokens, and issue a Firebase custom token for the existing UID. The mapped email is not returned to Android. A short-lived challenge must be signed by the install's Keystore private key. Device registration, pending queues, admin approval, and session-claim refresh are server-managed; the Android client cannot write device approvals directly.

The selected sharing policy is preserved: active accounts on approved devices can read the shared organization-wide `documents` ledger. Ordinary users may create only `DRAFT`/`SUBMITTED` documents, may not self-approve or self-pay, and may edit only their own drafts/submitted documents without changing status (except `DRAFT` to `SUBMITTED`). Finance/system administrators manage workflow transitions. Firestore rules preserve document ownership, deny client deletes, and deny client access to username aliases and approval queues. Existing device records without a key binding will need one-time re-approval after the secure flow is deployed. A `SYSTEM_ADMIN` may bootstrap the first trusted admin device only while no active, approved `SYSTEM_ADMIN` device exists in the project; once one exists, it must approve all new devices.

Synchronization remains **manual and merge-only**: fetch a server snapshot, match by `cloudId` (with cautious fingerprint matching for legacy `android_<numeric-id>` IDs), preserve ownerless legacy ownership, and never clear local data or apply remote deletions. Creates and updates use Firestore transactions as compare-and-set operations against the fetched document, so concurrent remote edits are reported as conflicts rather than silently overwritten. Local-only records get stable UUID cloud IDs.

## Android-only Firebase backend and validation

`firebase-backend/` contains its own Firebase config, Node.js 22 Functions v2 package, Firestore rules, TTL indexes, and a separate development-only `rules-tests/` package. This stays separate from repository-root/Web deployment configuration. Never deploy from the repository root.

Validated locally:

- Android Kotlin compilation, all 14 `:app:testDebugUnitTest` tests (including native-graphics rendering of all four expense-book pages), and debug APK packaging succeeded after the four-chapter expense-book update.
- Backend P-256 challenge/alias tests passed (4 tests); Node syntax and module-load checks passed. The production Functions dependency audit reported zero vulnerabilities after pinning the patched UUID transitive dependency.
- Firestore Emulator rule authorization tests passed (6 tests): approved shared reads succeed; inactive, unapproved, and key-mismatched devices are denied; ordinary users cannot create approved/paid records or self-approve; finance/admin transitions, ownership, no-delete behavior, and private alias/approval collections are enforced.
- The Firestore Emulator started with the Android-scoped config; no production project was contacted or modified.

The app's `google-services.json` was recovered locally from the original APK and remains Git-ignored. It has not been independently compared with the current production Firebase Console export. Confirm project ID, Android app registration, API key, roles, and production schema before rollout.

**No production Firebase Functions or Firestore rules have been deployed.** Deployment requires an authorized Firebase project owner/admin session and a final review of the current live rules and data fields. `functions/.env.<project-id>` must be created locally with `FIREBASE_WEB_API_KEY` from the matching `google-services.json`; it is ignored and must never be committed. Firebase App Check is not yet enforced because the installed/sideloaded distribution's provider compatibility has not been validated; configure and test an appropriate provider before enabling enforcement.

Local backend checks (run from `firebase-backend/`):

```sh
npm --prefix functions install
npm --prefix functions test
npm --prefix functions run check
npm --prefix rules-tests install
firebase emulators:exec --config firebase.json --only firestore --project demo-masrof-manager "cd rules-tests && npm test"
```

When deployment access and final project review are available, deploy only from `firebase-backend/` with its config. This Android backend must not alter the repository-root Web app or its Firebase configuration.

## Release signing

Release updates must use the original private keystore outside the Git repository. The certificate matched the original APK during the earlier build; keep the same application ID, version continuity, and certificate. Pass signing passwords only through hidden prompts/environment variables. Never commit the keystore, `google-services.json`, passwords, Firebase deployment credentials, or local SDK configuration.
