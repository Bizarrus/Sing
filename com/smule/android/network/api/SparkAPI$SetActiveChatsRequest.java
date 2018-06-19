package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.SNPChat;
import java.util.Collection;

public class SparkAPI$SetActiveChatsRequest extends SnpRequest {
    public Collection<SNPChat> add;
    public Collection<SNPChat> remove;

    public SparkAPI$SetActiveChatsRequest setAdd(Collection<SNPChat> collection) {
        this.add = collection;
        return this;
    }

    public SparkAPI$SetActiveChatsRequest setRemove(Collection<SNPChat> collection) {
        this.remove = collection;
        return this;
    }
}
