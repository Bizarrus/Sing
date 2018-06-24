package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.pre_sing.PreSingActivity;
import twitter4j.HttpResponseCode;

public class BusyDialog extends SmuleDialog {
    private static final String f22134a = BusyDialog.class.getName();
    private ViewGroup f22135b;
    private ViewGroup f22136c;
    private ViewGroup f22137d;
    private ProgressBar f22138e;
    private ProgressBar f22139f;
    private TextView f22140g;
    private TextView f22141h;
    private TextView f22142i;
    private TextView f22143j;
    private Button f22144k;
    private BusyDialogListener f22145l;
    private final Activity f22146m;

    public interface BusyDialogListener {
        void mo6373a();
    }

    class C44611 implements OnClickListener {
        final /* synthetic */ BusyDialog f22131a;

        C44611(BusyDialog busyDialog) {
            this.f22131a = busyDialog;
        }

        public void onClick(View view) {
            this.f22131a.m23573b();
        }
    }

    class C44622 implements Runnable {
        final /* synthetic */ BusyDialog f22132a;

        C44622(BusyDialog busyDialog) {
            this.f22132a = busyDialog;
        }

        public void run() {
            this.f22132a.m23578a(-1);
        }
    }

    class C44633 implements Runnable {
        final /* synthetic */ BusyDialog f22133a;

        C44633(BusyDialog busyDialog) {
            this.f22133a = busyDialog;
        }

        public void run() {
            try {
                this.f22133a.dismiss();
            } catch (Exception e) {
            }
        }
    }

    public void m23579a(BusyDialogListener busyDialogListener) {
        this.f22145l = busyDialogListener;
        if (this.f22144k != null) {
            this.f22144k.setVisibility(this.f22145l != null ? 0 : 4);
        }
    }

    private void m23571a() {
        this.f22135b = (ViewGroup) findViewById(C1947R.id.busy_container);
        this.f22136c = (ViewGroup) findViewById(C1947R.id.error_container);
        this.f22137d = (ViewGroup) findViewById(C1947R.id.error_with_title_container);
        this.f22140g = (TextView) findViewById(C1947R.id.message);
        this.f22141h = (TextView) findViewById(C1947R.id.error_title);
        this.f22142i = (TextView) findViewById(C1947R.id.error_details);
        this.f22143j = (TextView) findViewById(C1947R.id.error_text);
        this.f22139f = (ProgressBar) findViewById(C1947R.id.progress_bar);
        this.f22138e = (ProgressBar) findViewById(C1947R.id.progress_bar_indeterminate);
        this.f22144k = (Button) findViewById(C1947R.id.cancel_button);
    }

    public BusyDialog(Activity activity, int i) {
        this(activity, activity.getResources().getString(i));
    }

    public BusyDialog(Activity activity, String str) {
        super(activity, C1947R.style.Sing.Dialog);
        this.f22146m = activity;
        setContentView(C1947R.layout.new_busy_dialog);
        m23571a();
        this.f22140g.setText(str);
        this.f22144k.setOnClickListener(new C44611(this));
        setCancelable(false);
    }

    public void m23580a(boolean z) {
        super.show();
        if (!z || this.f22145l == null) {
            this.f22144k.setVisibility(4);
        } else {
            this.f22144k.setVisibility(0);
        }
    }

    public void onBackPressed() {
    }

    public void dismiss() {
        m23578a(0);
    }

    public void m23578a(long j) {
        if (j > 0) {
            new Handler(Looper.getMainLooper()).postDelayed(new C44622(this), Math.max(j, 1000));
            return;
        }
        try {
            super.dismiss();
        } catch (Exception e) {
            Log.e(f22134a, "Exception thrown when dismissing BusyDialog");
        }
    }

    private void m23573b() {
        if (this.f22145l != null) {
            this.f22145l.mo6373a();
        }
        dismiss();
        if (this.f22146m instanceof PreSingActivity) {
            this.f22146m.onBackPressed();
        }
    }

    public void m23576a(int i, String str, boolean z) {
        m23577a(i, str, z, getContext().getString(C1947R.string.core_ok));
    }

    public void m23577a(int i, String str, boolean z, String str2) {
        m23575a(i, null, str, z, str2, HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public void m23574a(int i, String str, String str2, boolean z, String str3) {
        m23575a(i, str, str2, z, str3, HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public void m23575a(int i, String str, String str2, boolean z, String str3, int i2) {
        this.f22140g.setText(str2);
        if (i == 1) {
            this.f22139f.setVisibility(8);
            this.f22138e.setVisibility(4);
            if (!(str2 == null || str2.isEmpty())) {
                this.f22140g.setText(str2);
                this.f22140g.setVisibility(0);
            }
            Runnable c44633 = new C44633(this);
            if (this.f22146m instanceof BaseActivity) {
                ((BaseActivity) this.f22146m).a(c44633, (long) i2);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(c44633, (long) i2);
            }
        } else if (i == 2) {
            if (str == null) {
                Log.d(f22134a, "Sad Puppies are deprecated. Please set the title for STATE_FAIL state.");
                this.f22135b.setVisibility(8);
                this.f22136c.setVisibility(0);
                this.f22137d.setVisibility(8);
                this.f22143j.setText(str2);
            } else {
                this.f22135b.setVisibility(8);
                this.f22136c.setVisibility(8);
                this.f22137d.setVisibility(0);
                this.f22141h.setText(str);
                this.f22142i.setText(str2);
            }
            if (str3 != null) {
                this.f22144k.setText(str3);
            }
        } else if (i == 0) {
            this.f22135b.setVisibility(0);
            this.f22138e.setVisibility(0);
            this.f22139f.setVisibility(8);
            this.f22136c.setVisibility(8);
        }
        this.f22144k.setVisibility(i == 2 ? 0 : this.f22144k.getVisibility());
    }
}
