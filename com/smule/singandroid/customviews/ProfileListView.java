package com.smule.singandroid.customviews;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.mopub.nativeads.MagicMoPubGhostStreamAdPlacer;
import com.mopub.nativeads.ViewBinder.Builder;
import com.smule.android.ads.MagicAdAdapterFactory;
import com.smule.android.ads.MagicAdSettings;
import com.smule.android.ads.nativeAds.MagicNativeAdMediatorAdapter;
import com.smule.android.logging.Analytics.NativeAdPlacementType;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserver;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataState;
import com.smule.android.network.api.PerformancesAPI$RenderType;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.uploader.FileUploaderService.VideoUploadStatus;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.WebViewActivity;
import com.smule.singandroid.adapters.profile.ProfileArrangementDataSource;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.dialogs.NativeAdsMoreDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.list_items.PerformanceListItem;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ProfileListView extends LinearLayout implements DataSourceObserver {
    public static final String f21886a = ProfileListView.class.getSimpleName();
    @ViewById
    public MediaListView f21887b;
    @ViewById
    protected SwipeRefreshLayout f21888c;
    BasePerformanceAdapter f21889d;
    protected ProfileFragment f21890e;
    protected int f21891f;
    protected View f21892g;
    protected Observer f21893h = new C44261(this);
    private MagicNativeAdMediatorAdapter f21894i;
    private Observer f21895j = new C44282(this);
    private OnClickListener f21896k = new C44326(this);

    class C44261 implements Observer {
        final /* synthetic */ ProfileListView f21860a;

        C44261(ProfileListView profileListView) {
            this.f21860a = profileListView;
        }

        public void update(Observable observable, Object obj) {
            if (this.f21860a.f21890e != null && this.f21860a.f21890e.isAdded()) {
                Bundle bundle = (Bundle) obj;
                String string = bundle.getString("PERFORMANCE_KEY");
                final long j = bundle.getLong("FILE_UPLOAD_PROGRESS", 0);
                final VideoUploadStatus videoUploadStatus = (VideoUploadStatus) bundle.get("FILE_UPLOAD_STATUS");
                final VideoUploadingView videoUploadingView = (VideoUploadingView) this.f21860a.f21887b.findViewWithTag(string);
                if (videoUploadingView != null && videoUploadStatus != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ C44261 f21859d;

                        public void run() {
                            if (this.f21859d.f21860a.f21890e != null && this.f21859d.f21860a.f21890e.isAdded()) {
                                videoUploadingView.setVideoStatus(videoUploadStatus);
                                if (videoUploadStatus == VideoUploadStatus.UPLOADING) {
                                    videoUploadingView.m24331a(j);
                                } else if (videoUploadStatus == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                                    this.f21859d.f21860a.f21890e.m21107b(videoUploadingView.getPerformance());
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    class C44282 implements Observer {
        final /* synthetic */ ProfileListView f21863a;

        C44282(ProfileListView profileListView) {
            this.f21863a = profileListView;
        }

        public void update(Observable observable, Object obj) {
            final int intValue = ((Integer) obj).intValue();
            if (this.f21863a.f21890e != null && this.f21863a.f21890e.isAdded() && intValue != this.f21863a.f21890e.m21070G() && intValue == this.f21863a.f21891f) {
                new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                    final /* synthetic */ C44282 f21862b;

                    public void run() {
                        if (this.f21862b.f21863a.f21890e != null && this.f21862b.f21863a.f21890e.isAdded()) {
                            this.f21862b.f21863a.f21890e.m21089Z();
                            this.f21862b.f21863a.f21890e.m21111d(intValue);
                            this.f21862b.f21863a.f21887b.setSelection(0);
                        }
                    }
                });
            }
        }
    }

    class C44293 implements OnRefreshListener {
        final /* synthetic */ ProfileListView f21864a;

        C44293(ProfileListView profileListView) {
            this.f21864a = profileListView;
        }

        public void onRefresh() {
            if (this.f21864a.f21889d != null) {
                this.f21864a.f21889d.m18054f();
            }
            if (this.f21864a.f21890e.m21068E() != null && this.f21864a.f21890e.m21069F()) {
                this.f21864a.f21890e.m21068E().m18940b();
            }
        }
    }

    class C44304 implements OnClickListener {
        final /* synthetic */ ProfileListView f21865a;

        C44304(ProfileListView profileListView) {
            this.f21865a = profileListView;
        }

        public void onClick(View view) {
            this.f21865a.f21890e.m19874y();
            new NativeAdsMoreDialog(this.f21865a.f21890e).show();
        }
    }

    class C44315 implements Runnable {
        final /* synthetic */ ProfileListView f21866a;

        C44315(ProfileListView profileListView) {
            this.f21866a = profileListView;
        }

        public void run() {
            this.f21866a.f21890e.m19874y();
        }
    }

    class C44326 implements OnClickListener {
        final /* synthetic */ ProfileListView f21867a;

        C44326(ProfileListView profileListView) {
            this.f21867a = profileListView;
        }

        public void onClick(View view) {
            ((MasterActivity) this.f21867a.f21890e.getActivity()).e("suggested_songs");
        }
    }

    public abstract class BasePerformanceAdapter extends MagicAdapter {
        final /* synthetic */ ProfileListView f21869c;
        private final String f21870d = BasePerformanceAdapter.class.getSimpleName();
        private Set<String> f21871e = new HashSet();

        class C44331 implements NetworkResponseCallback {
            final /* synthetic */ BasePerformanceAdapter f21868a;

            C44331(BasePerformanceAdapter basePerformanceAdapter) {
                this.f21868a = basePerformanceAdapter;
            }

            public void handleResponse(NetworkResponse networkResponse) {
            }
        }

        protected abstract int mo6800h();

        protected abstract String mo6802j();

        public BasePerformanceAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
            this.f21869c = profileListView;
            super(magicDataSource);
        }

        protected void m23412a(PerformanceV2 performanceV2, VideoUploadStatus videoUploadStatus) {
            if (performanceV2 != null && videoUploadStatus == VideoUploadStatus.RENDERING && (System.currentTimeMillis() / 1000) - ((long) performanceV2.createdAt) > 120 && !this.f21871e.contains(performanceV2.performanceKey)) {
                Log.b(this.f21870d, "triggerRerender");
                this.f21871e.add(performanceV2.performanceKey);
                PerformanceManager.a().a(performanceV2.performanceKey, performanceV2.video ? PerformancesAPI$RenderType.VIDEO : PerformancesAPI$RenderType.MAIN, new C44331(this));
            }
        }

        protected boolean mo6801i() {
            return m18048d() > 0;
        }

        public void mo6254c(MagicDataSource magicDataSource) {
            super.mo6254c(magicDataSource);
            if (this.f21869c.f21890e != null && this.f21869c.f21890e.isAdded() && m18026a().m17659i() != DataState.LOADING_FIRST_PAGE) {
                this.f21869c.f21890e.m21091a(mo6800h(), mo6801i());
            }
        }

        public void mo6786b() {
            super.mo6786b();
            if (this.f21869c.f21890e != null && this.f21869c.f21890e.isAdded() && m18026a().m17659i() != DataState.LOADING_FIRST_PAGE) {
                this.f21869c.f21890e.m21091a(mo6800h(), mo6801i());
            }
        }

        protected View m23411a(OnClickListener onClickListener) {
            View a = PerformanceListEmptyListItem.m24394a(this.f21869c.f21890e.getActivity());
            a.m24396a(mo6800h(), this.f21869c.f21890e.m21064A().accountId == UserManager.a().f(), this.f21869c.f21890e.m21064A().handle, onClickListener);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            a.setPadding(0, this.f21869c.getResources().getDimensionPixelOffset(C1947R.dimen.margin_huge), 0, 0);
            a.setLayoutParams(layoutParams);
            return a;
        }

        public boolean mo6799a(View view) {
            return true;
        }
    }

    public class FavoriteAdapter extends BasePerformanceAdapter {
        final /* synthetic */ ProfileListView f21873d;
        private final String f21874e = FavoriteAdapter.class.getSimpleName();

        class C44341 implements OnClickListener {
            final /* synthetic */ FavoriteAdapter f21872a;

            C44341(FavoriteAdapter favoriteAdapter) {
                this.f21872a = favoriteAdapter;
            }

            public void onClick(View view) {
                ((MasterActivity) this.f21872a.f21873d.f21890e.getActivity()).t();
            }
        }

        public FavoriteAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
            this.f21873d = profileListView;
            super(profileListView, magicDataSource);
            m18053e(C1947R.layout.list_loading_view);
        }

        protected int mo6800h() {
            return 3;
        }

        protected boolean mo6801i() {
            if (mo6800h() != 1) {
                return false;
            }
            for (int i = 0; i < m18026a().m17661k(); i++) {
                if (!((PerformanceListItemContainer) m18027a(i)).f19116e) {
                    return true;
                }
            }
            return false;
        }

        protected String mo6802j() {
            return this.f21873d.getResources().getQuantityString(C1947R.plurals.favorite_count, this.f21873d.f21890e.m21073J(), new Object[]{this.f21873d.f21890e.m21085V().m18999a((long) this.f21873d.f21890e.m21073J(), (long) this.f21873d.getResources().getInteger(C1947R.integer.long_form_threshold))});
        }

        protected void mo6463a(View view, int i, boolean z) {
            super.mo6463a(view, i, z);
        }

        public View mo6460d(ViewGroup viewGroup) {
            return m23411a(new C44341(this));
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return PerformanceListItem.m24362c(this.f21873d.getContext());
        }

        public void mo6419a(View view, int i, int i2) {
            VideoUploadStatus c;
            boolean z;
            boolean z2;
            PerformanceListItem performanceListItem = (PerformanceListItem) view;
            PerformanceV2 a = ((PerformanceListItemContainer) m18027a(i)).m20650a();
            if (a != null) {
                c = this.f21873d.f21890e.m21108c(a.performanceKey);
            } else {
                c = VideoUploadStatus.UNKNOWN;
            }
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            if (a == null || !a.video) {
                z2 = false;
            } else {
                z2 = true;
            }
            performanceListItem.setRecordingType(z2);
            performanceListItem.setPerformance(a);
            performanceListItem.setVideoStatus(c);
            performanceListItem.setIsFirstElement(z);
            performanceListItem.setListener(this.f21873d.f21890e.m21086W());
            performanceListItem.setCancelVideoUploadHandler(this.f21873d.f21890e.m21090a(a));
            if (z) {
                performanceListItem.setHeaderTextAndShowHeader(mo6802j());
            } else {
                performanceListItem.m24367d();
            }
            m23412a(a, c);
            if (c == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                this.f21873d.f21890e.m21107b(a);
            }
        }
    }

    public class InviteAdapter extends BasePerformanceAdapter {
        final /* synthetic */ ProfileListView f21875d;
        private final String f21876e = InviteAdapter.class.getSimpleName();

        public InviteAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
            this.f21875d = profileListView;
            super(profileListView, magicDataSource);
        }

        protected int mo6800h() {
            return 1;
        }

        public View mo6460d(ViewGroup viewGroup) {
            return m23411a(this.f21875d.f21896k);
        }

        protected String mo6802j() {
            Log.e(this.f21876e, "getHeaderText called in InviteAdapter");
            return null;
        }

        private String m23426k() {
            return this.f21875d.getResources().getQuantityString(this.f21875d.f21890e.m21071H() ? C1947R.plurals.open_call_count_mine : C1947R.plurals.open_call_count, this.f21875d.f21890e.m21075L(), new Object[]{this.f21875d.f21890e.m21085V().m18999a((long) this.f21875d.f21890e.m21075L(), (long) this.f21875d.getResources().getInteger(C1947R.integer.long_form_threshold))});
        }

        private String m23427l() {
            return this.f21875d.getResources().getQuantityString(C1947R.plurals.bookmark_count, this.f21875d.f21890e.m21076M(), new Object[]{this.f21875d.f21890e.m21085V().m18999a((long) this.f21875d.f21890e.m21076M(), (long) this.f21875d.getResources().getInteger(C1947R.integer.long_form_threshold))});
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return OpenCallListItem.m24333c(this.f21875d.getContext());
        }

        public void mo6419a(View view, int i, int i2) {
            VideoUploadStatus c;
            boolean z;
            boolean z2 = true;
            OpenCallListItem openCallListItem = (OpenCallListItem) view;
            PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer) m18027a(i);
            openCallListItem.setBackgroundColor(-1);
            PerformanceV2 a = performanceListItemContainer.m20650a();
            if (a != null) {
                c = this.f21875d.f21890e.m21108c(a.performanceKey);
            } else {
                c = VideoUploadStatus.UNKNOWN;
            }
            if (!this.f21875d.f21890e.m21071H() || performanceListItemContainer.f19116e) {
                z = false;
            } else {
                z = true;
            }
            openCallListItem.setHideBookmarkOption(z);
            if (a == null || !a.video) {
                z = false;
            } else {
                z = true;
            }
            openCallListItem.setRecordingType(z);
            if (i == 0 || ((PerformanceListItemContainer) m18027a(i - 1)).f19115d) {
                z = true;
            } else {
                z = false;
            }
            openCallListItem.m24336a(a, z);
            openCallListItem.setVideoStatus(c);
            openCallListItem.setExpandedPerformanceListener(this.f21875d.f21890e.m21088Y());
            openCallListItem.setCancelVideoUploadHandler(this.f21875d.f21890e.m21090a(a));
            if (!(this.f21875d.f21890e.m21064A().accountId == UserManager.a().f() && performanceListItemContainer.m20650a().n() && performanceListItemContainer.m20650a().l())) {
                z2 = false;
            }
            openCallListItem.m24340c(z2);
            openCallListItem.m24347j();
            m23412a(a, c);
            if (c == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                this.f21875d.f21890e.m21107b(a);
            }
            openCallListItem.setIsBookmarkItem(performanceListItemContainer.f19116e);
            if (i == 0 && !performanceListItemContainer.f19116e) {
                openCallListItem.m24337a(m23426k(), -1, 0);
            } else if (performanceListItemContainer.f19115d) {
                openCallListItem.m24337a(m23427l(), i == 0 ? -1 : this.f21875d.getResources().getColor(C1947R.color.list_item_pressed), C1947R.drawable.icn_private);
            } else {
                openCallListItem.m24349l();
            }
        }
    }

    public class OwnedArrangementsAdapter extends BasePerformanceAdapter {
        final /* synthetic */ ProfileListView f21883d;
        private final String f21884e = OwnedArrangementsAdapter.class.getSimpleName();

        class C44351 implements OnClickListener {
            final /* synthetic */ OwnedArrangementsAdapter f21877a;

            C44351(OwnedArrangementsAdapter ownedArrangementsAdapter) {
                this.f21877a = ownedArrangementsAdapter;
            }

            public void onClick(View view) {
                this.f21877a.f21883d.getContext().startActivity(WebViewActivity.m22001a(this.f21877a.f21883d.getContext(), this.f21877a.f21883d.getContext().getString(C1947R.string.how_to_upload_arrangement), true, true));
            }
        }

        public OwnedArrangementsAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
            this.f21883d = profileListView;
            super(profileListView, magicDataSource);
            m18053e(C1947R.layout.list_loading_view);
        }

        protected int mo6800h() {
            return 2;
        }

        protected String mo6802j() {
            return this.f21883d.getResources().getQuantityString(C1947R.plurals.arrangements_count, this.f21883d.f21890e.m21077N(), new Object[]{this.f21883d.f21890e.m21085V().m18999a((long) this.f21883d.f21890e.m21077N(), (long) this.f21883d.getResources().getInteger(C1947R.integer.long_form_threshold))});
        }

        public View mo6460d(ViewGroup viewGroup) {
            return m23411a(new C44351(this));
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return ListingListItem.m24377a(this.f21883d.f21890e.getActivity());
        }

        public void mo6419a(View view, int i, int i2) {
            boolean z = true;
            final ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) m18027a(i);
            final ListingListItem listingListItem = (ListingListItem) view;
            listingListItem.m24378a(arrangementVersionLiteEntry, i == 0);
            listingListItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ OwnedArrangementsAdapter f21879b;

                public void onClick(View view) {
                    SingAnalytics.m26133c(arrangementVersionLiteEntry);
                    PreSingActivity.m24763a(this.f21879b.f21883d.f21890e.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(arrangementVersionLiteEntry).a();
                }
            });
            listingListItem.setAlbumArtClickListener(new OnClickListener(this) {
                final /* synthetic */ OwnedArrangementsAdapter f21882c;

                public void onClick(View view) {
                    boolean z = true;
                    if (!listingListItem.m23046t() && this.f21882c.f21883d.f21890e.isAdded()) {
                        listingListItem.setAlbumArtClickedState(true);
                        Log.b(this.f21882c.f21884e, "mPerformanceItemListener - onAlbumArtClicked called");
                        if (this.f21882c.f21883d.f21890e.m21078O() == null) {
                            this.f21882c.f21883d.f21890e.m21103a(new ConcurrentHashMap());
                        }
                        if (this.f21882c.f21883d.f21890e.m21078O().containsKey(arrangementVersionLiteEntry.mo6289c())) {
                            this.f21882c.f21883d.f21890e.m21104a(((Boolean) this.f21882c.f21883d.f21890e.m21078O().get(arrangementVersionLiteEntry.mo6289c())).booleanValue(), arrangementVersionLiteEntry, listingListItem);
                            return;
                        }
                        int i = arrangementVersionLiteEntry.m18774a().removalCode;
                        if (i < 40 || i > 49) {
                            z = false;
                        }
                        this.f21882c.f21883d.f21890e.m21078O().put(arrangementVersionLiteEntry.mo6289c(), Boolean.valueOf(z));
                        this.f21882c.f21883d.f21890e.m21104a(z, arrangementVersionLiteEntry, listingListItem);
                    }
                }
            });
            if (i + 1 != m18048d() - 1) {
                z = false;
            }
            listingListItem.setLastItemBottomPaddingVisibility(z);
            if (i == 0) {
                listingListItem.setHeaderTextAndShowHeader(mo6802j());
            } else {
                listingListItem.m24382c();
            }
        }
    }

    public class PerfAdapter extends BasePerformanceAdapter {
        final /* synthetic */ ProfileListView f21885d;

        public PerfAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
            this.f21885d = profileListView;
            super(profileListView, magicDataSource);
        }

        protected int mo6800h() {
            return 0;
        }

        public View mo6460d(ViewGroup viewGroup) {
            return m23411a(this.f21885d.f21896k);
        }

        protected String mo6802j() {
            return this.f21885d.getResources().getQuantityString(C1947R.plurals.recording_count, this.f21885d.f21890e.m21074K(), new Object[]{this.f21885d.f21890e.m21085V().m18999a((long) this.f21885d.f21890e.m21074K(), (long) this.f21885d.getResources().getInteger(C1947R.integer.long_form_threshold))});
        }

        public boolean m23441b(String str) {
            for (int i = 0; i < m18048d(); i++) {
                if (((PerformanceListItemContainer) m18027a(this.f21885d.f21891f)).m20650a().performanceKey.equals(str)) {
                    return true;
                }
            }
            return false;
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return PerformanceListItem.m24362c(this.f21885d.getContext());
        }

        public void mo6419a(View view, int i, int i2) {
            VideoUploadStatus c;
            boolean z;
            boolean z2 = true;
            PerformanceV2 a = ((PerformanceListItemContainer) m18027a(i)).m20650a();
            if (a != null) {
                c = this.f21885d.f21890e.m21108c(a.performanceKey);
            } else {
                c = VideoUploadStatus.UNKNOWN;
            }
            if (i == 0) {
                z = true;
            } else {
                z = false;
            }
            PerformanceListItem performanceListItem = (PerformanceListItem) view;
            if (a == null || !a.video) {
                z2 = false;
            }
            performanceListItem.setRecordingType(z2);
            performanceListItem.setPerformance(a);
            performanceListItem.setVideoStatus(c);
            performanceListItem.setIsFirstElement(z);
            performanceListItem.setListener(this.f21885d.f21890e.m21086W());
            performanceListItem.setCancelVideoUploadHandler(this.f21885d.f21890e.m21090a(a));
            m23412a(a, c);
            if (c == VideoUploadStatus.ERROR_INVALID_MEDIA) {
                this.f21885d.f21890e.m21107b(a);
            }
            if (z) {
                performanceListItem.setHeaderTextAndShowHeader(mo6802j());
            } else {
                performanceListItem.m24367d();
            }
        }
    }

    public static ProfileListView m23446a(Context context, ProfileFragment profileFragment, int i) {
        ProfileListView a = ProfileListView_.m23455a(context);
        a.f21890e = profileFragment;
        a.f21891f = i;
        ReferenceMonitor.a().a(a);
        return a;
    }

    public ProfileListView(Context context) {
        super(context);
    }

    @UiThread
    public void mo6803a() {
        if (this.f21890e != null) {
            boolean z = this.f21892g == null;
            if (z) {
                this.f21892g = getFloatingHeader();
            }
            this.f21892g.setLayoutParams(new AbsListView.LayoutParams(-1, this.f21890e.m21083T()));
            if (z) {
                try {
                    this.f21887b.setHeaderView(this.f21892g);
                } catch (Throwable e) {
                    MagicCrittercism.a(e);
                }
            } else {
                this.f21889d.mo6786b();
            }
            this.f21888c.setProgressViewOffset(false, this.f21890e.m21083T() + getResources().getDimensionPixelOffset(C1947R.dimen.songbook_swipe_to_refresh_spinner_offset_top), this.f21890e.m21083T() + getResources().getDimensionPixelOffset(C1947R.dimen.songbook_swipe_to_refresh_spinner_offset_bottom));
        }
    }

    public ListView getListView() {
        return this.f21887b;
    }

    private View getFloatingHeader() {
        return ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C1947R.layout.songbook_list_placeholder, this.f21887b, false);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.m19011a().m19014a("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.f21893h);
        NotificationCenter.m19011a().m19014a("PERFORMANCE_TYPE_TABS_CLICKED", this.f21895j);
        this.f21888c.setOnRefreshListener(new C44293(this));
        mo6803a();
        m23450b();
        this.f21890e.aa();
    }

    public void m23450b() {
        if (this.f21890e == null) {
            return;
        }
        if (!this.f21890e.m21071H() || this.f21890e.m21069F()) {
            if (this.f21891f == 0) {
                this.f21889d = new PerfAdapter(this, new ProfilePerformanceDataSource(this.f21890e));
                this.f21890e.m21098a((PerfAdapter) this.f21889d);
            } else if (this.f21891f == 1) {
                this.f21889d = new InviteAdapter(this, new ProfileOpenCallDataSource(this.f21890e));
                this.f21890e.m21096a((InviteAdapter) this.f21889d);
            } else if (this.f21891f == 2) {
                this.f21889d = new OwnedArrangementsAdapter(this, new ProfileArrangementDataSource(this.f21890e));
                this.f21890e.m21097a((OwnedArrangementsAdapter) this.f21889d);
            } else if (this.f21891f == 3) {
                this.f21889d = new FavoriteAdapter(this, new ProfileFavoritesDataSource(this.f21890e));
                this.f21890e.m21095a((FavoriteAdapter) this.f21889d);
            }
            if (this.f21891f != 1) {
                this.f21887b.setMagicAdapter(this.f21889d);
            } else if (MagicAdSettings.m17435a(NativeAdPlacementType.PROFILE_INVITES)) {
                this.f21894i = MagicAdAdapterFactory.m17426a().m17428a(this.f21890e.getActivity(), NativeAdPlacementType.PROFILE_INVITES, new Builder(C1947R.layout.native_ad_ghost_profile_invite_item_view).build(), new Builder(C1947R.layout.native_ad_profile_invite_item_view).iconImageId(C1947R.id.profile_invite_ad_icon).titleId(C1947R.id.profile_invite_ad_title).textId(C1947R.id.profile_invite_ad_text).mainImageId(C1947R.id.profile_invite_ad_main_image).privacyInformationIconImageId(C1947R.id.profile_invite_privacy_icon).build(), new MagicMoPubGhostStreamAdPlacer(this.f21890e.getActivity()), AdUtils.m22219a(null), this.f21887b, this.f21889d, C1947R.id.mMoreIcon, new C44304(this), new C44315(this));
                if (this.f21894i != null) {
                    this.f21894i.loadAds();
                } else {
                    Log.e(f21886a, "mMagicNativeAdMediatorAdapter null");
                    this.f21887b.setMagicAdapter(this.f21889d);
                }
            } else {
                this.f21887b.setMagicAdapter(this.f21889d);
            }
            this.f21889d.mo6858a((DataSourceObserver) this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.m19011a().m19016b("com.smule.android.uploader.intent.FILE_UPLOAD_PROGRESS", this.f21893h);
        NotificationCenter.m19011a().m19016b("PERFORMANCE_TYPE_TABS_CLICKED", this.f21895j);
        this.f21887b.setHeaderView(null);
        this.f21892g = null;
        this.f21890e = null;
        if (this.f21894i != null) {
            this.f21894i.destroy();
            this.f21894i = null;
        }
    }

    @AfterViews
    protected void m23452c() {
        this.f21888c.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
    }

    public void mo6254c(MagicDataSource magicDataSource) {
    }

    public void mo6255d(MagicDataSource magicDataSource) {
    }

    public void mo6252a(MagicDataSource magicDataSource, List<Object> list) {
    }

    public void mo6251a(MagicDataSource magicDataSource) {
        this.f21888c.setRefreshing(true);
    }

    public void mo6253b(MagicDataSource magicDataSource) {
        this.f21888c.setRefreshing(false);
    }
}
