package com.smule.singandroid.dialogs;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;

public class BusyScreenDialog extends SmuleDialog {
    protected TextView f20413b;
    protected TextView f20414c;
    protected View f20415d;

    public BusyScreenDialog(Context context, int i) {
        this(context, context.getResources().getString(i));
    }

    public BusyScreenDialog(Context context, String str) {
        this(context, str, null);
    }

    public BusyScreenDialog(Context context, String str, String str2) {
        super(context, C1947R.style.Sing.Dialog.BusyScreen, false);
        setContentView(C1947R.layout.busy_screen_dialog);
        this.f20415d = findViewById(C1947R.id.root);
        ((ProgressBar) findViewById(C1947R.id.progress_bar)).getIndeterminateDrawable().setColorFilter(context.getResources().getColor(C1947R.color.button_text_inverse), Mode.SRC_ATOP);
        this.f20413b = (TextView) findViewById(C1947R.id.busy_title_text_view);
        this.f20414c = (TextView) findViewById(C1947R.id.sub_title_text_view);
        this.f20413b.setText(str);
        if (str2 != null) {
            this.f20414c.setText(str2);
        }
    }

    public void show() {
        super.show();
        Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        this.f20415d.startAnimation(alphaAnimation);
    }

    public void onBackPressed() {
    }
}
