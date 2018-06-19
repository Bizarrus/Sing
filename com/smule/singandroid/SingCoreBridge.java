package com.smule.singandroid;

import android.content.Context;
import com.getkeepsafe.relinker.ReLinker;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.WaveformData;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.models.Pitch;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SingCoreBridge {
    public static final String TAG = SingCoreBridge.class.getName();

    public static native void convertM4AToWAV(String str, String str2, float f);

    public static native int getFileLastAccessedTime(String str);

    public static native String getLyricVersion();

    public static native ArrayList<Lyric> getLyrics(int i);

    public static native List<Lyric> getLyricsForMidi(String str);

    public static native List<Pitch> getPitchEvents(int i);

    public static native WaveformData getWaveformAndAudioEvents(boolean z, boolean z2, String str, float f, int i, int i2, ArrayList<AudioPowerEvent> arrayList, ArrayList<AudioPowerEvent> arrayList2);

    public static native boolean midiHasChordsTrack();

    public static native int oggEncodePCM(String str, String str2, float f, String[] strArr) throws Exception;

    public static native boolean setFileLastAccessedTime(String str, int i);

    public static native boolean setMidiFile(String str);

    public static void loadLibrary(Context context) {
        ReLinker.m7257a(context, "sing");
    }

    public static void makeCacheDirWriteable(Context context) {
        try {
            File file = new File(ResourceUtils.b(SingApplication.m8798f()));
            file.setWritable(true, false);
            file.setExecutable(true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
