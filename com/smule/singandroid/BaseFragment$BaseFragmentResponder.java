package com.smule.singandroid;

import android.content.Intent;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.facebook.CallbackManager;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.fragments.NowPlayingFragment;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;

public interface BaseFragment$BaseFragmentResponder {
    QuickReturnListViewMenuSyncer mo4074a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener);

    void mo4075a();

    void mo4076a(PerformanceV2 performanceV2, boolean z, boolean z2);

    void mo4077a(SongbookEntry songbookEntry, boolean z, SearchTarget searchTarget);

    void mo4078a(MenuToggleAction menuToggleAction);

    void mo4079a(BaseFragment baseFragment);

    void mo4080a(BaseFragment baseFragment, String str);

    void mo4081a(NowPlayingFragment nowPlayingFragment);

    void mo4082a(String str);

    void a_(Intent intent);

    V3BillingHelper mo4084b();

    void m_();

    CallbackManager n_();
}
