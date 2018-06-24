package com.smule.singandroid.chat;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore.Images.Media;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.share.internal.ShareConstants;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.SocialChannel;
import com.smule.android.logging.Analytics.VideoStatusType;
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
import com.smule.android.utils.Toaster$Duration;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ChatCallback;
import com.smule.chat.ChatStatus;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.FindFriendsFragment;
import com.smule.singandroid.PerformanceSaveActivity;
import com.smule.singandroid.PhotoTakingActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.ChatAnalytics.ChatType;
import com.smule.singandroid.chat.ChatShareInviteActivity_.IntentBuilder_;
import com.smule.singandroid.chat.SelectUsersAndChatsView.SelectUsersAndChatsListener;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorInterface;
import com.smule.singandroid.chat.activator.ChatActivatorDialog;
import com.smule.singandroid.customviews.ActionBarCustomView;
import com.smule.singandroid.customviews.CustomToolBarBase.TitleType;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.CustomizeProfilePicDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EActivity
public class ChatShareInviteActivity extends PhotoTakingActivity implements ChatActivatorInterface, Runnable {
    public static final String f20981g = ChatShareInviteActivity.class.getName();
    @Extra
    protected SearchClkContext f20982A;
    @InstanceState
    protected boolean f20983B;
    protected ChatActivatorDialog f20984C;
    protected TextAlertDialog f20985D;
    protected ImageView f20986E;
    protected OnGlobalLayoutListener f20987F;
    protected OnClickListener f20988G = new C42969(this);
    private int f20989H;
    private boolean f20990I;
    private String f20991J;
    private int f20992K;
    private int f20993L;
    private final Handler f20994M = new Handler(Looper.getMainLooper());
    private CustomizeProfilePicDialog f20995N;
    private Runnable f20996O = new C42915(this);
    private Runnable f20997P = new C42926(this);
    private Runnable f20998Q = new C42937(this);
    @ViewById
    protected ActionBarCustomView f20999h;
    @ViewById
    protected View f21000i;
    @ViewById
    protected View f21001j;
    @ViewById
    protected SNPImageView f21002k;
    @ViewById
    protected EditText f21003l;
    @ViewById
    protected SelectUsersAndChatsView f21004m;
    @ViewById
    protected LinearLayout f21005n;
    @ViewById
    protected View f21006o;
    @ViewById
    protected View f21007p;
    @ViewById
    protected View f21008q;
    @ViewById
    protected View f21009r;
    @ViewById
    protected TextView f21010s;
    @ViewById
    protected View f21011t;
    @Extra
    protected PerformanceV2 f21012u;
    @Extra
    protected String f21013v;
    @Extra
    protected boolean f21014w;
    @Extra
    protected Long f21015x;
    @Extra
    protected boolean f21016y;
    @Extra
    protected PostSingBundle f21017z;

    class C42861 implements OnClickListener {
        final /* synthetic */ ChatShareInviteActivity f20966a;

        C42861(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20966a = chatShareInviteActivity;
        }

        public void onClick(View view) {
            this.f20966a.onBackPressed();
        }
    }

    class C42872 implements OnEditorActionListener {
        final /* synthetic */ ChatShareInviteActivity f20967a;

        C42872(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20967a = chatShareInviteActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6 || (i == 0 && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0)) {
                this.f20967a.mo6751b();
                return true;
            }
            Log.b(ChatShareInviteActivity.f20981g, "actionId: " + i);
            return false;
        }
    }

    class C42883 implements SelectUsersAndChatsListener {
        final /* synthetic */ ChatShareInviteActivity f20968a;

        C42883(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20968a = chatShareInviteActivity;
        }

        public void o_() {
            this.f20968a.m22670v();
        }

        public void p_() {
            this.f20968a.m22667s();
            this.f20968a.f21004m.m22889b(this.f20968a.f21004m.getSelectedCount() == this.f20968a.f20989H);
        }

        public boolean mo6728a(AccountIcon accountIcon) {
            boolean z = this.f20968a.f21004m.getSelectedCount() < this.f20968a.f20989H;
            if (!z) {
                m22643e();
            }
            return z;
        }

        public boolean mo6729a(Chat chat) {
            boolean z = this.f20968a.f21004m.getSelectedCount() < this.f20968a.f20989H;
            if (!z) {
                m22643e();
            }
            return z;
        }

        public void q_() {
            this.f20968a.f20999h.setVisibility(8);
            this.f20968a.f21009r.setVisibility(8);
            this.f20968a.f21011t.setVisibility(8);
            this.f20968a.f21004m.m22884a(true);
            if (!this.f20968a.f20990I) {
                this.f20968a.f21005n.setVisibility(8);
            }
        }

        public void r_() {
            this.f20968a.f20999h.setVisibility(0);
            this.f20968a.f21009r.setVisibility(0);
            this.f20968a.f21011t.setVisibility(0);
            this.f20968a.f21004m.m22884a(false);
            if (!this.f20968a.f20990I) {
                this.f20968a.f21005n.setVisibility(0);
            }
        }

        private void m22643e() {
            Toaster.a(this.f20968a, this.f20968a.getString(C1947R.string.chat_share_max_selected, new Object[]{Integer.valueOf(this.f20968a.f20989H)}), Toaster$Duration.SHORT);
        }
    }

    class C42904 implements ViewTreeObserver.OnGlobalLayoutListener {
        boolean f20970a = false;
        final /* synthetic */ ChatShareInviteActivity f20971b;

        class C42891 implements Runnable {
            final /* synthetic */ C42904 f20969a;

            C42891(C42904 c42904) {
                this.f20969a = c42904;
            }

            public void run() {
                if (!this.f20969a.f20970a) {
                    this.f20969a.f20971b.f21003l.clearFocus();
                }
            }
        }

        C42904(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20971b = chatShareInviteActivity;
        }

        public void onGlobalLayout() {
            if (this.f20971b.f21003l.hasFocus()) {
                Rect rect = new Rect();
                this.f20971b.f21006o.getWindowVisibleDisplayFrame(rect);
                int height = this.f20971b.f21006o.getRootView().getHeight();
                if (((double) (height - rect.bottom)) > ((double) height) * 0.15d) {
                    this.f20970a = true;
                    this.f20971b.f21007p.setVisibility(0);
                    this.f20971b.f21008q.setVisibility(0);
                    return;
                }
                this.f20970a = false;
                this.f20971b.f21007p.setVisibility(8);
                this.f20971b.f21008q.setVisibility(8);
                new Handler().postDelayed(new C42891(this), 200);
            }
        }
    }

    class C42915 implements Runnable {
        final /* synthetic */ ChatShareInviteActivity f20972a;

        C42915(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20972a = chatShareInviteActivity;
        }

        public void run() {
            this.f20972a.m20859b(200, 200);
        }
    }

    class C42926 implements Runnable {
        final /* synthetic */ ChatShareInviteActivity f20973a;

        C42926(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20973a = chatShareInviteActivity;
        }

        public void run() {
            this.f20973a.m20858a(200, 200);
        }
    }

    class C42937 implements Runnable {
        final /* synthetic */ ChatShareInviteActivity f20974a;

        C42937(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20974a = chatShareInviteActivity;
        }

        public void run() {
            this.f20974a.finish();
        }
    }

    class C42969 implements OnClickListener {
        final /* synthetic */ ChatShareInviteActivity f20980a;

        C42969(ChatShareInviteActivity chatShareInviteActivity) {
            this.f20980a = chatShareInviteActivity;
        }

        public void onClick(View view) {
            if (this.f20980a.f21004m.getSelectedCount() > 0) {
                this.f20980a.m22671w();
            } else {
                this.f20980a.m22663c(false);
            }
        }
    }

    public static Intent m22648a(Context context, PerformanceV2 performanceV2, SearchClkContext searchClkContext) {
        return new IntentBuilder_(context).m22676a(performanceV2).m22675a(searchClkContext).b();
    }

    public static IntentBuilder_ m22649a(Context context) {
        return new IntentBuilder_(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f20989H = getResources().getInteger(C1947R.integer.chat_max_share_targets);
    }

    protected void m22664d() {
        super.d();
        this.f20990I = this.f21013v != null;
        this.f20991J = getString(this.f20990I ? C1947R.string.invite_via_chat : C1947R.string.share_chat_title);
        this.f20999h.setTitleType(this.f20990I ? TitleType.Center : TitleType.Default);
        this.f20999h.setDisplayUpButton(true);
        this.f20999h.setOnClickListener(new C42861(this));
        this.f20999h.setTitle(this.f20991J);
        TextView textView = this.f21010s;
        int i = (this.f20990I && m22668t()) ? 0 : 8;
        textView.setVisibility(i);
        View inflate = LayoutInflater.from(this).inflate(C1947R.layout.action_bar_icon_button, null, false);
        this.f20992K = this.f20990I ? C1947R.drawable.icn_checkmark_white : C1947R.drawable.btn_send_cta_white;
        this.f20993L = getResources().getColor(this.f20990I ? C1947R.color.background_button : C1947R.color.background_button_grey);
        ((ImageView) inflate.findViewById(C1947R.id.button_next)).setImageResource(this.f20992K);
        this.f20999h.m23109a();
        this.f20986E = (ImageView) this.f20999h.m23106a(inflate);
        this.f20986E.setOnClickListener(this.f20988G);
        if (this.f20990I) {
            this.f21005n.setVisibility(8);
        } else {
            this.f21003l.setOnEditorActionListener(new C42872(this));
        }
        this.f21004m.setSearchClkContext(this.f20982A);
        this.f21004m.setIsInShareInviteActivity(true);
        this.f21004m.setSelectUsersAndChatsListener(new C42883(this));
        this.f21004m.m22881a(null);
        ImageUtils.a(this.f21012u.coverUrl, this.f21002k, C1947R.drawable.icn_default_album_small);
        this.f20987F = new OnGlobalLayoutListener(this, new C42904(this));
        LayoutUtils.m25854a(this.f21006o, this.f20987F);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f20995N = null;
    }

    @Click
    protected void mo6751b() {
        this.f21007p.setVisibility(8);
        this.f21008q.setVisibility(8);
        MiscUtils.m25894a(this.f21003l, true);
    }

    public void onStart() {
        super.onStart();
        m22667s();
    }

    protected void onStop() {
        super.onStop();
        m22665q();
    }

    protected void m22665q() {
        if (this.f20984C != null) {
            this.f20984C.dismiss();
            this.f20984C = null;
        }
    }

    public void onBackPressed() {
        if (!this.f21004m.m22893d()) {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                this.f21001j.setVisibility(8);
                this.f21000i.setVisibility(0);
                fragmentManager.popBackStack();
                return;
            }
            m22659a(false, false);
        }
    }

    public void m22663c(boolean z) {
        m22659a(z, true);
    }

    public void m22659a(boolean z, boolean z2) {
        Intent intent = new Intent();
        if (z) {
            intent.putParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS", new ArrayList(this.f21004m.getSelectedAccounts()));
            ArrayList arrayList = new ArrayList();
            for (Chat c : this.f21004m.getSelectedChats()) {
                arrayList.add(c.m19209c());
            }
            intent.putStringArrayListExtra("RESULT.SELECTED_CHATS", arrayList);
            setResult(-1, intent);
            int selectedCount = this.f21004m.getSelectedCount();
            if (selectedCount > 0) {
                int i = this.f20990I ? this.f21014w ? C1947R.plurals.chat_invite_followers_and_messages : C1947R.plurals.chat_invite_toast_count : C1947R.plurals.chat_share_toast_count;
                Toaster.a(this, getResources().getQuantityString(i, selectedCount, new Object[]{Integer.valueOf(selectedCount)}));
            }
        } else {
            intent.putParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS", new ArrayList());
            intent.putStringArrayListExtra("RESULT.SELECTED_CHATS", new ArrayList());
            setResult(0, intent);
        }
        if (this.f21016y && z2) {
            m22646A();
        } else {
            finish();
        }
    }

    public void finish() {
        if (this.f20995N != null) {
            this.f20995N.dismiss();
        }
        super.finish();
    }

    private void m22646A() {
        boolean z = true;
        long j = SingApplication.f().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).getLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", 0);
        if (j != 0) {
            SingApplication.f().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).edit().putBoolean("CUSTOMIZE_PROFILE_PIC_NO_MORE", true).apply();
        }
        if (j != 0) {
            z = false;
        }
        this.f20983B = z;
        if (this.f20995N == null) {
            this.f20995N = new CustomizeProfilePicDialog(this, this.f20983B, this.f20996O, this.f20997P, this.f20998Q);
        }
        SingApplication.f().getSharedPreferences(ChatShareInviteActivity.class.getName(), 0).edit().putLong("CUSTOMIZE_PROFILE_PIC_TIMESTAMP", System.currentTimeMillis()).apply();
        this.f20995N.show();
    }

    protected void mo6750a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ ChatShareInviteActivity f20979c;

            public void run() {
                final NetworkResponse a = UserManager.a().a(bitmap);
                this.f20979c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ C42958 f20976b;

                    public void run() {
                        if (a.c()) {
                            this.f20976b.f20979c.a(this.f20976b.f20979c.getString(C1947R.string.photo_saved), Toaster$Duration.LONG);
                        } else {
                            this.f20976b.f20979c.a(this.f20976b.f20979c.getString(C1947R.string.photo_save_error), Toaster$Duration.LONG);
                        }
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                });
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            switch (i) {
                case 2202:
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        Bitmap bitmap;
                        if (extras.getParcelable(ShareConstants.WEB_DIALOG_PARAM_DATA) != null) {
                            bitmap = (Bitmap) extras.getParcelable(ShareConstants.WEB_DIALOG_PARAM_DATA);
                        } else {
                            if (intent.getData() != null) {
                                try {
                                    bitmap = Media.getBitmap(getContentResolver(), intent.getData());
                                } catch (IOException e) {
                                    Log.e(f20981g, "IOException when attempting to load uri from CROP_PHOTO_INTENT_CODE " + e.getMessage());
                                }
                            }
                            bitmap = null;
                        }
                        if (bitmap != null) {
                            mo6750a(bitmap, this.f20998Q);
                            return;
                        }
                        Log.e(f20981g, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
                        m22655d(true);
                        return;
                    }
                    m22655d(true);
                    return;
                default:
                    return;
            }
        } else if (i2 != 0) {
            Log.e(f20981g, "Bad result code, " + i2 + ", returned for request code: " + i);
            if (i == 2202 || i == 2203 || i == 2201) {
                m22655d(true);
            }
        } else if (i == 2202 || i == 2203 || i == 2201) {
            m22655d(false);
        }
    }

    private void m22655d(boolean z) {
        if (this.f20995N == null) {
            this.f20995N = new CustomizeProfilePicDialog(this, this.f20983B, this.f20996O, this.f20997P, this.f20998Q);
        }
        if (this.f20995N.isShowing()) {
            this.f20995N.mo6374a();
        } else {
            this.f20995N.show();
        }
        if (z) {
            a(getString(C1947R.string.photo_save_error), Toaster$Duration.LONG);
        }
    }

    @Click
    protected void m22666r() {
        this.f21010s.setVisibility(8);
        m22669u();
    }

    protected void m22667s() {
        if (this.f21004m.getSelectedCount() > 0) {
            this.f20999h.setTitle(getString(C1947R.string.chat_selected_chats_out_of_max, new Object[]{Integer.valueOf(r0), Integer.valueOf(this.f20989H)}));
            this.f20986E.setEnabled(true);
            this.f20986E.setBackgroundColor(getResources().getColor(C1947R.color.background_button));
            this.f20986E.setImageResource(C1947R.drawable.btn_send_cta_white);
            return;
        }
        this.f20999h.setTitle(this.f20991J);
        this.f20986E.setEnabled(this.f20990I);
        this.f20986E.setBackgroundColor(this.f20993L);
        this.f20986E.setImageResource(this.f20992K);
    }

    protected boolean m22668t() {
        return SingApplication.f().getSharedPreferences(PerformanceSaveActivity.class.getName(), 0).getBoolean("INVITE_HINT_KEY", true);
    }

    protected void m22669u() {
        SingApplication.f().getSharedPreferences(PerformanceSaveActivity.class.getName(), 0).edit().putBoolean("INVITE_HINT_KEY", false).apply();
    }

    protected void m22670v() {
        this.f21000i.setVisibility(8);
        this.f21001j.setVisibility(0);
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        beginTransaction.replace(C1947R.id.fragment_content_view, FindFriendsWithoutHeaderFragment.m22731c(true), FindFriendsFragment.f18701e);
        beginTransaction.addToBackStack(FindFriendsWithoutHeaderFragment.f21056r);
        beginTransaction.commitAllowingStateLoss();
    }

    protected void m22671w() {
        if (this.f20984C == null) {
            int i = (this.f21012u == null || !this.f21012u.n()) ? C1947R.string.chat_status_connecting : C1947R.string.invite_progress;
            this.f20984C = new ChatActivatorDialog((Context) this, i);
            this.f20984C.m22945a((ChatActivatorInterface) this);
        }
        this.f20994M.postDelayed(this, (long) getResources().getInteger(C1947R.integer.chat_invite_timeout));
        this.f20984C.show();
    }

    protected void m22672x() {
        Collection linkedList = new LinkedList();
        for (AccountIcon accountIcon : this.f21004m.getSelectedAccounts()) {
            linkedList.add(Long.valueOf(accountIcon.accountId));
        }
        for (Chat chat : this.f21004m.getSelectedChats()) {
            if (chat.mo6335a() == Type.PEER) {
                linkedList.add(Long.valueOf(chat.mo6341f()));
            }
        }
        if (linkedList.isEmpty()) {
            m22673y();
        } else {
            InviteManager.m18240a().m18246b(this.f21012u.performanceKey, linkedList, new NetworkResponseCallback(this) {
                final /* synthetic */ ChatShareInviteActivity f20962a;

                {
                    this.f20962a = r1;
                }

                public void handleResponse(NetworkResponse networkResponse) {
                    if (networkResponse.c()) {
                        this.f20962a.m22673y();
                    } else {
                        this.f20962a.m22674z();
                    }
                }
            });
        }
    }

    protected void m22673y() {
        ChatManager j = SingApplication.j();
        final String obj = this.f21003l.getText().toString();
        for (AccountIcon a : this.f21004m.getSelectedAccounts()) {
            j.a(a, new ChatManager$ChatCallback(this) {
                final /* synthetic */ ChatShareInviteActivity f20964b;

                public void mo6326a(Chat chat, ChatStatus chatStatus) {
                    if (chat != null) {
                        if (!obj.isEmpty()) {
                            chat.mo6346a(new TextChatMessage(obj));
                        }
                        chat.mo6346a(new PerformanceChatMessage(this.f20964b.f21012u));
                        this.f20964b.m22661b(chat);
                    }
                }
            });
        }
        for (Chat chat : this.f21004m.getSelectedChats()) {
            if (!obj.isEmpty()) {
                chat.mo6346a(new TextChatMessage(obj));
            }
            chat.mo6346a(new PerformanceChatMessage(this.f21012u));
            m22661b(chat);
        }
        m22663c(true);
    }

    protected void m22661b(Chat chat) {
        SingAnalytics.m26097a(this.f21012u.performanceKey, SingAnalytics.m26146e(this.f21012u), Analytics.m17828a(this.f21012u), SingAnalytics.m26140d(this.f21012u), this.f21012u.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO, SocialChannel.CHAT, chat.m19209c(), ChatType.m22368a(chat), ChatAnalytics.m22391b(chat), ChatAnalytics.m22375a(chat));
    }

    protected void m22674z() {
        if (this.f20985D == null) {
            int i = (this.f21012u == null || !this.f21012u.n()) ? C1947R.string.share_fail : C1947R.string.invite_connect_fail_message;
            this.f20985D = new TextAlertDialog((Context) this, (int) C1947R.string.invite_connect_fail_title, i);
            this.f20985D.m19800a((int) C1947R.string.invite_connect_fail_retry, (int) C1947R.string.invite_connect_fail_cancel);
            this.f20985D.m19812c(true);
            this.f20985D.m19803a(new CustomAlertDialogListener(this) {
                final /* synthetic */ ChatShareInviteActivity f20965a;

                {
                    this.f20965a = r1;
                }

                public void mo6385a(CustomAlertDialog customAlertDialog) {
                    this.f20965a.m22671w();
                }

                public void mo6386b(CustomAlertDialog customAlertDialog) {
                    this.f20965a.m22663c(false);
                }
            });
        }
        if (!this.f20985D.isShowing()) {
            this.f20985D.show();
        }
    }

    public void s_() {
    }

    public void mo6551a(ChatStatus chatStatus) {
    }

    public void a_(Chat chat) {
    }

    public void mo6553c(Chat chat) {
    }

    public void mo6550a(Chat chat, ChatStatus chatStatus) {
    }

    public void t_() {
        this.f20994M.removeCallbacks(this);
        if (this.f21012u.k()) {
            m22672x();
        } else {
            m22673y();
        }
    }

    public void run() {
        if (this.f20984C != null) {
            this.f20984C.dismiss();
            m22674z();
        }
    }
}
