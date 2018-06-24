/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v13.app.FragmentCompat
 *  android.support.v13.app.FragmentCompat$OnRequestPermissionsResultCallback
 *  android.support.v7.app.ActionBar
 *  android.support.v7.app.AppCompatActivity
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnLongClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.facebook.CallbackManager
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.SupposeUiThread
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.LifecycleLoggingFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.MediaPlayingPlayable;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PlaybackPresenter;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;

@EFragment
public abstract class BaseFragment
extends LifecycleLoggingFragment
implements FragmentCompat.OnRequestPermissionsResultCallback {
    private static final String g = BaseFragment.class.getName();
    protected boolean a = false;
    protected QuickReturnListViewMenuSyncer b;
    protected ActionBarHighlightMode c = ActionBarHighlightMode.a;
    protected boolean d;
    protected int e = 0;
    protected Handler f = new Handler(Looper.getMainLooper());
    private RunTimePermissionsRequester h;
    private Observer i;

    public BaseFragment() {
        this.i = new Observer(){

            @Override
            public void update(Observable object, Object object2) {
                if (object2 instanceof Runnable) {
                    object = (Runnable)object2;
                    BaseFragment.this.a(new Runnable((Runnable)object){
                        final /* synthetic */ Runnable a;
                        {
                            this.a = runnable;
                        }

                        @Override
                        public void run() {
                            if (BaseFragment.this.isAdded()) {
                                this.a.run();
                            }
                        }
                    });
                }
            }

        };
    }

    private void a(View view) {
        if (view instanceof ViewGroup) {
            view = (ViewGroup)view;
            int n = 0;
            while (n < view.getChildCount()) {
                View view2 = view.getChildAt(n);
                if (view2 instanceof EditText) {
                    EditText editText = (EditText)view2;
                    editText.setOnClickListener(null);
                    editText.setOnLongClickListener(null);
                    editText.setOnEditorActionListener(null);
                    editText.setOnFocusChangeListener(null);
                    editText.setOnTouchListener(null);
                    view.removeView(view2);
                    continue;
                }
                this.a(view2);
                ++n;
            }
        }
    }

    private MasterToolbar.FadeMode b(ActionBarHighlightMode actionBarHighlightMode) {
        switch (.a[actionBarHighlightMode.ordinal()]) {
            default: {
                return MasterToolbar.FadeMode.a;
            }
            case 1: {
                return MasterToolbar.FadeMode.b;
            }
            case 2: 
        }
        return MasterToolbar.FadeMode.c;
    }

    protected void A() {
    }

    public void B() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void C() {
        if (!(this.getActivity() instanceof MediaPlayingActivity)) return;
        try {
            ((MediaPlayingActivity)this.getActivity()).l();
            return;
        }
        catch (IllegalStateException illegalStateException) {
            Log.b(g, "Unable to obatin Audio Focus", illegalStateException);
            return;
        }
    }

    protected boolean D() {
        return true;
    }

    public void E() {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).ar();
        }
    }

    public void a(int n, Toaster duration) {
        if (!this.isAdded()) {
            Log.e(g, "showToast - getActivity() is null; aborting showing Toast");
            return;
        }
        this.a(this.getResources().getString(n), duration);
    }

    protected void a(Intent intent) {
        Log.b(this.x(), "popFragmentAndPassIntentToNowPlayingFragment called");
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a_(intent);
        }
    }

    protected void a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        this.a(absListView, actionBarHighlightMode, onScrollListener, ActionBarHighlightMode.c);
    }

    protected void a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener, ActionBarHighlightMode actionBarHighlightMode2) {
        this.q();
        this.b = this.p().a(absListView, actionBarHighlightMode, onScrollListener);
        this.a(actionBarHighlightMode2);
    }

    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2) {
        Log.b(this.x(), "showNowPlayingBarForPerformance called with tag: " + this.x());
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(performanceV2, bl, bl2);
        }
    }

    @SupposeUiThread
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, final @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        if (this.h != null) {
            throw new IllegalStateException("A permission request is already in progress");
        }
        this.h = new RunTimePermissionsRequester(runTimePermissionsRequest, new RunTimePermissionsRequester.ResultCallback(){

            @Override
            public void a(boolean bl, @NonNull Set<String> set) {
                BaseFragment.this.h = null;
                if (resultCallback != null) {
                    resultCallback.a(bl, set);
                }
            }
        });
        this.h.a(this);
    }

    public void a(SongbookEntry songbookEntry) {
        Log.b(this.x(), "playPreview - called");
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(songbookEntry, false, null);
        }
    }

    public void a(SongbookEntry songbookEntry, Analytics searchTarget) {
        Log.b(this.x(), "playPreview - called");
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(songbookEntry, false, searchTarget);
        }
    }

    protected void a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).a(songbookEntry, performanceV2);
        }
    }

    protected void a(ActionBarHighlightMode actionBarHighlightMode) {
        this.c = actionBarHighlightMode;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(BaseFragment baseFragment) {
        String string2 = baseFragment.x() != null ? baseFragment.x() : baseFragment.getClass().getName();
        Log.b(this.x(), "showFragment called with tag: " + string2);
        this.a(baseFragment, string2);
    }

    public void a(BaseFragment baseFragment, String string2) {
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(baseFragment, string2);
        }
    }

    protected void a(NowPlayingFragment nowPlayingFragment) {
        Log.b(this.x(), "popNowPlayingFragment called");
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(nowPlayingFragment);
        }
    }

    protected void a(CharSequence charSequence) {
        this.a(charSequence, null);
    }

    protected void a(CharSequence charSequence, CharSequence charSequence2) {
        this.a(charSequence, charSequence2, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(CharSequence charSequence, CharSequence charSequence2, int n) {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).a(charSequence, charSequence2, n);
            return;
        } else {
            charSequence2 = this.n();
            if (charSequence2 == null) return;
            {
                charSequence2.setTitle(charSequence);
                return;
            }
        }
    }

    protected void a(Runnable runnable) {
        this.f.post(runnable);
    }

    protected void a(final Runnable runnable, long l) {
        this.f.postDelayed(new Runnable(){

            @Override
            public void run() {
                BaseFragment.this.b(runnable);
            }
        }, l);
    }

    protected void a(String string2) {
        Log.b(this.x(), "popNowPlayingFragment called");
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(string2);
        }
    }

    protected void a(String string2, Toaster duration) {
        if (!this.isAdded()) {
            Log.e(g, "showToast - getActivity() is null; aborting showing Toast");
            return;
        }
        com.smule.android.utils.Toaster.a((Context)this.getActivity(), string2, duration);
    }

    protected void a(@NonNull List<MediaPlayingPlayable> list, int n) {
        Log.b(this.x(), "onContinuousPlayRequest called with list of size " + list.size() + ", starting index = " + n);
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.a(list, n);
        }
    }

    public boolean a(int n) {
        if (!this.isAdded()) {
            return false;
        }
        if (n != this.e) {
            Log.b(g, "Fragment load count was not equal. Possibly a network call returned from a previous view");
            return false;
        }
        return true;
    }

    public boolean a(int n, KeyEvent keyEvent) {
        return false;
    }

    public void a_(boolean bl) {
        this.a = bl;
    }

    public void b(int n) {
        this.a(n, Toaster.b);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(BaseFragment baseFragment) {
        Log.b(this.x(), "popFragment called with tag: " + this.x());
         baseFragmentResponder = this.p();
        String string2 = this.x();
        StringBuilder stringBuilder = new StringBuilder().append("popFragment -- baseFramentResponder == null? ");
        boolean bl = baseFragmentResponder == null;
        Log.b(string2, stringBuilder.append(bl).toString());
        if (baseFragmentResponder != null) {
            baseFragmentResponder.b(baseFragment);
        }
    }

    protected void b(Runnable runnable) {
        NotificationCenter.a().a(this.getClass().getName(), (Object)runnable);
    }

    public void b(String string2) {
        this.a(string2, Toaster.b);
    }

    public void b_(boolean bl) {
        this.d = bl;
        if (this.getActivity() instanceof MediaPlayingActivity && ((MediaPlayingActivity)this.getActivity()).U() != null) {
            ((MediaPlayingActivity)this.getActivity()).U().a(bl);
        }
    }

    public Menu c() {
        if (this.getActivity() instanceof MediaPlayingActivity && ((MediaPlayingActivity)this.getActivity()).U() != null) {
            return ((MediaPlayingActivity)this.getActivity()).U().getCustomMenu();
        }
        return null;
    }

    protected void c(int n) {
        this.a((CharSequence)this.getResources().getString(n));
    }

    protected void c(Runnable runnable) {
        this.f.removeCallbacks(runnable);
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        return true;
    }

    public boolean f() {
        return this.e();
    }

    public boolean g() {
        return false;
    }

    protected V3BillingHelper h() {
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            return baseFragmentResponder.n_();
        }
        return null;
    }

    protected void i() {
         baseFragmentResponder = this.p();
        if (baseFragmentResponder != null) {
            baseFragmentResponder.o_();
        }
    }

    public boolean j() {
        return true;
    }

    public boolean k() {
        return true;
    }

    public boolean l() {
        return true;
    }

    protected boolean m() {
        return false;
    }

    protected ActionBar n() {
        Activity activity = this.getActivity();
        if (activity instanceof AppCompatActivity) {
            return ((AppCompatActivity)activity).getSupportActionBar();
        }
        return null;
    }

    protected ProgressBar o() {
        Activity activity = this.getActivity();
        if (activity instanceof PreSingActivity) {
            return ((PreSingActivity)activity).a();
        }
        return null;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReferenceMonitor.a().a((Object)this);
    }

    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ++this.e;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        this.a(this.getView());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (this.h != null) {
            this.h.a();
            this.h = null;
        }
    }

    public void onPrepareOptionsMenu(Menu menu2) {
        super.onPrepareOptionsMenu(menu2);
        this.a(new Runnable(){

            @Override
            public void run() {
                BaseFragment.this.r();
            }
        }, 1);
    }

    public void onRequestPermissionsResult(int n, @NonNull String[] arrstring, @NonNull int[] arrn) {
        if (this.h != null) {
            this.h.a(this, n, arrstring, arrn);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.r();
        this.A();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.getActivity() instanceof MediaPlayingActivity && ((MediaPlayingActivity)this.getActivity()).U() != null) {
            ((MediaPlayingActivity)this.getActivity()).U().a((Object)this, this.d);
        }
        NotificationCenter.a().a(this.getClass().getName(), this.i);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!(this instanceof MediaPlayingFragment)) {
            MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        }
        NotificationCenter.a().b(this.getClass().getName(), this.i);
    }

    protected  p() {
        if (this.getActivity() instanceof ) {
            return this.getActivity();
        }
        return null;
    }

    protected void q() {
        if (this.b != null) {
            this.b.b();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void r() {
        block10 : {
            block9 : {
                Object object;
                if (!this.isAdded() || this.c == ActionBarHighlightMode.e) break block9;
                if (this.getActivity() instanceof MediaPlayingActivity && (object = ((MediaPlayingActivity)this.getActivity()).U()) != null) {
                    object.setFadeMode(this.b(this.c));
                    object.a();
                    if (!this.D()) {
                        object.getToolbarNavigationIconView().setVisibility(8);
                        ((ViewGroup.MarginLayoutParams)object.getTitleView().getLayoutParams()).leftMargin = (int)this.getResources().getDimension(2131428172);
                    } else {
                        object.getToolbarNavigationIconView().setVisibility(0);
                        ((ViewGroup.MarginLayoutParams)object.getTitleView().getLayoutParams()).leftMargin = 0;
                    }
                }
                if (this.getActivity() instanceof MasterActivity && (object = ((MasterActivity)this.getActivity()).G()) instanceof ProfileFragment && !((ProfileFragment)object).O()) {
                    ((MasterActivity)this.getActivity()).U().getToolbarView().setDisplayUpButton(true);
                }
                if (this.b != null) {
                    this.b.c();
                    return;
                }
                if (!(this.getActivity() instanceof MediaPlayingActivity) || ((MediaPlayingActivity)this.getActivity()).U() == null) break block9;
                if (this.c == ActionBarHighlightMode.b) {
                    ((MediaPlayingActivity)this.getActivity()).U().b(255, true);
                    return;
                }
                if (this.c == ActionBarHighlightMode.a || this.c == ActionBarHighlightMode.d) break block10;
            }
            return;
        }
        ((MediaPlayingActivity)this.getActivity()).U().b(0, true);
    }

    public void s() {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).V();
        }
    }

    public void u() {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).W();
        }
    }

    public void v() {
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).a(this.b(this.c));
        }
    }

    public void w() {
    }

    @Override
    public abstract String x();

    public boolean x_() {
        return false;
    }

    public void y() {
    }

    public void z() {
        this.A();
    }

    public static enum ActionBarHighlightMode {
        a,
        b,
        c,
        d,
        e,
        f;
        

        private ActionBarHighlightMode() {
        }
    }

}

