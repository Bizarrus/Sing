/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.AssetManager
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.LocaleList
 *  android.util.DisplayMetrics
 */
package com.smule.android.l10n;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.l10n.LocalizationUtility;
import java.util.Locale;

public class LocalizationActivityDelegate {
    private boolean a = false;
    private Locale b = null;
    private final Activity c;

    public LocalizationActivityDelegate(Activity activity) {
        this.c = activity;
    }

    private void a() {
        if (this.c.getIntent().getBooleanExtra("activity_locale_changed", false)) {
            this.a = true;
            this.c.getIntent().removeExtra("activity_locale_changed");
        }
    }

    private void a(Context context, Locale locale) {
        if (Build.VERSION.SDK_INT < 17) {
            Configuration configuration = context.getResources().getConfiguration();
            configuration.locale = locale;
            locale = context.getResources().getDisplayMetrics();
            context.getResources().updateConfiguration(configuration, (DisplayMetrics)locale);
        }
    }

    private void a(Locale locale) {
        this.a((Context)this.c, locale);
    }

    private void b() {
        Locale locale = LocaleSettings.c((Context)this.c);
        if (locale != null) {
            this.a(locale);
            this.b = locale;
            LocaleSettings.a((Context)this.c, locale);
        }
    }

    private boolean b(Context object, Locale locale) {
        if ((object = LocaleSettings.c((Context)object)) != null) {
            return locale.equals(object);
        }
        return false;
    }

    private void c() {
        this.c.getIntent().putExtra("activity_locale_changed", true);
        this.c.recreate();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Context a(Context context) {
        Locale locale = LocaleSettings.c(context);
        Context context2 = context;
        if (locale == null) return context2;
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            configuration.setLocale(locale);
            context2 = new LocaleList(new Locale[]{locale});
            LocaleList.setDefault((LocaleList)context2);
            configuration.setLocales((LocaleList)context2);
            return context.createConfigurationContext(configuration);
        }
        context2 = context;
        if (Build.VERSION.SDK_INT < 17) return context2;
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    public Resources a(Resources resources) {
        Locale locale = LocaleSettings.c((Context)this.c);
        Resources resources2 = resources;
        if (locale != null) {
            resources2 = resources;
            if (Build.VERSION.SDK_INT < 24) {
                Configuration configuration = resources.getConfiguration();
                resources2 = resources;
                if (!configuration.locale.equals(locale)) {
                    configuration.locale = locale;
                    resources = resources.getDisplayMetrics();
                    resources2 = new Resources(this.c.getAssets(), (DisplayMetrics)resources, configuration);
                }
            }
        }
        return resources2;
    }

    public final void a(Context context, Locale locale, boolean bl) {
        if (!this.b(context, locale)) {
            LocaleSettings.a((Context)this.c, locale);
            if (bl) {
                this.c();
            }
        }
    }

    public void a(Bundle bundle) {
        this.b();
        this.a();
    }

    public Context b(Context context) {
        Context context2 = context;
        if (Build.VERSION.SDK_INT < 24) {
            context2 = LocalizationUtility.a(context);
        }
        return context2;
    }

    public final Locale c(Context context) {
        Locale locale;
        Locale locale2 = locale = LocaleSettings.c(context);
        if (locale != null || (locale2 = LocaleSettings.b(context)) != null && LocaleSettings.a(locale2)) {
            return locale2;
        }
        return Locale.ENGLISH;
    }
}

