package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.api.PerformancesAPI.CommentPerformanceRequest;
import com.smule.android.network.api.PerformancesAPI.CreatePerfRequest;
import com.smule.android.network.api.PerformancesAPI.DeleteCommentsRequest;
import com.smule.android.network.api.PerformancesAPI.DeletePerformanceRequest;
import com.smule.android.network.api.PerformancesAPI.EnsembleType;
import com.smule.android.network.api.PerformancesAPI.FillStatus;
import com.smule.android.network.api.PerformancesAPI.GetCommentsRequest;
import com.smule.android.network.api.PerformancesAPI.GetLovesRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformanceChildrenRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformanceDetailsByKeysRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformanceDetailsRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformanceListRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformancesByFeedRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformancesByPerformerRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformancesByTagRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformancesFromMyNetworkRequest;
import com.smule.android.network.api.PerformancesAPI.GetPerformancesRequest;
import com.smule.android.network.api.PerformancesAPI.HotType;
import com.smule.android.network.api.PerformancesAPI.JoinPerfRequest;
import com.smule.android.network.api.PerformancesAPI.ListenRequest;
import com.smule.android.network.api.PerformancesAPI.LoveRequest;
import com.smule.android.network.api.PerformancesAPI.PreuploadRequest;
import com.smule.android.network.api.PerformancesAPI.RenderPerformanceRequest;
import com.smule.android.network.api.PerformancesAPI.RenderType;
import com.smule.android.network.api.PerformancesAPI.ReportAbusePerformanceRequest;
import com.smule.android.network.api.PerformancesAPI.ReportAbusesRequest;
import com.smule.android.network.api.PerformancesAPI.SortOrder;
import com.smule.android.network.api.PerformancesAPI.UpdatePerformanceRequest;
import com.smule.android.network.api.PerformancesAPI.UploadType;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.PerformanceV2.CompositionType;
import com.smule.android.network.response.GetConnectedPerformancesResponse;
import com.smule.android.utils.TimeExpiringLruCache;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class PerformanceManager {
    protected static PerformanceManager f6815a = null;
    private static final String f6816b = PerformanceManager.class.getName();
    private PerformancesAPI f6817c = ((PerformancesAPI) MagicNetwork.m7789a().m7817a(PerformancesAPI.class));
    private TimeExpiringLruCache<String, PerformanceV2> f6818d = new TimeExpiringLruCache(10, (int) TimeUnit.SECONDS.toMillis(30));

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreateOrJoinResponse extends ParsedResponse {
        @JsonProperty("performance")
        public PerformanceV2 mPerformance;
        @JsonProperty("trackKey")
        public String mTrackKey;

        static CreateOrJoinResponse m7965a(NetworkResponse networkResponse) {
            return (CreateOrJoinResponse) ParsedResponse.m7676a(networkResponse, CreateOrJoinResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IsBookmarkSeedResponse extends ParsedResponse {
        @JsonProperty("isBookmarkSeed")
        public Boolean mIsBookmarkSeed;

        static IsBookmarkSeedResponse m7966a(NetworkResponse networkResponse) {
            return (IsBookmarkSeedResponse) ParsedResponse.m7676a(networkResponse, IsBookmarkSeedResponse.class);
        }

        public String toString() {
            return "IsBookmarkSeedResponse{mIsBookmarkSeed=" + this.mIsBookmarkSeed + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformanceLovesResponse extends ParsedResponse {
        @JsonProperty("performanceKey")
        public String mPerformanceKey;
        @JsonProperty("loves")
        public ArrayList<PerformancePost> mPerformancePosts = new ArrayList();

        public static PerformanceLovesResponse m7967a(NetworkResponse networkResponse) {
            return (PerformanceLovesResponse) ParsedResponse.m7676a(networkResponse, PerformanceLovesResponse.class);
        }

        public String toString() {
            return "PerformanceLovesResponse [mResponse=" + this.a + ", mPerformanceKey=" + this.mPerformanceKey + ", mPerformancePosts=" + this.mPerformancePosts + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformancePostsResponse extends ParsedResponse {
        @JsonProperty("next")
        public Integer mNext;
        @JsonProperty("performanceKey")
        public String mPerformanceKey;
        @JsonProperty("comments")
        public ArrayList<PerformancePost> mPerformancePosts = new ArrayList();

        public static PerformancePostsResponse m7968a(NetworkResponse networkResponse) {
            return (PerformancePostsResponse) ParsedResponse.m7676a(networkResponse, PerformancePostsResponse.class);
        }

        public String toString() {
            return "PerformancePostsResponse [mResponse=" + this.a + ", mPerformanceKey=" + this.mPerformanceKey + ", next=" + this.mNext + ", mPerformancePosts=" + this.mPerformancePosts + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformanceResourceInfo implements Parcelable {
        public static final Creator<PerformanceResourceInfo> CREATOR = new 1();
        @JsonProperty("hostname")
        public String mHostname;
        @JsonProperty("pop")
        public String mPOP;
        @JsonProperty("resourceId")
        public Long mResourceId;
        @JsonProperty("resourceType")
        public ResourceType mResourceType;

        private PerformanceResourceInfo(Parcel parcel) {
            this.mPOP = parcel.readString();
            this.mResourceType = ResourceType.valueOf(parcel.readString());
            this.mResourceId = Long.valueOf(parcel.readLong());
            this.mHostname = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPOP);
            parcel.writeString(this.mResourceType.toString());
            parcel.writeLong(this.mResourceId.longValue());
            parcel.writeString(this.mHostname);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformanceResponse extends ParsedResponse {
        @JsonProperty("performance")
        public PerformanceV2 mPerformance;

        static PerformanceResponse m7969a(NetworkResponse networkResponse) {
            return (PerformanceResponse) ParsedResponse.m7676a(networkResponse, PerformanceResponse.class);
        }

        public void m7970b() {
            this.a = NetworkResponse.m7834a();
        }

        public String toString() {
            return "PerformanceResponse [mResponse=" + this.a + ", mPerformance=" + this.mPerformance + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformancesByPerformerResponse extends ParsedResponse {
        @JsonProperty("next")
        public Integer mNext;
        @JsonProperty("performanceIcons")
        public ArrayList<PerformanceV2> mPerformances;
        @JsonProperty("storageLimit")
        public Integer mStorageLimit;
        @JsonProperty("totalPerformances")
        public Integer mTotalPerformances;

        static PerformancesByPerformerResponse m7971a(NetworkResponse networkResponse) {
            return (PerformancesByPerformerResponse) ParsedResponse.m7676a(networkResponse, PerformancesByPerformerResponse.class);
        }

        public String toString() {
            return "PerformancesByPerformerResponse{mPerformances=" + this.mPerformances + ", mTotalPerformances=" + this.mTotalPerformances + ", mStorageLimit=" + this.mStorageLimit + ", next=" + this.mNext + '}';
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PerformancesResponse extends ParsedResponse {
        @JsonProperty("next")
        public Integer mNext;
        @JsonProperty("performanceIcons")
        public ArrayList<PerformanceV2> mPerformances;

        static PerformancesResponse m7972a(NetworkResponse networkResponse) {
            return (PerformancesResponse) ParsedResponse.m7676a(networkResponse, PerformancesResponse.class);
        }

        public String toString() {
            return "PerformancesResponse [mResponse=" + this.a + ", next=" + this.mNext + ", mPerformances=" + this.mPerformances + "]";
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PreuploadResponse extends ParsedResponse {
        @JsonProperty("resources")
        public ArrayList<PerformanceResourceInfo> mResources;

        static PreuploadResponse m7973a(NetworkResponse networkResponse) {
            return (PreuploadResponse) ParsedResponse.m7676a(networkResponse, PreuploadResponse.class);
        }
    }

    private PerformanceManager() {
    }

    public static PerformanceManager m7975a() {
        if (f6815a == null) {
            f6815a = new PerformanceManager();
        }
        return f6815a;
    }

    public PerformanceV2 m7995a(String str) {
        return (PerformanceV2) this.f6818d.a(str);
    }

    public void m8013a(PerformanceV2 performanceV2) {
        if (performanceV2 != null) {
            this.f6818d.a(performanceV2.performanceKey, performanceV2);
        }
    }

    public PerformancesResponse m8018b() {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceList(new GetPerformanceListRequest().setSort(SortOrder.HOT))));
    }

    public PerformancesResponse m7986a(int i) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceList(new GetPerformanceListRequest().setSort(SortOrder.HOT).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(15)))));
    }

    public PerformancesResponse m8026c() {
        return m8018b();
    }

    public PerformancesResponse m8019b(int i) {
        return m7986a(i);
    }

    public PerformancesResponse m8028d() {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceList(new GetPerformanceListRequest().setSort(SortOrder.RECENT))));
    }

    @Deprecated
    public PerformancesResponse m8031e() {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformancesByFeed(new GetPerformancesByFeedRequest())));
    }

    public PerformancesResponse m7988a(FillStatus fillStatus, int i, int i2) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformancesFromMyNetwork(new GetPerformancesFromMyNetworkRequest().setFillStatus(fillStatus).setOffset(Integer.valueOf(i)).setLimit(Integer.valueOf(i2)))));
    }

    public PerformancesResponse m7989a(SortOrder sortOrder, Integer num, Integer num2, FillStatus fillStatus, HotType hotType, String str, String str2, String str3, boolean z, boolean z2) {
        String str4;
        PerformancesAPI performancesAPI = this.f6817c;
        GetPerformanceListRequest hotType2 = new GetPerformanceListRequest().setSort(sortOrder).setOffset(num).setLimit(num2).setFillStatus(fillStatus).setHotType(hotType);
        if (z) {
            str4 = null;
        } else {
            str4 = str;
        }
        GetPerformanceListRequest songUid = hotType2.setSongUid(str4);
        if (!z) {
            str = null;
        }
        return PerformancesResponse.m7972a(NetworkUtils.a(performancesAPI.getPerformanceList(songUid.setArrKey(str).setApp(str2).setMixApp(str3).setVideoOnly(Boolean.valueOf(z2)))));
    }

    public Future<?> m7998a(SortOrder sortOrder, Integer num, Integer num2, FillStatus fillStatus, HotType hotType, String str, String str2, String str3, boolean z, boolean z2, PerformancesResponseCallback performancesResponseCallback) {
        return MagicNetwork.m7790a(new 2(this, performancesResponseCallback, sortOrder, num, num2, fillStatus, hotType, str, str2, str3, z, z2));
    }

    public Future<?> m8003a(String str, SortOrder sortOrder, Integer num, Integer num2, Boolean bool, PerformancesResponseCallback performancesResponseCallback) {
        return m7998a(sortOrder, num, num2, FillStatus.ACTIVESEED, null, str, null, null, false, bool.booleanValue(), performancesResponseCallback);
    }

    public Future<?> m8022b(String str, SortOrder sortOrder, Integer num, Integer num2, Boolean bool, PerformancesResponseCallback performancesResponseCallback) {
        return m7998a(sortOrder, num, num2, FillStatus.SEED, null, str, null, null, true, bool.booleanValue(), performancesResponseCallback);
    }

    @Deprecated
    public PerformanceResponse m8017b(String str) {
        return PerformanceResponse.m7969a(NetworkUtils.a(this.f6817c.getPerformanceDetails(new GetPerformanceDetailsRequest().setPerformanceKey(str))));
    }

    public PerformancesResponse m7993a(Collection<String> collection) {
        if (collection.size() > 25) {
            Log.m7774d(f6816b, "too many keys in request (" + collection.size() + ") - this request may fail");
        }
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceDetailsByKeys(new GetPerformanceDetailsByKeysRequest().setPerformanceKeys(collection))));
    }

    public synchronized PerformanceResponse m8025c(String str) {
        PerformanceResponse a;
        PerformanceV2 a2 = m7995a(str);
        if (a2 == null) {
            a = PerformanceResponse.m7969a(NetworkUtils.a(this.f6817c.getPerformanceDetailsNoRender(new GetPerformanceDetailsRequest().setPerformanceKey(str))));
            if (a.m7677a() && a.mPerformance.m8266a()) {
                m8013a(a.mPerformance);
            }
        } else {
            a = new PerformanceResponse();
            a.m7970b();
            a.mPerformance = a2;
        }
        return a;
    }

    public Future<?> m8007a(String str, PerformanceResponseCallback performanceResponseCallback) {
        return MagicNetwork.m7790a(new 11(this, performanceResponseCallback, str));
    }

    public NetworkResponse m7976a(String str, RenderType renderType) {
        return NetworkUtils.a(this.f6817c.renderPerformance(new RenderPerformanceRequest().setPerformanceKey(str).setRenderType(renderType)));
    }

    public Future<?> m8002a(String str, RenderType renderType, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 12(this, networkResponseCallback, str, renderType));
    }

    public PerformancesResponse m7992a(String str, Integer num, Integer num2) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformancesByTag(new GetPerformancesByTagRequest().setTerm(str).setOffset(num).setLimit(num2))));
    }

    public PerformancesResponse m7991a(Long l, String str) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformances(new GetPerformancesRequest().setPlayerId(l).setApp(str))));
    }

    public PerformancesResponse m7987a(long j, String str) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformances(new GetPerformancesRequest().setAccountId(Long.valueOf(j)).setApp(str))));
    }

    public Future<?> m8012a(List<String> list, List<String> list2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 16(this, networkResponseCallback, list, list2));
    }

    public Future<?> m7996a(long j, Integer num, Integer num2, PerformancesByPerformerResponseCallback performancesByPerformerResponseCallback) {
        return MagicNetwork.m7790a(new 17(this, performancesByPerformerResponseCallback, j, num, num2));
    }

    public Future<?> m8005a(String str, IsBookmarkSeedResponseCallback isBookmarkSeedResponseCallback) {
        return MagicNetwork.m7790a(new 18(this, isBookmarkSeedResponseCallback, str));
    }

    public Future<?> m8011a(ArrayList<String> arrayList, ArrayList<String> arrayList2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 19(this, networkResponseCallback, arrayList, arrayList2));
    }

    public Future<?> m8021b(long j, Integer num, Integer num2, PerformancesByPerformerResponseCallback performancesByPerformerResponseCallback) {
        return MagicNetwork.m7790a(new 20(this, performancesByPerformerResponseCallback, j, num, num2));
    }

    public PerformancesResponse m8029d(String str) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformancesByPerformer(new GetPerformancesByPerformerRequest().setAccountId(Long.valueOf(0)).setApp(str))));
    }

    public PerformancesByPerformerResponse m7985a(long j, String str, FillStatus fillStatus, Boolean bool, Integer num, Integer num2) {
        return PerformancesByPerformerResponse.m7971a(NetworkUtils.a(this.f6817c.getPerformancesByPerformer(new GetPerformancesByPerformerRequest().setAccountId(Long.valueOf(j)).setApp(str).setFillStatus(fillStatus, bool).setOffset(num).setLimit(num2))));
    }

    public Future<?> m7997a(long j, String str, FillStatus fillStatus, Boolean bool, Integer num, Integer num2, PerformancesByPerformerResponseCallback performancesByPerformerResponseCallback) {
        return MagicNetwork.m7790a(new 22(this, performancesByPerformerResponseCallback, j, str, fillStatus, bool, num, num2));
    }

    public GetConnectedPerformancesResponse m8033f() {
        return (GetConnectedPerformancesResponse) ParsedResponse.m7676a(NetworkUtils.a(this.f6817c.getConnectedPerformances(new SnpRequest())), GetConnectedPerformancesResponse.class);
    }

    public Future<?> m8000a(ConnectedPerformancesResponseCallback connectedPerformancesResponseCallback) {
        return MagicNetwork.m7790a(new 23(this, connectedPerformancesResponseCallback));
    }

    public PerformancesResponse m8020b(String str, Integer num, Integer num2) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceChildren(new GetPerformanceChildrenRequest().setPerformanceKey(str).setOffset(num).setLimit(num2))));
    }

    public Future<?> m8009a(String str, Integer num, Integer num2, PerformancesResponseCallback performancesResponseCallback) {
        return MagicNetwork.m7790a(new 24(this, performancesResponseCallback, str, num, num2));
    }

    public PerformanceResponse m7983a(String str, float f, float f2) {
        return PerformanceResponse.m7969a(NetworkUtils.a(this.f6817c.listen(new ListenRequest().setPerformanceKey(str).setLatAndLong(Float.valueOf(f), Float.valueOf(f2)))));
    }

    public NetworkResponse m7978a(String str, String str2, float f, float f2) {
        return NetworkUtils.a(this.f6817c.comment(new CommentPerformanceRequest().setPerformanceKey(str).setComment(str2).setLatAndLong(Float.valueOf(f), Float.valueOf(f2))));
    }

    public PerformancePostsResponse m8024c(String str, Integer num, Integer num2) {
        return PerformancePostsResponse.m7968a(NetworkUtils.a(this.f6817c.getComments(new GetCommentsRequest().setPerformanceKey(str).setOffset(num).setLimit(num2))));
    }

    public Future<?> m8008a(String str, Integer num, Integer num2, PerformancePostsResponseCallback performancePostsResponseCallback) {
        return MagicNetwork.m7790a(new 27(this, performancePostsResponseCallback, str, num, num2));
    }

    public NetworkResponse m8014b(String str, float f, float f2) {
        return NetworkUtils.a(this.f6817c.love(new LoveRequest().setPerformanceKey(str).setLatAndLong(Float.valueOf(f), Float.valueOf(f2))));
    }

    public Future<?> m8001a(String str, float f, float f2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 28(this, networkResponseCallback, str, f, f2));
    }

    public PerformanceLovesResponse m8030e(String str) {
        return PerformanceLovesResponse.m7967a(NetworkUtils.a(this.f6817c.getLoves(new GetLovesRequest().setPerformanceKey(str))));
    }

    public Future<?> m8006a(String str, PerformanceLovesResponseCallback performanceLovesResponseCallback) {
        return MagicNetwork.m7790a(new 29(this, performanceLovesResponseCallback, str));
    }

    public NetworkResponse m7980a(String str, ArrayList<String> arrayList) {
        return NetworkUtils.a(this.f6817c.deleteComments(new DeleteCommentsRequest().setPerformanceKey(str).setPostKeys(arrayList)));
    }

    public NetworkResponse m7977a(String str, String str2) {
        return NetworkUtils.a(this.f6817c.deleteComments(new DeleteCommentsRequest().setPerformanceKey(str).setPostKey(str2)));
    }

    public NetworkResponse m8032f(String str) {
        return NetworkUtils.a(this.f6817c.deletePerformance(new DeletePerformanceRequest().setPerformanceKey(str)));
    }

    public NetworkResponse m7979a(String str, String str2, float f, float f2, Long l) {
        return NetworkUtils.a(this.f6817c.comment(new CommentPerformanceRequest().setPerformanceKey(str).setLatAndLong(Float.valueOf(f), Float.valueOf(f2)).setComment(str2).setReplyToAccountId(l)));
    }

    public NetworkResponse m8016b(String str, ArrayList<String> arrayList) {
        return NetworkUtils.a(this.f6817c.reportAbuses(new ReportAbusesRequest().setPerformanceKey(str).setPostKeys(arrayList)));
    }

    public NetworkResponse m8015b(String str, String str2) {
        return NetworkUtils.a(this.f6817c.reportAbuses(new ReportAbusesRequest().setPerformanceKey(str).setPostKey(str2)));
    }

    public NetworkResponse m8034g(String str) {
        return NetworkUtils.a(this.f6817c.reportAbusivePerformance(new ReportAbusePerformanceRequest().setPerformanceKey(str)));
    }

    public Future<?> m8004a(String str, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 36(this, networkResponseCallback, str));
    }

    public PerformanceResponse m7984a(String str, String str2, String str3, Boolean bool, Boolean bool2) {
        return PerformanceResponse.m7969a(NetworkUtils.a(this.f6817c.updatePerformance(new UpdatePerformanceRequest().setPerformanceKey(str).setClose(bool2).setIsPrivate(bool).setMessage(str3).setTitle(str2))));
    }

    public Future<?> m8010a(String str, String str2, String str3, Boolean bool, Boolean bool2, PerformanceResponseCallback performanceResponseCallback) {
        return MagicNetwork.m7790a(new 37(this, performanceResponseCallback, str, str2, str3, bool, bool2));
    }

    public PerformancesResponse m7990a(Integer num, Integer num2) {
        return PerformancesResponse.m7972a(NetworkUtils.a(this.f6817c.getPerformanceList(new GetPerformanceListRequest().setOffset(num).setLimit(num2).setSort(SortOrder.HOT).setFillStatus(FillStatus.FILLED).setSongUid("_open_mic_5m"))));
    }

    public Future<?> m8023b(String str, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 39(this, str, networkResponseCallback));
    }

    public Future<?> m8027c(String str, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 40(this, str, networkResponseCallback));
    }

    public Future<PreuploadResponse> m7999a(UploadType uploadType, EnsembleType ensembleType, CompositionType compositionType, String str, String str2, String str3, boolean z) {
        return NetworkUtils.a(this.f6817c.preupload(new PreuploadRequest().setUploadType(uploadType).setEnsembleType(ensembleType).setCompType(compositionType).setSongId(str).setArrKey(str2).setSeedKey(str3).setTrackVideo(z)), PreuploadResponse.class);
    }

    public PreuploadResponse m7994a(String str, boolean z) {
        return PreuploadResponse.m7973a(NetworkUtils.a(this.f6817c.preupload(new PreuploadRequest().setUploadType(UploadType.UPDATE).setPerformanceKey(str).setTrackVideo(z))));
    }

    public CreateOrJoinResponse m7982a(String str, Integer num, String str2, String str3, String str4, Long l, Boolean bool, Float f, Float f2, String str5, Long l2, Long l3, Long l4, Long l5, Integer num2, Boolean bool2, Boolean bool3, Float f3, Float f4, EnsembleType ensembleType) {
        return CreateOrJoinResponse.m7965a(NetworkUtils.a(this.f6817c.createPerformance(new CreatePerfRequest().setCompType(str != null ? CompositionType.b : CompositionType.a).setArrangement(str, num.intValue()).setSongId(str2).setTitle(str3).setMessage(str4).setSeconds(l).setPrivate(bool).setLatAndLong(f, f2).setTrackOptions(str5).setAudioResourceId(l2).setCoverResourceId(l3).setMetadataResourceId(l4).setVideoResourceId(l5).setTrackPartId(num2).setTrackVideo(bool2).setUsedHeadphone(bool3).setVscore(f3).setVfactor(f4).setEnsembleType(ensembleType))));
    }

    public CreateOrJoinResponse m7981a(String str, Float f, Float f2, String str2, Long l, Long l2, Long l3, Long l4, Integer num, Boolean bool, Boolean bool2, Float f3, Float f4) {
        return CreateOrJoinResponse.m7965a(NetworkUtils.a(this.f6817c.joinPerformance(new JoinPerfRequest().setPerformanceKey(str).setLatAndLong(f, f2).setTrackOptions(str2).setAudioResourceId(l).setCoverResourceId(l2).setMetadataResourceId(l3).setVideoResourceId(l4).setTrackPartId(num).setTrackVideo(bool).setUsedHeadphone(bool2).setVscore(f3).setVfactor(f4))));
    }
}
