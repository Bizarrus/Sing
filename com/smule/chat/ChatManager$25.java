package com.smule.chat;

import com.smule.android.network.managers.UserManager$AccountPreferencesResponseCallback;
import com.smule.android.network.managers.UserManager.AccountPreferencesResponse;
import com.smule.android.network.models.AccountPreference;

class ChatManager$25 implements UserManager$AccountPreferencesResponseCallback {
    final /* synthetic */ Runnable f18059a;
    final /* synthetic */ ChatManager f18060b;

    ChatManager$25(ChatManager chatManager, Runnable runnable) {
        this.f18060b = chatManager;
        this.f18059a = runnable;
    }

    public void handleResponse(AccountPreferencesResponse accountPreferencesResponse) {
        if (accountPreferencesResponse.a()) {
            for (AccountPreference accountPreference : accountPreferencesResponse.preferences) {
                if (accountPreference.name.equals("SPARK_PUSH_DISABLE")) {
                    ChatManager.a(this.f18060b, Boolean.parseBoolean(accountPreference.value));
                } else if (accountPreference.name.equals("SPARK_READRECEIPT_DISABLE")) {
                    ChatManager.b(this.f18060b, !Boolean.parseBoolean(accountPreference.value));
                }
            }
        }
        this.f18059a.run();
    }
}
