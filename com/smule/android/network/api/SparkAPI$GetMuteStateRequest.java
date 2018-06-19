package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.SNPChat;
import java.util.Collection;

public class SparkAPI$GetMuteStateRequest extends SnpRequest {
    public Collection<SNPChat> chats;

    public SparkAPI$GetMuteStateRequest setChats(Collection<SNPChat> collection) {
        this.chats = collection;
        return this;
    }
}
