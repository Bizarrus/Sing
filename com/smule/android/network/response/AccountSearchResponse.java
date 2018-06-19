package com.smule.android.network.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.AccountApps;
import com.smule.android.network.models.AccountIcon;
import java.util.List;

public class AccountSearchResponse extends ParsedResponse {
    @JsonProperty("accountApps")
    public List<AccountApps> accountAppsList;
    @JsonProperty("accounts")
    public List<AccountIcon> accountIcons;
    @JsonProperty("next")
    public int next;
}
