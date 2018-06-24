/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.text.Editable
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.LoginActivity;
import com.smule.singandroid.registration.NewAccountFlow;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.concurrent.Future;

public class ForgotPasswordActivity
extends BaseActivity {
    private static final String g = ForgotPasswordActivity.class.getName();
    private EditText h;
    private BusyDialog i;
    private Dialog j;

    private void a() {
        this.i = new BusyDialog((Activity)this, this.getString(2131296897));
        this.i.show();
        UserManager.a().a(this.h.getText().toString(), new NetworkResponseCallback(){

            @Override
            public void handleResponse(final com.smule.android.network.core.NetworkResponse networkResponse) {
                if (!networkResponse.c()) {
                    ForgotPasswordActivity.this.a(networkResponse);
                    return;
                }
                SingAnalytics.i();
                Handler handler = new Handler();
                ForgotPasswordActivity.this.i.a(0, ForgotPasswordActivity.this.getString(2131296906), null);
                handler.postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        ForgotPasswordActivity.this.a(networkResponse);
                    }
                }, 2000);
            }

        });
    }

    private void a(com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse.a != NetworkResponse.a) {
            this.i.a(2, this.getResources().getString(2131296895), null, "OK");
            return;
        }
        if (networkResponse.c()) {
            Log.c(g, "Email found!");
            this.i.dismiss();
            this.b();
            return;
        }
        if (networkResponse.b == 65) {
            Log.c(g, "Email not found!");
            this.i.dismiss();
            this.t();
            return;
        }
        this.i.a(2, this.getResources().getString(2131296908), networkResponse.h(), "OK");
        MagicNetwork.a(networkResponse);
    }

    private void b() {
        Intent intent = new Intent((Context)this, LoginActivity.class);
        intent.putExtra("param_email", this.h.getText().toString());
        this.startActivity(intent);
        this.finish();
    }

    private void t() {
        this.j = NavigationUtils.a((Context)this, (String)this.h.getText().toString(), (View.OnClickListener)new View.OnClickListener(){

            public void onClick(View view) {
                ForgotPasswordActivity.this.j = null;
            }
        }, (View.OnClickListener)new View.OnClickListener(){

            public void onClick(View view) {
                ForgotPasswordActivity.this.j = null;
                new NewAccountFlow((Activity)ForgotPasswordActivity.this).a(ForgotPasswordActivity.this.h.getText().toString(), null);
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(Bundle bundle) {
        boolean bl = true;
        super.onCreate(bundle);
        String string2 = this.getIntent().getStringExtra("EMAIL");
        if (bundle != null) {
            string2 = bundle.getString("EMAIL");
        }
        boolean bl2 = bundle != null && bundle.getBoolean("ALERT");
        this.setContentView(2130903253);
        bundle = this.findViewById(2131755221);
        bundle.findViewById(2131755222).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ForgotPasswordActivity.this.b();
            }
        });
        Button button = (Button)bundle.findViewById(2131755845);
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ForgotPasswordActivity.this.a();
            }
        });
        if (string2 == null || string2.isEmpty()) {
            bl = false;
        }
        button.setEnabled(bl);
        this.h = (EditText)bundle.findViewById(2131755848);
        if (string2 != null) {
            this.h.setText((CharSequence)string2);
        }
        if (bl2) {
            this.t();
        } else {
            MiscUtils.a((Activity)this, (EditText)this.h);
        }
        NavigationUtils.a((EditText)this.h, (Button)button);
    }

    @Override
    protected void onDestroy() {
        if (this.j != null) {
            this.j.dismiss();
            this.j = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SingAnalytics.h();
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString("EMAIL", this.h.getText().toString());
        if (this.j != null) {
            bundle.putBoolean("ALERT", true);
        }
    }

}

