package com.smule.android.network.managers;

import com.smule.android.logging.Log;
import com.smule.android.network.api.RecommendationAPI;
import com.smule.android.network.api.RecommendationAPI$GetRecommendedCompsByLocaleRequest;
import com.smule.android.network.api.RecommendationAPI$GetRecommendedCompsBySongRequest;
import com.smule.android.network.api.RecommendationAPI$GetRecommentdedSongsBySongRequest;
import com.smule.android.network.api.RecommendationAPI$GetRecommentedSingersRequest;
import com.smule.android.network.api.RecommendationAPI$SelectRecRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.songbook.SongbookEntry.PrimaryCompType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RecommendationManager {
    protected static RecommendationManager f16986a = null;
    private static final String f16987b = RecommendationManager.class.getName();
    private RecommendationAPI f16988c = ((RecommendationAPI) MagicNetwork.a().a(RecommendationAPI.class));
    private final ReentrantLock f16989d = new ReentrantLock();
    private final ReentrantLock f16990e = new ReentrantLock();
    private List<RecTrendingSearch> f16991f;

    public interface GetRecommendedSingersCallback extends ResponseInterface<RecommendedSingersResponse> {
        void handleResponse(RecommendedSingersResponse recommendedSingersResponse);
    }

    class C35861 implements Runnable {
        final /* synthetic */ RecommedationSelectCallback f16940a;
        final /* synthetic */ String f16941b;
        final /* synthetic */ String f16942c;
        final /* synthetic */ boolean f16943d;
        final /* synthetic */ RecommendationManager f16944e;

        public void run() {
            CoreUtil.m18079a(this.f16940a, this.f16944e.m18291a(this.f16941b, this.f16942c, this.f16943d));
        }
    }

    class C35872 implements Runnable {
        final /* synthetic */ GetRecommendedSongsBySongCallback f16945a;
        final /* synthetic */ String f16946b;
        final /* synthetic */ RecommendationManager f16947c;

        public void run() {
            CoreUtil.m18079a(this.f16945a, this.f16947c.m18292a(this.f16946b));
        }
    }

    class C35894 implements Runnable {
        final /* synthetic */ GetRecommendedCompsBySongCallback f16952a;
        final /* synthetic */ String f16953b;
        final /* synthetic */ RecommendationManager f16954c;

        public void run() {
            CoreUtil.m18079a(this.f16952a, this.f16954c.m18302b(this.f16953b));
        }
    }

    class C35905 implements Runnable {
        final /* synthetic */ GetRecommendedCompsBySongCallback f16955a;
        final /* synthetic */ String f16956b;
        final /* synthetic */ RecommendationManager f16957c;

        public void run() {
            CoreUtil.m18079a(this.f16955a, this.f16957c.m18305c(this.f16956b));
        }
    }

    class C35916 implements Runnable {
        final /* synthetic */ GetRecommendedCompsBySongCallback f16958a;
        final /* synthetic */ SongbookEntry f16959b;
        final /* synthetic */ RecommendationManager f16960c;

        public void run() {
            CoreUtil.m18079a(this.f16958a, this.f16960c.m18294a(this.f16959b));
        }
    }

    class C35927 implements Runnable {
        final /* synthetic */ GetRecommendedDemographicSongsCallback f16961a;
        final /* synthetic */ RecommendationManager f16962b;

        public void run() {
            CoreUtil.m18079a(this.f16961a, this.f16962b.m18301b());
        }
    }

    public enum CacheDuration {
        NONE(0),
        SHORT(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES)),
        LONG(TimeUnit.MILLISECONDS.convert(6, TimeUnit.HOURS));
        
        public final long f16973d;

        private CacheDuration(long j) {
            this.f16973d = j;
        }
    }

    public interface GetRecommendedCompsByLocaleCallback extends ResponseInterface<RecommendedCompsResponse> {
        void handleResponse(RecommendedCompsResponse recommendedCompsResponse);
    }

    public interface GetRecommendedCompsBySongCallback extends ResponseInterface<RecommendedCompsResponse> {
        void handleResponse(RecommendedCompsResponse recommendedCompsResponse);
    }

    public interface GetRecommendedCompsCallback extends ResponseInterface<RecommendedCompsResponse> {
        void handleResponse(RecommendedCompsResponse recommendedCompsResponse);
    }

    public interface GetRecommendedDemographicSongsCallback extends ResponseInterface<RecommededSongsResponse> {
        void handleResponse(RecommededSongsResponse recommededSongsResponse);
    }

    public interface GetRecommendedSongsBySongCallback extends ResponseInterface<RecommededSongsResponse> {
        void handleResponse(RecommededSongsResponse recommededSongsResponse);
    }

    public interface GetRecommendedTrendingsCallback extends ResponseInterface<RecommendedTrendingsResponse> {
        void handleResponse(RecommendedTrendingsResponse recommendedTrendingsResponse);
    }

    public enum RecContext {
        profile,
        pickasong,
        songbook,
        feed,
        notif,
        perflist,
        purchase,
        findfriends,
        join_onboarding_ia,
        d2_push,
        arranger
    }

    public interface RecommedationSelectCallback extends ResponseInterface<RecommedationSelectResponse> {
        void handleResponse(RecommedationSelectResponse recommedationSelectResponse);
    }

    public interface RecommendedSingersCallback {
        void mo6454a(List<RecAccountIcon> list, List<RecAccountIcon> list2);
    }

    private RecommendationManager() {
    }

    public static RecommendationManager m18285a() {
        if (f16986a == null) {
            f16986a = new RecommendationManager();
        }
        return f16986a;
    }

    public RecommedationSelectResponse m18291a(String str, String str2, boolean z) {
        return RecommedationSelectResponse.a(NetworkUtils.m18104a(this.f16988c.selectRec(new RecommendationAPI$SelectRecRequest().setSelection(str).setSelectionType(str2).setTopic(Boolean.valueOf(z)))));
    }

    public RecommededSongsResponse m18292a(String str) {
        return RecommededSongsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedSongsBySong(new RecommendationAPI$GetRecommentdedSongsBySongRequest().setSongId(str))));
    }

    public RecommendedCompsResponse m18293a(RecContext recContext) {
        return m18284a(RecommendedCompsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedCompsByLocale(new RecommendationAPI$GetRecommendedCompsByLocaleRequest().setCtxt(recContext.toString())))));
    }

    public Future<?> m18296a(final CacheDuration cacheDuration, final RecContext recContext, final GetRecommendedCompsByLocaleCallback getRecommendedCompsByLocaleCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ RecommendationManager f16951d;

            public void run() {
                this.f16951d.f16990e.lock();
                try {
                    Object a;
                    long j = MagicNetwork.e().getLong("RECOMMENDATION_BY_LOCALE_CACHE_TIMESTAMP", 0);
                    String appVersion = MagicNetwork.d().getAppVersion();
                    String string = MagicNetwork.e().getString("RECOMMENDATION_BY_LOCALE_CACHE_APP_VERSION", appVersion);
                    if (j + cacheDuration.f16973d < System.currentTimeMillis() || !string.equals(appVersion)) {
                        Log.b(RecommendationManager.f16987b, "getRecommendedCompsByLocale:network");
                        a = this.f16951d.m18293a(recContext);
                        if (a != null && a.a()) {
                            MagicNetwork.e().edit().putString("RECOMMENDATION_BY_LOCALE_CACHE", a.a.j).putLong("RECOMMENDATION_BY_LOCALE_CACHE_TIMESTAMP", System.currentTimeMillis()).putString("RECOMMENDATION_BY_LOCALE_CACHE_APP_VERSION", appVersion).apply();
                        }
                    } else {
                        Log.b(RecommendationManager.f16987b, "getRecommendedCompsByLocale:cache");
                        NetworkResponse networkResponse = new NetworkResponse(MagicNetwork.e().getString("RECOMMENDATION_BY_LOCALE_CACHE", null));
                        networkResponse.a = NetworkResponse$Status.OK;
                        RecommendedCompsResponse recommendedCompsResponse = (RecommendedCompsResponse) ParsedResponse.a(networkResponse, RecommendedCompsResponse.class);
                    }
                    this.f16951d.f16990e.unlock();
                    if (a == null) {
                        a = (RecommendedCompsResponse) ParsedResponse.a(NetworkResponse.b(), RecommendedCompsResponse.class);
                    }
                    CoreUtil.m18079a(getRecommendedCompsByLocaleCallback, a);
                } catch (Throwable th) {
                    this.f16951d.f16990e.unlock();
                }
            }
        });
    }

    public List<RecCompositionLite> m18303b(RecContext recContext) {
        String string = MagicNetwork.e().getString("RECOMMENDATION_BY_LOCALE_CACHE", null);
        if (string == null) {
            return null;
        }
        NetworkResponse networkResponse = new NetworkResponse(string);
        networkResponse.a = NetworkResponse$Status.OK;
        RecommendedCompsResponse recommendedCompsResponse = (RecommendedCompsResponse) ParsedResponse.a(networkResponse, RecommendedCompsResponse.class);
        if (!this.f16990e.isLocked()) {
            m18296a(CacheDuration.SHORT, recContext, null);
        }
        return recommendedCompsResponse.mComps;
    }

    private RecommendedCompsResponse m18284a(RecommendedCompsResponse recommendedCompsResponse) {
        if (recommendedCompsResponse.a()) {
            List arrayList = new ArrayList();
            for (RecCompositionLite recCompositionLite : recommendedCompsResponse.mComps) {
                if (recCompositionLite.mComp.a()) {
                    arrayList.add(recCompositionLite.mComp.mArrangementVersionLite);
                }
            }
            ArrangementManager.a().b(arrayList);
        }
        return recommendedCompsResponse;
    }

    public RecommendedCompsResponse m18302b(String str) {
        RecommendationAPI$GetRecommendedCompsBySongRequest compType = new RecommendationAPI$GetRecommendedCompsBySongRequest().setCompType(PrimaryCompType.ARR.name());
        compType.setArrKey(str);
        return m18284a(RecommendedCompsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedCompsBySong(compType))));
    }

    public RecommendedCompsResponse m18305c(String str) {
        RecommendationAPI$GetRecommendedCompsBySongRequest compType = new RecommendationAPI$GetRecommendedCompsBySongRequest().setCompType(PrimaryCompType.f17651a.name());
        compType.setSongId(str);
        return m18284a(RecommendedCompsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedCompsBySong(compType))));
    }

    public RecommendedCompsResponse m18294a(SongbookEntry songbookEntry) {
        RecommendationAPI$GetRecommendedCompsBySongRequest compType = new RecommendationAPI$GetRecommendedCompsBySongRequest().setCompType(songbookEntry.mo6299m().name());
        if (songbookEntry.mo6299m() == PrimaryCompType.ARR) {
            compType.setArrKey(songbookEntry.mo6289c());
        } else {
            compType.setSongId(songbookEntry.mo6289c());
        }
        return m18284a(RecommendedCompsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedCompsBySong(compType))));
    }

    public RecommededSongsResponse m18301b() {
        return RecommededSongsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedDemographicSongs(new SnpRequest())));
    }

    public RecommendedCompsResponse m18304c() {
        return RecommendedCompsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedComps(new SnpRequest())));
    }

    public Future<?> m18295a(final CacheDuration cacheDuration, final GetRecommendedCompsCallback getRecommendedCompsCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ RecommendationManager f16965c;

            public void run() {
                this.f16965c.f16989d.lock();
                try {
                    Object c;
                    long j = MagicNetwork.e().getLong("RECOMMENDATION_CACHE_TIMESTAMP", 0);
                    String appVersion = MagicNetwork.d().getAppVersion();
                    String string = MagicNetwork.e().getString("RECOMMENDATION_CACHE_APP_VERSION", appVersion);
                    if (j + cacheDuration.f16973d < System.currentTimeMillis() || !string.equals(appVersion)) {
                        Log.b(RecommendationManager.f16987b, "getRecommendedComps:network");
                        c = this.f16965c.m18304c();
                        if (c != null && c.a()) {
                            MagicNetwork.e().edit().putString("RECOMMENDATION_CACHE", c.a.j).putLong("RECOMMENDATION_CACHE_TIMESTAMP", System.currentTimeMillis()).putString("RECOMMENDATION_CACHE_APP_VERSION", appVersion).apply();
                        }
                    } else {
                        Log.b(RecommendationManager.f16987b, "getRecommendedComps:cache");
                        NetworkResponse networkResponse = new NetworkResponse(MagicNetwork.e().getString("RECOMMENDATION_CACHE", null));
                        networkResponse.a = NetworkResponse$Status.OK;
                        RecommendedCompsResponse recommendedCompsResponse = (RecommendedCompsResponse) ParsedResponse.a(networkResponse, RecommendedCompsResponse.class);
                    }
                    this.f16965c.f16989d.unlock();
                    if (c == null) {
                        c = (RecommendedCompsResponse) ParsedResponse.a(NetworkResponse.b(), RecommendedCompsResponse.class);
                    }
                    CoreUtil.m18079a(getRecommendedCompsCallback, c);
                } catch (Throwable th) {
                    this.f16965c.f16989d.unlock();
                }
            }
        });
    }

    public List<RecCompositionLite> m18307d() {
        String string = MagicNetwork.e().getString("RECOMMENDATION_CACHE", null);
        if (string == null) {
            return null;
        }
        NetworkResponse networkResponse = new NetworkResponse(string);
        networkResponse.a = NetworkResponse$Status.OK;
        RecommendedCompsResponse recommendedCompsResponse = (RecommendedCompsResponse) ParsedResponse.a(networkResponse, RecommendedCompsResponse.class);
        if (!this.f16989d.isLocked()) {
            m18295a(CacheDuration.SHORT, null);
        }
        return recommendedCompsResponse.mComps;
    }

    public RecommendedSingersResponse m18306d(String str) {
        return RecommendedSingersResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedSingers(new RecommendationAPI$GetRecommentedSingersRequest().setType(str))));
    }

    public Future<?> m18298a(final String str, final GetRecommendedSingersCallback getRecommendedSingersCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ RecommendationManager f16968c;

            public void run() {
                CoreUtil.m18079a(getRecommendedSingersCallback, this.f16968c.m18306d(str));
            }
        });
    }

    public void m18299a(final RecommendedSingersCallback recommendedSingersCallback) {
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ RecommendationManager f16935b;
            private List<RecAccountIcon> f16936c = null;
            private List<RecAccountIcon> f16937d = null;

            class C35841 implements GetRecommendedSingersCallback {
                final /* synthetic */ AnonymousClass10 f16932a;

                C35841(AnonymousClass10 anonymousClass10) {
                    this.f16932a = anonymousClass10;
                }

                public void handleResponse(RecommendedSingersResponse recommendedSingersResponse) {
                    this.f16932a.f16937d = new ArrayList();
                    if (recommendedSingersResponse.a()) {
                        for (RecAccountIcon recAccountIcon : recommendedSingersResponse.mRecAccountIcons) {
                            recAccountIcon.mRecommendationType = "NEW";
                            this.f16932a.f16937d.add(recAccountIcon);
                        }
                        if (this.f16932a.f16936c != null) {
                            recommendedSingersCallback.mo6454a(this.f16932a.f16937d, this.f16932a.f16936c);
                        }
                    }
                }
            }

            class C35852 implements GetRecommendedSingersCallback {
                final /* synthetic */ AnonymousClass10 f16933a;

                C35852(AnonymousClass10 anonymousClass10) {
                    this.f16933a = anonymousClass10;
                }

                public void handleResponse(RecommendedSingersResponse recommendedSingersResponse) {
                    this.f16933a.f16936c = new ArrayList();
                    if (recommendedSingersResponse.a()) {
                        for (RecAccountIcon recAccountIcon : recommendedSingersResponse.mRecAccountIcons) {
                            recAccountIcon.mRecommendationType = "SUGGESTED";
                            this.f16933a.f16936c.add(recAccountIcon);
                        }
                        if (this.f16933a.f16937d != null) {
                            recommendedSingersCallback.mo6454a(this.f16933a.f16937d, this.f16933a.f16936c);
                        }
                    }
                }
            }

            public void run() {
                RecommendationManager.m18285a().m18298a("NEW", new C35841(this));
                RecommendationManager.m18285a().m18298a("SUGGESTED", new C35852(this));
            }
        });
    }

    private RecommendedTrendingsResponse m18290g() {
        return RecommendedTrendingsResponse.a(NetworkUtils.m18104a(this.f16988c.getRecommendedTrendings(new SnpRequest())));
    }

    public Future<?> m18297a(final GetRecommendedTrendingsCallback getRecommendedTrendingsCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ RecommendationManager f16939b;

            public void run() {
                CoreUtil.m18079a(getRecommendedTrendingsCallback, this.f16939b.m18290g());
            }
        });
    }

    public List<RecTrendingSearch> m18308e() {
        return this.f16991f;
    }

    public void m18300a(List<RecTrendingSearch> list) {
        this.f16991f = list;
    }
}
