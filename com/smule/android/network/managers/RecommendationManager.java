/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  com.bluelinelabs.logansquare.annotation.JsonField
 *  com.bluelinelabs.logansquare.annotation.JsonObject
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.RecommendationAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.CursorModel;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import retrofit2.Call;

public class RecommendationManager {
    protected static RecommendationManager a;
    private static final String b;
    private com.smule.android.network.api.RecommendationAPI c = MagicNetwork.a().a(com.smule.android.network.api.RecommendationAPI.class);
    private final ReentrantLock d = new ReentrantLock();
    private final ReentrantLock e = new ReentrantLock();
    private List<RecommendedTrendingsResponse.RecTrendingSearch> f;

    static {
        b = RecommendationManager.class.getName();
        a = null;
    }

    private RecommendationManager() {
    }

    private RecommendedCompsResponse a(RecommendedCompsResponse recommendedCompsResponse) {
        if (recommendedCompsResponse.a()) {
            ArrayList<ArrangementVersionLite> arrayList = new ArrayList<ArrangementVersionLite>();
            for (RecommendedCompsResponse.RecCompositionLite recCompositionLite : recommendedCompsResponse.mComps) {
                if (!recCompositionLite.mComp.a()) continue;
                arrayList.add(recCompositionLite.mComp.mArrangementVersionLite);
            }
            ArrangementManager.a().b(arrayList);
        }
        return recommendedCompsResponse;
    }

    public static RecommendationManager a() {
        if (a == null) {
            a = new RecommendationManager();
        }
        return a;
    }

    static /* synthetic */ ReentrantLock a(RecommendationManager recommendationManager) {
        return recommendationManager.e;
    }

    static /* synthetic */ ReentrantLock b(RecommendationManager recommendationManager) {
        return recommendationManager.d;
    }

    static /* synthetic */ RecommendedTrendingsResponse c(RecommendationManager recommendationManager) {
        return recommendationManager.f();
    }

    static /* synthetic */ String e() {
        return b;
    }

    private RecommendedTrendingsResponse f() {
        return RecommendedTrendingsResponse.a(NetworkUtils.a(this.c.getRecommendedTrendings(new SnpRequest())));
    }

    public RecommedationSelectResponse a(String string2, String string3, boolean bl) {
        return RecommedationSelectResponse.a(NetworkUtils.a(this.c.selectRec(new SnpRequest(){
            public String selection;
            public String selectionType;
            public Boolean topic;

            public RecommendationAPI setSelection(String string2) {
                this.selection = string2;
                return this;
            }

            public RecommendationAPI setSelectionType(String string2) {
                this.selectionType = string2;
                return this;
            }

            public RecommendationAPI setTopic(Boolean bl) {
                this.topic = bl;
                return this;
            }
        }.setSelection(string2).setSelectionType(string3).setTopic(bl))));
    }

    @Deprecated
    public RecommededSongsResponse a(String string2) {
        return RecommededSongsResponse.a(NetworkUtils.a(this.c.getRecommendedSongsBySong(new SnpRequest(){
            public String songId;

            public RecommendationAPI setSongId(String string2) {
                this.songId = string2;
                return this;
            }
        }.setSongId(string2))));
    }

    public RecommendedCompsResponse a( object) {
        object = new SnpRequest(){
            public String ctxt;
            public Integer limit = 25;
            public Integer offset = 0;

            public RecommendationAPI setCtxt(String string2) {
                this.ctxt = string2;
                return this;
            }

            public RecommendationAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public RecommendationAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }
        }.setCtxt(object.toString());
        return this.a(RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedCompsByLocale((Object)object))));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public RecommendedCompsResponse a(com.smule.android.songbook.SongbookEntry songbookEntry) {
        RecommendationAPI getRecommendedCompsBySongRequest = new SnpRequest(){
            public String arrKey;
            public String compType;
            public String songId;

            public RecommendationAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }

            public RecommendationAPI setCompType(String string2) {
                this.compType = string2;
                return this;
            }

            public RecommendationAPI setSongId(String string2) {
                this.songId = string2;
                return this;
            }
        }.setCompType(songbookEntry.n().name());
        if (songbookEntry.n() == SongbookEntry.b) {
            getRecommendedCompsBySongRequest.setArrKey(songbookEntry.c());
            do {
                return this.a(RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedCompsBySong(getRecommendedCompsBySongRequest))));
                break;
            } while (true);
        }
        getRecommendedCompsBySongRequest.setSongId(songbookEntry.c());
        return this.a(RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedCompsBySong(getRecommendedCompsBySongRequest))));
    }

    public RecommendedSingersResponse a(String string2, Integer n) {
        return RecommendedSingersResponse.a(NetworkUtils.a(this.c.getMergedRecommendedSingers(new SnpRequest(){
            public String cursor;
            public Integer limit;

            public RecommendationAPI setCursor(String string2) {
                this.cursor = string2;
                return this;
            }

            public RecommendationAPI setLimit(Integer n) {
                this.limit = n;
                return this;
            }
        }.setCursor(string2).setLimit(n))));
    }

    public Future<?> a( getRecommendedTrendingsCallback) {
        return MagicNetwork.a(new Runnable(this, getRecommendedTrendingsCallback){
            final /* synthetic */  a;
            final /* synthetic */ RecommendationManager b;
            {
                this.b = recommendationManager;
                this.a = getRecommendedTrendingsCallback;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, RecommendationManager.c(this.b));
            }
        });
    }

    public Future<?> a(String string2,  getRecommendedSingersCallback) {
        return MagicNetwork.a(new Runnable(this, getRecommendedSingersCallback, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ RecommendationManager c;
            {
                this.c = recommendationManager;
                this.a = getRecommendedSingersCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.d(this.b));
            }
        });
    }

    public Future<?> a(String string2, Integer n,  getRecommendedSingersCallback) {
        return MagicNetwork.a(new Runnable(this, getRecommendedSingersCallback, string2, n){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ Integer c;
            final /* synthetic */ RecommendationManager d;
            {
                this.d = recommendationManager;
                this.a = getRecommendedSingersCallback;
                this.b = string2;
                this.c = n;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.d.a(this.b, this.c));
            }
        });
    }

    public void a( recommendedSingersCallback) {
        MagicNetwork.a(new Runnable(this, recommendedSingersCallback){
            final /* synthetic */  a;
            final /* synthetic */ RecommendationManager b;
            private List<RecommendedSingersResponse.RecAccountIcon> c;
            private List<RecommendedSingersResponse.RecAccountIcon> d;
            {
                this.b = recommendationManager;
                this.a = recommendedSingersCallback;
                this.c = null;
                this.d = null;
            }

            static /* synthetic */ List a( var0) {
                return var0.d;
            }

            static /* synthetic */ List a( var0, List list) {
                var0.d = list;
                return list;
            }

            static /* synthetic */ List b( var0) {
                return var0.c;
            }

            static /* synthetic */ List b( var0, List list) {
                var0.c = list;
                return list;
            }

            public void run() {
                RecommendationManager.a().a("NEW", new (this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void handleResponse(RecommendedSingersResponse object) {
                        .a(this.a, new ArrayList<E>());
                        if (object.a()) {
                            for (RecommendedSingersResponse.RecAccountIcon recAccountIcon : object.mRecAccountIcons) {
                                recAccountIcon.b = "NEW";
                                .a(this.a).add(recAccountIcon);
                            }
                            if (.b(this.a) != null) {
                                this.a.a.a(.a(this.a), .b(this.a));
                            }
                        }
                    }
                });
                RecommendationManager.a().a("SUGGESTED", new (this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void handleResponse(RecommendedSingersResponse object) {
                        .b(this.a, new ArrayList<E>());
                        if (object.a()) {
                            for (RecommendedSingersResponse.RecAccountIcon recAccountIcon : object.mRecAccountIcons) {
                                recAccountIcon.b = "SUGGESTED";
                                .b(this.a).add(recAccountIcon);
                            }
                            if (.a(this.a) != null) {
                                this.a.a.a(.a(this.a), .b(this.a));
                            }
                        }
                    }
                });
            }
        });
    }

    public void a(List<RecommendedTrendingsResponse.RecTrendingSearch> list) {
        this.f = list;
    }

    @Deprecated
    public RecommededSongsResponse b() {
        return RecommededSongsResponse.a(NetworkUtils.a(this.c.getRecommendedDemographicSongs(new SnpRequest())));
    }

    public RecommendedCompsResponse b(String string2) {
        RecommendationAPI getRecommendedCompsBySongRequest = new .setCompType(SongbookEntry.b.name());
        getRecommendedCompsBySongRequest.setArrKey(string2);
        return this.a(RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedCompsBySong(getRecommendedCompsBySongRequest))));
    }

    @Deprecated
    public RecommendedCompsResponse c() {
        return RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedComps(new SnpRequest())));
    }

    @Deprecated
    public RecommendedCompsResponse c(String string2) {
        RecommendationAPI getRecommendedCompsBySongRequest = new .setCompType(SongbookEntry.a.name());
        getRecommendedCompsBySongRequest.setSongId(string2);
        return this.a(RecommendedCompsResponse.a(NetworkUtils.a(this.c.getRecommendedCompsBySong(getRecommendedCompsBySongRequest))));
    }

    public RecommendedSingersResponse d(String string2) {
        return RecommendedSingersResponse.a(NetworkUtils.a(this.c.getRecommendedSingers(new SnpRequest(){
            public String type;

            public RecommendationAPI setType(String string2) {
                this.type = string2;
                return this;
            }
        }.setType(string2))));
    }

    public List<RecommendedTrendingsResponse.RecTrendingSearch> d() {
        return this.f;
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RecommedationSelectResponse
    extends ParsedResponse {
        static RecommedationSelectResponse a(NetworkResponse networkResponse) {
            return RecommedationSelectResponse.a(networkResponse, RecommedationSelectResponse.class);
        }

        public String toString() {
            return "RecommedationResponse{}";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RecommededSongsResponse
    extends ParsedResponse {
        @JsonProperty(value="songIds")
        public List<String> mSongs = new ArrayList<String>();

        static RecommededSongsResponse a(NetworkResponse networkResponse) {
            return RecommededSongsResponse.a(networkResponse, RecommededSongsResponse.class);
        }

        public String toString() {
            return "RecommedationResponse[songs=" + this.mSongs + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RecommendedCompsResponse
    extends ParsedResponse {
        @JsonProperty(value="recCompositionLites")
        public List<RecCompositionLite> mComps = new ArrayList<RecCompositionLite>();
        @JsonProperty(value="next")
        public Integer mNext;

        static RecommendedCompsResponse a(NetworkResponse networkResponse) {
            return RecommendedCompsResponse.a(networkResponse, RecommendedCompsResponse.class);
        }

        public List<ArrangementVersionLite> getArrangementLites() {
            ArrayList<ArrangementVersionLite> arrayList = new ArrayList<ArrangementVersionLite>();
            if (this.mComps != null) {
                for (RecCompositionLite recCompositionLite : this.mComps) {
                    if (recCompositionLite.mComp.mArrangementVersionLite == null) continue;
                    arrayList.add(recCompositionLite.mComp.mArrangementVersionLite);
                }
            }
            return arrayList;
        }

        public String toString() {
            return "RecommedationResponse[songs=" + this.mComps + "]";
        }

        public static class RecCompositionLite
        extends ParsedResponse {
            @JsonProperty(value="compositionLite")
            public CompositionLite mComp;
            @JsonProperty(value="recId")
            public String mRecId;

            public String toString() {
                return "RecCompositionLite[recId=" + this.mRecId + ",song=" + this.mComp + "]";
            }
        }

    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RecommendedSingersResponse
    extends ParsedResponse {
        @JsonProperty(value="cursor")
        public CursorModel mCursor;
        @JsonProperty(value="recAccountIcons")
        public List<RecAccountIcon> mRecAccountIcons = new ArrayList<RecAccountIcon>();

        static RecommendedSingersResponse a(NetworkResponse networkResponse) {
            return RecommendedSingersResponse.a(networkResponse, RecommendedSingersResponse.class);
        }

        public String toString() {
            return "RecommedationResponse[singers=" + this.mRecAccountIcons + "]";
        }

        @JsonIgnoreProperties(ignoreUnknown=true)
        @JsonObject
        public static class RecAccountIcon
        extends ParsedResponse
        implements Parcelable {
            public static final Parcelable.Creator<RecAccountIcon> CREATOR = new Parcelable.Creator<RecAccountIcon>(){

                public RecAccountIcon a(Parcel parcel) {
                    return new RecAccountIcon(parcel);
                }

                public RecAccountIcon[] a(int n) {
                    return new RecAccountIcon[n];
                }

                public /* synthetic */ Object createFromParcel(Parcel parcel) {
                    return this.a(parcel);
                }

                public /* synthetic */ Object[] newArray(int n) {
                    return this.a(n);
                }
            };
            @JsonField
            public String b = null;
            @JsonProperty(value="accountIcon")
            @JsonField
            public AccountIcon mAccountIcon;
            @JsonProperty(value="reasonType")
            @JsonField
            public String mReasonType = null;
            @JsonProperty(value="reasonVars")
            @JsonField
            public List<String> mReasonVars = new ArrayList<String>();
            @JsonProperty(value="recId")
            @JsonField
            public String mRecId;

            public RecAccountIcon() {
            }

            public RecAccountIcon(Parcel parcel) {
                this.mRecId = parcel.readString();
                this.b = parcel.readString();
                this.mReasonType = parcel.readString();
                parcel.readList(this.mReasonVars, this.getClass().getClassLoader());
                this.mAccountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
            }

            public int describeContents() {
                return 0;
            }

            public int hashCode() {
                if (this.mAccountIcon == null) {
                    return Object.super.hashCode();
                }
                return this.mAccountIcon.hashCode();
            }

            public String toString() {
                return "RecAccountIcon[recId=" + this.mRecId + ",singer=" + this.mAccountIcon + "]";
            }

            public void writeToParcel(Parcel parcel, int n) {
                parcel.writeString(this.mRecId);
                parcel.writeString(this.b);
                parcel.writeString(this.mReasonType);
                parcel.writeList(this.mReasonVars);
                parcel.writeParcelable((Parcelable)this.mAccountIcon, 0);
            }
        }

    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RecommendedTrendingsResponse
    extends ParsedResponse {
        @JsonProperty(value="recTrendingSearches")
        public List<RecTrendingSearch> mTrendingSearches = new ArrayList<RecTrendingSearch>();

        static RecommendedTrendingsResponse a(NetworkResponse networkResponse) {
            return RecommendedTrendingsResponse.a(networkResponse, RecommendedTrendingsResponse.class);
        }

        public static class RecTrendingSearch
        extends ParsedResponse {
            @JsonProperty(value="recId")
            public String mRecId;
            @JsonProperty(value="trendingSearch")
            public String mTrendingTerm;

            public int hashCode() {
                if (TextUtils.isEmpty((CharSequence)this.mTrendingTerm)) {
                    return Object.super.hashCode();
                }
                return this.mTrendingTerm.hashCode();
            }

            public String toString() {
                return "RecTrendingSearch[recId=" + this.mRecId + ",mTrendingTerm=" + this.mTrendingTerm + "]";
            }
        }

    }

}

