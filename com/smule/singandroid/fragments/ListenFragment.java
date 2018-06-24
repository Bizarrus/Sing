package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PointerIconCompat;
import android.support.v4.widget.AutoScrollHelper;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.GridView;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.logging.Analytics.RecommendationType;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.PlaylistManager;
import com.smule.android.network.managers.PlaylistManager$GetPlaylistsCallback;
import com.smule.android.network.managers.PlaylistManager$PlaylistPerformancesGetCallback;
import com.smule.android.network.managers.PlaylistManager.PlaylistPerformancesGetResponse;
import com.smule.android.network.managers.PlaylistManager.PlaylistsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Playlist;
import com.smule.android.network.models.RecPerformanceIcon;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.cards.ListenCard;
import com.smule.singandroid.customviews.PaginatedAdapter;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import com.smule.singandroid.list_items.SquarePerformanceItem.SquarePerformanceItemListener;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ListenFragment extends BaseFragment {
    public static final String f22388e = ListenFragment.class.getName();
    private Runnable f22389A;
    @ViewById
    protected View f22390f;
    @ViewById
    protected View f22391g;
    @ViewById
    protected TextView f22392h;
    @ViewById
    protected View f22393i;
    @ViewById
    protected TextView f22394j;
    @ViewById
    protected GridView f22395k;
    int f22396l;
    int f22397m;
    int f22398n;
    int f22399o;
    int f22400p = -1;
    private boolean f22401q;
    private boolean f22402r;
    private boolean f22403s;
    private Observer f22404t;
    private PerformancesListAdapter f22405u = new PerformancesListAdapter();
    private ArrayList<PerformanceListItemContainer> f22406v = new ArrayList();
    private Playlist f22407w;
    private int f22408x;
    private PerformanceItemListener f22409y = new C45342(this);
    private Runnable f22410z;

    class C45331 implements OnScrollListener {
        final /* synthetic */ ListenFragment f22376a;

        C45331(ListenFragment listenFragment) {
            this.f22376a = listenFragment;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.f22376a.f22400p = i;
            m23768a();
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f22376a.f22398n = i;
            this.f22376a.f22399o = i2;
        }

        private void m23768a() {
            if (this.f22376a.f22399o > 0 && this.f22376a.f22400p == 0) {
                if (this.f22376a.f22398n != this.f22376a.f22396l || this.f22376a.f22399o != this.f22376a.f22397m) {
                    this.f22376a.m23789F();
                }
            }
        }
    }

    class C45342 implements PerformanceItemListener {
        final /* synthetic */ ListenFragment f22377a;

        C45342(ListenFragment listenFragment) {
            this.f22377a = listenFragment;
        }

        public void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            int i = -1;
            if (mediaPlayingViewInterface instanceof SquarePerformanceItem) {
                i = ((Integer) ((SquarePerformanceItem) mediaPlayingViewInterface).getTag(C1947R.integer.listen_position_tag)).intValue();
            }
            SingAnalytics.m26072a(this.f22377a.f22407w, i, PerformanceV2Util.m25947e(performanceV2), performanceV2.performanceKey, PerformanceV2Util.m25950h(performanceV2), performanceV2.video);
            Analytics.m17860a(((SquarePerformanceItem) mediaPlayingViewInterface).getRecId(), ItemClickType.LISTEN, i, RecSysContext.PERFLIST, null);
            m23769a(performanceV2);
        }

        public void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
        }

        public void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
            m23769a(performanceV2);
        }

        private void m23769a(PerformanceV2 performanceV2) {
            this.f22377a.m19862m().a(performanceV2, MiscUtils.m25895a(performanceV2), true);
        }
    }

    class C45353 implements Observer {
        final /* synthetic */ ListenFragment f22378a;

        C45353(ListenFragment listenFragment) {
            this.f22378a = listenFragment;
        }

        public void update(Observable observable, Object obj) {
            String str = (String) obj;
            Log.b(ListenFragment.f22388e, "mLoveGivenObserver - update - notification received for performance with key: " + str);
            Collection hashSet = new HashSet();
            hashSet.add(str);
            this.f22378a.f22405u.m23781a(hashSet, true);
        }
    }

    class C45364 implements Runnable {
        final /* synthetic */ ListenFragment f22379a;

        C45364(ListenFragment listenFragment) {
            this.f22379a = listenFragment;
        }

        public void run() {
            if (this.f22379a.isAdded()) {
                this.f22379a.f22410z = null;
                SingAnalytics.m26071a(this.f22379a.f22407w);
            }
        }
    }

    class C45375 implements Runnable {
        final /* synthetic */ ListenFragment f22380a;

        C45375(ListenFragment listenFragment) {
            this.f22380a = listenFragment;
        }

        public void run() {
            if (this.f22380a.isAdded() && this.f22380a.f22402r) {
                this.f22380a.m23789F();
            }
        }
    }

    private class PerformancesListAdapter extends PaginatedAdapter {
        public final String f22385a;
        final /* synthetic */ ListenFragment f22386b;
        private boolean f22387h;

        class C45392 implements PlaylistManager$PlaylistPerformancesGetCallback {
            final /* synthetic */ PerformancesListAdapter f22383a;

            C45392(PerformancesListAdapter performancesListAdapter) {
                this.f22383a = performancesListAdapter;
            }

            public void handleResponse(PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
                if (!this.f22383a.f22386b.isAdded() || !this.f22383a.f22386b.m19843a(this.f22383a.f22386b.d)) {
                    return;
                }
                if (playlistPerformancesGetResponse == null || !playlistPerformancesGetResponse.a()) {
                    this.f22383a.m23784b(playlistPerformancesGetResponse);
                } else {
                    this.f22383a.m23780a(playlistPerformancesGetResponse);
                }
            }
        }

        class C45403 implements SquarePerformanceItemListener {
            final /* synthetic */ PerformancesListAdapter f22384a;

            C45403(PerformancesListAdapter performancesListAdapter) {
                this.f22384a = performancesListAdapter;
            }

            public void mo6821a() {
                Log.b(this.f22384a.f22385a, "commentButtonClicked - called");
                ((MasterActivity) this.f22384a.f22386b.getActivity()).d("");
            }

            public void mo6822b() {
                Log.b(this.f22384a.f22385a, "loveButtonClicked - called");
                ((MasterActivity) this.f22384a.f22386b.getActivity()).J();
            }
        }

        private PerformancesListAdapter(ListenFragment listenFragment) {
            this.f22386b = listenFragment;
            this.f22385a = PerformancesListAdapter.class.getSimpleName();
            this.f22387h = false;
        }

        public int m23777a() {
            return 0;
        }

        public int getCount() {
            return (this.f22386b.f22406v != null ? this.f22386b.f22406v.size() : 0) + m23777a();
        }

        public Object getItem(int i) {
            if (i < m23777a() || i - m23777a() >= this.f22386b.f22406v.size()) {
                return null;
            }
            return (PerformanceListItemContainer) this.f22386b.f22406v.get(i - m23777a());
        }

        public long getItemId(int i) {
            return 0;
        }

        public void m23779a(int i) {
            m23783b(i);
        }

        public void m23782b() {
            this.f22386b.f22405u.d(0);
            this.f22386b.f22405u.f();
            m23783b(1);
        }

        public void m23783b(final int i) {
            if (!this.f22386b.f22401q) {
                this.f22386b.f22401q = true;
                this.f22386b.mo6823B();
                if (this.f22386b.f22407w == null) {
                    List arrayList = new ArrayList();
                    arrayList.add(Long.valueOf((long) SingServerValues.m21668F()));
                    PlaylistManager.a().a(arrayList, new PlaylistManager$GetPlaylistsCallback(this) {
                        final /* synthetic */ PerformancesListAdapter f22382b;

                        public void handleResponse(PlaylistsResponse playlistsResponse) {
                            if (!this.f22382b.f22386b.isAdded()) {
                                return;
                            }
                            if (!playlistsResponse.a() || playlistsResponse.playlists == null || playlistsResponse.playlists.isEmpty()) {
                                this.f22382b.m23784b(null);
                                return;
                            }
                            this.f22382b.f22386b.f22407w = (Playlist) playlistsResponse.playlists.get(0);
                            if (this.f22382b.f22386b.f22410z != null) {
                                new Handler().post(this.f22382b.f22386b.f22410z);
                            }
                            this.f22382b.m23785c(i);
                        }
                    });
                    return;
                }
                m23785c(i);
            }
        }

        protected void m23785c(int i) {
            PlaylistManager.a().a((long) SingServerValues.m21668F(), (i - 1) * 15, 15, new C45392(this));
        }

        protected void m23780a(@NonNull PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
            if (playlistPerformancesGetResponse.next.intValue() == -1 || playlistPerformancesGetResponse.recPerformanceIcons == null || playlistPerformancesGetResponse.recPerformanceIcons.isEmpty()) {
                this.f22387h = true;
            }
            g();
            if (this.f22387h) {
                h();
            } else {
                i();
            }
            notifyDataSetChanged();
            this.f22386b.mo6823B();
            this.f22386b.mo6825b(playlistPerformancesGetResponse.recPerformanceIcons);
        }

        protected void m23784b(PlaylistPerformancesGetResponse playlistPerformancesGetResponse) {
            int i = 0;
            this.f22386b.f22401q = false;
            ListenFragment listenFragment = this.f22386b;
            if (this.f22386b.f22406v != null) {
                i = this.f22386b.f22406v.size();
            }
            listenFragment.mo6826d(i);
            if (playlistPerformancesGetResponse != null && playlistPerformancesGetResponse.a != null && playlistPerformancesGetResponse.a.b == PointerIconCompat.TYPE_NO_DROP) {
                this.f22386b.m19847b(this.f22386b);
            }
        }

        public View m23778a(int i, View view, ViewGroup viewGroup) {
            PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer) getItem(i);
            if (i < m23777a() || performanceListItemContainer != null) {
                View a;
                if (view == null || !(view instanceof ListenCard)) {
                    a = ListenCard.m22310a(this.f22386b.getActivity());
                } else {
                    a = view;
                }
                Object squarePerformanceItem = ((ListenCard) a).getSquarePerformanceItem();
                squarePerformanceItem.getLayoutParams().width = this.f22386b.f22408x;
                squarePerformanceItem.getPlayableItemView().getLayoutParams().height = this.f22386b.f22408x;
                squarePerformanceItem.setTag(C1947R.integer.listen_position_tag, Integer.valueOf(i));
                this.f22386b.m23796a((PerformanceItemInterface) squarePerformanceItem, performanceListItemContainer);
                squarePerformanceItem.setSquarePerformanceItemListener(new C45403(this));
                return a;
            }
            Log.d(this.f22385a, "Graceful handling of fragment/activity lifecycle issues");
            return new View(this.f22386b.getActivity());
        }

        public void m23781a(Collection<String> collection, boolean z) {
            Log.b(this.f22385a, "notifyPerformancesChanged - keys to change = " + Arrays.toString(collection.toArray()));
            ViewGroup viewGroup = this.f22386b.f22395k;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt != null) {
                    int positionForView = viewGroup.getPositionForView(childAt);
                    if (positionForView != -1) {
                        PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer) getItem(positionForView);
                        if (performanceListItemContainer != null) {
                            String str = performanceListItemContainer.m20650a().performanceKey;
                            if (str != null) {
                                for (String str2 : collection) {
                                    if (str2 != null && str2.equals(str)) {
                                        if (z) {
                                            performanceListItemContainer.m20651b();
                                        }
                                        getView(positionForView, childAt, viewGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }
    }

    public static ListenFragment m23792a() {
        return new ListenFragment_();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public boolean mo6446i() {
        return false;
    }

    protected boolean mo6450x() {
        return false;
    }

    public boolean mo6444d() {
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        Log.b(f22388e, "onCreateOptionsMenu");
        menuInflater.inflate(C1947R.menu.listen_fragment_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_search:
                Analytics.m17848a(SearchClkContext.EXPLORE);
                mo6487a(SearchFragment.m25251A());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    @AfterViews
    protected void m23819z() {
        this.f22392h.setText(C1947R.string.profile_loading_performances);
        this.f22394j.setText(C1947R.string.profile_no_performances);
        this.f22400p = -1;
        this.f22397m = -1;
        this.f22396l = -1;
        m23788E();
        this.f22403s = true;
        m23786C();
    }

    private void m23786C() {
        float dimensionPixelSize = (float) getResources().getDimensionPixelSize(C1947R.dimen.album_art_large);
        float f = (float) LayoutUtils.m25847a(getActivity().getWindowManager().getDefaultDisplay()).x;
        float dimensionPixelSize2 = (float) getResources().getDimensionPixelSize(C1947R.dimen.margin_small);
        float f2 = AutoScrollHelper.NO_MAX;
        int i = 1;
        float f3 = 0.0f;
        int i2 = 0;
        while (f2 >= dimensionPixelSize) {
            this.f22408x = (int) f2;
            int i3 = i2 + 1;
            float f4 = f / (3.0f + (21.0f * ((float) i3)));
            dimensionPixelSize2 = CloseButton.TEXT_SIZE_SP * f4;
            float f5 = f3;
            f3 = f4;
            i = i2;
            i2 = i3;
            f2 = dimensionPixelSize2;
            dimensionPixelSize2 = f5;
        }
        this.f22395k.setNumColumns(i);
        this.f22395k.setColumnWidth(this.f22408x);
        this.f22395k.setPadding((int) (dimensionPixelSize2 * 2.0f), (int) dimensionPixelSize2, (int) (dimensionPixelSize2 * 2.0f), 0);
        this.f22395k.setHorizontalSpacing((int) dimensionPixelSize2);
        this.f22395k.setVerticalSpacing((int) dimensionPixelSize2);
    }

    public void m23808A() {
        m23787D();
        this.f22406v.clear();
        this.f22402r = false;
        this.f22401q = false;
        this.f22405u.m23782b();
    }

    private void m23787D() {
        this.f22390f.setVisibility(4);
        this.f22391g.setVisibility(4);
        this.f22393i.setVisibility(4);
    }

    @SupposeUiThread
    protected void mo6823B() {
        int i;
        int i2;
        int i3 = 0;
        if (this.f22401q && this.f22406v.isEmpty()) {
            i = 1;
        } else {
            i = 0;
        }
        View view = this.f22390f;
        if (i != 0) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        view.setVisibility(i2);
        View view2 = this.f22391g;
        if (i == 0) {
            i3 = 4;
        }
        view2.setVisibility(i3);
    }

    @SupposeUiThread
    protected void mo6826d(int i) {
        int i2 = 4;
        int i3 = i == 0 ? 1 : 0;
        if (this.f22401q) {
            this.f22393i.setVisibility(4);
        } else {
            int i4;
            View view = this.f22393i;
            if (i3 != 0) {
                i4 = 0;
            } else {
                i4 = 4;
            }
            view.setVisibility(i4);
        }
        View view2 = this.f22390f;
        if (i3 == 0) {
            i2 = 0;
        }
        view2.setVisibility(i2);
        this.f22395k.setVisibility(0);
    }

    @SupposeUiThread
    public void mo6824a(ArrayList<RecPerformanceIcon> arrayList) {
        if (!this.f22401q) {
            if (arrayList != null) {
                Object arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(new PerformanceListItemContainer((RecPerformanceIcon) it.next()));
                }
                if (this.f22406v == null || this.f22406v.isEmpty()) {
                    this.f22406v = arrayList2;
                } else {
                    this.f22406v.addAll(arrayList2);
                }
                this.f22405u.notifyDataSetChanged();
                if (this.f22389A != null) {
                    new Handler().post(this.f22389A);
                }
            }
            mo6823B();
            mo6826d(this.f22406v == null ? 0 : this.f22406v.size());
        }
    }

    private void m23788E() {
        this.f22405u.i();
        this.f22395k.setAdapter(this.f22405u);
        this.f22405u.notifyDataSetChanged();
        mo6485a(this.f22395k, ActionBarHighlightMode.NEVER, new C45331(this));
    }

    private boolean m23789F() {
        if (this.f22398n == -1) {
            this.f22398n = 0;
        }
        if (this.f22399o == -1) {
            this.f22399o = Math.min(this.f22406v.size(), 4);
        }
        Pair a = m23790a(this.f22398n, this.f22399o);
        if (a != null) {
            Analytics.m17870a((String) a.first, (String) a.second, RecommendationType.PERFORMANCE, RecSysContext.PERFLIST, null);
        }
        return a != null;
    }

    private Pair<String, String> m23790a(int i, int i2) {
        if (this.f22395k == null || this.f22395k.getAdapter() == null || i2 <= 0 || this.f22406v.size() < i + i2) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        StringBuilder stringBuilder2 = new StringBuilder("");
        for (int i3 = 0; i3 < i2; i3++) {
            PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer) this.f22406v.get(i + i3);
            stringBuilder.append(i + i3);
            stringBuilder2.append(performanceListItemContainer.f19114c);
            if (i3 + 1 < i2) {
                stringBuilder.append(",");
                stringBuilder2.append(",");
            }
        }
        this.f22396l = this.f22398n;
        this.f22397m = this.f22399o;
        return new Pair(stringBuilder2.toString(), stringBuilder.toString());
    }

    @SupposeUiThread
    public void mo6825b(ArrayList<RecPerformanceIcon> arrayList) {
        this.f22401q = false;
        this.f22402r = true;
        mo6824a((ArrayList) arrayList);
    }

    private void m23796a(PerformanceItemInterface performanceItemInterface, PerformanceListItemContainer performanceListItemContainer) {
        if (performanceItemInterface == null) {
            Log.e(f22388e, "configurePerformanceItem - performanceItem is null!");
        } else if (performanceListItemContainer == null) {
            Log.e(f22388e, "configurePerformanceItem - performanceListItemContainer is null!");
        } else {
            if (performanceListItemContainer.f19113b != null) {
                performanceItemInterface.setPerformance(performanceListItemContainer.m20650a());
            }
            if (performanceItemInterface instanceof SquarePerformanceItem) {
                ((SquarePerformanceItem) performanceItemInterface).setRecId(performanceListItemContainer.f19114c);
            }
            performanceItemInterface.setListener(this.f22409y);
        }
    }

    public String mo6383s() {
        return f22388e;
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.core_listen);
        Observer c45353 = new C45353(this);
        this.f22404t = c45353;
        NotificationCenter.m19011a().m19014a("LOVE_GIVEN", c45353);
        ListViewMediaPlayerObserver.m25861a(this.f22395k);
    }

    public void onResume() {
        super.onResume();
        if (!(this.f22401q || this.f22402r) || this.f22403s) {
            m23808A();
            this.f22403s = false;
        }
        this.f22405u.notifyDataSetInvalidated();
    }

    public void onStop() {
        super.onStop();
        NotificationCenter.m19011a().m19016b("LOVE_GIVEN", this.f22404t);
        ListViewMediaPlayerObserver.m25862b(this.f22395k);
    }

    protected boolean mo6447j() {
        return true;
    }

    protected void mo6420v() {
        Analytics.m17883b();
        if (this.f22407w != null) {
            SingAnalytics.m26071a(this.f22407w);
        } else {
            this.f22410z = new C45364(this);
        }
        if (this.f22402r) {
            m23789F();
        } else {
            this.f22389A = new C45375(this);
        }
    }
}
