package com.smule.singandroid.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.smule.chat.Chat;
import java.util.List;

public class ChatListView extends ListView {
    public static final String f20938a = ChatListView.class.getName();
    protected ChatListViewAdapter f20939b;

    public static abstract class ChatListViewAdapter extends BaseAdapter {
        public abstract int mo6757a();

        public abstract void mo6758a(List<Chat> list);
    }

    public ChatListView(Context context) {
        this(context, null);
    }

    public ChatListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChatListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setVisibility(int i) {
        Object obj = getVisibility() != i ? 1 : null;
        super.setVisibility(i);
        if (obj != null) {
            this.f20939b.notifyDataSetChanged();
        }
    }

    public void setAdapter(ChatListViewAdapter chatListViewAdapter) {
        super.setAdapter(chatListViewAdapter);
        this.f20939b = chatListViewAdapter;
    }

    public void m22620a(List<Chat> list) {
        if (this.f20939b != null) {
            this.f20939b.mo6758a(list);
        }
    }

    public int getUnreadCount() {
        return this.f20939b.mo6757a();
    }

    public ChatListViewAdapter getAdapter() {
        return this.f20939b;
    }
}
