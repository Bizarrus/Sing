/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.LayoutTransition
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.v7.widget.DefaultItemAnimator
 *  android.support.v7.widget.LinearLayoutManager
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$AdapterDataObserver
 *  android.support.v7.widget.RecyclerView$ItemAnimator
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  android.text.Editable
 *  android.text.SpannableStringBuilder
 *  android.text.TextWatcher
 *  android.text.style.ImageSpan
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnFocusChangeListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.CheckBox
 *  android.widget.EditText
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ListAdapter
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.foound.widget.AmazingListView
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.foound.widget.AmazingListView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.WeakListener;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.chat.PeerChat;
import com.smule.singandroid.R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.SelectUsersAndChatsAdapter;
import com.smule.singandroid.chat.SelectedUsersAndChatsAdapter;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SelectUsersAndChatsView
extends FrameLayout {
    static final String a = SelectUsersAndChatsView.class.getName();
    protected static final List<AccountIcon> z = new ArrayList<AccountIcon>();
    WeakListener.OnGlobalLayoutListener A;
    final View.OnTouchListener B;
    private boolean C = false;
    private boolean D = false;
    private Handler E = new Handler(Looper.getMainLooper());
    private long F = 0;
    private long G;
    private String H;
    private Future<?> I;
    private Runnable J;
    private Chat K;
    private boolean L = false;
    private Analytics M;
    SelectUsersAndChatsListener b;
    @ViewById
    protected ViewGroup c;
    @ViewById
    protected ViewGroup d;
    @ViewById
    protected ViewGroup e;
    @ViewById
    protected ViewGroup f;
    @ViewById
    protected EditText g;
    @ViewById
    protected View h;
    @ViewById
    protected View i;
    @ViewById
    protected View j;
    @ViewById
    protected RecyclerView k;
    @ViewById
    protected ViewGroup l;
    @ViewById
    protected AmazingListView m;
    @ViewById
    protected ViewGroup n;
    @ViewById
    protected ImageView o;
    @ViewById
    protected View p;
    @ViewById
    protected ViewGroup q;
    protected Set<Integer> r;
    protected Set<AccountIcon> s;
    protected SelectedUsersAndChatsAdapter t;
    protected SelectUsersAndChatsAdapter u;
    protected LayoutTransition v;
    protected LayoutTransition w;
    protected boolean x;
    protected Mode y = Mode.b;

    public SelectUsersAndChatsView(Context context) {
        super(context);
        this.A = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                block6 : {
                    block7 : {
                        block5 : {
                            if (!SelectUsersAndChatsView.this.g.hasFocus()) break block5;
                            Rect rect = new Rect();
                            SelectUsersAndChatsView.this.c.getWindowVisibleDisplayFrame(rect);
                            int n = SelectUsersAndChatsView.this.c.getRootView().getHeight();
                            if ((double)(n - rect.bottom) <= (double)n * 0.15) break block6;
                            SelectUsersAndChatsView.this.C = true;
                            SelectUsersAndChatsView.this.D = true;
                            SelectUsersAndChatsView.this.o.setVisibility(0);
                            SelectUsersAndChatsView.this.h.setVisibility(8);
                            if (SelectUsersAndChatsView.this.b != null) {
                                SelectUsersAndChatsView.this.b.u_();
                            }
                            if (!SelectUsersAndChatsView.this.u.m()) break block7;
                        }
                        return;
                    }
                    SelectUsersAndChatsView.this.p.setVisibility(0);
                    SelectUsersAndChatsView.this.g.setFocusable(true);
                    SelectUsersAndChatsView.this.g.setFocusableInTouchMode(true);
                    return;
                }
                if (SelectUsersAndChatsView.this.C && SelectUsersAndChatsView.this.g.getText().length() == 0) {
                    SelectUsersAndChatsView.this.j();
                }
                SelectUsersAndChatsView.this.C = false;
            }
        });
        this.B = new View.OnTouchListener(){

            public boolean onTouch(View view, MotionEvent motionEvent) {
                com.smule.android.utils.Toaster.a(SelectUsersAndChatsView.this.getContext(), SelectUsersAndChatsView.this.getContext().getString(2131296585, new Object[]{SelectUsersAndChatsView.this.getResources().getInteger(2131623949)}), Toaster.a);
                return true;
            }
        };
        this.a(context, null, 0);
    }

    public SelectUsersAndChatsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.A = new WeakListener.OnGlobalLayoutListener((Object)this, new );
        this.B = new ;
        this.a(context, attributeSet, 0);
    }

    public SelectUsersAndChatsView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.A = new WeakListener.OnGlobalLayoutListener((Object)this, new );
        this.B = new ;
        this.a(context, attributeSet, n);
    }

    private CharSequence a(CharSequence charSequence) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)"   ");
            spannableStringBuilder.append(charSequence);
            Drawable drawable2 = this.getResources().getDrawable(2130838179);
            drawable2.setBounds(0, 0, 0, 0);
            spannableStringBuilder.setSpan((Object)new ImageSpan(drawable2), 1, 2, 33);
            return spannableStringBuilder;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return charSequence;
        }
    }

    @AfterViews
    protected void a() {
        this.g.setHint(this.a((CharSequence)this.getResources().getString(2131296583)));
        WeakListener.a(this.g, new TextWatcher(){

            /*
             * Enabled aggressive block sorting
             */
            public void afterTextChanged(Editable object) {
                View view = SelectUsersAndChatsView.this.i;
                int n = object.length() > 0 ? 0 : 8;
                view.setVisibility(n);
                object = object.toString();
                if (!object.isEmpty()) {
                    SelectUsersAndChatsView.this.g.setCompoundDrawables(null, null, null, null);
                } else {
                    SelectUsersAndChatsView.this.g.setCompoundDrawablesWithIntrinsicBounds(2130837947, 0, 0, 0);
                }
                if (SelectUsersAndChatsView.this.c((String)object).length() < 2) {
                    SelectUsersAndChatsView.this.g();
                    SelectUsersAndChatsView.this.H = "";
                    SelectUsersAndChatsView.this.a((List<AccountIcon>)null);
                    return;
                }
                SelectUsersAndChatsView.this.a((String)object);
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        });
        this.g.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            public boolean onEditorAction(TextView object, int n, KeyEvent keyEvent) {
                if (n == 3) {
                    object = SelectUsersAndChatsView.this.g.getText().toString();
                    SelectUsersAndChatsView.this.b((String)object);
                    SelectUsersAndChatsView.this.l();
                    return true;
                }
                return false;
            }
        });
        this.g.setOnFocusChangeListener(new View.OnFocusChangeListener(){

            public void onFocusChange(View view, boolean bl) {
                if (bl) {
                    SingAnalytics.a(SelectUsersAndChatsView.this.M);
                }
            }
        });
        this.u.a(new View.OnClickListener(){

            public void onClick(View view) {
                SelectUsersAndChatsView.this.a(view);
            }
        });
        this.m.setAdapter((ListAdapter)this.u);
        this.u.a(this.m);
        this.k.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(this.getContext(), 0, false));
        this.k.setAdapter((RecyclerView.Adapter)this.t);
        this.e();
        this.o.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SelectUsersAndChatsView.this.g.getText().clear();
                SelectUsersAndChatsView.this.d();
            }
        });
        this.h.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(Context context, AttributeSet attributeSet, int n) {
        this.y = (context = context.obtainStyledAttributes(attributeSet, R.styleable.SelectUsersAndChatsView, n, 0)).getInt(0, 1) == 1 ? Mode.a : Mode.b;
        context.recycle();
        this.t = new SelectedUsersAndChatsAdapter(this.getContext(), this);
        this.u = new SelectUsersAndChatsAdapter(this.getContext(), this.t);
        this.r = new HashSet<Integer>();
        this.s = new HashSet<AccountIcon>();
        this.t.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){

            public void onItemRangeInserted(int n, int n2) {
                super.onItemRangeInserted(n, n2);
                if (!SelectUsersAndChatsView.this.x) {
                    return;
                }
                if (SelectUsersAndChatsView.this.k.getVisibility() != 0) {
                    SelectUsersAndChatsView.this.k.setVisibility(0);
                }
                SelectUsersAndChatsView.this.k.smoothScrollToPosition(n);
                if (SelectUsersAndChatsView.this.b != null) {
                    SelectUsersAndChatsView.this.b.b();
                }
                SelectUsersAndChatsView.this.c();
            }

            public void onItemRangeRemoved(int n, int n2) {
                super.onItemRangeRemoved(n, n2);
                if (!SelectUsersAndChatsView.this.x) {
                    return;
                }
                if (SelectUsersAndChatsView.this.t.a()) {
                    SelectUsersAndChatsView.this.k.setVisibility(8);
                }
                if (SelectUsersAndChatsView.this.b != null) {
                    SelectUsersAndChatsView.this.b.b();
                }
                SelectUsersAndChatsView.this.c();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void a(View object) {
        boolean bl = true;
        SelectUsersAndChatsAdapter.ViewHolder viewHolder = (SelectUsersAndChatsAdapter.ViewHolder)object.getTag();
        boolean bl2 = !viewHolder.e.isChecked();
        object = this.u.getItem(viewHolder.i);
        boolean bl3 = this.u.m();
        if (bl3) {
            bl2 = bl;
            if (this.t.c(object)) {
                this.j();
                return;
            }
        }
        if (this.y == Mode.a && object instanceof PeerChat) {
            object = ((PeerChat)object).R();
        }
        if (this.b != null && bl2) {
            if (object instanceof AccountIcon) {
                if (!this.b.a((AccountIcon)object)) return;
            }
            if (object instanceof Chat) {
                if (!this.b.a((Chat)object)) return;
            }
        }
        if (!bl3) {
            viewHolder.e.setChecked(bl2);
        } else {
            viewHolder.e.setChecked(false);
        }
        if (bl2) {
            if (object instanceof AccountIcon) {
                this.s.add((AccountIcon)object);
            } else {
                this.r.add(viewHolder.i);
            }
            this.t.a(object);
        } else {
            if (object instanceof AccountIcon) {
                this.s.remove(object);
            } else {
                this.r.remove(viewHolder.i);
            }
            this.t.b(object);
        }
        if (!bl3) return;
        object = this.g.getText().toString().isEmpty() ? null : this.g.getText().toString();
        com.smule.android.logging.Analytics.a(this.M, this.u.getCount(), viewHolder.i, (String)object, this.G, "@" + viewHolder.d.getText().toString(), null, (Integer)viewHolder.i);
        this.j();
    }

    public void a(Chat chat) {
        this.e.setVisibility(0);
        this.q.setVisibility(8);
        this.K = chat;
        this.b(chat);
        this.f();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(final String string2) {
        long l = 2000;
        this.g();
        this.J = new Runnable(){

            @Override
            public void run() {
                SelectUsersAndChatsView.this.b(string2);
            }
        };
        if (System.currentTimeMillis() - this.F >= 2000) {
            l = 700;
        }
        this.E.postDelayed(this.J, l);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(List<AccountIcon> viewGroup) {
        int n = 0;
        this.e.setVisibility(8);
        this.q.setVisibility(8);
        this.l.setVisibility(8);
        if (this.t.getItemCount() > 0) {
            this.k.setVisibility(0);
        }
        if (this.D) {
            this.p.setVisibility(0);
        }
        if (viewGroup != null) {
            this.u.a((List<AccountIcon>)viewGroup);
        } else {
            this.u.l();
        }
        viewGroup = this.n;
        if (!this.u.isEmpty()) {
            n = 8;
        }
        viewGroup.setVisibility(n);
    }

    public void a(boolean bl) {
        if (bl) {
            this.f.setBackgroundColor(this.getResources().getColor(2131689483));
            this.j.setVisibility(0);
            return;
        }
        this.f.setBackgroundColor(-1);
        this.j.setVisibility(8);
    }

    @Click
    protected void b() {
        if (this.b != null) {
            this.b.t_();
        }
    }

    protected void b(Chat chat) {
        this.u.a(this.c(chat), chat);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(final String string2) {
        if (!this.x) {
            Log.c(a, "Ignoring search request: '" + string2 + "'");
            return;
        }
        String string3 = this.c(string2);
        if (this.H != null && this.H.equals(string3)) return;
        {
            this.H = string3;
            if (string3.length() < 2) {
                this.H = "";
                this.a((List<AccountIcon>)null);
                return;
            }
        }
        Log.b(a, "Running search with term: " + string3);
        this.h();
        this.F = System.currentTimeMillis();
        this.I = com.smule.android.network.managers.SearchManager.a().a(string3, 0, 25, new SearchManager.AccountSearchResultResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(SearchManager accountSearchResponse) {
                SelectUsersAndChatsView.this.G = System.currentTimeMillis() - SelectUsersAndChatsView.this.F;
                if (!SelectUsersAndChatsView.this.x || !string2.equals(SelectUsersAndChatsView.this.g.getText().toString())) {
                    return;
                }
                if (!accountSearchResponse.a()) {
                    com.smule.android.utils.Toaster.a(SelectUsersAndChatsView.this.getContext(), 2131297523);
                    SelectUsersAndChatsView.this.b(SelectUsersAndChatsView.z);
                    return;
                }
                SelectUsersAndChatsView.this.b(accountSearchResponse.accountIcons);
            }
        });
    }

    @SupposeUiThread
    protected void b(List<AccountIcon> viewGroup) {
        int n = 8;
        if (this.x) {
            this.u.b((List<AccountIcon>)viewGroup);
            this.k.setVisibility(8);
            this.p.setVisibility(8);
            this.n.setVisibility(8);
            this.q.setVisibility(8);
            viewGroup = this.l;
            if (this.u.getCount() == 0) {
                n = 0;
            }
            viewGroup.setVisibility(n);
            this.m.setVisibility(0);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void b(boolean bl) {
        EditText editText = this.g;
        View.OnTouchListener onTouchListener = bl ? this.B : null;
        editText.setOnTouchListener(onTouchListener);
    }

    protected String c(String string2) {
        return com.smule.android.network.managers.SearchManager.a(string2.replace("#", "").replace("@", ""));
    }

    /*
     * Enabled aggressive block sorting
     */
    protected List<Chat> c(Chat chat) {
        Object object = SingApplication.k().a(Chat.Bucket.a);
        ArrayList<Chat> arrayList = new ArrayList<Chat>();
        int n = this.getResources().getInteger(2131623948);
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                Chat chat2;
                Chat chat3 = (Chat)object.next();
                if (this.y != Mode.b && (this.y != Mode.a || !(chat3 instanceof PeerChat)) || chat3.v() || !chat3.i()) continue;
                if (chat3 instanceof PeerChat) {
                    chat2 = (PeerChat)chat3;
                    if (chat == null || !chat.g().contains(chat2.R().accountId)) {
                        arrayList.add(chat3);
                    }
                } else if (chat3 instanceof GroupChat) {
                    chat2 = (GroupChat)chat3;
                    if (chat2.d() == Chat.ChatState.b && chat2.b(UserManager.a().f()) == GroupMemberStatus.c) {
                        arrayList.add(chat3);
                    } else if (chat2.d() == Chat.ChatState.a && this.L) {
                        arrayList.add(chat3);
                    }
                }
                if (arrayList.size() != n) continue;
            }
        }
        return arrayList;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected void c() {
        if (this.u.m()) {
            return;
        }
        int n = 0;
        while (n < this.m.getChildCount()) {
            Object object = this.m.getChildAt(n);
            if (object.getTag() instanceof SelectUsersAndChatsAdapter.ViewHolder) {
                object = (SelectUsersAndChatsAdapter.ViewHolder)object.getTag();
                boolean bl = this.t.c(object.a);
                if (!bl && object.e.isChecked()) {
                    this.r.remove(object.i);
                }
                object.e.setChecked(bl);
            }
            ++n;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean d() {
        if (this.g.hasFocus()) {
            this.clearFocus();
        }
        if (!this.D) return false;
        if (!this.C || this.g.getText().length() == 0) {
            this.j();
            do {
                return true;
                break;
            } while (true);
        }
        this.l();
        return true;
    }

    protected void e() {
        int n = this.getResources().getInteger(2131623966);
        this.v = new LayoutTransition();
        this.v.setDuration(2, (long)n);
        this.v.setDuration(0, (long)n);
        this.v.setStartDelay(1, 10);
        this.v.setDuration(3, 10);
        this.w = new LayoutTransition();
        this.w.setDuration(2, 0);
        this.w.setDuration(0, 0);
        this.w.setStartDelay(1, 0);
        this.w.setDuration(3, 0);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(this.v.getDuration(2));
        defaultItemAnimator.setRemoveDuration(10);
        this.k.setItemAnimator((RecyclerView.ItemAnimator)defaultItemAnimator);
        if (this.t.getItemCount() > 0) {
            this.d.setLayoutTransition(this.w);
            this.k.setVisibility(0);
            this.postDelayed(new Runnable(){

                @Override
                public void run() {
                    SelectUsersAndChatsView.this.d.setLayoutTransition(SelectUsersAndChatsView.this.v);
                }
            }, 20);
            return;
        }
        this.d.setLayoutTransition(this.v);
    }

    protected void f() {
        com.smule.android.network.managers.FollowManager.a().a(new FollowManager.FolloweesResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(FollowManager followeesResponse) {
                if (followeesResponse.a()) {
                    SelectUsersAndChatsView.this.a(followeesResponse.mFollowees);
                    return;
                }
                followeesResponse = SelectUsersAndChatsView.this.q;
                int n = SelectUsersAndChatsView.this.e.getVisibility() == 0 ? 0 : 8;
                followeesResponse.setVisibility(n);
            }
        });
    }

    protected void g() {
        if (this.E != null && this.J != null) {
            this.E.removeCallbacks(this.J);
        }
    }

    public List<AccountIcon> getSelectedAccountIcons() {
        return new ArrayList<AccountIcon>(this.s);
    }

    public List<AccountIcon> getSelectedAccounts() {
        return this.t.b();
    }

    public List<Chat> getSelectedChats() {
        return this.t.c();
    }

    public int getSelectedCount() {
        return this.t.getItemCount();
    }

    public List<Integer> getSelectedPositions() {
        return new ArrayList<Integer>(this.r);
    }

    protected void h() {
        if (this.I != null) {
            this.I.cancel(true);
        }
        this.g();
    }

    @Click
    protected void i() {
        this.g.setText((CharSequence)"");
    }

    @Click
    protected void j() {
        this.g.setText((CharSequence)"");
        this.l();
        this.p.setVisibility(8);
        this.D = false;
        this.o.setVisibility(8);
        this.h.setVisibility(0);
        this.g.setCompoundDrawablesWithIntrinsicBounds(2130837947, 0, 0, 0);
        if (this.b != null) {
            this.b.v_();
        }
    }

    @Click
    protected void k() {
        this.a(this.K);
    }

    protected void l() {
        MiscUtils.a((View)this.g, (boolean)true);
        this.C = false;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.x = true;
        LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)this.A);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.x = false;
        LayoutUtils.b((View)this, (WeakListener.OnGlobalLayoutListener)this.A);
    }

    public void setIsInShareInviteActivity(boolean bl) {
        this.L = bl;
    }

    public void setSearchClkContext(Analytics searchClkContext) {
        this.M = searchClkContext;
    }

    public void setSelectUsersAndChatsListener(SelectUsersAndChatsListener selectUsersAndChatsListener) {
        this.b = selectUsersAndChatsListener;
    }

    public void setSelectedAccountIcons(List<AccountIcon> object) {
        this.s.addAll((Collection<AccountIcon>)object);
        for (AccountIcon accountIcon : this.s) {
            this.t.a(accountIcon);
        }
        this.c();
    }

    public void setSelectedAccounts(List<AccountIcon> object) {
        object = object.iterator();
        while (object.hasNext()) {
            AccountIcon accountIcon = (AccountIcon)object.next();
            this.t.a(accountIcon);
        }
    }

    public void setSelectedPositions(List<Integer> object) {
        this.r.addAll((Collection<Integer>)object);
        object = this.r.iterator();
        while (object.hasNext()) {
            int n = (Integer)object.next();
            Object object2 = this.u.d(n);
            this.t.a(object2);
        }
        this.c();
    }

    static enum Mode {
        a,
        b;
        

        private Mode() {
        }
    }

    public static interface SelectUsersAndChatsListener {
        public boolean a(AccountIcon var1);

        public boolean a(Chat var1);

        public void b();

        public void t_();

        public void u_();

        public void v_();
    }

}

