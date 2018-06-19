package com.smule.android.utils;

import android.graphics.Point;
import android.view.Display;
import com.smule.android.network.core.MagicNetwork;

public class DeviceUtils {
    public static String m18957a() {
        int i = MagicNetwork.d().getApplicationContext().getResources().getConfiguration().screenLayout & 15;
        if (i == 1) {
            return "small";
        }
        if (i == 2) {
            return "normal";
        }
        if (i == 3) {
            return "large";
        }
        if (i == 4) {
            return "xlarge";
        }
        return "unknown";
    }

    public static int m18956a(Display display) {
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    public static int m18958b(Display display) {
        Point point = new Point();
        display.getSize(point);
        return point.y;
    }
}
