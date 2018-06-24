package com.smule.singandroid.video;

import android.content.Context;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.video.VideoFilterDatabase.FilterType;
import java.util.Comparator;
import java.util.List;

public class VideoFilterManager {

    public static class VFXItem {
        public String f25457a;
        public String f25458b;
        public boolean f25459c;

        public VFXItem() {
            this.f25459c = false;
        }

        public VFXItem(FilterType filterType) {
            this.f25457a = VideoFilterManager.m26574a(filterType);
            this.f25458b = VideoFilterManager.m26575a(this.f25457a);
            this.f25459c = VideoFilterManager.m26577b(this.f25457a);
        }
    }

    public static class VFXItemSorter implements Comparator<FilterType> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m26573a((FilterType) obj, (FilterType) obj2);
        }

        public int m26573a(FilterType filterType, FilterType filterType2) {
            String a = VideoFilterManager.m26574a(filterType);
            String a2 = VideoFilterManager.m26574a(filterType2);
            List H = SingServerValues.m21670H();
            if (H.contains(a) && H.contains(a2)) {
                return H.indexOf(a) - H.indexOf(a2);
            }
            if (H.contains(a)) {
                return -1;
            }
            if (H.contains(a2)) {
                return 1;
            }
            return a.compareTo(a2);
        }
    }

    public static String m26574a(FilterType filterType) {
        if (filterType == null) {
            return "normal";
        }
        switch (filterType) {
            case SEPIA:
                return "sepia";
            case BLACK_AND_WHITE:
                return "bw";
            case VINTAGE:
                return "vintage";
            case SELFIE:
                return "selfie";
            case FIGHTCLUB:
                return "fight";
            default:
                return "normal";
        }
    }

    public static String m26575a(String str) {
        Context f = SingApplication.f();
        if (str == null) {
            return f.getResources().getString(C1947R.string.video_fx_normal);
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case -906020504:
                if (str.equals("selfie")) {
                    obj = 3;
                    break;
                }
                break;
            case 3157:
                if (str.equals("bw")) {
                    obj = 1;
                    break;
                }
                break;
            case 97429520:
                if (str.equals("fight")) {
                    obj = 4;
                    break;
                }
                break;
            case 109324790:
                if (str.equals("sepia")) {
                    obj = null;
                    break;
                }
                break;
            case 462452390:
                if (str.equals("vintage")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return f.getResources().getString(C1947R.string.video_fx_sepia);
            case 1:
                return f.getResources().getString(C1947R.string.video_fx_bw);
            case 2:
                return f.getResources().getString(C1947R.string.video_fx_vintage);
            case 3:
                return f.getResources().getString(C1947R.string.video_fx_selfie);
            case 4:
                return f.getResources().getString(C1947R.string.video_fx_fight);
            default:
                return f.getResources().getString(C1947R.string.video_fx_normal);
        }
    }

    public static boolean m26577b(String str) {
        return SingServerValues.m21671I().contains(str);
    }

    public static boolean m26578c(String str) {
        return SingServerValues.m21670H().contains(str);
    }

    public static boolean m26576a() {
        return DeviceSettings.r() && SingServerValues.m21672J();
    }
}
