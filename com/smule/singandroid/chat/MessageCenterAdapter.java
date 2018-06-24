package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatListView.ChatListViewAdapter;
import com.smule.singandroid.customviews.ChatMultiplePortraitFlipView;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.ChatUtils$GroupStatusCallback;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MessageCenterAdapter extends ChatListViewAdapter {
    private final LayoutInflater f21100a;
    private final List<Chat> f21101b = new ArrayList();
    private final Resources f21102c;
    private Set<String> f21103d = new HashSet();
    private final Context f21104e;
    private boolean f21105f = false;

    public class ViewHolder {
        public final View f21089a;
        public final View f21090b;
        public final ChatMultiplePortraitFlipView f21091c;
        public final TextView f21092d;
        public final TextView f21093e;
        public final TextView f21094f;
        public final ImageView f21095g;
        public final RelativeLayout f21096h;
        public boolean f21097i = false;
        public int f21098j;
        final /* synthetic */ MessageCenterAdapter f21099k;

        public ViewHolder(MessageCenterAdapter messageCenterAdapter, View view) {
            this.f21099k = messageCenterAdapter;
            this.f21089a = view.findViewById(C1947R.id.loading_mask);
            this.f21090b = view.findViewById(C1947R.id.progress_bar_indeterminate);
            this.f21091c = (ChatMultiplePortraitFlipView) view.findViewById(C1947R.id.multiple_portrait_profile_image);
            this.f21092d = (TextView) view.findViewById(C1947R.id.chat_title);
            this.f21093e = (TextView) view.findViewById(C1947R.id.chat_last_message);
            this.f21094f = (TextView) view.findViewById(C1947R.id.chat_timestamp);
            this.f21095g = (ImageView) view.findViewById(C1947R.id.chat_unread_badge);
            this.f21096h = (RelativeLayout) view.findViewById(C1947R.id.background);
            this.f21091c.m23146a(4, 2, messageCenterAdapter.f21104e.getResources().getDimensionPixelSize(C1947R.dimen.margin_tiny));
        }

        public void m22772a() {
            this.f21097i = !this.f21097i;
            this.f21091c.m23145a();
            this.f21096h.setActivated(this.f21097i);
        }

        public void m22773a(boolean z) {
            this.f21097i = z;
            this.f21096h.setActivated(z);
            this.f21091c.setSide(!z);
        }
    }

    public /* synthetic */ Object getItem(int i) {
        return m22777a(i);
    }

    public MessageCenterAdapter(Context context) {
        this.f21100a = LayoutInflater.from(context);
        this.f21102c = context.getResources();
        this.f21104e = context;
    }

    public void m22780a(String str) {
        this.f21103d.add(str);
    }

    public void m22783b(String str) {
        this.f21103d.remove(str);
    }

    public void m22782b() {
        this.f21103d.clear();
    }

    public void mo6758a(List<Chat> list) {
        if (list != null) {
            this.f21101b.clear();
            this.f21101b.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int mo6757a() {
        int i = 0;
        for (Chat m : this.f21101b) {
            int i2;
            if (m.m19225m()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public int getCount() {
        return this.f21101b.size();
    }

    public Chat m22777a(int i) {
        return (Chat) this.f21101b.get(i);
    }

    public long getItemId(int i) {
        if (m22775b(i)) {
            return 0;
        }
        return 1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate;
        ViewHolder a;
        if (view == null) {
            inflate = this.f21100a.inflate(C1947R.layout.chat_message_center_item, viewGroup, false);
            a = m22778a((ViewGroup) inflate);
            inflate.setTag(a);
        } else {
            a = (ViewHolder) view.getTag();
            inflate = view;
        }
        a.f21098j = i;
        m22779a(a, i);
        return inflate;
    }

    private boolean m22775b(int i) {
        return this.f21103d.contains(m22777a(i).m19209c());
    }

    public ViewHolder m22778a(ViewGroup viewGroup) {
        return new ViewHolder(this, viewGroup);
    }

    public void m22779a(final ViewHolder viewHolder, int i) {
        int i2;
        String a;
        boolean z;
        CharSequence a2;
        final ChatMessage l;
        AccountIcon a3;
        boolean b;
        Object string;
        TextChatMessage textChatMessage;
        PerformanceChatMessage performanceChatMessage;
        CharSequence charSequence;
        Chat a4 = m22777a(i);
        viewHolder.f21090b.setVisibility(a4.m19212d() == ChatState.LOADING ? 0 : 8);
        View view = viewHolder.f21089a;
        if (a4.m19212d() == ChatState.LOADING) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
        viewHolder.f21095g.setVisibility(a4.m19225m() ? 0 : 4);
        if (a4.m19212d() == ChatState.ERROR) {
            a = ChatUtils.a(a4);
            if (a4.m19214e().m19417a() || a.isEmpty()) {
                z = true;
                if (z) {
                    a2 = ChatUtils.a(a4);
                    if (a2.isEmpty()) {
                        if (a4.m19212d() != ChatState.LOADING) {
                            a2 = this.f21104e.getString(C1947R.string.message_center_loading);
                        } else {
                            a2 = this.f21104e.getString(C1947R.string.chat_load_error);
                        }
                    }
                    viewHolder.f21092d.setText(a2);
                    viewHolder.f21091c.m23147a(a4, this.f21104e.getResources().getDimensionPixelSize(C1947R.dimen.font_contextual_text));
                    if (!a4.m19227o() || a4.mo6347b() == Bucket.OTHER) {
                        viewHolder.f21092d.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1947R.drawable.icn_notification_off_gray, 0);
                    } else {
                        viewHolder.f21092d.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    }
                    l = a4.m19224l();
                    if (l != null) {
                        a3 = a4.m19186a(l.m19385b());
                        b = ChatUtils.b(l);
                        if (!b) {
                            String string2 = a3 == null ? a3.handle : this.f21102c.getString(C1947R.string.chat_message_unknown_user);
                        } else {
                            string = this.f21102c.getString(C1947R.string.core_you_cap);
                        }
                        switch (l.mo6360a()) {
                            case GROUP_INVITATION:
                                a = ((GroupInvitationChatMessage) l).m19614p();
                                if (a == null && !a.isEmpty()) {
                                    if (!b) {
                                        string = this.f21102c.getString(C1947R.string.chat_invite_title_group_with_name, new Object[]{a});
                                        z = false;
                                        break;
                                    }
                                    Resources resources = this.f21102c;
                                    Object[] objArr = new Object[2];
                                    objArr[0] = string;
                                    objArr[1] = this.f21102c.getString(C1947R.string.chat_invite_title_group_with_name, new Object[]{a});
                                    string = resources.getString(C1947R.string.chat_text_message_text_peer, objArr);
                                    z = false;
                                    break;
                                }
                                string = this.f21102c.getString(C1947R.string.chat_invite_title_default);
                                z = false;
                                break;
                            case TEXT:
                                textChatMessage = (TextChatMessage) l;
                                if (a4.mo6335a() == Type.PEER) {
                                    if (!b) {
                                        string = this.f21102c.getString(C1947R.string.chat_text_message_text_peer, new Object[]{string, textChatMessage.m19687o()});
                                        z = false;
                                        break;
                                    }
                                    string = textChatMessage.m19687o();
                                    z = false;
                                    break;
                                }
                                string = this.f21102c.getString(C1947R.string.chat_text_message_text_group, new Object[]{string, textChatMessage.m19687o()});
                                z = false;
                                break;
                            case PERFORMANCE:
                                performanceChatMessage = (PerformanceChatMessage) l;
                                if (performanceChatMessage.m19387d() == State.READY || performanceChatMessage.m19661o() == null) {
                                    a = this.f21102c.getString(C1947R.string.chat_text_message_share_unknown_cover);
                                } else if (performanceChatMessage.m19661o().n()) {
                                    a = this.f21102c.getString(C1947R.string.chat_text_message_share, new Object[]{performanceChatMessage.m19661o().title});
                                } else {
                                    a = this.f21102c.getString(C1947R.string.chat_text_message_share_cover, new Object[]{performanceChatMessage.m19661o().title});
                                }
                                if (a4.mo6335a() == Type.PEER) {
                                    if (!b) {
                                        string = this.f21102c.getString(C1947R.string.chat_text_message_text_peer, new Object[]{string, a});
                                        z = false;
                                        break;
                                    }
                                    string = a;
                                    z = false;
                                    break;
                                }
                                string = this.f21102c.getString(C1947R.string.chat_text_message_text_group, new Object[]{string, a});
                                z = false;
                                break;
                                break;
                            case GROUP_STATUS:
                                charSequence = null;
                                ChatUtils.a(a4, (GroupStatusChatMessage) l, new ChatUtils$GroupStatusCallback(this) {
                                    final /* synthetic */ MessageCenterAdapter f21087c;

                                    public void mo6759a(final String str, GroupStatusChatMessage groupStatusChatMessage) {
                                        if (groupStatusChatMessage == l) {
                                            new Handler(this.f21087c.f21104e.getMainLooper()).post(new Runnable(this) {
                                                final /* synthetic */ C43121 f21084b;

                                                public void run() {
                                                    viewHolder.f21093e.setVisibility(0);
                                                    viewHolder.f21093e.setText(str);
                                                    viewHolder.f21094f.setText(new ChatDate(l.mo6767c()).m22429a());
                                                }
                                            });
                                        }
                                    }
                                }, this.f21104e);
                                z = true;
                                break;
                            default:
                                charSequence = this.f21102c.getString(C1947R.string.chat_text_message_unknown, new Object[]{string});
                                z = false;
                                break;
                        }
                    }
                    charSequence = null;
                    viewHolder.f21093e.setVisibility(8);
                    z = false;
                    if (!z) {
                        if (charSequence == null) {
                            viewHolder.f21093e.setVisibility(0);
                            viewHolder.f21093e.setText(charSequence);
                            ChatDate chatDate = new ChatDate(l.mo6767c());
                            viewHolder.f21094f.setVisibility(0);
                            viewHolder.f21094f.setText(chatDate.m22429a());
                        } else {
                            viewHolder.f21093e.setVisibility(8);
                            viewHolder.f21094f.setVisibility(8);
                        }
                    }
                    if (this.f21105f) {
                        viewHolder.m22773a(m22775b(i));
                        return;
                    } else if (m22775b(i)) {
                        viewHolder.m22773a(false);
                        return;
                    } else {
                        viewHolder.m22773a(true);
                        viewHolder.m22772a();
                        return;
                    }
                }
                viewHolder.f21092d.setText(a4 instanceof PeerChat ? C1947R.string.chat_load_error_short : C1947R.string.chat_load_error_group_short);
                viewHolder.f21093e.setVisibility(0);
                viewHolder.f21093e.setText(C1947R.string.chat_load_error_retry);
                viewHolder.f21094f.setVisibility(8);
                viewHolder.m22773a(false);
                viewHolder.f21091c.setChat(a4);
            }
        }
        z = false;
        if (z) {
            a2 = ChatUtils.a(a4);
            if (a2.isEmpty()) {
                if (a4.m19212d() != ChatState.LOADING) {
                    a2 = this.f21104e.getString(C1947R.string.chat_load_error);
                } else {
                    a2 = this.f21104e.getString(C1947R.string.message_center_loading);
                }
            }
            viewHolder.f21092d.setText(a2);
            viewHolder.f21091c.m23147a(a4, this.f21104e.getResources().getDimensionPixelSize(C1947R.dimen.font_contextual_text));
            if (a4.m19227o()) {
            }
            viewHolder.f21092d.setCompoundDrawablesWithIntrinsicBounds(0, 0, C1947R.drawable.icn_notification_off_gray, 0);
            l = a4.m19224l();
            if (l != null) {
                a3 = a4.m19186a(l.m19385b());
                b = ChatUtils.b(l);
                if (!b) {
                    string = this.f21102c.getString(C1947R.string.core_you_cap);
                } else if (a3 == null) {
                }
                switch (l.mo6360a()) {
                    case GROUP_INVITATION:
                        a = ((GroupInvitationChatMessage) l).m19614p();
                        if (a == null) {
                            break;
                        }
                        string = this.f21102c.getString(C1947R.string.chat_invite_title_default);
                        z = false;
                        break;
                    case TEXT:
                        textChatMessage = (TextChatMessage) l;
                        if (a4.mo6335a() == Type.PEER) {
                            if (!b) {
                                string = textChatMessage.m19687o();
                                z = false;
                                break;
                            }
                            string = this.f21102c.getString(C1947R.string.chat_text_message_text_peer, new Object[]{string, textChatMessage.m19687o()});
                            z = false;
                            break;
                        }
                        string = this.f21102c.getString(C1947R.string.chat_text_message_text_group, new Object[]{string, textChatMessage.m19687o()});
                        z = false;
                        break;
                    case PERFORMANCE:
                        performanceChatMessage = (PerformanceChatMessage) l;
                        if (performanceChatMessage.m19387d() == State.READY) {
                            break;
                        }
                        a = this.f21102c.getString(C1947R.string.chat_text_message_share_unknown_cover);
                        if (a4.mo6335a() == Type.PEER) {
                            if (!b) {
                                string = a;
                                z = false;
                                break;
                            }
                            string = this.f21102c.getString(C1947R.string.chat_text_message_text_peer, new Object[]{string, a});
                            z = false;
                            break;
                        }
                        string = this.f21102c.getString(C1947R.string.chat_text_message_text_group, new Object[]{string, a});
                        z = false;
                        break;
                    case GROUP_STATUS:
                        charSequence = null;
                        ChatUtils.a(a4, (GroupStatusChatMessage) l, /* anonymous class already generated */, this.f21104e);
                        z = true;
                        break;
                    default:
                        charSequence = this.f21102c.getString(C1947R.string.chat_text_message_unknown, new Object[]{string});
                        z = false;
                        break;
                }
            }
            charSequence = null;
            viewHolder.f21093e.setVisibility(8);
            z = false;
            if (z) {
                if (charSequence == null) {
                    viewHolder.f21093e.setVisibility(8);
                    viewHolder.f21094f.setVisibility(8);
                } else {
                    viewHolder.f21093e.setVisibility(0);
                    viewHolder.f21093e.setText(charSequence);
                    ChatDate chatDate2 = new ChatDate(l.mo6767c());
                    viewHolder.f21094f.setVisibility(0);
                    viewHolder.f21094f.setText(chatDate2.m22429a());
                }
            }
            if (this.f21105f) {
                viewHolder.m22773a(m22775b(i));
                return;
            } else if (m22775b(i)) {
                viewHolder.m22773a(false);
                return;
            } else {
                viewHolder.m22773a(true);
                viewHolder.m22772a();
                return;
            }
        }
        if (a4 instanceof PeerChat) {
        }
        viewHolder.f21092d.setText(a4 instanceof PeerChat ? C1947R.string.chat_load_error_short : C1947R.string.chat_load_error_group_short);
        viewHolder.f21093e.setVisibility(0);
        viewHolder.f21093e.setText(C1947R.string.chat_load_error_retry);
        viewHolder.f21094f.setVisibility(8);
        viewHolder.m22773a(false);
        viewHolder.f21091c.setChat(a4);
    }

    public void m22784c() {
        this.f21105f = true;
        notifyDataSetChanged();
    }

    public void m22785d() {
        if (this.f21105f) {
            m22782b();
        }
        this.f21105f = false;
    }
}
