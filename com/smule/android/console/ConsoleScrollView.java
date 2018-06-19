package com.smule.android.console;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ConsoleScrollView extends ScrollView {
    private ScrollViewListener f15735a = null;

    public interface ScrollViewListener {
        void mo6238a(int i, int i2, int i3, int i4);
    }

    public ConsoleScrollView(Context context) {
        super(context);
    }

    public ConsoleScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ConsoleScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.f15735a = scrollViewListener;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f15735a != null) {
            this.f15735a.mo6238a(i, i2, i3, i4);
        }
    }
}
