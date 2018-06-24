/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.ColorInt
 *  android.support.annotation.NonNull
 *  android.support.v4.content.ContextCompat
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.UserProfile;
import com.smule.android.utils.ParcelUtils;
import com.smule.singandroid.profile.Theme;
import java.io.Serializable;

public class ProfileUserInfo
implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        public ProfileUserInfo a(Parcel parcel) {
            return new ProfileUserInfo(parcel);
        }

        public ProfileUserInfo[] a(int n) {
            return new ProfileUserInfo[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private final String a = ProfileUserInfo.class.getName();
    private final boolean b;
    private final boolean c;
    private final UserProfile d;
    private final ProfileCustomizations e;
    private Theme f;
    private ColorSet g;
    private String h;

    /*
     * Enabled aggressive block sorting
     */
    public ProfileUserInfo(Context context, boolean bl, @NonNull UserProfile userProfile, ProfileCustomizations profileCustomizations, @NonNull ColorSet colorSet, @NonNull String string2) {
        this.b = bl;
        this.d = userProfile;
        this.e = profileCustomizations;
        this.f = profileCustomizations == null || profileCustomizations.theme == null ? Theme.a : Theme.a(context, profileCustomizations.theme);
        this.g = colorSet;
        bl = this.d.accountIcon.accountId == UserManager.a().f();
        this.c = bl;
        this.h = string2;
    }

    public ProfileUserInfo(Parcel parcel) {
        this.b = ParcelUtils.a(parcel);
        this.c = ParcelUtils.a(parcel);
        this.d = (UserProfile)parcel.readParcelable(UserProfile.class.getClassLoader());
        this.e = (ProfileCustomizations)parcel.readParcelable(ProfileCustomizations.class.getClassLoader());
        this.f = (Theme)((Object)parcel.readSerializable());
        this.g = (ColorSet)parcel.readParcelable(ColorSet.class.getClassLoader());
        this.h = parcel.readString();
    }

    @NonNull
    public AccountIcon a() {
        return this.d.accountIcon;
    }

    public ProfileUserInfo a(Context context) {
        return new ProfileUserInfo(context, this.b, this.d, this.e, this.g, this.h);
    }

    public void a(PerformanceV2 performanceV2) {
        this.e.pinPerformanceIcon = performanceV2;
    }

    public void a(ColorSet colorSet) {
        this.g = colorSet;
    }

    public void a(Theme theme) {
        this.f = theme;
    }

    public void a(String string2) {
        this.d.accountIcon.picUrl = string2;
    }

    public long b() {
        return this.d.accountIcon.accountId;
    }

    public void b(String string2) {
        this.h = string2;
    }

    @NonNull
    public UserProfile c() {
        return this.d;
    }

    public ProfileCustomizations d() {
        return this.e;
    }

    public int describeContents() {
        return 0;
    }

    @NonNull
    public ColorSet e() {
        return this.g;
    }

    public boolean f() {
        return this.c;
    }

    @NonNull
    public boolean g() {
        return this.b;
    }

    @NonNull
    public Theme h() {
        return this.f;
    }

    public String i() {
        return this.d.accountIcon.picUrl;
    }

    public boolean j() {
        return this.d.accountIcon.a();
    }

    public String k() {
        return this.h;
    }

    public int l() {
        return this.d.getNumberFollowers();
    }

    public int m() {
        return this.d.getNumberFollowees();
    }

    public String n() {
        return this.d.accountIcon.jid;
    }

    public boolean o() {
        return this.d.accountIcon.c();
    }

    public String p() {
        return this.d.accountIcon.handle;
    }

    public void writeToParcel(Parcel parcel, int n) {
        ParcelUtils.a(parcel, this.b);
        ParcelUtils.a(parcel, this.c);
        parcel.writeParcelable((Parcelable)this.d, n);
        parcel.writeParcelable((Parcelable)this.e, n);
        parcel.writeSerializable((Serializable)((Object)this.f));
        parcel.writeParcelable((Parcelable)this.g, n);
        parcel.writeString(this.h);
    }

    public static class ColorSet
    implements Parcelable {
        public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

            public Object createFromParcel(Parcel parcel) {
                return new ColorSet(parcel);
            }

            public Object[] newArray(int n) {
                return new ColorSet[n];
            }
        };
        @ColorInt
        public int a;
        @ColorInt
        public int b;
        @ColorInt
        public int c;
        @ColorInt
        public int d;
        @ColorInt
        public int e;

        public ColorSet() {
        }

        public ColorSet(Context context) {
            this.a = ContextCompat.getColor((Context)context, (int)Theme.a.a());
            this.b = ContextCompat.getColor((Context)context, (int)Theme.a.b());
            this.c = ContextCompat.getColor((Context)context, (int)Theme.a.c());
            this.d = ContextCompat.getColor((Context)context, (int)Theme.a.d());
            this.e = ContextCompat.getColor((Context)context, (int)Theme.a.e());
        }

        public ColorSet(Parcel parcel) {
            this.a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int n) {
            parcel.writeInt(this.a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }

    }

}

