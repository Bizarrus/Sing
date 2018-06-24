package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessageTextListItem_ extends ChatMessageTextListItem implements HasViews, OnViewChangedListener {
    private boolean f21413p = false;
    private final OnViewChangedNotifier f21414q = new OnViewChangedNotifier();

    class C43681 implements OnClickListener {
        final /* synthetic */ ChatMessageTextListItem_ f21412a;

        C43681(ChatMessageTextListItem_ chatMessageTextListItem_) {
            this.f21412a = chatMessageTextListItem_;
        }

        public void onClick(View view) {
            this.f21412a.m23005c();
        }
    }

    public ChatMessageTextListItem_(Context context) {
        super(context);
        m23080g();
    }

    public static ChatMessageTextListItem m23079b(Context context) {
        ChatMessageTextListItem chatMessageTextListItem_ = new ChatMessageTextListItem_(context);
        chatMessageTextListItem_.onFinishInflate();
        return chatMessageTextListItem_;
    }

    public void onFinishInflate() {
        if (!this.f21413p) {
            this.f21413p = true;
            inflate(getContext(), C1947R.layout.chat_message_text, this);
            this.f21414q.a(this);
        }
        super.onFinishInflate();
    }

    private void m23080g() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21414q);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23081a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.chat_message_root);
        this.c = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_outer);
        this.d = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_inner);
        this.e = (TextView) hasViews.findViewById(C1947R.id.chat_datestamp);
        this.f = hasViews.findViewById(C1947R.id.chat_message_header_top_margin);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.chat_profile_image_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.user_handle_text_view);
        this.i = (MessageTimestampStatus) hasViews.findViewById(C1947R.id.chat_timestamp);
        this.o = (TextView) hasViews.findViewById(C1947R.id.chat_text_bubble);
        if (this.c != null) {
            this.c.setOnClickListener(new C43681(this));
        }
        m23078f();
    }
}
