/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Message$Type
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smackx.receipts.DeliveryReceipt
 */
package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessageList;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.FollowingBatcher;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;

public class PeerChat
extends Chat {
    private static final String l = PeerChat.class.getName();
    private boolean i;
    private String j;
    private String k;

    public PeerChat() {
    }

    PeerChat(XMPPDelegate xMPPDelegate, Chat.Options options) {
        super(xMPPDelegate, options);
        if (options.i != null) {
            this.i = true;
            this.a(options.i);
            return;
        }
        this.a(Chat.Bucket.b);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void T() {
        Message message;
        ChatMessage chatMessage;
        block4 : {
            if (this.d.g()) {
                message = this.f.f();
                while (message.hasNext()) {
                    chatMessage = message.next();
                    if (chatMessage.b() == this.d.j()) continue;
                    if (!chatMessage.f().equals(this.k)) break block4;
                }
            }
            return;
        }
        message = new Message(this.c(), Message.Type.b);
        message.a((ExtensionElement)new DeliveryReceipt(chatMessage.f()));
        try {
            this.d.a((Stanza)message);
            this.k = chatMessage.f();
            this.P();
            return;
        }
        catch (SmackException.NotConnectedException notConnectedException) {
            Log.e(l, "can't send receipt");
            return;
        }
    }

    private void a(String string2) {
        if (!string2.equals(this.j)) {
            long l = this.d.j();
            Iterator<ChatMessage> iterator = this.f.f();
            while (iterator.hasNext()) {
                ChatMessage chatMessage = iterator.next();
                if (!chatMessage.f().equals(string2) || chatMessage.b() != l) continue;
                this.j = string2;
                this.P();
                this.g(chatMessage);
                break;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(long l) {
        this.i = true;
        boolean bl = FollowManager.a().a(l);
        XMPPDelegate xMPPDelegate = this.d;
        Chat.Bucket bucket = bl ? Chat.Bucket.a : Chat.Bucket.b;
        xMPPDelegate.a(this, bucket);
        this.P();
    }

    private void c(final SimpleBarrier simpleBarrier) {
        if (!this.i) {
            simpleBarrier.d();
            final long l = this.f();
            FollowingBatcher.a().a(l, new Runnable(){

                @Override
                public void run() {
                    PeerChat.this.d.b(new Runnable(){

                        @Override
                        public void run() {
                            PeerChat.this.b(l);
                            simpleBarrier.a();
                        }
                    });
                }

            });
        }
    }

    private void d(final SimpleBarrier simpleBarrier) {
        if (this.c != null && this.c.e) {
            simpleBarrier.d();
            this.a(-100, 1, new Runnable(){

                @Override
                public void run() {
                    simpleBarrier.a();
                }
            });
        }
    }

    @Override
    void A() {
        this.b(this.f());
    }

    public AccountIcon R() {
        return (AccountIcon)this.e.get(this.d.c(this.c()));
    }

    public String S() {
        ChatManager.n();
        return this.j;
    }

    @Override
    public Chat.Type a() {
        return Chat.Type.a;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    ChatMessage a(Message object, boolean bl) {
        Object object2 = DeliveryReceipt.a((Message)object);
        if (object2 != null) {
            object = object2.a();
            if (object == null) return null;
            this.a((String)object);
            return null;
        }
        object = object2 = super.a((Message)object, bl);
        if (!this.g) return object;
        object = object2;
        if (object2 == null) return object;
        this.T();
        return object2;
    }

    @Override
    protected void a(SimpleBarrier simpleBarrier) {
        super.a(simpleBarrier);
        this.b(simpleBarrier);
        this.c(simpleBarrier);
        this.d(simpleBarrier);
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.i = smerializableInputStream.readBoolean();
        this.j = smerializableInputStream.b();
        this.k = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeBoolean(this.i);
        smerializableOutputStream.a(this.j);
        smerializableOutputStream.a(this.k);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(final SimpleBarrier simpleBarrier) {
        boolean bl = this.e.size() < 2;
        AccountIconCache accountIconCache = AccountIconCache.a();
        boolean bl2 = bl;
        if (this.c != null) {
            bl2 = bl;
            if (this.c.g != null) {
                accountIconCache.a(this.c.g);
                bl2 = true;
            }
        }
        if (bl2) {
            simpleBarrier.d();
            accountIconCache.a(this.g(), new UserManager(){

                @Override
                public void handleResponse(UserManager.AccountIconsResponse object) {
                    if (object.a()) {
                        PeerChat.this.h = new Date();
                        PeerChat.this.e.clear();
                        for (AccountIcon accountIcon : object.accountIcons) {
                            PeerChat.this.e.put(accountIcon.accountId, accountIcon);
                        }
                        PeerChat.this.P();
                    }
                    if (PeerChat.this.e.size() != 2) {
                        PeerChat.this.a(ChatStatus.b);
                    }
                    simpleBarrier.a();
                }
            });
        }
    }

    @Override
    void b(Completion<ChatStatus> completion) {
        super.b(completion);
        if (completion != null) {
            completion.a(ChatStatus.a);
        }
    }

    @Override
    public void c(Completion<ChatStatus> completion) {
        this.T();
        this.C();
        completion.a(this.e());
    }

    @Override
    public long f() {
        return this.d.c(this.c());
    }

    @Override
    public Set<Long> g() {
        HashSet<Long> hashSet = new HashSet<Long>();
        hashSet.add(this.d.c(this.c()));
        hashSet.add(this.d.c(this.d.i()));
        return hashSet;
    }

    @Override
    public boolean i() {
        if (super.i() && this.R() != null) {
            return true;
        }
        return false;
    }

}

