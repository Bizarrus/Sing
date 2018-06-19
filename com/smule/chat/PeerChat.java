package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;

public class PeerChat extends Chat {
    private static final String f18160l = PeerChat.class.getName();
    private boolean f18161i;
    private String f18162j;
    private String f18163k;

    public Type mo6335a() {
        return Type.PEER;
    }

    public Set<Long> mo6342g() {
        Set<Long> hashSet = new HashSet();
        hashSet.add(Long.valueOf(this.d.c(m19209c())));
        hashSet.add(Long.valueOf(this.d.c(this.d.i())));
        return hashSet;
    }

    public long mo6341f() {
        return this.d.c(m19209c());
    }

    public AccountIcon mo6333R() {
        return (AccountIcon) this.e.get(Long.valueOf(this.d.c(m19209c())));
    }

    public String mo6334S() {
        ChatManager.l();
        return this.f18162j;
    }

    public boolean mo6343i() {
        return super.mo6343i() && mo6333R() != null;
    }

    PeerChat(XMPPDelegate xMPPDelegate, Options options) {
        super(xMPPDelegate, options);
        if (options.f17961i != null) {
            this.f18161i = true;
            m19191a(options.f17961i);
            return;
        }
        m19191a(Bucket.OTHER);
    }

    public void mo6340c(Completion<ChatStatus> completion) {
        m19421T();
        m19171C();
        completion.mo6329a(m19214e());
    }

    void mo6339b(Completion<ChatStatus> completion) {
        super.mo6339b((Completion) completion);
        if (completion != null) {
            completion.mo6329a(ChatStatus.OK);
        }
    }

    void mo6332A() {
        m19424b(mo6341f());
    }

    protected void mo6337a(SimpleBarrier simpleBarrier) {
        super.mo6337a(simpleBarrier);
        mo6338b(simpleBarrier);
        m19425c(simpleBarrier);
        m19426d(simpleBarrier);
    }

    protected void mo6338b(final SimpleBarrier simpleBarrier) {
        Object obj = this.e.size() < 2 ? 1 : null;
        AccountIconCache a = AccountIconCache.m19106a();
        if (!(this.c == null || this.c.f17959g == null)) {
            a.m19110a(this.c.f17959g);
            obj = 1;
        }
        if (obj != null) {
            simpleBarrier.m19037d();
            a.m19111a(mo6342g(), new UserManager$AccountIconsResponseCallback(this) {
                final /* synthetic */ PeerChat f18294b;

                public void handleResponse(AccountIconsResponse accountIconsResponse) {
                    if (accountIconsResponse.a()) {
                        this.f18294b.h = new Date();
                        this.f18294b.e.clear();
                        for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                            this.f18294b.e.put(Long.valueOf(accountIcon.accountId), accountIcon);
                        }
                        this.f18294b.m19184P();
                    }
                    if (this.f18294b.e.size() != 2) {
                        this.f18294b.m19196a(ChatStatus.NETWORK_ERROR);
                    }
                    simpleBarrier.m19034a();
                }
            });
        }
    }

    private void m19425c(final SimpleBarrier simpleBarrier) {
        if (!this.f18161i) {
            simpleBarrier.m19037d();
            final long f = mo6341f();
            FollowingBatcher.m19474a().m19475a(f, new Runnable(this) {
                final /* synthetic */ PeerChat f18298c;

                class C37551 implements Runnable {
                    final /* synthetic */ C37562 f18295a;

                    C37551(C37562 c37562) {
                        this.f18295a = c37562;
                    }

                    public void run() {
                        this.f18295a.f18298c.m19424b(f);
                        simpleBarrier.m19034a();
                    }
                }

                public void run() {
                    this.f18298c.d.b(new C37551(this));
                }
            });
        }
    }

    private void m19426d(final SimpleBarrier simpleBarrier) {
        if (this.c != null && this.c.f17957e) {
            simpleBarrier.m19037d();
            mo6345a(-100, 1, new Runnable(this) {
                final /* synthetic */ PeerChat f18300b;

                public void run() {
                    simpleBarrier.m19034a();
                }
            });
        }
    }

    private void m19424b(long j) {
        this.f18161i = true;
        this.d.a(this, FollowManager.m18204a().m18222a(j) ? Bucket.INBOX : Bucket.OTHER);
        m19184P();
    }

    ChatMessage mo6336a(Message message, boolean z) {
        DeliveryReceipt a = DeliveryReceipt.a(message);
        if (a != null) {
            String a2 = a.a();
            if (a2 != null) {
                m19423a(a2);
            }
            return null;
        }
        ChatMessage a3 = super.mo6336a(message, z);
        if (!this.g || a3 == null) {
            return a3;
        }
        m19421T();
        return a3;
    }

    private void m19421T() {
        if (this.d.g()) {
            Iterator f = this.f.m19408f();
            while (f.hasNext()) {
                ChatMessage chatMessage = (ChatMessage) f.next();
                if (chatMessage.m19385b() != this.d.j()) {
                    if (!chatMessage.m19389f().equals(this.f18163k)) {
                        Stanza message = new Message(m19209c(), Message.Type.b);
                        message.a(new DeliveryReceipt(chatMessage.m19389f()));
                        try {
                            this.d.a(message);
                            this.f18163k = chatMessage.m19389f();
                            m19184P();
                            return;
                        } catch (NotConnectedException e) {
                            Log.e(f18160l, "can't send receipt");
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    private void m19423a(String str) {
        if (!str.equals(this.f18162j)) {
            long j = this.d.j();
            Iterator f = this.f.m19408f();
            while (f.hasNext()) {
                ChatMessage chatMessage = (ChatMessage) f.next();
                if (chatMessage.m19389f().equals(str) && chatMessage.m19385b() == j) {
                    this.f18162j = str;
                    m19184P();
                    m19219g(chatMessage);
                    return;
                }
            }
        }
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeBoolean(this.f18161i);
        smerializableOutputStream.m19757a(this.f18162j);
        smerializableOutputStream.m19757a(this.f18163k);
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18161i = smerializableInputStream.readBoolean();
        this.f18162j = smerializableInputStream.m19751b();
        this.f18163k = smerializableInputStream.m19751b();
    }
}
