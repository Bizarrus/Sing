package com.smule.singandroid.console;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;

public class DelaySliderCmd implements ExtCmd {
    private Context f7119a;

    public DelaySliderCmd(Context context) {
        this.f7119a = context;
    }

    public String mo4035a() {
        return "delayslider";
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public String mo4036a(String[] strArr) {
        if (strArr.length > 1) {
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                Editor edit = this.f7119a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                edit.putInt("delay_slider_visibility", parseInt);
                edit.apply();
            } catch (Throwable e) {
                Log.m7775d("DelaySliderCmd", "failed to set delay slider override", e);
            }
        }
        return "" + this.f7119a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("delay_slider_visibility", 0);
    }
}
