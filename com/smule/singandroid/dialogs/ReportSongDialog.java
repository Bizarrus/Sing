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
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;

public class ReportSongDialog
extends SmuleDialog {
    View a;
    View b;
    View c;

    public ReportSongDialog(final Activity activity) {
        super((Context)activity, 2131493244);
        this.setContentView(2130903389);
        this.a = this.findViewById(2131756434);
        this.b = this.findViewById(2131756435);
        this.c = this.findViewById(2131756436);
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                ReportSongDialog.this.dismiss();
                object = new TextAlertDialog((Context)activity, ReportSongDialog.this.getContext().getString(2131297460), ReportSongDialog.this.getContext().getString(2131297529), true, false);
                object.a(ReportSongDialog.this.getContext().getString(2131296705), null);
                object.show();
            }
        });
        this.b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                ReportSongDialog.this.dismiss();
                object = new TextAlertDialog((Context)activity, ReportSongDialog.this.getContext().getString(2131297457), ReportSongDialog.this.getContext().getString(2131297528), true, false);
                object.a(ReportSongDialog.this.getContext().getString(2131296705), null);
                object.show();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                ReportSongDialog.this.dismiss();
                object = new TextAlertDialog((Context)activity, ReportSongDialog.this.getContext().getString(2131297467), ReportSongDialog.this.getContext().getString(2131297466), true, false);
                object.a(ReportSongDialog.this.getContext().getString(2131296705), null);
                object.show();
            }
        });
    }

}

