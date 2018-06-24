package com.smule.singandroid.console;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;

public class StreamVerCmd implements ExtCmd {
    private Context f7121a;

    public StreamVerCmd(Context context) {
        this.f7121a = context;
    }

    public String mo4035a() {
        return "streamver";
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public String mo4036a(String[] strArr) {
        if (strArr.length > 1) {
            try {
                int parseInt = Integer.parseInt(strArr[1]);
                Editor edit = this.f7121a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                edit.putInt("opensl_stream_version", parseInt);
                edit.apply();
            } catch (Throwable e) {
                Log.m7775d("StreamVerCmd", "failed to set delay slider override", e);
            }
        }
        return "" + this.f7121a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getInt("opensl_stream_version", 1);
    }
}
