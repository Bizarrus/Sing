/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

import com.smule.singandroid.audio.AudioPower;
import com.smule.singandroid.audio.AudioPowerEvent;
import com.smule.singandroid.audio.GlitchType;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.audio.ScorePartEvent;
import java.util.ArrayList;

public class WaveformData {
    static final String TAG = Metadata.class.getSimpleName();
    public final AudioPower mAudioPower;
    public final GlitchType mGlitchType;
    public final float mJoinMaxPowerPositionSeconds;
    public final ArrayList<ScorePartEvent> mScorePartEvents;
    public final short[] mWaveformData;

    public WaveformData(short[] object, AudioPower audioPower, ArrayList<ScorePartEvent> arrayList, ArrayList<AudioPowerEvent> arrayList2, float f, GlitchType glitchType) {
        this.mWaveformData = object;
        this.mAudioPower = audioPower;
        this.mScorePartEvents = arrayList;
        this.mJoinMaxPowerPositionSeconds = f;
        object = glitchType;
        if (glitchType == null) {
            object = GlitchType.NONE;
        }
        this.mGlitchType = object;
    }
}

