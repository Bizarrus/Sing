package com.smule.singandroid.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatNotification$Notifications {
    @JsonProperty("notificationInfo")
    public List<ChatNotification$Info> notifications = new LinkedList();
}
