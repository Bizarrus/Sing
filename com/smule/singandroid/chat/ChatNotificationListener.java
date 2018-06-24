/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.StringUtils;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupChat;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.chat.ChatNotificationListener;
import com.smule.singandroid.utils.ChatUtils;

public class ChatNotificationListener
extends ChatListenerAdapter {
    Context a;

    public ChatNotificationListener(Context context, ChatManager chatManager) {
        this.a = context;
        chatManager.a(this);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(Chat object, ChatMessage object2, boolean bl) {
        Object object3;
        TextChatMessage textChatMessage;
        Intent intent;
        Object object4;
        String string2;
        block10 : {
            block9 : {
                object4 = null;
                if (bl) return;
                if (object.r() || object.v() || object.o() || object.b() == Chat.Bucket.b || SingApplication.k().f() || ChatUtils.a((ChatMessage)object2) || object2.a() != ChatMessage.Type.b) {
                    return;
                }
                textChatMessage = (TextChatMessage)object2;
                intent = new Intent();
                string2 = textChatMessage.o();
                object3 = object.a(object2.b());
                if (object3 != null) {
                    string2 = object3.handle + ": " + string2;
                }
                if (object.a() == Chat.Type.a) {
                    String string3 = "messages/peer/" + object.c();
                    if (object3 != null) {
                        object2 = object3.handle;
                        object3 = object3.picUrl;
                        object4 = string3;
                    } else {
                        object3 = null;
                        object2 = object4;
                        object4 = string3;
                    }
                } else {
                    object4 = "messages/group/" + object.c();
                    object2 = ((GroupChat)object).S();
                    string2 = "[" + StringUtils.a((String)object2, 12, 3) + "] " + string2;
                    object3 = null;
                }
                intent.setData(Uri.parse((String)("smulesing://" + (String)object4)));
                if (object2 == null) break block9;
                object4 = object2;
                if (!object2.isEmpty()) break block10;
            }
            object4 = "Messages";
        }
        object2 = new ChatNotification.Builder(this.a).a(intent).a(string2).b((String)object4).c(textChatMessage.o()).a(2130837977).d((String)object3).a();
        object = object.c();
        object2.a((String)object, new ChatNotification.NotificationReadyCallback(this, (String)object){
            final /* synthetic */ String a;
            final /* synthetic */ ChatNotificationListener b;
            {
                this.b = chatNotificationListener;
                this.a = string2;
            }

            public void a(android.app.Notification notification) {
                com.smule.android.network.core.MagicNetwork.d().postNotification(this.b.a, this.a, this.a.hashCode(), notification);
            }
        });
    }

    @Override
    public void d(Chat chat) {
        if (!chat.m()) {
            ChatNotification.b(SingApplication.g(), chat.c());
        }
    }
}

