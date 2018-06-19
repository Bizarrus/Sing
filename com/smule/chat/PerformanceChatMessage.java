package com.smule.chat;

import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.chat.Chat.ChatPhasedTask;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatMessage.Type;
import com.smule.chat.extensions.PerformanceExtension;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import org.jivesoftware.smack.packet.Message;

public class PerformanceChatMessage extends ChatMessage {
    private String f18309a;
    private PerformanceV2 f18310b;

    class C37601 extends ChatPhasedTask<Void, Object> {
        final /* synthetic */ Chat f18307a;
        final /* synthetic */ PerformanceChatMessage f18308b;

        class C37591 implements PerformanceManager$PerformanceResponseCallback {
            final /* synthetic */ C37601 f18306a;

            C37591(C37601 c37601) {
                this.f18306a = c37601;
            }

            public void handleResponse(PerformanceResponse performanceResponse) {
                if (performanceResponse.a()) {
                    this.f18306a.f18308b.f18310b = performanceResponse.mPerformance;
                    this.f18306a.f18308b.m19380a(this.f18306a.f18307a, State.READY, ChatStatus.OK);
                    return;
                }
                this.f18306a.f18308b.m19380a(this.f18306a.f18307a, State.ERROR, ChatStatus.NETWORK_ERROR);
            }
        }

        C37601(PerformanceChatMessage performanceChatMessage, Chat chat, Void[] voidArr, Chat chat2) {
            this.f18308b = performanceChatMessage;
            this.f18307a = chat2;
            chat.getClass();
            super(chat, voidArr);
        }

        protected Object m19651a(Void... voidArr) {
            PerformanceBatcher.m19648a().m19649a(this.f18308b.f18309a, new C37591(this));
            return null;
        }
    }

    public PerformanceChatMessage(PerformanceV2 performanceV2) {
        this.f18309a = performanceV2.performanceKey;
        this.f18310b = performanceV2;
    }

    public Type mo6360a() {
        return Type.PERFORMANCE;
    }

    public PerformanceV2 m19661o() {
        return this.f18310b;
    }

    public boolean m19662p() {
        return m19387d() == State.READY && this.f18310b == null;
    }

    public boolean mo6362g() {
        return true;
    }

    PerformanceChatMessage(Message message) {
        this.f18309a = ((PerformanceExtension) message.j("urn:x-smule:xmpp")).m19712a();
    }

    PerformanceChatMessage(String str) {
        this.f18309a = str;
    }

    Message mo6361a(Chat.Type type, String str) {
        Message a = super.mo6361a(type, str);
        a.c(" ");
        a.a(new PerformanceExtension(this.f18309a));
        return a;
    }

    ChatPhasedTask<Void, Object> mo6359a(Chat chat, boolean z, XMPPDelegate xMPPDelegate) {
        chat.getClass();
        return new C37601(this, chat, new Void[0], chat);
    }

    boolean mo6364m() {
        return m19387d() == State.ERROR && m19388e().m19417a();
    }

    public void mo6317a(SmerializableOutputStream smerializableOutputStream) throws IOException {
        super.mo6317a(smerializableOutputStream);
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.m19757a(this.f18309a);
    }

    public void mo6316a(SmerializableInputStream smerializableInputStream) throws IOException {
        super.mo6316a(smerializableInputStream);
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        this.f18309a = smerializableInputStream.m19751b();
    }
}
