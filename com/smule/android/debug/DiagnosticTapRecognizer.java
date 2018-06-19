package com.smule.android.debug;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class DiagnosticTapRecognizer implements OnClickListener, OnLongClickListener {
    private int f15900a;
    private long f15901b;
    private Runnable f15902c;

    public DiagnosticTapRecognizer(View view, Runnable runnable) {
        this.f15902c = runnable;
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (m17705a()) {
            this.f15900a = 0;
        } else if (this.f15900a == 1) {
            this.f15900a = 2;
        } else if (this.f15900a == 2) {
            this.f15902c.run();
            this.f15900a = 0;
        } else {
            this.f15900a = 0;
        }
    }

    public boolean onLongClick(View view) {
        this.f15900a = 1;
        this.f15901b = System.currentTimeMillis();
        return false;
    }

    private boolean m17705a() {
        return System.currentTimeMillis() - this.f15901b > 10000;
    }
}
