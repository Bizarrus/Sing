package com.smule.android.network.managers;

import com.smule.android.network.api.SearchAPI;
import com.smule.android.network.api.SearchAPI$GetUsersForTermRequest;
import com.smule.android.network.api.SearchAPI$SASearchRequest;
import com.smule.android.network.api.SearchAPI$SATermSearchRequest;
import com.smule.android.network.api.SearchAPI$SearchSongbookAutoCompleteRequest;
import com.smule.android.network.api.SearchAPI$SearchSongbookRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.models.ContestData$Reward;
import java.util.concurrent.Future;

public class SearchManager {
    private static final String f17058a = SearchManager.class.getName();
    private static SearchManager f17059b = null;
    private SearchAPI f17060c = ((SearchAPI) MagicNetwork.a().a(SearchAPI.class));

    class C36001 implements Runnable {
        final /* synthetic */ SearchSongbookResultResponseCallback f17016a;
        final /* synthetic */ String f17017b;
        final /* synthetic */ Integer f17018c;
        final /* synthetic */ Integer f17019d;
        final /* synthetic */ boolean f17020e;
        final /* synthetic */ SearchManager f17021f;

        public void run() {
            CoreUtil.m18079a(this.f17016a, this.f17021f.m18336a(this.f17017b, this.f17018c, this.f17019d, this.f17020e));
        }
    }

    class C36012 implements Runnable {
        final /* synthetic */ SearchSongbookAutoCompleteResultResponseCallback f17022a;
        final /* synthetic */ String f17023b;
        final /* synthetic */ boolean f17024c;
        final /* synthetic */ SearchManager f17025d;

        public void run() {
            CoreUtil.m18079a(this.f17022a, this.f17025d.m18335a(this.f17023b, this.f17024c));
        }
    }

    public interface AccountSearchResultResponseCallback extends ResponseInterface<AccountSearchResponse> {
        void handleResponse(AccountSearchResponse accountSearchResponse);
    }

    public interface SearchAutocompleteResponseCallback extends ResponseInterface<SASearchAutocompleteResponse> {
        void handleResponse(SASearchAutocompleteResponse sASearchAutocompleteResponse);
    }

    public interface SearchGlobalResponseCallback extends ResponseInterface<SASearchGlobalResponse> {
        void handleResponse(SASearchGlobalResponse sASearchGlobalResponse);
    }

    public interface SearchInstantResponseCallback extends ResponseInterface<SASearchInstantResponse> {
        void handleResponse(SASearchInstantResponse sASearchInstantResponse);
    }

    public interface SearchResponseCallback extends ResponseInterface<SASearchResponse> {
        void handleResponse(SASearchResponse sASearchResponse);
    }

    public enum SearchResultType {
        f17047a(ContestData$Reward.TYPE_SONG),
        ACCOUNT("ACCOUNT"),
        RECORDING("RECORDING"),
        ACTIVESEED("ACTIVESEED");
        
        private String f17052e;

        private SearchResultType(String str) {
            this.f17052e = str;
        }

        public String m18329a() {
            return this.f17052e;
        }
    }

    public interface SearchSongbookAutoCompleteResultResponseCallback extends ResponseInterface<SearchSongbookAutoCompleteResultResponse> {
        void handleResponse(SearchSongbookAutoCompleteResultResponse searchSongbookAutoCompleteResultResponse);
    }

    public interface SearchSongbookResultResponseCallback extends ResponseInterface<SearchSongbookResultResponse> {
        void handleResponse(SearchSongbookResultResponse searchSongbookResultResponse);
    }

    public enum SearchSortOption {
        POPULAR("POPULAR"),
        RECENT("RECENT"),
        EXPIRE("EXPIRE");
        
        private String f17057d;

        private SearchSortOption(String str) {
            this.f17057d = str;
        }

        public String m18330a() {
            return this.f17057d;
        }
    }

    private SearchManager() {
    }

    public static SearchManager m18331a() {
        if (f17059b == null) {
            f17059b = new SearchManager();
        }
        return f17059b;
    }

    public SearchSongbookResultResponse m18336a(String str, Integer num, Integer num2, boolean z) {
        return SearchSongbookResultResponse.a(NetworkUtils.m18104a(this.f17060c.searchSongbook(new SearchAPI$SearchSongbookRequest().setTerm(str).setOffset(num).setLimit(num2).setInclArr(Boolean.valueOf(z)))));
    }

    public SearchSongbookAutoCompleteResultResponse m18335a(String str, boolean z) {
        return SearchSongbookAutoCompleteResultResponse.a(NetworkUtils.m18104a(this.f17060c.searchSongbookAutoComplete(new SearchAPI$SearchSongbookAutoCompleteRequest().setTerm(str).setInclArr(z))));
    }

    public SASearchAutocompleteResponse m18333a(String str) {
        return SASearchAutocompleteResponse.a(NetworkUtils.m18104a(this.f17060c.searchAutoComplete(new SearchAPI$SATermSearchRequest().setTerm(str))));
    }

    public Future<?> m18338a(final String str, final SearchAutocompleteResponseCallback searchAutocompleteResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SearchManager f17028c;

            public void run() {
                CoreUtil.m18079a(searchAutocompleteResponseCallback, this.f17028c.m18333a(str));
            }
        });
    }

    public SASearchInstantResponse m18342b(String str) {
        return SASearchInstantResponse.a(NetworkUtils.m18104a(this.f17060c.searchInstant(new SearchAPI$SATermSearchRequest().setTerm(str))));
    }

    public Future<?> m18340a(final String str, final SearchInstantResponseCallback searchInstantResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SearchManager f17031c;

            public void run() {
                CoreUtil.m18079a(searchInstantResponseCallback, this.f17031c.m18342b(str));
            }
        });
    }

    public SASearchGlobalResponse m18343c(String str) {
        return SASearchGlobalResponse.a(NetworkUtils.m18104a(this.f17060c.searchGlobal(new SearchAPI$SATermSearchRequest().setTerm(str))));
    }

    public Future<?> m18339a(final String str, final SearchGlobalResponseCallback searchGlobalResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SearchManager f17034c;

            public void run() {
                CoreUtil.m18079a(searchGlobalResponseCallback, this.f17034c.m18343c(str));
            }
        });
    }

    public SASearchResponse m18334a(String str, SearchResultType searchResultType, int i, int i2, SearchSortOption searchSortOption) {
        return SASearchResponse.a(NetworkUtils.m18104a(this.f17060c.search(new SearchAPI$SASearchRequest().setTerm(str).setResultType(searchResultType.m18329a()).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(i2)).setSort(searchSortOption.m18330a()))));
    }

    public Future<?> m18341a(String str, SearchResultType searchResultType, int i, int i2, SearchSortOption searchSortOption, SearchResponseCallback searchResponseCallback) {
        final SearchResponseCallback searchResponseCallback2 = searchResponseCallback;
        final String str2 = str;
        final SearchResultType searchResultType2 = searchResultType;
        final int i3 = i;
        final int i4 = i2;
        final SearchSortOption searchSortOption2 = searchSortOption;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SearchManager f17041g;

            public void run() {
                CoreUtil.m18079a(searchResponseCallback2, this.f17041g.m18334a(str2, searchResultType2, i3, i4, searchSortOption2));
            }
        });
    }

    public AccountSearchResponse m18332a(String str, int i, int i2) {
        return AccountSearchResponse.a(NetworkUtils.m18104a(this.f17060c.getUsersForTerm(new SearchAPI$GetUsersForTermRequest().setTerm(str).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(i2)))));
    }

    public Future<?> m18337a(String str, int i, int i2, AccountSearchResultResponseCallback accountSearchResultResponseCallback) {
        final AccountSearchResultResponseCallback accountSearchResultResponseCallback2 = accountSearchResultResponseCallback;
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ SearchManager f17046e;

            public void run() {
                CoreUtil.m18079a(accountSearchResultResponseCallback2, this.f17046e.m18332a(str2, i3, i4));
            }
        });
    }
}
