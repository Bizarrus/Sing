package com.smule.chat;

import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManagerPersistence.LoadCallback;
import com.smule.chat.smerialization.Smerializable;
import java.util.ArrayList;
import java.util.Iterator;

class ChatManager$7 implements LoadCallback {
    final /* synthetic */ Completion f18075a;
    final /* synthetic */ ChatManager f18076b;

    ChatManager$7(ChatManager chatManager, Completion completion) {
        this.f18076b = chatManager;
        this.f18075a = completion;
    }

    public void mo6327a(ArrayList<Chat> arrayList, ArrayList<GroupInfo> arrayList2, boolean z, ChatStatus chatStatus) {
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Chat chat = (Chat) it.next();
            if (!ChatManager.c(this.f18076b).containsKey(chat.m19209c())) {
                arrayList3.add(chat);
            }
        }
        ChatManager.a(this.f18076b, arrayList3, arrayList2);
        ChatManager.d(this.f18076b);
        Iterator it2 = arrayList3.iterator();
        while (it2.hasNext()) {
            Smerializable smerializable = (Chat) it2.next();
            ChatManager.e(this.f18076b).m19367a(smerializable);
            if (smerializable.mo6335a() == Type.GROUP) {
                ChatManager.e(this.f18076b).m19367a((Smerializable) ChatManager.f(this.f18076b).get(smerializable.m19209c()));
            }
        }
        this.f18075a.mo6329a(chatStatus);
    }
}
