/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 */
package com.smule.singandroid.console;

import android.content.Context;
import android.content.SharedPreferences;
import com.smule.android.console.ExtCmd;
import com.smule.android.logging.Log;
import com.smule.singandroid.audio.AudioUtils;

public class EffectsJsonCmd
implements ExtCmd {
    private Context a;

    public EffectsJsonCmd(Context context) {
        this.a = context;
    }

    @Override
    public String a() {
        return "effectsjson";
    }

    @Override
    public String a(String[] object) {
        if (object.length > 1) {
            SharedPreferences.Editor editor;
            block6 : {
                editor = this.a.getApplicationContext().getSharedPreferences("sing_prefs", 0).edit();
                object = object[1];
                if (!object.equals("clear")) break block6;
                editor.remove("effects_json_location");
                editor.apply();
                return "setting cleared!";
            }
            try {
                if (AudioUtils.b((String)object)) {
                    editor.putString("effects_json_location", (String)object);
                    editor.apply();
                    return "Json location saved!";
                }
                return "Location does not contain both Presets.json and OTAPresets.json.";
            }
            catch (NumberFormatException numberFormatException) {
                Log.d("EffectsJsonCmd", "failed to set Effects location", numberFormatException);
            }
        }
        return this.a.getApplicationContext().getSharedPreferences("sing_prefs", 0).getString("effects_json_location", "No Json location stored.");
    }
}

