package com.smule.singandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
import com.smule.android.network.managers.SearchManager.SearchResponseCallback;
import com.smule.android.network.managers.SearchManager.SearchResultType;
import com.smule.android.network.managers.SearchManager.SearchSortOption;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.utils.MiscUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsSearchFragment extends BaseFragment {
    public static final String f18734e = FindFriendsSearchFragment.class.getName();
    @ViewById
    protected MagicListView f18735f;
    @ViewById
    protected LinearLayout f18736g;
    @InstanceState
    protected String f18737h;
    protected SearchAccountAdapter f18738i;
    protected boolean f18739j;
    private EditText f18740k;
    private SearchView f18741l;
    private MenuItem f18742m;
    private OnQueryTextListener f18743n = new C38411(this);

    class C38411 implements OnQueryTextListener {
        final /* synthetic */ FindFriendsSearchFragment f18720a;

        C38411(FindFriendsSearchFragment findFriendsSearchFragment) {
            this.f18720a = findFriendsSearchFragment;
        }

        public boolean onQueryTextSubmit(String str) {
            Log.b(FindFriendsSearchFragment.f18734e, "onQueryTextSubmit - " + str);
            this.f18720a.m20206c(str);
            return true;
        }

        public boolean onQueryTextChange(String str) {
            if (str.isEmpty()) {
                MiscUtils.m25890a(this.f18720a.getActivity(), this.f18720a.f18740k);
                this.f18720a.m20204z();
            }
            return true;
        }
    }

    class C38433 implements OnActionExpandListener {
        final /* synthetic */ FindFriendsSearchFragment f18723a;

        C38433(FindFriendsSearchFragment findFriendsSearchFragment) {
            this.f18723a = findFriendsSearchFragment;
        }

        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return true;
        }

        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            this.f18723a.getActivity().onBackPressed();
            return true;
        }
    }

    class C38444 implements OnClickListener {
        final /* synthetic */ FindFriendsSearchFragment f18724a;

        C38444(FindFriendsSearchFragment findFriendsSearchFragment) {
            this.f18724a = findFriendsSearchFragment;
        }

        public void onClick(View view) {
            this.f18724a.f18742m.expandActionView();
        }
    }

    private class SearchAccountAdapter extends MagicAdapter {
        final /* synthetic */ FindFriendsSearchFragment f18727c;

        public SearchAccountAdapter(FindFriendsSearchFragment findFriendsSearchFragment, MagicDataSource magicDataSource) {
            this.f18727c = findFriendsSearchFragment;
            super(magicDataSource);
        }

        public View mo6460d(ViewGroup viewGroup) {
            return m20188a(viewGroup, false);
        }

        public View mo6459c(ViewGroup viewGroup) {
            return m20188a(viewGroup, true);
        }

        private View m20188a(ViewGroup viewGroup, boolean z) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1947R.layout.songbook_search_empty_layout, null);
            TextView textView = (TextView) inflate.findViewById(C1947R.id.search_empty_textview);
            if (z) {
                textView.setText(C1947R.string.search_mix_result_error_text);
            } else {
                textView.setText(this.f18727c.getResources().getString(C1947R.string.search_mix_result_empty_text, new Object[]{this.f18727c.f18737h}));
            }
            return inflate;
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return UserFollowItem.m24442c(this.f18727c.getActivity());
        }

        public void mo6419a(View view, final int i, int i2) {
            if (view instanceof UserFollowItem) {
                boolean z;
                boolean z2;
                boolean z3;
                String str;
                UserFollowItem userFollowItem = (UserFollowItem) view;
                AccountIcon accountIcon = (AccountIcon) m18027a(i);
                Context activity = this.f18727c.getActivity();
                UserFollowItemListener c38451 = new UserFollowItemListener(this) {
                    final /* synthetic */ SearchAccountAdapter f18726b;

                    public void mo6457a(boolean z, boolean z2) {
                    }

                    public void mo6456a(ProfileFragment profileFragment) {
                        if (!this.f18726b.f18727c.f18739j) {
                            this.f18726b.f18727c.m19862m().a(profileFragment, profileFragment.mo6514z());
                        }
                    }

                    public void mo6455a(SearchResultClkContext searchResultClkContext) {
                        Analytics.m17854a(SearchTarget.USER, searchResultClkContext, SearchResultClkValue.SEE_ALL, null, null, Integer.valueOf(i), Long.valueOf(((AccountIcon) this.f18726b.m18027a(i)).accountId), null, null, this.f18726b.m18048d(), i);
                    }
                };
                if (i != 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (i == 9) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                userFollowItem.m24447a(accountIcon, i, activity, false, c38451, z, z2);
                String str2 = "";
                boolean z4 = (accountIcon.firstName == null || accountIcon.firstName.isEmpty()) ? false : true;
                if (accountIcon.lastName == null || accountIcon.lastName.isEmpty()) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (z4 && z3) {
                    str = accountIcon.firstName + " " + accountIcon.lastName;
                } else if (z4) {
                    str = accountIcon.firstName;
                } else if (z3) {
                    str = accountIcon.lastName;
                } else {
                    str = str2;
                }
                if (str.isEmpty()) {
                    userFollowItem.m24449a("", false);
                } else {
                    userFollowItem.m24449a(str, true);
                }
            }
        }
    }

    private class SearchAccountDataSource extends MagicDataSource<AccountIcon> {
        final /* synthetic */ FindFriendsSearchFragment f18731a;
        private String f18732b;
        private boolean f18733c;

        public SearchAccountDataSource(FindFriendsSearchFragment findFriendsSearchFragment, String str) {
            this.f18731a = findFriendsSearchFragment;
            super(SearchAccountDataSource.class.getName() + str);
            this.f18732b = str;
            if (this.f18733c) {
                this.f18733c = false;
                m20193a(m17661k(), 0);
            }
        }

        private void m20193a(int i, long j) {
            Analytics.m17853a(SearchTarget.USER, SearchExecuteContext.INPUT, i, this.f18732b, this.f18732b, j, null);
        }

        protected boolean mo6265e() {
            this.f18733c = super.mo6265e();
            return this.f18733c;
        }

        public int mo6242a() {
            return 25;
        }

        public Future<?> mo6243a(int i, int i2, final FetchDataCallback<AccountIcon> fetchDataCallback) {
            if (TextUtils.isEmpty(this.f18732b)) {
                fetchDataCallback.mo6257a(new ArrayList(), -1);
                return null;
            }
            Log.b(FindFriendsSearchFragment.f18734e, "Running deep search with term: " + this.f18732b);
            final long currentTimeMillis = System.currentTimeMillis();
            return SearchManager.m18331a().m18341a(this.f18732b, SearchResultType.ACCOUNT, i, i2, SearchSortOption.POPULAR, new SearchResponseCallback(this) {
                final /* synthetic */ SearchAccountDataSource f18730c;

                public void handleResponse(SASearchResponse sASearchResponse) {
                    List arrayList = new ArrayList();
                    if (sASearchResponse == null || !sASearchResponse.a()) {
                        this.f18730c.m20193a(0, System.currentTimeMillis() - currentTimeMillis);
                        fetchDataCallback.mo6256a();
                        return;
                    }
                    Iterator it = sASearchResponse.mAccts.iterator();
                    while (it.hasNext()) {
                        AccountIcon accountIcon = (AccountIcon) it.next();
                        if (AccountIcon.a(accountIcon)) {
                            arrayList.add(accountIcon);
                        } else {
                            Log.d(FindFriendsSearchFragment.f18734e, "Invalid account icon parsed in doDeepSearch");
                        }
                    }
                    this.f18730c.m20193a(this.f18730c.m17661k() + sASearchResponse.mAccts.size(), System.currentTimeMillis() - currentTimeMillis);
                    fetchDataCallback.mo6257a(arrayList, sASearchResponse.mNext.intValue());
                }
            });
        }
    }

    public String mo6383s() {
        return f18734e;
    }

    public static FindFriendsSearchFragment m20201b(boolean z) {
        FindFriendsSearchFragment findFriendsSearchFragment_ = new FindFriendsSearchFragment_();
        findFriendsSearchFragment_.f18739j = z;
        return findFriendsSearchFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Log.b(f18734e, "onCreateOptionsMenu - begin");
        if (menu.findItem(C1947R.id.action_search) == null) {
            menuInflater.inflate(C1947R.menu.songbook_search_fragment_menu, menu);
        }
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (isAdded()) {
            this.f18742m = menu.findItem(C1947R.id.action_search);
            if (this.f18742m == null) {
                Log.d(f18734e, "mSearchItem was null. Menu not ready.");
                return;
            }
            this.f18741l = (SearchView) this.f18742m.getActionView();
            Resources resources = SingApplication.f().getResources();
            this.f18741l.findViewById(C1947R.id.search_plate).setBackgroundColor(0);
            EditText editText = (EditText) this.f18741l.findViewById(C1947R.id.search_src_text);
            editText.setTextColor(-16777216);
            editText.setHintTextColor(resources.getColor(C1947R.color.search_empty_title_text_color));
            this.f18741l.setQueryHint(getString(C1947R.string.search_by_username_hint));
            editText.setFilters(new InputFilter[]{new LengthFilter(128)});
            editText.setTextSize(0, (float) resources.getDimensionPixelSize(C1947R.dimen.margin_medium_large));
            this.f18740k = editText;
            try {
                Field declaredField = SearchView.class.getDeclaredField("mSearchHintIcon");
                declaredField.setAccessible(true);
                ((Drawable) declaredField.get(this.f18741l)).setBounds(0, 0, 0, 0);
            } catch (Exception e) {
            }
            if (this.f18741l != null) {
                this.f18741l.setOnQueryTextListener(null);
            }
            final int i = this.d;
            m19839a(new Runnable(this) {
                final /* synthetic */ FindFriendsSearchFragment f18722b;

                public void run() {
                    if (this.f18722b.m19843a(i)) {
                        this.f18722b.m20199a(this.f18722b.f18742m);
                    }
                }
            }, 300);
        }
    }

    private void m20199a(MenuItem menuItem) {
        if (menuItem != null && this.f18741l != null) {
            this.f18742m.expandActionView();
            if (TextUtils.isEmpty(this.f18737h)) {
                MiscUtils.m25890a(getActivity(), this.f18740k);
            } else {
                this.f18740k.setText(this.f18737h);
                this.f18741l.clearFocus();
                MiscUtils.m25891a(getActivity(), true);
            }
            this.f18741l.setOnQueryTextListener(this.f18743n);
            MenuItemCompat.setOnActionExpandListener(menuItem, new C38433(this));
        }
    }

    private void m20204z() {
        if (isAdded()) {
            this.f18735f.setMagicAdapter(null);
            this.f18735f.setVisibility(8);
            this.f18736g.setVisibility(0);
            this.f18737h = "";
        }
    }

    protected void m20206c(String str) {
        if (!str.equals(this.f18737h)) {
            if (this.f18738i == null || !this.f18738i.m18026a().m17671u()) {
                this.f18737h = str;
                if (str == null || str.isEmpty()) {
                    Log.b(f18734e, "doSearch - searchTerm is null; aborting search");
                    m20204z();
                    return;
                }
                MiscUtils.m25894a(this.f18740k, true);
                Log.b(f18734e, "doSearch - searchTerm is: " + str);
                this.f18738i = new SearchAccountAdapter(this, new SearchAccountDataSource(this, this.f18737h));
                this.f18735f.setMagicAdapter(this.f18738i);
                this.f18736g.setVisibility(8);
                this.f18735f.setVisibility(0);
            }
        }
    }

    @AfterViews
    protected void m20205a() {
        m19850c((int) C1947R.string.core_find_friends);
        ((MasterActivity) getActivity()).B().getTitleView().setOnClickListener(new C38444(this));
        if (this.f18738i != null) {
            this.f18736g.setVisibility(8);
            this.f18735f.setVisibility(0);
            this.f18735f.setMagicAdapter(this.f18738i);
            return;
        }
        this.f18736g.setVisibility(0);
        this.f18735f.setVisibility(8);
    }

    protected void mo6420v() {
        if (this.f18738i != null) {
            Analytics.m17853a(SearchTarget.USER, SearchExecuteContext.BACK, this.f18738i.m18048d(), this.f18737h, this.f18737h, 0, null);
        }
    }
}
