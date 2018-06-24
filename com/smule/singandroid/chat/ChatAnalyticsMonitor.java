package com.smule.singandroid.chat;

import com.smule.android.logging.Log;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatMessage.Type;
import java.util.HashMap;

public class ChatAnalyticsMonitor {
    static ChatAnalyticsMonitor f7112a;
    static Type[] f7113e = new Type[]{Type.b, Type.f, Type.c, null, Type.g};
    HashMap<String, ChatMonitor> f7114b = new HashMap();
    ChatManagerListener f7115c = new 1(this);
    ChatListener f7116d = new 2(this);

    public static ChatAnalyticsMonitor m8827a() {
        if (f7112a == null) {
            f7112a = new ChatAnalyticsMonitor();
        }
        return f7112a;
    }

    public void m8828a(ChatManager chatManager) {
        chatManager.m8564a(this.f7115c);
        chatManager.m8562a(this.f7116d);
    }

    private ChatMonitor m8825a(Chat chat, boolean z) {
        String c = chat.c();
        ChatMonitor chatMonitor = (ChatMonitor) this.f7114b.get(c);
        if (chatMonitor == null && z) {
            chatMonitor = new ChatMonitor(this);
            if (this.f7114b.size() > 1) {
                Log.m7776e("odietest", "chatmonitors.size: " + this.f7114b.size());
            }
            this.f7114b.put(c, chatMonitor);
        }
        return chatMonitor;
    }

    public void m8829b() {
        for (ChatMonitor a : this.f7114b.values()) {
            a.a();
        }
        this.f7114b.clear();
    }
}
