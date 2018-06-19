package com.smule.singandroid;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.smule.android.network.models.AccountIcon;

public class BlockedUserListItemContainer implements Parcelable {
    public static final Creator<BlockedUserListItemContainer> CREATOR = new C37821();
    private boolean f18499a;
    private AccountIcon f18500b;

    static class C37821 implements Creator<BlockedUserListItemContainer> {
        C37821() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m19875a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m19876a(i);
        }

        public BlockedUserListItemContainer m19875a(Parcel parcel) {
            return new BlockedUserListItemContainer(parcel);
        }

        public BlockedUserListItemContainer[] m19876a(int i) {
            return new BlockedUserListItemContainer[i];
        }
    }

    public BlockedUserListItemContainer(AccountIcon accountIcon, boolean z) {
        this.f18500b = accountIcon;
        this.f18499a = z;
    }

    public BlockedUserListItemContainer(Parcel parcel) {
        this.f18500b = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.f18499a = parcel.readInt() == 1;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f18500b, i);
        parcel.writeInt(this.f18499a ? 1 : 0);
    }

    public AccountIcon m19877a() {
        return this.f18500b;
    }

    public boolean m19879b() {
        return this.f18499a;
    }

    public void m19878a(boolean z) {
        this.f18499a = z;
    }

    public int describeContents() {
        return 0;
    }
}
