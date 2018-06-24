package com.smule.singandroid.customviews;

import android.graphics.Point;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class WindowLocationBroadcastingView extends View {
    private static final String f22097b = WindowLocationBroadcastingView.class.getName();
    protected LocationChangeListener f22098a;

    class C44511 implements OnPreDrawListener {
        final /* synthetic */ WindowLocationBroadcastingView f22096a;

        public boolean onPreDraw() {
            int[] iArr = new int[2];
            this.f22096a.getLocationOnScreen(iArr);
            if (this.f22096a.f22098a != null) {
                this.f22096a.f22098a.m23555a(this.f22096a, new Point(iArr[0], iArr[1]));
            }
            return true;
        }
    }

    public interface LocationChangeListener {
        void m23555a(View view, Point point);
    }

    private synchronized void setLocationChangeListener(LocationChangeListener locationChangeListener) {
        this.f22098a = locationChangeListener;
    }
}
