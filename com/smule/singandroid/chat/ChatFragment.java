/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.database.DataSetObserver
 *  android.graphics.Rect
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.IBinder
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.util.DisplayMetrics
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.AnimationSet
 *  android.view.animation.Interpolator
 *  android.view.animation.OvershootInterpolator
 *  android.view.animation.TranslateAnimation
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.AbsListView
 *  android.widget.AbsListView$LayoutParams
 *  android.widget.AbsListView$OnScrollListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ListAdapter
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.foound.widget.AmazingAdapter
 *  com.foound.widget.AmazingListView
 *  com.nostra13.universalimageloader.core.ImageLoader
 *  com.nostra13.universalimageloader.core.listener.PauseOnScrollListener
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$EntryPoint
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.chat;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.foound.widget.AmazingAdapter;
import com.foound.widget.AmazingListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.bookmark.MediaPlayingMenuManager;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalyticsMonitor;
import com.smule.singandroid.chat.ChatDetailsFragment;
import com.smule.singandroid.chat.ChatFragment_;
import com.smule.singandroid.chat.ChatNotification;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.chat.message_aggregation.ChatMessageAggregator;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessageGroupInviteListItem;
import com.smule.singandroid.chat.message_views.ChatMessageGroupStatusListItem;
import com.smule.singandroid.chat.message_views.ChatMessageListItemInterface;
import com.smule.singandroid.chat.message_views.ChatMessagePerformanceBody;
import com.smule.singandroid.chat.message_views.ChatMessagePerformanceListItem;
import com.smule.singandroid.chat.message_views.ChatMessageTextListItem;
import com.smule.singandroid.chat.message_views.ChatMessageUnknownListItem;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class ChatFragment
extends ChatActivatorFragment
implements ChatMessageBaseListItem.ChatMessageViewListener {
    public static final String g = ChatFragment.class.getName();
    protected AnimationSet A;
    protected AnimationSet B;
    protected AnimationSet C;
    boolean D;
    String E;
    protected com.smule.chat.ChatManager F;
    boolean G;
    boolean H;
    WeakListener.OnGlobalLayoutListener I;
    ChatListener J;
    DataSetObserver K;
    private TranslateAnimation N;
    private TranslateAnimation O;
    @ViewById
    protected RelativeLayout h;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected View k;
    @ViewById
    protected View l;
    @ViewById
    protected EditText m;
    @ViewById
    protected AmazingListView n;
    @ViewById
    protected RelativeLayout o;
    @ViewById
    protected RelativeLayout p;
    @ViewById
    protected ProfileImageWithVIPBadge q;
    @ViewById
    protected TextView r;
    @ViewById
    protected ImageView s;
    @ViewById
    protected ProgressBar t;
    @ViewById
    protected TextView u;
    @ViewById
    protected LinearLayout v;
    @ViewById
    protected ImageView w;
    @ViewById
    protected View x;
    @ViewById
    protected ProfileImageWithVIPBadge y;
    protected ChatAdapter z;

    public ChatFragment() {
        this.J = new ChatListenerAdapter(){

            @Override
            public void a(Chat chat) {
                Log.b(ChatFragment.g, "onHistoryUpdated");
                ChatFragment.this.z.m();
                if (chat.j().size() <= 3 && chat.t()) {
                    ChatFragment.this.z.n();
                }
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(Chat chat, ChatMessage chatMessage) {
                block14 : {
                    block13 : {
                        if (!ChatFragment.this.isAdded()) break block13;
                        Log.b(ChatFragment.g, "onMessageUpdated: " + chatMessage + " state: " + (Object)((Object)chatMessage.d()));
                        int n = -1;
                        for (int i = 0; i < ChatFragment.this.n.getChildCount(); ++i) {
                            Object object = ChatFragment.this.n.getChildAt(i);
                            int n2 = n;
                            if (object instanceof ChatMessageListItemInterface) {
                                object = (ChatMessageListItemInterface)object;
                                ChatMessage chatMessage2 = object.getMessage();
                                n2 = n;
                                if (n == -1) {
                                    n2 = ChatFragment.this.n.getFirstVisiblePosition() + i - ChatFragment.this.n.getHeaderViewsCount();
                                }
                                if (ChatFragment.this.z.d(n2) != chatMessage2) {
                                    Log.b(ChatFragment.g, "Wrong message: itemAdapterPosition: " + n2 + " mChatAdapter.getCount(): " + ChatFragment.this.z.getCount());
                                } else {
                                    if (chatMessage2.equals(chatMessage)) {
                                        object.b(chat, chatMessage, n2);
                                    } else if (ChatFragment.this.E != null && chatMessage2.f().equals(ChatFragment.this.E)) {
                                        object.b(chat, chatMessage2, n2);
                                    }
                                    ++n2;
                                }
                            }
                            n = n2;
                        }
                        if (chatMessage.e() == ChatStatus.f) {
                            Toaster.a((Context)ChatFragment.this.getActivity(), 2131296506);
                        }
                        if (chat instanceof PeerChat) {
                            ChatFragment.this.E = ((PeerChat)chat).S();
                        }
                        if (ChatFragment.this.n.getLastVisiblePosition() == ChatFragment.this.n.getCount() - 1) break block14;
                    }
                    return;
                }
                ChatFragment.this.Q();
            }

            @Override
            public void a(Chat chat, ChatMessage chatMessage, boolean bl) {
                Log.b(ChatFragment.g, "onNewMessage");
                if (chat != ChatFragment.this.M) {
                    return;
                }
                ChatFragment.this.z.a(chatMessage, bl);
            }

            @Override
            public void b(Chat chat) {
                ChatFragment.this.L();
            }

            @Override
            public void b(Chat chat, ChatMessage chatMessage) {
                this.a(chat);
            }

            @Override
            public void c(Chat chat) {
                ChatFragment.this.P();
            }
        };
        this.K = new DataSetObserver(){

            public void onChanged() {
                super.onChanged();
                ChatFragment.this.J();
            }
        };
    }

    private boolean U() {
        if (!this.m.getText().toString().replace(" ", "").replace("\n", "").isEmpty()) {
            return true;
        }
        return false;
    }

    private void V() {
        this.N = new TranslateAnimation(1, 0.0f, 1, 0.0f, 0, (float)(this.W() * -1), 0, 0.0f);
        this.N.setDuration((long)800);
        this.N.setFillAfter(true);
        this.N.setInterpolator((Interpolator)new OvershootInterpolator(1.25f));
        this.N.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                ChatFragment.this.v.setVisibility(0);
            }
        });
        this.O = new TranslateAnimation(1, 0.0f, 1, 0.0f, 0, (float)(this.W() * -1), 0, 0.0f);
        this.O.setDuration((long)800);
        this.O.setFillAfter(true);
        this.O.setInterpolator((Interpolator)new ReverseOverShootInterpolator(1.25f));
        this.O.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
                ChatFragment.this.v.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    private int W() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void X() {
        this.B = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        translateAnimation.setDuration(500);
        this.B.addAnimation((Animation)translateAnimation);
        this.B.addAnimation((Animation)alphaAnimation);
        this.B.setStartOffset(1000);
        this.B.setFillAfter(true);
        this.A = new AnimationSet(true);
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.5f, 1, 0.0f);
        translateAnimation.setDuration(500);
        this.A.addAnimation((Animation)translateAnimation);
        this.A.addAnimation((Animation)alphaAnimation);
        this.A.setFillAfter(true);
        this.A.setAnimationListener(new Animation.AnimationListener(){

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                ChatFragment.this.C = new AnimationSet(true);
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float)(ChatFragment.this.o.getHeight() * -1));
                animation.setDuration(500);
                ChatFragment.this.C.addAnimation(animation);
                ChatFragment.this.C.setFillAfter(true);
                ChatFragment.this.C.setStartOffset(2000);
                ChatFragment.this.o.startAnimation((Animation)ChatFragment.this.C);
            }
        });
    }

    public static ChatFragment a(Chat chat) {
        ChatFragment chatFragment = ChatFragment_.U().a();
        chatFragment.e(chat);
        return chatFragment;
    }

    private String a(String string2, String string3) {
        int n;
        for (n = string2.indexOf((String)string3) + string3.length(); n < string2.length() && Character.isSpaceChar(string2.charAt(n)); ++n) {
        }
        if (n < string2.length()) {
            return string2.substring(n);
        }
        return null;
    }

    public static ChatFragment c(String string2) {
        ChatFragment chatFragment = ChatFragment_.U().a();
        chatFragment.e(string2);
        return chatFragment;
    }

    public static ChatFragment d(String string2) {
        ChatFragment chatFragment = ChatFragment_.U().a();
        chatFragment.f(string2);
        return chatFragment;
    }

    private String g(String string2) {
        try {
            Uri uri = Uri.parse((String)string2);
            if ((uri.getScheme().equals("http") || uri.getScheme().equals("https")) && uri.getHost().equals("www.smule.com") && Pattern.compile("[1-9][0-9]*_[0-9]*").matcher(string2).find()) {
                string2 = this.h(uri.getLastPathSegment());
                return string2;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String h(String string2) {
        int n;
        int n2 = string2 == null ? 0 : string2.length();
        for (n = 0; n < n2 && (string2.charAt(n) == '_' || Character.isDigit(string2.charAt(n))); ++n) {
        }
        if (n > 0) {
            return string2.substring(0, n);
        }
        return null;
    }

    public void F() {
        if (this.F.b() != ChatManager.e || this.M == null) {
            return;
        }
        if (this.O()) {
            this.a(ChatDetailsFragment.a(this.M));
            return;
        }
        final TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getResources().getString(2131296531), this.getResources().getString(2131296530), true, true);
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                textAlertDialog.dismiss();
                final BusyScreenDialog busyScreenDialog = new BusyScreenDialog((Context)ChatFragment.this.getActivity(), 2131296483);
                busyScreenDialog.show();
                SingApplication.k().a(ChatFragment.this.M, new Completion<ChatStatus>(){

                    @Override
                    public void a(ChatStatus chatStatus) {
                        busyScreenDialog.dismiss();
                        ChatFragment.this.I();
                        Toaster.a((Context)ChatFragment.this.getActivity(), 2131296527);
                    }
                });
            }

        });
        textAlertDialog.show();
    }

    protected void G() {
        this.G = true;
        if (this.z.getCount() == 0 || this.H) {
            this.z.n();
            this.J();
        }
        if (this.x.getVisibility() == 0) {
            this.x.setVisibility(8);
        }
    }

    protected void I() {
        if (!this.isAdded()) {
            return;
        }
        this.getFragmentManager().popBackStack();
    }

    /*
     * Enabled aggressive block sorting
     */
    @SupposeUiThread
    protected void J() {
        block5 : {
            block4 : {
                if (!this.isAdded()) break block4;
                if (this.z.getCount() != 0 || this.M == null || this.M.u()) {
                    this.v.setVisibility(8);
                    this.v.clearAnimation();
                    return;
                }
                if (this.v.getVisibility() != 0 && this.M.a() != Chat.Type.b) break block5;
            }
            return;
        }
        this.v.setVisibility(0);
        this.v.clearAnimation();
        this.v.startAnimation((Animation)this.N);
    }

    protected void K() {
        if (this.M == null || !this.isAdded()) {
            return;
        }
        this.J();
        this.P();
        this.L();
        this.M();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void L() {
        if (!this.isAdded()) {
            return;
        }
        int n = this.M.o() ? 2130837962 : 0;
        this.a(ChatUtils.a(this.M), null, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void M() {
        if (this.M.b() == Chat.Bucket.b) {
            this.o.setVisibility(0);
            this.y.setVisibility(8);
            Object object = this.M.a(this.M.f());
            this.q.setAccount((AccountIcon)object);
            object = object != null && object.handle != null ? object.handle : "";
            this.r.setText((CharSequence)this.getResources().getString(2131296522, new Object[]{object}));
            this.s.setOnClickListener(new View.OnClickListener((String)object){
                final /* synthetic */ String a;
                {
                    this.a = string2;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View object) {
                    ChatFragment.this.s.setVisibility(8);
                    ChatFragment.this.t.setVisibility(0);
                    long l = ChatFragment.this.M.f();
                    object = FollowManager.a().a(l) ? Analytics.b : Analytics.a;
                    com.smule.android.logging.Analytics.a(object, l);
                    FollowManager.a().a((Long)l, new FollowManager.ToggleFollowStateListener(){

                        @Override
                        public void a(final boolean bl, boolean bl2, final boolean bl3) {
                            ChatFragment.this.a(new Runnable(){

                                @Override
                                public void run() {
                                    if (!ChatFragment.this.isAdded()) {
                                        return;
                                    }
                                    ChatFragment.this.t.setVisibility(8);
                                    if (bl) {
                                        ChatFragment.this.r.setText((CharSequence)ChatFragment.this.getResources().getString(2131296523, new Object[]{11.this.a}));
                                        ChatFragment.this.p.startAnimation((Animation)ChatFragment.this.B);
                                        new Handler().postDelayed(new Runnable(){

                                            @Override
                                            public void run() {
                                                if (!ChatFragment.this.isAdded()) {
                                                    return;
                                                }
                                                ChatFragment.this.u.setVisibility(0);
                                                ChatFragment.this.u.startAnimation((Animation)ChatFragment.this.A);
                                            }
                                        }, 1250);
                                        return;
                                    }
                                    if (bl3) {
                                        ChatFragment.this.s.setVisibility(0);
                                        ChatFragment.this.b(2131297196);
                                        return;
                                    }
                                    ChatFragment.this.s.setVisibility(0);
                                    ChatFragment.this.b(2131297210);
                                }

                            });
                        }

                    });
                }

            });
            return;
        } else {
            this.o.setVisibility(8);
            if (this.M.a() != Chat.Type.a) return;
            {
                this.y.setVisibility(0);
                this.y.setAccount(this.M.a(this.M.f()));
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void N() {
        boolean bl = false;
        if (!this.isAdded() || this.D) {
            return;
        }
        this.D = true;
        List<ChatMessage> list = this.M.j();
        boolean bl2 = this.M instanceof GroupChat && list.size() <= this.M.g().size();
        if (bl2) {
            Iterator<ChatMessage> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().a() == ChatMessage.Type.g) continue;
                bl2 = false;
                break;
            }
        }
        boolean bl3 = bl;
        if (this.M instanceof PeerChat) {
            bl3 = bl;
            if (list.size() == 0) {
                bl3 = true;
            }
        }
        if (!bl3 && !bl2) {
            ((MediaPlayingActivity)this.getActivity()).aa();
            return;
        }
        ((InputMethodManager)this.getActivity().getSystemService("input_method")).showSoftInput((View)this.m, 1);
    }

    protected boolean O() {
        if (!(this.M instanceof GroupChat)) {
            return true;
        }
        long l = UserManager.a().f();
        switch (.b[((GroupChat)this.M).b(l).ordinal()]) {
            default: {
                return false;
            }
            case 1: 
            case 2: 
        }
        return true;
    }

    protected void P() {
        if (!this.isAdded()) {
            return;
        }
        if (!this.O()) {
            this.l.setVisibility(8);
            this.k.setVisibility(8);
            MiscUtils.a((Activity)this.getActivity(), (boolean)true);
            return;
        }
        this.l.setVisibility(0);
        this.k.setVisibility(0);
    }

    protected void Q() {
        if (!this.isAdded()) {
            return;
        }
        this.n.setSelection(this.n.getLastVisiblePosition());
    }

    @Click
    protected void R() {
        String string2;
        block3 : {
            block2 : {
                string2 = this.m.getText().toString();
                if (!this.U()) break block2;
                this.m.setText((CharSequence)"");
                final String string3 = this.g(string2);
                if (string3 == null) break block3;
                com.smule.android.network.managers.PerformanceManager.a().a(string3, false, new PerformanceManager(){

                    @Override
                    public void handleResponse(PerformanceManager.PerformanceResponse object) {
                        if (object.a() && object.mPerformance != null) {
                            ChatFragment.this.M.a(new PerformanceChatMessage(object.mPerformance));
                            object = ChatFragment.this.a(string2, string3);
                            if (object != null) {
                                ChatFragment.this.M.a(new TextChatMessage((String)object));
                            }
                            return;
                        }
                        ChatFragment.this.M.a(new TextChatMessage(string2));
                    }
                });
            }
            return;
        }
        this.M.a(new TextChatMessage(string2));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected boolean S() {
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

    @Click
    protected void T() {
        ((MediaPlayingActivity)this.getActivity()).am().a(this.i);
    }

    @Override
    public void a(AccountIcon accountIcon) {
        this.a(ProfileFragment.a(accountIcon));
    }

    @Override
    public void a(Chat chat, ChatStatus chatStatus) {
        super.a(chat, chatStatus);
        if (!chatStatus.a()) {
            this.G();
            return;
        }
        this.z.e();
        this.K();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public void a(ChatMessage chatMessage) {
        if (chatMessage.d() != ChatMessage.State.d || !ChatUtils.a(chatMessage)) return;
        switch (.a[chatMessage.e().ordinal()]) {
            default: {
                Toaster.a((Context)this.getActivity(), 2131296508);
            }
            case 6: {
                return;
            }
            case 1: 
            case 5: {
                this.M.a(chatMessage);
                return;
            }
            case 2: 
            case 3: 
            case 4: 
        }
        ChatUtils.a((Context)this.getActivity(), chatMessage.e());
    }

    @Override
    public void a(ChatStatus chatStatus) {
        super.a(chatStatus);
        this.x.setVisibility(8);
        this.z.e();
        switch (chatStatus) {
            default: 
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(GroupInvitationChatMessage groupInvitationChatMessage) {
        com.smule.chat.ChatManager chatManager = SingApplication.k();
        String string2 = groupInvitationChatMessage.o();
        if (string2 == null || string2.isEmpty()) {
            throw new NullPointerException("Expected JID in group chat invite but it was NULL! NULL I tell you!");
        }
        Object object = groupInvitationChatMessage.b(UserManager.a().f());
        int n = ChatUtils.a(groupInvitationChatMessage) ? 2131296549 : 2131296548;
        Object object2 = this.getString(n);
        object2 = new TextAlertDialog((Context)this.getActivity(), (String)object2, null, true, false);
        object2.a(2131296705, 0);
        if (object == GroupMemberStatus.a) {
            object2.show();
            return;
        }
        if (!this.S()) {
            ChatUtils.a((BaseFragment)this, string2);
            return;
        }
        object = new BusyDialog(this.getActivity(), this.getResources().getString(2131296554));
        object.show();
        chatManager.a(string2, new ChatManager((BusyDialog)((Object)object), ChatUtils.a(UserManager.a().f(), string2), groupInvitationChatMessage){
            final /* synthetic */ BusyDialog a;
            final /* synthetic */ boolean b;
            final /* synthetic */ GroupInvitationChatMessage c;
            {
                this.a = busyDialog;
                this.b = bl;
                this.c = groupInvitationChatMessage;
            }

            @Override
            public void a(Chat chat, ChatStatus chatStatus) {
                if (!ChatFragment.this.isAdded()) {
                    return;
                }
                this.a.hide();
                if (chatStatus != ChatStatus.a) {
                    ChatUtils.a((Context)ChatFragment.this.getActivity(), 2131296502, chatStatus);
                    return;
                }
                if (!this.b) {
                    boolean bl = FollowManager.a().a(this.c.b());
                    ChatAnalytics.a(this.c.b(), bl, chat.c());
                }
                ChatFragment.this.a(ChatFragment.a(chat));
            }
        });
    }

    void a(ChatMessagePerformanceListItem linearLayout) {
        linearLayout = linearLayout.getBody();
        linearLayout.setListener(new PerformanceItemInterface.PerformanceItemListener(){

            private void a(PerformanceV2 performanceV2, boolean bl) {
                ChatFragment.this.c(true);
                ((MediaPlayingActivity)ChatFragment.this.getActivity()).a(performanceV2, bl, true, null, -1, null, true, 2131820547, new NowPlayingFragment.NowPlayingFragmentMenuSelectedCallback(){

                    @Override
                    public boolean a(MenuItem menuItem) {
                        return ChatFragment.this.onOptionsItemSelected(menuItem);
                    }
                }, -1);
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                ChatFragment.this.a(accountIcon);
            }

            @Override
            public void a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                Log.b(ChatFragment.g, "mPerformanceItemListener - onItemClicked called");
                this.a(performanceV2, true);
            }

            @Override
            public void b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                Log.b(ChatFragment.g, "mPerformanceItemListener - onAlbumArtClicked called");
                this.a(performanceV2, MiscUtils.a((PerformanceV2)performanceV2));
            }

        });
        linearLayout.setOnJoinListener(new View.OnClickListener((ChatMessagePerformanceBody)linearLayout){
            final /* synthetic */ ChatMessagePerformanceBody a;
            {
                this.a = chatMessagePerformanceBody;
            }

            public void onClick(View view) {
                PreSingActivity.a((Context)ChatFragment.this.getActivity()).a(PreSingActivity.StartupMode.b).a(this.a.getPerformance()).a(PreSingActivity.EntryPoint.c).a();
            }
        });
        linearLayout.setOnMoreListener(new View.OnClickListener((ChatMessagePerformanceBody)linearLayout){
            final /* synthetic */ ChatMessagePerformanceBody a;
            {
                this.a = chatMessagePerformanceBody;
            }

            public void onClick(View object) {
                object = (MediaPlayingActivity)ChatFragment.this.getActivity();
                object.am().a(this.a.getPerformance(), new BookmarkDialogCallback((MediaPlayingActivity)object){
                    final /* synthetic */ MediaPlayingActivity a;
                    {
                        this.a = mediaPlayingActivity;
                    }

                    @Override
                    public void a(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)this.a).a(new Runnable(){

                            @Override
                            public void run() {
                                1.this.a.am().a(ChatFragment.this, ChatFragment.this.i, ChatFragment.this.j, true);
                            }
                        });
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", 17.this.a.getPerformance());
                    }

                    @Override
                    public void b(PerformanceV2 performanceV2) {
                        new UiHandler((Activity)this.a).a(new Runnable(){

                            @Override
                            public void run() {
                                1.this.a.am().a(ChatFragment.this, ChatFragment.this.i, ChatFragment.this.j, false);
                            }
                        });
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", 17.this.a.getPerformance());
                    }

                    @Override
                    public void c(PerformanceV2 performanceV2) {
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", 17.this.a.getPerformance());
                    }

                    @Override
                    public void d(PerformanceV2 performanceV2) {
                        NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", 17.this.a.getPerformance());
                    }

                }, false);
            }

        });
    }

    @Override
    public void b(Chat chat) {
        super.b(chat);
        if (this.M instanceof PeerChat && SingApplication.k().a(this.M.f())) {
            this.I();
            return;
        }
        this.M.b(this.J);
        this.M.a(this.J);
        if (this.z.getCount() == 0 && !this.M.j().isEmpty()) {
            this.z.m();
        }
        ChatNotification.b((Context)this.getActivity(), this.M.c());
        MessageCenterFragment.t = this.M.b();
        this.K();
        ChatAnalytics.c(chat);
    }

    @Override
    public void c(Chat chat) {
        super.b(chat);
        this.G();
        this.N();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c(boolean bl) {
        if (bl) {
            ((MediaPlayingActivity)this.getActivity()).ab().setVisibility(0);
            ((MediaPlayingActivity)this.getActivity()).aa();
        } else {
            ((MediaPlayingActivity)this.getActivity()).ab().setVisibility(4);
            ((MediaPlayingActivity)this.getActivity()).w();
        }
        if (this.getActivity() instanceof MasterActivity) {
            ((MasterActivity)this.getActivity()).J();
        }
    }

    @Override
    public boolean d() {
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        this.c(true);
        return super.d();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.M == null || this.M.b() != Chat.Bucket.b) return;
        {
            if (configuration.hardKeyboardHidden == 1) {
                this.o.setVisibility(8);
                this.y.setVisibility(0);
                this.y.setAccount(this.M.a(this.M.f()));
                return;
            } else {
                if (configuration.hardKeyboardHidden != 2) return;
                {
                    this.o.setVisibility(0);
                    this.v.setVisibility(8);
                    return;
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a(BaseFragment.ActionBarHighlightMode.a);
        this.b_(true);
        this.X();
        this.z = new ChatAdapter((Context)this.getActivity());
        this.F = SingApplication.k();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        if (this.M != null && this.M.v()) {
            return;
        }
        menuInflater.inflate(2131820547, menu2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.M != null && this.M.a() == Chat.Type.a && this.M.k()) {
            SingApplication.k().a(this.M, (Completion<ChatStatus>)null);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.b((View)this.h, (WeakListener.OnGlobalLayoutListener)this.I);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131756842) {
            this.F();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.z.unregisterDataSetObserver(this.K);
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.K();
        this.z.registerDataSetObserver(this.K);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.M != null) {
            this.M.b(this.J);
        }
        ChatAnalyticsMonitor.a().b();
    }

    @AfterViews
    protected void t() {
        if (!this.isAdded()) {
            return;
        }
        this.getActivity().getWindow().setSoftInputMode(16);
        this.V();
        this.m.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                if (n == 4 || n == 0 && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0) {
                    ChatFragment.this.b(new Runnable(){

                        @Override
                        public void run() {
                            ChatFragment.this.Q();
                        }
                    });
                    ChatFragment.this.R();
                    return true;
                }
                Log.b(ChatFragment.g, "actionId: " + n);
                return false;
            }

        });
        WeakListener.a(this.m, new TextWatcher(){

            /*
             * Enabled aggressive block sorting
             */
            public void afterTextChanged(Editable editable) {
                if (!ChatFragment.this.U()) {
                    ChatFragment.this.w.setImageResource(2130837658);
                } else {
                    ChatFragment.this.w.setImageResource(2130837657);
                }
                if (ChatFragment.this.v.getVisibility() == 0 && ChatFragment.this.z.getCount() == 0 && ChatFragment.this.v.getAnimation().hasEnded()) {
                    ChatFragment.this.v.clearAnimation();
                    ChatFragment.this.v.startAnimation((Animation)ChatFragment.this.O);
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        });
        this.m.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                ChatFragment.this.getActivity().getWindow().setSoftInputMode(16);
                return false;
            }
        });
        this.n.setTranscriptMode(1);
        this.n.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 2 || ChatFragment.this.getActivity() == null) {
                    return false;
                }
                ((InputMethodManager)ChatFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(ChatFragment.this.m.getWindowToken(), 0);
                return false;
            }
        });
        this.n.setOnScrollListener((AbsListView.OnScrollListener)new PauseOnScrollListener(ImageLoader.a(), true, true));
        this.n.setLoadingView(this.getActivity().getLayoutInflater().inflate(2130903273, null));
        View view = new View((Context)this.getActivity());
        view.setLayoutParams((ViewGroup.LayoutParams)new AbsListView.LayoutParams(-1, this.getResources().getDimensionPixelOffset(2131428167)));
        this.n.addFooterView(view);
        this.I = new WeakListener.OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                boolean bl = true;
                if (!ChatFragment.this.isAdded()) return;
                if (!ChatFragment.this.m.hasFocus()) {
                    return;
                }
                Object object = new Rect();
                ChatFragment.this.h.getWindowVisibleDisplayFrame((Rect)object);
                int n = ChatFragment.this.h.getRootView().getHeight();
                n = (double)(n - object.bottom) > (double)n * 0.15 ? 1 : 0;
                if (n == 0 && ChatFragment.this.v.getVisibility() == 8 && ChatFragment.this.z.getCount() == 0 && ChatFragment.this.M != null && ChatFragment.this.M.a() != Chat.Type.b) {
                    ChatFragment.this.v.clearAnimation();
                    ChatFragment.this.v.startAnimation((Animation)ChatFragment.this.N);
                }
                object = ChatFragment.this;
                if (n != 0) {
                    bl = false;
                }
                object.c(bl);
            }
        });
        LayoutUtils.a((View)this.h, (WeakListener.OnGlobalLayoutListener)this.I);
        this.n.setAdapter((ListAdapter)this.z);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void w_() {
        int n = 0;
        this.G = false;
        this.H = false;
        if (this.M != null) {
            this.M.b(this.J);
        }
        if (this.M != null && !this.M.j().isEmpty()) {
            this.z.m();
        }
        View view = this.x;
        if (this.z.getCount() != 0) {
            n = 8;
        }
        view.setVisibility(n);
    }

    @Override
    public String x() {
        return g;
    }

    @Override
    public void y_() {
        super.y_();
    }

    public class ChatAdapter
    extends AmazingAdapter {
        public final String h;
        boolean i;
        List<ChatMessage> j;
        ChatMessageAggregator k;
        private final Context m;
        private boolean n;

        public ChatAdapter(Context context) {
            this.h = ChatAdapter.class.getName();
            this.n = true;
            this.j = new ArrayList<ChatMessage>();
            this.k = new ChatMessageAggregator((Context)ChatFragment.this.getActivity());
            this.m = context;
            this.a(true);
        }

        /*
         * Enabled aggressive block sorting
         */
        public View a(int n, View object, ViewGroup object2) {
            object2 = this.d(n);
            if (object2.f().equals(this.j.get(this.j.size() - 1).f()) && this.n) {
                this.n = false;
                this.i = false;
            }
            if (object == null) {
                switch (.c[object2.a().ordinal()]) {
                    default: {
                        throw new RuntimeException("Invalid message type: " + (Object)((Object)object2.a()));
                    }
                    case 1: {
                        object = ChatMessageTextListItem.a(this.m);
                        break;
                    }
                    case 2: {
                        object = ChatMessagePerformanceListItem.a(this.m);
                        ChatFragment.this.a((ChatMessagePerformanceListItem)object);
                        break;
                    }
                    case 3: {
                        object = ChatMessageGroupInviteListItem.a(this.m, ChatFragment.this);
                        break;
                    }
                    case 4: {
                        object = ChatMessageGroupStatusListItem.a(this.m);
                        break;
                    }
                    case 5: 
                    case 6: 
                    case 7: {
                        object = ChatMessageUnknownListItem.a(this.m);
                    }
                }
                object.a(ChatFragment.this, this);
            } else {
                object = (ChatMessageListItemInterface)object;
            }
            object.a(ChatFragment.this.M, (ChatMessage)object2, n);
            if (n == this.getCount() - 1 && this.i) {
                this.i = false;
                object.e();
            }
            return (View)object;
        }

        public AccountIcon a(long l) {
            return ChatFragment.this.M.a(l);
        }

        public void a(ChatMessage chatMessage, boolean bl) {
            if (bl) {
                return;
            }
            if (chatMessage == ChatFragment.this.M.l()) {
                if (ChatFragment.this.n.getLastVisiblePosition() == ChatFragment.this.n.getCount() - 1) {
                    this.i = true;
                }
                this.k.a(ChatFragment.this.M, this.j, chatMessage, ChatFragment.this.J);
                this.notifyDataSetChanged();
                return;
            }
            this.m();
        }

        public void b(String string2) {
            Log.e(this.h, "Unsupported Operation");
        }

        public void c(int n) {
            this.n();
        }

        public ChatMessage d(int n) {
            if (n >= 0 && n < this.j.size()) {
                return this.j.get(n);
            }
            return null;
        }

        public int getCount() {
            return this.j.size();
        }

        public /* synthetic */ Object getItem(int n) {
            return this.d(n);
        }

        public long getItemId(int n) {
            return n;
        }

        public int getItemViewType(int n) {
            ChatMessage chatMessage = this.d(n);
            switch (.c[chatMessage.a().ordinal()]) {
                default: {
                    return this.getViewTypeCount() - 1;
                }
                case 1: 
                case 2: 
                case 3: 
                case 6: {
                    if (!ChatUtils.a(chatMessage)) break;
                    return chatMessage.a().ordinal() + ChatMessage.Type.values().length;
                }
                case 4: 
            }
            return chatMessage.a().ordinal();
        }

        public int getViewTypeCount() {
            return ChatMessage.Type.values().length * 2 + 1;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void l() {
            if (ChatFragment.this.M == null) {
                return;
            }
            this.j = this.k.a(ChatFragment.this.M, ChatFragment.this.M.j(), ChatFragment.this.J);
            if (ChatFragment.this.M.t()) {
                this.g();
            } else {
                this.e();
            }
            if (ChatFragment.this.M instanceof PeerChat) {
                ChatFragment.this.E = ((PeerChat)ChatFragment.this.M).S();
            }
            this.notifyDataSetChanged();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void m() {
            int n;
            int n2 = 0;
            this.i = false;
            View view = null;
            Object object = null;
            int n3 = ChatFragment.this.n.getLastVisiblePosition() == ChatFragment.this.n.getCount() - 1 ? 1 : 0;
            if (n3 == 0) {
                n = 0;
                for (int i = 0; i < ChatFragment.this.n.getCount(); ++i) {
                    view = ChatFragment.this.n.getChildAt(i);
                    if (!(view instanceof ChatMessageBaseListItem)) continue;
                    object = ((ChatMessageBaseListItem)view).getMessage();
                    n = view.getTop();
                }
            } else {
                n = 0;
                object = view;
            }
            this.l();
            if (n3 != 0) {
                ChatFragment.this.n.smoothScrollToPosition(ChatFragment.this.n.getCount() - 1);
                return;
            }
            if (object == null) return;
            n3 = n2;
            while (n3 < this.j.size()) {
                if (this.j.get(n3).equals(object)) {
                    ChatFragment.this.n.setSelectionFromTop(ChatFragment.this.n.getHeaderViewsCount() + n3, n);
                    return;
                }
                ++n3;
            }
            return;
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void n() {
            if (ChatFragment.this.M == null || ChatFragment.this.M.u()) {
                return;
            }
            if (!ChatFragment.this.G) {
                ChatFragment.this.H = true;
                return;
            }
            if (ChatFragment.this.M.t()) {
                ChatFragment.this.M.s();
                return;
            }
            this.e();
        }

        public List<ChatMessage> o() {
            return this.j;
        }
    }

    private class ReverseOverShootInterpolator
    extends OvershootInterpolator {
        public ReverseOverShootInterpolator(float f) {
            super(f);
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.abs(f - 1.0f));
        }
    }

}

