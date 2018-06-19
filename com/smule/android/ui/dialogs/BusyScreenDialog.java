package com.smule.android.ui.dialogs;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.C3482R;

public class BusyScreenDialog extends SmuleDialog {
    protected View f17708a;

    class C36531 implements AnimationListener {
        final /* synthetic */ BusyScreenDialog f17706a;

        C36531(BusyScreenDialog busyScreenDialog) {
            this.f17706a = busyScreenDialog;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            this.f17706a.dismiss();
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public BusyScreenDialog(Context context) {
        super(context);
        m18883a(context);
    }

    private void m18883a(Context context) {
        setContentView(C3482R.layout.cm_busy_screen_dialog);
        this.f17708a = findViewById(C3482R.id.root);
    }

    public void show() {
        super.show();
        Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        this.f17708a.startAnimation(alphaAnimation);
    }

    public void m18884a(long j) {
        Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setStartOffset(j);
        alphaAnimation.setDuration(200);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new C36531(this));
        this.f17708a.startAnimation(alphaAnimation);
    }
}
