package com.smule.singandroid.survey;

import com.smule.singandroid.C1947R;

public enum AVQualityRating implements RatingInterface {
    GOOD(C1947R.drawable.good_recording_quality),
    BAD(C1947R.drawable.bad_recording_quality);
    
    private int f24226c;

    private AVQualityRating(int i) {
        this.f24226c = i;
    }

    public int mo6949a() {
        return this.f24226c;
    }
}
