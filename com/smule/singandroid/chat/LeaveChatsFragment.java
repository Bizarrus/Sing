/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.EditGroupNameFragment;
import com.smule.singandroid.chat.LeaveChatsAdapter;
import com.smule.singandroid.chat.LeaveChatsFragment_;
import com.smule.singandroid.chat.NewChatFragment;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment
public class LeaveChatsFragment
extends BaseFragment
implements AdapterView.OnItemClickListener {
    @ViewById
    protected ChatListView g;
    @ViewById
    protected View h;
    @FragmentArg
    protected String i;
    protected List<AccountIcon> j;
    protected NewChatFragment.OnChatCreatedListener k;
    protected LeaveChatsAdapter l;

    public static LeaveChatsFragment a(List<AccountIcon> list) {
        LeaveChatsFragment leaveChatsFragment = LeaveChatsFragment_.G().a(null).a();
        leaveChatsFragment.j = list;
        return leaveChatsFragment;
    }

    public static LeaveChatsFragment c(String string2) {
        return LeaveChatsFragment_.G().a(string2).a();
    }

    @Override
    protected void A() {
        ChatAnalytics.d();
    }

    protected int F() {
        Iterator<Chat> iterator = SingApplication.k().a(Chat.Bucket.a).iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().a() != Chat.Type.b) continue;
            ++n;
        }
        return n;
    }

    @AfterViews
    protected void a() {
        this.b_(true);
        this.l = new LeaveChatsAdapter((Context)this.getActivity());
        this.g.setAdapter(this.l);
        this.g.setOnItemClickListener((AdapterView.OnItemClickListener)this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        menuInflater.inflate(2131820551, menu2);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
        this.c().findItem(2131756845).setEnabled(this.l.a(view));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131756845) {
            this.t();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        super.onStart();
        this.c(2131296560);
        MenuItem menuItem = new MenuItem();
        for (Chat chat : SingApplication.k().a(Chat.Bucket.a)) {
            if (chat.a() != Chat.Type.b) continue;
            menuItem.add(chat);
        }
        this.l.a((List<Chat>)menuItem);
        menuItem = this.c().findItem(2131756845);
        boolean bl = this.l.b() > 0;
        menuItem.setEnabled(bl);
    }

    protected void t() {
        int n = this.l.b();
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getResources().getQuantityString(2131361796, n, new Object[]{n}), this.getString(2131296558));
        textAlertDialog.a(new CustomAlertDialog.CustomAlertDialogListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(CustomAlertDialog object) {
                object = SingApplication.k();
                Iterator<Chat> iterator = LeaveChatsFragment.this.l.c().iterator();
                while (iterator.hasNext()) {
                    object.a(iterator.next(), (Completion<ChatStatus>)null);
                }
                if (LeaveChatsFragment.this.F() >= LeaveChatsFragment.this.getResources().getInteger(2131623945)) {
                    LeaveChatsFragment.this.h.setVisibility(0);
                    return;
                }
                if (LeaveChatsFragment.this.i != null) {
                    LeaveChatsFragment.this.a(ChatFragment.d(LeaveChatsFragment.this.i));
                } else {
                    LeaveChatsFragment.this.getActivity().getFragmentManager().popBackStack();
                    LeaveChatsFragment.this.a(EditGroupNameFragment.a(null, LeaveChatsFragment.this.j, LeaveChatsFragment.this.k));
                }
                LeaveChatsFragment.this.b(LeaveChatsFragment.this);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                LeaveChatsFragment.this.b(LeaveChatsFragment.this);
            }
        });
        textAlertDialog.show();
    }

    @Override
    public String x() {
        return LeaveChatsFragment.class.getName();
    }

}

