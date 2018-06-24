package com.smule.singandroid.chat;

import com.smule.android.logging.Analytics.FollowingStatus;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage.Type;
import com.smule.singandroid.chat.ChatAnalytics.ChatType;
import java.util.HashMap;

protected abstract class ChatAnalyticsMonitor$AnalyticsCounter {
    String f20763a;
    FollowingStatus f20764b;
    ChatType f20765c;
    final /* synthetic */ ChatAnalyticsMonitor f20766d;

    protected abstract void mo6742b();

    protected abstract void mo6743b(ChatAnalyticsMonitor$EventType chatAnalyticsMonitor$EventType, Type type);

    protected abstract void mo6744c();

    public ChatAnalyticsMonitor$AnalyticsCounter(ChatAnalyticsMonitor chatAnalyticsMonitor, Chat chat) {
        this.f20766d = chatAnalyticsMonitor;
        this.f20765c = ChatType.m22368a(chat);
        this.f20763a = chat.m19209c();
        if (chat.mo6335a() == Chat.Type.PEER) {
            this.f20764b = ChatAnalytics.m22375a(chat);
        }
    }

    protected void m22415a(ChatAnalyticsMonitor$EventType chatAnalyticsMonitor$EventType, Type type) {
        synchronized (this) {
            mo6743b(chatAnalyticsMonitor$EventType, type);
        }
    }

    protected void m22416a(HashMap<Type, Integer> hashMap, Type type) {
        Object valueOf;
        Integer num = (Integer) hashMap.get(type);
        if (num == null) {
            valueOf = Integer.valueOf(1);
        } else {
            valueOf = Integer.valueOf(num.intValue() + 1);
        }
        hashMap.put(type, valueOf);
    }

    protected void m22414a() {
        synchronized (this) {
            mo6742b();
            mo6744c();
        }
    }

    protected String m22413a(HashMap<Type, Integer> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        Type[] typeArr = ChatAnalyticsMonitor.e;
        int length = typeArr.length;
        int i = 0;
        Object obj = 1;
        while (i < length) {
            int i2;
            Integer num = (Integer) hashMap.get(typeArr[i]);
            if (obj == null) {
                stringBuilder.append(",");
            }
            if (num == null) {
                i2 = 0;
            } else {
                i2 = num.intValue();
            }
            stringBuilder.append(i2);
            i++;
            obj = null;
        }
        return stringBuilder.toString();
    }
}
