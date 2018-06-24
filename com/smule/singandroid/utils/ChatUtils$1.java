package com.smule.singandroid.utils;

import com.smule.android.network.models.AccountIcon;
import java.util.Comparator;

class ChatUtils$1 implements Comparator<AccountIcon> {
    final /* synthetic */ long f24661a;

    ChatUtils$1(long j) {
        this.f24661a = j;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m25811a((AccountIcon) obj, (AccountIcon) obj2);
    }

    public int m25811a(AccountIcon accountIcon, AccountIcon accountIcon2) {
        if (accountIcon.accountId == this.f24661a) {
            return 1;
        }
        if (accountIcon2.accountId == this.f24661a) {
            return -1;
        }
        return (int) Math.signum((float) (accountIcon.accountId - accountIcon2.accountId));
    }
}
