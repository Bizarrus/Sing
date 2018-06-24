/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.LocaleList
 */
package com.smule.android.l10n;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.l10n.LocalizationContext;
import java.util.Locale;

public class LocalizationUtility {
    public static Context a(Context object) {
        Locale locale;
        Context context;
        block5 : {
            block4 : {
                Locale locale2 = LocalizationUtility.a(object.getResources().getConfiguration());
                locale = LocaleSettings.c((Context)object);
                context = object;
                if (locale == null) break block4;
                context = object;
                if (locale2 == null) break block4;
                context = object;
                if (locale2.equals(locale)) break block4;
                object = new LocalizationContext((Context)object);
                context = object.getResources().getConfiguration();
                if (Build.VERSION.SDK_INT < 24) break block5;
                context.setLocale(locale);
                locale = new LocaleList(new Locale[]{locale});
                LocaleList.setDefault((LocaleList)locale);
                context.setLocales((LocaleList)locale);
                context = object.createConfigurationContext((Configuration)context);
            }
            return context;
        }
        if (Build.VERSION.SDK_INT >= 17) {
            context.setLocale(locale);
            return object.createConfigurationContext((Configuration)context);
        }
        return object;
    }

    public static Locale a(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 24) {
            if (configuration.getLocales().isEmpty()) {
                return null;
            }
            return configuration.getLocales().get(0);
        }
        return configuration.locale;
    }
}

