/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.widget.Adapter
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.smule.chat.Chat;
import java.util.List;

public class ChatListView
extends ListView {
    public static final String a = ChatListView.class.getName();
    protected ChatListViewAdapter b;

    public ChatListView(Context context) {
        this(context, null);
    }

    public ChatListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChatListView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public void a(List<Chat> list) {
        if (this.b != null) {
            this.b.a(list);
        }
    }

    public ChatListViewAdapter getAdapter() {
        return this.b;
    }

    public int getUnreadCount() {
        return this.b.a();
    }

    public void setAdapter(ChatListViewAdapter chatListViewAdapter) {
        super.setAdapter((ListAdapter)chatListViewAdapter);
        this.b = chatListViewAdapter;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVisibility(int n) {
        boolean bl = this.getVisibility() != n;
        super.setVisibility(n);
        if (bl) {
            this.b.notifyDataSetChanged();
        }
    }

    public static abstract class ChatListViewAdapter
    extends BaseAdapter {
        public abstract int a();

        public abstract void a(List<Chat> var1);
    }

}

