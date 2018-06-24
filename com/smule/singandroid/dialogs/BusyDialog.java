/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.Nullable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;

public class BusyDialog
extends SmuleDialog {
    private static final String a = BusyDialog.class.getName();
    private ViewGroup b;
    private ViewGroup c;
    private ViewGroup d;
    private ProgressBar e;
    private ProgressBar f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private Button k;
    private BusyDialogListener l;
    private final Activity m;

    public BusyDialog(Activity activity, int n) {
        this(activity, activity.getResources().getString(n));
    }

    public BusyDialog(Activity activity, String string2) {
        super((Context)activity, 2131493338);
        this.m = activity;
        this.setContentView(2130903306);
        this.a();
        this.g.setText((CharSequence)string2);
        this.k.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                BusyDialog.this.b();
            }
        });
        this.setCancelable(false);
    }

    private void a() {
        this.b = (ViewGroup)this.findViewById(2131756014);
        this.c = (ViewGroup)this.findViewById(2131756015);
        this.d = (ViewGroup)this.findViewById(2131756018);
        this.g = (TextView)this.findViewById(2131755909);
        this.h = (TextView)this.findViewById(2131756019);
        this.i = (TextView)this.findViewById(2131756020);
        this.j = (TextView)this.findViewById(2131756017);
        this.f = (ProgressBar)this.findViewById(2131755344);
        this.e = (ProgressBar)this.findViewById(2131755422);
        this.k = (Button)this.findViewById(2131755376);
    }

    private void b() {
        if (this.l != null) {
            this.l.a();
        }
        this.dismiss();
        if (this.m instanceof PreSingActivity) {
            this.m.onBackPressed();
        }
    }

    public void a(int n, String string2, @Nullable Integer n2) {
        this.a(n, string2, n2, this.getContext().getString(2131296705));
    }

    public void a(int n, String string2, @Nullable Integer n2, String string3) {
        this.a(n, null, string2, n2, string3, 500);
    }

    public void a(int n, String string2, String string3, @Nullable Integer n2, String string4) {
        this.a(n, string2, string3, n2, string4, 500);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n, String object, String string2, @Nullable Integer object2, String string3, int n2) {
        String string4 = string2;
        if (object2 != null) {
            object2 = this.m.getString(2131296725, new Object[]{object2});
            string4 = string2 + "\n" + (String)object2;
        }
        this.g.setText((CharSequence)string4);
        if (n == 1) {
            this.f.setVisibility(8);
            this.e.setVisibility(4);
            if (string4 != null && !string4.isEmpty()) {
                this.g.setText((CharSequence)string4);
                this.g.setVisibility(0);
            }
            object = new Runnable(){

                @Override
                public void run() {
                    try {
                        BusyDialog.this.dismiss();
                        return;
                    }
                    catch (Exception exception) {
                        return;
                    }
                }
            };
            if (this.m instanceof BaseActivity) {
                ((BaseActivity)this.m).a((Runnable)object, n2);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed((Runnable)object, (long)n2);
            }
        } else if (n == 2) {
            if (object == null) {
                Log.d(a, "Sad Puppies are deprecated. Please set the title for STATE_FAIL state.");
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                this.d.setVisibility(8);
                this.j.setText((CharSequence)string4);
            } else {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(0);
                this.h.setText((CharSequence)object);
                this.i.setText((CharSequence)string4);
            }
            if (string3 != null) {
                this.k.setText((CharSequence)string3);
            }
        } else if (n == 0) {
            this.b.setVisibility(0);
            this.e.setVisibility(0);
            this.f.setVisibility(8);
            this.c.setVisibility(8);
        }
        object = this.k;
        n = n == 2 ? 0 : this.k.getVisibility();
        object.setVisibility(n);
    }

    public void a(long l) {
        if (l > 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable(){

                @Override
                public void run() {
                    BusyDialog.this.a(-1);
                }
            }, Math.max(l, 1000));
            return;
        }
        try {
            super.dismiss();
            return;
        }
        catch (Exception exception) {
            Log.e(a, "Exception thrown when dismissing BusyDialog");
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(BusyDialogListener busyDialogListener) {
        this.l = busyDialogListener;
        if (this.k != null) {
            busyDialogListener = this.k;
            int n = this.l != null ? 0 : 4;
            busyDialogListener.setVisibility(n);
        }
    }

    public void a(boolean bl) {
        super.show();
        if (bl && this.l != null) {
            this.k.setVisibility(0);
            return;
        }
        this.k.setVisibility(4);
    }

    public void dismiss() {
        this.a(0);
    }

    public void onBackPressed() {
    }

    public static interface BusyDialogListener {
        public void a();
    }

}

