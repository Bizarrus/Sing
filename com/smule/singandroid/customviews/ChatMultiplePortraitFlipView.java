package com.smule.singandroid.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.FlipAnimation;
import com.smule.singandroid.utils.ChatUtils;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class ChatMultiplePortraitFlipView extends RelativeLayout {
    public static final String f21494a = ChatMultiplePortraitFlipView.class.getName();
    @ViewById
    protected ViewGroup f21495b;
    @ViewById
    protected ViewGroup f21496c;
    @ViewById
    protected ViewGroup f21497d;
    @ViewById
    protected ProfileImageWithVIPBadge f21498e;
    @ViewById
    protected ProfileImageWithVIPBadge f21499f;
    @ViewById
    protected ViewGroup f21500g;
    @ViewById
    protected ViewGroup f21501h;
    @ViewsById
    protected List<ProfileImageWithVIPBadgeAndPendingGreyDotView> f21502i;
    private boolean f21503j;
    private FlipAnimation f21504k;

    public ChatMultiplePortraitFlipView(Context context) {
        this(context, null);
    }

    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21503j = true;
    }

    @TargetApi(21)
    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f21503j = true;
    }

    public synchronized void m23145a() {
        if (this.f21504k != null) {
            this.f21504k.cancel();
        }
        this.f21504k = new FlipAnimation(this.f21496c, this.f21497d, getResources().getInteger(C1947R.integer.chat_portrait_flip_animation_duration));
        if (!this.f21503j) {
            this.f21504k.m22742a();
        }
        this.f21495b.startAnimation(this.f21504k);
        this.f21503j = !this.f21503j;
    }

    public void setSide(boolean z) {
        if (this.f21504k != null) {
            this.f21504k.cancel();
        }
        if (z) {
            this.f21496c.setVisibility(0);
            this.f21497d.setVisibility(8);
        } else {
            this.f21496c.setVisibility(8);
            this.f21497d.setVisibility(0);
        }
        this.f21503j = z;
    }

    public void m23146a(int i, int i2, int i3) {
        this.f21499f.m23388a(i);
        this.f21499f.setLoadImageWithBorder(false);
        this.f21498e.m23388a(i);
        for (ProfileImageWithVIPBadge profileImageWithVIPBadge : this.f21502i) {
            profileImageWithVIPBadge.m23388a(i2);
            profileImageWithVIPBadge.setLoadImageWithBorder(false);
        }
        this.f21500g.setPadding(i3, i3, this.f21499f.getExtraSpace() + i3, this.f21499f.getExtraSpace() + i3);
    }

    public void setChat(Chat chat) {
        m23147a(chat, getResources().getDimensionPixelSize(C1947R.dimen.font_body_text));
    }

    public void m23147a(Chat chat, int i) {
        if (chat.m19212d() == ChatState.ERROR) {
            this.f21499f.setVisibility(0);
            this.f21500g.setVisibility(8);
            this.f21499f.setImageDrawable(C1947R.drawable.icn_chatgroup_profile_gray);
        } else if (chat instanceof PeerChat) {
            setupChat((PeerChat) chat);
        } else if (chat instanceof GroupChat) {
            if (((GroupChat) chat).m19541b(UserManager.a().f()) == GroupMemberStatus.NONE) {
                this.f21499f.setVisibility(0);
                this.f21500g.setVisibility(8);
                this.f21499f.setImageDrawable(C1947R.drawable.icn_chatgroup_profile_gray);
            } else {
                m23148a((GroupChat) chat, i);
            }
        }
        this.f21498e.setImageDrawable(C1947R.drawable.chat_selected_circle_checkmark);
    }

    public void setAccount(AccountIcon accountIcon) {
        this.f21499f.setVisibility(0);
        this.f21500g.setVisibility(8);
        this.f21499f.setAccount(accountIcon);
    }

    protected void setupChat(PeerChat peerChat) {
        this.f21499f.setVisibility(0);
        this.f21500g.setVisibility(8);
        this.f21499f.setAccount(peerChat.m19186a(peerChat.mo6341f()));
    }

    protected void m23148a(GroupChat groupChat, int i) {
        int i2 = 8;
        List b = ChatUtils.b(groupChat.m19220h());
        int size = b.size();
        if (size == 0) {
            this.f21499f.setVisibility(0);
            this.f21500g.setVisibility(8);
            this.f21499f.setImageDrawable(C1947R.drawable.icn_group);
        } else if (size == 1) {
            this.f21499f.setVisibility(0);
            this.f21500g.setVisibility(8);
            this.f21499f.setAccount((AccountIcon) b.get(0));
        } else {
            this.f21499f.setVisibility(4);
            this.f21500g.setVisibility(0);
            ViewGroup viewGroup = this.f21501h;
            if (size > 2) {
                i2 = 0;
            }
            viewGroup.setVisibility(i2);
            List a = ChatUtils.a(groupChat);
            int i3 = 0;
            while (i3 < this.f21502i.size()) {
                ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView) this.f21502i.get(i3);
                if (i3 != this.f21502i.size() - 1 || size <= 4) {
                    AccountIcon accountIcon;
                    if (i3 < size) {
                        accountIcon = (AccountIcon) a.get(i3);
                    } else {
                        accountIcon = null;
                    }
                    if (accountIcon != null) {
                        profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                        profileImageWithVIPBadgeAndPendingGreyDotView.m23386a();
                        profileImageWithVIPBadgeAndPendingGreyDotView.setAccount(accountIcon);
                        if (groupChat.m19541b(accountIcon.accountId) == GroupMemberStatus.PENDING) {
                            profileImageWithVIPBadgeAndPendingGreyDotView.m23402a(true);
                        } else {
                            profileImageWithVIPBadgeAndPendingGreyDotView.m23402a(false);
                        }
                    } else {
                        profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(4);
                    }
                    i3++;
                } else {
                    profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                    profileImageWithVIPBadgeAndPendingGreyDotView.m23402a(false);
                    profileImageWithVIPBadgeAndPendingGreyDotView.setTextStyle(C1947R.style.Sing.Text.Share.Icon);
                    profileImageWithVIPBadgeAndPendingGreyDotView.m23393a(size - 3, false, null, C1947R.drawable.solid_grey_circle, C1947R.drawable.solid_grey_circle_with_border);
                    return;
                }
            }
        }
    }
}
