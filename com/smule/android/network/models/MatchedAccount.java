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
import com.smule.android.magicui.ExternalFriendContainer;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.MatchedAccount;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MatchedAccount
implements Parcelable,
ExternalFriendContainer {
    public static final Parcelable.Creator<MatchedAccount> CREATOR = new Parcelable.Creator<MatchedAccount>(){

        public MatchedAccount a(Parcel parcel) {
            return new MatchedAccount(parcel);
        }

        public MatchedAccount[] a(int n) {
            return new MatchedAccount[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="accountIcon")
    public AccountIcon accountIcon;
    @JsonProperty(value="realName")
    public String realName;

    public MatchedAccount() {
    }

    public MatchedAccount(Parcel parcel) {
        this.realName = parcel.readString();
        this.accountIcon = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
    }

    @Override
    public String a() {
        return this.realName;
    }

    @Override
    public String b() {
        return this.accountIcon.picUrl;
    }

    @Override
    public long c() {
        return this.accountIcon.accountId;
    }

    @Override
    public AccountIcon d() {
        return this.accountIcon;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        String string2 = this.realName == null ? "" : this.realName;
        parcel.writeString(string2);
        parcel.writeParcelable((Parcelable)this.accountIcon, n);
    }
}

