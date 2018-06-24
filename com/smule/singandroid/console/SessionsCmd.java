/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  com.facebook.AccessToken
 */
package com.smule.singandroid.console;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.AccessToken;
import com.smule.android.console.ExtCmd;
import com.smule.android.network.core.MagicNetwork;
import java.util.Arrays;

public class SessionsCmd
implements ExtCmd {
    private Context a;

    public SessionsCmd(Context context) {
        this.a = context;
    }

    @Override
    public String a() {
        return "sclr";
    }

    @Override
    public String a(String[] arrstring) {
        String string2 = "";
        if (Arrays.asList(arrstring).contains("snp")) {
            MagicNetwork.a().m();
            this.a.getSharedPreferences("network", 0).edit().putString("session", "").putLong("session_time", 0).apply();
            string2 = "" + "[snp session cleared]";
        }
        String string3 = string2;
        if (Arrays.asList(arrstring).contains("fb")) {
            AccessToken.setCurrentAccessToken((AccessToken)null);
            string3 = string2 + "[fb session cleared]";
        }
        return string3;
    }
}

