package com.smule.singandroid.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.appboy.models.outgoing.AppboyProperties;
import com.facebook.accountkit.internal.InternalLogger;
import com.facebook.accountkit.ui.LoginFlowState;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.android.gms.common.Scopes;
import com.mopub.common.AdType;
import com.smule.android.audio.AudioDefs.HeadphonesType;
import com.smule.android.audio.AudioDefs.MonitoringMode;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.AnalyticsType;
import com.smule.android.logging.Analytics.AppLaunchErrorType;
import com.smule.android.logging.Analytics.Ensemble;
import com.smule.android.logging.Analytics.FollowingStatus;
import com.smule.android.logging.Analytics.ItemClickType;
import com.smule.android.logging.Analytics.PageType;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.logging.Analytics.PerformanceStatus;
import com.smule.android.logging.Analytics.Share;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.AnalyticsProcessor;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$Event.Builder;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Playlist;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.chat.ChatAnalytics.ChatType;
import com.smule.singandroid.crm.SingAppboy;
import java.util.Locale;

public class SingAnalytics extends Analytics {
    public static final String f25064b = SingAnalytics.class.getSimpleName();
    public static boolean f25065c = false;
    public static boolean f25066d = false;
    private static int f25067e;
    private static long f25068f;
    private static AppLaunchErrorType f25069g = AppLaunchErrorType.NONE;
    private static int f25070h = 4;

    static /* synthetic */ class C50311 {
        static final /* synthetic */ int[] f24939a = new int[LoginFlowState.values().length];

        static {
            try {
                f24939a[LoginFlowState.PHONE_NUMBER_INPUT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f24939a[LoginFlowState.SENDING_CODE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f24939a[LoginFlowState.SENT_CODE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f24939a[LoginFlowState.CODE_INPUT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f24939a[LoginFlowState.VERIFYING_CODE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f24939a[LoginFlowState.VERIFIED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f24939a[LoginFlowState.RESEND.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum AccountKitFlow implements AnalyticsType {
        PHONE("phone"),
        EMAIL("email");
        
        private String f24943c;

        private AccountKitFlow(String str) {
            this.f24943c = str;
        }

        public String mo6235a() {
            return this.f24943c;
        }
    }

    public enum AudioCompletionContext implements AnalyticsType {
        RECORD_EXIT("record_exit"),
        REVIEW_EXIT("review_exit"),
        UPLOAD("upload");
        
        private String f24948d;

        private AudioCompletionContext(String str) {
            this.f24948d = str;
        }

        public String mo6235a() {
            return this.f24948d;
        }
    }

    public enum AudioInitContext implements AnalyticsType {
        START("start"),
        RESTART("restart");
        
        private String f24952c;

        private AudioInitContext(String str) {
            this.f24952c = str;
        }

        public String mo6235a() {
            return this.f24952c;
        }
    }

    public enum AudioSyncContext implements AnalyticsType {
        ATTEMPT("attempt"),
        SAVE("save");
        
        private String f24956c;

        private AudioSyncContext(String str) {
            this.f24956c = str;
        }

        public String mo6235a() {
            return this.f24956c;
        }
    }

    public enum FacebookPermissionPermittedType implements AnalyticsType {
        PERMITTED("permitted"),
        NOT_PERMITTED("not_permitted");
        
        private String f24960c;

        private FacebookPermissionPermittedType(String str) {
            this.f24960c = str;
        }

        public String mo6235a() {
            return this.f24960c;
        }
    }

    public enum FeedNoticeClickType implements AnalyticsType {
        YES("yes"),
        CANCEL("cancel");
        
        private String f24964c;

        private FeedNoticeClickType(String str) {
            this.f24964c = str;
        }

        public String mo6235a() {
            return this.f24964c;
        }
    }

    public enum FeedNoticeType implements AnalyticsType {
        FIND_FB_FRIENDS("FIND_FB_FRIENDS"),
        FIND_FRIENDS("FIND_FRIENDS");
        
        private String f24968c;

        private FeedNoticeType(String str) {
            this.f24968c = str;
        }

        public String mo6235a() {
            return this.f24968c;
        }
    }

    public enum FilterType implements AnalyticsType {
        NONE("none"),
        f24970b(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        
        private String f24972c;

        private FilterType(String str) {
            this.f24972c = str;
        }

        public String mo6235a() {
            return this.f24972c;
        }
    }

    public enum FxVipStatusType implements AnalyticsType {
        VIP("true"),
        NON_VIP(InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        
        private String f24976c;

        private FxVipStatusType(String str) {
            this.f24976c = str;
        }

        public String mo6235a() {
            return this.f24976c;
        }
    }

    public enum InviteType implements AnalyticsType {
        FOLLOWER("follower"),
        FOLLOWING("following");
        
        private String f24980c;

        private InviteType(String str) {
            this.f24980c = str;
        }

        public String mo6235a() {
            return this.f24980c;
        }
    }

    public enum NotificationFilterType implements AnalyticsType {
        ALL("ALL"),
        INVITE("INVITE");
        
        private String f24984c;

        private NotificationFilterType(String str) {
            this.f24984c = str;
        }

        public String mo6235a() {
            return this.f24984c;
        }
    }

    public enum NotificationScreenType implements AnalyticsType {
        EXPANDED("true"),
        INITIAL(InternalLogger.EVENT_PARAM_EXTRAS_FALSE);
        
        private String f24988c;

        private NotificationScreenType(String str) {
            this.f24988c = str;
        }

        public String mo6235a() {
            return this.f24988c;
        }
    }

    public enum ProfilePagevwType implements AnalyticsType {
        MENTION("mention");
        
        private String f24991b;

        private ProfilePagevwType(String str) {
            this.f24991b = str;
        }

        public String mo6235a() {
            return this.f24991b;
        }
    }

    public enum RecClkType implements AnalyticsType {
        START("START"),
        JOIN("JOIN");
        
        private String f24995c;

        private RecClkType(String str) {
            this.f24995c = str;
        }

        public String mo6235a() {
            return this.f24995c;
        }
    }

    public enum RecEnsembleType implements AnalyticsType {
        LOCKED("LOCKED"),
        SOLO("SOLO"),
        DUET("DUET"),
        GROUP("GROUP");
        
        private String f25001e;

        private RecEnsembleType(String str) {
            this.f25001e = str;
        }

        public String mo6235a() {
            return this.f25001e;
        }
    }

    public enum RecType implements AnalyticsType {
        LOCKED("LOCKED"),
        UNLOCKED("UNLOCKED"),
        VIP("VIP");
        
        private String f25006d;

        private RecType(String str) {
            this.f25006d = str;
        }

        public String mo6235a() {
            return this.f25006d;
        }
    }

    public enum ReviewStepsType implements AnalyticsType {
        REVIEW("review"),
        CUSTOMIZE("customize"),
        UPLOAD("upload");
        
        private String f25011d;

        private ReviewStepsType(String str) {
            this.f25011d = str;
        }

        public String mo6235a() {
            return this.f25011d;
        }
    }

    public enum ScreenLoad implements AnalyticsType {
        SONGBOOK("songbook"),
        PROFILE(Scopes.PROFILE),
        NOTIFICATIONS("notifications"),
        FEED("feed"),
        EXPLORE("explore");
        
        private String f25018f;

        private ScreenLoad(String str) {
            this.f25018f = str;
        }

        public String mo6235a() {
            return this.f25018f;
        }
    }

    public enum SearchBarExitContext implements AnalyticsType {
        EXIT("exit"),
        CLEAR(AdType.CLEAR),
        SCROLL("scroll"),
        GO("go"),
        CLICK("click");
        
        private String f25025f;

        private SearchBarExitContext(String str) {
            this.f25025f = str;
        }

        public String mo6235a() {
            return this.f25025f;
        }
    }

    public enum SectionType implements AnalyticsType {
        INVITES("invites"),
        NETWORK("network"),
        ALL("all");
        
        private String f25030d;

        private SectionType(String str) {
            this.f25030d = str;
        }

        public String mo6235a() {
            return this.f25030d;
        }
    }

    public enum ShareModuleType implements AnalyticsType {
        PAGE("page"),
        DIALOG("module");
        
        private String f25034c;

        private ShareModuleType(String str) {
            this.f25034c = str;
        }

        public String mo6235a() {
            return this.f25034c;
        }
    }

    public enum SingFlowPhase implements AnalyticsType {
        RECORD("record"),
        REVIEW("review");
        
        private String f25038c;

        private SingFlowPhase(String str) {
            this.f25038c = str;
        }

        public String mo6235a() {
            return this.f25038c;
        }
    }

    public enum SongDownloadFileType implements AnalyticsType {
        MID("MID"),
        M4A("M4A");
        
        private String f25042c;

        private SongDownloadFileType(String str) {
            this.f25042c = str;
        }

        public String mo6235a() {
            return this.f25042c;
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
        
        private String f25051h;

        private SongbookSortType(String str) {
            this.f25051h = str;
        }

        public String mo6235a() {
            return this.f25051h;
        }
    }

    public enum UserRelationType implements AnalyticsType {
        MINE("mine"),
        OTHER(FacebookRequestErrorClassification.KEY_OTHER);
        
        private String f25055c;

        private UserRelationType(String str) {
            this.f25055c = str;
        }

        public String mo6235a() {
            return this.f25055c;
        }
    }

    public enum VideoExitType implements AnalyticsType {
        CANCEL("cancel"),
        COMPLETE("complete");
        
        private String f25059c;

        private VideoExitType(String str) {
            this.f25059c = str;
        }

        public String mo6235a() {
            return this.f25059c;
        }
    }

    public enum VisualizerType implements AnalyticsType {
        RINGS("rings"),
        WAVES("waves");
        
        private String f25063c;

        private VisualizerType(String str) {
            this.f25063c = str;
        }

        public String mo6235a() {
            return this.f25063c;
        }
    }

    public static synchronized void m26152p() {
        synchronized (SingAnalytics.class) {
            if (!f25065c) {
                double d = AnalyticsProcessor.d("app_create_time");
                double d2 = AnalyticsProcessor.d("app_time") + d;
                AnalyticsProcessor.c("app_time");
                if (f25069g == AppLaunchErrorType.NONE) {
                    EventLogger2.a().a("app_launch_complete", null, null, Double.toString(d2), null, null, null, MagicNetwork.a().r().mo6235a(), true);
                } else {
                    EventLogger2.a().a("app_launch_complete", null, null, Double.toString(d2), f25069g.mo6235a(), Integer.toString(f25067e), Float.toString(Analytics.m17827a(((float) d2) - (((float) f25068f) / 1000.0f), f25070h)), MagicNetwork.a().r().mo6235a(), true);
                }
                f25065c = true;
                Log.b(f25064b, "startup:" + d + " " + d2);
            }
        }
    }

    public static synchronized void m26089a(String str) {
        synchronized (SingAnalytics.class) {
            if (!f25066d) {
                double d = AnalyticsProcessor.d(SongbookFragment.f20306e);
                AnalyticsProcessor.c(SongbookFragment.f20306e);
                EventLogger2.a().a(new Builder().m17935a("songbook_load_complete").m17943b(Double.valueOf(d)).m17952c(StoreManager.m18378a().m18429e() ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
                m26085a(ScreenLoad.SONGBOOK, str, (float) d);
                f25066d = true;
            }
        }
    }

    public static void m26153q() {
        f25067e++;
    }

    public static void m26065a(long j) {
        if (j > 0) {
            f25068f += j;
        }
    }

    public static void m26068a(AppLaunchErrorType appLaunchErrorType) {
        if (f25069g != AppLaunchErrorType.UNREACHABLE) {
            f25069g = appLaunchErrorType;
        }
    }

    public static void m26154r() {
        EventLogger2.a().a("reg_email_clk", null, null, true);
    }

    public static void m26155s() {
        EventLogger2.a().a("reg_phone_clk", null, null, true);
    }

    public static void m26077a(AccountKitFlow accountKitFlow, LoginFlowState loginFlowState) {
        String str;
        switch (C50311.f24939a[loginFlowState.ordinal()]) {
            case 1:
                str = "reg_acctkit_input_phone_number";
                break;
            case 2:
                str = "reg_acctkit_sending_code";
                break;
            case 3:
                str = "reg_acctkit_sent_code";
                break;
            case 4:
                str = "reg_acctkit_input_code";
                break;
            case 5:
                str = "reg_acctkit_verifying_code";
                break;
            case 6:
                str = "reg_acctkit_verified_code";
                break;
            case 7:
                str = "reg_acctkit_resend_code";
                break;
            default:
                str = null;
                break;
        }
        if (str != null) {
            EventLogger2.a().a(new Builder().m17935a(str).m17941b((AnalyticsType) accountKitFlow));
        }
    }

    public static void m26076a(AccountKitFlow accountKitFlow) {
        EventLogger2.a().a(new Builder().m17935a("reg_acctkit_success").m17941b((AnalyticsType) accountKitFlow));
    }

    protected static String m26123b(boolean z) {
        return z ? "cam_on" : "cam_off";
    }

    public static void m26107a(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, String str3, Boolean bool, boolean z2) {
        Analytics.m17866a(str, "songUid is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        EventLogger2.a().a(new Builder().m17935a("perf_customize_resource_upload_pre_perf_create").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26129b(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, String str3, Boolean bool, boolean z2) {
        Analytics.m17866a(str, "songUid is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        EventLogger2.a().a(new Builder().m17935a("perf_customize_save_clk").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26101a(String str, SocialChannel socialChannel, String str2, Ensemble ensemble, InviteType inviteType, String str3, boolean z) {
        Analytics.m17866a(str, "perfKey is required");
        Analytics.m17866a(str2, "songUid is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        Analytics.m17843a((AnalyticsType) inviteType, "inviteType is required");
        Analytics.m17866a(str3, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("perf_invite").m17944b(str).m17949c((AnalyticsType) socialChannel).m17966e(str2).m17969f((AnalyticsType) ensemble).m17974g((AnalyticsType) inviteType).m17985i(str3).m17988j(Analytics.m17831a(Boolean.valueOf(z))));
    }

    public static void m26112a(String str, String str2, Ensemble ensemble, String str3, boolean z) {
        Analytics.m17866a(str, "perfKey is required");
        Analytics.m17866a(str2, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("perf_love").m17944b(str).m17966e(str2).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(Boolean.valueOf(z))));
    }

    public static void m26127b(String str) {
        EventLogger2.a().a(new Builder().m17935a("perf_nowplaying_options_pgview").m17944b(str));
    }

    public static void m26135c(String str) {
        EventLogger2.a().a(new Builder().m17935a("perf_nowplaying_loves_pgview").m17944b(str));
    }

    public static void m26098a(String str, PerformanceStatus performanceStatus, String str2, Ensemble ensemble, String str3, String str4, VideoStatusType videoStatusType) {
        Analytics.m17866a(str, "perfKey is required");
        EventLogger2.a().a(new Builder().m17935a("perf_listen").m17944b(str).m17949c((AnalyticsType) performanceStatus).m17966e(str2).m17969f((AnalyticsType) ensemble).m17985i(str4).m17983i((AnalyticsType) videoStatusType));
    }

    public static void m26095a(String str, HeadphonesType headphonesType, boolean z, Ensemble ensemble, String str2, Boolean bool, boolean z2) {
        EventLogger2.a().a(new Builder().m17935a("song_sing_restart").m17944b(str).m17949c((AnalyticsType) headphonesType).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str2).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26067a(HeadphonesType headphonesType, boolean z, PageType pageType) {
        EventLogger2.a().a(new Builder().m17935a("song_sing_headphone_alert_clk").m17941b(z ? UserPath.ONBOARDING : UserPath.OTHER).m17949c((AnalyticsType) headphonesType).m17988j(z ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE).m17986j((AnalyticsType) pageType));
    }

    public static void m26122a(String str, boolean z, Ensemble ensemble, String str2, boolean z2, PageType pageType) {
        EventLogger2.a().a(new Builder().m17935a("song_sing_headphone_alert").m17941b(z2 ? UserPath.ONBOARDING : UserPath.OTHER).m17944b(str).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str2).m17988j(z2 ? "true" : InternalLogger.EVENT_PARAM_EXTRAS_FALSE).m17986j((AnalyticsType) pageType));
    }

    public static void m26133c(SongbookEntry songbookEntry) {
        m26074a(songbookEntry, null, null);
    }

    public static void m26073a(SongbookEntry songbookEntry, UserPath userPath) {
        m26075a(songbookEntry, null, null, userPath);
    }

    public static void m26074a(SongbookEntry songbookEntry, String str, Number number) {
        m26075a(songbookEntry, str, number, UserPath.OTHER);
    }

    public static void m26075a(SongbookEntry songbookEntry, String str, Number number, UserPath userPath) {
        Analytics.m17855a(userPath, SongbookEntry.m18752b(songbookEntry), songbookEntry.mo6294h(), songbookEntry.m18770p(), str, SongbookEntry.m18749a(songbookEntry), number);
    }

    public static void m26136c(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, String str3, Boolean bool, boolean z2) {
        Analytics.m17866a(str2, "perfKey is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        Analytics.m17866a(str3, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("song_review_continue").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26144d(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, String str3, Boolean bool, boolean z2) {
        Analytics.m17866a(str, "songUid is required");
        Analytics.m17866a(str2, "perfKey is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        Analytics.m17866a(str3, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("song_review_restart").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26116a(String str, String str2, VisualizerType visualizerType) {
        EventLogger2.a().a(new Builder().m17935a("song_opencall_pgview").m17966e(str).m17985i(str2).m17983i((AnalyticsType) visualizerType));
    }

    public static void m26143d(String str) {
        EventLogger2.a().a(new Builder().m17935a("songbook_pgview").m17944b(str));
    }

    public static void m26086a(SongbookSortType songbookSortType, String str) {
        EventLogger2.a().a(new Builder().m17935a("songbook_sort_clk").m17931a((AnalyticsType) songbookSortType).m17981h(str));
    }

    public static void m26137c(String str, String str2) {
        EventLogger2.a().a(new Builder().m17935a("songbook_category_pgview").m17944b(str).m17966e(str2));
    }

    public static void m26156t() {
        EventLogger2.a().a(new Builder().m17935a("notif_pgview"));
    }

    public static void m26082a(NotificationFilterType notificationFilterType) {
        EventLogger2.a().a(new Builder().m17935a("notif_filter_clk").m17931a((AnalyticsType) notificationFilterType));
    }

    public static void m26088a(Object obj, Notification$Type notification$Type, Ensemble ensemble, NotificationScreenType notificationScreenType) {
        EventLogger2.a().a(new Builder().m17935a("notif_clk").m17934a(obj).m17971f(notification$Type.name()).m17969f((AnalyticsType) ensemble).m17974g((AnalyticsType) notificationScreenType));
    }

    public static void m26108a(String str, Notification$Type notification$Type, Ensemble ensemble, NotificationScreenType notificationScreenType) {
        EventLogger2.a().a(new Builder().m17935a("notif_expand_clk").m17944b(str).m17971f(notification$Type.name()).m17969f((AnalyticsType) ensemble).m17974g((AnalyticsType) notificationScreenType));
    }

    public static void m26157u() {
        EventLogger2.a().a(new Builder().m17935a("feed_pgview"));
    }

    public static void m26079a(FeedNoticeType feedNoticeType) {
        EventLogger2.a().a(new Builder().m17935a("notice_feed_pgview").m17963e((AnalyticsType) feedNoticeType));
    }

    public static void m26080a(FeedNoticeType feedNoticeType, FeedNoticeClickType feedNoticeClickType) {
        EventLogger2.a().a(new Builder().m17935a("notice_feed_clk").m17949c((AnalyticsType) feedNoticeClickType).m17963e((AnalyticsType) feedNoticeType));
    }

    public static void m26158v() {
        EventLogger2.a().a("findfriends_fb_pgview");
    }

    public static void m26078a(FeedNoticeClickType feedNoticeClickType, FacebookPermissionPermittedType facebookPermissionPermittedType) {
        EventLogger2.a().a(new Builder().m17935a("findfriends_fb_clk").m17949c((AnalyticsType) feedNoticeClickType).m17983i((AnalyticsType) facebookPermissionPermittedType));
    }

    public static void m26159w() {
        EventLogger2.a().a(new Builder().m17935a("findfriends_pgview"));
    }

    public static void m26093a(String str, long j, String str2, UserPath userPath, long j2, long j3, String str3) {
        EventLogger2.a().a(new Builder().m17935a("song_download_cancel").m17944b(str).m17941b((AnalyticsType) userPath).m17930a(j).m17966e(str2).m17971f(Long.toString(j2)).m17976g(Long.toString(j3)).m17985i(str3).m17936a(true));
    }

    public static void m26092a(String str, long j, String str2, UserPath userPath, long j2, long j3, SongDownloadFileType songDownloadFileType, String str3) {
        EventLogger2.a().a(new Builder().m17935a("song_download_fail").m17944b(str).m17941b((AnalyticsType) userPath).m17930a(j).m17966e(str2).m17971f(Long.toString(j2)).m17976g(Long.toString(j3)).m17974g((AnalyticsType) songDownloadFileType).m17985i(str3).m17936a(true));
    }

    public static void m26128b(String str, long j, String str2, UserPath userPath, long j2, long j3, SongDownloadFileType songDownloadFileType, String str3) {
        EventLogger2.a().a(new Builder().m17935a("song_download_should_fail_sa_r60").m17944b(str).m17941b((AnalyticsType) userPath).m17930a(j).m17966e(str2).m17971f(Long.toString(j2)).m17976g(Long.toString(j3)).m17974g((AnalyticsType) songDownloadFileType).m17985i(str3).m17936a(true));
    }

    public static void m26094a(String str, long j, String str2, boolean z, UserPath userPath, long j2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("song_download_success").m17944b(str).m17941b((AnalyticsType) userPath).m17930a(j).m17966e(str2).m17971f(Analytics.m17833a(z)).m17976g(Long.toString(j2)).m17985i(str3));
    }

    public static void m26106a(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, ReviewStepsType reviewStepsType, String str3, Boolean bool, boolean z2) {
        Analytics.m17866a(str, "songUid is required");
        Analytics.m17866a(str2, "perfKey is required");
        Analytics.m17843a((AnalyticsType) ensemble, "ensembleType is required");
        Analytics.m17866a(str3, "arrangementKey is required");
        EventLogger2.a().a(new Builder().m17935a("song_review_cancel_clk").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Analytics.m17833a(z)).m17969f((AnalyticsType) ensemble).m17974g((AnalyticsType) reviewStepsType).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26102a(String str, UserPath userPath, int i, long j, ReviewStepsType reviewStepsType, String str2, Boolean bool, boolean z) {
        Analytics.m17866a(str, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("song_review_cancel").m17944b(str).m17941b((AnalyticsType) userPath).m17929a(i).m17940b(j).m17974g((AnalyticsType) reviewStepsType).m17985i(str2).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z)).m17936a(true));
    }

    public static void m26148e(String str) {
        EventLogger2.a().a(new Builder().m17935a("store_pgview").m17966e(str));
    }

    public static void m26091a(String str, int i, AudioSyncContext audioSyncContext, HeadphonesType headphonesType) {
        EventLogger2.a().a(new Builder().m17935a("sync_audio_success").m17944b(str).m17941b((AnalyticsType) audioSyncContext).m17929a(i).m17956d((AnalyticsType) headphonesType));
    }

    public static void m26064a(int i, boolean z, int i2, HeadphonesType headphonesType, String str, Float f, Float f2, Integer num, int i3, MonitoringMode monitoringMode, Integer num2) {
        Builder j = new Builder().m17935a("audio_init").m17934a(Integer.valueOf(i)).m17941b(z ? AudioInitContext.RESTART : AudioInitContext.START).m17929a(i2).m17956d((AnalyticsType) headphonesType).m17976g(str).m17967f(i3).m17986j((AnalyticsType) monitoringMode);
        if (f != null) {
            j = j.m17938b(Analytics.m17827a(f.floatValue(), f25070h));
        }
        if (f2 != null) {
            j = j.m17953d(Analytics.m17827a(f2.floatValue(), f25070h));
        }
        if (num != null) {
            j = j.m17975g((Number) num);
        }
        if (num2 != null) {
            j = j.m17987j((Number) num2);
        }
        EventLogger2.a().a(j);
    }

    public static void m26061a(int i, SingFlowPhase singFlowPhase, int i2, HeadphonesType headphonesType, Float f, Float f2, Float f3, Float f4, int i3, Integer num) {
        Builder f5 = new Builder().m17935a("audio_state_change").m17934a(Integer.valueOf(i)).m17941b((AnalyticsType) singFlowPhase).m17929a(i2).m17956d((AnalyticsType) headphonesType).m17967f(i3);
        if (f != null) {
            f5 = f5.m17938b(Analytics.m17827a(f.floatValue(), f25070h));
        }
        if (f2 != null) {
            f5 = f5.m17946c(Analytics.m17827a(f2.floatValue(), f25070h));
        }
        if (f3 != null) {
            f5 = f5.m17953d(Analytics.m17827a(f3.floatValue(), f25070h));
        }
        if (f4 != null) {
            f5 = f5.m17960e(Analytics.m17827a(f4.floatValue(), f25070h));
        }
        if (num != null) {
            f5 = f5.m17984i((Number) num);
        }
        EventLogger2.a().a(f5);
    }

    public static void m26060a(int i, AudioCompletionContext audioCompletionContext, Float f, String str, String str2, String str3, String str4, String str5, String str6, MonitoringMode monitoringMode, int i2) {
        Builder h = new Builder().m17935a("audio_complete").m17934a(Integer.valueOf(i)).m17941b((AnalyticsType) audioCompletionContext).m17971f(str2).m17976g(str3).m17981h(str4).m17985i(str5).m17988j(str6).m17986j((AnalyticsType) monitoringMode).m17977h(i2);
        if (f != null) {
            h = h.m17928a(Analytics.m17827a(f.floatValue(), f25070h));
        }
        if (str != null) {
            h = h.m17959d(str);
        }
        EventLogger2.a().a(h);
    }

    public static void m26109a(String str, GlitchType glitchType, HeadphonesType headphonesType) {
        EventLogger2.a().a(new Builder().m17935a("audio_glitch").m17944b(str).m17949c((AnalyticsType) headphonesType).m17952c(glitchType.getValue()));
    }

    public static void m26145d(String str, String str2) {
        EventLogger2.a().a(new Builder().m17935a("perf_nowplaying_comment_pgview").m17944b(str).m17985i(str2));
    }

    public static void m26149e(String str, String str2) {
        Analytics.m17866a(str, "songUid is required");
        EventLogger2.a().a(new Builder().m17935a("perf_nowplaying_pgview").m17944b(str).m17985i(str2));
    }

    public static void m26087a(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_opencall_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26126b(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_followers_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26134c(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_following_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26142d(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_recordings_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26147e(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_songs_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26150f(UserRelationType userRelationType, int i) {
        EventLogger2.a().a(new Builder().m17935a("profile_favorites_pgview").m17949c((AnalyticsType) userRelationType).m17939b(i));
    }

    public static void m26066a(long j, ProfilePagevwType profilePagevwType) {
        EventLogger2.a().a(new Builder().m17935a("profile_pgview").m17941b((AnalyticsType) profilePagevwType).m17930a(j));
    }

    public static void m26104a(String str, UserPath userPath, HeadphonesType headphonesType, String str2, Ensemble ensemble, int i, String str3, Boolean bool, boolean z) {
        String str4 = "rec_start";
        AppboyProperties appboyProperties = new AppboyProperties();
        appboyProperties.a("ensemble_type", ensemble == null ? null : ensemble.mo6235a());
        SingAppboy.m23096a().m23098a("rec_start", appboyProperties);
        EventLogger2.a().a(new Builder().m17935a("rec_start").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17969f((AnalyticsType) ensemble).m17961e(i).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z)));
    }

    public static void m26103a(String str, UserPath userPath, HeadphonesType headphonesType, String str2, int i, Ensemble ensemble, String str3, Boolean bool, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("rec_cancel").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Integer.toString(i)).m17969f((AnalyticsType) ensemble).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z)));
    }

    public static void m26105a(String str, UserPath userPath, HeadphonesType headphonesType, String str2, boolean z, Ensemble ensemble, int i, String str3, Boolean bool, boolean z2) {
        String str4 = "rec_complete";
        AppboyProperties appboyProperties = new AppboyProperties();
        appboyProperties.a("ensemble_type", ensemble == null ? null : ensemble.mo6235a());
        SingAppboy.m23096a().m23098a("rec_complete", appboyProperties);
        EventLogger2.a().a(new Builder().m17935a("rec_complete").m17944b(str).m17941b((AnalyticsType) userPath).m17949c((AnalyticsType) headphonesType).m17966e(str2).m17971f(Boolean.toString(z)).m17969f((AnalyticsType) ensemble).m17961e(i).m17985i(str3).m17988j(Analytics.m17831a(bool)).m17990k(m26123b(z2)));
    }

    public static void m26118a(String str, String str2, String str3, FxVipStatusType fxVipStatusType, String str4, FxVipStatusType fxVipStatusType2, String str5) {
        EventLogger2.a().a(new Builder().m17935a("rec_review_pgview").m17944b(str2).m17966e(str).m17971f(fxVipStatusType.mo6235a() + "," + str4 + "," + fxVipStatusType2.mo6235a() + "," + str5).m17985i(str3));
    }

    public static void m26119a(String str, String str2, String str3, String str4, String str5) {
        EventLogger2.a().a(new Builder().m17935a("arr_vote").m17944b(str).m17959d(str2).m17966e(str3).m17971f(str4).m17985i(str5));
    }

    public static void m26117a(@Nullable String str, @NonNull String str2, @Nullable Integer num, @Nullable HeadphonesType headphonesType, @Nullable Boolean bool, @Nullable Ensemble ensemble, @Nullable String str3, @Nullable String str4, @Nullable Boolean bool2) {
        String str5;
        Analytics.m17866a(str2, "entryPoint is required");
        Builder d = new Builder().m17935a("avq_vote").m17944b(str).m17952c(str2).m17943b((Number) num).m17956d((AnalyticsType) headphonesType);
        if (bool == null) {
            str5 = null;
        } else {
            str5 = Analytics.m17833a(bool.booleanValue());
        }
        EventLogger2.a().a(d.m17971f(str5).m17969f((AnalyticsType) ensemble).m17981h(str3).m17985i(str4).m17988j(Analytics.m17831a(bool2)));
    }

    public static void m26113a(String str, String str2, PerformanceStatus performanceStatus, Ensemble ensemble, String str3, VideoStatusType videoStatusType, ShareModuleType shareModuleType) {
        Analytics.m17866a(str2, "perfKey is required");
        EventLogger2.a().a(new Builder().m17935a("perf_share_pgview").m17944b(str2).m17966e(str).m17963e((AnalyticsType) performanceStatus).m17969f((AnalyticsType) ensemble).m17985i(str3).m17983i((AnalyticsType) videoStatusType).m17986j((AnalyticsType) shareModuleType));
    }

    public static void m26100a(String str, SocialChannel socialChannel, Share share) {
        m26114a(null, str, socialChannel, Share.BASIC, null, null, null);
    }

    public static void m26114a(String str, String str2, SocialChannel socialChannel, Share share, PerformanceStatus performanceStatus, Ensemble ensemble, VideoStatusType videoStatusType) {
        EventLogger2.a().a(new Builder().m17935a("share_ext_clk").m17944b(str).m17949c((AnalyticsType) socialChannel).m17956d((AnalyticsType) share).m17963e((AnalyticsType) performanceStatus).m17969f((AnalyticsType) ensemble).m17985i(str2).m17983i((AnalyticsType) videoStatusType));
    }

    public static void m26069a(SocialChannel socialChannel) {
        m26099a(null, socialChannel, null, null, null, null);
    }

    public static void m26070a(SocialChannel socialChannel, String str) {
        m26099a(null, socialChannel, null, null, str, null);
    }

    public static void m26099a(String str, SocialChannel socialChannel, PerformanceStatus performanceStatus, Ensemble ensemble, String str2, VideoStatusType videoStatusType) {
        EventLogger2.a().a(new Builder().m17935a("share_ext").m17944b(str).m17949c((AnalyticsType) socialChannel).m17956d(Share.BASIC).m17963e((AnalyticsType) performanceStatus).m17969f((AnalyticsType) ensemble).m17985i(str2).m17983i((AnalyticsType) videoStatusType));
    }

    public static void m26096a(String str, PerformanceStatus performanceStatus, Ensemble ensemble, String str2, VideoStatusType videoStatusType, SocialChannel socialChannel) {
        EventLogger2.a().a(new Builder().m17935a("share_int_clk").m17944b(str).m17956d((AnalyticsType) socialChannel).m17963e((AnalyticsType) performanceStatus).m17969f((AnalyticsType) ensemble).m17985i(str2).m17983i((AnalyticsType) videoStatusType));
    }

    public static void m26097a(String str, PerformanceStatus performanceStatus, Ensemble ensemble, String str2, VideoStatusType videoStatusType, SocialChannel socialChannel, String str3, ChatType chatType, Number number, FollowingStatus followingStatus) {
        EventLogger2.a().a(new Builder().m17935a("share_int").m17944b(str).m17941b((AnalyticsType) chatType).m17959d(str3).m17956d((AnalyticsType) socialChannel).m17963e((AnalyticsType) performanceStatus).m17969f((AnalyticsType) ensemble).m17970f(number).m17985i(str2).m17983i((AnalyticsType) videoStatusType).m17986j((AnalyticsType) followingStatus));
    }

    public static String m26140d(PerformanceV2 performanceV2) {
        if (performanceV2 == null || !performanceV2.r() || performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement.key.isEmpty()) {
            return "-";
        }
        return performanceV2.arrangementVersion.arrangement.key;
    }

    public static String m26141d(SongbookEntry songbookEntry) {
        if (songbookEntry == null || !songbookEntry.m18772r()) {
            return "-";
        }
        return songbookEntry.mo6289c();
    }

    public static PerformanceStatus m26146e(PerformanceV2 performanceV2) {
        if (performanceV2.j()) {
            return PerformanceStatus.EXPIRED;
        }
        if (performanceV2.closed) {
            return PerformanceStatus.CLOSED;
        }
        if (performanceV2.n()) {
            return PerformanceStatus.ACTIVE;
        }
        return PerformanceStatus.NORMAL;
    }

    public static void m26139c(boolean z) {
        EventLogger2.a().a(new Builder().m17935a("reg_isthisyou_clk").m17944b(z ? "yes" : "no"));
    }

    public static void m26160x() {
        EventLogger2.a().a("trial_popup_sub_clk");
    }

    public static void m26161y() {
        EventLogger2.a().a(new Builder().m17935a("onboardtopics_pgview"));
    }

    public static void m26162z() {
        EventLogger2.a().a(new Builder().m17935a("onboardtopics_skip"));
    }

    public static void m26063a(int i, String str, String str2) {
        EventLogger2.a().a(new Builder().m17935a("onboardtopics_next").m17929a(i).m17966e(str).m17971f(str2));
    }

    public static void m26056A() {
        EventLogger2.a().a(new Builder().m17935a("onboardtopics_more"));
    }

    public static void m26115a(String str, String str2, FxVipStatusType fxVipStatusType, String str3, String str4) {
        EventLogger2.a().a(new Builder().m17935a("pre_rec_confirm_clk").m17944b(str).m17966e(str2).m17971f(fxVipStatusType.mo6235a() + "," + str3).m17985i(str4));
    }

    public static void m26111a(String str, VideoExitType videoExitType, int i, int i2) {
        EventLogger2.a().a(new Builder().m17935a("video_join_slow_stream_count").m17944b(str).m17956d((AnalyticsType) videoExitType).m17947c(i).m17954d(i2));
    }

    public static void m26081a(FilterType filterType, SectionType sectionType) {
        EventLogger2.a().a(new Builder().m17935a("song_opencall_filter_join_clk").m17931a((AnalyticsType) filterType).m17941b((AnalyticsType) sectionType));
    }

    public static void m26059a(int i, SongbookEntry songbookEntry) {
        m26124b(i, songbookEntry.mo6290d(), SongbookEntry.m18749a(songbookEntry));
    }

    public static void m26124b(int i, String str, String str2) {
        if (str == null) {
            str = "-";
        }
        if (str2 == null) {
            str2 = "-";
        }
        EventLogger2.a().a(new Builder().m17935a("tut_clk").m17929a(i).m17966e(str).m17985i(str2));
    }

    public static void m26090a(String str, float f, float f2, int i, float f3, int i2) {
        EventLogger2.a().a(new Builder().m17935a("video_pipeline_stats").m17944b(str).m17928a(Analytics.m17827a(f, 2)).m17971f(EventLogger2.c()).m17946c(Analytics.m17827a(f2, 2)).m17961e(i).m17960e(Analytics.m17827a(f3, 2)).m17967f(i2));
    }

    public static void m26058a(int i, int i2, int i3, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("video_review_playback_stats").m17939b(i).m17947c(i2).m17954d(i3).m17981h(z ? "LOCAL" : "STREAM"));
    }

    public static void m26120a(String str, String str2, String str3, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("pre_rec_pgview").m17944b(str).m17966e(str2).m17985i(str3).m17964e(Boolean.valueOf(z)));
    }

    public static void m26084a(RecType recType, String str, String str2) {
        EventLogger2.a().a(new Builder().m17935a("rec_type_pgview").m17941b((AnalyticsType) recType).m17966e(str).m17985i(str2));
    }

    public static void m26083a(RecType recType, String str, RecClkType recClkType, RecEnsembleType recEnsembleType, String str2) {
        EventLogger2.a().a(new Builder().m17935a("rec_type_clk").m17941b((AnalyticsType) recType).m17966e(str).m17963e((AnalyticsType) recClkType).m17969f((AnalyticsType) recEnsembleType).m17985i(str2));
    }

    public static void m26110a(String str, RecEnsembleType recEnsembleType, String str2) {
        EventLogger2.a().a(new Builder().m17935a("rec_part_select_pgview").m17966e(str).m17969f((AnalyticsType) recEnsembleType).m17985i(str2));
    }

    public static void m26062a(int i, String str, RecEnsembleType recEnsembleType, String str2) {
        EventLogger2.a().a(new Builder().m17935a("rec_part_select_clk").m17929a(i).m17966e(str).m17969f((AnalyticsType) recEnsembleType).m17985i(str2));
    }

    public static void m26151f(String str) {
        EventLogger2.a().a(new Builder().m17935a("banner_clk").m17966e(str));
    }

    public static void m26125b(long j) {
        EventLogger2.a().a(new Builder().m17935a("promo_pgview").m17940b(j));
    }

    public static void m26132c(long j) {
        EventLogger2.a().a(new Builder().m17935a("promo_leaderboard_pgview").m17940b(j));
    }

    public static void m26131b(String str, String str2, String str3, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("bookmark_add").m17944b(str).m17966e(str2).m17985i(str3).m17983i(z ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO));
    }

    public static void m26138c(String str, String str2, String str3, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("bookmark_remove").m17944b(str).m17966e(str2).m17985i(str3).m17983i(z ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO));
    }

    public static void m26121a(String str, String str2, String str3, boolean z, ItemClickType itemClickType) {
        EventLogger2.a().a(new Builder().m17935a("bookmark_clk").m17944b(str).m17949c((AnalyticsType) itemClickType).m17966e(str2).m17985i(str3).m17983i(z ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO));
    }

    public static void m26071a(Playlist playlist) {
        EventLogger2.a().a(new Builder().m17935a("explore_vw").m17944b(playlist.name.toLowerCase(Locale.US)).m17940b(playlist.id));
    }

    public static void m26072a(Playlist playlist, int i, String str, String str2, String str3, boolean z) {
        EventLogger2.a().a(new Builder().m17935a("explore_clk").m17944b(playlist.name.toLowerCase(Locale.US)).m17933a(Long.valueOf(playlist.id)).m17929a(i).m17966e(str).m17971f(str2).m17985i(str3).m17983i(z ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO));
    }

    public static void m26085a(ScreenLoad screenLoad, String str, float f) {
        EventLogger2.a().a(new Builder().m17935a("screen_loaded").m17931a((AnalyticsType) screenLoad).m17952c(str).m17943b(Float.valueOf(Analytics.m17827a(f, f25070h))));
    }

    public static void m26130b(String str, String str2, String str3, PaywallType paywallType) {
        AppboyProperties appboyProperties = new AppboyProperties();
        appboyProperties.a("page", str3);
        SingAppboy.m23096a().m23098a("purchase_pgview", appboyProperties);
        Analytics.m17875a(str, str2, str3, paywallType);
    }

    public static void m26057B() {
        EventLogger2.a().a(new Builder().m17935a("vip_benefits_pgview"));
    }
}
