/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid.singflow;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.utils.ParcelUtils;

public class FreeformBundle
implements Parcelable {
    public static final Parcelable.Creator<FreeformBundle> CREATOR = new Parcelable.Creator<FreeformBundle>(){

        public FreeformBundle a(Parcel parcel) {
            return new FreeformBundle(parcel);
        }

        public FreeformBundle[] a(int n) {
            return new FreeformBundle[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private float a;
    private float b;
    private float c;
    private int d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;

    private FreeformBundle(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readInt();
        this.e = ParcelUtils.a(parcel);
        this.f = ParcelUtils.a(parcel);
        this.g = ParcelUtils.a(parcel);
        this.h = ParcelUtils.a(parcel);
    }

    private FreeformBundle(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
    }

    public float a() {
        return this.a;
    }

    public void a(float f) {
        this.c = f;
    }

    public void a(int n) {
        this.d = n;
    }

    public void a(boolean bl) {
        this.h = bl;
    }

    public float b() {
        return this.b;
    }

    public float c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public boolean g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public String toString() {
        return "FreeformBundle {foregroundDurationInSeconds=" + this.a + ", foregroundDelayInMS=" + this.b + ", videoDurationInMS=" + this.c + ", videoSegmentCount=" + this.d + ", wereHeadphonesPluggedInDuringRecording=" + this.e + ", headphonesHadMicDuringRecording=" + this.f + ", wereHeadphonesNeverUsed=" + this.g + ", isBlackScreenOver20Seconds=" + this.h + "}";
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeInt(this.d);
        ParcelUtils.a(parcel, this.e);
        ParcelUtils.a(parcel, this.f);
        ParcelUtils.a(parcel, this.g);
        ParcelUtils.a(parcel, this.h);
    }

    public static class Builder {
        private float a;
        private float b;
        private float c;
        private int d;
        private boolean e;
        private boolean f;
        private boolean g;
        private boolean h;

        public Builder a(float f) {
            this.a = f;
            return this;
        }

        public Builder a(boolean bl) {
            this.e = bl;
            return this;
        }

        public FreeformBundle a() {
            return new FreeformBundle(this);
        }

        public Builder b(float f) {
            this.b = f;
            return this;
        }

        public Builder b(boolean bl) {
            this.f = bl;
            return this;
        }

        public Builder c(boolean bl) {
            this.g = bl;
            return this;
        }
    }

}

