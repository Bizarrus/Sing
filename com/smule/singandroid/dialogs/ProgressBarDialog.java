/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Handler
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ProgressBar
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.ui.dialogs.SmuleDialog;

public class ProgressBarDialog
extends SmuleDialog {
    private static final String a = ProgressBarDialog.class.getName();
    private ProgressBarDialogInterface b;
    private Handler c;
    private Runnable d;
    private boolean e = false;
    private int f = 100;
    private TextView g;
    private TextView h;
    private ProgressBar i;
    private int j;

    public ProgressBarDialog(Activity activity, String string2, ProgressBarDialogInterface progressBarDialogInterface) {
        super((Context)activity, 2131493570);
        activity = activity.getLayoutInflater().inflate(2130903378, null, false);
        this.setContentView((View)activity);
        this.g = (TextView)activity.findViewById(2131755398);
        this.h = (TextView)activity.findViewById(2131755376);
        this.i = (ProgressBar)activity.findViewById(2131756417);
        this.j = 0;
        this.b = progressBarDialogInterface;
        this.g.setText((CharSequence)string2);
        this.h.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (ProgressBarDialog.this.b != null) {
                    ProgressBarDialog.this.b.a();
                }
                ProgressBarDialog.this.dismiss();
            }
        });
    }

    public void a() {
        this.h.setVisibility(4);
        this.e = true;
    }

    public void a(int n) {
        this.f = n;
    }

    public void a(ProgressBarDialogInterface progressBarDialogInterface) {
        this.b = progressBarDialogInterface;
    }

    public void a(String string2) {
        this.g.setText((CharSequence)string2);
    }

    public void b() {
        this.i.setProgress(0);
    }

    public void b(int n) {
        this.j = n;
    }

    public void c() {
        this.i.setProgress(this.i.getMax());
    }

    public void dismiss() {
        try {
            this.c.removeCallbacks(this.d);
            super.dismiss();
            return;
        }
        catch (Exception exception) {
            Log.d(a, "dismiss - exception thrown: ", exception);
            return;
        }
    }

    public void onBackPressed() {
        if (this.e) {
            return;
        }
        if (this.b != null) {
            this.b.a();
        }
        super.onBackPressed();
    }

    public void show() {
        super.show();
        this.c = new Handler();
        this.d = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (ProgressBarDialog.this.i.getMax() <= ProgressBarDialog.this.i.getProgress() + ProgressBarDialog.this.j) {
                    ProgressBarDialog.this.i.setProgress(ProgressBarDialog.this.i.getMax() - ProgressBarDialog.this.j);
                    return;
                }
                ProgressBarDialog.this.i.setProgress(ProgressBarDialog.this.i.getProgress() + 1);
                int n = ProgressBarDialog.this.i.getMax() - ProgressBarDialog.this.i.getProgress() <= 20 ? 50 : 0;
                ProgressBarDialog.this.c.postDelayed((Runnable)this, (long)(n + ProgressBarDialog.this.f));
            }
        };
        this.c.post(this.d);
    }

    public static interface ProgressBarDialogInterface {
        public void a();
    }

}

