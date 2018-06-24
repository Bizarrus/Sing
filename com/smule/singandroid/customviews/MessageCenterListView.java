/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.support.v4.widget.SwipeRefreshLayout$OnRefreshListener
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemLongClickListener
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.listener.PauseOnScrollListener
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.MessageCenterAdapter;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.customviews.MessageCenterListView_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class MessageCenterListView
extends RelativeLayout {
    @ViewById
    public ChatListView a;
    @ViewById
    SwipeRefreshLayout b;
    @ViewById
    public RelativeLayout c;
    @ViewById
    protected TextView d;
    @ViewById
    protected TextView e;
    MessageCenterFragment f;
    MessageCenterAdapter g;
    public Chat.Bucket h;

    public MessageCenterListView(Context context, MessageCenterFragment messageCenterFragment, Chat.Bucket bucket) {
        super(context);
        this.f = messageCenterFragment;
        this.h = bucket;
    }

    public static MessageCenterListView a(Context object, MessageCenterFragment messageCenterFragment, Chat.Bucket bucket) {
        object = MessageCenterListView_.b((Context)object, messageCenterFragment, bucket);
        ReferenceMonitor.a().a(object);
        return object;
    }

    @Click
    protected void a() {
        this.f.H();
    }

    public void b() {
        if (this.c.getVisibility() != 0) {
            return;
        }
        if (this.h == Chat.Bucket.a) {
            this.d.setText((CharSequence)this.getResources().getString(2131296607));
            this.e.setText((CharSequence)this.getResources().getString(2131296609));
            return;
        }
        this.d.setText((CharSequence)this.getResources().getString(2131296608));
        this.e.setText((CharSequence)this.getResources().getString(2131296610));
    }

    @AfterViews
    protected void c() {
        if (this.f == null) {
            return;
        }
        this.g = new MessageCenterAdapter(this.getContext());
        this.a.setAdapter(this.g);
        this.a.setOnItemClickListener((AdapterView.OnItemClickListener)this.f);
        this.a.setOnItemLongClickListener((AdapterView.OnItemLongClickListener)this.f);
        this.a.setOnScrollListener((AbsListView.OnScrollListener)new PauseOnScrollListener(ImageLoader.a(), true, true, new AbsListView.OnScrollListener(){

            public void onScroll(AbsListView absListView, int n, int n2, int n3) {
                MessageCenterListView.this.g.d();
            }

            public void onScrollStateChanged(AbsListView absListView, int n) {
            }
        }));
        this.b.setColorSchemeResources(new int[]{2131689905});
        this.b.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            public void onRefresh() {
                MessageCenterListView.this.b.setRefreshing(true);
                SingApplication.k().a(new Completion<ChatStatus>(){

                    @Override
                    public void a(ChatStatus chatStatus) {
                        if (MessageCenterListView.this.f != null && MessageCenterListView.this.f.isAdded()) {
                            MessageCenterListView.this.b.setRefreshing(false);
                        }
                    }
                });
            }

        });
        this.f.a(this.h, this.a, this.b);
        this.f.F();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f = null;
        this.h = null;
        this.g = null;
    }

}

