/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.Nullable
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  okhttp3.RequestBody
 *  retrofit2.Call
 *  retrofit2.FileRequestBody
 */
package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.ArrangementAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementInfo;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.CursorModel;
import com.smule.android.network.models.ReasonCount;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.utils.TimeExpiringLruCache;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.FileRequestBody;

public class ArrangementManager {
    public static final String a = ArrangementManager.class.getName();
    protected static ArrangementManager b = null;
    private final Map<String, ArrangementVersionLite> c = new HashMap<String, ArrangementVersionLite>();
    private com.smule.android.network.api.ArrangementAPI d = MagicNetwork.a().a(com.smule.android.network.api.ArrangementAPI.class);
    private TimeExpiringLruCache<String, ArrangementVersion> e = new TimeExpiringLruCache(10, (int)TimeUnit.SECONDS.toMillis(30));
    private boolean f = false;

    private ArrangementManager() {
    }

    static /* synthetic */ com.smule.android.network.api.ArrangementAPI a(ArrangementManager arrangementManager) {
        return arrangementManager.d;
    }

    private ArrangementVersionLiteListResponse a(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
        if (arrangementVersionLiteListResponse.a()) {
            this.b(arrangementVersionLiteListResponse.mArrangementVersionLites);
        }
        return arrangementVersionLiteListResponse;
    }

    public static ArrangementManager a() {
        if (b == null) {
            b = new ArrangementManager();
        }
        return b;
    }

    private void a(ArrangementVersion arrangementVersion) {
        if (arrangementVersion == null) {
            return;
        }
        this.e.a(arrangementVersion.arrangement.key, arrangementVersion);
    }

    public NetworkResponse a(ArrangementAPI arrangementUpdateRequest) {
        return NetworkUtils.a(this.d.arrangementUpdate(arrangementUpdateRequest));
    }

    public NetworkResponse a(String object, int n, Integer n2, String string2) {
        object = new SnpRequest(){
            public String arrKey;
            public Integer reason;
            public Integer ver;
            public String vote;

            public ArrangementAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }

            public ArrangementAPI setReason(Integer n) {
                this.reason = n;
                return this;
            }

            public ArrangementAPI setVer(Integer n) {
                this.ver = n;
                return this;
            }

            public ArrangementAPI setVote(String string2) {
                this.vote = string2;
                return this;
            }
        }.setArrKey((String)object).setVer(n).setReason(n2).setVote(string2);
        return NetworkUtils.a(this.d.arrangementVote((Object)object));
    }

    public NetworkResponse a(String string2, boolean bl) {
        return NetworkUtils.a(this.d.deleteArrangement(new SnpRequest(){
            public String arrKey;
            public boolean deletePerfs;

            public ArrangementAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }

            public ArrangementAPI setDeletePerfs(boolean bl) {
                this.deletePerfs = bl;
                return this;
            }
        }.setArrKey(string2).setDeletePerfs(bl)));
    }

    public ArrangementCreateResponse a(ArrangementAPI arrangementCreateRequest) {
        return ArrangementCreateResponse.a(NetworkUtils.a(this.d.arrangementCreate(arrangementCreateRequest)));
    }

    public ArrangementResourceCreateResponse a(File object, String string2) {
        if ((object = ArrangementResourceCreateResponse.a(NetworkUtils.a(this.d.arrangementResourceCreate(MultipartBody.Part.createFormData((String)"file", (String)object.getAbsolutePath(), (RequestBody)new FileRequestBody((File)object)), new SnpRequest(){
            public String role;

            public ArrangementAPI setRole(String string2) {
                this.role = string2;
                return this;
            }
        }.setRole(string2))))).a() && object.resource != null) {
            object.resource.role = string2;
        }
        return object;
    }

    public ArrangementVersionCreateResponse a(ArrangementAPI arrangementVersionCreateRequest) {
        return ArrangementVersionCreateResponse.a(NetworkUtils.a(this.d.arrangementVersionCreate(arrangementVersionCreateRequest)));
    }

    public ArrangementVersionLiteListResponse a(long l, int n, int n2) {
        return this.a(ArrangementVersionLiteListResponse.a(NetworkUtils.a(this.d.getArrangementListOwnedBy(new SnpRequest(){
            public Integer limit = 10;
            public Integer offset = 0;
            public Long ownerAccountId;

            public ArrangementAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public ArrangementAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }

            public ArrangementAPI setOwnerAccountId(Long l) {
                this.ownerAccountId = l;
                return this;
            }
        }.setOwnerAccountId(l).setLimit(n2).setOffset(n)))));
    }

    @Deprecated
    public ArrangementVersionLiteListResponse a(Integer n, Integer n2, ArrangementAPI listSortOrder) {
        return this.a(ArrangementVersionLiteListResponse.a(NetworkUtils.a(this.d.getArrangementList(new SnpRequest(){
            public Integer limit = 10;
            public Integer offset = 0;
            public String sort = ArrangementAPI.RECENT.toString();

            public ArrangementAPI setLimit(Integer n) {
                if (n != null) {
                    this.limit = n;
                }
                return this;
            }

            public ArrangementAPI setOffset(Integer n) {
                if (n != null) {
                    this.offset = n;
                }
                return this;
            }

            public ArrangementAPI setSort(ArrangementAPI listSortOrder) {
                if (listSortOrder != null) {
                    this.sort = listSortOrder.toString();
                }
                return this;
            }
        }.setOffset(n).setLimit(n2).setSort(listSortOrder)))));
    }

    public ArrangementVersionLiteListResponse a(List<String> list) {
        return this.a(ArrangementVersionLiteListResponse.a(NetworkUtils.a(this.d.getArrangementsByKeys(new SnpRequest(){
            public List<String> arrKeys;

            public ArrangementAPI setArrKeys(List<String> list) {
                this.arrKeys = list;
                return this;
            }
        }.setArrKeys(list)))));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ArrangementVersionResponse a(String object, @Nullable Integer object2) {
        synchronized (this) {
            ArrangementVersionResponse arrangementVersionResponse;
            ArrangementVersion arrangementVersion = this.a((String)object);
            if (arrangementVersion == null || arrangementVersionResponse != null && arrangementVersionResponse.intValue() != arrangementVersion.version) {
                arrangementVersionResponse = ArrangementVersionResponse.a(NetworkUtils.a(this.d.getArrangement(new SnpRequest(){
                    public String arrKey;
                    public Integer baseVer;

                    public ArrangementAPI setArrKey(String string2) {
                        this.arrKey = string2;
                        return this;
                    }

                    public ArrangementAPI setBaseVer(Integer n) {
                        this.baseVer = n;
                        return this;
                    }
                }.setArrKey((String)object).setBaseVer((Integer)((Object)arrangementVersionResponse)))));
                object = arrangementVersionResponse;
                if (!arrangementVersionResponse.a()) return object;
                this.a(arrangementVersionResponse.mArrangementVersion);
                return arrangementVersionResponse;
            }
            object = new ArrangementVersionResponse();
            object.b();
            object.mArrangementVersion = arrangementVersion;
            return object;
        }
    }

    public SongBookmarkListResponse a(String string2, int n) {
        return SongBookmarkListResponse.a(NetworkUtils.a(this.d.getSongBookmarkList(new SnpRequest(){
            public String cursor;
            public int limit;

            public ArrangementAPI setCursor(String string2) {
                this.cursor = string2;
                return this;
            }

            public ArrangementAPI setLimit(int n) {
                this.limit = n;
                return this;
            }
        }.setCursor(string2).setLimit(n))));
    }

    public ArrangementVersion a(String string2) {
        return this.e.a(string2);
    }

    public Future<?> a(long l, int n, int n2,  arrangementVersionLiteListCallback) {
        return MagicNetwork.a(new Runnable(this, arrangementVersionLiteListCallback, l, n, n2){
            final /* synthetic */  a;
            final /* synthetic */ long b;
            final /* synthetic */ int c;
            final /* synthetic */ int d;
            final /* synthetic */ ArrangementManager e;
            {
                this.e = arrangementManager;
                this.a = arrangementVersionLiteListCallback;
                this.b = l;
                this.c = n;
                this.d = n2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.e.a(this.b, this.c, this.d));
            }
        });
    }

    public Future<?> a(String string2, int n, ResponseInterface<SongBookmarkListResponse> responseInterface) {
        return MagicNetwork.a(new Runnable(this, responseInterface, string2, n){
            final /* synthetic */ ResponseInterface a;
            final /* synthetic */ String b;
            final /* synthetic */ int c;
            final /* synthetic */ ArrangementManager d;
            {
                this.d = arrangementManager;
                this.a = responseInterface;
                this.b = string2;
                this.c = n;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.d.a(this.b, this.c));
            }
        });
    }

    public Future<?> a(String string2, int n, Integer n2, String string3, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, string2, n, n2, string3){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ int c;
            final /* synthetic */ Integer d;
            final /* synthetic */ String e;
            final /* synthetic */ ArrangementManager f;
            {
                this.f = arrangementManager;
                this.a = networkResponseCallback;
                this.b = string2;
                this.c = n;
                this.d = n2;
                this.e = string3;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.f.a(this.b, this.c, this.d, this.e));
            }
        });
    }

    public Future<?> a(String string2, ResponseInterface<AddSongBookmarkResponse> responseInterface) {
        return MagicNetwork.a(new Runnable(this, responseInterface, string2){
            final /* synthetic */ ResponseInterface a;
            final /* synthetic */ String b;
            final /* synthetic */ ArrangementManager c;
            {
                this.c = arrangementManager;
                this.a = responseInterface;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.f(this.b));
            }
        });
    }

    public Future<?> a(String string2,  arrangementVersionCallback) {
        return this.a(string2, null, arrangementVersionCallback);
    }

    public Future<?> a(String string2, @Nullable Integer n,  arrangementVersionCallback) {
        return MagicNetwork.a(new Runnable(this, arrangementVersionCallback, string2, n){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ Integer c;
            final /* synthetic */ ArrangementManager d;
            {
                this.d = arrangementManager;
                this.a = arrangementVersionCallback;
                this.b = string2;
                this.c = n;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.d.a(this.b, this.c));
            }
        });
    }

    public Future<?> a(List<String> list,  arrangementVersionLiteListCallback) {
        return MagicNetwork.a(new Runnable(this, arrangementVersionLiteListCallback, list){
            final /* synthetic */  a;
            final /* synthetic */ List b;
            final /* synthetic */ ArrangementManager c;
            {
                this.c = arrangementManager;
                this.a = arrangementVersionLiteListCallback;
                this.b = list;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.a(this.b));
            }
        });
    }

    public void a(boolean bl) {
        this.f = bl;
    }

    public ArrangementVersionResponse b(String object) {
        synchronized (this) {
            object = this.a((String)object, (Integer)null);
            return object;
        }
    }

    public Future<?> b(String string2, ResponseInterface<RemoveSongBookmarkResponse> responseInterface) {
        return MagicNetwork.a(new Runnable(this, responseInterface, string2){
            final /* synthetic */ ResponseInterface a;
            final /* synthetic */ String b;
            final /* synthetic */ ArrangementManager c;
            {
                this.c = arrangementManager;
                this.a = responseInterface;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.g(this.b));
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(List<ArrangementVersionLite> object) {
        if (object == null) {
            return;
        }
        Map<String, ArrangementVersionLite> map = this.c;
        synchronized (map) {
            object = object.iterator();
            while (object.hasNext()) {
                ArrangementVersionLite arrangementVersionLite = (ArrangementVersionLite)object.next();
                this.c.put(arrangementVersionLite.key, arrangementVersionLite);
            }
            return;
        }
    }

    public boolean b() {
        return this.f;
    }

    public ArrangementOwnedDetailsResponse c(String string2) {
        return ArrangementOwnedDetailsResponse.a(NetworkUtils.a(this.d.getArrangementOwnedDetails(new .setArrKey(string2))));
    }

    @Deprecated
    public ArrangementVersionLiteListResponse c() {
        return this.a(ArrangementVersionLiteListResponse.a(NetworkUtils.a(this.d.getArrangementListUnlocked(new SnpRequest()))));
    }

    public Future<?> d(String string2) {
        return MagicNetwork.a(new Runnable(this, string2){
            final /* synthetic */ String a;
            final /* synthetic */ ArrangementManager b;
            {
                this.b = arrangementManager;
                this.a = string2;
            }

            public void run() {
                NetworkUtils.a(ArrangementManager.a(this.b).sendArrangementPlayed(new SnpRequest(){
                    public String arrKey;

                    public com.smule.android.network.api.ArrangementAPI$SendArrangementPlayedRequest setArrKey(String string2) {
                        this.arrKey = string2;
                        return this;
                    }
                }.setArrKey(this.a)));
            }
        });
    }

    public ArrangementDetailsResponse e(String string2) {
        return ArrangementDetailsResponse.a(NetworkUtils.a(this.d.getArrangementDetails(new SnpRequest(){
            public String arrKey;

            public ArrangementAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }
        }.setArrKey(string2))));
    }

    public AddSongBookmarkResponse f(String string2) {
        return AddSongBookmarkResponse.a(NetworkUtils.a(this.d.addSongBookmark(new SnpRequest(){
            public String arrKey;

            public ArrangementAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }
        }.setArrKey(string2))));
    }

    public RemoveSongBookmarkResponse g(String string2) {
        return RemoveSongBookmarkResponse.a(NetworkUtils.a(this.d.removeSongBookmark(new SnpRequest(){
            public String arrKey;

            public ArrangementAPI setArrKey(String string2) {
                this.arrKey = string2;
                return this;
            }
        }.setArrKey(string2))));
    }

    @JsonIgnoreProperties
    public static class AddSongBookmarkResponse
    extends ParsedResponse {
        static AddSongBookmarkResponse a(NetworkResponse networkResponse) {
            return AddSongBookmarkResponse.a(networkResponse, AddSongBookmarkResponse.class);
        }

        public String toString() {
            return "AddSongBookmarkResponse {}";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementCreateResponse
    extends ParsedResponse {
        @JsonProperty(value="arrKey")
        public String arrKey;

        static ArrangementCreateResponse a(NetworkResponse networkResponse) {
            return ArrangementCreateResponse.a(networkResponse, ArrangementCreateResponse.class);
        }

        public String toString() {
            return "ArrangementCreateResponse{arrKey=" + this.arrKey + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementDetailsResponse
    extends ParsedResponse
    implements Parcelable {
        public static final Parcelable.Creator<ArrangementDetailsResponse> CREATOR = new Parcelable.Creator<ArrangementDetailsResponse>(){

            public ArrangementDetailsResponse a(Parcel parcel) {
                return new ArrangementDetailsResponse(parcel);
            }

            public ArrangementDetailsResponse[] a(int n) {
                return new ArrangementDetailsResponse[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        @JsonProperty(value="arrVersion")
        public ArrangementVersion arrVersion;
        @JsonProperty(value="primeSong")
        public CompositionLite primeSong;
        @JsonProperty(value="reasonCounts")
        public ArrayList<ReasonCount> reasonCounts;

        public ArrangementDetailsResponse() {
        }

        public ArrangementDetailsResponse(Parcel parcel) {
            this.arrVersion = (ArrangementVersion)parcel.readParcelable(ArrangementVersion.class.getClassLoader());
            parcel.readTypedList(this.reasonCounts, ReasonCount.CREATOR);
            this.primeSong = (CompositionLite)parcel.readParcelable(CompositionLite.class.getClassLoader());
        }

        static ArrangementDetailsResponse a(NetworkResponse networkResponse) {
            return ArrangementDetailsResponse.a(networkResponse, ArrangementDetailsResponse.class);
        }

        public int describeContents() {
            return 0;
        }

        public String toString() {
            return "ArrangementDetailsResponse {arrVersion=" + this.arrVersion + ", reasonCounts=" + this.reasonCounts + ", primeSong=" + this.primeSong;
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeParcelable((Parcelable)this.arrVersion, 0);
            parcel.writeTypedList(this.reasonCounts);
            parcel.writeParcelable((Parcelable)this.primeSong, 0);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementOwnedDetailsResponse
    extends ParsedResponse {
        @JsonProperty(value="arrInfo")
        public ArrangementInfo mArrInfo;
        @JsonProperty(value="perfCount")
        public Integer mPerfCount;
        @JsonProperty(value="reasonCounts")
        public ArrayList<ReasonCount> mReasonCounts;

        static ArrangementOwnedDetailsResponse a(NetworkResponse networkResponse) {
            return ArrangementOwnedDetailsResponse.a(networkResponse, ArrangementOwnedDetailsResponse.class);
        }

        public String toString() {
            return "ArrangementOwnedDetailsResponse{mArrInfo=" + this.mArrInfo.toString() + ", mReasonCounts=" + this.mReasonCounts.toString() + ", mPerfCount=" + this.mPerfCount + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementResourceCreateResponse
    extends ParsedResponse {
        @JsonProperty(value="resource")
        public ResourceV2 resource;

        static ArrangementResourceCreateResponse a(NetworkResponse networkResponse) {
            return ArrangementResourceCreateResponse.a(networkResponse, ArrangementResourceCreateResponse.class);
        }

        public String toString() {
            return "ArrangementResourceCreateResponse{resource=" + this.resource + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementVersionCreateResponse
    extends ParsedResponse {
        @JsonProperty(value="arrKey")
        public String arrKey;
        @JsonProperty(value="expectedPubTime")
        public int expectedPubTime;
        @JsonProperty(value="ver")
        public int ver;

        static ArrangementVersionCreateResponse a(NetworkResponse networkResponse) {
            return ArrangementVersionCreateResponse.a(networkResponse, ArrangementVersionCreateResponse.class);
        }

        public String toString() {
            return "ArrangementVersionCreateResponse{arrKey=" + this.arrKey + ", ver=" + this.ver + ", expectedPubTime=" + this.expectedPubTime + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementVersionLiteListResponse
    extends ParsedResponse {
        @JsonProperty(value="arrCount")
        public Integer mArrCount;
        @JsonProperty(value="arrVersionLites")
        public ArrayList<ArrangementVersionLite> mArrangementVersionLites;
        @JsonProperty(value="next")
        public Integer mNext;

        static ArrangementVersionLiteListResponse a(NetworkResponse networkResponse) {
            return ArrangementVersionLiteListResponse.a(networkResponse, ArrangementVersionLiteListResponse.class);
        }

        public String toString() {
            return "ArrangementVersionLiteListResponse{mArrangementVersionLites=" + this.mArrangementVersionLites + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class ArrangementVersionResponse
    extends ParsedResponse {
        @JsonProperty(value="arrVersion")
        public ArrangementVersion mArrangementVersion;

        static ArrangementVersionResponse a(NetworkResponse networkResponse) {
            return ArrangementVersionResponse.a(networkResponse, ArrangementVersionResponse.class);
        }

        public void b() {
            this.a = NetworkResponse.a();
        }

        public String toString() {
            return "ArrangementVersionResponse{mArrangementVersion=" + this.mArrangementVersion + '}';
        }
    }

    @JsonIgnoreProperties
    public static class RemoveSongBookmarkResponse
    extends ParsedResponse {
        static RemoveSongBookmarkResponse a(NetworkResponse networkResponse) {
            return RemoveSongBookmarkResponse.a(networkResponse, RemoveSongBookmarkResponse.class);
        }

        public String toString() {
            return "RemoveSongBookmarkResponse {}";
        }
    }

    @JsonIgnoreProperties
    public static class SongBookmarkListResponse
    extends ParsedResponse {
        @JsonProperty
        public CursorModel cursor;
        @JsonProperty
        public ArrayList<ArrangementVersionLite> songs;
        @JsonProperty
        public Integer totalCount;

        static SongBookmarkListResponse a(NetworkResponse networkResponse) {
            return SongBookmarkListResponse.a(networkResponse, SongBookmarkListResponse.class);
        }

        public String toString() {
            return "ArrangementDetailsResponse {songs=" + this.songs + ", cursor=" + this.cursor + ", totalCount=" + this.totalCount + "}";
        }
    }

}

