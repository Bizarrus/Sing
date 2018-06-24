package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.BlockedUsersFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.utils.ChatUtils;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class BlockerUserItem extends LinearLayout {
    @ViewById
    protected TextView f22790a;
    @ViewById
    protected ProfileImageWithVIPBadge f22791b;
    @ViewById
    protected Button f22792c;
    @ViewById
    protected View f22793d;
    protected BlockButtonClickCallback f22794e;
    boolean f22795f = true;
    int f22796g;
    private AccountIcon f22797h;
    private BlockedUsersFragment f22798i;

    public interface BlockButtonClickCallback {
        void mo6726a(BlockerUserItem blockerUserItem, Long l, boolean z, int i);
    }

    class C46221 implements OnClickListener {
        final /* synthetic */ BlockerUserItem f22789a;

        class C46211 implements Completion<ChatStatus> {
            final /* synthetic */ C46221 f22788a;

            C46211(C46221 c46221) {
                this.f22788a = c46221;
            }

            public void m24233a(ChatStatus chatStatus) {
                if (chatStatus == ChatStatus.OK) {
                    this.f22788a.f22789a.f22794e.mo6726a(this.f22788a.f22789a, Long.valueOf(this.f22788a.f22789a.f22797h.accountId), this.f22788a.f22789a.f22795f, this.f22788a.f22789a.f22796g);
                    int i = this.f22788a.f22789a.f22795f ? C1947R.string.chat_unblocked_user : C1947R.string.chat_blocked_user;
                    this.f22788a.f22789a.f22795f = !this.f22788a.f22789a.f22795f;
                    Toaster.a(this.f22788a.f22789a.getContext(), i, Toaster$Duration.LONG);
                    List<Chat> arrayList = new ArrayList();
                    arrayList.addAll(SingApplication.j().a(Bucket.INBOX));
                    arrayList.addAll(SingApplication.j().a(Bucket.OTHER));
                    for (Chat chat : arrayList) {
                        if ((chat instanceof PeerChat) && chat.mo6341f() == this.f22788a.f22789a.f22797h.accountId) {
                            SingApplication.j().a(chat, null);
                        }
                    }
                    return;
                }
                ChatUtils.a(this.f22788a.f22789a.getContext(), this.f22788a.f22789a.f22795f ? C1947R.string.chat_error_unblock : C1947R.string.chat_error_block, chatStatus);
            }
        }

        C46221(BlockerUserItem blockerUserItem) {
            this.f22789a = blockerUserItem;
        }

        public void onClick(View view) {
            boolean z = false;
            this.f22789a.f22792c.setText("");
            this.f22789a.f22793d.setVisibility(0);
            ChatManager j = SingApplication.j();
            long j2 = this.f22789a.f22797h.accountId;
            if (!this.f22789a.f22795f) {
                z = true;
            }
            j.a(j2, z, new C46211(this));
        }
    }

    public BlockerUserItem(Context context) {
        super(context);
    }

    public static BlockerUserItem m24236a(Context context, BlockButtonClickCallback blockButtonClickCallback, BlockedUsersFragment blockedUsersFragment) {
        BlockerUserItem a = BlockerUserItem_.m24240a(context);
        a.f22794e = blockButtonClickCallback;
        a.f22798i = blockedUsersFragment;
        return a;
    }

    public void m24238a(AccountIcon accountIcon, boolean z, int i) {
        this.f22796g = i;
        if (accountIcon == null) {
            this.f22790a.setText("");
            this.f22792c.setText("");
            this.f22793d.setVisibility(0);
            return;
        }
        if (this.f22797h == null || this.f22797h.accountId != accountIcon.accountId) {
            if (this.f22790a != null) {
                this.f22790a.setText(accountIcon.handle);
            }
            if (this.f22791b != null) {
                this.f22791b.m23396a(this.f22798i, accountIcon);
            }
            this.f22797h = accountIcon;
        }
        this.f22792c.setText(z ? C1947R.string.chat_unblock : C1947R.string.chat_block);
        this.f22793d.setVisibility(8);
        this.f22795f = z;
    }

    @AfterViews
    protected void m24237a() {
        this.f22792c.setOnClickListener(new C46221(this));
    }

    @Click
    protected void m24239b() {
        this.f22798i.mo6487a(ProfileFragment.m21036a(this.f22797h));
    }
}
