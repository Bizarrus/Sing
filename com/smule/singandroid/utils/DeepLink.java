package com.smule.singandroid.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.Scopes;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.PromotionManager;
import com.smule.android.network.managers.PromotionManager.PromotionCallback;
import com.smule.android.network.managers.PromotionManager.PromotionResponse;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MagicPreferences;
import com.smule.singandroid.SingApplication;
import java.util.HashMap;
import java.util.Map;

public class DeepLink {
    public static final String f24738a = DeepLink.class.getName();
    private static final UriMatcher f24739f = new UriMatcher(-1);
    public final Uri f24740b;
    public final Hosts f24741c;
    public final String f24742d;
    public final String f24743e;

    static /* synthetic */ class C49912 {
        static final /* synthetic */ int[] f24684a = new int[Hosts.values().length];

        static {
            try {
                f24684a[Hosts.Notifications.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f24684a[Hosts.PerformJoin.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f24684a[Hosts.Play.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f24684a[Hosts.ProfileOther.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f24684a[Hosts.ChatGroup.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f24684a[Hosts.ChatPeer.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public enum Hosts {
        Songbook("songbook", ""),
        SongbookSection("songbook", "*"),
        ProfileMy(Scopes.PROFILE, ""),
        ProfileMyExplicit(Scopes.PROFILE, "my"),
        ProfileOther(Scopes.PROFILE, "user/*"),
        OpenCallSeeds(Scopes.PROFILE, "seed/*"),
        Newsfeed("newsfeed", ""),
        Perfs("perfs", ""),
        FindFriends("findfriends", ""),
        SingSong("sing", "song/*"),
        SingSeed("sing", "seed/*"),
        SingArrangement("sing", "arrangement/*"),
        PerformSongGroupSelect("perform", "song/group/select/*"),
        PerformSongGroupPart0("perform", "song/group/part0/*"),
        PerformSongGroupPart1("perform", "song/group/part1/*"),
        PerformSongGroupPart2("perform", "song/group/part2/*"),
        PerformSongGroupPart3("perform", "song/group/part3/*"),
        PerformSongGroupPart4("perform", "song/group/part4/*"),
        PerformSongDuetSelect("perform", "song/duet/select/*"),
        PerformSongDuetPart0("perform", "song/duet/part0/*"),
        PerformSongDuetPart1("perform", "song/duet/part1/*"),
        PerformSongDuetPart2("perform", "song/duet/part2/*"),
        PerformSongGroup("perform", "song/group/*"),
        PerformSongDuet("perform", "song/duet/*"),
        PerformSongSolo("perform", "song/solo/*"),
        PerformSong("perform", "song/*"),
        PerformJoin("perform", "join/*"),
        PerformArrangementGroupSelect("perform", "arrangement/group/select/*"),
        PerformArrangementGroupPart0("perform", "arrangement/group/part0/*"),
        PerformArrangementGroupPart1("perform", "arrangement/group/part1/*"),
        PerformArrangementGroupPart2("perform", "arrangement/group/part2/*"),
        PerformArrangementGroupPart3("perform", "arrangement/group/part3/*"),
        PerformArrangementGroupPart4("perform", "arrangement/group/part4/*"),
        PerformArrangementDuetSelect("perform", "arrangement/duet/select/*"),
        PerformArrangementDuetPart0("perform", "arrangement/duet/part0/*"),
        PerformArrangementDuetPart1("perform", "arrangement/duet/part1/*"),
        PerformArrangementDuetPart2("perform", "arrangement/duet/part2/*"),
        PerformArrangementGroup("perform", "arrangement/group/*"),
        PerformArrangementDuet("perform", "arrangement/duet/*"),
        PerformArrangementSolo("perform", "arrangement/solo/*"),
        PerformArrangement("perform", "arrangement/*"),
        OpenCall("perform", "opencall/*"),
        Solo("solo", ""),
        Play("play", "*"),
        Subscription("subscription", ""),
        Purchase("purchase", "*"),
        Offers("offers", ""),
        OffersSpecific("offers", "*"),
        f24707W("settings", ""),
        Privacy(ShareConstants.WEB_DIALOG_PARAM_PRIVACY, ""),
        Terms("terms", ""),
        Invitations("invitations", ""),
        Notifications("notifications", ""),
        MessageCenter("messages", ""),
        ChatPeer("messages", "peer/*"),
        ChatGroup("messages", "group/*"),
        Twitter("twitter", ""),
        AppIndexingSingSong("www.smule.com", "song/*"),
        LocaleOverride("debug", "locale/*"),
        Promo("promo", "*"),
        ai("leaderboard", "*"),
        Onboarding("onboarding", "song/*");
        
        private static final Map<Integer, Hosts> an = null;
        private static final Map<String, Hosts> ao = null;
        public final String ak;
        public final String al;
        public final int am;

        static {
            an = new HashMap();
            ao = new HashMap();
            Hosts[] values = values();
            int length = values.length;
            int i;
            while (i < length) {
                Hosts hosts = values[i];
                an.put(Integer.valueOf(hosts.am), hosts);
                if (hosts.al.isEmpty()) {
                    ao.put(hosts.ak, hosts);
                }
                i++;
            }
        }

        private Hosts(String str, String str2) {
            this.ak = str;
            this.al = str2;
            this.am = ordinal();
        }

        static Hosts m25818a(int i) {
            return (Hosts) an.get(Integer.valueOf(i));
        }

        static Hosts m25819a(String str) {
            return (Hosts) ao.get(str);
        }

        public String toString() {
            return this.ak + this.al;
        }
    }

    class InvalidDeepLinkException extends IllegalArgumentException {
        final /* synthetic */ DeepLink f24737a;

        public InvalidDeepLinkException(DeepLink deepLink, String str) {
            this.f24737a = deepLink;
            super(str);
        }
    }

    private static class NullUriException extends Exception {
        private NullUriException() {
        }
    }

    static {
        for (Hosts hosts : Hosts.values()) {
            f24739f.addURI(hosts.ak, hosts.al, hosts.am);
        }
    }

    public static Uri m25820a(Hosts hosts, String str) {
        String str2;
        String str3 = "smulesing://" + hosts.ak;
        if (hosts.al.isEmpty()) {
            str2 = str3;
        } else {
            str2 = hosts.al;
            if (str != null) {
                str2 = str2.replaceAll("\\*$", str);
            }
            str2 = str3 + "/" + str2;
        }
        return Uri.parse(str2);
    }

    public static boolean m25822a(Uri uri) {
        if (uri != null && Hosts.m25818a(f24739f.match(uri)) == Hosts.ChatPeer) {
            return true;
        }
        return false;
    }

    public static boolean m25824b(Uri uri) {
        if (uri != null && Hosts.m25818a(f24739f.match(uri)) == Hosts.ChatGroup) {
            return true;
        }
        return false;
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public static Long m25821a(final Context context, Uri uri) {
        Long valueOf = Long.valueOf(-1);
        String queryParameter = uri.getQueryParameter("promo_id");
        if (queryParameter == null) {
            return valueOf;
        }
        try {
            valueOf = Long.valueOf(Long.parseLong(queryParameter));
            PromotionManager.m18256a().m18261a(valueOf.longValue(), new PromotionCallback() {
                public void handleResponse(PromotionResponse promotionResponse) {
                    if (promotionResponse.a()) {
                        MagicPreferences.m20303a(context, "LAST_PROMOTION_HASHTAG_PAIR", valueOf + "," + promotionResponse.hashtag);
                    }
                }
            });
            return valueOf;
        } catch (NumberFormatException e) {
            Log.e(f24738a, "Trying to deep link with a non-number promoId: " + queryParameter);
            Toaster.a(context, C1947R.string.promo_inactive);
            return valueOf;
        }
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public static boolean m25823b(Context context, Uri uri) {
        boolean z = true;
        if (!SingApplication.n()) {
            return false;
        }
        String queryParameter = uri.getQueryParameter(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        if (queryParameter != null) {
            try {
                if (Integer.parseInt(queryParameter) != 1) {
                    z = false;
                }
            } catch (NumberFormatException e) {
                Log.e(f24738a, "Trying to deep link with an invalid video option: " + queryParameter);
            }
        }
        return z;
    }

    public DeepLink(Uri uri) {
        Hosts hosts = null;
        this.f24740b = uri;
        this.f24743e = uri.getScheme();
        if ("market".equals(this.f24743e)) {
            this.f24741c = null;
            this.f24742d = null;
            return;
        }
        Hosts a = Hosts.m25818a(f24739f.match(uri));
        if (a != null) {
            hosts = a;
        } else if (uri.getPath() == null || uri.getPath().isEmpty()) {
            a = Hosts.m25819a(uri.getHost());
            if (a == null) {
                a = null;
            }
            hosts = a;
        }
        if (hosts == null) {
            this.f24741c = Hosts.Songbook;
            Throwable invalidDeepLinkException = new InvalidDeepLinkException(this, "Failed to match deep link: " + uri);
            invalidDeepLinkException.fillInStackTrace();
            MagicCrittercism.a(invalidDeepLinkException);
        } else {
            this.f24741c = hosts;
        }
        this.f24742d = uri.getLastPathSegment();
    }

    public String toString() {
        return "{ link:" + this.f24740b + ", target:" + this.f24741c + ", parameter:" + this.f24742d + " }";
    }
}
