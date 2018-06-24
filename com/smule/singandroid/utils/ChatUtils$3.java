package com.smule.singandroid.utils;

import android.content.Context;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.singandroid.C1947R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class ChatUtils$3 implements UserManager$AccountIconsResponseCallback {
    final /* synthetic */ List f24664a;
    final /* synthetic */ List f24665b;
    final /* synthetic */ Chat f24666c;
    final /* synthetic */ int f24667d;
    final /* synthetic */ Context f24668e;
    final /* synthetic */ ChatUtils$GroupStatusCallback f24669f;
    final /* synthetic */ GroupStatusChatMessage f24670g;

    ChatUtils$3(List list, List list2, Chat chat, int i, Context context, ChatUtils$GroupStatusCallback chatUtils$GroupStatusCallback, GroupStatusChatMessage groupStatusChatMessage) {
        this.f24664a = list;
        this.f24665b = list2;
        this.f24666c = chat;
        this.f24667d = i;
        this.f24668e = context;
        this.f24669f = chatUtils$GroupStatusCallback;
        this.f24670g = groupStatusChatMessage;
    }

    public void handleResponse(AccountIconsResponse accountIconsResponse) {
        if (accountIconsResponse.a()) {
            AccountIcon accountIcon;
            HashMap hashMap = new HashMap();
            for (AccountIcon accountIcon2 : this.f24664a) {
                hashMap.put(Long.valueOf(accountIcon2.accountId), accountIcon2);
            }
            for (AccountIcon accountIcon22 : accountIconsResponse.accountIcons) {
                hashMap.put(Long.valueOf(accountIcon22.accountId), accountIcon22);
            }
            List arrayList = new ArrayList();
            for (Long l : this.f24665b) {
                accountIcon22 = (AccountIcon) hashMap.get(l);
                if (accountIcon22 != null) {
                    arrayList.add(accountIcon22.handle);
                } else {
                    return;
                }
            }
            if ((this.f24666c instanceof GroupChat) && (this.f24667d == C1947R.string.chat_group_status_renamed || this.f24667d == C1947R.string.chat_group_status_renamed_without_usernames)) {
                arrayList.add(((GroupChat) this.f24666c).mo6334S());
            }
            this.f24669f.mo6759a(this.f24668e.getResources().getString(this.f24667d, arrayList.toArray()), this.f24670g);
        }
    }
}
