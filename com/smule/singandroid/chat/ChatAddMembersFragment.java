package com.smule.singandroid.chat;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.utils.ChatUtils;
import java.util.List;
import org.androidannotations.annotations.EFragment;

@EFragment
public class ChatAddMembersFragment extends NewChatFragment {
    protected ChatDetailsFragment f20734e;

    protected interface ChatAddMembersCallback {
        void mo6727a(ChatStatus chatStatus);
    }

    public static ChatAddMembersFragment m22353a(ChatDetailsFragment chatDetailsFragment) {
        ChatAddMembersFragment a = ChatAddMembersFragment_.m22362C().m22361a();
        a.f20734e = chatDetailsFragment;
        return a;
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.chat_add_members);
    }

    protected void mo6737a(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(C1947R.menu.new_chat_menu, menu);
        MenuItem findItem = menu.findItem(C1947R.id.action_next);
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1947R.layout.action_bar_icon_button, null, false);
        ((ImageView) inflate.findViewById(C1947R.id.button_next)).setImageResource(C1947R.drawable.icn_checkmark_white);
        findItem.setActionView(inflate);
    }

    protected int mo6736a() {
        return this.f20734e.m22506z().m19220h().size();
    }

    protected void mo6740z() {
        if (!this.o) {
            this.o = true;
            List selectedAccounts = this.g.getSelectedAccounts();
            int size = selectedAccounts.size();
            if (size == 0) {
                m19846b((int) C1947R.string.new_chat_no_users_selected);
            } else if (size > 0) {
                this.o = false;
                if (m22342E()) {
                    selectedAccounts.add(((PeerChat) this.f20734e.m22506z()).mo6333R());
                    mo6487a(EditGroupNameFragment.m22708a(null, selectedAccounts, this.l));
                    return;
                }
                ChatUtils.a(this, selectedAccounts);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != C1947R.id.action_next) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (this.o) {
            return super.onOptionsItemSelected(menuItem);
        }
        List selectedAccounts = this.g.getSelectedAccounts();
        if (this.f20734e != null && (this.f20734e.m22506z() instanceof PeerChat)) {
            mo6740z();
        } else if (selectedAccounts.size() > 0 && this.f20734e != null && (this.f20734e.m22506z() instanceof GroupChat)) {
            this.o = true;
            final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(getActivity(), getResources().getString(C1947R.string.chat_getting_ready_group_busy_message), ((GroupChat) this.f20734e.m22506z()).mo6334S());
            busyScreenDialog.show();
            this.f20734e.m22504a(selectedAccounts, new ChatAddMembersCallback(this) {
                final /* synthetic */ ChatAddMembersFragment f20720b;

                public void mo6727a(ChatStatus chatStatus) {
                    busyScreenDialog.dismiss();
                    if (this.f20720b.isAdded()) {
                        this.f20720b.o = false;
                        if (!(this.f20720b.f20734e.m22506z() instanceof PeerChat)) {
                            if (chatStatus == ChatStatus.OK) {
                                this.f20720b.getActivity().getFragmentManager().popBackStack();
                            } else {
                                ChatUtils.a(this.f20720b.getActivity(), C1947R.string.chat_error_add_member, chatStatus);
                            }
                        }
                    }
                }
            });
        }
        return true;
    }

    protected void mo6734A() {
        this.g.m22881a(this.f20734e.m22506z());
    }

    public boolean mo6728a(AccountIcon accountIcon) {
        if (mo6735B()) {
            return true;
        }
        m19849b(getString(C1947R.string.chat_max_members_selected, new Object[]{Integer.valueOf(this.n)}));
        return false;
    }

    protected boolean mo6735B() {
        return this.g.getSelectedCount() + 1 <= this.n;
    }

    protected void mo6738d(int i) {
        if (i == 0) {
            m19850c((int) C1947R.string.chat_add_members);
            return;
        }
        mo6861a(getResources().getString(C1947R.string.chat_selected_chats_out_of_max, new Object[]{Integer.valueOf(i), Integer.valueOf(this.n)}));
    }
}
