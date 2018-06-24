/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.IState;

public interface StateChangeListener {
    public void a(@NonNull IState var1, @NonNull IState var2, @Nullable IError var3);
}

