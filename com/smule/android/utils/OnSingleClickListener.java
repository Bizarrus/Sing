/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package com.smule.android.utils;

import android.view.View;

public abstract class OnSingleClickListener
implements View.OnClickListener {
    private boolean a = true;

    public void a() {
        this.a = true;
    }

    public abstract void a(View var1);

    public final void onClick(View view) {
        if (this.a) {
            this.a = false;
            this.a(view);
        }
    }
}

