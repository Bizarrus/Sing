/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.content.res.AssetManager
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.util.DisplayMetrics
 */
package com.smule.android.l10n;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.smule.android.l10n.LocaleSettings;
import java.util.Locale;

public class LocalizationContext
extends ContextWrapper {
    public LocalizationContext(Context context) {
        super(context);
    }

    public Resources getResources() {
        Locale locale = LocaleSettings.c((Context)this);
        if (locale != null) {
            Configuration configuration = super.getResources().getConfiguration();
            configuration.locale = locale;
            locale = super.getResources().getDisplayMetrics();
            return new Resources(this.getAssets(), (DisplayMetrics)locale, configuration);
        }
        return super.getResources();
    }
}

