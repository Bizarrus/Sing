package com.smule.singandroid;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import com.smule.android.logging.MagicCrittercism;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum VocalEffect {
    NONE(C1947R.string.fx_dry, "dry"),
    POLISH(C1947R.string.fx_normal, "normal"),
    SUPER_HARMONY(C1947R.string.fx_super_harmonizer, "super_harmonizer"),
    POP(C1947R.string.fx_pop, "pop"),
    SUPER_POP(C1947R.string.fx_super_pop, "super_pop"),
    STUDIO(C1947R.string.fx_studio, "studio"),
    SUPER_STUDIO(C1947R.string.fx_super_studio, "super_studio"),
    INDIE(C1947R.string.fx_indie, "indie"),
    DOUBLE_YOU(C1947R.string.fx_doubler, "doubler"),
    STAR_DUST(C1947R.string.fx_star_dust, "star_dust"),
    GRUNGE(C1947R.string.fx_grunge, "grunge"),
    MAGIC(C1947R.string.fx_magic, "magic");
    
    private static final Map<String, VocalEffect> f20451o = null;
    private static ArrayList<VocalEffect> f20452p;
    private static Set<String> f20453q;
    private String f20455m;
    private int f20456n;

    static {
        f20451o = new HashMap();
        VocalEffect[] values = values();
        int length = values.length;
        int i;
        while (i < length) {
            VocalEffect vocalEffect = values[i];
            f20451o.put(vocalEffect.m21986c(), vocalEffect);
            i++;
        }
        f20452p = new ArrayList();
        f20453q = SingServerValues.m21686g();
    }

    private VocalEffect(int i, String str) {
        this.f20456n = i;
        this.f20455m = str;
    }

    public static boolean m21977a(String str) {
        return f20451o.containsKey(str);
    }

    public static VocalEffect m21979b(String str) {
        if (f20451o.containsKey(str)) {
            return (VocalEffect) f20451o.get(str);
        }
        MagicCrittercism.a(new IllegalArgumentException("Invalid vocal effect requested: '" + str + "'").fillInStackTrace());
        return null;
    }

    public static VocalEffect m21975a() {
        return m21979b(SingServerValues.m21689j());
    }

    public boolean m21984b() {
        VocalEffect b = m21979b(this.f20455m);
        return b == SUPER_HARMONY || b == SUPER_POP || b == SUPER_STUDIO;
    }

    public String toString() {
        return this.f20455m;
    }

    public String m21986c() {
        return this.f20455m;
    }

    public String m21982a(Context context) {
        return context.getString(this.f20456n);
    }

    public int m21983b(Context context) {
        return -16777216 | ((m21985c(context) & ViewCompat.MEASURED_SIZE_MASK) ^ -1);
    }

    public int m21985c(Context context) {
        return context.getResources().getColor(context.getResources().getIdentifier("review_fx_" + this.f20455m + "_bg", "color", context.getPackageName()));
    }

    public static ArrayList<VocalEffect> m21981d() {
        if (f20452p.isEmpty()) {
            for (String str : SingServerValues.m21687h()) {
                if (m21977a(str)) {
                    f20452p.add(m21979b(str));
                }
            }
        }
        return new ArrayList(f20452p);
    }

    public boolean m21987e() {
        return f20453q.contains(this.f20455m);
    }

    public static float m21974a(Context context, String str) {
        VocalEffect b = m21979b(str);
        if (b == null) {
            return 0.0f;
        }
        switch (b) {
            case SUPER_HARMONY:
                return MagicPreferences.m20308b(context, "FX_SUPER_HARMONIZER_PARAM_1", 0.0f);
            case SUPER_POP:
                return MagicPreferences.m20308b(context, "FX_SUPER_POP_PARAM_1", 0.5f);
            case SUPER_STUDIO:
                return MagicPreferences.m20308b(context, "FX_SUPER_STUDIO_PARAM_1", 0.5f);
            default:
                return 0.0f;
        }
    }

    public static float m21978b(Context context, String str) {
        VocalEffect b = m21979b(str);
        if (b == null) {
            return 0.0f;
        }
        switch (b) {
            case SUPER_HARMONY:
                return MagicPreferences.m20308b(context, "FX_SUPER_HARMONIZER_PARAM_2", 0.5f);
            case SUPER_POP:
                return MagicPreferences.m20308b(context, "FX_SUPER_POP_PARAM_2", 0.5f);
            case SUPER_STUDIO:
                return MagicPreferences.m20308b(context, "FX_SUPER_STUDIO_PARAM_2", 0.5f);
            default:
                return 0.0f;
        }
    }

    public static void m21976a(Context context, String str, float f) {
        VocalEffect b = m21979b(str);
        if (b != null) {
            switch (b) {
                case SUPER_HARMONY:
                    MagicPreferences.m20301a(context, "FX_SUPER_HARMONIZER_PARAM_1", f);
                    return;
                case SUPER_POP:
                    MagicPreferences.m20301a(context, "FX_SUPER_POP_PARAM_1", f);
                    return;
                case SUPER_STUDIO:
                    MagicPreferences.m20301a(context, "FX_SUPER_STUDIO_PARAM_1", f);
                    return;
                default:
                    return;
            }
        }
    }

    public static void m21980b(Context context, String str, float f) {
        VocalEffect b = m21979b(str);
        if (b != null) {
            switch (b) {
                case SUPER_HARMONY:
                    MagicPreferences.m20301a(context, "FX_SUPER_HARMONIZER_PARAM_2", f);
                    return;
                case SUPER_POP:
                    MagicPreferences.m20301a(context, "FX_SUPER_POP_PARAM_2", f);
                    return;
                case SUPER_STUDIO:
                    MagicPreferences.m20301a(context, "FX_SUPER_STUDIO_PARAM_2", f);
                    return;
                default:
                    return;
            }
        }
    }
}
