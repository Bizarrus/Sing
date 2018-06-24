/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.Card;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateCardResponse
extends ParsedResponse {
    @JsonProperty(value="card")
    public Card mCard;
}

