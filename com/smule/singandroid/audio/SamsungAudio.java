package com.smule.singandroid.audio;

import android.app.Activity;
import com.samsung.android.sdk.professionalaudio.SapaProcessor;
import com.samsung.android.sdk.professionalaudio.SapaProcessor.MessageListener;
import com.samsung.android.sdk.professionalaudio.SapaProcessor.StatusListener;
import com.samsung.android.sdk.professionalaudio.SapaService;
import com.smule.android.logging.Log;
import com.smule.singandroid.SingCoreBridge;
import java.nio.ByteBuffer;

public class SamsungAudio extends AudioInterface {
    private static final String f20670b = SingCoreBridge.class.getName();
    private static SapaService f20671c;
    private static SapaProcessor f20672d;

    class C42321 implements StatusListener {
        final /* synthetic */ Activity f20669a;

        public void onKilled() {
            Log.b(SamsungAudio.f20670b, "SapaProcessor killed");
            if (SamsungAudio.f20671c != null) {
                SamsungAudio.f20671c.stop(true);
            }
            if (this.f20669a != null) {
                this.f20669a.finish();
            }
        }
    }

    class C42332 implements MessageListener {
        public void onMessageReceived(int i, String str) {
            if (str != null) {
                Log.b(SamsungAudio.f20670b, "Received message from sapa: " + str);
            }
        }

        public void onDataReceived(int i, ByteBuffer byteBuffer) {
        }
    }

    public void start() {
        if (f20672d != null) {
            f20672d.activate();
        }
    }

    public void stopAndShutdown() {
    }

    public int mo6718e() {
        return f20671c.getParameters().getSampleRate();
    }
}
