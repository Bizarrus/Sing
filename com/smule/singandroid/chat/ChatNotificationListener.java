package com.smule.singandroid.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.StringUtils;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.Type;
import com.smule.chat.GroupChat;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatNotification.Builder;
import com.smule.singandroid.utils.ChatUtils;

public class ChatNotificationListener extends ChatListenerAdapter {
    Context f7117a;

    public ChatNotificationListener(Context context, ChatManager chatManager) {
        this.f7117a = context;
        chatManager.m8562a((ChatListener) this);
    }

    public void mo4046a(Chat chat, ChatMessage chatMessage, boolean z) {
        String str = null;
        if (!z && !chat.r() && !chat.v() && !chat.o() && chat.b() != Bucket.b && !SingApplication.m8802j().m8594f() && !ChatUtils.m8909a(chatMessage) && chatMessage.a() == Type.b) {
            String str2;
            String str3;
            TextChatMessage textChatMessage = (TextChatMessage) chatMessage;
            Intent intent = new Intent();
            String o = textChatMessage.o();
            AccountIcon a = chat.a(chatMessage.b());
            if (a != null) {
                str2 = a.handle + ": " + o;
            } else {
                str2 = o;
            }
            if (chat.a() == Chat.Type.a) {
                str3 = "messages/peer/" + chat.c();
                if (a != null) {
                    str = a.handle;
                    o = a.picUrl;
                } else {
                    o = null;
                }
            } else {
                str3 = "messages/group/" + chat.c();
                o = ((GroupChat) chat).S();
                str2 = "[" + StringUtils.a(o, 12, 3) + "] " + str2;
                str = o;
                o = null;
            }
            intent.setData(Uri.parse("smulesing://" + str3));
            if (str == null || str.isEmpty()) {
                str = "Messages";
            }
            ChatNotification a2 = new Builder(this.f7117a).a(intent).a(str2).b(str).c(textChatMessage.o()).a(C1947R.drawable.icn_push_notification).d(o).a();
            o = chat.c();
            a2.a(o, new 1(this, o));
        }
    }

    public void mo4051d(Chat chat) {
        if (!chat.m()) {
            ChatNotification.b(SingApplication.m8798f(), chat.c());
        }
    }
}
