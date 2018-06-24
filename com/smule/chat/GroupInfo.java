/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$XMPPErrorException
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.IQ$Type
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.packet.XMPPError
 *  org.jivesoftware.smackx.disco.packet.DiscoverInfo
 *  org.jivesoftware.smackx.disco.packet.DiscoverInfo$Identity
 *  org.jivesoftware.smackx.muc.MUCAffiliation
 *  org.jivesoftware.smackx.muc.packet.Destroy
 *  org.jivesoftware.smackx.muc.packet.MUCAdmin
 *  org.jivesoftware.smackx.muc.packet.MUCItem
 *  org.jivesoftware.smackx.muc.packet.MUCOwner
 *  org.jivesoftware.smackx.xdata.Form
 *  org.jivesoftware.smackx.xdata.packet.DataForm
 *  org.jxmpp.util.XmppStringUtils
 */
package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PriorityExecutor;
import com.smule.chat.XMPPDelegate;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smackx.disco.packet.DiscoverInfo;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.packet.Destroy;
import org.jivesoftware.smackx.muc.packet.MUCAdmin;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCOwner;
import org.jivesoftware.smackx.xdata.Form;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppStringUtils;

public class GroupInfo
implements Smerializable {
    private static String m = GroupInfo.class.getName();
    private final Object a = new Object();
    private XMPPDelegate b;
    private String c;
    private String d;
    private Set<Long> e;
    private Set<Long> f;
    private Map<Long, AccountIcon> g = new HashMap<Long, AccountIcon>();
    private List<WeakReference<Listener>> h = new ArrayList<WeakReference<Listener>>();
    private boolean i;
    private boolean j;
    private boolean k;
    private ChatStatus l = ChatStatus.a;

    public GroupInfo() {
    }

    GroupInfo(XMPPDelegate xMPPDelegate, String string2) {
        this();
        this.b = xMPPDelegate;
        this.c = string2;
        this.d = "";
        this.e = new HashSet<Long>();
        this.f = new HashSet<Long>();
        this.l = ChatStatus.a;
    }

    private List<MUCItem> a(MUCAffiliation mUCAffiliation) throws Exception {
        MUCAdmin mUCAdmin = new MUCAdmin();
        mUCAdmin.a(IQ.Type.a);
        mUCAdmin.g(this.c);
        mUCAdmin.a(new MUCItem(mUCAffiliation));
        return ((MUCAdmin)this.b.a((IQ)mUCAdmin).d()).a();
    }

    private <P extends IQ> P a(P object) throws Exception {
        if ((object = (IQ)this.b.a((IQ)object).d()) == null) {
            throw new SmackException("request timed out");
        }
        if (object.m() != null) {
            throw new XMPPException.XMPPErrorException(object.m());
        }
        return (P)object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void a(ChatStatus object, final Completion<ChatStatus> completion) {
        Object object2 = this.a;
        // MONITORENTER : object2
        if (!this.k) {
            object = new UserManager((ChatStatus)((Object)object)){
                final /* synthetic */ ChatStatus b;
                {
                    this.b = chatStatus;
                }

                /*
                 * Enabled aggressive block sorting
                 * Enabled unnecessary exception pruning
                 * Enabled aggressive exception aggregation
                 */
                @Override
                public void handleResponse(UserManager.AccountIconsResponse object) {
                    Object object2 = GroupInfo.this.a;
                    synchronized (object2) {
                        if (object.a()) {
                            GroupInfo.this.k = true;
                            GroupInfo.this.g.clear();
                            for (Object object3 : object.accountIcons) {
                                GroupInfo.this.g.put(object3.accountId, object3);
                            }
                            GroupInfo.this.p();
                            object = ChatStatus.a;
                        } else {
                            object = ChatStatus.b;
                        }
                        if (completion != null) {
                            Object object3;
                            object3 = completion;
                            if (this.b != ChatStatus.a) {
                                object = this.b;
                            }
                            object3.a(object);
                        }
                        return;
                    }
                }
            };
            AccountIconCache.a().a(this.e, object);
            // MONITOREXIT : object2
            return;
        }
        // MONITOREXIT : object2
        if (completion == null) return;
        completion.a((ChatStatus)((Object)object));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ChatStatus n() throws Exception {
        Object object = this.a;
        synchronized (object) {
            if (this.i) {
                return this.l;
            }
        }
        object = new DiscoverInfo();
        object.g(this.c);
        object.a(IQ.Type.a);
        object = (DiscoverInfo)this.a(object);
        if (object == null) return ChatStatus.a;
        if ((object = object.i()) == null) return ChatStatus.a;
        if (object.isEmpty()) return ChatStatus.a;
        object = ((DiscoverInfo.Identity)object.get(0)).b();
        if (object == null) return ChatStatus.a;
        Object object2 = this.a;
        synchronized (object2) {
            String string2 = this.d;
            this.d = object;
            this.i = true;
            this.s();
            if (string2.equals(this.d)) return ChatStatus.a;
            this.q();
            return ChatStatus.a;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private ChatStatus o() throws Exception {
        HashSet<Long> hashSet = this.a;
        synchronized (hashSet) {
            if (this.j) {
                return this.l;
            }
        }
        Object object = this.a((P)MUCAffiliation.a);
        Object object2 = this.a((P)MUCAffiliation.b);
        hashSet = new HashSet<Long>();
        HashSet<Long> hashSet2 = new HashSet<Long>();
        object = object.iterator();
        while (object.hasNext()) {
            MUCItem mUCItem = (MUCItem)object.next();
            hashSet.add(this.b.c(mUCItem.f()));
        }
        object2 = object2.iterator();
        while (object2.hasNext()) {
            object = (MUCItem)object2.next();
            long l = this.b.c(object.f());
            hashSet.add(l);
            hashSet2.add(l);
        }
        object2 = this.a;
        synchronized (object2) {
            this.j = true;
            this.e = hashSet;
            this.f = hashSet2;
            this.s();
            if (!this.e.equals(this.g.keySet())) {
                this.j();
            }
        }
        if (this.l() != GroupMemberStatus.a) return ChatStatus.a;
        return ChatStatus.c;
    }

    private void p() {
        this.s();
        final Map<Long, AccountIcon> map = this.e();
        final List<WeakReference<Listener>> list = this.r();
        this.b.b(new Runnable(){

            @Override
            public void run() {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    Listener listener = (Listener)((WeakReference)iterator.next()).get();
                    if (listener == null) continue;
                    listener.a(map);
                }
            }
        });
    }

    private void q() {
        final String string2 = this.d;
        final List<WeakReference<Listener>> list = this.r();
        this.b.b(new Runnable(){

            @Override
            public void run() {
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    Listener listener = (Listener)((WeakReference)iterator.next()).get();
                    if (listener == null) continue;
                    listener.a(string2);
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private List<WeakReference<Listener>> r() {
        Object object = this.a;
        synchronized (object) {
            return new ArrayList<WeakReference<Listener>>(this.h);
        }
    }

    private void s() {
        if (this.b != null) {
            this.b.a(this);
        }
    }

    @Override
    public String O() {
        return "group-" + XmppStringUtils.a((String)this.c) + ".data";
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    ChatStatus a(String object) {
        Object object2 = new MUCOwner();
        object2.g(this.c);
        object2.a(IQ.Type.a);
        try {
            object2 = Form.a((Stanza)this.a(object2));
            if (object2 == null) {
                Log.e(m, "no room info");
                return ChatStatus.b;
            }
        }
        catch (Exception exception) {
            return ChatStatus.a(exception);
        }
        object2 = object2.d();
        try {
            object2.a("muc#roomconfig_roomname", (String)object);
        }
        catch (Throwable throwable) {
            Log.e(m, "missing expected fields");
            return ChatStatus.a(throwable);
        }
        MUCOwner mUCOwner = new MUCOwner();
        mUCOwner.g(this.c);
        mUCOwner.a(IQ.Type.b);
        mUCOwner.a((ExtensionElement)object2.c());
        try {
            this.a((P)mUCOwner);
            object2 = this.a;
        }
        catch (Exception exception) {
            Log.e(m, "couldn't set name");
            return ChatStatus.a(exception);
        }
        synchronized (object2) {
            this.d = object;
        }
        this.q();
        this.s();
        return ChatStatus.a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    GroupMemberStatus a(long l) {
        Object object = this.a;
        synchronized (object) {
            if (this.f.contains(l)) {
                return GroupMemberStatus.b;
            }
            if (!this.e.contains(l)) return GroupMemberStatus.a;
            return GroupMemberStatus.c;
        }
    }

    void a(final Completion<ChatStatus> completion) {
        PriorityExecutor.a.execute(new Runnable(){

            @Override
            public void run() {
                GroupInfo.this.b(new Completion<ChatStatus>(){

                    @Override
                    public void a(final ChatStatus chatStatus) {
                        GroupInfo.this.b.b(new Runnable(){

                            @Override
                            public void run() {
                                completion.a(chatStatus);
                            }
                        });
                    }

                });
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(Listener listener) {
        Object object = this.a;
        synchronized (object) {
            this.h.add(new WeakReference<Listener>(listener));
            return;
        }
    }

    void a(XMPPDelegate xMPPDelegate) {
        this.b = xMPPDelegate;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        int n;
        int n2 = 0;
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.j = smerializableInputStream.readBoolean();
        this.i = smerializableInputStream.readBoolean();
        this.k = smerializableInputStream.readBoolean();
        this.c = smerializableInputStream.b();
        this.d = smerializableInputStream.b();
        int n3 = smerializableInputStream.readInt();
        this.e = new HashSet<Long>(n3);
        for (n = 0; n < n3; ++n) {
            this.e.add(smerializableInputStream.readLong());
        }
        n3 = smerializableInputStream.readInt();
        this.f = new HashSet<Long>(n3);
        for (n = 0; n < n3; ++n) {
            this.f.add(smerializableInputStream.readLong());
        }
        n3 = smerializableInputStream.readInt();
        this.g = new HashMap<Long, AccountIcon>(n3);
        for (n = n2; n < n3; ++n) {
            AccountIcon accountIcon = SmerializableUtils.c(smerializableInputStream);
            this.g.put(accountIcon.accountId, accountIcon);
        }
        AccountIconCache.a().a(this.g);
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeBoolean(this.j);
        smerializableOutputStream.writeBoolean(this.i);
        smerializableOutputStream.writeBoolean(this.k);
        smerializableOutputStream.a(this.c);
        smerializableOutputStream.a(this.d);
        smerializableOutputStream.writeInt(this.e.size());
        Iterator iterator = this.e.iterator();
        while (iterator.hasNext()) {
            smerializableOutputStream.writeLong(iterator.next());
        }
        smerializableOutputStream.writeInt(this.f.size());
        iterator = this.f.iterator();
        while (iterator.hasNext()) {
            smerializableOutputStream.writeLong(iterator.next());
        }
        smerializableOutputStream.writeInt(this.g.size());
        iterator = this.g.values().iterator();
        while (iterator.hasNext()) {
            SmerializableUtils.a(smerializableOutputStream, (AccountIcon)iterator.next());
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void a(Collection<AccountIcon> object) {
        Object object2 = this.a;
        synchronized (object2) {
            boolean bl = false;
            object = object.iterator();
            do {
                if (!object.hasNext()) {
                    this.p();
                    return;
                }
                AccountIcon accountIcon = (AccountIcon)object.next();
                if (!this.e.contains(accountIcon.accountId) || this.g.put(accountIcon.accountId, accountIcon) == accountIcon || accountIcon.accountId == this.b.j()) continue;
                bl = true;
            } while (true);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    void a(Collection<AccountIcon> object, MUCAffiliation mUCAffiliation, Completion<ChatStatus> completion) {
        if (object != null && !object.isEmpty()) {
            if (mUCAffiliation != MUCAffiliation.e) {
                AccountIconCache.a().a((Collection<AccountIcon>)((Object)object));
            }
            MUCAdmin mUCAdmin = new MUCAdmin();
            mUCAdmin.a(IQ.Type.b);
            mUCAdmin.g(this.c);
            object = object.iterator();
            while (object.hasNext()) {
                AccountIcon accountIcon = (AccountIcon)object.next();
                if (accountIcon.jid.equals(this.b.i())) continue;
                mUCAdmin.a(new MUCItem(mUCAffiliation, accountIcon.jid));
            }
            this.a((P)mUCAdmin);
        }
        this.i();
        this.j();
        this.b(completion);
        return;
        catch (Exception exception) {
            completion.a(ChatStatus.a(exception));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    boolean a() {
        Object object = this.a;
        synchronized (object) {
            if (this.h.size() != 0) return false;
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    ChatStatus b(String object) {
        Object object2 = new MUCOwner();
        object2.a(IQ.Type.b);
        object2.g(this.c);
        Destroy destroy = new Destroy();
        destroy.b((String)object);
        object2.a(destroy);
        try {
            this.a(object2);
            object = ChatStatus.a;
        }
        catch (Exception exception) {
            object = ChatStatus.a(exception);
        }
        object2 = this.a;
        synchronized (object2) {
            this.g.clear();
            this.e.clear();
            this.f.clear();
            this.p();
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void b(long l) {
        Object object = this.a;
        synchronized (object) {
            if (this.f.remove(l)) {
                this.p();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void b(Completion<ChatStatus> completion) {
        Object object;
        Object object2;
        block15 : {
            block14 : {
                try {
                    object = this.n();
                }
                catch (Exception exception) {
                    Log.e(m, "couldn't refresh room info: " + exception.toString());
                    object = object2 = ChatStatus.a(exception);
                    if (object2.a()) break block14;
                    object = this.a;
                    synchronized (object) {
                        this.i = true;
                    }
                    this.s();
                    object = object2;
                }
            }
            try {
                object2 = this.o();
            }
            catch (Exception exception) {
                Log.e(m, "couldn't refresh members: " + exception.toString());
                ChatStatus chatStatus = ChatStatus.a(exception);
                object2 = chatStatus;
                if (chatStatus.a()) break block15;
                object2 = this.a;
                synchronized (object2) {
                    this.j = true;
                }
                this.s();
                object2 = chatStatus;
            }
        }
        if (object != ChatStatus.a) {
            object2 = object;
        }
        object = this.a;
        synchronized (object) {
            this.l = object2;
        }
        this.a((ChatStatus)((Object)object2), completion);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void b(Listener listener) {
        Object object = this.a;
        synchronized (object) {
            int n = 0;
            while (n < this.h.size()) {
                if (this.h.get(n).get() == listener) {
                    this.h.remove(n);
                    return;
                }
                ++n;
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    boolean b() {
        Object object = this.a;
        synchronized (object) {
            if (this.e.size() <= this.f.size()) return false;
            return true;
        }
    }

    String c() {
        return this.c;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void c(long l) {
        Object object = this.a;
        synchronized (object) {
            this.f.remove(l);
            this.g.remove(l);
            if (this.e.remove(l)) {
                this.p();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    String d() {
        Object object = this.a;
        synchronized (object) {
            return this.d;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    Map<Long, AccountIcon> e() {
        Object object = this.a;
        synchronized (object) {
            return new HashMap<Long, AccountIcon>(this.g);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    Set<Long> f() {
        Object object = this.a;
        synchronized (object) {
            HashSet<Long> hashSet = new HashSet<Long>(this.e);
            hashSet.removeAll(this.f);
            return hashSet;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void g() {
        Object object = this.a;
        synchronized (object) {
            this.h();
            this.i();
            this.j();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void h() {
        Object object = this.a;
        synchronized (object) {
            this.i = false;
            this.s();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void i() {
        Object object = this.a;
        synchronized (object) {
            this.j = false;
            this.s();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    void j() {
        Object object = this.a;
        synchronized (object) {
            this.k = false;
            this.s();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    boolean k() {
        Object object = this.a;
        synchronized (object) {
            if (!this.i) return false;
            if (!this.j) return false;
            if (!this.k) return false;
            return true;
        }
    }

    GroupMemberStatus l() {
        return this.a(this.b.j());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    ChatStatus m() {
        boolean bl = true;
        long l = this.b.j();
        Object object = this.a;
        // MONITORENTER : object
        if (!this.e.contains(l) || this.f.contains(l) || this.e.size() - this.f.size() != 1) {
            bl = false;
        }
        // MONITOREXIT : object
        if (bl) {
            return this.b("no owners left");
        }
        object = new MUCAdmin();
        object.a(IQ.Type.b);
        object.g(this.c);
        object.a(new MUCItem(MUCAffiliation.e, this.b.i()));
        try {
            this.a(object);
            object = ChatStatus.a;
        }
        catch (Exception exception) {
            object = ChatStatus.a(exception);
        }
        Object object2 = this.a;
        // MONITORENTER : object2
        this.g.remove(l);
        this.e.remove(l);
        this.f.remove(l);
        this.p();
        // MONITOREXIT : object2
        return object;
    }

    static interface Listener {
        public void a(String var1);

        public void a(Map<Long, AccountIcon> var1);
    }

}

