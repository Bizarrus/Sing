/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  com.facebook.appevents.AppEventsLogger
 */
package com.smule.android.facebook;

import android.app.Activity;
import android.content.Context;
import com.facebook.appevents.AppEventsLogger;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;
import java.util.Map;

public class MagicFacebookEventLogger
implements EventLog2Listener {
    private Map<String, String> a;
    private AppEventsLogger b;

    public MagicFacebookEventLogger(Context context, Map<String, String> map) {
        this.a = map;
        this.b = AppEventsLogger.newLogger((Context)context.getApplicationContext());
    }

    @Override
    public void a(Activity activity) {
    }

    @Override
    public void a(EventLogger2 object) {
        object = this.a.get(object.b);
        if (object != null) {
            this.b.logEvent((String)object);
        }
    }

    @Override
    public void b(Activity activity) {
    }
}

