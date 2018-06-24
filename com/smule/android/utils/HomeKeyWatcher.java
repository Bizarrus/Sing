/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 */
package com.smule.android.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smule.android.logging.Log;

public class HomeKeyWatcher {
    private OnHomeKeyPressedListener a;

    private class InnerReceiver
    extends BroadcastReceiver {
        final /* synthetic */ HomeKeyWatcher a;

        public void onReceive(Context object, Intent object2) {
            object = object2.getAction();
            if (object.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && (object2 = object2.getStringExtra("reason")) != null) {
                Log.c("HomeKeyBR", "receive action:" + (String)object + ",reason:" + (String)object2);
                if (this.a.a != null && object2.equals("homekey")) {
                    this.a.a.a();
                }
            }
        }
    }

    public static interface OnHomeKeyPressedListener {
        public void a();
    }

}

