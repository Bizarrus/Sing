package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.JsonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PerformanceV2 implements Parcelable {
    public static final Creator<PerformanceV2> CREATOR = new 1();
    private static final String f6891b = PerformanceV2.class.getName();
    Map<String, Object> f6892a = new HashMap();
    @JsonProperty("accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("app")
    public String app;
    @JsonProperty("arrKey")
    public String arrKey;
    @JsonProperty("arrVersion")
    public ArrangementVersion arrangementVersion;
    @JsonProperty("artist")
    public String artist;
    @JsonIgnore
    public File backgroundTrackFileAbsolutePath;
    @JsonProperty("childCount")
    public int childCount;
    @JsonProperty("closed")
    public boolean closed;
    @JsonProperty("compType")
    public String compType;
    @JsonProperty("connectUrl")
    public String connectUrl;
    @JsonProperty("coprDisable")
    public boolean coprDisabled;
    @JsonProperty("coverUrl")
    public String coverUrl;
    @JsonProperty("createdAt")
    public int createdAt;
    @JsonProperty("currentRound")
    public int currentRound;
    @JsonProperty("ensembleType")
    public String ensembleType;
    @JsonProperty("expireAt")
    public long expireAt;
    @JsonProperty("filmstripUrl")
    public String filmstripUrl;
    @JsonIgnore
    public boolean hasBeenLoved = false;
    @JsonProperty("highlightUrl")
    public String highlightUrl;
    @JsonProperty("isPrivate")
    public boolean isPrivate;
    @JsonProperty("joinVideoUrl")
    public String joinVideoUrl;
    @JsonProperty("lastJoinTs")
    public long lastJoinTs;
    @JsonProperty("longTermRenderedUrl")
    public String longTermRenderedUrl;
    @JsonProperty("message")
    public String message;
    @JsonIgnore
    public File metadataFile;
    @JsonProperty("nextJoiners")
    public List<Long> nextJoiners = new ArrayList();
    @JsonProperty("origTrackInstrumentId")
    public String origTrackInstrumentId;
    @JsonProperty("origTrackMetaUrl")
    public String origTrackMetaUrl;
    @JsonProperty("origTrackOptions")
    public String origTrackOptions;
    @JsonProperty("origTrackPartId")
    public int origTrackPartId;
    @JsonProperty("origTrackUrl")
    public String origTrackUrl;
    @JsonProperty("origTrackVideoUrl")
    public String origTrackVideoUrl;
    @JsonProperty("parentPerformanceKey")
    public String parentPerformanceKey;
    @JsonProperty("performanceKey")
    public String performanceKey;
    @JsonProperty("playerId")
    public int playerId;
    @JsonProperty("recentTracks")
    public List<Track> recentTracks = new ArrayList();
    @JsonProperty("rm")
    public int removalCode;
    @JsonProperty("rounds")
    public List<Round> rounds = new ArrayList();
    @JsonProperty("seed")
    public boolean seed;
    @JsonProperty("shortTermRenderedUrl")
    public String shortTermRenderedUrl;
    @JsonProperty("songUid")
    public String songUid;
    @JsonProperty("title")
    public String title;
    @JsonProperty("totalComments")
    public int totalComments;
    @JsonProperty("totalListens")
    public int totalListens;
    @JsonProperty("totalLoves")
    public int totalLoves;
    @JsonProperty("totalPerformers")
    public int totalPerformers;
    @JsonProperty("totalVideoTracks")
    public int totalVideoTracks;
    @JsonIgnore
    public String vfxId;
    @JsonProperty("video")
    public boolean video;
    @JsonProperty("videoRenderedMp4Url")
    public String videoRenderedMp4Url;
    @JsonProperty("videoRenderedUrl")
    public String videoRenderedUrl;
    @JsonProperty("vocalRenderedUrl")
    public String vocalRenderedUrl;
    @JsonProperty("webUrl")
    public String webUrl;

    public boolean m8266a() {
        return (!this.video && m8268b()) || (this.video && m8269c());
    }

    public boolean m8268b() {
        return !TextUtils.isEmpty(this.shortTermRenderedUrl);
    }

    public boolean m8269c() {
        return !TextUtils.isEmpty(this.videoRenderedUrl);
    }

    public String toString() {
        return "PerformanceV2{performanceKey='" + this.performanceKey + '\'' + ", playerId=" + this.playerId + ", accountIcon=" + this.accountIcon + ", songUid='" + this.songUid + '\'' + ", title='" + this.title + '\'' + ", artist='" + this.artist + '\'' + ", message='" + this.message + '\'' + ", shortTermRenderedUrl='" + this.shortTermRenderedUrl + '\'' + ", vocalRenderedUrl='" + this.vocalRenderedUrl + '\'' + ", longTermRenderedUrl='" + this.longTermRenderedUrl + '\'' + ", videoRenderedUrl='" + this.videoRenderedUrl + '\'' + ", videoRenderedMp4Url='" + this.videoRenderedMp4Url + '\'' + ", origTrackVideoUrl='" + this.origTrackVideoUrl + '\'' + ", joinVideoUrl='" + this.joinVideoUrl + '\'' + ", filmstripUrl='" + this.filmstripUrl + '\'' + ", webUrl='" + this.webUrl + '\'' + ", connectUrl='" + this.connectUrl + '\'' + ", origTrackUrl='" + this.origTrackUrl + '\'' + ", origTrackMetaUrl='" + this.origTrackMetaUrl + '\'' + ", origTrackOptions='" + this.origTrackOptions + '\'' + ", origTrackInstrumentId='" + this.origTrackInstrumentId + '\'' + ", coverUrl='" + this.coverUrl + '\'' + ", totalLoves=" + this.totalLoves + ", totalComments=" + this.totalComments + ", totalPerformers=" + this.totalPerformers + ", totalVideoTracks=" + this.totalVideoTracks + ", createdAt=" + this.createdAt + ", origTrackPartId=" + this.origTrackPartId + ", recentTracks=" + this.recentTracks + ", app='" + this.app + '\'' + ", isPrivate=" + this.isPrivate + ", totalListens=" + this.totalListens + ", ensembleType='" + this.ensembleType + '\'' + ", childCount=" + this.childCount + ", currentRound=" + this.currentRound + ", lastJoinTs=" + this.lastJoinTs + ", nextJoiners=" + this.nextJoiners + ", rounds=" + this.rounds + ", seed=" + this.seed + ", expireAt=" + this.expireAt + ", closed=" + this.closed + ", parentPerformanceKey='" + this.parentPerformanceKey + '\'' + ", compType='" + this.compType + '\'' + ", arrangementVersion=" + this.arrangementVersion + ", coprDisabled=" + this.coprDisabled + ", video=" + this.video + ", backgroundTrackFileAbsolutePath=" + this.backgroundTrackFileAbsolutePath + ", metadataFile=" + this.metadataFile + ", hasBeenLoved=" + this.hasBeenLoved + ", removalCode=" + this.removalCode + '}';
    }

    public PerformanceV2(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.performanceKey = parcel.readString();
        this.playerId = parcel.readInt();
        this.accountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.songUid = parcel.readString();
        this.title = parcel.readString();
        this.artist = parcel.readString();
        this.message = parcel.readString();
        this.shortTermRenderedUrl = parcel.readString();
        this.longTermRenderedUrl = parcel.readString();
        this.videoRenderedUrl = parcel.readString();
        this.videoRenderedMp4Url = parcel.readString();
        this.origTrackVideoUrl = parcel.readString();
        this.joinVideoUrl = parcel.readString();
        this.webUrl = parcel.readString();
        this.connectUrl = parcel.readString();
        this.origTrackUrl = parcel.readString();
        this.origTrackMetaUrl = parcel.readString();
        this.coverUrl = parcel.readString();
        this.totalLoves = parcel.readInt();
        this.totalComments = parcel.readInt();
        this.createdAt = parcel.readInt();
        this.totalPerformers = parcel.readInt();
        this.origTrackPartId = parcel.readInt();
        this.recentTracks = parcel.readArrayList(AccountIcon.class.getClassLoader());
        this.app = parcel.readString();
        this.isPrivate = parcel.readInt() != 0;
        this.totalListens = parcel.readInt();
        this.ensembleType = parcel.readString();
        this.vocalRenderedUrl = parcel.readString();
        this.origTrackOptions = parcel.readString();
        this.origTrackInstrumentId = parcel.readString();
        this.childCount = parcel.readInt();
        this.currentRound = parcel.readInt();
        this.lastJoinTs = parcel.readLong();
        this.nextJoiners = parcel.readArrayList(Long.class.getClassLoader());
        this.rounds = parcel.readArrayList(Round.class.getClassLoader());
        this.seed = parcel.readInt() != 0;
        this.expireAt = parcel.readLong();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.closed = z;
        this.parentPerformanceKey = parcel.readString();
        this.compType = parcel.readString();
        this.arrangementVersion = (ArrangementVersion) parcel.readParcelable(ArrangementVersion.class.getClassLoader());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.coprDisabled = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.video = z2;
        this.filmstripUrl = parcel.readString();
        this.totalVideoTracks = parcel.readInt();
        this.removalCode = parcel.readInt();
        m8288v();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.performanceKey);
        parcel.writeInt(this.playerId);
        parcel.writeParcelable(this.accountIcon, 0);
        parcel.writeString(this.songUid);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.message);
        parcel.writeString(this.shortTermRenderedUrl);
        parcel.writeString(this.longTermRenderedUrl);
        parcel.writeString(this.videoRenderedUrl);
        parcel.writeString(this.videoRenderedMp4Url);
        parcel.writeString(this.origTrackVideoUrl);
        parcel.writeString(this.joinVideoUrl);
        parcel.writeString(this.webUrl);
        parcel.writeString(this.connectUrl);
        parcel.writeString(this.origTrackUrl);
        parcel.writeString(this.origTrackMetaUrl);
        parcel.writeString(this.coverUrl);
        parcel.writeInt(this.totalLoves);
        parcel.writeInt(this.totalComments);
        parcel.writeInt(this.createdAt);
        parcel.writeInt(this.totalPerformers);
        parcel.writeInt(this.origTrackPartId);
        parcel.writeList(this.recentTracks);
        parcel.writeString(this.app);
        parcel.writeInt(this.isPrivate ? 1 : 0);
        parcel.writeInt(this.totalListens);
        parcel.writeString(this.ensembleType);
        parcel.writeString(this.vocalRenderedUrl);
        parcel.writeString(this.origTrackOptions);
        parcel.writeString(this.origTrackInstrumentId);
        parcel.writeInt(this.childCount);
        parcel.writeInt(this.currentRound);
        parcel.writeLong(this.lastJoinTs);
        parcel.writeList(this.nextJoiners);
        parcel.writeList(this.rounds);
        parcel.writeInt(this.seed ? 1 : 0);
        parcel.writeLong(this.expireAt);
        if (this.closed) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        parcel.writeString(this.parentPerformanceKey);
        parcel.writeString(this.compType);
        parcel.writeParcelable(this.arrangementVersion, 0);
        if (this.coprDisabled) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.video) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeString(this.filmstripUrl);
        parcel.writeInt(this.totalVideoTracks);
        parcel.writeInt(this.removalCode);
    }

    public boolean m8270d() {
        return "DUET".equals(this.ensembleType);
    }

    public boolean m8271e() {
        return "GROUP".equals(this.ensembleType);
    }

    public boolean m8272f() {
        return this.video;
    }

    public String m8273g() {
        Object obj = null;
        String str = "";
        try {
            JSONObject optJSONObject = new JSONObject(this.origTrackOptions).optJSONObject("video_filter_map");
            if (optJSONObject != null) {
                str = optJSONObject.optString("color");
                obj = 1;
            }
        } catch (JSONException e) {
            Log.m7776e(f6891b, "error parsing json object: " + e);
        }
        if (obj == null) {
            try {
                str = new JSONObject(this.origTrackOptions).optString("vfx");
            } catch (JSONException e2) {
                Log.m7776e(f6891b, "error parsing json object: " + e2);
            }
        }
        return str;
    }

    public boolean m8274h() {
        boolean z = false;
        try {
            JSONObject optJSONObject = new JSONObject(this.origTrackOptions).optJSONObject("video_filter_map");
            if (optJSONObject != null) {
                z = optJSONObject.optBoolean("airbrush");
            }
        } catch (JSONException e) {
            Log.m7776e(f6891b, "error parsing json object: " + e);
        }
        return z;
    }

    public PerformanceType m8275i() {
        if (m8270d()) {
            return PerformanceType.b;
        }
        if (m8271e()) {
            return PerformanceType.c;
        }
        return PerformanceType.a;
    }

    public boolean m8276j() {
        return System.currentTimeMillis() / 1000 > this.expireAt;
    }

    public boolean m8277k() {
        return (!m8278l() || this.closed || m8276j()) ? false : true;
    }

    public boolean m8278l() {
        return UserManager.m8111a().m8203f() == this.accountIcon.accountId;
    }

    public boolean m8279m() {
        if (m8278l()) {
            return true;
        }
        long f = UserManager.m8111a().m8203f();
        for (Track track : this.recentTracks) {
            if (track != null && track.accountIcon != null && f == track.accountIcon.accountId) {
                return true;
            }
        }
        return false;
    }

    public boolean m8280n() {
        return (!this.seed || m8276j() || this.closed) ? false : true;
    }

    public String m8265a(boolean z) {
        if (m8271e()) {
            return m8264x();
        }
        String str = this.accountIcon.handle;
        if (this.recentTracks == null) {
            return str;
        }
        String str2;
        int size = this.recentTracks.size();
        if (size <= 0 || (this.accountIcon.accountId == ((Track) this.recentTracks.get(0)).accountIcon.accountId && !z)) {
            str2 = str;
        } else {
            str2 = str + " + " + ((Track) this.recentTracks.get(0)).accountIcon.handle;
        }
        if (size > 1) {
            return str2 + " and others";
        }
        return str2;
    }

    public String m8281o() {
        return m8265a(false);
    }

    private String m8264x() {
        return this.accountIcon.handle + " + " + (this.totalPerformers - 1);
    }

    public boolean m8282p() {
        return m8267a(UserManager.m8111a().m8203f());
    }

    public boolean m8267a(long j) {
        if (this.recentTracks == null || j == 0) {
            return false;
        }
        for (Track track : this.recentTracks) {
            if (j == track.accountIcon.accountId) {
                return true;
            }
        }
        return false;
    }

    public CompositionType m8283q() {
        if (this.compType == null) {
            return CompositionType.a;
        }
        return CompositionType.valueOf(this.compType);
    }

    public boolean m8284r() {
        return m8283q() == CompositionType.b;
    }

    public boolean m8285s() {
        return m8283q() == CompositionType.a;
    }

    public boolean m8286t() {
        return m8284r() && this.coprDisabled;
    }

    public String m8287u() {
        if (!m8276j() && m8280n() && this.connectUrl != null && !this.connectUrl.isEmpty()) {
            return this.connectUrl;
        }
        if (this.webUrl != null && this.webUrl.length() > 0) {
            return this.webUrl;
        }
        if (this.shortTermRenderedUrl.length() > 0) {
            return this.shortTermRenderedUrl;
        }
        Log.m7776e(f6891b, "showShareIntent - no valid URL found");
        return "";
    }

    public void m8288v() {
        try {
            this.f6892a = (Map) JsonUtils.a().readValue(this.origTrackOptions, HashMap.class);
        } catch (Exception e) {
            Log.m7776e(f6891b, "unable to parse track options");
        }
        if (this.f6892a == null) {
            this.f6892a = new HashMap();
        }
    }

    public boolean m8289w() {
        Object obj = this.f6892a.get("trans");
        if (!(obj instanceof String)) {
            return true;
        }
        return !((String) obj).equals("no");
    }
}
