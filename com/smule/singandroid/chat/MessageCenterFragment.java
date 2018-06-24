package com.smule.singandroid.chat;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ConnectionStatus;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerListenerAdapter;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SimpleTypeTabs.OnTabClickListener;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics.MessageCenterFilterType;
import com.smule.singandroid.chat.ChatAnalytics.SettingToggleType;
import com.smule.singandroid.chat.MessageCenterAdapter.ViewHolder;
import com.smule.singandroid.chat.NewChatFragment.OnChatCreatedListener;
import com.smule.singandroid.customviews.MessageCenterListView;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class MessageCenterFragment extends BaseFragment implements OnItemClickListener, OnItemLongClickListener, OnTabClickListener, OnChatCreatedListener {
    public static final String f21133e = MessageCenterFragment.class.getName();
    public static Bucket f21134r;
    private boolean f21135A;
    private boolean f21136B = false;
    private Bucket f21137C;
    @ViewById
    protected View f21138f;
    @ViewById
    protected View f21139g;
    @ViewById
    protected ImageView f21140h;
    @ViewById
    protected TextView f21141i;
    @ViewById
    protected Button f21142j;
    @ViewById
    protected CustomViewPager f21143k;
    @ViewById
    protected TabLayout f21144l;
    protected SingTabLayoutHelper f21145m;
    protected MessageCenterPagerAdapter f21146n;
    protected Map<Bucket, BucketInfo> f21147o = new HashMap();
    protected Bucket f21148p = Bucket.INBOX;
    protected ChatManager f21149q;
    ConnectionStatusIndicator f21150s;
    private ArrayList<Chat> f21151t = new ArrayList();
    private boolean f21152u = false;
    private ChatListener f21153v = new C43141(this);
    private ChatManagerListener f21154w = new C43152(this);
    private int[] f21155x = new int[2];
    private OnClickListener f21156y = new C43163(this);
    private OnClickListener f21157z = new C43174(this);

    class C43141 extends ChatListenerAdapter {
        final /* synthetic */ MessageCenterFragment f21110a;

        C43141(MessageCenterFragment messageCenterFragment) {
            this.f21110a = messageCenterFragment;
        }

        public void m22790a(Chat chat, ChatMessage chatMessage, boolean z) {
            if (!z || chat.m19222j().size() == 1) {
                this.f21110a.m22830a(chat.mo6347b());
            }
        }

        public void m22789a(Chat chat, ChatMessage chatMessage) {
            if (chatMessage == chat.m19224l()) {
                this.f21110a.m22830a(chat.mo6347b());
            }
        }

        public void m22793d(Chat chat) {
            if (chat.m19226n() && this.f21110a.f21148p != chat.mo6347b()) {
                this.f21110a.m22834b(chat.mo6347b());
            }
        }

        public void m22791b(Chat chat) {
            this.f21110a.m22830a(chat.mo6347b());
        }

        public void m22792c(Chat chat) {
            this.f21110a.m22830a(chat.mo6347b());
        }

        public void m22788a(Chat chat, ChatState chatState) {
            this.f21110a.m22830a(chat.mo6347b());
        }
    }

    class C43152 extends ChatManagerListenerAdapter {
        final /* synthetic */ MessageCenterFragment f21111a;

        C43152(MessageCenterFragment messageCenterFragment) {
            this.f21111a = messageCenterFragment;
        }

        public void mo6322e(Chat chat) {
            this.f21111a.m22830a(chat.mo6347b());
        }

        public void mo6323f(Chat chat) {
            this.f21111a.m22830a(chat.mo6347b());
        }

        public void mo6320a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
            if (this.f21111a.f21149q.b() == ChatManager$ConnectionStatus.CONNECTED && !this.f21111a.f21136B) {
                this.f21111a.m22829D();
            }
            this.f21111a.m22833a(chatManager$ConnectionStatus);
        }
    }

    class C43163 implements OnClickListener {
        final /* synthetic */ MessageCenterFragment f21112a;

        C43163(MessageCenterFragment messageCenterFragment) {
            this.f21112a = messageCenterFragment;
        }

        public void onClick(View view) {
            SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", true).apply();
            this.f21112a.m22827B();
        }
    }

    class C43174 implements OnClickListener {
        final /* synthetic */ MessageCenterFragment f21113a;

        C43174(MessageCenterFragment messageCenterFragment) {
            this.f21113a = messageCenterFragment;
        }

        public void onClick(View view) {
            SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", true).apply();
            ((MasterActivity) this.f21113a.getActivity()).z();
        }
    }

    class C43185 implements OnTabSelectedListener {
        final /* synthetic */ MessageCenterFragment f21114a;

        C43185(MessageCenterFragment messageCenterFragment) {
            this.f21114a = messageCenterFragment;
        }

        public void onTabSelected(Tab tab) {
            this.f21114a.b_(tab.getPosition());
            this.f21114a.m22812a(tab);
        }

        public void onTabUnselected(Tab tab) {
            this.f21114a.m22817b(tab);
        }

        public void onTabReselected(Tab tab) {
            this.f21114a.m22812a(tab);
        }
    }

    protected class BucketInfo {
        final int f21125a;
        final ChatListView f21126b;
        final SwipeRefreshLayout f21127c;
        final Drawable f21128d;
        final int f21129e;
        final /* synthetic */ MessageCenterFragment f21130f;

        public BucketInfo(MessageCenterFragment messageCenterFragment, int i, ChatListView chatListView, SwipeRefreshLayout swipeRefreshLayout, Drawable drawable, int i2) {
            this.f21130f = messageCenterFragment;
            this.f21125a = i;
            this.f21126b = chatListView;
            this.f21127c = swipeRefreshLayout;
            this.f21128d = drawable;
            this.f21129e = i2;
        }
    }

    private class MessageCenterPagerAdapter extends PagerAdapter {
        protected Map<Integer, MessageCenterListView> f21131a;
        final /* synthetic */ MessageCenterFragment f21132b;

        private MessageCenterPagerAdapter(MessageCenterFragment messageCenterFragment) {
            this.f21132b = messageCenterFragment;
            this.f21131a = new HashMap();
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int i) {
            switch (i) {
                case 0:
                    return this.f21132b.getResources().getString(C1947R.string.message_center_inbox);
                default:
                    return this.f21132b.getResources().getString(C1947R.string.message_center_other);
            }
        }

        public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
            if (this.f21132b.isAdded()) {
                MessageCenterListView messageCenterListView = (MessageCenterListView) this.f21132b.f21143k.findViewWithTag("sb_item#" + i);
                if (messageCenterListView != null) {
                    for (MessageCenterListView messageCenterListView2 : this.f21131a.values()) {
                        if (messageCenterListView2 != messageCenterListView) {
                            messageCenterListView2.f21603a.setOnScrollListener(null);
                        }
                    }
                }
            }
        }

        public Parcelable saveState() {
            for (MessageCenterListView messageCenterListView : this.f21131a.values()) {
                messageCenterListView.f21603a.setAdapter(null);
            }
            this.f21131a.clear();
            this.f21132b.f21147o.clear();
            this.f21132b.f21135A = true;
            return super.saveState();
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            this.f21132b.f21135A = false;
            super.restoreState(parcelable, classLoader);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            if (this.f21131a.containsKey(Integer.valueOf(i))) {
                return null;
            }
            Object a = MessageCenterListView.m23239a(this.f21132b.getActivity(), this.f21132b, i == 0 ? Bucket.INBOX : Bucket.OTHER);
            a.setLayoutParams(new LayoutParams(-1, -1));
            viewGroup.addView(a);
            this.f21131a.put(Integer.valueOf(i), a);
            return a;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            MessageCenterListView messageCenterListView = (MessageCenterListView) obj;
            viewGroup.removeView(messageCenterListView);
            if (messageCenterListView.f21603a != null) {
                messageCenterListView.f21603a.setAdapter(null);
            }
            this.f21132b.f21147o.remove(messageCenterListView.f21610h);
            this.f21131a.remove(Integer.valueOf(i));
        }
    }

    public static MessageCenterFragment m22811a() {
        return MessageCenterFragment_.m22842E().m22841a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19831a(ActionBarHighlightMode.ALWAYS);
        m19842a(true);
        this.f21149q = SingApplication.j();
        this.f21150s = new ConnectionStatusIndicator(getActivity(), this.f21149q);
        f21134r = null;
        SharedPreferences sharedPreferences = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0);
        boolean contains = sharedPreferences.contains("SHOW_TOOLTIP_HEADER");
        if (!sharedPreferences.contains("SHOW_TOOLTIP_HEADER_OTHER")) {
            if (contains) {
                sharedPreferences.edit().putBoolean("SHOW_TOOLTIP_HEADER_OTHER", false).apply();
                return;
            }
            sharedPreferences.edit().putBoolean("SHOW_TOOLTIP_HEADER", true).apply();
            sharedPreferences.edit().putBoolean("SHOW_TOOLTIP_HEADER_OTHER", true).apply();
        }
    }

    @AfterViews
    protected void m22840z() {
        this.f21146n = new MessageCenterPagerAdapter();
        this.f21143k.setAdapter(this.f21146n);
        this.f21145m = new SingTabLayoutHelper(this.f21144l, this.f21143k);
        this.f21145m.m11744a(true);
        this.f21145m.m11740a(new C43185(this));
    }

    private void m22812a(Tab tab) {
        this.f21145m.m23479a(true, tab);
    }

    private void m22817b(Tab tab) {
        this.f21145m.m23479a(false, tab);
    }

    public void m22831a(Bucket bucket, ChatListView chatListView, SwipeRefreshLayout swipeRefreshLayout) {
        if (bucket == Bucket.INBOX && !this.f21147o.containsKey(Bucket.INBOX)) {
            this.f21147o.put(Bucket.INBOX, new BucketInfo(this, 0, chatListView, swipeRefreshLayout, getResources().getDrawable(C1947R.drawable.chat_empty_inbox_chat), C1947R.string.message_center_no_chats_inbox));
        } else if (bucket == Bucket.OTHER && !this.f21147o.containsKey(Bucket.OTHER)) {
            this.f21147o.put(Bucket.OTHER, new BucketInfo(this, 1, chatListView, swipeRefreshLayout, getResources().getDrawable(C1947R.drawable.chat_empty_other_chat), C1947R.string.message_center_no_chats_others));
        }
        if (bucket == this.f21137C) {
            this.f21137C = null;
            m22836c(bucket);
        } else {
            m22834b(bucket);
        }
        m22830a(bucket);
    }

    public void m22826A() {
        if (isAdded()) {
            SharedPreferences sharedPreferences = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0);
            for (MessageCenterListView messageCenterListView : this.f21146n.f21131a.values()) {
                if (!(messageCenterListView == null || messageCenterListView.f21605c == null)) {
                    boolean z;
                    if (!sharedPreferences.getBoolean(this.f21148p == Bucket.INBOX ? "SHOW_TOOLTIP_HEADER" : "SHOW_TOOLTIP_HEADER_OTHER", true) || this.f21149q.b(this.f21148p)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    if (z) {
                        messageCenterListView.f21605c.setVisibility(0);
                        messageCenterListView.m23241b();
                    } else {
                        messageCenterListView.f21605c.setVisibility(8);
                    }
                }
            }
        }
    }

    private void m22804E() {
        if (isAdded()) {
            SharedPreferences sharedPreferences = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0);
            if (!this.f21149q.b(Bucket.INBOX)) {
                sharedPreferences.edit().putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).apply();
            } else if (!sharedPreferences.getBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", false)) {
                sharedPreferences.edit().putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).apply();
            } else if (!sharedPreferences.getBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", false) && !sharedPreferences.getBoolean("TAPPED_ON_NEW_MESSAGE", false)) {
                ((MasterActivity) getActivity()).x();
            }
        }
    }

    public void onStart() {
        super.onStart();
        if (this.f21135A) {
            m22840z();
        }
        ChatAnalyticsMonitor.a().b();
        m19850c((int) C1947R.string.message_center_title);
        this.f21149q.a(this.f21154w);
        this.f21149q.a(this.f21153v);
        this.f21138f.setVisibility(0);
        ((MasterActivity) getActivity()).y();
        m22833a(this.f21149q.b());
        this.f21149q.e(this.f21148p);
    }

    public void onResume() {
        super.onResume();
        this.f21150s.m22693b();
        this.f21136B = false;
        if (this.f21149q.b() == ChatManager$ConnectionStatus.CONNECTED) {
            m22829D();
        } else {
            this.f21149q.a(true);
        }
    }

    public void onPause() {
        super.onPause();
        this.f21150s.m22695c();
    }

    public void onStop() {
        super.onStop();
        m19850c((int) C1947R.string.message_center_title);
        this.f21149q.b(this.f21154w);
        this.f21149q.b(this.f21153v);
        ((MasterActivity) getActivity()).z();
        m22810K();
        SingApplication.d().a(f21133e);
        this.f21149q.e(null);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21145m = null;
    }

    public void onDestroy() {
        super.onDestroy();
        f21134r = null;
        this.f21146n = null;
    }

    protected void mo6420v() {
        ChatAnalytics.m22387a(this.f21148p == Bucket.INBOX ? MessageCenterFilterType.INBOX : MessageCenterFilterType.OTHER, this.f21149q.c(this.f21148p));
    }

    public boolean mo6400c() {
        if (!this.f21152u) {
            return super.mo6400c();
        }
        m22810K();
        return true;
    }

    public void onCreateOptionsMenu(final Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(C1947R.menu.message_center_fragment_menu, menu);
        new Handler().post(new Runnable(this) {
            final /* synthetic */ MessageCenterFragment f21116b;

            public void run() {
                View actionView = menu.findItem(C1947R.id.start_new_chat_menu).getActionView();
                actionView.getLocationOnScreen(this.f21116b.f21155x);
                int width = this.f21116b.f21155x[0] + (actionView.getWidth() / 2);
                int i = this.f21116b.f21155x[1];
                if (this.f21116b.isAdded()) {
                    ((MasterActivity) this.f21116b.getActivity()).a(this.f21116b, width, i, this.f21116b.f21156y, this.f21116b.f21157z);
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (this.f21149q.b() != ChatManager$ConnectionStatus.CONNECTED) {
            return super.onOptionsItemSelected(menuItem);
        }
        final TextAlertDialog textAlertDialog;
        switch (menuItem.getItemId()) {
            case C1947R.id.start_new_chat_menu:
                m22827B();
                return true;
            case C1947R.id.mute_chat:
                final boolean I = m22808I();
                Iterator it = this.f21151t.iterator();
                while (it.hasNext()) {
                    final Chat chat = (Chat) it.next();
                    chat.m19203a(I, new Completion<ChatStatus>(this) {
                        final /* synthetic */ MessageCenterFragment f21120c;

                        class C43201 implements Runnable {
                            final /* synthetic */ C43217 f21117a;

                            C43201(C43217 c43217) {
                                this.f21117a = c43217;
                            }

                            public void run() {
                                ((BucketInfo) this.f21117a.f21120c.f21147o.get(this.f21117a.f21120c.f21148p)).f21126b.m22620a(this.f21117a.f21120c.f21149q.a(this.f21117a.f21120c.f21148p));
                                Iterator it = this.f21117a.f21120c.f21151t.iterator();
                                while (it.hasNext()) {
                                    this.f21117a.f21120c.m22807H().m22780a(((Chat) it.next()).m19209c());
                                }
                                this.f21117a.f21120c.m22810K();
                                ChatAnalytics.m22382a(chat, I ? SettingToggleType.OFF : SettingToggleType.ON);
                            }
                        }

                        public void m22797a(ChatStatus chatStatus) {
                            if (chatStatus == ChatStatus.OK) {
                                new Handler(Looper.getMainLooper()).post(new C43201(this));
                            } else {
                                ChatUtils.a(this.f21120c.getActivity(), C1947R.string.chat_error_mute_chat, chatStatus);
                            }
                        }
                    });
                }
                return true;
            case C1947R.id.remove_chat:
                if (this.f21148p == Bucket.INBOX) {
                    textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), getResources().getString(C1947R.string.chat_delete_peer_chat));
                    textAlertDialog.m19800a((int) C1947R.string.core_delete, (int) C1947R.string.core_cancel);
                    textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                        final /* synthetic */ MessageCenterFragment f21124b;

                        public void mo6385a(CustomAlertDialog customAlertDialog) {
                            this.f21124b.m22805F();
                        }

                        public void mo6386b(CustomAlertDialog customAlertDialog) {
                            textAlertDialog.dismiss();
                        }
                    });
                    textAlertDialog.show();
                    break;
                }
                m22805F();
                return true;
            case C1947R.id.leave_chat:
                if (this.f21148p == Bucket.INBOX) {
                    textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_are_you_sure), getResources().getString(C1947R.string.chat_delete_group_chat));
                    textAlertDialog.m19800a((int) C1947R.string.core_leave, (int) C1947R.string.core_cancel);
                    textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
                        final /* synthetic */ MessageCenterFragment f21122b;

                        public void mo6385a(CustomAlertDialog customAlertDialog) {
                            this.f21122b.m22805F();
                        }

                        public void mo6386b(CustomAlertDialog customAlertDialog) {
                            textAlertDialog.dismiss();
                        }
                    });
                    textAlertDialog.show();
                    break;
                }
                m22805F();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void m22805F() {
        final BusyDialog busyDialog = new BusyDialog(getActivity(), (int) C1947R.string.chat_deleting_busy_message);
        busyDialog.show();
        SimpleBarrier simpleBarrier = new SimpleBarrier(this.f21151t.size(), new Runnable(this) {
            final /* synthetic */ MessageCenterFragment f21107b;

            public void run() {
                busyDialog.dismiss();
            }
        });
        Iterator it = this.f21151t.iterator();
        while (it.hasNext()) {
            m22813a((Chat) it.next(), simpleBarrier);
        }
        m22836c(this.f21148p);
        m22810K();
    }

    private void m22813a(Chat chat, final SimpleBarrier simpleBarrier) {
        this.f21149q.a(chat, new Completion<ChatStatus>(this) {
            final /* synthetic */ MessageCenterFragment f21109b;

            public void m22786a(ChatStatus chatStatus) {
                simpleBarrier.m19034a();
            }
        });
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (((Chat) adapterView.getItemAtPosition(i)).m19212d() != ChatState.LOADING) {
            if (this.f21152u) {
                onItemLongClick(adapterView, view, i, j);
            } else {
                mo6487a(ChatFragment.m22554b((Chat) adapterView.getItemAtPosition(i)));
            }
        }
    }

    public synchronized boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        Chat chat = (Chat) adapterView.getItemAtPosition(i);
        if (chat.m19212d() != ChatState.LOADING) {
            boolean z;
            int i2 = 0;
            while (i2 < this.f21151t.size()) {
                if (((Chat) this.f21151t.get(i2)).m19209c().equals(chat.m19209c())) {
                    z = true;
                    break;
                }
                i2++;
            }
            z = false;
            if (z) {
                ((ViewHolder) view.getTag()).m22772a();
                this.f21151t.remove(i2);
                if (!this.f21151t.isEmpty()) {
                    m22807H().m22783b(chat.m19209c());
                }
            } else {
                ((ViewHolder) view.getTag()).m22772a();
                this.f21151t.add(chat);
                m22807H().m22780a(chat.m19209c());
            }
            if (this.f21151t.isEmpty()) {
                m22810K();
            } else {
                ChatAnalytics.m22392b();
                m22809J();
            }
        }
        return true;
    }

    @Click
    protected void m22827B() {
        SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("TAPPED_ON_NEW_MESSAGE", true).apply();
        ((MasterActivity) getActivity()).z();
        mo6487a(NewChatFragment.m22338a((OnChatCreatedListener) this));
    }

    @Click
    public void m22828C() {
        SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean(this.f21148p == Bucket.INBOX ? "SHOW_TOOLTIP_HEADER" : "SHOW_TOOLTIP_HEADER_OTHER", false).apply();
        for (MessageCenterListView messageCenterListView : this.f21146n.f21131a.values()) {
            if (messageCenterListView.f21610h == this.f21148p) {
                messageCenterListView.f21605c.setVisibility(8);
            }
        }
    }

    private ChatListView m22806G() {
        if (this.f21146n.f21131a == null || this.f21146n.f21131a.get(Integer.valueOf(this.f21148p.ordinal())) == null) {
            return null;
        }
        return ((MessageCenterListView) this.f21146n.f21131a.get(Integer.valueOf(this.f21148p.ordinal()))).f21603a;
    }

    private MessageCenterAdapter m22807H() {
        ChatListView G = m22806G();
        if (G == null) {
            return null;
        }
        return (MessageCenterAdapter) G.getAdapter();
    }

    protected void m22829D() {
        this.f21136B = true;
        for (Bucket a : this.f21147o.keySet()) {
            m22830a(a);
        }
        m22836c(f21134r != null ? f21134r : this.f21148p);
        f21134r = null;
        m22826A();
    }

    private boolean m22808I() {
        Iterator it = this.f21151t.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2;
            if (((Chat) it.next()).m19227o()) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i = i2 + i;
        }
        if (i < this.f21151t.size()) {
            return true;
        }
        return false;
    }

    private void m22809J() {
        mo6861a(getResources().getString(C1947R.string.message_center_selected_chats, new Object[]{Integer.valueOf(this.f21151t.size())}));
        this.f21152u = true;
        Menu b = m19845b();
        if (b != null) {
            boolean z;
            b.findItem(C1947R.id.start_new_chat_menu).setVisible(false);
            if (this.f21148p == Bucket.INBOX) {
                b.findItem(C1947R.id.mute_chat).setVisible(true);
                if (m22808I()) {
                    b.findItem(C1947R.id.mute_chat).setIcon(C1947R.drawable.notification_off_white);
                } else {
                    b.findItem(C1947R.id.mute_chat).setIcon(C1947R.drawable.notification_on_white);
                }
            } else {
                b.findItem(C1947R.id.mute_chat).setVisible(false);
            }
            Iterator it = this.f21151t.iterator();
            while (it.hasNext()) {
                if (((Chat) it.next()) instanceof GroupChat) {
                    z = true;
                    break;
                }
            }
            z = false;
            if (z) {
                b.findItem(C1947R.id.leave_chat).setVisible(true);
                b.findItem(C1947R.id.remove_chat).setVisible(false);
                return;
            }
            b.findItem(C1947R.id.remove_chat).setVisible(true);
            b.findItem(C1947R.id.leave_chat).setVisible(false);
        }
    }

    private void m22810K() {
        if (isAdded()) {
            this.f21151t.clear();
            MessageCenterAdapter H = m22807H();
            if (H != null) {
                H.m22784c();
            }
            m19850c((int) C1947R.string.message_center_title);
            this.f21152u = false;
            Menu b = m19845b();
            if (b != null && b.findItem(C1947R.id.start_new_chat_menu) != null) {
                b.findItem(C1947R.id.start_new_chat_menu).setVisible(true);
                b.findItem(C1947R.id.mute_chat).setVisible(false);
                b.findItem(C1947R.id.remove_chat).setVisible(false);
                b.findItem(C1947R.id.leave_chat).setVisible(false);
            }
        }
    }

    protected void m22833a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
        boolean z = true;
        if (isAdded() && m19845b() != null) {
            MenuItem findItem = m19845b().findItem(C1947R.id.start_new_chat_menu);
            if (findItem != null) {
                findItem.setEnabled(chatManager$ConnectionStatus == ChatManager$ConnectionStatus.CONNECTED);
            }
            Button button = this.f21142j;
            if (chatManager$ConnectionStatus != ChatManager$ConnectionStatus.CONNECTED) {
                z = false;
            }
            button.setEnabled(z);
            if (chatManager$ConnectionStatus == ChatManager$ConnectionStatus.CONNECTED) {
                if (this.f21138f.getVisibility() == 0) {
                    m22804E();
                }
                this.f21138f.setVisibility(8);
            }
        }
    }

    public void b_(int i) {
        Bucket bucket;
        switch (i) {
            case 0:
                bucket = Bucket.INBOX;
                break;
            case 1:
                bucket = Bucket.OTHER;
                break;
            default:
                bucket = Bucket.OTHER;
                break;
        }
        if (this.f21148p != bucket) {
            m22810K();
            m22836c(bucket);
            ChatAnalytics.m22394b(bucket == Bucket.INBOX ? MessageCenterFilterType.INBOX : MessageCenterFilterType.OTHER, this.f21149q.c(bucket));
        }
    }

    public void mo6760a(Chat chat) {
    }

    protected void m22830a(Bucket bucket) {
        BucketInfo bucketInfo = (BucketInfo) this.f21147o.get(bucket);
        if (bucketInfo != null) {
            bucketInfo.f21126b.m22620a(this.f21149q.a(bucket));
        }
        if (this.f21137C == bucket) {
            m22835b(this.f21149q.b(this.f21148p));
        }
    }

    protected void m22834b(Bucket bucket) {
        BucketInfo bucketInfo = (BucketInfo) this.f21147o.get(bucket);
        if (bucketInfo != null) {
            int i;
            int i2;
            if (bucket == this.f21148p) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                i2 = 0;
                for (Chat chat : this.f21149q.a(bucket)) {
                    if (!chat.m19226n() || chat.mo6350v()) {
                        i = i2;
                    } else {
                        i = i2 + 1;
                    }
                    i2 = i;
                }
            } else {
                i2 = 0;
            }
            Tab tabAt = this.f21144l.getTabAt(bucketInfo.f21125a);
            if (tabAt != null) {
                TextView textView = (TextView) tabAt.getCustomView().findViewById(C1947R.id.tab_badge);
                ImageView imageView = (ImageView) tabAt.getCustomView().findViewById(C1947R.id.tab_badge_empty);
                if (bucket == Bucket.INBOX || i2 == 0) {
                    if (i2 == 0) {
                        textView.setVisibility(8);
                        imageView.setVisibility(8);
                        return;
                    }
                    textView.setText(MiscUtils.m25884a(i2));
                    textView.setVisibility(0);
                    imageView.setVisibility(8);
                } else if (i2 > 0) {
                    textView.setVisibility(8);
                    imageView.setVisibility(0);
                }
            }
        }
    }

    protected void m22836c(Bucket bucket) {
        boolean z = false;
        BucketInfo bucketInfo = (BucketInfo) this.f21147o.get(bucket);
        if (bucketInfo == null) {
            this.f21137C = bucket;
            return;
        }
        Bucket bucket2;
        this.f21148p = bucket;
        this.f21144l.getTabAt(bucketInfo.f21125a).select();
        if (this.f21149q.b(bucket)) {
            bucket2 = null;
        } else {
            bucket2 = bucket;
        }
        this.f21149q.d(bucket);
        this.f21149q.e(bucket);
        m22830a(bucket);
        for (Entry entry : this.f21147o.entrySet()) {
            int i;
            SwipeRefreshLayout swipeRefreshLayout = ((BucketInfo) entry.getValue()).f21127c;
            if (entry.getKey() == bucket2) {
                i = 0;
            } else {
                i = 8;
            }
            swipeRefreshLayout.setVisibility(i);
        }
        if (bucket2 == null) {
            z = true;
        }
        m22835b(z);
        m22834b(bucket);
        m22834b(bucket == Bucket.INBOX ? Bucket.OTHER : Bucket.INBOX);
        if (this.f21146n != null) {
            m22826A();
        }
    }

    protected void m22835b(boolean z) {
        BucketInfo bucketInfo = (BucketInfo) this.f21147o.get(this.f21148p);
        if (z) {
            this.f21139g.setVisibility(0);
            if (bucketInfo != null) {
                this.f21140h.setImageDrawable(bucketInfo.f21128d);
                this.f21141i.setText(bucketInfo.f21129e);
                return;
            }
            return;
        }
        this.f21139g.setVisibility(8);
        if (bucketInfo != null) {
            bucketInfo.f21127c.setVisibility(0);
        }
    }

    public String mo6383s() {
        return f21133e;
    }
}
