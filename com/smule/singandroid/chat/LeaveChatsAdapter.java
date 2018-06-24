/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.CheckBox
 *  android.widget.TextView
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeaveChatsAdapter
extends ChatListView.ChatListViewAdapter {
    private final LayoutInflater a;
    private final List<Chat> b = new ArrayList<Chat>();
    private final Set<Chat> c = new HashSet<Chat>();

    public LeaveChatsAdapter(Context context) {
        this.a = LayoutInflater.from((Context)context);
    }

    @Override
    public int a() {
        return 0;
    }

    public Chat a(int n) {
        return this.b.get(n);
    }

    public ViewHolder a(ViewGroup viewGroup) {
        return new ViewHolder((View)viewGroup);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ViewHolder viewHolder, int n) {
        Chat chat = this.b.get(n);
        String string2 = ((GroupChat)chat).S();
        TextView textView = viewHolder.b;
        if (string2 == null || string2.isEmpty()) {
            string2 = "[Room Name]";
        }
        textView.setText((CharSequence)string2);
        viewHolder.a.setChat(chat);
        viewHolder.d.setChecked(this.c.contains(chat));
        viewHolder.e = n;
    }

    @Override
    public void a(List<Chat> list) {
        if (list != null) {
            this.b.clear();
            this.b.addAll(list);
            this.c.clear();
        }
        this.notifyDataSetChanged();
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean a(View object) {
        object = (ViewHolder)object.getTag();
        boolean bl = !object.d.isChecked();
        object.d.setChecked(bl);
        object = this.a(object.e);
        if (bl) {
            this.c.add((Chat)object);
        } else {
            this.c.remove(object);
        }
        if (this.c.size() > 0) {
            return true;
        }
        return false;
    }

    public int b() {
        return this.c.size();
    }

    public Collection<Chat> c() {
        return this.c;
    }

    public int getCount() {
        return this.b.size();
    }

    public /* synthetic */ Object getItem(int n) {
        return this.a(n);
    }

    public long getItemId(int n) {
        return this.b.get(n).hashCode();
    }

    /*
     * Enabled aggressive block sorting
     */
    public View getView(int n, View object, ViewGroup object2) {
        if (object == null) {
            object2 = this.a.inflate(2130903118, (ViewGroup)object2, false);
            object = this.a((ViewGroup)object2);
            object2.setTag(object);
        } else {
            ViewHolder viewHolder = (ViewHolder)object.getTag();
            object2 = object;
            object = viewHolder;
        }
        this.a((ViewHolder)object, n);
        return object2;
    }

    public static class ViewHolder {
        public final ChatMultiplePortraitFlipView a;
        public final TextView b;
        public final TextView c;
        public final CheckBox d;
        public int e;

        public ViewHolder(View view) {
            this.a = (ChatMultiplePortraitFlipView)view.findViewById(2131755346);
            this.b = (TextView)view.findViewById(2131755418);
            this.c = (TextView)view.findViewById(2131755417);
            this.d = (CheckBox)view.findViewById(2131755445);
        }
    }

}

