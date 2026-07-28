package com.heytap.wallet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String QUICKDRAW = "com.google.android.apps.wallet.main.QUICKDRAW";
    private static final String WALLET_PKG = "com.google.android.apps.walletnfcrel";
    private static final String PREFS = "wallet_prefs";
    private static final String KEY_QUICK_PAY = "quick_pay";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setShowWhenLocked(true);
        setTurnScreenOn(true);

        boolean quickPay = getSharedPreferences(PREFS, MODE_PRIVATE)
                .getBoolean(KEY_QUICK_PAY, true);
        if (quickPay) {
            launchQuickPay();
        } else {
            launchWallet();
        }
    }

    private void launchQuickPay() {
        try {
            Intent intent = new Intent(QUICKDRAW).setPackage(WALLET_PKG);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            launchWallet();
            return;
        }
        finish();
    }

    private void launchWallet() {
        try {
            Intent intent = getPackageManager().getLaunchIntentForPackage(WALLET_PKG);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Google Wallet is not installed", Toast.LENGTH_SHORT).show();
                finish();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Wallet shim error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
