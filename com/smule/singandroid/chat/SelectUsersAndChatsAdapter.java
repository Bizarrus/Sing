package com.smule.singandroid.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.foound.widget.AmazingAdapter;
import com.foound.widget.AmazingListView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManager;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import java.util.ArrayList;
import java.util.List;

class SelectUsersAndChatsAdapter extends AmazingAdapter {
    public final String f21175e = SelectUsersAndChatsAdapter.class.getName();
    protected final int f21176f = 0;
    protected final int f21177g = 1;
    protected final Context f21178h;
    protected final LayoutInflater f21179i;
    protected boolean f21180j;
    protected String[] f21181k;
    protected final List<Chat> f21182l = new ArrayList();
    protected final List<AccountIcon> f21183m = new ArrayList();
    protected final List<AccountIcon> f21184n = new ArrayList();
    protected SelectedUsersAndChatsAdapter f21185o;
    protected TextView f21186p;
    OnClickListener f21187q;
    private Chat f21188r;

    public static class ViewHolder {
        Object f21166a;
        public final TextView f21167b;
        public final ChatMultiplePortraitFlipView f21168c;
        public final TextView f21169d;
        public final CheckBox f21170e;
        public final CheckBox f21171f;
        public final ImageView f21172g;
        public final View f21173h;
        public int f21174i;

        public ViewHolder(View view) {
            this.f21167b = (TextView) view.findViewById(C1947R.id.header);
            this.f21168c = (ChatMultiplePortraitFlipView) view.findViewById(C1947R.id.multiple_portrait_profile_image);
            this.f21169d = (TextView) view.findViewById(C1947R.id.chat_title);
            this.f21170e = (CheckBox) view.findViewById(C1947R.id.chat_selected);
            this.f21171f = (CheckBox) view.findViewById(C1947R.id.checkbox_exist_in_chat);
            this.f21172g = (ImageView) view.findViewById(C1947R.id.checkbox_exit_in_chat_grey);
            this.f21173h = view.findViewById(C1947R.id.selected_overlay);
        }
    }

    public SelectUsersAndChatsAdapter(Context context, SelectedUsersAndChatsAdapter selectedUsersAndChatsAdapter) {
        this.f21178h = context;
        this.f21179i = LayoutInflater.from(this.f21178h);
        this.f21185o = selectedUsersAndChatsAdapter;
        this.f21181k = context.getResources().getStringArray(C1947R.array.chat_new_chat_sections);
    }

    public void m22858a(OnClickListener onClickListener) {
        this.f21187q = onClickListener;
    }

    public void m22868i() {
        this.f21180j = false;
        notifyDataSetChanged();
    }

    public void m22864a(List<Chat> list, Chat chat) {
        this.f21188r = chat;
        this.f21182l.clear();
        this.f21182l.addAll(list);
    }

    public void m22863a(List<AccountIcon> list) {
        this.f21183m.clear();
        for (AccountIcon accountIcon : list) {
            if (!(accountIcon.jid == null || accountIcon.d())) {
                this.f21183m.add(accountIcon);
            }
        }
        this.f21180j = false;
        notifyDataSetChanged();
    }

    public void m22865b(List<AccountIcon> list) {
        this.f21184n.clear();
        ChatManager j = SingApplication.j();
        for (AccountIcon accountIcon : list) {
            if (!(accountIcon.jid == null || accountIcon.d() || j.a(accountIcon.accountId))) {
                this.f21184n.add(accountIcon);
            }
        }
        this.f21180j = true;
        notifyDataSetChanged();
    }

    public void m22866c(int i) {
    }

    public View m22856a(int i, View view, ViewGroup viewGroup) {
        View inflate;
        ViewHolder a;
        if (view == null) {
            inflate = this.f21179i.inflate(C1947R.layout.chat_selectable_list_item, viewGroup, false);
            a = m22857a((ViewGroup) inflate);
            inflate.setTag(a);
        } else {
            a = (ViewHolder) view.getTag();
            inflate = view;
        }
        m22862a(a, i, inflate);
        return inflate;
    }

    public boolean m22869j() {
        return this.f21180j;
    }

    public int getCount() {
        return this.f21180j ? this.f21184n.size() : this.f21182l.size() + this.f21183m.size();
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    public Object getItem(int i) {
        if (this.f21180j) {
            return this.f21184n.get(i);
        }
        if (i < this.f21182l.size()) {
            return this.f21182l.get(i);
        }
        return this.f21183m.get(i - this.f21182l.size());
    }

    public Object m22867d(int i) {
        if (i < this.f21182l.size()) {
            return this.f21182l.get(i);
        }
        return this.f21183m.get(i - this.f21182l.size());
    }

    public long getItemId(int i) {
        return 0;
    }

    public Object[] getSections() {
        if (this.f21180j) {
            return null;
        }
        return this.f21181k;
    }

    public int getPositionForSection(int i) {
        if (this.f21180j || i == 0) {
            return 0;
        }
        return this.f21182l.size();
    }

    public int getSectionForPosition(int i) {
        if (!this.f21180j && i >= this.f21182l.size()) {
            return 1;
        }
        return 0;
    }

    protected void m22860a(View view, int i, int i2, boolean z) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (!z || i >= this.f21181k.length || this.f21180j) {
            viewHolder.f21167b.setVisibility(8);
            return;
        }
        viewHolder.f21167b.setText(this.f21181k[i]);
        viewHolder.f21167b.setVisibility(0);
    }

    public ViewHolder m22857a(ViewGroup viewGroup) {
        return new ViewHolder(viewGroup);
    }

    public void m22862a(ViewHolder viewHolder, int i, View view) {
        CharSequence charSequence;
        Object item = getItem(i);
        viewHolder.f21166a = item;
        Object obj = null;
        viewHolder.f21168c.m23146a(6, 1, 0);
        if (item instanceof Chat) {
            Chat chat = (Chat) item;
            if (chat.mo6335a() == Type.PEER) {
                charSequence = ((PeerChat) chat).mo6333R().handle;
            } else {
                charSequence = ((GroupChat) chat).mo6334S();
            }
            viewHolder.f21168c.m23147a(chat, this.f21178h.getResources().getDimensionPixelSize(C1947R.dimen.font_chat_timestamp));
            obj = null;
        } else if (item instanceof AccountIcon) {
            AccountIcon accountIcon = (AccountIcon) item;
            String str = accountIcon.handle;
            viewHolder.f21168c.setAccount(accountIcon);
            obj = ((!(this.f21188r instanceof GroupChat) || ((GroupChat) this.f21188r).m19541b(accountIcon.accountId) == GroupMemberStatus.NONE) && !((this.f21188r instanceof PeerChat) && this.f21188r.mo6341f() == accountIcon.accountId)) ? null : 1;
            Object obj2 = str;
        } else {
            charSequence = null;
        }
        boolean c = this.f21185o.m22926c(item);
        viewHolder.f21169d.setText(charSequence);
        if (obj != null) {
            viewHolder.f21168c.setAlpha(0.5f);
            viewHolder.f21169d.setAlpha(0.5f);
            viewHolder.f21172g.setAlpha(0.5f);
            viewHolder.f21172g.setVisibility(0);
            viewHolder.f21171f.setVisibility(8);
            viewHolder.f21170e.setVisibility(4);
            view.setOnClickListener(null);
        } else if (!m22869j()) {
            viewHolder.f21171f.setVisibility(8);
            viewHolder.f21172g.setVisibility(8);
            viewHolder.f21170e.setVisibility(0);
            viewHolder.f21168c.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            viewHolder.f21169d.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            viewHolder.f21170e.setVisibility(this.f21180j ? 4 : 0);
            viewHolder.f21170e.setChecked(c);
            view.setOnClickListener(this.f21187q);
        } else if (c) {
            viewHolder.f21168c.setAlpha(0.5f);
            viewHolder.f21169d.setAlpha(0.5f);
            viewHolder.f21171f.setAlpha(0.5f);
            viewHolder.f21171f.setVisibility(0);
            viewHolder.f21172g.setVisibility(8);
            viewHolder.f21170e.setVisibility(4);
            view.setOnClickListener(null);
        } else {
            viewHolder.f21171f.setVisibility(8);
            viewHolder.f21172g.setVisibility(8);
            viewHolder.f21170e.setVisibility(4);
            viewHolder.f21168c.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            viewHolder.f21169d.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            view.setOnClickListener(this.f21187q);
        }
        viewHolder.f21174i = i;
    }

    public void m22861a(AmazingListView amazingListView) {
        View inflate = this.f21179i.inflate(C1947R.layout.list_header, amazingListView, false);
        amazingListView.a(inflate, amazingListView.getResources().getDimensionPixelSize(C1947R.dimen.section_heading_height));
        this.f21186p = (TextView) inflate.findViewById(C1947R.id.text_view);
    }

    public void m22859a(View view, int i, int i2, int i3) {
        Object[] sections = getSections();
        if (sections != null && sections.length > i) {
            this.f21186p.setText((String) sections[i]);
        }
    }

    public int m22855a(int i) {
        if (this.f21180j) {
            return 0;
        }
        return super.a(i);
    }
}
