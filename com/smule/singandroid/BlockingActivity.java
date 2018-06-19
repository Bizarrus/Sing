package com.smule.singandroid;

import android.app.AlertDialog;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EActivity;

@EActivity
public abstract class BlockingActivity extends BaseActivity {
    private AlertDialog f18409g;

    protected abstract AlertDialog mo6371a();

    protected void onResume() {
        super.onResume();
        if (this.f18409g == null || !this.f18409g.isShowing()) {
            this.f18409g = mo6371a();
            this.f18409g.show();
            SingAnalytics.m26152p();
        }
    }
}
