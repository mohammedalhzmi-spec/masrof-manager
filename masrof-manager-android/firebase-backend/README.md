# Android Firebase backend

This folder contains Firebase Functions and Firestore rules used only by the Android application. It is deliberately separate from repository-root configuration and Web code. Do not deploy from the repository root.

## Security design

`beginCloudLogin` resolves a legacy username through `authAliases` on the server and verifies the supplied password against Firebase Auth. It never returns the mapped email, REST ID token, or refresh token. `completeCloudLogin` requires a fresh ECDSA P-256 signature from a private key held in Android Keystore, creates/updates the device request, and returns a Firebase custom token for the existing UID. Device listing/approval and token refresh are also server-side callables; device re-requests and approvals are transactional to avoid stale status overwrites. Firestore rules deny client reads of `authAliases`, and only active sessions tied to an approved device may read the shared `documents` ledger. Ordinary users may create only `DRAFT`/`SUBMITTED` records and cannot set or change approval/payment states; finance administrators manage those transitions. Client-side document deletions and client-written device approvals are denied.

For existing installs, the first secure sign-in binds an Android Keystore public key to that install's existing Android ID. Because old device records have no key binding, a record without a matching key becomes `PENDING` and must be approved once. A `SYSTEM_ADMIN` may bootstrap the first trusted admin device only while no active, approved `SYSTEM_ADMIN` device exists in the project; once one exists, it must approve all new devices. A fresh password login is required for administrative approval. This prevents a copied Android ID alone from impersonating an approved install.

App Check enforcement is intentionally not enabled yet: the installed APK is sideloaded, and its production App Check provider/distribution compatibility has not been verified. Login attempts are rate-limited server-side and Firebase Auth's own protection remains in effect. Before production deployment, configure and test an App Check provider suitable for the actual distribution channel; only then enable `enforceAppCheck` on callable endpoints.

## Local checks

From this folder, install/check the deployed Functions package:

```sh
npm --prefix functions install
npm --prefix functions test
npm --prefix functions run check
npm --prefix functions audit --omit=dev --audit-level=moderate
```

The separate `rules-tests/` package contains the Firebase client SDK and `@firebase/rules-unit-testing`; it is not included in the deployed Functions source. Run the Firestore authorization tests with Firebase CLI and the local emulator:

```sh
npm --prefix rules-tests install
firebase emulators:exec --config firebase.json --only firestore --project demo-masrof-manager "cd rules-tests && npm test"
```

The Functions package uses Node.js 22 and Firebase Functions v2. `functions/.env.example` is a template only. For deployment, create `functions/.env.<firebase-project-id>` with `FIREBASE_WEB_API_KEY` from the matching Android `google-services.json`; this is a public client API key, not an Admin credential. The local `.env.*` file is ignored by Git. Never place a service-account key, keystore, or password in this repository.

## Deployment boundary

No Firebase deployment has been performed. These rules are a proposed replacement for the current production rules and need a schema/role review before publication. When authorized, use a Firebase project owner/admin session and deploy only from this folder:

```sh
firebase deploy --config firebase.json --project <existing-firebase-project-id> --only functions,firestore
```

The repository-root `firestore.rules` and root Firebase configuration (if present) are outside this Android backend and must remain untouched. Review the target project and rules before applying anything live. Existing account data and document records are not deleted by these rules or functions.
