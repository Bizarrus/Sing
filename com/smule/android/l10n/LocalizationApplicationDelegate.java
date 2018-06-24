/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.android.l10n;

import android.content.Context;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.l10n.LocalizationUtility;

public class LocalizationApplicationDelegate {
    public void a(Context context) {
        LocalizationUtility.a(context);
    }

    public Context b(Context context) {
        LocaleSettings.a(context);
        return LocalizationUtility.a(context);
    }
}

