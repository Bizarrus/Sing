package com.smule.singandroid.console;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;

public class DelayCmd implements ExtCmd {
    private Context f21424a;

    public String m23094a() {
        return "delay";
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public String m23095a(String[] strArr) {
        if (strArr.length > 1) {
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                Editor edit = this.f21424a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                edit.putInt("real_delay_comp", parseInt);
                edit.putBoolean("AUDIOCALIBRATED", true);
                edit.apply();
            } catch (Throwable e) {
                Log.d("DelayCmd", "failed to set new latency", e);
            }
        }
        return "" + this.f21424a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("real_delay_comp", 0);
    }
}
