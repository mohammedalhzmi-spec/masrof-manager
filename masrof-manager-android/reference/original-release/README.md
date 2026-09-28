# Reconstructed reference from the original Android release

This directory preserves a **reference extraction** from the user-provided release APK (`com.mohammedalhzmi.masrofmanager`, version `1.1.0`, versionCode `2`). It was produced with JADX 1.5.6 by static inspection only; the APK was not executed.

## Contents

- `sources/`: 120 reconstructed app Java files, including the original cloud-sync, data, document, print/export, and Compose UI classes.
- `resources/`: 674 decoded resource files, including the manifest, layouts/assets, and resource XML.
- `SHA256SUMS.txt`: integrity hashes for the copied source and decoded resource files.

## This is not the original Android Studio project

The APK contained Kotlin bytecode. JADX reconstructed Java-like source; this is **not** the original Kotlin source, comments, Gradle project, or a drop-in build. JADX reported decompilation errors, so reconstructed methods can be incomplete or inaccurate. Keep this directory outside `app/src`; port verified behavior into the maintained Kotlin project under `app/src/main` and test it there. Do not build or execute files from this reference directory.

## Redactions and exclusions

The GitHub repository is public. Before adding this reference, the manager email was replaced with `[REDACTED_EMAIL]`, and six Firebase/Google client configuration values in decoded XML were replaced with `REDACTED_FOR_PUBLIC_REPOSITORY`. The reference intentionally excludes the original APK binary, keystore, passwords, Firebase service-account credentials, Room database snapshots, and user records. The app-lock source contains preference-key names and hashing logic, not a user's stored PIN/password.

Firebase client configuration and security rules must be managed securely outside this public reference. A client API key or app ID is not a substitute for correctly configured Firebase Authentication and Firestore security rules.
