# Android source and compatibility guide

## Source of truth

The maintained, buildable Android application is `masrof-manager-android/app/src/main`. The original APK reconstruction is retained only as a reference in [`reference/original-release/`](reference/original-release/README.md); it is deliberately outside Gradle source sets and must not be compiled or copied wholesale into the app.

The original release APK was version `1.1.0` (versionCode `2`). Its decompiled Java-like output is not equivalent to the original Kotlin project and contains decompilation errors. Use the reference to compare behavior, then port changes into the maintained Kotlin sources.

## Document type parity

The original persisted enum contains these 14 values:

`REQUEST`, `ORDER`, `RECEIPT`, `RECEIPT_PAPER`, `FINANCIAL_MEMO`, `PURCHASE_ORDER`, `SUPPLY_PERMIT`, `RECEIPT_MINUTES`, `FINANCIAL_CLAIM`, `CUSTODY_SETTLEMENT`, `ADVANCE_PERMIT`, `EXPENSE_STATEMENT`, `OFFICIAL_FINANCIAL_LETTER`, `BOOK`.

The Android chooser exposes the original createable document types plus `VIOLATION_REPORT`. `BOOK` remains the separate document-book workflow, as in the original APK. `EXPENSE_REPORT` is retained only as a compatibility alias for records created by the earlier feature build; new expense statements use the original `EXPENSE_STATEMENT` value.

## Local data

The active local database is Room (`MasrofDatabase`, schema version 13). The 11-to-12 migration adds `cloudId` and violation-report fields only when missing; the 12-to-13 migration adds `createdByUid` with an empty default. Both are additive and preserve existing records. Current Room entities cover documents, organization profile/settings, contacts, users, audit logs, document designs, and design elements. The app also has local database/full-backup export and restore code. Before any future schema change, add and test a non-destructive migration; do not use destructive fallback for user data.

## Cloud synchronization — important integration boundary

The maintained Android source now includes `cloud/FirebaseCloudSyncService.kt` and `cloud/CloudDocumentMapper.kt`. The Settings screen supports signing in to an existing Firebase account by email or username alias, checking device approval, administrator approval of pending devices, and **explicit/manual** synchronization. It uses the original Android ID device key, existing username-alias/fallback email behavior, and legacy Firestore field names where needed.

Synchronization first reads a server snapshot, then merges into Room by `cloudId` (with a cautious fingerprint check for legacy `android_<numeric-id>` records). It never clears local data or applies remote deletions. Local-only records receive stable UUID cloud IDs; local edits are uploaded only when the Firestore owner/role rules permit. Equal-timestamp differences are preserved locally and reported as conflicts. Newly added document ownership metadata uses additive Room migration 12-to-13.

The integration is **not production-ready until the correct Firebase project configuration and reviewed Firestore rules are installed**. `google-services.json` must match the existing Firebase project and application ID and must remain untracked. The selected sharing behavior is that active, approved accounts can read the shared document collection; writes should remain restricted by owner/finance/admin rules. Do not enable production sync based only on the decompiled client or the local draft rules. No production sync or rules deployment has been performed from this workspace.

## Release signing

Release signing must use the original private keystore outside the Git repository, with passwords passed only as environment variables. Before generating an update APK, verify that the keystore certificate matches the certificate of the user's currently installed release. Never commit the keystore, `google-services.json`, passwords, or local SDK configuration.
