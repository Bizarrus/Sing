package com.smule.chat;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat.Bucket;

public class FakePeerChat extends PeerChat {
    private ChatConfiguration f18164i;

    class C37191 implements Runnable {
        final /* synthetic */ FakePeerChat f18157a;

        C37191(FakePeerChat fakePeerChat) {
            this.f18157a = fakePeerChat;
        }

        public void run() {
            this.f18157a.m19441T();
        }
    }

    FakePeerChat(XMPPDelegate xMPPDelegate, Options options, ChatConfiguration chatConfiguration) {
        super(xMPPDelegate, options);
        this.f18164i = chatConfiguration;
        SharedPreferences b = chatConfiguration.b();
        this.a = b.getBoolean(m19452c(mo6341f()), true);
        this.b = b.getBoolean(m19454d(mo6341f()), true);
    }

    public Bucket mo6347b() {
        return Bucket.INBOX;
    }

    public boolean mo6350v() {
        return true;
    }

    public boolean mo6349t() {
        return false;
    }

    public void mo6346a(ChatMessage chatMessage) {
        super.mo6346a(chatMessage);
        this.d.a(500, new C37191(this));
    }

    public void mo6351w() {
    }

    void mo6339b(Completion<ChatStatus> completion) {
        m19451b(this.f18164i.b(), mo6341f());
        super.mo6339b((Completion) completion);
    }

    void mo6332A() {
    }

    protected void mo6348f(ChatMessage chatMessage) {
        chatMessage.m19391h();
        m19219g(chatMessage);
    }

    protected void mo6345a(int i, int i2, Runnable runnable) {
        runnable.run();
    }

    protected void mo6337a(SimpleBarrier simpleBarrier) {
        mo6338b(simpleBarrier);
        m19453c(simpleBarrier);
    }

    private void m19453c(final SimpleBarrier simpleBarrier) {
        simpleBarrier.m19037d();
        this.d.b(new Runnable(this) {
            final /* synthetic */ FakePeerChat f18159b;

            public void run() {
                this.f18159b.m19172D();
                this.f18159b.m19447a(this.f18159b.f18164i.d());
                String f = this.f18159b.f18164i.f();
                if (f != null) {
                    this.f18159b.m19446a(f);
                }
                simpleBarrier.m19034a();
            }
        });
    }

    protected void mo6344I() {
        m19442U();
        super.mo6344I();
    }

    void mo6352x() {
        super.mo6352x();
        m19442U();
    }

    public void mo6340c(Completion<ChatStatus> completion) {
        super.mo6340c((Completion) completion);
        m19442U();
    }

    private void m19447a(String[] strArr) {
        for (String textChatMessage : strArr) {
            m19455h(new TextChatMessage(textChatMessage));
        }
    }

    private void m19446a(String str) {
        m19455h(new PerformanceChatMessage(str));
    }

    private void m19441T() {
        m19447a(this.f18164i.e());
    }

    private void m19455h(ChatMessage chatMessage) {
        chatMessage.m19379a(mo6341f());
        m19215e(chatMessage);
        boolean z = (m19230r() || m19225m()) ? false : true;
        m19195a(chatMessage, z);
        m19207b(chatMessage, z);
    }

    static boolean m19448a(SharedPreferences sharedPreferences, long j) {
        return sharedPreferences.getBoolean(m19450b(j), false);
    }

    private static void m19451b(SharedPreferences sharedPreferences, long j) {
        Editor edit = sharedPreferences.edit();
        edit.putBoolean(m19450b(j), true);
        edit.apply();
    }

    private void m19442U() {
        Editor edit = this.f18164i.b().edit();
        edit.putBoolean(m19452c(mo6341f()), m19225m());
        edit.putBoolean(m19454d(mo6341f()), m19226n());
        edit.apply();
    }

    private static String m19450b(long j) {
        return "chat.deleted." + j;
    }

    private static String m19452c(long j) {
        return "chat.read." + j;
    }

    private static String m19454d(long j) {
        return "chat.read-unlocked." + j;
    }
}
