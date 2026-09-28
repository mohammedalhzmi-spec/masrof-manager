# Android source and compatibility guide

## Source of truth

The maintained, buildable Android application is `masrof-manager-android/app/src/main`. The original APK reconstruction is retained only as a reference in [`reference/original-release/`](reference/original-release/README.md); it is deliberately outside Gradle source sets and must not be compiled or copied wholesale into the app.

The original release APK was version `1.1.0` (versionCode `2`). Its decompiled Java-like output is not equivalent to the original Kotlin project and contains decompilation errors. Use the reference to compare behavior, then port changes into the maintained Kotlin sources.

## Document type parity

The original persisted enum contains these 14 values:

`REQUEST`, `ORDER`, `RECEIPT`, `RECEIPT_PAPER`, `FINANCIAL_MEMO`, `PURCHASE_ORDER`, `SUPPLY_PERMIT`, `RECEIPT_MINUTES`, `FINANCIAL_CLAIM`, `CUSTODY_SETTLEMENT`, `ADVANCE_PERMIT`, `EXPENSE_STATEMENT`, `OFFICIAL_FINANCIAL_LETTER`, `BOOK`.

The Android chooser exposes the original createable document types plus `VIOLATION_REPORT`. `BOOK` remains the separate document-book workflow, as in the original APK. `EXPENSE_REPORT` is retained only as a compatibility alias for records created by the earlier feature build; new expense statements use the original `EXPENSE_STATEMENT` value.

## Local data

The active local database is Room (`MasrofDatabase`, schema version 12). The 11-to-12 migration adds `cloudId` and violation-report fields only when missing, preserving existing records. Current Room entities cover documents, organization profile/settings, contacts, users, audit logs, document designs, and design elements. The app also has local database/full-backup export and restore code. Before any future schema change, add and test a non-destructive migration; do not use destructive fallback for user data.

## Cloud synchronization — important integration boundary

The original APK reconstruction contains `cloud/FirebaseCloudSync.java`, with Firebase Auth/Firestore account, username/profile, email-verification/reset, device-approval, and document-sync flows. It is preserved in the reference directory.

Those reconstructed cloud flows are **not currently active in the maintained Kotlin app**: the current Android Gradle file has Firebase Auth and Firestore dependencies commented out, there is no Android `google-services.json` in the repository, and no maintained `FirebaseCloudSync` Kotlin service is wired into the app. The active app currently uses local Room storage; local backup/export is not the same thing as cloud synchronization.

The APK does not provide the Firebase project's authoritative Firestore security rules or a safe deployment/configuration workflow. Do not enable cloud writes based only on decompiled client code. To port cloud sync safely, first obtain the correct Firebase project configuration and Firestore rules from the project owner, then implement and test the sync against the current `Document`/`MasrofRepository` model, including conflict resolution, deletion/archive semantics, authorization, and the original `cloudId` mapping. Keep configuration and private credentials out of Git.

## Release signing

The expected original signing file is `keystore/masrof-release.jks` with alias `masrof-release`; passwords are not stored in source control. The keystore file itself is not present in this workspace. Passwords alone cannot recreate a private signing key. Supply the original keystore through a secure channel and pass passwords as environment variables only. Rotate any signing passwords previously shared in chat before release use.
