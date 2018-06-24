/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.Spannable
 *  android.text.SpannableString
 *  android.text.Spanned
 *  android.text.TextUtils
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.view.View
 *  android.view.View$OnLongClickListener
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.view.View;
import android.widget.TextView;
import com.smule.android.utils.Toaster;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessageTextListItem_;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.ChatUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageTextListItem
extends ChatMessageBaseListItem {
    @ViewById
    TextView o;

    public ChatMessageTextListItem(Context context) {
        super(context);
    }

    public static ChatMessageTextListItem a(Context context) {
        return ChatMessageTextListItem_.b(context);
    }

    private void g() {
        ((ClipboardManager)this.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)a, (CharSequence)((TextChatMessage)this.l).o()));
        com.smule.android.utils.Toaster.a(this.getContext(), this.getContext().getResources().getString(2131296476), Toaster.a);
    }

    private void setupHashTagSpannable(CharSequence charSequence) {
        SpannableString spannableString = new SpannableString(charSequence);
        if (charSequence instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned)((Spanned)charSequence), (int)0, (int)charSequence.length(), (Class)null, (Spannable)spannableString, (int)0);
        }
        Hashtag.a((MediaPlayingActivity)this.getContext(), spannableString);
        this.o.setMovementMethod((MovementMethod)new LinkMovementMethod());
        this.o.setText((CharSequence)spannableString);
        this.o.setHighlightColor(0);
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
        Resources resources = this.getContext().getResources();
        n = bl ? 2131689549 : 2131689947;
        chat.setTextColor(resources.getColor(n));
        this.a(chatMessage);
    }

    protected void a(ChatMessage chatMessage) {
        this.setupHashTagSpannable(((TextChatMessage)chatMessage).o());
    }

    @Override
    public void b(Chat chat, ChatMessage chatMessage, int n) {
        super.b(chat, chatMessage, n);
        this.a(chatMessage);
    }

    @AfterViews
    protected void f() {
        this.o.setOnLongClickListener(new View.OnLongClickListener(){

            public boolean onLongClick(View view) {
                ChatMessageTextListItem.this.g();
                return true;
            }
        });
    }

}

