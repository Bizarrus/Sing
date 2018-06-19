package com.smule.android.network.managers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.SNPChat;
import java.util.List;

public class SparkManager$OfflineMessageResponse extends ParsedResponse {
    @JsonProperty("inbox")
    public List<SNPChat> inbox;
    @JsonProperty("other")
    public List<SNPChat> other;
}
