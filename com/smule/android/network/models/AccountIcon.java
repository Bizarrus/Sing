/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.bluelinelabs.logansquare.annotation.JsonField
 *  com.bluelinelabs.logansquare.annotation.JsonObject
 *  com.fasterxml.jackson.annotation.JsonIgnore
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.VerifiedUrl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonObject
public class AccountIcon
implements Parcelable {
    public static final Parcelable.Creator<AccountIcon> CREATOR = new Parcelable.Creator<AccountIcon>(){

        public AccountIcon a(Parcel parcel) {
            return new AccountIcon(parcel);
        }

        public AccountIcon[] a(int n) {
            return new AccountIcon[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private String a = null;
    @JsonProperty(value="accountId")
    @JsonField
    public long accountId;
    @JsonIgnore
    public HashSet<String> appsUsed = new HashSet();
    @JsonProperty(value="subApps")
    @JsonField
    public HashSet<String> appsWithSubscription = new HashSet();
    @JsonProperty(value="blurb")
    @JsonField
    public String blurb;
    @JsonProperty(value="firstName")
    @JsonField
    public String firstName;
    @JsonProperty(value="handle")
    @JsonField
    public String handle;
    @JsonProperty(value="jid")
    @JsonField
    public String jid;
    @JsonProperty(value="lastName")
    @JsonField
    public String lastName;
    @JsonProperty(value="latitude")
    @JsonField
    public float latitude = 0.0f;
    @JsonProperty(value="longitude")
    @JsonField
    public float longitude = 0.0f;
    @JsonProperty(value="picUrl")
    @JsonField
    public String picUrl;
    @JsonProperty(value="vip")
    @JsonField
    public HashSet<String> platformsWithSmulePass = new HashSet();
    @JsonProperty(value="verifiedUrls")
    @JsonField
    public List<VerifiedUrl> verifiedUrls = new ArrayList<VerifiedUrl>();

    public AccountIcon() {
    }

    public AccountIcon(Parcel parcel) {
        this.accountId = parcel.readLong();
        this.picUrl = parcel.readString();
        this.handle = parcel.readString();
        this.blurb = parcel.readString();
        this.latitude = parcel.readFloat();
        this.longitude = parcel.readFloat();
        this.jid = parcel.readString();
        ArrayList arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.appsWithSubscription.addAll(arrayList);
        arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.platformsWithSmulePass.addAll(arrayList);
        parcel.readList(this.verifiedUrls, this.getClass().getClassLoader());
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(AccountIcon accountIcon) {
        if (accountIcon == null || accountIcon.accountId == 0 || accountIcon.handle == null || accountIcon.handle.length() == 0) {
            return false;
        }
        return true;
    }

    public void a(Collection<String> collection) {
        this.appsUsed.addAll(collection);
    }

    public boolean a() {
        if (this.accountId == UserManager.a().f()) {
            return this.a(new HashSet<String>(Arrays.asList(MagicNetwork.d().getAppUID())));
        }
        return this.a(MagicNetwork.d().getAppsInFamily());
    }

    public boolean a(HashSet<String> object) {
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            if (!this.appsWithSubscription.contains(string2)) continue;
            return true;
        }
        return false;
    }

    public boolean a(List<String> object) {
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            if (!this.appsUsed.contains(string2)) continue;
            return true;
        }
        return false;
    }

    public boolean b() {
        return this.a((List<String>)AppSettingsManager.a().f());
    }

    public boolean c() {
        if (this.verifiedUrls != null && this.verifiedUrls.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean d() {
        if (this.accountId == UserManager.a().f()) {
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
        if (object == null) return false;
        if (this.getClass() != object.getClass()) {
            return false;
        }
        object = (AccountIcon)object;
        if (this.accountId != object.accountId) {
            return false;
        }
        if (Float.compare(object.latitude, this.latitude) != 0) {
            return false;
        }
        if (Float.compare(object.longitude, this.longitude) != 0) {
            return false;
        }
        if (this.appsUsed != null) {
            if (!this.appsUsed.equals(object.appsUsed)) {
                return false;
            }
        } else if (object.appsUsed != null) return false;
        if (this.appsWithSubscription != null) {
            if (!this.appsWithSubscription.equals(object.appsWithSubscription)) {
                return false;
            }
        } else if (object.appsWithSubscription != null) return false;
        if (this.handle != null) {
            if (!this.handle.equals(object.handle)) {
                return false;
            }
        } else if (object.handle != null) return false;
        if (this.picUrl != null) {
            if (!this.picUrl.equals(object.picUrl)) {
                return false;
            }
        } else if (object.picUrl != null) return false;
        if (this.blurb != null) {
            if (!this.blurb.equals(object.blurb)) {
                return false;
            }
        } else if (object.blurb != null) return false;
        if (this.platformsWithSmulePass != null) {
            if (!this.platformsWithSmulePass.equals(object.platformsWithSmulePass)) {
                return false;
            }
        } else if (object.platformsWithSmulePass != null) return false;
        if (this.verifiedUrls != null) {
            if (!this.verifiedUrls.equals(object.verifiedUrls)) {
                return false;
            }
        } else if (object.verifiedUrls != null) return false;
        if (this.firstName != null) {
            if (!this.firstName.equals(object.firstName)) {
                return false;
            }
        } else if (object.firstName != null) return false;
        if (this.lastName != null) {
            if (this.lastName.equals(object.lastName)) return true;
            return false;
        }
        if (object.lastName == null) return true;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int n = 0;
        int n2 = (int)(this.accountId ^ this.accountId >>> 32);
        int n3 = this.picUrl != null ? this.picUrl.hashCode() : 0;
        int n4 = this.handle != null ? this.handle.hashCode() : 0;
        int n5 = this.blurb != null ? this.blurb.hashCode() : 0;
        int n6 = this.latitude != 0.0f ? Float.floatToIntBits(this.latitude) : 0;
        int n7 = this.longitude != 0.0f ? Float.floatToIntBits(this.longitude) : 0;
        int n8 = this.appsWithSubscription != null ? this.appsWithSubscription.hashCode() : 0;
        int n9 = this.platformsWithSmulePass != null ? this.platformsWithSmulePass.hashCode() : 0;
        int n10 = this.verifiedUrls != null ? this.verifiedUrls.hashCode() : 0;
        int n11 = this.appsUsed != null ? this.appsUsed.hashCode() : 0;
        int n12 = this.firstName != null ? this.firstName.hashCode() : 0;
        if (this.lastName != null) {
            n = this.lastName.hashCode();
        }
        return (n12 + (n11 + (n10 + (n9 + (n8 + (n7 + (n6 + (n5 + (n4 + (n3 + n2 * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + n;
    }

    public String toString() {
        return "AccountIcon [accountId=" + this.accountId + ", picUrl=" + this.picUrl + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeLong(this.accountId);
        parcel.writeString(this.picUrl);
        parcel.writeString(this.handle);
        parcel.writeString(this.blurb);
        parcel.writeFloat(this.latitude);
        parcel.writeFloat(this.longitude);
        parcel.writeString(this.jid);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.addAll(this.appsWithSubscription);
        parcel.writeStringList(arrayList);
        arrayList = new ArrayList();
        arrayList.addAll(this.platformsWithSmulePass);
        parcel.writeStringList(arrayList);
        parcel.writeList(this.verifiedUrls);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        if (UserManager.a() != null && this.accountId == UserManager.a().f()) {
            UserManager.a().d(this.firstName, this.lastName);
        }
    }

}

