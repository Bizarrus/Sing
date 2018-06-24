/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 */
package com.smule.singandroid.audio.core.state_machine;

import android.support.annotation.NonNull;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.logger.TagLogger;
import com.smule.singandroid.audio.core.state_machine.CommandListener;
import com.smule.singandroid.audio.core.state_machine.ICommand;

public class CommandLogger
implements CommandListener {
    TagLogger a;
    TagLogger.LogLevel b;

    public CommandLogger(TagLogger tagLogger, TagLogger.LogLevel logLevel) {
        this.a = tagLogger;
        this.b = logLevel;
    }

    public CommandLogger(String string2, TagLogger.LogLevel logLevel) {
        this(new TagLogger(string2), logLevel);
    }

    @Override
    public void a(@NonNull ICommand iCommand, @NonNull IError iError) {
        if (this.b(iCommand, iError)) {
            TagLogger.LogLevel logLevel = this.c(iCommand, iError);
            this.a.a("Command issued: " + iCommand.toString() + " with error: " + iError.toString(), logLevel);
        }
    }

    protected boolean b(@NonNull ICommand iCommand, @NonNull IError iError) {
        return true;
    }

    protected TagLogger.LogLevel c(@NonNull ICommand iCommand, @NonNull IError iError) {
        return this.b;
    }
}

