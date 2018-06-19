package com.smule.singandroid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.ListSetConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import twitter4j.HttpResponseCode;

public class SingServerValues {
    private static final String f20182a = SingServerValues.class.getName();
    private static final PreSingSeedScreenVersion f20183b = PreSingSeedScreenVersion.R45_List;
    private static final PreSingSeedScreenAudioMode f20184c = PreSingSeedScreenAudioMode.Playing;

    static class C41411 extends TypeReference<List<String>> {
        C41411() {
        }
    }

    static class C41422 extends TypeReference<List<String>> {
        C41422() {
        }
    }

    public enum PaywallAdButtonPosition {
        TOP("top"),
        BOTTOM("bottom");
        
        private final String f20172c;

        private PaywallAdButtonPosition(String str) {
            this.f20172c = str;
        }
    }

    public enum PreSingSeedScreenAudioMode {
        Muted("muted"),
        Playing("playing"),
        AudioRamp("volume_ramp");
        
        private final String f20177d;

        private PreSingSeedScreenAudioMode(String str) {
            this.f20177d = str;
        }

        public String toString() {
            return this.f20177d;
        }
    }

    public enum PreSingSeedScreenVersion {
        R45_List("list"),
        R46_Highlights("snippets");
        
        private final String f20181c;

        private PreSingSeedScreenVersion(String str) {
            this.f20181c = str;
        }

        public String toString() {
            return this.f20181c;
        }
    }

    public static String m21678a() {
        return AppSettingsManager.a().a(SingApplication.h() + ".user_messages", "forced_upgrade_alert_positive_button_text", C1947R.string.server_upgrade_btn);
    }

    public static String m21681b() {
        return AppSettingsManager.a().a(SingApplication.h() + ".user_messages", "forced_upgrade_alert_message", C1947R.string.server_upgrade_msg);
    }

    public static int m21682c() {
        return AppSettingsManager.a().c("sing.arr", "unlockPrice", 35);
    }

    private static ArrayList<String> m21680a(String str, String str2, String str3, String str4) {
        try {
            ArrayList<String> arrayList = new ArrayList((List) JsonUtils.m18984a().readValue(AppSettingsManager.a().a(str + str2, str3, str4), new C41411()));
            if (arrayList.size() > 0) {
                return arrayList;
            }
        } catch (IOException e) {
            Log.e(f20182a, "getArrayValue() - Exception thrown parsing JSON");
            e.printStackTrace();
        }
        try {
            Log.d(f20182a, "getArrayValue for settingId " + str2 + " and key " + str3 + " had some issues. Returning default value: " + str4);
            return new ArrayList((List) JsonUtils.m18984a().readValue(str4, new C41422()));
        } catch (IOException e2) {
            Log.e(f20182a, "getArrayValue() - Exception thrown in fallback option");
            e2.printStackTrace();
            return new ArrayList();
        }
    }

    private static ArrayList<String> m21679a(String str, String str2, String str3) {
        return m21680a(SingApplication.h(), str, str2, str3);
    }

    public static List<String> m21683d() {
        return m21680a("sing_google", ".ads", "preload_sdks", "[]");
    }

    public static List<String> m21684e() {
        return m21679a(".offers", "video_priority", "[\"AdColony\"]");
    }

    public static List<String> m21685f() {
        return m21679a(".offers", "wall_priority", "[\"SponsorPay\"]");
    }

    public static Set<String> m21686g() {
        return ListSetConverter.m18995a(m21680a("sing", ".audioConfig", "vipOnlyFX", "[\"super_studio\", \"super_pop\"]"));
    }

    public static List<String> m21687h() {
        return m21680a("sing", ".audioConfig", "audioFXOrder", "[\"dry\", \"studio\", \"normal\", \"super_harmonizer\", \"pop\", \"super_pop\", \"super_studio\", \"indie\", \"doubler\", \"star_dust\", \"grunge\"]");
    }

    public static boolean m21688i() {
        return AppSettingsManager.a().a("sing.audioConfig", "usePreGain", false);
    }

    public static String m21689j() {
        return AppSettingsManager.a().a("sing.audioConfig", "defaultFX", "studio");
    }

    public static String m21690k() {
        return AppSettingsManager.a().a("sing_google.songbook", "defaultSectionId", "suggested_songs");
    }

    public static String m21691l() {
        return AppSettingsManager.a().a("sing.cccp", "defaultSort", "mostPlayed");
    }

    public static boolean m21692m() {
        return AppSettingsManager.a().a("sing.cccp", "arrVisible", true);
    }

    public static int m21693n() {
        return AppSettingsManager.a().c("sing.cccp", "minTilOld", 60);
    }

    public static String m21694o() {
        return AppSettingsManager.a().b("sing_google.cccp", "tabNameKey", C1947R.string.songbook_community_tab);
    }

    public static boolean m21695p() {
        return AppSettingsManager.a().a("sing.cccp", "showPitchTracks", false);
    }

    public static int m21696q() {
        return AppSettingsManager.a().c("sing.acappella", "minDurationSec", 30);
    }

    public static long m21697r() {
        return (long) AppSettingsManager.a().c("sing.video", "uploadSliceSizeKB", HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public static int m21698s() {
        return AppSettingsManager.a().c("sing.video", "uploadTimeoutSecs", 600);
    }

    public static int m21699t() {
        return AppSettingsManager.a().c("sing.video", "groupJoinLimit", 50);
    }

    public static boolean m21700u() {
        return AppSettingsManager.a().a("sing_google.audioConfig", "SAPAToggle", false);
    }

    public static int m21701v() {
        return AppSettingsManager.a().c("sing.audioConfig", "openSLVersion", 0);
    }

    public static PaywallAdButtonPosition m21702w() {
        PaywallAdButtonPosition paywallAdButtonPosition = PaywallAdButtonPosition.TOP;
        String a = AppSettingsManager.a().a("sing.softPaywall", "adPosition", paywallAdButtonPosition.toString());
        try {
            paywallAdButtonPosition = PaywallAdButtonPosition.valueOf(a.toUpperCase());
        } catch (Error e) {
            Log.e(f20182a, "PaywallAdButtonPosition enum does not contain string value: '" + a.toUpperCase() + "'");
        }
        return paywallAdButtonPosition;
    }

    public static long m21703x() {
        return (long) AppSettingsManager.a().c("sing.audioConfig", "uploadSliceSizeKB", HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public static int m21704y() {
        return AppSettingsManager.a().c("sing.audioConfig", "uploadTimeoutSecs", 600);
    }

    public static boolean m21705z() {
        return !SubscriptionManager.a().b() && AppSettingsManager.a().a("sing_google.songbook", "recommendedSectionFree", false);
    }

    public static String m21663A() {
        return AppSettingsManager.a().b("sing_google.songbook", "recommendedSectionFreeTitle", C1947R.string.songbook_suggested_songs_free);
    }

    public static boolean m21664B() {
        return !SubscriptionManager.a().b() && AppSettingsManager.a().a("sing_google.songUpsell", "enabled", false);
    }

    public static int m21665C() {
        return AppSettingsManager.a().c("sing.search", "autocompleteDelayMS", HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public static int m21666D() {
        return AppSettingsManager.a().c("sing.search", "instantSearchDelayMS", HttpResponseCode.INTERNAL_SERVER_ERROR);
    }

    public static int m21667E() {
        return AppSettingsManager.a().c("sing.search", "numRecsToFetch", 3);
    }

    public static int m21668F() {
        return AppSettingsManager.a().c("sing_google.explore", "playlistId", 11);
    }

    public static int m21669G() {
        return AppSettingsManager.a().c("sing.chat", "maxGroupMembers", 25);
    }

    public static List<String> m21670H() {
        return AppSettingsManager.a().a("sing.videoFX", "order", new ArrayList());
    }

    public static List<String> m21671I() {
        return AppSettingsManager.a().a("sing.videoFX", "vip", new ArrayList());
    }

    public static boolean m21672J() {
        JsonNode a = AppSettingsManager.a().a("sing.videoFX", "airbrush", null);
        if (a == null || !a.get("enabled").asBoolean(false)) {
            return false;
        }
        return true;
    }

    public static boolean m21673K() {
        return AppSettingsManager.a().a("sing.crm", "appboyEnabled", false);
    }

    public static String m21674L() {
        return AppSettingsManager.a().a("sing.softPaywall", "adCopyPackage", "packageA");
    }

    public static double m21675M() {
        return AppSettingsManager.a().a("sing.avqSurvey", "rate", 0.015d);
    }

    public static boolean m21676N() {
        return AppSettingsManager.a().a("sing.paywall", "showV2", false);
    }

    public static int m21677O() {
        return AppSettingsManager.a().c("sing.profile", "storageLimit", 5);
    }
}
