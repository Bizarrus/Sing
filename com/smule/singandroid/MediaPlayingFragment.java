package com.smule.singandroid;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController.MediaPlayerObserver;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import com.smule.singandroid.utils.LayoutUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class MediaPlayingFragment extends BaseFragment {
    private static final String f18901G = MediaPlayingFragment.class.getName();
    protected boolean f18902A = false;
    protected AnimationDirection f18903B;
    protected int f18904C;
    protected int f18905D;
    protected int f18906E;
    protected OnGlobalLayoutListener f18907F = new OnGlobalLayoutListener(this, new C38774(this));
    private boolean f18908H;
    private Handler f18909I;
    private Runnable f18910J;
    private boolean f18911K;
    private ListenerWrapper f18912L = new ListenerWrapper();
    private MediaPlayerObserver f18913M = new C38785(this);
    @ViewById
    protected CustomToolbar f18914e;
    @ViewById
    protected SeekBar f18915f;
    @ViewById
    protected ImageView f18916g;
    @ViewById
    protected TextView f18917h;
    @ViewById
    protected View f18918i;
    @ViewById
    protected ImageView f18919j;
    @ViewById
    protected View f18920k;
    @ViewById
    protected Button f18921l;
    @ViewById
    protected TextView f18922m;
    @ViewById
    protected TextView f18923n;
    @ViewById
    protected ProgressBar f18924o;
    @ViewById
    protected RippleBackground f18925p;
    protected MediaPlayerServiceController f18926q;
    @ViewById
    protected View f18927r;
    @ViewById
    protected View f18928s;
    @ViewById
    protected FractionalRelativeLayout f18929t;
    @FragmentArg
    @InstanceState
    protected boolean f18930u = true;
    @FragmentArg
    @InstanceState
    protected boolean f18931v = true;
    protected int f18932w;
    protected boolean f18933x;
    protected boolean f18934y;
    protected boolean f18935z = false;

    class C38741 implements OnClickListener {
        final /* synthetic */ MediaPlayingFragment f18889a;

        C38741(MediaPlayingFragment mediaPlayingFragment) {
            this.f18889a = mediaPlayingFragment;
        }

        public void onClick(View view) {
            this.f18889a.mo6546B();
        }
    }

    class C38752 implements OnSeekBarChangeListener {
        final /* synthetic */ MediaPlayingFragment f18890a;

        C38752(MediaPlayingFragment mediaPlayingFragment) {
            this.f18890a = mediaPlayingFragment;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            this.f18890a.f18917h.setText(this.f18890a.m20387e(i));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            this.f18890a.f18908H = true;
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (!this.f18890a.f18926q.m24680l()) {
                this.f18890a.f18926q.m24648a(seekBar.getProgress());
                this.f18890a.f18908H = false;
                this.f18890a.m20371D();
            }
        }
    }

    class C38763 implements Runnable {
        final /* synthetic */ MediaPlayingFragment f18891a;

        C38763(MediaPlayingFragment mediaPlayingFragment) {
            this.f18891a = mediaPlayingFragment;
        }

        public void run() {
            if (this.f18891a.isAdded() && !this.f18891a.f18908H) {
                int g = (int) this.f18891a.f18926q.m24669g();
                this.f18891a.m20385d(g);
                if (!this.f18891a.f18911K) {
                    this.f18891a.f18911K = this.f18891a.mo6833f(g);
                }
                this.f18891a.f18909I.postDelayed(this, 500);
            }
        }
    }

    class C38774 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ MediaPlayingFragment f18892a;

        C38774(MediaPlayingFragment mediaPlayingFragment) {
            this.f18892a = mediaPlayingFragment;
        }

        public void onGlobalLayout() {
            if (this.f18892a.isAdded()) {
                LayoutUtils.m25859b(this.f18892a.f18929t, this.f18892a.f18907F);
                this.f18892a.f18904C = this.f18892a.f18927r.getHeight();
                this.f18892a.f18905D = this.f18892a.f18929t.getHeight();
                this.f18892a.f18932w = this.f18892a.f18905D - this.f18892a.f18904C;
                if (this.f18892a.f18931v) {
                    MediaPlayingFragment mediaPlayingFragment = this.f18892a;
                    mediaPlayingFragment.f18932w -= this.f18892a.f18906E;
                }
                this.f18892a.f18929t.setMiniBarHeight(this.f18892a.f18904C);
                this.f18892a.f18929t.setBottomMenuShowing(this.f18892a.f18931v);
            }
        }
    }

    class C38785 extends MediaPlayerObserver {
        final /* synthetic */ MediaPlayingFragment f18893a;

        C38785(MediaPlayingFragment mediaPlayingFragment) {
            this.f18893a = mediaPlayingFragment;
        }

        public void mo6476a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.m20379a(mediaPlayerServiceController, str);
        }

        public void mo6477b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.m20381b(mediaPlayerServiceController, str);
        }

        public void mo6478c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.m20383c(mediaPlayerServiceController, str);
        }

        public void mo6479d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.mo6831d(mediaPlayerServiceController, str);
        }

        public void mo6480e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.mo6545e(mediaPlayerServiceController, str);
        }

        public void mo6481f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.mo6832f(mediaPlayerServiceController, str);
        }

        public void mo6482g(MediaPlayerServiceController mediaPlayerServiceController, String str) {
            this.f18893a.m20392g(mediaPlayerServiceController, str);
        }
    }

    class C38796 implements AnimatorListener {
        final /* synthetic */ MediaPlayingFragment f18894a;

        C38796(MediaPlayingFragment mediaPlayingFragment) {
            this.f18894a = mediaPlayingFragment;
        }

        public void onAnimationStart(Animator animator) {
            this.f18894a.f18933x = true;
        }

        public void onAnimationEnd(Animator animator) {
            this.f18894a.f18933x = false;
        }

        public void onAnimationCancel(Animator animator) {
            this.f18894a.f18933x = false;
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    protected enum AnimationDirection {
        RAISE,
        LOWER,
        LOWER_SHOW_BOTTOM_MENU
    }

    private class ListenerWrapper extends AnimatorListenerAdapter {
        final /* synthetic */ MediaPlayingFragment f18899a;
        private AnimatorListenerAdapter f18900b;

        private ListenerWrapper(MediaPlayingFragment mediaPlayingFragment) {
            this.f18899a = mediaPlayingFragment;
        }

        public void m20362a(AnimatorListenerAdapter animatorListenerAdapter) {
            this.f18900b = animatorListenerAdapter;
        }

        private void m20361a() {
            this.f18899a.f18934y = false;
            if (this.f18899a.f18935z) {
                this.f18899a.f18929t.setY((float) this.f18899a.f18932w);
            }
        }

        public void onAnimationCancel(Animator animator) {
            if (this.f18899a.isAdded()) {
                if (this.f18900b != null) {
                    this.f18900b.onAnimationCancel(animator);
                }
                m20361a();
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f18899a.isAdded()) {
                if (this.f18899a.f18935z) {
                    this.f18899a.f18928s.setAlpha(0.0f);
                } else {
                    this.f18899a.f18927r.setVisibility(8);
                    this.f18899a.m20375H();
                }
                if (this.f18900b != null) {
                    this.f18900b.onAnimationEnd(animator);
                }
                m20361a();
            }
        }

        public void onAnimationRepeat(Animator animator) {
            if (this.f18899a.isAdded() && this.f18900b != null) {
                this.f18900b.onAnimationRepeat(animator);
            }
        }

        public void onAnimationStart(Animator animator) {
            if (this.f18899a.isAdded()) {
                if (this.f18899a.f18935z) {
                    this.f18899a.f18927r.setVisibility(0);
                } else {
                    this.f18899a.f18928s.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                }
                if (this.f18900b != null) {
                    this.f18900b.onAnimationStart(animator);
                }
            }
        }
    }

    protected abstract boolean mo6543a();

    protected void m20394z() {
        Log.b(mo6383s(), "setupAudioUI - called");
        OnClickListener c38741 = new C38741(this);
        this.f18916g.setOnClickListener(c38741);
        this.f18920k.setOnClickListener(c38741);
        this.f18921l.setOnClickListener(c38741);
        this.f18915f.setOnSeekBarChangeListener(new C38752(this));
    }

    protected void m20368A() {
        m20385d((int) this.f18926q.m24669g());
        if (this.f18926q.m24676j()) {
            if (this.f18925p != null) {
                this.f18925p.m23474a();
            }
            mo6549b(true);
            m20371D();
            return;
        }
        if (this.f18925p != null) {
            this.f18925p.m23476b();
        }
        mo6549b(false);
        m20372E();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19831a(ActionBarHighlightMode.IGNORE);
        this.f18926q = MediaPlayerServiceController.m24641a();
        this.f18906E = (int) getResources().getDimension(C1947R.dimen.row_single_height);
    }

    public void onStart() {
        super.onStart();
        m19831a(ActionBarHighlightMode.IGNORE);
        if (MediaPlayerServiceController.m24641a().m24676j()) {
            m20371D();
        }
    }

    public void onResume() {
        super.onResume();
        m20368A();
        this.f18926q.m24652a(this.f18913M);
    }

    public void onPause() {
        Log.b(f18901G, "onPause");
        super.onPause();
        this.f18926q.m24659b(this.f18913M);
    }

    public void onStop() {
        super.onStop();
        m20372E();
    }

    @UiThread
    protected void mo6546B() {
        Log.b(mo6383s(), "toggleMediaPlayerPlayState - begin");
        if (!isAdded()) {
            Log.d(f18901G, "toggleMediaPlayerPlayState - fragment not added; aborting");
        } else if (this.f18926q.m24680l()) {
            Log.b(mo6383s(), "toggleMediaPlayerPlayState - already loading; returning");
        } else {
            this.f18926q.m24663d();
        }
    }

    @UiThread
    protected void mo6549b(boolean z) {
        if (!isAdded()) {
            return;
        }
        if (z) {
            Log.b(mo6383s(), "refreshPlayButtons - is playing");
            this.f18916g.setImageDrawable(getResources().getDrawable(C1947R.drawable.btn_playingbar_pause_gray));
            ImageUtils.a(this.f18921l, getResources().getDrawable(C1947R.drawable.icn_album_cover_pause_small));
            return;
        }
        Log.b(mo6383s(), "refreshPlayButtons - is not playing");
        this.f18916g.setImageDrawable(getResources().getDrawable(C1947R.drawable.btn_playingbar_play_gray));
        ImageUtils.a(this.f18921l, getResources().getDrawable(C1947R.drawable.icn_album_cover_play_small));
    }

    @SupposeUiThread
    protected void mo6547C() {
        Log.b(mo6383s(), "configureViewsForPlayStart - called");
        if (isAdded()) {
            int f = (int) this.f18926q.m24667f();
            this.f18915f.setMax(f);
            this.f18924o.setMax(f);
            this.f18924o.setVisibility(4);
            this.f18908H = false;
            this.f18918i.setVisibility(8);
        }
    }

    protected void m20371D() {
        Log.b(mo6383s(), "startSeekBarHandler - called");
        m20372E();
        this.f18924o.setVisibility(0);
        int f = (int) this.f18926q.m24667f();
        this.f18915f.setMax(f);
        this.f18924o.setMax(f);
        this.f18909I = new Handler();
        this.f18910J = new C38763(this);
        this.f18909I.postDelayed(this.f18910J, 500);
    }

    protected void m20372E() {
        if (this.f18909I != null) {
            this.f18909I.removeCallbacks(this.f18910J);
        }
    }

    protected void m20385d(int i) {
        this.f18915f.setProgress(i);
        this.f18924o.setProgress(i);
    }

    protected String m20387e(int i) {
        int i2 = i / 1000;
        int floor = (int) Math.floor((double) (i2 % 60));
        return ((int) Math.floor((double) (i2 / 60))) + ":" + (floor < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "") + floor;
    }

    protected boolean mo6833f(int i) {
        return true;
    }

    public boolean m20373F() {
        return this.f18902A;
    }

    public void mo6542G() {
        if (!m20373F()) {
            mo6829a(AnimationDirection.RAISE, null);
        }
    }

    protected void mo6829a(AnimationDirection animationDirection, AnimatorListenerAdapter animatorListenerAdapter) {
        float f = 0.0f;
        if (isAdded()) {
            float f2;
            this.f18934y = true;
            if (animationDirection == AnimationDirection.RAISE) {
                this.f18935z = false;
                this.f18902A = true;
                mo6420v();
                f2 = 0.0f;
            } else if (this.f18902A) {
                this.f18935z = true;
                this.f18902A = false;
                float f3 = (float) (this.f18905D - this.f18904C);
                Activity activity = getActivity();
                if ((activity instanceof MediaPlayingFragmentResponder) && ((MediaPlayingFragmentResponder) activity).M()) {
                    f = f3 - ((float) this.f18906E);
                } else {
                    f = f3;
                }
                if (activity instanceof MasterActivity) {
                    ((MasterActivity) activity).H();
                }
                f2 = f;
                f = 1.0f;
            } else {
                Log.d(f18901G, "animateFragment: fragment is already lowered; aborting");
                this.f18934y = false;
                if (animatorListenerAdapter != null) {
                    animatorListenerAdapter.onAnimationEnd(null);
                    return;
                }
                return;
            }
            this.f18903B = animationDirection;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f18929t, "y", new float[]{f2});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f18927r, "alpha", new float[]{f});
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
            animatorSet.setDuration(450);
            this.f18912L.m20362a(animatorListenerAdapter);
            animatorSet.addListener(this.f18912L);
            animatorSet.start();
            if (this.f18925p != null) {
                if (this.f18935z) {
                    this.f18925p.m23476b();
                } else if (this.f18926q.m24676j() && !mo6543a()) {
                    this.f18925p.m23474a();
                }
            }
            if (animationDirection == AnimationDirection.LOWER_SHOW_BOTTOM_MENU) {
                m20376I();
            }
        }
    }

    protected void m20375H() {
        Log.b(f18901G, "hideBottomMenu - called");
        if (m19862m() != null) {
            m19862m().a(BaseFragment$BaseFragmentResponder$MenuToggleAction.HIDE);
        }
    }

    protected void m20376I() {
        Log.b(f18901G, "showBottomMenu - called");
        if (m19862m() != null) {
            m19862m().a(BaseFragment$BaseFragmentResponder$MenuToggleAction.SHOW_IF_ALLOWED);
        }
    }

    public void m20391g(int i) {
        if (isAdded()) {
            this.f18932w = (this.f18905D + i) - this.f18904C;
            if (!this.f18934y && !this.f18933x) {
                this.f18929t.setY((float) this.f18932w);
            }
        }
    }

    public void m20393h(int i) {
        if (isAdded()) {
            this.f18932w = (this.f18905D + i) - this.f18904C;
            if (!this.f18934y && !this.f18933x) {
                this.f18929t.animate().setDuration(200).y((float) this.f18932w);
            }
        }
    }

    public void m20384c(boolean z) {
        if (isAdded() && !this.f18902A) {
            this.f18931v = z;
            this.f18929t.setBottomMenuShowing(z);
            m20391g(z ? -this.f18906E : 0);
        }
    }

    @AfterViews
    protected void m20377J() {
        LayoutUtils.m25854a(this.f18929t, this.f18907F);
    }

    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.m25859b(this.f18929t, this.f18907F);
    }

    protected void m20379a(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        if (this.f18925p != null) {
            this.f18925p.m23474a();
        }
        mo6549b(true);
        m20371D();
    }

    protected void m20381b(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        if (this.f18925p != null) {
            this.f18925p.m23476b();
        }
        m20372E();
        mo6549b(false);
    }

    protected void m20383c(MediaPlayerServiceController mediaPlayerServiceController, String str) {
    }

    protected void mo6831d(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        mo6547C();
        this.f18930u = false;
    }

    protected void mo6545e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        Log.d(f18901G, "onMediaLoadFailedCallback: " + str);
    }

    protected void mo6832f(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        m20372E();
        this.f18915f.setProgress(0);
        this.f18926q.m24648a(0);
        mo6549b(false);
    }

    protected void m20392g(MediaPlayerServiceController mediaPlayerServiceController, String str) {
    }

    public Animator onCreateAnimator(int i, boolean z, int i2) {
        if (i2 == 0) {
            return null;
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(getActivity(), i2);
        if (loadAnimator == null) {
            return loadAnimator;
        }
        this.f18933x = true;
        loadAnimator.addListener(new C38796(this));
        return loadAnimator;
    }
}
