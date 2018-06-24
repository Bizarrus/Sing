package com.smule.singandroid.survey;

import com.smule.singandroid.C1947R;

public enum ArrangementRating implements RatingInterface {
    GOOD(C1947R.drawable.icn_thumbs_up),
    BAD(C1947R.drawable.icn_thumbs_down);
    
    private int f24254c;

    private ArrangementRating(int i) {
        this.f24254c = i;
    }

    public int mo6949a() {
        return this.f24254c;
    }
}
