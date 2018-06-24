package com.smule.singandroid.utils;

import com.smule.android.network.models.AccountIcon;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import java.util.Comparator;

class ChatUtils$2 implements Comparator<AccountIcon> {
    final /* synthetic */ GroupInvitationChatMessage f24662a;
    final /* synthetic */ long f24663b;

    ChatUtils$2(GroupInvitationChatMessage groupInvitationChatMessage, long j) {
        this.f24662a = groupInvitationChatMessage;
        this.f24663b = j;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m25812a((AccountIcon) obj, (AccountIcon) obj2);
    }

    public int m25812a(AccountIcon accountIcon, AccountIcon accountIcon2) {
        if (this.f24662a.m19609b(accountIcon.accountId) == GroupMemberStatus.JOINED && this.f24662a.m19609b(accountIcon2.accountId) == GroupMemberStatus.PENDING) {
            return -1;
        }
        if (this.f24662a.m19609b(accountIcon2.accountId) == GroupMemberStatus.JOINED && this.f24662a.m19609b(accountIcon.accountId) == GroupMemberStatus.PENDING) {
            return 1;
        }
        if (accountIcon.accountId == this.f24663b) {
            return 1;
        }
        if (accountIcon2.accountId != this.f24663b) {
            return (int) Math.signum((float) (accountIcon.accountId - accountIcon2.accountId));
        }
        return -1;
    }
}
