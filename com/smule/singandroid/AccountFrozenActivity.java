/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  org.androidannotations.annotations.EActivity
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.smule.singandroid.BlockingActivity;
import org.androidannotations.annotations.EActivity;

@SuppressLint(value={"Registered"})
@EActivity
public class AccountFrozenActivity
extends BlockingActivity {
    @Override
    protected AlertDialog a() {
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        builder.setTitle(2131296909);
        builder.setMessage(2131296894);
        builder.setCancelable(false);
        builder.setPositiveButton(2131296705, new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialogInterface, int n) {
                AccountFrozenActivity.this.finishAffinity();
                System.exit(0);
            }
        });
        return builder.create();
    }

}

