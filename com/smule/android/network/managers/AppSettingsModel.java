/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  com.fasterxml.jackson.databind.JsonNode
 */
package com.smule.android.network.managers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.databind.JsonNode;

public interface AppSettingsModel {
    public double a(@NonNull String var1, @NonNull String var2, double var3);

    @Nullable
    public JsonNode a(@NonNull String var1, @NonNull String var2, @Nullable JsonNode var3);

    @Nullable
    public String a(@NonNull String var1, @NonNull String var2, int var3);

    @Nullable
    public String a(@NonNull String var1, @NonNull String var2, @Nullable String var3);

    public boolean a(@NonNull String var1, @NonNull String var2, boolean var3);

    public int b(@NonNull String var1, @NonNull String var2, int var3);

}

