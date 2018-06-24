/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.customviews.ScrollViewWithMaxHeight;

public class WhatsNewDialog
extends SmuleDialog {
    protected Button a;
    protected TextView b;
    protected LinearLayout c;
    protected ScrollViewWithMaxHeight d;
    private ViewGroup e;
    private Runnable f;

    public WhatsNewDialog(BaseActivity arrstring) {
        super((Context)arrstring, 2131493580, true);
        this.e = (ViewGroup)LayoutInflater.from((Context)arrstring).inflate(2130903469, null, false);
        this.setContentView((View)this.e);
        this.a = (Button)this.e.findViewById(2131755638);
        this.b = (TextView)this.e.findViewById(2131756823);
        this.c = (LinearLayout)this.e.findViewById(2131756825);
        this.d = (ScrollViewWithMaxHeight)this.e.findViewById(2131756824);
        this.d.setMaxHeight(this.getContext().getResources().getDimensionPixelSize(2131427978));
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                WhatsNewDialog.this.a();
            }
        });
        this.b.setText((CharSequence)arrstring.getString(2131297625, new Object[]{MagicNetwork.d().getAppVersion()}));
        String[] arrstring2 = arrstring.getResources().getStringArray(2131230728);
        arrstring = arrstring.getResources().getStringArray(2131230727);
        if (arrstring2.length != arrstring.length) {
            throw new RuntimeException("What's New titles and descriptions numbers are not equal!");
        }
        for (int i = 0; i < arrstring2.length; ++i) {
            LinearLayout linearLayout = (LinearLayout)this.getLayoutInflater().inflate(2130903468, null);
            TextView textView = (TextView)linearLayout.findViewById(2131756821);
            TextView textView2 = (TextView)linearLayout.findViewById(2131756822);
            textView.setText((CharSequence)arrstring2[i]);
            textView2.setText((CharSequence)arrstring[i]);
            this.c.addView((View)linearLayout);
        }
    }

    protected void a() {
        if (this.f != null) {
            this.f.run();
        }
        this.dismiss();
    }

    public void a(Runnable runnable) {
        this.f = runnable;
    }

}

