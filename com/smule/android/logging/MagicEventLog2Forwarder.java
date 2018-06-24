/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 */
package com.smule.android.logging;

import android.app.Activity;
import com.smule.android.logging.EventLog2Listener;
import com.smule.android.logging.EventLogger2;

public class MagicEventLog2Forwarder
implements EventLog2Listener {
    private Filter a;
    private EventLog2Listener b;

    public MagicEventLog2Forwarder(Filter filter, EventLog2Listener eventLog2Listener) {
        this.a = filter;
        this.b = eventLog2Listener;
    }

    @Override
    public void a(Activity activity) {
    }

    @Override
    public void a(EventLogger2 event) {
        if (this.a.a(event)) {
            this.b.a(event);
        }
    }

    @Override
    public void b(Activity activity) {
    }

    public static interface Filter {
        public boolean a(EventLogger2 var1);
    }

}

