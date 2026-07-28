package com.heytap.wallet;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.materialswitch.MaterialSwitch;

public class SettingsActivity extends Activity {
    private static final String PREFS = "wallet_prefs";
    private static final String KEY_QUICK_PAY = "quick_pay";

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);

        MaterialSwitch switchQuickPay = findViewById(R.id.switch_quick_pay);
        switchQuickPay.setChecked(prefs.getBoolean(KEY_QUICK_PAY, true));
        switchQuickPay.setOnCheckedChangeListener((buttonView, isChecked) ->
                prefs.edit().putBoolean(KEY_QUICK_PAY, isChecked).apply());
    }
}
