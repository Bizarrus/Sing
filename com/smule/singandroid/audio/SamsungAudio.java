/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  com.samsung.android.sdk.professionalaudio.SapaProcessor
 *  com.samsung.android.sdk.professionalaudio.SapaProcessor$MessageListener
 *  com.samsung.android.sdk.professionalaudio.SapaProcessor$StatusListener
 *  com.samsung.android.sdk.professionalaudio.SapaService
 *  com.samsung.android.sdk.professionalaudio.SapaService$Parameters
 */
package com.smule.singandroid.audio;

import android.app.Activity;
import com.samsung.android.sdk.professionalaudio.SapaProcessor;
import com.samsung.android.sdk.professionalaudio.SapaService;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingCoreBridge;
import com.smule.singandroid.audio.AudioController;
import java.nio.ByteBuffer;

public class SamsungAudio
extends AudioController {
    private static final String f = SingCoreBridge.class.getName();
    private static SapaService g;
    private static SapaProcessor h;

    @Override
    public void b() {
        if (h != null) {
            h.activate();
        }
    }

    @Override
    public void c() {
    }

    @Override
    public void f() {
        if (h != null) {
            h.deactivate();
        }
    }

    @Override
    public int h() {
        return g.getParameters().getSampleRate();
    }

}

