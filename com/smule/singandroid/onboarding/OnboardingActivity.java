/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.RelativeLayout
 *  com.facebook.CallbackManager
 *  com.facebook.appevents.AppEventsLogger
 *  com.smule.singandroid.utils.DeepLink
 *  com.smule.singandroid.utils.DeepLink$Hosts
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer$ActionBarHighlightMode
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.AbsListView;
import android.widget.RelativeLayout;
import com.facebook.CallbackManager;
import com.facebook.appevents.AppEventsLogger;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.TrialSubscriptionActivity;
import com.smule.singandroid.TrialSubscriptionActivity_;
import com.smule.singandroid.customviews.BottomNavView;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.mediaplaying.PreviewFragment;
import com.smule.singandroid.onboarding.OnboardingAutoCompleteSearchFragment;
import com.smule.singandroid.onboarding.OnboardingAutoCompleteSearchFragment_;
import com.smule.singandroid.onboarding.OnboardingSongsFragment;
import com.smule.singandroid.onboarding.OnboardingSongsSearchFragment;
import com.smule.singandroid.onboarding.OnboardingSongsSearchFragment_;
import com.smule.singandroid.onboarding.OnboardingTopicsFragment;
import com.smule.singandroid.onboarding.OnboardingTopicsFragment_;
import com.smule.singandroid.preference.PreferenceKeys;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.utils.DeepLink;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class OnboardingActivity
extends MediaPlayingActivity
implements BaseFragment {
    private static final String h = OnboardingActivity.class.getName();
    @ViewById
    protected RelativeLayout g;
    private SingServerValues i = new SingServerValues();
    private PreviewFragment j;
    private Boolean k = false;

    @Override
    public void J() {
    }

    @Override
    public QuickReturnListViewMenuSyncer a(AbsListView absListView, QuickReturnListViewMenuSyncer.ActionBarHighlightMode actionBarHighlightMode, AbsListView.OnScrollListener onScrollListener) {
        return null;
    }

    public void a() {
        this.a(new OnboardingTopicsFragment_(), OnboardingTopicsFragment.g);
    }

    @Override
    public void a(PerformanceV2 performanceV2, boolean bl, boolean bl2) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(SongbookEntry songbookEntry) {
        boolean bl = songbookEntry != null;
        Intent intent = !bl && TrialSubscriptionActivity.a((Context)this) ? new Intent((Context)this, TrialSubscriptionActivity_.class) : new Intent((Context)this, MasterActivity_.class);
        if (bl) {
            intent.setData(DeepLink.a((DeepLink.Hosts)DeepLink.Hosts.ao, (String)songbookEntry.d()));
            intent.putExtra("ONBOARDING_SONGBOOK_ENTRY", (Parcelable)songbookEntry);
        }
        this.k = true;
        this.startActivity(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(SongbookEntry songbookEntry, boolean bl, Analytics searchTarget) {
        Log.b(h, "playPreview called");
        if (!bl) {
            this.af();
        }
        this.j = PreviewFragment.a(songbookEntry, bl, false, searchTarget);
        songbookEntry = this.getFragmentManager().beginTransaction();
        songbookEntry.setCustomAnimations(2131034128, 2131034117, 2131034128, 2131034117);
        if (this.j.isAdded()) {
            songbookEntry.show((Fragment)this.j);
        } else {
            songbookEntry.add(2131755945, (Fragment)this.j, "PREVIEW_FRAGMENT");
        }
        songbookEntry.commitAllowingStateLoss();
        this.J();
    }

    @Override
    public void a(BaseFragment menuToggleAction) {
    }

    @Override
    public void a(NowPlayingFragment nowPlayingFragment) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(String string2) {
        if (string2.equals(OnboardingSongsSearchFragment.g)) {
            MediaPlayerServiceController.a().e();
            super.a(OnboardingAutoCompleteSearchFragment.g);
            return;
        } else {
            if (!string2.equals(OnboardingAutoCompleteSearchFragment.g)) return;
            {
                super.a(string2);
                return;
            }
        }
    }

    public void a(String string2, String string3) {
        OnboardingSongsSearchFragment_ onboardingSongsSearchFragment_ = new OnboardingSongsSearchFragment_();
        Bundle bundle = new Bundle();
        bundle.putString("BUNDLE_SEARCH_INPUT", string2);
        bundle.putString("BUNDLE_SEARCH_QUERY", string3);
        onboardingSongsSearchFragment_.setArguments(bundle);
        this.a((BaseFragment)((Object)onboardingSongsSearchFragment_), OnboardingSongsSearchFragment.g);
    }

    public void a(boolean bl, ArrayList<Integer> arrayList) {
        OnboardingSongsFragment onboardingSongsFragment = OnboardingSongsFragment.c(bl);
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("BUNDLE_TOPIC_IDS", arrayList);
        bundle.putBoolean("BUNDLE_SEARCH_ENABLED", this.i.ae());
        onboardingSongsFragment.setArguments(bundle);
        this.a(onboardingSongsFragment, OnboardingSongsFragment.g);
    }

    @Override
    public void a_(Intent intent) {
    }

    @Override
    protected void af() {
        this.ah();
        this.getFragmentManager().executePendingTransactions();
        this.J();
    }

    @Override
    public void ah() {
        PreviewFragment previewFragment = (PreviewFragment)this.getFragmentManager().findFragmentByTag("PREVIEW_FRAGMENT");
        if (previewFragment != null) {
            FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
            fragmentTransaction.remove((Fragment)previewFragment);
            fragmentTransaction.commitAllowingStateLoss();
            if (MediaPlayerServiceController.a().c(previewFragment.S())) {
                MediaPlayerServiceController.a().e();
            }
        }
        this.j = null;
    }

    public void b() {
        MediaPlayerServiceController.a().e();
        this.a((BaseFragment)((Object)new OnboardingAutoCompleteSearchFragment_()), OnboardingAutoCompleteSearchFragment.g);
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @Override
    protected void f() {
        super.f();
        Object object = this.getIntent().hasExtra("ONBOARDING_TOPICS") ? this.getIntent().getStringExtra("ONBOARDING_TOPICS") : RegistrationContext.o();
        if (object != null) {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            object = new StringTokenizer((String)object, ",");
            do {
                if (!object.hasMoreTokens()) {
                    this.a(true, arrayList);
                    return;
                }
                arrayList.add(Integer.parseInt(object.nextToken()));
            } while (true);
        }
        if (this.i.af()) {
            this.a();
            return;
        }
        this.a(false, new ArrayList<Integer>());
    }

    @Override
    public CallbackManager h() {
        return null;
    }

    @Override
    public void m_() {
    }

    @Override
    public V3BillingHelper n_() {
        return null;
    }

    @Override
    public void o_() {
    }

    @Override
    public void onBackPressed() {
        Log.b(h, "onBackPressed - begin");
        if (SingApplication.h.booleanValue() && new Random().nextInt(Integer.MAX_VALUE) % 2 != 0) {
            return;
        }
        Log.b(h, "onBackPressed - backStackEntryCount is: " + this.getFragmentManager().getBackStackEntryCount());
        BaseFragment baseFragment = this.Z();
        if (baseFragment != null) {
            baseFragment.d();
            return;
        }
        Log.b(h, "onBackPressed - nothing else happened so calling our super");
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isFinishing()) {
            Log.c(h, "Exiting onResume() as the activity is about to be closed");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        SingAnalytics.a((BottomNavView.Tab)null);
        MediaPlayerServiceController.a().a((Activity)this);
    }

    @Override
    public void onStop() {
        super.onStop();
        MediaPlayerServiceController.a().c();
        MediaPlayerServiceController.a().b((Activity)this);
        if (this.k.booleanValue()) {
            this.finish();
        }
    }

    public void t() {
        AppEventsLogger.newLogger((Context)this).logEvent("fb_mobile_tutorial_completion");
        this.getSharedPreferences("sing_prefs", 0).edit().putInt("ONBOARD_STATUS_KEY", PreferenceKeys.OnboardStatus.c.ordinal()).apply();
    }
}

