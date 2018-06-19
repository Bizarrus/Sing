package com.smule.android.logging;

import android.app.Activity;
import com.facebook.places.model.PlaceFields;
import com.smule.android.logging.EventLogger2.Event;
import java.net.URLEncoder;

public abstract class BaseEventLog2Listener implements EventLog2Listener {
    private static void m7722a(StringBuilder stringBuilder, String str, String str2) {
        String encode;
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        if (str != null) {
            try {
                encode = URLEncoder.encode(str, "UTF-8");
            } catch (Throwable e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }
        encode = "";
        stringBuilder.append(encode);
        stringBuilder.append("=");
        stringBuilder.append(str2 != null ? URLEncoder.encode(str2, "UTF-8") : "");
    }

    protected static String m7723b(Event event) {
        if (event == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (event.d != null) {
            m7722a(stringBuilder, PlaceFields.CONTEXT, event.d);
        }
        if (event.c != null) {
            m7722a(stringBuilder, "target", event.c);
        }
        if (event.e != null) {
            m7722a(stringBuilder, "value", event.e);
        }
        return stringBuilder.toString();
    }

    protected String m7724c(Activity activity) {
        if (!(activity instanceof TrackedActivity)) {
            return activity.getClass().getSimpleName();
        }
        TrackedActivity trackedActivity = (TrackedActivity) activity;
        if (trackedActivity.a()) {
            return trackedActivity.b();
        }
        return null;
    }
}
