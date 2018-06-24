/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.SearchAPI;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SAOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import retrofit2.Call;

public class SearchManager {
    private static final String a = SearchManager.class.getName();
    private static SearchManager b = null;
    private com.smule.android.network.api.SearchAPI c = MagicNetwork.a().a(com.smule.android.network.api.SearchAPI.class);

    private SearchManager() {
    }

    public static SearchManager a() {
        if (b == null) {
            b = new SearchManager();
        }
        return b;
    }

    public static String a(String string2) {
        if (string2 == null) {
            return string2;
        }
        return string2.trim();
    }

    public  a(String string2, int n, int n2) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.getUsersForTerm(new SnpRequest(){
            public Integer limit;
            public Integer offset;
            public String term;

            public SearchAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public SearchAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public SearchAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setOffset(n).setLimit(n2))));
    }

    public  a(String string2, int n) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.searchAutoComplete(new SnpRequest(){
            public Integer limit;
            public String term;

            public SearchAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public SearchAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setLimit(n))));
    }

    public  a(String string2, SearchResultType searchResultType, int n, int n2, SearchSortOption searchSortOption) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.search(new SnpRequest(){
            public Integer limit;
            public Integer offset;
            public String resultType;
            public String sort;
            public String term;

            public SearchAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }

            public SearchAPI setOffset(Integer n) {
                this.offset = n;
                return this;
            }

            public SearchAPI setResultType(String string2) {
                this.resultType = string2;
                return this;
            }

            public SearchAPI setSort(String string2) {
                this.sort = string2;
                return this;
            }

            public SearchAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setResultType(searchResultType.a()).setOffset(n).setLimit(n2).setSort(searchSortOption.a()))));
    }

    public  a(String string2, boolean bl) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.searchSongbookAutoComplete(new SnpRequest(){
            public boolean inclArr;
            public String term;

            public SearchAPI setInclArr(boolean bl) {
                this.inclArr = bl;
                return this;
            }

            public SearchAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setInclArr(bl))));
    }

    public  a(String string2, Integer n, Integer n2, boolean bl) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.searchSongbook(new SnpRequest(){
            public Boolean inclArr;
            public Integer limit = 20;
            public Integer offset = 0;
            public String term;

            public SearchAPI setInclArr(Boolean bl) {
                this.inclArr = bl;
                return this;
            }

            public SearchAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public SearchAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }

            public SearchAPI setTerm(String string2) {
                this.term = string2;
                return this;
            }
        }.setTerm(string2).setOffset(n).setLimit(n2).setInclArr(bl))));
    }

    public Future<?> a(final String string2, final int n, final int n2, final AccountSearchResultResponseCallback accountSearchResultResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(accountSearchResultResponseCallback, SearchManager.this.a(string2, n, n2));
            }
        });
    }

    public Future<?> a(final String string2, final int n, final SearchAutocompleteResponseCallback searchAutocompleteResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(searchAutocompleteResponseCallback, SearchManager.this.a(string2, n));
            }
        });
    }

    public Future<?> a(final String string2, final SearchGlobalResponseCallback searchGlobalResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(searchGlobalResponseCallback, SearchManager.this.b(string2));
            }
        });
    }

    public Future<?> a(final String string2, final SearchResultType searchResultType, final int n, final int n2, final SearchSortOption searchSortOption, final SearchResponseCallback searchResponseCallback) {
        return MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                CoreUtil.a(searchResponseCallback, SearchManager.this.a(string2, searchResultType, n, n2, searchSortOption));
            }
        });
    }

    public  b(String string2) {
        if (TextUtils.isEmpty((CharSequence)(string2 = SearchManager.a(string2)))) {
            return .a(null);
        }
        return .a(NetworkUtils.a(this.c.searchGlobal(new .setTerm(string2))));
    }

    public static interface AccountSearchResultResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SearchAutocompleteResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SearchGlobalResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SearchResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static enum SearchResultType {
        a("SONG"),
        b("ACCOUNT"),
        c("RECORDING"),
        d("ACTIVESEED");
        
        private String e;

        private SearchResultType(String string3) {
            this.e = string3;
        }

        public String a() {
            return this.e;
        }
    }

    public static interface SearchSongbookAutoCompleteResultResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static interface SearchSongbookResultResponseCallback
    extends ResponseInterface<> {
        @Override
        public void handleResponse( var1);
    }

    public static enum SearchSortOption {
        a("POPULAR"),
        b("RECENT"),
        c("EXPIRE");
        
        private String d;

        private SearchSortOption(String string3) {
            this.d = string3;
        }

        public String a() {
            return this.d;
        }
    }

}

