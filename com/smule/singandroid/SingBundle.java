/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$Intensity
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ParcelUtils;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.singflow.FreeformBundle;
import com.smule.singandroid.video.VideoEffects;
import java.io.File;

public class SingBundle
implements Parcelable,
Cloneable {
    public static final Parcelable.Creator<SingBundle> CREATOR;
    public static final String a;
    private String A;
    private String B;
    private VideoEffects.Intensity C;
    private boolean D;
    private FreeformBundle E;
    private final Bundle F;
    public final PerformanceType b;
    public final GateType c;
    public final SongbookEntry d;
    public final int e;
    public final PerformanceV2 f;
    public final int g;
    public final String h;
    public final String i;
    public final String j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;
    public final String p;
    public final boolean q;
    public final boolean r;
    public final Long s;
    public final int t;
    public final float u;
    public final boolean v;
    public final JoinSectionType w;
    public final Metadata x;
    public final boolean y;
    private boolean z;

    static {
        a = SingBundle.class.getName();
        CREATOR = new Parcelable.Creator<SingBundle>(){

            public SingBundle a(Parcel parcel) {
                return new SingBundle(parcel);
            }

            public SingBundle[] a(int n) {
                return new SingBundle[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    protected SingBundle(Parcel parcel) {
        boolean bl = true;
        this.l = false;
        this.b = PerformanceType.a(parcel.readInt());
        this.c = GateType.a(parcel.readInt());
        this.d = (SongbookEntry)parcel.readParcelable(SongbookEntry.class.getClassLoader());
        this.e = parcel.readInt();
        this.f = (PerformanceV2)parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.g = parcel.readInt();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        boolean bl2 = parcel.readByte() != 0;
        this.k = bl2;
        bl2 = parcel.readByte() != 0;
        this.m = bl2;
        bl2 = parcel.readByte() != 0;
        this.n = bl2;
        bl2 = parcel.readByte() != 0;
        this.o = bl2;
        this.p = parcel.readString();
        bl2 = parcel.readByte() != 0;
        this.q = bl2;
        bl2 = parcel.readByte() != 0;
        this.r = bl2;
        this.s = parcel.readLong();
        this.t = parcel.readInt();
        this.u = parcel.readFloat();
        this.z = ParcelUtils.a(parcel);
        bl2 = parcel.readByte() != 0;
        this.v = bl2;
        this.w = ParcelUtils.a(parcel, JoinSectionType.class, JoinSectionType.h);
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = ParcelUtils.a(parcel, VideoEffects.Intensity.class, VideoEffects.Intensity.a);
        bl2 = parcel.readByte() != 0;
        this.D = bl2;
        this.E = (FreeformBundle)parcel.readParcelable(FreeformBundle.class.getClassLoader());
        this.x = (Metadata)parcel.readParcelable(Metadata.class.getClassLoader());
        bl2 = parcel.readByte() != 0 ? bl : false;
        this.y = bl2;
        this.F = parcel.readBundle();
    }

    private SingBundle(Builder builder) {
        this.l = false;
        this.b = builder.g;
        this.c = builder.h;
        this.d = builder.i;
        this.e = builder.j;
        this.f = builder.k;
        this.g = builder.l;
        this.h = builder.m;
        this.i = builder.n;
        this.j = builder.o;
        this.k = builder.p;
        this.m = builder.r;
        this.n = builder.s;
        this.o = builder.t;
        this.p = builder.u;
        this.q = builder.a;
        this.r = builder.b;
        this.s = builder.c;
        this.t = builder.d;
        this.u = builder.e;
        this.v = builder.w;
        this.w = builder.x;
        this.A = builder.y;
        this.B = builder.z;
        this.C = builder.A;
        this.D = builder.B;
        this.z = builder.v;
        this.E = builder.C;
        this.x = builder.f;
        this.y = builder.D;
        if (builder.E != null) {
            this.F = builder.E;
            return;
        }
        this.F = new Bundle(SingBundle.class.getClassLoader());
    }

    public static SingBundle a(Intent intent) {
        intent.setExtrasClassLoader(SingBundle.class.getClassLoader());
        return (SingBundle)intent.getParcelableExtra(a);
    }

    public Intent a(Context context, Class<?> class_) {
        context = new Intent(context, class_);
        this.b((Intent)context);
        context.setExtrasClassLoader(SingBundle.class.getClassLoader());
        return context;
    }

    public FreeformBundle a() {
        return this.E;
    }

    public void a(FreeformBundle freeformBundle) {
        this.E = freeformBundle;
    }

    public void a(VideoEffects.Intensity intensity) {
        this.C = intensity;
    }

    public void a(String string2) {
        this.A = string2;
    }

    public void a(String string2, float f) {
        this.F.putFloat(string2, f);
    }

    public void a(String string2, int n) {
        this.F.putInt(string2, n);
    }

    public void a(String string2, String string3) {
        this.F.putString(string2, string3);
    }

    public void a(String string2, boolean bl) {
        this.F.putBoolean(string2, bl);
    }

    public void a(boolean bl) {
        this.D = bl;
    }

    public float b(String string2, float f) {
        return this.F.getFloat(string2, f);
    }

    public int b(String string2, int n) {
        return this.F.getInt(string2, n);
    }

    public String b(String string2, String string3) {
        return this.F.getString(string2, string3);
    }

    public void b(Intent intent) {
        intent.putExtra(a, (Parcelable)this);
    }

    public void b(String string2) {
        this.B = string2;
    }

    public void b(boolean bl) {
        this.z = bl;
    }

    public boolean b() {
        if (this.E != null) {
            return true;
        }
        return false;
    }

    public boolean b(String string2, boolean bl) {
        return this.F.getBoolean(string2, bl);
    }

    public boolean c() {
        if (this.b == PerformanceType.c) {
            return true;
        }
        return false;
    }

    public boolean c(String string2) {
        return this.F.containsKey(string2);
    }

    public int d(String string2) {
        return this.b(string2, 0);
    }

    public boolean d() {
        if (this.b == PerformanceType.d) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public String e(String string2) {
        return this.b(string2, null);
    }

    public boolean e() {
        if (this.f != null) {
            return true;
        }
        return false;
    }

    public boolean f() {
        return this.b("VIDEO_RECORD_ENABLED_KEY", false);
    }

    public String g() {
        return this.b("RECORDING_FILE_EXTRA_KEY", null);
    }

    public String h() {
        return this.A;
    }

    public String i() {
        return this.B;
    }

    public VideoEffects.Intensity j() {
        return this.C;
    }

    public boolean k() {
        return this.D;
    }

    public boolean l() {
        return this.z;
    }

    public boolean m() {
        if (!this.A.equals("classic")) {
            return true;
        }
        return false;
    }

    public SingBundle n() {
        SingBundle singBundle = new Builder(this).a();
        singBundle.F.putAll(this.F);
        return singBundle;
    }

    public String toString() {
        return "SingBundle{performanceType='" + (Object)((Object)this.b) + '\'' + ", pendingGate=" + (Object)((Object)this.c) + ", entry=" + this.d + ", entryPrice=" + this.e + ", openCall=" + this.f + ", singingPart=" + this.g + ", openCallBackgroundTrackFile=" + this.h + ", openCallMetadataFile=" + this.i + ", openCallKey=" + this.j + ", isJoin=" + this.k + ", userHasSubscription=" + this.m + ", shouldReportStream=" + this.n + ", isOnboarding=" + this.o + ", analyticsProgress=" + this.p + ", purchaseStateCompleted=" + this.q + ", duetPartSelectStateCompleted=" + this.r + ", promoId=" + this.s + ", singFlowUUID=" + this.t + ", normalizationFactor=" + this.u + ", isAutoTuneOn=" + this.z + ", videoOptionChosen=" + this.v + ", initialSectionDisplayed=" + (Object)((Object)this.w) + ", videoStyleId=" + this.A + ", colorFilterId=" + this.B + ", particleIntensity=" + (Object)this.C + ", isAirbrushOn=" + this.D + ", freeformBundle=" + this.E + ", metadata=" + this.x + ", isIntendedToPin=" + this.y + ", mBundle=" + (Object)this.F + '}';
    }

    /*
     * Enabled aggressive block sorting
     */
    public void writeToParcel(Parcel parcel, int n) {
        int n2 = 1;
        parcel.writeInt(this.b.e);
        parcel.writeInt(this.c.d);
        parcel.writeParcelable((Parcelable)this.d, 0);
        parcel.writeInt(this.e);
        parcel.writeParcelable((Parcelable)this.f, 0);
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        n = this.k ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.m ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.n ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.o ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeString(this.p);
        n = this.q ? 1 : 0;
        parcel.writeByte((byte)n);
        n = this.r ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeLong(this.s.longValue());
        parcel.writeInt(this.t);
        parcel.writeFloat(this.u);
        ParcelUtils.a(parcel, this.z);
        n = this.v ? 1 : 0;
        parcel.writeByte((byte)n);
        ParcelUtils.a(parcel, this.w, JoinSectionType.h.a());
        String string2 = TextUtils.isEmpty((CharSequence)this.A) ? "classic" : this.A;
        parcel.writeString(string2);
        string2 = TextUtils.isEmpty((CharSequence)this.B) ? "normal" : this.B;
        parcel.writeString(string2);
        ParcelUtils.a(parcel, this.C, VideoEffects.Intensity.a.name());
        n = this.D ? 1 : 0;
        parcel.writeByte((byte)n);
        parcel.writeParcelable((Parcelable)this.E, 0);
        parcel.writeParcelable((Parcelable)this.x, 0);
        n = this.y ? n2 : 0;
        parcel.writeByte((byte)n);
        parcel.writeBundle(this.F);
    }

    public static final class Builder {
        private VideoEffects.Intensity A = VideoEffects.Intensity.a;
        private boolean B = false;
        private FreeformBundle C;
        private boolean D = false;
        private Bundle E;
        public boolean a;
        public boolean b;
        public Long c = -1;
        public int d;
        public float e;
        public Metadata f;
        private PerformanceType g = PerformanceType.a;
        private GateType h = GateType.a;
        private SongbookEntry i;
        private int j = 0;
        private PerformanceV2 k = null;
        private int l = 0;
        private String m = null;
        private String n = null;
        private String o = null;
        private boolean p = false;
        private boolean q = false;
        private boolean r = false;
        private boolean s = true;
        private boolean t = false;
        private String u = null;
        private boolean v = false;
        private boolean w = false;
        private JoinSectionType x;
        private String y = "classic";
        private String z = "normal";

        public Builder() {
        }

        public Builder(SingBundle singBundle) {
            this.g = singBundle.b;
            this.h = singBundle.c;
            this.i = singBundle.d;
            this.j = singBundle.e;
            this.k = singBundle.f;
            this.l = singBundle.g;
            this.m = singBundle.h;
            this.n = singBundle.i;
            this.o = singBundle.j;
            this.p = singBundle.k;
            singBundle.getClass();
            this.q = false;
            this.r = singBundle.m;
            this.s = singBundle.n;
            this.t = singBundle.o;
            this.u = singBundle.p;
            this.y = singBundle.A;
            this.z = singBundle.B;
            this.A = singBundle.C;
            this.B = singBundle.D;
            this.v = singBundle.z;
            this.C = singBundle.E;
            this.a = singBundle.q;
            this.b = singBundle.r;
            this.c = singBundle.s;
            this.d = singBundle.t;
            this.e = singBundle.u;
            this.f = singBundle.x;
            this.D = singBundle.y;
            this.E = singBundle.F;
        }

        public Builder a(float f) {
            this.e = f;
            return this;
        }

        public Builder a(int n) {
            this.d = n;
            return this;
        }

        /*
         * Enabled aggressive block sorting
         */
        public Builder a(PerformanceV2 performanceV2) {
            this.k = performanceV2;
            if (performanceV2 == null) {
                this.m = null;
                this.n = null;
                this.o = null;
                this.p = false;
                return this;
            }
            PerformanceType performanceType = performanceV2.e() ? PerformanceType.c : PerformanceType.d;
            this.g = performanceType;
            int n = performanceV2.origTrackPartId == 0 ? 0 : (performanceV2.origTrackPartId == 1 ? 2 : 1);
            this.l = n;
            if (performanceV2.backgroundTrackFileAbsolutePath != null) {
                this.m = performanceV2.backgroundTrackFileAbsolutePath.getAbsolutePath();
            }
            if (performanceV2.metadataFile != null) {
                this.n = performanceV2.metadataFile.getAbsolutePath();
            }
            this.o = performanceV2.performanceKey;
            this.p = true;
            return this;
        }

        public Builder a(SongbookEntry songbookEntry) {
            this.i = songbookEntry;
            return this;
        }

        public Builder a(GateType gateType) {
            this.h = gateType;
            return this;
        }

        public Builder a(PerformanceType performanceType) {
            this.g = performanceType;
            return this;
        }

        public Builder a(Metadata metadata) {
            this.f = metadata;
            return this;
        }

        public Builder a(JoinSectionType joinSectionType) {
            this.x = joinSectionType;
            return this;
        }

        public Builder a(VideoEffects.Intensity intensity) {
            this.A = intensity;
            return this;
        }

        public Builder a(Long l) {
            this.c = l;
            return this;
        }

        public Builder a(String string2) {
            this.y = string2;
            return this;
        }

        public Builder a(boolean bl) {
            this.D = bl;
            return this;
        }

        public SingBundle a() {
            this.j = this.i.i();
            if (this.k != null || this.t) {
                this.b = true;
            }
            return new SingBundle(this);
        }

        public Builder b(int n) {
            this.l = n;
            return this;
        }

        public Builder b(String string2) {
            this.z = string2;
            return this;
        }

        public Builder b(boolean bl) {
            this.r = bl;
            return this;
        }

        public Builder c(boolean bl) {
            this.s = bl;
            return this;
        }

        public Builder d(boolean bl) {
            this.t = bl;
            return this;
        }

        public Builder e(boolean bl) {
            this.a = bl;
            return this;
        }

        public Builder f(boolean bl) {
            this.b = bl;
            return this;
        }

        public Builder g(boolean bl) {
            this.w = bl;
            return this;
        }
    }

    public static enum GateType {
        a(0, "none"),
        b(1, "soft_paywall"),
        c(2, "hard_paywall");
        
        public final int d;
        public final String e;

        private GateType(int n2, String string3) {
            this.d = n2;
            this.e = string3;
        }

        protected static GateType a(int n) {
            for (GateType gateType : GateType.values()) {
                if (gateType.d != n) continue;
                return gateType;
            }
            return a;
        }
    }

    public static enum PerformanceType {
        a(0, "undefined"),
        b(1, "solo"),
        c(2, "duet"),
        d(3, "group");
        
        public final int e;
        public final String f;

        private PerformanceType(int n2, String string3) {
            this.e = n2;
            this.f = string3;
        }

        protected static PerformanceType a(int n) {
            for (PerformanceType performanceType : PerformanceType.values()) {
                if (performanceType.e != n) continue;
                return performanceType;
            }
            return a;
        }

        public static PerformanceType a(String string2) {
            for (PerformanceType performanceType : PerformanceType.values()) {
                if (!performanceType.f.equalsIgnoreCase(string2)) continue;
                return performanceType;
            }
            return a;
        }

        public Analytics a() {
            switch (.a[this.ordinal()]) {
                default: {
                    return Analytics.g;
                }
                case 1: {
                    return Analytics.a;
                }
                case 2: {
                    return Analytics.b;
                }
                case 3: 
            }
            return Analytics.c;
        }
    }

}

