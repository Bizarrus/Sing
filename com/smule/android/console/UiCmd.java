/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.util.DisplayMetrics
 *  android.view.Display
 */
package com.smule.android.console;

import android.util.DisplayMetrics;
import android.view.Display;
import com.smule.android.R;
import com.smule.android.console.CFunc;
import com.smule.android.console.StdOut;
import com.smule.android.utils.DeviceUtils;

public class UiCmd {
    private UiCmd() {
    }

    public static void a(Display display, StdOut stdOut) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        stdOut.b(CFunc.a(R.string.display_height) + ": " + DeviceUtils.b(display));
        stdOut.b(CFunc.a(R.string.display_width) + ": " + DeviceUtils.a(display));
        stdOut.b(CFunc.a(R.string.metrics_density) + ": " + displayMetrics.density);
        stdOut.b(CFunc.a(R.string.metrics_densityDpi) + ": " + displayMetrics.densityDpi);
        stdOut.b(CFunc.a(R.string.metrics_scaledDensity) + ": " + displayMetrics.scaledDensity);
        stdOut.b(CFunc.a(R.string.metrics_heightPixels) + ": " + displayMetrics.heightPixels);
        stdOut.b(CFunc.a(R.string.metrics_widthPixels) + ": " + displayMetrics.widthPixels);
        stdOut.b(CFunc.a(R.string.metrics_xdpi) + ": " + displayMetrics.xdpi);
        stdOut.b(CFunc.a(R.string.metrics_ydpi) + ": " + displayMetrics.ydpi);
    }
}

