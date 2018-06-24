/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
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
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.smule.android.logging.TrackedActivity;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.registration.LoginActivity;
import com.smule.singandroid.registration.NewAccountFlow;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;

public class RegisterActivity
extends BaseActivity
implements TrackedActivity {
    private static final String g = RegisterActivity.class.getName();
    private EditText h;
    private Button i;
    private View.OnClickListener j;
    private View.OnClickListener k;
    private Dialog l;

    private void t() {
        this.startActivity(RegistrationEntryActivity.a((Activity)this, false, false, null, null, null));
        this.finish();
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public String b() {
        return "RegisterNewEmail";
    }

    @Override
    protected void g() {
        SingAnalytics.l();
    }

    @Override
    public void onBackPressed() {
        this.t();
    }

    @Override
    protected void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.setContentView(2130903387);
        this.findViewById(2131755222).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                RegisterActivity.this.t();
            }
        });
        this.h = (EditText)this.findViewById(2131755848);
        object = this.getIntent().getStringExtra("email_param");
        if (object != null) {
            this.h.setText((CharSequence)object);
        }
        this.h.requestFocus();
        MiscUtils.a((Activity)this, (EditText)this.h);
        this.i = (Button)this.findViewById(2131756433);
        this.i.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (new NewAccountFlow((Activity)RegisterActivity.this).a(RegisterActivity.this.h.getText().toString(), null)) {
                    RegisterActivity.this.i.setClickable(false);
                }
            }
        });
        this.j = new View.OnClickListener(){

            public void onClick(View view) {
                view = new Intent((Context)RegisterActivity.this, LoginActivity.class);
                view.putExtra("param_email", RegisterActivity.this.h.getText().toString());
                RegisterActivity.this.startActivity((Intent)view);
                RegisterActivity.this.finish();
            }
        };
        this.k = new View.OnClickListener(){

            public void onClick(View view) {
                RegisterActivity.this.l.dismiss();
                RegisterActivity.this.h.setText((CharSequence)"");
            }
        };
        NavigationUtils.a((EditText)this.h, (Button)this.i);
    }

    @Override
    protected void onStart() {
        this.f = false;
        super.onStart();
    }

    @Override
    protected void onStop() {
        this.f = false;
        super.onStop();
    }

    public void onWindowFocusChanged(boolean bl) {
        super.onWindowFocusChanged(bl);
        if (bl) {
            this.i.setClickable(true);
        }
    }

}

