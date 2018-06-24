package com.smule.singandroid.songbook_search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.MediaPlayingActivity$OnPopNowPlayingFragmentListener;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.fragments.SearchByTagFragment;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem;
import com.smule.singandroid.list_items.SearchMediaExpandableListItem$ItemType;
import com.smule.singandroid.list_items.SearchMediaExpandableListViewItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.songbook_search.SearchFragment.AnalyticsResultTriplet;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SearchBaseFragment extends BaseFragment {
    public static final String f22660p = SearchBaseFragment.class.getName();
    private boolean f22661e;
    protected ConcurrentHashMap<String, Boolean> f22662q = new ConcurrentHashMap();
    protected SearchMediaExpandableListViewItem f22663r;
    protected int f22664s = -1;

    class C48581 implements MediaPlayingActivity$OnPopNowPlayingFragmentListener {
        final /* synthetic */ SearchBaseFragment f23958a;

        C48581(SearchBaseFragment searchBaseFragment) {
            this.f23958a = searchBaseFragment;
        }

        public void mo6923a(String str) {
            if (!this.f23958a.f22661e) {
                if (this.f23958a.f22663r == null || !str.equals(this.f23958a.f22663r.getMediaKey())) {
                    this.f23958a.f22663r = null;
                    this.f23958a.f22664s = -1;
                } else if (this.f23958a.mo6470a(this.f23958a.f22663r)) {
                    final AnimatorSet collapseAnimatorSet = this.f23958a.f22663r.getCollapseAnimatorSet();
                    collapseAnimatorSet.addListener(new AnimatorListenerAdapter(this) {
                        final /* synthetic */ C48581 f23957b;

                        public void onAnimationStart(Animator animator) {
                            super.onAnimationStart(animator);
                            this.f23957b.f23958a.f22663r.m24473g();
                            this.f23957b.f23958a.f22663r.setIsExpanded(false);
                        }

                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            if (this.f23957b.f23958a.f22663r != null) {
                                this.f23957b.f23958a.f22663r.m24469c();
                                this.f23957b.f23958a.f22663r.m24471e();
                                this.f23957b.f23958a.f22663r = null;
                            }
                            collapseAnimatorSet.removeAllListeners();
                            this.f23957b.f23958a.f22664s = -1;
                        }
                    });
                    if (!collapseAnimatorSet.isRunning()) {
                        this.f23958a.f22663r.setCollapsedWithAnimation(true);
                        collapseAnimatorSet.start();
                    }
                } else {
                    this.f23958a.f22663r.m24473g();
                    this.f23958a.f22663r.setIsExpanded(false);
                    this.f23958a.f22663r.setCollapsedWithAnimation(false);
                    this.f23958a.f22663r.m24469c();
                    this.f23958a.f22663r = null;
                    this.f23958a.f22664s = -1;
                }
            }
        }
    }

    protected int mo6932e(int i) {
        return i;
    }

    public String mo6383s() {
        return f22660p;
    }

    protected boolean mo6470a(View view) {
        return false;
    }

    protected void m24127D() {
        ((MediaPlayingActivity) getActivity()).a(new C48581(this));
    }

    protected void m24135a(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem, int i, MagicAdapter magicAdapter) {
        if (getActivity() != null && !searchMediaExpandableListViewItem.m23046t() && !this.f22661e) {
            AnalyticsResultTriplet analyticsResultTriplet;
            int i2;
            this.f22661e = true;
            SongbookEntry a = m24130a(searchMediaExpandableListViewItem.getPerformance());
            if (mo6383s().equals(SearchShowAllFragment.f24178e)) {
                analyticsResultTriplet = null;
            } else {
                analyticsResultTriplet = m24131a(i, searchMediaExpandableListViewItem.getItem().a() == SearchMediaExpandableListItem$ItemType.f23184a ? 2 : 3, magicAdapter);
            }
            if (mo6383s().equals(SearchFragment.f24075e)) {
                int i3;
                Analytics.m17854a(mo6931a(searchMediaExpandableListViewItem.getItem()), SearchResultClkContext.PREVIEW, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(a), searchMediaExpandableListViewItem.getPerformance().performanceKey, Integer.valueOf(analyticsResultTriplet.m25208e()), Long.valueOf(searchMediaExpandableListViewItem.getPerformance().accountIcon.accountId), SongbookEntryUtils.m26168c(a), searchMediaExpandableListViewItem.getPerformance().video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, analyticsResultTriplet.m25207d(), analyticsResultTriplet.m25209f());
                mo6928E();
                if (mo6932e(i) != mo6862z().getCount() - 1) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i2 = i3;
            } else if (mo6383s().equals(SearchByTagFragment.f22665e)) {
                Analytics.m17854a(mo6931a(searchMediaExpandableListViewItem.getItem()), SearchResultClkContext.PREVIEW, SearchResultClkValue.MIXED, SongbookEntryUtils.m26167b(a), null, Integer.valueOf(analyticsResultTriplet.m25208e()), null, SongbookEntryUtils.m26168c(a), null, analyticsResultTriplet.m25207d(), analyticsResultTriplet.m25209f());
                i2 = 0;
            } else {
                Analytics.m17854a(mo6931a(searchMediaExpandableListViewItem.getItem()), SearchResultClkContext.PREVIEW, SearchResultClkValue.SEE_ALL, SongbookEntryUtils.m26167b(a), null, Integer.valueOf(i), null, SongbookEntryUtils.m26168c(a), null, magicAdapter.m18048d(), i);
                i2 = mo6862z().getHeaderViewsCount();
            }
            MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) getActivity();
            Object obj = (mediaPlayingActivity.aa() || mediaPlayingActivity.ab()) ? 1 : null;
            if (this.f22664s != i || obj == null || !MediaPlayerServiceController.m24641a().m24662c(searchMediaExpandableListViewItem.getPerformance().performanceKey)) {
                final View view = this.f22663r;
                mo6862z().smoothScrollToPositionFromTop(mo6932e(i) + i2, 0);
                this.f22664s = i;
                if (view != null && MediaPlayerServiceController.m24641a().m24662c(view.getMediaKey())) {
                    if (mo6470a(view)) {
                        final AnimatorSet collapseAnimatorSet = view.getCollapseAnimatorSet();
                        collapseAnimatorSet.addListener(new AnimatorListenerAdapter(this) {
                            final /* synthetic */ SearchBaseFragment f23961c;

                            public void onAnimationStart(Animator animator) {
                                super.onAnimationStart(animator);
                                view.m24473g();
                                view.setIsExpanded(false);
                            }

                            public void onAnimationEnd(Animator animator) {
                                super.onAnimationEnd(animator);
                                collapseAnimatorSet.removeAllListeners();
                                view.m24471e();
                                view.m24469c();
                            }
                        });
                        view.setCollapsedWithAnimation(true);
                        collapseAnimatorSet.start();
                    } else {
                        view.m24473g();
                        view.setIsExpanded(false);
                        view.setCollapsedWithAnimation(false);
                        view.m24469c();
                    }
                }
                final AnimatorSet expandAnimatorSet = searchMediaExpandableListViewItem.getExpandAnimatorSet();
                final SearchMediaExpandableListViewItem searchMediaExpandableListViewItem2 = searchMediaExpandableListViewItem;
                final int i4 = i;
                expandAnimatorSet.addListener(new AnimatorListenerAdapter(this) {
                    final /* synthetic */ SearchBaseFragment f23966e;

                    public void onAnimationStart(Animator animator) {
                        super.onAnimationStart(animator);
                        this.f23966e.m24134a(searchMediaExpandableListViewItem2);
                        if (MiscUtils.m25895a(searchMediaExpandableListViewItem2.getPerformance())) {
                            searchMediaExpandableListViewItem2.m24468b(searchMediaExpandableListViewItem2.m23046t());
                        }
                    }

                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        if (this.f23966e.isAdded()) {
                            this.f23966e.mo6862z().setSelection(this.f23966e.mo6932e(i4) + i2);
                            searchMediaExpandableListViewItem2.setIsExpanded(true);
                            this.f23966e.f22661e = false;
                            expandAnimatorSet.removeAllListeners();
                        }
                    }
                });
                if (!expandAnimatorSet.isRunning()) {
                    searchMediaExpandableListViewItem.m24470d();
                    expandAnimatorSet.start();
                    searchMediaExpandableListViewItem.setTag(Integer.valueOf(this.f22664s));
                    this.f22663r = searchMediaExpandableListViewItem;
                    m24127D();
                }
            } else if (searchMediaExpandableListViewItem.m23043q() || searchMediaExpandableListViewItem.m23044r()) {
                searchMediaExpandableListViewItem.mo6881u();
                this.f22661e = false;
            } else {
                this.f22661e = false;
            }
        }
    }

    protected boolean m24138a(SearchMediaExpandableListItem searchMediaExpandableListItem, int i) {
        if (searchMediaExpandableListItem.performanceIcon != null) {
            String str = searchMediaExpandableListItem.performanceIcon.performanceKey;
            if (str != null && this.f22664s == -1 && MediaPlayerServiceController.m24641a().m24662c(str)) {
                this.f22664s = i;
            }
        }
        return this.f22664s == i;
    }

    protected SearchTarget mo6931a(SearchMediaExpandableListItem searchMediaExpandableListItem) {
        return mo6383s().equals(SearchByTagFragment.f22665e) ? searchMediaExpandableListItem.b() ? SearchTarget.DIRECT_INVITE : SearchTarget.DIRECT_PERFORMANCE : searchMediaExpandableListItem.b() ? SearchTarget.INVITE : SearchTarget.PERFORMANCE;
    }

    protected void m24134a(SearchMediaExpandableListViewItem searchMediaExpandableListViewItem) {
        searchMediaExpandableListViewItem.m24464a((BaseFragment) this, mo6931a(searchMediaExpandableListViewItem.getItem()));
    }

    protected SongbookEntry m24130a(PerformanceV2 performanceV2) {
        if (!performanceV2.r()) {
            return SongbookEntry.m18747a(performanceV2);
        }
        ArrangementVersionLite arrangementVersionLite = new ArrangementVersionLite();
        arrangementVersionLite.key = performanceV2.arrKey;
        arrangementVersionLite.name = performanceV2.title;
        arrangementVersionLite.coverUrl = performanceV2.coverUrl;
        return SongbookEntry.m18744a(arrangementVersionLite);
    }

    protected AnalyticsResultTriplet m24131a(int i, int i2, MagicAdapter magicAdapter) {
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i3 < magicAdapter.m18048d()) {
            if (!(magicAdapter.m18027a(i3) instanceof Integer)) {
                i6++;
                if (i3 < i) {
                    i7++;
                    if (i5 != 0) {
                        i4++;
                    }
                }
            } else if (magicAdapter.m18027a(i3).equals(Integer.valueOf(i2))) {
                i5 = 1;
            }
            i3++;
        }
        return new AnalyticsResultTriplet(Integer.valueOf(i6), Integer.valueOf(i7), Integer.valueOf(i4));
    }

    protected void m24136a(boolean z, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
        this.f22662q.put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
        if (z) {
            MediaPlayerServiceController.m24641a().m24660b(arrangementVersionLiteEntry.mo6289c());
            m24133a(listingListItem);
            return;
        }
        mo6443a((SongbookEntry) arrangementVersionLiteEntry);
    }

    protected List<SearchMediaExpandableListItem> m24132a(List<PerformanceV2> list) {
        List arrayList = new ArrayList();
        for (PerformanceV2 a : list) {
            arrayList.add(SearchMediaExpandableListItem.a(a));
        }
        return arrayList;
    }

    protected void m24133a(ListingListItem listingListItem) {
        if (isAdded()) {
            listingListItem.setAlbumArtClickedState(false);
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.arrangement_copyright_violation_title), MessageFormat.format(getString(C1947R.string.arrangement_copyright_violation_detail), new Object[]{getString(C1947R.string.performance_copyright_violation_email)}), true, false);
            textAlertDialog.m19806a(getString(C1947R.string.core_ok), null);
            textAlertDialog.show();
        }
    }

    protected void mo6928E() {
    }

    protected MediaListView mo6862z() {
        return null;
    }
}
