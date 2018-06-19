package com.smule.chat;

import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.SparkManager.MuteStateResponse;
import com.smule.android.network.managers.SparkManager.MuteStateResponseCallback;
import com.smule.android.network.managers.SparkManager.MuteStateUpdateCallback;
import com.smule.android.network.managers.SparkManager.MuteStateUpdateResponse;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.SNPChat;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.PriorityExecutor.Task;
import com.smule.chat.mam.MamManager.MamQueryResult;
import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import com.smule.chat.smerialization.SmerializableUtils;
import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.packet.XMPPError.Condition;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jxmpp.util.XmppStringUtils;

public abstract class Chat implements Smerializable, Comparable<Chat> {
    private static final String f17965w = Chat.class.getName();
    private static final long f17966x = TimeUnit.DAYS.toMillis(1);
    protected boolean f17967a;
    protected boolean f17968b;
    protected Options f17969c;
    protected XMPPDelegate f17970d;
    protected Map<Long, AccountIcon> f17971e;
    protected ChatMessageList f17972f;
    protected boolean f17973g;
    protected Date f17974h;
    private String f17975i;
    private ChatMessage f17976j;
    private Bucket f17977k;
    private ChatState f17978l;
    private ChatStatus f17979m;
    private List<WeakReference<ChatListener>> f17980n;
    private Boolean f17981o;
    private BuildState f17982p;
    private Date f17983q;
    private boolean f17984r;
    private boolean f17985s;
    private boolean f17986t;
    private boolean f17987u;
    private String f17988v;

    class C36813 implements UserManager$AccountIconsResponseCallback {
        final /* synthetic */ Chat f17927a;

        C36813(Chat chat) {
            this.f17927a = chat;
        }

        public void handleResponse(AccountIconsResponse accountIconsResponse) {
            if (accountIconsResponse.a()) {
                this.f17927a.f17970d.a(accountIconsResponse.accountIcons);
            }
        }
    }

    protected abstract class BuildTask implements Task {
        final /* synthetic */ Chat f17934c;

        protected BuildTask(Chat chat) {
            this.f17934c = chat;
        }

        public int mo6313a() {
            return this.f17934c.m19143T() ? 100 : -100;
        }
    }

    public enum Bucket {
        INBOX(0),
        OTHER(1);
        
        private final int f17944c;

        private Bucket(int i) {
            this.f17944c = i;
        }

        public void m19136a(SmerializableOutputStream smerializableOutputStream) throws IOException {
            smerializableOutputStream.writeInt(0);
            smerializableOutputStream.writeInt(this.f17944c);
        }

        public static Bucket m19135a(SmerializableInputStream smerializableInputStream) throws IOException {
            if (smerializableInputStream.readInt() != 0) {
                throw new InvalidClassException("bad version");
            }
            int readInt = smerializableInputStream.readInt();
            if (readInt == INBOX.f17944c) {
                return INBOX;
            }
            if (readInt == OTHER.f17944c) {
                return OTHER;
            }
            throw new InvalidClassException("bad value: " + readInt);
        }
    }

    private enum BuildState {
        UNBUILT,
        BUILDING,
        BUILT
    }

    public enum ChatState {
        LOADING,
        READY,
        ERROR
    }

    static class Options {
        public Type f17953a;
        public String f17954b;
        public boolean f17955c;
        public boolean f17956d;
        public boolean f17957e;
        public boolean f17958f;
        public Collection<AccountIcon> f17959g;
        public String f17960h;
        public Bucket f17961i;

        Options() {
        }
    }

    public enum Type {
        PEER,
        GROUP
    }

    abstract void mo6332A();

    public abstract Type mo6335a();

    abstract void mo6340c(Completion<ChatStatus> completion);

    public abstract long mo6341f();

    public abstract Set<Long> mo6342g();

    public /* synthetic */ int compareTo(@NonNull Object obj) {
        return m19185a((Chat) obj);
    }

    public Bucket mo6347b() {
        ChatManager.l();
        return this.f17977k;
    }

    public void m19192a(ChatListener chatListener) {
        ChatManager.l();
        this.f17980n.add(new WeakReference(chatListener));
    }

    public void m19205b(ChatListener chatListener) {
        ChatManager.l();
        for (int i = 0; i < this.f17980n.size(); i++) {
            if (((WeakReference) this.f17980n.get(i)).get() == chatListener) {
                this.f17980n.remove(i);
                return;
            }
        }
    }

    public String m19209c() {
        return this.f17975i;
    }

    public ChatState m19212d() {
        return this.f17978l;
    }

    public ChatStatus m19214e() {
        return this.f17979m;
    }

    public Map<Long, AccountIcon> m19220h() {
        ChatManager.l();
        return Collections.unmodifiableMap(this.f17971e);
    }

    public AccountIcon m19186a(long j) {
        ChatManager.l();
        return (AccountIcon) this.f17971e.get(Long.valueOf(j));
    }

    public boolean mo6343i() {
        ChatManager.l();
        return this.f17983q != null;
    }

    public List<ChatMessage> m19222j() {
        ChatManager.l();
        return this.f17972f.m19406d();
    }

    public boolean m19223k() {
        ChatManager.l();
        return this.f17972f.m19399a();
    }

    public ChatMessage m19224l() {
        ChatManager.l();
        return this.f17976j;
    }

    public boolean m19225m() {
        ChatManager.l();
        return this.f17967a;
    }

    public boolean m19226n() {
        ChatManager.l();
        return this.f17968b;
    }

    public boolean m19227o() {
        ChatManager.l();
        return this.f17981o != null && this.f17981o.booleanValue();
    }

    public void m19203a(final boolean z, final Completion<ChatStatus> completion) {
        ChatManager.l();
        if (z == m19227o() || mo6350v()) {
            this.f17981o = Boolean.valueOf(z);
            if (completion != null) {
                completion.mo6329a(ChatStatus.OK);
                return;
            }
            return;
        }
        MuteStateUpdateCallback c36791 = new MuteStateUpdateCallback(this) {
            final /* synthetic */ Chat f17924c;

            public void handleResponse(MuteStateUpdateResponse muteStateUpdateResponse) {
                Object obj;
                if (muteStateUpdateResponse.a()) {
                    this.f17924c.f17981o = Boolean.valueOf(z);
                    obj = ChatStatus.OK;
                    this.f17924c.m19184P();
                } else {
                    obj = ChatStatus.NETWORK_ERROR;
                }
                if (completion != null) {
                    completion.mo6329a(obj);
                }
            }
        };
        if (z) {
            SparkManager.m18359a().m18363a(m19170B(), c36791);
        } else {
            SparkManager.m18359a().m18367b(m19170B(), c36791);
        }
    }

    public void m19228p() {
        ChatManager.l();
        this.f17973g = true;
    }

    public void m19229q() {
        ChatManager.l();
        this.f17973g = false;
    }

    public boolean m19230r() {
        ChatManager.l();
        return this.f17973g;
    }

    public final void m19197a(final Completion<ChatStatus> completion) {
        ChatManager.l();
        if (m19212d() == ChatState.LOADING) {
            completion.mo6329a(ChatStatus.BAD_REQUEST);
            return;
        }
        if (this.f17967a) {
            this.f17967a = false;
            m19184P();
        }
        if (m19212d() == ChatState.ERROR) {
            m19153a(new Runnable(this) {
                final /* synthetic */ Chat f17926b;

                public void run() {
                    this.f17926b.mo6340c(completion);
                }
            });
        } else {
            mo6340c((Completion) completion);
        }
    }

    public void mo6346a(ChatMessage chatMessage) {
        ChatManager.l();
        if (chatMessage.m19385b() == 0) {
            chatMessage.m19379a(this.f17970d.j());
        }
        if (chatMessage.m19385b() == this.f17970d.j()) {
            switch (chatMessage.m19387d()) {
                case RAW:
                    m19215e(chatMessage);
                    m19207b(chatMessage, false);
                    mo6348f(chatMessage);
                    return;
                case ERROR:
                    switch (chatMessage.m19388e()) {
                        case QUEUE_FULL:
                        case DELIVERY_FAILED:
                            this.f17972f.m19402b(chatMessage);
                            m19167h(chatMessage);
                            chatMessage.m19384a(new Date());
                            m19215e(chatMessage);
                            m19207b(chatMessage, false);
                            mo6348f(chatMessage);
                            return;
                        default:
                            return;
                    }
                case READY:
                    return;
                default:
                    return;
            }
        }
    }

    public void m19206b(ChatMessage chatMessage) {
        ChatManager.l();
        if (chatMessage.mo6364m()) {
            chatMessage.m19393j();
            m19219g(chatMessage);
            m19195a(chatMessage, true);
        }
    }

    public void m19231s() {
        mo6345a(0, 25, null);
    }

    public boolean mo6349t() {
        ChatManager.l();
        return !this.f17986t;
    }

    public boolean m19233u() {
        return this.f17987u;
    }

    public boolean mo6350v() {
        return false;
    }

    public void mo6351w() {
        ChatManager.l();
        if (!this.f17973g && this.f17972f.m19401b() > 1 && !mo6350v()) {
            this.f17972f.m19403c();
            this.f17972f.m19400a(this.f17976j);
            this.f17988v = "";
            this.f17986t = false;
        }
    }

    public int m19185a(@NonNull Chat chat) {
        long j = 0;
        if (this == chat) {
            return 0;
        }
        if (mo6350v() == chat.mo6350v()) {
            long time = this.f17976j == null ? 0 : this.f17976j.mo6767c().getTime();
            if (chat.f17976j != null) {
                j = chat.f17976j.mo6767c().getTime();
            }
            if (j < time) {
                return -1;
            }
            if (j <= time) {
                return m19209c().compareTo(chat.m19209c());
            }
            return 1;
        } else if (mo6350v()) {
            return 1;
        } else {
            return -1;
        }
    }

    Chat(XMPPDelegate xMPPDelegate, Options options) {
        this();
        this.f17969c = options;
        this.f17975i = options.f17954b;
        this.f17970d = xMPPDelegate;
    }

    public String toString() {
        return super.toString() + ": {" + mo6335a() + ": " + m19209c() + "}";
    }

    void m19191a(Bucket bucket) {
        this.f17977k = bucket;
    }

    void mo6352x() {
        ChatManager.l();
        if (this.f17968b) {
            this.f17968b = false;
            m19184P();
        }
    }

    void mo6339b(Completion<ChatStatus> completion) {
        if (this.f17970d != null) {
            this.f17970d.b(this);
        }
    }

    void m19237y() {
        mo6333R();
    }

    void m19238z() {
        this.f17984r = true;
        this.f17988v = "";
        this.f17986t = false;
        m19184P();
    }

    protected SNPChat m19170B() {
        SNPChat sNPChat = new SNPChat();
        sNPChat.jid = m19209c();
        sNPChat.type = mo6335a() == Type.PEER ? "ACCT" : "GRP";
        return sNPChat;
    }

    protected void m19171C() {
        if (m19174F()) {
            Collection g = mo6342g();
            AccountIconCache a = AccountIconCache.m19106a();
            a.m19113b(g);
            a.m19111a(g, new C36813(this));
            this.f17974h = new Date();
        }
    }

    void m19210c(ChatMessage chatMessage) {
        chatMessage.m19391h();
        m19219g(chatMessage);
        m19168i(chatMessage);
    }

    void m19194a(ChatMessage chatMessage, ChatStatus chatStatus) {
        chatMessage.m19381a(chatStatus);
        m19219g(chatMessage);
    }

    private void m19154a(List<Forwarded> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            Forwarded forwarded = (Forwarded) list.get(size);
            Stanza e = forwarded.e();
            if (e instanceof Message) {
                Message message = (Message) e;
                if (!(message.k("urn:xmpp:delay") || forwarded.f() == null)) {
                    message.a(forwarded.f());
                }
                mo6336a(message, true);
            }
        }
    }

    ChatMessage mo6336a(Message message, boolean z) {
        if (message.a() == org.jivesoftware.smack.packet.Message.Type.e) {
            m19155a(message);
            return null;
        } else if (message.f().isEmpty()) {
            return null;
        } else {
            ChatMessage a = ChatMessage.m19372a(message, this.f17970d);
            m19195a(a, z);
            m19207b(a, z);
            return a;
        }
    }

    private void m19155a(Message message) {
        ChatMessage a = mo6355a(message.j());
        if (a != null) {
            ChatStatus chatStatus;
            XMPPError m = message.m();
            if (m == null) {
                chatStatus = ChatStatus.NETWORK_ERROR;
            } else if (m.a() == Condition.r) {
                chatStatus = ChatStatus.QUEUE_FULL;
            } else {
                chatStatus = ChatStatus.REJECTED;
            }
            m19194a(a, chatStatus);
        }
    }

    private ChatMessage mo6355a(String str) {
        Iterator f = this.f17972f.m19408f();
        while (f.hasNext()) {
            ChatMessage chatMessage = (ChatMessage) f.next();
            if (chatMessage.m19389f().equals(str)) {
                return chatMessage;
            }
        }
        return null;
    }

    protected void m19195a(ChatMessage chatMessage, boolean z) {
        if (chatMessage.m19387d() == State.RAW) {
            ChatPhasedTask a = chatMessage.mo6359a(this, z, this.f17970d);
            if (a != null) {
                a.a(PriorityExecutor.f18318a);
            }
        }
    }

    void mo6358a(Presence presence) {
    }

    void m19213d(ChatMessage chatMessage) {
        m19219g(chatMessage);
    }

    protected void m19172D() {
        this.f17972f.m19403c();
        this.f17976j = null;
        this.f17988v = "";
        this.f17986t = false;
        mo6333R();
        m19184P();
    }

    protected void m19207b(ChatMessage chatMessage, boolean z) {
        ChatMessage chatMessage2 = this.f17976j;
        if (!this.f17972f.m19400a(chatMessage)) {
            this.f17972f.m19404c(chatMessage);
        } else if (!(z || !chatMessage.mo6362g() || chatMessage.m19385b() == this.f17970d.j())) {
            m19173E();
        }
        this.f17976j = this.f17972f.m19407e();
        if (this.f17976j == chatMessage && !chatMessage.equals(chatMessage2)) {
            m19184P();
        }
        m19161c(chatMessage, z);
    }

    protected void mo6345a(int i, int i2, Runnable runnable) {
        ChatManager.l();
        if (!this.f17986t && !this.f17987u) {
            this.f17987u = true;
            final int i3 = i2;
            final Runnable runnable2 = runnable;
            new ChatPhasedTask<Void, MamQueryResult>(this, i, new Void[0]) {
                final /* synthetic */ Chat f17930c;

                protected MamQueryResult m19129a(Void... voidArr) {
                    Throwable e;
                    try {
                        return this.f17930c.f17970d.a(this.f17930c, i3, this.f17930c.f17988v);
                    } catch (NoResponseException e2) {
                        e = e2;
                        Log.c(Chat.f17965w, "error fetching history", e);
                        return null;
                    } catch (NotConnectedException e3) {
                        e = e3;
                        Log.c(Chat.f17965w, "error fetching history", e);
                        return null;
                    } catch (Throwable e4) {
                        Log.d(Chat.f17965w, "error fetching history", e4);
                        return null;
                    }
                }

                protected void m19131a(MamQueryResult mamQueryResult) {
                    this.f17930c.f17987u = false;
                    if (mamQueryResult != null) {
                        if (this.f17930c.f17984r) {
                            this.f17930c.f17984r = false;
                            if (!mamQueryResult.f18357a.isEmpty()) {
                                this.f17930c.m19172D();
                            }
                        }
                        this.f17930c.f17988v = mamQueryResult.f18358b.m19736e().a();
                        if (mamQueryResult.f18358b.m19737f()) {
                            this.f17930c.f17986t = true;
                        }
                        this.f17930c.m19154a(mamQueryResult.f18357a);
                        this.f17930c.mo6333R();
                    }
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }.a(PriorityExecutor.f18318a);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    void m19173E() {
        boolean z = true;
        boolean z2 = false;
        if (!this.f17973g) {
            this.f17967a = true;
            z2 = true;
        }
        if (this.f17970d.e() != mo6347b()) {
            this.f17968b = true;
        } else {
            z = z2;
        }
        if (z) {
            m19184P();
            mo6344I();
        }
    }

    protected void m19215e(ChatMessage chatMessage) {
        Date c = chatMessage.mo6767c();
        c.setTime(c.getTime() + this.f17970d.k());
    }

    protected void mo6348f(ChatMessage chatMessage) {
        chatMessage.m19392i();
        this.f17970d.a(this, chatMessage, chatMessage.mo6361a(mo6335a(), m19209c()));
        m19219g(chatMessage);
    }

    protected boolean m19174F() {
        return this.f17974h == null || this.f17974h.before(new Date(System.currentTimeMillis() - f17966x));
    }

    void mo6356a(Collection<AccountIcon> collection) {
        Set g = mo6342g();
        Object obj = null;
        for (AccountIcon accountIcon : collection) {
            Object obj2;
            if (!g.contains(Long.valueOf(accountIcon.accountId)) || this.f17971e.put(Long.valueOf(accountIcon.accountId), accountIcon) == accountIcon || accountIcon.accountId == this.f17970d.j()) {
                obj2 = obj;
            } else {
                obj2 = 1;
            }
            obj = obj2;
        }
        if (obj != null) {
            m19184P();
            m19175G();
        }
    }

    protected void m19219g(ChatMessage chatMessage) {
        Log.b(f17965w, "updating: " + chatMessage);
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(this, chatMessage);
            }
        }
    }

    private void m19161c(ChatMessage chatMessage, boolean z) {
        Log.b(f17965w, "adding: " + chatMessage);
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(this, chatMessage, z);
            }
        }
    }

    private void m19167h(ChatMessage chatMessage) {
        Log.b(f17965w, "removing: " + chatMessage);
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.b(this, chatMessage);
            }
        }
    }

    private void m19168i(ChatMessage chatMessage) {
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.c(this, chatMessage);
            }
        }
    }

    private void mo6333R() {
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(this);
            }
        }
    }

    protected void m19175G() {
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.c(this);
            }
        }
    }

    protected void m19176H() {
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.b(this);
            }
        }
    }

    protected void mo6344I() {
        for (WeakReference weakReference : mo6334S()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.d(this);
            }
        }
    }

    private List<WeakReference<ChatListener>> mo6334S() {
        return new ArrayList(this.f17980n);
    }

    private void m19150a(ChatState chatState) {
        ChatManager.l();
        if (chatState != this.f17978l) {
            this.f17978l = chatState;
            for (WeakReference weakReference : mo6334S()) {
                ChatListener chatListener = (ChatListener) weakReference.get();
                if (chatListener != null) {
                    chatListener.a(this, this.f17978l);
                }
            }
        }
    }

    void m19178J() {
        this.f17985s = true;
    }

    private boolean m19143T() {
        return this.f17985s || this.f17973g;
    }

    boolean m19179K() {
        return this.f17982p == BuildState.UNBUILT;
    }

    Date m19180L() {
        return this.f17983q;
    }

    void m19181M() {
        if (m19179K()) {
            m19153a(null);
        }
    }

    private void m19153a(final Runnable runnable) {
        this.f17982p = BuildState.BUILDING;
        m19150a(ChatState.LOADING);
        mo6337a(new SimpleBarrier(0, new Runnable(this) {
            final /* synthetic */ Chat f17933b;

            class C36831 implements Runnable {
                final /* synthetic */ C36845 f17931a;

                C36831(C36845 c36845) {
                    this.f17931a = c36845;
                }

                public void run() {
                    this.f17931a.f17933b.f17985s = false;
                    this.f17931a.f17933b.mo6353N();
                    if (this.f17931a.f17933b.m19212d() == ChatState.LOADING) {
                        this.f17931a.f17933b.f17982p = BuildState.BUILT;
                        this.f17931a.f17933b.f17979m = ChatStatus.OK;
                        this.f17931a.f17933b.m19150a(ChatState.READY);
                        this.f17931a.f17933b.f17983q = new Date();
                        this.f17931a.f17933b.m19184P();
                    } else {
                        this.f17931a.f17933b.f17982p = BuildState.UNBUILT;
                    }
                    this.f17931a.f17933b.f17970d.a(this.f17931a.f17933b);
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }

            public void run() {
                this.f17933b.f17970d.b(new C36831(this));
            }
        }));
        this.f17969c = null;
    }

    protected void mo6353N() {
    }

    protected void mo6337a(SimpleBarrier simpleBarrier) {
        mo6338b(simpleBarrier);
        m19160c(simpleBarrier);
    }

    private void mo6338b(final SimpleBarrier simpleBarrier) {
        simpleBarrier.m19037d();
        PriorityExecutor.f18318a.m19668a(new BuildTask(this) {
            final /* synthetic */ Chat f17936b;

            public void run() {
                Iterator it = this.f17936b.f17972f.iterator();
                while (it.hasNext()) {
                    this.f17936b.m19195a((ChatMessage) it.next(), true);
                }
                simpleBarrier.m19034a();
            }
        });
    }

    private void m19160c(final SimpleBarrier simpleBarrier) {
        if (this.f17981o == null) {
            simpleBarrier.m19037d();
            MuteBatcher.m19637a().m19638a(m19170B(), new MuteStateResponseCallback(this) {
                final /* synthetic */ Chat f17938b;

                public void handleResponse(MuteStateResponse muteStateResponse) {
                    if (muteStateResponse.a()) {
                        boolean z;
                        for (SNPChat sNPChat : muteStateResponse.muted) {
                            if (sNPChat.jid.equals(this.f17938b.m19209c())) {
                                z = true;
                                break;
                            }
                        }
                        z = false;
                        this.f17938b.f17981o = Boolean.valueOf(z);
                    }
                    simpleBarrier.m19034a();
                }
            });
        }
    }

    protected void m19196a(ChatStatus chatStatus) {
        this.f17979m = chatStatus;
        m19150a(ChatState.ERROR);
    }

    protected Chat() {
        this.f17980n = new ArrayList(5);
        this.f17972f = new ChatMessageList();
        this.f17971e = new HashMap(2);
        this.f17988v = "";
        this.f17978l = ChatState.LOADING;
        this.f17979m = ChatStatus.OK;
        this.f17982p = BuildState.UNBUILT;
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(2);
        smerializableOutputStream.m19757a(this.f17975i);
        smerializableOutputStream.writeInt(this.f17971e.size());
        for (AccountIcon a : this.f17971e.values()) {
            SmerializableUtils.m19759a(smerializableOutputStream, a);
        }
        Smerializable smerializable = (this.f17976j == null || !this.f17976j.mo6367n()) ? null : this.f17976j;
        smerializableOutputStream.m19754a(smerializable);
        this.f17977k.m19136a(smerializableOutputStream);
        smerializableOutputStream.writeBoolean(this.f17967a);
        smerializableOutputStream.writeBoolean(this.f17968b);
        smerializableOutputStream.m19755a(this.f17981o);
        SmerializableUtils.m19761a(smerializableOutputStream, this.f17974h);
        SmerializableUtils.m19761a(smerializableOutputStream, this.f17983q);
        smerializableOutputStream.writeBoolean(this.f17984r);
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        int a = smerializableInputStream.m19749a(1, 2);
        this.f17975i = smerializableInputStream.m19751b();
        int readInt = smerializableInputStream.readInt();
        this.f17971e = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            AccountIcon c = SmerializableUtils.m19763c(smerializableInputStream);
            this.f17971e.put(Long.valueOf(c.accountId), c);
        }
        this.f17976j = (ChatMessage) smerializableInputStream.m19752c();
        this.f17977k = Bucket.m19135a(smerializableInputStream);
        this.f17967a = smerializableInputStream.readBoolean();
        this.f17968b = smerializableInputStream.readBoolean();
        this.f17981o = smerializableInputStream.m19750a();
        this.f17974h = SmerializableUtils.m19758a(smerializableInputStream);
        this.f17983q = SmerializableUtils.m19758a(smerializableInputStream);
        if (a >= 2) {
            this.f17984r = smerializableInputStream.readBoolean();
        }
        if (this.f17976j != null) {
            this.f17972f.m19400a(this.f17976j);
        }
        AccountIconCache.m19106a().m19112a(this.f17971e);
    }

    void mo6354a(XMPPDelegate xMPPDelegate) {
        this.f17970d = xMPPDelegate;
    }

    public String mo6315O() {
        if (mo6350v()) {
            return null;
        }
        return "chat-" + XmppStringUtils.a(m19209c()) + ".data";
    }

    protected void m19184P() {
        if (this.f17970d != null) {
            this.f17970d.a(this);
        }
    }
}
