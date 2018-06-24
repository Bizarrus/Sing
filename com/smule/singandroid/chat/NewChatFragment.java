package com.smule.singandroid.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ChatCallback;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.chat.SelectUsersAndChatsView.SelectUsersAndChatsListener;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.io.Serializable;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NewChatFragment extends ChatActivatorFragment implements SelectUsersAndChatsListener {
    public static final String f20724f = NewChatFragment.class.getName();
    @ViewById
    protected SelectUsersAndChatsView f20725g;
    @ViewById
    protected LinearLayout f20726h;
    protected List<Integer> f20727i;
    protected List<AccountIcon> f20728j;
    protected ChatManager f20729k;
    protected OnChatCreatedListener f20730l;
    protected List<AccountIcon> f20731m;
    protected int f20732n;
    protected boolean f20733o;

    public interface OnChatCreatedListener {
        void mo6760a(Chat chat);
    }

    public static NewChatFragment m22338a(OnChatCreatedListener onChatCreatedListener) {
        NewChatFragment a = NewChatFragment_.m22849F().m22848a();
        a.f20730l = onChatCreatedListener;
        return a;
    }

    protected int mo6736a() {
        return 1;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selected_positions", (Serializable) this.f20727i);
        bundle.putSerializable("selected_accountIcons", (Serializable) this.f20728j);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f20732n = SingServerValues.m21669G() - mo6736a();
        this.f20729k = SingApplication.j();
        m19831a(ActionBarHighlightMode.NEVER);
        if (bundle != null) {
            this.f20727i = (List) bundle.getSerializable("selected_positions");
            this.f20728j = (List) bundle.getSerializable("selected_accountIcons");
        }
    }

    @AfterViews
    public void m22341D() {
        m19842a(true);
        getActivity().getWindow().setSoftInputMode(16);
        if (this.f20731m != null) {
            this.f20725g.setSelectedAccounts(this.f20731m);
            this.f20731m = null;
        }
        this.f20725g.setSearchClkContext(SearchClkContext.CHATSEARCH);
        this.f20725g.setSelectUsersAndChatsListener(this);
        mo6734A();
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.create_chat_title);
        if (this.f20725g.getSelectedCount() == 0) {
            m19845b().findItem(C1947R.id.action_next).setEnabled(false);
        } else {
            m19845b().findItem(C1947R.id.action_next).setEnabled(true);
        }
    }

    public void onResume() {
        super.onResume();
        mo6738d(this.f20725g.getSelectedCount());
        if (this.f20727i != null) {
            this.f20725g.setSelectedPositions(this.f20727i);
        }
        if (this.f20728j != null) {
            this.f20725g.setSelectedAccountIcons(this.f20728j);
        }
        p_();
    }

    public void onPause() {
        super.onPause();
        this.f20727i = this.f20725g.getSelectedPositions();
        this.f20728j = this.f20725g.getSelectedAccountIcons();
    }

    public void onStop() {
        super.onStop();
        MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) getActivity();
        if (mediaPlayingActivity != null) {
            mediaPlayingActivity.B().getToolbarView().setDoneButtonOnClickListener(null);
        }
        MiscUtils.m25891a(getActivity(), true);
    }

    protected void mo6420v() {
        ChatAnalytics.m22376a();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        mo6737a(menu, menuInflater);
    }

    protected void mo6737a(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(C1947R.menu.new_chat_menu, menu);
        MenuItem findItem = menu.findItem(C1947R.id.action_next);
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1947R.layout.action_bar_icon_button, null, false);
        ((ImageView) inflate.findViewById(C1947R.id.button_next)).setImageResource(C1947R.drawable.icn_arrow_forward_white);
        findItem.setActionView(inflate);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != C1947R.id.action_next) {
            return super.onOptionsItemSelected(menuItem);
        }
        mo6740z();
        return true;
    }

    public void o_() {
        mo6487a(FindFriendsWithoutHeaderFragment.m22730B());
    }

    public void p_() {
        if (this.f20725g.getSelectedCount() == 0) {
            m19845b().findItem(C1947R.id.action_next).setEnabled(false);
        } else if (this.f20725g.getSelectedCount() == 1) {
            m19845b().findItem(C1947R.id.action_next).setEnabled(true);
        }
        mo6738d(this.f20725g.getSelectedCount());
        m19864o();
    }

    public boolean mo6728a(AccountIcon accountIcon) {
        if (mo6735B()) {
            return true;
        }
        m19849b(getString(C1947R.string.chat_max_members_selected, new Object[]{Integer.valueOf(this.f20732n)}));
        return false;
    }

    public boolean mo6729a(Chat chat) {
        return false;
    }

    public void q_() {
        if (isAdded()) {
            ((MediaPlayingActivity) getActivity()).S().setVisibility(8);
            ((MediaPlayingActivity) getActivity()).B().setVisibility(8);
            ((MediaPlayingActivity) getActivity()).I();
            this.f20725g.m22884a(true);
        }
    }

    public void r_() {
        if (isAdded()) {
            ((MediaPlayingActivity) getActivity()).S().setVisibility(0);
            ((MediaPlayingActivity) getActivity()).B().setVisibility(0);
            ((MediaPlayingActivity) getActivity()).I();
            this.f20725g.m22884a(false);
        }
    }

    protected void mo6734A() {
        this.f20725g.m22881a(null);
    }

    protected void mo6738d(int i) {
        if (i == 0) {
            m19850c((int) C1947R.string.create_chat_title);
            return;
        }
        mo6861a(getResources().getString(C1947R.string.message_center_selected_chats, new Object[]{Integer.valueOf(i)}));
    }

    protected void mo6740z() {
        if (!this.f20733o) {
            this.f20733o = true;
            List selectedAccounts = this.f20725g.getSelectedAccounts();
            int size = selectedAccounts.size();
            ChatManager j = SingApplication.j();
            if (size == 0) {
                m19846b((int) C1947R.string.new_chat_no_users_selected);
            } else if (size > 1) {
                this.f20733o = false;
                if (m22342E()) {
                    mo6487a(EditGroupNameFragment.m22708a(null, selectedAccounts, this.f20730l));
                } else {
                    ChatUtils.a(this, selectedAccounts);
                }
            } else {
                final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(getActivity(), (int) C1947R.string.chat_getting_ready);
                busyScreenDialog.show();
                j.a((AccountIcon) selectedAccounts.get(0), new ChatManager$ChatCallback(this) {
                    final /* synthetic */ NewChatFragment f21163b;

                    public void mo6326a(Chat chat, ChatStatus chatStatus) {
                        busyScreenDialog.dismiss();
                        this.f21163b.f20733o = false;
                        if (!this.f21163b.isAdded()) {
                            return;
                        }
                        if (chat != null) {
                            ChatAnalytics.m22383a(chat, null);
                            this.f21163b.m22347b(chat);
                            this.f21163b.getActivity().getFragmentManager().popBackStack(MessageCenterFragment.f21133e, 0);
                            this.f21163b.mo6487a(ChatFragment.m22554b(chat));
                            return;
                        }
                        ChatUtils.a(this.f21163b.getActivity(), C1947R.string.chat_error_creating_group_chat, chatStatus);
                    }
                });
            }
        }
    }

    protected boolean m22342E() {
        int i = 0;
        for (Chat a : SingApplication.j().a(Bucket.INBOX)) {
            int i2;
            if (a.mo6335a() == Type.GROUP) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i = i2 + i;
        }
        if (i < getResources().getInteger(C1947R.integer.chat_max_group_chats)) {
            return true;
        }
        return false;
    }

    protected void m22347b(Chat chat) {
        if (this.f20730l != null) {
            this.f20730l.mo6760a(chat);
        }
    }

    public boolean mo6400c() {
        return this.f20725g.m22893d() || super.mo6400c();
    }

    public String mo6383s() {
        return f20724f;
    }

    protected boolean mo6735B() {
        return this.f20725g.getSelectedCount() + 1 <= this.f20732n;
    }
}
