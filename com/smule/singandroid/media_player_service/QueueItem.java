package com.smule.singandroid.media_player_service;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaMetadataCompat.Builder;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.ArrangementManager$ArrangementVersionCallback;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;
import com.smule.android.network.managers.ResourceManager;
import com.smule.android.network.managers.ResourceManager.ResourceFetchListener;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.PerformanceV2Util;
import com.smule.android.songbook.SongbookEntry;

public class QueueItem implements Parcelable {
    public static final Creator<QueueItem> CREATOR = new C47293();
    private final String f23466a;
    private final SongbookEntry f23467b;
    private final PerformanceV2 f23468c;
    private final boolean f23469d;
    private boolean f23470e;
    private boolean f23471f;
    private String f23472g;
    private String f23473h;
    private long f23474i;
    private boolean f23475j;
    private MediaMetadataCompat f23476k;

    public interface DownloadResourcesListener {
        void mo6887a();

        void mo6888b();
    }

    static class C47293 implements Creator<QueueItem> {
        C47293() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m24718a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m24719a(i);
        }

        public QueueItem m24718a(Parcel parcel) {
            return new QueueItem(parcel);
        }

        public QueueItem[] m24719a(int i) {
            return new QueueItem[i];
        }
    }

    public QueueItem(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        this(songbookEntry, performanceV2, true);
    }

    public QueueItem(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean z) {
        this.f23466a = QueueItem.class.getName();
        this.f23467b = songbookEntry;
        this.f23468c = performanceV2;
        this.f23469d = z;
    }

    public PerformanceV2 m24725a() {
        return this.f23468c;
    }

    public String m24731b() {
        if (this.f23468c != null) {
            return this.f23468c.performanceKey;
        }
        return this.f23467b.mo6289c();
    }

    public String m24733c() {
        if (this.f23468c == null || !this.f23468c.b()) {
            return this.f23472g;
        }
        return this.f23468c.shortTermRenderedUrl;
    }

    public String m24734d() {
        if (this.f23468c == null || !this.f23468c.c()) {
            return null;
        }
        return this.f23468c.videoRenderedUrl;
    }

    public String m24726a(boolean z) {
        return PerformanceV2Util.m18805a(this.f23468c, z);
    }

    public long m24735e() {
        return this.f23474i;
    }

    public void m24727a(long j) {
        this.f23474i = j;
    }

    public boolean m24736f() {
        return this.f23470e;
    }

    public void m24732b(boolean z) {
        this.f23471f = z;
    }

    public boolean m24737g() {
        return this.f23471f;
    }

    public boolean m24738h() {
        return this.f23469d;
    }

    public void m24729a(final DownloadResourcesListener downloadResourcesListener) {
        Log.b(this.f23466a, "downloadResources: " + m24731b());
        if (this.f23468c != null || this.f23475j) {
            downloadResourcesListener.mo6887a();
        } else if (this.f23467b == null) {
            throw new RuntimeException("mEntry was null?");
        } else if (this.f23467b.m18773s()) {
            ResourceManager.m18313a(this.f23467b.mo6289c(), "preview", new ResourceFetchListener(this) {
                final /* synthetic */ QueueItem f23462b;

                public void mo6892a(ResourceV2 resourceV2) {
                    this.f23462b.f23472g = resourceV2.url;
                    this.f23462b.f23475j = true;
                    downloadResourcesListener.mo6887a();
                }

                public void mo6891a() {
                    Log.e(this.f23462b.f23466a, "Error fetching preview resource for listing with product id: " + this.f23462b.f23467b.mo6289c());
                    downloadResourcesListener.mo6888b();
                }
            });
        } else if (this.f23467b.m18772r()) {
            final ArrangementVersionLite arrangementVersionLite = ((ArrangementVersionLiteEntry) this.f23467b).f17623a;
            if (arrangementVersionLite.arrangementVersion != null) {
                this.f23472g = arrangementVersionLite.arrangementVersion.b().url;
                this.f23475j = true;
                downloadResourcesListener.mo6887a();
                return;
            }
            ArrangementManager.a().a(this.f23467b.mo6289c(), new ArrangementManager$ArrangementVersionCallback(this) {
                final /* synthetic */ QueueItem f23465c;

                public void handleResponse(ArrangementVersionResponse arrangementVersionResponse) {
                    if (arrangementVersionResponse.a()) {
                        arrangementVersionLite.a(arrangementVersionResponse.mArrangementVersion);
                        this.f23465c.f23472g = arrangementVersionLite.arrangementVersion.b().url;
                        this.f23465c.f23475j = true;
                        downloadResourcesListener.mo6887a();
                        return;
                    }
                    Log.e(this.f23465c.f23466a, "Error fetching preview resource for listing with product id: " + this.f23465c.f23467b.mo6289c());
                    downloadResourcesListener.mo6888b();
                }
            });
        }
    }

    public MediaMetadataCompat m24724a(Context context) {
        if (this.f23476k == null) {
            this.f23476k = m24730b(context);
        }
        return this.f23476k;
    }

    public void m24728a(MediaMetadataCompat mediaMetadataCompat) {
        this.f23476k = mediaMetadataCompat;
    }

    protected MediaMetadataCompat m24730b(Context context) {
        String str;
        String str2;
        String spannableString;
        String str3;
        if (this.f23468c != null) {
            str = this.f23468c.performanceKey;
            str2 = this.f23468c.title;
            spannableString = com.smule.singandroid.utils.PerformanceV2Util.m25933a(context.getResources(), this.f23468c, false).toString();
            str3 = this.f23468c.coverUrl;
        } else {
            str = this.f23467b.mo6289c();
            str2 = this.f23467b.mo6291e();
            spannableString = this.f23467b.mo6292f();
            str3 = this.f23467b.mo6295i();
        }
        if (str3 == null) {
            str3 = "";
        }
        return new Builder().putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, str).putString(MediaMetadataCompat.METADATA_KEY_TITLE, str2).putString(MediaMetadataCompat.METADATA_KEY_ARTIST, spannableString).putString("com.smule.CUSTOM_METADATA_TRACK_SOURCE", this.f23472g).putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, str3).putLong(MediaMetadataCompat.METADATA_KEY_DURATION, this.f23474i).build();
    }

    public int describeContents() {
        return 0;
    }

    public QueueItem(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f23466a = QueueItem.class.getName();
        this.f23467b = (SongbookEntry) parcel.readParcelable(SongbookEntry.class.getClassLoader());
        this.f23468c = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.f23472g = parcel.readString();
        this.f23473h = parcel.readString();
        this.f23474i = parcel.readLong();
        if (parcel.readInt() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.f23471f = z;
        if (parcel.readInt() == 0) {
            z2 = false;
        }
        this.f23469d = z2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(this.f23467b, i);
        parcel.writeParcelable(this.f23468c, i);
        parcel.writeString(this.f23472g);
        parcel.writeString(this.f23473h);
        parcel.writeLong(this.f23474i);
        if (this.f23471f) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.f23469d) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }
}
