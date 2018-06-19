package com.smule.chat;

class ChatManager$3 implements Runnable {
    final /* synthetic */ boolean f18064a;
    final /* synthetic */ ChatManager f18065b;

    class C37031 implements Runnable {
        final /* synthetic */ ChatManager$3 f18063a;

        C37031(ChatManager$3 chatManager$3) {
            this.f18063a = chatManager$3;
        }

        public void run() {
            ChatManager.b(this.f18063a.f18065b).m19277b(this.f18063a.f18064a);
        }
    }

    ChatManager$3(ChatManager chatManager, boolean z) {
        this.f18065b = chatManager;
        this.f18064a = z;
    }

    public void run() {
        this.f18065b.b(new C37031(this));
    }
}
