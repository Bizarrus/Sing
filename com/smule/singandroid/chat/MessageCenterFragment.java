/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.widget.SwipeRefreshLayout
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.AdapterView$OnItemLongClickListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.SimpleBarrier;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerListenerAdapter;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SimpleTypeTabs;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalyticsMonitor;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.ChatListView;
import com.smule.singandroid.chat.ConnectionStatusIndicator;
import com.smule.singandroid.chat.MessageCenterAdapter;
import com.smule.singandroid.chat.MessageCenterFragment_;
import com.smule.singandroid.chat.NewChatFragment;
import com.smule.singandroid.customviews.MessageCenterListView;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class MessageCenterFragment
extends BaseFragment
implements AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener,
SimpleTypeTabs.OnTabClickListener,
NewChatFragment.OnChatCreatedListener {
    public static final String g = MessageCenterFragment.class.getName();
    public static Chat.Bucket t;
    private View.OnClickListener A;
    private View.OnClickListener B;
    private boolean C;
    private boolean D;
    private Chat.Bucket E;
    @ViewById
    protected View h;
    @ViewById
    protected View i;
    @ViewById
    protected ImageView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected Button l;
    @ViewById
    protected CustomViewPager m;
    @ViewById
    protected TabLayout n;
    protected SingTabLayoutHelper o;
    protected MessageCenterPagerAdapter p;
    protected Map<Chat.Bucket, BucketInfo> q = new HashMap<Chat.Bucket, BucketInfo>();
    protected Chat.Bucket r = Chat.Bucket.a;
    protected com.smule.chat.ChatManager s;
    ConnectionStatusIndicator u;
    private ArrayList<Chat> v = new ArrayList();
    private boolean w = false;
    private ChatListener x;
    private ChatManagerListener y;
    private int[] z;

    public MessageCenterFragment() {
        this.x = new ChatListenerAdapter(){

            @Override
            public void a(Chat chat, Chat.ChatState chatState) {
                MessageCenterFragment.this.a(chat.b());
            }

            @Override
            public void a(Chat chat, ChatMessage chatMessage) {
                if (chatMessage == chat.l()) {
                    MessageCenterFragment.this.a(chat.b());
                }
            }

            @Override
            public void a(Chat chat, ChatMessage chatMessage, boolean bl) {
                if (!bl || chat.j().size() == 1) {
                    MessageCenterFragment.this.a(chat.b());
                }
            }

            @Override
            public void b(Chat chat) {
                MessageCenterFragment.this.a(chat.b());
            }

            @Override
            public void c(Chat chat) {
                MessageCenterFragment.this.a(chat.b());
            }

            @Override
            public void d(Chat chat) {
                if (chat.n() && MessageCenterFragment.this.r != chat.b()) {
                    MessageCenterFragment.this.b(chat.b());
                }
            }
        };
        this.y = new ChatManagerListenerAdapter(){

            @Override
            public void a(ChatManager connectionStatus) {
                if (MessageCenterFragment.this.s.b() == ChatManager.e && !MessageCenterFragment.this.D) {
                    MessageCenterFragment.this.I();
                }
                MessageCenterFragment.this.a(connectionStatus);
            }

            @Override
            public void e(Chat chat) {
                MessageCenterFragment.this.a(chat.b());
            }

            @Override
            public void f(Chat chat) {
                MessageCenterFragment.this.a(chat.b());
            }
        };
        this.z = new int[2];
        this.A = new View.OnClickListener(){

            public void onClick(View view) {
                SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", true).apply();
                MessageCenterFragment.this.G();
            }
        };
        this.B = new View.OnClickListener(){

            public void onClick(View view) {
                SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", true).apply();
                ((MasterActivity)MessageCenterFragment.this.getActivity()).B();
            }
        };
        this.D = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void J() {
        block6 : {
            block5 : {
                if (!this.isAdded()) break block5;
                SharedPreferences sharedPreferences = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0);
                if (!this.s.b(Chat.Bucket.a)) {
                    sharedPreferences.edit().putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).apply();
                    return;
                }
                if (!sharedPreferences.getBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", false)) {
                    sharedPreferences.edit().putBoolean("SEEN_MESSAGE_CENTER_FIRST_TIME", true).apply();
                    return;
                }
                if (!sharedPreferences.getBoolean("SEEN_MESSAGE_CENTER_SECOND_TIME", false) && !sharedPreferences.getBoolean("TAPPED_ON_NEW_MESSAGE", false)) break block6;
            }
            return;
        }
        ((MasterActivity)this.getActivity()).z();
    }

    private void K() {
        Object object = new BusyDialog(this.getActivity(), 2131296483);
        object.show();
        object = new SimpleBarrier(this.v.size(), new Runnable((BusyDialog)((Object)object)){
            final /* synthetic */ BusyDialog a;
            {
                this.a = busyDialog;
            }

            @Override
            public void run() {
                this.a.dismiss();
            }
        });
        Iterator<Chat> iterator = this.v.iterator();
        while (iterator.hasNext()) {
            this.a(iterator.next(), (SimpleBarrier)object);
        }
        this.c(this.r);
        this.P();
    }

    private ChatListView L() {
        if (this.p.a == null || this.p.a.get(this.r.ordinal()) == null) {
            return null;
        }
        return this.p.a.get((Object)Integer.valueOf((int)this.r.ordinal())).a;
    }

    private MessageCenterAdapter M() {
        ChatListView chatListView = this.L();
        if (chatListView == null) {
            return null;
        }
        return (MessageCenterAdapter)chatListView.getAdapter();
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean N() {
        Iterator<Chat> iterator = this.v.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            int n2 = iterator.next().o() ? 1 : 0;
            n = n2 + n;
        }
        if (n < this.v.size()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void O() {
        boolean bl;
        Menu menu2;
        block7 : {
            this.a((CharSequence)this.getResources().getString(2131296955, new Object[]{this.v.size()}));
            this.w = true;
            menu2 = this.c();
            if (menu2 == null) {
                return;
            }
            menu2.findItem(2131756846).setVisible(false);
            if (this.r == Chat.Bucket.a) {
                menu2.findItem(2131756847).setVisible(true);
                if (!this.N()) {
                    menu2.findItem(2131756847).setIcon(2130838149);
                } else {
                    menu2.findItem(2131756847).setIcon(2130838148);
                }
            } else {
                menu2.findItem(2131756847).setVisible(false);
            }
            Iterator<Chat> iterator = this.v.iterator();
            while (iterator.hasNext()) {
                if (!(iterator.next() instanceof GroupChat)) continue;
                bl = true;
                break block7;
            }
            bl = false;
        }
        if (bl) {
            menu2.findItem(2131756849).setVisible(true);
            menu2.findItem(2131756848).setVisible(false);
            return;
        }
        menu2.findItem(2131756848).setVisible(true);
        menu2.findItem(2131756849).setVisible(false);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void P() {
        MessageCenterAdapter messageCenterAdapter;
        block5 : {
            block4 : {
                if (!this.isAdded()) break block4;
                this.v.clear();
                messageCenterAdapter = this.M();
                if (messageCenterAdapter != null) {
                    messageCenterAdapter.c();
                }
                this.c(2131296957);
                this.w = false;
                messageCenterAdapter = this.c();
                if (messageCenterAdapter != null && messageCenterAdapter.findItem(2131756846) != null) break block5;
            }
            return;
        }
        messageCenterAdapter.findItem(2131756846).setVisible(true);
        messageCenterAdapter.findItem(2131756847).setVisible(false);
        messageCenterAdapter.findItem(2131756848).setVisible(false);
        messageCenterAdapter.findItem(2131756849).setVisible(false);
    }

    public static MessageCenterFragment a() {
        return MessageCenterFragment_.J().a();
    }

    private void a(TabLayout.Tab tab) {
        this.o.a(true, tab);
    }

    private void a(Chat chat, final SimpleBarrier simpleBarrier) {
        this.s.a(chat, new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                simpleBarrier.a();
            }
        });
    }

    private void b(TabLayout.Tab tab) {
        this.o.a(false, tab);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void A() {
        ChatAnalytics.MessageCenterFilterType messageCenterFilterType = this.r == Chat.Bucket.a ? ChatAnalytics.MessageCenterFilterType.a : ChatAnalytics.MessageCenterFilterType.b;
        ChatAnalytics.a(messageCenterFilterType, this.s.c(this.r));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void F() {
        if (this.isAdded()) {
            SharedPreferences sharedPreferences = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0);
            for (MessageCenterListView messageCenterListView : this.p.a.values()) {
                if (messageCenterListView == null || messageCenterListView.c == null) continue;
                String string2 = this.r == Chat.Bucket.a ? "SHOW_TOOLTIP_HEADER" : "SHOW_TOOLTIP_HEADER_OTHER";
                boolean bl = sharedPreferences.getBoolean(string2, true) && !this.s.b(this.r);
                if (bl) {
                    messageCenterListView.c.setVisibility(0);
                    messageCenterListView.b();
                    continue;
                }
                messageCenterListView.c.setVisibility(8);
            }
        }
    }

    @Click
    protected void G() {
        SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit().putBoolean("TAPPED_ON_NEW_MESSAGE", true).apply();
        ((MasterActivity)this.getActivity()).B();
        this.a(NewChatFragment.a(this));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    public void H() {
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        Object object = this.r == Chat.Bucket.a ? "SHOW_TOOLTIP_HEADER" : "SHOW_TOOLTIP_HEADER_OTHER";
        editor.putBoolean((String)object, false).apply();
        object = this.p.a.values().iterator();
        while (object.hasNext()) {
            MessageCenterListView messageCenterListView = (MessageCenterListView)((Object)object.next());
            if (messageCenterListView.h != this.r) continue;
            messageCenterListView.c.setVisibility(8);
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void I() {
        this.D = true;
        Object object = this.q.keySet().iterator();
        while (object.hasNext()) {
            this.a(object.next());
        }
        object = t != null ? t : this.r;
        this.c((Chat.Bucket)((Object)object));
        t = null;
        this.F();
    }

    protected void a(Chat.Bucket bucket) {
        BucketInfo bucketInfo = this.q.get((Object)bucket);
        if (bucketInfo != null) {
            bucketInfo.b.a(this.s.a(bucket));
        }
        if (this.E == bucket) {
            this.c(this.s.b(this.r));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Chat.Bucket bucket, ChatListView chatListView, SwipeRefreshLayout swipeRefreshLayout) {
        if (bucket == Chat.Bucket.a && !this.q.containsKey((Object)Chat.Bucket.a)) {
            this.q.put(Chat.Bucket.a, new BucketInfo(0, chatListView, swipeRefreshLayout, this.getResources().getDrawable(2130837685), 2131296952));
        } else if (bucket == Chat.Bucket.b && !this.q.containsKey((Object)Chat.Bucket.b)) {
            this.q.put(Chat.Bucket.b, new BucketInfo(1, chatListView, swipeRefreshLayout, this.getResources().getDrawable(2130837686), 2131296953));
        }
        if (bucket == this.E) {
            this.E = null;
            this.c(bucket);
        } else {
            this.b(bucket);
        }
        this.a(bucket);
    }

    @Override
    public void a(Chat chat) {
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(ChatManager connectionStatus) {
        block6 : {
            block5 : {
                boolean bl;
                boolean bl2 = true;
                if (!this.isAdded()) return;
                if (this.c() == null) break block5;
                MenuItem menuItem = this.c().findItem(2131756846);
                if (menuItem != null) {
                    bl = connectionStatus == ChatManager.e;
                    menuItem.setEnabled(bl);
                }
                menuItem = this.l;
                bl = connectionStatus == ChatManager.e ? bl2 : false;
                menuItem.setEnabled(bl);
                if (connectionStatus == ChatManager.e) break block6;
            }
            return;
        }
        if (this.h.getVisibility() == 0) {
            this.J();
        }
        this.h.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(Chat.Bucket bucket) {
        BucketInfo bucketInfo;
        TabLayout.Tab tab;
        block11 : {
            block10 : {
                int n;
                bucketInfo = this.q.get((Object)bucket);
                if (bucketInfo == null) break block10;
                int n2 = bucket == this.r ? 1 : 0;
                if (n2 == 0) {
                    tab = this.s.a(bucket).iterator();
                    n2 = 0;
                    do {
                        n = n2++;
                        if (tab.hasNext()) {
                            Chat chat = (Chat)tab.next();
                            if (!chat.n() || chat.v()) continue;
                            continue;
                        }
                        break;
                    } while (true);
                } else {
                    n = 0;
                }
                if ((tab = this.n.getTabAt(bucketInfo.a)) == null) return;
                bucketInfo = (TextView)tab.getCustomView().findViewById(2131756755);
                tab = (ImageView)tab.getCustomView().findViewById(2131756756);
                if (bucket == Chat.Bucket.a || n == 0) {
                    if (n == 0) {
                        bucketInfo.setVisibility(8);
                        tab.setVisibility(8);
                        return;
                    }
                    bucketInfo.setText((CharSequence)MiscUtils.a((int)n));
                    bucketInfo.setVisibility(0);
                    tab.setVisibility(8);
                    return;
                }
                if (n > 0) break block11;
            }
            return;
        }
        bucketInfo.setVisibility(8);
        tab.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void b_(int n) {
        Chat.Bucket bucket;
        switch (n) {
            default: {
                bucket = Chat.Bucket.b;
                break;
            }
            case 0: {
                bucket = Chat.Bucket.a;
                break;
            }
            case 1: {
                bucket = Chat.Bucket.b;
            }
        }
        if (this.r != bucket) {
            this.P();
            this.c(bucket);
            ChatAnalytics.MessageCenterFilterType messageCenterFilterType = bucket == Chat.Bucket.a ? ChatAnalytics.MessageCenterFilterType.a : ChatAnalytics.MessageCenterFilterType.b;
            ChatAnalytics.b(messageCenterFilterType, this.s.c(bucket));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void c(Chat.Bucket bucket) {
        boolean bl = false;
        Object object = this.q.get((Object)bucket);
        if (object == null) {
            this.E = bucket;
            return;
        }
        this.r = bucket;
        this.n.getTabAt(object.a).select();
        object = !this.s.b(bucket) ? bucket : null;
        this.s.d(bucket);
        this.s.e(bucket);
        this.a(bucket);
        for (Map.Entry<Chat.Bucket, BucketInfo> entry : this.q.entrySet()) {
            SwipeRefreshLayout swipeRefreshLayout = entry.getValue().c;
            int n = entry.getKey() == object ? 0 : 8;
            swipeRefreshLayout.setVisibility(n);
        }
        if (object == null) {
            bl = true;
        }
        this.c(bl);
        this.b(bucket);
        bucket = bucket == Chat.Bucket.a ? Chat.Bucket.b : Chat.Bucket.a;
        this.b(bucket);
        if (this.p == null) return;
        this.F();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(boolean bl) {
        BucketInfo bucketInfo = this.q.get((Object)this.r);
        if (bl) {
            this.i.setVisibility(0);
            if (bucketInfo == null) return;
            {
                this.j.setImageDrawable(bucketInfo.d);
                this.k.setText(bucketInfo.e);
                return;
            }
        } else {
            this.i.setVisibility(8);
            if (bucketInfo == null) return;
            {
                bucketInfo.c.setVisibility(0);
                return;
            }
        }
    }

    @Override
    public boolean d() {
        if (this.w) {
            this.P();
            return true;
        }
        return super.d();
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        block3 : {
            block2 : {
                super.onCreate(bundle);
                this.a(BaseFragment.ActionBarHighlightMode.b);
                this.b_(true);
                this.s = SingApplication.k();
                this.u = new ConnectionStatusIndicator((Context)this.getActivity(), this.s);
                t = null;
                bundle = SingApplication.g().getSharedPreferences(MasterActivity.class.getName(), 0);
                boolean bl = bundle.contains("SHOW_TOOLTIP_HEADER");
                if (bundle.contains("SHOW_TOOLTIP_HEADER_OTHER")) break block2;
                if (bl) break block3;
                bundle.edit().putBoolean("SHOW_TOOLTIP_HEADER", true).apply();
                bundle.edit().putBoolean("SHOW_TOOLTIP_HEADER_OTHER", true).apply();
            }
            return;
        }
        bundle.edit().putBoolean("SHOW_TOOLTIP_HEADER_OTHER", false).apply();
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        menuInflater.inflate(2131820553, menu2);
        new Handler().post(new Runnable(){

            @Override
            public void run() {
                View view = menu2.findItem(2131756846).getActionView();
                view.getLocationOnScreen(MessageCenterFragment.this.z);
                int n = MessageCenterFragment.this.z[0];
                int n2 = view.getWidth() / 2;
                int n3 = MessageCenterFragment.this.z[1];
                if (MessageCenterFragment.this.isAdded()) {
                    ((MasterActivity)MessageCenterFragment.this.getActivity()).a(MessageCenterFragment.this, n + n2, n3, MessageCenterFragment.this.A, MessageCenterFragment.this.B);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        t = null;
        this.p = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.o = null;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int n, long l) {
        if (((Chat)adapterView.getItemAtPosition(n)).d() == Chat.ChatState.a) {
            return;
        }
        if (this.w) {
            this.onItemLongClick(adapterView, view, n, l);
            return;
        }
        this.a(ChatFragment.a((Chat)adapterView.getItemAtPosition(n)));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean onItemLongClick(AdapterView<?> object, View view, int n, long l) {
        synchronized (this) {
            object = (Chat)object.getItemAtPosition(n);
            Chat.ChatState chatState = object.d();
            Chat.ChatState chatState2 = Chat.ChatState.a;
            if (chatState != chatState2) {
                boolean bl;
                block11 : {
                    for (n = 0; n < this.v.size(); ++n) {
                        if (!this.v.get(n).c().equals(object.c())) continue;
                        bl = true;
                        break block11;
                    }
                    bl = false;
                }
                if (bl) {
                    ((MessageCenterAdapter.ViewHolder)view.getTag()).a();
                    this.v.remove(n);
                    if (!this.v.isEmpty()) {
                        this.M().b(object.c());
                    }
                } else {
                    ((MessageCenterAdapter.ViewHolder)view.getTag()).a();
                    this.v.add((Chat)object);
                    this.M().a(object.c());
                }
                if (this.v.isEmpty()) {
                    this.P();
                } else {
                    ChatAnalytics.b();
                    this.O();
                }
            }
            return true;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onOptionsItemSelected(MenuItem iterator) {
        if (this.s.b() != ChatManager.e) {
            return super.onOptionsItemSelected(iterator);
        }
        switch (iterator.getItemId()) {
            case 2131756846: {
                this.G();
                return true;
            }
            case 2131756847: {
                final boolean bl = this.N();
                iterator = this.v.iterator();
                while (iterator.hasNext()) {
                    final Chat chat = iterator.next();
                    chat.a(bl, new Completion<ChatStatus>(){

                        @Override
                        public void a(ChatStatus chatStatus) {
                            if (chatStatus == ChatStatus.a) {
                                new Handler(Looper.getMainLooper()).post(new Runnable(){

                                    /*
                                     * Enabled aggressive block sorting
                                     */
                                    @Override
                                    public void run() {
                                        Chat chat2;
                                        MessageCenterFragment.this.q.get((Object)MessageCenterFragment.this.r).b.a(MessageCenterFragment.this.s.a(MessageCenterFragment.this.r));
                                        for (Chat chat2 : MessageCenterFragment.this.v) {
                                            MessageCenterFragment.this.M().a(chat2.c());
                                        }
                                        MessageCenterFragment.this.P();
                                        chat2 = chat;
                                        ChatAnalytics.SettingToggleType settingToggleType = bl ? ChatAnalytics.SettingToggleType.b : ChatAnalytics.SettingToggleType.a;
                                        ChatAnalytics.a(chat2, settingToggleType);
                                    }
                                });
                                return;
                            }
                            ChatUtils.a((Context)MessageCenterFragment.this.getActivity(), 2131296503, chatStatus);
                        }

                    });
                }
                return true;
            }
            case 2131756849: {
                if (this.r != Chat.Bucket.a) {
                    this.K();
                    return true;
                }
                Object object = this.getResources().getString(2131296481);
                object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
                object.a(2131296696, 2131296672);
                object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object)){
                    final /* synthetic */ TextAlertDialog a;
                    {
                        this.a = textAlertDialog;
                    }

                    @Override
                    public void a(CustomAlertDialog customAlertDialog) {
                        MessageCenterFragment.this.K();
                    }

                    @Override
                    public void b(CustomAlertDialog customAlertDialog) {
                        this.a.dismiss();
                    }
                });
                object.show();
            }
            default: {
                return super.onOptionsItemSelected(iterator);
            }
            case 2131756848: 
        }
        if (this.r == Chat.Bucket.a) {
            Object object = this.getResources().getString(2131296482);
            object = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296466), (String)object);
            object.a(2131296677, 2131296672);
            object.a(new CustomAlertDialog.CustomAlertDialogListener((TextAlertDialog)((Object)object)){
                final /* synthetic */ TextAlertDialog a;
                {
                    this.a = textAlertDialog;
                }

                @Override
                public void a(CustomAlertDialog customAlertDialog) {
                    MessageCenterFragment.this.K();
                }

                @Override
                public void b(CustomAlertDialog customAlertDialog) {
                    this.a.dismiss();
                }
            });
            object.show();
            return super.onOptionsItemSelected(iterator);
        }
        this.K();
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        this.u.c();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.u.b();
        this.D = false;
        if (this.s.b() == ChatManager.e) {
            this.I();
            return;
        }
        this.s.a(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.C) {
            this.t();
        }
        ChatAnalyticsMonitor.a().b();
        this.c(2131296957);
        this.s.a(this.y);
        this.s.a(this.x);
        this.h.setVisibility(0);
        ((MasterActivity)this.getActivity()).A();
        this.a(this.s.b());
        this.s.e(this.r);
    }

    @Override
    public void onStop() {
        super.onStop();
        this.c(2131296957);
        this.s.b(this.y);
        this.s.b(this.x);
        ((MasterActivity)this.getActivity()).B();
        this.P();
        SingApplication.d().a(g);
        this.s.e((Chat.Bucket)null);
    }

    @AfterViews
    protected void t() {
        this.p = new MessageCenterPagerAdapter();
        this.m.setAdapter((PagerAdapter)this.p);
        this.o = new SingTabLayoutHelper(this.n, this.m);
        this.o.a(true);
        this.o.a(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab) {
                MessageCenterFragment.this.a(tab);
            }

            public void onTabSelected(TabLayout.Tab tab) {
                MessageCenterFragment.this.b_(tab.getPosition());
                MessageCenterFragment.this.a(tab);
            }

            public void onTabUnselected(TabLayout.Tab tab) {
                MessageCenterFragment.this.b(tab);
            }
        });
    }

    @Override
    public String x() {
        return g;
    }

    protected class BucketInfo {
        final int a;
        final ChatListView b;
        final SwipeRefreshLayout c;
        final Drawable d;
        final int e;

        public BucketInfo(int n, ChatListView chatListView, SwipeRefreshLayout swipeRefreshLayout, Drawable drawable2, int n2) {
            this.a = n;
            this.b = chatListView;
            this.c = swipeRefreshLayout;
            this.d = drawable2;
            this.e = n2;
        }
    }

    private class MessageCenterPagerAdapter
    extends PagerAdapter {
        protected Map<Integer, MessageCenterListView> a;

        private MessageCenterPagerAdapter() {
            this.a = new HashMap<Integer, MessageCenterListView>();
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            if (object == null) {
                return;
            }
            object = (MessageCenterListView)((Object)object);
            viewGroup.removeView((View)object);
            if (object.a != null) {
                object.a.setAdapter(null);
            }
            MessageCenterFragment.this.q.remove((Object)object.h);
            this.a.remove(n);
        }

        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int n) {
            switch (n) {
                default: {
                    return MessageCenterFragment.this.getResources().getString(2131296954);
                }
                case 0: 
            }
            return MessageCenterFragment.this.getResources().getString(2131296950);
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            if (this.a.containsKey(n)) {
                return null;
            }
            Object object = n == 0 ? Chat.Bucket.a : Chat.Bucket.b;
            object = MessageCenterListView.a((Context)MessageCenterFragment.this.getActivity(), MessageCenterFragment.this, (Chat.Bucket)((Object)object));
            object.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
            viewGroup.addView((View)object);
            this.a.put(n, (MessageCenterListView)((Object)object));
            return object;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
            MessageCenterFragment.this.C = false;
            super.restoreState(parcelable, classLoader);
        }

        public Parcelable saveState() {
            Iterator<MessageCenterListView> iterator = this.a.values().iterator();
            while (iterator.hasNext()) {
                iterator.next().a.setAdapter(null);
            }
            this.a.clear();
            MessageCenterFragment.this.q.clear();
            MessageCenterFragment.this.C = true;
            return super.saveState();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void setPrimaryItem(ViewGroup object, int n, Object iterator) {
            if (MessageCenterFragment.this.isAdded() && (object = (MessageCenterListView)MessageCenterFragment.this.m.findViewWithTag((Object)("sb_item#" + n))) != null) {
                for (MessageCenterListView messageCenterListView : this.a.values()) {
                    if (messageCenterListView == object) continue;
                    messageCenterListView.a.setOnScrollListener(null);
                }
            }
        }
    }

}

