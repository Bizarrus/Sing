/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  org.androidannotations.annotations.EFragment
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.chat.ChatAddMembersFragment_;
import com.smule.singandroid.chat.ChatDetailsFragment;
import com.smule.singandroid.chat.EditGroupNameFragment;
import com.smule.singandroid.chat.NewChatFragment;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.utils.ChatUtils;
import java.util.List;
import java.util.Map;
import org.androidannotations.annotations.EFragment;

@EFragment
public class ChatAddMembersFragment
extends NewChatFragment {
    protected ChatDetailsFragment g;

    public static ChatAddMembersFragment a(ChatDetailsFragment chatDetailsFragment) {
        ChatAddMembersFragment chatAddMembersFragment = ChatAddMembersFragment_.H().a();
        chatAddMembersFragment.g = chatDetailsFragment;
        return chatAddMembersFragment;
    }

    @Override
    protected void F() {
        this.i.a(this.g.t());
    }

    @Override
    protected boolean G() {
        if (this.i.getSelectedCount() + 1 <= this.p) {
            return true;
        }
        return false;
    }

    @Override
    protected int a() {
        return this.g.t().h().size();
    }

    @Override
    protected void a(Menu menu2, MenuInflater menuInflater) {
        menuInflater.inflate(2131820554, menu2);
        menu2 = menu2.findItem(2131756845);
        menuInflater = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903071, null, false);
        ((ImageView)menuInflater.findViewById(2131755235)).setImageResource(2130837888);
        menu2.setActionView((View)menuInflater);
    }

    @Override
    public boolean a(AccountIcon accountIcon) {
        if (this.G()) {
            return true;
        }
        this.b(this.getString(2131296568, new Object[]{this.p}));
        return false;
    }

    @Override
    protected void d(int n) {
        if (n == 0) {
            this.c(2131296464);
            return;
        }
        this.a((CharSequence)this.getResources().getString(2131296584, new Object[]{n, this.p}));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem list) {
        if (list.getItemId() != 2131756845) return super.onOptionsItemSelected((MenuItem)list);
        if (this.q) {
            return super.onOptionsItemSelected((MenuItem)list);
        }
        list = this.i.getSelectedAccounts();
        if (this.g != null && this.g.t() instanceof PeerChat) {
            this.t();
            return true;
        }
        if (list.size() <= 0) return true;
        if (this.g == null) return true;
        if (!(this.g.t() instanceof GroupChat)) return true;
        this.q = true;
        final BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)this.getActivity(), this.getResources().getString(2131296526), ((GroupChat)this.g.t()).S());
        busyScreenDialog.show();
        this.g.a(list, new ChatAddMembersCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatStatus chatStatus) {
                block5 : {
                    block4 : {
                        busyScreenDialog.dismiss();
                        if (!ChatAddMembersFragment.this.isAdded()) break block4;
                        ChatAddMembersFragment.this.q = false;
                        if (!(ChatAddMembersFragment.this.g.t() instanceof PeerChat)) break block5;
                    }
                    return;
                }
                if (chatStatus == ChatStatus.a) {
                    ChatAddMembersFragment.this.getActivity().getFragmentManager().popBackStack();
                    return;
                }
                ChatUtils.a((Context)ChatAddMembersFragment.this.getActivity(), 2131296495, chatStatus);
            }
        });
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.c(2131296464);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void t() {
        List<AccountIcon> list;
        block6 : {
            block5 : {
                if (this.q) break block5;
                this.q = true;
                list = this.i.getSelectedAccounts();
                int n = list.size();
                if (n == 0) {
                    this.b(2131296969);
                    return;
                }
                if (n > 0) break block6;
            }
            return;
        }
        this.q = false;
        if (this.J()) {
            list.add(((PeerChat)this.g.t()).R());
            this.a(EditGroupNameFragment.a(null, list, this.n));
            return;
        }
        ChatUtils.a((BaseFragment)this, list);
    }

    protected static interface ChatAddMembersCallback {
        public void a(ChatStatus var1);
    }

}

