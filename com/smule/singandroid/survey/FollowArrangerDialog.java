package com.smule.singandroid.survey;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog;

public class FollowArrangerDialog extends CustomAlertDialog {
    private ProfileImageWithVIPBadge f24272c = ((ProfileImageWithVIPBadge) findViewById(C1947R.id.profile_pic));
    private TextView f24273d = ((TextView) findViewById(C1947R.id.profile_handle));

    class C49211 implements AnimatorListener {
        final /* synthetic */ FollowArrangerDialog f24271a;

        C49211(FollowArrangerDialog followArrangerDialog) {
            this.f24271a = followArrangerDialog;
        }

        public void onAnimationStart(Animator animator) {
            this.f24271a.m19817g();
        }

        public void onAnimationEnd(Animator animator) {
            this.f24271a.f24272c.setScaleY(0.0f);
            this.f24271a.f24272c.setScaleX(0.0f);
            this.f24271a.m19816f().animate().alpha(100.0f).setInterpolator(new AccelerateInterpolator(2.0f)).setDuration(700);
            this.f24271a.f24272c.animate().scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setInterpolator(new OvershootInterpolator(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)).setDuration(615);
            this.f24271a.f24273d.setTranslationY(-20.0f);
            this.f24271a.f24273d.animate().translationY(0.0f).setInterpolator(new DecelerateInterpolator(2.0f)).setDuration(950);
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    public FollowArrangerDialog(Context context, AccountIcon accountIcon, CustomAlertDialog customAlertDialog) {
        super(context, C1947R.layout.rating_follow_contents, true, true, true, customAlertDialog);
        this.f24273d.setText(accountIcon.handle);
        this.f24272c.setAccount(accountIcon);
        m19802a(new C49211(this));
        m19805a(null);
        m19800a(FollowManager.m18204a().m18222a(accountIcon.accountId) ? C1947R.string.core_following : C1947R.string.core_follow, (int) C1947R.string.core_skip);
    }
}
