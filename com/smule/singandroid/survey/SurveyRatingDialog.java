package com.smule.singandroid.survey;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.TypefaceUtils;

public class SurveyRatingDialog extends CustomAlertDialog {
    private static final String f24255e = SurveyRatingDialog.class.getName();
    protected SurveyContext f24256c;
    protected SurveyConfig f24257d;

    class C49322 implements CustomAlertDialogListener {
        final /* synthetic */ SurveyRatingDialog f24318a;

        C49322(SurveyRatingDialog surveyRatingDialog) {
            this.f24318a = surveyRatingDialog;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f24318a.m19812c(false);
            SurveyPresenter.m25508a().m25550c();
        }
    }

    class C49343 extends AnimatorEndListener {
        final /* synthetic */ SurveyRatingDialog f24320a;

        class C49331 implements Runnable {
            final /* synthetic */ C49343 f24319a;

            C49331(C49343 c49343) {
                this.f24319a = c49343;
            }

            public void run() {
                if (this.f24319a.f24320a.isShowing()) {
                    SurveyPresenter.m25508a().m25554f();
                }
            }
        }

        C49343(SurveyRatingDialog surveyRatingDialog) {
            this.f24320a = surveyRatingDialog;
        }

        public void onAnimationEnd(Animator animator) {
            new Handler(Looper.getMainLooper()).postDelayed(new C49331(this), 850);
        }
    }

    protected SurveyRatingDialog(@NonNull Context context, @NonNull SurveyContext surveyContext, @NonNull SurveyConfig surveyConfig) {
        super(context, surveyConfig.mo6943e(), false, true, true);
        this.f24256c = surveyContext;
        this.f24257d = surveyConfig;
        m25489j();
        m25488a(this.f24257d.mo6939b(), C1947R.id.good_rating_icon);
        m25488a(this.f24257d.mo6941c(), C1947R.id.bad_rating_icon);
        m25490k();
    }

    private void m25489j() {
        m19805a(null);
        ((TextView) findViewById(C1947R.id.question_text)).setText(this.f24257d.mo6937a());
    }

    private void m25488a(final RatingInterface ratingInterface, @IdRes int i) {
        ImageView imageView = (ImageView) findViewById(i);
        imageView.setImageDrawable(SingApplication.f().getResources().getDrawable(ratingInterface.mo6949a()));
        imageView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SurveyRatingDialog f24317b;

            public void onClick(View view) {
                this.f24317b.m25491a(ratingInterface);
            }
        });
    }

    protected void m25491a(RatingInterface ratingInterface) {
        SurveyPresenter.m25508a().m25544a(ratingInterface);
    }

    private void m25490k() {
        m19800a(0, (int) C1947R.string.performance_rating_skip);
        Button button = (Button) findViewById(C1947R.id.noButton);
        button.setTextColor(ContextCompat.getColor(getContext(), C1947R.color.body_text_grey));
        button.setTypeface(TypefaceUtils.m26191b());
        m19803a(new C49322(this));
    }

    protected void m25492h() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(C1947R.id.rating_layout);
        final TextView textView = (TextView) findViewById(C1947R.id.post_rating_text);
        textView.setText(this.f24257d.mo6948j());
        final AnimatorEndListener c49343 = new C49343(this);
        final AnimatorEndListener c49354 = new AnimatorEndListener(this) {
            final /* synthetic */ SurveyRatingDialog f24323c;

            public void onAnimationEnd(Animator animator) {
                textView.animate().scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(250).setListener(c49343);
            }
        };
        relativeLayout.animate().setDuration(500).alpha(0.0f).setListener(new AnimatorEndListener(this) {
            final /* synthetic */ SurveyRatingDialog f24326c;

            public void onAnimationEnd(Animator animator) {
                textView.setVisibility(0);
                textView.setAlpha(0.0f);
                textView.animate().setDuration(250).alpha(100.0f).scaleX(1.2f).scaleY(1.2f).setListener(c49354);
            }
        });
        m19811c();
    }

    public void m25493i() {
        ((RelativeLayout) findViewById(C1947R.id.rating_layout)).setAlpha(0.0f);
        ((LinearLayout) findViewById(C1947R.id.button_layout)).setAlpha(0.0f);
    }
}
