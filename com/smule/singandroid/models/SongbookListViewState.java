package com.smule.singandroid.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.smule.singandroid.customviews.SongbookSortSelector;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;

public class SongbookListViewState implements Parcelable {
    public static final Creator<SongbookListViewState> CREATOR = new C47301();
    public SortType f23509a;
    public int f23510b;
    public int f23511c;

    static class C47301 implements Creator<SongbookListViewState> {
        C47301() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m24753a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m24754a(i);
        }

        public SongbookListViewState m24753a(Parcel parcel) {
            return new SongbookListViewState(parcel);
        }

        public SongbookListViewState[] m24754a(int i) {
            return new SongbookListViewState[i];
        }
    }

    public void m24755a(int i, int i2) {
        this.f23510b = i;
        this.f23511c = i2;
    }

    public SongbookListViewState(SongbookSection songbookSection) {
        this.f23509a = SongbookSortSelector.m23493a(songbookSection).m23489b();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f23509a == null ? -1 : this.f23509a.ordinal());
        parcel.writeInt(this.f23510b);
        parcel.writeInt(this.f23511c);
    }

    private SongbookListViewState(Parcel parcel) {
        int readInt = parcel.readInt();
        this.f23509a = readInt == -1 ? null : SortType.values()[readInt];
        this.f23510b = parcel.readInt();
        this.f23511c = parcel.readInt();
    }
}
