package com.smule.singandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.audio.Metadata;

public class SingBundle implements Parcelable, Cloneable {
    public static final Creator<SingBundle> CREATOR = new C41391();
    public static final String f20136a = SingBundle.class.getName();
    public final PerformanceType f20137b;
    public final GateType f20138c;
    public final SongbookEntry f20139d;
    public final int f20140e;
    public final PerformanceV2 f20141f;
    public final int f20142g;
    public final String f20143h;
    public final String f20144i;
    public final String f20145j;
    public final boolean f20146k;
    public final boolean f20147l;
    public final boolean f20148m;
    public final boolean f20149n;
    public final String f20150o;
    public final boolean f20151p;
    public final boolean f20152q;
    public final Long f20153r;
    public final int f20154s;
    public final float f20155t;
    public final boolean f20156u;
    public final Metadata f20157v;
    public final GlitchType f20158w;
    private String f20159x;
    private boolean f20160y;
    private final Bundle f20161z;

    static class C41391 implements Creator<SingBundle> {
        C41391() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m21608a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m21609a(i);
        }

        public SingBundle m21608a(Parcel parcel) {
            return new SingBundle(parcel);
        }

        public SingBundle[] m21609a(int i) {
            return new SingBundle[i];
        }
    }

    public static final class Builder {
        public boolean f20098a;
        public boolean f20099b;
        public Long f20100c = Long.valueOf(-1);
        public int f20101d;
        public float f20102e;
        public Metadata f20103f;
        public GlitchType f20104g;
        private PerformanceType f20105h = PerformanceType.UNDEFINED;
        private GateType f20106i = GateType.NONE;
        private SongbookEntry f20107j;
        private int f20108k = 0;
        private PerformanceV2 f20109l = null;
        private int f20110m = 0;
        private String f20111n = null;
        private String f20112o = null;
        private String f20113p = null;
        private boolean f20114q = false;
        private boolean f20115r = false;
        private boolean f20116s = true;
        private boolean f20117t = false;
        private String f20118u = null;
        private boolean f20119v = false;
        private String f20120w;
        private boolean f20121x = false;
        private Bundle f20122y;

        public Builder(SingBundle singBundle) {
            this.f20105h = singBundle.f20137b;
            this.f20106i = singBundle.f20138c;
            this.f20107j = singBundle.f20139d;
            this.f20108k = singBundle.f20140e;
            this.f20109l = singBundle.f20141f;
            this.f20110m = singBundle.f20142g;
            this.f20111n = singBundle.f20143h;
            this.f20112o = singBundle.f20144i;
            this.f20113p = singBundle.f20145j;
            this.f20114q = singBundle.f20146k;
            this.f20115r = singBundle.f20147l;
            this.f20116s = singBundle.f20148m;
            this.f20117t = singBundle.f20149n;
            this.f20118u = singBundle.f20150o;
            this.f20120w = singBundle.f20159x;
            this.f20121x = singBundle.f20160y;
            this.f20098a = singBundle.f20151p;
            this.f20099b = singBundle.f20152q;
            this.f20100c = singBundle.f20153r;
            this.f20101d = singBundle.f20154s;
            this.f20102e = singBundle.f20155t;
            this.f20103f = singBundle.f20157v;
            this.f20104g = singBundle.f20158w;
            this.f20122y = singBundle.f20161z;
        }

        public Builder m21615a(PerformanceType performanceType) {
            this.f20105h = performanceType;
            return this;
        }

        public Builder m21614a(GateType gateType) {
            this.f20106i = gateType;
            return this;
        }

        public Builder m21613a(SongbookEntry songbookEntry) {
            this.f20107j = songbookEntry;
            return this;
        }

        public Builder m21612a(PerformanceV2 performanceV2) {
            this.f20109l = performanceV2;
            if (performanceV2 != null) {
                this.f20105h = performanceV2.d() ? PerformanceType.DUET : PerformanceType.GROUP;
                int i = performanceV2.origTrackPartId == 0 ? 0 : performanceV2.origTrackPartId == 1 ? 2 : 1;
                this.f20110m = i;
                if (performanceV2.backgroundTrackFileAbsolutePath != null) {
                    this.f20111n = performanceV2.backgroundTrackFileAbsolutePath.getAbsolutePath();
                }
                if (performanceV2.metadataFile != null) {
                    this.f20112o = performanceV2.metadataFile.getAbsolutePath();
                }
                this.f20113p = performanceV2.performanceKey;
                this.f20114q = true;
            } else {
                this.f20111n = null;
                this.f20112o = null;
                this.f20113p = null;
                this.f20114q = false;
            }
            return this;
        }

        public Builder m21611a(int i) {
            this.f20101d = i;
            return this;
        }

        public Builder m21610a(float f) {
            this.f20102e = f;
            return this;
        }

        public Builder m21617a(Metadata metadata) {
            this.f20103f = metadata;
            return this;
        }

        public Builder m21616a(GlitchType glitchType) {
            this.f20104g = glitchType;
            return this;
        }

        public Builder m21622b(int i) {
            this.f20110m = i;
            return this;
        }

        public Builder m21620a(boolean z) {
            this.f20115r = z;
            return this;
        }

        public Builder m21623b(boolean z) {
            this.f20116s = z;
            return this;
        }

        public Builder m21624c(boolean z) {
            this.f20117t = z;
            return this;
        }

        public Builder m21625d(boolean z) {
            this.f20098a = z;
            return this;
        }

        public Builder m21626e(boolean z) {
            this.f20099b = z;
            return this;
        }

        public Builder m21618a(Long l) {
            this.f20100c = l;
            return this;
        }

        public Builder m21627f(boolean z) {
            this.f20119v = z;
            return this;
        }

        public Builder m21619a(String str) {
            this.f20120w = str;
            return this;
        }

        public SingBundle m21621a() {
            this.f20108k = this.f20107j.mo6294h();
            if (this.f20109l != null || this.f20117t) {
                this.f20099b = true;
            }
            return new SingBundle(this.f20105h, this.f20106i, this.f20107j, this.f20108k, this.f20109l, this.f20110m, this.f20111n, this.f20112o, this.f20113p, this.f20114q, this.f20115r, this.f20116s, this.f20117t, this.f20118u, this.f20098a, this.f20099b, this.f20100c, this.f20101d, this.f20102e, this.f20119v, this.f20120w, this.f20121x, this.f20103f, this.f20104g, this.f20122y);
        }
    }

    public enum GateType {
        NONE(0, "none"),
        SOFT_PAYWALL(1, "soft_paywall"),
        HARD_PAYWALL(2, "hard_paywall");
        
        public final int f20127d;
        public final String f20128e;

        private GateType(int i, String str) {
            this.f20127d = i;
            this.f20128e = str;
        }

        protected static GateType m21628a(int i) {
            for (GateType gateType : values()) {
                if (gateType.f20127d == i) {
                    return gateType;
                }
            }
            return NONE;
        }
    }

    public enum PerformanceType {
        UNDEFINED(0, "undefined"),
        SOLO(1, "solo"),
        DUET(2, "duet"),
        GROUP(3, "group");
        
        public final int f20134e;
        public final String f20135f;

        private PerformanceType(int i, String str) {
            this.f20134e = i;
            this.f20135f = str;
        }

        protected static PerformanceType m21629a(int i) {
            for (PerformanceType performanceType : values()) {
                if (performanceType.f20134e == i) {
                    return performanceType;
                }
            }
            return UNDEFINED;
        }

        public static PerformanceType m21630a(String str) {
            for (PerformanceType performanceType : values()) {
                if (performanceType.f20135f.equalsIgnoreCase(str)) {
                    return performanceType;
                }
            }
            return UNDEFINED;
        }

        public Ensemble m21631a() {
            switch (this) {
                case SOLO:
                    return Ensemble.SOLO;
                case DUET:
                    return Ensemble.DUET;
                case GROUP:
                    return Ensemble.GROUP;
                default:
                    return Ensemble.UNDEFINED;
            }
        }
    }

    public static SingBundle m21632a(Intent intent) {
        intent.setExtrasClassLoader(SingBundle.class.getClassLoader());
        return (SingBundle) intent.getParcelableExtra(f20136a);
    }

    public Intent m21636a(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        m21647b(intent);
        intent.setExtrasClassLoader(SingBundle.class.getClassLoader());
        return intent;
    }

    public void m21647b(Intent intent) {
        intent.putExtra(f20136a, this);
    }

    public boolean m21643a() {
        return this.f20137b == PerformanceType.DUET;
    }

    public boolean m21648b() {
        return this.f20137b == PerformanceType.GROUP;
    }

    public boolean m21652c() {
        return this.f20141f != null;
    }

    public boolean m21654d() {
        return m21650b("VIDEO_RECORD_ENABLED_KEY", false);
    }

    public String m21655e() {
        return m21646b("RECORDING_FILE_EXTRA_KEY", null);
    }

    public String m21657f() {
        if (TextUtils.isEmpty(this.f20159x)) {
            return null;
        }
        return this.f20159x;
    }

    public void m21637a(String str) {
        this.f20159x = str;
    }

    public boolean m21658g() {
        return this.f20160y;
    }

    public void m21642a(boolean z) {
        this.f20160y = z;
    }

    public SongV2 m21659h() {
        if (this.f20139d == null) {
            return null;
        }
        if (this.f20139d.m18773s()) {
            ListingEntry listingEntry = (ListingEntry) this.f20139d;
            if (listingEntry.f17626a == null) {
                return null;
            }
            if (listingEntry.f17626a.song != null) {
                return listingEntry.f17626a.song;
            }
            return StoreManager.m18378a().m18416a(listingEntry.f17626a.productId);
        }
        if (this.f20139d.m18772r()) {
            ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) this.f20139d;
            if (!(arrangementVersionLiteEntry.f17623a == null || arrangementVersionLiteEntry.f17623a.songId == null)) {
                return StoreManager.m18378a().m18416a(((ArrangementVersionLiteEntry) this.f20139d).f17623a.songId);
            }
        }
        return null;
    }

    public boolean m21649b(String str) {
        return this.f20161z.containsKey(str);
    }

    public void m21641a(String str, boolean z) {
        this.f20161z.putBoolean(str, z);
    }

    public boolean m21650b(String str, boolean z) {
        return this.f20161z.getBoolean(str, z);
    }

    public void m21639a(String str, int i) {
        this.f20161z.putInt(str, i);
    }

    public int m21651c(String str) {
        return m21645b(str, 0);
    }

    public int m21645b(String str, int i) {
        return this.f20161z.getInt(str, i);
    }

    public void m21638a(String str, float f) {
        this.f20161z.putFloat(str, f);
    }

    public float m21653d(String str) {
        return m21644b(str, 0.0f);
    }

    public float m21644b(String str, float f) {
        return this.f20161z.getFloat(str, f);
    }

    public void m21640a(String str, String str2) {
        this.f20161z.putString(str, str2);
    }

    public String m21656e(String str) {
        return m21646b(str, null);
    }

    public String m21646b(String str, String str2) {
        return this.f20161z.getString(str, str2);
    }

    private SingBundle(PerformanceType performanceType, GateType gateType, SongbookEntry songbookEntry, int i, PerformanceV2 performanceV2, int i2, String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, String str4, boolean z5, boolean z6, Long l, int i3, float f, boolean z7, String str5, boolean z8, Metadata metadata, GlitchType glitchType, Bundle bundle) {
        this.f20137b = performanceType;
        this.f20138c = gateType;
        this.f20139d = songbookEntry;
        this.f20140e = i;
        this.f20141f = performanceV2;
        this.f20142g = i2;
        this.f20143h = str;
        this.f20144i = str2;
        this.f20145j = str3;
        this.f20146k = z;
        this.f20147l = z2;
        this.f20148m = z3;
        this.f20149n = z4;
        this.f20150o = str4;
        this.f20151p = z5;
        this.f20152q = z6;
        this.f20153r = l;
        this.f20154s = i3;
        this.f20155t = f;
        this.f20156u = z7;
        this.f20159x = str5;
        this.f20160y = z8;
        this.f20157v = metadata;
        this.f20158w = glitchType;
        if (bundle != null) {
            this.f20161z = bundle;
        } else {
            this.f20161z = new Bundle(SingBundle.class.getClassLoader());
        }
    }

    public SingBundle m21660i() {
        SingBundle a = new Builder(this).m21621a();
        a.f20161z.putAll(this.f20161z);
        return a;
    }

    protected SingBundle(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.f20137b = PerformanceType.m21629a(parcel.readInt());
        this.f20138c = GateType.m21628a(parcel.readInt());
        this.f20139d = (SongbookEntry) parcel.readParcelable(SongbookEntry.class.getClassLoader());
        this.f20140e = parcel.readInt();
        this.f20141f = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.f20142g = parcel.readInt();
        this.f20143h = parcel.readString();
        this.f20144i = parcel.readString();
        this.f20145j = parcel.readString();
        this.f20146k = parcel.readByte() != (byte) 0;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20147l = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20148m = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20149n = z;
        this.f20150o = parcel.readString();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20151p = z;
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20152q = z;
        this.f20153r = Long.valueOf(parcel.readLong());
        this.f20154s = parcel.readInt();
        this.f20155t = parcel.readFloat();
        if (parcel.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.f20156u = z;
        this.f20159x = parcel.readString();
        if (parcel.readByte() == (byte) 0) {
            z2 = false;
        }
        this.f20160y = z2;
        this.f20157v = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
        this.f20158w = GlitchType.valueOf(parcel.readString());
        this.f20161z = parcel.readBundle();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.f20137b.f20134e);
        parcel.writeInt(this.f20138c.f20127d);
        parcel.writeParcelable(this.f20139d, 0);
        parcel.writeInt(this.f20140e);
        parcel.writeParcelable(this.f20141f, 0);
        parcel.writeInt(this.f20142g);
        parcel.writeString(this.f20143h);
        parcel.writeString(this.f20144i);
        parcel.writeString(this.f20145j);
        parcel.writeByte((byte) (this.f20146k ? 1 : 0));
        if (this.f20147l) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f20148m) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f20149n) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.f20150o);
        if (this.f20151p) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f20152q) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeLong(this.f20153r.longValue());
        parcel.writeInt(this.f20154s);
        parcel.writeFloat(this.f20155t);
        if (this.f20156u) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(TextUtils.isEmpty(this.f20159x) ? "normal" : this.f20159x);
        if (!this.f20160y) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeParcelable(this.f20157v, 0);
        parcel.writeString(this.f20158w == null ? GlitchType.NONE.name() : this.f20158w.name());
        parcel.writeBundle(this.f20161z);
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "SingBundle{performanceType='" + this.f20137b + '\'' + ", pendingGate=" + this.f20138c + ", entry=" + this.f20139d + ", entryPrice=" + this.f20140e + ", openCall=" + this.f20141f + ", singingPart=" + this.f20142g + ", openCallBackgroundTrackFile=" + this.f20143h + ", openCallMetadataFile=" + this.f20144i + ", openCallKey=" + this.f20145j + ", isJoin=" + this.f20146k + ", userHasSubscription=" + this.f20147l + ", shouldReportStream=" + this.f20148m + ", isOnboarding=" + this.f20149n + ", analyticsProgress=" + this.f20150o + ", purchaseStateCompleted=" + this.f20151p + ", duetPartSelectStateCompleted=" + this.f20152q + ", promoId=" + this.f20153r + ", singFlowUUID=" + this.f20154s + ", normalizationFactor=" + this.f20155t + ", videoOptionChosen=" + this.f20156u + ", vfxId=" + this.f20159x + ", isAirbrushOn=" + this.f20160y + ", metadata=" + this.f20157v + ", glitchType=" + this.f20158w + ", mBundle=" + this.f20161z + '}';
    }
}
