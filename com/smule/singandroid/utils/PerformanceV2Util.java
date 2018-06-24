package com.smule.singandroid.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.widget.TextView;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.Track;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.CustomImageSpan;

public class PerformanceV2Util {

    public enum RemovalReason {
        GENERIC(10),
        CONTENT_OWNER(20),
        PERF_OWNER(21),
        ARR_OWNER(22),
        CONTENT_PARTICIPANT(30),
        PERF_PARTICIPANT(31),
        CONTENT_COPYRIGHT(40),
        PERF_COPYRIGHT(41),
        ARR_COPYRIGHT(42),
        GENERIC_PERF_REMOVAL(51),
        GENERIC_ARR_REMOVAL(52);
        
        private final int f24897l;

        private RemovalReason(int i) {
            this.f24897l = i;
        }
    }

    public static boolean m25940a(PerformanceV2 performanceV2) {
        boolean z;
        boolean l = performanceV2.l();
        boolean d = performanceV2.d();
        boolean p = performanceV2.p();
        if (!performanceV2.seed || performanceV2.childCount <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            if (l) {
                return true;
            }
            if (d && p) {
                return true;
            }
        }
        return false;
    }

    public static boolean m25944b(PerformanceV2 performanceV2) {
        return performanceV2.d() && performanceV2.recentTracks.size() == 1;
    }

    public static boolean m25945c(PerformanceV2 performanceV2) {
        return performanceV2.d() && performanceV2.recentTracks.size() == 0;
    }

    public static String m25946d(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            return null;
        }
        if (performanceV2.s()) {
            return performanceV2.songUid;
        }
        if (performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement == null || performanceV2.arrangementVersion.arrangement.songId == null) {
            return null;
        }
        return performanceV2.arrangementVersion.arrangement.songId;
    }

    private static String m25943b(PerformanceV2 performanceV2, SongV2 songV2) {
        if (songV2 != null) {
            return songV2.songId;
        }
        return m25946d(performanceV2);
    }

    public static String m25938a(PerformanceV2 performanceV2, SongV2 songV2) {
        String b = m25943b(performanceV2, songV2);
        if (TextUtils.isEmpty(b)) {
            return "-";
        }
        return b;
    }

    public static String m25947e(PerformanceV2 performanceV2) {
        String d = m25946d(performanceV2);
        return (d == null || d.isEmpty()) ? "-" : d;
    }

    public static SongV2 m25948f(PerformanceV2 performanceV2) {
        String d = m25946d(performanceV2);
        return d != null ? StoreManager.m18378a().m18416a(d) : null;
    }

    public static String m25949g(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            return null;
        }
        if (performanceV2.arrKey != null) {
            return performanceV2.arrKey;
        }
        if (performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement == null) {
            return null;
        }
        return performanceV2.arrangementVersion.arrangement.key;
    }

    public static String m25950h(PerformanceV2 performanceV2) {
        String str = null;
        if (performanceV2 != null) {
            if (performanceV2.arrKey != null) {
                str = performanceV2.arrKey;
            } else if (!(performanceV2.arrangementVersion == null || performanceV2.arrangementVersion.arrangement == null)) {
                str = performanceV2.arrangementVersion.arrangement.key;
            }
        }
        if (str == null || str.isEmpty()) {
            return "-";
        }
        return str;
    }

    public static boolean m25941a(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("_open_mic_");
    }

    private static SpannableString m25935a(Resources resources, boolean z, PerformanceV2 performanceV2, boolean z2) {
        return m25936a(false, resources, z, performanceV2, z2);
    }

    private static SpannableString m25936a(boolean z, Resources resources, boolean z2, PerformanceV2 performanceV2, boolean z3) {
        if (performanceV2.e()) {
            return m25942b(resources, z2, performanceV2, z3);
        }
        String str;
        String str2 = performanceV2.accountIcon.handle;
        if (z2 && performanceV2.accountIcon.c()) {
            str = "*" + str2;
        } else {
            str = str2;
        }
        if (performanceV2.recentTracks != null) {
            int size = performanceV2.recentTracks.size();
            if (size > 0 && (performanceV2.accountIcon.accountId != ((Track) performanceV2.recentTracks.get(0)).accountIcon.accountId || z)) {
                if (z2 && ((Track) performanceV2.recentTracks.get(0)).accountIcon.c()) {
                    str = str + " + *" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle;
                } else {
                    str = str + " + " + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle;
                }
            }
            if (size > 1) {
                str = str + " and others";
            }
        }
        SpannableString spannableString = new SpannableString(str);
        if (z2 && performanceV2.accountIcon.c()) {
            spannableString.setSpan(m25937a(resources), 0, 1, 17);
        }
        if (performanceV2.recentTracks != null && performanceV2.recentTracks.size() > 0 && ((performanceV2.accountIcon.accountId != ((Track) performanceV2.recentTracks.get(0)).accountIcon.accountId || z) && z2 && ((Track) performanceV2.recentTracks.get(0)).accountIcon.c())) {
            spannableString.setSpan(m25937a(resources), str.indexOf("*" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle), str.indexOf("*" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle) + 1, 17);
        }
        return spannableString;
    }

    private static SpannableString m25942b(Resources resources, boolean z, PerformanceV2 performanceV2, boolean z2) {
        String str;
        SpannableString spannableString;
        int i = performanceV2.totalPerformers - 1;
        int i2 = (z && performanceV2.accountIcon.c()) ? 1 : 0;
        if (i2 != 0) {
            str = "*" + performanceV2.accountIcon.handle;
        } else {
            str = performanceV2.accountIcon.handle;
        }
        if (!z2) {
            spannableString = new SpannableString(str + " + " + i);
        } else if (i == 0) {
            spannableString = new SpannableString(resources.getString(C1947R.string.chat_title_solo_cover, new Object[]{str}));
        } else {
            spannableString = new SpannableString(resources.getQuantityString(C1947R.plurals.chat_title_group_cover, i, new Object[]{str, Integer.valueOf(i)}));
        }
        if (i2 != 0) {
            spannableString.setSpan(m25937a(resources), 0, 1, 17);
        }
        return spannableString;
    }

    public static SpannableString m25933a(Resources resources, PerformanceV2 performanceV2, boolean z) {
        return m25934a(resources, performanceV2, z, false);
    }

    public static SpannableString m25934a(Resources resources, PerformanceV2 performanceV2, boolean z, boolean z2) {
        if (resources == null || performanceV2 == null) {
            return new SpannableString("");
        }
        if (performanceV2.e()) {
            return m25935a(resources, z, performanceV2, z2);
        }
        SpannableString spannableString;
        if (performanceV2.d()) {
            if (performanceV2.seed) {
                if (!z || !performanceV2.accountIcon.c()) {
                    return new SpannableString(performanceV2.accountIcon.handle);
                }
                spannableString = new SpannableString("*" + performanceV2.accountIcon.handle);
                spannableString.setSpan(m25937a(resources), 0, 1, 17);
                return spannableString;
            } else if (performanceV2.recentTracks == null || performanceV2.recentTracks.isEmpty()) {
                if (!z || !performanceV2.accountIcon.c()) {
                    return new SpannableString(performanceV2.accountIcon.handle);
                }
                spannableString = new SpannableString("*" + performanceV2.accountIcon.handle);
                spannableString.setSpan(m25937a(resources), 0, 1, 17);
                return spannableString;
            } else if (z) {
                String str;
                Object[] objArr = new Object[2];
                objArr[0] = performanceV2.accountIcon.c() ? "*" + performanceV2.accountIcon.handle : performanceV2.accountIcon.handle;
                if (((Track) performanceV2.recentTracks.get(0)).accountIcon.c()) {
                    str = "*" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle;
                } else {
                    str = ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle;
                }
                objArr[1] = str;
                String string = resources.getString(C1947R.string.chat_title_duet_cover, objArr);
                SpannableString spannableString2 = new SpannableString(string);
                if (performanceV2.accountIcon.c()) {
                    spannableString2.setSpan(m25937a(resources), string.indexOf("*" + performanceV2.accountIcon.handle), string.indexOf("*" + performanceV2.accountIcon.handle) + 1, 17);
                }
                if (((Track) performanceV2.recentTracks.get(0)).accountIcon.c()) {
                    spannableString2.setSpan(m25937a(resources), string.indexOf("*" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle, 1), string.indexOf("*" + ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle, 1) + 1, 17);
                }
                return spannableString2;
            } else {
                return new SpannableString(resources.getString(C1947R.string.chat_title_duet_cover, new Object[]{performanceV2.accountIcon.handle, ((Track) performanceV2.recentTracks.get(0)).accountIcon.handle}));
            }
        } else if (!z || !performanceV2.accountIcon.c()) {
            return new SpannableString(performanceV2.accountIcon.handle);
        } else {
            spannableString = new SpannableString("*" + performanceV2.accountIcon.handle);
            spannableString.setSpan(m25937a(resources), 0, 1, 17);
            return spannableString;
        }
    }

    public static ImageSpan m25937a(Resources resources) {
        Drawable drawable = resources.getDrawable(C1947R.drawable.icn_verified);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicWidth());
        return new CustomImageSpan(drawable, 1);
    }

    public static void m25939a(Resources resources, TextView textView, AccountIcon accountIcon) {
        if (accountIcon.c()) {
            CharSequence spannableString = new SpannableString("*" + accountIcon.handle);
            spannableString.setSpan(m25937a(resources), 0, 1, 17);
            textView.setText(spannableString);
            return;
        }
        textView.setText(accountIcon.handle);
    }
}
