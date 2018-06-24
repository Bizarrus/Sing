package com.smule.singandroid.utils;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.utils.SongbookSectionUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.models.SongbookSection;

public class SongbookSectionUtil {
    private static final String f25072a = SongbookSectionUtil.class.getSimpleName();

    public static SongbookSection m26169a(Context context) {
        Log.c(f25072a, "create mySongs section");
        SongbookSection songbookSection = new SongbookSection();
        songbookSection.f23516e = -3;
        songbookSection.f23514c = "my_songs";
        songbookSection.f23515d = context.getString(C1947R.string.my_songs);
        songbookSection.f23512a = SongbookSectionUtils.m19042a().m19050b();
        return songbookSection;
    }

    public static SongbookSection m26171b(Context context) {
        Log.c(f25072a, "create Community section");
        SongbookSection songbookSection = new SongbookSection();
        songbookSection.f23516e = 2;
        songbookSection.f23514c = "community_songs";
        songbookSection.f23515d = SingServerValues.m21694o();
        return songbookSection;
    }

    public static SongbookSection m26172c(Context context) {
        SongbookSection songbookSection = new SongbookSection();
        songbookSection.f23515d = SingServerValues.m21705z() ? SingServerValues.m21663A() : context.getString(C1947R.string.songbook_suggested_songs);
        songbookSection.f23516e = -2;
        songbookSection.f23514c = "suggested_songs";
        return songbookSection;
    }

    public static SongbookSection m26173d(Context context) {
        SongbookSection songbookSection = new SongbookSection();
        songbookSection.f23515d = context.getString(C1947R.string.songbook_trending_tab);
        songbookSection.f23516e = 1;
        songbookSection.f23514c = "trending_songs";
        return songbookSection;
    }

    public static boolean m26170a(String str) {
        return "suggested_songs".equals(str) && SingServerValues.m21705z();
    }
}
