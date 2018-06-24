/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 */
package com.smule.chat;

import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.PerformanceBatcher;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.extensions.PerformanceExtension;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;

public class PerformanceChatMessage
extends ChatMessage {
    private String a;
    private PerformanceV2 b;

    public PerformanceChatMessage() {
    }

    public PerformanceChatMessage(PerformanceV2 performanceV2) {
        this.a = performanceV2.performanceKey;
        this.b = performanceV2;
    }

    PerformanceChatMessage(String string2) {
        this.a = string2;
    }

    PerformanceChatMessage(Message message) {
        this.a = ((PerformanceExtension)message.j("urn:x-smule:xmpp")).a();
    }

    @Override
    Chat<Void, Object> a(final Chat chat, boolean bl, XMPPDelegate xMPPDelegate) {
        chat.getClass();
        return new Chat<Void, Object>(chat, new Void[0]){
            {
                chat3.getClass();
                super(arrvoid);
            }

            protected /* varargs */ Object a(Void ... arrvoid) {
                PerformanceBatcher.a().a(PerformanceChatMessage.this.a, new PerformanceManager(){

                    @Override
                    public void handleResponse(PerformanceManager.PerformanceResponse performanceResponse) {
                        if (performanceResponse.a()) {
                            PerformanceChatMessage.this.b = performanceResponse.mPerformance;
                            PerformanceChatMessage.this.a(chat, ChatMessage.State.b, ChatStatus.a);
                            return;
                        }
                        PerformanceChatMessage.this.a(chat, ChatMessage.State.d, ChatStatus.b);
                    }
                });
                return null;
            }

        };
    }

    @Override
    public ChatMessage.Type a() {
        return ChatMessage.Type.c;
    }

    @Override
    Message a(Chat.Type type, String string2) {
        type = super.a(type, string2);
        type.c(" ");
        type.a((ExtensionElement)new PerformanceExtension(this.a));
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
    public boolean g() {
        return true;
    }

    @Override
    boolean m() {
        if (this.d() == ChatMessage.State.d && this.e().a()) {
            return true;
        }
        return false;
    }

    public PerformanceV2 o() {
        return this.b;
    }

    public boolean p() {
        if (this.d() == ChatMessage.State.b && this.b == null) {
            return true;
        }
        return false;
    }

}

