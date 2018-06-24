package com.smule.singandroid.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatListView.ChatListViewAdapter;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeaveChatsAdapter extends ChatListViewAdapter {
    private final LayoutInflater f21071a;
    private final List<Chat> f21072b = new ArrayList();
    private final Set<Chat> f21073c = new HashSet();

    public static class ViewHolder {
        public final ChatMultiplePortraitFlipView f21066a;
        public final TextView f21067b;
        public final TextView f21068c;
        public final CheckBox f21069d;
        public int f21070e;

        public ViewHolder(View view) {
            this.f21066a = (ChatMultiplePortraitFlipView) view.findViewById(C1947R.id.multiple_portrait_profile_image);
            this.f21067b = (TextView) view.findViewById(C1947R.id.chat_title);
            this.f21068c = (TextView) view.findViewById(C1947R.id.chat_timestamp);
            this.f21069d = (CheckBox) view.findViewById(C1947R.id.chat_selected);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m22744a(i);
    }

    public LeaveChatsAdapter(Context context) {
        this.f21071a = LayoutInflater.from(context);
    }

    public void mo6758a(List<Chat> list) {
        if (list != null) {
            this.f21072b.clear();
            this.f21072b.addAll(list);
            this.f21073c.clear();
        }
        notifyDataSetChanged();
    }

    public int mo6757a() {
        return 0;
    }

    public int getCount() {
        return this.f21072b.size();
    }

    public Chat m22744a(int i) {
        return (Chat) this.f21072b.get(i);
    }

    public long getItemId(int i) {
        return (long) ((Chat) this.f21072b.get(i)).hashCode();
    }

    public int m22749b() {
        return this.f21073c.size();
    }

    public Collection<Chat> m22750c() {
        return this.f21073c;
    }

    public boolean m22748a(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        boolean z = !viewHolder.f21069d.isChecked();
        viewHolder.f21069d.setChecked(z);
        Chat a = m22744a(viewHolder.f21070e);
        if (z) {
            this.f21073c.add(a);
        } else {
            this.f21073c.remove(a);
        }
        if (this.f21073c.size() > 0) {
            return true;
        }
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        ViewHolder a;
        if (view == null) {
            inflate = this.f21071a.inflate(C1947R.layout.chat_selectable_list_item, viewGroup, false);
            a = m22745a((ViewGroup) inflate);
            inflate.setTag(a);
        } else {
            a = (ViewHolder) view.getTag();
            inflate = view;
        }
        m22746a(a, i);
        return inflate;
    }

    public ViewHolder m22745a(ViewGroup viewGroup) {
        return new ViewHolder(viewGroup);
    }

    public void m22746a(ViewHolder viewHolder, int i) {
        Chat chat = (Chat) this.f21072b.get(i);
        CharSequence S = ((GroupChat) chat).mo6334S();
        TextView textView = viewHolder.f21067b;
        if (S == null || S.isEmpty()) {
            S = "[Room Name]";
        }
        textView.setText(S);
        viewHolder.f21066a.setChat(chat);
        viewHolder.f21069d.setChecked(this.f21073c.contains(chat));
        viewHolder.f21070e = i;
    }
}
