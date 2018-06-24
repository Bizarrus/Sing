/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 */
package com.smule.android.debug;

import android.view.View;

public class DiagnosticTapRecognizer
implements View.OnClickListener,
View.OnLongClickListener {
    private int a;
    private long b;
    private Runnable c;

    public DiagnosticTapRecognizer(View view, Runnable runnable) {
        this.c = runnable;
        view.setOnLongClickListener((View.OnLongClickListener)this);
        view.setOnClickListener((View.OnClickListener)this);
    }

    private boolean a() {
        if (System.currentTimeMillis() - this.b > 10000) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        if (this.a()) {
            this.a = 0;
            return;
        }
        if (this.a == 1) {
            this.a = 2;
            return;
        }
        if (this.a == 2) {
            this.c.run();
            this.a = 0;
            return;
        }
        this.a = 0;
    }

    public boolean onLongClick(View view) {
        this.a = 1;
        this.b = System.currentTimeMillis();
        return false;
    }
}

