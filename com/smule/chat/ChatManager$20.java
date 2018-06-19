package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.chat.ChatManagerPersistence.LoadCallback;
import java.util.ArrayList;

class ChatManager$20 extends Operation {
    final /* synthetic */ ChatManager f18051a;

    ChatManager$20(ChatManager chatManager) {
        this.f18051a = chatManager;
    }

    public void m19335a(final OperationLoader operationLoader) {
        ChatManager.e(this.f18051a).m19366a(new LoadCallback(this) {
            final /* synthetic */ ChatManager$20 f18050b;

            public void mo6327a(final ArrayList<Chat> arrayList, ArrayList<GroupInfo> arrayList2, final boolean z, ChatStatus chatStatus) {
                ChatManager.a(this.f18050b.f18051a, arrayList, arrayList2);
                PriorityExecutor.f18318a.execute(new Runnable(this) {
                    final /* synthetic */ C37021 f18048c;

                    public void run() {
                        if (z) {
                            long currentTimeMillis = System.currentTimeMillis();
                            ChatManager.e(this.f18048c.f18050b.f18051a).m19368a(new ArrayList(arrayList));
                            ChatManager.e(this.f18048c.f18050b.f18051a).m19368a(new ArrayList(ChatManager.f(this.f18048c.f18050b.f18051a).values()));
                            Log.c(ChatManager.m(), "saved in " + (System.currentTimeMillis() - currentTimeMillis));
                        }
                        ChatManager.j(this.f18048c.f18050b.f18051a);
                        operationLoader.c(this.f18048c.f18050b.g);
                    }
                });
            }
        });
    }
}
