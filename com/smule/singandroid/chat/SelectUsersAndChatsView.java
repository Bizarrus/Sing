package com.smule.singandroid.chat;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.foound.widget.AmazingListView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.FolloweesResponse;
import com.smule.android.network.managers.FollowManager.FolloweesResponseCallback;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.AccountSearchResponse;
import com.smule.android.network.managers.SearchManager.AccountSearchResultResponseCallback;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.android.utils.WeakListener;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.SelectUsersAndChatsAdapter.ViewHolder;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SelectUsersAndChatsView extends FrameLayout {
    static final String f21206a = SelectUsersAndChatsView.class.getName();
    protected static final List<AccountIcon> f21207z = new ArrayList();
    OnGlobalLayoutListener f21208A = new OnGlobalLayoutListener(this, new C43337(this));
    final OnTouchListener f21209B = new OnTouchListener(this) {
        final /* synthetic */ SelectUsersAndChatsView f21193a;

        {
            this.f21193a = r1;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            Toaster.a(this.f21193a.getContext(), this.f21193a.getContext().getString(C1947R.string.chat_share_max_selected, new Object[]{Integer.valueOf(this.f21193a.getResources().getInteger(C1947R.integer.chat_max_share_targets))}), Toaster$Duration.SHORT);
            return true;
        }
    };
    private boolean f21210C = false;
    private boolean f21211D = false;
    private Handler f21212E = new Handler(Looper.getMainLooper());
    private long f21213F = 0;
    private long f21214G;
    private String f21215H;
    private Future<?> f21216I;
    private Runnable f21217J;
    private Chat f21218K;
    private boolean f21219L = false;
    private SearchClkContext f21220M;
    SelectUsersAndChatsListener f21221b;
    @ViewById
    protected ViewGroup f21222c;
    @ViewById
    protected ViewGroup f21223d;
    @ViewById
    protected ViewGroup f21224e;
    @ViewById
    protected ViewGroup f21225f;
    @ViewById
    protected EditText f21226g;
    @ViewById
    protected View f21227h;
    @ViewById
    protected View f21228i;
    @ViewById
    protected View f21229j;
    @ViewById
    protected RecyclerView f21230k;
    @ViewById
    protected ViewGroup f21231l;
    @ViewById
    protected AmazingListView f21232m;
    @ViewById
    protected ViewGroup f21233n;
    @ViewById
    protected ImageView f21234o;
    @ViewById
    protected View f21235p;
    @ViewById
    protected ViewGroup f21236q;
    protected Set<Integer> f21237r;
    protected Set<AccountIcon> f21238s;
    protected SelectedUsersAndChatsAdapter f21239t;
    protected SelectUsersAndChatsAdapter f21240u;
    protected LayoutTransition f21241v;
    protected LayoutTransition f21242w;
    protected boolean f21243x;
    protected Mode f21244y = Mode.USERS_AND_CHATS;

    public interface SelectUsersAndChatsListener {
        boolean mo6728a(AccountIcon accountIcon);

        boolean mo6729a(Chat chat);

        void o_();

        void p_();

        void q_();

        void r_();
    }

    class C43271 extends AdapterDataObserver {
        final /* synthetic */ SelectUsersAndChatsView f21194a;

        C43271(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21194a = selectUsersAndChatsView;
        }

        public void onItemRangeInserted(int i, int i2) {
            super.onItemRangeInserted(i, i2);
            if (this.f21194a.f21243x) {
                if (this.f21194a.f21230k.getVisibility() != 0) {
                    this.f21194a.f21230k.setVisibility(0);
                }
                this.f21194a.f21230k.smoothScrollToPosition(i);
                if (this.f21194a.f21221b != null) {
                    this.f21194a.f21221b.p_();
                }
                this.f21194a.m22892c();
            }
        }

        public void onItemRangeRemoved(int i, int i2) {
            super.onItemRangeRemoved(i, i2);
            if (this.f21194a.f21243x) {
                if (this.f21194a.f21239t.m22912a()) {
                    this.f21194a.f21230k.setVisibility(8);
                }
                if (this.f21194a.f21221b != null) {
                    this.f21194a.f21221b.p_();
                }
                this.f21194a.m22892c();
            }
        }
    }

    class C43282 implements TextWatcher {
        final /* synthetic */ SelectUsersAndChatsView f21195a;

        C43282(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21195a = selectUsersAndChatsView;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.f21195a.f21228i.setVisibility(editable.length() > 0 ? 0 : 8);
            String obj = editable.toString();
            if (obj.isEmpty()) {
                this.f21195a.f21226g.setCompoundDrawablesWithIntrinsicBounds(C1947R.drawable.icn_magnify_gray, 0, 0, 0);
            } else {
                this.f21195a.f21226g.setCompoundDrawables(null, null, null, null);
            }
            if (this.f21195a.m22890c(obj).length() < 2) {
                this.f21195a.m22896g();
                this.f21195a.f21215H = "";
                this.f21195a.m22883a(null);
                return;
            }
            this.f21195a.m22882a(obj);
        }
    }

    class C43293 implements OnEditorActionListener {
        final /* synthetic */ SelectUsersAndChatsView f21196a;

        C43293(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21196a = selectUsersAndChatsView;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 3) {
                return false;
            }
            this.f21196a.m22887b(this.f21196a.f21226g.getText().toString());
            this.f21196a.m22901l();
            return true;
        }
    }

    class C43304 implements OnFocusChangeListener {
        final /* synthetic */ SelectUsersAndChatsView f21197a;

        C43304(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21197a = selectUsersAndChatsView;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                Analytics.m17848a(this.f21197a.f21220M);
            }
        }
    }

    class C43315 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView f21198a;

        C43315(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21198a = selectUsersAndChatsView;
        }

        public void onClick(View view) {
            this.f21198a.m22880a(view);
        }
    }

    class C43326 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView f21199a;

        C43326(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21199a = selectUsersAndChatsView;
        }

        public void onClick(View view) {
            this.f21199a.f21226g.getText().clear();
            this.f21199a.m22893d();
        }
    }

    class C43337 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ SelectUsersAndChatsView f21200a;

        C43337(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21200a = selectUsersAndChatsView;
        }

        public void onGlobalLayout() {
            if (this.f21200a.f21226g.hasFocus()) {
                Rect rect = new Rect();
                this.f21200a.f21222c.getWindowVisibleDisplayFrame(rect);
                int height = this.f21200a.f21222c.getRootView().getHeight();
                if (((double) (height - rect.bottom)) > ((double) height) * 0.15d) {
                    this.f21200a.f21210C = true;
                    this.f21200a.f21211D = true;
                    this.f21200a.f21234o.setVisibility(0);
                    this.f21200a.f21227h.setVisibility(8);
                    if (this.f21200a.f21221b != null) {
                        this.f21200a.f21221b.q_();
                    }
                    if (!this.f21200a.f21240u.m22869j()) {
                        this.f21200a.f21235p.setVisibility(0);
                        this.f21200a.f21226g.setCompoundDrawables(null, null, null, null);
                        return;
                    }
                    return;
                }
                if (this.f21200a.f21210C && this.f21200a.f21226g.getText().length() == 0) {
                    this.f21200a.m22899j();
                }
                this.f21200a.f21210C = false;
            }
        }
    }

    class C43348 implements Runnable {
        final /* synthetic */ SelectUsersAndChatsView f21201a;

        C43348(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21201a = selectUsersAndChatsView;
        }

        public void run() {
            this.f21201a.f21223d.setLayoutTransition(this.f21201a.f21241v);
        }
    }

    class C43359 implements FolloweesResponseCallback {
        final /* synthetic */ SelectUsersAndChatsView f21202a;

        C43359(SelectUsersAndChatsView selectUsersAndChatsView) {
            this.f21202a = selectUsersAndChatsView;
        }

        public void handleResponse(FolloweesResponse followeesResponse) {
            if (followeesResponse.a()) {
                this.f21202a.m22883a(followeesResponse.mFollowees);
            } else {
                this.f21202a.f21236q.setVisibility(this.f21202a.f21224e.getVisibility() == 0 ? 0 : 8);
            }
        }
    }

    enum Mode {
        USERS,
        USERS_AND_CHATS
    }

    public SelectUsersAndChatsView(Context context) {
        super(context);
        m22879a(context, null, 0);
    }

    public SelectUsersAndChatsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m22879a(context, attributeSet, 0);
    }

    public SelectUsersAndChatsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m22879a(context, attributeSet, i);
    }

    public List<AccountIcon> getSelectedAccounts() {
        return this.f21239t.m22917b();
    }

    public void setSelectedAccounts(List<AccountIcon> list) {
        for (AccountIcon a : list) {
            this.f21239t.m22913a(a);
        }
    }

    public List<Chat> getSelectedChats() {
        return this.f21239t.m22922c();
    }

    public int getSelectedCount() {
        return this.f21239t.getItemCount();
    }

    public void setSelectUsersAndChatsListener(SelectUsersAndChatsListener selectUsersAndChatsListener) {
        this.f21221b = selectUsersAndChatsListener;
    }

    public void setIsInShareInviteActivity(boolean z) {
        this.f21219L = z;
    }

    protected void m22879a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SelectUsersAndChatsView, i, 0);
        if (obtainStyledAttributes.getInt(0, 1) == 1) {
            this.f21244y = Mode.USERS;
        } else {
            this.f21244y = Mode.USERS_AND_CHATS;
        }
        obtainStyledAttributes.recycle();
        this.f21239t = new SelectedUsersAndChatsAdapter(getContext(), this);
        this.f21240u = new SelectUsersAndChatsAdapter(getContext(), this.f21239t);
        this.f21237r = new HashSet();
        this.f21238s = new HashSet();
        this.f21239t.registerAdapterDataObserver(new C43271(this));
    }

    public void setSearchClkContext(SearchClkContext searchClkContext) {
        this.f21220M = searchClkContext;
    }

    private CharSequence m22872a(CharSequence charSequence) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
            spannableStringBuilder.append(charSequence);
            Drawable drawable = getResources().getDrawable(C1947R.drawable.search_gray);
            drawable.setBounds(0, 0, 0, 0);
            spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
            return spannableStringBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return charSequence;
        }
    }

    @AfterViews
    protected void m22878a() {
        this.f21226g.setHint(m22872a(getResources().getString(C1947R.string.chat_search_prompt)));
        WeakListener.m19083a(this.f21226g, new C43282(this));
        this.f21226g.setOnEditorActionListener(new C43293(this));
        this.f21226g.setOnFocusChangeListener(new C43304(this));
        this.f21240u.m22858a(new C43315(this));
        this.f21232m.setAdapter(this.f21240u);
        this.f21240u.m22861a(this.f21232m);
        this.f21230k.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.f21230k.setAdapter(this.f21239t);
        m22894e();
        this.f21234o.setOnClickListener(new C43326(this));
        this.f21227h.setVisibility(0);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f21243x = true;
        LayoutUtils.m25854a((View) this, this.f21208A);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f21243x = false;
        LayoutUtils.m25859b(this, this.f21208A);
    }

    @Click
    protected void m22885b() {
        if (this.f21221b != null) {
            this.f21221b.o_();
        }
    }

    public void m22881a(Chat chat) {
        this.f21224e.setVisibility(0);
        this.f21236q.setVisibility(8);
        this.f21218K = chat;
        m22886b(chat);
        m22895f();
    }

    public void setSelectedPositions(List<Integer> list) {
        this.f21237r.addAll(list);
        for (Integer intValue : this.f21237r) {
            this.f21239t.m22916a(this.f21240u.m22867d(intValue.intValue()));
        }
        m22892c();
    }

    public List<Integer> getSelectedPositions() {
        return new ArrayList(this.f21237r);
    }

    public void setSelectedAccountIcons(List<AccountIcon> list) {
        this.f21238s.addAll(list);
        for (AccountIcon a : this.f21238s) {
            this.f21239t.m22913a(a);
        }
        m22892c();
    }

    public List<AccountIcon> getSelectedAccountIcons() {
        return new ArrayList(this.f21238s);
    }

    public void m22880a(View view) {
        Object R;
        boolean z = true;
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        boolean z2 = !viewHolder.f21170e.isChecked();
        Object item = this.f21240u.getItem(viewHolder.f21174i);
        boolean j = this.f21240u.m22869j();
        if (!j) {
            z = z2;
        } else if (this.f21239t.m22926c(item)) {
            m22899j();
            return;
        }
        if (this.f21244y == Mode.USERS && (item instanceof PeerChat)) {
            R = ((PeerChat) item).mo6333R();
        } else {
            R = item;
        }
        if (this.f21221b != null && z) {
            if ((R instanceof AccountIcon) && !this.f21221b.mo6728a((AccountIcon) R)) {
                return;
            }
            if ((R instanceof Chat) && !this.f21221b.mo6729a((Chat) R)) {
                return;
            }
        }
        if (j) {
            viewHolder.f21170e.setChecked(false);
        } else {
            viewHolder.f21170e.setChecked(z);
        }
        if (z) {
            if (R instanceof AccountIcon) {
                this.f21238s.add((AccountIcon) R);
            } else {
                this.f21237r.add(Integer.valueOf(viewHolder.f21174i));
            }
            this.f21239t.m22916a(R);
        } else {
            if (R instanceof AccountIcon) {
                this.f21238s.remove(R);
            } else {
                this.f21237r.remove(Integer.valueOf(viewHolder.f21174i));
            }
            this.f21239t.m22921b(R);
        }
        if (j) {
            Analytics.m17849a(this.f21220M, this.f21240u.getCount(), viewHolder.f21174i, this.f21226g.getText().toString().isEmpty() ? null : this.f21226g.getText().toString(), this.f21214G, "@" + viewHolder.f21169d.getText().toString(), null, Integer.valueOf(viewHolder.f21174i));
            m22899j();
        }
    }

    public void m22884a(boolean z) {
        if (z) {
            this.f21225f.setBackgroundColor(getResources().getColor(C1947R.color.action_bar_background));
            this.f21229j.setVisibility(0);
            return;
        }
        this.f21225f.setBackgroundColor(-1);
        this.f21229j.setVisibility(8);
    }

    protected void m22892c() {
        if (!this.f21240u.m22869j()) {
            for (int i = 0; i < this.f21232m.getChildCount(); i++) {
                View childAt = this.f21232m.getChildAt(i);
                if (childAt.getTag() instanceof ViewHolder) {
                    ViewHolder viewHolder = (ViewHolder) childAt.getTag();
                    boolean c = this.f21239t.m22926c(viewHolder.f21166a);
                    if (!c && viewHolder.f21170e.isChecked()) {
                        this.f21237r.remove(Integer.valueOf(viewHolder.f21174i));
                    }
                    viewHolder.f21170e.setChecked(c);
                }
            }
        }
    }

    public boolean m22893d() {
        if (this.f21226g.hasFocus()) {
            clearFocus();
        }
        if (!this.f21211D) {
            return false;
        }
        if (!this.f21210C || this.f21226g.getText().length() == 0) {
            m22899j();
        } else {
            m22901l();
        }
        return true;
    }

    protected void m22894e() {
        int integer = getResources().getInteger(C1947R.integer.new_chat_fade_in_duration);
        this.f21241v = new LayoutTransition();
        this.f21241v.setDuration(2, (long) integer);
        this.f21241v.setDuration(0, (long) integer);
        this.f21241v.setStartDelay(1, 10);
        this.f21241v.setDuration(3, 10);
        this.f21242w = new LayoutTransition();
        this.f21242w.setDuration(2, 0);
        this.f21242w.setDuration(0, 0);
        this.f21242w.setStartDelay(1, 0);
        this.f21242w.setDuration(3, 0);
        ItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(this.f21241v.getDuration(2));
        defaultItemAnimator.setRemoveDuration(10);
        this.f21230k.setItemAnimator(defaultItemAnimator);
        if (this.f21239t.getItemCount() > 0) {
            this.f21223d.setLayoutTransition(this.f21242w);
            this.f21230k.setVisibility(0);
            postDelayed(new C43348(this), 20);
            return;
        }
        this.f21223d.setLayoutTransition(this.f21241v);
    }

    protected void m22895f() {
        FollowManager.m18204a().m18214a(new C43359(this));
    }

    protected void m22883a(List<AccountIcon> list) {
        int i = 0;
        this.f21224e.setVisibility(8);
        this.f21236q.setVisibility(8);
        this.f21231l.setVisibility(8);
        if (this.f21239t.getItemCount() > 0) {
            this.f21230k.setVisibility(0);
        }
        if (this.f21211D) {
            this.f21235p.setVisibility(0);
        }
        if (list != null) {
            this.f21240u.m22863a((List) list);
        } else {
            this.f21240u.m22868i();
        }
        ViewGroup viewGroup = this.f21233n;
        if (!this.f21240u.isEmpty()) {
            i = 8;
        }
        viewGroup.setVisibility(i);
    }

    protected void m22886b(Chat chat) {
        this.f21240u.m22864a(m22891c(chat), chat);
    }

    protected List<Chat> m22891c(Chat chat) {
        List<Chat> a = SingApplication.j().a(Bucket.INBOX);
        List<Chat> arrayList = new ArrayList();
        int integer = getResources().getInteger(C1947R.integer.chat_max_recent);
        if (a != null) {
            for (Chat chat2 : a) {
                if ((this.f21244y == Mode.USERS_AND_CHATS || (this.f21244y == Mode.USERS && (chat2 instanceof PeerChat))) && !chat2.mo6350v() && chat2.mo6343i()) {
                    if (chat2 instanceof PeerChat) {
                        PeerChat peerChat = (PeerChat) chat2;
                        if (chat == null || !chat.mo6342g().contains(Long.valueOf(peerChat.mo6333R().accountId))) {
                            arrayList.add(chat2);
                        }
                    } else if (chat2 instanceof GroupChat) {
                        GroupChat groupChat = (GroupChat) chat2;
                        if (groupChat.m19212d() == ChatState.READY && groupChat.m19541b(UserManager.a().f()) == GroupMemberStatus.JOINED) {
                            arrayList.add(chat2);
                        } else if (groupChat.m19212d() == ChatState.LOADING && this.f21219L) {
                            arrayList.add(chat2);
                        }
                    }
                    if (arrayList.size() == integer) {
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    protected void m22882a(final String str) {
        long j = 2000;
        m22896g();
        this.f21217J = new Runnable(this) {
            final /* synthetic */ SelectUsersAndChatsView f21190b;

            public void run() {
                this.f21190b.m22887b(str);
            }
        };
        if (System.currentTimeMillis() - this.f21213F >= 2000) {
            j = 700;
        }
        this.f21212E.postDelayed(this.f21217J, j);
    }

    protected void m22896g() {
        if (this.f21212E != null && this.f21217J != null) {
            this.f21212E.removeCallbacks(this.f21217J);
        }
    }

    protected void m22897h() {
        if (this.f21216I != null) {
            this.f21216I.cancel(true);
        }
        m22896g();
    }

    protected void m22887b(final String str) {
        if (this.f21243x) {
            String c = m22890c(str);
            if (this.f21215H == null || !this.f21215H.equals(c)) {
                this.f21215H = c;
                if (c.length() < 2) {
                    this.f21215H = "";
                    m22883a(null);
                    return;
                }
                Log.b(f21206a, "Running search with term: " + c);
                m22897h();
                this.f21213F = System.currentTimeMillis();
                this.f21216I = SearchManager.m18331a().m18337a(c, 0, 25, new AccountSearchResultResponseCallback(this) {
                    final /* synthetic */ SelectUsersAndChatsView f21192b;

                    public void handleResponse(AccountSearchResponse accountSearchResponse) {
                        this.f21192b.f21214G = System.currentTimeMillis() - this.f21192b.f21213F;
                        if (!this.f21192b.f21243x || !str.equals(this.f21192b.f21226g.getText().toString())) {
                            return;
                        }
                        if (accountSearchResponse.a()) {
                            this.f21192b.mo6762b(accountSearchResponse.accountIcons);
                            return;
                        }
                        Toaster.a(this.f21192b.getContext(), C1947R.string.songbook_error_connecting_to_network);
                        this.f21192b.mo6762b(SelectUsersAndChatsView.f21207z);
                    }
                });
                return;
            }
            return;
        }
        Log.c(f21206a, "Ignoring search request: '" + str + "'");
    }

    protected String m22890c(String str) {
        return str.replace("#", "").replace("@", "");
    }

    @SupposeUiThread
    protected void mo6762b(List<AccountIcon> list) {
        int i = 8;
        if (this.f21243x) {
            this.f21240u.m22865b(list);
            this.f21230k.setVisibility(8);
            this.f21235p.setVisibility(8);
            this.f21233n.setVisibility(8);
            this.f21236q.setVisibility(8);
            ViewGroup viewGroup = this.f21231l;
            if (this.f21240u.getCount() == 0) {
                i = 0;
            }
            viewGroup.setVisibility(i);
            this.f21232m.setVisibility(0);
        }
    }

    @Click
    protected void m22898i() {
        this.f21226g.setText("");
    }

    @Click
    protected void m22899j() {
        this.f21226g.setText("");
        m22901l();
        this.f21235p.setVisibility(8);
        this.f21211D = false;
        this.f21234o.setVisibility(8);
        this.f21227h.setVisibility(0);
        this.f21226g.setCompoundDrawablesWithIntrinsicBounds(C1947R.drawable.icn_magnify_gray, 0, 0, 0);
        if (this.f21221b != null) {
            this.f21221b.r_();
        }
    }

    @Click
    protected void m22900k() {
        m22881a(this.f21218K);
    }

    protected void m22901l() {
        MiscUtils.m25894a(this.f21226g, true);
        this.f21210C = false;
    }

    public void m22889b(boolean z) {
        this.f21226g.setOnTouchListener(z ? this.f21209B : null);
    }
}
