package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@JsonObject
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountIcon implements Parcelable {
    public static final Creator<AccountIcon> CREATOR = new 1();
    private String f6877a = null;
    @JsonProperty("accountId")
    @JsonField
    public long accountId;
    @JsonIgnore
    public HashSet<String> appsUsed = new HashSet();
    @JsonProperty("subApps")
    @JsonField
    public HashSet<String> appsWithSubscription = new HashSet();
    @JsonProperty("firstName")
    @JsonField
    public String firstName;
    @JsonProperty("handle")
    @JsonField
    public String handle;
    @JsonProperty("jid")
    @JsonField
    public String jid;
    @JsonProperty("lastName")
    @JsonField
    public String lastName;
    @JsonProperty("latitude")
    @JsonField
    public float latitude = 0.0f;
    @JsonProperty("longitude")
    @JsonField
    public float longitude = 0.0f;
    @JsonProperty("picUrl")
    @JsonField
    public String picUrl;
    @JsonProperty("vip")
    @JsonField
    public HashSet<String> platformsWithSmulePass = new HashSet();
    @JsonProperty("verifiedUrls")
    @JsonField
    public List<VerifiedUrl> verifiedUrls = new ArrayList();

    public void m8234a(Collection<String> collection) {
        this.appsUsed.addAll(collection);
    }

    public boolean m8235a() {
        if (this.accountId != UserManager.m8111a().m8203f()) {
            return m8236a(MagicNetwork.m7804d().getAppsInFamily());
        }
        return m8236a(new HashSet(Arrays.asList(new String[]{MagicNetwork.m7804d().getAppUID()})));
    }

    public boolean m8236a(HashSet<String> hashSet) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (this.appsWithSubscription.contains((String) it.next())) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "AccountIcon [accountId=" + this.accountId + ", picUrl=" + this.picUrl + ", latitude=" + this.latitude + ", longitude=" + this.longitude + "]";
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AccountIcon accountIcon = (AccountIcon) obj;
        if (this.accountId != accountIcon.accountId) {
            return false;
        }
        if (Float.compare(accountIcon.latitude, this.latitude) != 0) {
            return false;
        }
        if (Float.compare(accountIcon.longitude, this.longitude) != 0) {
            return false;
        }
        if (this.appsUsed == null ? accountIcon.appsUsed != null : !this.appsUsed.equals(accountIcon.appsUsed)) {
            return false;
        }
        if (this.appsWithSubscription == null ? accountIcon.appsWithSubscription != null : !this.appsWithSubscription.equals(accountIcon.appsWithSubscription)) {
            return false;
        }
        if (this.handle == null ? accountIcon.handle != null : !this.handle.equals(accountIcon.handle)) {
            return false;
        }
        if (this.picUrl == null ? accountIcon.picUrl != null : !this.picUrl.equals(accountIcon.picUrl)) {
            return false;
        }
        if (this.platformsWithSmulePass == null ? accountIcon.platformsWithSmulePass != null : !this.platformsWithSmulePass.equals(accountIcon.platformsWithSmulePass)) {
            return false;
        }
        if (this.verifiedUrls == null ? accountIcon.verifiedUrls != null : !this.verifiedUrls.equals(accountIcon.verifiedUrls)) {
            return false;
        }
        if (this.firstName == null ? accountIcon.firstName != null : !this.firstName.equals(accountIcon.firstName)) {
            return false;
        }
        if (this.lastName != null) {
            if (this.lastName.equals(accountIcon.lastName)) {
                return true;
            }
        } else if (accountIcon.lastName == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = ((this.picUrl != null ? this.picUrl.hashCode() : 0) + (((int) (this.accountId ^ (this.accountId >>> 32))) * 31)) * 31;
        if (this.handle != null) {
            hashCode = this.handle.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.latitude != 0.0f) {
            hashCode = Float.floatToIntBits(this.latitude);
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.longitude != 0.0f) {
            hashCode = Float.floatToIntBits(this.longitude);
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.appsWithSubscription != null) {
            hashCode = this.appsWithSubscription.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.platformsWithSmulePass != null) {
            hashCode = this.platformsWithSmulePass.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.verifiedUrls != null) {
            hashCode = this.verifiedUrls.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.appsUsed != null) {
            hashCode = this.appsUsed.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.firstName != null) {
            hashCode = this.firstName.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.lastName != null) {
            i = this.lastName.hashCode();
        }
        return hashCode + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.accountId);
        parcel.writeString(this.picUrl);
        parcel.writeString(this.handle);
        parcel.writeFloat(this.latitude);
        parcel.writeFloat(this.longitude);
        parcel.writeString(this.jid);
        List arrayList = new ArrayList();
        arrayList.addAll(this.appsWithSubscription);
        parcel.writeStringList(arrayList);
        arrayList = new ArrayList();
        arrayList.addAll(this.platformsWithSmulePass);
        parcel.writeStringList(arrayList);
        parcel.writeList(this.verifiedUrls);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        if (UserManager.m8111a() != null && this.accountId == UserManager.m8111a().m8203f()) {
            UserManager.m8111a().m8199d(this.firstName, this.lastName);
        }
    }

    public AccountIcon(Parcel parcel) {
        this.accountId = parcel.readLong();
        this.picUrl = parcel.readString();
        this.handle = parcel.readString();
        this.latitude = parcel.readFloat();
        this.longitude = parcel.readFloat();
        this.jid = parcel.readString();
        Collection arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.appsWithSubscription.addAll(arrayList);
        arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.platformsWithSmulePass.addAll(arrayList);
        parcel.readList(this.verifiedUrls, getClass().getClassLoader());
        this.firstName = parcel.readString();
        this.lastName = parcel.readString();
    }

    public static boolean m8233a(AccountIcon accountIcon) {
        if (accountIcon == null || accountIcon.accountId == 0 || accountIcon.handle == null || accountIcon.handle.length() == 0) {
            return false;
        }
        return true;
    }

    public boolean m8237a(List<String> list) {
        for (String contains : list) {
            if (this.appsUsed.contains(contains)) {
                return true;
            }
        }
        return false;
    }

    public boolean m8238b() {
        return m8237a(AppSettingsManager.m7855a().m7889f());
    }

    public boolean m8239c() {
        return this.verifiedUrls != null && this.verifiedUrls.size() > 0;
    }

    public boolean m8240d() {
        return this.accountId == UserManager.m8111a().m8203f();
    }
}
