package com.smule.singandroid.utils;

import android.content.Context;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.GroupStatusChatMessage;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatUtils {
    public static final String f7177a = ChatUtils.class.getName();

    public static boolean m8906a() {
        return AppSettingsManager.m7855a().m7881a("sing.chat", "enabled", true) && UserManager.m8111a().m8220o() != null;
    }

    public static boolean m8911b() {
        return m8906a() && SingApplication.m8797d().m8401c("InitAppTask.OP_CHAT_INIT");
    }

    public static boolean m8909a(ChatMessage chatMessage) {
        return chatMessage.b() == SingApplication.m8802j().mo4071j();
    }

    public static List<AccountIcon> m8894a(Map<Long, AccountIcon> map) {
        List<AccountIcon> arrayList = new ArrayList(map.size());
        arrayList.addAll(map.values());
        return arrayList;
    }

    public static String m8892a(Chat chat) {
        String str = null;
        if (chat.a() == Type.a) {
            AccountIcon a = chat.a(chat.f());
            if (a != null) {
                str = a.handle;
            }
        } else {
            str = ((GroupChat) chat).S();
        }
        return str != null ? str : "";
    }

    public static boolean m8908a(long j, String str) {
        Chat a = SingApplication.m8802j().m8548a(str);
        return a != null && a.g().contains(Long.valueOf(j));
    }

    public static List<AccountIcon> m8910b(Map<Long, AccountIcon> map) {
        List<AccountIcon> arrayList = new ArrayList(map.values());
        Collections.sort(arrayList, new 1(UserManager.m8111a().m8203f()));
        return arrayList;
    }

    public static List<AccountIcon> m8895a(Map<Long, AccountIcon> map, GroupInvitationChatMessage groupInvitationChatMessage) {
        List<AccountIcon> arrayList = new ArrayList(map.values());
        Collections.sort(arrayList, new 2(groupInvitationChatMessage, UserManager.m8111a().m8203f()));
        return arrayList;
    }

    public static List<AccountIcon> m8893a(GroupChat groupChat) {
        Map hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        Map h = groupChat.h();
        for (Long l : h.keySet()) {
            if (groupChat.b(l.longValue()) == GroupMemberStatus.b) {
                hashMap2.put(l, h.get(l));
            } else {
                hashMap.put(l, h.get(l));
            }
        }
        List<AccountIcon> b = m8910b(hashMap);
        b.addAll(m8910b(hashMap2));
        return b;
    }

    public static void m8902a(Chat chat, GroupStatusChatMessage groupStatusChatMessage, GroupStatusCallback groupStatusCallback, Context context) {
        int i;
        int i2;
        List arrayList = new ArrayList();
        switch (6.a[groupStatusChatMessage.o().ordinal()]) {
            case 1:
                i = C1947R.string.chat_group_status_user_joined_without_usernames;
                i2 = C1947R.string.chat_group_status_user_joined;
                arrayList.add(Long.valueOf(groupStatusChatMessage.b()));
                break;
            case 2:
                i = C1947R.string.chat_group_status_user_left_without_usernames;
                i2 = C1947R.string.chat_group_status_user_left;
                arrayList.add(Long.valueOf(groupStatusChatMessage.b()));
                break;
            case 3:
                if (groupStatusChatMessage.p().equals(UserManager.m8111a().m8220o())) {
                    i2 = C1947R.string.chat_group_status_user_removed_you_without_usernames;
                    i = C1947R.string.chat_group_status_user_removed_you;
                } else {
                    i2 = C1947R.string.chat_group_status_user_removed_without_usernames;
                    i = C1947R.string.chat_group_status_user_removed;
                }
                arrayList.add(Long.valueOf(groupStatusChatMessage.b()));
                arrayList.add(Long.valueOf(SingApplication.m8802j().mo4066c(groupStatusChatMessage.p())));
                int i3 = i;
                i = i2;
                i2 = i3;
                break;
            case 4:
                i = C1947R.string.chat_group_status_user_invited_without_usernames;
                i2 = C1947R.string.chat_group_status_user_invited;
                arrayList.add(Long.valueOf(groupStatusChatMessage.b()));
                arrayList.add(Long.valueOf(SingApplication.m8802j().mo4066c(groupStatusChatMessage.p())));
                break;
            case 5:
                i = C1947R.string.chat_group_status_renamed_without_usernames;
                i2 = C1947R.string.chat_group_status_renamed;
                arrayList.add(Long.valueOf(groupStatusChatMessage.b()));
                break;
            default:
                Log.m7776e(f7177a, "setMessage - Status unknown: " + groupStatusChatMessage.o());
                groupStatusCallback.a("Status unknown: " + groupStatusChatMessage.o(), groupStatusChatMessage);
                return;
        }
        m8896a(i, i2, arrayList, chat, groupStatusChatMessage, groupStatusCallback, context);
    }

    private static void m8896a(int i, int i2, List<Long> list, Chat chat, GroupStatusChatMessage groupStatusChatMessage, GroupStatusCallback groupStatusCallback, Context context) {
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        for (Long l : list) {
            AccountIcon a = chat.a(l.longValue());
            if (a != null) {
                arrayList.add(a);
            } else {
                arrayList2.add(l);
            }
        }
        if (arrayList2.isEmpty()) {
            m8897a(i2, arrayList, groupStatusChatMessage, groupStatusCallback, context, chat);
            return;
        }
        groupStatusCallback.a(context.getResources().getString(i), groupStatusChatMessage);
        m8898a(i2, list, arrayList2, arrayList, groupStatusChatMessage, context, groupStatusCallback, chat);
    }

    private static void m8897a(int i, List<AccountIcon> list, GroupStatusChatMessage groupStatusChatMessage, GroupStatusCallback groupStatusCallback, Context context, Chat chat) {
        List arrayList = new ArrayList();
        for (AccountIcon accountIcon : list) {
            if (m8907a(accountIcon.accountId)) {
                arrayList.add(context.getResources().getString(C1947R.string.core_you_cap));
            } else {
                arrayList.add(accountIcon.handle);
            }
        }
        if ((chat instanceof GroupChat) && (i == C1947R.string.chat_group_status_renamed || i == C1947R.string.chat_group_status_renamed_without_usernames)) {
            arrayList.add(((GroupChat) chat).S());
        }
        groupStatusCallback.a(context.getResources().getString(i, arrayList.toArray()), groupStatusChatMessage);
    }

    private static void m8898a(int i, List<Long> list, List<Long> list2, List<AccountIcon> list3, GroupStatusChatMessage groupStatusChatMessage, Context context, GroupStatusCallback groupStatusCallback, Chat chat) {
        AccountIconCache.a().a(list2, new 3(list3, list, chat, i, context, groupStatusCallback, groupStatusChatMessage));
    }

    public static boolean m8907a(long j) {
        return j == UserManager.m8111a().m8203f();
    }

    public static boolean m8912b(ChatMessage chatMessage) {
        return m8907a(chatMessage.b());
    }

    public static void m8900a(Context context, ChatStatus chatStatus) {
        m8901a(context, null, chatStatus);
    }

    public static void m8899a(Context context, int i, ChatStatus chatStatus) {
        m8901a(context, context.getString(i), chatStatus);
    }

    public static void m8901a(Context context, String str, ChatStatus chatStatus) {
        int i;
        switch (6.b[chatStatus.ordinal()]) {
            case 1:
                Log.m7776e(f7177a, "showChatStatusErrorToast - chatStatus was OK");
                return;
            case 2:
            case 3:
                i = C1947R.string.chat_network_error;
                break;
            case 4:
                i = C1947R.string.chat_error_no_longer_member;
                break;
            case 5:
                i = C1947R.string.chat_error_no_longer_exists;
                break;
            default:
                i = C1947R.string.chat_load_error;
                break;
        }
        Toaster.m8430a(context, (str != null ? str + "\n" : "") + context.getString(i));
    }

    public static void m8903a(BaseFragment baseFragment, String str) {
        m8904a(baseFragment, str, null);
    }

    public static void m8905a(BaseFragment baseFragment, List<AccountIcon> list) {
        m8904a(baseFragment, null, (List) list);
    }

    private static void m8904a(BaseFragment baseFragment, String str, List<AccountIcon> list) {
        CustomAlertDialog textAlertDialog = new TextAlertDialog(baseFragment.getActivity(), baseFragment.getString(C1947R.string.chat_joining_too_many_chats_title, new Object[]{Integer.valueOf(baseFragment.getResources().getInteger(C1947R.integer.chat_max_group_chats))}), baseFragment.getString(C1947R.string.chat_joining_too_many_chats_body));
        textAlertDialog.a(new 5(str, list, baseFragment));
        textAlertDialog.show();
        ChatAnalytics.c();
    }
}
