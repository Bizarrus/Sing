package com.smule.singandroid.utils;

import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.chat.LeaveChatsFragment;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import java.util.List;

class ChatUtils$5 implements CustomAlertDialogListener {
    final /* synthetic */ String f24672a;
    final /* synthetic */ List f24673b;
    final /* synthetic */ BaseFragment f24674c;

    ChatUtils$5(String str, List list, BaseFragment baseFragment) {
        this.f24672a = str;
        this.f24673b = list;
        this.f24674c = baseFragment;
    }

    public void mo6385a(CustomAlertDialog customAlertDialog) {
        BaseFragment c;
        if (this.f24672a != null) {
            c = LeaveChatsFragment.m22756c(this.f24672a);
        } else {
            c = LeaveChatsFragment.m22753a(this.f24673b);
        }
        this.f24674c.mo6487a(c);
    }

    public void mo6386b(CustomAlertDialog customAlertDialog) {
    }
}
