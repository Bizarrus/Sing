/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  com.adjust.sdk.Adjust
 *  com.adjust.sdk.AdjustEvent
 */
package com.smule.android.ads.attribution;

import android.app.Activity;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustEvent;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;
import java.util.Map;

public class MagicAdjustEventLogger
implements EventLog2Listener {
    private Map<String, String> a;

    public MagicAdjustEventLogger(Map<String, String> map) {
        this.a = map;
    }

    @Override
    public void a(Activity activity) {
    }

    @Override
    public void a(EventLogger2 event) {
        String string2 = this.a.get(event.b);
        if (string2 == null) {
            return;
        }
        string2 = new AdjustEvent(string2);
        string2.addCallbackParameter("event_name", event.b);
        if (event.d != null) {
            string2.addCallbackParameter("context", event.d);
        }
        if (event.c != null) {
            string2.addCallbackParameter("target", event.c);
        }
        if (event.f != null) {
            string2.addCallbackParameter("k1", event.f);
        }
        if (event.j != null) {
            string2.addCallbackParameter("k5", event.j);
        }
        Adjust.trackEvent((AdjustEvent)string2);
    }

    @Override
    public void b(Activity activity) {
    }
}

