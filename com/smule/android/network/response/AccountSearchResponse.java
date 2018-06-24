/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import java.util.List;

public class AccountSearchResponse
extends ParsedResponse {
    @JsonProperty(value="accountApps")
    public List<AccountApps> accountAppsList;
    @JsonProperty(value="accounts")
    public List<AccountIcon> accountIcons;
    @JsonProperty(value="next")
    public int next;
}

