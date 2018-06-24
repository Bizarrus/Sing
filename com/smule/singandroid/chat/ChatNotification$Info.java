package com.smule.singandroid.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatNotification$Info {
    @JsonProperty("jid")
    public String jid;
    @JsonProperty("messages")
    public List<String> messages = new ArrayList();
    @JsonProperty("tag")
    public String tag;
}
