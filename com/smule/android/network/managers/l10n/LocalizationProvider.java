/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.android.network.managers.l10n;

import android.content.Context;
import com.smule.android.network.managers.LocalizationManager;

public interface LocalizationProvider {
    public <T> T a(T var1);

    public String a();

    public void a(Context var1);

    public void a(LocalizationManager.LocalizationConfig var1);
}

