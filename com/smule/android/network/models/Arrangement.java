/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.Composition;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Arrangement
implements Parcelable {
    public static final Parcelable.Creator<Arrangement> CREATOR;
    public static final Integer a;
    public static final Integer b;
    public static final Integer c;
    @JsonProperty(value="artist")
    public String artist;
    @JsonProperty(value="composition")
    public Composition composition;
    @JsonProperty(value="coprDisable")
    public boolean coprDisable;
    @JsonProperty(value="createdAt")
    public long createdAt;
    @JsonProperty(value="downVotes")
    public int downVotes;
    @JsonProperty(value="highlyRated")
    public boolean highlyRated;
    @JsonProperty(value="key")
    public String key;
    @JsonProperty(value="lastPublishedVer")
    public int lastPublishedVer;
    @JsonProperty(value="name")
    public String name;
    @JsonProperty(value="noPaywall")
    public boolean noPaywall;
    @JsonProperty(value="ownerAccountIcon")
    public AccountIcon ownerAccountIcon;
    @JsonProperty(value="primeArrangerAccountIcon")
    public AccountIcon primeArrangerAccountIcon;
    @JsonProperty(value="primeSongType")
    public Integer primeSongType;
    @JsonProperty(value="rating")
    public Float rating;
    @JsonProperty(value="rm")
    public int removalCode;
    @JsonProperty(value="smuleOwned")
    public boolean smuleOwned;
    @JsonProperty(value="songId")
    public String songId;
    @JsonProperty(value="tags")
    public List<String> tags;
    @JsonProperty(value="totalPlays")
    public int totalPlays;
    @JsonProperty(value="totalVotes")
    public int totalVotes;
    @JsonProperty(value="upVotes")
    public int upVotes;

    static {
        a = 0;
        b = 1;
        c = null;
        CREATOR = new Parcelable.Creator<Arrangement>(){

            public Arrangement a(Parcel parcel) {
                return new Arrangement(parcel);
            }

            public Arrangement[] a(int n) {
                return new Arrangement[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public Arrangement() {
        this.tags = new ArrayList<String>();
    }

    /*
     * Enabled aggressive block sorting
     */
    private Arrangement(Parcel parcel) {
        boolean bl = true;
        this.tags = new ArrayList<String>();
        this.key = parcel.readString();
        this.ownerAccountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.name = parcel.readString();
        this.songId = parcel.readString();
        this.tags = new ArrayList<String>();
        parcel.readList(this.tags, List.class.getClassLoader());
        this.rating = (Float)parcel.readValue(Float.class.getClassLoader());
        boolean bl2 = parcel.readByte() != 0;
        this.highlyRated = bl2;
        bl2 = parcel.readByte() != 0;
        this.noPaywall = bl2;
        this.totalVotes = parcel.readInt();
        this.totalPlays = parcel.readInt();
        this.upVotes = parcel.readInt();
        this.downVotes = parcel.readInt();
        bl2 = parcel.readByte() != 0;
        this.coprDisable = bl2;
        bl2 = parcel.readByte() != 0 ? bl : false;
        this.smuleOwned = bl2;
        this.lastPublishedVer = parcel.readInt();
        this.createdAt = parcel.readLong();
        this.removalCode = parcel.readInt();
        this.composition = (Composition)parcel.readParcelable(Composition.class.getClassLoader());
        this.artist = parcel.readString();
        this.primeSongType = (Integer)parcel.readValue(Integer.class.getClassLoader());
        this.primeArrangerAccountIcon = (AccountIcon)parcel.readValue(AccountIcon.class.getClassLoader());
    }

    public boolean a() {
        if (this.songId == null) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Arrangement)) {
            return false;
        }
        object = (Arrangement)object;
        if (this.coprDisable != object.coprDisable) {
            return false;
        }
        if (this.smuleOwned != object.smuleOwned) {
            return false;
        }
        if (this.createdAt != object.createdAt) {
            return false;
        }
        if (this.removalCode != object.removalCode) {
            return false;
        }
        if (this.highlyRated != object.highlyRated) {
            return false;
        }
        if (this.noPaywall != object.noPaywall) {
            return false;
        }
        if (this.lastPublishedVer != object.lastPublishedVer) {
            return false;
        }
        if (this.totalVotes != object.totalVotes) {
            return false;
        }
        if (this.totalPlays != object.totalPlays) {
            return false;
        }
        if (this.upVotes != object.upVotes) {
            return false;
        }
        if (this.downVotes != object.downVotes) {
            return false;
        }
        if (this.key != null) {
            if (!this.key.equals(object.key)) {
                return false;
            }
        } else if (object.key != null) return false;
        if (this.name != null) {
            if (!this.name.equals(object.name)) {
                return false;
            }
        } else if (object.name != null) return false;
        if (this.ownerAccountIcon != null) {
            if (!this.ownerAccountIcon.equals(object.ownerAccountIcon)) {
                return false;
            }
        } else if (object.ownerAccountIcon != null) return false;
        if (this.rating != null) {
            if (!this.rating.equals(object.rating)) {
                return false;
            }
        } else if (object.rating != null) return false;
        if (this.songId != null) {
            if (!this.songId.equals(object.songId)) {
                return false;
            }
        } else if (object.songId != null) return false;
        if (this.tags != null) {
            if (!this.tags.equals(object.tags)) {
                return false;
            }
        } else if (object.tags != null) return false;
        if (this.composition != null) {
            if (!this.composition.equals(object.composition)) {
                return false;
            }
        } else if (object.composition != null) return false;
        if (this.artist != null) {
            if (!this.artist.equals(object.artist)) {
                return false;
            }
        } else if (object.artist != null) return false;
        if (this.primeSongType != null) {
            if (!this.primeSongType.equals(object.primeSongType)) {
                return false;
            }
        } else if (object.primeSongType != null) return false;
        if (this.primeArrangerAccountIcon != null) {
            if (this.primeArrangerAccountIcon.equals(object.primeArrangerAccountIcon)) return true;
            return false;
        }
        if (object.primeArrangerAccountIcon == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 1;
        int n2 = 0;
        int n3 = this.key != null ? this.key.hashCode() : 0;
        int n4 = this.ownerAccountIcon != null ? this.ownerAccountIcon.hashCode() : 0;
        int n5 = this.name != null ? this.name.hashCode() : 0;
        int n6 = this.songId != null ? this.songId.hashCode() : 0;
        int n7 = this.tags != null ? this.tags.hashCode() : 0;
        int n8 = this.rating != null ? this.rating.hashCode() : 0;
        int n9 = this.highlyRated ? 1 : 0;
        int n10 = this.noPaywall ? 1 : 0;
        int n11 = this.totalVotes;
        int n12 = this.totalPlays;
        int n13 = this.upVotes;
        int n14 = this.downVotes;
        int n15 = this.coprDisable ? 1 : 0;
        if (!this.smuleOwned) {
            n = 0;
        }
        int n16 = this.lastPublishedVer;
        int n17 = (int)(this.createdAt ^ this.createdAt >>> 32);
        int n18 = this.removalCode;
        int n19 = this.composition != null ? this.composition.hashCode() : 0;
        int n20 = this.artist != null ? this.artist.hashCode() : 0;
        int n21 = this.primeSongType != null ? this.primeSongType.hashCode() : 0;
        if (this.primeArrangerAccountIcon != null) {
            n2 = this.primeArrangerAccountIcon.hashCode();
        }
        return (n21 + (n20 + (n19 + (((((n15 + (((((n10 + (n9 + (n8 + (n7 + (n6 + (n5 + (n4 + n3 * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + n11) * 31 + n12) * 31 + n13) * 31 + n14) * 31) * 31 + n) * 31 + n16) * 31 + n17) * 31 + n18) * 31) * 31) * 31) * 31 + n2;
    }

    public String toString() {
        return "Arrangement{key='" + this.key + '\'' + ", ownerAccountIcon=" + this.ownerAccountIcon + ", name='" + this.name + '\'' + ", songId='" + this.songId + '\'' + ", tags=" + this.tags + ", rating=" + this.rating + ", highlyRated=" + this.highlyRated + ", totalVotes=" + this.totalVotes + ", totalPlays=" + this.totalPlays + ", upVotes=" + this.upVotes + ", downVotes=" + this.downVotes + ", coprDisable=" + this.coprDisable + ", smuleOwned=" + this.smuleOwned + ", lastPublishedVer=" + this.lastPublishedVer + ", createdAt=" + this.createdAt + ", removalCode=" + this.removalCode + ", composition=" + this.composition + ", artist=" + this.artist + ", primeSongType=" + this.primeSongType + ", primeArrangerAccountIcon" + this.primeArrangerAccountIcon + ", noPaywall=" + this.noPaywall + '}';
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        byte by = 1;
        parcel.writeString(this.key);
        parcel.writeParcelable((Parcelable)this.ownerAccountIcon, 0);
        parcel.writeString(this.name);
        parcel.writeString(this.songId);
        parcel.writeList(this.tags);
        parcel.writeValue((Object)this.rating);
        byte by2 = this.highlyRated ? 1 : 0;
        parcel.writeByte(by2);
        by2 = this.noPaywall ? 1 : 0;
        parcel.writeByte(by2);
        parcel.writeInt(this.totalVotes);
        parcel.writeInt(this.totalPlays);
        parcel.writeInt(this.upVotes);
        parcel.writeInt(this.downVotes);
        by2 = this.coprDisable ? 1 : 0;
        parcel.writeByte(by2);
        by2 = this.smuleOwned ? by : 0;
        parcel.writeByte(by2);
        parcel.writeInt(this.lastPublishedVer);
        parcel.writeLong(this.createdAt);
        parcel.writeInt(this.removalCode);
        parcel.writeParcelable((Parcelable)this.composition, 0);
        parcel.writeString(this.artist);
        parcel.writeValue((Object)this.primeSongType);
        parcel.writeValue((Object)this.primeArrangerAccountIcon);
    }
}

