/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.graphics.Point
 *  android.view.Display
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.view.Display;
import com.smule.android.network.core.MagicNetwork;

public class DeviceUtils {
    public static int a(Display display) {
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    public static String a() {
        int n = MagicNetwork.d().getApplicationContext().getResources().getConfiguration().screenLayout & 15;
        if (n == 1) {
            return "small";
        }
        if (n == 2) {
            return "normal";
        }
        if (n == 3) {
            return "large";
        }
        if (n == 4) {
            return "xlarge";
        }
        return "unknown";
    }

    public static int b(Display display) {
        Point point = new Point();
        display.getSize(point);
        return point.y;
    }
}

