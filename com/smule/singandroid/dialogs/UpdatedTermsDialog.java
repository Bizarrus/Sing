/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.customviews.ScrollViewWithMaxHeight;
import com.smule.singandroid.utils.MiscUtils;

public class UpdatedTermsDialog
extends SmuleDialog {
    protected Button a;
    protected TextView b;
    protected TextView c;
    protected LinearLayout d;
    protected ScrollViewWithMaxHeight e;
    private ViewGroup f;
    private Runnable g;

    public UpdatedTermsDialog(BaseActivity baseActivity, String string2, String string3) {
        super((Context)baseActivity, 2131493580, true);
        this.f = (ViewGroup)LayoutInflater.from((Context)baseActivity).inflate(2130903453, null, false);
        this.setContentView((View)this.f);
        this.a = (Button)this.f.findViewById(2131755638);
        this.b = (TextView)this.f.findViewById(2131756768);
        this.c = (TextView)this.f.findViewById(2131756771);
        this.d = (LinearLayout)this.f.findViewById(2131756770);
        this.e = (ScrollViewWithMaxHeight)this.f.findViewById(2131756769);
        this.e.setMaxHeight(this.getContext().getResources().getDimensionPixelSize(2131427939));
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                UpdatedTermsDialog.this.a();
            }
        });
        string2 = "href=\"" + string2 + "\"";
        string3 = "href=\"" + string3 + "\"";
        this.b.setText((CharSequence)baseActivity.getResources().getString(2131297572));
        string2 = MiscUtils.a((Activity)baseActivity, (String)baseActivity.getResources().getString(2131297571, new Object[]{string3, string2}));
        this.c.setText((CharSequence)string2);
        this.c.setLinkTextColor(baseActivity.getResources().getColor(2131689487));
        this.c.setHighlightColor(0);
        this.c.setMovementMethod(LinkMovementMethod.getInstance());
    }

    protected void a() {
        if (this.g != null) {
            this.g.run();
        }
    }

    public void a(Runnable runnable) {
        this.g = runnable;
    }

    public void cancel() {
        super.cancel();
    }

    public void dismiss() {
        super.dismiss();
    }

}

