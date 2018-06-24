package com.smule.singandroid.chat;

import android.content.Context;
import android.content.res.Configuration;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.foound.widget.AmazingAdapter;
import com.foound.widget.AmazingListView;
import com.mopub.common.Constants;
import com.mopub.volley.DefaultRetryPolicy;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformanceResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ChatCallback;
import com.smule.chat.ChatManager$ConnectionStatus;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.chat.PerformanceChatMessage;
import com.smule.chat.TextChatMessage;
import com.smule.singandroid.BaseFragment.ActionBarHighlightMode;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.chat.activator.ChatActivatorFragment;
import com.smule.singandroid.chat.message_aggregation.ChatMessageAggregator;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem.ChatMessageViewListener;
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
import com.smule.singandroid.fragments.NowPlayingFragment.NowPlayingFragmentMenuSelectedCallback;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ChatFragment extends ChatActivatorFragment implements ChatMessageViewListener {
    public static final String f20901e = ChatFragment.class.getName();
    protected AnimationSet f20902A;
    boolean f20903B;
    String f20904C;
    protected ChatManager f20905D;
    boolean f20906E;
    boolean f20907F;
    OnGlobalLayoutListener f20908G;
    ChatListener f20909H = new C42711(this);
    DataSetObserver f20910I = new C42808(this);
    private TranslateAnimation f20911L;
    private TranslateAnimation f20912M;
    @ViewById
    protected RelativeLayout f20913f;
    @ViewById
    protected View f20914g;
    @ViewById
    protected TextView f20915h;
    @ViewById
    protected View f20916i;
    @ViewById
    protected View f20917j;
    @ViewById
    protected EditText f20918k;
    @ViewById
    protected AmazingListView f20919l;
    @ViewById
    protected RelativeLayout f20920m;
    @ViewById
    protected RelativeLayout f20921n;
    @ViewById
    protected ProfileImageWithVIPBadge f20922o;
    @ViewById
    protected TextView f20923p;
    @ViewById
    protected ImageView f20924q;
    @ViewById
    protected ProgressBar f20925r;
    @ViewById
    protected TextView f20926s;
    @ViewById
    protected LinearLayout f20927t;
    @ViewById
    protected ImageView f20928u;
    @ViewById
    protected View f20929v;
    @ViewById
    protected ProfileImageWithVIPBadge f20930w;
    protected ChatAdapter f20931x;
    protected AnimationSet f20932y;
    protected AnimationSet f20933z;

    class C42711 extends ChatListenerAdapter {
        final /* synthetic */ ChatFragment f20880a;

        C42711(ChatFragment chatFragment) {
            this.f20880a = chatFragment;
        }

        public void m22528a(Chat chat, ChatMessage chatMessage, boolean z) {
            Log.b(ChatFragment.f20901e, "onNewMessage");
            if (chat == this.f20880a.K) {
                this.f20880a.f20931x.m22536a(chatMessage, z);
            }
        }

        public void m22527a(Chat chat, ChatMessage chatMessage) {
            if (this.f20880a.isAdded()) {
                Log.b(ChatFragment.f20901e, "onMessageUpdated: " + chatMessage + " state: " + chatMessage.m19387d());
                int i = -1;
                for (int i2 = 0; i2 < this.f20880a.f20919l.getChildCount(); i2++) {
                    View childAt = this.f20880a.f20919l.getChildAt(i2);
                    if (childAt instanceof ChatMessageListItemInterface) {
                        ChatMessageListItemInterface chatMessageListItemInterface = (ChatMessageListItemInterface) childAt;
                        ChatMessage message = chatMessageListItemInterface.getMessage();
                        if (i == -1) {
                            i = (this.f20880a.f20919l.getFirstVisiblePosition() + i2) - this.f20880a.f20919l.getHeaderViewsCount();
                        }
                        if (this.f20880a.f20931x.m22538d(i) != message) {
                            Log.b(ChatFragment.f20901e, "Wrong message: itemAdapterPosition: " + i + " mChatAdapter.getCount(): " + this.f20880a.f20931x.getCount());
                        } else {
                            if (message.equals(chatMessage)) {
                                chatMessageListItemInterface.mo6771b(chat, chatMessage, i);
                            } else if (this.f20880a.f20904C != null && message.m19389f().equals(this.f20880a.f20904C)) {
                                chatMessageListItemInterface.mo6771b(chat, message, i);
                            }
                            i++;
                        }
                    }
                }
                if (chatMessage.m19388e() == ChatStatus.QUEUE_FULL) {
                    Toaster.a(this.f20880a.getActivity(), C1947R.string.chat_error_queue_full);
                }
                if (chat instanceof PeerChat) {
                    this.f20880a.f20904C = ((PeerChat) chat).mo6334S();
                }
                if (this.f20880a.f20919l.getLastVisiblePosition() == this.f20880a.f20919l.getCount() - 1) {
                    this.f20880a.m22594L();
                }
            }
        }

        public void m22530b(Chat chat, ChatMessage chatMessage) {
            m22526a(chat);
        }

        public void m22526a(Chat chat) {
            Log.b(ChatFragment.f20901e, "onHistoryUpdated");
            this.f20880a.f20931x.m22540j();
            if (chat.m19222j().size() <= 3 && chat.mo6349t()) {
                this.f20880a.f20931x.m22541k();
            }
        }

        public void m22529b(Chat chat) {
            this.f20880a.m22589G();
        }

        public void m22531c(Chat chat) {
            this.f20880a.m22593K();
        }
    }

    class C42732 implements OnEditorActionListener {
        final /* synthetic */ ChatFragment f20882a;

        class C42721 implements Runnable {
            final /* synthetic */ C42732 f20881a;

            C42721(C42732 c42732) {
                this.f20881a = c42732;
            }

            public void run() {
                this.f20881a.f20882a.m22594L();
            }
        }

        C42732(ChatFragment chatFragment) {
            this.f20882a = chatFragment;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 4 || (i == 0 && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0)) {
                this.f20882a.m19848b(new C42721(this));
                this.f20882a.m22595M();
                return true;
            }
            Log.b(ChatFragment.f20901e, "actionId: " + i);
            return false;
        }
    }

    class C42743 implements TextWatcher {
        final /* synthetic */ ChatFragment f20883a;

        C42743(ChatFragment chatFragment) {
            this.f20883a = chatFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (this.f20883a.m22546P()) {
                this.f20883a.f20928u.setImageResource(C1947R.drawable.btn_send_cta_blue);
            } else {
                this.f20883a.f20928u.setImageResource(C1947R.drawable.btn_send_cta_gray);
            }
            if (this.f20883a.f20927t.getVisibility() == 0 && this.f20883a.f20931x.getCount() == 0 && this.f20883a.f20927t.getAnimation().hasEnded()) {
                this.f20883a.f20927t.clearAnimation();
                this.f20883a.f20927t.startAnimation(this.f20883a.f20912M);
            }
        }
    }

    class C42754 implements OnTouchListener {
        final /* synthetic */ ChatFragment f20884a;

        C42754(ChatFragment chatFragment) {
            this.f20884a = chatFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f20884a.getActivity().getWindow().setSoftInputMode(16);
            return false;
        }
    }

    class C42765 implements OnTouchListener {
        final /* synthetic */ ChatFragment f20885a;

        C42765(ChatFragment chatFragment) {
            this.f20885a = chatFragment;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 2 && this.f20885a.getActivity() != null) {
                ((InputMethodManager) this.f20885a.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.f20885a.f20918k.getWindowToken(), 0);
            }
            return false;
        }
    }

    class C42776 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ ChatFragment f20886a;

        C42776(ChatFragment chatFragment) {
            this.f20886a = chatFragment;
        }

        public void onGlobalLayout() {
            boolean z = true;
            if (this.f20886a.isAdded() && this.f20886a.f20918k.hasFocus()) {
                Rect rect = new Rect();
                this.f20886a.f20913f.getWindowVisibleDisplayFrame(rect);
                int height = this.f20886a.f20913f.getRootView().getHeight();
                boolean z2 = ((double) (height - rect.bottom)) > ((double) height) * 0.15d;
                if (!(z2 || this.f20886a.f20927t.getVisibility() != 8 || this.f20886a.f20931x.getCount() != 0 || this.f20886a.K == null || this.f20886a.K.mo6335a() == Type.GROUP)) {
                    this.f20886a.f20927t.clearAnimation();
                    this.f20886a.f20927t.startAnimation(this.f20886a.f20911L);
                }
                ChatFragment chatFragment = this.f20886a;
                if (z2) {
                    z = false;
                }
                chatFragment.m22604b(z);
            }
        }
    }

    class C42808 extends DataSetObserver {
        final /* synthetic */ ChatFragment f20891a;

        C42808(ChatFragment chatFragment) {
            this.f20891a = chatFragment;
        }

        public void onChanged() {
            super.onChanged();
            this.f20891a.mo6749E();
        }
    }

    class C42819 implements AnimationListener {
        final /* synthetic */ ChatFragment f20892a;

        C42819(ChatFragment chatFragment) {
            this.f20892a = chatFragment;
        }

        public void onAnimationStart(Animation animation) {
            this.f20892a.f20927t.setVisibility(0);
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public class ChatAdapter extends AmazingAdapter {
        public final String f20893e = ChatAdapter.class.getName();
        boolean f20894f;
        List<ChatMessage> f20895g = new ArrayList();
        ChatMessageAggregator f20896h = new ChatMessageAggregator(this.f20897i.getActivity());
        final /* synthetic */ ChatFragment f20897i;
        private final Context f20898j;
        private boolean f20899k = true;

        public /* synthetic */ Object getItem(int i) {
            return m22538d(i);
        }

        public ChatAdapter(ChatFragment chatFragment, Context context) {
            this.f20897i = chatFragment;
            this.f20898j = context;
            a(true);
        }

        public void m22536a(ChatMessage chatMessage, boolean z) {
            if (!z) {
                if (chatMessage == this.f20897i.K.m19224l()) {
                    if (this.f20897i.f20919l.getLastVisiblePosition() == this.f20897i.f20919l.getCount() - 1) {
                        this.f20894f = true;
                    }
                    this.f20896h.m22987a(this.f20897i.K, this.f20895g, chatMessage, this.f20897i.f20909H);
                    notifyDataSetChanged();
                    return;
                }
                m22540j();
            }
        }

        protected void m22539i() {
            if (this.f20897i.K != null) {
                this.f20895g = this.f20896h.m22986a(this.f20897i.K, this.f20897i.K.m19222j(), this.f20897i.f20909H);
                if (this.f20897i.K.mo6349t()) {
                    f();
                } else {
                    e();
                }
                if (this.f20897i.K instanceof PeerChat) {
                    this.f20897i.f20904C = ((PeerChat) this.f20897i.K).mo6334S();
                }
                notifyDataSetChanged();
            }
        }

        public void m22540j() {
            int i;
            int i2;
            int i3 = 0;
            this.f20894f = false;
            Object obj = null;
            if (this.f20897i.f20919l.getLastVisiblePosition() == this.f20897i.f20919l.getCount() - 1) {
                i = 1;
            } else {
                i = 0;
            }
            if (i == 0) {
                int i4 = 0;
                int i5 = 0;
                while (i4 < this.f20897i.f20919l.getCount()) {
                    ChatMessage message;
                    ChatMessage chatMessage;
                    View childAt = this.f20897i.f20919l.getChildAt(i4);
                    if (childAt instanceof ChatMessageBaseListItem) {
                        message = ((ChatMessageBaseListItem) childAt).getMessage();
                        i5 = childAt.getTop();
                        chatMessage = message;
                    } else {
                        chatMessage = message;
                    }
                    i4++;
                    message = chatMessage;
                }
                i2 = i5;
            } else {
                i2 = 0;
            }
            m22539i();
            if (i != 0) {
                this.f20897i.f20919l.smoothScrollToPosition(this.f20897i.f20919l.getCount() - 1);
            } else if (obj != null) {
                while (i3 < this.f20895g.size()) {
                    if (((ChatMessage) this.f20895g.get(i3)).equals(obj)) {
                        this.f20897i.f20919l.setSelectionFromTop(this.f20897i.f20919l.getHeaderViewsCount() + i3, i2);
                        return;
                    }
                    i3++;
                }
            }
        }

        public void m22537c(int i) {
            m22541k();
        }

        protected void m22541k() {
            if (this.f20897i.K != null && !this.f20897i.K.m19233u()) {
                if (!this.f20897i.f20906E) {
                    this.f20897i.f20907F = true;
                } else if (this.f20897i.K.mo6349t()) {
                    this.f20897i.K.m19231s();
                } else {
                    e();
                }
            }
        }

        public View m22534a(int i, View view, ViewGroup viewGroup) {
            ChatMessageListItemInterface a;
            ChatMessage d = m22538d(i);
            if (d.m19389f().equals(((ChatMessage) this.f20895g.get(this.f20895g.size() - 1)).m19389f()) && this.f20899k) {
                this.f20899k = false;
                this.f20894f = false;
            }
            if (view == null) {
                switch (d.mo6360a()) {
                    case TEXT:
                        a = ChatMessageTextListItem.m23072a(this.f20898j);
                        break;
                    case PERFORMANCE:
                        ChatMessagePerformanceListItem a2 = ChatMessagePerformanceListItem.m23066a(this.f20898j);
                        this.f20897i.m22603a(a2);
                        break;
                    case GROUP_INVITATION:
                        a = ChatMessageGroupInviteListItem.m23022a(this.f20898j, this.f20897i);
                        break;
                    case GROUP_STATUS:
                        a = ChatMessageGroupStatusListItem.m23028a(this.f20898j);
                        break;
                    case f18117d:
                    case ARRANGEMENT:
                    case UNKNOWN:
                        a = ChatMessageUnknownListItem.m23082a(this.f20898j);
                        break;
                    default:
                        throw new RuntimeException("Invalid message type: " + d.mo6360a());
                }
                a.mo6770a(this.f20897i, this);
            } else {
                a = (ChatMessageListItemInterface) view;
            }
            a.mo6769a(this.f20897i.K, d, i);
            if (i == getCount() - 1 && this.f20894f) {
                this.f20894f = false;
                a.mo6772e();
            }
            return (View) a;
        }

        public int getCount() {
            return this.f20895g.size();
        }

        public ChatMessage m22538d(int i) {
            return (i < 0 || i >= this.f20895g.size()) ? null : (ChatMessage) this.f20895g.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public int getViewTypeCount() {
            return (ChatMessage.Type.values().length * 2) + 1;
        }

        public int getItemViewType(int i) {
            ChatMessage d = m22538d(i);
            switch (d.mo6360a()) {
                case TEXT:
                case PERFORMANCE:
                case GROUP_INVITATION:
                case ARRANGEMENT:
                    if (ChatUtils.a(d)) {
                        return d.mo6360a().ordinal() + ChatMessage.Type.values().length;
                    }
                    break;
                case GROUP_STATUS:
                    break;
                default:
                    return getViewTypeCount() - 1;
            }
            return d.mo6360a().ordinal();
        }

        public AccountIcon m22535a(long j) {
            return this.f20897i.K.m19186a(j);
        }

        public List<ChatMessage> m22542l() {
            return this.f20895g;
        }
    }

    private class ReverseOverShootInterpolator extends OvershootInterpolator {
        final /* synthetic */ ChatFragment f20900a;

        public ReverseOverShootInterpolator(ChatFragment chatFragment, float f) {
            this.f20900a = chatFragment;
            super(f);
        }

        public float getInterpolation(float f) {
            return super.getInterpolation(Math.abs(f - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
    }

    public static ChatFragment m22554b(Chat chat) {
        ChatFragment a = ChatFragment_.m22610P().m22609a();
        a.m22333d(chat);
        return a;
    }

    public static ChatFragment m22558c(String str) {
        ChatFragment a = ChatFragment_.m22610P().m22609a();
        a.m22334e(str);
        return a;
    }

    public static ChatFragment m22560d(String str) {
        ChatFragment a = ChatFragment_.m22610P().m22609a();
        a.m22335f(str);
        return a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19831a(ActionBarHighlightMode.NEVER);
        m19842a(true);
        m22549S();
        this.f20931x = new ChatAdapter(this, getActivity());
        this.f20905D = SingApplication.j();
    }

    @AfterViews
    protected void m22608z() {
        if (isAdded()) {
            getActivity().getWindow().setSoftInputMode(16);
            m22547Q();
            this.f20918k.setOnEditorActionListener(new C42732(this));
            WeakListener.m19083a(this.f20918k, new C42743(this));
            this.f20918k.setOnTouchListener(new C42754(this));
            this.f20919l.setTranscriptMode(1);
            this.f20919l.setOnTouchListener(new C42765(this));
            this.f20919l.setOnScrollListener(new PauseOnScrollListener(ImageLoader.a(), true, true));
            this.f20919l.setLoadingView(getActivity().getLayoutInflater().inflate(C1947R.layout.list_loading_view, null));
            View view = new View(getActivity());
            view.setLayoutParams(new LayoutParams(-1, getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium)));
            this.f20919l.addFooterView(view);
            this.f20908G = new OnGlobalLayoutListener(this, new C42776(this));
            LayoutUtils.m25854a(this.f20913f, this.f20908G);
            this.f20919l.setAdapter(this.f20931x);
        }
    }

    protected void m22604b(boolean z) {
        if (z) {
            ((MediaPlayingActivity) getActivity()).S().setVisibility(0);
            ((MediaPlayingActivity) getActivity()).R();
        } else {
            ((MediaPlayingActivity) getActivity()).S().setVisibility(4);
            ((MediaPlayingActivity) getActivity()).u();
        }
        if (getActivity() instanceof MasterActivity) {
            ((MasterActivity) getActivity()).I();
        }
    }

    public boolean mo6400c() {
        MiscUtils.m25891a(getActivity(), true);
        m22604b(true);
        return super.mo6400c();
    }

    private boolean m22546P() {
        return !this.f20918k.getText().toString().replace(" ", "").replace("\n", "").isEmpty();
    }

    public void onResume() {
        super.onResume();
        m22588F();
        this.f20931x.registerDataSetObserver(this.f20910I);
    }

    public void onPause() {
        super.onPause();
        this.f20931x.unregisterDataSetObserver(this.f20910I);
        MiscUtils.m25891a(getActivity(), true);
    }

    public void onStop() {
        super.onStop();
        if (this.K != null) {
            this.K.m19205b(this.f20909H);
        }
        ChatAnalyticsMonitor.a().b();
    }

    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.m25859b(this.f20913f, this.f20908G);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.K != null && this.K.mo6335a() == Type.PEER && this.K.m19223k()) {
            SingApplication.j().a(this.K, null);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.K.mo6347b() != Bucket.OTHER) {
            return;
        }
        if (configuration.hardKeyboardHidden == 1) {
            this.f20920m.setVisibility(8);
            this.f20930w.setVisibility(0);
            this.f20930w.setAccount(this.K.m19186a(this.K.mo6341f()));
        } else if (configuration.hardKeyboardHidden == 2) {
            this.f20920m.setVisibility(0);
            this.f20927t.setVisibility(8);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        if (this.K == null || !this.K.mo6350v()) {
            menuInflater.inflate(C1947R.menu.chat_menu, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != C1947R.id.details_info) {
            return super.onOptionsItemSelected(menuItem);
        }
        m22584A();
        return true;
    }

    public void m22584A() {
        if (this.f20905D.b() == ChatManager$ConnectionStatus.CONNECTED && this.K != null) {
            if (m22592J()) {
                mo6487a(ChatDetailsFragment.m22475b(this.K));
                return;
            }
            final TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getResources().getString(C1947R.string.chat_group_removed_leave_alert_title), getResources().getString(C1947R.string.chat_group_removed_leave_alert_message), true, true);
            textAlertDialog.m19804a(new Runnable(this) {
                final /* synthetic */ ChatFragment f20890b;

                public void run() {
                    textAlertDialog.dismiss();
                    final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(this.f20890b.getActivity(), (int) C1947R.string.chat_deleting_busy_message);
                    busyScreenDialog.show();
                    SingApplication.j().a(this.f20890b.K, new Completion<ChatStatus>(this) {
                        final /* synthetic */ C42797 f20888b;

                        public void m22532a(ChatStatus chatStatus) {
                            busyScreenDialog.dismiss();
                            this.f20888b.f20890b.m22586D();
                            Toaster.a(this.f20888b.f20890b.getActivity(), C1947R.string.chat_group_deleted);
                        }
                    });
                }
            });
            textAlertDialog.show();
        }
    }

    public void s_() {
        int i = 0;
        this.f20906E = false;
        this.f20907F = false;
        if (this.K != null) {
            this.K.m19205b(this.f20909H);
        }
        if (!(this.K == null || this.K.m19222j().isEmpty())) {
            this.f20931x.m22540j();
        }
        View view = this.f20929v;
        if (this.f20931x.getCount() != 0) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public void mo6551a(ChatStatus chatStatus) {
        super.mo6551a(chatStatus);
        this.f20929v.setVisibility(8);
        this.f20931x.e();
        switch (chatStatus) {
        }
    }

    public void a_(Chat chat) {
        super.a_(chat);
        if ((this.K instanceof PeerChat) && SingApplication.j().a(this.K.mo6341f())) {
            m22586D();
            return;
        }
        this.K.m19205b(this.f20909H);
        this.K.m19192a(this.f20909H);
        if (this.f20931x.getCount() == 0 && !this.K.m19222j().isEmpty()) {
            this.f20931x.m22540j();
        }
        ChatNotification.m22636b(getActivity(), this.K.m19209c());
        MessageCenterFragment.f21134r = this.K.mo6347b();
        m22588F();
        ChatAnalytics.m22397c(chat);
    }

    public void mo6553c(Chat chat) {
        super.a_(chat);
        m22585B();
        m22591I();
    }

    public void mo6550a(Chat chat, ChatStatus chatStatus) {
        super.mo6550a(chat, chatStatus);
        if (chatStatus.m19417a()) {
            this.f20931x.e();
            m22588F();
            return;
        }
        m22585B();
    }

    protected void m22585B() {
        this.f20906E = true;
        if (this.f20931x.getCount() == 0 || this.f20907F) {
            this.f20931x.m22541k();
            mo6749E();
        }
        if (this.f20929v.getVisibility() == 0) {
            this.f20929v.setVisibility(8);
        }
    }

    public void t_() {
        super.t_();
    }

    protected void m22586D() {
        if (isAdded()) {
            getFragmentManager().popBackStack();
        }
    }

    @SupposeUiThread
    protected void mo6749E() {
        if (!isAdded()) {
            return;
        }
        if (this.f20931x.getCount() != 0 || this.K == null || this.K.m19233u()) {
            this.f20927t.setVisibility(8);
            this.f20927t.clearAnimation();
        } else if (this.f20927t.getVisibility() != 0 && this.K.mo6335a() != Type.GROUP) {
            this.f20927t.setVisibility(0);
            this.f20927t.clearAnimation();
            this.f20927t.startAnimation(this.f20911L);
        }
    }

    private void m22547Q() {
        this.f20911L = new TranslateAnimation(1, 0.0f, 1, 0.0f, 0, (float) (m22548R() * -1), 0, 0.0f);
        this.f20911L.setDuration((long) 800);
        this.f20911L.setFillAfter(true);
        this.f20911L.setInterpolator(new OvershootInterpolator(1.25f));
        this.f20911L.setAnimationListener(new C42819(this));
        this.f20912M = new TranslateAnimation(1, 0.0f, 1, 0.0f, 0, (float) (m22548R() * -1), 0, 0.0f);
        this.f20912M.setDuration((long) 800);
        this.f20912M.setFillAfter(true);
        this.f20912M.setInterpolator(new ReverseOverShootInterpolator(this, 1.25f));
        this.f20912M.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ChatFragment f20851a;

            {
                this.f20851a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.f20851a.f20927t.setVisibility(8);
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private int m22548R() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    protected void m22588F() {
        if (this.K != null && isAdded()) {
            mo6749E();
            m22593K();
            m22589G();
            m22590H();
        }
    }

    protected void m22589G() {
        if (isAdded()) {
            int i;
            if (this.K.m19227o()) {
                i = C1947R.drawable.icn_notification_off_gray;
            } else {
                i = 0;
            }
            m19837a(ChatUtils.a(this.K), null, i);
        }
    }

    protected void m22590H() {
        if (this.K.mo6347b() == Bucket.OTHER) {
            this.f20920m.setVisibility(0);
            this.f20930w.setVisibility(8);
            AccountIcon a = this.K.m19186a(this.K.mo6341f());
            this.f20922o.setAccount(a);
            final String str = (a == null || a.handle == null) ? "" : a.handle;
            this.f20923p.setText(getResources().getString(C1947R.string.chat_follow_follow, new Object[]{str}));
            this.f20924q.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ ChatFragment f20858b;

                class C42661 implements ToggleFollowStateListener {
                    final /* synthetic */ AnonymousClass11 f20856a;

                    C42661(AnonymousClass11 anonymousClass11) {
                        this.f20856a = anonymousClass11;
                    }

                    public void mo6399a(final boolean z, boolean z2, final boolean z3) {
                        this.f20856a.f20858b.m19838a(new Runnable(this) {
                            final /* synthetic */ C42661 f20855c;

                            class C42641 implements Runnable {
                                final /* synthetic */ C42651 f20852a;

                                C42641(C42651 c42651) {
                                    this.f20852a = c42651;
                                }

                                public void run() {
                                    if (this.f20852a.f20855c.f20856a.f20858b.isAdded()) {
                                        this.f20852a.f20855c.f20856a.f20858b.f20926s.setVisibility(0);
                                        this.f20852a.f20855c.f20856a.f20858b.f20926s.startAnimation(this.f20852a.f20855c.f20856a.f20858b.f20932y);
                                    }
                                }
                            }

                            public void run() {
                                if (this.f20855c.f20856a.f20858b.isAdded()) {
                                    this.f20855c.f20856a.f20858b.f20925r.setVisibility(8);
                                    if (z) {
                                        this.f20855c.f20856a.f20858b.f20923p.setText(this.f20855c.f20856a.f20858b.getResources().getString(C1947R.string.chat_follow_followed, new Object[]{str}));
                                        this.f20855c.f20856a.f20858b.f20921n.startAnimation(this.f20855c.f20856a.f20858b.f20933z);
                                        new Handler().postDelayed(new C42641(this), 1250);
                                    } else if (z3) {
                                        this.f20855c.f20856a.f20858b.f20924q.setVisibility(0);
                                        this.f20855c.f20856a.f20858b.m19846b((int) C1947R.string.profile_follow_limit_reached);
                                    } else {
                                        this.f20855c.f20856a.f20858b.f20924q.setVisibility(0);
                                        this.f20855c.f20856a.f20858b.m19846b((int) C1947R.string.profile_update_error);
                                    }
                                }
                            }
                        });
                    }
                }

                public void onClick(View view) {
                    this.f20858b.f20924q.setVisibility(8);
                    this.f20858b.f20925r.setVisibility(0);
                    FollowManager.m18204a().m18215a(Long.valueOf(this.f20858b.K.mo6341f()), new C42661(this));
                }
            });
            return;
        }
        this.f20920m.setVisibility(8);
        if (this.K.mo6335a() == Type.PEER) {
            this.f20930w.setVisibility(0);
            this.f20930w.setAccount(this.K.m19186a(this.K.mo6341f()));
        }
    }

    protected void m22591I() {
        boolean z = false;
        if (isAdded() && !this.f20903B) {
            boolean z2;
            this.f20903B = true;
            List<ChatMessage> j = this.K.m19222j();
            boolean z3 = (this.K instanceof GroupChat) && j.size() <= this.K.mo6342g().size();
            if (z3) {
                for (ChatMessage a : j) {
                    if (a.mo6360a() != ChatMessage.Type.GROUP_STATUS) {
                        z2 = false;
                        break;
                    }
                }
            }
            z2 = z3;
            if ((this.K instanceof PeerChat) && j.size() == 0) {
                z = true;
            }
            if (z || r0) {
                ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.f20918k, 1);
            } else {
                ((MediaPlayingActivity) getActivity()).R();
            }
        }
    }

    protected boolean m22592J() {
        if (!(this.K instanceof GroupChat)) {
            return true;
        }
        switch (((GroupChat) this.K).m19541b(UserManager.a().f())) {
            case PENDING:
            case JOINED:
                return true;
            default:
                return false;
        }
    }

    protected void m22593K() {
        if (!isAdded()) {
            return;
        }
        if (m22592J()) {
            this.f20917j.setVisibility(0);
            this.f20916i.setVisibility(0);
            return;
        }
        this.f20917j.setVisibility(8);
        this.f20916i.setVisibility(8);
        MiscUtils.m25891a(getActivity(), true);
    }

    protected void m22594L() {
        if (isAdded()) {
            this.f20919l.setSelection(this.f20919l.getLastVisiblePosition());
        }
    }

    @Click
    protected void m22595M() {
        final String obj = this.f20918k.getText().toString();
        if (m22546P()) {
            this.f20918k.setText("");
            final String g = m22564g(obj);
            if (g != null) {
                PerformanceManager.a().a(g, new PerformanceManager$PerformanceResponseCallback(this) {
                    final /* synthetic */ ChatFragment f20861c;

                    public void handleResponse(PerformanceResponse performanceResponse) {
                        if (!performanceResponse.a() || performanceResponse.mPerformance == null) {
                            this.f20861c.K.mo6346a(new TextChatMessage(obj));
                            return;
                        }
                        this.f20861c.K.mo6346a(new PerformanceChatMessage(performanceResponse.mPerformance));
                        String a = this.f20861c.m22552a(obj, g);
                        if (a != null) {
                            this.f20861c.K.mo6346a(new TextChatMessage(a));
                        }
                    }
                });
            } else {
                this.K.mo6346a(new TextChatMessage(obj));
            }
        }
    }

    private void m22549S() {
        this.f20933z = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 0.0f);
        alphaAnimation.setDuration(500);
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        translateAnimation.setDuration(500);
        this.f20933z.addAnimation(translateAnimation);
        this.f20933z.addAnimation(alphaAnimation);
        this.f20933z.setStartOffset(1000);
        this.f20933z.setFillAfter(true);
        this.f20932y = new AnimationSet(true);
        alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setDuration(500);
        translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.5f, 1, 0.0f);
        translateAnimation.setDuration(500);
        this.f20932y.addAnimation(translateAnimation);
        this.f20932y.addAnimation(alphaAnimation);
        this.f20932y.setFillAfter(true);
        this.f20932y.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ ChatFragment f20862a;

            {
                this.f20862a = r1;
            }

            public void onAnimationStart(Animation animation) {
                this.f20862a.f20902A = new AnimationSet(true);
                Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (this.f20862a.f20920m.getHeight() * -1));
                translateAnimation.setDuration(500);
                this.f20862a.f20902A.addAnimation(translateAnimation);
                this.f20862a.f20902A.setFillAfter(true);
                this.f20862a.f20902A.setStartOffset(2000);
                this.f20862a.f20920m.startAnimation(this.f20862a.f20902A);
            }

            public void onAnimationEnd(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void mo6746a(AccountIcon accountIcon) {
        mo6487a(ProfileFragment.m21036a(accountIcon));
    }

    public void mo6747a(ChatMessage chatMessage) {
        if (chatMessage.m19387d() == State.ERROR && ChatUtils.a(chatMessage)) {
            switch (chatMessage.m19388e()) {
                case NETWORK_ERROR:
                case DELIVERY_FAILED:
                    this.K.mo6346a(chatMessage);
                    return;
                case NON_MEMBER:
                case CHAT_NOT_FOUND:
                case BAD_REQUEST:
                    ChatUtils.a(getActivity(), chatMessage.m19388e());
                    return;
                case QUEUE_FULL:
                    return;
                default:
                    Toaster.a(getActivity(), C1947R.string.chat_error_send);
                    return;
            }
        }
    }

    public void mo6748a(final GroupInvitationChatMessage groupInvitationChatMessage) {
        ChatManager j = SingApplication.j();
        String o = groupInvitationChatMessage.m19613o();
        if (o == null || o.isEmpty()) {
            throw new NullPointerException("Expected JID in group chat invite but it was NULL! NULL I tell you!");
        }
        GroupMemberStatus b = groupInvitationChatMessage.m19609b(UserManager.a().f());
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(ChatUtils.a(groupInvitationChatMessage) ? C1947R.string.chat_invite_removed_message : C1947R.string.chat_invite_invalid_message), null, true, false);
        textAlertDialog.m19800a((int) C1947R.string.core_ok, 0);
        if (b == GroupMemberStatus.NONE) {
            textAlertDialog.show();
        } else if (m22596N()) {
            final BusyDialog busyDialog = new BusyDialog(getActivity(), getResources().getString(C1947R.string.chat_joining_group_busy_message));
            busyDialog.show();
            final boolean a = ChatUtils.a(UserManager.a().f(), o);
            j.a(o, new ChatManager$ChatCallback(this) {
                final /* synthetic */ ChatFragment f20866d;

                public void mo6326a(Chat chat, ChatStatus chatStatus) {
                    if (this.f20866d.isAdded()) {
                        busyDialog.hide();
                        if (chatStatus != ChatStatus.OK) {
                            ChatUtils.a(this.f20866d.getActivity(), C1947R.string.chat_error_join_group, chatStatus);
                            return;
                        }
                        if (!a) {
                            ChatAnalytics.m22380a(groupInvitationChatMessage.m19385b(), FollowManager.m18204a().m18222a(groupInvitationChatMessage.m19385b()), chat.m19209c());
                        }
                        this.f20866d.mo6487a(ChatFragment.m22554b(chat));
                    }
                }
            });
        } else {
            ChatUtils.a(this, o);
        }
    }

    protected boolean m22596N() {
        int i = 0;
        for (Chat a : SingApplication.j().a(Bucket.INBOX)) {
            int i2;
            if (a.mo6335a() == Type.GROUP) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            i = i2 + i;
        }
        if (i < getResources().getInteger(C1947R.integer.chat_max_group_chats)) {
            return true;
        }
        return false;
    }

    public String mo6383s() {
        return f20901e;
    }

    @Click
    protected void m22597O() {
        ((MediaPlayingActivity) getActivity()).ad().m22307a(this.f20914g);
    }

    void m22603a(ChatMessagePerformanceListItem chatMessagePerformanceListItem) {
        final ChatMessagePerformanceBody body = chatMessagePerformanceListItem.getBody();
        body.setListener(new PerformanceItemListener(this) {
            final /* synthetic */ ChatFragment f20868a;

            class C42671 implements NowPlayingFragmentMenuSelectedCallback {
                final /* synthetic */ AnonymousClass15 f20867a;

                C42671(AnonymousClass15 anonymousClass15) {
                    this.f20867a = anonymousClass15;
                }

                public boolean mo6745a(MenuItem menuItem) {
                    return this.f20867a.f20868a.onOptionsItemSelected(menuItem);
                }
            }

            {
                this.f20868a = r1;
            }

            public void mo6471a(MediaPlayingViewInterface mediaPlayingViewInterface, AccountIcon accountIcon) {
                this.f20868a.mo6746a(accountIcon);
            }

            public void mo6472a(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                Log.b(ChatFragment.f20901e, "mPerformanceItemListener - onItemClicked called");
                m22518a(performanceV2, true);
            }

            public void mo6473b(MediaPlayingViewInterface mediaPlayingViewInterface, PerformanceV2 performanceV2) {
                Log.b(ChatFragment.f20901e, "mPerformanceItemListener - onAlbumArtClicked called");
                m22518a(performanceV2, MiscUtils.m25895a(performanceV2));
            }

            private void m22518a(PerformanceV2 performanceV2, boolean z) {
                this.f20868a.m22604b(true);
                ((MediaPlayingActivity) this.f20868a.getActivity()).a(performanceV2, z, true, null, Long.valueOf(-1), null, true, C1947R.menu.chat_menu, new C42671(this));
            }
        });
        body.setOnJoinListener(new OnClickListener(this) {
            final /* synthetic */ ChatFragment f20870b;

            public void onClick(View view) {
                PreSingActivity.m24763a(this.f20870b.getActivity()).m24796a(StartupMode.OPENCALL).m24793a(body.getPerformance()).a();
            }
        });
        body.setOnMoreListener(new OnClickListener(this) {
            final /* synthetic */ ChatFragment f20876b;

            public void onClick(View view) {
                final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity) this.f20876b.getActivity();
                mediaPlayingActivity.ad().m22308a(body.getPerformance(), new BookmarkDialogCallback(this) {
                    final /* synthetic */ AnonymousClass17 f20874b;

                    class C42681 implements Runnable {
                        final /* synthetic */ C42701 f20871a;

                        C42681(C42701 c42701) {
                            this.f20871a = c42701;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f20871a.f20874b.f20876b, this.f20871a.f20874b.f20876b.f20914g, this.f20871a.f20874b.f20876b.f20915h, true);
                        }
                    }

                    class C42692 implements Runnable {
                        final /* synthetic */ C42701 f20872a;

                        C42692(C42701 c42701) {
                            this.f20872a = c42701;
                        }

                        public void run() {
                            mediaPlayingActivity.ad().m22306a(this.f20872a.f20874b.f20876b, this.f20872a.f20874b.f20876b.f20914g, this.f20872a.f20874b.f20876b.f20915h, false);
                        }
                    }

                    public void mo6428a(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C42681(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "BOOKMARKED_PERFORMANCE", body.getPerformance());
                    }

                    public void mo6429b(PerformanceV2 performanceV2) {
                        new UiHandler(mediaPlayingActivity).m19081a(new C42692(this));
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERFORMANCE", body.getPerformance());
                    }

                    public void mo6430c(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNBOOKMARKED_PERFORMANCE", body.getPerformance());
                    }

                    public void mo6431d(PerformanceV2 performanceV2) {
                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "UNFAVORITED_PERFORMANCE", body.getPerformance());
                    }
                }, false);
            }
        });
    }

    private String m22564g(String str) {
        try {
            Uri parse = Uri.parse(str);
            if ((parse.getScheme().equals(Constants.HTTP) || parse.getScheme().equals(Constants.HTTPS)) && parse.getHost().equals("www.smule.com") && Pattern.compile("[1-9][0-9]*_[0-9]*").matcher(str).find()) {
                return m22566h(parse.getLastPathSegment());
            }
        } catch (Exception e) {
        }
        return null;
    }

    private String m22552a(String str, String str2) {
        int indexOf = str.indexOf(str2) + str2.length();
        while (indexOf < str.length() && Character.isSpaceChar(str.charAt(indexOf))) {
            indexOf++;
        }
        return indexOf < str.length() ? str.substring(indexOf) : null;
    }

    private String m22566h(String str) {
        int length = str == null ? 0 : str.length();
        int i = 0;
        while (i < length && (str.charAt(i) == '_' || Character.isDigit(str.charAt(i)))) {
            i++;
        }
        return i > 0 ? str.substring(0, i) : null;
    }
}
