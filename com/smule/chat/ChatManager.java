/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.os.Handler
 *  android.os.Looper
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.SmackConfiguration
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.debugger.SmackDebuggerFactory
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Message$Type
 *  org.jivesoftware.smack.packet.Presence
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.provider.ProviderManager
 *  org.jivesoftware.smackx.rsm.packet.RSMSet
 *  org.jivesoftware.smackx.rsm.packet.RSMSet$PageDirection
 *  org.jxmpp.util.XmppStringUtils
 */
package com.smule.chat;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.smule.chat.ActiveChatBatcher;
import com.smule.chat.BlockManager;
import com.smule.chat.Chat;
import com.smule.chat.ChatConfiguration;
import com.smule.chat.ChatConnectionManager;
import com.smule.chat.ChatContainer;
import com.smule.chat.ChatHistoryPruner;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerBroadcaster;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerPersistence;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatReadyCallback;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.FakePeerChat;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInfo;
import com.smule.chat.PeerChat;
import com.smule.chat.SmuleMUCUserProvider;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.extensions.GroupStatusExtension;
import com.smule.chat.extensions.PerformanceExtension;
import com.smule.chat.extensions.SmuleExtension;
import com.smule.chat.extensions.SmuleTimeProvider;
import com.smule.chat.mam.MamManager;
import com.smule.chat.mam.provider.MamFinProvider;
import com.smule.chat.mam.provider.MamResultProvider;
import com.smule.chat.smerialization.Smerializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Future;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.debugger.SmackDebuggerFactory;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.jxmpp.util.XmppStringUtils;

public class ChatManager
implements XMPPDelegate {
    private static final String v = ChatManager.class.getName();
    private ChatConnectionManager a;
    private ChatConfiguration b;
    private List<WeakReference<ChatManagerListener>> c;
    private ChatListenerBroadcaster d;
    private ChatManagerPersistence e;
    private Handler f;
    private final OperationLoader g;
    private Map<String, Chat> h;
    private EnumMap<Chat.Bucket, ChatContainer> i;
    private ChatContainer j;
    private ChatContainer k;
    private ChatContainer l;
    private ChatHistoryPruner m;
    private final Map<String, GroupInfo> n;
    private BlockManager o;
    private boolean p;
    private boolean q;
    private Chat.Bucket r;
    private Set<String> s;
    private Set<String> t;
    private long u;
    private OperationLoader.Operation w;
    private OperationLoader.Operation x;
    private OperationLoader.Operation y;

    public ChatManager(ChatConfiguration arrbucket) {
        this.b = arrbucket;
        this.c = new ArrayList<WeakReference<ChatManagerListener>>();
        this.f = new Handler(Looper.getMainLooper());
        this.a = new ChatConnectionManager(this, arrbucket.a());
        this.g = new OperationLoader();
        this.s = new HashSet<String>();
        this.t = new HashSet<String>();
        this.p = false;
        this.q = true;
        this.d = new ChatListenerBroadcaster();
        this.e = new ChatManagerPersistence(this);
        this.h = new HashMap<String, Chat>(100);
        this.i = new EnumMap(Chat.Bucket.class);
        for (Chat.Bucket bucket : Chat.Bucket.values()) {
            this.i.put(bucket, new ChatContainer(0));
        }
        this.j = new ChatContainer(200);
        this.k = new ChatContainer(200);
        this.l = new ChatContainer(100);
        this.m = new ChatHistoryPruner(20);
        this.o = new BlockManager();
        this.n = new HashMap<String, GroupInfo>();
        SmackConfiguration.a((SmackDebuggerFactory)new SmackDebuggerFactory(this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            public org.jivesoftware.smack.debugger.SmackDebugger a(org.jivesoftware.smack.XMPPConnection xMPPConnection, java.io.Writer writer, java.io.Reader reader) throws IllegalArgumentException {
                return new org.jivesoftware.smackx.debugger.android.AndroidDebugger(xMPPConnection, writer, reader);
            }
        });
        SmackConfiguration.d = true;
        NotificationCenter.a().a("FOLLOW_STATE_CHANGED", new Observer(this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            public void update(java.util.Observable object, Object object2) {
                if (object2 instanceof Map && (object = ((Map)object2).get("FOLLOW_STATE_ACCOUNT")) instanceof Long && (object = (Long)object).longValue() != 0) {
                    ChatManager.a(this.a).post(new Runnable(this, (Long)object){
                        final /* synthetic */ Long a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = l;
                        }

                        public void run() {
                            ChatManager.a(this.b.a, this.a);
                        }
                    });
                }
            }
        });
    }

    private void A() {
        if (this.y == null) {
            this.y = new OperationLoader.Operation(this){
                final /* synthetic */ ChatManager a;
                {
                    this.a = chatManager;
                }

                public void a(OperationLoader operationLoader) {
                    ChatManager.e(this.a).a(new ChatManagerPersistence.LoadCallback(this, operationLoader){
                        final /* synthetic */ OperationLoader a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = operationLoader;
                        }

                        public void a(ArrayList<Chat> arrayList, ArrayList<GroupInfo> arrayList2, boolean bl, ChatStatus chatStatus) {
                            ChatManager.a(this.b.a, arrayList, arrayList2);
                            com.smule.chat.PriorityExecutor.a.execute(new Runnable(this, bl, arrayList){
                                final /* synthetic */ boolean a;
                                final /* synthetic */ ArrayList b;
                                final /* synthetic */ com.smule.chat.ChatManager$20$1 c;
                                {
                                    this.c = var1_1;
                                    this.a = bl;
                                    this.b = arrayList;
                                }

                                public void run() {
                                    if (this.a) {
                                        long l = System.currentTimeMillis();
                                        ChatManager.e(this.c.b.a).a(new ArrayList<Smerializable>(this.b));
                                        ChatManager.e(this.c.b.a).a(new ArrayList<Smerializable>(ChatManager.f(this.c.b.a).values()));
                                        long l2 = System.currentTimeMillis();
                                        Log.c(ChatManager.o(), "saved in " + (l2 - l));
                                    }
                                    ChatManager.j(this.c.b.a);
                                    this.c.a.c(this.c.b.f);
                                }
                            });
                        }
                    });
                }
            };
            this.g.a("chat-load", Arrays.asList("chat-services", "chat-smack"), this.y);
        }
    }

    private void B() {
        this.b(new Runnable(this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            public void run() {
                Iterator<E> iterator = ChatManager.k(this.a).iterator();
                while (iterator.hasNext()) {
                    ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)iterator.next()).get();
                    if (chatManagerListener == null) continue;
                    chatManagerListener.a();
                }
            }
        });
    }

    static /* synthetic */ Handler a(ChatManager chatManager) {
        return chatManager.f;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Chat a(Chat chat, Chat.Options options) {
        if (chat == null) {
            chat = options.a == Chat.Type.a ? (options.f ? new FakePeerChat(this, options, this.b) : new PeerChat(this, options)) : new GroupChat(this, options);
        }
        this.a(chat, .c);
        if (this.b() == .e) {
            chat.M();
        }
        return chat;
    }

    static /* synthetic */ Chat a(ChatManager chatManager, Chat chat, Chat.Options options) {
        return chatManager.a(chat, options);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Chat.Bucket bucket, List<SNPChat> object) {
        Iterator iterator = object.iterator();
        while (iterator.hasNext()) {
            SNPChat sNPChat = (SNPChat)iterator.next();
            object = sNPChat.type.equals("ACCT") ? Chat.Type.a : Chat.Type.b;
            this.b(bucket, (Chat.Type)((Object)object), sNPChat.jid);
        }
        return;
    }

    private void a(Chat chat,  object) {
        this.h.put(chat.c(), chat);
        this.m.a(chat);
        this.b(chat).a(chat);
        ChatContainer chatContainer = this.c(chat);
        chatContainer.a(chat);
        this.d.e(chat);
        if (object != .c) {
            object = this.r().iterator();
            while (object.hasNext()) {
                ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)object.next()).get();
                if (chatManagerListener == null) continue;
                chatManagerListener.e(chat);
            }
        }
        if (chatContainer.a()) {
            this.a(chatContainer.d(), (Completion)null);
        }
    }

    static /* synthetic */ void a(ChatManager chatManager, long l) {
        chatManager.b(l);
    }

    static /* synthetic */ void a(ChatManager chatManager, Chat.Bucket bucket, List list) {
        chatManager.a(bucket, list);
    }

    static /* synthetic */ void a(ChatManager chatManager, Chat chat) {
        chatManager.e(chat);
    }

    static /* synthetic */ void a(ChatManager chatManager, GroupChat groupChat) {
        chatManager.a(groupChat);
    }

    static /* synthetic */ void a(ChatManager chatManager, Runnable runnable) {
        chatManager.d(runnable);
    }

    static /* synthetic */ void a(ChatManager chatManager, ArrayList arrayList, ArrayList arrayList2) {
        chatManager.a(arrayList, arrayList2);
    }

    private void a(GroupChat groupChat) {
        this.b(new Runnable(this, groupChat){
            final /* synthetic */ GroupChat a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = groupChat;
            }

            public void run() {
                Iterator<E> iterator = ChatManager.k(this.b).iterator();
                while (iterator.hasNext()) {
                    ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)iterator.next()).get();
                    if (chatManagerListener == null) continue;
                    chatManagerListener.a(this.a);
                }
            }
        });
    }

    private void a(ArrayList<Chat> object) {
        Collections.sort(object, new Comparator<Chat>(this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            /*
             * Enabled aggressive block sorting
             */
            public int a(Chat chat, Chat chat2) {
                if (ChatManager.g(this.a).contains(chat.c()) != ChatManager.g(this.a).contains(chat2.c())) {
                    if (ChatManager.g(this.a).contains(chat.c())) return -1;
                    return 1;
                }
                if (chat.r() != chat2.r()) {
                    if (chat.r()) return -1;
                    return 1;
                }
                ChatMessage chatMessage = chat.l();
                ChatMessage chatMessage2 = chat2.l();
                if (chatMessage != null && chatMessage2 != null) {
                    return - chatMessage.c().compareTo(chatMessage2.c());
                }
                if (chatMessage != null) {
                    return -1;
                }
                if (chatMessage2 == null) return chat.c().compareTo(chat2.c());
                return 1;
            }

            public /* synthetic */ int compare(Object object, Object object2) {
                return this.a((Chat)object, (Chat)object2);
            }
        });
        object = object.iterator();
        while (object.hasNext()) {
            Chat chat = (Chat)object.next();
            if (!this.s.remove(chat.c())) continue;
            chat.J();
        }
    }

    private void a(ArrayList<Chat> iterator, ArrayList<GroupInfo> object) {
        object = object.iterator();
        while (object.hasNext()) {
            GroupInfo groupInfo = (GroupInfo)object.next();
            groupInfo.a(this);
            this.n.put(groupInfo.c(), groupInfo);
        }
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            object = (Chat)iterator.next();
            if (this.h.containsKey(object.c())) continue;
            object.a(this);
            this.a((Chat)object, .c);
        }
        this.v();
        iterator = this.n.values().iterator();
        while (iterator.hasNext()) {
            object = iterator.next();
            if (!object.a()) continue;
            this.e.b((Smerializable)object);
            iterator.remove();
        }
        this.s();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Presence presence) {
        Object object = XmppStringUtils.d((String)presence.l());
        if (object.equals(this.c())) {
            this.u();
            return;
        } else {
            if ((object = this.h.get(object)) == null) return;
            {
                object.a(presence);
                return;
            }
        }
    }

    static /* synthetic */ boolean a(ChatManager chatManager, boolean bl) {
        chatManager.p = bl;
        return bl;
    }

    static /* synthetic */ ChatConnectionManager b(ChatManager chatManager) {
        return chatManager.a;
    }

    private ChatContainer b(Chat chat) {
        return this.i.get((Object)chat.b());
    }

    private void b(long l) {
        Chat chat = this.c(l);
        if (chat != null) {
            chat.A();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Chat.Bucket bucket, Chat.Type object, String string2) {
        Chat chat = this.h.get(string2);
        if (chat != null) {
            chat.E();
            return;
        } else {
            if (object != Chat.Type.a) return;
            {
                object = new Chat.Options();
                object.b = string2;
                object.c = true;
                object.e = true;
                object.a = Chat.Type.a;
                object.i = bucket;
                this.b((Chat.Options)object, new (this){
                    final /* synthetic */ ChatManager a;
                    {
                        this.a = chatManager;
                    }

                    public void a(Chat chat, ChatStatus chatStatus) {
                        if (chat != null) {
                            chat.E();
                        }
                    }
                });
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Chat.Options object,  chatCallback) {
        Object object2 = null;
        if (object.b == null) {
            this.b(new Runnable(this, chatCallback){
                final /* synthetic */  a;
                final /* synthetic */ ChatManager b;
                {
                    this.b = chatManager;
                    this.a = chatCallback;
                }

                public void run() {
                    this.a.a(null, ChatStatus.e);
                }
            });
            return;
        }
        object.b = XmppStringUtils.d((String)object.b);
        Object object3 = this.h.get(object.b);
        if (object3 != null) {
            if (object3.a() != object.a) {
                Log.e(v, "chat found with conflicting jid: " + object.b);
                object3 = ChatStatus.e;
                object = object2;
                object2 = object3;
            } else {
                object2 = ChatStatus.a;
                object = object3;
            }
            this.b(new Runnable(this, chatCallback, (Chat)object, (ChatStatus)((Object)object2)){
                final /* synthetic */  a;
                final /* synthetic */ Chat b;
                final /* synthetic */ ChatStatus c;
                final /* synthetic */ ChatManager d;
                {
                    this.d = chatManager;
                    this.a = chatCallback;
                    this.b = chat;
                    this.c = chatStatus;
                }

                public void run() {
                    this.a.a(this.b, this.c);
                }
            });
            return;
        }
        if (!object.c) {
            this.b(new Runnable(this, chatCallback){
                final /* synthetic */  a;
                final /* synthetic */ ChatManager b;
                {
                    this.b = chatManager;
                    this.a = chatCallback;
                }

                public void run() {
                    this.a.a(null, ChatStatus.d);
                }
            });
            return;
        }
        if (object.f) {
            chatCallback.a(this.a(null, (Chat.Options)object), ChatStatus.a);
            return;
        }
        object3 = new SNPChat();
        object2 = object.a == Chat.Type.a ? "ACCT" : "GRP";
        object3.type = object2;
        object3.jid = object.b;
        ActiveChatBatcher.a().a((SNPChat)object3, new SparkManager.ActiveChatsUpdateCallback(this, (Chat.Options)object, chatCallback){
            final /* synthetic */ Chat.Options a;
            final /* synthetic */  b;
            final /* synthetic */ ChatManager c;
            {
                this.c = chatManager;
                this.a = options;
                this.b = chatCallback;
            }

            public void handleResponse(com.smule.android.network.core.NetworkResponse object) {
                Chat chat = (Chat)ChatManager.c(this.c).get(this.a.b);
                if (object.c()) {
                    object = ChatManager.a(this.c, chat, this.a);
                    this.b.a((Chat)object, ChatStatus.a);
                    return;
                }
                this.b.a(null, ChatStatus.b);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(Message message) {
        boolean bl;
        Chat.Options options;
        block7 : {
            Object object;
            block8 : {
                block6 : {
                    object = message.a();
                    long l = this.a(message);
                    if (l == 0 && object != Message.Type.e) break block6;
                    options = new Chat.Options();
                    options.b = XmppStringUtils.d((String)message.l());
                    switch (.a[object.ordinal()]) {
                        default: {
                            return;
                        }
                        case 1: {
                            if (this.o.a(l)) break;
                            options.a = Chat.Type.a;
                            options.c = true;
                            break block7;
                        }
                        case 2: {
                            options.a = Chat.Type.b;
                            options.c = false;
                            break block7;
                        }
                        case 3: {
                            object = this.a(message.l());
                            if (object != null) break block8;
                        }
                    }
                }
                return;
            }
            options.a = object.a();
            options.c = false;
        }
        if (bl = ChatMessage.a(message)) {
            this.t.add(options.b);
        }
        this.a(options, new (this, message, bl){
            final /* synthetic */ Message a;
            final /* synthetic */ boolean b;
            final /* synthetic */ ChatManager c;
            {
                this.c = chatManager;
                this.a = message;
                this.b = bl;
            }

            public void a(Chat chat, ChatStatus chatStatus) {
                if (chat != null) {
                    chat.a(this.a, this.b);
                }
            }
        });
    }

    private boolean b(Chat chat,  object) {
        if (this.h.remove(chat.c()) != null) {
            this.m.b(chat);
            this.b(chat).b(chat);
            this.c(chat).b(chat);
            this.d.f(chat);
            if (object != .c) {
                object = this.r().iterator();
                while (object.hasNext()) {
                    ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)object.next()).get();
                    if (chatManagerListener == null) continue;
                    chatManagerListener.f(chat);
                }
            }
            return true;
        }
        return false;
    }

    static /* synthetic */ boolean b(ChatManager chatManager, boolean bl) {
        chatManager.q = bl;
        return bl;
    }

    private Chat c(long l) {
        for (Chat chat : this.h.values()) {
            if (chat.a() != Chat.Type.a || this.c(chat.c()) != l) continue;
            return chat;
        }
        return null;
    }

    private ChatContainer c(Chat chat) {
        if (chat.b() == Chat.Bucket.a) {
            if (chat.a() == Chat.Type.a) {
                return this.k;
            }
            return this.l;
        }
        return this.j;
    }

    static /* synthetic */ Map c(ChatManager chatManager) {
        return chatManager.h;
    }

    private void c(Runnable runnable) {
        this.y();
        this.z();
        this.A();
        this.g.a(Collections.singleton("chat-load"), runnable);
    }

    private void d(Chat chat) {
        this.b(new Runnable(this, chat){
            final /* synthetic */ Chat a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = chat;
            }

            public void run() {
                Iterator<E> iterator = ChatManager.k(this.b).iterator();
                while (iterator.hasNext()) {
                    ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)iterator.next()).get();
                    if (chatManagerListener == null) continue;
                    chatManagerListener.h(this.a);
                }
            }
        });
    }

    static /* synthetic */ void d(ChatManager chatManager) {
        chatManager.w();
    }

    private void d(Runnable runnable) {
        ArrayList<String> arrayList = new ArrayList<String>(2);
        arrayList.add("SPARK_PUSH_DISABLE");
        arrayList.add("SPARK_READRECEIPT_DISABLE");
        com.smule.android.network.managers.UserManager.a().a(arrayList, new UserManager(this, runnable){
            final /* synthetic */ Runnable a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = runnable;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void handleResponse(com.smule.android.network.managers.UserManager$AccountPreferencesResponse iterator) {
                if (iterator.a()) {
                    for (AccountPreference accountPreference : iterator.preferences) {
                        if (accountPreference.name.equals("SPARK_PUSH_DISABLE")) {
                            ChatManager.a(this.b, Boolean.parseBoolean(accountPreference.value));
                            continue;
                        }
                        if (!accountPreference.name.equals("SPARK_READRECEIPT_DISABLE")) continue;
                        ChatManager chatManager = this.b;
                        boolean bl = !Boolean.parseBoolean(accountPreference.value);
                        ChatManager.b(chatManager, bl);
                    }
                }
                this.a.run();
            }
        });
    }

    static /* synthetic */ ChatManagerPersistence e(ChatManager chatManager) {
        return chatManager.e;
    }

    private void e(Chat chat) {
        this.b(new Runnable(this, chat){
            final /* synthetic */ Chat a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = chat;
            }

            public void run() {
                Iterator<E> iterator = ChatManager.k(this.b).iterator();
                while (iterator.hasNext()) {
                    ChatManagerListener chatManagerListener = (ChatManagerListener)((WeakReference)iterator.next()).get();
                    if (chatManagerListener == null) continue;
                    chatManagerListener.g(this.a);
                }
            }
        });
    }

    static /* synthetic */ Map f(ChatManager chatManager) {
        return chatManager.n;
    }

    static /* synthetic */ Set g(ChatManager chatManager) {
        return chatManager.s;
    }

    static /* synthetic */ void h(ChatManager chatManager) {
        chatManager.x();
    }

    static /* synthetic */ BlockManager i(ChatManager chatManager) {
        return chatManager.o;
    }

    static /* synthetic */ void j(ChatManager chatManager) {
        chatManager.B();
    }

    static /* synthetic */ Collection k(ChatManager chatManager) {
        return chatManager.r();
    }

    static void n() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalArgumentException("not called from main Looper");
        }
    }

    static /* synthetic */ String o() {
        return v;
    }

    private void p() {
        if (!this.g.c("chat-load")) {
            throw new IllegalArgumentException("not setup yet");
        }
    }

    private String q() {
        String string2 = this.a.c();
        if (string2 != null) {
            return XmppStringUtils.a((String)UUID.randomUUID().toString(), (String)string2);
        }
        return null;
    }

    private Collection<WeakReference<ChatManagerListener>> r() {
        return new ArrayList<WeakReference<ChatManagerListener>>(this.c);
    }

    private void s() {
        SparkManager.a().a(new SparkManager.OfflineMessageCallback(this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            public void handleResponse(com.smule.android.network.managers.SparkManager$OfflineMessageResponse offlineMessageResponse) {
                if (!this.a.a() && offlineMessageResponse.a()) {
                    ChatManager.a(this.a, Chat.Bucket.a, offlineMessageResponse.inbox);
                    ChatManager.a(this.a, Chat.Bucket.b, offlineMessageResponse.other);
                }
            }
        });
    }

    private void t() {
        long l = System.currentTimeMillis();
        if (l - this.u > 120000) {
            this.u = l;
            SparkManager.a().a((SparkManager.OfflineMessageCallback)null);
        }
    }

    private void u() {
        for (String string2 : this.t) {
            Chat.Options options = new Chat.Options();
            options.a = Chat.Type.a;
            options.c = false;
            options.b = string2;
            this.a(options, new (this){
                final /* synthetic */ ChatManager a;
                {
                    this.a = chatManager;
                }

                public void a(Chat chat, ChatStatus chatStatus) {
                    if (chat != null) {
                        chat.y();
                    }
                }
            });
        }
        this.t.clear();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void v() {
        String string2;
        ChatManager.n();
        if (this.b.c() == 0 || FakePeerChat.a(this.b.b(), this.b.c()) || this.k.b() || this.h.containsKey(string2 = XmppStringUtils.a((String)Long.toString(this.b.c()), (String)XmppStringUtils.b((String)this.i())))) {
            return;
        }
        Chat.Options options = new Chat.Options();
        options.a = Chat.Type.a;
        options.i = Chat.Bucket.a;
        options.c = true;
        options.f = true;
        options.b = string2;
        this.a(options, new (this){
            final /* synthetic */ ChatManager a;
            {
                this.a = chatManager;
            }

            public void a(Chat chat, ChatStatus chatStatus) {
            }
        });
    }

    private void w() {
        Object object = new ArrayList<Chat>();
        for (Chat chat : this.h.values()) {
            if (!chat.K()) continue;
            object.add(chat);
        }
        this.a((ArrayList<Chat>)object);
        object = object.iterator();
        while (object.hasNext()) {
            ((Chat)object.next()).M();
        }
    }

    private void x() {
        ProviderManager.b((String)"fin", (String)"urn:xmpp:mam:0", (Object)((Object)new MamFinProvider()));
        ProviderManager.b((String)"result", (String)"urn:xmpp:mam:0", (Object)((Object)new MamResultProvider()));
        ProviderManager.b((String)"x", (String)"http://jabber.org/protocol/muc#user", (Object)((Object)new SmuleMUCUserProvider()));
        ProviderManager.b((String)"time", (String)"urn:xmpp:time");
        ProviderManager.a((String)"time", (String)"urn:xmpp:time", (Object)((Object)new SmuleTimeProvider()));
        ProviderManager.b((String)"performance", (String)"urn:x-smule:xmpp", (Object)((Object)new PerformanceExtension.Provider()));
        ProviderManager.b((String)"join", (String)"urn:x-smule:xmpp", new SmuleExtension.SimpleProvider<GroupStatusExtension.Join>(GroupStatusExtension.Join.class));
        ProviderManager.b((String)"leave", (String)"urn:x-smule:xmpp", new SmuleExtension.SimpleProvider<GroupStatusExtension.Leave>(GroupStatusExtension.Leave.class));
        ProviderManager.b((String)"invite", (String)"urn:x-smule:xmpp", (Object)((Object)new GroupStatusExtension.Invite.Provider()));
        ProviderManager.b((String)"remove", (String)"urn:x-smule:xmpp", (Object)((Object)new GroupStatusExtension.Remove.Provider()));
        ProviderManager.b((String)"rename", (String)"urn:x-smule:xmpp", (Object)((Object)new GroupStatusExtension.Rename.Provider()));
    }

    private void y() {
        if (this.w == null) {
            this.w = new OperationLoader.Operation(this){
                final /* synthetic */ ChatManager a;
                {
                    this.a = chatManager;
                }

                public void a(OperationLoader operationLoader) {
                    android.os.AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable(this, operationLoader){
                        final /* synthetic */ OperationLoader a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = operationLoader;
                        }

                        public void run() {
                            ChatManager.h(this.b.a);
                            this.a.c(this.b.f);
                        }
                    });
                }
            };
            this.g.a("chat-smack", null, this.w);
        }
    }

    private void z() {
        if (this.x == null) {
            this.x = new OperationLoader.Operation(this){
                final /* synthetic */ ChatManager a;
                {
                    this.a = chatManager;
                }

                public void a(OperationLoader operationLoader) {
                    ChatManager.i(this.a).a(new Runnable(this, operationLoader){
                        final /* synthetic */ OperationLoader a;
                        final /* synthetic */  b;
                        {
                            this.b = var1_1;
                            this.a = operationLoader;
                        }

                        public void run() {
                            ChatManager.a(this.b.a, new Runnable(this){
                                final /* synthetic */ com.smule.chat.ChatManager$19$1 a;
                                {
                                    this.a = var1_1;
                                }

                                public void run() {
                                    this.a.a.c(this.a.b.f);
                                }
                            });
                        }
                    });
                }
            };
            this.g.a("chat-services", null, this.x);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public long a(Message object) {
        long l = 0;
        Message.Type type = object.a();
        if (type == Message.Type.b) {
            return this.c(object.l());
        }
        long l2 = l;
        if (type != Message.Type.c) return l2;
        int n = object.l().indexOf(47);
        l2 = l;
        if (n < 0) return l2;
        object = object.l().substring(n + 1);
        try {
            return Long.parseLong((String)object);
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    public Chat a(String string2) {
        ChatManager.n();
        return this.h.get(XmppStringUtils.d((String)string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public MamManager.MamQueryResult a(Chat object, int n, String string2) throws Exception {
        long l;
        MamManager mamManager = this.a.g();
        String string3 = object.c();
        object = object.a() == Chat.Type.a ? string3 : null;
        if (n > 1) {
            l = 10000;
            return mamManager.a(string3, n, null, null, (String)object, RSMSet.PageDirection.a, string2, l);
        }
        l = 0;
        return mamManager.a(string3, n, null, null, (String)object, RSMSet.PageDirection.a, string2, l);
    }

    public List<Chat> a(Chat.Bucket bucket) {
        ChatManager.n();
        return this.i.get((Object)bucket).e();
    }

    @Override
    public PacketCollector a(StanzaFilter stanzaFilter, Stanza stanza) throws SmackException.NotConnectedException {
        return this.a.a(stanzaFilter, stanza);
    }

    @Override
    public PacketCollector a(IQ iQ) throws SmackException.NotConnectedException {
        return this.a.a(iQ);
    }

    @Override
    public void a(long l, Runnable runnable) {
        this.f.postDelayed(runnable, l);
    }

    public void a(long l, boolean bl, Completion<ChatStatus> completion) {
        ChatManager.n();
        this.o.a(l, bl, completion);
    }

    public void a(AccountIcon accountIcon,  chatCallback) {
        ChatManager.n();
        this.p();
        Chat.Options options = new Chat.Options();
        options.a = Chat.Type.a;
        options.c = true;
        options.b = accountIcon.jid;
        options.g = Collections.singleton(accountIcon);
        this.a(options, chatCallback);
    }

    public void a(Chat.Bucket bucket, Chat.Type type, String string2) {
        ChatManager.n();
        this.b(bucket, type, string2);
    }

    @Override
    public void a(Chat.Options options,  chatCallback) {
        this.b(options, new ChatReadyCallback(chatCallback));
    }

    @Override
    public void a(Chat chat) {
        if (chat.d() == Chat.ChatState.c && chat.e() == ChatStatus.i) {
            this.a(chat, (Completion)null);
        }
    }

    @Override
    public void a(Chat chat, Chat.Bucket bucket) {
        if (chat.b() != bucket || !this.h.containsKey(chat.c())) {
            this.b(chat, .b);
            chat.a(bucket);
            this.a(chat, .b);
        }
    }

    @Override
    public void a(Chat chat, ChatMessage chatMessage, Message message) {
        this.a.a(chat, chatMessage, message);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Chat chat, Completion<ChatStatus> completion) {
        ChatManager.n();
        if (this.b(chat, .a)) {
            chat.b(new Completion<ChatStatus>(this, completion, chat){
                final /* synthetic */ Completion a;
                final /* synthetic */ Chat b;
                final /* synthetic */ ChatManager c;
                {
                    this.c = chatManager;
                    this.a = completion;
                    this.b = chat;
                }

                public void a(ChatStatus chatStatus) {
                    if (this.a != null) {
                        this.a.a(chatStatus);
                    }
                    if (this.b.a() == Chat.Type.a) {
                        ChatManager.a(this.c, this.b);
                        return;
                    }
                    if (this.c.d(this.b.c()).b()) {
                        ChatManager.a(this.c, (GroupChat)this.b);
                        return;
                    }
                    ChatManager.a(this.c, this.b);
                }
            });
            this.d(chat);
        } else if (completion != null) {
            completion.a(ChatStatus.d);
        }
        if (!chat.v()) {
            ActiveChatBatcher.a().b(chat.B(), null);
        }
    }

    public void a(ChatListener chatListener) {
        ChatManager.n();
        this.d.a(chatListener);
    }

    void a( connectionStatus) {
        if (connectionStatus == .e) {
            this.t();
            this.w();
        }
        Iterator<WeakReference<ChatManagerListener>> iterator = this.r().iterator();
        while (iterator.hasNext()) {
            ChatManagerListener chatManagerListener = iterator.next().get();
            if (chatManagerListener == null) continue;
            chatManagerListener.a(connectionStatus);
        }
    }

    public void a(ChatManagerListener chatManagerListener) {
        ChatManager.n();
        this.c.add(new WeakReference<ChatManagerListener>(chatManagerListener));
    }

    public void a(Completion<ChatStatus> completion) {
        ChatManager.n();
        this.e.b(new ChatManagerPersistence.LoadCallback(this, completion){
            final /* synthetic */ Completion a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = completion;
            }

            public void a(ArrayList<Chat> iterator, ArrayList<GroupInfo> object2, boolean bl, ChatStatus chatStatus) {
                void var4_6;
                ArrayList<Chat> arrayList = new ArrayList<Chat>();
                iterator = iterator.iterator();
                while (iterator.hasNext()) {
                    Chat chat = (Chat)iterator.next();
                    if (ChatManager.c(this.b).containsKey(chat.c())) continue;
                    arrayList.add(chat);
                }
                ChatManager.a(this.b, arrayList, (ArrayList)object2);
                ChatManager.d(this.b);
                for (Chat chat : arrayList) {
                    ChatManager.e(this.b).a(chat);
                    if (chat.a() != Chat.Type.b) continue;
                    ChatManager.e(this.b).a((Smerializable)ChatManager.f(this.b).get(chat.c()));
                }
                this.a.a(var4_6);
            }
        });
    }

    @Override
    public void a(Smerializable smerializable) {
        this.e.a(smerializable);
    }

    public void a(Runnable runnable) {
        ChatManager.n();
        this.c(runnable);
    }

    public void a(String string2,  chatCallback) {
        ChatManager.n();
        this.p();
        Chat.Options options = new Chat.Options();
        options.a = Chat.Type.b;
        options.c = true;
        options.b = string2;
        this.a(options, chatCallback);
    }

    @Override
    public void a(Collection<AccountIcon> collection) {
        Iterator iterator = this.h.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().a(collection);
        }
        iterator = this.n.values().iterator();
        while (iterator.hasNext()) {
            ((GroupInfo)iterator.next()).a(collection);
        }
    }

    public void a(Collection<AccountIcon> collection, String string2,  chatCallback) {
        ChatManager.n();
        this.p();
        String string3 = this.q();
        if (string3 == null) {
            chatCallback.a(null, ChatStatus.b);
            return;
        }
        Chat.Options options = new Chat.Options();
        options.a = Chat.Type.b;
        options.c = true;
        options.d = true;
        options.b = string3;
        options.g = collection;
        options.h = string2;
        this.a(options, chatCallback);
    }

    @Override
    public void a(Stanza stanza) throws SmackException.NotConnectedException {
        this.a.a(stanza);
    }

    public void a(boolean bl) {
        ChatManager.n();
        this.c(new Runnable(this, bl){
            final /* synthetic */ boolean a;
            final /* synthetic */ ChatManager b;
            {
                this.b = chatManager;
                this.a = bl;
            }

            public void run() {
                this.b.b(new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        ChatManager.b(this.a.b).b(this.a.a);
                    }
                });
            }
        });
    }

    public void a(boolean bl, Completion<ChatStatus> completion) {
        ChatManager.n();
        if (bl != this.p) {
            AccountPreference accountPreference = new AccountPreference();
            accountPreference.name = "SPARK_PUSH_DISABLE";
            accountPreference.value = Boolean.toString(bl);
            com.smule.android.network.managers.UserManager.a().a(accountPreference, new UserManager(this, bl, completion){
                final /* synthetic */ boolean a;
                final /* synthetic */ Completion b;
                final /* synthetic */ ChatManager c;
                {
                    this.c = chatManager;
                    this.a = bl;
                    this.b = completion;
                }

                public void handleResponse(com.smule.android.network.managers.UserManager$UpdateAccountPreferencesResponse updateAccountPreferencesResponse) {
                    if (updateAccountPreferencesResponse.a()) {
                        ChatManager.a(this.c, this.a);
                        this.b.a(ChatStatus.a);
                        return;
                    }
                    this.b.a(ChatStatus.b);
                }
            });
            return;
        }
        completion.a(ChatStatus.a);
    }

    public boolean a() {
        ChatManager.n();
        return this.a.f();
    }

    public boolean a(long l) {
        ChatManager.n();
        return this.o.a(l);
    }

    public  b() {
        ChatManager.n();
        return this.a.a();
    }

    public void b(ChatListener chatListener) {
        ChatManager.n();
        this.d.b(chatListener);
    }

    public void b(ChatManagerListener chatManagerListener) {
        ChatManager.n();
        int n = 0;
        do {
            block4 : {
                block3 : {
                    if (n >= this.c.size()) break block3;
                    if (this.c.get(n).get() != chatManagerListener) break block4;
                    this.c.remove(n);
                }
                return;
            }
            ++n;
        } while (true);
    }

    @Override
    public void b(Smerializable smerializable) {
        this.e.b(smerializable);
    }

    @Override
    public void b(Runnable runnable) {
        this.f.post(runnable);
    }

    public void b(String string2) {
        ChatManager.n();
        this.s.add(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    void b(Stanza stanza) {
        if (stanza instanceof Message) {
            this.b((Message)stanza);
            return;
        } else {
            if (!(stanza instanceof Presence)) return;
            {
                this.a((Presence)stanza);
                return;
            }
        }
    }

    public void b(boolean bl) {
        ChatManager.n();
        this.a.a(bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(boolean bl, Completion<ChatStatus> completion) {
        ChatManager.n();
        if (bl == this.q) {
            completion.a(ChatStatus.a);
            return;
        }
        AccountPreference accountPreference = new AccountPreference();
        accountPreference.name = "SPARK_READRECEIPT_DISABLE";
        boolean bl2 = !bl;
        accountPreference.value = Boolean.toString(bl2);
        com.smule.android.network.managers.UserManager.a().a(accountPreference, new UserManager(this, bl, completion){
            final /* synthetic */ boolean a;
            final /* synthetic */ Completion b;
            final /* synthetic */ ChatManager c;
            {
                this.c = chatManager;
                this.a = bl;
                this.b = completion;
            }

            public void handleResponse(com.smule.android.network.managers.UserManager$UpdateAccountPreferencesResponse updateAccountPreferencesResponse) {
                if (updateAccountPreferencesResponse.a()) {
                    ChatManager.b(this.c, this.a);
                    this.b.a(ChatStatus.a);
                    return;
                }
                this.b.a(ChatStatus.b);
            }
        });
    }

    public boolean b(Chat.Bucket bucket) {
        ChatManager.n();
        return this.i.get((Object)bucket).c();
    }

    public int c(Chat.Bucket object) {
        ChatManager.n();
        int n = 0;
        object = this.i.get(object).e().iterator();
        while (object.hasNext()) {
            if (!((Chat)object.next()).n()) continue;
            ++n;
        }
        return n;
    }

    @Override
    public long c(String string2) {
        try {
            long l = Long.parseLong(XmppStringUtils.a((String)string2));
            return l;
        }
        catch (NumberFormatException numberFormatException) {
            return 0;
        }
    }

    public String c() {
        return com.smule.android.network.managers.UserManager.a().o();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public GroupInfo d(String string2) {
        Map<String, GroupInfo> map = this.n;
        synchronized (map) {
            GroupInfo groupInfo;
            GroupInfo groupInfo2 = groupInfo = this.n.get(string2);
            if (groupInfo == null) {
                groupInfo2 = new GroupInfo(this, string2);
                this.n.put(string2, groupInfo2);
            }
            return groupInfo2;
        }
    }

    public List<String> d() {
        return com.smule.android.network.managers.UserManager.a().p();
    }

    public void d(Chat.Bucket object) {
        ChatManager.n();
        object = this.i.get(object).e().iterator();
        while (object.hasNext()) {
            ((Chat)object.next()).x();
        }
    }

    @Override
    public Chat.Bucket e() {
        return this.r;
    }

    public void e(Chat.Bucket bucket) {
        this.r = bucket;
    }

    public boolean f() {
        ChatManager.n();
        return this.p;
    }

    @Override
    public boolean g() {
        ChatManager.n();
        return this.q;
    }

    public List<Long> h() {
        ChatManager.n();
        return this.o.a();
    }

    @Override
    public String i() {
        return this.c();
    }

    @Override
    public long j() {
        return com.smule.android.network.managers.UserManager.a().f();
    }

    @Override
    public long k() {
        return this.a.d();
    }

    @Override
    public Context l() {
        return this.b.a();
    }

    @Override
    public SharedPreferences m() {
        return this.b.b();
    }

}

