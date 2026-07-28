package com.heytap.wallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends Activity {
    private static final String QUICKDRAW = "com.google.android.apps.wallet.main.QUICKDRAW";
    private static final String WALLET_PKG = "com.google.android.apps.walletnfcrel";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setShowWhenLocked(true);
        setTurnScreenOn(true);
        try {
            startActivity(new Intent(QUICKDRAW).setPackage(WALLET_PKG).addFlags(268435456));
        } catch (Exception e) {
            try {
                Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(WALLET_PKG);
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(268435456);
                    startActivity(launchIntentForPackage);
                } else {
                    Toast.makeText(this, "Google Wallet is not installed", 0).show();
                }
            } catch (Exception e2) {
                Toast.makeText(this, "Wallet shim error: " + e2.getMessage(), 1).show();
            }
        }
        finish();
    }
}
