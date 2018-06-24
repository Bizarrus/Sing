/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  com.getkeepsafe.relinker.ReLinker
 */
package com.smule.singandroid;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.WaveformData;
import com.smule.singandroid.audio.exception.BadAlloc;
import com.smule.singandroid.audio.exception.NativeException;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.Pitch;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SingCoreBridge {
    public static final String TAG = SingCoreBridge.class.getName();

    public static native void convertM4AToWAV(String var0, String var1, float var2);

    public static native float estimateLatency(String var0, String var1) throws NativeException;

    public static native int getFileLastAccessedTime(String var0);

    public static native String getLyricVersion();

    public static native ArrayList<Lyric> getLyrics(int var0);

    public static native List<Lyric> getLyricsForMidi(String var0);

    public static native List<Pitch> getPitchEvents(int var0);

    public static native WaveformData getWaveformAndAudioEvents(boolean var0, boolean var1, String var2, float var3, int var4, int var5, List<AudioPowerEvent> var6, List<AudioPowerEvent> var7);

    public static void loadLibrary(Context context) {
        ReLinker.a((Context)context, (String)"sing");
    }

    public static void makeCacheDirWriteable(Context object) {
        try {
            object = new File(ResourceUtils.b(SingApplication.g()));
            object.setWritable(true, false);
            object.setExecutable(true, false);
            return;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
    }

    public static native boolean midiHasChordsTrack();

    public static native int oggEncodePCM(String var0, String var1, float var2, String[] var3) throws BadAlloc, Exception;

    public static native byte predictRobotVoice(float[] var0, String var1) throws NativeException;

    public static native boolean setFileLastAccessedTime(String var0, int var1);

    public static native boolean setMidiFile(String var0);
}

