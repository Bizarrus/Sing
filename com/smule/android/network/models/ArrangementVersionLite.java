package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrangementVersionLite implements Parcelable {
    public static final Creator<ArrangementVersionLite> CREATOR = new 1();
    @JsonIgnore
    public static final int VOTES_THRESHOLD = 3;
    @JsonProperty("ownerAccountIcon")
    public AccountIcon accountIcon;
    @JsonProperty("arrCreatedAt")
    public long arrCreatedAt;
    @JsonIgnore
    public ArrangementVersion arrangementVersion;
    @JsonProperty("artist")
    public String artist;
    @JsonProperty("compTitle")
    public String compTitle;
    @JsonProperty("coverUrl")
    public String coverUrl;
    @JsonProperty("highlyRated")
    public boolean highlyRated;
    @JsonProperty("key")
    public String key;
    @JsonProperty("lyrics")
    public boolean lyrics;
    @JsonProperty("name")
    public String name;
    @JsonProperty("rating")
    public Float rating;
    @JsonProperty("rm")
    public int removalCode;
    @JsonProperty("songId")
    public String songId;
    @JsonProperty("totalVotes")
    public int totalVotes;
    @JsonProperty("ver")
    public int version;
    @JsonProperty("webUrl")
    public String webUrl;

    public void m8246a(ArrangementVersion arrangementVersion) {
        this.arrangementVersion = arrangementVersion;
        this.version = arrangementVersion.version;
        this.lyrics = arrangementVersion.lyrics;
        if (arrangementVersion.m8243a() != null) {
            this.coverUrl = arrangementVersion.m8243a().url;
        }
        if (arrangementVersion.arrangement != null) {
            this.key = arrangementVersion.arrangement.key;
            this.accountIcon = arrangementVersion.arrangement.ownerAccountIcon;
            this.name = arrangementVersion.arrangement.name;
            this.songId = arrangementVersion.arrangement.songId;
            this.arrCreatedAt = arrangementVersion.arrangement.createdAt;
            if (arrangementVersion.arrangement.composition != null) {
                this.compTitle = arrangementVersion.arrangement.composition.title;
            }
            if (this.songId == null || arrangementVersion.arrangement.composition == null || arrangementVersion.arrangement.composition.artist == null) {
                this.artist = arrangementVersion.arrangement.artist;
            } else {
                this.artist = arrangementVersion.arrangement.composition.artist;
            }
            this.rating = arrangementVersion.arrangement.rating;
            this.highlyRated = arrangementVersion.arrangement.highlyRated;
            this.totalVotes = arrangementVersion.arrangement.totalVotes;
            this.removalCode = arrangementVersion.arrangement.removalCode;
        }
    }

    public boolean m8247a() {
        return this.songId == null;
    }

    public String toString() {
        return "ArrangementVersionLite{key='" + this.key + '\'' + ", version=" + this.version + ", accountIcon=" + this.accountIcon + ", name='" + this.name + '\'' + ", compTitle='" + this.compTitle + '\'' + ", artist='" + this.artist + '\'' + ", songId='" + this.songId + '\'' + ", rating=" + this.rating + ", highlyRated=" + this.highlyRated + ", totalVotes=" + this.totalVotes + ", lyrics=" + this.lyrics + ", coverUrl='" + this.coverUrl + '\'' + ", webUrl='" + this.webUrl + '\'' + ", arrCreatedAt=" + this.arrCreatedAt + ", arrangementVersion=" + this.arrangementVersion + ", removalCode=" + this.removalCode + '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArrangementVersionLite)) {
            return false;
        }
        ArrangementVersionLite arrangementVersionLite = (ArrangementVersionLite) obj;
        if (this.arrCreatedAt != arrangementVersionLite.arrCreatedAt) {
            return false;
        }
        if (this.highlyRated != arrangementVersionLite.highlyRated) {
            return false;
        }
        if (this.lyrics != arrangementVersionLite.lyrics) {
            return false;
        }
        if (this.totalVotes != arrangementVersionLite.totalVotes) {
            return false;
        }
        if (this.version != arrangementVersionLite.version) {
            return false;
        }
        if (this.accountIcon == null ? arrangementVersionLite.accountIcon != null : !this.accountIcon.equals(arrangementVersionLite.accountIcon)) {
            return false;
        }
        if (this.arrangementVersion == null ? arrangementVersionLite.arrangementVersion != null : !this.arrangementVersion.equals(arrangementVersionLite.arrangementVersion)) {
            return false;
        }
        if (this.coverUrl == null ? arrangementVersionLite.coverUrl != null : !this.coverUrl.equals(arrangementVersionLite.coverUrl)) {
            return false;
        }
        if (this.webUrl == null ? arrangementVersionLite.webUrl != null : !this.webUrl.equals(arrangementVersionLite.webUrl)) {
            return false;
        }
        if (this.key == null ? arrangementVersionLite.key != null : !this.key.equals(arrangementVersionLite.key)) {
            return false;
        }
        if (this.name == null ? arrangementVersionLite.name != null : !this.name.equals(arrangementVersionLite.name)) {
            return false;
        }
        if (this.compTitle == null ? arrangementVersionLite.compTitle != null : !this.compTitle.equals(arrangementVersionLite.compTitle)) {
            return false;
        }
        if (this.artist == null ? arrangementVersionLite.artist != null : !this.artist.equals(arrangementVersionLite.artist)) {
            return false;
        }
        if (this.rating == null ? arrangementVersionLite.rating != null : !this.rating.equals(arrangementVersionLite.rating)) {
            return false;
        }
        if (this.songId == null ? arrangementVersionLite.songId != null : !this.songId.equals(arrangementVersionLite.songId)) {
            return false;
        }
        if (this.removalCode != arrangementVersionLite.removalCode) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode;
        int i = 1;
        int i2 = 0;
        int hashCode2 = (((this.key != null ? this.key.hashCode() : 0) * 31) + this.version) * 31;
        if (this.accountIcon != null) {
            hashCode = this.accountIcon.hashCode();
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
        if (this.compTitle != null) {
            hashCode = this.compTitle.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.artist != null) {
            hashCode = this.artist.hashCode();
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
        hashCode = (((hashCode + hashCode2) * 31) + this.totalVotes) * 31;
        if (!this.lyrics) {
            i = 0;
        }
        i = (hashCode + i) * 31;
        if (this.coverUrl != null) {
            hashCode = this.coverUrl.hashCode();
        } else {
            hashCode = 0;
        }
        i = (hashCode + i) * 31;
        if (this.webUrl != null) {
            hashCode = this.webUrl.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (((hashCode + i) * 31) + ((int) (this.arrCreatedAt ^ (this.arrCreatedAt >>> 32)))) * 31;
        if (this.arrangementVersion != null) {
            i2 = this.arrangementVersion.hashCode();
        }
        return ((hashCode + i2) * 31) + this.removalCode;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        byte b = (byte) 1;
        parcel.writeString(this.key);
        parcel.writeInt(this.version);
        parcel.writeParcelable(this.accountIcon, 0);
        parcel.writeString(this.name);
        parcel.writeString(this.compTitle);
        parcel.writeString(this.artist);
        parcel.writeString(this.songId);
        parcel.writeValue(this.rating);
        parcel.writeByte(this.highlyRated ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.totalVotes);
        if (!this.lyrics) {
            b = (byte) 0;
        }
        parcel.writeByte(b);
        parcel.writeString(this.coverUrl);
        parcel.writeString(this.webUrl);
        parcel.writeLong(this.arrCreatedAt);
        parcel.writeParcelable(this.arrangementVersion, 0);
        parcel.writeInt(this.removalCode);
    }

    private ArrangementVersionLite(Parcel parcel) {
        boolean z = true;
        this.key = parcel.readString();
        this.version = parcel.readInt();
        this.accountIcon = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.name = parcel.readString();
        this.compTitle = parcel.readString();
        this.artist = parcel.readString();
        this.songId = parcel.readString();
        this.rating = (Float) parcel.readValue(Float.class.getClassLoader());
        this.highlyRated = parcel.readByte() != (byte) 0;
        this.totalVotes = parcel.readInt();
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.lyrics = z;
        this.coverUrl = parcel.readString();
        this.webUrl = parcel.readString();
        this.arrCreatedAt = parcel.readLong();
        this.arrangementVersion = (ArrangementVersion) parcel.readParcelable(ArrangementVersion.class.getClassLoader());
        this.removalCode = parcel.readInt();
    }

    public String m8248b() {
        return this.webUrl;
    }

    public String m8249c() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(this.compTitle)) {
            stringBuilder.append(this.compTitle);
        }
        if (!TextUtils.isEmpty(this.name)) {
            if (!TextUtils.isEmpty(this.compTitle)) {
                stringBuilder.append(" - ");
            }
            stringBuilder.append(this.name);
        }
        return stringBuilder.toString();
    }
}
