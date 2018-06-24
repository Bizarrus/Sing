/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smackx.muc.packet.GroupChatInvitation
 */
package com.smule.chat;

import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInfo;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Map;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.muc.packet.GroupChatInvitation;

public class GroupInvitationChatMessage
extends ChatMessage
implements GroupInfo.Listener {
    private String a;
    private String b;
    private String c = "";
    private Map<Long, AccountIcon> d;
    private XMPPDelegate e;

    public GroupInvitationChatMessage() {
    }

    public GroupInvitationChatMessage(GroupChat groupChat, XMPPDelegate xMPPDelegate) {
        this.a = groupChat.c();
        this.c = groupChat.S();
        this.e = xMPPDelegate;
    }

    GroupInvitationChatMessage(Message message) {
        this.a = GroupChatInvitation.a((Stanza)message).a();
    }

    private void r() {
        if (this.e != null && this.d() == ChatMessage.State.b || this.d() == ChatMessage.State.d) {
            Chat.Options options = new Chat.Options();
            options.a = Chat.Type.a;
            options.b = this.b;
            options.c = false;
            this.e.a(options, new ChatManager(){

                @Override
                public void a(Chat chat, ChatStatus chatStatus) {
                    if (chat != null) {
                        chat.d(GroupInvitationChatMessage.this);
                    }
                }
            });
        }
    }

    @Override
    Chat<Void, Object> a(final Chat chat, final boolean bl, final XMPPDelegate xMPPDelegate) {
        if (this.e == null) {
            this.e = xMPPDelegate;
            this.b = chat.c();
            xMPPDelegate.d(this.a).a(this);
        }
        chat.getClass();
        return new Chat<Void, Object>(chat, new Void[0]){
            {
                chat3.getClass();
                super(arrvoid);
            }

            protected /* varargs */ Object a(Void ... object) {
                object = xMPPDelegate.d(GroupInvitationChatMessage.this.a);
                if (!bl) {
                    object.g();
                }
                object.b(new Completion<ChatStatus>((GroupInfo)object){
                    final /* synthetic */ GroupInfo a;
                    {
                        this.a = groupInfo;
                    }

                    @Override
                    public void a(final ChatStatus chatStatus) {
                        GroupInvitationChatMessage.this.e.b(new Runnable(){

                            @Override
                            public void run() {
                                if (chatStatus == ChatStatus.a) {
                                    GroupInvitationChatMessage.this.c = 1.this.a.d();
                                    GroupInvitationChatMessage.this.d = 1.this.a.e();
                                    GroupInvitationChatMessage.this.a(chat, ChatMessage.State.b, ChatStatus.a);
                                    return;
                                }
                                GroupInvitationChatMessage.this.a(chat, ChatMessage.State.d, chatStatus);
                            }
                        });
                    }

                });
                return null;
            }

        };
    }

    @Override
    public ChatMessage.Type a() {
        return ChatMessage.Type.f;
    }

    @Override
    Message a(Chat.Type type, String string2) {
        type = super.a(type, string2);
        type.a((ExtensionElement)new GroupChatInvitation(this.a));
        type.c(" ");
        return type;
    }

    @Override
    public void a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.a = smerializableInputStream.b();
    }

    @Override
    public void a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.a(this.a);
    }

    @Override
    public void a(String string2) {
        this.c = string2;
        this.r();
    }

    @Override
    public void a(Map<Long, AccountIcon> map) {
        this.d = map;
        this.r();
    }

    public GroupMemberStatus b(long l) {
        if (this.e != null) {
            return this.e.d(this.a).a(l);
        }
        return GroupMemberStatus.a;
    }

    @Override
    public boolean g() {
        return true;
    }

    @Override
    void l() {
        super.l();
        if (this.e != null) {
            this.e.d(this.a).b(this);
        }
    }

    @Override
    boolean m() {
        if (this.d() == ChatMessage.State.d && this.e().a()) {
            return true;
        }
        return false;
    }

    public String o() {
        return this.a;
    }

    public String p() {
        return this.c;
    }

    public Map<Long, AccountIcon> q() {
        return this.d;
    }

    @Override
    public String toString() {
        return super.toString() + " group invite:{" + this.c + "}";
    }

}

