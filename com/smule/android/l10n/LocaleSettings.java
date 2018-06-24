/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.content.res.Resources
 */
package com.smule.android.l10n;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.smule.android.l10n.LocalizationUtility;
import com.smule.android.network.core.MagicNetwork;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class LocaleSettings {
    private static Set<String> a = new HashSet<String>();
    private static Locale b = null;
    private static boolean c = false;
    private static Locale d;

    public static Locale a(String string2, Locale locale) {
        if (string2.equals("zh-Hans")) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (string2.equals("zh-Hant")) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (string2.equals("id")) {
            return new Locale("in", locale.getCountry());
        }
        return new Locale(string2, locale.getCountry());
    }

    public static void a() {
        for (String string2 : MagicNetwork.d().getSupportedLanguages()) {
            a.add(string2);
        }
    }

    public static void a(Context context) {
        d = LocalizationUtility.a(context.getResources().getConfiguration());
    }

    public static void a(Context context, Locale locale) {
        Locale.setDefault(locale);
        context = LocaleSettings.d(context).edit();
        context.putString("key_locale", locale.toString());
        context.commit();
        c = true;
        b = locale;
    }

    public static boolean a(String string2) {
        return a.contains(string2);
    }

    public static boolean a(Locale locale) {
        return LocaleSettings.a(LocaleSettings.b(locale));
    }

    public static String b() {
        Locale locale = LocaleSettings.c(MagicNetwork.d().getApplicationContext());
        if (locale != null) {
            return LocaleSettings.b(locale);
        }
        return null;
    }

    public static String b(Locale locale) {
        if (locale.equals(Locale.SIMPLIFIED_CHINESE)) {
            return "zh-Hans";
        }
        if (locale.equals(Locale.TRADITIONAL_CHINESE)) {
            return "zh-Hant";
        }
        if (locale.getLanguage().equals("in")) {
            return "id";
        }
        return locale.getLanguage();
    }

    public static Locale b(Context context) {
        return d;
    }

    private static Locale b(String arrstring) {
        if (arrstring == null) {
            return null;
        }
        if ((arrstring = arrstring.split("_")).length == 1) {
            return new Locale(arrstring[0]);
        }
        if (arrstring.length == 2) {
            return new Locale(arrstring[0], arrstring[1].toUpperCase());
        }
        return new Locale(arrstring[0], arrstring[1].toUpperCase(), arrstring[2]);
    }

    public static Collection<String> c() {
        return a;
    }

    public static Locale c(Context object) {
        synchronized (LocaleSettings.class) {
            if (!c) {
                b = LocaleSettings.b(LocaleSettings.d((Context)object).getString("key_locale", null));
                c = true;
            }
            object = b;
            return object;
        }
    }

    private static SharedPreferences d(Context context) {
        return context.getSharedPreferences("pref_locale", 0);
    }
}

