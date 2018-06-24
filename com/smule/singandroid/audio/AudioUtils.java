/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.SharedPreferences
 */
package com.smule.singandroid.audio;

import android.content.SharedPreferences;
import com.smule.singandroid.SingApplication;
import java.io.File;

public class AudioUtils {
    public static String a(String string2) {
        Object object = string2;
        if (AudioUtils.a()) {
            Object object2 = SingApplication.g().getSharedPreferences("sing_prefs", 0);
            object = string2;
            if (object2.contains("effects_json_location")) {
                object2 = object2.getString("effects_json_location", "");
                object = string2;
                if (AudioUtils.b((String)object2)) {
                    object = object2;
                }
            }
        }
        return object;
    }

    public static boolean a() {
        boolean bl = false;
        if (SingApplication.g().getSharedPreferences("sing_prefs", 0).getInt("audio_debug_mode", 0) != 0) {
            bl = true;
        }
        return bl;
    }

    public static boolean b(String object) {
        File file = new File((String)object + "/Presets.json");
        object = new File((String)object + "/OTAPresets.json");
        boolean bl = file.exists();
        boolean bl2 = object.exists();
        if (bl && bl2) {
            return true;
        }
        return false;
    }
}

