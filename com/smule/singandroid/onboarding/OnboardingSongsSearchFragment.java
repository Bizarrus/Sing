/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.text.Editable
 *  android.text.Html
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.songbook_search.SearchBaseFragment
 *  com.smule.singandroid.songbook_search.SearchFragment
 *  com.smule.singandroid.songbook_search.SearchFragment$SearchDataSource
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.onboarding.OnboardingActivity;
import com.smule.singandroid.songbook_search.SearchBaseFragment;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingSongsSearchFragment
extends SearchBaseFragment {
    public static final String g = OnboardingSongsSearchFragment.class.getName();
    private WeakListener.OnTouchListener A;
    protected SearchFragment.SearchDataSource h;
    @ViewById
    protected EditText i;
    @ViewById
    protected View j;
    @ViewById
    protected View k;
    @ViewById
    protected TextView l;
    @ViewById
    protected TextView m;
    @ViewById
    protected MediaListView n;
    protected MagicAdapter o;
    protected String p = "";
    protected String q = "";
    protected String r = "";
    protected Boolean s = false;
    protected Boolean t = true;
    private WeakListener.TextWatcher u;
    private WeakListener.OnEditorActionListener z;

    public OnboardingSongsSearchFragment() {
        this.u = new WeakListener.TextWatcher((Object)this.i, new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                if (!OnboardingSongsSearchFragment.this.isAdded()) {
                    return;
                }
                View view = OnboardingSongsSearchFragment.this.j;
                n = charSequence.length() == 0 ? 8 : 0;
                view.setVisibility(n);
                if (charSequence.length() == 0) {
                    OnboardingSongsSearchFragment.this.a(Analytics.b, OnboardingSongsSearchFragment.this.q, OnboardingSongsSearchFragment.this.H());
                }
                if (OnboardingSongsSearchFragment.this.b != null) {
                    OnboardingSongsSearchFragment.this.b.a(0);
                }
                OnboardingSongsSearchFragment.this.i.setHintTextColor(OnboardingSongsSearchFragment.this.getResources().getColor(2131689680));
            }
        });
        this.z = new WeakListener.OnEditorActionListener((Object)this.i, new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView object, int n, KeyEvent keyEvent) {
                object = com.smule.android.network.managers.SearchManager.a(object.getText().toString());
                Log.b(OnboardingSongsSearchFragment.g, "onboarding search submit from keyboard: " + (String)object);
                if (object.isEmpty()) {
                    return true;
                }
                OnboardingSongsSearchFragment.this.j.setVisibility(8);
                OnboardingSongsSearchFragment.this.i.clearFocus();
                OnboardingSongsSearchFragment.this.I();
                OnboardingSongsSearchFragment.this.a(Analytics.d, OnboardingSongsSearchFragment.this.q, OnboardingSongsSearchFragment.this.H());
                OnboardingSongsSearchFragment.this.e((String)object);
                return true;
            }
        });
        this.A = new WeakListener.OnTouchListener(this.n, new View.OnTouchListener(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (OnboardingSongsSearchFragment.this.n == null || !((InputMethodManager)OnboardingSongsSearchFragment.this.getActivity().getSystemService("input_method")).isAcceptingText()) {
                    return false;
                }
                OnboardingSongsSearchFragment.this.a(Analytics.c, OnboardingSongsSearchFragment.this.q, OnboardingSongsSearchFragment.this.H());
                OnboardingSongsSearchFragment.this.I();
                OnboardingSongsSearchFragment.this.i.clearFocus();
                return false;
            }
        });
    }

    private void a(View object, int n) {
        object = (ListingListItem)object;
        Object object2 = (CompositionLite)this.h.a(n);
        if (object2 == null) {
            Log.e(g, "bindSongItemView: Unable to bind, CompositionLite is null");
            return;
        }
        if ((object2 = SongbookEntryUtils.a((CompositionLite)object2)) == null) {
            Log.e(g, "bindSongItemView: Unable to bind, SongbookEntry is null");
            return;
        }
        object.a((SongbookEntry)object2, false);
        object.setAlbumArtClickListener(new View.OnClickListener((SongbookEntry)object2, (ListingListItem)object, n){
            final /* synthetic */ SongbookEntry a;
            final /* synthetic */ ListingListItem b;
            final /* synthetic */ int c;
            {
                this.a = songbookEntry;
                this.b = listingListItem;
                this.c = n;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                int n;
                boolean bl = true;
                if (this.a instanceof ArrangementVersionLiteEntry) {
                    if (this.b.t()) {
                        return;
                    }
                    this.b.setAlbumArtClickedState(true);
                    if (OnboardingSongsSearchFragment.this.w == null) {
                        OnboardingSongsSearchFragment.this.w = new ConcurrentHashMap();
                    }
                    if (!OnboardingSongsSearchFragment.this.w.containsKey(this.a.c())) {
                        n = ((ArrangementVersionLiteEntry)this.a).a().removalCode;
                        if (n < 40 || n > 49) {
                            bl = false;
                        }
                        OnboardingSongsSearchFragment.this.w.put(this.a.c(), bl);
                        OnboardingSongsSearchFragment.this.a(bl, (ArrangementVersionLiteEntry)this.a, this.b);
                    } else {
                        OnboardingSongsSearchFragment.this.a((Boolean)OnboardingSongsSearchFragment.this.w.get(this.a.c()), (ArrangementVersionLiteEntry)this.a, this.b);
                    }
                }
                n = OnboardingSongsSearchFragment.this.h.k();
                com.smule.android.logging.Analytics.a(SongbookEntryUtils.d((SongbookEntry)this.a), this.c, SongbookEntryUtils.e((SongbookEntry)this.a), null, n, this.c);
            }
        });
        object.setOnClickListener(new View.OnClickListener((SongbookEntry)object2, n){
            final /* synthetic */ SongbookEntry a;
            final /* synthetic */ int b;
            {
                this.a = songbookEntry;
                this.b = n;
            }

            public void onClick(View view) {
                SingAnalytics.a((SongbookEntry)this.a, (String)null, (Number)this.b, Analytics.c);
                OnboardingSongsSearchFragment.this.a(Analytics.e, OnboardingSongsSearchFragment.this.q, OnboardingSongsSearchFragment.this.H());
                int n = OnboardingSongsSearchFragment.this.h.k();
                com.smule.android.logging.Analytics.a(SongbookEntryUtils.d((SongbookEntry)this.a), this.b, SongbookEntryUtils.e((SongbookEntry)this.a), null, n, this.b);
                ((OnboardingActivity)OnboardingSongsSearchFragment.this.getActivity()).t();
                ((OnboardingActivity)OnboardingSongsSearchFragment.this.getActivity()).a(this.a);
            }
        });
    }

    private void e(String string2) {
        if (!this.isAdded()) {
            Log.d(g, "executeManualSearch - fragment not added; aborting");
            return;
        }
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.e(g, "Attempting to search for empty/ null string");
            return;
        }
        this.t = false;
        this.p = com.smule.android.network.managers.SearchManager.a(string2);
        Log.b(g, "running search with term: " + this.p);
        this.o.f();
    }

    @Click
    protected void F() {
        this.s = true;
        this.i.setText((CharSequence)"");
        this.i.requestFocus();
        this.s = false;
    }

    @AfterViews
    protected void G() {
        this.h = new OnboardingSongbookSearchDataSource();
        this.o = new MagicAdapter((MagicDataSource)this.h){

            @Override
            public View a(ViewGroup viewGroup, int n) {
                return ListingListItem.a((Context)OnboardingSongsSearchFragment.this.getActivity());
            }

            @Override
            public void a(View view, int n, int n2) {
                OnboardingSongsSearchFragment.this.a(view, n);
            }
        };
        this.n.setMagicAdapter(this.o);
    }

    protected String H() {
        return com.smule.android.network.managers.SearchManager.a(this.i.getText().toString());
    }

    protected void I() {
        ((InputMethodManager)this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.i.getWindowToken(), 0);
    }

    @Click
    protected void a() {
        this.getActivity().onBackPressed();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(Analytics searchBarExitContext, String string2, String string3) {
        boolean bl = true;
        boolean bl2 = searchBarExitContext == Analytics.d || searchBarExitContext == Analytics.e;
        if (string2.equals(string3)) {
            bl = false;
        }
        if (Boolean.valueOf(bl2).booleanValue() || Boolean.valueOf(bl).booleanValue()) {
            com.smule.android.logging.Analytics.b(searchBarExitContext, string2, string3);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(String string2, int n, long l) {
        String string3 = this.t != false ? this.r : this.H();
        Analytics searchExecuteContext = this.t != false ? Analytics.b : Analytics.a;
        com.smule.android.logging.Analytics.a(Analytics.a, searchExecuteContext, n, string3, string2, l, null);
    }

    protected void c(String string2) {
        this.n.setVisibility(8);
        this.m.setVisibility(8);
        this.k.setVisibility(0);
        this.l.setText((CharSequence)this.getString(2131297050, new Object[]{string2}));
    }

    protected void d(String string2) {
        this.n.setVisibility(0);
        this.m.setVisibility(0);
        this.k.setVisibility(8);
        this.m.setText((CharSequence)Html.fromHtml((String)this.getResources().getString(2131297301, new Object[]{string2})));
    }

    public boolean d() {
        this.a(Analytics.a, this.q, this.H());
        this.a(g);
        return false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.p = com.smule.android.network.managers.SearchManager.a(this.getArguments().getString("BUNDLE_SEARCH_QUERY"));
        this.q = "";
        this.r = com.smule.android.network.managers.SearchManager.a(this.getArguments().getString("BUNDLE_SEARCH_INPUT"));
        this.t = true;
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onStart() {
        super.onStart();
        this.i.setText((CharSequence)this.p);
        this.q = this.p;
        this.i.addTextChangedListener((TextWatcher)this.u);
        this.i.setOnEditorActionListener((TextView.OnEditorActionListener)this.z);
        this.i.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange(View view, boolean bl) {
                if (OnboardingSongsSearchFragment.this.isAdded() && view == OnboardingSongsSearchFragment.this.i && bl) {
                    if (!TextUtils.isEmpty((CharSequence)OnboardingSongsSearchFragment.this.i.getText().toString())) {
                        OnboardingSongsSearchFragment.this.j.setVisibility(0);
                    }
                    OnboardingSongsSearchFragment.this.q = OnboardingSongsSearchFragment.this.H();
                }
            }
        });
        this.n.setOnTouchListener((View.OnTouchListener)this.A);
    }

    public void onStop() {
        super.onStop();
        this.i.removeTextChangedListener((TextWatcher)this.u);
        this.i.setOnEditorActionListener(null);
        this.i.setOnFocusChangeListener(null);
        this.n.setOnTouchListener((View.OnTouchListener)this.A);
    }

    private class OnboardingSongbookSearchDataSource
    extends SearchFragment.SearchDataSource<CompositionLite, MagicDataSource.OffsetPaginationTracker> {
        public OnboardingSongbookSearchDataSource() {
            super(null, (MagicDataSource.PaginationTracker)new MagicDataSource.OffsetPaginationTracker());
            this.b = new ArrayList();
        }

        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<CompositionLite, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            final String string2 = OnboardingSongsSearchFragment.this.p;
            final long l = System.currentTimeMillis();
            return com.smule.android.network.managers.SearchManager.a().a(string2, SearchManager.SearchResultType.a, offsetPaginationTracker.a(), 25, SearchManager.SearchSortOption.a, new SearchManager.SearchResponseCallback(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(SearchManager sASearchResponse) {
                    if (!OnboardingSongsSearchFragment.this.isAdded()) {
                        return;
                    }
                    if (!sASearchResponse.a()) {
                        fetchDataCallback.a();
                        return;
                    }
                    if (sASearchResponse.mSongs.isEmpty()) {
                        OnboardingSongsSearchFragment.this.c(string2);
                    } else {
                        OnboardingSongsSearchFragment.this.d(string2);
                    }
                    long l3 = System.currentTimeMillis();
                    long l2 = l;
                    OnboardingSongsSearchFragment.this.a(string2, sASearchResponse.mSongs.size(), l3 - l2);
                    fetchDataCallback.a(sASearchResponse.mSongs, new MagicDataSource.OffsetPaginationTracker(sASearchResponse.mNext));
                }
            });
        }

    }

}

