package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.SNPChat;
import java.util.List;

public class SparkAPI$SetMuteStateRequest extends SnpRequest {
    public List<SNPChat> add;
    public List<SNPChat> remove;

    public SparkAPI$SetMuteStateRequest setAdd(List<SNPChat> list) {
        this.add = list;
        return this;
    }

    public SparkAPI$SetMuteStateRequest setRemove(List<SNPChat> list) {
        this.remove = list;
        return this;
    }
}
