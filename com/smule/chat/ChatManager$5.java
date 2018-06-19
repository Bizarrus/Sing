package com.smule.chat;

import com.smule.android.network.managers.UserManager$UpdateAccountPreferencesResponseCallback;
import com.smule.android.network.managers.UserManager.UpdateAccountPreferencesResponse;

class ChatManager$5 implements UserManager$UpdateAccountPreferencesResponseCallback {
    final /* synthetic */ boolean f18069a;
    final /* synthetic */ Completion f18070b;
    final /* synthetic */ ChatManager f18071c;

    ChatManager$5(ChatManager chatManager, boolean z, Completion completion) {
        this.f18071c = chatManager;
        this.f18069a = z;
        this.f18070b = completion;
    }

    public void handleResponse(UpdateAccountPreferencesResponse updateAccountPreferencesResponse) {
        if (updateAccountPreferencesResponse.a()) {
            ChatManager.a(this.f18071c, this.f18069a);
            this.f18070b.mo6329a(ChatStatus.OK);
            return;
        }
        this.f18070b.mo6329a(ChatStatus.NETWORK_ERROR);
    }
}
