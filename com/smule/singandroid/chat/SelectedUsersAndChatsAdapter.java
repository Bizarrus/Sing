/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectedUsersAndChatsAdapter
extends RecyclerView.Adapter<ViewHolder> {
    public final ArrayList<ItemWrapper> a = new ArrayList();
    protected final LayoutInflater b;
    protected int c = -1;
    protected Context d;
    protected SelectUsersAndChatsView e;

    public SelectedUsersAndChatsAdapter(Context context, SelectUsersAndChatsView selectUsersAndChatsView) {
        this.d = context;
        this.b = LayoutInflater.from((Context)context);
        this.e = selectUsersAndChatsView;
    }

    public ViewHolder a(ViewGroup viewGroup, int n) {
        return new ViewHolder(this.b.inflate(2130903097, viewGroup, false), new OnItemClickedListener(){

            @Override
            public void a(View view, ItemWrapper itemWrapper) {
                if (itemWrapper.a() instanceof AccountIcon) {
                    SelectedUsersAndChatsAdapter.this.e.s.remove(itemWrapper.a());
                }
                SelectedUsersAndChatsAdapter.this.b(itemWrapper);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ViewHolder viewHolder, int n) {
        boolean bl = n > this.c;
        viewHolder.a(this.a.get(n), bl, n);
        if (!bl) {
            n = this.c;
        }
        this.c = n;
    }

    public boolean a() {
        return this.a.isEmpty();
    }

    public boolean a(AccountIcon accountIcon) {
        return this.a(new AccountItem(accountIcon));
    }

    public boolean a(Chat chat) {
        return this.a(new ChatItem(chat));
    }

    protected boolean a(ItemWrapper itemWrapper) {
        if (!this.c(itemWrapper)) {
            this.a.add(itemWrapper);
            this.notifyItemInserted(this.a.size() - 1);
            if (this.a.size() > 1) {
                this.notifyItemChanged(this.a.size() - 2);
            }
            return true;
        }
        return false;
    }

    public boolean a(Object object) {
        if (object instanceof Chat) {
            return this.a((Chat)object);
        }
        if (object instanceof AccountIcon) {
            return this.a((AccountIcon)object);
        }
        return false;
    }

    public List<AccountIcon> b() {
        ArrayList<AccountIcon> arrayList = new ArrayList<AccountIcon>();
        for (ItemWrapper itemWrapper : this.a) {
            if (!(itemWrapper instanceof AccountItem)) continue;
            arrayList.add((AccountIcon)itemWrapper.a());
        }
        return arrayList;
    }

    public boolean b(AccountIcon accountIcon) {
        return this.b(new AccountItem(accountIcon));
    }

    public boolean b(Chat chat) {
        return this.b(new ChatItem(chat));
    }

    protected boolean b(ItemWrapper itemWrapper) {
        int n;
        if (!this.c(itemWrapper) || (n = this.a.indexOf(itemWrapper)) == -1) {
            return false;
        }
        this.a.remove(n);
        --this.c;
        this.notifyItemRemoved(n);
        this.notifyItemChanged(n);
        return true;
    }

    public boolean b(Object object) {
        if (object instanceof Chat) {
            return this.b((Chat)object);
        }
        if (object instanceof AccountIcon) {
            return this.b((AccountIcon)object);
        }
        return false;
    }

    public List<Chat> c() {
        ArrayList<Chat> arrayList = new ArrayList<Chat>();
        for (ItemWrapper itemWrapper : this.a) {
            if (!(itemWrapper instanceof ChatItem)) continue;
            arrayList.add((Chat)itemWrapper.a());
        }
        return arrayList;
    }

    public boolean c(AccountIcon accountIcon) {
        return this.c(new AccountItem(accountIcon));
    }

    public boolean c(Chat chat) {
        return this.c(new ChatItem(chat));
    }

    public boolean c(ItemWrapper itemWrapper) {
        return this.a.contains(itemWrapper);
    }

    public boolean c(Object object) {
        if (object instanceof Chat) {
            return this.c((Chat)object);
        }
        if (object instanceof AccountIcon) {
            return this.c((AccountIcon)object);
        }
        return false;
    }

    public int getItemCount() {
        return this.a.size();
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int n) {
        this.a((ViewHolder)viewHolder, n);
    }

    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int n) {
        return this.a(viewGroup, n);
    }

    public class AccountItem
    extends ItemWrapper {
        public AccountItem(AccountIcon accountIcon) {
            super(accountIcon, "a" + accountIcon.accountId);
        }
    }

    public class ChatItem
    extends ItemWrapper {
        /*
         * Enabled aggressive block sorting
         */
        public ChatItem(Chat chat) {
            String string2 = chat instanceof PeerChat ? "a" + ((PeerChat)chat).R().accountId : "c" + chat.c();
            super(chat, string2);
        }
    }

    class ItemWrapper {
        Object b;
        String c;

        public ItemWrapper(Object object, String string2) {
            this.b = object;
            this.c = string2;
        }

        Object a() {
            return this.b;
        }

        public boolean equals(Object object) {
            if (object instanceof ItemWrapper && this.c.equals(((ItemWrapper)object).c)) {
                return true;
            }
            return false;
        }
    }

    protected static interface OnItemClickedListener {
        public void a(View var1, ItemWrapper var2);
    }

    public class ViewHolder
    extends RecyclerView.ViewHolder {
        public final View a;
        public final ChatMultiplePortraitFlipView b;
        public final OnItemClickedListener c;
        public boolean d;
        ItemWrapper e;

        public ViewHolder(View view, OnItemClickedListener onItemClickedListener) {
            super(view);
            this.a = view;
            this.b = (ChatMultiplePortraitFlipView)view.findViewById(2131755346);
            this.c = onItemClickedListener;
            view.setOnClickListener(new View.OnClickListener(SelectedUsersAndChatsAdapter.this, onItemClickedListener){
                final /* synthetic */ SelectedUsersAndChatsAdapter a;
                final /* synthetic */ OnItemClickedListener b;
                {
                    this.a = selectedUsersAndChatsAdapter;
                    this.b = onItemClickedListener;
                }

                public void onClick(View view) {
                    this.b.a(view, ViewHolder.this.e);
                }
            });
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(ItemWrapper object, boolean bl, int n) {
            if (n == 0) {
                this.a.setPadding(SelectedUsersAndChatsAdapter.this.d.getResources().getDimensionPixelSize(2131428167), 0, 0, 0);
            } else if (n == SelectedUsersAndChatsAdapter.this.a.size() - 1) {
                this.a.setPadding(0, 0, SelectedUsersAndChatsAdapter.this.d.getResources().getDimensionPixelSize(2131428182), 0);
            } else {
                this.a.setPadding(0, 0, 0, 0);
            }
            this.e = object;
            this.b.a(6, 1, 0);
            if (object instanceof ChatItem) {
                object = (Chat)object.a();
                this.b.a((Chat)object, SelectedUsersAndChatsAdapter.this.d.getResources().getDimensionPixelSize(2131427560));
            } else if (object instanceof AccountItem) {
                object = (AccountIcon)object.a();
                this.b.setAccount((AccountIcon)object);
            }
            this.d = bl;
        }

    }

}

