package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Arrangement implements Parcelable {
    public static final Creator<Arrangement> CREATOR = new 1();
    public static final Integer f6878a = Integer.valueOf(0);
    public static final Integer f6879b = Integer.valueOf(1);
    public static final Integer f6880c = null;
    @JsonProperty("artist")
    public String artist;
    @JsonProperty("composition")
    public Composition composition;
    @JsonProperty("coprDisable")
    public boolean coprDisable;
    @JsonProperty("createdAt")
    public long createdAt;
    @JsonProperty("downVotes")
    public int downVotes;
    @JsonProperty("highlyRated")
    public boolean highlyRated;
    @JsonProperty("key")
    public String key;
    @JsonProperty("lastPublishedVer")
    public int lastPublishedVer;
    @JsonProperty("name")
    public String name;
    @JsonProperty("ownerAccountIcon")
    public AccountIcon ownerAccountIcon;
    @JsonProperty("primeArrangerAccountIcon")
    public AccountIcon primeArrangerAccountIcon;
    @JsonProperty("primeSongType")
    public Integer primeSongType;
    @JsonProperty("rating")
    public Float rating;
    @JsonProperty("rm")
    public int removalCode;
    @JsonProperty("songId")
    public String songId;
    @JsonProperty("tags")
    public List<String> tags;
    @JsonProperty("totalPlays")
    public int totalPlays;
    @JsonProperty("totalVotes")
    public int totalVotes;
    @JsonProperty("upVotes")
    public int upVotes;

    public Arrangement() {
        this.tags = new ArrayList();
    }

    public String toString() {
        return "Arrangement{key='" + this.key + '\'' + ", ownerAccountIcon=" + this.ownerAccountIcon + ", name='" + this.name + '\'' + ", songId='" + this.songId + '\'' + ", tags=" + this.tags + ", rating=" + this.rating + ", highlyRated=" + this.highlyRated + ", totalVotes=" + this.totalVotes + ", totalPlays=" + this.totalPlays + ", upVotes=" + this.upVotes + ", downVotes=" + this.downVotes + ", coprDisable=" + this.coprDisable + ", lastPublishedVer=" + this.lastPublishedVer + ", createdAt=" + this.createdAt + ", removalCode=" + this.removalCode + ", composition=" + this.composition + ", artist=" + this.artist + ", primeSongType=" + this.primeSongType + ", primeArrangerAccountIcon" + this.primeArrangerAccountIcon + '}';
    }

    public boolean m8241a() {
        return this.songId == null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Arrangement)) {
            return false;
        }
        Arrangement arrangement = (Arrangement) obj;
        if (this.coprDisable != arrangement.coprDisable) {
            return false;
        }
        if (this.createdAt != arrangement.createdAt) {
            return false;
        }
        if (this.removalCode != arrangement.removalCode) {
            return false;
        }
        if (this.highlyRated != arrangement.highlyRated) {
            return false;
        }
        if (this.lastPublishedVer != arrangement.lastPublishedVer) {
            return false;
        }
        if (this.totalVotes != arrangement.totalVotes) {
            return false;
        }
        if (this.totalPlays != arrangement.totalPlays) {
            return false;
        }
        if (this.upVotes != arrangement.upVotes) {
            return false;
        }
        if (this.downVotes != arrangement.downVotes) {
            return false;
        }
        if (this.key == null ? arrangement.key != null : !this.key.equals(arrangement.key)) {
            return false;
        }
        if (this.name == null ? arrangement.name != null : !this.name.equals(arrangement.name)) {
            return false;
        }
        if (this.ownerAccountIcon == null ? arrangement.ownerAccountIcon != null : !this.ownerAccountIcon.equals(arrangement.ownerAccountIcon)) {
            return false;
        }
        if (this.rating == null ? arrangement.rating != null : !this.rating.equals(arrangement.rating)) {
            return false;
        }
        if (this.songId == null ? arrangement.songId != null : !this.songId.equals(arrangement.songId)) {
            return false;
        }
        if (this.tags == null ? arrangement.tags != null : !this.tags.equals(arrangement.tags)) {
            return false;
        }
        if (this.composition == null ? arrangement.composition != null : !this.composition.equals(arrangement.composition)) {
            return false;
        }
        if (this.artist == null ? arrangement.artist != null : !this.artist.equals(arrangement.artist)) {
            return false;
        }
        if (this.primeSongType == null ? arrangement.primeSongType != null : !this.primeSongType.equals(arrangement.primeSongType)) {
            return false;
        }
        if (this.primeArrangerAccountIcon != null) {
            if (this.primeArrangerAccountIcon.equals(arrangement.primeArrangerAccountIcon)) {
                return true;
            }
        } else if (arrangement.primeArrangerAccountIcon == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int i2 = 0;
        int hashCode2 = (this.key != null ? this.key.hashCode() : 0) * 31;
        if (this.ownerAccountIcon != null) {
            hashCode = this.ownerAccountIcon.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.name != null) {
            hashCode = this.name.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.songId != null) {
            hashCode = this.songId.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.tags != null) {
            hashCode = this.tags.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.rating != null) {
            hashCode = this.rating.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.highlyRated) {
            hashCode = 1;
        } else {
            hashCode = 0;
        }
        hashCode = (((((((((hashCode + hashCode2) * 31) + this.totalVotes) * 31) + this.totalPlays) * 31) + this.upVotes) * 31) + this.downVotes) * 31;
        if (!this.coprDisable) {
            i = 0;
        }
        i = (((((((hashCode + i) * 31) + this.lastPublishedVer) * 31) + ((int) (this.createdAt ^ (this.createdAt >>> 32)))) * 31) + this.removalCode) * 31;
        if (this.composition != null) {
            hashCode = this.composition.hashCode();
        } else {
            hashCode = 0;
        }
        i = (hashCode + i) * 31;
        if (this.artist != null) {
            hashCode = this.artist.hashCode();
        } else {
            hashCode = 0;
        }
        i = (hashCode + i) * 31;
        if (this.primeSongType != null) {
            hashCode = this.primeSongType.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + i) * 31;
        if (this.primeArrangerAccountIcon != null) {
            i2 = this.primeArrangerAccountIcon.hashCode();
        }
        return hashCode + i2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeString(this.key);
        parcel.writeParcelable(this.ownerAccountIcon, 0);
        parcel.writeString(this.name);
        parcel.writeString(this.songId);
        parcel.writeList(this.tags);
        parcel.writeValue(this.rating);
        parcel.writeByte(this.highlyRated ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.totalVotes);
        parcel.writeInt(this.totalPlays);
        parcel.writeInt(this.upVotes);
        parcel.writeInt(this.downVotes);
        if (!this.coprDisable) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        parcel.writeInt(this.lastPublishedVer);
        parcel.writeLong(this.createdAt);
        parcel.writeInt(this.removalCode);
        parcel.writeParcelable(this.composition, 0);
        parcel.writeString(this.artist);
        parcel.writeValue(this.primeSongType);
        parcel.writeValue(this.primeArrangerAccountIcon);
    }

    private Arrangement(Parcel parcel) {
        boolean z = true;
        this.tags = new ArrayList();
        this.key = parcel.readString();
        this.ownerAccountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.name = parcel.readString();
        this.songId = parcel.readString();
        this.tags = new ArrayList();
        parcel.readList(this.tags, List.class.getClassLoader());
        this.rating = (Float) parcel.readValue(Float.class.getClassLoader());
        this.highlyRated = parcel.readByte() != (byte) 0;
        this.totalVotes = parcel.readInt();
        this.totalPlays = parcel.readInt();
        this.upVotes = parcel.readInt();
        this.downVotes = parcel.readInt();
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.coprDisable = z;
        this.lastPublishedVer = parcel.readInt();
        this.createdAt = parcel.readLong();
        this.removalCode = parcel.readInt();
        this.composition = (Composition) parcel.readParcelable(Composition.class.getClassLoader());
        this.artist = parcel.readString();
        this.primeSongType = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.primeArrangerAccountIcon = (AccountIcon) parcel.readValue(AccountIcon.class.getClassLoader());
    }
}
