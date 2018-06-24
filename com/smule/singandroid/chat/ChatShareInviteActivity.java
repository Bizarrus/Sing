/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Rect
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.provider.MediaStore
 *  android.provider.MediaStore$Images
 *  android.provider.MediaStore$Images$Media
 *  android.text.Editable
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.Extra
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.InviteManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.ui.SNPImageView;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.InviteFriendsFragment;
import com.smule.singandroid.PerformanceSaveActivity;
import com.smule.singandroid.PhotoTakingActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatShareInviteActivity_;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.CustomToolBarBase;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomizeProfilePicDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class ChatShareInviteActivity
extends PhotoTakingActivity
implements ChatActivator.ChatActivatorInterface,
Runnable {
    public static final String g = ChatShareInviteActivity.class.getName();
    @Extra
    protected Analytics A;
    @InstanceState
    protected boolean B;
    protected ChatActivatorDialog C;
    protected TextAlertDialog D;
    protected ImageView E;
    protected WeakListener.OnGlobalLayoutListener F;
    protected View.OnClickListener G;
    private int H;
    private boolean I;
    private String J;
    private int K;
    private int L;
    private final Handler M = new Handler(Looper.getMainLooper());
    private CustomizeProfilePicDialog N;
    private Runnable O;
    private Runnable P;
    private Runnable Q;
    @ViewById
    protected ActionBarCustomView h;
    @ViewById
    protected View i;
    @ViewById
    protected View j;
    @ViewById
    protected SNPImageView k;
    @ViewById
    protected EditText l;
    @ViewById
    protected SelectUsersAndChatsView m;
    @ViewById
    protected LinearLayout n;
    @ViewById
    protected View o;
    @ViewById
    protected View p;
    @ViewById
    protected View q;
    @ViewById
    protected View r;
    @ViewById
    protected TextView s;
    @ViewById
    protected View t;
    @Extra
    protected PerformanceV2 u;
    @Extra
    protected String v;
    @Extra
    protected boolean w;
    @Extra
    protected Long x;
    @Extra
    protected boolean y;
    @Extra
    protected PostSingBundle z;

    public ChatShareInviteActivity() {
        this.O = new Runnable(){

            @Override
            public void run() {
                ChatShareInviteActivity.this.b(200, 200);
            }
        };
        this.P = new Runnable(){

            @Override
            public void run() {
                ChatShareInviteActivity.this.a(200, 200);
            }
        };
        this.Q = new Runnable(){

            @Override
            public void run() {
                ChatShareInviteActivity.this.finish();
            }
        };
        this.G = new View.OnClickListener(){

            public void onClick(View view) {
                if (ChatShareInviteActivity.this.m.getSelectedCount() > 0) {
                    ChatShareInviteActivity.this.z();
                    return;
                }
                ChatShareInviteActivity.this.c(false);
            }
        };
    }

    /*
     * Enabled aggressive block sorting
     */
    private void E() {
        boolean bl = true;
        long l = SingApplication.g().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", 0);
        if (l != 0) {
            SingApplication.g().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).edit().putBoolean("CUSTOMIZE_PROFILE_PIC_NO_MORE", true).apply();
        }
        if (l != 0) {
            bl = false;
        }
        this.B = bl;
        if (this.N == null) {
            this.N = new CustomizeProfilePicDialog(this, this.B, this.O, this.P, this.Q);
        }
        SingApplication.g().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).edit().putLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", System.currentTimeMillis()).apply();
        this.N.show();
    }

    public static Intent a(Context context, PerformanceV2 performanceV2, Analytics searchClkContext) {
        return new ChatShareInviteActivity_.IntentBuilder_(context).a(performanceV2).a(searchClkContext).b();
    }

    public static ChatShareInviteActivity_.IntentBuilder_ a(Context context) {
        return new ChatShareInviteActivity_.IntentBuilder_(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(boolean bl) {
        if (this.N == null) {
            this.N = new CustomizeProfilePicDialog(this, this.B, this.O, this.P, this.Q);
        }
        if (!this.N.isShowing()) {
            this.N.show();
        } else {
            this.N.a();
        }
        if (bl) {
            this.a(this.getString(2131297147), Toaster.b);
        }
    }

    public void A() {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(2131755458, (Fragment)InviteFriendsFragment.a(), InviteFriendsFragment.g);
        fragmentTransaction.addToBackStack(InviteFriendsFragment.g);
        fragmentTransaction.commitAllowingStateLoss();
    }

    protected void B() {
        LinkedList<Long> linkedList = new LinkedList<Long>();
        Iterator iterator = this.m.getSelectedAccounts().iterator();
        while (iterator.hasNext()) {
            linkedList.add(iterator.next().accountId);
        }
        for (Chat chat : this.m.getSelectedChats()) {
            if (chat.a() != Chat.Type.a) continue;
            linkedList.add(chat.f());
        }
        if (!linkedList.isEmpty()) {
            InviteManager.a().b(this.u.performanceKey, linkedList, new NetworkResponseCallback(){

                @Override
                public void handleResponse(NetworkResponse networkResponse) {
                    if (networkResponse.c()) {
                        ChatShareInviteActivity.this.C();
                        return;
                    }
                    ChatShareInviteActivity.this.D();
                }
            });
            return;
        }
        this.C();
    }

    protected void C() {
        com.smule.chat.ChatManager chatManager = SingApplication.k();
        final String string2 = this.l.getText().toString();
        Iterator<AccountIcon> object2 = this.m.getSelectedAccounts().iterator();
        while (object2.hasNext()) {
            chatManager.a(object2.next(), new ChatManager(){

                @Override
                public void a(Chat chat, ChatStatus chatStatus) {
                    if (chat != null) {
                        if (!string2.isEmpty()) {
                            chat.a(new TextChatMessage(string2));
                        }
                        chat.a(new PerformanceChatMessage(ChatShareInviteActivity.this.u));
                        ChatShareInviteActivity.this.a(chat);
                    }
                }
            });
        }
        for (Chat chat : this.m.getSelectedChats()) {
            if (!string2.isEmpty()) {
                chat.a(new TextChatMessage(string2));
            }
            chat.a(new PerformanceChatMessage(this.u));
            this.a(chat);
        }
        this.c(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void D() {
        if (this.D == null) {
            int n = this.u != null && this.u.p() ? 2131296862 : 2131297395;
            this.D = new TextAlertDialog((Context)this, 2131296864, n);
            this.D.a(2131296863, 2131296710);
            this.D.c(true);
            this.D.a(new CustomAlertDialog.CustomAlertDialogListener(){

                @Override
                public void a(CustomAlertDialog customAlertDialog) {
                    ChatShareInviteActivity.this.z();
                }

                @Override
                public void b(CustomAlertDialog customAlertDialog) {
                    ChatShareInviteActivity.this.c(false);
                }
            });
        }
        if (!this.D.isShowing()) {
            this.D.show();
        }
    }

    @Override
    protected void a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                final NetworkResponse networkResponse = UserManager.a().a(bitmap);
                ChatShareInviteActivity.this.runOnUiThread(new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    @Override
                    public void run() {
                        if (networkResponse.c()) {
                            ChatShareInviteActivity.this.a(ChatShareInviteActivity.this.getString(2131297148), Toaster.b);
                        } else {
                            ChatShareInviteActivity.this.a(ChatShareInviteActivity.this.getString(2131297147), Toaster.b);
                        }
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
            }

        });
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(Chat chat) {
        String string2 = this.u.performanceKey;
        Analytics performanceStatus = SingAnalytics.e((PerformanceV2)this.u);
        Analytics ensemble = SingAnalytics.a((PerformanceV2)this.u);
        String string3 = SingAnalytics.d((PerformanceV2)this.u);
        Analytics videoStatusType = this.u.video ? Analytics.a : Analytics.b;
        SingAnalytics.a((String)string2, performanceStatus, ensemble, (String)string3, videoStatusType, Analytics.f, (String)chat.c(), (ChatAnalytics.ChatType)ChatAnalytics.ChatType.a(chat), (Number)ChatAnalytics.b(chat), ChatAnalytics.a(chat));
    }

    @Override
    public void a(Chat chat, ChatStatus chatStatus) {
    }

    @Override
    public void a(ChatStatus chatStatus) {
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(boolean bl, boolean bl2) {
        Intent intent = new Intent();
        if (bl) {
            intent.putParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS", new ArrayList<AccountIcon>(this.m.getSelectedAccounts()));
            ArrayList<String> arrayList = new ArrayList<String>();
            Iterator<Chat> iterator = this.m.getSelectedChats().iterator();
            while (iterator.hasNext()) {
                arrayList.add(iterator.next().c());
            }
            intent.putStringArrayListExtra("RESULT.SELECTED_CHATS", arrayList);
            this.setResult(-1, intent);
            int n = this.m.getSelectedCount();
            if (n > 0) {
                int n2 = this.I ? (this.w ? 2131361794 : 2131361795) : 2131361797;
                com.smule.android.utils.Toaster.a((Context)this, this.getResources().getQuantityString(n2, n, new Object[]{n}));
            }
        } else {
            intent.putParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS", new ArrayList());
            intent.putStringArrayListExtra("RESULT.SELECTED_CHATS", new ArrayList());
            this.setResult(0, intent);
        }
        if (this.y && bl2) {
            this.E();
            return;
        }
        this.finish();
    }

    @Click
    protected void b() {
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        MiscUtils.a((View)this.l, (boolean)true);
    }

    @Override
    public void b(Chat chat) {
    }

    @Override
    public void c(Chat chat) {
    }

    public void c(boolean bl) {
        this.a(bl, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void f() {
        super.f();
        boolean bl = this.v != null;
        this.I = bl;
        int n = this.I ? 2131296872 : 2131297384;
        this.J = this.getString(n);
        ActionBarCustomView actionBarCustomView = this.h;
        CustomToolBarBase.TitleType titleType = this.I ? CustomToolBarBase.TitleType.b : CustomToolBarBase.TitleType.a;
        actionBarCustomView.setTitleType(titleType);
        this.h.setDisplayUpButton(true);
        this.h.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                ChatShareInviteActivity.this.onBackPressed();
            }
        });
        this.h.setTitle(this.J);
        titleType = this.s;
        n = this.I && this.w() ? 0 : 8;
        titleType.setVisibility(n);
        titleType = LayoutInflater.from((Context)this).inflate(2130903071, null, false);
        n = this.I ? 2130837888 : 2130837659;
        this.K = n;
        actionBarCustomView = this.getResources();
        n = this.I ? 2131689500 : 2131689503;
        this.L = actionBarCustomView.getColor(n);
        ((ImageView)titleType.findViewById(2131755235)).setImageResource(this.K);
        this.h.a();
        this.E = (ImageView)this.h.a((View)titleType);
        this.E.setOnClickListener(this.G);
        if (this.I) {
            this.n.setVisibility(8);
        } else {
            this.l.setOnEditorActionListener(new TextView.OnEditorActionListener(){

                public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                    if (n == 6 || n == 0 && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0) {
                        ChatShareInviteActivity.this.b();
                        return true;
                    }
                    Log.b(ChatShareInviteActivity.g, "actionId: " + n);
                    return false;
                }
            });
        }
        this.m.setSearchClkContext(this.A);
        this.m.setIsInShareInviteActivity(true);
        this.m.setSelectUsersAndChatsListener(new SelectUsersAndChatsView.SelectUsersAndChatsListener(){

            private void e() {
                com.smule.android.utils.Toaster.a((Context)ChatShareInviteActivity.this, ChatShareInviteActivity.this.getString(2131296585, new Object[]{ChatShareInviteActivity.this.H}), Toaster.a);
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public boolean a(AccountIcon accountIcon) {
                if (ChatShareInviteActivity.this.m.getSelectedCount() < ChatShareInviteActivity.this.H) {
                    return true;
                }
                boolean bl = false;
                if (bl) return bl;
                this.e();
                return bl;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public boolean a(Chat chat) {
                if (ChatShareInviteActivity.this.m.getSelectedCount() < ChatShareInviteActivity.this.H) {
                    return true;
                }
                boolean bl = false;
                if (bl) return bl;
                this.e();
                return bl;
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void b() {
                ChatShareInviteActivity.this.v();
                SelectUsersAndChatsView selectUsersAndChatsView = ChatShareInviteActivity.this.m;
                boolean bl = ChatShareInviteActivity.this.m.getSelectedCount() == ChatShareInviteActivity.this.H;
                selectUsersAndChatsView.b(bl);
            }

            @Override
            public void t_() {
                ChatShareInviteActivity.this.y();
            }

            @Override
            public void u_() {
                ChatShareInviteActivity.this.h.setVisibility(8);
                ChatShareInviteActivity.this.r.setVisibility(8);
                ChatShareInviteActivity.this.t.setVisibility(8);
                ChatShareInviteActivity.this.m.a(true);
                if (!ChatShareInviteActivity.this.I) {
                    ChatShareInviteActivity.this.n.setVisibility(8);
                }
            }

            @Override
            public void v_() {
                ChatShareInviteActivity.this.h.setVisibility(0);
                ChatShareInviteActivity.this.r.setVisibility(0);
                ChatShareInviteActivity.this.t.setVisibility(0);
                ChatShareInviteActivity.this.m.a(false);
                if (!ChatShareInviteActivity.this.I) {
                    ChatShareInviteActivity.this.n.setVisibility(0);
                }
            }
        });
        this.m.a((Chat)null);
        ImageUtils.a(this.u.coverUrl, (ImageView)this.k, 2130837897);
        this.F = new WeakListener.OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(){
            boolean a;
            {
                this.a = false;
            }

            public void onGlobalLayout() {
                if (!ChatShareInviteActivity.this.l.hasFocus()) {
                    return;
                }
                Rect rect = new Rect();
                ChatShareInviteActivity.this.o.getWindowVisibleDisplayFrame(rect);
                int n = ChatShareInviteActivity.this.o.getRootView().getHeight();
                if ((double)(n - rect.bottom) > (double)n * 0.15) {
                    this.a = true;
                    ChatShareInviteActivity.this.p.setVisibility(0);
                    ChatShareInviteActivity.this.q.setVisibility(0);
                    return;
                }
                this.a = false;
                ChatShareInviteActivity.this.p.setVisibility(8);
                ChatShareInviteActivity.this.q.setVisibility(8);
                new Handler().postDelayed(new Runnable(){

                    @Override
                    public void run() {
                        if (!4.this.a) {
                            ChatShareInviteActivity.this.l.clearFocus();
                        }
                    }
                }, 200);
            }

        });
        LayoutUtils.a((View)this.o, (WeakListener.OnGlobalLayoutListener)this.F);
    }

    @Override
    public void finish() {
        if (this.N != null) {
            this.N.dismiss();
        }
        super.finish();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    protected void onActivityResult(int n, int n2, Intent intent) {
        block15 : {
            super.onActivityResult(n, n2, intent);
            if (n2 != -1) {
                if (n2 == 0) {
                    if (n != 2202 && n != 2203 && n != 2201) return;
                    {
                        this.d(false);
                        return;
                    }
                } else {
                    Log.e(g, "Bad result code, " + n2 + ", returned for request code: " + n);
                    if (n != 2202 && n != 2203 && n != 2201) return;
                    {
                        this.d(true);
                        return;
                    }
                }
            }
            switch (n) {
                default: {
                    return;
                }
                case 2202: 
            }
            Bundle bundle = intent.getExtras();
            if (bundle == null) {
                this.d(true);
                return;
            }
            if (bundle.getParcelable("data") != null) {
                intent = (Bitmap)bundle.getParcelable("data");
            } else {
                if (intent.getData() != null) {
                    try {
                        intent = MediaStore.Images.Media.getBitmap((ContentResolver)this.getContentResolver(), (Uri)intent.getData());
                        break block15;
                    }
                    catch (IOException iOException) {
                        Log.e(g, "IOException when attempting to load uri from CROP_PHOTO_INTENT_CODE " + iOException.getMessage());
                    }
                }
                intent = null;
            }
        }
        if (intent != null) {
            this.a((Bitmap)intent, this.Q);
            return;
        }
        Log.e(g, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
        this.d(true);
    }

    @Override
    public void onBackPressed() {
        if (this.m.d()) {
            return;
        }
        FragmentManager fragmentManager = this.getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 0) {
            this.j.setVisibility(8);
            this.i.setVisibility(0);
            fragmentManager.popBackStack();
            this.m.f();
            return;
        }
        this.a(false, false);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.H = this.getResources().getInteger(2131623949);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.N = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        this.v();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.t();
    }

    @Override
    public void run() {
        if (this.C == null) {
            return;
        }
        this.C.dismiss();
        this.D();
    }

    protected void t() {
        if (this.C != null) {
            this.C.dismiss();
            this.C = null;
        }
    }

    @Click
    protected void u() {
        this.s.setVisibility(8);
        this.x();
    }

    protected void v() {
        int n = this.m.getSelectedCount();
        if (n > 0) {
            this.h.setTitle(this.getString(2131296584, new Object[]{n, this.H}));
            this.E.setEnabled(true);
            this.E.setBackgroundColor(this.getResources().getColor(2131689500));
            this.E.setImageResource(2130837659);
            return;
        }
        this.h.setTitle(this.J);
        this.E.setEnabled(this.I);
        this.E.setBackgroundColor(this.L);
        this.E.setImageResource(this.K);
    }

    protected boolean w() {
        return SingApplication.g().getSharedPreferences(PerformanceSaveActivity.class.getName(), 0).getBoolean("INVITE_HINT_KEY", true);
    }

    @Override
    public void w_() {
    }

    protected void x() {
        SingApplication.g().getSharedPreferences(PerformanceSaveActivity.class.getName(), 0).edit().putBoolean("INVITE_HINT_KEY", false).apply();
    }

    protected void y() {
        this.i.setVisibility(8);
        this.j.setVisibility(0);
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(2131755458, (Fragment)FindFriendsFragment.a(FindFriendsFragment.EntryPoint.c), FindFriendsFragment.g);
        fragmentTransaction.addToBackStack(FindFriendsFragment.g);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void y_() {
        this.M.removeCallbacks((Runnable)this);
        if (this.u.m()) {
            this.B();
            return;
        }
        this.C();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void z() {
        if (this.C == null) {
            int n = this.u != null && this.u.p() ? 2131296870 : 2131296588;
            this.C = new ChatActivatorDialog((Context)this, n);
            this.C.a(this);
        }
        this.M.postDelayed((Runnable)this, (long)this.getResources().getInteger(2131623943));
        this.C.show();
    }

}

