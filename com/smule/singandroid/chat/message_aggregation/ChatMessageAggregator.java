package com.smule.singandroid.chat.message_aggregation;

import android.content.Context;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.Type;
import com.smule.chat.GroupStatusChatMessage;
import java.util.ArrayList;
import java.util.List;

public class ChatMessageAggregator {
    Context f21318a;

    public static boolean m22985a(ChatMessage chatMessage, ChatMessage chatMessage2) {
        return chatMessage2.mo6767c().getTime() - chatMessage.mo6767c().getTime() < 60000;
    }

    public ChatMessageAggregator(Context context) {
        this.f21318a = context;
    }

    public List<ChatMessage> m22986a(Chat chat, List<ChatMessage> list, ChatListener chatListener) {
        List<ChatMessage> arrayList = new ArrayList();
        AggregatedChatMessage aggregatedChatMessage = null;
        for (ChatMessage chatMessage : list) {
            if (chatMessage.mo6360a() != Type.GROUP_STATUS) {
                arrayList.add(chatMessage);
                aggregatedChatMessage = null;
            } else if (aggregatedChatMessage == null || !aggregatedChatMessage.mo6766b(chatMessage)) {
                aggregatedChatMessage = new AggregatedGroupStatusChatMessage(this.f21318a, chat, (GroupStatusChatMessage) chatMessage, chatListener);
                arrayList.add(aggregatedChatMessage);
            }
        }
        return arrayList;
    }

    public void m22987a(Chat chat, List<ChatMessage> list, ChatMessage chatMessage, ChatListener chatListener) {
        if (chatMessage.mo6360a() == Type.GROUP_STATUS) {
            ChatMessage chatMessage2;
            if (list.isEmpty()) {
                chatMessage2 = null;
            } else {
                chatMessage2 = (ChatMessage) list.get(list.size() - 1);
            }
            if (chatMessage2 == null || chatMessage2.mo6360a() != Type.GROUP_STATUS || !((AggregatedGroupStatusChatMessage) chatMessage2).mo6766b(chatMessage)) {
                list.add(new AggregatedGroupStatusChatMessage(this.f21318a, chat, (GroupStatusChatMessage) chatMessage, chatListener));
                return;
            }
            return;
        }
        list.add(chatMessage);
    }
}
