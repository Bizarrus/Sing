/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;
import com.smule.android.ui.dialogs.SmuleDialog;

public class SortOptionsDialog
extends SmuleDialog {
    View a;
    TextView b;
    TextView c;

    public SortOptionsDialog(Activity activity, int n, int n2, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super((Context)activity, 2131493244);
        this.setContentView(2130903398);
        this.a = this.findViewById(2131756500);
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SortOptionsDialog.this.dismiss();
            }
        });
        this.b = (TextView)this.findViewById(2131756501);
        this.c = (TextView)this.findViewById(2131756502);
        this.b.setText(n);
        this.c.setText(n2);
        this.b.setOnClickListener(onClickListener);
        this.c.setOnClickListener(onClickListener2);
        this.a(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl) {
        int n = -1;
        TextView textView = this.b;
        int n2 = bl ? this.getContext().getResources().getColor(2131689718) : -1;
        textView.setBackgroundColor(n2);
        textView = this.c;
        n2 = n;
        if (!bl) {
            n2 = this.getContext().getResources().getColor(2131689718);
        }
        textView.setBackgroundColor(n2);
    }

}

