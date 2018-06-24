package com.smule.singandroid.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.text.Html;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.messenger.MessengerUtils;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.ShareActivityDialog;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShareUtils {
    public static final String f24937a = ShareUtils.class.getName();
    private static final int[][] f24938b = new int[][]{new int[]{C1947R.string.share_mine_plain, C1947R.string.share_mine_tweet, C1947R.string.share_mine_tweet_short, C1947R.string.share_mine_subject, C1947R.string.share_mine_email}, new int[]{C1947R.string.share_other_plain, C1947R.string.share_other_tweet, C1947R.string.share_mine_tweet_short, C1947R.string.share_other_subject, C1947R.string.share_other_email}, new int[]{C1947R.string.share_join_mine_plain, C1947R.string.share_join_mine_tweet, C1947R.string.share_mine_tweet_short, C1947R.string.share_join_mine_subject, C1947R.string.share_join_mine_email}, new int[]{C1947R.string.share_join_other_plain, C1947R.string.share_join_other_tweet, C1947R.string.share_mine_tweet_short, C1947R.string.share_join_other_subject, C1947R.string.share_join_other_email}};

    private static int m25996a(PerformanceV2 performanceV2) {
        int i = performanceV2.l() ? 0 : 1;
        return (performanceV2.seed && performanceV2.n()) ? i + 2 : i;
    }

    public static String m26003a(Context context, PerformanceV2 performanceV2, SongV2 songV2) {
        return m26004a(context, performanceV2, songV2, m25996a(performanceV2), 0);
    }

    public static String m26011b(Context context, PerformanceV2 performanceV2, SongV2 songV2) {
        String a = m26004a(context, performanceV2, songV2, m25996a(performanceV2), 1);
        return a.length() <= 140 ? a : m26004a(context, performanceV2, songV2, m25996a(performanceV2), 2);
    }

    public static String m26000a(Context context, ArrangementVersionLite arrangementVersionLite) {
        return m26001a(context, arrangementVersionLite, 1);
    }

    public static String m26016c(Context context, PerformanceV2 performanceV2, SongV2 songV2) {
        return m26004a(context, performanceV2, songV2, m25996a(performanceV2), 3);
    }

    public static String m26021d(Context context, PerformanceV2 performanceV2, SongV2 songV2) {
        return m26004a(context, performanceV2, songV2, m25996a(performanceV2), 4);
    }

    public static String m26010b(Context context, ArrangementVersionLite arrangementVersionLite) {
        return m26001a(context, arrangementVersionLite, 0);
    }

    public static String m26015c(Context context, ArrangementVersionLite arrangementVersionLite) {
        return m26001a(context, arrangementVersionLite, 3);
    }

    public static String m26020d(Context context, ArrangementVersionLite arrangementVersionLite) {
        return m26001a(context, arrangementVersionLite, 4);
    }

    public static String m25999a(Context context) {
        return context.getString(C1947R.string.share_invite_plain, new Object[]{UserManager.a().i(), context.getString(C1947R.string.share_invite_url)});
    }

    public static String m26009b(Context context) {
        return context.getString(C1947R.string.share_invite_tweet, new Object[]{UserManager.a().i(), context.getString(C1947R.string.share_invite_url)});
    }

    public static String m26014c(Context context) {
        return context.getString(C1947R.string.share_invite_subject);
    }

    public static String m26019d(Context context) {
        return context.getString(C1947R.string.share_invite_body, new Object[]{UserManager.a().i(), context.getString(C1947R.string.share_invite_url)});
    }

    public static void m26024e(Context context, PerformanceV2 performanceV2, SongV2 songV2) {
        if (context != null && performanceV2 != null) {
            int a = m25996a(performanceV2);
            String a2 = m26002a(context, performanceV2);
            String g = m26027g(context);
            List arrayList = new ArrayList(4);
            for (int i : f24938b[a]) {
                String str;
                Object[] objArr = new Object[5];
                objArr[0] = performanceV2.title;
                if (songV2 == null || songV2.artistTwitter == null) {
                    str = "";
                } else {
                    str = "@" + songV2.artistTwitter;
                }
                objArr[1] = str;
                objArr[2] = performanceV2.u();
                objArr[3] = a2;
                objArr[4] = g;
                arrayList.add(context.getString(i, objArr).replace("  ", " "));
            }
            m26006a(context, arrayList);
        }
    }

    public static void m26023e(Context context, ArrangementVersionLite arrangementVersionLite) {
        if (context != null && arrangementVersionLite != null) {
            List arrayList = new ArrayList(4);
            for (int i = 0; i < 4; i++) {
                arrayList.add(m26001a(context, arrangementVersionLite, i));
            }
            m26006a(context, arrayList);
        }
    }

    public static void m26022e(Context context) {
        List arrayList = new ArrayList(4);
        arrayList.add(m25999a(context));
        arrayList.add(m26009b(context));
        arrayList.add(m26009b(context));
        arrayList.add(m26014c(context));
        arrayList.add(m26019d(context));
        m26006a(context, arrayList);
    }

    private static void m26006a(Context context, List<String> list) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            Log.e(f24937a, "Could not access package manager to share");
            return;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        if (list.size() > 4) {
            intent.putExtra("android.intent.extra.TEXT", Html.fromHtml((String) list.get(4)));
        }
        if (list.size() > 3) {
            intent.putExtra("android.intent.extra.SUBJECT", (String) list.get(3));
        }
        intent.setType("message/rfc822");
        Intent createChooser = Intent.createChooser(intent, context.getString(C1947R.string.performance_share_using));
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Set hashSet = new HashSet();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            hashSet.add(resolveInfo.activityInfo.packageName);
        }
        new Intent("android.intent.action.VIEW").setData(Uri.parse("http://twitter.com/Smule/status/0"));
        queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        Set hashSet2 = new HashSet(queryIntentActivities.size());
        for (ResolveInfo resolveInfo2 : queryIntentActivities) {
            hashSet2.add(resolveInfo2.activityInfo.packageName);
        }
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.TEXT", (String) list.get(0));
        intent2.setType("text/plain");
        Intent intent3 = new Intent("android.intent.action.SEND");
        intent2.putExtra("android.intent.extra.TEXT", (String) list.get(1));
        intent2.setType("text/plain");
        queryIntentActivities = packageManager.queryIntentActivities(intent2, 0);
        List arrayList = new ArrayList();
        for (ResolveInfo resolveInfo22 : queryIntentActivities) {
            if (resolveInfo22.activityInfo == null) {
                Log.e(f24937a, "Could not access ResolveInfo.activityInfo to share");
            } else {
                String str = resolveInfo22.activityInfo.packageName;
                if (!hashSet.contains(str)) {
                    if (hashSet2.contains(str)) {
                        intent = (Intent) intent3.clone();
                    } else {
                        intent = (Intent) intent2.clone();
                    }
                    intent.setComponent(new ComponentName(str, resolveInfo22.activityInfo.name));
                    arrayList.add(new LabeledIntent(intent, str, resolveInfo22.loadLabel(packageManager), resolveInfo22.icon));
                    hashSet.add(str);
                }
            }
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
        context.startActivity(createChooser);
    }

    public static boolean m26007a() {
        PackageManager d = m26018d();
        return (d == null || d.getLaunchIntentForPackage("com.google.android.youtube") == null) ? false : true;
    }

    public static Intent m26025f(Context context) {
        if (!m26007a()) {
            return null;
        }
        Parcelable uriForFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", new File(context.getExternalFilesDir(null).toString() + "/" + "sing_video" + "/" + AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO));
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("video/*");
            intent.putExtra("android.intent.extra.SUBJECT", context.getResources().getString(C1947R.string.share_youtube_message));
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.setPackage("com.google.android.youtube");
            return intent;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean m26012b() {
        PackageManager d = m26018d();
        return (d == null || d.getLaunchIntentForPackage(MessengerUtils.PACKAGE_NAME) == null) ? false : true;
    }

    public static Intent m25997a(String str) {
        if (m26018d() == null || !m26012b()) {
            return null;
        }
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setPackage(MessengerUtils.PACKAGE_NAME);
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.TEXT", str);
            return intent;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean m26017c() {
        PackageManager d = m26018d();
        return (d == null || d.getLaunchIntentForPackage("jp.naver.line.android") == null) ? false : true;
    }

    public static Intent m26008b(String str) {
        if (m26018d() == null || !m26017c()) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setPackage("jp.naver.line.android");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", str);
        return intent;
    }

    public static Intent m26013c(String str) {
        Uri parse = Uri.parse("sms:");
        PackageManager d = m26018d();
        if (d == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("text/plain");
        intent.putExtra("sms_body", str);
        intent.setData(parse);
        List queryIntentActivities = d.queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return null;
        }
        ActivityInfo activityInfo = ((ResolveInfo) queryIntentActivities.get(0)).activityInfo;
        intent.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
        return intent;
    }

    public static Intent m25998a(String str, String str2) {
        PackageManager d = m26018d();
        if (d == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse("mailto:test@smule.com"));
        List<ResolveInfo> queryIntentActivities = d.queryIntentActivities(intent, 0);
        Set hashSet = new HashSet(queryIntentActivities.size());
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            ResolveInfo resolveInfo2;
            hashSet.add(resolveInfo2.activityInfo.packageName);
        }
        Intent intent2 = new Intent("android.intent.action.SEND");
        intent2.setType("message/rfc822");
        intent2.putExtra("android.intent.extra.SUBJECT", str);
        intent2.putExtra("android.intent.extra.TEXT", Html.fromHtml(str2));
        queryIntentActivities = d.queryIntentActivities(intent2, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return null;
        }
        ResolveInfo resolveInfo3 = null;
        for (ResolveInfo resolveInfo22 : queryIntentActivities) {
            if (hashSet.contains(resolveInfo22.activityInfo.packageName)) {
                if (resolveInfo22.isDefault) {
                    ActivityInfo activityInfo = resolveInfo22.activityInfo;
                    intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
                    return intent2;
                }
                if (resolveInfo3 != null) {
                    if (resolveInfo22.match > resolveInfo3.match) {
                    }
                }
                resolveInfo3 = resolveInfo22;
            }
            resolveInfo22 = resolveInfo3;
            resolveInfo3 = resolveInfo22;
        }
        if (resolveInfo3 == null) {
            return null;
        }
        activityInfo = resolveInfo3.activityInfo;
        intent2.setComponent(new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name));
        return intent2;
    }

    private static PackageManager m26018d() {
        return SingApplication.f().getPackageManager();
    }

    public static void m26005a(Activity activity, PerformanceV2 performanceV2) {
        activity.startActivityForResult(ShareActivityDialog.m23688a((Context) activity, performanceV2), 42405);
    }

    public static void m26026f(Context context, ArrangementVersionLite arrangementVersionLite) {
        context.startActivity(ShareActivityDialog.m23687a(context, arrangementVersionLite));
    }

    public static String m26004a(Context context, PerformanceV2 performanceV2, SongV2 songV2, int i, int i2) {
        if (context == null || performanceV2 == null) {
            return null;
        }
        String str = i2 == 1 ? (songV2 == null || songV2.artistTwitter == null) ? "" : "@" + songV2.artistTwitter : (songV2 == null || songV2.artist == null) ? "" : songV2.artist;
        if (i2 == 1) {
            String str2 = "https://t.co/OtTfFsSeNtEtTf";
            if (context.getString(f24938b[i][i2], new Object[]{performanceV2.title, str, "https://t.co/OtTfFsSeNtEtTf", "https://t.co/OtTfFsSeNtEtTf", "https://t.co/OtTfFsSeNtEtTf"}).length() > 140) {
                i2 = 2;
            }
        }
        return context.getString(f24938b[i][i2], new Object[]{performanceV2.title, str, performanceV2.u(), m26002a(context, performanceV2), m26027g(context)}).replace("  ", " ");
    }

    public static String m26001a(Context context, ArrangementVersionLite arrangementVersionLite, int i) {
        if (context == null || arrangementVersionLite == null) {
            return null;
        }
        switch (i) {
            case 0:
                if (arrangementVersionLite.artist == null || arrangementVersionLite.artist.isEmpty()) {
                    return context.getString(C1947R.string.share_arrangement_plain_sing_without_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.b()});
                }
                return context.getString(C1947R.string.share_arrangement_plain_sing_with_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.artist, arrangementVersionLite.b()});
            case 1:
                if (arrangementVersionLite.artist == null || arrangementVersionLite.artist.isEmpty()) {
                    return context.getString(C1947R.string.share_arrangement_plain_sing_without_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.b()});
                }
                return context.getString(C1947R.string.share_arrangement_plain_sing_with_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.artist, arrangementVersionLite.b()});
            case 3:
                return context.getString(C1947R.string.share_arrangement_email_subject, new Object[]{arrangementVersionLite.c()});
            case 4:
                if (arrangementVersionLite.artist == null || arrangementVersionLite.artist.isEmpty()) {
                    return context.getString(C1947R.string.share_arrangement_email_body_without_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.b(), m26028g(context, arrangementVersionLite), m26027g(context)});
                }
                return context.getString(C1947R.string.share_arrangement_email_body_with_artist, new Object[]{arrangementVersionLite.c(), arrangementVersionLite.artist, arrangementVersionLite.b(), m26028g(context, arrangementVersionLite), m26027g(context)});
            default:
                return "";
        }
    }

    private static String m26002a(Context context, PerformanceV2 performanceV2) {
        return context.getString(C1947R.string.performance_share_uts_download, new Object[]{performanceV2.performanceKey});
    }

    private static String m26028g(Context context, ArrangementVersionLite arrangementVersionLite) {
        return context.getString(C1947R.string.performance_share_uts_download, new Object[]{arrangementVersionLite.key});
    }

    private static String m26027g(Context context) {
        String i = UserManager.a().i();
        if (i == null || i.isEmpty()) {
            MagicCrittercism.a(new Exception("User handle was empty"));
            return context.getString(C1947R.string.smule_web_url_simple);
        }
        return context.getString(C1947R.string.smule_profile_url, new Object[]{URLEncoder.encode(i)});
    }
}
