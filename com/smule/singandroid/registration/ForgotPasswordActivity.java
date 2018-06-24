package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.UserManager;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;

public class ForgotPasswordActivity extends BaseActivity {
    private static final String f23827g = ForgotPasswordActivity.class.getName();
    private EditText f23828h;
    private BusyDialog f23829i;
    private Dialog f23830j;

    class C48151 implements OnClickListener {
        final /* synthetic */ ForgotPasswordActivity f23820a;

        C48151(ForgotPasswordActivity forgotPasswordActivity) {
            this.f23820a = forgotPasswordActivity;
        }

        public void onClick(View view) {
            this.f23820a.m25108b();
        }
    }

    class C48162 implements OnClickListener {
        final /* synthetic */ ForgotPasswordActivity f23821a;

        C48162(ForgotPasswordActivity forgotPasswordActivity) {
            this.f23821a = forgotPasswordActivity;
        }

        public void onClick(View view) {
            this.f23821a.m25104a();
        }
    }

    class C48183 implements NetworkResponseCallback {
        final /* synthetic */ ForgotPasswordActivity f23824a;

        C48183(ForgotPasswordActivity forgotPasswordActivity) {
            this.f23824a = forgotPasswordActivity;
        }

        public void handleResponse(final NetworkResponse networkResponse) {
            if (networkResponse.c()) {
                Analytics.m17901h();
                Handler handler = new Handler();
                this.f23824a.f23829i.m23576a(0, this.f23824a.getString(C1947R.string.login_email_sent), false);
                handler.postDelayed(new Runnable(this) {
                    final /* synthetic */ C48183 f23823b;

                    public void run() {
                        this.f23823b.f23824a.m25105a(networkResponse);
                    }
                }, 2000);
                return;
            }
            this.f23824a.m25105a(networkResponse);
        }
    }

    class C48194 implements OnClickListener {
        final /* synthetic */ ForgotPasswordActivity f23825a;

        C48194(ForgotPasswordActivity forgotPasswordActivity) {
            this.f23825a = forgotPasswordActivity;
        }

        public void onClick(View view) {
            this.f23825a.f23830j = null;
        }
    }

    class C48205 implements OnClickListener {
        final /* synthetic */ ForgotPasswordActivity f23826a;

        C48205(ForgotPasswordActivity forgotPasswordActivity) {
            this.f23826a = forgotPasswordActivity;
        }

        public void onClick(View view) {
            this.f23826a.f23830j = null;
            new NewAccountFlow(this.f23826a).m25132a(this.f23826a.f23828h.getText().toString(), null);
        }
    }

    public void onCreate(Bundle bundle) {
        boolean z = true;
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("EMAIL");
        if (bundle != null) {
            CharSequence string = bundle.getString("EMAIL");
        } else {
            Object obj = stringExtra;
        }
        boolean z2 = bundle != null && bundle.getBoolean("ALERT");
        setContentView(C1947R.layout.forgot_password_activity);
        View findViewById = findViewById(C1947R.id.root);
        findViewById.findViewById(C1947R.id.backbutton).setOnClickListener(new C48151(this));
        Button button = (Button) findViewById.findViewById(C1947R.id.send_email_button);
        button.setOnClickListener(new C48162(this));
        if (string == null || string.isEmpty()) {
            z = false;
        }
        button.setEnabled(z);
        this.f23828h = (EditText) findViewById.findViewById(C1947R.id.email_editText);
        if (string != null) {
            this.f23828h.setText(string);
        }
        if (z2) {
            m25112q();
        } else {
            MiscUtils.m25890a((Activity) this, this.f23828h);
        }
        NavigationUtils.m25909a(this.f23828h, button);
    }

    protected void onResume() {
        super.onResume();
        Analytics.m17900g();
    }

    private void m25104a() {
        this.f23829i = new BusyDialog((Activity) this, getString(C1947R.string.login_checking_email));
        this.f23829i.show();
        UserManager.a().a(this.f23828h.getText().toString(), new C48183(this));
    }

    public void onBackPressed() {
        m25108b();
    }

    protected void onDestroy() {
        if (this.f23830j != null) {
            this.f23830j.dismiss();
            this.f23830j = null;
        }
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString("EMAIL", this.f23828h.getText().toString());
        if (this.f23830j != null) {
            bundle.putBoolean("ALERT", true);
        }
    }

    private void m25108b() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("param_email", this.f23828h.getText().toString());
        startActivity(intent);
        finish();
    }

    private void m25105a(NetworkResponse networkResponse) {
        if (networkResponse.a != NetworkResponse$Status.OK) {
            this.f23829i.m23577a(2, getResources().getString(C1947R.string.login_cannot_connect_to_smule), true, "OK");
        } else if (networkResponse.c()) {
            Log.c(f23827g, "Email found!");
            this.f23829i.dismiss();
            m25108b();
        } else if (networkResponse.b == 65) {
            Log.c(f23827g, "Email not found!");
            this.f23829i.dismiss();
            m25112q();
        } else {
            this.f23829i.m23577a(2, getResources().getString(C1947R.string.login_error_with_servers), true, "OK");
            MagicNetwork.a(networkResponse);
        }
    }

    private void m25112q() {
        this.f23830j = NavigationUtils.m25903a(this, this.f23828h.getText().toString(), new C48194(this), new C48205(this));
    }
}
