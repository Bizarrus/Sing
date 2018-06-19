package com.smule.android.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smule.android.logging.Log;

public class HomeKeyWatcher {
    private OnHomeKeyPressedListener f17767a;

    private class InnerReceiver extends BroadcastReceiver {
        final /* synthetic */ HomeKeyWatcher f17766a;

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                String stringExtra = intent.getStringExtra("reason");
                if (stringExtra != null) {
                    Log.c("HomeKeyBR", "receive action:" + action + ",reason:" + stringExtra);
                    if (this.f17766a.f17767a != null && stringExtra.equals("homekey")) {
                        this.f17766a.f17767a.m18965a();
                    }
                }
            }
        }
    }

    public interface OnHomeKeyPressedListener {
        void m18965a();
    }
}
