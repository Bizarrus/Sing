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
import com.smule.android.logging.TrackedActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public abstract class BaseEventLog2Listener
implements EventLog2Listener {
    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void a(StringBuilder stringBuilder, String string2, String string3) {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        if (string2 != null) {
            try {
                string2 = URLEncoder.encode(string2, "UTF-8");
            }
            catch (UnsupportedEncodingException unsupportedEncodingException) {
                throw new RuntimeException("This method requires UTF-8 encoding support", unsupportedEncodingException);
            }
        } else {
            string2 = "";
        }
        stringBuilder.append(string2);
        stringBuilder.append("=");
        string2 = string3 != null ? URLEncoder.encode(string3, "UTF-8") : "";
        stringBuilder.append(string2);
    }

    protected static String b(EventLogger2 event) {
        if (event == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (event.d != null) {
            BaseEventLog2Listener.a(stringBuilder, "context", event.d);
        }
        if (event.c != null) {
            BaseEventLog2Listener.a(stringBuilder, "target", event.c);
        }
        if (event.e != null) {
            BaseEventLog2Listener.a(stringBuilder, "value", event.e);
        }
        return stringBuilder.toString();
    }

    protected String c(Activity object) {
        if (object instanceof TrackedActivity) {
            if (!(object = (TrackedActivity)object).a()) {
                return null;
            }
            return object.b();
        }
        return object.getClass().getSimpleName();
    }
}

