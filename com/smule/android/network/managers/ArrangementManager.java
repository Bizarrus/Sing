package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.api.ArrangementAPI;
import com.smule.android.network.api.ArrangementAPI.ArrangementCreateRequest;
import com.smule.android.network.api.ArrangementAPI.ArrangementCreateResourceRequest;
import com.smule.android.network.api.ArrangementAPI.ArrangementRemoveRequest;
import com.smule.android.network.api.ArrangementAPI.ArrangementUpdateRequest;
import com.smule.android.network.api.ArrangementAPI.ArrangementVersionCreateRequest;
import com.smule.android.network.api.ArrangementAPI.ArrangementVoteRequest;
import com.smule.android.network.api.ArrangementAPI.GetArrangementDetailsRequest;
import com.smule.android.network.api.ArrangementAPI.GetArrangementListRequest;
import com.smule.android.network.api.ArrangementAPI.GetArrangementRequest;
import com.smule.android.network.api.ArrangementAPI.GetArrangementsByKeysRequest;
import com.smule.android.network.api.ArrangementAPI.GetArrangmentListOwnedByRequest;
import com.smule.android.network.api.ArrangementAPI.ListSortOrder;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.ArrangementInfo;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CompositionLite;
import com.smule.android.network.models.ReasonCount;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.utils.TimeExpiringLruCache;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.MultipartBody.Part;
import retrofit2.FileRequestBody;

public class ArrangementManager {
    public static final String f6790a = ArrangementManager.class.getName();
    protected static ArrangementManager f6791b = null;
    private final Map<String, ArrangementVersionLite> f6792c = new HashMap();
    private ArrangementAPI f6793d = ((ArrangementAPI) MagicNetwork.m7789a().m7817a(ArrangementAPI.class));
    private TimeExpiringLruCache<String, ArrangementVersion> f6794e = new TimeExpiringLruCache(10, (int) TimeUnit.SECONDS.toMillis(30));

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementCreateResponse extends ParsedResponse {
        @JsonProperty("arrKey")
        public String arrKey;

        static ArrangementCreateResponse m7891a(NetworkResponse networkResponse) {
            return (ArrangementCreateResponse) ParsedResponse.m7676a(networkResponse, ArrangementCreateResponse.class);
        }

        public String toString() {
            return "ArrangementCreateResponse{arrKey=" + this.arrKey + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementDetailsResponse extends ParsedResponse implements Parcelable {
        public static final Creator<ArrangementDetailsResponse> CREATOR = new 1();
        @JsonProperty("arrVersion")
        public ArrangementVersion arrVersion;
        @JsonProperty("primeSong")
        public CompositionLite primeSong;
        @JsonProperty("reasonCounts")
        public ArrayList<ReasonCount> reasonCounts;

        public ArrangementDetailsResponse(Parcel parcel) {
            this.arrVersion = (ArrangementVersion) parcel.readParcelable(ArrangementVersion.class.getClassLoader());
            parcel.readTypedList(this.reasonCounts, ReasonCount.CREATOR);
            this.primeSong = (CompositionLite) parcel.readParcelable(CompositionLite.class.getClassLoader());
        }

        static ArrangementDetailsResponse m7892a(NetworkResponse networkResponse) {
            return (ArrangementDetailsResponse) ParsedResponse.m7676a(networkResponse, ArrangementDetailsResponse.class);
        }

        public String toString() {
            return "ArrangementDetailsResponse {arrVersion=" + this.arrVersion + ", reasonCounts=" + this.reasonCounts + ", primeSong=" + this.primeSong;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.arrVersion, 0);
            parcel.writeTypedList(this.reasonCounts);
            parcel.writeParcelable(this.primeSong, 0);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementOwnedDetailsResponse extends ParsedResponse {
        @JsonProperty("arrInfo")
        public ArrangementInfo mArrInfo;
        @JsonProperty("perfCount")
        public Integer mPerfCount;
        @JsonProperty("reasonCounts")
        public ArrayList<ReasonCount> mReasonCounts;

        static ArrangementOwnedDetailsResponse m7893a(NetworkResponse networkResponse) {
            return (ArrangementOwnedDetailsResponse) ParsedResponse.m7676a(networkResponse, ArrangementOwnedDetailsResponse.class);
        }

        public String toString() {
            return "ArrangementOwnedDetailsResponse{mArrInfo=" + this.mArrInfo.toString() + ", mReasonCounts=" + this.mReasonCounts.toString() + ", mPerfCount=" + this.mPerfCount + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementResourceCreateResponse extends ParsedResponse {
        @JsonProperty("resource")
        public ResourceV2 resource;

        static ArrangementResourceCreateResponse m7894a(NetworkResponse networkResponse) {
            return (ArrangementResourceCreateResponse) ParsedResponse.m7676a(networkResponse, ArrangementResourceCreateResponse.class);
        }

        public String toString() {
            return "ArrangementResourceCreateResponse{resource=" + this.resource + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementVersionCreateResponse extends ParsedResponse {
        @JsonProperty("arrKey")
        public String arrKey;
        @JsonProperty("expectedPubTime")
        public int expectedPubTime;
        @JsonProperty("ver")
        public int ver;

        static ArrangementVersionCreateResponse m7895a(NetworkResponse networkResponse) {
            return (ArrangementVersionCreateResponse) ParsedResponse.m7676a(networkResponse, ArrangementVersionCreateResponse.class);
        }

        public String toString() {
            return "ArrangementVersionCreateResponse{arrKey=" + this.arrKey + ", ver=" + this.ver + ", expectedPubTime=" + this.expectedPubTime + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementVersionLiteListResponse extends ParsedResponse {
        @JsonProperty("arrCount")
        public Integer mArrCount;
        @JsonProperty("arrVersionLites")
        public ArrayList<ArrangementVersionLite> mArrangementVersionLites;
        @JsonProperty("next")
        public Integer mNext;

        static ArrangementVersionLiteListResponse m7896a(NetworkResponse networkResponse) {
            return (ArrangementVersionLiteListResponse) ParsedResponse.m7676a(networkResponse, ArrangementVersionLiteListResponse.class);
        }

        public String toString() {
            return "ArrangementVersionLiteListResponse{mArrangementVersionLites=" + this.mArrangementVersionLites + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ArrangementVersionResponse extends ParsedResponse {
        @JsonProperty("arrVersion")
        public ArrangementVersion mArrangementVersion;

        static ArrangementVersionResponse m7897a(NetworkResponse networkResponse) {
            return (ArrangementVersionResponse) ParsedResponse.m7676a(networkResponse, ArrangementVersionResponse.class);
        }

        public void m7898b() {
            this.a = NetworkResponse.m7834a();
        }

        public String toString() {
            return "ArrangementVersionResponse{mArrangementVersion=" + this.mArrangementVersion + '}';
        }
    }

    private ArrangementManager() {
    }

    public static ArrangementManager m7901a() {
        if (f6791b == null) {
            f6791b = new ArrangementManager();
        }
        return f6791b;
    }

    public ArrangementVersion m7913a(String str) {
        return (ArrangementVersion) this.f6794e.a(str);
    }

    private void m7902a(ArrangementVersion arrangementVersion) {
        if (arrangementVersion != null) {
            this.f6794e.a(arrangementVersion.arrangement.key, arrangementVersion);
        }
    }

    public synchronized ArrangementVersionResponse m7921b(String str) {
        return m7912a(str, (Integer) null);
    }

    public synchronized ArrangementVersionResponse m7912a(String str, @Nullable Integer num) {
        ArrangementVersionResponse a;
        ArrangementVersion a2 = m7913a(str);
        if (a2 == null || !(num == null || num.intValue() == a2.version)) {
            a = ArrangementVersionResponse.m7897a(NetworkUtils.a(this.f6793d.getArrangement(new GetArrangementRequest().setArrKey(str).setBaseVer(num))));
            if (a.m7677a()) {
                m7902a(a.mArrangementVersion);
            }
        } else {
            a = new ArrangementVersionResponse();
            a.m7898b();
            a.mArrangementVersion = a2;
        }
        return a;
    }

    public Future<?> m7917a(String str, ArrangementVersionCallback arrangementVersionCallback) {
        return m7918a(str, null, arrangementVersionCallback);
    }

    public Future<?> m7918a(String str, @Nullable Integer num, ArrangementVersionCallback arrangementVersionCallback) {
        return MagicNetwork.m7790a(new 1(this, arrangementVersionCallback, str, num));
    }

    public ArrangementOwnedDetailsResponse m7923c(String str) {
        return ArrangementOwnedDetailsResponse.m7893a(NetworkUtils.a(this.f6793d.getArrangementOwnedDetails(new GetArrangementRequest().setArrKey(str))));
    }

    public NetworkResponse m7905a(String str, boolean z) {
        return NetworkUtils.a(this.f6793d.deleteArrangement(new ArrangementRemoveRequest().setArrKey(str).setDeletePerfs(z)));
    }

    public ArrangementVersionLiteListResponse m7910a(Integer num, Integer num2, ListSortOrder listSortOrder) {
        return m7900a(ArrangementVersionLiteListResponse.m7896a(NetworkUtils.a(this.f6793d.getArrangementList(new GetArrangementListRequest().setOffset(num).setLimit(num2).setSort(listSortOrder)))));
    }

    public Future<?> m7915a(Integer num, Integer num2, ListSortOrder listSortOrder, ArrangementVersionLiteListCallback arrangementVersionLiteListCallback) {
        return MagicNetwork.m7790a(new 4(this, arrangementVersionLiteListCallback, num, num2, listSortOrder));
    }

    private ArrangementVersionLiteListResponse m7900a(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
        if (arrangementVersionLiteListResponse.m7677a()) {
            m7922b(arrangementVersionLiteListResponse.mArrangementVersionLites);
        }
        return arrangementVersionLiteListResponse;
    }

    public ArrangementVersionLiteListResponse m7911a(List<String> list) {
        return m7900a(ArrangementVersionLiteListResponse.m7896a(NetworkUtils.a(this.f6793d.getArrangementsByKeys(new GetArrangementsByKeysRequest().setArrKeys(list)))));
    }

    public Future<?> m7919a(List<String> list, ArrangementVersionLiteListCallback arrangementVersionLiteListCallback) {
        return MagicNetwork.m7790a(new 5(this, arrangementVersionLiteListCallback, list));
    }

    @Deprecated
    public ArrangementVersionLiteListResponse m7920b() {
        return m7900a(ArrangementVersionLiteListResponse.m7896a(NetworkUtils.a(this.f6793d.getArrangementListUnlocked(new SnpRequest()))));
    }

    public ArrangementVersionLiteListResponse m7909a(long j, int i, int i2) {
        return m7900a(ArrangementVersionLiteListResponse.m7896a(NetworkUtils.a(this.f6793d.getArrangementListOwnedBy(new GetArrangmentListOwnedByRequest().setOwnerAccountId(Long.valueOf(j)).setLimit(Integer.valueOf(i2)).setOffset(Integer.valueOf(i))))));
    }

    public Future<?> m7914a(long j, int i, int i2, ArrangementVersionLiteListCallback arrangementVersionLiteListCallback) {
        return MagicNetwork.m7790a(new 7(this, arrangementVersionLiteListCallback, j, i, i2));
    }

    public ArrangementVersionLite m7924d(String str) {
        ArrangementVersionLite arrangementVersionLite;
        synchronized (this.f6792c) {
            arrangementVersionLite = (ArrangementVersionLite) this.f6792c.get(str);
        }
        return arrangementVersionLite;
    }

    public void m7922b(List<ArrangementVersionLite> list) {
        if (list != null) {
            synchronized (this.f6792c) {
                for (ArrangementVersionLite arrangementVersionLite : list) {
                    this.f6792c.put(arrangementVersionLite.key, arrangementVersionLite);
                }
            }
        }
    }

    public NetworkResponse m7904a(String str, int i, Integer num, String str2) {
        return NetworkUtils.a(this.f6793d.arrangementVote(new ArrangementVoteRequest().setArrKey(str).setVer(Integer.valueOf(i)).setReason(num).setVote(str2)));
    }

    public Future<?> m7916a(String str, int i, Integer num, String str2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 8(this, networkResponseCallback, str, i, num, str2));
    }

    public ArrangementCreateResponse m7906a(ArrangementCreateRequest arrangementCreateRequest) {
        return ArrangementCreateResponse.m7891a(NetworkUtils.a(this.f6793d.arrangementCreate(arrangementCreateRequest)));
    }

    public NetworkResponse m7903a(ArrangementUpdateRequest arrangementUpdateRequest) {
        return NetworkUtils.a(this.f6793d.arrangementUpdate(arrangementUpdateRequest));
    }

    public ArrangementResourceCreateResponse m7907a(File file, String str) {
        ArrangementResourceCreateResponse a = ArrangementResourceCreateResponse.m7894a(NetworkUtils.a(this.f6793d.arrangementResourceCreate(Part.createFormData("file", file.getAbsolutePath(), new FileRequestBody(file)), new ArrangementCreateResourceRequest().setRole(str))));
        if (a.m7677a() && a.resource != null) {
            a.resource.role = str;
        }
        return a;
    }

    public ArrangementVersionCreateResponse m7908a(ArrangementVersionCreateRequest arrangementVersionCreateRequest) {
        return ArrangementVersionCreateResponse.m7895a(NetworkUtils.a(this.f6793d.arrangementVersionCreate(arrangementVersionCreateRequest)));
    }

    public Future<?> m7925e(String str) {
        return MagicNetwork.m7790a(new 13(this, str));
    }

    public ArrangementDetailsResponse m7926f(String str) {
        return ArrangementDetailsResponse.m7892a(NetworkUtils.a(this.f6793d.getArrangementDetails(new GetArrangementDetailsRequest().setArrKey(str))));
    }
}
