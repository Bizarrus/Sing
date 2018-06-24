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

public final class ChatMessageUnknownListItem_ extends ChatMessageUnknownListItem implements HasViews, OnViewChangedListener {
    private boolean f21417p = false;
    private final OnViewChangedNotifier f21418q = new OnViewChangedNotifier();

    class C43691 implements OnClickListener {
        final /* synthetic */ ChatMessageUnknownListItem_ f21416a;

        C43691(ChatMessageUnknownListItem_ chatMessageUnknownListItem_) {
            this.f21416a = chatMessageUnknownListItem_;
        }

        public void onClick(View view) {
            this.f21416a.m23005c();
        }
    }

    public ChatMessageUnknownListItem_(Context context) {
        super(context);
        m23087g();
    }

    public static ChatMessageUnknownListItem m23086b(Context context) {
        ChatMessageUnknownListItem chatMessageUnknownListItem_ = new ChatMessageUnknownListItem_(context);
        chatMessageUnknownListItem_.onFinishInflate();
        return chatMessageUnknownListItem_;
    }

    public void onFinishInflate() {
        if (!this.f21417p) {
            this.f21417p = true;
            inflate(getContext(), C1947R.layout.chat_message_text, this);
            this.f21418q.a(this);
        }
        super.onFinishInflate();
    }

    private void m23087g() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21418q);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23088a(HasViews hasViews) {
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
            this.c.setOnClickListener(new C43691(this));
        }
    }
}
