package com.smule.android.logging;

import com.smule.android.logging.Analytics.AnalyticsType;
import java.io.Serializable;

public class EventLogger2$Event implements Serializable {
    public long f16358a;
    public String f16359b;
    public String f16360c;
    public String f16361d;
    public String f16362e;
    public String f16363f;
    public String f16364g;
    public String f16365h;
    public String f16366i;
    public String f16367j;
    public String f16368k;
    public String f16369l;
    public String f16370m;
    public String f16371n;
    public boolean f16372o;
    public boolean f16373p;

    public static class Builder {
        private String f16343a;
        private String f16344b = null;
        private String f16345c = null;
        private String f16346d = null;
        private String f16347e = null;
        private String f16348f = null;
        private String f16349g = null;
        private String f16350h = null;
        private String f16351i = null;
        private String f16352j = null;
        private String f16353k = null;
        private String f16354l = null;
        private String f16355m = null;
        private boolean f16356n = false;
        private boolean f16357o = false;

        public Builder m17935a(String str) {
            this.f16343a = str;
            return this;
        }

        public Builder m17944b(String str) {
            this.f16344b = str;
            return this;
        }

        public Builder m17934a(Object obj) {
            this.f16344b = String.valueOf(obj);
            return this;
        }

        public Builder m17931a(AnalyticsType analyticsType) {
            this.f16344b = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17941b(AnalyticsType analyticsType) {
            this.f16345c = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17952c(String str) {
            this.f16345c = str;
            return this;
        }

        public Builder m17933a(Number number) {
            this.f16345c = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17932a(Boolean bool) {
            this.f16346d = bool != null ? bool.toString() : null;
            return this;
        }

        public Builder m17959d(String str) {
            this.f16346d = str;
            return this;
        }

        public Builder m17929a(int i) {
            this.f16346d = String.valueOf(i);
            return this;
        }

        public Builder m17930a(long j) {
            this.f16346d = String.valueOf(j);
            return this;
        }

        public Builder m17943b(Number number) {
            this.f16346d = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17949c(AnalyticsType analyticsType) {
            this.f16346d = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17966e(String str) {
            this.f16347e = str;
            return this;
        }

        public Builder m17939b(int i) {
            this.f16347e = String.valueOf(i);
            return this;
        }

        public Builder m17928a(float f) {
            this.f16347e = String.valueOf(f);
            return this;
        }

        public Builder m17940b(long j) {
            this.f16347e = String.valueOf(j);
            return this;
        }

        public Builder m17951c(Number number) {
            this.f16347e = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17956d(AnalyticsType analyticsType) {
            this.f16347e = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17971f(String str) {
            this.f16348f = str;
            return this;
        }

        public Builder m17942b(Boolean bool) {
            this.f16348f = bool != null ? bool.toString() : null;
            return this;
        }

        public Builder m17947c(int i) {
            this.f16348f = String.valueOf(i);
            return this;
        }

        public Builder m17948c(long j) {
            this.f16348f = String.valueOf(j);
            return this;
        }

        public Builder m17938b(float f) {
            this.f16348f = String.valueOf(f);
            return this;
        }

        public Builder m17958d(Number number) {
            this.f16348f = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17963e(AnalyticsType analyticsType) {
            this.f16348f = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17976g(String str) {
            this.f16349g = str;
            return this;
        }

        public Builder m17954d(int i) {
            this.f16349g = String.valueOf(i);
            return this;
        }

        public Builder m17955d(long j) {
            this.f16349g = String.valueOf(j);
            return this;
        }

        public Builder m17946c(float f) {
            this.f16349g = String.valueOf(f);
            return this;
        }

        public Builder m17965e(Number number) {
            this.f16349g = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17969f(AnalyticsType analyticsType) {
            this.f16349g = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17981h(String str) {
            this.f16350h = str;
            return this;
        }

        public Builder m17961e(int i) {
            this.f16350h = String.valueOf(i);
            return this;
        }

        public Builder m17962e(long j) {
            this.f16350h = String.valueOf(j);
            return this;
        }

        public Builder m17953d(float f) {
            this.f16350h = String.valueOf(f);
            return this;
        }

        public Builder m17970f(Number number) {
            this.f16350h = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17974g(AnalyticsType analyticsType) {
            this.f16350h = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17985i(String str) {
            this.f16351i = str;
            return this;
        }

        public Builder m17950c(Boolean bool) {
            this.f16351i = bool != null ? bool.toString() : null;
            return this;
        }

        public Builder m17960e(float f) {
            this.f16351i = String.valueOf(f);
            return this;
        }

        public Builder m17975g(Number number) {
            this.f16351i = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17979h(AnalyticsType analyticsType) {
            this.f16351i = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17988j(String str) {
            this.f16352j = str;
            return this;
        }

        public Builder m17957d(Boolean bool) {
            this.f16352j = bool != null ? Boolean.toString(bool.booleanValue()) : null;
            return this;
        }

        public Builder m17967f(int i) {
            this.f16352j = String.valueOf(i);
            return this;
        }

        public Builder m17968f(long j) {
            this.f16352j = String.valueOf(j);
            return this;
        }

        public Builder m17980h(Number number) {
            this.f16352j = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17983i(AnalyticsType analyticsType) {
            this.f16352j = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17990k(String str) {
            this.f16353k = str;
            return this;
        }

        public Builder m17964e(Boolean bool) {
            this.f16353k = bool != null ? bool.toString() : null;
            return this;
        }

        public Builder m17972g(int i) {
            this.f16353k = String.valueOf(i);
            return this;
        }

        public Builder m17973g(long j) {
            this.f16353k = String.valueOf(j);
            return this;
        }

        public Builder m17984i(Number number) {
            this.f16353k = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17986j(AnalyticsType analyticsType) {
            this.f16353k = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17977h(int i) {
            this.f16354l = String.valueOf(i);
            return this;
        }

        public Builder m17978h(long j) {
            this.f16354l = String.valueOf(j);
            return this;
        }

        public Builder m17987j(Number number) {
            this.f16354l = number != null ? number.toString() : null;
            return this;
        }

        public Builder m17989k(AnalyticsType analyticsType) {
            this.f16354l = analyticsType != null ? analyticsType.mo6235a() : null;
            return this;
        }

        public Builder m17982i(long j) {
            this.f16355m = String.valueOf(j);
            return this;
        }

        public Builder m17936a(boolean z) {
            this.f16356n = z;
            return this;
        }

        public Builder m17945b(boolean z) {
            this.f16357o = z;
            return this;
        }

        public EventLogger2$Event m17937a() {
            return new EventLogger2$Event(this);
        }
    }

    public EventLogger2$Event(Builder builder) {
        this.f16359b = builder.f16343a;
        this.f16360c = builder.f16344b;
        this.f16361d = builder.f16345c;
        this.f16362e = builder.f16346d;
        this.f16363f = builder.f16347e;
        this.f16364g = builder.f16348f;
        this.f16365h = builder.f16349g;
        this.f16366i = builder.f16350h;
        this.f16367j = builder.f16351i;
        this.f16368k = builder.f16352j;
        this.f16369l = builder.f16353k;
        this.f16370m = builder.f16354l;
        this.f16371n = builder.f16355m;
        this.f16372o = builder.f16356n;
        this.f16373p = builder.f16357o;
        this.f16358a = EventLogger2.a(EventLogger2.a());
    }

    public EventLogger2$Event(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, null, null, false);
    }

    public EventLogger2$Event(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, boolean z) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, null, null, z);
    }

    public EventLogger2$Event(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, boolean z) {
        this.f16359b = str;
        this.f16360c = str2;
        this.f16361d = str3;
        this.f16362e = str4;
        this.f16363f = str5;
        this.f16364g = str6;
        this.f16365h = str7;
        this.f16366i = str8;
        this.f16367j = str9;
        this.f16368k = str10;
        this.f16369l = str11;
        this.f16370m = str12;
        this.f16371n = str13;
        this.f16372o = z;
        this.f16358a = EventLogger2.a(EventLogger2.a());
    }

    public String toString() {
        return "[timeStamp=" + this.f16358a + ", eventType=" + this.f16359b + ", target=" + this.f16360c + ", context=" + this.f16361d + ", value=" + this.f16362e + ", k1=" + this.f16363f + ", k2=" + this.f16364g + ", k3=" + this.f16365h + ", k4=" + this.f16366i + ", k5=" + this.f16367j + ", k6=" + this.f16368k + ", k7=" + this.f16369l + ", k8=" + this.f16370m + ", k9=" + this.f16371n + ", immediate=" + this.f16372o + "]";
    }
}
