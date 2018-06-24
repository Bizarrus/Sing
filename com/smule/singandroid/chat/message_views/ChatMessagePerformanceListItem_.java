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

public final class ChatMessagePerformanceListItem_ extends ChatMessagePerformanceListItem implements HasViews, OnViewChangedListener {
    private boolean f21408p = false;
    private final OnViewChangedNotifier f21409q = new OnViewChangedNotifier();

    class C43661 implements OnClickListener {
        final /* synthetic */ ChatMessagePerformanceListItem_ f21407a;

        C43661(ChatMessagePerformanceListItem_ chatMessagePerformanceListItem_) {
            this.f21407a = chatMessagePerformanceListItem_;
        }

        public void onClick(View view) {
            this.f21407a.m23005c();
        }
    }

    public ChatMessagePerformanceListItem_(Context context) {
        super(context);
        m23070f();
    }

    public static ChatMessagePerformanceListItem m23069b(Context context) {
        ChatMessagePerformanceListItem chatMessagePerformanceListItem_ = new ChatMessagePerformanceListItem_(context);
        chatMessagePerformanceListItem_.onFinishInflate();
        return chatMessagePerformanceListItem_;
    }

    public void onFinishInflate() {
        if (!this.f21408p) {
            this.f21408p = true;
            inflate(getContext(), C1947R.layout.chat_message_performance, this);
            this.f21409q.a(this);
        }
        super.onFinishInflate();
    }

    private void m23070f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21409q);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23071a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.chat_message_root);
        this.c = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_outer);
        this.d = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_inner);
        this.e = (TextView) hasViews.findViewById(C1947R.id.chat_datestamp);
        this.f = hasViews.findViewById(C1947R.id.chat_message_header_top_margin);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.chat_profile_image_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.user_handle_text_view);
        this.i = (MessageTimestampStatus) hasViews.findViewById(C1947R.id.chat_timestamp);
        this.o = (ChatMessagePerformanceBody) hasViews.findViewById(C1947R.id.chat_message_body_view_performance);
        if (this.c != null) {
            this.c.setOnClickListener(new C43661(this));
        }
    }
}
