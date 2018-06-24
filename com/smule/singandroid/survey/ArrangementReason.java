package com.smule.singandroid.survey;

import com.smule.singandroid.C1947R;

public enum ArrangementReason implements ReasonInterface {
    WRONG_SONG(3, C1947R.string.reason_wrong_song),
    INCOMPLETE_SONG(4, C1947R.string.reason_incomplete_song),
    WRONG_LYRICS(5, C1947R.string.reason_wrong_lyrics),
    DUET_PARTS(8, C1947R.string.reason_duet_parts),
    GROUP_PARTS(9, C1947R.string.reason_group_parts),
    INCORRECT_TIMING(6, C1947R.string.reason_incorrect_timing),
    VOCAL_TRACK(7, C1947R.string.reason_vocal_track),
    POOR_AUDIO(2, C1947R.string.reason_poor_audio),
    OTHER(1, C1947R.string.reason_other);
    
    private final int f24269j;
    private final int f24270k;

    private ArrangementReason(int i, int i2) {
        this.f24269j = i;
        this.f24270k = i2;
    }

    public int mo6950a() {
        return this.f24269j;
    }

    public int mo6951b() {
        return this.f24270k;
    }
}
