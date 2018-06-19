package com.smule.singandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.FollowManager.FollowListDataSource;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.UserRelationType;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FollowListFragment extends BaseFragment {
    private static final String f18748j = FollowListFragment.class.getName();
    @InstanceState
    protected long f18749e;
    @InstanceState
    protected String f18750f;
    @InstanceState
    protected int f18751g;
    @InstanceState
    protected int f18752h;
    @ViewById
    protected MagicListView f18753i;

    private class FollowListAdapter extends MagicAdapter {
        final /* synthetic */ FollowListFragment f18747c;

        class C38471 implements UserFollowItemListener {
            final /* synthetic */ FollowListAdapter f18746a;

            C38471(FollowListAdapter followListAdapter) {
                this.f18746a = followListAdapter;
            }

            public void mo6457a(boolean z, boolean z2) {
            }

            public void mo6456a(ProfileFragment profileFragment) {
                this.f18746a.f18747c.m19862m().a(profileFragment, profileFragment.mo6514z());
            }

            public void mo6455a(SearchResultClkContext searchResultClkContext) {
            }
        }

        public FollowListAdapter(FollowListFragment followListFragment, MagicDataSource magicDataSource) {
            this.f18747c = followListFragment;
            super(magicDataSource);
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return UserFollowItem.m24442c(this.f18747c.getActivity());
        }

        public void mo6419a(View view, int i, int i2) {
            ((UserFollowItem) view).m24446a((AccountIcon) m18027a(i), i, this.f18747c.getActivity(), false, new C38471(this), true);
        }

        public View mo6460d(ViewGroup viewGroup) {
            return LayoutInflater.from(this.f18747c.getActivity()).inflate(C1947R.layout.follow_list_empty_layout, null, false);
        }

        public View mo6464b(ViewGroup viewGroup) {
            return LayoutInflater.from(this.f18747c.getActivity()).inflate(C1947R.layout.follow_list_loading_layout, null, false);
        }

        public int getPositionForSection(int i) {
            FollowListDataSource followListDataSource = (FollowListDataSource) m18026a();
            if (i == 0) {
                return 0;
            }
            return followListDataSource.mo6264d();
        }

        protected void mo6463a(View view, int i, boolean z) {
            super.mo6463a(view, i, z);
            UserFollowItem userFollowItem = (UserFollowItem) view;
            if (!z) {
                ((UserFollowItem) view).m24443a(8, "");
            } else if (getSectionForPosition(i) == 0) {
                ((UserFollowItem) view).m24443a(0, this.f18747c.getResources().getString(C1947R.string.news_follow_title));
            } else {
                ((UserFollowItem) view).m24443a(0, this.f18747c.getResources().getString(C1947R.string.news_follow_other_apps));
            }
        }

        public Object[] getSections() {
            FollowListDataSource followListDataSource = (FollowListDataSource) m18026a();
            int i = 0;
            if (followListDataSource.m18199v() > 0) {
                i = 1;
            }
            if (followListDataSource.mo6264d() > 0) {
                i++;
            }
            return new Object[i];
        }

        public int getSectionForPosition(int i) {
            return i < ((FollowListDataSource) m18026a()).mo6264d() ? 0 : 1;
        }
    }

    public String mo6383s() {
        return f18748j;
    }

    public static FollowListFragment m20222a(long j, String str, int i, int i2) {
        FollowListFragment followListFragment_ = new FollowListFragment_();
        Bundle bundle = new Bundle();
        bundle.putLong("FOLLOW_LIST_ACCOUNT_ID_KEY", j);
        bundle.putString("FOLLOW_LIST_PICTURE_URL", str);
        bundle.putInt("FOLLOW_LIST_DISPLAY_MODE_KEY", i);
        bundle.putInt("FOLLOW_LIST_CREATED_COUNT_KEY", i2);
        followListFragment_.setArguments(bundle);
        return followListFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            Bundle arguments = getArguments();
            this.f18749e = arguments.getLong("FOLLOW_LIST_ACCOUNT_ID_KEY");
            this.f18750f = arguments.getString("FOLLOW_LIST_PICTURE_URL");
            this.f18751g = arguments.getInt("FOLLOW_LIST_DISPLAY_MODE_KEY", -1);
            this.f18752h = arguments.getInt("FOLLOW_LIST_CREATED_COUNT_KEY", 0);
        }
        if (this.f18751g == -1) {
            throw new RuntimeException("Display mode not set properly when creating activity. Finishing now.");
        }
    }

    @AfterViews
    public void m20224a() {
        this.f18753i.setMagicAdapter(new FollowListAdapter(this, new FollowListDataSource(this.f18751g, this.f18749e, null, null)));
    }

    public void onStart() {
        super.onStart();
        if (this.f18751g == 0) {
            m19850c((int) C1947R.string.core_followers);
        } else if (this.f18751g == 1) {
            m19850c((int) C1947R.string.core_following);
        }
    }

    protected void mo6420v() {
        if (this.f18751g == 1) {
            SingAnalytics.m26134c(m20223z(), this.f18752h);
        } else if (this.f18751g == 0) {
            SingAnalytics.m26126b(m20223z(), this.f18752h);
        }
    }

    private UserRelationType m20223z() {
        return this.f18749e == UserManager.a().f() ? UserRelationType.MINE : UserRelationType.OTHER;
    }
}
