/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.state_machine.ICommand;

public interface CommandListener {
    public void a(@NonNull ICommand var1, @NonNull IError var2);
}

