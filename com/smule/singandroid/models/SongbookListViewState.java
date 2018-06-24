/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.models.SongbookSection;

public class SongbookListViewState
implements Parcelable {
    public static final Parcelable.Creator<SongbookListViewState> CREATOR = new Parcelable.Creator<SongbookListViewState>(){

        public SongbookListViewState a(Parcel parcel) {
            return new SongbookListViewState(parcel);
        }

        public SongbookListViewState[] a(int n) {
            return new SongbookListViewState[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    public SongbookSortSelector.SortType a;
    public int b;
    public int c;

    /*
     * Enabled aggressive block sorting
     */
    private SongbookListViewState(Parcel parcel) {
        int n = parcel.readInt();
        SongbookSortSelector.SortType sortType = n == -1 ? null : SongbookSortSelector.SortType.values()[n];
        this.a = sortType;
        this.b = parcel.readInt();
        this.c = parcel.readInt();
    }

    public SongbookListViewState(SongbookSection songbookSection) {
        this.a = SongbookSortSelector.a(songbookSection).b();
    }

    public void a(int n, int n2) {
        this.b = n;
        this.c = n2;
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        n = this.a == null ? -1 : this.a.ordinal();
        parcel.writeInt(n);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
    }

}

