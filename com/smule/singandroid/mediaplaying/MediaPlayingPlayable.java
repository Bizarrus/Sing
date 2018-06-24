/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.ParcelFormatException
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.NonNull
 *  android.text.TextUtils
 */
package com.smule.singandroid.mediaplaying;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;

public final class MediaPlayingPlayable
implements Parcelable {
    public static final Parcelable.Creator<MediaPlayingPlayable> CREATOR;
    static final String a;
    final PerformanceV2 b;
    final SongbookEntry c;
    final String d;

    static {
        a = MediaPlayingPlayable.class.getSimpleName();
        CREATOR = new Parcelable.Creator<MediaPlayingPlayable>(){

            public MediaPlayingPlayable a(Parcel parcel) {
                return new MediaPlayingPlayable(parcel);
            }

            public MediaPlayingPlayable[] a(int n) {
                return new MediaPlayingPlayable[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public MediaPlayingPlayable(Parcel parcel) {
        this.b = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.c = (SongbookEntry)parcel.readParcelable(SongbookEntry.class.getClassLoader());
        this.d = parcel.readString();
        this.d();
    }

    public MediaPlayingPlayable(@NonNull PerformanceV2 performanceV2) {
        this(MediaPlayingPlayable.a(performanceV2), performanceV2, null);
    }

    public MediaPlayingPlayable(@NonNull SongbookEntry songbookEntry) {
        this(MediaPlayingPlayable.a(songbookEntry), null, songbookEntry);
    }

    private MediaPlayingPlayable(String string2, PerformanceV2 performanceV2, SongbookEntry songbookEntry) {
        this.d = string2;
        this.b = performanceV2;
        this.c = songbookEntry;
        this.d();
    }

    public static String a(PerformanceV2 performanceV2) {
        return performanceV2.performanceKey;
    }

    public static String a(SongbookEntry songbookEntry) {
        return songbookEntry.c();
    }

    private void d() {
        if (TextUtils.isEmpty((CharSequence)this.d) || this.b == null && this.c == null || this.b != null && this.c != null) {
            Log.d(a, "Invalid Playable parcel", (Throwable)new ParcelFormatException());
        }
    }

    public PerformanceV2 a() {
        return this.b;
    }

    public SongbookEntry b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeParcelable((Parcelable)this.b, n);
        parcel.writeParcelable((Parcelable)this.c, n);
        parcel.writeString(this.d);
    }

}

