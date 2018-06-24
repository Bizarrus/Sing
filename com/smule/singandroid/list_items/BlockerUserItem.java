/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.PeerChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.BlockedUsersFragment;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.BlockerUserItem_;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.ChatUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class BlockerUserItem
extends LinearLayout {
    @ViewById
    protected TextView a;
    @ViewById
    protected ProfileImageWithVIPBadge b;
    @ViewById
    protected Button c;
    @ViewById
    protected View d;
    protected BlockButtonClickCallback e;
    boolean f = true;
    int g;
    private AccountIcon h;
    private BlockedUsersFragment i;

    public BlockerUserItem(Context context) {
        super(context);
    }

    static /* synthetic */ AccountIcon a(BlockerUserItem blockerUserItem) {
        return blockerUserItem.h;
    }

    public static BlockerUserItem a(Context object, BlockButtonClickCallback blockButtonClickCallback, BlockedUsersFragment blockedUsersFragment) {
        object = BlockerUserItem_.a((Context)object);
        object.e = blockButtonClickCallback;
        object.i = blockedUsersFragment;
        return object;
    }

    @AfterViews
    protected void a() {
        this.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                boolean bl = false;
                BlockerUserItem.this.c.setText((CharSequence)"");
                BlockerUserItem.this.d.setVisibility(0);
                object = SingApplication.k();
                long l = BlockerUserItem.a((BlockerUserItem)BlockerUserItem.this).accountId;
                if (!BlockerUserItem.this.f) {
                    bl = true;
                }
                object.a(l, bl, new Completion<ChatStatus>(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void a(ChatStatus object) {
                        if (object == ChatStatus.a) {
                            BlockerUserItem.this.e.a(BlockerUserItem.this, BlockerUserItem.a((BlockerUserItem)BlockerUserItem.this).accountId, BlockerUserItem.this.f, BlockerUserItem.this.g);
                            int n = BlockerUserItem.this.f ? 2131296616 : 2131296472;
                            object = BlockerUserItem.this;
                            boolean bl = !BlockerUserItem.this.f;
                            object.f = bl;
                            com.smule.android.utils.Toaster.a(BlockerUserItem.this.getContext(), n, Toaster.b);
                            object = new ArrayList<Chat>();
                            object.addAll(SingApplication.k().a(Chat.Bucket.a));
                            object.addAll(SingApplication.k().a(Chat.Bucket.b));
                            object = object.iterator();
                            while (object.hasNext()) {
                                Chat chat = (Chat)object.next();
                                if (!(chat instanceof PeerChat) || chat.f() != BlockerUserItem.a((BlockerUserItem)BlockerUserItem.this).accountId) continue;
                                SingApplication.k().a(chat, (Completion<ChatStatus>)null);
                            }
                            return;
                        } else {
                            int n = BlockerUserItem.this.f ? 2131296509 : 2131296496;
                            ChatUtils.a(BlockerUserItem.this.getContext(), n, (ChatStatus)((Object)object));
                        }
                    }
                });
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(AccountIcon accountIcon, boolean bl, int n) {
        this.g = n;
        if (accountIcon == null) {
            this.a.setText((CharSequence)"");
            this.c.setText((CharSequence)"");
            this.d.setVisibility(0);
            return;
        }
        if (this.h == null || this.h.accountId != accountIcon.accountId) {
            if (this.a != null) {
                this.a.setText((CharSequence)accountIcon.handle);
            }
            if (this.b != null) {
                this.b.a((BaseFragment)this.i, accountIcon);
            }
            this.h = accountIcon;
        }
        accountIcon = this.c;
        n = bl ? 2131296614 : 2131296467;
        accountIcon.setText(n);
        this.d.setVisibility(8);
        this.f = bl;
    }

    @Click
    protected void b() {
        ProfileFragment profileFragment = ProfileFragment.a(this.h);
        this.i.a(profileFragment);
    }

    public static interface BlockButtonClickCallback {
        public void a(BlockerUserItem var1, Long var2, boolean var3, int var4);
    }

}

