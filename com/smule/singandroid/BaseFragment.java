package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentCompat.OnRequestPermissionsResultCallback;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;

@EFragment
public abstract class BaseFragment extends LifecycleLoggingFragment implements OnRequestPermissionsResultCallback {
    private static final String f18491e = BaseFragment.class.getName();
    protected QuickReturnListViewMenuSyncer f18492a;
    protected ActionBarHighlightMode f18493b = ActionBarHighlightMode.NEVER;
    protected boolean f18494c;
    protected int f18495d = 0;
    private RunTimePermissionsRequester f18496f;
    private Handler f18497g = new Handler(Looper.getMainLooper());
    private Observer f18498h = new C37803(this);

    class C37771 implements Runnable {
        final /* synthetic */ BaseFragment f18473a;

        C37771(BaseFragment baseFragment) {
            this.f18473a = baseFragment;
        }

        public void run() {
            this.f18473a.m19864o();
        }
    }

    class C37803 implements Observer {
        final /* synthetic */ BaseFragment f18478a;

        C37803(BaseFragment baseFragment) {
            this.f18478a = baseFragment;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof Runnable) {
                final Runnable runnable = (Runnable) obj;
                this.f18478a.m19838a(new Runnable(this) {
                    final /* synthetic */ C37803 f18477b;

                    public void run() {
                        if (this.f18477b.f18478a.isAdded()) {
                            runnable.run();
                        }
                    }
                });
            }
        }
    }

    public enum ActionBarHighlightMode {
        NEVER,
        ALWAYS,
        AFTER_SCROLL,
        WHITE,
        IGNORE
    }

    public abstract String mo6383s();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReferenceMonitor.a().a(this);
    }

    public void onStart() {
        super.onStart();
        if ((getActivity() instanceof MediaPlayingActivity) && ((MediaPlayingActivity) getActivity()).B() != null) {
            ((MediaPlayingActivity) getActivity()).B().m23230a((Object) this, this.f18494c);
        }
        NotificationCenter.m19011a().m19014a(getClass().getName(), this.f18498h);
    }

    public void onResume() {
        super.onResume();
        m19864o();
        mo6420v();
    }

    public void onStop() {
        super.onStop();
        if (!(this instanceof MediaPlayingFragment)) {
            MiscUtils.m25891a(getActivity(), true);
        }
        NotificationCenter.m19011a().m19016b(getClass().getName(), this.f18498h);
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.f18492a != null) {
            this.f18492a.m25992b();
            this.f18492a = null;
        }
        mo6470a(getView());
    }

    private void mo6470a(View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int i = 0;
            while (i < viewGroup.getChildCount()) {
                int i2;
                View childAt = viewGroup.getChildAt(i);
                if (childAt instanceof EditText) {
                    EditText editText = (EditText) childAt;
                    editText.setOnClickListener(null);
                    editText.setOnLongClickListener(null);
                    editText.setOnEditorActionListener(null);
                    editText.setOnFocusChangeListener(null);
                    editText.setOnTouchListener(null);
                    viewGroup.removeView(childAt);
                    i2 = i;
                } else {
                    mo6470a(childAt);
                    i2 = i + 1;
                }
                i = i2;
            }
        }
    }

    public void m19842a(boolean z) {
        this.f18494c = z;
        if ((getActivity() instanceof MediaPlayingActivity) && ((MediaPlayingActivity) getActivity()).B() != null) {
            ((MediaPlayingActivity) getActivity()).B().m23231a(z);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        m19839a(new C37771(this), 1);
    }

    public Menu m19845b() {
        if (!(getActivity() instanceof MediaPlayingActivity) || ((MediaPlayingActivity) getActivity()).B() == null) {
            return null;
        }
        return ((MediaPlayingActivity) getActivity()).B().getCustomMenu();
    }

    public boolean mo6400c() {
        return false;
    }

    public boolean mo6544a(int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean mo6444d() {
        return true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f18495d++;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @SupposeUiThread
    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable final ResultCallback resultCallback) {
        if (this.f18496f != null) {
            throw new IllegalStateException("A permission request is already in progress");
        }
        this.f18496f = new RunTimePermissionsRequester(runTimePermissionsRequest, new ResultCallback(this) {
            final /* synthetic */ BaseFragment f18475b;

            public void mo6372a(boolean z, @NonNull Set<String> set) {
                this.f18475b.f18496f = null;
                if (resultCallback != null) {
                    resultCallback.mo6372a(z, set);
                }
            }
        });
        this.f18496f.m18739a((Fragment) this);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (this.f18496f != null) {
            this.f18496f.m18740a((Fragment) this, i, strArr, iArr);
        }
    }

    public boolean m19843a(int i) {
        if (!isAdded()) {
            return false;
        }
        if (i == this.f18495d) {
            return true;
        }
        Log.b(f18491e, "Fragment load count was not equal. Possibly a network call returned from a previous view");
        return false;
    }

    protected void mo6486a(PerformanceV2 performanceV2, boolean z, boolean z2) {
        Log.b(mo6383s(), "showNowPlayingBarForPerformance called with tag: " + mo6383s());
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(performanceV2, z, z2);
        }
    }

    public void mo6487a(BaseFragment baseFragment) {
        String s = baseFragment.mo6383s() != null ? baseFragment.mo6383s() : baseFragment.getClass().getName();
        Log.b(mo6383s(), "showFragment called with tag: " + s);
        mo6929a(baseFragment, s);
    }

    public void mo6929a(BaseFragment baseFragment, String str) {
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(baseFragment, str);
        }
    }

    public void mo6443a(SongbookEntry songbookEntry) {
        Log.b(mo6383s(), "playPreview - called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(songbookEntry, false, null);
        }
    }

    public void m19829a(SongbookEntry songbookEntry, SearchTarget searchTarget) {
        Log.b(mo6383s(), "playPreview - called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(songbookEntry, false, searchTarget);
        }
    }

    protected void m19847b(BaseFragment baseFragment) {
        Log.b(mo6383s(), "popFragment called with tag: " + mo6383s());
        BaseFragmentResponder m = m19862m();
        Log.b(mo6383s(), "popFragment -- baseFramentResponder == null? " + (m == null));
        if (m != null) {
            m.a(baseFragment);
        }
    }

    protected void m19840a(String str) {
        Log.b(mo6383s(), "popNowPlayingFragment called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(str);
        }
    }

    protected void mo6830a(NowPlayingFragment nowPlayingFragment) {
        Log.b(mo6383s(), "popNowPlayingFragment called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a(nowPlayingFragment);
        }
    }

    protected void m19824a(Intent intent) {
        Log.b(mo6383s(), "popFragmentAndPassIntentToNowPlayingFragment called");
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.a_(intent);
        }
    }

    protected V3BillingHelper m19854e() {
        BaseFragmentResponder m = m19862m();
        return m != null ? m.b() : null;
    }

    protected void m19855f() {
        BaseFragmentResponder m = m19862m();
        if (m != null) {
            m.m_();
        }
    }

    public void m19846b(int i) {
        m19823a(i, Toaster$Duration.LONG);
    }

    public void m19823a(int i, Toaster$Duration toaster$Duration) {
        if (isAdded()) {
            m19841a(getResources().getString(i), toaster$Duration);
        } else {
            Log.e(f18491e, "showToast - getActivity() is null; aborting showing Toast");
        }
    }

    public void m19849b(String str) {
        m19841a(str, Toaster$Duration.LONG);
    }

    protected void m19841a(String str, Toaster$Duration toaster$Duration) {
        if (isAdded()) {
            Toaster.a(getActivity(), str, toaster$Duration);
        } else {
            Log.e(f18491e, "showToast - getActivity() is null; aborting showing Toast");
        }
    }

    public boolean mo6445g() {
        return true;
    }

    public boolean mo6511h() {
        return true;
    }

    protected void m19850c(int i) {
        mo6861a(getResources().getString(i));
    }

    protected void mo6861a(CharSequence charSequence) {
        m19836a(charSequence, null);
    }

    protected void m19836a(CharSequence charSequence, CharSequence charSequence2) {
        m19837a(charSequence, charSequence2, 0);
    }

    protected void m19837a(CharSequence charSequence, CharSequence charSequence2, int i) {
        if (getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) getActivity()).a(charSequence, charSequence2, i);
            return;
        }
        ActionBar k = m19860k();
        if (k != null) {
            k.setTitle(charSequence);
        }
    }

    protected void m19830a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        if (getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) getActivity()).a(songbookEntry, performanceV2);
        }
    }

    public boolean mo6446i() {
        return true;
    }

    protected boolean mo6447j() {
        return false;
    }

    protected void m19838a(Runnable runnable) {
        this.f18497g.post(runnable);
    }

    protected void m19848b(Runnable runnable) {
        NotificationCenter.m19011a().m19012a(getClass().getName(), (Object) runnable);
    }

    protected void m19839a(final Runnable runnable, long j) {
        this.f18497g.postDelayed(new Runnable(this) {
            final /* synthetic */ BaseFragment f18480b;

            public void run() {
                this.f18480b.m19848b(runnable);
            }
        }, j);
    }

    protected void m19851c(Runnable runnable) {
        this.f18497g.removeCallbacks(runnable);
    }

    protected ActionBar m19860k() {
        Activity activity = getActivity();
        if (activity instanceof AppCompatActivity) {
            return ((AppCompatActivity) activity).getSupportActionBar();
        }
        return null;
    }

    protected ProgressBar m19861l() {
        Activity activity = getActivity();
        if (activity instanceof PreSingActivity) {
            return ((PreSingActivity) activity).m24786q();
        }
        return null;
    }

    protected BaseFragmentResponder m19862m() {
        if (getActivity() instanceof BaseFragmentResponder) {
            return (BaseFragmentResponder) getActivity();
        }
        return null;
    }

    protected void mo6485a(AbsListView absListView, com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        mo6488n();
        this.f18492a = m19862m().a(absListView, actionBarHighlightMode, onScrollListener);
        m19831a(ActionBarHighlightMode.AFTER_SCROLL);
    }

    protected void mo6488n() {
        if (this.f18492a != null) {
            this.f18492a.m25992b();
        }
    }

    protected void m19831a(ActionBarHighlightMode actionBarHighlightMode) {
        this.f18493b = actionBarHighlightMode;
    }

    protected void m19864o() {
        if (isAdded() && this.f18493b != ActionBarHighlightMode.IGNORE) {
            if (getActivity() instanceof MediaPlayingActivity) {
                MasterToolbar B = ((MediaPlayingActivity) getActivity()).B();
                if (B != null) {
                    B.setWhiteMode(this.f18493b == ActionBarHighlightMode.WHITE);
                    B.m23226a();
                    if (mo6450x()) {
                        B.getToolbarNavigationIconView().setVisibility(0);
                        ((MarginLayoutParams) B.getTitleView().getLayoutParams()).leftMargin = 0;
                    } else {
                        B.getToolbarNavigationIconView().setVisibility(8);
                        ((MarginLayoutParams) B.getTitleView().getLayoutParams()).leftMargin = (int) getResources().getDimension(C1947R.dimen.margin_larger);
                    }
                }
            }
            if (getActivity() instanceof MasterActivity) {
                BaseFragment F = ((MasterActivity) getActivity()).F();
                if ((F instanceof ProfileFragment) && !((ProfileFragment) F).m21071H()) {
                    ((MasterActivity) getActivity()).B().getToolbarView().setDisplayUpButton(true);
                }
            }
            if (this.f18492a != null) {
                this.f18492a.m25993c();
            } else if ((getActivity() instanceof MediaPlayingActivity) && ((MediaPlayingActivity) getActivity()).B() != null) {
                if (this.f18493b == ActionBarHighlightMode.ALWAYS) {
                    ((MediaPlayingActivity) getActivity()).B().m23227a(255, true);
                } else if (this.f18493b == ActionBarHighlightMode.NEVER || this.f18493b == ActionBarHighlightMode.WHITE) {
                    ((MediaPlayingActivity) getActivity()).B().m23227a(0, true);
                }
            }
        }
    }

    public void mo6933p() {
        if (getActivity() instanceof MediaPlayingActivity) {
            LayoutParams layoutParams = (LayoutParams) ((MediaPlayingActivity) getActivity()).B().getLayoutParams();
            layoutParams.height = 0;
            ((MediaPlayingActivity) getActivity()).B().setLayoutParams(layoutParams);
        }
    }

    public void m19866q() {
        if (getActivity() instanceof MediaPlayingActivity) {
            LayoutParams layoutParams = (LayoutParams) ((MediaPlayingActivity) getActivity()).B().getLayoutParams();
            layoutParams.height = getResources().getDimensionPixelSize(C1947R.dimen.app_bar_height);
            ((MediaPlayingActivity) getActivity()).B().setLayoutParams(layoutParams);
        }
    }

    public void mo6895r() {
    }

    public void mo6930t() {
    }

    public void mo6449u() {
        mo6420v();
    }

    protected void mo6420v() {
    }

    public void mo6904w() {
    }

    protected boolean mo6450x() {
        return true;
    }

    public void m19874y() {
        if (getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) getActivity()).ah();
        }
    }
}
