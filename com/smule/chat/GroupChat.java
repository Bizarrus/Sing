/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$XMPPErrorException
 *  org.jivesoftware.smack.filter.AndFilter
 *  org.jivesoftware.smack.filter.FromMatchesFilter
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.filter.StanzaTypeFilter
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Message$Type
 *  org.jivesoftware.smack.packet.Presence
 *  org.jivesoftware.smack.packet.Presence$Type
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.packet.XMPPError
 *  org.jivesoftware.smackx.address.packet.MultipleAddresses
 *  org.jivesoftware.smackx.address.packet.MultipleAddresses$Type
 *  org.jivesoftware.smackx.muc.MUCAffiliation
 *  org.jivesoftware.smackx.muc.packet.MUCInitialPresence
 *  org.jivesoftware.smackx.muc.packet.MUCInitialPresence$History
 *  org.jivesoftware.smackx.muc.packet.MUCItem
 *  org.jivesoftware.smackx.muc.packet.MUCUser
 *  org.jivesoftware.smackx.muc.packet.MUCUser$Status
 */
package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupInfo;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.PriorityExecutor;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;

public class GroupChat
extends Chat
implements GroupInfo.Listener {
    private static final String k = GroupChat.class.getName();
    private String i;
    private boolean j;

    public GroupChat() {
    }

    GroupChat(XMPPDelegate object, Chat.Options options) {
        super((XMPPDelegate)object, options);
        this.a(Chat.Bucket.a);
        object = this.U();
        object.a(this);
        this.i = object.d();
    }

    private GroupInfo U() {
        return this.d.d(this.c());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean V() {
        boolean bl = true;
        if (!this.g().contains(this.d.j())) {
            return false;
        }
        ChatMessage chatMessage = this.l();
        boolean bl2 = bl;
        if (chatMessage == null) return bl2;
        bl2 = bl;
        if (chatMessage.a() != ChatMessage.Type.g) return bl2;
        if ((chatMessage = (GroupStatusChatMessage)chatMessage).o() == GroupStatusChatMessage.Status.c) {
            if (chatMessage.p().equals(this.d.i())) return false;
            return true;
        }
        bl2 = bl;
        if (chatMessage.o() != GroupStatusChatMessage.Status.b) return bl2;
        bl2 = bl;
        if (chatMessage.b() != this.d.j()) return bl2;
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ChatStatus W() {
        Message message = GroupStatusChatMessage.s().a(Chat.Type.b, this.c());
        message.g(this.c());
        message.a(Message.Type.c);
        try {
            this.d.a((Stanza)message);
        }
        catch (SmackException.NotConnectedException notConnectedException) {
            return this.U().m();
        }
        do {
            return this.U().m();
            break;
        } while (true);
    }

    private String X() {
        return this.c() + "/" + Long.toString(this.d.j());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ChatStatus a(int n, boolean bl) {
        Object object = new Presence(Presence.Type.a);
        object.g(this.X());
        MUCInitialPresence.History history = new MUCInitialPresence.History();
        history.a(n);
        MUCInitialPresence mUCInitialPresence = new MUCInitialPresence();
        mUCInitialPresence.a(history);
        object.a((ExtensionElement)mUCInitialPresence);
        history = new AndFilter(new StanzaFilter[]{FromMatchesFilter.b((String)this.c()), StanzaTypeFilter.a});
        try {
            object = (Presence)this.d.a((StanzaFilter)history, (Stanza)object).d();
            if (object.m() != null) {
                throw new XMPPException.XMPPErrorException(object.m());
            }
            if (bl) return ChatStatus.a;
            object = MUCUser.a((Stanza)object);
            if (object == null) return ChatStatus.a;
            if (!object.i().contains((Object)MUCUser.Status.a)) return ChatStatus.a;
            Log.d(k, "destroying re-created MUC: " + this.c());
            this.U().b("group shouldn't have been created");
            return ChatStatus.i;
        }
        catch (Exception exception) {
            Log.e(k, "could not enter " + this.c() + ": " + exception.toString());
            ChatStatus chatStatus = ChatStatus.a(exception);
            if (chatStatus.a()) return chatStatus;
            this.d.b(new Runnable(){

                @Override
                public void run() {
                    GroupChat.this.a(GroupChat.this.d.i(), 0);
                }
            });
            return chatStatus;
        }
    }

    private void a(ChatStatus chatStatus, Completion<ChatStatus> completion) {
        switch (.a[this.U().a(this.d.j()).ordinal()]) {
            default: {
                return;
            }
            case 1: {
                completion.a(chatStatus);
                return;
            }
            case 2: {
                this.d(completion);
                this.C();
                return;
            }
            case 3: 
        }
        this.d(completion);
        this.C();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(GroupStatusChatMessage groupStatusChatMessage) {
        if (groupStatusChatMessage.o() == GroupStatusChatMessage.Status.d) {
            PriorityExecutor.a.execute(new Runnable(){

                @Override
                public void run() {
                    GroupInfo groupInfo = GroupChat.this.U();
                    groupInfo.i();
                    groupInfo.b((Completion<ChatStatus>)null);
                }
            });
            return;
        } else {
            if (groupStatusChatMessage.o() != GroupStatusChatMessage.Status.e) return;
            {
                PriorityExecutor.a.execute(new Runnable(){

                    @Override
                    public void run() {
                        GroupInfo groupInfo = GroupChat.this.U();
                        groupInfo.h();
                        groupInfo.b((Completion<ChatStatus>)null);
                    }
                });
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(String object, long l) {
        long l2;
        long l3 = this.d.c((String)object);
        if (l3 == (l2 = this.d.j())) {
            l2 = l;
            if (!this.e.containsKey(l)) {
                l2 = 0;
            }
            if (this.V()) {
                this.z();
                object = GroupStatusChatMessage.a(l2, (String)object);
                this.e((ChatMessage)object);
                object.h();
                this.b((ChatMessage)object, false);
            }
            this.U().c(l3);
            return;
        } else {
            if (l == l2) return;
            {
                this.U().c(l3);
                return;
            }
        }
    }

    private boolean a(ChatMessage chatMessage, Message message) {
        if (chatMessage.f().equals(message.j()) && chatMessage.b() == this.d.a(message)) {
            return true;
        }
        return false;
    }

    private void b(final SimpleBarrier simpleBarrier) {
        if (this.c == null || !this.c.d) {
            simpleBarrier.d();
            PriorityExecutor.a.a(new Chat.BuildTask(){

                @Override
                public void run() {
                    GroupChat.this.U().b(new Completion<ChatStatus>(){

                        @Override
                        public void a(final ChatStatus chatStatus) {
                            if (chatStatus == ChatStatus.a) {
                                simpleBarrier.a();
                                return;
                            }
                            GroupChat.this.d.b(new Runnable(){

                                @Override
                                public void run() {
                                    GroupChat.this.a(chatStatus);
                                    simpleBarrier.a();
                                }
                            });
                        }

                    });
                }

            });
        }
    }

    private void b(final String string2) {
        if (string2.equals(this.d.i())) {
            return;
        }
        Chat.Options options = new Chat.Options();
        options.a = Chat.Type.a;
        options.b = string2;
        options.c = false;
        this.d.a(options, new ChatManager(){

            @Override
            public void a(Chat chat, ChatStatus object) {
                object = new GroupInvitationChatMessage(GroupChat.this, GroupChat.this.d);
                if (chat == null) {
                    chat = object.a(Chat.Type.a, string2);
                    try {
                        GroupChat.this.d.a((Stanza)chat);
                        return;
                    }
                    catch (SmackException.NotConnectedException notConnectedException) {
                        Log.e(k, "can't send invitation");
                        return;
                    }
                }
                chat.a((ChatMessage)object);
            }
        });
    }

    private void b(Collection<AccountIcon> object) {
        object = object.iterator();
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            if (accountIcon.jid.equals(this.d.i())) continue;
            this.a(GroupStatusChatMessage.b(accountIcon.jid));
            this.b(accountIcon.jid);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void c(final SimpleBarrier simpleBarrier) {
        final boolean bl = this.c != null && this.c.d;
        simpleBarrier.d();
        PriorityExecutor.a.a(new Chat.BuildTask(){

            @Override
            public void run() {
                if (GroupChat.this.d() == Chat.ChatState.c) {
                    simpleBarrier.a();
                    return;
                }
                final GroupMemberStatus groupMemberStatus = GroupChat.this.U().a(GroupChat.this.d.j());
                final ChatStatus chatStatus = GroupChat.this.a(1, bl);
                GroupChat.this.d.b(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        if (chatStatus == ChatStatus.a) {
                            if (groupMemberStatus == GroupMemberStatus.b) {
                                GroupChat.this.a(GroupStatusChatMessage.r());
                            }
                        } else if (groupMemberStatus == GroupMemberStatus.a) {
                            GroupChat.this.a(chatStatus);
                        }
                        simpleBarrier.a();
                    }
                });
            }

        });
    }

    private void c(Collection<AccountIcon> object) {
        object = object.iterator();
        while (object.hasNext()) {
            this.a(GroupStatusChatMessage.a(((AccountIcon)object.next()).jid));
        }
    }

    private void d(final SimpleBarrier simpleBarrier) {
        if (this.c != null) {
            simpleBarrier.d();
            final String string2 = this.c.h;
            final Collection<AccountIcon> collection = this.c.g;
            PriorityExecutor.a.a(new Chat.BuildTask(){

                @Override
                public void run() {
                    ChatStatus chatStatus;
                    if (GroupChat.this.d() == Chat.ChatState.c) {
                        simpleBarrier.a();
                        return;
                    }
                    GroupInfo groupInfo = GroupChat.this.U();
                    if (string2 != null && (chatStatus = groupInfo.a(string2)) != ChatStatus.a) {
                        GroupChat.this.d.b(new Runnable(){

                            @Override
                            public void run() {
                                GroupChat.this.a(chatStatus);
                                simpleBarrier.a();
                            }
                        });
                        return;
                    }
                    groupInfo.a(collection, MUCAffiliation.b, new Completion<ChatStatus>(){

                        @Override
                        public void a(final ChatStatus chatStatus) {
                            GroupChat.this.d.b(new Runnable(){

                                /*
                                 * Enabled aggressive block sorting
                                 */
                                @Override
                                public void run() {
                                    if (chatStatus == ChatStatus.a) {
                                        if (collection != null) {
                                            GroupChat.this.b(collection);
                                        }
                                    } else {
                                        GroupChat.this.a(chatStatus);
                                    }
                                    simpleBarrier.a();
                                }
                            });
                        }

                    });
                }

            });
        }
    }

    private void d(final Completion<ChatStatus> completion) {
        new Chat<Void, ChatStatus>(new Void[0]){

            protected /* varargs */ ChatStatus a(Void ... arrvoid) {
                return GroupChat.this.a(1, false);
            }

            @Override
            protected void a(ChatStatus chatStatus) {
                if (completion != null) {
                    completion.a(chatStatus);
                }
            }
        }.a(PriorityExecutor.a);
    }

    @Override
    void A() {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void N() {
        if (this.d() == Chat.ChatState.c) {
            if (this.e() != ChatStatus.c || this.L() != null) return;
            {
                this.a(ChatStatus.i);
                return;
            }
        } else {
            if (this.U().k()) return;
            {
                this.a(ChatStatus.b);
                return;
            }
        }
    }

    public Set<Long> R() {
        ChatManager.n();
        return this.U().f();
    }

    public String S() {
        ChatManager.n();
        return this.i;
    }

    @Override
    public Chat.Type a() {
        return Chat.Type.b;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    ChatMessage a(Message object, boolean bl) {
        boolean bl2 = bl;
        if (!bl) {
            long l = this.d.a((Message)object);
            Object object2 = (MultipleAddresses)object.c("addresses", "http://jabber.org/protocol/address");
            if (object2 != null) {
                bl2 = bl;
                if (object2.a(MultipleAddresses.Type.g).size() > 0) {
                    bl = true;
                    object2 = this.l();
                    bl2 = bl;
                    if (object2 != null) {
                        bl2 = bl;
                        if (!this.a((ChatMessage)object2, (Message)object)) {
                            this.D();
                            bl2 = bl;
                        }
                    }
                }
            } else {
                bl2 = bl;
                if (l == this.d.j()) {
                    return null;
                }
            }
        }
        object = super.a((Message)object, bl2);
        if (!bl2 && object != null && object.a() == ChatMessage.Type.g) {
            this.a((GroupStatusChatMessage)object);
        }
        return object;
    }

    @Override
    protected void a(SimpleBarrier simpleBarrier) {
        this.b(simpleBarrier);
        this.c(simpleBarrier);
        this.d(simpleBarrier);
        super.a(simpleBarrier);
    }

    @Override
    void a(XMPPDelegate xMPPDelegate) {
        super.a(xMPPDelegate);
        this.U().a(this);
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.i = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.a(this.i);
    }

    @Override
    public void a(String string2) {
        this.i = string2;
        this.P();
        this.H();
    }

    public void a(final String string2, final Completion<ChatStatus> completion) {
        ChatManager.n();
        final String string3 = this.i;
        new Chat<Void, ChatStatus>(new Void[0]){

            protected /* varargs */ ChatStatus a(Void ... arrvoid) {
                return GroupChat.this.U().a(string2);
            }

            @Override
            protected void a(ChatStatus chatStatus) {
                if (chatStatus == ChatStatus.a) {
                    GroupChat.this.a(GroupStatusChatMessage.a(string3, string2));
                }
                completion.a(chatStatus);
            }
        }.a(PriorityExecutor.a);
    }

    @Override
    void a(Collection<AccountIcon> collection) {
    }

    public void a(final Collection<AccountIcon> collection, final Completion<ChatStatus> completion) {
        PriorityExecutor.a.execute(new Runnable(){

            @Override
            public void run() {
                GroupChat.this.U().a(collection, MUCAffiliation.b, new Completion<ChatStatus>(){

                    @Override
                    public void a(final ChatStatus chatStatus) {
                        GroupChat.this.d.b(new Runnable(){

                            @Override
                            public void run() {
                                if (chatStatus == ChatStatus.a) {
                                    GroupChat.this.b(collection);
                                }
                                completion.a(chatStatus);
                            }
                        });
                    }

                });
            }

        });
    }

    @Override
    public void a(Map<Long, AccountIcon> map) {
        this.e = map;
        this.h = new Date();
        this.P();
        this.G();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    void a(Presence object) {
        MUCItem mUCItem;
        long l;
        block7 : {
            block6 : {
                super.a((Presence)object);
                object = MUCUser.a((Stanza)object);
                if (object == null || (mUCItem = object.g()) == null || mUCItem.f() == null) break block6;
                GroupInfo groupInfo = this.U();
                object = mUCItem.f();
                if (mUCItem.e() == MUCAffiliation.a) {
                    groupInfo.b(this.d.c((String)object));
                    return;
                }
                if (mUCItem.e() == MUCAffiliation.e && groupInfo.a(this.d.c((String)object)) != GroupMemberStatus.a) break block7;
            }
            return;
        }
        try {
            l = Long.parseLong(mUCItem.a());
        }
        catch (NumberFormatException numberFormatException) {
            l = 0;
        }
        this.a((String)object, l);
    }

    public GroupMemberStatus b(long l) {
        return this.U().a(l);
    }

    @Override
    void b(final Completion<ChatStatus> completion) {
        super.b(completion);
        this.U().b(this);
        new Chat<Void, ChatStatus>(new Void[0]){

            protected /* varargs */ ChatStatus a(Void ... arrvoid) {
                return GroupChat.this.W();
            }

            @Override
            protected void a(ChatStatus chatStatus) {
                if (completion != null) {
                    completion.a(chatStatus);
                }
            }
        }.a(PriorityExecutor.a);
    }

    public void b(final Collection<AccountIcon> collection, final Completion<ChatStatus> completion) {
        PriorityExecutor.a.execute(new Runnable(){

            @Override
            public void run() {
                GroupChat.this.U().a(collection, MUCAffiliation.e, new Completion<ChatStatus>(){

                    @Override
                    public void a(final ChatStatus chatStatus) {
                        GroupChat.this.d.b(new Runnable(){

                            @Override
                            public void run() {
                                if (chatStatus == ChatStatus.a) {
                                    GroupChat.this.c(collection);
                                }
                                completion.a(chatStatus);
                            }
                        });
                    }

                });
            }

        });
    }

    @Override
    public void c(final Completion<ChatStatus> completion) {
        if (this.j) {
            this.a(ChatStatus.a, completion);
            return;
        }
        this.j = true;
        GroupInfo groupInfo = this.U();
        groupInfo.h();
        groupInfo.i();
        groupInfo.a(new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                GroupChat.this.a(chatStatus, completion);
            }
        });
    }

    @Override
    public long f() {
        throw new IllegalArgumentException();
    }

    @Override
    public Set<Long> g() {
        ChatManager.n();
        return this.e.keySet();
    }

    @Override
    public boolean i() {
        if (super.i() && !this.i.isEmpty() && !this.e.isEmpty()) {
            return true;
        }
        return false;
    }

}

