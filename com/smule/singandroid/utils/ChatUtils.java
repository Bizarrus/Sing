/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  com.smule.singandroid.utils.ChatUtils$1
 *  com.smule.singandroid.utils.ChatUtils$2
 *  com.smule.singandroid.utils.ChatUtils$3
 *  com.smule.singandroid.utils.ChatUtils$5
 *  com.smule.singandroid.utils.ChatUtils$6
 *  com.smule.singandroid.utils.ChatUtils$GroupStatusCallback
 */
package com.smule.singandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.ChatUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChatUtils {
    public static final String a = ChatUtils.class.getName();

    /*
     * Enabled aggressive block sorting
     */
    public static String a(Chat object) {
        Object var1_1 = null;
        if (object.a() == Chat.Type.a) {
            AccountIcon accountIcon = object.a(object.f());
            object = var1_1;
            if (accountIcon != null) {
                object = accountIcon.handle;
            }
        } else {
            object = ((GroupChat)object).S();
        }
        if (object != null) {
            return object;
        }
        return "";
    }

    public static List<AccountIcon> a(GroupChat object) {
        HashMap<Long, AccountIcon> hashMap = new HashMap<Long, AccountIcon>();
        HashMap<Long, AccountIcon> hashMap2 = new HashMap<Long, AccountIcon>();
        Map<Long, AccountIcon> map = object.h();
        for (Long l : map.keySet()) {
            if (object.b(l) == GroupMemberStatus.b) {
                hashMap2.put(l, map.get(l));
                continue;
            }
            hashMap.put(l, map.get(l));
        }
        object = ChatUtils.b(hashMap);
        object.addAll(ChatUtils.b(hashMap2));
        return object;
    }

    public static List<AccountIcon> a(Map<Long, AccountIcon> map) {
        ArrayList<AccountIcon> arrayList = new ArrayList<AccountIcon>(map.size());
        arrayList.addAll(map.values());
        return arrayList;
    }

    public static List<AccountIcon> a(Map<Long, AccountIcon> object, GroupInvitationChatMessage groupInvitationChatMessage) {
        object = new ArrayList<AccountIcon>(object.values());
        Collections.sort(object, new 2(groupInvitationChatMessage, com.smule.android.network.managers.UserManager.a().f()));
        return object;
    }

    private static void a(int n, int n2, List<Long> list, Chat chat, GroupStatusChatMessage groupStatusChatMessage,  groupStatusCallback, Context context) {
        ArrayList<AccountIcon> arrayList = new ArrayList<AccountIcon>();
        ArrayList<Long> arrayList2 = new ArrayList<Long>();
        for (Long l : list) {
            AccountIcon accountIcon = chat.a(l);
            if (accountIcon != null) {
                arrayList.add(accountIcon);
                continue;
            }
            arrayList2.add(l);
        }
        if (arrayList2.isEmpty()) {
            ChatUtils.a(n2, arrayList, groupStatusChatMessage, groupStatusCallback, context, chat);
            return;
        }
        groupStatusCallback.a(context.getResources().getString(n), groupStatusChatMessage);
        ChatUtils.a(n2, list, arrayList2, arrayList, groupStatusChatMessage, context, groupStatusCallback, chat);
    }

    private static void a(int n, List<AccountIcon> object, GroupStatusChatMessage groupStatusChatMessage,  groupStatusCallback, Context context, Chat chat) {
        ArrayList<String> arrayList = new ArrayList<String>();
        object = object.iterator();
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            if (ChatUtils.a(accountIcon.accountId)) {
                arrayList.add(context.getResources().getString(2131296734));
                continue;
            }
            arrayList.add(accountIcon.handle);
        }
        if (chat instanceof GroupChat && (n == 2131296532 || n == 2131296533)) {
            arrayList.add(((GroupChat)chat).S());
        }
        groupStatusCallback.a(context.getResources().getString(n, arrayList.toArray()), groupStatusChatMessage);
    }

    private static void a(int n, List<Long> list, List<Long> list2, List<AccountIcon> list3, GroupStatusChatMessage groupStatusChatMessage, Context context,  groupStatusCallback, Chat chat) {
        AccountIconCache.a().a(list2, new 3(list3, list, chat, n, context, groupStatusCallback, groupStatusChatMessage));
    }

    public static void a(Context context, int n, ChatStatus chatStatus) {
        ChatUtils.a(context, context.getString(n), chatStatus);
    }

    public static void a(Context context, ChatStatus chatStatus) {
        ChatUtils.a(context, null, chatStatus);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Context context, String string2, ChatStatus chatStatus) {
        int n;
        switch (6.b[chatStatus.ordinal()]) {
            default: {
                n = 2131296564;
                break;
            }
            case 1: {
                Log.e(a, "showChatStatusErrorToast - chatStatus was OK");
                return;
            }
            case 2: 
            case 3: {
                n = 2131296572;
                break;
            }
            case 4: {
                n = 2131296505;
                break;
            }
            case 5: {
                n = 2131296504;
            }
        }
        string2 = string2 != null ? string2 + "\n" : "";
        Toaster.a(context, string2 + context.getString(n));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(Chat chat, GroupStatusChatMessage groupStatusChatMessage,  groupStatusCallback, Context context) {
        int n;
        int n2;
        ArrayList<Long> arrayList = new ArrayList<Long>();
        switch (6.a[groupStatusChatMessage.o().ordinal()]) {
            default: {
                Log.e(a, "setMessage - Status unknown: " + (Object)((Object)groupStatusChatMessage.o()));
                groupStatusCallback.a("Status unknown: " + (Object)((Object)groupStatusChatMessage.o()), groupStatusChatMessage);
                return;
            }
            case 1: {
                n2 = 2131296537;
                n = 2131296536;
                arrayList.add(groupStatusChatMessage.b());
                break;
            }
            case 2: {
                n2 = 2131296539;
                n = 2131296538;
                arrayList.add(groupStatusChatMessage.b());
                break;
            }
            case 3: {
                if (groupStatusChatMessage.p().equals(com.smule.android.network.managers.UserManager.a().o())) {
                    n = 2131296543;
                    n2 = 2131296542;
                } else {
                    n = 2131296541;
                    n2 = 2131296540;
                }
                arrayList.add(groupStatusChatMessage.b());
                arrayList.add(SingApplication.k().c(groupStatusChatMessage.p()));
                int n3 = n2;
                n2 = n;
                n = n3;
                break;
            }
            case 4: {
                n2 = 2131296535;
                n = 2131296534;
                arrayList.add(groupStatusChatMessage.b());
                arrayList.add(SingApplication.k().c(groupStatusChatMessage.p()));
                break;
            }
            case 5: {
                n2 = 2131296533;
                n = 2131296532;
                arrayList.add(groupStatusChatMessage.b());
            }
        }
        ChatUtils.a(n2, n, arrayList, chat, groupStatusChatMessage, groupStatusCallback, context);
    }

    public static void a(BaseFragment baseFragment, String string2) {
        ChatUtils.a(baseFragment, string2, null);
    }

    private static void a(BaseFragment baseFragment, String string2, List<AccountIcon> list) {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)baseFragment.getActivity(), baseFragment.getString(2131296556, new Object[]{baseFragment.getResources().getInteger(2131623945)}), baseFragment.getString(2131296555));
        textAlertDialog.a((CustomAlertDialog.CustomAlertDialogListener)new 5(string2, list, baseFragment));
        textAlertDialog.show();
        ChatAnalytics.c();
    }

    public static void a(BaseFragment baseFragment, List<AccountIcon> list) {
        ChatUtils.a(baseFragment, null, list);
    }

    public static boolean a() {
        if (AppSettingsManager.a().a("sing.chat", "enabled", true) && com.smule.android.network.managers.UserManager.a().o() != null) {
            return true;
        }
        return false;
    }

    public static boolean a(long l) {
        if (l == com.smule.android.network.managers.UserManager.a().f()) {
            return true;
        }
        return false;
    }

    public static boolean a(long l, String object) {
        object = SingApplication.k().a((String)object);
        if (object != null && object.g().contains(l)) {
            return true;
        }
        return false;
    }

    public static boolean a(ChatMessage chatMessage) {
        long l = SingApplication.k().j();
        if (chatMessage.b() == l) {
            return true;
        }
        return false;
    }

    public static List<AccountIcon> b(Map<Long, AccountIcon> object) {
        object = new ArrayList<AccountIcon>(object.values());
        Collections.sort(object, new 1(com.smule.android.network.managers.UserManager.a().f()));
        return object;
    }

    public static boolean b() {
        if (ChatUtils.a() && SingApplication.d().c("InitAppTask.OP_CHAT_INIT")) {
            return true;
        }
        return false;
    }

    public static boolean b(ChatMessage chatMessage) {
        return ChatUtils.a(chatMessage.b());
    }
}

