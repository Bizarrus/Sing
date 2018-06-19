package com.smule.android.logging;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.accountkit.internal.InternalLogger;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.common.Scopes;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.logging.EventLogger2$Event.Builder;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;

public class Analytics {
    protected static String f16324a = null;
    private static final String f16325b = Analytics.class.getName();

    public interface AnalyticsType {
        String mo6235a();
    }

    public enum AdCategory implements AnalyticsType {
        THIRD_PARTY("third_party"),
        XPROMO("x_promo"),
        PARTNER_ARTIST("partner_artist");
        
        private String f16005d;

        private AdCategory(String str) {
            this.f16005d = str;
        }

        public String mo6235a() {
            return this.f16005d;
        }
    }

    public enum AdMediator implements AnalyticsType {
        MOPUB("mopub"),
        DFP("dfp");
        
        private String f16009c;

        private AdMediator(String str) {
            this.f16009c = str;
        }

        public String mo6235a() {
            return this.f16009c;
        }
    }

    public enum AdType implements AnalyticsType {
        BANNER("banner"),
        INTERSTITIAL(com.mopub.common.AdType.INTERSTITIAL),
        NATIVE(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE),
        REWARDED_VIDEO(com.mopub.common.AdType.REWARDED_VIDEO);
        
        private String f16015e;

        private AdType(String str) {
            this.f16015e = str;
        }

        public String mo6235a() {
            return this.f16015e;
        }
    }

    public enum AppLaunchErrorType implements AnalyticsType {
        NONE("none"),
        TIMEOUT("timeout"),
        UNREACHABLE("unreachable");
        
        private String f16020d;

        private AppLaunchErrorType(String str) {
            this.f16020d = str;
        }

        public String mo6235a() {
            return this.f16020d;
        }
    }

    public enum AttemptType implements AnalyticsType {
        NEW("new"),
        RETRY(InternalLogger.EVENT_PARAM_EXTRAS_RETRY);
        
        private String f16024c;

        private AttemptType(String str) {
            this.f16024c = str;
        }

        public String mo6235a() {
            return this.f16024c;
        }
    }

    public enum AutocompleteSectionName implements AnalyticsType {
        RECENT("recent"),
        FOLLOWING("following"),
        SUGGESTED("suggested"),
        SINGERS("singers");
        
        private String f16030e;

        private AutocompleteSectionName(String str) {
            this.f16030e = str;
        }

        public String mo6235a() {
            return this.f16030e;
        }
    }

    public enum CacheType implements AnalyticsType {
        RESOURCE("resource"),
        f16032b("image");
        
        private String f16034c;

        private CacheType(String str) {
            this.f16034c = str;
        }

        public String mo6235a() {
            return this.f16034c;
        }
    }

    public enum CameraStatusType implements AnalyticsType {
        CAMERA_ON("cam_on"),
        CAMERA_OFF("cam_off");
        
        private String f16038c;

        private CameraStatusType(String str) {
            this.f16038c = str;
        }

        public String mo6235a() {
            return this.f16038c;
        }
    }

    public enum Cost implements AnalyticsType {
        FREE("free"),
        OWNED("owned"),
        CREDITS("credits"),
        VIP("vip"),
        GIFT("gift");
        
        private String f16045f;

        private Cost(String str) {
            this.f16045f = str;
        }

        public String mo6235a() {
            return this.f16045f;
        }
    }

    public enum DFPVendor implements AnalyticsType {
        ADCOLONY(EarnVCVendor.ADCOLONY.mo6235a()),
        APPLOVIN(EarnVCVendor.APPLOVIN.mo6235a()),
        ADMOB("admob");
        
        private String f16050d;

        private DFPVendor(String str) {
            this.f16050d = str;
        }

        public String mo6235a() {
            return this.f16050d;
        }
    }

    public enum EarnVC implements AnalyticsType {
        WATCHING_VIDEO("videoads"),
        OFFER_WALL("offerwall"),
        LOGIN("login"),
        ACHIEVEMENT("achievement"),
        FB_CONNECT("fbconnect"),
        FB_LIKE("fblike"),
        SMS("sms");
        
        private String f16059h;

        private EarnVC(String str) {
            this.f16059h = str;
        }

        public String mo6235a() {
            return this.f16059h;
        }
    }

    public enum EarnVCVendor implements AnalyticsType {
        ADCOLONY("adcolony"),
        FYBER("sponsorpay"),
        APPLOVIN("applovin");
        
        private String f16064d;

        private EarnVCVendor(String str) {
            this.f16064d = str;
        }

        public String mo6235a() {
            return this.f16064d;
        }
    }

    public enum Ensemble implements AnalyticsType {
        SOLO("SOLO"),
        DUET("DUET"),
        GROUP("GROUP"),
        BACKUP("BACKUP"),
        MIX("MIX"),
        BATTLE("BATTLE"),
        UNDEFINED("UNDEFINED");
        
        private String f16073h;

        private Ensemble(String str) {
            this.f16073h = str;
        }

        public String mo6235a() {
            return this.f16073h;
        }
    }

    public interface EventModifier {
        EventLogger2$Event m17787a(EventLogger2$Event eventLogger2$Event);
    }

    public enum FacebookReferrer implements AnalyticsType {
        SPLASH("splash"),
        CREATE("create"),
        SIGN_IN("signin");
        
        private String f16078d;

        private FacebookReferrer(String str) {
            this.f16078d = str;
        }

        public String mo6235a() {
            return this.f16078d;
        }
    }

    public enum FollowingStatus implements AnalyticsType {
        FOLLOWING("following"),
        NOT_FOLLOWING("not_following");
        
        private String f16082c;

        private FollowingStatus(String str) {
            this.f16082c = str;
        }

        public String mo6235a() {
            return this.f16082c;
        }
    }

    public enum FullScreenAdPlacementType implements AnalyticsType {
        SINGING_SOLO("singing.solo"),
        SINGING_SEED("singing.seed"),
        SINGING_JOIN("singing.join"),
        LAUNCH("launch");
        
        private String f16088e;

        private FullScreenAdPlacementType(String str) {
            this.f16088e = str;
        }

        public String mo6235a() {
            return this.f16088e;
        }
    }

    public enum HandleUpdateType implements AnalyticsType {
        UNCHANGED("unchanged"),
        CHANGED("changed");
        
        private String f16092c;

        private HandleUpdateType(String str) {
            this.f16092c = str;
        }

        public String mo6235a() {
            return this.f16092c;
        }
    }

    public enum ItemClickType implements AnalyticsType {
        LOVE("love"),
        LISTEN("listen"),
        COMMENT("comment"),
        PROFILE(Scopes.PROFILE),
        SING("start"),
        JOIN("join"),
        FOLLOW("follow"),
        UNFOLLOW("unfollow"),
        SHARE(ShareDialog.WEB_SHARE_DIALOG),
        PREVIEW("preview"),
        SEARCH("search"),
        GROUP("group"),
        f16105m("song");
        
        private String f16107n;

        private ItemClickType(String str) {
            this.f16107n = str;
        }

        public String mo6235a() {
            return this.f16107n;
        }
    }

    public enum NativeAdPlacementType implements AnalyticsType {
        SONGBOOK_LISTINGS("songbook.listings"),
        SONGBOOK_CAROUSEL("songbook.carousel"),
        FEED("feed"),
        NOTIFICATIONS_INVITES("notifications.invites"),
        PROFILE_INVITES("profile.invites"),
        SEARCH_MIXED("search.mixed"),
        SEARCH_EXPANDED("search.expanded");
        
        private String f16116h;

        private NativeAdPlacementType(String str) {
            this.f16116h = str;
        }

        public String mo6235a() {
            return this.f16116h;
        }
    }

    public enum Notification implements AnalyticsType {
        SOCIAL("social"),
        CONTENT("content"),
        MESSAGING("messaging");
        
        private String f16121d;

        private Notification(String str) {
            this.f16121d = str;
        }

        public String mo6235a() {
            return this.f16121d;
        }
    }

    public enum PageType implements AnalyticsType {
        SCREEN("screen"),
        MODAL("modal");
        
        private String f16125c;

        private PageType(String str) {
            this.f16125c = str;
        }

        public String mo6235a() {
            return this.f16125c;
        }
    }

    public enum PaywallType implements AnalyticsType {
        HARD("hard"),
        SOFT("soft");
        
        private String f16129c;

        private PaywallType(String str) {
            this.f16129c = str;
        }

        public String mo6235a() {
            return this.f16129c;
        }
    }

    public enum PerformanceStatus implements AnalyticsType {
        NORMAL("n"),
        ACTIVE("a"),
        CLOSED("c"),
        EXPIRED("e");
        
        private String f16135e;

        private PerformanceStatus(String str) {
            this.f16135e = str;
        }

        public String mo6235a() {
            return this.f16135e;
        }
    }

    public enum PermissionAskType implements AnalyticsType {
        HARD_ASK("hard_ask"),
        SOFT_ASK("soft_ask"),
        SOFT_ASK_AGAIN("soft_ask_again"),
        ERROR_ASK("error_ask");
        
        private String f16141e;

        private PermissionAskType(String str) {
            this.f16141e = str;
        }

        public String mo6235a() {
            return this.f16141e;
        }
    }

    public enum PermissionErrorReason implements AnalyticsType {
        PERMISSION_DENIED("permission_denied"),
        DEVICE_ERROR("device_error");
        
        private String f16145c;

        private PermissionErrorReason(String str) {
            this.f16145c = str;
        }

        public String mo6235a() {
            return this.f16145c;
        }
    }

    public enum PermissionNeverAskAgain implements AnalyticsType {
        YES("yes"),
        NO("no");
        
        private String f16149c;

        public static PermissionNeverAskAgain m17800a(Boolean bool) {
            if (bool == null) {
                return null;
            }
            return bool.booleanValue() ? YES : NO;
        }

        private PermissionNeverAskAgain(String str) {
            this.f16149c = str;
        }

        public String mo6235a() {
            return this.f16149c;
        }
    }

    public enum PermissionResult implements AnalyticsType {
        OKAY("okay"),
        DENY("deny"),
        OPEN_SETTINGS("open_settings");
        
        private String f16154d;

        public static PermissionResult m17802a(int i) {
            return i == 0 ? OKAY : DENY;
        }

        private PermissionResult(String str) {
            this.f16154d = str;
        }

        public String mo6235a() {
            return this.f16154d;
        }
    }

    public enum ProfilePicType implements AnalyticsType {
        NONE("none"),
        PHOTO(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO),
        EXISTING("existing");
        
        private String f16159d;

        private ProfilePicType(String str) {
            this.f16159d = str;
        }

        public String mo6235a() {
            return this.f16159d;
        }
    }

    public enum PurchaseType implements AnalyticsType {
        RESTORE("RESTORE"),
        START("START");
        
        private String f16163c;

        private PurchaseType(String str) {
            this.f16163c = str;
        }

        public String mo6235a() {
            return this.f16163c;
        }
    }

    public enum RecSysContext implements AnalyticsType {
        FEED("feed"),
        SONGBOOK("songbook"),
        FINDFRIENDS("findfriends"),
        OPEN_SEED_LIST_ALL("open_seed_list_all"),
        OPEN_SEED_LIST_VIDEO("open_seed_list_video"),
        SUGGESTED_ARRANGERS("suggested_arrangers"),
        PICK_A_SONG("pickasong"),
        SEARCH("search"),
        PERFLIST("perflist"),
        PG_SONGBOOK("pg_songbook"),
        SUGGESTED_SONGS("suggested_songs");
        
        private String f16176l;

        private RecSysContext(String str) {
            this.f16176l = str;
        }

        public String mo6235a() {
            return this.f16176l;
        }
    }

    public enum RecommendationType implements AnalyticsType {
        FEED("feed"),
        PERFORMANCE("perf"),
        f16179c("song"),
        ACCOUNT("acct"),
        TRENDING("trending"),
        NONE("-");
        
        private String f16184g;

        private RecommendationType(String str) {
            this.f16184g = str;
        }

        public String mo6235a() {
            return this.f16184g;
        }
    }

    public enum RegistrationAccountType implements AnalyticsType {
        NEW("new"),
        EXISTING("existing");
        
        private String f16188c;

        private RegistrationAccountType(String str) {
            this.f16188c = str;
        }

        public String mo6235a() {
            return this.f16188c;
        }
    }

    public enum RegistrationFailure implements AnalyticsType {
        CLIENT_ERROR("client_error"),
        SNP_ERROR("snp_error"),
        FACEBOOK_ERROR("fb_error"),
        GPLUS_ERROR("gplus_error");
        
        private String f16194e;

        private RegistrationFailure(String str) {
            this.f16194e = str;
        }

        public String mo6235a() {
            return this.f16194e;
        }
    }

    public enum RegistrationFlow implements AnalyticsType {
        EMAIL("email"),
        FACEBOOK("fb"),
        DEVICE_FOUND("device_found"),
        GPLUS("gplus"),
        GOOGLE("goog"),
        PHONE("acctkit");
        
        private String f16202g;

        private RegistrationFlow(String str) {
            this.f16202g = str;
        }

        public String mo6235a() {
            return this.f16202g;
        }
    }

    public enum RegistrationType implements AnalyticsType {
        EMAIL("email"),
        PHONE("phone");
        
        private String f16206c;

        private RegistrationType(String str) {
            this.f16206c = str;
        }

        public String mo6235a() {
            return this.f16206c;
        }
    }

    public enum SearchBarExitContext implements AnalyticsType {
        EXIT("exit"),
        CLEAR(com.mopub.common.AdType.CLEAR),
        SCROLL("scroll"),
        GO("go"),
        CLICK("click");
        
        private String f16213f;

        private SearchBarExitContext(String str) {
            this.f16213f = str;
        }

        public String mo6235a() {
            return this.f16213f;
        }
    }

    public enum SearchClkContext implements AnalyticsType {
        SONGBOOK("songbook"),
        EXPLORE("explore"),
        FEED("feed"),
        NOTIFICATION("notification"),
        PROFILE(Scopes.PROFILE),
        FINDFRIENDS("findfriends"),
        CHATSEARCH("chatsearch"),
        POSTSING("postsing"),
        SHAREMESSAGE("sharemessage"),
        COMMENT("comment"),
        RECENT("recent"),
        MIXED("mixed"),
        BOTTOMOFRECLIST("bottomofreclist");
        
        private String f16228n;

        private SearchClkContext(String str) {
            this.f16228n = str;
        }

        public String mo6235a() {
            return this.f16228n;
        }
    }

    public enum SearchExecuteContext implements AnalyticsType {
        INPUT("input"),
        AUTOCOMPLETE("autocomplete"),
        BACK("back"),
        TAG("tag"),
        RECENT("recent"),
        INSTEAD("instead"),
        TRENDING("trending"),
        MENTION("mention");
        
        private String f16238i;

        private SearchExecuteContext(String str) {
            this.f16238i = str;
        }

        public String mo6235a() {
            return this.f16238i;
        }
    }

    public enum SearchResultClkContext implements AnalyticsType {
        PREVIEW("preview"),
        REGULAR("regular"),
        FOLLOW("follow"),
        UNFOLLOW("unfollow"),
        SING("sing"),
        JOIN("join"),
        PROFILE(Scopes.PROFILE);
        
        private String f16247h;

        private SearchResultClkContext(String str) {
            this.f16247h = str;
        }

        public String mo6235a() {
            return this.f16247h;
        }
    }

    public enum SearchResultClkValue implements AnalyticsType {
        SEE_ALL("see all"),
        MIXED("mixed"),
        NOWPLAYING("nowplaying"),
        DIRECT("direct");
        
        private String f16253e;

        private SearchResultClkValue(String str) {
            this.f16253e = str;
        }

        public String mo6235a() {
            return this.f16253e;
        }
    }

    public enum SearchSortClkTarget implements AnalyticsType {
        MOST_RECENT("MOST_RECENT"),
        MOST_POPULAR("MOST_POPULAR"),
        CLOSING_SOON("CLOSING_SOON");
        
        private String f16258d;

        private SearchSortClkTarget(String str) {
            this.f16258d = str;
        }

        public String mo6235a() {
            return this.f16258d;
        }
    }

    public enum SearchTarget implements AnalyticsType {
        f16259a("song"),
        PERFORMANCE("perf"),
        USER("acct"),
        INVITE("invite"),
        INSTANT_MIXED("instant song acct invite"),
        INSTANT_PERFORMANCE("instant perf"),
        DIRECT_INSTANT_MIXED("direct instant song acct invite"),
        DIRECT_INSTANT_PERFORMANCE("direct instant perf"),
        DIRECT_SONG("direct song"),
        DIRECT_PERFORMANCE("direct perf"),
        DIRECT_USER("direct acct"),
        DIRECT_INVITE("direct invite");
        
        private String f16272m;

        private SearchTarget(String str) {
            this.f16272m = str;
        }

        public String mo6235a() {
            return this.f16272m;
        }
    }

    public enum Share implements AnalyticsType {
        BASIC("basic"),
        CARD("card"),
        MORE("more");
        
        private String f16277d;

        private Share(String str) {
            this.f16277d = str;
        }

        public String mo6235a() {
            return this.f16277d;
        }
    }

    public enum SocialChannel implements AnalyticsType {
        SNP("snp"),
        FACEBOOK("facebook"),
        YOUTUBE("youtube"),
        TWITTER("twitter"),
        CHAT("chat"),
        LINE("line"),
        MESSENGER("messenger"),
        SMS("sms"),
        EMAIL("email"),
        SINGAGRAM("singagram"),
        COPY_LINK("copy_link"),
        GENERIC("more");
        
        private String f16291m;

        private SocialChannel(String str) {
            this.f16291m = str;
        }

        public String mo6235a() {
            return this.f16291m;
        }
    }

    public enum SongDownloadFileType implements AnalyticsType {
        MID("MID"),
        M4A("M4A");
        
        private String f16295c;

        private SongDownloadFileType(String str) {
            this.f16295c = str;
        }

        public String mo6235a() {
            return this.f16295c;
        }
    }

    public enum SongbookSortType implements AnalyticsType {
        MOST_RECENT("MOST_RECENT"),
        SONGS_ALPHA("SONGS_ALPHA"),
        ARTISTS_ALPHA("ARTISTS_ALPHA"),
        HIGHEST_RATED("HIGHEST_RATED"),
        MOST_POPULAR("MOST_POPULAR"),
        MOST_RELEVANT("MOST_RELEVANT"),
        FEATURED("FEATURED");
        
        private String f16304h;

        private SongbookSortType(String str) {
            this.f16304h = str;
        }

        public String mo6235a() {
            return this.f16304h;
        }
    }

    public enum SpendVC implements AnalyticsType {
        f16305a("song"),
        SOUNDFONT("soundfont");
        
        private String f16308c;

        private SpendVC(String str) {
            this.f16308c = str;
        }

        public String mo6235a() {
            return this.f16308c;
        }
    }

    public enum UploadCompletionType implements AnalyticsType {
        SUCCESS("success"),
        FAIL("fail"),
        CANCEL("cancel");
        
        private String f16313d;

        private UploadCompletionType(String str) {
            this.f16313d = str;
        }

        public String mo6235a() {
            return this.f16313d;
        }
    }

    public enum UserPath implements AnalyticsType {
        TUTORIAL("tutorial"),
        REWARD("reward"),
        ONBOARDING("onboarding"),
        OTHER(null);
        
        private String f16319e;

        private UserPath(String str) {
            this.f16319e = str;
        }

        public String mo6235a() {
            return this.f16319e;
        }
    }

    public enum VideoStatusType implements AnalyticsType {
        f16320a("true"),
        NOT_VIDEO(InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        
        private String f16323c;

        private VideoStatusType(String str) {
            this.f16323c = str;
        }

        public String mo6235a() {
            return this.f16323c;
        }
    }

    protected static String m17833a(boolean z) {
        return z ? "join" : "notjoin";
    }

    public static Ensemble m17828a(PerformanceV2 performanceV2) {
        if (performanceV2.e()) {
            return Ensemble.GROUP;
        }
        if (performanceV2.d()) {
            return Ensemble.DUET;
        }
        return Ensemble.SOLO;
    }

    public static String m17834a(boolean z, String str, boolean z2, String str2) {
        return Boolean.toString(z) + ',' + str + ',' + Boolean.toString(z2) + ',' + str2;
    }

    public static String m17831a(Boolean bool) {
        return bool == null ? null : bool.toString();
    }

    public static float m17827a(float f, int i) {
        float pow = (float) Math.pow(10.0d, (double) i);
        return ((float) Math.round(f * pow)) / pow;
    }

    public static <T extends Enum<T>> T m17829a(Class<T> cls, String str) {
        for (T t : (Enum[]) cls.getEnumConstants()) {
            if ((t instanceof AnalyticsType) && ((AnalyticsType) t).mo6235a().equalsIgnoreCase(str)) {
                return t;
            }
        }
        return null;
    }

    protected static void m17866a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            Log.e(f16325b, str2);
        }
    }

    protected static void m17843a(AnalyticsType analyticsType, String str) {
        if (analyticsType == null) {
            Log.e(f16325b, str);
        }
    }

    public static void m17888b(String str, String str2) {
        m17866a(str, "adUnitId is required");
        EventLogger2.a().a(new Builder().m17935a("interstitial_ad_shown").m17959d(str).m17966e(str2));
    }

    public static void m17848a(SearchClkContext searchClkContext) {
        EventLogger2.a().a(new Builder().m17935a("search_clk").m17941b((AnalyticsType) searchClkContext));
    }

    public static void m17854a(SearchTarget searchTarget, SearchResultClkContext searchResultClkContext, SearchResultClkValue searchResultClkValue, String str, String str2, Integer num, Long l, String str3, VideoStatusType videoStatusType, int i, int i2) {
        EventLogger2.a().a(new Builder().m17935a("search_result_clk").m17931a((AnalyticsType) searchTarget).m17941b((AnalyticsType) searchResultClkContext).m17949c((AnalyticsType) searchResultClkValue).m17966e(str).m17971f(str2).m17965e((Number) num).m17970f((Number) l).m17985i(str3).m17983i((AnalyticsType) videoStatusType).m17972g(i).m17977h(i2));
    }

    public static void m17853a(SearchTarget searchTarget, SearchExecuteContext searchExecuteContext, int i, String str, String str2, long j, Integer num) {
        if (str2 != null && str2.length() > 128) {
            str2 = str2.substring(0, 128);
        }
        Builder d = new Builder().m17935a("search_execute").m17931a((AnalyticsType) searchTarget).m17941b((AnalyticsType) searchExecuteContext).m17929a(i).m17966e(str).m17971f(str2).m17955d(j);
        if (!(num == null || num.intValue() == -1)) {
            d.m17970f((Number) num);
        }
        EventLogger2.a().a(d);
    }

    public static void m17851a(SearchClkContext searchClkContext, int i, Integer num, String str) {
        EventLogger2.a().a(new Builder().m17935a("search_clear_clk").m17941b((AnalyticsType) searchClkContext).m17929a(i).m17951c((Number) num).m17971f(str));
    }

    public static void m17850a(SearchClkContext searchClkContext, int i, int i2, String str, long j, String str2, Boolean bool, Boolean bool2, Boolean bool3) {
        EventLogger2.a().a(new Builder().m17935a("autocomplete_clk").m17941b((AnalyticsType) searchClkContext).m17929a(i).m17939b(i2).m17971f(m17832a(str)).m17955d(j).m17981h(str2).m17950c(bool).m17957d(bool2).m17964e(bool3));
    }

    public static void m17849a(SearchClkContext searchClkContext, int i, int i2, String str, long j, String str2, AutocompleteSectionName autocompleteSectionName, Integer num) {
        EventLogger2.a().a(new Builder().m17935a("autocomplete_clk").m17941b((AnalyticsType) searchClkContext).m17929a(i).m17939b(i2).m17971f(m17832a(str)).m17955d(j).m17981h(str2).m17979h((AnalyticsType) autocompleteSectionName).m17977h(i2));
    }

    public static void m17847a(SearchBarExitContext searchBarExitContext, String str, String str2) {
        EventLogger2.a().a(new Builder().m17935a("search_bar_exit").m17941b((AnalyticsType) searchBarExitContext).m17966e(m17832a(str2)).m17971f(m17832a(str)));
    }

    public static void m17852a(SearchSortClkTarget searchSortClkTarget, SearchTarget searchTarget) {
        EventLogger2.a().a(new Builder().m17935a("search_sort_clk").m17931a((AnalyticsType) searchSortClkTarget).m17974g((AnalyticsType) searchTarget));
    }

    private static String m17832a(String str) {
        if (str == null || !str.isEmpty()) {
            return str;
        }
        return null;
    }

    public static void m17877a(String str, String str2, String str3, String str4) {
        m17866a(str, "sku is required");
        m17866a(str3, "period is required");
        m17866a(str2, "context is required");
        EventLogger2.a().a("subs_buy_clk", str, str2, str3, str4);
    }

    public static void m17875a(String str, String str2, String str3, PaywallType paywallType) {
        EventLogger2.a().a(new Builder().m17935a("purchase_pgview").m17966e(str).m17976g(paywallType != null ? paywallType.mo6235a() : "-").m17985i(str2).m17990k(str3));
    }

    public static void m17835a() {
        EventLogger2.a().a(new Builder().m17935a("settings_pgview"));
    }

    public static void m17868a(String str, String str2, Ensemble ensemble) {
        m17866a(str, "perfKey is required");
        m17866a(str2, "songUid is required");
        EventLogger2.a().a("perf_del_clk", str, null, null, str2, null, ensemble.mo6235a());
    }

    public static void m17869a(String str, String str2, Ensemble ensemble, Integer num, String str3, VideoStatusType videoStatusType) {
        m17866a(str, "perfKey is required");
        m17866a(str2, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("perf_comment").m17944b(str).m17966e(str2).m17969f((AnalyticsType) ensemble).m17981h(num != null ? Integer.toString(num.intValue()) : null).m17985i(str3).m17983i((AnalyticsType) videoStatusType));
    }

    public static void m17864a(String str, UserPath userPath, String str2, String str3, Ensemble ensemble, String str4) {
        Builder b = new Builder().m17935a("perf_start_clk").m17944b(str).m17941b((AnalyticsType) userPath);
        if (str2 == null) {
            str2 = "-";
        }
        b = b.m17966e(str2).m17969f((AnalyticsType) ensemble);
        if (str3 == null) {
            str3 = "-";
        }
        EventLogger2.a().a(b.m17985i(str3).m17990k(str4));
    }

    protected static void m17874a(String str, String str2, String str3, Ensemble ensemble, EventModifier eventModifier) {
        m17866a(str, "perfKey is required");
        EventLogger2$Event eventLogger2$Event = new EventLogger2$Event("perf_join_clk", str, null, null, str2 != null ? str2 : "-", null, ensemble.mo6235a(), null, str3 != null ? str3 : "-", null, null);
        if (eventModifier != null) {
            eventModifier.m17787a(eventLogger2$Event);
        }
        EventLogger2.a().a(eventLogger2$Event);
    }

    public static void m17873a(String str, String str2, String str3, Ensemble ensemble) {
        m17866a(str, "perfKey is required");
        m17874a(str, str2, str3, ensemble, null);
    }

    public static void m17883b() {
        EventLogger2.a().a(new Builder().m17935a("perflist_pgview"));
    }

    public static void m17865a(String str, UserPath userPath, boolean z, String str2, boolean z2, String str3, HeadphonesType headphonesType, String str4, Ensemble ensemble, String str5, boolean z3) {
        m17866a(str, "perfKey is required");
        m17866a(str4, "songUid is required");
        m17843a((AnalyticsType) ensemble, "ensembleType is required");
        m17866a(str5, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("perf_start_create").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str4).m17971f(m17834a(z, str2, z2, str3)).m17969f((AnalyticsType) ensemble).m17985i(str5).m17988j(m17831a(Boolean.valueOf(z3))));
    }

    public static void m17887b(String str, UserPath userPath, boolean z, String str2, boolean z2, String str3, HeadphonesType headphonesType, String str4, Ensemble ensemble, String str5, boolean z3) {
        m17866a(str, "perfKey is required");
        m17866a(str4, "songUid is required");
        m17843a((AnalyticsType) ensemble, "ensembleType is required");
        m17866a(str5, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("perf_join_create").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str4).m17971f(m17834a(z, str2, z2, str3)).m17969f((AnalyticsType) ensemble).m17985i(str5).m17988j(m17831a(Boolean.valueOf(z3))));
    }

    public static void m17855a(UserPath userPath, String str, int i, String str2, String str3, String str4, Number number) {
        m17866a(str2, "costType is required");
        m17866a(str, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("song_clk").m17941b((AnalyticsType) userPath).m17943b(number).m17966e(str).m17947c(i).m17976g(str2).m17981h(str3).m17985i(str4));
    }

    public static void m17867a(String str, String str2, int i, String str3, String str4, String str5) {
        m17866a(str3, "costType is required");
        m17866a(str2, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("song_preview").m17952c(str).m17966e(str2).m17947c(i).m17976g(str3).m17981h(str4).m17985i(str5));
    }

    public static void m17846a(RegistrationType registrationType) {
        m17843a((AnalyticsType) registrationType, "type is required");
        EventLogger2.a().a("reg_acct_found", null, null, registrationType.mo6235a(), true);
    }

    public static void m17892c() {
        EventLogger2.a().a("reg_fbconnect_click", null, null, true);
    }

    public static void m17896d() {
        EventLogger2.a().a("reg_fbconnect_success", null, null, true);
    }

    public static void m17898e() {
        EventLogger2.a().a("reg_googconnect_clk", null, null, true);
    }

    public static void m17899f() {
        EventLogger2.a().a("reg_googconnect_success", null, null, true);
    }

    public static void m17845a(RegistrationAccountType registrationAccountType, RegistrationFlow registrationFlow) {
        m17843a((AnalyticsType) registrationAccountType, "accountType is required");
        m17843a((AnalyticsType) registrationFlow, "flow is required");
        EventLogger2.a().a("reg_flow_complete", null, null, registrationAccountType.mo6235a(), registrationFlow.mo6235a(), true);
    }

    public static void m17880a(boolean z, String str) {
        EventLogger2.a().a("reg_flow_start", null, str, Boolean.toString(z), true);
    }

    public static void m17900g() {
        EventLogger2.a().a("reg_forgotpwd_pgview", true);
    }

    public static void m17901h() {
        EventLogger2.a().a("reg_forgotpwd_success", true);
    }

    public static void m17902i() {
        EventLogger2.a().a("reg_gplusdisconnect_success", true);
    }

    public static void m17903j() {
        EventLogger2.a().a("reg_landing_pgview", null, null, true);
    }

    public static void m17904k() {
        EventLogger2.a().a("reg_newacct_pgview", null, null, true);
    }

    public static void m17844a(HandleUpdateType handleUpdateType, ProfilePicType profilePicType) {
        EventLogger2.a().a("reg_profile_update", null, null, null, handleUpdateType.mo6235a(), profilePicType.mo6235a(), true);
    }

    public static void m17905l() {
        EventLogger2.a().a("reg_signin_pgview", null, null);
    }

    public static void m17906m() {
        EventLogger2.a().a("reg_welcome_pgview");
    }

    public static void m17872a(String str, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("push_clk").m17952c(str).m17981h(str3).m17959d(str2));
    }

    public static void m17907n() {
        EventLogger2.a().a(new Builder().m17935a("pickasong_pgview"));
    }

    public static void m17857a(String str, HeadphonesType headphonesType, String str2, String str3, CameraStatusType cameraStatusType) {
        EventLogger2.a().a(new Builder().m17935a("tut_start").m17944b(str).m17952c("pickasong").m17949c((AnalyticsType) headphonesType).m17966e(str2).m17985i(str3).m17986j((AnalyticsType) cameraStatusType));
    }

    public static void m17859a(String str, HeadphonesType headphonesType, String str2, String str3, CameraStatusType cameraStatusType, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("tut_complete").m17944b(str).m17952c("pickasong").m17949c((AnalyticsType) headphonesType).m17966e(str2).m17942b(Boolean.valueOf(z)).m17985i(str3).m17986j((AnalyticsType) cameraStatusType));
    }

    public static void m17858a(String str, HeadphonesType headphonesType, String str2, String str3, CameraStatusType cameraStatusType, long j) {
        EventLogger2.a().a(new Builder().m17935a("tut_cancel").m17944b(str).m17952c("pickasong").m17949c((AnalyticsType) headphonesType).m17966e(str2).m17948c(j).m17985i(str3).m17986j((AnalyticsType) cameraStatusType).m17936a(true));
    }

    public static void m17890b(String str, String str2, String str3, String str4) {
        EventLogger2.a().a(new Builder().m17935a("reg_fail").m17952c(str).m17966e(str2).m17971f(str3).m17976g(str4).m17936a(true));
    }

    public static void m17895c(String str, String str2, String str3, String str4) {
        EventLogger2.a().a(new Builder().m17935a("reg_fail_r105").m17952c(str).m17966e(str2).m17971f(str3).m17976g(str4).m17936a(true));
    }

    public static void m17871a(String str, String str2, Integer num, Integer num2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("reg_fail").m17952c(str).m17966e(str2).m17958d((Number) num).m17965e((Number) num2).m17981h(str3).m17936a(true));
    }

    public static void m17889b(String str, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("perf_video_upload_cancel").m17944b(str).m17966e(str2).m17971f(EventLogger2.c()).m17985i(str3).m17936a(true));
    }

    public static void m17876a(String str, String str2, String str3, Integer num, String str4, String str5) {
        EventLogger2.a().a(new Builder().m17935a("perf_video_upload_fail").m17952c(str2).m17959d(str3).m17944b(str).m17966e(str4).m17971f(EventLogger2.c()).m17965e((Number) num).m17985i(str5).m17936a(true));
    }

    public static void m17894c(String str, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("perf_video_upload_retry").m17944b(str).m17966e(str2).m17971f(EventLogger2.c()).m17985i(str3));
    }

    public static void m17897d(String str, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("perf_video_upload_success").m17944b(str).m17966e(str2).m17971f(EventLogger2.c()).m17985i(str3));
    }

    public static void m17862a(String str, UserPath userPath, AttemptType attemptType, String str2, boolean z, Ensemble ensemble, String str3) {
        EventLogger2.a().a(new Builder().m17935a("perf_audio_upload_start").m17944b(str).m17941b((AnalyticsType) userPath).m17966e(str2).m17971f(m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17989k((AnalyticsType) attemptType));
    }

    public static void m17863a(String str, UserPath userPath, UploadCompletionType uploadCompletionType, String str2, boolean z, Ensemble ensemble, String str3, String str4, Integer num, String str5) {
        EventLogger2.a().a(new Builder().m17935a("perf_audio_upload_complete").m17944b(str).m17941b((AnalyticsType) userPath).m17966e(str2).m17971f(m17833a(z)).m17969f((AnalyticsType) ensemble).m17981h(str4).m17985i(str5).m17988j(str3).m17984i((Number) num).m17989k((AnalyticsType) uploadCompletionType));
    }

    public static void m17878a(String str, String str2, String str3, String str4, int i, String str5, String str6, Long l, Integer num, String str7) {
        EventLogger2.a().a(new Builder().m17935a("subs_buy_feedback").m17944b(str).m17952c(str2).m17959d(str3).m17966e(str4).m17947c(i).m17976g(str5).m17981h(str6).m17975g((Number) l).m17980h((Number) num).m17990k(str7));
    }

    public static void m17856a(String str, int i, String str2) {
        EventLogger2.a().a(new Builder().m17935a("subs_fetch_sku_feedback").m17944b(str).m17947c(i).m17976g(str2));
    }

    public static void m17908o() {
        EventLogger2.a().a(new Builder().m17935a("trial_popup_pgview"));
    }

    public static void m17870a(String str, String str2, RecommendationType recommendationType, RecSysContext recSysContext, String str3) {
        EventLogger2.a().a(new Builder().m17935a("recsys_vw").m17959d(str).m17966e(str2).m17971f(str3).m17969f((AnalyticsType) recommendationType).m17941b((AnalyticsType) recSysContext));
    }

    public static void m17860a(String str, ItemClickType itemClickType, int i, RecSysContext recSysContext, String str2) {
        EventLogger2.a().a(new Builder().m17935a("recsys_clk").m17959d(str).m17939b(i).m17971f(str2).m17969f((AnalyticsType) itemClickType).m17941b((AnalyticsType) recSysContext));
    }

    public static String m17830a(SongbookEntry songbookEntry) {
        if (songbookEntry != null) {
            String d = songbookEntry.mo6290d();
            if (d != null) {
                return d;
            }
        }
        return "-";
    }

    public static String m17881b(PerformanceV2 performanceV2) {
        if (performanceV2 == null || performanceV2.songUid == null || TextUtils.isEmpty(performanceV2.songUid)) {
            return "-";
        }
        return performanceV2.songUid;
    }

    public static String m17882b(SongbookEntry songbookEntry) {
        if (songbookEntry != null && songbookEntry.m18772r()) {
            String c = songbookEntry.mo6289c();
            if (c != null) {
                return c;
            }
        }
        return "-";
    }

    public static String m17891c(PerformanceV2 performanceV2) {
        if (performanceV2 == null || performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement == null || TextUtils.isEmpty(performanceV2.arrangementVersion.arrangement.key)) {
            return "-";
        }
        return performanceV2.arrangementVersion.arrangement.key;
    }

    public static void m17842a(AdType adType, NativeAdPlacementType nativeAdPlacementType, boolean z, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3, String str4, Long l) {
        EventLogger2.a().a(new Builder().m17935a("ad_request").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) nativeAdPlacementType).m17959d(z ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3).m17988j(str4).m17984i((Number) l));
    }

    public static void m17841a(AdType adType, NativeAdPlacementType nativeAdPlacementType, Integer num, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("ad_impression").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) nativeAdPlacementType).m17943b((Number) num).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3));
    }

    public static void m17886b(AdType adType, NativeAdPlacementType nativeAdPlacementType, Integer num, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("ad_clk").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) nativeAdPlacementType).m17943b((Number) num).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3));
    }

    public static void m17840a(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, boolean z, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3, String str4, Long l) {
        EventLogger2.a().a(new Builder().m17935a("ad_request").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17959d(z ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3).m17988j(str4).m17984i((Number) l));
    }

    public static void m17838a(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("ad_impression").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3));
    }

    public static void m17839a(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3, long j) {
        EventLogger2.a().a(new Builder().m17935a("ad_impression_cancel").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3).m17973g(j));
    }

    public static void m17885b(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3, long j) {
        EventLogger2.a().a(new Builder().m17935a("ad_impression_skip").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3).m17973g(j));
    }

    public static void m17884b(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("ad_clk").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3));
    }

    public static void m17893c(AdType adType, FullScreenAdPlacementType fullScreenAdPlacementType, AdMediator adMediator, String str, AdCategory adCategory, String str2, String str3, long j) {
        EventLogger2.a().a(new Builder().m17935a("ad_complete").m17931a((AnalyticsType) adType).m17941b((AnalyticsType) fullScreenAdPlacementType).m17956d((AnalyticsType) adMediator).m17971f(str).m17969f((AnalyticsType) adCategory).m17981h(str2).m17985i(str3).m17973g(j));
    }

    public static void m17837a(AdType adType) {
        EventLogger2.a().a(new Builder().m17935a("ad_reward_clk").m17931a((AnalyticsType) adType));
    }

    public static void m17861a(@NonNull String str, @Nullable PermissionNeverAskAgain permissionNeverAskAgain, @NonNull PermissionAskType permissionAskType, @NonNull PermissionResult permissionResult, @Nullable PermissionErrorReason permissionErrorReason) {
        EventLogger2.a().a(new Builder().m17935a(str).m17931a((AnalyticsType) permissionNeverAskAgain).m17941b((AnalyticsType) permissionAskType).m17949c((AnalyticsType) permissionResult).m17956d((AnalyticsType) permissionErrorReason));
    }

    public static void m17836a(long j, long j2, long j3, long j4, long j5) {
        if (EventLogger2.b) {
            EventLogger2.a().a(new Builder().m17935a("npt_d").m17934a(Long.valueOf(j)).m17930a(j2).m17940b(j3).m17955d(j4).m17962e(j5).m17945b(true).m17937a());
        }
    }

    public static void m17879a(@NonNull String str, boolean z, @NonNull CacheType cacheType) {
        if (EventLogger2.b && !TextUtils.isEmpty(str)) {
            String substring = str.substring(str.lastIndexOf("/") + 1);
            if (!TextUtils.isEmpty(substring)) {
                EventLogger2.a().a(new Builder().m17935a("npt_c").m17944b(substring).m17932a(Boolean.valueOf(z)).m17941b((AnalyticsType) cacheType).m17945b(true).m17937a());
            }
        }
    }
}
