/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.Html
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessageUnknownListItem_;
import com.smule.singandroid.utils.ChatUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageUnknownListItem
extends ChatMessageBaseListItem {
    @ViewById
    TextView o;

    public ChatMessageUnknownListItem(Context context) {
        super(context);
    }

    public static ChatMessageUnknownListItem a(Context context) {
        return ChatMessageUnknownListItem_.b(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(Chat chat, ChatMessage chatMessage, int n) {
        super.a(chat, chatMessage, n);
        boolean bl = ChatUtils.b(chatMessage);
        chat = this.o;
        n = bl ? 2130837682 : 2130837683;
        chat.setBackgroundResource(n);
        chat = this.o;
        chatMessage = this.getContext().getResources();
        n = bl ? 2131689549 : 2131689947;
        chat.setTextColor(chatMessage.getColor(n));
        this.f();
    }

    @Override
    public void b(Chat chat, ChatMessage chatMessage, int n) {
        super.b(chat, chatMessage, n);
        this.f();
    }

    protected void f() {
        this.o.setMovementMethod(LinkMovementMethod.getInstance());
        this.o.setText((CharSequence)Html.fromHtml((String)this.getResources().getString(2131296618)));
        this.o.setHighlightColor(0);
    }
}

