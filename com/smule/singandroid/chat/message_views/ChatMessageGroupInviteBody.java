/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.annotations.ViewsById
 */
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
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.List;
import java.util.Map;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class ChatMessageGroupInviteBody
extends FrameLayout
implements ChatMessageBaseListItem.ChatMessageBodyViewInterface {
    public static final String a = ChatMessageGroupInviteBody.class.getName();
    @ViewById
    TextView b;
    @ViewsById
    List<ProfileImageWithVIPBadgeAndPendingGreyDotView> c;
    @ViewById
    Button d;
    @ViewById
    LinearLayout e;
    @ViewById
    ImageView f;
    @ViewById
    View g;
    ChatMessageBaseListItem.ChatMessageViewListener h;
    GroupInvitationChatMessage i;
    Chat j;
    boolean k;
    boolean l = false;
    ChatAnalytics.GroupInviteActionType m;
    private boolean n = false;

    public ChatMessageGroupInviteBody(Context context) {
        super(context);
    }

    public ChatMessageGroupInviteBody(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChatMessageGroupInviteBody(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(List<AccountIcon> list) {
        if (list == null || list.isEmpty() || this.i.b(UserManager.a().f()) == GroupMemberStatus.a) {
            this.c();
            return;
        }
        this.d();
        int n = 0;
        int n2 = 0;
        while (n < this.c.size()) {
            ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = this.c.get(n);
            if (n < list.size()) {
                if (n == this.c.size() - 1 && this.c.size() < list.size()) {
                    profileImageWithVIPBadgeAndPendingGreyDotView.a(list.size() - n2, false, null, 2130838207, 2130838208);
                    profileImageWithVIPBadgeAndPendingGreyDotView.b(false);
                } else {
                    AccountIcon accountIcon = list.get(n);
                    if (MiscUtils.a((String)accountIcon.picUrl)) {
                        profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(8);
                    } else {
                        ++n2;
                        profileImageWithVIPBadgeAndPendingGreyDotView.setProfilePicUrl(accountIcon.picUrl);
                        profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(0);
                        boolean bl = this.i.b(accountIcon.accountId) == GroupMemberStatus.b;
                        profileImageWithVIPBadgeAndPendingGreyDotView.b(bl);
                    }
                }
            } else {
                profileImageWithVIPBadgeAndPendingGreyDotView.setVisibility(8);
            }
            ++n;
        }
    }

    private void c() {
        this.e.setVisibility(8);
    }

    private void d() {
        this.e.setVisibility(0);
    }

    protected void a() {
        if (this.i.q() == null) {
            this.c();
            return;
        }
        this.a(ChatUtils.a(this.i.q(), this.i));
    }

    /*
     * Exception decompiling
     */
    public void a(ChatMessage var1_1, boolean var2_2, Chat var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Extractable last case doesn't follow previous
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:486)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:423)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doJar(Main.java:134)
        // org.benf.cfr.reader.Main.main(Main.java:189)
        throw new IllegalStateException("Decompilation failed");
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(ChatAnalytics.GroupInviteActionType groupInviteActionType) {
        this.m = groupInviteActionType;
        int n = 2131296698;
        this.l = false;
        int n2 = n;
        if (groupInviteActionType != null) {
            switch (.b[groupInviteActionType.ordinal()]) {
                default: {
                    n2 = n;
                    break;
                }
                case 1: {
                    n2 = 2131296544;
                    this.l = false;
                    this.d.setEnabled(true);
                    break;
                }
                case 2: {
                    n2 = 2131296545;
                    this.l = false;
                    this.d.setEnabled(true);
                    break;
                }
                case 3: {
                    n2 = 2131296547;
                    this.l = true;
                    this.d.setEnabled(false);
                    break;
                }
                case 4: {
                    n2 = 2131296546;
                    this.l = true;
                    this.d.setEnabled(true);
                }
            }
        }
        this.d.setText(n2);
        groupInviteActionType = this.f;
        n2 = this.l ? 0 : 8;
        groupInviteActionType.setVisibility(n2);
    }

    public void a(ChatMessageBaseListItem.ChatMessageViewListener chatMessageViewListener) {
        this.h = chatMessageViewListener;
    }

    @Click
    protected void b() {
        if (this.n) {
            if (this.j != null) {
                this.j.b(this.i);
            }
            return;
        }
        boolean bl = FollowManager.a().a(this.i.b());
        ChatAnalytics.a(this.i.b(), bl, this.m, this.i.o());
        this.h.a(this.i);
        this.a(this.i, this.k, this.j);
    }

}

