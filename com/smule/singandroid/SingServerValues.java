/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.singandroid;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.AppSettingsModel;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListSetConverter;
import com.smule.singandroid.SingApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SingServerValues {
    private static final String b = SingServerValues.class.getName();
    private static final PreSingSeedScreenVersion d = PreSingSeedScreenVersion.a;
    private static final PreSingSeedScreenAudioMode e = PreSingSeedScreenAudioMode.b;
    public final String a = "deviceSettings";
    private AppSettingsModel c;

    public SingServerValues() {
        this(AppSettingsManager.a());
    }

    public SingServerValues(@NonNull AppSettingsModel appSettingsModel) {
        this.c = appSettingsModel;
    }

    private ArrayList<String> a(String string2, String string3, String string4) {
        return this.a(SingApplication.i(), string2, string3, string4);
    }

    private ArrayList<String> a(String string2, String string3, String string4, String string5) {
        return this.a(string2, string3, string4, string5, false);
    }

    private ArrayList<String> a(String arrayList, String string2, String string3, String string4, boolean bl) {
        arrayList = this.c.a((String)((Object)arrayList) + string2, string3, string4);
        try {
            arrayList = new ArrayList((List)JsonUtils.a().readValue((String)((Object)arrayList), (TypeReference)new TypeReference<List<String>>(){}));
            int n = arrayList.size();
            if (n > 0 || bl) {
                return arrayList;
            }
        }
        catch (IOException iOException) {
            Log.e(b, "getArrayValue() - Exception thrown parsing JSON");
            iOException.printStackTrace();
        }
        try {
            Log.d(b, "getArrayValue for settingId " + string2 + " and key " + string3 + " had some issues. Returning default value: " + string4);
            arrayList = new ArrayList<String>((List)JsonUtils.a().readValue(string4, (TypeReference)new TypeReference<List<String>>(){}));
            return arrayList;
        }
        catch (IOException iOException) {
            Log.e(b, "getArrayValue() - Exception thrown in fallback option");
            iOException.printStackTrace();
            return new ArrayList<String>();
        }
    }

    private String at() {
        return this.c.a("sing.audioConfig", "defaultFX", "studio");
    }

    private String au() {
        return this.c.a("sing.audioConfig", "defaultOTAFX", "normal");
    }

    public int A() {
        return this.c.b("sing.audioConfig", "openSLVer", 0);
    }

    public OtaLatencyMethod B() {
        String string2 = this.c.a("sing.audioConfig", "otaLatencyMethod", "deviceSettings");
        if ("deviceSettings".equals(string2)) {
            return OtaLatencyMethod.a;
        }
        if ("audioAlignment".equals(string2)) {
            return OtaLatencyMethod.b;
        }
        Log.d(b, "OTA method unknown: " + string2);
        return OtaLatencyMethod.a;
    }

    public int C() {
        return this.c.b("sing.audioConfig", "defaultSuperpoweredLatency", 20);
    }

    public int D() {
        return this.c.b("sing.audioConfig", "nptgPercentage", 10);
    }

    public PaywallAdButtonPosition E() {
        PaywallAdButtonPosition paywallAdButtonPosition = PaywallAdButtonPosition.a;
        String string2 = this.c.a("sing.softPaywall", "adPosition", paywallAdButtonPosition.toString());
        try {
            PaywallAdButtonPosition paywallAdButtonPosition2 = PaywallAdButtonPosition.valueOf(string2.toUpperCase());
            return paywallAdButtonPosition2;
        }
        catch (Error error) {
            Log.e(b, "PaywallAdButtonPosition enum does not contain string value: '" + string2.toUpperCase() + "'");
            return paywallAdButtonPosition;
        }
    }

    public long F() {
        return this.c.b("sing.audioConfig", "uploadSliceSizeKB", 500);
    }

    public int G() {
        return this.c.b("sing.audioConfig", "uploadTimeoutSecs", 600);
    }

    public boolean H() {
        boolean bl;
        boolean bl2 = bl = false;
        if (!SubscriptionManager.a().b()) {
            bl2 = bl;
            if (this.c.a("sing_google.songUpsell", "enabled", false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public int I() {
        return this.c.b("sing.search", "autocompleteDelayMS", 500);
    }

    public int J() {
        return this.c.b("sing.search", "numRecsToFetch", 3);
    }

    public int K() {
        return this.c.b("sing_google.explore", "playlistId", 11);
    }

    public int L() {
        return this.c.b("sing.chat", "maxGroupMembers", 25);
    }

    public List<String> M() {
        return AppSettingsManager.a().a("sing.videoFX", "order", new ArrayList());
    }

    public List<String> N() {
        return AppSettingsManager.a().a("sing.videoFX", "vip", new ArrayList());
    }

    public String O() {
        return AppSettingsManager.a().a("sing.videoFX", "default", "selfie");
    }

    public boolean P() {
        boolean bl = false;
        JsonNode jsonNode = this.c.a("sing.videoFX", "airbrush", (JsonNode)null);
        boolean bl2 = bl;
        if (jsonNode != null) {
            bl2 = bl;
            if (jsonNode.get("enabled").asBoolean(false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public List<String> Q() {
        return AppSettingsManager.a().a("sing.videoStyles", "order", new ArrayList());
    }

    public List<String> R() {
        return AppSettingsManager.a().a("sing.videoStyles", "teaser", new ArrayList());
    }

    public List<String> S() {
        return AppSettingsManager.a().a("sing.videoStyles", "vip", new ArrayList());
    }

    public String T() {
        return AppSettingsManager.a().a("sing.videoStyles", "default", "rio");
    }

    public boolean U() {
        return this.c.a("sing.videoStyles", "particlesEnabled", false);
    }

    public String V() {
        if (!this.U()) {
            return "off";
        }
        return AppSettingsManager.a().a("sing.videoStyles", "defaultParticleIntensity", "off");
    }

    public int W() {
        return this.c.b("sing.video", "singleEGLContextVersion", 0);
    }

    public boolean X() {
        return this.c.a("sing.crm", "appboyEnabled", false);
    }

    public String Y() {
        return this.c.a("sing.softPaywall", "adCopyPackage", "packageA");
    }

    public double Z() {
        return this.c.a("sing.avqSurvey", "rate", 0.015);
    }

    public String a() {
        return this.c.a(SingApplication.i() + ".user_messages", "forced_upgrade_alert_positive_button_text", 2131297305);
    }

    public String a(boolean bl) {
        if (bl) {
            return this.at();
        }
        return this.au();
    }

    public boolean aa() {
        return this.c.a("sing.paywall", "showV2", false);
    }

    public int ab() {
        return this.c.b("sing.profile", "storageLimit", 5);
    }

    public boolean ac() {
        return this.c.a("sing.boost", "enableBoostFeature", false);
    }

    public boolean ad() {
        return this.c.a("sing.share", "FBDirect", false);
    }

    public boolean ae() {
        return this.c.a("sing.onboarding", "search", false);
    }

    public boolean af() {
        return this.c.a("sing.onboarding", "topics", true);
    }

    public boolean ag() {
        return this.c.a("sing.onboarding", "skipTopicsEnabled", true);
    }

    public String ah() {
        return this.c.a("sing_google.preSing", "recTypeScreen", RecTypeScreen.a.a());
    }

    public boolean ai() {
        return this.c.a("sing.feed", "socialOnlyEnabled", true);
    }

    public String aj() {
        return this.c.a("sing.findFriendsModule", "placements", (String)null);
    }

    public boolean ak() {
        return this.c.a("sing.videoStyles", "joinersChoiceEnabled", false);
    }

    public boolean al() {
        return this.c.a("sing.registration", "fbFindFriendsEnabled", false);
    }

    public boolean am() {
        return this.c.a("sing.registration", "contactsFindFriendsEnabled", false);
    }

    public String an() {
        return this.c.a("sing.registration", "newHandleScreenType", (String)null);
    }

    public boolean ao() {
        return this.c.a("sing.localization", "localeSwitcherEnabled", false);
    }

    public boolean ap() {
        return this.c.a("sing.seeds", "hotListEnabled", false);
    }

    public boolean aq() {
        return this.c.a("sing.freeform", "videoAddEnabled", false);
    }

    public boolean ar() {
        return this.c.a("sing.audioConfig", "autoTuneToggleEnabled", false);
    }

    public boolean as() {
        return this.c.a("sing.audioConfig", "autoTuneDefaultValue", false);
    }

    public String b() {
        return this.c.a(SingApplication.i() + ".user_messages", "forced_upgrade_alert_message", 2131297306);
    }

    public int c() {
        return this.c.b("sing.arr", "unlockPrice", 35);
    }

    public boolean d() {
        return this.c.a("sing.arr", "logRecCompletedArr", false);
    }

    public List<String> e() {
        return this.a("sing_google", ".ads", "preload_sdks", "[]");
    }

    public List<String> f() {
        return this.a(".offers", "video_priority", "[\"AdColony\"]");
    }

    public List<String> g() {
        return this.a(".offers", "wall_priority", "[\"SponsorPay\"]");
    }

    public Set<String> h() {
        return ListSetConverter.a(this.a("sing", ".audioConfig", "vipOnlyFX", "[\"super_studio\", \"super_pop\"]", true));
    }

    public List<String> i() {
        return this.a("sing", ".audioConfig", "audioFXOrder", "[\"dry\", \"studio\", \"normal\", \"super_harmonizer\", \"pop\", \"super_pop\", \"super_studio\", \"indie\", \"doubler\", \"star_dust\", \"grunge\"]");
    }

    public float j() {
        return (float)this.c.a("sing.audioConfig", "otaBacktrackLevel", 0.5);
    }

    public String k() {
        return this.c.a("sing.audioConfig", "vocalMonitorConfig", "{ \"vocalMonitor\": { \"versionSpec\": \"0\" } }");
    }

    public String l() {
        return this.c.a("sing.audioConfig", "otaFXConfig", "{ \"otaBacktrackRecording\": { \"versionSpec\": \"0\" } }");
    }

    public String m() {
        return this.c.a("sing.audioConfig", "fxConfig", "{}");
    }

    public boolean n() {
        return this.c.a("sing.audioConfig", "usePreGain", false);
    }

    public boolean o() {
        return this.c.a("sing.audioConfig", "autoplayOnReview", true);
    }

    public boolean p() {
        return this.c.a("sing.audioConfig", "rtmV2Enabled", false);
    }

    public boolean q() {
        return this.c.a("sing.audioConfig", "offlineDecoding", false);
    }

    public String r() {
        return this.c.a("sing.cccp", "defaultSort", "mostPlayed");
    }

    public boolean s() {
        return this.c.a("sing.cccp", "showPitchTracks", false);
    }

    public int t() {
        return this.c.b("sing.acappella", "minDurationSec", 30);
    }

    public long u() {
        return this.c.b("sing.video", "uploadSliceSizeKB", 500);
    }

    public int v() {
        return this.c.b("sing.video", "uploadTimeoutSecs", 600);
    }

    public boolean w() {
        boolean bl = false;
        JsonNode jsonNode = this.c.a("sing.video", "uploadWifiOnly", (JsonNode)null);
        boolean bl2 = bl;
        if (jsonNode != null) {
            bl2 = bl;
            if (jsonNode.get("enabled").asBoolean(false)) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public int x() {
        int n = 3;
        JsonNode jsonNode = this.c.a("sing.video", "uploadWifiOnly", (JsonNode)null);
        if (jsonNode != null) {
            n = jsonNode.get("reminderDialogThreshold").asInt(3);
        }
        return n;
    }

    public int y() {
        return this.c.b("sing.video", "groupJoinLimit", 50);
    }

    public boolean z() {
        return this.c.a("sing_google.audioConfig", "SAPAToggle", false);
    }

    public static enum OtaLatencyMethod {
        a,
        b;
        

        private OtaLatencyMethod() {
        }
    }

    public static enum PaywallAdButtonPosition {
        a("top"),
        b("bottom");
        
        private final String c;

        private PaywallAdButtonPosition(String string3) {
            this.c = string3;
        }
    }

    public static enum PreSingSeedScreenAudioMode {
        a("muted"),
        b("playing"),
        c("volume_ramp");
        
        private final String d;

        private PreSingSeedScreenAudioMode(String string3) {
            this.d = string3;
        }

        public String toString() {
            return this.d;
        }
    }

    public static enum PreSingSeedScreenVersion {
        a("list"),
        b("snippets");
        
        private final String c;

        private PreSingSeedScreenVersion(String string3) {
            this.c = string3;
        }

        public String toString() {
            return this.c;
        }
    }

    public static enum RecTypeScreen {
        a("original"),
        b("clarified");
        
        String c;

        private RecTypeScreen(String string3) {
            this.c = string3;
        }

        public String a() {
            return this.c;
        }
    }

}

