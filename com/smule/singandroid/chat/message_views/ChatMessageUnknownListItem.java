package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.ChatUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageUnknownListItem extends ChatMessageBaseListItem {
    @ViewById
    TextView f21415o;

    public static ChatMessageUnknownListItem m23082a(Context context) {
        return ChatMessageUnknownListItem_.m23086b(context);
    }

    public ChatMessageUnknownListItem(Context context) {
        super(context);
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6769a(chat, chatMessage, i);
        boolean b = ChatUtils.b(chatMessage);
        this.f21415o.setBackgroundResource(b ? C1947R.drawable.chat_bubble_me : C1947R.drawable.chat_bubble_others);
        this.f21415o.setTextColor(getContext().getResources().getColor(b ? C1947R.color.body_text_white : C1947R.color.sub_heading_dark));
        m23085f();
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6771b(chat, chatMessage, i);
        m23085f();
    }

    protected void m23085f() {
        this.f21415o.setMovementMethod(LinkMovementMethod.getInstance());
        this.f21415o.setText(Html.fromHtml(getResources().getString(C1947R.string.chat_unknown_message_type)));
        this.f21415o.setHighlightColor(0);
    }
}
