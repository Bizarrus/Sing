package com.smule.chat;

import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat.ChatPhasedTask;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatMessage.Type;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Map;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.packet.GroupChatInvitation;

public class GroupInvitationChatMessage extends ChatMessage implements Listener {
    private String f18256a;
    private String f18257b;
    private String f18258c = "";
    private Map<Long, AccountIcon> f18259d;
    private XMPPDelegate f18260e;

    class C37491 extends ChatPhasedTask<Void, Object> {
        final /* synthetic */ XMPPDelegate f18251a;
        final /* synthetic */ boolean f18252b;
        final /* synthetic */ Chat f18253c;
        final /* synthetic */ GroupInvitationChatMessage f18254e;

        C37491(GroupInvitationChatMessage groupInvitationChatMessage, Chat chat, Void[] voidArr, XMPPDelegate xMPPDelegate, boolean z, Chat chat2) {
            this.f18254e = groupInvitationChatMessage;
            this.f18251a = xMPPDelegate;
            this.f18252b = z;
            this.f18253c = chat2;
            chat.getClass();
            super(chat, voidArr);
        }

        protected Object m19595a(Void... voidArr) {
            final GroupInfo d = this.f18251a.d(this.f18254e.f18256a);
            if (!this.f18252b) {
                d.m19585g();
            }
            d.m19577b(new Completion<ChatStatus>(this) {
                final /* synthetic */ C37491 f18250b;

                public void m19592a(final ChatStatus chatStatus) {
                    this.f18250b.f18254e.f18260e.b(new Runnable(this) {
                        final /* synthetic */ C37481 f18248b;

                        public void run() {
                            if (chatStatus == ChatStatus.OK) {
                                this.f18248b.f18250b.f18254e.f18258c = d.m19582d();
                                this.f18248b.f18250b.f18254e.f18259d = d.m19583e();
                                this.f18248b.f18250b.f18254e.m19380a(this.f18248b.f18250b.f18253c, State.READY, ChatStatus.OK);
                                return;
                            }
                            this.f18248b.f18250b.f18254e.m19380a(this.f18248b.f18250b.f18253c, State.ERROR, chatStatus);
                        }
                    });
                }
            });
            return null;
        }
    }

    class C37502 implements ChatManager$ChatCallback {
        final /* synthetic */ GroupInvitationChatMessage f18255a;

        C37502(GroupInvitationChatMessage groupInvitationChatMessage) {
            this.f18255a = groupInvitationChatMessage;
        }

        public void mo6326a(Chat chat, ChatStatus chatStatus) {
            if (chat != null) {
                chat.m19213d(this.f18255a);
            }
        }
    }

    public GroupInvitationChatMessage(GroupChat groupChat, XMPPDelegate xMPPDelegate) {
        this.f18256a = groupChat.m19209c();
        this.f18258c = groupChat.mo6334S();
        this.f18260e = xMPPDelegate;
    }

    public Type mo6360a() {
        return Type.GROUP_INVITATION;
    }

    public String m19613o() {
        return this.f18256a;
    }

    public String m19614p() {
        return this.f18258c;
    }

    public Map<Long, AccountIcon> m19615q() {
        return this.f18259d;
    }

    public GroupMemberStatus m19609b(long j) {
        return this.f18260e != null ? this.f18260e.d(this.f18256a).m19566a(j) : GroupMemberStatus.NONE;
    }

    public boolean mo6362g() {
        return true;
    }

    public String toString() {
        return super.toString() + " group invite:{" + this.f18258c + "}";
    }

    GroupInvitationChatMessage(Message message) {
        this.f18256a = GroupChatInvitation.a(message).a();
    }

    void mo6363l() {
        super.mo6363l();
        if (this.f18260e != null) {
            this.f18260e.d(this.f18256a).m19578b((Listener) this);
        }
    }

    ChatPhasedTask<Void, Object> mo6359a(Chat chat, boolean z, XMPPDelegate xMPPDelegate) {
        if (this.f18260e == null) {
            this.f18260e = xMPPDelegate;
            this.f18257b = chat.m19209c();
            xMPPDelegate.d(this.f18256a).m19568a((Listener) this);
        }
        chat.getClass();
        return new C37491(this, chat, new Void[0], xMPPDelegate, z, chat);
    }

    boolean mo6364m() {
        return m19387d() == State.ERROR && m19388e().m19417a();
    }

    private void m19601r() {
        if ((this.f18260e != null && m19387d() == State.READY) || m19387d() == State.ERROR) {
            Options options = new Options();
            options.f17953a = Chat.Type.PEER;
            options.f17954b = this.f18257b;
            options.f17955c = false;
            this.f18260e.a(options, new C37502(this));
        }
    }

    Message mo6361a(Chat.Type type, String str) {
        Message a = super.mo6361a(type, str);
        a.a(new GroupChatInvitation(this.f18256a));
        a.c(" ");
        return a;
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18256a = smerializableInputStream.m19751b();
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.m19757a(this.f18256a);
    }

    public void mo6357a(Map<Long, AccountIcon> map) {
        this.f18259d = map;
        m19601r();
    }

    public void mo6355a(String str) {
        this.f18258c = str;
        m19601r();
    }
}
