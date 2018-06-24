package com.smule.singandroid.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SelectedUsersAndChatsAdapter extends Adapter<ViewHolder> {
    public final ArrayList<ItemWrapper> f21266a = new ArrayList();
    protected final LayoutInflater f21267b;
    protected int f21268c = -1;
    protected Context f21269d;
    protected SelectUsersAndChatsView f21270e;

    protected interface OnItemClickedListener {
        void mo6763a(View view, ItemWrapper itemWrapper);
    }

    class C43401 implements OnItemClickedListener {
        final /* synthetic */ SelectedUsersAndChatsAdapter f21251a;

        C43401(SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter) {
            this.f21251a = selectedUsersAndChatsAdapter;
        }

        public void mo6763a(View view, ItemWrapper itemWrapper) {
            if (itemWrapper.m22908a() instanceof AccountIcon) {
                this.f21251a.f21270e.f21238s.remove(itemWrapper.m22908a());
            }
            this.f21251a.m22920b(itemWrapper);
        }
    }

    class ItemWrapper {
        Object f21252b;
        String f21253c;
        final /* synthetic */ SelectedUsersAndChatsAdapter f21254d;

        public ItemWrapper(SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter, Object obj, String str) {
            this.f21254d = selectedUsersAndChatsAdapter;
            this.f21252b = obj;
            this.f21253c = str;
        }

        Object m22908a() {
            return this.f21252b;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ItemWrapper) && this.f21253c.equals(((ItemWrapper) obj).f21253c);
        }
    }

    public class AccountItem extends ItemWrapper {
        final /* synthetic */ SelectedUsersAndChatsAdapter f21255a;

        public /* bridge */ /* synthetic */ boolean equals(Object obj) {
            return super.equals(obj);
        }

        public AccountItem(SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter, AccountIcon accountIcon) {
            this.f21255a = selectedUsersAndChatsAdapter;
            super(selectedUsersAndChatsAdapter, accountIcon, "a" + accountIcon.accountId);
        }
    }

    public class ChatItem extends ItemWrapper {
        final /* synthetic */ SelectedUsersAndChatsAdapter f21256a;

        public /* bridge */ /* synthetic */ boolean equals(Object obj) {
            return super.equals(obj);
        }

        public ChatItem(SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter, Chat chat) {
            this.f21256a = selectedUsersAndChatsAdapter;
            super(selectedUsersAndChatsAdapter, chat, chat instanceof PeerChat ? "a" + ((PeerChat) chat).mo6333R().accountId : "c" + chat.m19209c());
        }
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        public final View f21260a;
        public final ChatMultiplePortraitFlipView f21261b;
        public final OnItemClickedListener f21262c;
        public boolean f21263d;
        ItemWrapper f21264e;
        final /* synthetic */ SelectedUsersAndChatsAdapter f21265f;

        public ViewHolder(final SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter, View view, final OnItemClickedListener onItemClickedListener) {
            this.f21265f = selectedUsersAndChatsAdapter;
            super(view);
            this.f21260a = view;
            this.f21261b = (ChatMultiplePortraitFlipView) view.findViewById(C1947R.id.multiple_portrait_profile_image);
            this.f21262c = onItemClickedListener;
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ViewHolder f21259c;

                public void onClick(View view) {
                    onItemClickedListener.mo6763a(view, this.f21259c.f21264e);
                }
            });
        }

        public void m22909a(ItemWrapper itemWrapper, boolean z, int i) {
            if (i == 0) {
                this.f21260a.setPadding(this.f21265f.f21269d.getResources().getDimensionPixelSize(C1947R.dimen.margin_medium), 0, 0, 0);
            } else if (i == this.f21265f.f21266a.size() - 1) {
                this.f21260a.setPadding(0, 0, this.f21265f.f21269d.getResources().getDimensionPixelSize(C1947R.dimen.margin_small), 0);
            } else {
                this.f21260a.setPadding(0, 0, 0, 0);
            }
            this.f21264e = itemWrapper;
            this.f21261b.m23146a(6, 1, 0);
            if (itemWrapper instanceof ChatItem) {
                this.f21261b.m23147a((Chat) itemWrapper.m22908a(), this.f21265f.f21269d.getResources().getDimensionPixelSize(C1947R.dimen.font_chat_timestamp));
            } else if (itemWrapper instanceof AccountItem) {
                this.f21261b.setAccount((AccountIcon) itemWrapper.m22908a());
            }
            this.f21263d = z;
        }
    }

    public /* synthetic */ void onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder viewHolder, int i) {
        m22911a((ViewHolder) viewHolder, i);
    }

    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m22910a(viewGroup, i);
    }

    public SelectedUsersAndChatsAdapter(Context context, SelectUsersAndChatsView selectUsersAndChatsView) {
        this.f21269d = context;
        this.f21267b = LayoutInflater.from(context);
        this.f21270e = selectUsersAndChatsView;
    }

    public boolean m22913a(AccountIcon accountIcon) {
        return m22915a(new AccountItem(this, accountIcon));
    }

    public boolean m22914a(Chat chat) {
        return m22915a(new ChatItem(this, chat));
    }

    public boolean m22916a(Object obj) {
        if (obj instanceof Chat) {
            return m22914a((Chat) obj);
        }
        if (obj instanceof AccountIcon) {
            return m22913a((AccountIcon) obj);
        }
        return false;
    }

    protected boolean m22915a(ItemWrapper itemWrapper) {
        if (m22925c(itemWrapper)) {
            return false;
        }
        this.f21266a.add(itemWrapper);
        notifyItemInserted(this.f21266a.size() - 1);
        if (this.f21266a.size() <= 1) {
            return true;
        }
        notifyItemChanged(this.f21266a.size() - 2);
        return true;
    }

    public boolean m22918b(AccountIcon accountIcon) {
        return m22920b(new AccountItem(this, accountIcon));
    }

    public boolean m22919b(Chat chat) {
        return m22920b(new ChatItem(this, chat));
    }

    public boolean m22921b(Object obj) {
        if (obj instanceof Chat) {
            return m22919b((Chat) obj);
        }
        if (obj instanceof AccountIcon) {
            return m22918b((AccountIcon) obj);
        }
        return false;
    }

    protected boolean m22920b(ItemWrapper itemWrapper) {
        if (!m22925c(itemWrapper)) {
            return false;
        }
        int indexOf = this.f21266a.indexOf(itemWrapper);
        if (indexOf == -1) {
            return false;
        }
        this.f21266a.remove(indexOf);
        this.f21268c--;
        notifyItemRemoved(indexOf);
        notifyItemChanged(indexOf);
        return true;
    }

    public boolean m22923c(AccountIcon accountIcon) {
        return m22925c(new AccountItem(this, accountIcon));
    }

    public boolean m22924c(Chat chat) {
        return m22925c(new ChatItem(this, chat));
    }

    public boolean m22926c(Object obj) {
        if (obj instanceof Chat) {
            return m22924c((Chat) obj);
        }
        if (obj instanceof AccountIcon) {
            return m22923c((AccountIcon) obj);
        }
        return false;
    }

    public boolean m22925c(ItemWrapper itemWrapper) {
        return this.f21266a.contains(itemWrapper);
    }

    public boolean m22912a() {
        return this.f21266a.isEmpty();
    }

    public ViewHolder m22910a(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, this.f21267b.inflate(C1947R.layout.cancellable_profile_image, viewGroup, false), new C43401(this));
    }

    public void m22911a(ViewHolder viewHolder, int i) {
        boolean z = i > this.f21268c;
        viewHolder.m22909a((ItemWrapper) this.f21266a.get(i), z, i);
        if (!z) {
            i = this.f21268c;
        }
        this.f21268c = i;
    }

    public int getItemCount() {
        return this.f21266a.size();
    }

    public List<AccountIcon> m22917b() {
        List<AccountIcon> arrayList = new ArrayList();
        Iterator it = this.f21266a.iterator();
        while (it.hasNext()) {
            ItemWrapper itemWrapper = (ItemWrapper) it.next();
            if (itemWrapper instanceof AccountItem) {
                arrayList.add((AccountIcon) itemWrapper.m22908a());
            }
        }
        return arrayList;
    }

    public List<Chat> m22922c() {
        List<Chat> arrayList = new ArrayList();
        Iterator it = this.f21266a.iterator();
        while (it.hasNext()) {
            ItemWrapper itemWrapper = (ItemWrapper) it.next();
            if (itemWrapper instanceof ChatItem) {
                arrayList.add((Chat) itemWrapper.m22908a());
            }
        }
        return arrayList;
    }
}
