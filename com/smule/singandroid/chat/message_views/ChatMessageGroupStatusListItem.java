/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.ChatDate;
import com.smule.singandroid.chat.message_aggregation.AggregatedGroupStatusChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessageGroupStatusListItem_;
import java.util.Date;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageGroupStatusListItem
extends ChatMessageBaseListItem {
    public static final String o = ChatMessageGroupStatusListItem.class.getName();
    AggregatedGroupStatusChatMessage p;
    @ViewById
    TextView q;

    public ChatMessageGroupStatusListItem(Context context) {
        super(context);
    }

    public static ChatMessageGroupStatusListItem a(Context object) {
        object = ChatMessageGroupStatusListItem_.b((Context)object);
        object.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, -2));
        return object;
    }

    @Override
    public void a(Chat chat, ChatMessage chatMessage, int n) {
        super.a(chat, chatMessage, n);
        this.b(chat, chatMessage, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b(Chat object, ChatMessage chatMessage, int n) {
        super.b((Chat)object, chatMessage, n);
        if (chatMessage == null || chatMessage.a() != ChatMessage.Type.g) {
            this.q.setText((CharSequence)"Invalid GroupStatusChatMessage");
            return;
        }
        if (chatMessage instanceof AggregatedGroupStatusChatMessage) {
            this.p = (AggregatedGroupStatusChatMessage)chatMessage;
        } else {
            this.p.d(chatMessage);
        }
        object = new ChatDate(this.p.c(), this.getContext());
        object = this.p.o() + "\n" + object.b();
        this.q.setText((CharSequence)object);
    }
}

