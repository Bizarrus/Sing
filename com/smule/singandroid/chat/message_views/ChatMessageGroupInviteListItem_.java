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

public final class ChatMessageGroupInviteListItem_ extends ChatMessageGroupInviteListItem implements HasViews, OnViewChangedListener {
    private boolean f21357p = false;
    private final OnViewChangedNotifier f21358q = new OnViewChangedNotifier();

    class C43561 implements OnClickListener {
        final /* synthetic */ ChatMessageGroupInviteListItem_ f21356a;

        C43561(ChatMessageGroupInviteListItem_ chatMessageGroupInviteListItem_) {
            this.f21356a = chatMessageGroupInviteListItem_;
        }

        public void onClick(View view) {
            this.f21356a.m23005c();
        }
    }

    public ChatMessageGroupInviteListItem_(Context context) {
        super(context);
        m23026f();
    }

    public static ChatMessageGroupInviteListItem m23025a(Context context) {
        ChatMessageGroupInviteListItem chatMessageGroupInviteListItem_ = new ChatMessageGroupInviteListItem_(context);
        chatMessageGroupInviteListItem_.onFinishInflate();
        return chatMessageGroupInviteListItem_;
    }

    public void onFinishInflate() {
        if (!this.f21357p) {
            this.f21357p = true;
            inflate(getContext(), C1947R.layout.chat_message_group_invite, this);
            this.f21358q.a(this);
        }
        super.onFinishInflate();
    }

    private void m23026f() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21358q);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23027a(HasViews hasViews) {
        this.b = hasViews.findViewById(C1947R.id.chat_message_root);
        this.c = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_outer);
        this.d = (LinearLayout) hasViews.findViewById(C1947R.id.message_container_inner);
        this.e = (TextView) hasViews.findViewById(C1947R.id.chat_datestamp);
        this.f = hasViews.findViewById(C1947R.id.chat_message_header_top_margin);
        this.g = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.chat_profile_image_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.user_handle_text_view);
        this.i = (MessageTimestampStatus) hasViews.findViewById(C1947R.id.chat_timestamp);
        this.o = (ChatMessageGroupInviteBody) hasViews.findViewById(C1947R.id.chat_message_body_view_group_invite);
        if (this.c != null) {
            this.c.setOnClickListener(new C43561(this));
        }
    }
}
