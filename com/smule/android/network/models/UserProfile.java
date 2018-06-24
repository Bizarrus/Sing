/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.UserProfile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserProfile
extends ParsedResponse
implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    private static final String d;
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="apps")
    public List<String> apps = new ArrayList<String>();
    public int b;
    public int c;
    private HashMap<String, Integer> e = new HashMap();
    private HashMap<String, String> f = new HashMap();
    @JsonProperty(value="webUrl")
    public String webUrl;

    static {
        d = UserProfile.class.getName();
        CREATOR = new Parcelable.Creator(){

            public UserProfile a(Parcel parcel) {
                return new UserProfile(parcel);
            }

            public UserProfile[] a(int n) {
                return new UserProfile[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public UserProfile() {
    }

    private UserProfile(Parcel parcel) {
        this.accountIcon = (AccountIcon)parcel.readParcelable(UserProfile.class.getClassLoader());
        this.webUrl = parcel.readString();
        parcel.readStringList(this.apps);
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.e = (HashMap)parcel.readSerializable();
        this.f = (HashMap)parcel.readSerializable();
    }

    public static UserProfile a(NetworkResponse networkResponse) {
        return UserProfile.a(networkResponse, UserProfile.class);
    }

    public void a(int n) {
        this.b += n;
    }

    public boolean b() {
        return this.accountIcon.d();
    }

    public int describeContents() {
        return 0;
    }

    public long getAccountId() {
        return this.accountIcon.accountId;
    }

    public List<String> getApps() {
        return this.apps;
    }

    public String getHandle() {
        return this.accountIcon.handle;
    }

    public int getNumberFollowees() {
        return this.c;
    }

    public int getNumberFollowers() {
        return this.b;
    }

    public HashMap<String, Integer> getPerformanceCountByApp() {
        return this.e;
    }

    public String getPictureUrl() {
        return this.accountIcon.picUrl;
    }

    public HashMap<String, String> getPlayerIds() {
        return this.f;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    @JsonProperty(value="performances")
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public void setPerformances(List<Map<String, String>> object) {
        object = object.iterator();
        while (object.hasNext()) {
            Map map = (Map)object.next();
            this.e.put((String)map.get("app"), Integer.valueOf((String)map.get("numPerformances")));
        }
    }

    @JsonProperty(value="players")
    public void setPlayers(List<Map<String, String>> object) {
        object = object.iterator();
        while (object.hasNext()) {
            Map map = (Map)object.next();
            this.f.put((String)map.get("app"), (String)map.get("playerId"));
        }
    }

    @JsonProperty(value="social")
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public void setSocial(Map<String, Object> map) {
        this.b = Integer.parseInt(map.get("numFollowers").toString());
        this.c = Integer.parseInt(map.get("numFollowees").toString());
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.accountIcon, n);
        parcel.writeString(this.webUrl);
        parcel.writeStringList(this.apps);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeSerializable(this.e);
        parcel.writeSerializable(this.f);
    }
}

