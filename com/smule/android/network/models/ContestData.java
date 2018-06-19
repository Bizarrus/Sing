package com.smule.android.network.models;

import com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter;

public class ContestData {

    public enum SubmitState {
        NOT_SUBMITTED,
        SUBMIT_SUCCESS,
        SUBMIT_EXPIRED,
        SUBMIT_ERROR;

        public static class TypeConverter extends IntBasedTypeConverter<SubmitState> {
            public SubmitState getFromInt(int i) {
                return SubmitState.values()[i];
            }

            public int convertToInt(SubmitState submitState) {
                return submitState.ordinal();
            }
        }
    }
}
