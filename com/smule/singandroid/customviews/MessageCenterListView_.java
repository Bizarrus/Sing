package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.chat.Chat.Bucket;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.MessageCenterFragment;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class MessageCenterListView_ extends MessageCenterListView implements HasViews, OnViewChangedListener {
    private boolean f21612i = false;
    private final OnViewChangedNotifier f21613j = new OnViewChangedNotifier();

    class C43961 implements OnClickListener {
        final /* synthetic */ MessageCenterListView_ f21611a;

        C43961(MessageCenterListView_ messageCenterListView_) {
            this.f21611a = messageCenterListView_;
        }

        public void onClick(View view) {
            this.f21611a.m23240a();
        }
    }

    public MessageCenterListView_(Context context, MessageCenterFragment messageCenterFragment, Bucket bucket) {
        super(context, messageCenterFragment, bucket);
        m23244d();
    }

    public void onFinishInflate() {
        if (!this.f21612i) {
            this.f21612i = true;
            inflate(getContext(), C1947R.layout.message_center_list_view, this);
            this.f21613j.a(this);
        }
        super.onFinishInflate();
    }

    private void m23244d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21613j);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public static MessageCenterListView m23243b(Context context, MessageCenterFragment messageCenterFragment, Bucket bucket) {
        MessageCenterListView messageCenterListView_ = new MessageCenterListView_(context, messageCenterFragment, bucket);
        messageCenterListView_.onFinishInflate();
        return messageCenterListView_;
    }

    public void m23245a(HasViews hasViews) {
        this.a = (ChatListView) hasViews.findViewById(C1947R.id.chats_list);
        this.b = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.refresh_layout);
        this.c = (RelativeLayout) hasViews.findViewById(C1947R.id.tooltip_header);
        this.d = (TextView) hasViews.findViewById(C1947R.id.tooltip_header_title);
        this.e = (TextView) hasViews.findViewById(C1947R.id.tooltip_header_subtitle);
        View findViewById = hasViews.findViewById(C1947R.id.tooltip_header_cancel);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C43961(this));
        }
        m23242c();
    }
}
