/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.chat;

import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import java.util.Set;

public class ChatAnalytics {
    protected static Analytics a(Chat chat) {
        if (chat instanceof PeerChat) {
            if (chat.b() == Chat.Bucket.a) {
                return Analytics.a;
            }
            return Analytics.b;
        }
        return null;
    }

    public static void a() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("new_msg_pgview");
        com.smule.android.logging.EventLogger2.a().a(builder);
    }

    public static void a(long l) {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("user_flag_clk").a((Object)l);
        com.smule.android.logging.EventLogger2.a().a(builder);
    }

    public static void a(long l, FlagUserType object) {
        object = new EventLogger2.Builder().a("user_flag_desc").a((Object)l).b(object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(long l, boolean bl, GroupInviteActionType object, String object2) {
        object2 = new EventLogger2.Builder().a("groupchat_request_clk").a((Object)l).b(object).d((String)object2);
        object = bl ? Analytics.a : Analytics.b;
        object = object2.f(object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void a(long l, boolean bl, String object) {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("groupchat_join").a((Object)l).d((String)object);
        object = bl ? Analytics.a : Analytics.b;
        object = builder.f(object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(Chat object, long l) {
        object = new EventLogger2.Builder().a("groupchat_request_sent").a((Object)l).d(object.c()).f(ChatAnalytics.a((Chat)object));
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(Chat object, SettingToggleType settingToggleType) {
        object = new EventLogger2.Builder().a("chat_notif_set").a(settingToggleType).b(ChatType.a((Chat)object)).d(object.c()).b(object.g().size());
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    static void a(Chat object, Number number) {
        object = new EventLogger2.Builder().a("chat_create").b(ChatType.a((Chat)object)).a((Object)ChatAnalytics.b((Chat)object)).d(object.c()).c(number);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(GroupChat object) {
        object = new EventLogger2.Builder().a("groupchat_leave").d(object.c());
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(GroupChat object, long l) {
        object = new EventLogger2.Builder().a("groupchat_removemember").a((Object)l).d(object.c());
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    static void a(ChatType object, String string2, Number number, Analytics followingStatus, String string3, String string4) {
        object = new EventLogger2.Builder().a("chat_send").b(object).a((Object)number).d(string2).e(string3).f(string4).f(followingStatus);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(MessageCenterFilterType object, int n) {
        object = new EventLogger2.Builder().a("messages_pgview").b(object).b(n);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(SettingToggleType object) {
        object = new EventLogger2.Builder().a("chat_all_notif_set").a(object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object) {
        object = new EventLogger2.Builder().a("groupchat_destroy").d((String)object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void a(String object, String string2, String string3, Number number) {
        object = new EventLogger2.Builder().a("push_clk").c((String)object).c(number).h(string3).d(string2);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    protected static Number b(Chat chat) {
        if (chat instanceof PeerChat) {
            return chat.f();
        }
        return null;
    }

    public static void b() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("messages_gesture_clk");
        com.smule.android.logging.EventLogger2.a().a(builder);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void b(GroupChat object) {
        Object var2_1 = null;
        EventLogger2.Builder builder = new EventLogger2.Builder().a("groupchatname_pgview");
        Object object2 = object == null ? null : object.c();
        object2 = builder.d((String)object2);
        object = object == null ? var2_1 : Integer.valueOf(object.g().size());
        object = object2.c((Number)object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b(MessageCenterFilterType object, int n) {
        object = new EventLogger2.Builder().a("messages_filter_clk").b(object).b(n);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void b(SettingToggleType object) {
        object = new EventLogger2.Builder().a("chat_all_receipt_set").a(object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void c() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("maxgroupchats_pgview");
        com.smule.android.logging.EventLogger2.a().a(builder);
    }

    public static void c(Chat object) {
        object = new EventLogger2.Builder().a("chat_pgview").a((Object)ChatAnalytics.b((Chat)object)).b(ChatType.a((Chat)object)).d(object.c()).f(ChatAnalytics.a((Chat)object));
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void c(GroupChat object) {
        Object var2_1 = null;
        EventLogger2.Builder builder = new EventLogger2.Builder().a("groupchatname_edited");
        Object object2 = object == null ? null : object.c();
        object2 = builder.d((String)object2);
        object = object == null ? var2_1 : Integer.valueOf(object.g().size());
        object = object2.c((Number)object);
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void d() {
        EventLogger2.Builder builder = new EventLogger2.Builder().a("maxgroupchats_leave_pgview");
        com.smule.android.logging.EventLogger2.a().a(builder);
    }

    public static void d(Chat object) {
        object = new EventLogger2.Builder().a("chat_details_pgview").a((Object)ChatAnalytics.b((Chat)object)).b(ChatType.a((Chat)object)).d(object.c()).f(ChatAnalytics.a((Chat)object));
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void e(Chat object) {
        object = new EventLogger2.Builder().a("chat_delete").a((Object)ChatAnalytics.b((Chat)object)).d(object.c()).f(ChatAnalytics.a((Chat)object));
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void f(Chat object) {
        object = new EventLogger2.Builder().a("groupchat_addmember_clk").a((Object)ChatAnalytics.b((Chat)object)).b(ChatType.a((Chat)object)).d(object.c());
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static void g(Chat object) {
        object = new EventLogger2.Builder().a("groupchat_removemember_clk").d(object.c());
        com.smule.android.logging.EventLogger2.a().a((EventLogger2.Builder)object);
    }

    public static enum ChatType implements Analytics
    {
        a("PeerChat"),
        b("GroupChat");
        
        private String c;

        private ChatType(String string3) {
            this.c = string3;
        }

        public static ChatType a(Chat chat) {
            if (chat instanceof PeerChat) {
                return a;
            }
            if (chat instanceof GroupChat) {
                return b;
            }
            return null;
        }

        @Override
        public String a() {
            return this.c;
        }
    }

    public static enum FlagUserType implements Analytics
    {
        a("chat", 2131296516),
        b("sexual", 2131296520),
        c("harassment", 2131296513);
        
        private String d;
        private int e;

        private FlagUserType(String string3, int n2) {
            this.d = string3;
            this.e = n2;
        }

        @Override
        public String a() {
            return this.d;
        }

        public int b() {
            return this.e;
        }
    }

    public static enum GroupInviteActionType implements Analytics
    {
        a("accept"),
        b("view"),
        c("expired"),
        d("error");
        
        private String e;

        private GroupInviteActionType(String string3) {
            this.e = string3;
        }

        @Override
        public String a() {
            return this.e;
        }
    }

    public static enum MessageCenterFilterType implements Analytics
    {
        a("inbox"),
        b("other");
        
        private String c;

        private MessageCenterFilterType(String string3) {
            this.c = string3;
        }

        @Override
        public String a() {
            return this.c;
        }
    }

    public static enum SettingToggleType implements Analytics
    {
        a("on"),
        b("off");
        
        private String c;

        private SettingToggleType(String string3) {
            this.c = string3;
        }

        @Override
        public String a() {
            return this.c;
        }
    }

}

