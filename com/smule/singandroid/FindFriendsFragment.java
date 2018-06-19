package com.smule.singandroid;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.RecSysContext;
import com.smule.android.logging.Analytics.RecommendationType;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.RecommendedSingersCallback;
import com.smule.android.network.managers.RecommendationManager.RecommendedSingersResponse.RecAccountIcon;
import com.smule.singandroid.customviews.FindFriendsHeaderView;
import com.smule.singandroid.list_items.RecUserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsFragment extends BaseFragment {
    public static final String f18701e = FindFriendsFragment.class.getName();
    @ViewById
    protected View f18702f;
    @ViewById
    protected ListView f18703g;
    protected RecommendedSingersAdapter f18704h;
    @ViewById
    protected View f18705i;
    @ViewById
    protected View f18706j;
    protected FindFriendsHeaderView f18707k;
    @FragmentArg
    protected boolean f18708l;
    int f18709m;
    int f18710n;
    int f18711o;
    int f18712p;
    int f18713q = -1;
    private boolean f18714r = true;
    private ArrayList<RecAccountIcon> f18715s = new ArrayList();
    private MenuItem f18716t;

    class C38351 implements OnScrollListener {
        final /* synthetic */ FindFriendsFragment f18694a;

        C38351(FindFriendsFragment findFriendsFragment) {
            this.f18694a = findFriendsFragment;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
            this.f18694a.f18713q = i;
            m20152a();
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f18694a.f18711o = i;
            this.f18694a.f18712p = i2;
        }

        private void m20152a() {
            if (this.f18694a.f18712p > 0 && this.f18694a.f18713q == 0) {
                if (this.f18694a.f18711o != this.f18694a.f18709m || this.f18694a.f18712p != this.f18694a.f18710n) {
                    Pair a = this.f18694a.m20165a(this.f18694a.f18711o, this.f18694a.f18712p);
                    if (a != null) {
                        Analytics.m17870a((String) a.first, (String) a.second, RecommendationType.ACCOUNT, RecSysContext.FINDFRIENDS, null);
                    }
                }
            }
        }
    }

    class C38362 implements RecommendedSingersCallback {
        final /* synthetic */ FindFriendsFragment f18695a;

        C38362(FindFriendsFragment findFriendsFragment) {
            this.f18695a = findFriendsFragment;
        }

        public void mo6454a(List<RecAccountIcon> list, List<RecAccountIcon> list2) {
            Collection arrayList = new ArrayList();
            for (RecAccountIcon recAccountIcon : list) {
                if (list2.contains(recAccountIcon)) {
                    arrayList.add(recAccountIcon);
                }
            }
            list.removeAll(arrayList);
            Collections.shuffle(list);
            Iterator it = list.iterator();
            Iterator it2 = list2.iterator();
            while (this.f18695a.f18715s.size() < list.size() + list2.size()) {
                if (it2.hasNext()) {
                    this.f18695a.f18715s.add(it2.next());
                }
                if (it2.hasNext()) {
                    this.f18695a.f18715s.add(it2.next());
                }
                if (it.hasNext()) {
                    this.f18695a.f18715s.add(it.next());
                }
            }
            this.f18695a.mo6458A();
        }
    }

    class C38373 implements Runnable {
        final /* synthetic */ FindFriendsFragment f18696a;

        C38373(FindFriendsFragment findFriendsFragment) {
            this.f18696a = findFriendsFragment;
        }

        public void run() {
            this.f18696a.m20163E();
        }
    }

    class C38384 implements OnMenuItemClickListener {
        final /* synthetic */ FindFriendsFragment f18697a;

        C38384(FindFriendsFragment findFriendsFragment) {
            this.f18697a = findFriendsFragment;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            Analytics.m17848a(SearchClkContext.FINDFRIENDS);
            this.f18697a.mo6487a(FindFriendsSearchFragment.m20201b(this.f18697a.f18708l));
            return true;
        }
    }

    protected class RecommendedSingersAdapter extends ArrayAdapter {
        final /* synthetic */ FindFriendsFragment f18700a;

        public RecommendedSingersAdapter(FindFriendsFragment findFriendsFragment, Context context, int i, List list) {
            this.f18700a = findFriendsFragment;
            super(context, i, list);
        }

        public int getCount() {
            if (this.f18700a.f18715s != null) {
                return this.f18700a.f18715s.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return this.f18700a.f18715s.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            View a;
            if (view == null || !(view instanceof UserFollowItem)) {
                a = RecUserFollowItem.m24451a(this.f18700a.getActivity());
            } else {
                a = view;
            }
            ((RecUserFollowItem) a).m24453a((RecAccountIcon) this.f18700a.f18715s.get(i), i, this.f18700a.getActivity(), false, true, new UserFollowItemListener(this) {
                final /* synthetic */ RecommendedSingersAdapter f18699b;

                public void mo6457a(boolean z, boolean z2) {
                }

                public void mo6456a(ProfileFragment profileFragment) {
                    if (!this.f18699b.f18700a.f18708l) {
                        this.f18699b.f18700a.m19862m().a(profileFragment, profileFragment.mo6514z());
                    }
                }

                public void mo6455a(SearchResultClkContext searchResultClkContext) {
                    Analytics.m17860a(((RecAccountIcon) this.f18699b.f18700a.f18715s.get(i)).mRecId, ItemClickType.PROFILE, i, RecSysContext.FINDFRIENDS, null);
                }
            });
            return a;
        }
    }

    public static FindFriendsFragment m20167a() {
        return m20169b(false);
    }

    public static FindFriendsFragment m20169b(boolean z) {
        return FindFriendsFragment_.m20177B().m20176a(z).m20175a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        this.f18707k = FindFriendsHeaderView.m23176a(getActivity());
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.core_find_friends);
    }

    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            getActivity().invalidateOptionsMenu();
        }
    }

    public void onStop() {
        super.onStop();
        m20164F();
        this.f18714r = false;
    }

    protected void mo6420v() {
        SingAnalytics.m26159w();
    }

    public String mo6383s() {
        return null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(C1947R.layout.find_friends_fragment, viewGroup, false);
    }

    @AfterViews
    protected void m20174z() {
        this.f18707k.m23177a();
        this.f18703g.addHeaderView(this.f18707k);
        m20161C();
    }

    private void m20160B() {
        Context activity = getActivity();
        if (activity != null && this.f18703g != null && this.f18703g.getAdapter() != null && this.f18704h != null) {
            Pair a;
            if (LayoutUtils.m25858a(activity)) {
                a = m20165a(0, this.f18703g.getHeaderViewsCount() + 4);
            } else {
                a = m20165a(0, this.f18703g.getHeaderViewsCount() + 3);
            }
            if (a != null) {
                Analytics.m17870a((String) a.first, (String) a.second, RecommendationType.ACCOUNT, RecSysContext.FINDFRIENDS, null);
            }
        }
    }

    private Pair<String, String> m20165a(int i, int i2) {
        int i3 = 0;
        if (this.f18703g == null || this.f18703g.getAdapter() == null || this.f18704h == null) {
            return null;
        }
        int min = Math.min(0, i - this.f18703g.getHeaderViewsCount()) * -1;
        int headerViewsCount = i - this.f18703g.getHeaderViewsCount();
        if (min > 0) {
            i2 -= min;
            headerViewsCount = 0;
        }
        if (i2 <= 0 || this.f18704h.getCount() <= headerViewsCount + i2) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        StringBuilder stringBuilder2 = new StringBuilder("");
        while (i3 < i2) {
            RecAccountIcon recAccountIcon = (RecAccountIcon) this.f18704h.getItem(headerViewsCount + i3);
            if (recAccountIcon.mRecId != null) {
                stringBuilder.append(headerViewsCount + i3);
                stringBuilder2.append(recAccountIcon.mRecId);
                if (i3 + 1 < i2) {
                    stringBuilder.append(",");
                    stringBuilder2.append(",");
                }
            }
            i3++;
        }
        this.f18711o = headerViewsCount;
        this.f18712p = i2;
        this.f18709m = this.f18711o;
        this.f18710n = this.f18712p;
        return new Pair(stringBuilder2.toString(), stringBuilder.toString());
    }

    private void m20161C() {
        if (this.f18714r) {
            this.f18705i.setVisibility(0);
            this.f18703g.setAdapter(null);
            this.f18703g.setOnScrollListener(new PauseOnScrollListener(ImageLoader.a(), true, true, new C38351(this)));
            RecommendationManager.m18285a().m18299a(new C38362(this));
            return;
        }
        mo6458A();
    }

    @UiThread
    protected void mo6458A() {
        if (isAdded()) {
            this.f18704h = new RecommendedSingersAdapter(this, getActivity(), C1947R.layout.user_follow_item, this.f18715s);
            this.f18703g.setAdapter(this.f18704h);
            m20162D();
            return;
        }
        Log.e(f18701e, "showRecommededUsers - fragment not added; aborting!");
    }

    private void m20162D() {
        if ((this.f18715s == null ? 0 : this.f18715s.size()) + 0 > 0) {
            this.f18703g.setVisibility(0);
            this.f18706j.setVisibility(8);
        } else {
            this.f18703g.setVisibility(8);
            this.f18706j.setVisibility(0);
        }
        this.f18705i.setVisibility(8);
        m20160B();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        Log.b(f18701e, "onCreateOptionsMenu - begin");
        if (menu.findItem(C1947R.id.action_search) == null) {
            menuInflater.inflate(C1947R.menu.find_friends_fragment_menu, menu);
        }
        this.f18716t = menu.findItem(C1947R.id.action_search);
        this.f18716t.getIcon().setColorFilter(getResources().getColor(C1947R.color.action_bar_content), Mode.SRC_ATOP);
        m19839a(new C38373(this), 300);
    }

    private void m20163E() {
        this.f18716t.setOnMenuItemClickListener(new C38384(this));
    }

    private void m20164F() {
        if (this.f18716t != null) {
            this.f18716t.setOnMenuItemClickListener(null);
        }
    }
}
