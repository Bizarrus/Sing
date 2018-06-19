package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI$EnsembleType;
import com.smule.android.network.api.PerformancesAPI$UploadType;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager.PreuploadResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.PerformanceV2$CompositionType;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.CompletedFuture;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

@EActivity
public class PerformanceSaveActivity extends BaseActivity implements BaseFragmentResponder {
    private static final String f19126i = PerformanceSaveActivity.class.getName();
    @InstanceState
    protected PostSingBundle f19127g;
    protected SingBundle f19128h;
    private Future<PreuploadResponse> f19129j;
    private Bitmap f19130k;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Log.b(f19126i, "onCreate - no saved instance state");
            this.f19127g = PostSingBundle.m20870a(getIntent());
        } else {
            Log.b(f19126i, "onCreate - restoring from saved instance state");
        }
        this.f19128h = this.f19127g.f19316b;
        FollowManager.m18204a().m18214a(null);
        FragmentManager fragmentManager = getFragmentManager();
        Bundle bundle2 = (Bundle) getIntent().getParcelableExtra("OGG_FILE_METADATA_BUNDLE_EXTRA_KEY");
        FragmentTransaction beginTransaction;
        if (this.f19128h.f20146k) {
            this.f19130k = ImageToDiskUtils.m25830a((Context) this, "duetjoinerthumb");
            if (((DuetJoinSaveFragment) fragmentManager.findFragmentByTag(DuetJoinSaveFragment.f18548e)) == null) {
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(C1947R.id.performance_save_root_view, DuetJoinSaveFragment.m19926a(this.f19127g, bundle2), DuetJoinSaveFragment.f18548e);
                beginTransaction.commit();
            }
        } else if (this.f19128h.f20149n) {
            if (((OnboardingUploadFragment) fragmentManager.findFragmentByTag(OnboardingUploadFragment.f19030f)) == null) {
                beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(C1947R.id.performance_save_root_view, OnboardingUploadFragment.m20545a(this.f19127g, bundle2), OnboardingUploadFragment.f19029e);
                beginTransaction.commit();
            }
        } else if (((PerformanceSaveFragment) fragmentManager.findFragmentByTag(PerformanceSaveFragment.f19187e)) == null) {
            beginTransaction = fragmentManager.beginTransaction();
            beginTransaction.add(C1947R.id.performance_save_root_view, PerformanceSaveFragmentFactory.m20780a(this.f19127g, bundle2), PerformanceSaveFragment.f19187e);
            beginTransaction.commit();
        }
    }

    protected void onStart() {
        super.onStart();
        m20653t();
    }

    private void m20653t() {
        if (this.f19127g.f19323i == null) {
            PerformancesAPI$UploadType performancesAPI$UploadType;
            String str;
            String c;
            PerformancesAPI$EnsembleType performancesAPI$EnsembleType;
            if (this.f19128h.f20146k) {
                performancesAPI$UploadType = PerformancesAPI$UploadType.JOIN;
                str = this.f19127g.f19316b.f20145j;
            } else {
                performancesAPI$UploadType = PerformancesAPI$UploadType.CREATE;
                str = null;
            }
            SongbookEntry songbookEntry = this.f19128h.f20139d;
            String d = songbookEntry.mo6290d();
            if (songbookEntry.m18772r()) {
                c = songbookEntry.mo6289c();
            } else {
                c = null;
            }
            PerformanceV2$CompositionType performanceV2$CompositionType = songbookEntry.m18772r() ? PerformanceV2$CompositionType.ARR : PerformanceV2$CompositionType.f17476a;
            switch (this.f19128h.f20137b) {
                case DUET:
                    performancesAPI$EnsembleType = PerformancesAPI$EnsembleType.DUET;
                    break;
                case GROUP:
                    performancesAPI$EnsembleType = PerformancesAPI$EnsembleType.GROUP;
                    break;
                default:
                    performancesAPI$EnsembleType = PerformancesAPI$EnsembleType.SOLO;
                    break;
            }
            m20652a(PerformanceManager.a().a(performancesAPI$UploadType, performancesAPI$EnsembleType, performanceV2$CompositionType, d, c, str, m20654u()));
            return;
        }
        PreuploadResponse preuploadResponse = new PreuploadResponse();
        preuploadResponse.a = NetworkResponse.a();
        preuploadResponse.mResources = this.f19127g.f19323i;
        m20652a(new CompletedFuture(preuploadResponse));
    }

    private void m20652a(Future<PreuploadResponse> future) {
        this.f19129j = future;
        Fragment findFragmentById = getFragmentManager().findFragmentById(C1947R.id.performance_save_root_view);
        if (findFragmentById instanceof DuetJoinSaveFragment) {
            ((DuetJoinSaveFragment) findFragmentById).m19983a((Future) future);
        } else if (findFragmentById instanceof OnboardingUploadFragment) {
            ((OnboardingUploadFragment) findFragmentById).m20580a((Future) future);
        } else if (findFragmentById instanceof PerformanceSaveFragment) {
            ((PerformanceSaveFragment) findFragmentById).m20770a((Future) future);
        }
    }

    public void m20666q() {
        if (this.f19129j != null) {
            this.f19129j.cancel(true);
            this.f19129j = null;
        }
        m20653t();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        if (this.f19129j != null && this.f19127g.f19323i == null) {
            try {
                PreuploadResponse preuploadResponse = (PreuploadResponse) this.f19129j.get(0, TimeUnit.NANOSECONDS);
                if (preuploadResponse != null) {
                    this.f19127g.f19323i = preuploadResponse.mResources;
                }
            } catch (Exception e) {
            }
        }
        super.onSaveInstanceState(bundle);
    }

    private boolean m20654u() {
        Bundle bundle = (Bundle) getIntent().getParcelableExtra("OGG_FILE_METADATA_BUNDLE_EXTRA_KEY");
        return (bundle == null || TextUtils.isEmpty(bundle.getString("VIDEO_FILE"))) ? false : true;
    }

    public BaseFragment m20667r() {
        return (BaseFragment) getFragmentManager().findFragmentById(C1947R.id.performance_save_root_view);
    }

    public Bitmap m20668s() {
        return this.f19130k;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            BaseFragment r = m20667r();
            if (r instanceof DuetJoinSaveFragment) {
                ((DuetJoinSaveFragment) r).m19981a();
            } else if (r instanceof OnboardingUploadFragment) {
                ((OnboardingUploadFragment) r).m20583z();
            }
        }
    }

    public void m20662a(PostSingBundle postSingBundle, PerformanceV2 performanceV2, boolean z) {
        if (z) {
            PostSingFlowActivity.m20877a((Activity) this, postSingBundle, performanceV2);
        } else {
            PostSingFlowActivity.m20876a((Activity) this, postSingBundle);
        }
        finish();
    }

    public void onBackPressed() {
        BaseFragment baseFragment = (BaseFragment) getFragmentManager().findFragmentByTag(this.f19128h.f20146k ? DuetJoinSaveFragment.f18548e : PerformanceSaveFragment.f19187e);
        if (baseFragment == null || !baseFragment.mo6400c()) {
            super.onBackPressed();
        }
    }

    public void m20660a(BaseFragment baseFragment) {
        Log.b(f19126i, "popFragment - called with fragment tag: " + baseFragment.getTag());
        finish();
    }

    public void m20661a(BaseFragment baseFragment, String str) {
    }

    public void m20664a(String str) {
    }

    public void m20658a(SongbookEntry songbookEntry, boolean z, SearchTarget searchTarget) {
    }

    public void m20657a(PerformanceV2 performanceV2, boolean z, boolean z2) {
    }

    public void m20663a(NowPlayingFragment nowPlayingFragment) {
    }

    public void a_(Intent intent) {
    }

    public void m20656a() {
    }

    public V3BillingHelper m20665b() {
        return null;
    }

    public void m_() {
    }

    public QuickReturnListViewMenuSyncer m20655a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        return null;
    }

    public void m20659a(BaseFragment$BaseFragmentResponder$MenuToggleAction baseFragment$BaseFragmentResponder$MenuToggleAction) {
    }

    public CallbackManager n_() {
        return null;
    }
}
