# ColorOSWalletLauncher

A shim app that lets you use Google Wallet with ColorOS's double-press-power NFC gesture.

## Why

ColorOS hard-codes the double-press-power-button gesture to 4 packages. One of them is `com.heytap.wallet` (OPPO's own wallet), which isn't installed on most phones. Google Wallet is not in the list, so you can't assign it to the gesture.

The fix: a tiny app that takes the empty `com.heytap.wallet` package name and just forwards to Google Wallet. ColorOS then accepts it and lets you pick it in Settings.

- Real Google Wallet is never touched — the shim is a separate redirector.
- Don't clone Google Wallet itself — a re-signed copy fails Play Integrity.

## How it works

1. Install this app as `com.heytap.wallet`
2. ColorOS sees the package and offers it as a gesture target
3. Double-press power → ColorOS launches the shim → shim forwards to Google Wallet
4. The shim runs on the lock screen (`showWhenLocked`) and turns the screen on

### Two launch modes

| Mode | What happens |
|------|-------------|
| **Quick Pay** (default) | Sends `QUICKDRAW` intent directly to Google Wallet — opens the tap-to-pay screen, same as Pixels |
| **Open Wallet** | Falls back to launching Google Wallet's normal main activity |

Toggle between them in Settings (accessed via the launcher shortcut).

## Build

```bash
.\gradlew.bat assembleDebug
```

Output: `app/build/outputs/apk/debug/app-debug.apk`

Requires JDK 17+. If your default JDK is older:

```bash
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-17.0.19.10-hotspot"
.\gradlew.bat assembleDebug
```

## Setup

1. Build and install the APK
2. Go to **Settings → Buttons & Gestures → Double-press power button**
3. Select **ColorOSWalletLauncher**
4. Double-press power on lock screen → Google Wallet opens

## Project structure

```
app/src/main/
  java/com/heytap/wallet/
    MainActivity.java      # Shim: forwards to Google Wallet
    SettingsActivity.java  # Quick Pay / Open Wallet toggle
  AndroidManifest.xml      # Package name + lock screen flags
  res/
    layout/activity_settings.xml
    xml/shortcuts.xml      # Launcher shortcut to Settings
```

`sources/` and `resources/` are the original decompiled output (reference only).

## Permissions

- `<queries>` declares `com.google.android.apps.walletnfcrel` so `getLaunchIntentForPackage()` works on Android 11+

## License

MIT