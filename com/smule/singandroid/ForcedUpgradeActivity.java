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
 *  android.content.Intent
 *  android.net.Uri
 *  org.androidannotations.annotations.EActivity
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.smule.android.logging.Log;
import com.smule.singandroid.BlockingActivity;
import com.smule.singandroid.SingServerValues;
import org.androidannotations.annotations.EActivity;

@SuppressLint(value={"Registered"})
@EActivity
public class ForcedUpgradeActivity
extends BlockingActivity {
    public static String g = "UPGRADE_URL_EXTRAS_KEY";

    @Override
    protected AlertDialog a() {
        final String string2 = this.getIntent().getStringExtra(g);
        Log.c(a, "Showing upgrade dialog!");
        AlertDialog.Builder builder = new AlertDialog.Builder((Context)this);
        SingServerValues singServerValues = new SingServerValues();
        builder.setMessage((CharSequence)singServerValues.b());
        builder.setCancelable(false);
        builder.setPositiveButton((CharSequence)singServerValues.a(), new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface object, int n) {
                String string22 = string2;
                object = string22;
                if (!string22.startsWith("http://")) {
                    object = string22;
                    if (!string22.startsWith("https://")) {
                        object = "http://" + string22;
                    }
                }
                object = new Intent("android.intent.action.VIEW", Uri.parse((String)object));
                ForcedUpgradeActivity.this.startActivity((Intent)object);
                ForcedUpgradeActivity.this.finish();
                System.exit(0);
            }
        });
        return builder.create();
    }

}

