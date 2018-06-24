/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.singandroid.chat.message_aggregation;

import android.content.Context;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.singandroid.chat.message_aggregation.AggregatedGroupStatusChatMessage;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ChatMessageAggregator {
    Context a;

    public ChatMessageAggregator(Context context) {
        this.a = context;
    }

    public static boolean a(ChatMessage chatMessage, ChatMessage chatMessage2) {
        if (chatMessage2.c().getTime() - chatMessage.c().getTime() < 60000) {
            return true;
        }
        return false;
    }

    public List<ChatMessage> a(Chat chat, List<ChatMessage> object, ChatListener chatListener) {
        ArrayList<ChatMessage> arrayList = new ArrayList<ChatMessage>();
        Iterator<ChatMessage> iterator = object.iterator();
        object = null;
        while (iterator.hasNext()) {
            ChatMessage chatMessage = iterator.next();
            if (chatMessage.a() == ChatMessage.Type.g) {
                if (object != null && object.b(chatMessage)) continue;
                object = new AggregatedGroupStatusChatMessage(this.a, chat, (GroupStatusChatMessage)chatMessage, chatListener);
                arrayList.add((ChatMessage)object);
                continue;
            }
            arrayList.add(chatMessage);
            object = null;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Chat chat, List<ChatMessage> list, ChatMessage chatMessage, ChatListener chatListener) {
        if (chatMessage.a() != ChatMessage.Type.g) {
            list.add(chatMessage);
            return;
        }
        ChatMessage chatMessage2 = list.isEmpty() ? null : list.get(list.size() - 1);
        if (chatMessage2 != null && chatMessage2.a() == ChatMessage.Type.g && ((AggregatedGroupStatusChatMessage)chatMessage2).b(chatMessage)) {
            return;
        }
        list.add(new AggregatedGroupStatusChatMessage(this.a, chat, (GroupStatusChatMessage)chatMessage, chatListener));
    }
}

