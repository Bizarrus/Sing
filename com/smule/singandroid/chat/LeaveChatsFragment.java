package com.smule.singandroid.chat;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.NewChatFragment.OnChatCreatedListener;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import java.util.LinkedList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

@EFragment
public class LeaveChatsFragment extends BaseFragment implements OnItemClickListener {
    @ViewById
    protected ChatListView f21075e;
    @ViewById
    protected View f21076f;
    @FragmentArg
    protected String f21077g;
    protected List<AccountIcon> f21078h;
    protected OnChatCreatedListener f21079i;
    protected LeaveChatsAdapter f21080j;

    class C43101 implements CustomAlertDialogListener {
        final /* synthetic */ LeaveChatsFragment f21074a;

        C43101(LeaveChatsFragment leaveChatsFragment) {
            this.f21074a = leaveChatsFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            ChatManager j = SingApplication.j();
            for (Chat a : this.f21074a.f21080j.m22750c()) {
                j.a(a, null);
            }
            if (this.f21074a.m22757A() < this.f21074a.getResources().getInteger(C1947R.integer.chat_max_group_chats)) {
                if (this.f21074a.f21077g != null) {
                    this.f21074a.mo6487a(ChatFragment.m22560d(this.f21074a.f21077g));
                } else {
                    this.f21074a.getActivity().getFragmentManager().popBackStack();
                    this.f21074a.mo6487a(EditGroupNameFragment.m22708a(null, this.f21074a.f21078h, this.f21074a.f21079i));
                }
                this.f21074a.m19847b(this.f21074a);
                return;
            }
            this.f21074a.f21076f.setVisibility(0);
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f21074a.m19847b(this.f21074a);
        }
    }

    public static LeaveChatsFragment m22756c(String str) {
        return LeaveChatsFragment_.m22764B().m22763a(str).m22762a();
    }

    public static LeaveChatsFragment m22753a(List<AccountIcon> list) {
        LeaveChatsFragment a = LeaveChatsFragment_.m22764B().m22763a(null).m22762a();
        a.f21078h = list;
        return a;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        m19845b().findItem(C1947R.id.action_next).setEnabled(this.f21080j.m22748a(view));
    }

    @AfterViews
    protected void m22758a() {
        m19842a(true);
        this.f21080j = new LeaveChatsAdapter(getActivity());
        this.f21075e.setAdapter(this.f21080j);
        this.f21075e.setOnItemClickListener(this);
    }

    public void onStart() {
        super.onStart();
        m19850c((int) C1947R.string.chat_leave_chats_title);
        List linkedList = new LinkedList();
        for (Chat chat : SingApplication.j().a(Bucket.INBOX)) {
            if (chat.mo6335a() == Type.GROUP) {
                linkedList.add(chat);
            }
        }
        this.f21080j.mo6758a(linkedList);
        m19845b().findItem(C1947R.id.action_next).setEnabled(this.f21080j.m22749b() > 0);
    }

    protected void mo6420v() {
        ChatAnalytics.m22399d();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(C1947R.menu.leave_chats_menu, menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != C1947R.id.action_next) {
            return super.onOptionsItemSelected(menuItem);
        }
        m22761z();
        return true;
    }

    protected void m22761z() {
        int b = this.f21080j.m22749b();
        CustomAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getQuantityString(C1947R.plurals.chat_leave_chats_confirm_title, b, new Object[]{Integer.valueOf(b)}), getString(C1947R.string.chat_leave_chats_confirm_body));
        textAlertDialog.m19803a(new C43101(this));
        textAlertDialog.show();
    }

    protected int m22757A() {
        int i = 0;
        for (Chat a : SingApplication.j().a(Bucket.INBOX)) {
            int i2;
            if (a.mo6335a() == Type.GROUP) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    public String mo6383s() {
        return LeaveChatsFragment.class.getName();
    }
}
