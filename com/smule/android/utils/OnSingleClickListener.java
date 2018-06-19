package com.smule.android.utils;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class OnSingleClickListener implements OnClickListener {
    private boolean f17814a = true;

    public abstract void mo6533a(View view);

    public final void onClick(View view) {
        if (this.f17814a) {
            this.f17814a = false;
            mo6533a(view);
        }
    }

    public void m19018a() {
        this.f17814a = true;
    }
}
