/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 */
package com.smule.singandroid;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.RecPerformanceIcon;
import java.util.Comparator;

public class PerformanceListItemContainer
implements Parcelable {
    public static final Parcelable.Creator<PerformanceListItemContainer> CREATOR = new Parcelable.Creator<PerformanceListItemContainer>(){

        public PerformanceListItemContainer a(Parcel parcel) {
            return new PerformanceListItemContainer(parcel);
        }

        public PerformanceListItemContainer[] a(int n) {
            return new PerformanceListItemContainer[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public int a;
    public PerformanceV2 b;
    public String c;
    public boolean d;

    /*
     * Enabled aggressive block sorting
     */
    public PerformanceListItemContainer(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        boolean bl = parcel.readInt() == 1;
        this.d = bl;
        this.c = parcel.readString();
    }

    public PerformanceListItemContainer(PerformanceV2 performanceV2) {
        this.a = performanceV2.createdAt;
        this.b = performanceV2;
        this.c = null;
    }

    public PerformanceListItemContainer(RecPerformanceIcon recPerformanceIcon) {
        this.a = recPerformanceIcon.performanceIcon.createdAt;
        this.b = recPerformanceIcon.performanceIcon;
        this.c = recPerformanceIcon.recId;
    }

    public PerformanceV2 a() {
        return this.b;
    }

    public void b() {
        this.b.hasBeenLoved = true;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block3 : {
            block2 : {
                if (!(object instanceof PerformanceListItemContainer)) break block2;
                object = (PerformanceListItemContainer)object;
                if (this.b.performanceKey.equals(object.b.performanceKey) && object.d == this.d && TextUtils.equals((CharSequence)object.c, (CharSequence)this.c)) break block3;
            }
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeInt(this.a);
        parcel.writeParcelable((Parcelable)this.b, n);
        n = this.d ? 1 : 0;
        parcel.writeInt(n);
        parcel.writeString(this.c);
    }

    public static class PerformanceComparatorByTimeCreated
    implements Comparator<PerformanceListItemContainer> {
        public int a(PerformanceListItemContainer performanceListItemContainer, PerformanceListItemContainer performanceListItemContainer2) {
            if (performanceListItemContainer2.a > performanceListItemContainer.a) {
                return 1;
            }
            if (performanceListItemContainer.a > performanceListItemContainer2.a) {
                return -1;
            }
            return 0;
        }

        @Override
        public /* synthetic */ int compare(Object object, Object object2) {
            return this.a((PerformanceListItemContainer)object, (PerformanceListItemContainer)object2);
        }
    }

}

