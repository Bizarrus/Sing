package com.smule.singandroid.console;

import android.content.Context;
import com.facebook.AccessToken;
import com.smule.android.console.ExtCmd;
import com.smule.android.network.core.MagicNetwork;
import java.util.Arrays;

public class SessionsCmd implements ExtCmd {
    private Context f7120a;

    public SessionsCmd(Context context) {
        this.f7120a = context;
    }

    public String mo4035a() {
        return "sclr";
    }

    public String mo4036a(String[] strArr) {
        String str = "";
        if (Arrays.asList(strArr).contains("snp")) {
            MagicNetwork.m7789a().m7826m();
            this.f7120a.getSharedPreferences("network", 0).edit().putString("session", "").putLong("session_time", 0).apply();
            str = str + "[snp session cleared]";
        }
        if (!Arrays.asList(strArr).contains("fb")) {
            return str;
        }
        AccessToken.setCurrentAccessToken(null);
        return str + "[fb session cleared]";
    }
}
