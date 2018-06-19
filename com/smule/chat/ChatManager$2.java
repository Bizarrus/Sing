package com.smule.chat;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

class ChatManager$2 implements Observer {
    final /* synthetic */ ChatManager f18062a;

    ChatManager$2(ChatManager chatManager) {
        this.f18062a = chatManager;
    }

    public void update(Observable observable, Object obj) {
        if (obj instanceof Map) {
            Object obj2 = ((Map) obj).get("FOLLOW_STATE_ACCOUNT");
            if (obj2 instanceof Long) {
                final Long l = (Long) obj2;
                if (l.longValue() != 0) {
                    ChatManager.a(this.f18062a).post(new Runnable(this) {
                        final /* synthetic */ ChatManager$2 f18045b;

                        public void run() {
                            ChatManager.a(this.f18045b.f18062a, l.longValue());
                        }
                    });
                }
            }
        }
    }
}
