package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.MessageCenterAdapter;
import com.smule.singandroid.chat.MessageCenterFragment;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class MessageCenterListView extends RelativeLayout {
    @ViewById
    public ChatListView f21603a;
    @ViewById
    SwipeRefreshLayout f21604b;
    @ViewById
    public RelativeLayout f21605c;
    @ViewById
    protected TextView f21606d;
    @ViewById
    protected TextView f21607e;
    MessageCenterFragment f21608f;
    MessageCenterAdapter f21609g;
    public Bucket f21610h;

    class C43931 implements OnScrollListener {
        final /* synthetic */ MessageCenterListView f21600a;

        C43931(MessageCenterListView messageCenterListView) {
            this.f21600a = messageCenterListView;
        }

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            this.f21600a.f21609g.m22785d();
        }
    }

    class C43952 implements OnRefreshListener {
        final /* synthetic */ MessageCenterListView f21602a;

        class C43941 implements Completion<ChatStatus> {
            final /* synthetic */ C43952 f21601a;

            C43941(C43952 c43952) {
                this.f21601a = c43952;
            }

            public void m23237a(ChatStatus chatStatus) {
                if (this.f21601a.f21602a.f21608f != null && this.f21601a.f21602a.f21608f.isAdded()) {
                    this.f21601a.f21602a.f21604b.setRefreshing(false);
                }
            }
        }

        C43952(MessageCenterListView messageCenterListView) {
            this.f21602a = messageCenterListView;
        }

        public void onRefresh() {
            this.f21602a.f21604b.setRefreshing(true);
            SingApplication.j().a(new C43941(this));
        }
    }

    public MessageCenterListView(Context context, MessageCenterFragment messageCenterFragment, Bucket bucket) {
        super(context);
        this.f21608f = messageCenterFragment;
        this.f21610h = bucket;
    }

    public static MessageCenterListView m23239a(Context context, MessageCenterFragment messageCenterFragment, Bucket bucket) {
        MessageCenterListView b = MessageCenterListView_.m23243b(context, messageCenterFragment, bucket);
        ReferenceMonitor.a().a(b);
        return b;
    }

    @Click
    protected void m23240a() {
        this.f21608f.m22828C();
    }

    public void m23241b() {
        if (this.f21605c.getVisibility() == 0) {
            if (this.f21610h == Bucket.INBOX) {
                this.f21606d.setText(getResources().getString(C1947R.string.chat_tooltip_message_center_header));
                this.f21607e.setText(getResources().getString(C1947R.string.chat_tooltip_message_center_header_subtitle));
                return;
            }
            this.f21606d.setText(getResources().getString(C1947R.string.chat_tooltip_message_center_header_other));
            this.f21607e.setText(getResources().getString(C1947R.string.chat_tooltip_message_center_header_subtitle_other));
        }
    }

    @AfterViews
    protected void m23242c() {
        if (this.f21608f != null) {
            this.f21609g = new MessageCenterAdapter(getContext());
            this.f21603a.setAdapter(this.f21609g);
            this.f21603a.setOnItemClickListener(this.f21608f);
            this.f21603a.setOnItemLongClickListener(this.f21608f);
            this.f21603a.setOnScrollListener(new PauseOnScrollListener(ImageLoader.a(), true, true, new C43931(this)));
            this.f21604b.setColorSchemeResources(new int[]{C1947R.color.refresh_icon});
            this.f21604b.setOnRefreshListener(new C43952(this));
            this.f21608f.m22831a(this.f21610h, this.f21603a, this.f21604b);
            this.f21608f.m22826A();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f21608f = null;
        this.f21610h = null;
        this.f21609g = null;
    }
}
