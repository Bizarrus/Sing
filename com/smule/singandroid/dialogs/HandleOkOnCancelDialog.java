/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class HandleOkOnCancelDialog
extends TextAlertDialog {
    public HandleOkOnCancelDialog(Activity activity, int n, int n2, int n3, int n4) {
        super((Context)activity, n, n2);
        this.a(n3, n4);
    }

    @Override
    public void onBackPressed() {
        this.c();
    }

    @Override
    public void setCanceledOnTouchOutside(boolean bl) {
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                HandleOkOnCancelDialog.this.c();
            }
        });
    }

}

