/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import com.smule.android.network.models.PartnerArtistV2;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@JsonIgnoreProperties(ignoreUnknown=true)
@LocalizationObject(a="song")
@Deprecated
public class SongV2
implements Parcelable {
    public static final Parcelable.Creator<SongV2> CREATOR;
    @JsonIgnore
    private static Set<String> unnecessaryResourcesForJam;
    @JsonIgnore
    private static Set<String> unnecessaryResourcesForOriginal;
    @JsonProperty(value="artist")
    public String artist;
    @JsonProperty(value="artistGender")
    public String artistGender;
    @JsonProperty(value="artistTwitter")
    public String artistTwitter;
    @JsonProperty(value="difficulty")
    public int difficulty;
    @JsonIgnore
    public String eTag;
    @JsonProperty(value="enabled")
    public boolean enabled;
    @JsonProperty(value="extraData")
    public String extraData;
    @JsonProperty(value="genre")
    public String genre;
    @JsonProperty(value="globe")
    public boolean globe;
    @JsonProperty(value="googleCoverArtUrl")
    public String googleCoverArtUrl;
    @JsonProperty(value="length")
    public int length;
    @JsonIgnore
    public String newEtag;
    @JsonProperty(value="partnerArtist")
    public PartnerArtistV2 partnerArtist;
    @JsonProperty(value="performanceKey")
    public String performanceKey;
    @JsonProperty(value="previewUrl")
    public String previewUrl;
    @JsonProperty(value="releaseYear")
    public int releaseYear;
    @JsonIgnore
    public ConcurrentHashMap<String, String> resourceFilePaths;
    @JsonProperty(value="resources")
    public List<ResourceV2> resources;
    @JsonProperty(value="songId")
    @LocalizationObjectId
    public String songId;
    @JsonProperty(value="tags")
    public List<String> tags;
    @JsonProperty(value="title")
    public String title;
    @JsonIgnore
    public int totalPlayCount;
    @JsonProperty(value="ts")
    public long ts;
    @JsonProperty(value="webUrl")
    public String webUrl;

    static {
        unnecessaryResourcesForOriginal = new HashSet<String>();
        unnecessaryResourcesForJam = new HashSet<String>();
        unnecessaryResourcesForOriginal.add("vocal_only");
        unnecessaryResourcesForOriginal.add("accomp");
        unnecessaryResourcesForJam.add("main");
        CREATOR = new Parcelable.Creator<SongV2>(){

            public SongV2 a(Parcel parcel) {
                return new SongV2(parcel);
            }

            public SongV2[] a(int n) {
                return new SongV2[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public SongV2() {
        this.resources = new ArrayList<ResourceV2>();
        this.length = -1;
        this.resourceFilePaths = new ConcurrentHashMap();
    }

    /*
     * Enabled aggressive block sorting
     */
    public SongV2(Parcel parcel) {
        boolean bl = true;
        this.resources = new ArrayList<ResourceV2>();
        this.length = -1;
        this.resourceFilePaths = new ConcurrentHashMap();
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
        boolean bl2 = parcel.readByte() == 1;
        this.globe = bl2;
        this.difficulty = parcel.readInt();
        bl2 = parcel.readByte() == 1 ? bl : false;
        this.enabled = bl2;
        this.partnerArtist = (PartnerArtistV2)parcel.readParcelable(PartnerArtistV2.class.getClassLoader());
        this.webUrl = parcel.readString();
        this.eTag = parcel.readString();
        this.newEtag = parcel.readString();
        this.googleCoverArtUrl = parcel.readString();
        this.length = parcel.readInt();
        this.resourceFilePaths = (ConcurrentHashMap)parcel.readSerializable();
        this.artistTwitter = parcel.readString();
        this.tags = new ArrayList<String>();
        parcel.readStringList(this.tags);
        parcel.readTypedList(this.resources, ResourceV2.CREATOR);
        this.totalPlayCount = parcel.readInt();
    }

    public ResourceV2 a(String string2) {
        if (this.resources == null) {
            return null;
        }
        for (ResourceV2 resourceV2 : this.resources) {
            if (!resourceV2.role.equals(string2)) continue;
            return resourceV2;
        }
        return null;
    }

    public void a(SongV2 songV2) {
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

    /*
     * Enabled aggressive block sorting
     */
    public boolean a() {
        block5 : {
            block4 : {
                if (this.newEtag == null) break block4;
                if (this.eTag == null) {
                    return true;
                }
                if (!this.newEtag.equals(this.eTag)) break block5;
            }
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeString(this.songId);
        parcel.writeLong(this.ts);
        parcel.writeString(this.title);
        parcel.writeString(this.artist);
        parcel.writeString(this.artistGender);
        parcel.writeInt(this.releaseYear);
        String string2 = this.previewUrl == null ? "" : this.previewUrl;
        parcel.writeString(string2);
        string2 = this.performanceKey == null ? "" : this.performanceKey;
        parcel.writeString(string2);
        string2 = this.genre == null ? "" : this.genre;
        parcel.writeString(string2);
        string2 = this.extraData == null ? "" : this.extraData;
        parcel.writeString(string2);
        n = this.globe ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeInt(this.difficulty);
        n = this.enabled ? n2 : 0;
        parcel.writeByte((byte)n);
        parcel.writeParcelable((Parcelable)this.partnerArtist, 0);
        string2 = this.webUrl == null ? "" : this.webUrl;
        parcel.writeString(string2);
        string2 = this.eTag == null ? "" : this.eTag;
        parcel.writeString(string2);
        string2 = this.newEtag == null ? "" : this.newEtag;
        parcel.writeString(string2);
        string2 = this.googleCoverArtUrl == null ? "" : this.googleCoverArtUrl;
        parcel.writeString(string2);
        parcel.writeInt(this.length);
        parcel.writeSerializable(this.resourceFilePaths);
        parcel.writeString(this.artistTwitter);
        parcel.writeStringList(this.tags);
        parcel.writeTypedList(this.resources);
        parcel.writeInt(this.totalPlayCount);
    }

}

