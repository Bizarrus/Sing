package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class TopicsAPI$SelectTopicsRequest extends SnpRequest {
    public Boolean compositionChoices;
    public List<Integer> topicIds;

    public TopicsAPI$SelectTopicsRequest setTopicIds(List<Integer> list) {
        this.topicIds = list;
        return this;
    }

    public TopicsAPI$SelectTopicsRequest setCompositionChoices(Boolean bool) {
        this.compositionChoices = bool;
        return this;
    }
}
