/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 */
package com.smule.singandroid.chat.message_aggregation;

import android.content.Context;
import android.content.res.Resources;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.message_aggregation.AggregatedChatMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AggregatedGroupStatusChatMessage
extends AggregatedChatMessage {
    static String b = AggregatedGroupStatusChatMessage.class.getName();
    Context c;
    Chat d;
    ChatListener e;
    List<GroupStatusChatMessage> f = new ArrayList<GroupStatusChatMessage>();
    long g;
    Map<Long, AccountIcon> h = new HashMap<Long, AccountIcon>();
    Set<Long> i = new HashSet<Long>();
    AtomicBoolean j = new AtomicBoolean(false);
    private GroupStatusChatMessage.Status k;

    AggregatedGroupStatusChatMessage(Context context, Chat chat, GroupStatusChatMessage groupStatusChatMessage, ChatListener chatListener) {
        this.c = context;
        this.d = chat;
        this.e = chatListener;
        this.k = groupStatusChatMessage.o();
        this.g = groupStatusChatMessage.b();
        this.f.add(groupStatusChatMessage);
    }

    @Override
    public ChatMessage.Type a() {
        return ChatMessage.Type.g;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected String a(boolean bl) {
        Iterator<GroupStatusChatMessage> iterator = this.f.iterator();
        Object object = null;
        while (iterator.hasNext()) {
            Object object2 = iterator.next();
            long l = bl ? SingApplication.k().c(object2.p()) : object2.b();
            object2 = this.b(l);
            if (object == null) {
                object = object2;
                continue;
            }
            object = (String)object + ", " + (String)object2;
        }
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean a(ChatMessage chatMessage) {
        if (!super.a(chatMessage)) {
            do {
                return false;
                break;
            } while (true);
        }
        if (chatMessage.a() != ChatMessage.Type.g) return false;
        GroupStatusChatMessage groupStatusChatMessage = (GroupStatusChatMessage)chatMessage;
        if (groupStatusChatMessage.o() != this.k) return false;
        switch (.a[this.k.ordinal()]) {
            case 5: {
                return false;
            }
            default: {
                return true;
            }
            case 1: 
            case 2: {
                return true;
            }
            case 3: 
            case 4: 
        }
        if (groupStatusChatMessage.b() != chatMessage.b()) return false;
        return true;
    }

    protected String b(long l) {
        AccountIcon accountIcon;
        if (l == com.smule.android.network.managers.UserManager.a().f()) {
            return this.c.getResources().getString(2131296734);
        }
        AccountIcon accountIcon2 = accountIcon = this.h.get(l);
        if (accountIcon == null) {
            accountIcon2 = this.d.a(l);
        }
        if (accountIcon2 == null) {
            this.i.add(l);
            return this.c.getResources().getString(2131296726);
        }
        return accountIcon2.handle;
    }

    @Override
    public boolean b(ChatMessage chatMessage) {
        if (super.b(chatMessage)) {
            this.f.add((GroupStatusChatMessage)chatMessage);
            return true;
        }
        return false;
    }

    @Override
    public Date c() {
        return this.f.get(this.f.size() - 1).c();
    }

    public void d(ChatMessage chatMessage) {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ChatMessage)) return false;
        ChatMessage chatMessage = (ChatMessage)object;
        if (((ChatMessage)object).a() != ChatMessage.Type.g) return false;
        if (!this.a.contains(chatMessage.f())) return false;
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public String o() {
        block8 : {
            block7 : {
                switch (.a[this.k.ordinal()]) {
                    default: {
                        Log.e(AggregatedGroupStatusChatMessage.b, "setMessage - Status unknown: " + (Object)this.k);
                        return "Unknown Status";
                    }
                    case 1: {
                        var2_1 = 2131296536;
                        var1_2 = false;
                        ** GOTO lbl20
                    }
                    case 2: {
                        var2_1 = 2131296538;
                        var1_2 = false;
                        ** GOTO lbl20
                    }
                    case 3: {
                        var2_1 = 2131296540;
                        var1_2 = true;
                        ** GOTO lbl20
                    }
                    case 4: {
                        var2_1 = 2131296534;
                        var1_2 = true;
lbl20: // 4 sources:
                        if (!var1_2) break block7;
                        var4_3 = this.b(this.g);
                        var3_4 = this.a(true);
                        break block8;
                    }
                    case 5: 
                }
                return this.c.getString(2131296532, new Object[]{this.b(this.g), this.f.get(0).q()});
            }
            var4_3 = this.a(false);
            var3_4 = null;
        }
        this.p();
        return this.c.getString(var2_1, new Object[]{var4_3, var3_4});
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void p() {
        if (this.i.isEmpty() || this.j.getAndSet(true)) {
            return;
        }
        AccountIconCache.a().a(this.i, new UserManager(){

            @Override
            public void handleResponse(UserManager.AccountIconsResponse object) {
                if (object.a()) {
                    for (AccountIcon accountIcon : object.accountIcons) {
                        AggregatedGroupStatusChatMessage.this.h.put(accountIcon.accountId, accountIcon);
                    }
                    AggregatedGroupStatusChatMessage.this.e.a(AggregatedGroupStatusChatMessage.this.d, AggregatedGroupStatusChatMessage.this);
                }
                AggregatedGroupStatusChatMessage.this.j.set(false);
            }
        });
        this.i.clear();
    }

}

