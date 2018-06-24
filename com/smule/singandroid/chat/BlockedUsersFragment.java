package com.smule.singandroid.chat;

import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BlockedUserListItemContainer;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.datasource.BlockedUsersDataSource;
import com.smule.singandroid.list_items.BlockerUserItem;
import com.smule.singandroid.list_items.BlockerUserItem.BlockButtonClickCallback;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class BlockedUsersFragment extends BaseFragment {
    public static final String f20713e = BlockedUsersFragment.class.getName();
    @ViewById
    protected MagicListView f20714f;
    @ViewById
    protected View f20715g;
    protected List<Long> f20716h;

    protected class BlockedUsersAdapter extends MagicAdapter {
        final /* synthetic */ BlockedUsersFragment f20712c;

        class C42431 implements BlockButtonClickCallback {
            final /* synthetic */ BlockedUsersAdapter f20711a;

            C42431(BlockedUsersAdapter blockedUsersAdapter) {
                this.f20711a = blockedUsersAdapter;
            }

            public void mo6726a(BlockerUserItem blockerUserItem, Long l, boolean z, int i) {
                Log.a(BlockedUsersFragment.f20713e, z ? "Unblocked " : "Blocked " + l);
                ((BlockedUserListItemContainer) this.f20711a.m18026a().m17641a(i)).m19878a(!z);
                this.f20711a.m18026a().m17666p();
            }
        }

        public BlockedUsersAdapter(BlockedUsersFragment blockedUsersFragment, MagicDataSource magicDataSource) {
            this.f20712c = blockedUsersFragment;
            super(magicDataSource);
        }

        public View mo6418a(ViewGroup viewGroup, int i) {
            return BlockerUserItem.m24236a(this.f20712c.getActivity(), new C42431(this), this.f20712c);
        }

        public void mo6419a(View view, int i, int i2) {
            BlockedUserListItemContainer blockedUserListItemContainer = (BlockedUserListItemContainer) m18027a(i);
            ((BlockerUserItem) view).m24238a(blockedUserListItemContainer.m19877a(), blockedUserListItemContainer.m19879b(), i);
        }
    }

    public String mo6383s() {
        return f20713e;
    }

    public static BlockedUsersFragment m22319a() {
        return BlockedUsersFragment_.m22323A().m22322a();
    }

    @AfterViews
    protected void m22321z() {
        m19850c((int) C1947R.string.chat_blocked_list);
        if (this.f20716h != null) {
            this.f20714f.setMagicAdapter(new BlockedUsersAdapter(this, new BlockedUsersDataSource(getActivity(), this.f20716h)));
            return;
        }
        this.f20716h = SingApplication.j().h();
        MagicAdapter blockedUsersAdapter = new BlockedUsersAdapter(this, new BlockedUsersDataSource(getActivity(), this.f20716h));
        this.f20714f.setMagicAdapter(blockedUsersAdapter);
        blockedUsersAdapter.m18050d((int) C1947R.layout.list_loading_view);
        blockedUsersAdapter.m18057g(C1947R.layout.blocked_users_empty_layout);
        blockedUsersAdapter.m18055f(C1947R.layout.blocked_users_network_layout);
    }
}
