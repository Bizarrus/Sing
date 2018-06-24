package com.smule.singandroid.console;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;

public class AudioDebugCmd implements ExtCmd {
    private Context f7118a;

    public AudioDebugCmd(Context context) {
        this.f7118a = context;
    }

    public String mo4035a() {
        return "audiodebug";
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public String mo4036a(String[] strArr) {
        if (strArr.length > 1) {
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                Editor edit = this.f7118a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                edit.putInt("audio_debug_mode", parseInt);
                edit.apply();
            } catch (Throwable e) {
                Log.m7775d("AudioDebugCmd", "failed to set delay slider override", e);
            }
        }
        return "" + this.f7118a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("audio_debug_mode", 0);
    }
}
