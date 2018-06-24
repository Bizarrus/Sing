/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Intent
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.smule.singandroid.survey.SurveyContext
 *  com.smule.singandroid.survey.SurveyContext$EntryPoint
 */
package com.smule.singandroid;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.survey.SurveyContext;
import java.util.ArrayList;
import java.util.List;

public class PostSingBundle
implements Parcelable {
    public static final Parcelable.Creator<PostSingBundle> CREATOR;
    public static String a;
    public SingBundle b;
    public PerformanceV2 c;
    public boolean d;
    public boolean e;
    public String f;
    public SurveyContext.EntryPoint g;
    public boolean h;
    public ArrayList<PerformanceManager.PerformanceResourceInfo> i;

    static {
        a = PostSingBundle.class.getName();
        CREATOR = new Parcelable.Creator<PostSingBundle>(){

            public PostSingBundle a(Parcel parcel) {
                return new PostSingBundle(parcel);
            }

            public PostSingBundle[] a(int n) {
                return new PostSingBundle[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    protected PostSingBundle(Parcel parcel) {
        boolean bl = true;
        this.b = (SingBundle)parcel.readParcelable(SingBundle.class.getClassLoader());
        this.c = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        boolean bl2 = parcel.readByte() != 0;
        this.d = bl2;
        bl2 = parcel.readByte() != 0 ? bl : false;
        this.e = bl2;
        this.f = parcel.readString();
        String string2 = parcel.readString();
        string2 = string2 == null ? null : SurveyContext.EntryPoint.valueOf((String)string2);
        this.g = string2;
        this.i = parcel.createTypedArrayList(PerformanceManager.PerformanceResourceInfo.CREATOR);
    }

    public PostSingBundle(SingBundle singBundle) {
        this.b = singBundle;
    }

    public static PostSingBundle a(Intent intent) {
        intent.setExtrasClassLoader(PostSingBundle.class.getClassLoader());
        return (PostSingBundle)intent.getParcelableExtra(a);
    }

    public void b(Intent intent) {
        intent.putExtra(a, (Parcelable)this);
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeParcelable((Parcelable)this.b, 0);
        parcel.writeParcelable((Parcelable)this.c, 0);
        n = this.d ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.e ? n2 : 0;
        parcel.writeByte((byte)n);
        parcel.writeString(this.f);
        String string2 = this.g == null ? null : this.g.name();
        parcel.writeString(string2);
        parcel.writeTypedList(this.i);
    }

}

