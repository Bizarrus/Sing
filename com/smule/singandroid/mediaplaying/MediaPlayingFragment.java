/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorInflater
 *  android.animation.AnimatorListenerAdapter
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.support.v4.content.ContextCompat
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewPropertyAnimator
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.ProgressBar
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  com.smule.singandroid.utils.FractionalRelativeLayout
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.mediaplaying;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.media_player_service.MediaPlayerService;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.DummyPlaybackPresenter;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
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
public abstract class MediaPlayingFragment
extends BaseFragment {
    private static final String P = MediaPlayingFragment.class.getName();
    @ViewById
    protected FractionalRelativeLayout A;
    @FragmentArg
    @InstanceState
    protected boolean B = true;
    @FragmentArg
    @InstanceState
    protected boolean C = true;
    @FragmentArg
    protected int D = -1;
    PlaybackPresenter E = new DummyPlaybackPresenter();
    protected int F;
    protected boolean G;
    protected boolean H;
    protected boolean I = false;
    protected boolean J = false;
    protected AnimationDirection K;
    protected int L;
    protected int M;
    protected int N;
    protected WeakListener.OnGlobalLayoutListener O;
    private boolean Q;
    private Handler R;
    private Runnable S;
    private Runnable T = null;
    private boolean U;
    private ListenerWrapper V;
    private Runnable W;
    private MediaPlayerServiceController.MediaPlayerObserver X;
    @ViewById
    protected CustomToolbar g;
    @ViewById
    protected SeekBar h;
    @ViewById
    protected IconFontView i;
    @ViewById
    protected IconFontView j;
    @ViewById
    protected IconFontView k;
    @ViewById
    protected View l;
    @ViewById
    protected View m;
    @ViewById
    protected TextView n;
    @ViewById
    protected View o;
    @ViewById
    protected IconFontView p;
    @ViewById
    protected IconFontView q;
    @ViewById
    protected IconFontView r;
    @ViewById
    protected View s;
    @ViewById
    protected TextView t;
    @ViewById
    protected TextView u;
    @ViewById
    protected ProgressBar v;
    @ViewById
    protected RippleBackground w;
    protected MediaPlayerServiceController x;
    @ViewById
    protected View y;
    @ViewById
    protected View z;

    public MediaPlayingFragment() {
        this.V = new ListenerWrapper();
        this.W = null;
        this.O = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                block5 : {
                    block4 : {
                        if (!MediaPlayingFragment.this.isAdded()) break block4;
                        LayoutUtils.b((View)MediaPlayingFragment.this.A, (WeakListener.OnGlobalLayoutListener)MediaPlayingFragment.this.O);
                        MediaPlayingFragment.this.M = MediaPlayingFragment.this.A.getHeight();
                        MediaPlayingFragment.this.F = MediaPlayingFragment.this.M - MediaPlayingFragment.this.L;
                        if (MediaPlayingFragment.this.C) {
                            MediaPlayingFragment mediaPlayingFragment = MediaPlayingFragment.this;
                            mediaPlayingFragment.F -= MediaPlayingFragment.this.N;
                        }
                        MediaPlayingFragment.this.A.setBottomMenuShowing(MediaPlayingFragment.this.C);
                        if (MediaPlayingFragment.this.W != null) break block5;
                    }
                    return;
                }
                MediaPlayingFragment.this.W.run();
                MediaPlayingFragment.this.W = null;
            }
        });
        this.X = new MediaPlayerServiceController.MediaPlayerObserver(){

            @Override
            public void a(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.e(mediaPlayerServiceController, string2);
            }

            @Override
            public void b(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.d(mediaPlayerServiceController, string2);
            }

            @Override
            public void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.a(mediaPlayerServiceController, string2);
            }

            @Override
            public void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.b(mediaPlayerServiceController, string2);
            }

            @Override
            public void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.c(mediaPlayerServiceController, string2);
            }

            @Override
            public void g(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.f(mediaPlayerServiceController, string2);
            }

            @Override
            public void h(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
                MediaPlayingFragment.this.g(mediaPlayerServiceController, string2);
            }
        };
    }

    private void g(boolean bl) {
        if (this.T != null) {
            this.f.removeCallbacks(this.T);
            this.T = null;
        }
        if (bl) {
            this.T = new Runnable(){

                @Override
                public void run() {
                    MediaPlayingFragment.this.T = null;
                    if (MediaPlayingFragment.this.m != null && MediaPlayingFragment.this.m.getVisibility() != 0) {
                        Log.e(P, "HUD is already hidden");
                        return;
                    }
                    MediaPlayingFragment.this.Q();
                }
            };
            this.f.postDelayed(this.T, 3000);
        }
    }

    protected void F() {
        this.d((int)this.x.g());
        if (this.x.j()) {
            if (this.w != null) {
                this.w.a();
            }
            this.c(true);
            this.I();
            return;
        }
        if (this.w != null) {
            this.w.b();
        }
        this.c(false);
        this.J();
    }

    @UiThread
    protected void G() {
        Log.b(this.x(), "toggleMediaPlayerPlayState - begin");
        if (!this.isAdded()) {
            Log.d(P, "toggleMediaPlayerPlayState - fragment not added; aborting");
            return;
        }
        if (this.x.l()) {
            Log.b(this.x(), "toggleMediaPlayerPlayState - already loading; returning");
            return;
        }
        this.x.d();
    }

    @SupposeUiThread
    protected void H() {
        Log.b(this.x(), "configureViewsForPlayStart - called");
        if (!this.isAdded()) {
            return;
        }
        int n = (int)this.x.f();
        this.h.setMax(n);
        this.v.setMax(n);
        this.v.setVisibility(4);
        this.Q = false;
        this.o.setVisibility(8);
    }

    protected void I() {
        Log.b(this.x(), "startSeekBarHandler - called");
        this.J();
        this.v.setVisibility(0);
        int n = (int)this.x.f();
        this.h.setMax(n);
        this.v.setMax(n);
        this.R = new Handler();
        this.S = new Runnable(){

            @Override
            public void run() {
                if (!MediaPlayingFragment.this.isAdded() || MediaPlayingFragment.this.Q) {
                    return;
                }
                int n = (int)MediaPlayingFragment.this.x.g();
                MediaPlayingFragment.this.d(n);
                if (!MediaPlayingFragment.this.U) {
                    MediaPlayingFragment.this.U = MediaPlayingFragment.this.f(n);
                }
                MediaPlayingFragment.this.R.postDelayed((Runnable)this, 500);
            }
        };
        this.R.postDelayed(this.S, 500);
    }

    protected void J() {
        if (this.R != null) {
            this.R.removeCallbacks(this.S);
        }
    }

    public boolean K() {
        return this.J;
    }

    public void L() {
        if (this.K()) {
            return;
        }
        this.a(AnimationDirection.a, null);
    }

    protected void M() {
        Log.b(P, "hideBottomMenu - called");
        if (this.p() != null) {
            this.p().a(BaseFragment.b);
        }
    }

    protected void N() {
        Log.b(P, "showBottomMenu - called");
        if (this.p() != null) {
            this.p().a(BaseFragment.c);
        }
    }

    @AfterViews
    protected void O() {
        this.E = this.p().i();
        this.E.a(this, this.D);
        LayoutUtils.a((View)this.A, (WeakListener.OnGlobalLayoutListener)this.O);
        this.p.setText(2131297814);
        this.r.setText(2131297832);
    }

    protected void P() {
        if (this.m.getVisibility() == 0) {
            this.Q();
            return;
        }
        this.e(true);
    }

    protected void Q() {
        if (this.m == null) {
            return;
        }
        this.g(false);
        if (this.m.getVisibility() == 0) {
            this.m.setVisibility(4);
            this.n.setVisibility(4);
            this.l.setVisibility(8);
        }
        this.f(false);
    }

    protected void a(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        if (this.w != null) {
            this.w.a();
        }
        if (this.K()) {
            SingAppboy.a().d();
        }
        this.c(true);
        this.I();
        this.E.b(this.D, string2);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void a(final AnimationDirection animationDirection, final AnimatorListenerAdapter animatorListenerAdapter) {
        float f;
        float f2;
        Activity activity;
        float f3 = 1.0f;
        if (!this.isAdded()) {
            return;
        }
        this.W = this.M == 0 ? new Runnable(){

            @Override
            public void run() {
                MediaPlayingFragment.this.a(animationDirection, animatorListenerAdapter);
            }
        } : null;
        this.H = true;
        if (animationDirection == AnimationDirection.a || animationDirection == AnimationDirection.b) {
            this.I = false;
            this.J = true;
            f2 = 1.0f - (float)(this.M - this.L) / ((float)this.M * 1.0f);
            f = 0.0f;
            this.A();
        } else {
            if (!this.J) {
                Log.d(P, "animateFragment: fragment is already lowered; aborting");
                this.H = false;
                if (animatorListenerAdapter == null) return;
                animatorListenerAdapter.onAnimationEnd(null);
                return;
            }
            this.I = true;
            this.J = false;
            int n = this.M - this.L;
            activity = this.getActivity();
            if (activity instanceof  && (activity).N()) {
                n -= this.N;
            }
            f3 = (float)n / ((float)this.M * 1.0f);
            if (activity instanceof MasterActivity) {
                ((MasterActivity)activity).I();
            }
            f2 = 1.0f;
            f = 1.0f;
            f3 = 1.0f - f3;
        }
        this.K = animationDirection;
        activity = ObjectAnimator.ofFloat((Object)this.A, (String)"yFraction", (float[])new float[]{f2, f3});
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)this.y, (String)"alpha", (float[])new float[]{f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{activity, objectAnimator});
        if (animationDirection == AnimationDirection.b || animationDirection == AnimationDirection.d) {
            animatorSet.setDuration(0);
        } else {
            animatorSet.setDuration(450);
        }
        this.V.a(animatorListenerAdapter);
        animatorSet.addListener((Animator.AnimatorListener)this.V);
        animatorSet.start();
        if (this.w != null) {
            if (this.I) {
                this.w.b();
            } else if (this.x.j() && !this.a()) {
                this.w.a();
            }
        }
        if (animationDirection != AnimationDirection.e) return;
        this.N();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, boolean bl2, MediaPlayingPlayable object) {
        int n = 4;
        int n2 = 0;
        object = this.k;
        int n3 = bl ? 0 : 4;
        object.setVisibility(n3);
        object = this.j;
        n3 = n;
        if (bl2) {
            n3 = 0;
        }
        object.setVisibility(n3);
        object = this.r;
        n3 = bl2 ? 0 : 8;
        object.setVisibility(n3);
        object = this.s;
        n3 = bl2 ? n2 : 8;
        object.setVisibility(n3);
    }

    protected abstract boolean a();

    /*
     * Enabled aggressive block sorting
     */
    protected void b(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        SingAppboy.a().e();
        if (this.w != null) {
            this.w.b();
        }
        this.J();
        this.c(false);
        if (MediaPlayerService.a() == null) {
            Log.e(P, "onMediaPausedCallback - MediaPlayerService destroyed. PlaylistIndex=" + this.D + ", audioId=" + string2);
            return;
        } else {
            if (!MediaPlayerService.a().e()) return;
            {
                this.E.c(this.D, string2);
                return;
            }
        }
    }

    protected void c(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
    }

    @UiThread
    protected void c(boolean bl) {
        block3 : {
            block2 : {
                if (!this.isAdded()) break block2;
                if (!bl) break block3;
                Log.b(this.x(), "refreshPlayButtons - is playing");
                this.i.setText(2131297830);
                this.q.setText(2131297830);
                this.Q();
            }
            return;
        }
        Log.b(this.x(), "refreshPlayButtons - is not playing");
        this.i.setText(2131297831);
        this.q.setText(2131297831);
        this.e(false);
    }

    protected void d(int n) {
        this.h.setProgress(n);
        this.v.setProgress(n);
    }

    protected void d(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        this.H();
        this.B = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d(boolean bl) {
        if (!this.isVisible() || this.J) {
            return;
        }
        this.C = bl;
        this.A.setBottomMenuShowing(bl);
        int n = bl ? - this.N : 0;
        this.g(n);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected String e(int n) {
        String string2;
        int n2 = n / 1000;
        n = (int)Math.floor(n2 / 60);
        n2 = (int)Math.floor(n2 % 60);
        StringBuilder stringBuilder = new StringBuilder().append(n).append(":");
        if (n2 < 10) {
            string2 = "0";
            do {
                return stringBuilder.append(string2).append(n2).toString();
                break;
            } while (true);
        }
        string2 = "";
        return stringBuilder.append(string2).append(n2).toString();
    }

    protected void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        Log.d(P, "onMediaLoadFailedCallback: " + string2);
    }

    protected void e(boolean bl) {
        if (this.m == null) {
            return;
        }
        this.g(bl);
        if (this.m.getVisibility() != 0) {
            this.m.setVisibility(0);
            this.n.setVisibility(0);
            this.l.setVisibility(0);
        }
        this.f(true);
    }

    protected void f(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        if (this.w != null) {
            this.w.b();
        }
        this.J();
        this.h.setProgress(0);
        this.x.a(0);
        this.c(false);
    }

    protected void f(boolean bl) {
        if (this.h == null) {
            return;
        }
        if (bl) {
            this.h.setThumb(ContextCompat.getDrawable((Context)this.h.getContext(), (int)2130838221));
            return;
        }
        this.h.setThumb(ContextCompat.getDrawable((Context)this.h.getContext(), (int)2130838222));
    }

    protected boolean f(int n) {
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void g(int n) {
        block3 : {
            block2 : {
                if (!this.isAdded()) break block2;
                this.F = this.M + n - this.L;
                if (!this.H && !this.G) break block3;
            }
            return;
        }
        this.A.setY((float)this.F);
    }

    protected void g(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void h(int n) {
        block3 : {
            block2 : {
                if (!this.isAdded()) break block2;
                this.F = this.M + n - this.L;
                if (!this.H && !this.G) break block3;
            }
            return;
        }
        this.A.animate().setDuration(200).y((float)this.F);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a(BaseFragment.ActionBarHighlightMode.e);
        this.x = MediaPlayerServiceController.a();
        this.N = (int)this.getResources().getDimension(2131427817);
        this.L = (int)this.getResources().getDimension(2131427627);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Animator onCreateAnimator(int n, boolean bl, int n2) {
        Animator animator2;
        if (n2 == 0) {
            return null;
        }
        Animator animator3 = animator2 = AnimatorInflater.loadAnimator((Context)this.getActivity(), (int)n2);
        if (animator2 == null) return animator3;
        this.G = true;
        animator2.addListener(new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
                MediaPlayingFragment.this.G = false;
            }

            public void onAnimationEnd(Animator animator2) {
                MediaPlayingFragment.this.G = false;
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                MediaPlayingFragment.this.G = true;
            }
        });
        return animator2;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.b((View)this.A, (WeakListener.OnGlobalLayoutListener)this.O);
        this.g(false);
    }

    @Override
    public void onPause() {
        Log.b(P, "onPause");
        super.onPause();
        this.x.b(this.X);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.F();
        this.x.a(this.X);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.a(BaseFragment.ActionBarHighlightMode.e);
        if (MediaPlayerServiceController.a().j()) {
            this.I();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        this.J();
    }

    protected void t() {
        Log.b(this.x(), "setupAudioUI - called");
        View.OnClickListener onClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                MediaPlayingFragment.this.G();
            }
        };
        this.i.setOnClickListener(onClickListener);
        this.q.setOnClickListener(onClickListener);
        this.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (MediaPlayingFragment.this.isAdded()) {
                    MediaPlayingFragment.this.E.a(MediaPlayingFragment.this.D, PlaybackPresenter.ClickSource.a);
                }
            }
        });
        this.r.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (MediaPlayingFragment.this.isAdded()) {
                    MediaPlayingFragment.this.E.a(MediaPlayingFragment.this.D, PlaybackPresenter.ClickSource.b);
                }
            }
        });
        this.k.setOnClickListener(new View.OnClickListener(){
            boolean a;
            {
                this.a = false;
            }

            public void onClick(View view) {
                block5 : {
                    block4 : {
                        if (!MediaPlayingFragment.this.isAdded()) break block4;
                        if (!this.a) break block5;
                        Log.b(P, "further clicks absorbed on view " + view.getId());
                    }
                    return;
                }
                if (MediaPlayingFragment.this.x.g() > 3000) {
                    MediaPlayingFragment.this.x.a(0);
                    MediaPlayingFragment.this.h.setProgress(0);
                    return;
                }
                this.a = true;
                MediaPlayingFragment.this.E.b(MediaPlayingFragment.this.D, PlaybackPresenter.ClickSource.a);
            }
        });
        this.h.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                MediaPlayingFragment.this.n.setText((CharSequence)(MediaPlayingFragment.this.e(n) + " / " + MediaPlayingFragment.this.e(MediaPlayingFragment.this.h.getMax())));
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                MediaPlayingFragment.this.e(false);
                MediaPlayingFragment.this.Q = true;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                MediaPlayingFragment.this.e(true);
                if (!MediaPlayingFragment.this.x.l()) {
                    MediaPlayingFragment.this.x.a(seekBar.getProgress());
                    MediaPlayingFragment.this.Q = false;
                    MediaPlayingFragment.this.I();
                }
            }
        });
    }

    protected static enum AnimationDirection {
        a,
        b,
        c,
        d,
        e;
        

        private AnimationDirection() {
        }
    }

    private class ListenerWrapper
    extends AnimatorListenerAdapter {
        private AnimatorListenerAdapter b;

        private ListenerWrapper() {
        }

        private void a() {
            MediaPlayingFragment.this.H = false;
            if (MediaPlayingFragment.this.I) {
                MediaPlayingFragment.this.A.setYFractionMinibar(1.0f);
            }
        }

        public void a(AnimatorListenerAdapter animatorListenerAdapter) {
            this.b = animatorListenerAdapter;
        }

        public void onAnimationCancel(Animator animator2) {
            if (!MediaPlayingFragment.this.isAdded()) {
                return;
            }
            if (this.b != null) {
                this.b.onAnimationCancel(animator2);
            }
            this.a();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onAnimationEnd(Animator animator2) {
            if (!MediaPlayingFragment.this.isAdded()) {
                return;
            }
            if (MediaPlayingFragment.this.I) {
                MediaPlayingFragment.this.z.setAlpha(0.0f);
            } else {
                MediaPlayingFragment.this.y.setVisibility(8);
                MediaPlayingFragment.this.M();
            }
            if (this.b != null) {
                this.b.onAnimationEnd(animator2);
            }
            this.a();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onAnimationRepeat(Animator animator2) {
            if (!MediaPlayingFragment.this.isAdded() || this.b == null) {
                return;
            }
            this.b.onAnimationRepeat(animator2);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onAnimationStart(Animator animator2) {
            block6 : {
                block5 : {
                    if (!MediaPlayingFragment.this.isAdded()) break block5;
                    if (MediaPlayingFragment.this.I) {
                        MediaPlayingFragment.this.y.setVisibility(0);
                    } else {
                        MediaPlayingFragment.this.z.setAlpha(1.0f);
                    }
                    if (this.b != null) break block6;
                }
                return;
            }
            this.b.onAnimationStart(animator2);
        }
    }

}

