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

public final class ChatMessageGroupStatusListItem_ extends ChatMessageGroupStatusListItem implements HasViews, OnViewChangedListener {
    private boolean f21363r = false;
    private final OnViewChangedNotifier f21364s = new OnViewChangedNotifier();

    class C43571 implements OnClickListener {
        final /* synthetic */ ChatMessageGroupStatusListItem_ f21362a;

        C43571(ChatMessageGroupStatusListItem_ chatMessageGroupStatusListItem_) {
            this.f21362a = chatMessageGroupStatusListItem_;
        }

        public void onClick(View view) {
            this.f21362a.m23005c();
        }
    }

    public ChatMessageGroupStatusListItem_(Context context) {
        super(context);
        m23032f();
    }

    public static ChatMessageGroupStatusListItem m23031b(Context context) {
        ChatMessageGroupStatusListItem chatMessageGroupStatusListItem_ = new ChatMessageGroupStatusListItem_(context);
        chatMessageGroupStatusListItem_.onFinishInflate();
        return chatMessageGroupStatusListItem_;
    }

    public void onFinishInflate() {
        if (!this.f21363r) {
            this.f21363r = true;
            inflate(getContext(), C1947R.layout.chat_message_group_status, this);
            this.f21364s.a(this);
        }
        super.onFinishInflate();
    }

    private void m23032f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21364s);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23033a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.chat_message_root);
        this.c = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_outer);
        this.d = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_inner);
        this.e = (TextView) hasViews.findViewById(C1947R.id.chat_datestamp);
        this.f = hasViews.findViewById(C1947R.id.chat_message_header_top_margin);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.chat_profile_image_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.user_handle_text_view);
        this.i = (MessageTimestampStatus) hasViews.findViewById(C1947R.id.chat_timestamp);
        this.q = (TextView) hasViews.findViewById(C1947R.id.text_view);
        if (this.c != null) {
            this.c.setOnClickListener(new C43571(this));
        }
    }
}
