package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.widget.AbsListView.LayoutParams;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.Type;
import com.smule.singandroid.chat.ChatDate;
import com.smule.singandroid.chat.message_aggregation.AggregatedGroupStatusChatMessage;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageGroupStatusListItem extends ChatMessageBaseListItem {
    public static final String f21359o = ChatMessageGroupStatusListItem.class.getName();
    AggregatedGroupStatusChatMessage f21360p;
    @ViewById
    TextView f21361q;

    public ChatMessageGroupStatusListItem(Context context) {
        super(context);
    }

    public static ChatMessageGroupStatusListItem m23028a(Context context) {
        ChatMessageGroupStatusListItem b = ChatMessageGroupStatusListItem_.m23031b(context);
        b.setLayoutParams(new LayoutParams(-1, -2));
        return b;
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6769a(chat, chatMessage, i);
        mo6771b(chat, chatMessage, i);
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6771b(chat, chatMessage, i);
        if (chatMessage == null || chatMessage.mo6360a() != Type.GROUP_STATUS) {
            this.f21361q.setText("Invalid GroupStatusChatMessage");
            return;
        }
        if (chatMessage instanceof AggregatedGroupStatusChatMessage) {
            this.f21360p = (AggregatedGroupStatusChatMessage) chatMessage;
        } else {
            this.f21360p.m22981d(chatMessage);
        }
        this.f21361q.setText(this.f21360p.m22983o() + "\n" + new ChatDate(this.f21360p.mo6767c(), getContext()).m22434b());
    }
}
