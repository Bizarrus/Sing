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

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubscriptionPack {
    public int a;
    public String b;
    @JsonProperty(value="badge")
    public String badge;
    @JsonProperty(value="description")
    public String description;
    @JsonProperty(value="description_key")
    public String descriptionKey;
    @JsonProperty(value="google_price")
    public String googlePrice;
    @JsonProperty(value="label")
    public String label;
    @JsonProperty(value="label_key")
    public String labelKey;
    @JsonProperty(value="modal_label_key")
    public String modalLabelKey;
    @JsonProperty(value="modal_trial_label_key")
    public String modalTrialLabelKey;
    @JsonProperty(value="period")
    public String period;
    @JsonProperty(value="sku")
    public String sku;
    @JsonProperty(value="trial")
    public boolean trial;
    @JsonProperty(value="trial_description_key")
    public String trialDescriptionKey;
    @JsonProperty(value="trial_label_key")
    public String trialLabelKey;
}

