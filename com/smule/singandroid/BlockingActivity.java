/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EActivity
 */
package com.smule.singandroid;

import android.app.AlertDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class BlockingActivity
extends BaseActivity {
    private AlertDialog g;

    protected abstract AlertDialog a();

    @Override
    protected void onResume() {
        super.onResume();
        if (this.g != null && this.g.isShowing()) {
            return;
        }
        this.g = this.a();
        this.g.show();
        SingAnalytics.a((BottomNavView.Tab)null);
    }
}

