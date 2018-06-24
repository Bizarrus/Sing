package com.smule.singandroid.survey;

import com.smule.android.core.exception.ErrorHelper;
import com.smule.android.core.exception.IError;

public enum SurveyStateError implements IError {
    NO_RATING_SELECTED_ERROR(1, "Must select a rating"),
    NO_REASON_SELECTED_ERROR(2, "Must select a reason");
    
    private int f24345c;
    private int f24346d;
    private String f24347e;

    private SurveyStateError(int i, String str) {
        this.f24346d = i;
        this.f24347e = str;
        this.f24345c = ErrorHelper.m17582a("SURVEY_PRESENTATION_ERROR_CODE_OFFSET");
    }

    public String mo6239a() {
        return this.f24347e;
    }
}
