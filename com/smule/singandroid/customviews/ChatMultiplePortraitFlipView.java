/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.TargetApi
 *  android.content.Context
 *  android.content.res.Resources
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.animation.Animation
 *  android.widget.RelativeLayout
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.annotations.ViewsById
 */
package com.smule.singandroid.customviews;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.FlipAnimation;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import com.smule.singandroid.utils.ChatUtils;
import java.util.List;
import java.util.Map;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class ChatMultiplePortraitFlipView
extends RelativeLayout {
    public static final String a = ChatMultiplePortraitFlipView.class.getName();
    @ViewById
    protected ViewGroup b;
    @ViewById
    protected ViewGroup c;
    @ViewById
    protected ViewGroup d;
    @ViewById
    protected ProfileImageWithVIPBadge e;
    @ViewById
    protected ProfileImageWithVIPBadge f;
    @ViewById
    protected ViewGroup g;
    @ViewById
    protected ViewGroup h;
    @ViewsById
    protected List<ProfileImageWithVIPBadgeAndPendingGreyDotView> i;
    private boolean j = true;
    private FlipAnimation k;

    public ChatMultiplePortraitFlipView(Context context) {
        this(context, null);
    }

    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    @TargetApi(value=21)
    public ChatMultiplePortraitFlipView(Context context, AttributeSet attributeSet, int n, int n2) {
        super(context, attributeSet, n, n2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a() {
        synchronized (this) {
            if (this.k != null) {
                this.k.cancel();
            }
            this.k = new FlipAnimation((View)this.c, (View)this.d, this.getResources().getInteger(2131623950));
            if (!this.j) {
                this.k.a();
            }
            this.b.startAnimation((Animation)this.k);
            boolean bl = !this.j;
            this.j = bl;
            return;
        }
    }

    public void a(int n, int n2, int n3) {
        this.f.a(n);
        this.f.setLoadImageWithBorder(false);
        this.e.a(n);
        for (ProfileImageWithVIPBadge profileImageWithVIPBadge : this.i) {
            profileImageWithVIPBadge.a(n2);
            profileImageWithVIPBadge.setLoadImageWithBorder(false);
        }
        this.g.setPadding(n3, n3, this.f.getExtraSpace() + n3, this.f.getExtraSpace() + n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Chat chat, int n) {
        if (chat.d() == Chat.ChatState.c) {
            this.f.setVisibility(0);
            this.g.setVisibility(8);
            this.f.setImageDrawable(2130837885);
        } else if (chat instanceof PeerChat) {
            this.setupChat((PeerChat)chat);
        } else if (chat instanceof GroupChat) {
            if (((GroupChat)chat).b(UserManager.a().f()) == GroupMemberStatus.a) {
                this.f.setVisibility(0);
                this.g.setVisibility(8);
                this.f.setImageDrawable(2130837885);
            } else {
                this.a((GroupChat)chat, n);
            }
        }
        this.e.setImageDrawable(2130837690);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(GroupChat groupChat, int n) {
        n = 8;
        Object object = ChatUtils.b(groupChat.h());
        int n2 = object.size();
        if (n2 == 0) {
            this.f.setVisibility(0);
            this.g.setVisibility(8);
            this.f.setImageDrawable(2130837932);
            return;
        }
        if (n2 == 1) {
            this.f.setVisibility(0);
            this.g.setVisibility(8);
            this.f.setAccount((AccountIcon)object.get(0));
            return;
        }
        this.f.setVisibility(4);
        this.g.setVisibility(0);
        object = this.h;
        if (n2 > 2) {
            n = 0;
        }
        object.setVisibility(n);
        List<AccountIcon> list = ChatUtils.a(groupChat);
        n = 0;
        while (n < this.i.size()) {
            ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = this.i.get(n);
            if (n == this.i.size() - 1 && n2 > 4) {
                profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                profileImageWithVIPBadgeAndPendingGreyDotView.b(false);
                profileImageWithVIPBadgeAndPendingGreyDotView.setTextStyle(2131493441);
                profileImageWithVIPBadgeAndPendingGreyDotView.a(n2 - 3, false, null, 2130838207, 2130838208);
                return;
            }
            object = n < n2 ? list.get(n) : null;
            if (object != null) {
                profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                profileImageWithVIPBadgeAndPendingGreyDotView.a();
                profileImageWithVIPBadgeAndPendingGreyDotView.setAccount((AccountIcon)object);
                if (groupChat.b(object.accountId) == GroupMemberStatus.b) {
                    profileImageWithVIPBadgeAndPendingGreyDotView.b(true);
                } else {
                    profileImageWithVIPBadgeAndPendingGreyDotView.b(false);
                }
            } else {
                profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(4);
            }
            ++n;
        }
    }

    public void setAccount(AccountIcon accountIcon) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.f.setAccount(accountIcon);
    }

    public void setChat(Chat chat) {
        this.a(chat, this.getResources().getDimensionPixelSize(2131427559));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSide(boolean bl) {
        if (this.k != null) {
            this.k.cancel();
        }
        if (bl) {
            this.c.setVisibility(0);
            this.d.setVisibility(8);
        } else {
            this.c.setVisibility(8);
            this.d.setVisibility(0);
        }
        this.j = bl;
    }

    protected void setupChat(PeerChat peerChat) {
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.f.setAccount(peerChat.a(peerChat.f()));
    }
}

