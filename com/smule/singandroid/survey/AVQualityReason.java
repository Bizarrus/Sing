package com.smule.singandroid.survey;

import com.smule.singandroid.C1947R;

public enum AVQualityReason implements ReasonInterface {
    OTHER(1, C1947R.string.av_survey_other),
    MUSIC_PLAYBACK_OFF(2, C1947R.string.av_survey_music_playback_off),
    VIDEO_QUALITY_BAD(3, C1947R.string.av_survey_video_quality_bad),
    RECORDING_SOUNDED_BROKEN(4, C1947R.string.av_survey_recording_sounded_broken),
    SINGING_TOO_LOUD_OR_SOFT(5, C1947R.string.av_survey_singing_too_loud_or_soft),
    SINGING_OUT_OF_SYNC(6, C1947R.string.av_survey_singing_out_of_sync),
    AUDIO_VIDEO_OUT_OF_SYNC(7, C1947R.string.av_survey_audio_video_out_of_sync),
    UNABLE_TO_ADJUST_MIC_VOLUME(8, C1947R.string.av_survey_unable_to_adjust_mic_volume),
    UPLOAD_DOES_NOT_MATCH(9, C1947R.string.av_survey_upload_does_not_match);
    
    private final int f24237j;
    private final int f24238k;

    private AVQualityReason(int i, int i2) {
        this.f24237j = i;
        this.f24238k = i2;
    }

    public int mo6950a() {
        return this.f24237j;
    }

    public int mo6951b() {
        return this.f24238k;
    }
}
