/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OfferModel
extends ParsedResponse {
    @JsonProperty
    public boolean eligible;
    @JsonProperty
    public Integer reward;
    @JsonProperty
    public String trigger;

    public static OfferModel a(NetworkResponse networkResponse) {
        return OfferModel.a(networkResponse, OfferModel.class);
    }
}

