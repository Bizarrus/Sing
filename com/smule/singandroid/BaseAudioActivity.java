/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  com.smule.singandroid.utils.CacheUtils
 *  com.smule.singandroid.utils.CacheUtils$DecodeCache
 */
package com.smule.singandroid;

import android.content.Context;
import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.audio.AudioController;
import com.smule.singandroid.audio.AudioSystemStateMachine;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.audio.core.exception.IError;
import com.smule.singandroid.audio.core.exception.StateMachineTransitionException;
import com.smule.singandroid.audio.exception.NativeException;
import com.smule.singandroid.utils.CacheUtils;

public abstract class BaseAudioActivity
extends BaseActivity
implements AudioController.AudioObserver {
    protected final DeviceSettings g = new DeviceSettings();
    protected AudioController h;
    protected String i;
    protected String j;
    protected String k;

    @NonNull
    protected static String c(@NonNull String string2) {
        String string3 = string2;
        if (CacheUtils.DecodeCache.a((String)(string2 + ".wav"))) {
            string3 = string2 + ".wav";
        }
        return string3;
    }

    @Override
    public void a(AudioSystemStateMachine.State state, AudioSystemStateMachine.Result result) {
    }

    protected void a(@NonNull Metadata metadata) throws StateMachineTransitionException, NativeException {
        if (this.h != null) {
            String string2 = BaseAudioActivity.c(this.k);
            this.h.a(new DeviceSettings(), string2, this.i, null, this.j, metadata.b());
            return;
        }
        Log.e(a, "mAudioController not instantiated. Did createAudioController() run successfully?");
    }

    @Override
    public void a(IError iError) {
    }

    @Override
    public void b(IError iError) {
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(boolean bl) throws Exception {
        this.h = new AudioController(this, (Context)this);
        if (bl) {
            this.h.n();
        } else {
            this.h.a();
        }
        this.h.a(this.g);
    }

    @Override
    public void t() {
    }
}

