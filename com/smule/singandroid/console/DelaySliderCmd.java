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

public class DelaySliderCmd
implements ExtCmd {
    private Context a;

    public DelaySliderCmd(Context context) {
        this.a = context;
    }

    @Override
    public String a() {
        return "delayslider";
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
                editor.putInt("delay_slider_visibility", n);
                editor.apply();
            }
            catch (NumberFormatException numberFormatException) {
                Log.d("DelaySliderCmd", "failed to set delay slider override", numberFormatException);
            }
        }
        n = this.a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("delay_slider_visibility", 0);
        return "" + n;
    }
}

