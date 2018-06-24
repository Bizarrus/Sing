/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.text.TextUtils
 */
package com.smule.android.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.network.models.Arrangement;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import java.io.Serializable;

public class Analytics {
    protected static String a;
    private static final String b;

    static {
        b = Analytics.class.getName();
        a = null;
    }

    public static float a(float f, int n) {
        float f2 = (float)Math.pow(10.0, n);
        return (float)Math.round(f * f2) / f2;
    }

    public static  a(PerformanceV2 performanceV2) {
        if (performanceV2.f()) {
            return .c;
        }
        if (performanceV2.e()) {
            return .b;
        }
        return .a;
    }

    public static <T extends Enum<T>> T a(Class<T> arrenum, String string2) {
        for (Enum enum_ : (Enum[])arrenum.getEnumConstants()) {
            if (!(enum_ instanceof ) || !((Object)enum_).a().equalsIgnoreCase(string2)) continue;
            return (T)enum_;
        }
        return null;
    }

    public static String a(ArrangementVersionLite arrangementVersionLite) {
        if (arrangementVersionLite != null && arrangementVersionLite.songId != null) {
            return arrangementVersionLite.songId;
        }
        return "-";
    }

    public static String a(SongbookEntry object) {
        if (object != null && (object = object.d()) != null) {
            return object;
        }
        return "-";
    }

    public static String a(Boolean bl) {
        if (bl == null) {
            return null;
        }
        return bl.toString();
    }

    private static String a(String string2) {
        String string3 = string2;
        if (string2 != null) {
            string3 = string2;
            if (string2.isEmpty()) {
                string3 = null;
            }
        }
        return string3;
    }

    public static String a(String string2, String string3) {
        return string2 + ',' + string3;
    }

    protected static String a(boolean bl) {
        if (bl) {
            return "join";
        }
        return "notjoin";
    }

    public static void a() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("t_search_clk");
        EventLogger2.a().a(builder);
    }

    public static void a(long l, long l2, long l3, long l4, long l5) {
        if (!EventLogger2.b()) {
            return;
        }
        EventLogger2 event = new EventLogger2.Builder().a("npt_d").a((Object)l).a(l2).b(l3).d(l4).e(l5).b(true).a();
        EventLogger2.a().a(event);
    }

    public static void a( object) {
        object = new EventLogger2.Builder().a("ad_reward_clk").a(object);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  analyticsType,  adMediator, String string2,  adCategory, String string3, String string4) {
        object = new EventLogger2.Builder().a("ad_impression").a(object).b(analyticsType).d(adMediator).f(string2).f(adCategory).h(string3).i(string4);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  analyticsType,  adMediator, String string2,  adCategory, String string3, String string4, long l) {
        object = new EventLogger2.Builder().a("ad_impression_cancel").a(object).b(analyticsType).d(adMediator).f(string2).f(adCategory).h(string3).i(string4).g(l);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a( object,  object2, boolean bl,  adMediator, String string2,  adCategory, String string3, String string4, String string5, Long l) {
        object2 = new EventLogger2.Builder().a("ad_request").a(object).b(object2);
        object = bl ? "true" : "false";
        object = object2.d((String)object).d(adMediator).f(string2).f(adCategory).h(string3).i(string4).j(string5).i(l);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  nativeAdPlacementType, Integer n,  adMediator, String string2,  adCategory, String string3, String string4) {
        object = new EventLogger2.Builder().a("ad_impression").a(object).b(nativeAdPlacementType).b(n).d(adMediator).f(string2).f(adCategory).h(string3).i(string4);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a( object,  object2, boolean bl,  adMediator, String string2,  adCategory, String string3, String string4, String string5, Long l) {
        object2 = new EventLogger2.Builder().a("ad_request").a(object).b(object2);
        object = bl ? "true" : "false";
        object = object2.d((String)object).d(adMediator).f(string2).f(adCategory).h(string3).i(string4).j(string5).i(l);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    protected static void a( analyticsType, String string2) {
        if (analyticsType == null) {
            Log.e(b, string2);
        }
    }

    public static void a( followType, int n) {
        Analytics.a(followType, null, n);
    }

    public static void a( followType, Long l) {
        Analytics.a(followType, l, 1);
    }

    private static void a( object, Long l, int n) {
        object = new EventLogger2.Builder().a("follow_clk").c(object.a()).b(l).b(n);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( handleUpdateType,  profilePicType) {
        EventLogger2.a().a("reg_profile_update", null, null, null, handleUpdateType.a(), profilePicType.a(), true);
    }

    public static void a( registrationAccountType,  registrationFlow) {
        Analytics.a(registrationAccountType, "accountType is required");
        Analytics.a(registrationFlow, "flow is required");
        EventLogger2.a().a("reg_flow_complete", null, null, registrationAccountType.a(), registrationFlow.a(), true);
    }

    public static void a( registrationType) {
        Analytics.a(registrationType, "type is required");
        EventLogger2.a().a("reg_acct_found", null, null, registrationType.a(), true);
    }

    public static void a( object, String string2, String string3) {
        string2 = Analytics.a(string2);
        string3 = Analytics.a(string3);
        object = new EventLogger2.Builder().a("search_bar_exit").b(object).e(string3).f(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object) {
        object = new EventLogger2.Builder().a("search_clk").b(object);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object, int n, int n2, String string2, long l, String string3,  autocompleteSectionName, Integer n3) {
        string2 = Analytics.a(string2);
        object = new EventLogger2.Builder().a("autocomplete_clk").b(object).a(n).b(n2).f(string2).d(l).h(string3).h(autocompleteSectionName).i(n2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object, int n, int n2, String string2, long l, String string3, Boolean bl, Boolean bl2, Boolean bl3, Boolean bl4, Boolean bl5) {
        string2 = Analytics.a(string2);
        object = new EventLogger2.Builder().a("autocomplete_clk").b(object).a(n).b(n2).f(string2).d(l).h(string3).e(bl).f(bl2).g(bl3).h(bl4).i(bl5);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object, int n, Integer n2, String string2) {
        object = new EventLogger2.Builder().a("search_clear_clk").b(object).a(n).c(n2).f(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  searchTarget) {
        object = new EventLogger2.Builder().a("search_sort_clk").a(object).g(searchTarget);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  searchExecuteContext, int n, String string2, String string3, long l, Integer n2) {
        String string4 = string3;
        if (string3 != null) {
            string4 = string3;
            if (string3.length() > 128) {
                string4 = string3.substring(0, 128);
            }
        }
        object = new EventLogger2.Builder().a("search_execute").a(object).b(searchExecuteContext).a(n).e(string2).f(string4).d(l);
        if (n2 != null && n2 != -1) {
            object.f(n2);
        }
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object,  searchResultClkContext,  searchResultClkValue, String string2, String string3, Integer n, Long l, String string4,  videoStatusType, int n2, int n3) {
        object = new EventLogger2.Builder().a("search_result_clk").a(object).b(searchResultClkContext).c(searchResultClkValue).e(string2).f(string3).e(n).f(l).i(string4).i(videoStatusType).h(n2).i(n3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a( object, String string2, int n, String string3, String string4, String string5, Number number) {
        Analytics.b(string3, "costType is required");
        Analytics.b(string2, "songUid is required");
        object = new EventLogger2.Builder().a("song_clk").b(object).b(number).e(string2).c(n).g(string3).h(string4).i(string5);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    protected static void a(EventLogger2.Builder builder, boolean bl) {
        if (bl) {
            builder.l("vidadd");
        }
    }

    public static void a(String object, int n, String string2) {
        object = new EventLogger2.Builder().a("subs_fetch_sku_feedback").b((String)object).c(n).g(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, AudioDefs.HeadphonesType headphonesType, String string2, String string3,  cameraStatusType) {
        object = new EventLogger2.Builder().a("tut_start").b((String)object).c("pickasong").c(headphonesType).e(string2).i(string3).j(cameraStatusType);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, AudioDefs.HeadphonesType headphonesType, String string2, String string3,  cameraStatusType, long l) {
        object = new EventLogger2.Builder().a("tut_cancel").b((String)object).c("pickasong").c(headphonesType).e(string2).c(l).i(string3).j(cameraStatusType).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, AudioDefs.HeadphonesType headphonesType, String string2, String string3,  cameraStatusType, boolean bl) {
        object = new EventLogger2.Builder().a("tut_complete").b((String)object).c("pickasong").c(headphonesType).e(string2).c(bl).i(string3).j(cameraStatusType);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object,  itemClickType, int n,  recSysContext, String string2) {
        object = new EventLogger2.Builder().a("recsys_clk").d((String)object).b(n).f(string2).f(itemClickType).b(recSysContext);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(@NonNull String object, @Nullable  permissionNeverAskAgain, @NonNull  permissionAskType, @NonNull  permissionResult, @Nullable  permissionErrorReason) {
        object = new EventLogger2.Builder().a((String)object).a(permissionNeverAskAgain).b(permissionAskType).c(permissionResult).d(permissionErrorReason);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object,  userPath,  attemptType, String string2, boolean bl,  ensemble, String string3) {
        object = new EventLogger2.Builder().a("perf_audio_upload_start").b((String)object).b(userPath).e(string2).f(Analytics.a(bl)).f(ensemble).i(string3).k(attemptType);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object,  userPath,  uploadCompletionType, String string2, boolean bl,  ensemble, String string3, String string4, Integer n, String string5) {
        object = new EventLogger2.Builder().a("perf_audio_upload_complete").b((String)object).b(userPath).e(string2).f(Analytics.a(bl)).f(ensemble).h(string4).i(string5).j(string3).i(n).k(uploadCompletionType);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object,  userPath, String string2, String string3, AudioDefs.HeadphonesType headphonesType, String string4,  ensemble, String string5, boolean bl, boolean bl2) {
        Analytics.b((String)object, "perfKey is required");
        Analytics.b(string4, "songUid is required");
        Analytics.a(ensemble, "ensembleType is required");
        Analytics.b(string5, "arrangementKey is required");
        object = new EventLogger2.Builder().a("perf_start_create").b((String)object).b(userPath).c(headphonesType).e(string4).f(Analytics.a(string2, string3)).f(ensemble).i(string5).j(Analytics.a((Boolean)bl));
        Analytics.a((EventLogger2.Builder)object, bl2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object,  userPath, String string2, String string3,  ensemble, String string4) {
        object = new EventLogger2.Builder().a("perf_start_clk").b((String)object).b(userPath);
        if (string2 == null) {
            string2 = "-";
        }
        object = object.e(string2).f(ensemble);
        if (string3 == null) {
            string3 = "-";
        }
        object = object.i(string3).k(string4);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, Integer n, String string2,  videoStatusType, int n2, int n3) {
        object = new EventLogger2.Builder().a("t_search_result_clk").e((String)object).e(n).i(string2).i(videoStatusType).h(n2).i(n3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2, int n, String string3, String string4, String string5) {
        Analytics.b(string3, "costType is required");
        Analytics.b(string2, "songUid is required");
        object = new EventLogger2.Builder().a("song_preview").c((String)object).e(string2).c(n).g(string3).h(string4).i(string5);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String string2, String string3,  ensemble) {
        Analytics.b(string2, "perfKey is required");
        Analytics.b(string3, "songUid is required");
        EventLogger2.a().a("perf_del_clk", string2, null, null, string3, null, ensemble.a());
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object, String object2,  ensemble, Integer n, String string2,  videoStatusType) {
        Analytics.b((String)object, "perfKey is required");
        Analytics.b((String)object2, "songUid is required");
        object2 = new EventLogger2.Builder().a("perf_comment").b((String)object).e((String)object2).f(ensemble);
        object = n != null ? Integer.toString(n) : null;
        object = object2.h((String)object).i(string2).i(videoStatusType);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object, String string2,  ensemble, Integer n, String string3, boolean bl) {
        Analytics.b((String)object, "perfKey is required");
        boolean bl2 = string2 != null || string3 != null;
        Analytics.a(bl2, "songUid or arrKey required");
        object = new EventLogger2.Builder().a("perf_favorite").b((String)object).e(string2).f(ensemble).f(n).i(string3).f(bl);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2,  recommendationType,  recSysContext, String string3) {
        object = new EventLogger2.Builder().a("recsys_vw").d((String)object).e(string2).f(string3).f(recommendationType).b(recSysContext);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2, Integer n, Integer n2, String string3) {
        object = new EventLogger2.Builder().a("reg_fail").c((String)object).e(string2).d(n).e(n2).h(string3).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2, String string3) {
        object = new EventLogger2.Builder().a("push_clk").c((String)object).h(string3).d(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String string2, String string3, String string4,  ensemble) {
        Analytics.b(string2, "perfKey is required");
        Analytics.a(string2, string3, string4, ensemble, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected static void a(String object, String string2, String string3,  ensemble,  eventModifier) {
        Analytics.b((String)object, "perfKey is required");
        if (string2 == null) {
            string2 = "-";
        }
        if (string3 == null) {
            string3 = "-";
        }
        object = new Serializable("perf_join_clk", (String)object, null, null, string2, null, ensemble.a(), null, string3, null, null){
            public long a;
            public String b;
            public String c;
            public String d;
            public String e;
            public String f;
            public String g;
            public String h;
            public String i;
            public String j;
            public String k;
            public String l;
            public String m;
            public String n;
            public boolean o;
            public boolean p;
            {
                this.b = EventLogger2.Builder.a(builder);
                this.c = EventLogger2.Builder.b(builder);
                this.d = EventLogger2.Builder.c(builder);
                this.e = EventLogger2.Builder.d(builder);
                this.f = EventLogger2.Builder.e(builder);
                this.g = EventLogger2.Builder.f(builder);
                this.h = EventLogger2.Builder.g(builder);
                this.i = EventLogger2.Builder.h(builder);
                this.j = EventLogger2.Builder.i(builder);
                this.k = EventLogger2.Builder.j(builder);
                this.l = EventLogger2.Builder.k(builder);
                this.m = EventLogger2.Builder.l(builder);
                this.n = EventLogger2.Builder.m(builder);
                this.o = EventLogger2.Builder.n(builder);
                this.p = EventLogger2.Builder.o(builder);
                this.a = EventLogger2.a(EventLogger2.a());
            }
            {
                this(string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, null, null, false);
            }
            {
                this.b = string2;
                this.c = string3;
                this.d = string4;
                this.e = string5;
                this.f = string6;
                this.g = string7;
                this.h = string8;
                this.i = string9;
                this.j = string10;
                this.k = string11;
                this.l = string12;
                this.m = string13;
                this.n = string14;
                this.o = bl;
                this.a = EventLogger2.a(EventLogger2.a());
            }
            {
                this(string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, null, null, bl);
            }

            public String toString() {
                return "[timeStamp=" + this.a + ", eventType=" + this.b + ", target=" + this.c + ", context=" + this.d + ", value=" + this.e + ", k1=" + this.f + ", k2=" + this.g + ", k3=" + this.h + ", k4=" + this.i + ", k5=" + this.j + ", k6=" + this.k + ", k7=" + this.l + ", k8=" + this.m + ", k9=" + this.n + ", immediate=" + this.o + "]";
            }
        };
        if (eventModifier != null) {
            eventModifier.a(object);
        }
        EventLogger2.a().a(object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(String object, String string2, String string3,  paywallType) {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("purchase_pgview").e((String)object);
        object = paywallType != null ? paywallType.a() : "-";
        object = builder.g((String)object).i(string2).k(string3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2, String string3, Integer n, String string4, String string5) {
        object = new EventLogger2.Builder().a("perf_video_upload_fail").c(string2).d(string3).b((String)object).e(string4).f(EventLogger2.e()).e(n).i(string5).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String string2, String string3, String string4, String string5) {
        Analytics.b(string2, "sku is required");
        Analytics.b(string4, "period is required");
        Analytics.b(string3, "context is required");
        EventLogger2.a().a("subs_buy_clk", string2, string3, string4, string5);
    }

    public static void a(String object, String string2, String string3, String string4, int n, String string5, String string6, Long l, Integer n2, String string7) {
        object = new EventLogger2.Builder().a("subs_buy_feedback").b((String)object).c(string2).d(string3).e(string4).c(n).g(string5).h(string6).g(l).h(n2).k(string7);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(@NonNull String object, boolean bl, @NonNull  cacheType) {
        if (!EventLogger2.b() || TextUtils.isEmpty((CharSequence)object) || TextUtils.isEmpty((CharSequence)(object = object.substring(object.lastIndexOf("/") + 1)))) {
            return;
        }
        object = new EventLogger2.Builder().a("npt_c").b((String)object).a((Boolean)bl).b(cacheType).b(true).a();
        EventLogger2.a().a(object);
    }

    public static void a(String object, boolean bl, String string2, String string3, AudioDefs.HeadphonesType headphonesType, String string4,  ensemble, String string5, boolean bl2, boolean bl3) {
        Analytics.b((String)object, "perfKey is required");
        Analytics.b(string4, "songUid is required");
        Analytics.a(ensemble, "ensembleType is required");
        Analytics.b(string5, "arrangementKey is required");
        object = new EventLogger2.Builder().a("perf_join_create").b((String)object).c(Analytics.b(bl)).c(headphonesType).e(string4).f(Analytics.a(string2, string3)).f(ensemble).i(string5).j(Analytics.a((Boolean)bl2));
        Analytics.a((EventLogger2.Builder)object, bl3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    protected static void a(boolean bl, String string2) {
        if (!bl) {
            Log.e(b, string2);
        }
    }

    public static String b(ArrangementVersionLite arrangementVersionLite) {
        if (arrangementVersionLite != null && arrangementVersionLite.key != null) {
            return arrangementVersionLite.key;
        }
        return "-";
    }

    @Deprecated
    public static String b(PerformanceV2 performanceV2) {
        if (performanceV2 != null && performanceV2.songUid != null && !TextUtils.isEmpty((CharSequence)performanceV2.songUid)) {
            return performanceV2.songUid;
        }
        return "-";
    }

    public static String b(SongbookEntry object) {
        if (object != null && object.t() && (object = object.c()) != null) {
            return object;
        }
        return "-";
    }

    protected static String b(boolean bl) {
        if (bl) {
            return .a.a();
        }
        return .b.a();
    }

    public static void b() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("settings_pgview");
        EventLogger2.a().a(builder);
    }

    public static void b( object,  analyticsType,  adMediator, String string2,  adCategory, String string3, String string4) {
        object = new EventLogger2.Builder().a("ad_clk").a(object).b(analyticsType).d(adMediator).f(string2).f(adCategory).h(string3).i(string4);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b( object,  analyticsType,  adMediator, String string2,  adCategory, String string3, String string4, long l) {
        object = new EventLogger2.Builder().a("ad_impression_skip").a(object).b(analyticsType).d(adMediator).f(string2).f(adCategory).h(string3).i(string4).g(l);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b( object,  nativeAdPlacementType, Integer n,  adMediator, String string2,  adCategory, String string3, String string4) {
        object = new EventLogger2.Builder().a("ad_clk").a(object).b(nativeAdPlacementType).b(n).d(adMediator).f(string2).f(adCategory).h(string3).i(string4);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b( object, String string2, String string3) {
        string2 = Analytics.a(string2);
        string3 = Analytics.a(string3);
        object = new EventLogger2.Builder().a("t_search_bar_exit").b(object).e(string3).f(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    protected static void b(String string2, String string3) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            Log.e(b, string3);
        }
    }

    public static void b(String object, String string2, String string3) {
        String string4 = string2;
        if (string2 == null) {
            string4 = "-";
        }
        object = new EventLogger2.Builder().a("perf_video_upload_cancel").b((String)object).e(string4).f(EventLogger2.e()).i(string3).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b(String object, String string2, String string3, String string4) {
        object = new EventLogger2.Builder().a("reg_fail").c((String)object).e(string2).f(string3).g(string4).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b(boolean bl, String string2) {
        EventLogger2.a().a("reg_flow_start", null, string2, Boolean.toString(bl), true);
    }

    public static String c(PerformanceV2 performanceV2) {
        if (performanceV2 != null && performanceV2.arrangementVersion != null && performanceV2.arrangementVersion.arrangement != null && !TextUtils.isEmpty((CharSequence)performanceV2.arrangementVersion.arrangement.key)) {
            return performanceV2.arrangementVersion.arrangement.key;
        }
        return "-";
    }

    public static void c() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("perflist_pgview");
        EventLogger2.a().a(builder);
    }

    public static void c( object,  analyticsType,  adMediator, String string2,  adCategory, String string3, String string4, long l) {
        object = new EventLogger2.Builder().a("ad_complete").a(object).b(analyticsType).d(adMediator).f(string2).f(adCategory).h(string3).i(string4).g(l);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void c(String object, String string2) {
        Analytics.b((String)object, "adUnitId is required");
        object = new EventLogger2.Builder().a("interstitial_ad_shown").d((String)object).e(string2);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void c(String object, String string2, String string3) {
        object = new EventLogger2.Builder().a("perf_video_upload_retry").b((String)object).e(string2).f(EventLogger2.e()).i(string3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void c(String object, String string2, String string3, String string4) {
        object = new EventLogger2.Builder().a("reg_fail_r105").c((String)object).e(string2).f(string3).g(string4).a(true);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void d() {
        EventLogger2.a().a("reg_fbconnect_click", null, null, true);
    }

    public static void d(String object, String string2, String string3) {
        object = new EventLogger2.Builder().a("perf_video_upload_success").b((String)object).e(string2).f(EventLogger2.e()).i(string3);
        EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void e() {
        EventLogger2.a().a("reg_fbconnect_success", null, null, true);
    }

    public static void f() {
        EventLogger2.a().a("reg_googconnect_clk", null, null, true);
    }

    public static void g() {
        EventLogger2.a().a("reg_googconnect_success", null, null, true);
    }

    public static void h() {
        EventLogger2.a().a("reg_forgotpwd_pgview", true);
    }

    public static void i() {
        EventLogger2.a().a("reg_forgotpwd_success", true);
    }

    public static void j() {
        EventLogger2.a().a("reg_gplusdisconnect_success", true);
    }

    public static void k() {
        EventLogger2.a().a("reg_landing_pgview", null, null, true);
    }

    public static void l() {
        EventLogger2.a().a("reg_newacct_pgview", null, null, true);
    }

    public static void m() {
        EventLogger2.a().a("reg_signin_pgview", null, null);
    }

    public static void n() {
        EventLogger2.a().a("reg_welcome_pgview");
    }

    public static void o() {
        EventLogger2.a().a("reg_photo_add_clk");
    }

    public static void p() {
        EventLogger2.a().a("reg_photo_pgview");
    }

    public static void q() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("pickasong_pgview");
        EventLogger2.a().a(builder);
    }

    public static void r() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("upload_wifi_only_on");
        EventLogger2.a().a(builder);
    }

    public static void s() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("upload_wifi_only_off");
        EventLogger2.a().a(builder);
    }

    public static void t() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("trial_popup_pgview");
        EventLogger2.a().a(builder);
    }

}

