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
import com.smule.singandroid.audio.core.logger.TagLogger;
import com.smule.singandroid.audio.core.state_machine.IState;
import com.smule.singandroid.audio.core.state_machine.StateChangeListener;

public class StateChangeLogger
implements StateChangeListener {
    private TagLogger a;
    private TagLogger.LogLevel b;

    public StateChangeLogger(TagLogger tagLogger, TagLogger.LogLevel logLevel) {
        this.a = tagLogger;
        this.b = logLevel;
    }

    public static String a(@Nullable IError iError) {
        if (iError == null) {
            return "no error";
        }
        return iError.toString();
    }

    @Override
    public void a(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
        if (this.b(iState, iState2, iError)) {
            TagLogger.LogLevel logLevel = this.c(iState, iState2, iError);
            this.a.a("Transitioned: " + iState.toString() + " -> " + iState2.toString() + " (" + StateChangeLogger.a(iError) + ")", logLevel);
        }
    }

    protected boolean b(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
        return true;
    }

    protected TagLogger.LogLevel c(@NonNull IState iState, @NonNull IState iState2, @Nullable IError iError) {
        return this.b;
    }
}

