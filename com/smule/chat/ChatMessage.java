/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Message$Type
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.packet.id.StanzaIdUtil
 *  org.jivesoftware.smackx.delay.packet.DelayInformation
 */
package com.smule.chat;

import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.chat.UnknownChatMessage;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import com.smule.chat.smerialization.SmerializableUtils;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.id.StanzaIdUtil;
import org.jivesoftware.smackx.delay.packet.DelayInformation;

public abstract class ChatMessage
implements Smerializable {
    private long a;
    private Date b = new Date();
    private State c = State.a;
    private ChatStatus d = ChatStatus.a;
    private String e = StanzaIdUtil.a();

    /*
     * Enabled aggressive block sorting
     */
    static ChatMessage a(Message object, XMPPDelegate xMPPDelegate) {
        Object object2 = ChatMessage.b((Message)object);
        switch (.a[object2.ordinal()]) {
            default: {
                object2 = new TextChatMessage(object.e());
                break;
            }
            case 2: {
                object2 = new GroupInvitationChatMessage((Message)object);
                break;
            }
            case 3: {
                object2 = new GroupStatusChatMessage((Message)object);
                break;
            }
            case 4: {
                object2 = new PerformanceChatMessage((Message)object);
                break;
            }
            case 5: {
                object2 = new UnknownChatMessage();
            }
        }
        if (object.j() != null) {
            object2.e = object.j();
        }
        object2.a(xMPPDelegate.a((Message)object));
        object = DelayInformation.a((Stanza)object);
        if (object != null && (object = object.a()) != null) {
            object2.a((Date)object);
        }
        return object2;
    }

    static boolean a(Message message) {
        if ((message = DelayInformation.a((Stanza)message)) != null && "Offline Storage".equals(message.e())) {
            return true;
        }
        return false;
    }

    private static Type b(Message message) {
        if (message.j("jabber:x:conference") != null) {
            return Type.f;
        }
        if (message.c("performance", "urn:x-smule:xmpp") != null) {
            return Type.c;
        }
        if (message.c("join", "urn:x-smule:xmpp") != null) {
            return Type.g;
        }
        if (message.c("leave", "urn:x-smule:xmpp") != null) {
            return Type.g;
        }
        if (message.c("invite", "urn:x-smule:xmpp") != null) {
            return Type.g;
        }
        if (message.c("remove", "urn:x-smule:xmpp") != null) {
            return Type.g;
        }
        if (message.c("rename", "urn:x-smule:xmpp") != null) {
            return Type.g;
        }
        if (message.j("urn:x-smule:xmpp") != null && message.e().trim().length() == 0) {
            return Type.a;
        }
        return Type.b;
    }

    @Override
    public String O() {
        return null;
    }

    Chat<Void, Object> a(Chat chat, boolean bl, XMPPDelegate xMPPDelegate) {
        this.c = State.b;
        return null;
    }

    public abstract Type a();

    /*
     * Enabled aggressive block sorting
     */
    Message a(Chat.Type type, String string2) {
        Message message = new Message(string2);
        message.f(this.e);
        message.g(string2);
        type = type == Chat.Type.a ? Message.Type.b : Message.Type.c;
        message.a((Message.Type)type);
        return message;
    }

    void a(long l) {
        this.a = l;
    }

    void a(Chat chat, State state, ChatStatus chatStatus) {
        this.c = state;
        this.d = chatStatus;
        if (chat != null) {
            chat.d(this);
        }
    }

    void a(ChatStatus chatStatus) {
        this.d = chatStatus;
        this.c = State.d;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.a = smerializableInputStream.readLong();
        this.b = SmerializableUtils.a(smerializableInputStream);
        this.e = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeLong(this.a);
        SmerializableUtils.a(smerializableOutputStream, this.b);
        smerializableOutputStream.a(this.e);
    }

    void a(Date date) {
        this.b = date;
    }

    public long b() {
        return this.a;
    }

    public Date c() {
        return this.b;
    }

    public State d() {
        return this.c;
    }

    public ChatStatus e() {
        return this.d;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        block5 : {
            block4 : {
                if (this == object) break block4;
                if (object == null || this.getClass() != object.getClass()) {
                    return false;
                }
                object = (ChatMessage)object;
                if (this.a != object.a || !this.e.equals(object.e)) break block5;
            }
            return true;
        }
        return false;
    }

    public String f() {
        return this.e;
    }

    public abstract boolean g();

    void h() {
        this.d = ChatStatus.a;
        this.c = State.b;
    }

    public int hashCode() {
        return this.e.hashCode() ^ (int)this.b();
    }

    void i() {
        this.d = ChatStatus.a;
        this.c = State.c;
    }

    void j() {
        this.d = ChatStatus.a;
        this.c = State.a;
    }

    void k() {
    }

    void l() {
    }

    boolean m() {
        return false;
    }

    boolean n() {
        return true;
    }

    public String toString() {
        return "from:" + this.a + ", at:" + this.b;
    }

    public static enum State {
        a,
        b,
        c,
        d;
        

        private State() {
        }
    }

    public static enum Type {
        a,
        b,
        c,
        d,
        e,
        f,
        g;
        

        private Type() {
        }
    }

}

