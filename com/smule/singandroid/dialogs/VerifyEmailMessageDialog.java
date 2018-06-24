/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.Spannable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.view.View;
import android.widget.TextView;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class VerifyEmailMessageDialog
extends TextAlertDialog {
    public VerifyEmailMessageDialog(Activity activity, int n, Spannable spannable, int n2, int n3) {
        super((Context)activity, activity.getString(n), (CharSequence)spannable, true, true);
        spannable = (TextView)this.findViewById(2131756735);
        spannable.setLinkTextColor(activity.getResources().getColor(2131689533));
        spannable.setHighlightColor(0);
        this.a(n2, n3);
    }

    @Override
    public void onBackPressed() {
        this.d();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean bl) {
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                VerifyEmailMessageDialog.this.d();
            }
        });
    }

}

