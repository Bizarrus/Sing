/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  org.json.JSONException
 *  org.json.JSONObject
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Round;
import com.smule.android.network.models.Track;
import com.smule.android.utils.JsonUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PerformanceV2
implements Parcelable {
    public static final Parcelable.Creator<PerformanceV2> CREATOR;
    private static final String b;
    Map<String, Object> a;
    @JsonProperty
    public AccountIcon accountIcon;
    @JsonProperty
    public String app;
    @JsonProperty
    public String arrKey;
    @JsonProperty(value="arrVersion")
    public ArrangementVersion arrangementVersion;
    @JsonProperty
    public String artist;
    @JsonIgnore
    public File backgroundTrackFileAbsolutePath;
    @JsonProperty
    public boolean boost;
    @JsonProperty
    public int childCount;
    @JsonProperty
    public boolean closed;
    @JsonProperty
    public String compType;
    @JsonProperty
    public String connectUrl;
    @JsonProperty
    public boolean coprDisabled;
    @JsonProperty
    public String coverUrl;
    @JsonProperty
    public int createdAt;
    @JsonProperty
    public int currentRound;
    @JsonProperty
    public String ensembleType;
    @JsonProperty
    public long expireAt;
    @JsonProperty
    public String filmstripUrl;
    @JsonIgnore
    public boolean hasBeenLoved;
    @JsonProperty
    public String highlightUrl;
    @JsonProperty
    public boolean isPrivate;
    @JsonProperty
    public String joinVideoUrl;
    @JsonProperty
    public long lastJoinTs;
    @JsonProperty
    public String longTermRenderedUrl;
    @JsonProperty
    public String message;
    @JsonIgnore
    public File metadataFile;
    @JsonProperty
    public List<Long> nextJoiners;
    @JsonProperty
    public String origTrackInstrumentId;
    @JsonProperty
    public String origTrackMetaUrl;
    @JsonProperty
    public String origTrackOptions;
    @JsonProperty
    public int origTrackPartId;
    @JsonProperty
    public String origTrackUrl;
    @JsonProperty
    public String origTrackVideoUrl;
    @JsonProperty
    public String parentPerformanceKey;
    @JsonProperty
    public String performanceKey;
    @JsonProperty
    public int playerId;
    @JsonProperty
    public List<Track> recentTracks;
    @JsonProperty(value="rm")
    public int removalCode;
    @JsonProperty
    public List<Round> rounds;
    @JsonProperty
    public boolean seed;
    @JsonProperty
    public String shortTermRenderedUrl;
    @JsonProperty
    public boolean songDShare;
    @JsonProperty
    @Deprecated
    public String songUid;
    @JsonProperty
    public String title;
    @JsonProperty
    public int totalComments;
    @JsonProperty
    public int totalListens;
    @JsonProperty
    public int totalLoves;
    @JsonProperty
    public int totalPerformers;
    @JsonProperty
    public int totalVideoTracks;
    @JsonIgnore
    public String vfxId;
    @JsonProperty
    public boolean video;
    @JsonProperty
    public String videoRenderedMp4Url;
    @JsonProperty
    public String videoRenderedUrl;
    @JsonProperty
    public String vocalRenderedUrl;
    @JsonProperty
    public String webUrl;

    static {
        b = PerformanceV2.class.getName();
        CREATOR = new Parcelable.Creator<PerformanceV2>(){

            public PerformanceV2 a(Parcel parcel) {
                return new PerformanceV2(parcel);
            }

            public PerformanceV2[] a(int n) {
                return new PerformanceV2[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public PerformanceV2() {
        this.recentTracks = new ArrayList<Track>();
        this.nextJoiners = new ArrayList<Long>();
        this.rounds = new ArrayList<Round>();
        this.hasBeenLoved = false;
        this.a = new HashMap<String, Object>();
    }

    /*
     * Enabled aggressive block sorting
     */
    public PerformanceV2(Parcel parcel) {
        boolean bl = true;
        this.recentTracks = new ArrayList<Track>();
        this.nextJoiners = new ArrayList<Long>();
        this.rounds = new ArrayList<Round>();
        this.hasBeenLoved = false;
        this.a = new HashMap<String, Object>();
        this.performanceKey = parcel.readString();
        this.playerId = parcel.readInt();
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
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
        boolean bl2 = parcel.readInt() != 0;
        this.isPrivate = bl2;
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
        bl2 = parcel.readInt() != 0;
        this.seed = bl2;
        this.expireAt = parcel.readLong();
        bl2 = parcel.readInt() != 0;
        this.closed = bl2;
        this.parentPerformanceKey = parcel.readString();
        this.compType = parcel.readString();
        this.arrangementVersion = (ArrangementVersion)parcel.readParcelable(ArrangementVersion.class.getClassLoader());
        bl2 = parcel.readInt() != 0;
        this.coprDisabled = bl2;
        bl2 = parcel.readInt() != 0;
        this.video = bl2;
        this.filmstripUrl = parcel.readString();
        this.totalVideoTracks = parcel.readInt();
        this.removalCode = parcel.readInt();
        bl2 = parcel.readInt() != 0;
        this.boost = bl2;
        bl2 = parcel.readInt() != 0 ? bl : false;
        this.songDShare = bl2;
        this.z();
    }

    private String B() {
        return this.accountIcon.handle + " + " + (this.totalPerformers - 1);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean A() {
        Object object = this.a.get("trans");
        if (!(object instanceof String)) {
            return true;
        }
        if (((String)object).equals("no")) return false;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String a(boolean bl) {
        if (this.f()) {
            return this.B();
        }
        String string2 = this.accountIcon.handle;
        if (this.recentTracks == null) return string2;
        int n = this.recentTracks.size();
        if (n > 0 && (this.accountIcon.accountId != this.recentTracks.get((int)0).accountIcon.accountId || bl)) {
            string2 = string2 + " + " + this.recentTracks.get((int)0).accountIcon.handle;
        }
        String string3 = string2;
        if (n <= 1) return string3;
        return string2 + " and others";
    }

    public boolean a() {
        if (!this.video && this.b() || this.video && this.c()) {
            return true;
        }
        return false;
    }

    public boolean a(long l) {
        if (this.recentTracks == null || l == 0) {
            return false;
        }
        Iterator<Track> iterator = this.recentTracks.iterator();
        while (iterator.hasNext()) {
            if (l != iterator.next().accountIcon.accountId) continue;
            return true;
        }
        return false;
    }

    public boolean b() {
        if (!TextUtils.isEmpty((CharSequence)this.shortTermRenderedUrl)) {
            return true;
        }
        return false;
    }

    public boolean c() {
        if (!TextUtils.isEmpty((CharSequence)this.videoRenderedUrl)) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.j() == .a) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return "DUET".equals(this.ensembleType);
    }

    public boolean f() {
        return "GROUP".equals(this.ensembleType);
    }

    public boolean g() {
        return this.video;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public String h() {
        boolean bl;
        String string2;
        boolean bl2 = false;
        String string3 = "";
        try {
            JSONObject jSONObject = new JSONObject(this.origTrackOptions).optJSONObject("video_filter_map");
            string2 = string3;
            bl = bl2;
            if (jSONObject != null) {
                string2 = jSONObject.optString("color");
                bl = true;
            }
        }
        catch (JSONException jSONException) {
            Log.e(b, "error parsing json object: " + (Object)jSONException);
            string2 = string3;
            bl = bl2;
        }
        string3 = string2;
        if (bl) return string3;
        try {
            return new JSONObject(this.origTrackOptions).optString("vfx");
        }
        catch (JSONException jSONException) {
            Log.e(b, "error parsing json object: " + (Object)jSONException);
            return string2;
        }
    }

    public boolean i() {
        boolean bl;
        block3 : {
            JSONObject jSONObject;
            bl = false;
            try {
                jSONObject = new JSONObject(this.origTrackOptions).optJSONObject("video_filter_map");
                if (jSONObject == null) break block3;
            }
            catch (JSONException jSONException) {
                Log.e(b, "error parsing json object: " + (Object)jSONException);
                return false;
            }
            bl = jSONObject.optBoolean("airbrush");
        }
        return bl;
    }

    public  j() {
        if (this.e()) {
            return .b;
        }
        if (this.f()) {
            return .c;
        }
        return .a;
    }

    public boolean k() {
        if (System.currentTimeMillis() / 1000 > this.expireAt) {
            return true;
        }
        return false;
    }

    public long l() {
        long l = System.currentTimeMillis() / 1000;
        return this.expireAt - l;
    }

    public boolean m() {
        if (this.n() && !this.closed && !this.k()) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if (UserManager.a().f() == this.accountIcon.accountId) {
            return true;
        }
        return false;
    }

    public boolean o() {
        if (this.n()) {
            return true;
        }
        long l = UserManager.a().f();
        for (Track track : this.recentTracks) {
            if (track == null || track.accountIcon == null || l != track.accountIcon.accountId) continue;
            return true;
        }
        return false;
    }

    public boolean p() {
        if (this.seed && !this.k() && !this.closed) {
            return true;
        }
        return false;
    }

    public boolean q() {
        if (MagicNetwork.d().getBoostEnabled() && this.p() && this.n()) {
            return true;
        }
        return false;
    }

    public boolean r() {
        if (!this.n() && this.boost) {
            return true;
        }
        return false;
    }

    public String s() {
        return this.a(false);
    }

    public boolean t() {
        return this.a(UserManager.a().f());
    }

    public String toString() {
        return "PerformanceV2{performanceKey='" + this.performanceKey + '\'' + ", playerId=" + this.playerId + ", accountIcon=" + this.accountIcon + ", songUid='" + this.songUid + '\'' + ", title='" + this.title + '\'' + ", artist='" + this.artist + '\'' + ", message='" + this.message + '\'' + ", shortTermRenderedUrl='" + this.shortTermRenderedUrl + '\'' + ", vocalRenderedUrl='" + this.vocalRenderedUrl + '\'' + ", longTermRenderedUrl='" + this.longTermRenderedUrl + '\'' + ", videoRenderedUrl='" + this.videoRenderedUrl + '\'' + ", videoRenderedMp4Url='" + this.videoRenderedMp4Url + '\'' + ", origTrackVideoUrl='" + this.origTrackVideoUrl + '\'' + ", joinVideoUrl='" + this.joinVideoUrl + '\'' + ", filmstripUrl='" + this.filmstripUrl + '\'' + ", webUrl='" + this.webUrl + '\'' + ", connectUrl='" + this.connectUrl + '\'' + ", origTrackUrl='" + this.origTrackUrl + '\'' + ", origTrackMetaUrl='" + this.origTrackMetaUrl + '\'' + ", origTrackOptions='" + this.origTrackOptions + '\'' + ", origTrackInstrumentId='" + this.origTrackInstrumentId + '\'' + ", coverUrl='" + this.coverUrl + '\'' + ", totalLoves=" + this.totalLoves + ", totalComments=" + this.totalComments + ", totalPerformers=" + this.totalPerformers + ", totalVideoTracks=" + this.totalVideoTracks + ", createdAt=" + this.createdAt + ", origTrackPartId=" + this.origTrackPartId + ", recentTracks=" + this.recentTracks + ", app='" + this.app + '\'' + ", isPrivate=" + this.isPrivate + ", totalListens=" + this.totalListens + ", ensembleType='" + this.ensembleType + '\'' + ", childCount=" + this.childCount + ", currentRound=" + this.currentRound + ", lastJoinTs=" + this.lastJoinTs + ", nextJoiners=" + this.nextJoiners + ", rounds=" + this.rounds + ", seed=" + this.seed + ", expireAt=" + this.expireAt + ", closed=" + this.closed + ", parentPerformanceKey='" + this.parentPerformanceKey + '\'' + ", compType='" + this.compType + '\'' + ", arrangementVersion=" + this.arrangementVersion + ", coprDisabled=" + this.coprDisabled + ", video=" + this.video + ", backgroundTrackFileAbsolutePath=" + this.backgroundTrackFileAbsolutePath + ", metadataFile=" + this.metadataFile + ", hasBeenLoved=" + this.hasBeenLoved + ", removalCode=" + this.removalCode + ", boost=" + this.boost + ", songDShare=" + this.songDShare + '}';
    }

    @Deprecated
    public CompositionType u() {
        if (this.compType == null) {
            return CompositionType.a;
        }
        return CompositionType.valueOf(this.compType);
    }

    public boolean v() {
        if (this.u() == CompositionType.b) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean w() {
        if (this.compType == null || CompositionType.valueOf(this.compType) != CompositionType.a) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeString(this.performanceKey);
        parcel.writeInt(this.playerId);
        parcel.writeParcelable((Parcelable)this.accountIcon, 0);
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
        n = this.isPrivate ? 1 : 0;
        parcel.writeInt(n);
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
        n = this.seed ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeLong(this.expireAt);
        n = this.closed ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeString(this.parentPerformanceKey);
        parcel.writeString(this.compType);
        parcel.writeParcelable((Parcelable)this.arrangementVersion, 0);
        n = this.coprDisabled ? 1 : 0;
        parcel.writeInt(n);
        n = this.video ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeString(this.filmstripUrl);
        parcel.writeInt(this.totalVideoTracks);
        parcel.writeInt(this.removalCode);
        n = this.boost ? 1 : 0;
        parcel.writeInt(n);
        n = this.songDShare ? n2 : 0;
        parcel.writeInt(n);
    }

    public boolean x() {
        if (this.v() && this.coprDisabled) {
            return true;
        }
        return false;
    }

    public String y() {
        if (!this.k() && this.p() && this.connectUrl != null && !this.connectUrl.isEmpty()) {
            return this.connectUrl;
        }
        if (this.webUrl != null && this.webUrl.length() > 0) {
            return this.webUrl;
        }
        if (this.shortTermRenderedUrl.length() > 0) {
            return this.shortTermRenderedUrl;
        }
        Log.e(b, "showShareIntent - no valid URL found");
        return "";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void z() {
        try {
            this.a = (Map)JsonUtils.a().readValue(this.origTrackOptions, HashMap.class);
        }
        catch (Exception exception) {
            Log.e(b, "unable to parse track options");
        }
        if (this.a == null) {
            this.a = new HashMap<String, Object>();
        }
    }

    public static enum CompositionType {
        a,
        b;
        

        private CompositionType() {
        }
    }

}

