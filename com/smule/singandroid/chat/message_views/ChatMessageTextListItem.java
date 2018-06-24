package com.smule.singandroid.chat.message_views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.utils.ChatUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessageTextListItem extends ChatMessageBaseListItem {
    @ViewById
    TextView f21411o;

    class C43671 implements OnLongClickListener {
        final /* synthetic */ ChatMessageTextListItem f21410a;

        C43671(ChatMessageTextListItem chatMessageTextListItem) {
            this.f21410a = chatMessageTextListItem;
        }

        public boolean onLongClick(View view) {
            this.f21410a.m23074g();
            return true;
        }
    }

    public ChatMessageTextListItem(Context context) {
        super(context);
    }

    public static ChatMessageTextListItem m23072a(Context context) {
        return ChatMessageTextListItem_.m23079b(context);
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6769a(chat, chatMessage, i);
        boolean b = ChatUtils.b(chatMessage);
        this.f21411o.setBackgroundResource(b ? C1947R.drawable.chat_bubble_me : C1947R.drawable.chat_bubble_others);
        this.f21411o.setTextColor(getContext().getResources().getColor(b ? C1947R.color.body_text_white : C1947R.color.sub_heading_dark));
        m23076a(chatMessage);
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        super.mo6771b(chat, chatMessage, i);
        m23076a(chatMessage);
    }

    protected void m23076a(ChatMessage chatMessage) {
        setupHashTagSpannable(((TextChatMessage) chatMessage).m19687o());
    }

    private void setupHashTagSpannable(CharSequence charSequence) {
        SpannableString spannableString = new SpannableString(charSequence);
        if (charSequence instanceof Spanned) {
            TextUtils.copySpansFrom((Spanned) charSequence, 0, charSequence.length(), null, spannableString, 0);
        }
        Hashtag.m24194a((MediaPlayingActivity) getContext(), spannableString);
        this.f21411o.setMovementMethod(new LinkMovementMethod());
        this.f21411o.setText(spannableString);
        this.f21411o.setHighlightColor(0);
    }

    @AfterViews
    protected void m23078f() {
        this.f21411o.setOnLongClickListener(new C43671(this));
    }

    private void m23074g() {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(a, ((TextChatMessage) this.l).m19687o()));
        Toaster.a(getContext(), getContext().getResources().getString(C1947R.string.chat_copy), Toaster$Duration.SHORT);
    }
}
