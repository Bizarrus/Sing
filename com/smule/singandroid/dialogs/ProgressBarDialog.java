package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;

public class ProgressBarDialog extends SmuleDialog {
    private static final String f22282a = ProgressBarDialog.class.getName();
    private ProgressBarDialogInterface f22283b;
    private Handler f22284c;
    private Runnable f22285d;
    private boolean f22286e = false;
    private int f22287f = 100;
    private TextView f22288g;
    private TextView f22289h;
    private ProgressBar f22290i;
    private int f22291j;

    public interface ProgressBarDialogInterface {
        void mo6384a();
    }

    class C45041 implements OnClickListener {
        final /* synthetic */ ProgressBarDialog f22280a;

        C45041(ProgressBarDialog progressBarDialog) {
            this.f22280a = progressBarDialog;
        }

        public void onClick(View view) {
            if (this.f22280a.f22283b != null) {
                this.f22280a.f22283b.mo6384a();
            }
            this.f22280a.dismiss();
        }
    }

    class C45052 implements Runnable {
        final /* synthetic */ ProgressBarDialog f22281a;

        C45052(ProgressBarDialog progressBarDialog) {
            this.f22281a = progressBarDialog;
        }

        public void run() {
            if (this.f22281a.f22290i.getMax() > this.f22281a.f22290i.getProgress() + this.f22281a.f22291j) {
                this.f22281a.f22290i.setProgress(this.f22281a.f22290i.getProgress() + 1);
                this.f22281a.f22284c.postDelayed(this, (long) ((this.f22281a.f22290i.getMax() - this.f22281a.f22290i.getProgress() <= 20 ? 50 : 0) + this.f22281a.f22287f));
                return;
            }
            this.f22281a.f22290i.setProgress(this.f22281a.f22290i.getMax() - this.f22281a.f22291j);
        }
    }

    public ProgressBarDialog(Activity activity, String str, ProgressBarDialogInterface progressBarDialogInterface) {
        super(activity, C1947R.style.Theme.Transparent);
        View inflate = activity.getLayoutInflater().inflate(C1947R.layout.progress_bar_dialog, null, false);
        setContentView(inflate);
        this.f22288g = (TextView) inflate.findViewById(C1947R.id.title_text);
        this.f22289h = (TextView) inflate.findViewById(C1947R.id.cancel_button);
        this.f22290i = (ProgressBar) inflate.findViewById(C1947R.id.progress_seek_bar);
        this.f22291j = 0;
        this.f22283b = progressBarDialogInterface;
        this.f22288g.setText(str);
        this.f22289h.setOnClickListener(new C45041(this));
    }

    public void m23682a(ProgressBarDialogInterface progressBarDialogInterface) {
        this.f22283b = progressBarDialogInterface;
    }

    public void m23683a(String str) {
        this.f22288g.setText(str);
    }

    public void mo6374a() {
        this.f22289h.setVisibility(4);
        this.f22286e = true;
    }

    public void m23684b() {
        this.f22290i.setProgress(0);
    }

    public void m23686c() {
        this.f22290i.setProgress(this.f22290i.getMax());
    }

    public void onBackPressed() {
        if (!this.f22286e) {
            if (this.f22283b != null) {
                this.f22283b.mo6384a();
            }
            super.onBackPressed();
        }
    }

    public void m23681a(int i) {
        this.f22287f = i;
    }

    public void m23685b(int i) {
        this.f22291j = i;
    }

    public void show() {
        super.show();
        this.f22284c = new Handler();
        this.f22285d = new C45052(this);
        this.f22284c.post(this.f22285d);
    }

    public void dismiss() {
        try {
            this.f22284c.removeCallbacks(this.f22285d);
            super.dismiss();
        } catch (Throwable e) {
            Log.d(f22282a, "dismiss - exception thrown: ", e);
        }
    }
}
