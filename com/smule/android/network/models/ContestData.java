/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.bluelinelabs.logansquare.annotation.JsonField
 *  com.bluelinelabs.logansquare.annotation.JsonObject
 *  com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.bluelinelabs.logansquare.typeconverters.IntBasedTypeConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.models.AccountIcon;
import java.util.ArrayList;
import java.util.Date;

public class ContestData {

    public static enum SubmitState {
        NOT_SUBMITTED,
        SUBMIT_SUCCESS,
        SUBMIT_EXPIRED,
        SUBMIT_ERROR;
        

        private SubmitState() {
        }

        public static class TypeConverter
        extends IntBasedTypeConverter<SubmitState> {
            public int convertToInt(SubmitState submitState) {
                return submitState.ordinal();
            }

            public SubmitState getFromInt(int n) {
                return SubmitState.values()[n];
            }
        }

    }

}

