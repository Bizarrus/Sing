/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.text.Editable
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.TextView
 *  com.smule.singandroid.task.LoginTask
 *  com.smule.singandroid.task.LoginTask$LoginTaskListener
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.ValidationUtils
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.TrackedActivity;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.ContactsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.registration.ForgotPasswordActivity;
import com.smule.singandroid.registration.NewAccountFlow;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.RegistrationEntryActivity;
import com.smule.singandroid.registration.WelcomeActivity_;
import com.smule.singandroid.task.LoginTask;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.ValidationUtils;
import java.io.Serializable;
import java.util.Locale;
import java.util.concurrent.Future;

public class LoginActivity
extends BaseActivity
implements TrackedActivity,
UserManager,
LoginTask.LoginTaskListener {
    private static final String g = LoginActivity.class.getName();
    private EditText h;
    private EditText i;
    private BusyDialog j;
    private boolean k = false;
    private Dialog l;
    private View.OnClickListener m;
    private View.OnClickListener n;
    private boolean o = false;

    private boolean t() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.c() != null) {
            bl2 = bl;
            if (this.c().toString().substring(0, 2).equals("ur")) {
                bl2 = true;
            }
        }
        return bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void u() {
        block7 : {
            block6 : {
                Object object = this.getIntent().getData();
                if (object == null) break block6;
                String string2 = object.getQueryParameter("email");
                object = object.getQueryParameter("password");
                if (string2 != null) {
                    this.h.setText((CharSequence)string2);
                }
                if (object != null) {
                    this.i.setText((CharSequence)object);
                }
                if (string2 != null && object != null) break block7;
            }
            return;
        }
        this.k = false;
        if (this.o) {
            this.w();
            return;
        }
        this.k = true;
    }

    private void v() {
        this.startActivity(RegistrationEntryActivity.a((Activity)this, false, false, null, null, null));
        this.finish();
    }

    private void w() {
        this.j = new BusyDialog((Activity)this, this.getResources().getString(2131296892));
        this.j.show();
        new LoginTask((Activity)this, (LoginTask.LoginTaskListener)this, this.h.getText().toString(), this.i.getText().toString()).execute((Object[])new Void[0]);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(UserManager loginResponse) {
        if (loginResponse != null && loginResponse.a != null && loginResponse.a.a == NetworkResponse.a) {
            switch (loginResponse.a.b) {
                default: {
                    this.j.a(2, this.getResources().getString(2131296909), loginResponse.a.h());
                    MagicNetwork.a(loginResponse.a);
                    return;
                }
                case 0: {
                    if (this.j != null) {
                        this.j.dismiss();
                    }
                    ContactsManager.e();
                    RegistrationContext.d(loginResponse.o);
                    RegistrationContext.c();
                    if (loginResponse.t.booleanValue()) {
                        loginResponse = new Intent((Context)this, WelcomeActivity_.class);
                        loginResponse.putExtra("PARAM_LOGIN_METHOD", (Serializable)((Object)Analytics.a));
                        this.startActivity((Intent)loginResponse);
                        this.finish();
                        return;
                    }
                    RegistrationContext.a((Activity)this, true, Analytics.a);
                    return;
                }
                case 69: {
                    com.smule.android.network.managers.UserManager.a().a(this.h.getText().toString(), this);
                    return;
                }
            }
        }
        if (this.j == null) return;
        {
            this.j.a(2, this.getResources().getString(2131296908), null, this.getString(2131296705));
            return;
        }
    }

    @Override
    public boolean a() {
        return true;
    }

    @Override
    public String b() {
        return "RegisterExistingAccount";
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleResponse(UserManager.AccountIconResponse accountIconResponse) {
        if (accountIconResponse.a == null) {
            MagicNetwork.a(null);
            if (this.j == null) return;
            {
                this.j.a(2, this.getResources().getString(2131296908), null, this.getString(2131296705));
                return;
            }
        } else if (accountIconResponse.a.c()) {
            if (this.j == null) return;
            {
                this.j.a(2, this.getResources().getString(2131297338), this.getResources().getString(2131297339), null, this.getString(2131296705));
                return;
            }
        } else {
            if (accountIconResponse.a.b == 1012 || accountIconResponse.a.b == 65) {
                if (this.j != null) {
                    this.j.dismiss();
                }
                this.l = NavigationUtils.a((Context)this, (String)this.h.getText().toString(), (View.OnClickListener)this.m, (View.OnClickListener)this.n);
                return;
            }
            MagicNetwork.a(accountIconResponse.a);
            if (this.j == null) return;
            {
                this.j.a(2, this.getResources().getString(2131296916), accountIconResponse.a.h(), this.getString(2131296705));
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        this.v();
        if (this.l != null) {
            this.l.dismiss();
            this.l = null;
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130903277);
        this.findViewById(2131755222).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                LoginActivity.this.v();
            }
        });
        this.h = (EditText)this.findViewById(2131755848);
        WeakListener.a(this.h, new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        });
        this.h.requestFocus();
        MiscUtils.a((Activity)this, (EditText)this.h);
        this.i = (EditText)this.findViewById(2131755941);
        if (this.t()) {
            this.i.setGravity(5);
            this.h.setGravity(5);
        }
        bundle = (Button)this.findViewById(2131755940);
        bundle.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                if (!ValidationUtils.a((String)LoginActivity.this.h.getText().toString())) {
                    object = new BusyDialog((Activity)LoginActivity.this, LoginActivity.this.getResources().getString(2131297338));
                    object.a(2, LoginActivity.this.getString(2131296920), LoginActivity.this.getString(2131296919), null, LoginActivity.this.getString(2131296705));
                    object.show();
                    return;
                }
                if (LoginActivity.this.i.getText().length() < 6 || LoginActivity.this.i.getText().length() > 16) {
                    object = new BusyDialog((Activity)LoginActivity.this, LoginActivity.this.getResources().getString(2131297338));
                    object.a(2, LoginActivity.this.getString(2131297338), LoginActivity.this.getString(2131297358), null, LoginActivity.this.getString(2131296705));
                    object.show();
                    return;
                }
                LoginActivity.this.w();
            }
        });
        ((TextView)this.findViewById(2131755942)).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                view = new Intent((Context)LoginActivity.this, ForgotPasswordActivity.class);
                String string2 = LoginActivity.this.h.getText().toString();
                if (!string2.isEmpty()) {
                    view.putExtra("EMAIL", string2);
                }
                LoginActivity.this.startActivity((Intent)view);
                LoginActivity.this.finish();
            }
        });
        String string2 = this.getIntent().getStringExtra("param_email");
        if (string2 != null && !TextUtils.isEmpty((CharSequence)string2)) {
            this.h.setText((CharSequence)string2);
            this.i.requestFocus();
            MiscUtils.a((Activity)this, (EditText)this.i);
        }
        this.m = new View.OnClickListener(){

            public void onClick(View view) {
                LoginActivity.this.l.dismiss();
                LoginActivity.this.l = null;
            }
        };
        this.n = new View.OnClickListener(){

            public void onClick(View view) {
                if (LoginActivity.this.l != null && LoginActivity.this.l.isShowing()) {
                    LoginActivity.this.l.dismiss();
                    LoginActivity.this.l = null;
                }
                new NewAccountFlow((Activity)LoginActivity.this).a(LoginActivity.this.h.getText().toString(), LoginActivity.this.i.getText().toString());
            }
        };
        this.u();
        NavigationUtils.a((EditText)this.h, (EditText)this.i, (Button)bundle);
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
        Log.b(g, "onNewIntent : " + (Object)intent.getData());
        this.u();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.o = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.o = true;
        SingAnalytics.m();
        if (this.k) {
            this.w();
            this.k = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (this.j != null) {
            this.j.dismiss();
            this.j = null;
        }
    }

}

