package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionPack {
    public int f6896a;
    public String f6897b;
    @JsonProperty("badge")
    public String badge;
    @JsonProperty("description")
    public String description;
    @JsonProperty("description_key")
    public String descriptionKey;
    @JsonProperty("google_price")
    public String googlePrice;
    @JsonProperty("label")
    public String label;
    @JsonProperty("label_key")
    public String labelKey;
    @JsonProperty("modal_label_key")
    public String modalLabelKey;
    @JsonProperty("modal_trial_label_key")
    public String modalTrialLabelKey;
    @JsonProperty("period")
    public String period;
    @JsonProperty("sku")
    public String sku;
    @JsonProperty("trial")
    public boolean trial;
    @JsonProperty("trial_description_key")
    public String trialDescriptionKey;
    @JsonProperty("trial_label_key")
    public String trialLabelKey;
}
