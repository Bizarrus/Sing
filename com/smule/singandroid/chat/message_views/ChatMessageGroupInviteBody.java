package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalytics.GroupInviteActionType;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageBodyViewInterface;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageViewListener;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.List;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class ChatMessageGroupInviteBody extends FrameLayout implements ChatMessageBodyViewInterface {
    public static final String f21337a = ChatMessageGroupInviteBody.class.getName();
    @ViewById
    TextView f21338b;
    @ViewsById
    List<ProfileImageWithVIPBadgeAndPendingGreyDotView> f21339c;
    @ViewById
    Button f21340d;
    @ViewById
    LinearLayout f21341e;
    @ViewById
    ImageView f21342f;
    @ViewById
    View f21343g;
    ChatMessageViewListener f21344h;
    GroupInvitationChatMessage f21345i;
    Chat f21346j;
    boolean f21347k;
    boolean f21348l = false;
    GroupInviteActionType f21349m;
    private boolean f21350n = false;

    public ChatMessageGroupInviteBody(Context context) {
        super(context);
    }

    public ChatMessageGroupInviteBody(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChatMessageGroupInviteBody(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23018a(ChatMessageViewListener chatMessageViewListener) {
        this.f21344h = chatMessageViewListener;
    }

    public void m23016a(ChatMessage chatMessage, boolean z, Chat chat) {
        if (this.f21344h == null) {
            throw new RuntimeException("Make sure to call ChatMessageGroupInviteBody.init first");
        }
        this.f21345i = (GroupInvitationChatMessage) chatMessage;
        this.f21346j = chat;
        this.f21347k = z;
        if (this.f21345i.m19387d() == State.ERROR) {
            if (this.f21345i.m19388e() == ChatStatus.CHAT_NOT_FOUND) {
                this.f21338b.setText(C1947R.string.chat_invite_title_group);
                m23017a(GroupInviteActionType.EXPIRED);
            } else {
                this.f21338b.setText(C1947R.string.chat_invite_title_error);
                m23017a(GroupInviteActionType.ERROR);
            }
            this.f21338b.setTextColor(getResources().getColor(C1947R.color.contextual_text));
            m23013c();
            this.f21350n = true;
            return;
        }
        CharSequence string;
        this.f21350n = false;
        String p = this.f21345i.m19614p();
        if (p == null || p.isEmpty()) {
            this.f21338b.setTextColor(getResources().getColor(C1947R.color.contextual_text));
            string = getResources().getString(C1947R.string.chat_invite_title_group);
        } else {
            this.f21338b.setTextColor(getResources().getColor(C1947R.color.black));
            string = getResources().getString(C1947R.string.chat_invite_title_group_with_name, new Object[]{p});
        }
        this.f21338b.setText(string);
        if (!this.f21347k) {
            if (this.f21345i.m19387d() != State.RAW) {
                if (!isInEditMode()) {
                    switch (this.f21345i.m19609b(UserManager.a().f())) {
                        case PENDING:
                            m23017a(GroupInviteActionType.ACCEPT);
                            break;
                        case JOINED:
                            m23017a(GroupInviteActionType.VIEW);
                            break;
                        default:
                            m23017a(GroupInviteActionType.EXPIRED);
                            break;
                    }
                }
            }
            m23017a(null);
        } else {
            switch (isInEditMode() ? GroupMemberStatus.JOINED : this.f21345i.m19609b(UserManager.a().f())) {
                case NONE:
                    m23017a(GroupInviteActionType.EXPIRED);
                    break;
                default:
                    m23017a(GroupInviteActionType.VIEW);
                    break;
            }
        }
        m23015a();
    }

    protected void m23017a(GroupInviteActionType groupInviteActionType) {
        this.f21349m = groupInviteActionType;
        int i = C1947R.string.core_loading;
        this.f21348l = false;
        if (groupInviteActionType != null) {
            switch (groupInviteActionType) {
                case ACCEPT:
                    i = C1947R.string.chat_invite_accept;
                    this.f21348l = false;
                    this.f21340d.setEnabled(true);
                    break;
                case VIEW:
                    i = C1947R.string.chat_invite_accepted;
                    this.f21348l = false;
                    this.f21340d.setEnabled(true);
                    break;
                case EXPIRED:
                    i = C1947R.string.chat_invite_invalid;
                    this.f21348l = true;
                    this.f21340d.setEnabled(false);
                    break;
                case ERROR:
                    i = C1947R.string.chat_invite_error;
                    this.f21348l = true;
                    this.f21340d.setEnabled(true);
                    break;
            }
        }
        this.f21340d.setText(i);
        this.f21342f.setVisibility(this.f21348l ? 0 : 8);
    }

    protected void m23015a() {
        if (this.f21345i.m19615q() == null) {
            m23013c();
        } else {
            m23012a(ChatUtils.a(this.f21345i.m19615q(), this.f21345i));
        }
    }

    private void m23012a(List<AccountIcon> list) {
        if (list == null || list.isEmpty() || this.f21345i.m19609b(UserManager.a().f()) == GroupMemberStatus.NONE) {
            m23013c();
            return;
        }
        m23014d();
        int i = 0;
        int i2 = 0;
        while (i < this.f21339c.size()) {
            int i3;
            ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView) this.f21339c.get(i);
            if (i >= list.size()) {
                profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(8);
                i3 = i2;
            } else if (i != this.f21339c.size() - 1 || this.f21339c.size() >= list.size()) {
                AccountIcon accountIcon = (AccountIcon) list.get(i);
                if (MiscUtils.m25896a(accountIcon.picUrl)) {
                    profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(8);
                } else {
                    i2++;
                    profileImageWithVIPBadgeAndPendingGreyDotView.setProfilePicUrl(accountIcon.picUrl);
                    profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                    profileImageWithVIPBadgeAndPendingGreyDotView.m23402a(this.f21345i.m19609b(accountIcon.accountId) == GroupMemberStatus.PENDING);
                }
                i3 = i2;
            } else {
                profileImageWithVIPBadgeAndPendingGreyDotView.m23393a(list.size() - i2, false, null, C1947R.drawable.solid_grey_circle, C1947R.drawable.solid_grey_circle_with_border);
                profileImageWithVIPBadgeAndPendingGreyDotView.m23402a(false);
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
    }

    private void m23013c() {
        this.f21341e.setVisibility(8);
    }

    private void m23014d() {
        this.f21341e.setVisibility(0);
    }

    @Click
    protected void m23019b() {
        if (!this.f21350n) {
            ChatAnalytics.m22379a(this.f21345i.m19385b(), FollowManager.m18204a().m18222a(this.f21345i.m19385b()), this.f21349m, this.f21345i.m19613o());
            this.f21344h.mo6748a(this.f21345i);
            m23016a(this.f21345i, this.f21347k, this.f21346j);
        } else if (this.f21346j != null) {
            this.f21346j.m19206b(this.f21345i);
        }
    }
}
