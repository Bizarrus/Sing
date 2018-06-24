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
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.Composition;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ArrangementVersionLite
implements Parcelable {
    public static final Parcelable.Creator<ArrangementVersionLite> CREATOR = new Parcelable.Creator<ArrangementVersionLite>(){

        public ArrangementVersionLite a(Parcel parcel) {
            return new ArrangementVersionLite(parcel);
        }

        public ArrangementVersionLite[] a(int n) {
            return new ArrangementVersionLite[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonIgnore
    public static final int VOTES_THRESHOLD = 3;
    @JsonProperty(value="ownerAccountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="arrCreatedAt")
    public long arrCreatedAt;
    @JsonIgnore
    public ArrangementVersion arrangementVersion;
    @JsonProperty(value="artist")
    public String artist;
    @JsonProperty(value="compTitle")
    public String compTitle;
    @JsonProperty(value="coverUrl")
    public String coverUrl;
    @JsonProperty(value="highlyRated")
    public boolean highlyRated;
    @JsonProperty(value="key")
    public String key;
    @JsonProperty(value="lyrics")
    public boolean lyrics;
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="rating")
    public Float rating;
    @JsonProperty(value="rm")
    public int removalCode;
    @JsonProperty(value="smuleOwned")
    public boolean smuleOwned;
    @JsonProperty(value="songId")
    public String songId;
    @JsonProperty(value="totalVotes")
    public int totalVotes;
    @JsonProperty(value="ver")
    public int version;
    @JsonProperty(value="webUrl")
    public String webUrl;

    public ArrangementVersionLite() {
    }

    /*
     * Enabled aggressive block sorting
     */
    private ArrangementVersionLite(Parcel parcel) {
        boolean bl = true;
        this.key = parcel.readString();
        this.version = parcel.readInt();
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.name = parcel.readString();
        this.compTitle = parcel.readString();
        this.artist = parcel.readString();
        this.songId = parcel.readString();
        this.rating = (Float)parcel.readValue(Float.class.getClassLoader());
        boolean bl2 = parcel.readByte() != 0;
        this.highlyRated = bl2;
        this.totalVotes = parcel.readInt();
        bl2 = parcel.readByte() != 0;
        this.lyrics = bl2;
        bl2 = parcel.readByte() != 0 ? bl : false;
        this.smuleOwned = bl2;
        this.coverUrl = parcel.readString();
        this.webUrl = parcel.readString();
        this.arrCreatedAt = parcel.readLong();
        this.arrangementVersion = (ArrangementVersion)parcel.readParcelable(ArrangementVersion.class.getClassLoader());
        this.removalCode = parcel.readInt();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ArrangementVersion arrangementVersion) {
        this.arrangementVersion = arrangementVersion;
        this.version = arrangementVersion.version;
        this.lyrics = arrangementVersion.lyrics;
        if (arrangementVersion.a() != null) {
            this.coverUrl = arrangementVersion.a().url;
        }
        if (arrangementVersion.arrangement != null) {
            this.smuleOwned = arrangementVersion.arrangement.smuleOwned;
            this.key = arrangementVersion.arrangement.key;
            this.accountIcon = arrangementVersion.arrangement.ownerAccountIcon;
            this.name = arrangementVersion.arrangement.name;
            this.songId = arrangementVersion.arrangement.songId;
            this.arrCreatedAt = arrangementVersion.arrangement.createdAt;
            if (arrangementVersion.arrangement.composition != null) {
                this.compTitle = arrangementVersion.arrangement.composition.title;
            }
            this.artist = this.songId != null && arrangementVersion.arrangement.composition != null && arrangementVersion.arrangement.composition.artist != null ? arrangementVersion.arrangement.composition.artist : arrangementVersion.arrangement.artist;
            this.rating = arrangementVersion.arrangement.rating;
            this.highlyRated = arrangementVersion.arrangement.highlyRated;
            this.totalVotes = arrangementVersion.arrangement.totalVotes;
            this.removalCode = arrangementVersion.arrangement.removalCode;
        }
    }

    public boolean a() {
        if (this.songId == null) {
            return true;
        }
        return false;
    }

    public String b() {
        return this.webUrl;
    }

    public String c() {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty((CharSequence)this.compTitle)) {
            stringBuilder.append(this.compTitle);
        }
        if (!TextUtils.isEmpty((CharSequence)this.name)) {
            if (!TextUtils.isEmpty((CharSequence)this.compTitle)) {
                stringBuilder.append(" - ");
            }
            stringBuilder.append(this.name);
        }
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block21 : {
            block20 : {
                if (this == object) break block20;
                if (!(object instanceof ArrangementVersionLite)) {
                    return false;
                }
                object = (ArrangementVersionLite)object;
                if (this.arrCreatedAt != object.arrCreatedAt) {
                    return false;
                }
                if (this.highlyRated != object.highlyRated) {
                    return false;
                }
                if (this.lyrics != object.lyrics) {
                    return false;
                }
                if (this.smuleOwned != object.smuleOwned) {
                    return false;
                }
                if (this.totalVotes != object.totalVotes) {
                    return false;
                }
                if (this.version != object.version) {
                    return false;
                }
                if (this.accountIcon != null ? !this.accountIcon.equals(object.accountIcon) : object.accountIcon != null) {
                    return false;
                }
                if (this.arrangementVersion != null ? !this.arrangementVersion.equals(object.arrangementVersion) : object.arrangementVersion != null) {
                    return false;
                }
                if (this.coverUrl != null ? !this.coverUrl.equals(object.coverUrl) : object.coverUrl != null) {
                    return false;
                }
                if (this.webUrl != null ? !this.webUrl.equals(object.webUrl) : object.webUrl != null) {
                    return false;
                }
                if (this.key != null ? !this.key.equals(object.key) : object.key != null) {
                    return false;
                }
                if (this.name != null ? !this.name.equals(object.name) : object.name != null) {
                    return false;
                }
                if (this.compTitle != null ? !this.compTitle.equals(object.compTitle) : object.compTitle != null) {
                    return false;
                }
                if (this.artist != null ? !this.artist.equals(object.artist) : object.artist != null) {
                    return false;
                }
                if (this.rating != null ? !this.rating.equals(object.rating) : object.rating != null) {
                    return false;
                }
                if (this.songId != null ? !this.songId.equals(object.songId) : object.songId != null) {
                    return false;
                }
                if (this.removalCode != object.removalCode) break block21;
            }
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 1;
        int n2 = 0;
        int n3 = this.key != null ? this.key.hashCode() : 0;
        int n4 = this.version;
        int n5 = this.accountIcon != null ? this.accountIcon.hashCode() : 0;
        int n6 = this.name != null ? this.name.hashCode() : 0;
        int n7 = this.compTitle != null ? this.compTitle.hashCode() : 0;
        int n8 = this.artist != null ? this.artist.hashCode() : 0;
        int n9 = this.songId != null ? this.songId.hashCode() : 0;
        int n10 = this.rating != null ? this.rating.hashCode() : 0;
        int n11 = this.highlyRated ? 1 : 0;
        int n12 = this.totalVotes;
        int n13 = this.lyrics ? 1 : 0;
        if (!this.smuleOwned) {
            n = 0;
        }
        int n14 = this.coverUrl != null ? this.coverUrl.hashCode() : 0;
        int n15 = this.webUrl != null ? this.webUrl.hashCode() : 0;
        int n16 = (int)(this.arrCreatedAt ^ this.arrCreatedAt >>> 32);
        if (this.arrangementVersion != null) {
            n2 = this.arrangementVersion.hashCode();
        }
        return (((n15 + (n14 + ((n13 + ((n11 + (n10 + (n9 + (n8 + (n7 + (n6 + (n5 + (n3 * 31 + n4) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + n12) * 31) * 31 + n) * 31) * 31) * 31 + n16) * 31 + n2) * 31 + this.removalCode;
    }

    public String toString() {
        return "ArrangementVersionLite{key='" + this.key + '\'' + ", version=" + this.version + ", accountIcon=" + this.accountIcon + ", name='" + this.name + '\'' + ", compTitle='" + this.compTitle + '\'' + ", artist='" + this.artist + '\'' + ", songId='" + this.songId + '\'' + ", rating=" + this.rating + ", highlyRated=" + this.highlyRated + ", totalVotes=" + this.totalVotes + ", lyrics=" + this.lyrics + ", smuleOwned=" + this.smuleOwned + ", coverUrl='" + this.coverUrl + '\'' + ", webUrl='" + this.webUrl + '\'' + ", arrCreatedAt=" + this.arrCreatedAt + ", arrangementVersion=" + this.arrangementVersion + ", removalCode=" + this.removalCode + '}';
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        byte by = 1;
        parcel.writeString(this.key);
        parcel.writeInt(this.version);
        parcel.writeParcelable((Parcelable)this.accountIcon, 0);
        parcel.writeString(this.name);
        parcel.writeString(this.compTitle);
        parcel.writeString(this.artist);
        parcel.writeString(this.songId);
        parcel.writeValue((Object)this.rating);
        byte by2 = this.highlyRated ? 1 : 0;
        parcel.writeByte(by2);
        parcel.writeInt(this.totalVotes);
        by2 = this.lyrics ? 1 : 0;
        parcel.writeByte(by2);
        by2 = this.smuleOwned ? by : 0;
        parcel.writeByte(by2);
        parcel.writeString(this.coverUrl);
        parcel.writeString(this.webUrl);
        parcel.writeLong(this.arrCreatedAt);
        parcel.writeParcelable((Parcelable)this.arrangementVersion, 0);
        parcel.writeInt(this.removalCode);
    }
}

