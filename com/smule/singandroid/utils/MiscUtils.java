package com.smule.singandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Pair;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.internal.AnalyticsEvents;
import com.foound.widget.AmazingListView;
import com.millennialmedia.InterstitialAd.InterstitialErrorStatus;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.twitter.MagicTwitter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.WebViewActivity;
import java.lang.reflect.Field;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import twitter4j.HttpResponseCode;

public class MiscUtils {
    static DecimalFormat f24781a = new DecimalFormat("@@@");
    public static float f24782b = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    public static float f24783c = 0.0f;
    public static float f24784d = 0.5f;
    private static final String f24785e = MiscUtils.class.getSimpleName();
    private static final Map<Integer, Pair<String, String>> f24786f = new HashMap();

    static class C49962 implements Comparator<Entry<K, V>> {
        C49962() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m25874a((Entry) obj, (Entry) obj2);
        }

        public int m25874a(Entry<K, V> entry, Entry<K, V> entry2) {
            return ((Comparable) entry.getValue()).compareTo(entry2.getValue());
        }
    }

    public static class TimeStat {
        public long f24778a;
        public long f24779b;
        public long f24780c;

        public TimeStat() {
            m25875a();
        }

        public TimeStat(TimeStat timeStat) {
            this.f24778a = timeStat.f24778a;
            this.f24779b = timeStat.f24779b;
            this.f24780c = timeStat.f24780c;
        }

        public void m25875a() {
            this.f24778a = 0;
            this.f24779b = 0;
            this.f24780c = 0;
        }

        public void m25876a(String str) {
            Log.b(str, "    mStartTime:" + this.f24778a);
            Log.b(str, "    mEndTime:" + this.f24779b);
            Log.b(str, "    mDuration:" + this.f24780c);
        }
    }

    static {
        f24781a.setRoundingMode(RoundingMode.FLOOR);
        f24786f.put(Integer.valueOf(10), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_generic), SingApplication.g().getString(C1947R.string.removed_content_body_generic)));
        f24786f.put(Integer.valueOf(11), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_generic), SingApplication.g().getString(C1947R.string.removed_content_body_generic)));
        f24786f.put(Integer.valueOf(12), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_generic), SingApplication.g().getString(C1947R.string.removed_content_body_generic)));
        f24786f.put(Integer.valueOf(200), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_deleted_recording_by_author)));
        f24786f.put(Integer.valueOf(InterstitialErrorStatus.EXPIRED), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_arrangement), SingApplication.g().getString(C1947R.string.removed_content_body_deleted_arrangement_by_author)));
        f24786f.put(Integer.valueOf(21), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_deleted_recording_by_author)));
        f24786f.put(Integer.valueOf(22), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_cascade_deleted_arrangement_by_author)));
        f24786f.put(Integer.valueOf(30), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_deleted_recording_by_joiner)));
        f24786f.put(Integer.valueOf(31), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_cascade_deleted_recording_by_joiner)));
        f24786f.put(Integer.valueOf(32), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_generic), SingApplication.g().getString(C1947R.string.removed_content_body_generic)));
        f24786f.put(Integer.valueOf(HttpResponseCode.BAD_REQUEST), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_disabled_recording), MessageFormat.format(SingApplication.g().getString(C1947R.string.removed_content_body_disabled_recording), new Object[]{SingApplication.g().getString(C1947R.string.performance_copyright_violation_email)})));
        f24786f.put(Integer.valueOf(HttpResponseCode.UNAUTHORIZED), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_disabled_arrangement), MessageFormat.format(SingApplication.g().getString(C1947R.string.removed_content_body_disabled_arrangement), new Object[]{SingApplication.g().getString(C1947R.string.performance_copyright_violation_email)})));
        f24786f.put(Integer.valueOf(41), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_disabled_recording), MessageFormat.format(SingApplication.g().getString(C1947R.string.removed_content_body_disabled_recording), new Object[]{SingApplication.g().getString(C1947R.string.performance_copyright_violation_email)})));
        f24786f.put(Integer.valueOf(42), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_disabled_recording), MessageFormat.format(SingApplication.g().getString(C1947R.string.removed_content_body_disabled_recording), new Object[]{SingApplication.g().getString(C1947R.string.performance_copyright_violation_email)})));
        f24786f.put(Integer.valueOf(50), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_generic), SingApplication.g().getString(C1947R.string.removed_content_body_generic)));
        f24786f.put(Integer.valueOf(51), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_deleted_recording)));
        f24786f.put(Integer.valueOf(52), new Pair(SingApplication.g().getString(C1947R.string.removed_content_title_deleted_recording), SingApplication.g().getString(C1947R.string.removed_content_body_cascade_deleted_arrangement)));
    }

    public static void m25890a(final Activity activity, final EditText editText) {
        if (activity != null && editText != null) {
            editText.postDelayed(new Runnable() {
                public void run() {
                    ((InputMethodManager) activity.getSystemService("input_method")).showSoftInput(editText, 0);
                }
            }, 200);
        }
    }

    public static void m25891a(Activity activity, boolean z) {
        if (activity != null) {
            View currentFocus = activity.getCurrentFocus();
            if (currentFocus == null || (currentFocus instanceof AmazingListView)) {
                Log.b(f24785e, "hideSoftKeyboard - Nothing focused, no keyboard to hide");
                return;
            }
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            if (z) {
                currentFocus.clearFocus();
            }
        }
    }

    public static void m25894a(View view, boolean z) {
        if (view == null) {
            Log.b(f24785e, "hideSoftKeyboard - Nothing focused, no keyboard to hide");
            return;
        }
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        if (z) {
            view.clearFocus();
        }
    }

    public static String m25887a(long j, boolean z, boolean z2, boolean z3) {
        return m25897b(1000 * j, z, z2);
    }

    public static String m25886a(long j, boolean z, boolean z2) {
        return m25897b(1000 * j, z, z2);
    }

    public static String m25897b(long j, boolean z, boolean z2) {
        return m25901c(Math.abs((System.currentTimeMillis() - j) / 1000), z, z2);
    }

    public static String m25901c(long j, boolean z, boolean z2) {
        int i;
        int i2;
        if (j < 60) {
            i = (int) j;
            if (z) {
                i2 = C1947R.plurals.time_seconds_short;
            } else {
                i2 = C1947R.plurals.time_seconds;
            }
        } else if (j < 3600) {
            i = (int) Math.round(((double) j) / 60.0d);
            if (z) {
                i2 = C1947R.plurals.time_minutes_short;
            } else {
                i2 = C1947R.plurals.time_minutes;
            }
        } else if (j < 86400) {
            i = Math.round(((float) j) / 3600.0f);
            if (z) {
                i2 = C1947R.plurals.time_hours_short;
            } else {
                i2 = C1947R.plurals.time_hours;
            }
        } else if (j < 2678400) {
            i = Math.round(((float) j) / 86400.0f);
            if (z) {
                i2 = C1947R.plurals.time_days_short;
            } else {
                i2 = C1947R.plurals.time_days;
            }
        } else if (j < 31536000) {
            i = Math.round(((float) j) / 2628000.0f);
            if (z) {
                i2 = C1947R.plurals.time_months_short;
            } else {
                i2 = C1947R.plurals.time_months;
            }
        } else {
            i = Math.round(((float) j) / 3.1536E7f);
            if (z) {
                i2 = C1947R.plurals.time_years_short;
            } else {
                i2 = C1947R.plurals.time_years;
            }
        }
        return m25885a(i2, i, z2);
    }

    private static String m25885a(int i, int i2, boolean z) {
        Context applicationContext = MagicNetwork.d().getApplicationContext();
        String quantityString = applicationContext.getResources().getQuantityString(i, i2, new Object[]{Integer.valueOf(i2)});
        if (!z) {
            return quantityString;
        }
        return String.format(applicationContext.getString(C1947R.string.time_elapsed_format), new Object[]{quantityString});
    }

    public static String m25883a(float f) {
        int round = Math.round(f);
        int i = round / 60;
        round %= 60;
        return String.format("%d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(round)});
    }

    public static SpannedString m25878a(String str, String str2, String str3, Object obj) {
        return m25879a(str, str2, str3, obj, null);
    }

    public static SpannedString m25879a(String str, String str2, String str3, Object obj, Object obj2) {
        CharSequence spannableString;
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            spannableString = new SpannableString(str);
            if (obj2 != null) {
                spannableString.setSpan(obj2, 0, spannableString.length(), 0);
            }
        } else {
            spannableString = new SpannableString(str.replace(str2, str3));
            if (obj2 != null) {
                spannableString.setSpan(obj2, 0, spannableString.length(), 0);
            }
            spannableString.setSpan(obj, indexOf, str3.length() + indexOf, 0);
        }
        return new SpannedString(spannableString);
    }

    public static <K, V extends Comparable<V>> List<Entry<K, V>> m25889a(Map<K, V> map) {
        List<Entry> linkedList = new LinkedList(map.entrySet());
        Collections.sort(linkedList, new C49962());
        List<Entry<K, V>> arrayList = new ArrayList();
        for (Entry add : linkedList) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static String m25884a(int i) {
        if (i < 1) {
            return "";
        }
        if (i < 100) {
            return "" + i;
        }
        return "99+";
    }

    public static Pair<String, String> m25881a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean z) {
        Object e;
        Object obj = null;
        if (performanceV2 == null || !(songbookEntry == null || PerformanceV2Util.m25941a(performanceV2.songUid))) {
            e = songbookEntry.mo6291e();
        } else {
            e = performanceV2.title;
        }
        if (songbookEntry != null) {
            if (z) {
                obj = songbookEntry.mo6292f();
            }
        } else if (!(performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.c() || e.endsWith(" - " + performanceV2.arrangementVersion.arrangement.name))) {
            e = e + " - " + performanceV2.arrangementVersion.arrangement.name;
        }
        return new Pair(e, obj);
    }

    public static MagicTwitter m25882a() {
        MagicTwitter a = MagicTwitter.m18838a();
        if (a != null) {
            return a;
        }
        Log.c(f24785e, "Initializing MagicTwitter");
        Context f = SingApplication.f();
        MagicTwitter.m18839a(f, f.getString(C1947R.string.twitter_consumer_key), f.getString(C1947R.string.twitter_consumer_secret));
        return MagicTwitter.m18838a();
    }

    public static boolean m25899b() {
        MagicTwitter a = m25882a();
        return a != null && a.m18845c();
    }

    public static boolean m25896a(String str) {
        return str.matches(".*account/icon/.*_defpic.*");
    }

    public static String m25900c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("android : ").append(VERSION.RELEASE);
        for (Field field : VERSION_CODES.class.getFields()) {
            String name = field.getName();
            int i = -1;
            try {
                i = field.getInt(new Object());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NullPointerException e3) {
                e3.printStackTrace();
            }
            if (i == VERSION.SDK_INT) {
                stringBuilder.append(" : ").append(name).append(" : ");
                stringBuilder.append("sdk=").append(i);
            }
        }
        return stringBuilder.toString();
    }

    public static String m25888a(Context context) {
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        }
    }

    public static String m25898b(Context context) {
        boolean isProviderEnabled;
        boolean isProviderEnabled2;
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        try {
            isProviderEnabled = locationManager.isProviderEnabled("gps");
        } catch (Exception e) {
            isProviderEnabled = false;
        }
        try {
            isProviderEnabled2 = locationManager.isProviderEnabled("network");
        } catch (Exception e2) {
            isProviderEnabled2 = false;
        }
        return "GPS enabled: " + isProviderEnabled + ", Network enabled: " + isProviderEnabled2;
    }

    public static boolean m25895a(PerformanceV2 performanceV2) {
        return performanceV2 != null && performanceV2.f() && SingApplication.o();
    }

    public static Pair<String, String> m25880a(int i, Boolean bool) {
        if (bool != null && (i == 40 || i == 20)) {
            i = (i * 10) + (bool.booleanValue() ? 0 : 1);
        }
        Pair<String, String> pair = (Pair) f24786f.get(Integer.valueOf(i));
        if (pair == null) {
            return (Pair) f24786f.get(Integer.valueOf(10));
        }
        return pair;
    }

    public static void m25892a(View view, float f, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, float f2, Runnable runnable) {
        m25893a(view, f, z, z2, z3, z4, j, j2, f2, runnable, false, 0);
    }

    public static void m25893a(final View view, float f, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2, float f2, Runnable runnable, boolean z5, int i) {
        view.setAlpha(0.0f);
        Animation animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        animationSet.setStartOffset(500 + j);
        final Runnable runnable2 = runnable;
        animationSet.setAnimationListener(new AnimationListener() {
            public void onAnimationStart(Animation animation) {
                view.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }

            public void onAnimationEnd(Animation animation) {
                if (runnable2 != null) {
                    runnable2.run();
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
        if (z4) {
            float f3;
            if (i == 0) {
                f3 = (float) (f == f24782b ? 40 : -10);
            } else {
                f3 = (float) i;
            }
            Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, f3, 0.0f);
            translateAnimation.setFillAfter(true);
            translateAnimation.setDuration(j2);
            animationSet.addAnimation(translateAnimation);
        }
        if (z3) {
            Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            alphaAnimation.setFillAfter(true);
            long j3 = ((!z3 || z2 || z4) && !z5) ? j2 / 4 : 0;
            alphaAnimation.setStartOffset(j3);
            if ((!z3 || z2 || z4) && !z5) {
                j3 = (j2 / 4) * 3;
            } else {
                j3 = j2;
            }
            alphaAnimation.setDuration(j3);
            animationSet.addAnimation(alphaAnimation);
        }
        if (z2) {
            Animation scaleAnimation = new ScaleAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1, 0.5f, 1, f);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setDuration(j2);
            if (z) {
                if (z3 || z4) {
                    scaleAnimation.setInterpolator(new AnticipateOvershootInterpolator(f2));
                } else {
                    scaleAnimation.setInterpolator(new OvershootInterpolator(2.0f * f2));
                }
            }
            animationSet.addAnimation(scaleAnimation);
        }
        view.startAnimation(animationSet);
    }

    public static Spannable m25877a(final Activity activity, String str) {
        Spannable spannable = (Spannable) Html.fromHtml(str);
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            final String url = uRLSpan.getURL();
            spannable.setSpan(new ClickableSpan() {
                public void onClick(View view) {
                    activity.startActivity(WebViewActivity.m22001a(activity, url, true, true));
                }
            }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 0);
            spannable.removeSpan(uRLSpan);
        }
        return spannable;
    }
}
