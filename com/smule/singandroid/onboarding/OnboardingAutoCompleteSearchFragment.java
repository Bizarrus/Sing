/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Handler
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.songbook_search.SearchBaseFragment
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.QuickReturnListViewMenuSyncer
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.models.SAOption;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.onboarding.OnboardingActivity;
import com.smule.singandroid.songbook_search.SearchBaseFragment;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingAutoCompleteSearchFragment
extends SearchBaseFragment {
    public static final String g = OnboardingAutoCompleteSearchFragment.class.getName();
    public static final Integer h = 5;
    private WeakListener.TextWatcher A;
    private WeakListener.OnEditorActionListener B;
    private Runnable C;
    @ViewById
    protected EditText i;
    @InstanceState
    protected int j;
    @ViewById
    protected View k;
    @ViewById
    protected MagicListView l;
    protected MagicAdapter m;
    protected OnboardingAutoCompleteDataSource n;
    protected Boolean o = false;
    protected Boolean p = false;
    protected String q = "";
    protected String r = "";
    @ViewById
    protected View s;
    protected int t = 0;
    protected long u = 0;
    private Handler z = new Handler();

    public OnboardingAutoCompleteSearchFragment() {
        this.A = new WeakListener.TextWatcher((Object)this.i, new TextWatcher(){

            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                block5 : {
                    block4 : {
                        if (!OnboardingAutoCompleteSearchFragment.this.isAdded()) break block4;
                        View view = OnboardingAutoCompleteSearchFragment.this.k;
                        n = charSequence.length() == 0 ? 8 : 0;
                        view.setVisibility(n);
                        if (OnboardingAutoCompleteSearchFragment.this.b != null) {
                            OnboardingAutoCompleteSearchFragment.this.b.a(0);
                        }
                        OnboardingAutoCompleteSearchFragment.this.i.setHintTextColor(OnboardingAutoCompleteSearchFragment.this.getResources().getColor(2131689680));
                        OnboardingAutoCompleteSearchFragment.this.z.postDelayed(OnboardingAutoCompleteSearchFragment.this.C, (long)OnboardingAutoCompleteSearchFragment.this.j);
                        if (com.smule.android.network.managers.SearchManager.a(charSequence.toString()).isEmpty() && !OnboardingAutoCompleteSearchFragment.this.p.booleanValue()) break block5;
                    }
                    return;
                }
                OnboardingAutoCompleteSearchFragment.this.a(Analytics.b, OnboardingAutoCompleteSearchFragment.this.r, "");
            }
        });
        this.B = new WeakListener.OnEditorActionListener((Object)this.i, new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView object, int n, KeyEvent keyEvent) {
                object = com.smule.android.network.managers.SearchManager.a(object.getText().toString());
                Log.b(OnboardingAutoCompleteSearchFragment.g, "onboarding search submit from keyboard: '" + (String)object + "'");
                if (object.isEmpty()) {
                    return true;
                }
                OnboardingAutoCompleteSearchFragment.this.H();
                OnboardingAutoCompleteSearchFragment.this.a(Analytics.d, OnboardingAutoCompleteSearchFragment.this.r, OnboardingAutoCompleteSearchFragment.this.q);
                OnboardingAutoCompleteSearchFragment.this.c((String)object);
                return true;
            }
        });
        this.C = new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (!OnboardingAutoCompleteSearchFragment.this.isAdded()) {
                    Log.d(OnboardingAutoCompleteSearchFragment.g, "mExecuteSearchRunnable - fragment not added; aborting");
                    return;
                } else {
                    String string2 = com.smule.android.network.managers.SearchManager.a(OnboardingAutoCompleteSearchFragment.this.i.getText().toString());
                    Log.b(OnboardingAutoCompleteSearchFragment.g, "running auto-complete search with term: " + string2);
                    boolean bl = string2.length() == 0;
                    Boolean bl2 = bl;
                    OnboardingAutoCompleteSearchFragment.this.a(bl2);
                    if (bl2.booleanValue()) return;
                    {
                        OnboardingAutoCompleteSearchFragment.this.o = true;
                        OnboardingAutoCompleteSearchFragment.this.q = string2;
                        OnboardingAutoCompleteSearchFragment.this.m.f();
                        return;
                    }
                }
            }
        };
    }

    private void H() {
        this.z.removeCallbacks(this.C);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Boolean bl) {
        int n = 0;
        MagicListView magicListView = this.l;
        int n2 = bl != false ? 8 : 0;
        magicListView.setVisibility(n2);
        magicListView = this.s;
        n2 = bl != false ? n : 8;
        magicListView.setVisibility(n2);
    }

    private void c(String string2) {
        String string3 = com.smule.android.network.managers.SearchManager.a(this.i.getText().toString());
        string2 = com.smule.android.network.managers.SearchManager.a(string2);
        ((OnboardingActivity)this.getActivity()).a(string3, string2);
    }

    @Click
    protected void F() {
        this.a(Analytics.a, this.r, this.q);
        this.getActivity().onBackPressed();
    }

    @AfterViews
    protected void G() {
        this.n = new OnboardingAutoCompleteDataSource();
        this.m = new MagicAdapter(this.n){

            @Override
            public View a(ViewGroup viewGroup, int n) {
                viewGroup = LayoutInflater.from((Context)OnboardingAutoCompleteSearchFragment.this.getActivity()).inflate(2130903083, (ViewGroup)OnboardingAutoCompleteSearchFragment.this.l, false);
                ((ImageView)viewGroup.findViewById(2131755294)).setVisibility(8);
                ((ImageView)viewGroup.findViewById(2131755293)).setVisibility(8);
                return viewGroup;
            }

            @Override
            public void a(View view, final int n, int n2) {
                final SAOption sAOption = (SAOption)this.a(n);
                ((TextView)view.findViewById(2131755292)).setText((CharSequence)sAOption.text);
                view.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        OnboardingAutoCompleteSearchFragment.this.q = sAOption.text;
                        OnboardingAutoCompleteSearchFragment.this.a(Analytics.e, OnboardingAutoCompleteSearchFragment.this.r, OnboardingAutoCompleteSearchFragment.this.q);
                        OnboardingAutoCompleteSearchFragment.this.a(n, sAOption.text);
                        OnboardingAutoCompleteSearchFragment.this.c(sAOption.text);
                    }
                });
            }

        };
        this.l.setMagicAdapter(this.m);
        this.i.requestFocus();
        MiscUtils.a((Activity)this.getActivity(), (EditText)this.i);
    }

    @Click
    protected void a() {
        this.p = true;
        this.i.setText((CharSequence)"");
        this.i.requestFocus();
        this.p = false;
        this.a(Analytics.b, this.r, this.q);
    }

    protected void a(int n, String string2) {
        String string3 = com.smule.android.network.managers.SearchManager.a(this.i.getText().toString());
        com.smule.android.logging.Analytics.a(Analytics.l, this.t, n, string3, this.u, string2, null, null, null, null, null);
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
            this.r = string3;
        }
    }

    public boolean d() {
        this.a(g);
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.j = new SingServerValues().I();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onStart() {
        super.onStart();
        this.i.addTextChangedListener((TextWatcher)this.A);
        this.i.setOnEditorActionListener((TextView.OnEditorActionListener)this.B);
        boolean bl = this.q.length() == 0;
        this.a(bl);
    }

    public void onStop() {
        super.onStop();
        this.i.removeTextChangedListener((TextWatcher)this.A);
        this.i.setOnEditorActionListener(null);
    }

    private class OnboardingAutoCompleteDataSource
    extends MagicDataSource<SAOption, MagicDataSource.OffsetPaginationTracker> {
        protected ArrayList<SAOption> a;
        final long b;

        public OnboardingAutoCompleteDataSource() {
            super(new MagicDataSource.OffsetPaginationTracker());
            this.b = System.currentTimeMillis();
            this.a = new ArrayList();
        }

        @Override
        public Future<?> a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<SAOption, MagicDataSource.OffsetPaginationTracker> fetchDataCallback) {
            if (!OnboardingAutoCompleteSearchFragment.this.o.booleanValue()) {
                fetchDataCallback.a(new ArrayList(), new MagicDataSource.OffsetPaginationTracker(-1));
                return null;
            }
            return com.smule.android.network.managers.SearchManager.a().a(OnboardingAutoCompleteSearchFragment.this.q, (int)OnboardingAutoCompleteSearchFragment.h, new SearchManager.SearchAutocompleteResponseCallback(){

                @Override
                public void handleResponse(SearchManager sASearchAutocompleteResponse) {
                    if (!sASearchAutocompleteResponse.a()) {
                        fetchDataCallback.a();
                        return;
                    }
                    OnboardingAutoCompleteSearchFragment.this.t = sASearchAutocompleteResponse.mOptions.size();
                    OnboardingAutoCompleteSearchFragment.this.u = System.currentTimeMillis() - OnboardingAutoCompleteDataSource.this.b;
                    fetchDataCallback.a(sASearchAutocompleteResponse.mOptions, new MagicDataSource.OffsetPaginationTracker(-1));
                }
            });
        }

    }

}

