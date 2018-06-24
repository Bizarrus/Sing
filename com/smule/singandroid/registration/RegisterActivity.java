package com.smule.singandroid.registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.TrackedActivity;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;

public class RegisterActivity extends BaseActivity implements TrackedActivity {
    private static final String f23884g = RegisterActivity.class.getName();
    private EditText f23885h;
    private Button f23886i;
    private OnClickListener f23887j;
    private OnClickListener f23888k;
    private Dialog f23889l;

    class C48351 implements OnClickListener {
        final /* synthetic */ RegisterActivity f23880a;

        C48351(RegisterActivity registerActivity) {
            this.f23880a = registerActivity;
        }

        public void onClick(View view) {
            this.f23880a.m25158q();
        }
    }

    class C48362 implements OnClickListener {
        final /* synthetic */ RegisterActivity f23881a;

        C48362(RegisterActivity registerActivity) {
            this.f23881a = registerActivity;
        }

        public void onClick(View view) {
            if (new NewAccountFlow(this.f23881a).m25132a(this.f23881a.f23885h.getText().toString(), null)) {
                this.f23881a.f23886i.setClickable(false);
            }
        }
    }

    class C48373 implements OnClickListener {
        final /* synthetic */ RegisterActivity f23882a;

        C48373(RegisterActivity registerActivity) {
            this.f23882a = registerActivity;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.f23882a, LoginActivity.class);
            intent.putExtra("param_email", this.f23882a.f23885h.getText().toString());
            this.f23882a.startActivity(intent);
            this.f23882a.finish();
        }
    }

    class C48384 implements OnClickListener {
        final /* synthetic */ RegisterActivity f23883a;

        C48384(RegisterActivity registerActivity) {
            this.f23883a = registerActivity;
        }

        public void onClick(View view) {
            this.f23883a.f23889l.dismiss();
            this.f23883a.f23885h.setText("");
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1947R.layout.register_activity);
        findViewById(C1947R.id.backbutton).setOnClickListener(new C48351(this));
        this.f23885h = (EditText) findViewById(C1947R.id.email_editText);
        CharSequence stringExtra = getIntent().getStringExtra("email_param");
        if (stringExtra != null) {
            this.f23885h.setText(stringExtra);
        }
        this.f23885h.requestFocus();
        MiscUtils.m25890a((Activity) this, this.f23885h);
        this.f23886i = (Button) findViewById(C1947R.id.registerButton);
        this.f23886i.setOnClickListener(new C48362(this));
        this.f23887j = new C48373(this);
        this.f23888k = new C48384(this);
        NavigationUtils.m25909a(this.f23885h, this.f23886i);
    }

    protected void onStart() {
        this.f = false;
        super.onStart();
    }

    protected void onStop() {
        this.f = false;
        super.onStop();
    }

    protected void m25161e() {
        Analytics.m17904k();
    }

    public void onBackPressed() {
        m25158q();
    }

    private void m25158q() {
        startActivity(RegistrationEntryActivity.m25166a(this, false, false, null, null, null));
        finish();
    }

    public boolean mo6612a() {
        return true;
    }

    public String mo6613b() {
        return "RegisterNewEmail";
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.f23886i.setClickable(true);
        }
    }
}
