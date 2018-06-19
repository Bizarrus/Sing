package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import com.smule.chat.smerialization.SmerializableUtils;
import java.io.IOException;
import java.io.InvalidClassException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.xdata.Form;
import org.jxmpp.util.XmppStringUtils;

public class GroupInfo implements Smerializable {
    private static String f18234m = GroupInfo.class.getName();
    private final Object f18235a;
    private XMPPDelegate f18236b;
    private String f18237c;
    private String f18238d;
    private Set<Long> f18239e;
    private Set<Long> f18240f;
    private Map<Long, AccountIcon> f18241g;
    private List<WeakReference<Listener>> f18242h;
    private boolean f18243i;
    private boolean f18244j;
    private boolean f18245k;
    private ChatStatus f18246l;

    interface Listener {
        void mo6355a(String str);

        void mo6357a(Map<Long, AccountIcon> map);
    }

    GroupInfo(XMPPDelegate xMPPDelegate, String str) {
        this();
        this.f18236b = xMPPDelegate;
        this.f18237c = str;
        this.f18238d = "";
        this.f18239e = new HashSet();
        this.f18240f = new HashSet();
        this.f18246l = ChatStatus.OK;
    }

    void m19568a(Listener listener) {
        synchronized (this.f18235a) {
            this.f18242h.add(new WeakReference(listener));
        }
    }

    void m19578b(Listener listener) {
        synchronized (this.f18235a) {
            for (int i = 0; i < this.f18242h.size(); i++) {
                if (((WeakReference) this.f18242h.get(i)).get() == listener) {
                    this.f18242h.remove(i);
                    break;
                }
            }
        }
    }

    boolean m19574a() {
        boolean z;
        synchronized (this.f18235a) {
            z = this.f18242h.size() == 0;
        }
        return z;
    }

    boolean m19579b() {
        boolean z;
        synchronized (this.f18235a) {
            z = this.f18239e.size() > this.f18240f.size();
        }
        return z;
    }

    String m19580c() {
        return this.f18237c;
    }

    String m19582d() {
        String str;
        synchronized (this.f18235a) {
            str = this.f18238d;
        }
        return str;
    }

    Map<Long, AccountIcon> m19583e() {
        Map hashMap;
        synchronized (this.f18235a) {
            hashMap = new HashMap(this.f18241g);
        }
        return hashMap;
    }

    Set<Long> m19584f() {
        Set<Long> hashSet;
        synchronized (this.f18235a) {
            hashSet = new HashSet(this.f18239e);
            hashSet.removeAll(this.f18240f);
        }
        return hashSet;
    }

    ChatStatus m19565a(String str) {
        IQ mUCOwner = new MUCOwner();
        mUCOwner.g(this.f18237c);
        mUCOwner.a(Type.a);
        try {
            Form a = Form.a(m19552a(mUCOwner));
            if (a == null) {
                Log.e(f18234m, "no room info");
                return ChatStatus.NETWORK_ERROR;
            }
            a = a.d();
            try {
                a.a("muc#roomconfig_roomname", str);
                IQ mUCOwner2 = new MUCOwner();
                mUCOwner2.g(this.f18237c);
                mUCOwner2.a(Type.b);
                mUCOwner2.a(a.c());
                try {
                    m19552a(mUCOwner2);
                    synchronized (this.f18235a) {
                        this.f18238d = str;
                    }
                    m19561q();
                    m19563s();
                    return ChatStatus.OK;
                } catch (Throwable e) {
                    Log.e(f18234m, "couldn't set name");
                    return ChatStatus.m19416a(e);
                }
            } catch (Throwable e2) {
                Log.e(f18234m, "missing expected fields");
                return ChatStatus.m19416a(e2);
            }
        } catch (Throwable e22) {
            return ChatStatus.m19416a(e22);
        }
    }

    void m19567a(final Completion<ChatStatus> completion) {
        PriorityExecutor.f18318a.execute(new Runnable(this) {
            final /* synthetic */ GroupInfo f18224b;

            class C37421 implements Completion<ChatStatus> {
                final /* synthetic */ C37431 f18222a;

                C37421(C37431 c37431) {
                    this.f18222a = c37431;
                }

                public void m19548a(final ChatStatus chatStatus) {
                    this.f18222a.f18224b.f18236b.b(new Runnable(this) {
                        final /* synthetic */ C37421 f18221b;

                        public void run() {
                            completion.mo6329a(chatStatus);
                        }
                    });
                }
            }

            public void run() {
                this.f18224b.m19577b(new C37421(this));
            }
        });
    }

    void m19577b(Completion<ChatStatus> completion) {
        ChatStatus n;
        ChatStatus o;
        try {
            n = m19558n();
        } catch (Throwable e) {
            Log.e(f18234m, "couldn't refresh room info: " + e.toString());
            n = ChatStatus.m19416a(e);
            if (!n.m19417a()) {
                synchronized (this.f18235a) {
                    this.f18243i = true;
                    m19563s();
                }
            }
        }
        try {
            o = m19559o();
        } catch (Throwable e2) {
            Log.e(f18234m, "couldn't refresh members: " + e2.toString());
            o = ChatStatus.m19416a(e2);
            if (!o.m19417a()) {
                synchronized (this.f18235a) {
                    this.f18244j = true;
                    m19563s();
                }
            }
        }
        if (n != ChatStatus.OK) {
            o = n;
        }
        synchronized (this.f18235a) {
            this.f18246l = o;
        }
        m19553a(o, (Completion) completion);
    }

    void m19585g() {
        synchronized (this.f18235a) {
            m19586h();
            m19587i();
            m19588j();
        }
    }

    void m19586h() {
        synchronized (this.f18235a) {
            this.f18243i = false;
            m19563s();
        }
    }

    void m19587i() {
        synchronized (this.f18235a) {
            this.f18244j = false;
            m19563s();
        }
    }

    void m19588j() {
        synchronized (this.f18235a) {
            this.f18245k = false;
            m19563s();
        }
    }

    boolean m19589k() {
        boolean z;
        synchronized (this.f18235a) {
            z = this.f18243i && this.f18244j && this.f18245k;
        }
        return z;
    }

    GroupMemberStatus m19566a(long j) {
        GroupMemberStatus groupMemberStatus;
        synchronized (this.f18235a) {
            if (this.f18240f.contains(Long.valueOf(j))) {
                groupMemberStatus = GroupMemberStatus.PENDING;
            } else if (this.f18239e.contains(Long.valueOf(j))) {
                groupMemberStatus = GroupMemberStatus.JOINED;
            } else {
                groupMemberStatus = GroupMemberStatus.NONE;
            }
        }
        return groupMemberStatus;
    }

    GroupMemberStatus m19590l() {
        return m19566a(this.f18236b.j());
    }

    void m19576b(long j) {
        synchronized (this.f18235a) {
            if (this.f18240f.remove(Long.valueOf(j))) {
                m19560p();
            }
        }
    }

    void m19581c(long j) {
        synchronized (this.f18235a) {
            this.f18240f.remove(Long.valueOf(j));
            this.f18241g.remove(Long.valueOf(j));
            if (this.f18239e.remove(Long.valueOf(j))) {
                m19560p();
            }
        }
    }

    void m19573a(Collection<AccountIcon> collection, MUCAffiliation mUCAffiliation, Completion<ChatStatus> completion) {
        if (!(collection == null || collection.isEmpty())) {
            if (mUCAffiliation != MUCAffiliation.e) {
                AccountIconCache.m19106a().m19110a((Collection) collection);
            }
            IQ mUCAdmin = new MUCAdmin();
            mUCAdmin.a(Type.b);
            mUCAdmin.g(this.f18237c);
            for (AccountIcon accountIcon : collection) {
                if (!accountIcon.jid.equals(this.f18236b.i())) {
                    mUCAdmin.a(new MUCItem(mUCAffiliation, accountIcon.jid));
                }
            }
            try {
                m19552a(mUCAdmin);
            } catch (Throwable e) {
                completion.mo6329a(ChatStatus.m19416a(e));
                return;
            }
        }
        m19587i();
        m19588j();
        m19577b((Completion) completion);
    }

    ChatStatus m19591m() {
        Object obj = 1;
        long j = this.f18236b.j();
        synchronized (this.f18235a) {
            if (!(this.f18239e.contains(Long.valueOf(j)) && !this.f18240f.contains(Long.valueOf(j)) && this.f18239e.size() - this.f18240f.size() == 1)) {
                obj = null;
            }
        }
        if (obj != null) {
            return m19575b("no owners left");
        }
        ChatStatus chatStatus;
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.a(Type.b);
        mUCAdmin.g(this.f18237c);
        mUCAdmin.a(new MUCItem(MUCAffiliation.e, this.f18236b.i()));
        try {
            m19552a(mUCAdmin);
            chatStatus = ChatStatus.OK;
        } catch (Throwable e) {
            chatStatus = ChatStatus.m19416a(e);
        }
        synchronized (this.f18235a) {
            this.f18241g.remove(Long.valueOf(j));
            this.f18239e.remove(Long.valueOf(j));
            this.f18240f.remove(Long.valueOf(j));
            m19560p();
        }
        return chatStatus;
    }

    ChatStatus m19575b(String str) {
        ChatStatus chatStatus;
        IQ mUCOwner = new MUCOwner();
        mUCOwner.a(Type.b);
        mUCOwner.g(this.f18237c);
        Destroy destroy = new Destroy();
        destroy.b(str);
        mUCOwner.a(destroy);
        try {
            m19552a(mUCOwner);
            chatStatus = ChatStatus.OK;
        } catch (Throwable e) {
            chatStatus = ChatStatus.m19416a(e);
        }
        synchronized (this.f18235a) {
            this.f18241g.clear();
            this.f18239e.clear();
            this.f18240f.clear();
            m19560p();
        }
        return chatStatus;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.smule.chat.ChatStatus m19558n() throws java.lang.Exception {
        /*
        r3 = this;
        r1 = r3.f18235a;
        monitor-enter(r1);
        r0 = r3.f18243i;	 Catch:{ all -> 0x0059 }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r3.f18246l;	 Catch:{ all -> 0x0059 }
        monitor-exit(r1);	 Catch:{ all -> 0x0059 }
    L_0x000a:
        return r0;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0059 }
        r0 = new org.jivesoftware.smackx.disco.packet.DiscoverInfo;
        r0.<init>();
        r1 = r3.f18237c;
        r0.g(r1);
        r1 = org.jivesoftware.smack.packet.IQ.Type.a;
        r0.a(r1);
        r1 = 0;
        r0 = r3.m19552a(r0);
        r0 = (org.jivesoftware.smackx.disco.packet.DiscoverInfo) r0;
        if (r0 == 0) goto L_0x005f;
    L_0x0024:
        r0 = r0.i();
        if (r0 == 0) goto L_0x005f;
    L_0x002a:
        r2 = r0.isEmpty();
        if (r2 != 0) goto L_0x005f;
    L_0x0030:
        r1 = 0;
        r0 = r0.get(r1);
        r0 = (org.jivesoftware.smackx.disco.packet.DiscoverInfo.Identity) r0;
        r0 = r0.b();
    L_0x003b:
        if (r0 == 0) goto L_0x0056;
    L_0x003d:
        r1 = r3.f18235a;
        monitor-enter(r1);
        r2 = r3.f18238d;	 Catch:{ all -> 0x005c }
        r3.f18238d = r0;	 Catch:{ all -> 0x005c }
        r0 = 1;
        r3.f18243i = r0;	 Catch:{ all -> 0x005c }
        r3.m19563s();	 Catch:{ all -> 0x005c }
        r0 = r3.f18238d;	 Catch:{ all -> 0x005c }
        r0 = r2.equals(r0);	 Catch:{ all -> 0x005c }
        if (r0 != 0) goto L_0x0055;
    L_0x0052:
        r3.m19561q();	 Catch:{ all -> 0x005c }
    L_0x0055:
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
    L_0x0056:
        r0 = com.smule.chat.ChatStatus.OK;
        goto L_0x000a;
    L_0x0059:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0059 }
        throw r0;
    L_0x005c:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x005c }
        throw r0;
    L_0x005f:
        r0 = r1;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.chat.GroupInfo.n():com.smule.chat.ChatStatus");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.smule.chat.ChatStatus m19559o() throws java.lang.Exception {
        /*
        r8 = this;
        r1 = r8.f18235a;
        monitor-enter(r1);
        r0 = r8.f18244j;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x000b;
    L_0x0007:
        r0 = r8.f18246l;	 Catch:{ all -> 0x0044 }
        monitor-exit(r1);	 Catch:{ all -> 0x0044 }
    L_0x000a:
        return r0;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0044 }
        r0 = org.jivesoftware.smackx.muc.MUCAffiliation.a;
        r0 = r8.m19551a(r0);
        r1 = org.jivesoftware.smackx.muc.MUCAffiliation.b;
        r1 = r8.m19551a(r1);
        r2 = new java.util.HashSet;
        r2.<init>();
        r3 = new java.util.HashSet;
        r3.<init>();
        r4 = r0.iterator();
    L_0x0026:
        r0 = r4.hasNext();
        if (r0 == 0) goto L_0x0047;
    L_0x002c:
        r0 = r4.next();
        r0 = (org.jivesoftware.smackx.muc.packet.MUCItem) r0;
        r5 = r8.f18236b;
        r0 = r0.f();
        r6 = r5.c(r0);
        r0 = java.lang.Long.valueOf(r6);
        r2.add(r0);
        goto L_0x0026;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0044 }
        throw r0;
    L_0x0047:
        r1 = r1.iterator();
    L_0x004b:
        r0 = r1.hasNext();
        if (r0 == 0) goto L_0x0070;
    L_0x0051:
        r0 = r1.next();
        r0 = (org.jivesoftware.smackx.muc.packet.MUCItem) r0;
        r4 = r8.f18236b;
        r0 = r0.f();
        r4 = r4.c(r0);
        r0 = java.lang.Long.valueOf(r4);
        r2.add(r0);
        r0 = java.lang.Long.valueOf(r4);
        r3.add(r0);
        goto L_0x004b;
    L_0x0070:
        r1 = r8.f18235a;
        monitor-enter(r1);
        r0 = 1;
        r8.f18244j = r0;	 Catch:{ all -> 0x009b }
        r8.f18239e = r2;	 Catch:{ all -> 0x009b }
        r8.f18240f = r3;	 Catch:{ all -> 0x009b }
        r8.m19563s();	 Catch:{ all -> 0x009b }
        r0 = r8.f18239e;	 Catch:{ all -> 0x009b }
        r2 = r8.f18241g;	 Catch:{ all -> 0x009b }
        r2 = r2.keySet();	 Catch:{ all -> 0x009b }
        r0 = r0.equals(r2);	 Catch:{ all -> 0x009b }
        if (r0 != 0) goto L_0x008e;
    L_0x008b:
        r8.m19588j();	 Catch:{ all -> 0x009b }
    L_0x008e:
        monitor-exit(r1);	 Catch:{ all -> 0x009b }
        r0 = r8.m19590l();
        r1 = com.smule.chat.GroupMemberStatus.NONE;
        if (r0 != r1) goto L_0x009e;
    L_0x0097:
        r0 = com.smule.chat.ChatStatus.NON_MEMBER;
        goto L_0x000a;
    L_0x009b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x009b }
        throw r0;
    L_0x009e:
        r0 = com.smule.chat.ChatStatus.OK;
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.chat.GroupInfo.o():com.smule.chat.ChatStatus");
    }

    void m19572a(Collection<AccountIcon> collection) {
        synchronized (this.f18235a) {
            Object obj = null;
            for (AccountIcon accountIcon : collection) {
                Object obj2;
                if (!this.f18239e.contains(Long.valueOf(accountIcon.accountId)) || this.f18241g.put(Long.valueOf(accountIcon.accountId), accountIcon) == accountIcon || accountIcon.accountId == this.f18236b.j()) {
                    obj2 = obj;
                } else {
                    obj2 = 1;
                }
                obj = obj2;
            }
            if (obj != null) {
                m19560p();
            }
        }
    }

    private <P extends IQ> P m19552a(P p) throws Exception {
        IQ iq = (IQ) this.f18236b.a(p).d();
        if (iq == null) {
            throw new SmackException("request timed out");
        } else if (iq.m() == null) {
            return iq;
        } else {
            throw new XMPPErrorException(iq.m());
        }
    }

    private void m19560p() {
        m19563s();
        final Map e = m19583e();
        final List r = m19562r();
        this.f18236b.b(new Runnable(this) {
            final /* synthetic */ GroupInfo f18227c;

            public void run() {
                for (WeakReference weakReference : r) {
                    Listener listener = (Listener) weakReference.get();
                    if (listener != null) {
                        listener.mo6357a(e);
                    }
                }
            }
        });
    }

    private void m19561q() {
        final String str = this.f18238d;
        final List r = m19562r();
        this.f18236b.b(new Runnable(this) {
            final /* synthetic */ GroupInfo f18230c;

            public void run() {
                for (WeakReference weakReference : r) {
                    Listener listener = (Listener) weakReference.get();
                    if (listener != null) {
                        listener.mo6355a(str);
                    }
                }
            }
        });
    }

    private List<WeakReference<Listener>> m19562r() {
        List arrayList;
        synchronized (this.f18235a) {
            arrayList = new ArrayList(this.f18242h);
        }
        return arrayList;
    }

    private List<MUCItem> m19551a(MUCAffiliation mUCAffiliation) throws Exception {
        IQ mUCAdmin = new MUCAdmin();
        mUCAdmin.a(Type.a);
        mUCAdmin.g(this.f18237c);
        mUCAdmin.a(new MUCItem(mUCAffiliation));
        return ((MUCAdmin) this.f18236b.a(mUCAdmin).d()).a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m19553a(final com.smule.chat.ChatStatus r5, final com.smule.chat.Completion<com.smule.chat.ChatStatus> r6) {
        /*
        r4 = this;
        r1 = r4.f18235a;
        monitor-enter(r1);
        r0 = r4.f18245k;	 Catch:{ all -> 0x001e }
        if (r0 != 0) goto L_0x0017;
    L_0x0007:
        r0 = new com.smule.chat.GroupInfo$4;	 Catch:{ all -> 0x001e }
        r0.<init>(r4, r6, r5);	 Catch:{ all -> 0x001e }
        r2 = com.smule.chat.AccountIconCache.m19106a();	 Catch:{ all -> 0x001e }
        r3 = r4.f18239e;	 Catch:{ all -> 0x001e }
        r2.m19111a(r3, r0);	 Catch:{ all -> 0x001e }
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
    L_0x0016:
        return;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
        if (r6 == 0) goto L_0x0016;
    L_0x001a:
        r6.mo6329a(r5);
        goto L_0x0016;
    L_0x001e:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001e }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.chat.GroupInfo.a(com.smule.chat.ChatStatus, com.smule.chat.Completion):void");
    }

    public GroupInfo() {
        this.f18235a = new Object();
        this.f18241g = new HashMap();
        this.f18242h = new ArrayList();
        this.f18246l = ChatStatus.OK;
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeBoolean(this.f18244j);
        smerializableOutputStream.writeBoolean(this.f18243i);
        smerializableOutputStream.writeBoolean(this.f18245k);
        smerializableOutputStream.m19757a(this.f18237c);
        smerializableOutputStream.m19757a(this.f18238d);
        smerializableOutputStream.writeInt(this.f18239e.size());
        for (Long longValue : this.f18239e) {
            smerializableOutputStream.writeLong(longValue.longValue());
        }
        smerializableOutputStream.writeInt(this.f18240f.size());
        for (Long longValue2 : this.f18240f) {
            smerializableOutputStream.writeLong(longValue2.longValue());
        }
        smerializableOutputStream.writeInt(this.f18241g.size());
        for (AccountIcon a : this.f18241g.values()) {
            SmerializableUtils.m19759a(smerializableOutputStream, a);
        }
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        int i = 0;
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        int i2;
        this.f18244j = smerializableInputStream.readBoolean();
        this.f18243i = smerializableInputStream.readBoolean();
        this.f18245k = smerializableInputStream.readBoolean();
        this.f18237c = smerializableInputStream.m19751b();
        this.f18238d = smerializableInputStream.m19751b();
        int readInt = smerializableInputStream.readInt();
        this.f18239e = new HashSet(readInt);
        for (i2 = 0; i2 < readInt; i2++) {
            this.f18239e.add(Long.valueOf(smerializableInputStream.readLong()));
        }
        readInt = smerializableInputStream.readInt();
        this.f18240f = new HashSet(readInt);
        for (i2 = 0; i2 < readInt; i2++) {
            this.f18240f.add(Long.valueOf(smerializableInputStream.readLong()));
        }
        i2 = smerializableInputStream.readInt();
        this.f18241g = new HashMap(i2);
        while (i < i2) {
            AccountIcon c = SmerializableUtils.m19763c(smerializableInputStream);
            this.f18241g.put(Long.valueOf(c.accountId), c);
            i++;
        }
        AccountIconCache.m19106a().m19112a(this.f18241g);
    }

    void m19569a(XMPPDelegate xMPPDelegate) {
        this.f18236b = xMPPDelegate;
    }

    public String mo6315O() {
        return "group-" + XmppStringUtils.a(this.f18237c) + ".data";
    }

    private void m19563s() {
        if (this.f18236b != null) {
            this.f18236b.a(this);
        }
    }
}
