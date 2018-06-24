/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.IBinder
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.EditGroupNameFragment_;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.chat.NewChatFragment;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class EditGroupNameFragment
extends ChatActivatorFragment {
    public static final String g = EditGroupNameFragment.class.getName();
    @ViewById
    protected EditText h;
    @ViewById
    protected TextView i;
    @ViewById
    protected View j;
    protected boolean k;
    protected List<AccountIcon> l;
    private NewChatFragment.OnChatCreatedListener m;

    @SuppressLint(value={"SetTextI18n"})
    private void I() {
        int n = this.h.getText().toString().length();
        this.i.setText((CharSequence)("" + n + "/" + this.getResources().getInteger(2131623946)));
    }

    private GroupChat J() {
        return (GroupChat)this.M;
    }

    private void K() {
        if (!this.isAdded() || this.k) {
            return;
        }
        final String string2 = this.h.getText().toString().trim();
        if (string2.isEmpty()) {
            this.b(2131296501);
            return;
        }
        this.k = true;
        ((InputMethodManager)this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.h.getWindowToken(), 0);
        if (this.M == null) {
            final ChatActivatorDialog chatActivatorDialog = new ChatActivatorDialog((Context)this.getActivity(), this.getResources().getString(2131296526), string2);
            chatActivatorDialog.a(new ChatActivator.ChatActivatorListener(){

                @Override
                public void y_() {
                    EditGroupNameFragment.this.a(string2, chatActivatorDialog);
                }
            });
            chatActivatorDialog.a(false);
            chatActivatorDialog.show();
            return;
        }
        if (string2.equals(this.J().S())) {
            this.G();
            return;
        }
        final ChatActivatorDialog chatActivatorDialog = new ChatActivatorDialog((Context)this.getActivity(), this.getResources().getString(2131296475), string2);
        chatActivatorDialog.a(this.M, (ChatActivator.ChatActivatorInterface)new ChatActivator.ChatActivatorListener(){

            @Override
            public void c(Chat chat) {
                EditGroupNameFragment.this.a(chat, string2, chatActivatorDialog);
            }
        });
        chatActivatorDialog.a(false);
        chatActivatorDialog.show();
    }

    public static EditGroupNameFragment a(GroupChat groupChat, List<AccountIcon> list, NewChatFragment.OnChatCreatedListener onChatCreatedListener) {
        EditGroupNameFragment editGroupNameFragment = EditGroupNameFragment_.I().a();
        editGroupNameFragment.e(groupChat);
        editGroupNameFragment.a(list, onChatCreatedListener);
        return editGroupNameFragment;
    }

    private void a(Chat chat, String string2, final ChatActivatorDialog chatActivatorDialog) {
        ((GroupChat)chat).a(string2, new Completion<ChatStatus>(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatStatus chatStatus) {
                chatActivatorDialog.dismiss();
                if (!EditGroupNameFragment.this.isAdded()) {
                    return;
                }
                EditGroupNameFragment.this.k = false;
                if (chatStatus == ChatStatus.a) {
                    EditGroupNameFragment.this.G();
                } else {
                    ChatUtils.a((Context)EditGroupNameFragment.this.getActivity(), 2131296497, chatStatus);
                }
                ChatAnalytics.c(EditGroupNameFragment.this.J());
            }
        });
    }

    private void a(String string2, final ChatActivatorDialog chatActivatorDialog) {
        SingApplication.k().a(this.l, string2, new ChatManager(){

            @Override
            public void a(Chat chat, ChatStatus object) {
                chatActivatorDialog.dismiss();
                if (!EditGroupNameFragment.this.isAdded()) {
                    return;
                }
                EditGroupNameFragment.this.k = false;
                if (chat != null) {
                    object = (GroupChat)chat;
                    EditGroupNameFragment.this.a(chat);
                    Log.b(EditGroupNameFragment.g, "Created group chat: " + object.S());
                    EditGroupNameFragment.this.getActivity().getFragmentManager().popBackStack(MessageCenterFragment.g, 0);
                    EditGroupNameFragment.this.a(ChatFragment.a((Chat)object));
                    ChatAnalytics.a(chat, (Number)EditGroupNameFragment.this.l.size());
                    return;
                }
                ChatUtils.a((Context)EditGroupNameFragment.this.getActivity(), 2131296500, (ChatStatus)((Object)object));
            }
        });
    }

    @Override
    protected void A() {
        ChatAnalytics.b(this.J());
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void F() {
        if (this.c() != null && this.c().findItem(2131756844) != null) {
            MenuItem menuItem = this.c().findItem(2131756844);
            boolean bl = !this.h.getText().toString().trim().isEmpty();
            menuItem.setEnabled(bl);
        }
    }

    protected void G() {
        this.getActivity().getFragmentManager().popBackStack();
    }

    protected void a(Chat chat) {
        if (this.m != null) {
            this.m.a(chat);
        }
    }

    protected void a(List<AccountIcon> list, NewChatFragment.OnChatCreatedListener onChatCreatedListener) {
        this.l = list;
        this.m = onChatCreatedListener;
    }

    @Override
    public void b(Chat object) {
        super.b((Chat)object);
        object = this.J();
        if (object != null) {
            object = object.S();
            this.h.setText((CharSequence)object);
            this.h.selectAll();
        }
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.l = bundle.getParcelableArrayList("accountIcons");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        menuInflater.inflate(2131820550, menu2);
        menu2 = menu2.findItem(2131756844);
        menuInflater = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903071, null, false);
        ((ImageView)menuInflater.findViewById(2131755235)).setImageResource(2130837888);
        menu2.setActionView((View)menuInflater);
        this.F();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.getActivity().getWindow().setSoftInputMode(32);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                do {
                    return super.onOptionsItemSelected(menuItem);
                    break;
                } while (true);
            }
            case 2131756844: 
        }
        this.K();
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((MediaPlayingActivity)this.getActivity() != null) {
            this.getActivity().getWindow().setSoftInputMode(16);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.l != null) {
            bundle.putParcelableArrayList("accountIcons", new ArrayList<AccountIcon>(this.l));
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.c(2131296528);
        MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)this.getActivity();
        if (mediaPlayingActivity != null) {
            MiscUtils.a((Activity)mediaPlayingActivity, (EditText)this.h);
        }
        this.F();
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

    @AfterViews
    public void t() {
        this.b_(true);
        this.getActivity().getWindow().setSoftInputMode(16);
        this.I();
        WeakListener.a(this.h, new TextWatcher(){

            @SuppressLint(value={"SetTextI18n"})
            public void afterTextChanged(Editable editable) {
                EditGroupNameFragment.this.I();
                EditGroupNameFragment.this.F();
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        });
        this.h.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (n != 0 && n != 6 || EditGroupNameFragment.this.k) {
                    return false;
                }
                EditGroupNameFragment.this.K();
                return true;
            }
        });
        this.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                EditGroupNameFragment.this.h.setText((CharSequence)"");
            }
        });
    }

    @Override
    public String x() {
        return g;
    }

}

