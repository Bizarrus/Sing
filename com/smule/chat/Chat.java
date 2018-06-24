/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.support.annotation.NonNull
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NoResponseException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Message$Type
 *  org.jivesoftware.smack.packet.Presence
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.packet.XMPPError
 *  org.jivesoftware.smack.packet.XMPPError$Condition
 *  org.jivesoftware.smackx.delay.packet.DelayInformation
 *  org.jivesoftware.smackx.forward.packet.Forwarded
 *  org.jivesoftware.smackx.rsm.packet.RSMSet
 *  org.jxmpp.util.XmppStringUtils
 */
package com.smule.chat;

import android.support.annotation.NonNull;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.SparkManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.SNPChat;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.AccountIconCache;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessageList;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.MuteBatcher;
import com.smule.chat.PriorityExecutor;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.mam.MamManager;
import com.smule.chat.mam.packet.MamPacket;
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
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.jxmpp.util.XmppStringUtils;

public abstract class Chat
implements Smerializable,
Comparable<Chat> {
    private static final String w = Chat.class.getName();
    private static final long x = TimeUnit.DAYS.toMillis(1);
    protected boolean a;
    protected boolean b;
    protected Options c;
    protected XMPPDelegate d;
    protected Map<Long, AccountIcon> e = new HashMap<Long, AccountIcon>(2);
    protected ChatMessageList f = new ChatMessageList();
    protected boolean g;
    protected Date h;
    private String i;
    private ChatMessage j;
    private Bucket k;
    private ChatState l = ChatState.a;
    private ChatStatus m = ChatStatus.a;
    private List<WeakReference<ChatListener>> n = new ArrayList<WeakReference<ChatListener>>(5);
    private Boolean o;
    private BuildState p = BuildState.a;
    private Date q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private String v = "";

    protected Chat() {
    }

    Chat(XMPPDelegate xMPPDelegate, Options options) {
        this();
        this.c = options;
        this.i = options.b;
        this.d = xMPPDelegate;
    }

    static /* synthetic */ String Q() {
        return w;
    }

    private void R() {
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(this);
        }
    }

    private List<WeakReference<ChatListener>> S() {
        return new ArrayList<WeakReference<ChatListener>>(this.n);
    }

    private boolean T() {
        if (this.s || this.g) {
            return true;
        }
        return false;
    }

    private ChatMessage a(String string2) {
        Iterator<ChatMessage> iterator = this.f.f();
        while (iterator.hasNext()) {
            ChatMessage chatMessage = iterator.next();
            if (!chatMessage.f().equals(string2)) continue;
            return chatMessage;
        }
        return null;
    }

    private void a(ChatState object) {
        ChatManager.n();
        if (object != this.l) {
            this.l = object;
            object = this.S().iterator();
            while (object.hasNext()) {
                ChatListener chatListener = (ChatListener)((WeakReference)object.next()).get();
                if (chatListener == null) continue;
                chatListener.a(this, this.l);
            }
        }
    }

    private void a(final Runnable runnable) {
        this.p = BuildState.b;
        this.a(ChatState.a);
        this.a(new SimpleBarrier(0, new Runnable(){

            @Override
            public void run() {
                Chat.this.d.b(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        Chat.this.s = false;
                        Chat.this.N();
                        if (Chat.this.d() == ChatState.a) {
                            Chat.this.p = BuildState.c;
                            Chat.this.m = ChatStatus.a;
                            Chat.this.a(ChatState.b);
                            Chat.this.q = new Date();
                            Chat.this.P();
                        } else {
                            Chat.this.p = BuildState.a;
                        }
                        Chat.this.d.a(Chat.this);
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
            }

        }));
        this.c = null;
    }

    private void a(List<Forwarded> list) {
        for (int i = list.size() - 1; i >= 0; --i) {
            Forwarded forwarded = list.get(i);
            Stanza stanza = forwarded.e();
            if (!(stanza instanceof Message)) continue;
            if (!(stanza = (Message)stanza).k("urn:xmpp:delay") && forwarded.f() != null) {
                stanza.a((ExtensionElement)forwarded.f());
            }
            this.a((Message)stanza, true);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Message object) {
        ChatMessage chatMessage = this.a(object.j());
        if (chatMessage != null) {
            object = (object = object.m()) != null ? (object.a() == XMPPError.Condition.r ? ChatStatus.f : ChatStatus.g) : ChatStatus.b;
            this.a(chatMessage, (ChatStatus)((Object)object));
        }
    }

    static /* synthetic */ String b(Chat chat) {
        return chat.v;
    }

    private void b(final SimpleBarrier simpleBarrier) {
        simpleBarrier.d();
        PriorityExecutor.a.a(new BuildTask(){

            @Override
            public void run() {
                for (ChatMessage chatMessage : Chat.this.f) {
                    Chat.this.a(chatMessage, true);
                }
                simpleBarrier.a();
            }
        });
    }

    private void c(final SimpleBarrier simpleBarrier) {
        if (this.o == null) {
            simpleBarrier.d();
            MuteBatcher.a().a(this.B(), new SparkManager.MuteStateResponseCallback(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(SparkManager iterator) {
                    if (iterator.a()) {
                        boolean bl;
                        block2 : {
                            iterator = iterator.muted.iterator();
                            while (iterator.hasNext()) {
                                if (!iterator.next().jid.equals(Chat.this.c())) continue;
                                bl = true;
                                break block2;
                            }
                            bl = false;
                        }
                        Chat.this.o = (Boolean)bl;
                    }
                    simpleBarrier.a();
                }
            });
        }
    }

    private void c(ChatMessage chatMessage, boolean bl) {
        Log.b(w, "adding: " + chatMessage);
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(this, chatMessage, bl);
        }
    }

    private void h(ChatMessage chatMessage) {
        Log.b(w, "removing: " + chatMessage);
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.b(this, chatMessage);
        }
    }

    private void i(ChatMessage chatMessage) {
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.c(this, chatMessage);
        }
    }

    abstract void A();

    /*
     * Enabled aggressive block sorting
     */
    protected SNPChat B() {
        SNPChat sNPChat = new SNPChat();
        sNPChat.jid = this.c();
        String string2 = this.a() == Type.a ? "ACCT" : "GRP";
        sNPChat.type = string2;
        return sNPChat;
    }

    protected void C() {
        if (this.F()) {
            Set<Long> set = this.g();
            AccountIconCache accountIconCache = AccountIconCache.a();
            accountIconCache.b(set);
            accountIconCache.a(set, new UserManager(){

                @Override
                public void handleResponse(UserManager.AccountIconsResponse accountIconsResponse) {
                    if (accountIconsResponse.a()) {
                        Chat.this.d.a(accountIconsResponse.accountIcons);
                    }
                }
            });
            this.h = new Date();
        }
    }

    protected void D() {
        this.f.c();
        this.j = null;
        this.v = "";
        this.t = false;
        this.R();
        this.P();
    }

    void E() {
        boolean bl = true;
        boolean bl2 = false;
        if (!this.g) {
            this.a = true;
            bl2 = true;
        }
        if (this.d.e() != this.b()) {
            this.b = true;
            bl2 = bl;
        }
        if (bl2) {
            this.P();
            this.I();
        }
    }

    protected boolean F() {
        Date date = new Date(System.currentTimeMillis() - x);
        if (this.h == null || this.h.before(date)) {
            return true;
        }
        return false;
    }

    protected void G() {
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.c(this);
        }
    }

    protected void H() {
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.b(this);
        }
    }

    protected void I() {
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.d(this);
        }
    }

    void J() {
        this.s = true;
    }

    boolean K() {
        if (this.p == BuildState.a) {
            return true;
        }
        return false;
    }

    Date L() {
        return this.q;
    }

    void M() {
        if (this.K()) {
            this.a((Runnable)null);
        }
    }

    protected void N() {
    }

    @Override
    public String O() {
        if (this.v()) {
            return null;
        }
        return "chat-" + XmppStringUtils.a((String)this.c()) + ".data";
    }

    protected void P() {
        if (this.d != null) {
            this.d.a((Smerializable)this);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public int a(@NonNull Chat chat) {
        long l = 0;
        int n = 1;
        if (this == chat) {
            return 0;
        }
        if (this.v() != chat.v()) {
            if (this.v()) return n;
            return -1;
        }
        long l2 = this.j == null ? 0 : this.j.c().getTime();
        if (chat.j != null) {
            l = chat.j.c().getTime();
        }
        if (l < l2) {
            return -1;
        }
        if (l > l2) return n;
        return this.c().compareTo(chat.c());
    }

    public AccountIcon a(long l) {
        ChatManager.n();
        return this.e.get(l);
    }

    public abstract Type a();

    /*
     * Enabled aggressive block sorting
     */
    ChatMessage a(Message object, boolean bl) {
        if (object.a() == Message.Type.e) {
            this.a((Message)object);
            return null;
        } else {
            if (object.f().isEmpty()) return null;
            {
                object = ChatMessage.a((Message)object, this.d);
                this.a((ChatMessage)object, bl);
                this.b((ChatMessage)object, bl);
                return object;
            }
        }
    }

    protected void a(int n, final int n2, final Runnable runnable) {
        ChatManager.n();
        if (this.t || this.u) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        this.u = true;
        new <Void, MamManager.MamQueryResult>(n, new Void[0]){

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            protected /* varargs */ MamManager.MamQueryResult a(Void ... var1_1) {
                try {
                    return Chat.this.d.a(Chat.this, n2, Chat.b(Chat.this));
                }
                catch (SmackException.NoResponseException var1_2) {}
                ** GOTO lbl-1000
                catch (Exception var1_4) {
                    Log.d(Chat.Q(), "error fetching history", var1_4);
                    return null;
                }
                catch (SmackException.NotConnectedException var1_5) {}
lbl-1000: // 2 sources:
                {
                    Log.c(Chat.Q(), "error fetching history", (Throwable)var1_3);
                    return null;
                }
            }

            @Override
            protected void a(MamManager.MamQueryResult mamQueryResult) {
                Chat.this.u = false;
                if (mamQueryResult != null) {
                    if (Chat.this.r) {
                        Chat.this.r = false;
                        if (!mamQueryResult.a.isEmpty()) {
                            Chat.this.D();
                        }
                    }
                    Chat.this.v = mamQueryResult.b.e().a();
                    if (mamQueryResult.b.f()) {
                        Chat.this.t = true;
                    }
                    Chat.this.a(mamQueryResult.a);
                    Chat.this.R();
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        }.a(PriorityExecutor.a);
    }

    protected void a(SimpleBarrier simpleBarrier) {
        this.b(simpleBarrier);
        this.c(simpleBarrier);
    }

    void a(Bucket bucket) {
        this.k = bucket;
    }

    public void a(ChatListener chatListener) {
        ChatManager.n();
        this.n.add(new WeakReference<ChatListener>(chatListener));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void a(ChatMessage chatMessage) {
        ChatManager.n();
        if (chatMessage.b() == 0) {
            chatMessage.a(this.d.j());
        }
        if (chatMessage.b() != this.d.j()) {
            do {
                return;
                break;
            } while (true);
        }
        switch (.b[chatMessage.d().ordinal()]) {
            case 3: {
                return;
            }
            default: {
                return;
            }
            case 1: {
                this.e(chatMessage);
                this.b(chatMessage, false);
                this.f(chatMessage);
                return;
            }
            case 2: 
        }
        switch (.a[chatMessage.e().ordinal()]) {
            default: {
                return;
            }
            case 1: 
            case 2: 
        }
        this.f.b(chatMessage);
        this.h(chatMessage);
        chatMessage.a(new Date());
        this.e(chatMessage);
        this.b(chatMessage, false);
        this.f(chatMessage);
    }

    void a(ChatMessage chatMessage, ChatStatus chatStatus) {
        chatMessage.a(chatStatus);
        this.g(chatMessage);
    }

    protected void a(ChatMessage object, boolean bl) {
        if (object.d() == ChatMessage.State.a && (object = object.a(this, bl, this.d)) != null) {
            object.a(PriorityExecutor.a);
        }
    }

    protected void a(ChatStatus chatStatus) {
        this.m = chatStatus;
        this.a(ChatState.c);
    }

    public final void a(final Completion<ChatStatus> completion) {
        ChatManager.n();
        if (this.d() == ChatState.a) {
            completion.a(ChatStatus.e);
            return;
        }
        if (this.a) {
            this.a = false;
            this.P();
        }
        if (this.d() == ChatState.c) {
            this.a(new Runnable(){

                @Override
                public void run() {
                    Chat.this.c(completion);
                }
            });
            return;
        }
        this.c(completion);
    }

    void a(XMPPDelegate xMPPDelegate) {
        this.d = xMPPDelegate;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        int n = smerializableInputStream.a(1, 2);
        this.i = smerializableInputStream.b();
        int n2 = smerializableInputStream.readInt();
        this.e = new HashMap<Long, AccountIcon>(n2);
        for (int i = 0; i < n2; ++i) {
            AccountIcon accountIcon = SmerializableUtils.c(smerializableInputStream);
            this.e.put(accountIcon.accountId, accountIcon);
        }
        this.j = (ChatMessage)smerializableInputStream.c();
        this.k = Bucket.a(smerializableInputStream);
        this.a = smerializableInputStream.readBoolean();
        this.b = smerializableInputStream.readBoolean();
        this.o = smerializableInputStream.a();
        this.h = SmerializableUtils.a(smerializableInputStream);
        this.q = SmerializableUtils.a(smerializableInputStream);
        if (n >= 2) {
            this.r = smerializableInputStream.readBoolean();
        }
        if (this.j != null) {
            this.f.a(this.j);
        }
        AccountIconCache.a().a(this.e);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(2);
        smerializableOutputStream.a(this.i);
        smerializableOutputStream.writeInt(this.e.size());
        Object object = this.e.values().iterator();
        while (object.hasNext()) {
            SmerializableUtils.a(smerializableOutputStream, object.next());
        }
        object = this.j != null && this.j.n() ? this.j : null;
        smerializableOutputStream.a((Smerializable)object);
        this.k.a(smerializableOutputStream);
        smerializableOutputStream.writeBoolean(this.a);
        smerializableOutputStream.writeBoolean(this.b);
        smerializableOutputStream.a(this.o);
        SmerializableUtils.a(smerializableOutputStream, this.h);
        SmerializableUtils.a(smerializableOutputStream, this.q);
        smerializableOutputStream.writeBoolean(this.r);
    }

    void a(Collection<AccountIcon> object) {
        Set<Long> set = this.g();
        object = object.iterator();
        boolean bl = false;
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            if (!set.contains(accountIcon.accountId) || this.e.put(accountIcon.accountId, accountIcon) == accountIcon || accountIcon.accountId == this.d.j()) continue;
            bl = true;
        }
        if (bl) {
            this.P();
            this.G();
        }
    }

    void a(Presence presence) {
    }

    public void a(final boolean bl, Completion<ChatStatus> object) {
        ChatManager.n();
        if (bl == this.o() || this.v()) {
            this.o = bl;
            if (object != null) {
                object.a((ChatStatus)ChatStatus.a);
            }
            return;
        }
        object = new SparkManager.MuteStateUpdateCallback((Completion)object){
            final /* synthetic */ Completion b;
            {
                this.b = completion;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(SparkManager.MuteStateUpdateResponse object) {
                if (object.a()) {
                    Chat.this.o = (Boolean)bl;
                    object = ChatStatus.a;
                    Chat.this.P();
                } else {
                    object = ChatStatus.b;
                }
                if (this.b != null) {
                    this.b.a(object);
                }
            }
        };
        if (bl) {
            com.smule.android.network.managers.SparkManager.a().a(this.B(), (SparkManager.MuteStateUpdateCallback)object);
            return;
        }
        com.smule.android.network.managers.SparkManager.a().b(this.B(), (SparkManager.MuteStateUpdateCallback)object);
    }

    public Bucket b() {
        ChatManager.n();
        return this.k;
    }

    public void b(ChatListener chatListener) {
        ChatManager.n();
        int n = 0;
        do {
            block4 : {
                block3 : {
                    if (n >= this.n.size()) break block3;
                    if (this.n.get(n).get() != chatListener) break block4;
                    this.n.remove(n);
                }
                return;
            }
            ++n;
        } while (true);
    }

    public void b(ChatMessage chatMessage) {
        ChatManager.n();
        if (chatMessage.m()) {
            chatMessage.j();
            this.g(chatMessage);
            this.a(chatMessage, true);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(ChatMessage chatMessage, boolean bl) {
        ChatMessage chatMessage2 = this.j;
        if (this.f.a(chatMessage)) {
            if (!bl && chatMessage.g() && chatMessage.b() != this.d.j()) {
                this.E();
            }
        } else {
            this.f.c(chatMessage);
        }
        this.j = this.f.e();
        if (this.j == chatMessage && !chatMessage.equals(chatMessage2)) {
            this.P();
        }
        this.c(chatMessage, bl);
    }

    void b(Completion<ChatStatus> completion) {
        if (this.d != null) {
            this.d.b(this);
        }
    }

    public String c() {
        return this.i;
    }

    void c(ChatMessage chatMessage) {
        chatMessage.h();
        this.g(chatMessage);
        this.i(chatMessage);
    }

    abstract void c(Completion<ChatStatus> var1);

    @Override
    public /* synthetic */ int compareTo(@NonNull Object object) {
        return this.a((Chat)object);
    }

    public ChatState d() {
        return this.l;
    }

    void d(ChatMessage chatMessage) {
        this.g(chatMessage);
    }

    public ChatStatus e() {
        return this.m;
    }

    protected void e(ChatMessage object) {
        object = object.c();
        object.setTime(object.getTime() + this.d.k());
    }

    public abstract long f();

    protected void f(ChatMessage chatMessage) {
        chatMessage.i();
        Message message = chatMessage.a(this.a(), this.c());
        this.d.a(this, chatMessage, message);
        this.g(chatMessage);
    }

    public abstract Set<Long> g();

    protected void g(ChatMessage chatMessage) {
        Log.b(w, "updating: " + chatMessage);
        Iterator<WeakReference<ChatListener>> iterator = this.S().iterator();
        while (iterator.hasNext()) {
            ChatListener chatListener = iterator.next().get();
            if (chatListener == null) continue;
            chatListener.a(this, chatMessage);
        }
    }

    public Map<Long, AccountIcon> h() {
        ChatManager.n();
        return Collections.unmodifiableMap(this.e);
    }

    public boolean i() {
        ChatManager.n();
        if (this.q != null) {
            return true;
        }
        return false;
    }

    public List<ChatMessage> j() {
        ChatManager.n();
        return this.f.d();
    }

    public boolean k() {
        ChatManager.n();
        return this.f.a();
    }

    public ChatMessage l() {
        ChatManager.n();
        return this.j;
    }

    public boolean m() {
        ChatManager.n();
        return this.a;
    }

    public boolean n() {
        ChatManager.n();
        return this.b;
    }

    public boolean o() {
        ChatManager.n();
        if (this.o != null && this.o.booleanValue()) {
            return true;
        }
        return false;
    }

    public void p() {
        ChatManager.n();
        this.g = true;
    }

    public void q() {
        ChatManager.n();
        this.g = false;
    }

    public boolean r() {
        ChatManager.n();
        return this.g;
    }

    public void s() {
        this.a(0, 25, null);
    }

    public boolean t() {
        ChatManager.n();
        if (!this.t) {
            return true;
        }
        return false;
    }

    public String toString() {
        return super.toString() + ": {" + (Object)((Object)this.a()) + ": " + this.c() + "}";
    }

    public boolean u() {
        return this.u;
    }

    public boolean v() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void w() {
        ChatManager.n();
        if (this.g || this.f.b() <= 1 || this.v()) {
            return;
        }
        this.f.c();
        this.f.a(this.j);
        this.v = "";
        this.t = false;
    }

    void x() {
        ChatManager.n();
        if (this.b) {
            this.b = false;
            this.P();
        }
    }

    void y() {
        this.R();
    }

    void z() {
        this.r = true;
        this.v = "";
        this.t = false;
        this.P();
    }

    public static enum Bucket {
        a(0),
        b(1);
        
        private final int c;

        private Bucket(int n2) {
            this.c = n2;
        }

        public static Bucket a(SmerializableInputStream smerializableInputStream) throws IOException {
            if (smerializableInputStream.readInt() != 0) {
                throw new InvalidClassException("bad version");
            }
            int n = smerializableInputStream.readInt();
            if (n == Bucket.a.c) {
                return a;
            }
            if (n == Bucket.b.c) {
                return b;
            }
            throw new InvalidClassException("bad value: " + n);
        }

        public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
            smerializableOutputStream.writeInt(0);
            smerializableOutputStream.writeInt(this.c);
        }
    }

    private static enum BuildState {
        a,
        b,
        c;
        

        private BuildState() {
        }
    }

    protected abstract class BuildTask
    implements PriorityExecutor.Task {
        protected BuildTask() {
        }

        @Override
        public int a() {
            if (Chat.this.T()) {
                return 100;
            }
            return -100;
        }
    }

    public static enum ChatState {
        a,
        b,
        c;
        

        private ChatState() {
        }
    }

    static class Options {
        public Type a;
        public String b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        public Collection<AccountIcon> g;
        public String h;
        public Bucket i;

        Options() {
        }
    }

    public static enum Type {
        a,
        b;
        

        private Type() {
        }
    }

}

