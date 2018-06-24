/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.ViewGroup
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BlockedUserListItemContainer;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.BlockedUsersFragment_;
import com.smule.singandroid.datasource.BlockedUsersDataSource;
import com.smule.singandroid.list_items.BlockerUserItem;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class BlockedUsersFragment
extends BaseFragment {
    public static final String g = BlockedUsersFragment.class.getName();
    @ViewById
    protected MagicListView h;
    @ViewById
    protected View i;
    protected List<Long> j;

    public static BlockedUsersFragment a() {
        return BlockedUsersFragment_.F().a();
    }

    @AfterViews
    protected void t() {
        this.c(2131296470);
        if (this.j != null) {
            BlockedUsersAdapter blockedUsersAdapter = new BlockedUsersAdapter(new BlockedUsersDataSource((Context)this.getActivity(), this.j));
            this.h.setMagicAdapter(blockedUsersAdapter);
            return;
        }
        this.j = SingApplication.k().h();
        BlockedUsersAdapter blockedUsersAdapter = new BlockedUsersAdapter(new BlockedUsersDataSource((Context)this.getActivity(), this.j));
        this.h.setMagicAdapter(blockedUsersAdapter);
        blockedUsersAdapter.d(2130903273);
        blockedUsersAdapter.g(2130903087);
        blockedUsersAdapter.f(2130903089);
    }

    @Override
    public String x() {
        return g;
    }

    protected class BlockedUsersAdapter
    extends MagicAdapter {
        public BlockedUsersAdapter(MagicDataSource magicDataSource) {
            super(magicDataSource);
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return BlockerUserItem.a((Context)BlockedUsersFragment.this.getActivity(), new BlockerUserItem.BlockButtonClickCallback(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void a(BlockerUserItem object, Long l, boolean bl, int n) {
                    String string2 = BlockedUsersFragment.g;
                    object = bl ? "Unblocked " : "Blocked " + l;
                    Log.a(string2, (String)object);
                    object = (BlockedUserListItemContainer)BlockedUsersAdapter.this.a().a(n);
                    bl = !bl;
                    object.a(bl);
                    BlockedUsersAdapter.this.a().p();
                }
            }, BlockedUsersFragment.this);
        }

        @Override
        public void a(View object, int n, int n2) {
            object = (BlockerUserItem)((Object)object);
            BlockedUserListItemContainer blockedUserListItemContainer = (BlockedUserListItemContainer)this.a(n);
            object.a(blockedUserListItemContainer.a(), blockedUserListItemContainer.b(), n);
        }

    }

}

