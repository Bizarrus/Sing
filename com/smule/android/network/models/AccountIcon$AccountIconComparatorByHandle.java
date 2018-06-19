package com.smule.android.network.models;

import java.util.Comparator;

public class AccountIcon$AccountIconComparatorByHandle implements Comparator<AccountIcon> {
    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m18532a((AccountIcon) obj, (AccountIcon) obj2);
    }

    public int m18532a(AccountIcon accountIcon, AccountIcon accountIcon2) {
        if (accountIcon.handle == null && accountIcon2.handle == null) {
            return 0;
        }
        if (accountIcon.handle == null || accountIcon2.handle == null) {
            return accountIcon.handle == null ? -1 : 1;
        } else {
            return accountIcon.handle.toLowerCase().compareTo(accountIcon2.handle.toLowerCase());
        }
    }
}
