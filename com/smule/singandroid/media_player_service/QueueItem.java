/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.v4.media.MediaMetadataCompat
 *  android.support.v4.media.MediaMetadataCompat$Builder
 *  com.smule.singandroid.utils.PerformanceV2Util
 */
package com.smule.singandroid.media_player_service;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaMetadataCompat;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.PerformanceV2Util;
import com.smule.android.songbook.SongbookEntry;
import java.util.concurrent.Future;

public class QueueItem
implements Parcelable {
    public static final Parcelable.Creator<QueueItem> CREATOR = new Parcelable.Creator<QueueItem>(){

        public QueueItem a(Parcel parcel) {
            return new QueueItem(parcel);
        }

        public QueueItem[] a(int n) {
            return new QueueItem[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    private final String a;
    private final SongbookEntry b;
    private final PerformanceV2 c;
    private final boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private String h;
    private long i;
    private boolean j;
    private MediaMetadataCompat k;

    /*
     * Enabled aggressive block sorting
     */
    public QueueItem(Parcel parcel) {
        boolean bl = true;
        this.a = QueueItem.class.getName();
        this.b = (SongbookEntry)parcel.readParcelable(SongbookEntry.class.getClassLoader());
        this.c = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readLong();
        boolean bl2 = parcel.readInt() != 0;
        this.f = bl2;
        bl2 = parcel.readInt() != 0 ? bl : false;
        this.d = bl2;
    }

    public QueueItem(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        this(songbookEntry, performanceV2, true);
    }

    public QueueItem(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean bl) {
        this.a = QueueItem.class.getName();
        this.b = songbookEntry;
        this.c = performanceV2;
        this.d = bl;
    }

    public MediaMetadataCompat a(Context context) {
        if (this.k == null) {
            this.k = this.b(context);
        }
        return this.k;
    }

    public PerformanceV2 a() {
        return this.c;
    }

    public String a(boolean bl) {
        return PerformanceV2Util.a(this.c, bl);
    }

    public void a(long l) {
        this.i = l;
    }

    public void a(MediaMetadataCompat mediaMetadataCompat) {
        this.k = mediaMetadataCompat;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final DownloadResourcesListener downloadResourcesListener) {
        ArrangementVersionLite arrangementVersionLite;
        Log.b(this.a, "downloadResources: " + this.b());
        if (this.c != null || this.j) {
            downloadResourcesListener.a();
            return;
        }
        if (this.b == null) {
            throw new RuntimeException("mEntry was null?");
        }
        if (!this.b.t()) return;
        {
            arrangementVersionLite = ((ArrangementVersionLiteEntry)this.b).a;
            if (arrangementVersionLite.arrangementVersion != null) {
                this.g = arrangementVersionLite.arrangementVersion.c().url;
                this.j = true;
                downloadResourcesListener.a();
                return;
            }
        }
        com.smule.android.network.managers.ArrangementManager.a().a(this.b.c(), new ArrangementManager(){

            @Override
            public void handleResponse(ArrangementManager.ArrangementVersionResponse arrangementVersionResponse) {
                if (arrangementVersionResponse.a()) {
                    arrangementVersionLite.a(arrangementVersionResponse.mArrangementVersion);
                    QueueItem.this.g = arrangementVersionLite.arrangementVersion.c().url;
                    QueueItem.this.j = true;
                    downloadResourcesListener.a();
                    return;
                }
                Log.e(QueueItem.this.a, "Error fetching preview resource for listing with product id: " + QueueItem.this.b.c());
                downloadResourcesListener.b();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    protected MediaMetadataCompat b(Context object) {
        String string2;
        String string3;
        String string4;
        if (this.c != null) {
            string3 = this.c.performanceKey;
            string2 = this.c.title;
            string4 = com.smule.singandroid.utils.PerformanceV2Util.a((Resources)object.getResources(), (PerformanceV2)this.c, (boolean)false).toString();
            object = this.c.coverUrl;
        } else {
            string3 = this.b.c();
            string2 = this.b.e();
            string4 = this.b.f();
            object = this.b.j();
        }
        Object object2 = object;
        if (object == null) {
            object2 = "";
        }
        object = this.g;
        return new MediaMetadataCompat.Builder().putString("android.media.metadata.MEDIA_ID", string3).putString("android.media.metadata.TITLE", string2).putString("android.media.metadata.ARTIST", string4).putString("com.smule.CUSTOM_METADATA_TRACK_SOURCE", (String)object).putString("android.media.metadata.ALBUM_ART_URI", (String)object2).putLong("android.media.metadata.DURATION", this.i).build();
    }

    public String b() {
        if (this.c != null) {
            return this.c.performanceKey;
        }
        return this.b.c();
    }

    public void b(boolean bl) {
        this.f = bl;
    }

    public String c() {
        if (this.c != null && this.c.b()) {
            return this.c.shortTermRenderedUrl;
        }
        return this.g;
    }

    public String d() {
        if (this.c != null && this.c.c()) {
            return this.c.videoRenderedUrl;
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public long e() {
        return this.i;
    }

    public boolean f() {
        return this.e;
    }

    public boolean g() {
        return this.f;
    }

    public boolean h() {
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeParcelable((Parcelable)this.b, n);
        parcel.writeParcelable((Parcelable)this.c, n);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeLong(this.i);
        n = this.f ? 1 : 0;
        parcel.writeInt(n);
        n = this.d ? n2 : 0;
        parcel.writeInt(n);
    }

    public static interface DownloadResourcesListener {
        public void a();

        public void b();
    }

}

