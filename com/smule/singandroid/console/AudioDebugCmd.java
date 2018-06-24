/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 */
package com.smule.singandroid.console;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;

public class AudioDebugCmd
implements ExtCmd {
    private Context a;

    public AudioDebugCmd(Context context) {
        this.a = context;
    }

    @Override
    public String a() {
        return "audiodebug";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @Override
    public String a(String[] editor) {
        int n;
        if (editor.length > 1) {
            try {
                n = Integer.parseInt((String)editor[1]);
                editor = this.a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                editor.putInt("audio_debug_mode", n);
                editor.apply();
            }
            catch (NumberFormatException numberFormatException) {
                Log.d("AudioDebugCmd", "failed to set delay slider override", numberFormatException);
            }
        }
        n = this.a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("audio_debug_mode", 0);
        return "" + n;
    }
}

