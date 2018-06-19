package com.smule.singandroid;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.RecPerformanceIcon;
import java.util.Comparator;

public class PerformanceListItemContainer implements Parcelable {
    public static final Creator<PerformanceListItemContainer> CREATOR = new C39211();
    public int f19112a;
    public PerformanceV2 f19113b;
    public String f19114c;
    public boolean f19115d = false;
    public boolean f19116e;

    static class C39211 implements Creator<PerformanceListItemContainer> {
        C39211() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m20647a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m20648a(i);
        }

        public PerformanceListItemContainer m20647a(Parcel parcel) {
            return new PerformanceListItemContainer(parcel);
        }

        public PerformanceListItemContainer[] m20648a(int i) {
            return new PerformanceListItemContainer[i];
        }
    }

    public static class PerformanceComparatorByTimeCreated implements Comparator<PerformanceListItemContainer> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m20649a((PerformanceListItemContainer) obj, (PerformanceListItemContainer) obj2);
        }

        public int m20649a(PerformanceListItemContainer performanceListItemContainer, PerformanceListItemContainer performanceListItemContainer2) {
            if (performanceListItemContainer2.f19112a > performanceListItemContainer.f19112a) {
                return 1;
            }
            if (performanceListItemContainer.f19112a > performanceListItemContainer2.f19112a) {
                return -1;
            }
            return 0;
        }
    }

    public PerformanceListItemContainer(PerformanceV2 performanceV2) {
        this.f19112a = performanceV2.createdAt;
        this.f19113b = performanceV2;
        this.f19114c = null;
    }

    public PerformanceListItemContainer(RecPerformanceIcon recPerformanceIcon) {
        this.f19112a = recPerformanceIcon.performanceIcon.createdAt;
        this.f19113b = recPerformanceIcon.performanceIcon;
        this.f19114c = recPerformanceIcon.recId;
    }

    public PerformanceListItemContainer(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f19112a = parcel.readInt();
        this.f19113b = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.f19115d = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.f19116e = z2;
        this.f19114c = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.f19112a);
        parcel.writeParcelable(this.f19113b, i);
        if (this.f19115d) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.f19116e) {
            i3 = 0;
        }
        parcel.writeInt(i3);
        parcel.writeString(this.f19114c);
    }

    public PerformanceV2 m20650a() {
        return this.f19113b;
    }

    public void m20651b() {
        this.f19113b.hasBeenLoved = true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PerformanceListItemContainer)) {
            return false;
        }
        PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer) obj;
        if (this.f19113b.performanceKey.equals(performanceListItemContainer.f19113b.performanceKey) && performanceListItemContainer.f19116e == this.f19116e && TextUtils.equals(performanceListItemContainer.f19114c, this.f19114c)) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }
}
