package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.ChatPhasedTask;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.Chat.Type;
import com.smule.chat.GroupStatusChatMessage.Status;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.FromMatchesFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.address.packet.MultipleAddresses;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence;
import org.jivesoftware.smackx.muc.packet.MUCInitialPresence.History;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;

public class GroupChat extends Chat implements Listener {
    private static final String f18217k = GroupChat.class.getName();
    private String f18218i;
    private boolean f18219j;

    class C37365 implements Runnable {
        final /* synthetic */ GroupChat f18210a;

        C37365(GroupChat groupChat) {
            this.f18210a = groupChat;
        }

        public void run() {
            GroupInfo a = this.f18210a.m19502U();
            a.m19587i();
            a.m19577b(null);
        }
    }

    class C37376 implements Runnable {
        final /* synthetic */ GroupChat f18211a;

        C37376(GroupChat groupChat) {
            this.f18211a = groupChat;
        }

        public void run() {
            GroupInfo a = this.f18211a.m19502U();
            a.m19586h();
            a.m19577b(null);
        }
    }

    class C37409 implements Runnable {
        final /* synthetic */ GroupChat f18216a;

        C37409(GroupChat groupChat) {
            this.f18216a = groupChat;
        }

        public void run() {
            this.f18216a.m19514a(this.f18216a.d.i(), 0);
        }
    }

    GroupChat(XMPPDelegate xMPPDelegate, Options options) {
        super(xMPPDelegate, options);
        m19191a(Bucket.INBOX);
        GroupInfo U = m19502U();
        U.m19568a((Listener) this);
        this.f18218i = U.m19582d();
    }

    public Type mo6335a() {
        return Type.GROUP;
    }

    public Set<Long> mo6342g() {
        ChatManager.l();
        return this.e.keySet();
    }

    public Set<Long> mo6333R() {
        ChatManager.l();
        return m19502U().m19584f();
    }

    public GroupMemberStatus m19541b(long j) {
        return m19502U().m19566a(j);
    }

    public String mo6334S() {
        ChatManager.l();
        return this.f18218i;
    }

    public void m19536a(String str, Completion<ChatStatus> completion) {
        ChatManager.l();
        final String str2 = this.f18218i;
        final String str3 = str;
        final Completion<ChatStatus> completion2 = completion;
        new ChatPhasedTask<Void, ChatStatus>(this, new Void[0]) {
            final /* synthetic */ GroupChat f18195e;

            protected ChatStatus m19481a(Void... voidArr) {
                return this.f18195e.m19502U().m19565a(str3);
            }

            protected void m19483a(ChatStatus chatStatus) {
                if (chatStatus == ChatStatus.OK) {
                    this.f18195e.mo6346a(GroupStatusChatMessage.m19620a(str2, str3));
                }
                completion2.mo6329a(chatStatus);
            }
        }.a(PriorityExecutor.f18318a);
    }

    public long mo6341f() {
        throw new IllegalArgumentException();
    }

    public void m19538a(final Collection<AccountIcon> collection, final Completion<ChatStatus> completion) {
        PriorityExecutor.f18318a.execute(new Runnable(this) {
            final /* synthetic */ GroupChat f18201c;

            class C37301 implements Completion<ChatStatus> {
                final /* synthetic */ C37312 f18198a;

                C37301(C37312 c37312) {
                    this.f18198a = c37312;
                }

                public void m19485a(final ChatStatus chatStatus) {
                    this.f18198a.f18201c.d.b(new Runnable(this) {
                        final /* synthetic */ C37301 f18197b;

                        public void run() {
                            if (chatStatus == ChatStatus.OK) {
                                this.f18197b.f18198a.f18201c.m19520b(collection);
                            }
                            completion.mo6329a(chatStatus);
                        }
                    });
                }
            }

            public void run() {
                this.f18201c.m19502U().m19573a(collection, MUCAffiliation.b, new C37301(this));
            }
        });
    }

    public void m19543b(final Collection<AccountIcon> collection, final Completion<ChatStatus> completion) {
        PriorityExecutor.f18318a.execute(new Runnable(this) {
            final /* synthetic */ GroupChat f18207c;

            class C37331 implements Completion<ChatStatus> {
                final /* synthetic */ C37343 f18204a;

                C37331(C37343 c37343) {
                    this.f18204a = c37343;
                }

                public void m19487a(final ChatStatus chatStatus) {
                    this.f18204a.f18207c.d.b(new Runnable(this) {
                        final /* synthetic */ C37331 f18203b;

                        public void run() {
                            if (chatStatus == ChatStatus.OK) {
                                this.f18203b.f18204a.f18207c.m19522c(collection);
                            }
                            completion.mo6329a(chatStatus);
                        }
                    });
                }
            }

            public void run() {
                this.f18207c.m19502U().m19573a(collection, MUCAffiliation.e, new C37331(this));
            }
        });
    }

    public boolean mo6343i() {
        return (!super.mo6343i() || this.f18218i.isEmpty() || this.e.isEmpty()) ? false : true;
    }

    public void mo6340c(final Completion<ChatStatus> completion) {
        if (this.f18219j) {
            m19509a(ChatStatus.OK, (Completion) completion);
            return;
        }
        this.f18219j = true;
        GroupInfo U = m19502U();
        U.m19586h();
        U.m19587i();
        U.m19567a(new Completion<ChatStatus>(this) {
            final /* synthetic */ GroupChat f18209b;

            public void m19489a(ChatStatus chatStatus) {
                this.f18209b.m19509a(chatStatus, completion);
            }
        });
    }

    private void m19509a(ChatStatus chatStatus, Completion<ChatStatus> completion) {
        switch (m19502U().m19566a(this.d.j())) {
            case NONE:
                completion.mo6329a(chatStatus);
                return;
            case PENDING:
                m19524d((Completion) completion);
                m19171C();
                return;
            case JOINED:
                m19524d((Completion) completion);
                m19171C();
                return;
            default:
                return;
        }
    }

    void mo6358a(Presence presence) {
        super.mo6358a(presence);
        MUCUser a = MUCUser.a(presence);
        if (a != null) {
            MUCItem g = a.g();
            if (g != null && g.f() != null) {
                GroupInfo U = m19502U();
                String f = g.f();
                if (g.e() == MUCAffiliation.a) {
                    U.m19576b(this.d.c(f));
                } else if (g.e() == MUCAffiliation.e && U.m19566a(this.d.c(f)) != GroupMemberStatus.NONE) {
                    long parseLong;
                    try {
                        parseLong = Long.parseLong(g.a());
                    } catch (NumberFormatException e) {
                        parseLong = 0;
                    }
                    m19514a(f, parseLong);
                }
            }
        }
    }

    ChatMessage mo6336a(Message message, boolean z) {
        if (!z) {
            long a = this.d.a(message);
            MultipleAddresses multipleAddresses = (MultipleAddresses) message.c("addresses", "http://jabber.org/protocol/address");
            if (multipleAddresses != null) {
                if (multipleAddresses.a(MultipleAddresses.Type.g).size() > 0) {
                    z = true;
                    ChatMessage l = m19224l();
                    if (!(l == null || m19515a(l, message))) {
                        m19172D();
                    }
                }
            } else if (a == this.d.j()) {
                return null;
            }
        }
        ChatMessage a2 = super.mo6336a(message, z);
        if (z || a2 == null || a2.mo6360a() != ChatMessage.Type.GROUP_STATUS) {
            return a2;
        }
        m19513a((GroupStatusChatMessage) a2);
        return a2;
    }

    private boolean m19515a(ChatMessage chatMessage, Message message) {
        return chatMessage.m19389f().equals(message.j()) && chatMessage.m19385b() == this.d.a(message);
    }

    private GroupInfo m19502U() {
        return this.d.d(m19209c());
    }

    private void m19513a(GroupStatusChatMessage groupStatusChatMessage) {
        if (groupStatusChatMessage.m19629o() == Status.INVITED) {
            PriorityExecutor.f18318a.execute(new C37365(this));
        } else if (groupStatusChatMessage.m19629o() == Status.RENAMED) {
            PriorityExecutor.f18318a.execute(new C37376(this));
        }
    }

    void mo6332A() {
    }

    void mo6356a(Collection<AccountIcon> collection) {
    }

    void mo6339b(final Completion<ChatStatus> completion) {
        super.mo6339b((Completion) completion);
        m19502U().m19578b((Listener) this);
        new ChatPhasedTask<Void, ChatStatus>(this, new Void[0]) {
            final /* synthetic */ GroupChat f18213b;

            protected ChatStatus m19491a(Void... voidArr) {
                return this.f18213b.m19504W();
            }

            protected void m19493a(ChatStatus chatStatus) {
                if (completion != null) {
                    completion.mo6329a(chatStatus);
                }
            }
        }.a(PriorityExecutor.f18318a);
    }

    private void m19524d(final Completion<ChatStatus> completion) {
        new ChatPhasedTask<Void, ChatStatus>(this, new Void[0]) {
            final /* synthetic */ GroupChat f18215b;

            protected ChatStatus m19495a(Void... voidArr) {
                return this.f18215b.m19506a(1, false);
            }

            protected void m19497a(ChatStatus chatStatus) {
                if (completion != null) {
                    completion.mo6329a(chatStatus);
                }
            }
        }.a(PriorityExecutor.f18318a);
    }

    private ChatStatus m19506a(int i, boolean z) {
        Stanza presence = new Presence(Presence.Type.a);
        presence.g(m19505X());
        History history = new History();
        history.a(i);
        ExtensionElement mUCInitialPresence = new MUCInitialPresence();
        mUCInitialPresence.a(history);
        presence.a(mUCInitialPresence);
        try {
            Presence presence2 = (Presence) this.d.a(new AndFilter(new StanzaFilter[]{FromMatchesFilter.b(m19209c()), StanzaTypeFilter.a}), presence).d();
            if (presence2.m() != null) {
                throw new XMPPErrorException(presence2.m());
            }
            if (!z) {
                MUCUser a = MUCUser.a(presence2);
                if (a != null && a.i().contains(MUCUser.Status.a)) {
                    Log.d(f18217k, "destroying re-created MUC: " + m19209c());
                    m19502U().m19575b("group shouldn't have been created");
                    return ChatStatus.DELETED;
                }
            }
            return ChatStatus.OK;
        } catch (Throwable e) {
            Log.e(f18217k, "could not enter " + m19209c() + ": " + e.toString());
            ChatStatus a2 = ChatStatus.m19416a(e);
            if (a2.m19417a()) {
                return a2;
            }
            this.d.b(new C37409(this));
            return a2;
        }
    }

    protected void mo6337a(SimpleBarrier simpleBarrier) {
        m19517b(simpleBarrier);
        m19521c(simpleBarrier);
        m19523d(simpleBarrier);
        super.mo6337a(simpleBarrier);
    }

    private void m19517b(final SimpleBarrier simpleBarrier) {
        if (this.c == null || !this.c.f17956d) {
            simpleBarrier.m19037d();
            PriorityExecutor.f18318a.m19668a(new BuildTask(this) {
                final /* synthetic */ GroupChat f18173b;

                class C37231 implements Completion<ChatStatus> {
                    final /* synthetic */ AnonymousClass10 f18171a;

                    C37231(AnonymousClass10 anonymousClass10) {
                        this.f18171a = anonymousClass10;
                    }

                    public void m19476a(final ChatStatus chatStatus) {
                        if (chatStatus == ChatStatus.OK) {
                            simpleBarrier.m19034a();
                        } else {
                            this.f18171a.f18173b.d.b(new Runnable(this) {
                                final /* synthetic */ C37231 f18170b;

                                public void run() {
                                    this.f18170b.f18171a.f18173b.m19196a(chatStatus);
                                    simpleBarrier.m19034a();
                                }
                            });
                        }
                    }
                }

                public void run() {
                    this.f18173b.m19502U().m19577b(new C37231(this));
                }
            });
        }
    }

    private void m19521c(final SimpleBarrier simpleBarrier) {
        final boolean z = this.c != null && this.c.f17956d;
        simpleBarrier.m19037d();
        PriorityExecutor.f18318a.m19668a(new BuildTask(this) {
            final /* synthetic */ GroupChat f18179d;

            public void run() {
                if (this.f18179d.m19212d() == ChatState.ERROR) {
                    simpleBarrier.m19034a();
                    return;
                }
                final GroupMemberStatus a = this.f18179d.m19502U().m19566a(this.f18179d.d.j());
                final ChatStatus a2 = this.f18179d.m19506a(1, z);
                this.f18179d.d.b(new Runnable(this) {
                    final /* synthetic */ AnonymousClass11 f18176c;

                    public void run() {
                        if (a2 == ChatStatus.OK) {
                            if (a == GroupMemberStatus.PENDING) {
                                this.f18176c.f18179d.mo6346a(GroupStatusChatMessage.m19622r());
                            }
                        } else if (a == GroupMemberStatus.NONE) {
                            this.f18176c.f18179d.m19196a(a2);
                        }
                        simpleBarrier.m19034a();
                    }
                });
            }
        });
    }

    private void m19523d(final SimpleBarrier simpleBarrier) {
        if (this.c != null) {
            simpleBarrier.m19037d();
            final String str = this.c.f17960h;
            final Collection collection = this.c.f17959g;
            PriorityExecutor.f18318a.m19668a(new BuildTask(this) {
                final /* synthetic */ GroupChat f18188e;

                class C37272 implements Completion<ChatStatus> {
                    final /* synthetic */ AnonymousClass12 f18184a;

                    C37272(AnonymousClass12 anonymousClass12) {
                        this.f18184a = anonymousClass12;
                    }

                    public void m19478a(final ChatStatus chatStatus) {
                        this.f18184a.f18188e.d.b(new Runnable(this) {
                            final /* synthetic */ C37272 f18183b;

                            public void run() {
                                if (chatStatus != ChatStatus.OK) {
                                    this.f18183b.f18184a.f18188e.m19196a(chatStatus);
                                } else if (collection != null) {
                                    this.f18183b.f18184a.f18188e.m19520b(collection);
                                }
                                simpleBarrier.m19034a();
                            }
                        });
                    }
                }

                public void run() {
                    if (this.f18188e.m19212d() == ChatState.ERROR) {
                        simpleBarrier.m19034a();
                        return;
                    }
                    GroupInfo a = this.f18188e.m19502U();
                    if (str != null) {
                        final ChatStatus a2 = a.m19565a(str);
                        if (a2 != ChatStatus.OK) {
                            this.f18188e.d.b(new Runnable(this) {
                                final /* synthetic */ AnonymousClass12 f18181b;

                                public void run() {
                                    this.f18181b.f18188e.m19196a(a2);
                                    simpleBarrier.m19034a();
                                }
                            });
                            return;
                        }
                    }
                    a.m19573a(collection, MUCAffiliation.b, new C37272(this));
                }
            });
        }
    }

    protected void mo6353N() {
        if (m19212d() == ChatState.ERROR) {
            if (m19214e() == ChatStatus.NON_MEMBER && m19180L() == null) {
                m19196a(ChatStatus.DELETED);
            }
        } else if (!m19502U().m19589k()) {
            m19196a(ChatStatus.NETWORK_ERROR);
        }
    }

    private void m19520b(Collection<AccountIcon> collection) {
        for (AccountIcon accountIcon : collection) {
            if (!accountIcon.jid.equals(this.d.i())) {
                mo6346a(GroupStatusChatMessage.m19621b(accountIcon.jid));
                m19519b(accountIcon.jid);
            }
        }
    }

    private void m19519b(final String str) {
        if (!str.equals(this.d.i())) {
            Options options = new Options();
            options.f17953a = Type.PEER;
            options.f17954b = str;
            options.f17955c = false;
            this.d.a(options, new ChatManager$ChatCallback(this) {
                final /* synthetic */ GroupChat f18190b;

                public void mo6326a(Chat chat, ChatStatus chatStatus) {
                    ChatMessage groupInvitationChatMessage = new GroupInvitationChatMessage(this.f18190b, this.f18190b.d);
                    if (chat == null) {
                        try {
                            this.f18190b.d.a(groupInvitationChatMessage.mo6361a(Type.PEER, str));
                            return;
                        } catch (NotConnectedException e) {
                            Log.e(GroupChat.f18217k, "can't send invitation");
                            return;
                        }
                    }
                    chat.mo6346a(groupInvitationChatMessage);
                }
            });
        }
    }

    private void m19522c(Collection<AccountIcon> collection) {
        for (AccountIcon accountIcon : collection) {
            mo6346a(GroupStatusChatMessage.m19619a(accountIcon.jid));
        }
    }

    private void m19514a(String str, long j) {
        long c = this.d.c(str);
        long j2 = this.d.j();
        if (c == j2) {
            if (!this.e.containsKey(Long.valueOf(j))) {
                j = 0;
            }
            if (m19503V()) {
                m19238z();
                ChatMessage a = GroupStatusChatMessage.m19618a(j, str);
                m19215e(a);
                a.m19391h();
                m19207b(a, false);
            }
            m19502U().m19581c(c);
        } else if (j != j2) {
            m19502U().m19581c(c);
        }
    }

    private boolean m19503V() {
        if (!mo6342g().contains(Long.valueOf(this.d.j()))) {
            return false;
        }
        ChatMessage l = m19224l();
        if (l == null || l.mo6360a() != ChatMessage.Type.GROUP_STATUS) {
            return true;
        }
        GroupStatusChatMessage groupStatusChatMessage = (GroupStatusChatMessage) l;
        if (groupStatusChatMessage.m19629o() == Status.REMOVED) {
            return !groupStatusChatMessage.m19630p().equals(this.d.i());
        } else if (groupStatusChatMessage.m19629o() == Status.LEFT && groupStatusChatMessage.m19385b() == this.d.j()) {
            return false;
        } else {
            return true;
        }
    }

    private ChatStatus m19504W() {
        Stanza a = GroupStatusChatMessage.m19623s().mo6361a(Type.GROUP, m19209c());
        a.g(m19209c());
        a.a(Message.Type.c);
        try {
            this.d.a(a);
        } catch (NotConnectedException e) {
        }
        return m19502U().m19591m();
    }

    private String m19505X() {
        return m19209c() + "/" + Long.toString(this.d.j());
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18218i = smerializableInputStream.m19751b();
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.m19757a(this.f18218i);
    }

    void mo6354a(XMPPDelegate xMPPDelegate) {
        super.mo6354a(xMPPDelegate);
        m19502U().m19568a((Listener) this);
    }

    public void mo6357a(Map<Long, AccountIcon> map) {
        this.e = map;
        this.h = new Date();
        m19184P();
        m19175G();
    }

    public void mo6355a(String str) {
        this.f18218i = str;
        m19184P();
        m19176H();
    }
}
