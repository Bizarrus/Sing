package com.smule.chat;

import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;

class ChatManager$19 extends Operation {
    final /* synthetic */ ChatManager f18042a;

    ChatManager$19(ChatManager chatManager) {
        this.f18042a = chatManager;
    }

    public void m19331a(final OperationLoader operationLoader) {
        ChatManager.i(this.f18042a).m19127a(new Runnable(this) {
            final /* synthetic */ ChatManager$19 f18041b;

            class C36981 implements Runnable {
                final /* synthetic */ C36991 f18039a;

                C36981(C36991 c36991) {
                    this.f18039a = c36991;
                }

                public void run() {
                    operationLoader.c(this.f18039a.f18041b.g);
                }
            }

            public void run() {
                ChatManager.a(this.f18041b.f18042a, new C36981(this));
            }
        });
    }
}
