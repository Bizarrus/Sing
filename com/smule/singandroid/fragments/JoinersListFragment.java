package com.smule.singandroid.fragments;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment
public class JoinersListFragment extends BaseFragment {
    public static final String f22366e = JoinersListFragment.class.getName();
    @ViewById
    protected ViewGroup f22367f;
    @ViewById
    protected ViewGroup f22368g;
    @ViewById
    protected TextView f22369h;
    @ViewById
    protected ListView f22370i;
    @ViewById
    protected ProgressBar f22371j;
    @FragmentArg
    protected PerformanceV2 f22372k;
    private LocalizedShortNumberFormatter f22373l;

    class C45321 implements PerformanceManager$PerformanceResponseCallback {
        final /* synthetic */ JoinersListFragment f22363a;

        C45321(JoinersListFragment joinersListFragment) {
            this.f22363a = joinersListFragment;
        }

        public void handleResponse(final PerformanceResponse performanceResponse) {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                final /* synthetic */ C45321 f22362b;

                class C45301 implements Runnable {
                    final /* synthetic */ C45311 f22360a;

                    C45301(C45311 c45311) {
                        this.f22360a = c45311;
                    }

                    public void run() {
                        this.f22360a.f22362b.f22363a.m23758b(null);
                    }
                }

                public void run() {
                    if (performanceResponse.a.e()) {
                        ((BaseActivity) this.f22362b.f22363a.getActivity()).a(performanceResponse.a.f, true, new C45301(this));
                    } else {
                        this.f22362b.f22363a.m23758b(performanceResponse.a() ? performanceResponse.mPerformance : null);
                    }
                }
            });
        }
    }

    private final class JoinersAdapter extends BaseAdapter implements UserFollowItemListener {
        final List<Track> f22364a;
        final /* synthetic */ JoinersListFragment f22365b;

        public JoinersAdapter(JoinersListFragment joinersListFragment, List<Track> list) {
            this.f22365b = joinersListFragment;
            this.f22364a = list;
        }

        public int getCount() {
            return this.f22364a != null ? this.f22364a.size() : 0;
        }

        public Object getItem(int i) {
            return (this.f22364a == null || i >= this.f22364a.size()) ? null : (Track) this.f22364a.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            Track track = (Track) this.f22364a.get(i);
            if (view == null || !(view instanceof UserFollowItem)) {
                view = UserFollowItem.m24442c(this.f22365b.getActivity());
            } else {
                UserFollowItem userFollowItem = (UserFollowItem) view;
            }
            view.m24448a(track.accountIcon, this.f22365b.getActivity(), false, this);
            view.setTime(track.createdAt);
            view.setJoinersStyle(this.f22365b.getResources());
            return view;
        }

        public void mo6457a(boolean z, boolean z2) {
            notifyDataSetChanged();
        }

        public void mo6456a(ProfileFragment profileFragment) {
            this.f22365b.mo6487a((BaseFragment) profileFragment);
        }

        public void mo6455a(SearchResultClkContext searchResultClkContext) {
        }
    }

    public static JoinersListFragment m23755a(PerformanceV2 performanceV2) {
        return JoinersListFragment_.m23762a().m23761a(performanceV2).m23760a();
    }

    public void onStart() {
        super.onStart();
        if (this.f22372k == null || !isAdded()) {
            Activity activity = getActivity();
            if (activity != null) {
                activity.onBackPressed();
            }
        } else if (this.f22372k.recentTracks == null || this.f22372k.recentTracks.size() < 2) {
            PerformanceManager.a().a(this.f22372k.performanceKey, new C45321(this));
        } else {
            m23758b(this.f22372k);
        }
    }

    private LocalizedShortNumberFormatter m23754a() {
        if (this.f22373l == null) {
            this.f22373l = new LocalizedShortNumberFormatter(getActivity());
        }
        return this.f22373l;
    }

    protected void m23758b(PerformanceV2 performanceV2) {
        if (isAdded()) {
            this.f22372k = performanceV2;
            if (this.f22372k != null) {
                List z = m23757z();
                m23756a(this.f22372k.accountIcon.accountId, z);
                if (z.size() > 0) {
                    this.f22369h.setText(getResources().getQuantityString(C1947R.plurals.singers_joined_count, this.f22372k.totalPerformers - 1, new Object[]{m23754a().m18998a((long) (this.f22372k.totalPerformers - 1))}));
                }
                this.f22370i.setAdapter(new JoinersAdapter(this, z));
                this.f22371j.setVisibility(8);
                return;
            }
            this.f22367f.setVisibility(8);
            this.f22368g.setVisibility(0);
        }
    }

    private List<Track> m23757z() {
        List<Track> arrayList = new ArrayList();
        for (Track add : this.f22372k.recentTracks) {
            arrayList.add(add);
        }
        return arrayList;
    }

    private void m23756a(long j, List<Track> list) {
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                if (j == ((Track) list.get(i)).accountIcon.accountId) {
                    list.remove(i);
                    return;
                }
            }
        }
    }

    public String mo6383s() {
        return JoinersListFragment.class.getName();
    }
}
