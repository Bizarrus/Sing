/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.EditGroupNameFragment;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.chat.NewChatFragment_;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NewChatFragment
extends ChatActivatorFragment
implements SelectUsersAndChatsView.SelectUsersAndChatsListener {
    public static final String h = NewChatFragment.class.getName();
    @ViewById
    protected SelectUsersAndChatsView i;
    @ViewById
    protected LinearLayout j;
    protected List<Integer> k;
    protected List<AccountIcon> l;
    protected com.smule.chat.ChatManager m;
    protected OnChatCreatedListener n;
    protected List<AccountIcon> o;
    protected int p;
    protected boolean q;

    public static NewChatFragment a(OnChatCreatedListener onChatCreatedListener) {
        NewChatFragment newChatFragment = NewChatFragment_.K().a();
        newChatFragment.n = onChatCreatedListener;
        return newChatFragment;
    }

    @Override
    protected void A() {
        ChatAnalytics.a();
    }

    protected void F() {
        this.i.a((Chat)null);
    }

    protected boolean G() {
        if (this.i.getSelectedCount() + 1 <= this.p) {
            return true;
        }
        return false;
    }

    @AfterViews
    public void I() {
        this.b_(true);
        this.getActivity().getWindow().setSoftInputMode(16);
        if (this.o != null) {
            this.i.setSelectedAccounts(this.o);
            this.o = null;
        }
        this.i.setSearchClkContext(Analytics.g);
        this.i.setSelectUsersAndChatsListener(this);
        this.F();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean J() {
        Iterator<Chat> iterator = SingApplication.k().a(Chat.Bucket.a).iterator();
        int n = 0;
        while (iterator.hasNext()) {
            int n2 = iterator.next().a() == Chat.Type.b ? 1 : 0;
            n = n2 + n;
        }
        if (n < this.getResources().getInteger(2131623945)) {
            return true;
        }
        return false;
    }

    protected int a() {
        return 1;
    }

    protected void a(Menu menu2, MenuInflater menuInflater) {
        menuInflater.inflate(2131820554, menu2);
        menu2 = menu2.findItem(2131756845);
        menuInflater = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903071, null, false);
        ((ImageView)menuInflater.findViewById(2131755235)).setImageResource(2130837868);
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
    public boolean a(Chat chat) {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b() {
        if (this.i.getSelectedCount() == 0) {
            this.c().findItem(2131756845).setEnabled(false);
        } else if (this.i.getSelectedCount() == 1) {
            this.c().findItem(2131756845).setEnabled(true);
        }
        this.d(this.i.getSelectedCount());
        this.r();
    }

    protected void d(int n) {
        if (n == 0) {
            this.c(2131296735);
            return;
        }
        this.a((CharSequence)this.getResources().getString(2131296955, new Object[]{n}));
    }

    protected void d(Chat chat) {
        if (this.n != null) {
            this.n.a(chat);
        }
    }

    @Override
    public boolean d() {
        if (this.i.d() || super.d()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean e() {
        return false;
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.p = new SingServerValues().L() - this.a();
        this.m = SingApplication.k();
        this.a(BaseFragment.ActionBarHighlightMode.a);
        if (bundle != null) {
            this.k = (List)((Object)bundle.getSerializable("selected_positions"));
            this.l = (List)((Object)bundle.getSerializable("selected_accountIcons"));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        this.a(menu2, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131756845) {
            this.t();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.k = this.i.getSelectedPositions();
        this.l = this.i.getSelectedAccountIcons();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getActivity().getWindow().setSoftInputMode(16);
        this.d(this.i.getSelectedCount());
        if (this.k != null) {
            this.i.setSelectedPositions(this.k);
        }
        if (this.l != null) {
            this.i.setSelectedAccountIcons(this.l);
        }
        this.b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("selected_positions", (Serializable)((Object)this.k));
        bundle.putSerializable("selected_accountIcons", (Serializable)((Object)this.l));
    }

    @Override
    public void onStart() {
        super.onStart();
        this.c(2131296735);
        if (this.i.getSelectedCount() == 0) {
            this.c().findItem(2131756845).setEnabled(false);
            return;
        }
        this.c().findItem(2131756845).setEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)this.getActivity();
        if (mediaPlayingActivity != null) {
            mediaPlayingActivity.U().getToolbarView().setDoneButtonOnClickListener(null);
        }
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
    }

    protected void t() {
        if (this.q) {
            return;
        }
        this.q = true;
        List<AccountIcon> list = this.i.getSelectedAccounts();
        int n = list.size();
        com.smule.chat.ChatManager chatManager = SingApplication.k();
        if (n == 0) {
            this.b(2131296969);
            return;
        }
        if (n > 1) {
            this.q = false;
            if (this.J()) {
                this.a(EditGroupNameFragment.a(null, list, this.n));
                return;
            }
            ChatUtils.a((BaseFragment)this, list);
            return;
        }
        final BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)this.getActivity(), 2131296525);
        busyScreenDialog.show();
        chatManager.a(list.get(0), new ChatManager(){

            @Override
            public void a(Chat chat, ChatStatus chatStatus) {
                busyScreenDialog.dismiss();
                NewChatFragment.this.q = false;
                if (!NewChatFragment.this.isAdded()) {
                    return;
                }
                if (chat != null) {
                    ChatAnalytics.a(chat, null);
                    NewChatFragment.this.d(chat);
                    NewChatFragment.this.getActivity().getFragmentManager().popBackStack(MessageCenterFragment.g, 0);
                    NewChatFragment.this.a(ChatFragment.a(chat));
                    return;
                }
                ChatUtils.a((Context)NewChatFragment.this.getActivity(), 2131296500, chatStatus);
            }
        });
    }

    @Override
    public void t_() {
        this.a(FindFriendsFragment.a(FindFriendsFragment.EntryPoint.b));
    }

    @Override
    public void u_() {
        if (!this.isAdded()) {
            return;
        }
        ((MediaPlayingActivity)this.getActivity()).ab().setVisibility(8);
        ((MediaPlayingActivity)this.getActivity()).U().setVisibility(8);
        ((MediaPlayingActivity)this.getActivity()).J();
        this.i.a(true);
    }

    @Override
    public void v_() {
        if (!this.isAdded()) {
            return;
        }
        ((MediaPlayingActivity)this.getActivity()).ab().setVisibility(0);
        ((MediaPlayingActivity)this.getActivity()).U().setVisibility(0);
        ((MediaPlayingActivity)this.getActivity()).J();
        this.i.a(false);
    }

    @Override
    public String x() {
        return h;
    }

    public static interface OnChatCreatedListener {
        public void a(Chat var1);
    }

}

