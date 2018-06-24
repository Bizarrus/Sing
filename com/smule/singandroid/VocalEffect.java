/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.ColorRes
 *  android.support.annotation.StringRes
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import com.smule.android.logging.MagicCrittercism;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.preference.MagicPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public enum VocalEffect {
    a(2131297725, "dry", 2131297757, 2131689910),
    b(2131297730, "normal", 2131297762, 2131689915),
    c(2131297734, "super_harmonizer", 2131297767, 2131689920),
    d(2131297731, "pop", 2131297763, 2131689916),
    e(2131297735, "super_pop", 2131297768, 2131689921),
    f(2131297733, "studio", 2131297766, 2131689919),
    g(2131297736, "super_studio", 2131297769, 2131689922),
    h(2131297727, "indie", 2131297760, 2131689912),
    i(2131297724, "doubler", 2131297756, 2131689907),
    j(2131297732, "star_dust", 2131297765, 2131689918),
    k(2131297726, "grunge", 2131297758, 2131689911),
    l(2131297728, "magic", 2131297761, 2131689913);
    
    public static final ArrayList<VocalEffect> m;
    public static final ArrayList<VocalEffect> n;
    private static final Map<String, VocalEffect> s;
    private static ArrayList<VocalEffect> t;
    private static Set<String> u;
    private String o;
    @StringRes
    private int p;
    @StringRes
    private int q;
    @ColorRes
    private int r;

    static {
        s = new HashMap<String, VocalEffect>();
        for (VocalEffect vocalEffect : VocalEffect.values()) {
            s.put(vocalEffect.b(), vocalEffect);
        }
        m = new ArrayList<VocalEffect>(){
            {
                this.add(VocalEffect.c);
                this.add(VocalEffect.l);
                this.add(VocalEffect.j);
            }
        };
        n = new ArrayList<VocalEffect>(){
            {
                this.add(VocalEffect.l);
                this.add(VocalEffect.j);
            }
        };
        t = new ArrayList();
        u = new SingServerValues().h();
    }

    private VocalEffect(@StringRes int n2, String string3, @StringRes int n3, @ColorRes int n4) {
        this.p = n2;
        this.o = string3;
        this.q = n3;
        this.r = n4;
    }

    public static float a(Context context, String object) {
        if ((object = VocalEffect.b((String)object)) == null) {
            return 0.0f;
        }
        switch (.a[object.ordinal()]) {
            default: {
                return 0.0f;
            }
            case 1: {
                return MagicPreferences.b(context, "FX_SUPER_HARMONIZER_PARAM_1", 0.0f);
            }
            case 2: {
                return MagicPreferences.b(context, "FX_SUPER_POP_PARAM_1", 0.5f);
            }
            case 3: 
        }
        return MagicPreferences.b(context, "FX_SUPER_STUDIO_PARAM_1", 0.5f);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(Context context, String object, float f) {
        if ((object = VocalEffect.b((String)object)) == null) return;
        switch (.a[object.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                MagicPreferences.a(context, "FX_SUPER_HARMONIZER_PARAM_1", f);
                return;
            }
            case 2: {
                MagicPreferences.a(context, "FX_SUPER_POP_PARAM_1", f);
                return;
            }
            case 3: 
        }
        MagicPreferences.a(context, "FX_SUPER_STUDIO_PARAM_1", f);
    }

    public static boolean a(VocalEffect vocalEffect) {
        if (!m.contains((Object)vocalEffect)) {
            return true;
        }
        return false;
    }

    public static boolean a(String string2) {
        return s.containsKey(string2);
    }

    public static float b(Context context, String object) {
        if ((object = VocalEffect.b((String)object)) == null) {
            return 0.0f;
        }
        switch (.a[object.ordinal()]) {
            default: {
                return 0.0f;
            }
            case 1: {
                return MagicPreferences.b(context, "FX_SUPER_HARMONIZER_PARAM_2", 0.5f);
            }
            case 2: {
                return MagicPreferences.b(context, "FX_SUPER_POP_PARAM_2", 0.5f);
            }
            case 3: 
        }
        return MagicPreferences.b(context, "FX_SUPER_STUDIO_PARAM_2", 0.5f);
    }

    public static VocalEffect b(String string2) {
        if (s.containsKey(string2)) {
            return s.get(string2);
        }
        MagicCrittercism.a(new IllegalArgumentException("Invalid vocal effect requested: '" + string2 + "'").fillInStackTrace());
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void b(Context context, String object, float f) {
        if ((object = VocalEffect.b((String)object)) == null) return;
        switch (.a[object.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                MagicPreferences.a(context, "FX_SUPER_HARMONIZER_PARAM_2", f);
                return;
            }
            case 2: {
                MagicPreferences.a(context, "FX_SUPER_POP_PARAM_2", f);
                return;
            }
            case 3: 
        }
        MagicPreferences.a(context, "FX_SUPER_STUDIO_PARAM_2", f);
    }

    public static boolean b(VocalEffect vocalEffect) {
        if (!n.contains((Object)vocalEffect)) {
            return true;
        }
        return false;
    }

    public static ArrayList<VocalEffect> d() {
        if (t.isEmpty()) {
            for (String string2 : new SingServerValues().i()) {
                if (!VocalEffect.a(string2)) continue;
                t.add(VocalEffect.b(string2));
            }
        }
        return new ArrayList<VocalEffect>(t);
    }

    public String a(Context context) {
        return context.getString(this.p);
    }

    public boolean a() {
        VocalEffect vocalEffect = VocalEffect.b(this.o);
        if (vocalEffect == c || vocalEffect == e || vocalEffect == g) {
            return true;
        }
        return false;
    }

    public int b(Context context) {
        return -16777216 | ~ (this.d(context) & 16777215);
    }

    public String b() {
        return this.o;
    }

    public int c() {
        return this.q;
    }

    public int c(Context context) {
        return context.getResources().getColor(2131689909);
    }

    public int d(Context context) {
        return context.getResources().getColor(this.r);
    }

    public int e(Context context) {
        return context.getResources().getColor(2131689908);
    }

    public boolean e() {
        return u.contains(this.o);
    }

    public String toString() {
        return this.o;
    }

}

