package com.smule.singandroid.chat;

import com.facebook.internal.FacebookRequestErrorClassification;
import com.smule.android.logging.Analytics.AnalyticsType;
import com.smule.android.logging.Analytics.FollowingStatus;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$Event.Builder;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;

public class ChatAnalytics {

    public enum ChatType implements AnalyticsType {
        PEER("PeerChat"),
        GROUP("GroupChat");
        
        private String f20740c;

        private ChatType(String str) {
            this.f20740c = str;
        }

        public String mo6235a() {
            return this.f20740c;
        }

        public static ChatType m22368a(Chat chat) {
            if (chat instanceof PeerChat) {
                return PEER;
            }
            if (chat instanceof GroupChat) {
                return GROUP;
            }
            return null;
        }
    }

    public enum FlagUserType implements AnalyticsType {
        CHAT("chat", C1947R.string.chat_flag_offensive_chat_message),
        SEXUAL_CONTENT("sexual", C1947R.string.chat_flag_sexual_content),
        HARASSMENT("harassment", C1947R.string.chat_flag_harassment);
        
        private String f20745d;
        private int f20746e;

        private FlagUserType(String str, int i) {
            this.f20745d = str;
            this.f20746e = i;
        }

        public String mo6235a() {
            return this.f20745d;
        }

        public int m22371b() {
            return this.f20746e;
        }
    }

    public enum GroupInviteActionType implements AnalyticsType {
        ACCEPT("accept"),
        VIEW("view"),
        EXPIRED("expired"),
        ERROR("error");
        
        private String f20752e;

        private GroupInviteActionType(String str) {
            this.f20752e = str;
        }

        public String mo6235a() {
            return this.f20752e;
        }
    }

    public enum MessageCenterFilterType implements AnalyticsType {
        INBOX("inbox"),
        OTHER(FacebookRequestErrorClassification.KEY_OTHER);
        
        private String f20756c;

        private MessageCenterFilterType(String str) {
            this.f20756c = str;
        }

        public String mo6235a() {
            return this.f20756c;
        }
    }

    public enum SettingToggleType implements AnalyticsType {
        ON("on"),
        OFF("off");
        
        private String f20760c;

        private SettingToggleType(String str) {
            this.f20760c = str;
        }

        public String mo6235a() {
            return this.f20760c;
        }
    }

    protected static FollowingStatus m22375a(Chat chat) {
        if (chat instanceof PeerChat) {
            return chat.mo6347b() == Bucket.INBOX ? FollowingStatus.FOLLOWING : FollowingStatus.NOT_FOLLOWING;
        } else {
            return null;
        }
    }

    protected static Number m22391b(Chat chat) {
        if (chat instanceof PeerChat) {
            return Long.valueOf(chat.mo6341f());
        }
        return null;
    }

    static void m22383a(Chat chat, Number number) {
        EventLogger2.a().a(new Builder().m17935a("chat_create").m17941b(ChatType.m22368a(chat)).m17934a(m22391b(chat)).m17959d(chat.m19209c()).m17951c(number));
    }

    static void m22386a(ChatType chatType, String str, Number number, FollowingStatus followingStatus, String str2, String str3) {
        EventLogger2.a().a(new Builder().m17935a("chat_send").m17941b((AnalyticsType) chatType).m17934a((Object) number).m17959d(str).m17966e(str2).m17971f(str3).m17969f((AnalyticsType) followingStatus));
    }

    public static void m22390a(String str, String str2, String str3, Number number) {
        EventLogger2.a().a(new Builder().m17935a("push_clk").m17952c(str).m17951c(number).m17981h(str3).m17959d(str2));
    }

    public static void m22381a(Chat chat, long j) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_request_sent").m17934a(Long.valueOf(j)).m17959d(chat.m19209c()).m17969f(m22375a(chat)));
    }

    public static void m22379a(long j, boolean z, GroupInviteActionType groupInviteActionType, String str) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_request_clk").m17934a(Long.valueOf(j)).m17941b((AnalyticsType) groupInviteActionType).m17959d(str).m17969f(z ? FollowingStatus.FOLLOWING : FollowingStatus.NOT_FOLLOWING));
    }

    public static void m22380a(long j, boolean z, String str) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_join").m17934a(Long.valueOf(j)).m17959d(str).m17969f(z ? FollowingStatus.FOLLOWING : FollowingStatus.NOT_FOLLOWING));
    }

    public static void m22389a(String str) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_destroy").m17959d(str));
    }

    public static void m22387a(MessageCenterFilterType messageCenterFilterType, int i) {
        EventLogger2.a().a(new Builder().m17935a("messages_pgview").m17941b((AnalyticsType) messageCenterFilterType).m17939b(i));
    }

    public static void m22394b(MessageCenterFilterType messageCenterFilterType, int i) {
        EventLogger2.a().a(new Builder().m17935a("messages_filter_clk").m17941b((AnalyticsType) messageCenterFilterType).m17939b(i));
    }

    public static void m22376a() {
        EventLogger2.a().a(new Builder().m17935a("new_msg_pgview"));
    }

    public static void m22397c(Chat chat) {
        EventLogger2.a().a(new Builder().m17935a("chat_pgview").m17934a(m22391b(chat)).m17941b(ChatType.m22368a(chat)).m17959d(chat.m19209c()).m17969f(m22375a(chat)));
    }

    public static void m22400d(Chat chat) {
        EventLogger2.a().a(new Builder().m17935a("chat_details_pgview").m17934a(m22391b(chat)).m17941b(ChatType.m22368a(chat)).m17959d(chat.m19209c()).m17969f(m22375a(chat)));
    }

    public static void m22392b() {
        EventLogger2.a().a(new Builder().m17935a("messages_gesture_clk"));
    }

    public static void m22401e(Chat chat) {
        EventLogger2.a().a(new Builder().m17935a("chat_delete").m17934a(m22391b(chat)).m17959d(chat.m19209c()).m17969f(m22375a(chat)));
    }

    public static void m22384a(GroupChat groupChat) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_leave").m17959d(groupChat.m19209c()));
    }

    public static void m22402f(Chat chat) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_addmember_clk").m17934a(m22391b(chat)).m17941b(ChatType.m22368a(chat)).m17959d(chat.m19209c()));
    }

    public static void m22403g(Chat chat) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_removemember_clk").m17959d(chat.m19209c()));
    }

    public static void m22385a(GroupChat groupChat, long j) {
        EventLogger2.a().a(new Builder().m17935a("groupchat_removemember").m17934a(Long.valueOf(j)).m17959d(groupChat.m19209c()));
    }

    public static void m22393b(GroupChat groupChat) {
        String str;
        Number number = null;
        Builder a = new Builder().m17935a("groupchatname_pgview");
        if (groupChat == null) {
            str = null;
        } else {
            str = groupChat.m19209c();
        }
        Builder d = a.m17959d(str);
        if (groupChat != null) {
            number = Integer.valueOf(groupChat.mo6342g().size());
        }
        EventLogger2.a().a(d.m17951c(number));
    }

    public static void m22398c(GroupChat groupChat) {
        String str;
        Number number = null;
        Builder a = new Builder().m17935a("groupchatname_edited");
        if (groupChat == null) {
            str = null;
        } else {
            str = groupChat.m19209c();
        }
        Builder d = a.m17959d(str);
        if (groupChat != null) {
            number = Integer.valueOf(groupChat.mo6342g().size());
        }
        EventLogger2.a().a(d.m17951c(number));
    }

    public static void m22396c() {
        EventLogger2.a().a(new Builder().m17935a("maxgroupchats_pgview"));
    }

    public static void m22399d() {
        EventLogger2.a().a(new Builder().m17935a("maxgroupchats_leave_pgview"));
    }

    public static void m22382a(Chat chat, SettingToggleType settingToggleType) {
        EventLogger2.a().a(new Builder().m17935a("chat_notif_set").m17931a((AnalyticsType) settingToggleType).m17941b(ChatType.m22368a(chat)).m17959d(chat.m19209c()).m17939b(chat.mo6342g().size()));
    }

    public static void m22388a(SettingToggleType settingToggleType) {
        EventLogger2.a().a(new Builder().m17935a("chat_all_notif_set").m17931a((AnalyticsType) settingToggleType));
    }

    public static void m22395b(SettingToggleType settingToggleType) {
        EventLogger2.a().a(new Builder().m17935a("chat_all_receipt_set").m17931a((AnalyticsType) settingToggleType));
    }

    public static void m22377a(long j) {
        EventLogger2.a().a(new Builder().m17935a("user_flag_clk").m17934a(Long.valueOf(j)));
    }

    public static void m22378a(long j, FlagUserType flagUserType) {
        EventLogger2.a().a(new Builder().m17935a("user_flag_desc").m17934a(Long.valueOf(j)).m17941b((AnalyticsType) flagUserType));
    }
}
