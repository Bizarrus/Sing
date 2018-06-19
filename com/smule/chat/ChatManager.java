package com.smule.chat;

import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountPreference;
import com.smule.android.network.models.SNPChat;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.Chat.Options;
import com.smule.chat.Chat.Type;
import com.smule.chat.extensions.GroupStatusExtension.Invite;
import com.smule.chat.extensions.GroupStatusExtension.Join;
import com.smule.chat.extensions.GroupStatusExtension.Leave;
import com.smule.chat.extensions.GroupStatusExtension.Remove;
import com.smule.chat.extensions.GroupStatusExtension.Rename;
import com.smule.chat.extensions.PerformanceExtension.Provider;
import com.smule.chat.extensions.SmuleExtension.SimpleProvider;
import com.smule.chat.extensions.SmuleTimeProvider;
import com.smule.chat.mam.MamManager;
import com.smule.chat.mam.MamManager.MamQueryResult;
import com.smule.chat.mam.provider.MamFinProvider;
import com.smule.chat.mam.provider.MamResultProvider;
import com.smule.chat.smerialization.Smerializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.rsm.packet.RSMSet.PageDirection;
import org.jxmpp.util.XmppStringUtils;

public class ChatManager implements XMPPDelegate {
    private static final String f6991v = ChatManager.class.getName();
    Bucket f6992a;
    private ChatConnectionManager f6993b;
    private ChatConfiguration f6994c;
    private List<WeakReference<ChatManagerListener>> f6995d = new ArrayList();
    private ChatListenerBroadcaster f6996e;
    private ChatManagerPersistence f6997f;
    private Handler f6998g = new Handler(Looper.getMainLooper());
    private final OperationLoader f6999h;
    private Map<String, Chat> f7000i;
    private EnumMap<Bucket, ChatContainer> f7001j;
    private ChatContainer f7002k;
    private ChatContainer f7003l;
    private ChatContainer f7004m;
    private ChatHistoryPruner f7005n;
    private final Map<String, GroupInfo> f7006o;
    private BlockManager f7007p;
    private boolean f7008q;
    private boolean f7009r;
    private Set<String> f7010s;
    private Set<String> f7011t;
    private long f7012u;
    private Operation f7013w;
    private Operation f7014x;
    private Operation f7015y;

    public ChatManager(ChatConfiguration chatConfiguration) {
        this.f6994c = chatConfiguration;
        this.f6993b = new ChatConnectionManager(this, chatConfiguration.mo4108a());
        this.f6999h = new OperationLoader();
        this.f7010s = new HashSet();
        this.f7011t = new HashSet();
        this.f7008q = false;
        this.f7009r = true;
        this.f6996e = new ChatListenerBroadcaster();
        this.f6997f = new ChatManagerPersistence(this, this.f6994c);
        this.f7000i = new HashMap(100);
        this.f7001j = new EnumMap(Bucket.class);
        for (Enum put : Bucket.values()) {
            this.f7001j.put(put, new ChatContainer(0));
        }
        this.f7002k = new ChatContainer(200);
        this.f7003l = new ChatContainer(200);
        this.f7004m = new ChatContainer(100);
        this.f7005n = new ChatHistoryPruner(20);
        this.f7007p = new BlockManager();
        this.f7006o = new HashMap();
        SmackConfiguration.a(new 1(this));
        SmackConfiguration.d = true;
        NotificationCenter.a().a("FOLLOW_STATE_CHANGED", new 2(this));
    }

    public void m8567a(Runnable runnable) {
        m8532l();
        m8520c(runnable);
    }

    public void m8572a(boolean z) {
        m8532l();
        m8520c(new 3(this, z));
    }

    public boolean m8574a() {
        m8532l();
        return this.f6993b.f();
    }

    public void m8583b(boolean z) {
        m8532l();
        this.f6993b.a(z);
    }

    public ConnectionStatus m8576b() {
        m8532l();
        return this.f6993b.a();
    }

    public void m8564a(ChatManagerListener chatManagerListener) {
        m8532l();
        this.f6995d.add(new WeakReference(chatManagerListener));
    }

    public void m8578b(ChatManagerListener chatManagerListener) {
        m8532l();
        for (int i = 0; i < this.f6995d.size(); i++) {
            if (((WeakReference) this.f6995d.get(i)).get() == chatManagerListener) {
                this.f6995d.remove(i);
                return;
            }
        }
    }

    public void m8562a(ChatListener chatListener) {
        m8532l();
        this.f6996e.a(chatListener);
    }

    public void m8577b(ChatListener chatListener) {
        m8532l();
        this.f6996e.b(chatListener);
    }

    public String m8588c() {
        return UserManager.m8111a().m8220o();
    }

    public List<String> m8590d() {
        return UserManager.m8111a().m8221p();
    }

    public Chat m8548a(String str) {
        m8532l();
        return (Chat) this.f7000i.get(XmppStringUtils.d(str));
    }

    public void m8555a(AccountIcon accountIcon, ChatCallback chatCallback) {
        m8532l();
        m8534n();
        Options options = new Options();
        options.a = Type.a;
        options.c = true;
        options.b = accountIcon.jid;
        options.g = Collections.singleton(accountIcon);
        mo4057a(options, chatCallback);
    }

    public void m8568a(String str, ChatCallback chatCallback) {
        m8532l();
        m8534n();
        Options options = new Options();
        options.a = Type.b;
        options.c = true;
        options.b = str;
        mo4057a(options, chatCallback);
    }

    public void m8570a(Collection<AccountIcon> collection, String str, ChatCallback chatCallback) {
        m8532l();
        m8534n();
        String o = m8535o();
        if (o == null) {
            chatCallback.a(null, ChatStatus.b);
            return;
        }
        Options options = new Options();
        options.a = Type.b;
        options.c = true;
        options.d = true;
        options.b = o;
        options.g = collection;
        options.h = str;
        mo4057a(options, chatCallback);
    }

    public void m8561a(Chat chat, Completion<ChatStatus> completion) {
        m8532l();
        if (m8515b(chat, ChatUpdateReason.a)) {
            chat.b(new 4(this, completion, chat));
            m8521d(chat);
        } else if (completion != null) {
            completion.a(ChatStatus.d);
        }
        if (!chat.v()) {
            ActiveChatBatcher.a().b(chat.B(), null);
        }
    }

    public List<Chat> m8550a(Bucket bucket) {
        m8532l();
        return ((ChatContainer) this.f7001j.get(bucket)).e();
    }

    public boolean m8585b(Bucket bucket) {
        m8532l();
        return ((ChatContainer) this.f7001j.get(bucket)).c();
    }

    public int m8586c(Bucket bucket) {
        m8532l();
        int i = 0;
        for (Chat n : ((ChatContainer) this.f7001j.get(bucket)).e()) {
            int i2;
            if (n.n()) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public void m8591d(Bucket bucket) {
        m8532l();
        for (Chat x : ((ChatContainer) this.f7001j.get(bucket)).e()) {
            x.x();
        }
    }

    public void m8556a(Bucket bucket, Type type, String str) {
        m8532l();
        m8512b(bucket, type, str);
    }

    public void m8593e(Bucket bucket) {
        this.f6992a = bucket;
    }

    public Bucket mo4068e() {
        return this.f6992a;
    }

    public void m8581b(String str) {
        m8532l();
        this.f7010s.add(str);
    }

    public boolean m8594f() {
        m8532l();
        return this.f7008q;
    }

    public void m8573a(boolean z, Completion<ChatStatus> completion) {
        m8532l();
        if (z != this.f7008q) {
            AccountPreference accountPreference = new AccountPreference();
            accountPreference.name = "SPARK_PUSH_DISABLE";
            accountPreference.value = Boolean.toString(z);
            UserManager.m8111a().m8174a(accountPreference, new 5(this, z, completion));
            return;
        }
        completion.a(ChatStatus.a);
    }

    public boolean mo4069g() {
        m8532l();
        return this.f7009r;
    }

    public void m8584b(boolean z, Completion<ChatStatus> completion) {
        m8532l();
        if (z != this.f7009r) {
            AccountPreference accountPreference = new AccountPreference();
            accountPreference.name = "SPARK_READRECEIPT_DISABLE";
            accountPreference.value = Boolean.toString(!z);
            UserManager.m8111a().m8174a(accountPreference, new 6(this, z, completion));
            return;
        }
        completion.a(ChatStatus.a);
    }

    public boolean m8575a(long j) {
        m8532l();
        return this.f7007p.a(j);
    }

    public void m8554a(long j, boolean z, Completion<ChatStatus> completion) {
        m8532l();
        this.f7007p.a(j, z, completion);
    }

    public List<Long> m8596h() {
        m8532l();
        return this.f7007p.a();
    }

    public void m8565a(Completion<ChatStatus> completion) {
        m8532l();
        this.f6997f.b(new 7(this, completion));
    }

    public String mo4070i() {
        return m8588c();
    }

    public long mo4071j() {
        return UserManager.m8111a().m8203f();
    }

    public void mo4061a(Smerializable smerializable) {
        this.f6997f.a(smerializable);
    }

    public void mo4064b(Smerializable smerializable) {
        this.f6997f.b(smerializable);
    }

    public long mo4066c(String str) {
        try {
            return Long.parseLong(XmppStringUtils.a(str));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void mo4060a(Chat chat, ChatMessage chatMessage, Message message) {
        this.f6993b.a(chat, chatMessage, message);
    }

    public PacketCollector mo4055a(IQ iq) throws NotConnectedException {
        return this.f6993b.a(iq);
    }

    public PacketCollector mo4054a(StanzaFilter stanzaFilter, Stanza stanza) throws NotConnectedException {
        return this.f6993b.a(stanzaFilter, stanza);
    }

    public void mo4063a(Stanza stanza) throws NotConnectedException {
        this.f6993b.a(stanza);
    }

    public void mo4065b(Runnable runnable) {
        this.f6998g.post(runnable);
    }

    public void mo4056a(long j, Runnable runnable) {
        this.f6998g.postDelayed(runnable, j);
    }

    public long mo4052a(Message message) {
        long j = 0;
        Message.Type a = message.a();
        if (a == Message.Type.b) {
            try {
                j = mo4066c(message.l());
            } catch (NumberFormatException e) {
            }
        } else if (a == Message.Type.c) {
            int indexOf = message.l().indexOf(47);
            if (indexOf >= 0) {
                try {
                    j = Long.parseLong(message.l().substring(indexOf + 1));
                } catch (NumberFormatException e2) {
                }
            }
        }
        return j;
    }

    public MamQueryResult mo4053a(Chat chat, int i, String str) throws Exception {
        String str2;
        MamManager g = this.f6993b.g();
        String c = chat.c();
        if (chat.a() == Type.a) {
            str2 = c;
        } else {
            str2 = null;
        }
        return g.a(c, Integer.valueOf(i), null, null, str2, PageDirection.a, str, i > 1 ? 10000 : 0);
    }

    public GroupInfo mo4067d(String str) {
        GroupInfo groupInfo;
        synchronized (this.f7006o) {
            groupInfo = (GroupInfo) this.f7006o.get(str);
            if (groupInfo == null) {
                groupInfo = new GroupInfo(this, str);
                this.f7006o.put(str, groupInfo);
            }
        }
        return groupInfo;
    }

    public void mo4062a(Collection<AccountIcon> collection) {
        for (Chat a : this.f7000i.values()) {
            a.a(collection);
        }
        for (GroupInfo a2 : this.f7006o.values()) {
            a2.a(collection);
        }
    }

    public long mo4072k() {
        return this.f6993b.d();
    }

    public void mo4058a(Chat chat) {
        if (chat.d() == ChatState.c && chat.e() == ChatStatus.i) {
            m8561a(chat, (Completion) null);
        }
    }

    static void m8532l() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalArgumentException("not called from main Looper");
        }
    }

    private void m8534n() {
        if (!this.f6999h.m8401c("chat-load")) {
            throw new IllegalArgumentException("not setup yet");
        }
    }

    private String m8535o() {
        String c = this.f6993b.c();
        if (c != null) {
            return XmppStringUtils.a(UUID.randomUUID().toString(), c);
        }
        return null;
    }

    private Collection<WeakReference<ChatManagerListener>> m8536p() {
        return new ArrayList(this.f6995d);
    }

    void m8563a(ConnectionStatus connectionStatus) {
        if (connectionStatus == ConnectionStatus.e) {
            m8538r();
            m8541u();
        }
        for (WeakReference weakReference : m8536p()) {
            ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
            if (chatManagerListener != null) {
                chatManagerListener.a(connectionStatus);
            }
        }
    }

    private void m8537q() {
        SparkManager.a().a(new 8(this));
    }

    private void m8538r() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f7012u > 120000) {
            this.f7012u = currentTimeMillis;
            SparkManager.a().a(null);
        }
    }

    private void m8496a(Bucket bucket, List<SNPChat> list) {
        for (SNPChat sNPChat : list) {
            m8512b(bucket, sNPChat.type.equals("ACCT") ? Type.a : Type.b, sNPChat.jid);
        }
    }

    private void m8512b(Bucket bucket, Type type, String str) {
        Chat chat = (Chat) this.f7000i.get(str);
        if (chat != null) {
            chat.E();
        } else if (type == Type.a) {
            Options options = new Options();
            options.b = str;
            options.c = true;
            options.e = true;
            options.a = Type.a;
            options.i = bucket;
            m8513b(options, new 9(this));
        }
    }

    void m8582b(Stanza stanza) {
        if (stanza instanceof Message) {
            m8514b((Message) stanza);
        } else if (stanza instanceof Presence) {
            m8507a((Presence) stanza);
        }
    }

    private void m8514b(Message message) {
        Message.Type a = message.a();
        long a2 = mo4052a(message);
        if (a2 != 0 || a == Message.Type.e) {
            Options options = new Options();
            options.b = XmppStringUtils.d(message.l());
            switch (26.a[a.ordinal()]) {
                case 1:
                    if (!this.f7007p.a(a2)) {
                        options.a = Type.a;
                        options.c = true;
                        break;
                    }
                    return;
                case 2:
                    options.a = Type.b;
                    options.c = false;
                    break;
                case 3:
                    Chat a3 = m8548a(message.l());
                    if (a3 != null) {
                        options.a = a3.a();
                        options.c = false;
                        break;
                    }
                    return;
                default:
                    return;
            }
            boolean a4 = ChatMessage.a(message);
            if (a4) {
                this.f7011t.add(options.b);
            }
            mo4057a(options, new 10(this, message, a4));
        }
    }

    private void m8507a(Presence presence) {
        String d = XmppStringUtils.d(presence.l());
        if (d.equals(m8588c())) {
            m8539s();
            return;
        }
        Chat chat = (Chat) this.f7000i.get(d);
        if (chat != null) {
            chat.a(presence);
        }
    }

    private void m8539s() {
        for (String str : this.f7011t) {
            Options options = new Options();
            options.a = Type.a;
            options.c = false;
            options.b = str;
            mo4057a(options, new 11(this));
        }
        this.f7011t.clear();
    }

    private void m8497a(Chat chat, ChatUpdateReason chatUpdateReason) {
        this.f7000i.put(chat.c(), chat);
        this.f7005n.a(chat);
        m8510b(chat).a(chat);
        ChatContainer c = m8518c(chat);
        c.a(chat);
        this.f6996e.e(chat);
        if (chatUpdateReason != ChatUpdateReason.c) {
            for (WeakReference weakReference : m8536p()) {
                ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
                if (chatManagerListener != null) {
                    chatManagerListener.e(chat);
                }
            }
        }
        if (c.a()) {
            m8561a(c.d(), (Completion) null);
        }
    }

    private boolean m8515b(Chat chat, ChatUpdateReason chatUpdateReason) {
        if (this.f7000i.remove(chat.c()) == null) {
            return false;
        }
        this.f7005n.b(chat);
        m8510b(chat).b(chat);
        m8518c(chat).b(chat);
        this.f6996e.f(chat);
        if (chatUpdateReason != ChatUpdateReason.c) {
            for (WeakReference weakReference : m8536p()) {
                ChatManagerListener chatManagerListener = (ChatManagerListener) weakReference.get();
                if (chatManagerListener != null) {
                    chatManagerListener.f(chat);
                }
            }
        }
        return true;
    }

    private ChatContainer m8510b(Chat chat) {
        return (ChatContainer) this.f7001j.get(chat.b());
    }

    private ChatContainer m8518c(Chat chat) {
        if (chat.b() == Bucket.a) {
            return chat.a() == Type.a ? this.f7003l : this.f7004m;
        } else {
            return this.f7002k;
        }
    }

    private void m8540t() {
        m8532l();
        if (this.f6994c.mo4110c() != 0 && !FakePeerChat.a(this.f6994c.mo4109b(), this.f6994c.mo4110c()) && !this.f7003l.b()) {
            String a = XmppStringUtils.a(Long.toString(this.f6994c.mo4110c()), XmppStringUtils.b(mo4070i()));
            if (!this.f7000i.containsKey(a)) {
                Options options = new Options();
                options.a = Type.a;
                options.i = Bucket.a;
                options.c = true;
                options.f = true;
                options.b = a;
                mo4057a(options, new 12(this));
            }
        }
    }

    public void mo4057a(Options options, ChatCallback chatCallback) {
        m8513b(options, new ChatReadyCallback(chatCallback));
    }

    private void m8513b(Options options, ChatCallback chatCallback) {
        Chat chat = null;
        if (options.b == null) {
            mo4065b(new 13(this, chatCallback));
            return;
        }
        options.b = XmppStringUtils.d(options.b);
        Chat chat2 = (Chat) this.f7000i.get(options.b);
        if (chat2 != null) {
            ChatStatus chatStatus;
            if (chat2.a() != options.a) {
                Log.m7776e(f6991v, "chat found with conflicting jid: " + options.b);
                chatStatus = ChatStatus.e;
            } else {
                chat = chat2;
                chatStatus = ChatStatus.a;
            }
            mo4065b(new 14(this, chatCallback, chat, chatStatus));
        } else if (!options.c) {
            mo4065b(new 15(this, chatCallback));
        } else if (options.f) {
            chatCallback.a(m8494a(null, options), ChatStatus.a);
        } else {
            SNPChat sNPChat = new SNPChat();
            sNPChat.type = options.a == Type.a ? "ACCT" : "GRP";
            sNPChat.jid = options.b;
            ActiveChatBatcher.a().a(sNPChat, new 16(this, options, chatCallback));
        }
    }

    private Chat m8494a(Chat chat, Options options) {
        Chat fakePeerChat = chat == null ? options.a == Type.a ? options.f ? new FakePeerChat(this, options, this.f6994c) : new PeerChat(this, options) : new GroupChat(this, options) : chat;
        m8497a(fakePeerChat, ChatUpdateReason.c);
        if (m8576b() == ConnectionStatus.e) {
            fakePeerChat.M();
        }
        return fakePeerChat;
    }

    private void m8541u() {
        ArrayList arrayList = new ArrayList();
        for (Chat chat : this.f7000i.values()) {
            if (chat.K()) {
                arrayList.add(chat);
            }
        }
        m8505a(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Chat) it.next()).M();
        }
    }

    private void m8505a(ArrayList<Chat> arrayList) {
        Collections.sort(arrayList, new 17(this));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Chat chat = (Chat) it.next();
            if (this.f7010s.remove(chat.c())) {
                chat.J();
            }
        }
    }

    public void mo4059a(Chat chat, Bucket bucket) {
        if (chat.b() != bucket || !this.f7000i.containsKey(chat.c())) {
            m8515b(chat, ChatUpdateReason.b);
            chat.a(bucket);
            m8497a(chat, ChatUpdateReason.b);
        }
    }

    private void m8511b(long j) {
        Chat c = m8517c(j);
        if (c != null) {
            c.A();
        }
    }

    private Chat m8517c(long j) {
        for (Chat chat : this.f7000i.values()) {
            if (chat.a() == Type.a && mo4066c(chat.c()) == j) {
                return chat;
            }
        }
        return null;
    }

    private void m8542v() {
        ProviderManager.b("fin", "urn:xmpp:mam:0", new MamFinProvider());
        ProviderManager.b("result", "urn:xmpp:mam:0", new MamResultProvider());
        ProviderManager.b("x", "http://jabber.org/protocol/muc#user", new SmuleMUCUserProvider());
        ProviderManager.b("time", "urn:xmpp:time");
        ProviderManager.a("time", "urn:xmpp:time", new SmuleTimeProvider());
        ProviderManager.b("performance", "urn:x-smule:xmpp", new Provider());
        ProviderManager.b("join", "urn:x-smule:xmpp", new SimpleProvider(Join.class));
        ProviderManager.b("leave", "urn:x-smule:xmpp", new SimpleProvider(Leave.class));
        ProviderManager.b("invite", "urn:x-smule:xmpp", new Invite.Provider());
        ProviderManager.b("remove", "urn:x-smule:xmpp", new Remove.Provider());
        ProviderManager.b("rename", "urn:x-smule:xmpp", new Rename.Provider());
    }

    private void m8543w() {
        if (this.f7013w == null) {
            this.f7013w = new 18(this);
            this.f6999h.m8393a("chat-smack", null, this.f7013w);
        }
    }

    private void m8544x() {
        if (this.f7014x == null) {
            this.f7014x = new 19(this);
            this.f6999h.m8393a("chat-services", null, this.f7014x);
        }
    }

    private void m8545y() {
        if (this.f7015y == null) {
            this.f7015y = new 20(this);
            this.f6999h.m8393a("chat-load", Arrays.asList(new String[]{"chat-services", "chat-smack"}), this.f7015y);
        }
    }

    private void m8546z() {
        mo4065b(new 21(this));
    }

    private void m8521d(Chat chat) {
        mo4065b(new 22(this, chat));
    }

    private void m8525e(Chat chat) {
        mo4065b(new 23(this, chat));
    }

    private void m8504a(GroupChat groupChat) {
        mo4065b(new 24(this, groupChat));
    }

    private void m8520c(Runnable runnable) {
        m8543w();
        m8544x();
        m8545y();
        this.f6999h.m8396a(Collections.singleton("chat-load"), runnable);
    }

    private void m8506a(ArrayList<Chat> arrayList, ArrayList<GroupInfo> arrayList2) {
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            GroupInfo groupInfo = (GroupInfo) it.next();
            groupInfo.a(this);
            this.f7006o.put(groupInfo.c(), groupInfo);
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
            Chat chat = (Chat) it.next();
            if (!this.f7000i.containsKey(chat.c())) {
                chat.a(this);
                m8497a(chat, ChatUpdateReason.c);
            }
        }
        m8540t();
        it = this.f7006o.values().iterator();
        while (it.hasNext()) {
            groupInfo = (GroupInfo) it.next();
            if (groupInfo.a()) {
                this.f6997f.b(groupInfo);
                it.remove();
            }
        }
        m8537q();
    }

    private void m8523d(Runnable runnable) {
        List arrayList = new ArrayList(2);
        arrayList.add("SPARK_PUSH_DISABLE");
        arrayList.add("SPARK_READRECEIPT_DISABLE");
        UserManager.m8111a().m8178a(arrayList, new 25(this, runnable));
    }
}
