/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.v4.view.MenuItemCompat
 *  android.support.v4.view.MenuItemCompat$OnActionExpandListener
 *  android.support.v7.widget.SearchView
 *  android.support.v7.widget.SearchView$OnQueryTextListener
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.text.TextUtils
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsSearchFragment_;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FindFriendsSearchFragment
extends BaseFragment {
    public static final String g = FindFriendsSearchFragment.class.getName();
    @ViewById
    protected MagicListView h;
    @ViewById
    protected LinearLayout i;
    @InstanceState
    protected String j;
    protected SearchAccountAdapter k;
    protected boolean l;
    private EditText m;
    private SearchView n;
    private MenuItem o;
    private SearchView.OnQueryTextListener p;

    public FindFriendsSearchFragment() {
        this.p = new SearchView.OnQueryTextListener(){

            public boolean onQueryTextChange(String string2) {
                if (string2.isEmpty()) {
                    MiscUtils.a((Activity)FindFriendsSearchFragment.this.getActivity(), (EditText)FindFriendsSearchFragment.this.m);
                    FindFriendsSearchFragment.this.t();
                }
                return true;
            }

            public boolean onQueryTextSubmit(String string2) {
                string2 = com.smule.android.network.managers.SearchManager.a(string2);
                Log.b(FindFriendsSearchFragment.g, "onQueryTextSubmit - " + string2);
                FindFriendsSearchFragment.this.c(string2);
                return true;
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(MenuItem menuItem) {
        if (menuItem == null || this.n == null) {
            return;
        }
        this.o.expandActionView();
        if (!TextUtils.isEmpty((CharSequence)this.j)) {
            this.m.setText((CharSequence)this.j);
            this.n.clearFocus();
            MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        } else {
            MiscUtils.a((Activity)this.getActivity(), (EditText)this.m);
        }
        this.n.setOnQueryTextListener(this.p);
        MenuItemCompat.setOnActionExpandListener((MenuItem)menuItem, (MenuItemCompat.OnActionExpandListener)new MenuItemCompat.OnActionExpandListener(){

            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                FindFriendsSearchFragment.this.getActivity().onBackPressed();
                return true;
            }

            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                return true;
            }
        });
    }

    public static FindFriendsSearchFragment c(boolean bl) {
        FindFriendsSearchFragment_ findFriendsSearchFragment_ = new FindFriendsSearchFragment_();
        findFriendsSearchFragment_.l = bl;
        return findFriendsSearchFragment_;
    }

    private void t() {
        if (!this.isAdded()) {
            return;
        }
        this.h.setMagicAdapter(null);
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.j = "";
    }

    @Override
    protected void A() {
        if (this.k != null) {
            SingAnalytics.a(Analytics.c, Analytics.c, (int)this.k.d(), (String)this.j, (String)this.j, (long)0, (Integer)null);
        }
    }

    @AfterViews
    protected void a() {
        ((MasterActivity)this.getActivity()).U().getTitleView().setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FindFriendsSearchFragment.this.o.expandActionView();
            }
        });
        if (this.k != null) {
            this.a((CharSequence)"");
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.h.setMagicAdapter(this.k);
            return;
        }
        this.c(2131296687);
        this.i.setVisibility(0);
        this.h.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(String string2) {
        if (string2.equals(this.j)) {
            MiscUtils.a((View)this.m, (boolean)true);
            return;
        } else {
            if (this.k != null && this.k.a().v()) return;
            {
                this.j = string2;
                if (string2 != null && !string2.isEmpty()) {
                    MiscUtils.a((View)this.m, (boolean)true);
                    Log.b(g, "doSearch - searchTerm is: " + string2);
                    this.k = new SearchAccountAdapter(new SearchAccountDataSource(this.j));
                    this.h.setMagicAdapter(this.k);
                    this.i.setVisibility(8);
                    this.h.setVisibility(0);
                    return;
                }
                Log.b(g, "doSearch - searchTerm is null; aborting search");
                this.t();
                return;
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        Log.b(g, "onCreateOptionsMenu - begin");
        if (menu2.findItem(2131756839) == null) {
            menuInflater.inflate(2131820559, menu2);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onPrepareOptionsMenu(Menu object) {
        super.onPrepareOptionsMenu((Menu)object);
        if (!this.isAdded()) {
            return;
        }
        this.o = object.findItem(2131756839);
        if (this.o == null) {
            Log.d(g, "mSearchItem was null. Menu not ready.");
            return;
        }
        this.n = (SearchView)this.o.getActionView();
        object = SingApplication.g().getResources();
        this.n.findViewById(2131755210).setBackgroundColor(0);
        EditText editText = (EditText)this.n.findViewById(2131755211);
        editText.setTextColor(-16777216);
        editText.setHintTextColor(object.getColor(2131689930));
        this.n.setQueryHint((CharSequence)this.getString(2131297290));
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(128)});
        editText.setTextSize(0, (float)object.getDimensionPixelSize(2131428168));
        this.m = editText;
        try {
            object = SearchView.class.getDeclaredField("mSearchHintIcon");
            object.setAccessible(true);
            ((Drawable)object.get((Object)this.n)).setBounds(0, 0, 0, 0);
        }
        catch (Exception exception) {}
        if (this.n != null) {
            this.n.setOnQueryTextListener(null);
        }
        this.a(new Runnable(this.e){
            final /* synthetic */ int a;
            {
                this.a = n;
            }

            @Override
            public void run() {
                if (!FindFriendsSearchFragment.this.a(this.a)) {
                    return;
                }
                FindFriendsSearchFragment.this.a(FindFriendsSearchFragment.this.o);
            }
        }, 300);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.a((CharSequence)"");
    }

    @Override
    public String x() {
        return g;
    }

    private class SearchAccountAdapter
    extends MagicAdapter {
        public SearchAccountAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
        }

        private View a(ViewGroup viewGroup, boolean bl) {
            viewGroup = LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903425, null);
            TextView textView = (TextView)viewGroup.findViewById(2131756719);
            if (bl) {
                textView.setText(2131297299);
                return viewGroup;
            }
            textView.setText((CharSequence)FindFriendsSearchFragment.this.getResources().getString(2131297298, new Object[]{FindFriendsSearchFragment.this.j}));
            return viewGroup;
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return UserFollowListItem.c((Context)FindFriendsSearchFragment.this.getActivity());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(View object, int n, int n2) {
            if (object instanceof UserFollowListItem) {
                UserFollowListItem userFollowListItem = (UserFollowListItem)((Object)object);
                object = (AccountIcon)this.a(n);
                Activity activity = FindFriendsSearchFragment.this.getActivity();
                UserFollowListItem.UserFollowListItemListener userFollowListItemListener = new UserFollowListItem.UserFollowListItemListener((AccountIcon)object, n){
                    final /* synthetic */ AccountIcon a;
                    final /* synthetic */ int b;
                    {
                        this.a = accountIcon;
                        this.b = n;
                    }

                    @Override
                    public void a(Analytics searchResultClkContext) {
                        com.smule.android.logging.Analytics.a(Analytics.c, searchResultClkContext, Analytics.a, null, null, this.b, ((AccountIcon)SearchAccountAdapter.this.a((int)this.b)).accountId, null, null, SearchAccountAdapter.this.d(), this.b);
                    }

                    @Override
                    public void a(ProfileFragment profileFragment) {
                        if (!FindFriendsSearchFragment.this.l) {
                            Log.b(FindFriendsSearchFragment.g, "showing profile: " + this.a.handle);
                            ((MasterActivity)FindFriendsSearchFragment.this.getActivity()).U().getTitleView().setOnClickListener(null);
                            FindFriendsSearchFragment.this.p().a(profileFragment, profileFragment.t());
                        }
                    }

                    @Override
                    public void a(boolean bl, boolean bl2) {
                    }
                };
                boolean bl = n != 0;
                boolean bl2 = n == 9;
                userFollowListItem.a((AccountIcon)object, n, (Context)activity, false, userFollowListItemListener, bl, bl2);
                n = object.firstName != null && !object.firstName.isEmpty() ? 1 : 0;
                n2 = object.lastName != null && !object.lastName.isEmpty() ? 1 : 0;
                object = n != 0 && n2 != 0 ? object.firstName + " " + object.lastName : (n != 0 ? object.firstName : (n2 != 0 ? object.lastName : ""));
                if (object.isEmpty()) {
                    userFollowListItem.c("", false);
                    return;
                }
                userFollowListItem.c((String)object, true);
            }
        }

        @Override
        public View c(ViewGroup viewGroup) {
            return this.a(viewGroup, true);
        }

        @Override
        public View d(ViewGroup viewGroup) {
            return this.a(viewGroup, false);
        }

    }

    private class SearchAccountDataSource
    extends MagicDataSource<AccountIcon, MagicDataSource.OffsetPaginationTracker> {
        private final String b;
        private boolean l;

        public SearchAccountDataSource(String string2) {
            super(SearchAccountDataSource.class.getName() + string2, new MagicDataSource.OffsetPaginationTracker());
            this.b = string2;
            if (this.l) {
                this.l = false;
                this.a(this.k(), 0);
            }
        }

        @Override
        private void a(int n, long l) {
            com.smule.android.logging.Analytics.a(Analytics.c, Analytics.a, n, this.b, this.b, l, null);
        }

        @Override
        public int a() {
            return 25;
        }

        @Override
        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<AccountIcon, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            if (TextUtils.isEmpty((CharSequence)this.b)) {
                fetchDataCallback.a(new ArrayList(), new MagicDataSource.OffsetPaginationTracker(-1));
                return null;
            }
            Log.b(FindFriendsSearchFragment.g, "Running deep search with term: " + this.b);
            final long l = System.currentTimeMillis();
            return com.smule.android.network.managers.SearchManager.a().a(this.b, SearchManager.SearchResultType.b, offsetPaginationTracker.a(), n, SearchManager.SearchSortOption.a, new SearchManager.SearchResponseCallback(){

                @Override
                public void handleResponse(SearchManager sASearchResponse) {
                    ArrayList<AccountIcon> arrayList = new ArrayList<AccountIcon>();
                    if (sASearchResponse != null && sASearchResponse.a()) {
                        for (AccountIcon accountIcon : sASearchResponse.mAccts) {
                            if (!AccountIcon.a(accountIcon)) {
                                Log.d(FindFriendsSearchFragment.g, "Invalid account icon parsed in doDeepSearch");
                                continue;
                            }
                            arrayList.add(accountIcon);
                        }
                        SearchAccountDataSource.this.a(SearchAccountDataSource.this.k() + sASearchResponse.mAccts.size(), System.currentTimeMillis() - l);
                        fetchDataCallback.a(arrayList, new MagicDataSource.OffsetPaginationTracker(sASearchResponse.mNext));
                        return;
                    }
                    SearchAccountDataSource.this.a(0, System.currentTimeMillis() - l);
                    fetchDataCallback.a();
                }
            });
        }

        @Override
        protected boolean f() {
            this.l = super.f();
            return this.l;
        }

    }

}

