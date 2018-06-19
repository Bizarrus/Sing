package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@LocalizationObject(a = "song")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongV2 implements Parcelable {
    public static final Creator<SongV2> CREATOR = new 1();
    @JsonIgnore
    private static Set<String> unnecessaryResourcesForJam = new HashSet();
    @JsonIgnore
    private static Set<String> unnecessaryResourcesForOriginal = new HashSet();
    @JsonProperty("artist")
    public String artist;
    @JsonProperty("artistGender")
    public String artistGender;
    @JsonProperty("artistTwitter")
    public String artistTwitter;
    @JsonProperty("difficulty")
    public int difficulty;
    @JsonIgnore
    public String eTag;
    @JsonProperty("enabled")
    public boolean enabled;
    @JsonProperty("extraData")
    public String extraData;
    @JsonProperty("genre")
    public String genre;
    @JsonProperty("globe")
    public boolean globe;
    @JsonProperty("googleCoverArtUrl")
    public String googleCoverArtUrl;
    @JsonProperty("length")
    public int length = -1;
    @JsonIgnore
    public String newEtag;
    @JsonProperty("partnerArtist")
    public PartnerArtistV2 partnerArtist;
    @JsonProperty("performanceKey")
    public String performanceKey;
    @JsonProperty("previewUrl")
    public String previewUrl;
    @JsonProperty("releaseYear")
    public int releaseYear;
    @JsonIgnore
    public ConcurrentHashMap<String, String> resourceFilePaths = new ConcurrentHashMap();
    @JsonProperty("resources")
    public List<ResourceV2> resources = new ArrayList();
    @JsonProperty("songId")
    @LocalizationObjectId
    public String songId;
    @JsonProperty("tags")
    public List<String> tags;
    @JsonProperty("title")
    public String title;
    @JsonIgnore
    public int totalPlayCount;
    @JsonProperty("ts")
    public long ts;
    @JsonProperty("webUrl")
    public String webUrl;

    static {
        unnecessaryResourcesForOriginal.add("vocal_only");
        unnecessaryResourcesForOriginal.add("accomp");
        unnecessaryResourcesForJam.add("main");
    }

    public boolean m8292a() {
        if (this.newEtag == null) {
            return false;
        }
        if (this.eTag == null) {
            return true;
        }
        if (this.newEtag.equals(this.eTag)) {
            return false;
        }
        return true;
    }

    public ResourceV2 m8290a(String str) {
        if (this.resources == null) {
            return null;
        }
        for (ResourceV2 resourceV2 : this.resources) {
            if (resourceV2.role.equals(str)) {
                return resourceV2;
            }
        }
        return null;
    }

    public void m8291a(SongV2 songV2) {
        this.artist = songV2.artist;
        this.artistGender = songV2.artistGender;
        this.releaseYear = songV2.releaseYear;
        this.title = songV2.title;
        this.genre = songV2.genre;
        this.webUrl = songV2.webUrl;
        this.eTag = songV2.eTag;
        this.extraData = songV2.extraData;
        this.performanceKey = songV2.performanceKey;
        this.artistTwitter = songV2.artistTwitter;
        this.tags = songV2.tags;
        this.resources = songV2.resources;
        this.length = songV2.length;
        this.totalPlayCount = songV2.totalPlayCount;
    }

    public SongV2(Parcel parcel) {
        boolean z = true;
        this.songId = parcel.readString();
        this.ts = parcel.readLong();
        this.title = parcel.readString();
        this.artist = parcel.readString();
        this.artistGender = parcel.readString();
        this.releaseYear = parcel.readInt();
        this.previewUrl = parcel.readString();
        this.performanceKey = parcel.readString();
        this.genre = parcel.readString();
        this.extraData = parcel.readString();
        this.globe = parcel.readByte() == (byte) 1;
        this.difficulty = parcel.readInt();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.enabled = z;
        this.partnerArtist = (PartnerArtistV2) parcel.readParcelable(PartnerArtistV2.class.getClassLoader());
        this.webUrl = parcel.readString();
        this.eTag = parcel.readString();
        this.newEtag = parcel.readString();
        this.googleCoverArtUrl = parcel.readString();
        this.length = parcel.readInt();
        this.resourceFilePaths = (ConcurrentHashMap) parcel.readSerializable();
        this.artistTwitter = parcel.readString();
        this.tags = new ArrayList();
        parcel.readStringList(this.tags);
        parcel.readTypedList(this.resources, ResourceV2.CREATOR);
        this.totalPlayCount = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeString(this.songId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.artistGender);
        parcel.writeInt(this.releaseYear);
        parcel.writeString(this.previewUrl == null ? "" : this.previewUrl);
        parcel.writeString(this.performanceKey == null ? "" : this.performanceKey);
        parcel.writeString(this.genre == null ? "" : this.genre);
        parcel.writeString(this.extraData == null ? "" : this.extraData);
        if (this.globe) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeInt(this.difficulty);
        if (!this.enabled) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeParcelable(this.partnerArtist, 0);
        parcel.writeString(this.webUrl == null ? "" : this.webUrl);
        parcel.writeString(this.eTag == null ? "" : this.eTag);
        parcel.writeString(this.newEtag == null ? "" : this.newEtag);
        parcel.writeString(this.googleCoverArtUrl == null ? "" : this.googleCoverArtUrl);
        parcel.writeInt(this.length);
        parcel.writeSerializable(this.resourceFilePaths);
        parcel.writeString(this.artistTwitter);
        parcel.writeStringList(this.tags);
        parcel.writeTypedList(this.resources);
        parcel.writeInt(this.totalPlayCount);
    }

    public int describeContents() {
        return 0;
    }
}
