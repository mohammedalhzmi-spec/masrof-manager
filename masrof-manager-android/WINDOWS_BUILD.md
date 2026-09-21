# Windows build status

The current repository is an Android application built with Kotlin, Jetpack Compose, and Room. The present Linux build environment does not contain a Windows toolchain (.NET/WinUI, Kotlin Compose Desktop Windows target, Wine, or a Windows cross-compiler), so a genuine `.exe` cannot be produced or validated here without changing the product architecture.

## Recommended production path

To produce a Windows edition with the same official templates, document storage, PDF/PNG export, sharing, and settings, create a Windows target using Kotlin Multiplatform + Compose Desktop or .NET/WPF. The document model and official renderer should be shared, while Android Room/FileProvider integrations should be replaced by a desktop database and native file-share/print integrations.

## Current deliverable

The Android edition is built and tested in this repository. The Android code now includes per-document logo settings, local persistence, editing, multi-document PDF export, PNG export, direct printing, and sharing. A Windows implementation should be added as a separate target rather than mislabeled as an Android APK.
