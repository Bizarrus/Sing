/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.android.network.models.AccountIcon;

public class BlockedUserListItemContainer
implements Parcelable {
    public static final Parcelable.Creator<BlockedUserListItemContainer> CREATOR = new Parcelable.Creator<BlockedUserListItemContainer>(){

        public BlockedUserListItemContainer a(Parcel parcel) {
            return new BlockedUserListItemContainer(parcel);
        }

        public BlockedUserListItemContainer[] a(int n) {
            return new BlockedUserListItemContainer[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private boolean a;
    private AccountIcon b;

    /*
     * Enabled aggressive block sorting
     */
    public BlockedUserListItemContainer(Parcel parcel) {
        this.b = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        boolean bl = parcel.readInt() == 1;
        this.a = bl;
    }

    public BlockedUserListItemContainer(AccountIcon accountIcon, boolean bl) {
        this.b = accountIcon;
        this.a = bl;
    }

    public AccountIcon a() {
        return this.b;
    }

    public void a(boolean bl) {
        this.a = bl;
    }

    public boolean b() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.b, n);
        n = this.a ? 1 : 0;
        parcel.writeInt(n);
    }

}

