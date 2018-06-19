package com.smule.android.console;

import android.util.DisplayMetrics;
import android.view.Display;
import com.smule.android.C3482R;
import com.smule.android.utils.DeviceUtils;

public class UiCmd {
    private UiCmd() {
    }

    public static void m17569a(Display display, StdOut stdOut) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.display_height) + ": " + DeviceUtils.m18958b(display));
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.display_width) + ": " + DeviceUtils.m18956a(display));
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_density) + ": " + displayMetrics.density);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_densityDpi) + ": " + displayMetrics.densityDpi);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_scaledDensity) + ": " + displayMetrics.scaledDensity);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_heightPixels) + ": " + displayMetrics.heightPixels);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_widthPixels) + ": " + displayMetrics.widthPixels);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_xdpi) + ": " + displayMetrics.xdpi);
        stdOut.mo6237b(CFunc.m17512a(C3482R.string.metrics_ydpi) + ": " + displayMetrics.ydpi);
    }
}
