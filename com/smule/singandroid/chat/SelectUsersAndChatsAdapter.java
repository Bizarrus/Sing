/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.CheckBox
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.foound.widget.AmazingAdapter
 *  com.foound.widget.AmazingListView
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.foound.widget.AmazingAdapter;
import com.foound.widget.AmazingListView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.SelectedUsersAndChatsAdapter;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class SelectUsersAndChatsAdapter
extends AmazingAdapter {
    public final String h = SelectUsersAndChatsAdapter.class.getName();
    protected final int i = 0;
    protected final int j = 1;
    protected final Context k;
    protected final LayoutInflater l;
    protected boolean m;
    protected String[] n;
    protected final List<Chat> o = new ArrayList<Chat>();
    protected final List<AccountIcon> p = new ArrayList<AccountIcon>();
    protected final List<AccountIcon> q = new ArrayList<AccountIcon>();
    protected SelectedUsersAndChatsAdapter r;
    protected TextView s;
    View.OnClickListener t;
    private Chat u;

    public SelectUsersAndChatsAdapter(Context context, SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter) {
        this.k = context;
        this.l = LayoutInflater.from((Context)this.k);
        this.r = selectedUsersAndChatsAdapter;
        this.n = context.getResources().getStringArray(2131230721);
    }

    public int a(int n) {
        if (this.m) {
            return 0;
        }
        return super.a(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public View a(int n, View object, ViewGroup object2) {
        if (object == null) {
            object2 = this.l.inflate(2130903118, (ViewGroup)object2, false);
            object = this.a((ViewGroup)object2);
            object2.setTag(object);
        } else {
            ViewHolder viewHolder = (ViewHolder)object.getTag();
            object2 = object;
            object = viewHolder;
        }
        this.a((ViewHolder)object, n, (View)object2);
        return object2;
    }

    public ViewHolder a(ViewGroup viewGroup) {
        return new ViewHolder((View)viewGroup);
    }

    public void a(View.OnClickListener onClickListener) {
        this.t = onClickListener;
    }

    public void a(View arrobject, int n, int n2, int n3) {
        arrobject = this.getSections();
        if (arrobject != null && arrobject.length > n) {
            this.s.setText((CharSequence)((String)arrobject[n]));
        }
    }

    protected void a(View object, int n, int n2, boolean bl) {
        object = (ViewHolder)object.getTag();
        if (!bl || n >= this.n.length || this.m) {
            object.b.setVisibility(8);
            return;
        }
        object.b.setText((CharSequence)this.n[n]);
        object.b.setVisibility(0);
    }

    public void a(AmazingListView amazingListView) {
        View view = this.l.inflate(2130903271, (ViewGroup)amazingListView, false);
        amazingListView.a(view, amazingListView.getResources().getDimensionPixelSize(2131427835));
        this.s = (TextView)view.findViewById(2131755430);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ViewHolder viewHolder, int n, View view) {
        Object object;
        String string2;
        viewHolder.a = object = this.getItem(n);
        int n2 = 0;
        viewHolder.c.a(6, 1, 0);
        if (object instanceof Chat) {
            Chat chat = (Chat)object;
            string2 = chat.a() == Chat.Type.a ? ((PeerChat)chat).R().handle : ((GroupChat)chat).S();
            viewHolder.c.a(chat, this.k.getResources().getDimensionPixelSize(2131427560));
            n2 = 0;
        } else if (object instanceof AccountIcon) {
            AccountIcon accountIcon = (AccountIcon)object;
            string2 = accountIcon.handle;
            viewHolder.c.setAccount(accountIcon);
            n2 = this.u instanceof GroupChat && ((GroupChat)this.u).b(accountIcon.accountId) != GroupMemberStatus.a || this.u instanceof PeerChat && this.u.f() == accountIcon.accountId ? 1 : 0;
        } else {
            string2 = null;
        }
        boolean bl = this.r.c(object);
        viewHolder.d.setText((CharSequence)string2);
        if (n2 != 0) {
            viewHolder.c.setAlpha(0.5f);
            viewHolder.d.setAlpha(0.5f);
            viewHolder.g.setAlpha(0.5f);
            viewHolder.g.setVisibility(0);
            viewHolder.f.setVisibility(8);
            viewHolder.e.setVisibility(4);
            view.setOnClickListener(null);
        } else if (!this.m()) {
            viewHolder.f.setVisibility(8);
            viewHolder.g.setVisibility(8);
            viewHolder.e.setVisibility(0);
            viewHolder.c.setAlpha(1.0f);
            viewHolder.d.setAlpha(1.0f);
            string2 = viewHolder.e;
            n2 = this.m ? 4 : 0;
            string2.setVisibility(n2);
            viewHolder.e.setChecked(bl);
            view.setOnClickListener(this.t);
        } else if (bl) {
            viewHolder.c.setAlpha(0.5f);
            viewHolder.d.setAlpha(0.5f);
            viewHolder.f.setAlpha(0.5f);
            viewHolder.f.setVisibility(0);
            viewHolder.g.setVisibility(8);
            viewHolder.e.setVisibility(4);
            view.setOnClickListener(null);
        } else {
            viewHolder.f.setVisibility(8);
            viewHolder.g.setVisibility(8);
            viewHolder.e.setVisibility(4);
            viewHolder.c.setAlpha(1.0f);
            viewHolder.d.setAlpha(1.0f);
            view.setOnClickListener(this.t);
        }
        viewHolder.i = n;
    }

    public void a(List<AccountIcon> object) {
        this.p.clear();
        object = object.iterator();
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            if (accountIcon.jid == null || accountIcon.d()) continue;
            this.p.add(accountIcon);
        }
        this.m = false;
        this.notifyDataSetChanged();
    }

    public void a(List<Chat> list, Chat chat) {
        this.u = chat;
        this.o.clear();
        this.o.addAll(list);
    }

    public void b(String string2) {
    }

    public void b(List<AccountIcon> object) {
        this.q.clear();
        ChatManager chatManager = SingApplication.k();
        object = object.iterator();
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            if (accountIcon.jid == null || accountIcon.d() || chatManager.a(accountIcon.accountId)) continue;
            this.q.add(accountIcon);
        }
        this.m = true;
        this.notifyDataSetChanged();
    }

    public void c(int n) {
    }

    public Object d(int n) {
        if (n < this.o.size()) {
            return this.o.get(n);
        }
        return this.p.get(n - this.o.size());
    }

    public int getCount() {
        if (this.m) {
            return this.q.size();
        }
        return this.o.size() + this.p.size();
    }

    public Object getItem(int n) {
        if (this.m) {
            return this.q.get(n);
        }
        if (n < this.o.size()) {
            return this.o.get(n);
        }
        return this.p.get(n - this.o.size());
    }

    public long getItemId(int n) {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int getPositionForSection(int n) {
        if (this.m || n == 0) {
            return 0;
        }
        return this.o.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    public int getSectionForPosition(int n) {
        if (this.m || n < this.o.size()) {
            return 0;
        }
        return 1;
    }

    public Object[] getSections() {
        if (this.m) {
            return null;
        }
        return this.n;
    }

    public boolean isEmpty() {
        if (this.getCount() == 0) {
            return true;
        }
        return false;
    }

    public void l() {
        this.m = false;
        this.notifyDataSetChanged();
    }

    public boolean m() {
        return this.m;
    }

    public static class ViewHolder {
        Object a;
        public final TextView b;
        public final ChatMultiplePortraitFlipView c;
        public final TextView d;
        public final CheckBox e;
        public final CheckBox f;
        public final ImageView g;
        public final View h;
        public int i;

        public ViewHolder(View view) {
            this.b = (TextView)view.findViewById(2131755218);
            this.c = (ChatMultiplePortraitFlipView)view.findViewById(2131755346);
            this.d = (TextView)view.findViewById(2131755418);
            this.e = (CheckBox)view.findViewById(2131755445);
            this.f = (CheckBox)view.findViewById(2131755446);
            this.g = (ImageView)view.findViewById(2131755447);
            this.h = view.findViewById(2131755448);
        }
    }

}

