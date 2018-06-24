package com.smule.singandroid.audio;

import com.smule.singandroid.SingApplication;

public class AudioUtils {
    public static boolean m22280a() {
        if (SingApplication.f().getSharedPreferences("sing_prefs", 0).getInt("audio_debug_mode", 0) != 0) {
            return true;
        }
        return false;
    }
}
