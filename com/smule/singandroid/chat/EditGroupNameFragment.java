package com.smule.singandroid.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager$ChatCallback;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.NewChatFragment.OnChatCreatedListener;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorListener;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class EditGroupNameFragment extends ChatActivatorFragment {
    public static final String f21047e = EditGroupNameFragment.class.getName();
    @ViewById
    protected EditText f21048f;
    @ViewById
    protected TextView f21049g;
    @ViewById
    protected View f21050h;
    protected boolean f21051i;
    protected List<AccountIcon> f21052j;
    private OnChatCreatedListener f21053k;

    class C43021 implements TextWatcher {
        final /* synthetic */ EditGroupNameFragment f21034a;

        C43021(EditGroupNameFragment editGroupNameFragment) {
            this.f21034a = editGroupNameFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @SuppressLint({"SetTextI18n"})
        public void afterTextChanged(Editable editable) {
            this.f21034a.m22705D();
            this.f21034a.m22716A();
        }
    }

    class C43032 implements OnEditorActionListener {
        final /* synthetic */ EditGroupNameFragment f21035a;

        C43032(EditGroupNameFragment editGroupNameFragment) {
            this.f21035a = editGroupNameFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if ((i != 0 && i != 6) || this.f21035a.f21051i) {
                return false;
            }
            this.f21035a.m22707F();
            return true;
        }
    }

    class C43043 implements OnClickListener {
        final /* synthetic */ EditGroupNameFragment f21036a;

        C43043(EditGroupNameFragment editGroupNameFragment) {
            this.f21036a = editGroupNameFragment;
        }

        public void onClick(View view) {
            this.f21036a.f21048f.setText("");
        }
    }

    public String mo6383s() {
        return f21047e;
    }

    public static EditGroupNameFragment m22708a(GroupChat groupChat, List<AccountIcon> list, OnChatCreatedListener onChatCreatedListener) {
        EditGroupNameFragment a = EditGroupNameFragment_.m22724D().m22723a();
        a.m22333d(groupChat);
        a.m22718a((List) list, onChatCreatedListener);
        return a;
    }

    protected void m22718a(List<AccountIcon> list, OnChatCreatedListener onChatCreatedListener) {
        this.f21052j = list;
        this.f21053k = onChatCreatedListener;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.f21052j != null) {
            bundle.putParcelableArrayList("accountIcons", new ArrayList(this.f21052j));
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (bundle != null) {
            this.f21052j = bundle.getParcelableArrayList("accountIcons");
        }
    }

    @AfterViews
    public void m22722z() {
        m19842a(true);
        getActivity().getWindow().setSoftInputMode(16);
        m22705D();
        WeakListener.m19083a(this.f21048f, new C43021(this));
        this.f21048f.setOnEditorActionListener(new C43032(this));
        this.f21050h.setOnClickListener(new C43043(this));
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.chat_group_name);
        Activity activity = (MediaPlayingActivity) getActivity();
        if (activity != null) {
            MiscUtils.m25890a(activity, this.f21048f);
        }
        m22716A();
    }

    public void onStop() {
        super.onStop();
        MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) getActivity();
        if (mediaPlayingActivity != null) {
            mediaPlayingActivity.B().getToolbarView().setDoneButtonOnClickListener(null);
        }
        MiscUtils.m25891a(getActivity(), true);
    }

    public void onDestroyView() {
        super.onDestroyView();
        getActivity().getWindow().setSoftInputMode(32);
    }

    protected void mo6420v() {
        ChatAnalytics.m22393b(m22706E());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(C1947R.menu.group_name_menu, menu);
        MenuItem findItem = menu.findItem(C1947R.id.action_done);
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1947R.layout.action_bar_icon_button, null, false);
        ((ImageView) inflate.findViewById(C1947R.id.button_next)).setImageResource(C1947R.drawable.icn_checkmark_white);
        findItem.setActionView(inflate);
        m22716A();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_done:
                m22707F();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void a_(Chat chat) {
        super.a_(chat);
        GroupChat E = m22706E();
        if (E != null) {
            this.f21048f.setText(E.mo6334S());
            this.f21048f.selectAll();
        }
    }

    protected void m22716A() {
        if (m19845b() != null && m19845b().findItem(C1947R.id.action_done) != null) {
            m19845b().findItem(C1947R.id.action_done).setEnabled(!this.f21048f.getText().toString().trim().isEmpty());
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void m22705D() {
        this.f21049g.setText(this.f21048f.getText().toString().length() + "/" + getResources().getInteger(C1947R.integer.chat_max_group_name_characters));
    }

    protected void m22719b(Chat chat) {
        if (this.f21053k != null) {
            this.f21053k.mo6760a(chat);
        }
    }

    private GroupChat m22706E() {
        return (GroupChat) this.K;
    }

    private void m22707F() {
        if (isAdded() && !this.f21051i) {
            final String trim = this.f21048f.getText().toString().trim();
            if (trim.isEmpty()) {
                m19846b((int) C1947R.string.chat_error_empty_group_name);
                return;
            }
            this.f21051i = true;
            ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f21048f.getWindowToken(), 0);
            final ChatActivatorDialog chatActivatorDialog;
            if (this.K == null) {
                chatActivatorDialog = new ChatActivatorDialog(getActivity(), getResources().getString(C1947R.string.chat_getting_ready_group_busy_message), trim);
                chatActivatorDialog.m22945a(new ChatActivatorListener(this) {
                    final /* synthetic */ EditGroupNameFragment f21039c;

                    public void t_() {
                        this.f21039c.m22713a(trim, chatActivatorDialog);
                    }
                });
                chatActivatorDialog.m22947a(false);
                chatActivatorDialog.show();
            } else if (trim.equals(m22706E().mo6334S())) {
                m22717B();
            } else {
                chatActivatorDialog = new ChatActivatorDialog(getActivity(), getResources().getString(C1947R.string.chat_changing_group_name_busy_message), trim);
                chatActivatorDialog.m22944a(this.K, new ChatActivatorListener(this) {
                    final /* synthetic */ EditGroupNameFragment f21042c;

                    public void mo6553c(Chat chat) {
                        this.f21042c.m22709a(chat, trim, chatActivatorDialog);
                    }
                });
                chatActivatorDialog.m22947a(false);
                chatActivatorDialog.show();
            }
        }
    }

    private void m22713a(String str, final ChatActivatorDialog chatActivatorDialog) {
        SingApplication.j().a(this.f21052j, str, new ChatManager$ChatCallback(this) {
            final /* synthetic */ EditGroupNameFragment f21044b;

            public void mo6326a(Chat chat, ChatStatus chatStatus) {
                chatActivatorDialog.dismiss();
                if (this.f21044b.isAdded()) {
                    this.f21044b.f21051i = false;
                    if (chat != null) {
                        Chat chat2 = (GroupChat) chat;
                        this.f21044b.m22719b(chat);
                        Log.b(EditGroupNameFragment.f21047e, "Created group chat: " + chat2.mo6334S());
                        this.f21044b.getActivity().getFragmentManager().popBackStack(MessageCenterFragment.f21133e, 0);
                        this.f21044b.mo6487a(ChatFragment.m22554b(chat2));
                        ChatAnalytics.m22383a(chat, Integer.valueOf(this.f21044b.f21052j.size()));
                        return;
                    }
                    ChatUtils.a(this.f21044b.getActivity(), C1947R.string.chat_error_creating_group_chat, chatStatus);
                }
            }
        });
    }

    private void m22709a(Chat chat, String str, final ChatActivatorDialog chatActivatorDialog) {
        ((GroupChat) chat).m19536a(str, new Completion<ChatStatus>(this) {
            final /* synthetic */ EditGroupNameFragment f21046b;

            public void m22703a(ChatStatus chatStatus) {
                chatActivatorDialog.dismiss();
                if (this.f21046b.isAdded()) {
                    this.f21046b.f21051i = false;
                    if (chatStatus == ChatStatus.OK) {
                        this.f21046b.m22717B();
                    } else {
                        ChatUtils.a(this.f21046b.getActivity(), C1947R.string.chat_error_change_name, chatStatus);
                    }
                    ChatAnalytics.m22398c(this.f21046b.m22706E());
                }
            }
        });
    }

    protected void m22717B() {
        getActivity().getFragmentManager().popBackStack();
    }
}
