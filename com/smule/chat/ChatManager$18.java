package com.smule.chat;

import android.os.AsyncTask;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;

class ChatManager$18 extends Operation {
    final /* synthetic */ ChatManager f18038a;

    ChatManager$18(ChatManager chatManager) {
        this.f18038a = chatManager;
    }

    public void m19330a(final OperationLoader operationLoader) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable(this) {
            final /* synthetic */ ChatManager$18 f18037b;

            public void run() {
                ChatManager.h(this.f18037b.f18038a);
                operationLoader.c(this.f18037b.g);
            }
        });
    }
}
