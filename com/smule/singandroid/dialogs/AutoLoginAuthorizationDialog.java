/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.network.managers.OAuth2Manager;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.util.concurrent.Future;

public class AutoLoginAuthorizationDialog
extends TextAlertDialog {
    private Context c;

    public AutoLoginAuthorizationDialog(Activity activity, final String string2, final String string3, final String string4, final String string5, final String string6, final String string7, String string8) {
        super((Context)activity, activity.getString(2131296407), activity.getString(2131296406, new Object[]{UserManager.a().i(), string8}));
        this.c = activity;
        this.a(2131296733, 2131296701);
        this.b(new Runnable(){

            @Override
            public void run() {
                AutoLoginAuthorizationDialog.this.b(string7);
            }
        });
        this.a(new Runnable(){

            @Override
            public void run() {
                com.smule.android.network.managers.OAuth2Manager.a().a(string2, string3, string4, string5, string6, new OAuth2Manager.AuthorizeResponseCallback(){

                    @Override
                    public void handleResponse(final OAuth2Manager authorizeResponse) {
                        new Handler(Looper.getMainLooper()).post(new Runnable(){

                            @Override
                            public void run() {
                                if (authorizeResponse.a()) {
                                    AutoLoginAuthorizationDialog.this.b(authorizeResponse.mRedirectUri);
                                    return;
                                }
                                AutoLoginAuthorizationDialog.this.b(string7);
                            }
                        });
                    }

                });
            }

        });
    }

    private void b(String string2) {
        if ((string2 = Uri.parse((String)string2)).getScheme().equals("http") || string2.getScheme().equals("https")) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData((Uri)string2);
            intent.addFlags(268435456);
            this.c.startActivity(intent);
        }
    }

}

