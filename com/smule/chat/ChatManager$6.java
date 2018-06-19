package com.smule.chat;

import com.smule.android.network.managers.UserManager$UpdateAccountPreferencesResponseCallback;
import com.smule.android.network.managers.UserManager.UpdateAccountPreferencesResponse;

class ChatManager$6 implements UserManager$UpdateAccountPreferencesResponseCallback {
    final /* synthetic */ boolean f18072a;
    final /* synthetic */ Completion f18073b;
    final /* synthetic */ ChatManager f18074c;

    ChatManager$6(ChatManager chatManager, boolean z, Completion completion) {
        this.f18074c = chatManager;
        this.f18072a = z;
        this.f18073b = completion;
    }

    public void handleResponse(UpdateAccountPreferencesResponse updateAccountPreferencesResponse) {
        if (updateAccountPreferencesResponse.a()) {
            ChatManager.b(this.f18074c, this.f18072a);
            this.f18073b.mo6329a(ChatStatus.OK);
            return;
        }
        this.f18073b.mo6329a(ChatStatus.NETWORK_ERROR);
    }
}
