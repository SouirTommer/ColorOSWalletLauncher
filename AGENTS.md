# AGENTS.md

## What this repo is

Decompiled Android shim APK (likely JADX) — a single-activity redirector.

- **Package:** `com.heytap.wallet`
- **Purpose:** Launches Google Wallet (`com.google.android.apps.walletnfcrel`) via `QUICKDRAW` intent, falls back to generic launch intent, then finishes.
- **No tests, no CI.** `sources/` and `resources/` are decompiled output.

## Build

```
.\gradlew.bat assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

**Requires:** JDK 17+ (Android Studio bundled JDK works). AGP 8.7.3, Gradle 8.11.1.

## Project structure

- `app/src/main/java/com/heytap/wallet/MainActivity.java` — the only source file; the entire app logic.
- `app/src/main/AndroidManifest.xml` — app manifest (min SDK 29, target 34).
- `sources/`, `resources/` — original decompiled output (reference only).
