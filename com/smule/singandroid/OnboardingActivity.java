package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.fragments.OnboardingSongsFragment;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity
public class OnboardingActivity extends MediaPlayingActivity implements BaseFragmentResponder {
    public static boolean f18978h = false;
    private static final String f18979i = OnboardingActivity.class.getName();
    @ViewById
    protected RelativeLayout f18980g;
    private PreviewFragment f18981j;

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    protected void m20475d() {
        String stringExtra;
        super.d();
        if (getIntent().hasExtra("ONBOARDING_TOPICS")) {
            stringExtra = getIntent().getStringExtra("ONBOARDING_TOPICS");
        } else {
            stringExtra = RegistrationContext.g();
        }
        if (stringExtra != null) {
            ArrayList arrayList = new ArrayList();
            StringTokenizer stringTokenizer = new StringTokenizer(stringExtra, ",");
            while (stringTokenizer.hasMoreTokens()) {
                arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
            }
            m20472a(true, arrayList);
            return;
        }
        m20476q();
    }

    public void m20476q() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.add(C1947R.id.onboarding_content_view, new OnboardingTopicsFragment_());
        beginTransaction.commit();
    }

    public void m20472a(boolean z, ArrayList<Integer> arrayList) {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment b = OnboardingSongsFragment.m24011b(z);
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("topicIDs", arrayList);
        b.setArguments(bundle);
        beginTransaction.replace(C1947R.id.onboarding_content_view, b);
        beginTransaction.commit();
    }

    public void onResume() {
        super.onResume();
        if (isFinishing()) {
            Log.c(f18979i, "Exiting onResume() as the activity is about to be closed");
        }
        f18978h = true;
    }

    public void onPause() {
        super.onPause();
        f18978h = false;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onStart() {
        super.onStart();
        SingAnalytics.m26152p();
        MediaPlayerServiceController.m24641a().m24649a((Activity) this);
    }

    public void onStop() {
        super.onStop();
        MediaPlayerServiceController.m24641a().m24661c();
        MediaPlayerServiceController.m24641a().m24658b((Activity) this);
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void m20474c(BaseFragment baseFragment) {
    }

    public void m20469a(BaseFragment baseFragment, String str) {
    }

    public void m20468a(BaseFragment baseFragment) {
    }

    public void m20471a(String str) {
    }

    public void m20466a(SongbookEntry songbookEntry, boolean z, SearchTarget searchTarget) {
        Log.b(f18979i, "playPreview called");
        if (!z) {
            m20461W();
        }
        this.f18981j = PreviewFragment.m20942a(songbookEntry, z, false, searchTarget);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.setCustomAnimations(C1947R.animator.slide_up, C1947R.animator.slide_down, C1947R.animator.slide_up, C1947R.animator.slide_down);
        if (this.f18981j.isAdded()) {
            beginTransaction.show(this.f18981j);
        } else {
            beginTransaction.add(C1947R.id.now_playing_bar_layout, this.f18981j, "PREVIEW_FRAGMENT");
        }
        beginTransaction.commitAllowingStateLoss();
        m20460I();
    }

    protected void m20461W() {
        m20462Y();
        getFragmentManager().executePendingTransactions();
        m20460I();
    }

    public void m20462Y() {
        PreviewFragment previewFragment = (PreviewFragment) getFragmentManager().findFragmentByTag("PREVIEW_FRAGMENT");
        if (previewFragment != null) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            beginTransaction.remove(previewFragment);
            beginTransaction.commitAllowingStateLoss();
            if (MediaPlayerServiceController.m24641a().m24662c(previewFragment.m20944K())) {
                MediaPlayerServiceController.m24641a().m24665e();
            }
        }
        this.f18981j = null;
    }

    public void m20460I() {
        if (this.f18980g == null) {
            Log.d(f18979i, "refreshBottomMarginToFragmentContentView - called, but mFragmentContentView is null. Aborting!");
            return;
        }
        int dimension;
        if (this.f18981j != null) {
            dimension = (int) getResources().getDimension(C1947R.dimen.row_single_height);
        } else {
            dimension = 0;
        }
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f18980g.getLayoutParams();
        if (marginLayoutParams.bottomMargin != dimension) {
            marginLayoutParams.setMargins(0, 0, 0, dimension);
            this.f18980g.setLayoutParams(marginLayoutParams);
        }
    }

    public void m20465a(PerformanceV2 performanceV2, boolean z, boolean z2) {
    }

    public void m20470a(NowPlayingFragment nowPlayingFragment) {
    }

    public void a_(Intent intent) {
    }

    public void m20464a() {
    }

    public V3BillingHelper m20473b() {
        return null;
    }

    public void m_() {
    }

    public QuickReturnListViewMenuSyncer m20463a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        return null;
    }

    public void m20467a(BaseFragment$BaseFragmentResponder$MenuToggleAction baseFragment$BaseFragmentResponder$MenuToggleAction) {
    }

    public CallbackManager n_() {
        return null;
    }

    public void onBackPressed() {
        Log.b(f18979i, "onBackPressed - begin");
        if (!SingApplication.g.booleanValue() || new Random().nextInt(Integer.MAX_VALUE) % 2 == 0) {
            Log.b(f18979i, "onBackPressed - backStackEntryCount is: " + getFragmentManager().getBackStackEntryCount());
            BaseFragment baseFragment = (BaseFragment) getFragmentManager().findFragmentById(C1947R.id.onboarding_content_view);
            if (baseFragment != null) {
                baseFragment.mo6400c();
                return;
            }
            Log.b(f18979i, "onBackPressed - nothing else happened so calling our super");
            super.onBackPressed();
        }
    }
}
