package com.smule.singandroid.chat.message_aggregation;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.Type;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.GroupStatusChatMessage.Status;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AggregatedGroupStatusChatMessage extends AggregatedChatMessage {
    static String f21308b = AggregatedGroupStatusChatMessage.class.getName();
    Context f21309c;
    Chat f21310d;
    ChatListener f21311e;
    List<GroupStatusChatMessage> f21312f = new ArrayList();
    long f21313g;
    Map<Long, AccountIcon> f21314h = new HashMap();
    Set<Long> f21315i = new HashSet();
    AtomicBoolean f21316j = new AtomicBoolean(false);
    private Status f21317k;

    class C43501 implements UserManager$AccountIconsResponseCallback {
        final /* synthetic */ AggregatedGroupStatusChatMessage f21306a;

        C43501(AggregatedGroupStatusChatMessage aggregatedGroupStatusChatMessage) {
            this.f21306a = aggregatedGroupStatusChatMessage;
        }

        public void handleResponse(AccountIconsResponse accountIconsResponse) {
            if (accountIconsResponse.a()) {
                for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                    this.f21306a.f21314h.put(Long.valueOf(accountIcon.accountId), accountIcon);
                }
                this.f21306a.f21311e.a(this.f21306a.f21310d, this.f21306a);
            }
            this.f21306a.f21316j.set(false);
        }
    }

    public /* bridge */ /* synthetic */ boolean mo6362g() {
        return super.mo6362g();
    }

    AggregatedGroupStatusChatMessage(Context context, Chat chat, GroupStatusChatMessage groupStatusChatMessage, ChatListener chatListener) {
        this.f21309c = context;
        this.f21310d = chat;
        this.f21311e = chatListener;
        this.f21317k = groupStatusChatMessage.m19629o();
        this.f21313g = groupStatusChatMessage.m19385b();
        this.f21312f.add(groupStatusChatMessage);
    }

    public Type mo6360a() {
        return Type.GROUP_STATUS;
    }

    public Date mo6767c() {
        return ((GroupStatusChatMessage) this.f21312f.get(this.f21312f.size() - 1)).mo6767c();
    }

    public boolean mo6765a(ChatMessage chatMessage) {
        if (!super.mo6765a(chatMessage) || chatMessage.mo6360a() != Type.GROUP_STATUS) {
            return false;
        }
        GroupStatusChatMessage groupStatusChatMessage = (GroupStatusChatMessage) chatMessage;
        if (groupStatusChatMessage.m19629o() != this.f21317k) {
            return false;
        }
        switch (this.f21317k) {
            case JOINED:
            case LEFT:
                return true;
            case REMOVED:
            case INVITED:
                return groupStatusChatMessage.m19385b() == chatMessage.m19385b();
            case RENAMED:
                return false;
            default:
                return true;
        }
    }

    public boolean mo6766b(ChatMessage chatMessage) {
        if (!super.mo6766b(chatMessage)) {
            return false;
        }
        this.f21312f.add((GroupStatusChatMessage) chatMessage);
        return true;
    }

    public void m22981d(ChatMessage chatMessage) {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ChatMessage)) {
            return false;
        }
        boolean z = ((ChatMessage) obj).mo6360a() == Type.GROUP_STATUS && this.a.contains(((ChatMessage) obj).m19389f());
        return z;
    }

    public String m22983o() {
        int i;
        boolean z;
        String b;
        String a;
        switch (this.f21317k) {
            case JOINED:
                i = C1947R.string.chat_group_status_user_joined;
                z = false;
                break;
            case LEFT:
                i = C1947R.string.chat_group_status_user_left;
                z = false;
                break;
            case REMOVED:
                i = C1947R.string.chat_group_status_user_removed;
                z = true;
                break;
            case INVITED:
                i = C1947R.string.chat_group_status_user_invited;
                z = true;
                break;
            case RENAMED:
                return this.f21309c.getString(C1947R.string.chat_group_status_renamed, new Object[]{m22978b(this.f21313g), ((GroupStatusChatMessage) this.f21312f.get(0)).m19631q()});
            default:
                Log.e(f21308b, "setMessage - Status unknown: " + this.f21317k);
                return "Unknown Status";
        }
        if (z) {
            b = m22978b(this.f21313g);
            a = m22976a(true);
        } else {
            b = m22976a(false);
            a = null;
        }
        m22984p();
        return this.f21309c.getString(i, new Object[]{b, a});
    }

    protected String m22976a(boolean z) {
        String str = null;
        for (GroupStatusChatMessage groupStatusChatMessage : this.f21312f) {
            String b = m22978b(z ? SingApplication.j().c(groupStatusChatMessage.m19630p()) : groupStatusChatMessage.m19385b());
            if (str != null) {
                b = str + ", " + b;
            }
            str = b;
        }
        return str;
    }

    protected String m22978b(long j) {
        if (j == UserManager.a().f()) {
            return this.f21309c.getResources().getString(C1947R.string.core_you_cap);
        }
        AccountIcon accountIcon = (AccountIcon) this.f21314h.get(Long.valueOf(j));
        if (accountIcon == null) {
            accountIcon = this.f21310d.m19186a(j);
        }
        if (accountIcon != null) {
            return accountIcon.handle;
        }
        this.f21315i.add(Long.valueOf(j));
        return this.f21309c.getResources().getString(C1947R.string.core_someone_cap);
    }

    protected void m22984p() {
        if (!this.f21315i.isEmpty() && !this.f21316j.getAndSet(true)) {
            AccountIconCache.m19106a().m19111a(this.f21315i, new C43501(this));
            this.f21315i.clear();
        }
    }
}
