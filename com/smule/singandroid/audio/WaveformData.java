package com.smule.singandroid.audio;

import java.util.ArrayList;

public class WaveformData {
    static final String TAG = Metadata.class.getSimpleName();
    public final AudioPower mAudioPower;
    public final GlitchType mGlitchType;
    public final float mJoinMaxPowerPositionSeconds;
    public final ArrayList<ScorePartEvent> mScorePartEvents;
    public final short[] mWaveformData;

    public WaveformData(short[] sArr, AudioPower audioPower, ArrayList<ScorePartEvent> arrayList, ArrayList<AudioPowerEvent> arrayList2, float f, GlitchType glitchType) {
        this.mWaveformData = sArr;
        this.mAudioPower = audioPower;
        this.mScorePartEvents = arrayList;
        this.mJoinMaxPowerPositionSeconds = f;
        if (glitchType == null) {
            glitchType = GlitchType.NONE;
        }
        this.mGlitchType = glitchType;
    }
}
