/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.graphics.Typeface
 *  android.os.Handler
 *  android.support.annotation.NonNull
 *  android.support.annotation.UiThread
 *  android.text.Editable
 *  android.text.InputFilter
 *  android.text.InputFilter$LengthFilter
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.view.inputmethod.InputMethodManager
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.StyleReplacer
 *  com.smule.singandroid.utils.TypefaceUtils
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.InputFilter;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.SoftInputBehaviorListener;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.EditTextCursorWatcher;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CommentsListDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.UserSearchItem;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

public class CommentsListDialog
extends SmuleDialog
implements CommentItem.CommentItemListener {
    private static final String b = CommentsListDialog.class.getSimpleName();
    private int A;
    private final Handler B;
    private String C;
    private long D;
    private Runnable E;
    private ArrayList<AccountIcon> F;
    protected TextView a;
    private View c;
    private CustomToolbar d;
    private ListView e;
    private TextView f;
    private ProfileImageWithVIPBadge g;
    private EditTextCursorWatcher h;
    private ListView i;
    private PerformanceV2 j;
    private final  k;
    private  l;
    private String m;
    private List<PerformancePost> n;
    private LocalizedShortNumberFormatter o;
    private  p = .a;
    private Activity q;
    private View r;
    private View s;
    private boolean t = false;
    private boolean u = false;
    private  v = null;
    private  w;
    private WeakListener.OnGlobalLayoutListener x;
    private long y;
    private int z;

    public CommentsListDialog(BaseFragment baseFragment, PerformanceV2 performanceV2, String string2, long l) {
        this(baseFragment, performanceV2, string2, null);
        this.y = l;
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public CommentsListDialog(BaseFragment baseFragment, PerformanceV2 performanceV2, String string2,  commentsDataSource) {
        super((Context)baseFragment.getActivity(), 2131493244);
        this.w = new BaseAdapter(){
            ArrayList<AccountIcon> a;
            {
                this.a = null;
            }

            public void a(ArrayList<AccountIcon> arrayList) {
                this.a = arrayList;
            }

            public int getCount() {
                if (this.a != null) {
                    return this.a.size();
                }
                return 0;
            }

            public Object getItem(int n) {
                return null;
            }

            public long getItemId(int n) {
                return 0;
            }

            public View getView(final int n, View object, ViewGroup object2) {
                if (object == null || !(object instanceof UserSearchItem)) {
                    object = UserSearchItem.a((Context)CommentsListDialog.this.q);
                }
                object2 = (UserSearchItem)((Object)object);
                object2.a(this.a.get(n));
                object2.setOnClickListener(new View.OnClickListener(){

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void onClick(View object) {
                        UserSearchItem userSearchItem = (UserSearchItem)((Object)object);
                        Editable editable = CommentsListDialog.this.h.getEditableText();
                        Analytics searchClkContext = Analytics.j;
                        int n3 = UserSearchAdapter.this.getCount();
                        int n2 = n;
                        object = CommentsListDialog.this.p == .b ? "" : (CommentsListDialog.this.C.isEmpty() ? null : CommentsListDialog.this.C);
                        long l = CommentsListDialog.this.p == .b ? 0 : CommentsListDialog.this.D;
                        com.smule.android.logging.Analytics.a(searchClkContext, n3, n2, (String)object, l, "@" + userSearchItem.getAccountIcon().handle, null, (Integer)n);
                        if (CommentsListDialog.this.p == .b) {
                            editable.insert(CommentsListDialog.this.z + 1, (CharSequence)userSearchItem.getAccountIcon().handle);
                        } else {
                            editable.replace(CommentsListDialog.this.z + 1, CommentsListDialog.this.A + 1, (CharSequence)userSearchItem.getAccountIcon().handle);
                        }
                        CommentsListDialog.this.p = .a;
                        CommentsListDialog.this.a(false, null);
                    }
                });
                return object;
            }

        };
        this.y = -1;
        this.z = -1;
        this.A = -1;
        this.E = new Runnable(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                if (!this.a.isShowing()) {
                    Log.d(CommentsListDialog.e(), "mExecuteSearchRunnable - fragment not added; aborting");
                    return;
                } else {
                    CommentsListDialog.a(this.a, CommentsListDialog.B(this.a));
                    Log.b(CommentsListDialog.e(), "Running auto-complete search with term: " + CommentsListDialog.q(this.a));
                    if (CommentsListDialog.q(this.a).length() == 0) return;
                    {
                        com.smule.android.network.core.MagicNetwork.a(new Runnable(this, java.lang.System.currentTimeMillis()){
                            final /* synthetic */ long a;
                            final /* synthetic */  b;
                            {
                                this.b = var1_1;
                                this.a = l;
                            }

                            public void run() {
                                Log.b(CommentsListDialog.e(), "Running deep search with term: " + CommentsListDialog.q(this.b.a));
                                com.smule.android.network.managers.SearchManager$SASearchResponse sASearchResponse = SearchManager.a().a(CommentsListDialog.q(this.b.a), com.smule.android.network.managers.SearchManager$SearchResultType.b, 0, 25, com.smule.android.network.managers.SearchManager$SearchSortOption.a);
                                CommentsListDialog.c(this.b.a, java.lang.System.currentTimeMillis() - this.a);
                                HashMap hashMap = new HashMap<K, V>();
                                if (sASearchResponse != null && sASearchResponse.a()) {
                                    for (AccountIcon accountIcon : sASearchResponse.mAccts) {
                                        if (!AccountIcon.a(accountIcon)) {
                                            Log.d(CommentsListDialog.e(), "Invalid account icon parsed in doneSearching");
                                            continue;
                                        }
                                        hashMap.put(accountIcon.accountId, accountIcon);
                                    }
                                    hashMap = new ArrayList<V>(hashMap.values());
                                    Collections.sort(hashMap, new java.util.Comparator<AccountIcon>(){

                                        public int a(AccountIcon accountIcon, AccountIcon accountIcon2) {
                                            if (accountIcon.handle == null && accountIcon2.handle == null) {
                                                return 0;
                                            }
                                            if (accountIcon.handle == null || accountIcon2.handle == null) {
                                                if (accountIcon.handle == null) {
                                                    return -1;
                                                }
                                                return 1;
                                            }
                                            return accountIcon.handle.toLowerCase().compareTo(accountIcon2.handle.toLowerCase());
                                        }

                                        @Override
                                        public /* synthetic */ int compare(Object object, Object object2) {
                                            return this.a((AccountIcon)object, (AccountIcon)object2);
                                        }
                                    });
                                    this.b.a.a(false, (ArrayList<AccountIcon>)((Object)hashMap));
                                }
                            }
                        });
                        return;
                    }
                }
            }
        };
        this.F = null;
        Log.c(b, "CommentsListDialog created");
        this.B = new Handler();
        this.n = null;
        this.q = baseFragment.getActivity();
        this.setContentView(this.q.getLayoutInflater().inflate(2130903185, null, false));
        this.setCanceledOnTouchOutside(false);
        this.j = performanceV2;
        this.c = this.findViewById(2131755221);
        this.d = (CustomToolbar)this.findViewById(2131755624);
        this.e = (ListView)this.findViewById(2131755626);
        this.f = (TextView)this.findViewById(2131755625);
        this.g = (ProfileImageWithVIPBadge)this.findViewById(2131755629);
        this.h = (EditTextCursorWatcher)this.findViewById(2131755630);
        this.i = (ListView)this.findViewById(2131755627);
        this.a = (TextView)this.findViewById(2131755628);
        this.r = this.q.getLayoutInflater().inflate(2130903273, null);
        this.s = this.q.getLayoutInflater().inflate(2130903273, null);
        this.p = .a;
        this.getWindow().setSoftInputMode(19);
        this.e.setTranscriptMode(1);
        this.e.setOnTouchListener(new View.OnTouchListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public boolean onTouch(View view, android.view.MotionEvent motionEvent) {
                MiscUtils.a((View)this.a.getCurrentFocus(), (boolean)true);
                return false;
            }
        });
        this.x = new WeakListener.OnGlobalLayoutListener(this, new SoftInputBehaviorListener(this.c, new SoftInputBehaviorListener.OnSoftInputBehaveListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public void a() {
                if (CommentsListDialog.a(this.a) != -1) {
                    CommentsListDialog.b(this.a).setSelection(CommentsListDialog.a(this.a, CommentsListDialog.a(this.a)));
                    CommentsListDialog.b(this.a, -1);
                }
            }

            public void b() {
            }
        }));
        LayoutUtils.a((View)this.c, (WeakListener.OnGlobalLayoutListener)this.x);
        if (string2 != null) {
            this.h.setText((CharSequence)string2);
            this.h.requestFocus();
            this.h.setSelection(string2.length());
        }
        if ((baseFragment = (InputMethodManager)this.q.getSystemService("input_method")) != null) {
            baseFragment.showSoftInput((View)this.h, 1);
        }
        this.l = new CommentDataSourceObserver(){
            private List<PerformancePost> b;
            {
                this.b = new ArrayList<PerformancePost>();
            }

            public List<PerformancePost> a() {
                return this.b;
            }

            void a(PerformancePost performancePost) {
                this.b.remove(performancePost);
                this.notifyDataSetChanged();
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(List<PerformancePost> list) {
                int n = 0;
                Collections.reverse(list);
                if (CommentsListDialog.this.n == null || CommentsListDialog.this.n.size() == 0) {
                    CommentsListDialog.this.n = list;
                    CommentsListDialog.this.a(true, null);
                    CommentsListDialog.this.j();
                }
                int n2 = CommentsListDialog.this.e.getFirstVisiblePosition();
                View view = CommentsListDialog.this.e.getChildAt(0);
                if (view != null) {
                    n = view.getTop();
                }
                int n3 = list.size() - this.b.size();
                this.b = list;
                if (CommentsListDialog.this.k.c()) {
                    CommentsListDialog.this.a();
                }
                this.notifyDataSetChanged();
                if (CommentsListDialog.this.y == -1) {
                    if (this.b.size() != n3) {
                        CommentsListDialog.this.e.setSelectionFromTop(n2 + n3, n);
                        return;
                    }
                    CommentsListDialog.this.e.setSelection(this.b.size() - 1);
                }
            }

            public int getCount() {
                if (this.b != null) {
                    return this.b.size();
                }
                return 0;
            }

            public Object getItem(int n) {
                return null;
            }

            public long getItemId(int n) {
                return 0;
            }

            /*
             * Enabled aggressive block sorting
             */
            public View getView(int n, View object, ViewGroup object2) {
                boolean bl = true;
                if (object == null || !(object instanceof CommentItem)) {
                    object = CommentItem.a((Context)CommentsListDialog.this.q);
                }
                object2 = (CommentItem)((Object)object);
                PerformancePost performancePost = this.b.get(n);
                boolean bl2 = UserManager.a().f() == performancePost.accountIcon.accountId;
                boolean bl3 = CommentsListDialog.d((CommentsListDialog)CommentsListDialog.this).accountIcon.accountId == performancePost.accountIcon.accountId;
                if (UserManager.a().f() != CommentsListDialog.d((CommentsListDialog)CommentsListDialog.this).accountIcon.accountId) {
                    bl = false;
                }
                object2.a(null, performancePost, bl2, bl3, bl);
                object2.setListener(CommentsListDialog.this);
                if (n == 0) {
                    CommentsListDialog.this.c();
                }
                return object;
            }
        };
        this.f();
        if (commentsDataSource == null) {
            commentsDataSource = new Object(this.j.performanceKey){
                CommentDataSourceObserver a;
                PerformanceManager b;
                private final String c;
                private List<PerformancePost> d = new ArrayList<PerformancePost>();
                private int e;
                private boolean f = false;
                private boolean g = false;
                {
                    this.c = string2;
                }

                private void f() {
                    if (this.a != null) {
                        this.a.a(new ArrayList<PerformancePost>(this.d));
                    }
                }

                public int a() {
                    return this.d.size();
                }

                public void a(CommentDataSourceObserver commentDataSourceObserver) {
                    this.a = commentDataSourceObserver;
                    this.f();
                }

                public void b(CommentDataSourceObserver commentDataSourceObserver) {
                    if (this.a == commentDataSourceObserver) {
                        this.a = null;
                    }
                }

                public boolean b() {
                    return this.f;
                }

                public boolean c() {
                    return this.g;
                }

                public void d() {
                    this.e = 0;
                    this.g = false;
                    this.f = false;
                    this.b = null;
                    this.d.clear();
                }

                public void e() {
                    if (this.f) {
                        return;
                    }
                    this.f = true;
                    this.b = new PerformanceManager(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @UiThread
                        @Override
                        public void handleResponse(PerformanceManager.PerformancePostsResponse performancePostsResponse) {
                            if (this != CommentsDataSource.this.b) {
                                return;
                            }
                            CommentsDataSource.this.f = false;
                            if (!performancePostsResponse.a()) {
                                Log.c(b, "could not load comments");
                                return;
                            }
                             commentsDataSource = CommentsDataSource.this;
                            int n = performancePostsResponse.mNext == null ? CommentsDataSource.this.e + 25 : performancePostsResponse.mNext;
                            commentsDataSource.e = n;
                            if (CommentsDataSource.this.e == -1 || performancePostsResponse.mPerformancePosts.size() <= 0) {
                                CommentsDataSource.this.g = true;
                            }
                            CommentsDataSource.this.d.addAll(performancePostsResponse.mPerformancePosts);
                            CommentsDataSource.this.f();
                        }
                    };
                    com.smule.android.network.managers.PerformanceManager.a().a(this.c, (Integer)this.e, (Integer)25, this.b);
                }

                public static interface CommentDataSourceObserver {
                    public void a(@NonNull List<PerformancePost> var1);
                }

            };
        }
        this.k = commentsDataSource;
        if (this.k.a() == 0) {
            this.c();
        }
        this.d.setLeftButtonOnClickListener(new View.OnClickListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        this.d.a(null, this.j);
        this.h.setEnabled(false);
        this.l();
        new StyleReplacer(this.q.getString(2131297581), this.a, this.a.getTextSize(), 2131689749, TypefaceUtils.b((Context)this.q)).a();
    }

    static /* synthetic */ String A(CommentsListDialog commentsListDialog) {
        return commentsListDialog.i();
    }

    static /* synthetic */ String B(CommentsListDialog commentsListDialog) {
        return commentsListDialog.k();
    }

    private int a(long l) {
        for (int i = 0; i < this.l.a().size(); ++i) {
            if (this.l.a().get((int)i).time != 1000 * l) continue;
            return i;
        }
        return this.l.a().size() - 1;
    }

    static /* synthetic */ int a(CommentsListDialog commentsListDialog, long l) {
        return commentsListDialog.a(l);
    }

    static /* synthetic */ String a(CommentsListDialog commentsListDialog, String string2) {
        commentsListDialog.C = string2;
        return string2;
    }

    private void a(NetworkResponse object, PerformancePost performancePost) {
        if (object.b == 0) {
            this.n.remove(performancePost);
            this.l.a(performancePost);
            object = this.j;
            --object.totalComments;
            this.a(false, null);
            this.d();
        }
    }

    static /* synthetic */ void a(CommentsListDialog commentsListDialog, Context context, String string2) {
        commentsListDialog.a(context, string2);
    }

    static /* synthetic */ void a(CommentsListDialog commentsListDialog, NetworkResponse networkResponse, PerformancePost performancePost) {
        commentsListDialog.a(networkResponse, performancePost);
    }

    static /* synthetic */ boolean a(CommentsListDialog commentsListDialog, boolean bl) {
        commentsListDialog.t = bl;
        return bl;
    }

    static /* synthetic */ long b(CommentsListDialog commentsListDialog, long l) {
        commentsListDialog.y = l;
        return l;
    }

    private void b(PerformancePost performancePost) {
        this.m = "@" + performancePost.accountIcon.handle + " ";
        if (this.h != null) {
            this.h.setText((CharSequence)this.m);
            this.h.setSelection(this.m.length());
            this.m = null;
        }
    }

    static /* synthetic */ void b(CommentsListDialog commentsListDialog, Context context, String string2) {
        commentsListDialog.a(context, string2);
    }

    static /* synthetic */ boolean b(CommentsListDialog commentsListDialog, boolean bl) {
        commentsListDialog.u = bl;
        return bl;
    }

    static /* synthetic */ long c(CommentsListDialog commentsListDialog, long l) {
        commentsListDialog.D = l;
        return l;
    }

    static /* synthetic */ PerformanceV2 d(CommentsListDialog commentsListDialog) {
        return commentsListDialog.j;
    }

    static /* synthetic */ LocalizedShortNumberFormatter f(CommentsListDialog commentsListDialog) {
        return commentsListDialog.h();
    }

    private void f() {
        View view = new View((Context)this.q);
        this.e.addHeaderView(view);
        this.e.setAdapter((ListAdapter)this.l);
        this.e.removeHeaderView(view);
        this.g.setAccount(UserManager.a().O());
    }

    static /* synthetic */ TextView g(CommentsListDialog commentsListDialog) {
        return commentsListDialog.f;
    }

    @Deprecated
    private String g() {
        return "-";
    }

    static /* synthetic */ ListView h(CommentsListDialog commentsListDialog) {
        return commentsListDialog.i;
    }

    private LocalizedShortNumberFormatter h() {
        if (this.o == null) {
            this.o = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.o;
    }

    static /* synthetic */  i(CommentsListDialog commentsListDialog) {
        return commentsListDialog.l;
    }

    private String i() {
        return SingAnalytics.d((PerformanceV2)this.j);
    }

    static /* synthetic */ ArrayList j(CommentsListDialog commentsListDialog) {
        return commentsListDialog.F;
    }

    private void j() {
        MiscUtils.a((Activity)this.q, (boolean)false);
        this.h.setOnClickListener(new View.OnClickListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public void onClick(View view) {
                CommentsListDialog.p(this.a).setCursorVisible(true);
            }
        });
        this.h.setOnSelectionChangedListener(new EditTextCursorWatcher.OnSelectionChangedListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public void a(int n, int n2) {
                n = 1;
                CommentsListDialog.y(this.a).removeCallbacks(CommentsListDialog.x(this.a));
                 suggestionMode = this.a.a(CommentsListDialog.p(this.a).getText().toString());
                if (CommentsListDialog.c(this.a) != suggestionMode) {
                    CommentsListDialog.a(this.a, suggestionMode);
                    this.a.a(false, null);
                    n = 0;
                }
                if (CommentsListDialog.c(this.a) == .c) {
                    if (n != 0) {
                        this.a.a(false, null);
                    }
                    CommentsListDialog.y(this.a).postDelayed(CommentsListDialog.x(this.a), 500);
                }
            }
        });
        this.h.setOnEditorActionListener(new TextView.OnEditorActionListener(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public boolean onEditorAction(TextView textView, int n, android.view.KeyEvent object) {
                if (!this.a.isShowing()) {
                    return false;
                }
                if (n == 4 || n == 0 && object.getKeyCode() == 66 && object.getAction() == 0) {
                    Object var4_4 = null;
                    object = var4_4;
                    if (textView != null) {
                        object = var4_4;
                        if (textView.getText() != null) {
                            object = textView.getText().toString().trim();
                        }
                    }
                    if (object != null && android.text.TextUtils.getTrimmedLength((CharSequence)object) > 0) {
                        com.smule.android.network.core.MagicNetwork.a(new Runnable(this, (String)object){
                            final /* synthetic */ String a;
                            final /* synthetic */  b;
                            {
                                this.b = var1_1;
                                this.a = string2;
                            }

                            /*
                             * Enabled aggressive block sorting
                             */
                            public void run() {
                                Object object = com.smule.android.utils.LocationUtils.a();
                                NetworkResponse networkResponse = com.smule.android.network.managers.PerformanceManager.a().a(CommentsListDialog.d((CommentsListDialog)this.b.a).performanceKey, this.a, (float)object.getLatitude(), (float)object.getLongitude());
                                if (networkResponse.b == 0) {
                                    String string2 = CommentsListDialog.d((CommentsListDialog)this.b.a).performanceKey;
                                    String string3 = CommentsListDialog.z(this.b.a);
                                    com.smule.android.logging.Analytics$Ensemble ensemble = com.smule.singandroid.SingBundle$PerformanceType.a(CommentsListDialog.d((CommentsListDialog)this.b.a).ensembleType).a();
                                    String string4 = CommentsListDialog.A(this.b.a);
                                    object = CommentsListDialog.d((CommentsListDialog)this.b.a).video ? com.smule.android.logging.Analytics$VideoStatusType.a : com.smule.android.logging.Analytics$VideoStatusType.b;
                                    SingAnalytics.a((String)string2, (String)string3, ensemble, (Integer)null, (String)string4, object);
                                    CommentsListDialog.e(this.b.a).runOnUiThread(new Runnable(this, networkResponse){
                                        final /* synthetic */ NetworkResponse a;
                                        final /* synthetic */ com.smule.singandroid.dialogs.CommentsListDialog$8$1 b;
                                        {
                                            this.b = var1_1;
                                            this.a = networkResponse;
                                        }

                                        public void run() {
                                            if (!this.b.b.a.isShowing()) {
                                                return;
                                            }
                                            CommentsListDialog.p(this.b.b.a).setText((CharSequence)"");
                                            Object object = CommentsListDialog.d(this.b.b.a);
                                            ++object.totalComments;
                                            object = new PerformancePost();
                                            object.postKey = this.a.f().get("postKey").asText();
                                            object.accountIcon = UserManager.a().O();
                                            object.message = this.b.a;
                                            object.time = java.lang.System.currentTimeMillis();
                                            this.b.b.a.a((PerformancePost)object);
                                            CommentsListDialog.p(this.b.b.a).setCursorVisible(false);
                                        }
                                    });
                                    return;
                                }
                                if (networkResponse.e()) {
                                    ((com.smule.singandroid.BaseActivity)CommentsListDialog.e(this.b.a)).a(networkResponse.f, true, null);
                                    return;
                                }
                                this.b.a.a(networkResponse, CommentsListDialog.e(this.b.a).getString(2131296655), CommentsListDialog.e(this.b.a).getString(2131296654));
                            }
                        });
                        ((InputMethodManager)CommentsListDialog.e(this.a).getSystemService("input_method")).hideSoftInputFromWindow(CommentsListDialog.p(this.a).getApplicationWindowToken(), 2);
                        CommentsListDialog.p(this.a).setCursorVisible(false);
                        return true;
                    }
                }
                return false;
            }
        });
        this.h.setCustomSelectionActionModeCallback(new ActionMode.Callback(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public boolean onActionItemClicked(ActionMode actionMode, android.view.MenuItem menuItem) {
                return false;
            }

            public boolean onCreateActionMode(ActionMode actionMode, android.view.Menu menu2) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onPrepareActionMode(ActionMode actionMode, android.view.Menu menu2) {
                return false;
            }
        });
        this.h.setFilters(new InputFilter[]{new InputFilter.LengthFilter(140)});
        this.h.setEnabled(true);
    }

    static /* synthetic */  k(CommentsListDialog commentsListDialog) {
        return commentsListDialog.w;
    }

    private String k() {
        if (this.p == .a) {
            return "";
        }
        if (this.z < 0 || this.A < 0) {
            return "";
        }
        if (this.A < this.z) {
            return "";
        }
        if (this.z >= this.h.getText().length()) {
            return "";
        }
        if (this.A > this.h.getText().length()) {
            return "";
        }
        try {
            String string2 = SearchManager.a(this.h.getText().toString().substring(this.z + 1, this.A + 1));
            return string2;
        }
        catch (Exception exception) {
            MagicCrittercism.a(new Exception(this.h.getText().toString(), exception){
                {
                    super(string2);
                    this.initCause(throwable);
                }
            });
            return "";
        }
    }

    static /* synthetic */ View l(CommentsListDialog commentsListDialog) {
        return commentsListDialog.s;
    }

    private void l() {
        HashMap<Long, AccountIcon> hashMap = new HashMap<Long, AccountIcon>();
        hashMap.put(this.j.accountIcon.accountId, this.j.accountIcon);
        for (Track track : this.j.recentTracks) {
            hashMap.put(track.accountIcon.accountId, track.accountIcon);
        }
        this.F = new ArrayList();
        this.F.addAll(hashMap.values());
        this.i.setAdapter((ListAdapter)this.w);
    }

    static /* synthetic */ boolean m(CommentsListDialog commentsListDialog) {
        return commentsListDialog.t;
    }

    static /* synthetic */ boolean n(CommentsListDialog commentsListDialog) {
        return commentsListDialog.u;
    }

    static /* synthetic */ View o(CommentsListDialog commentsListDialog) {
        return commentsListDialog.r;
    }

    static /* synthetic */ Runnable x(CommentsListDialog commentsListDialog) {
        return commentsListDialog.E;
    }

    static /* synthetic */ Handler y(CommentsListDialog commentsListDialog) {
        return commentsListDialog.B;
    }

    static /* synthetic */ String z(CommentsListDialog commentsListDialog) {
        return commentsListDialog.g();
    }

    /*
     * Enabled aggressive block sorting
     */
     a(String string2) {
        int n;
        if (string2.length() == 0) {
            return .a;
        }
        int n2 = this.h.getSelectionStart();
        for (n = n2 - 1; n >= 0 && string2.charAt(n) != ' ' && string2.charAt(n) != '@'; --n) {
        }
        if (n < 0) {
            return .a;
        }
        if (string2.charAt(n) != '@') {
            return .a;
        }
        this.z = n;
        if (n2 >= string2.length()) {
            n = string2.length() - 1;
        } else {
            for (n = n2; n < string2.length() && string2.charAt(n) != ' ' && string2.charAt(n) != '@'; ++n) {
            }
            --n;
        }
        this.A = n;
        if (this.z == this.A) {
            return .b;
        }
        return .c;
    }

    public void a() {
        if (this.r != null) {
            this.e.removeHeaderView(this.r);
        }
        this.u = false;
    }

    protected void a(NetworkResponse networkResponse, String string2, String string3) {
        this.q.runOnUiThread(new Runnable(this, networkResponse, string2, string3){
            final /* synthetic */ NetworkResponse a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ CommentsListDialog d;
            {
                this.d = commentsListDialog;
                this.a = networkResponse;
                this.b = string2;
                this.c = string3;
            }

            public void run() {
                if (!this.d.isShowing()) {
                    return;
                }
                if (this.a.b == 0) {
                    CommentsListDialog.a(this.d, (Context)CommentsListDialog.e(this.d), this.b);
                    return;
                }
                CommentsListDialog.b(this.d, (Context)CommentsListDialog.e(this.d), this.c);
            }
        });
    }

    protected void a(PerformancePost performancePost) {
        this.q.runOnUiThread(new Runnable(this, performancePost){
            final /* synthetic */ PerformancePost a;
            final /* synthetic */ CommentsListDialog b;
            {
                this.b = commentsListDialog;
                this.a = performancePost;
            }

            public void run() {
                CommentsListDialog.u(this.b).add(this.a);
                this.b.a(true, null);
                this.b.d();
            }
        });
    }

    public void a( commentsListDialogListener) {
        this.v = commentsListDialogListener;
    }

    @Override
    public void a(CommentItem object, PerformancePost performancePost) {
        object = new TextAlertDialog((Context)this.q, this.q.getString(2131296652), this.q.getString(2131296651));
        object.a(this.q.getString(2131296649), this.q.getString(2131296672));
        object.a(new CustomAlertDialog.CustomAlertDialogListener(this, performancePost){
            final /* synthetic */ PerformancePost a;
            final /* synthetic */ CommentsListDialog b;
            {
                this.b = commentsListDialog;
                this.a = performancePost;
            }

            public void a(CustomAlertDialog customAlertDialog) {
                com.smule.android.network.core.MagicNetwork.a(new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        NetworkResponse networkResponse = com.smule.android.network.managers.PerformanceManager.a().b(CommentsListDialog.d((CommentsListDialog)this.a.b).performanceKey, this.a.a.postKey);
                        this.a.b.a(networkResponse, CommentsListDialog.e(this.a.b).getString(2131296653), CommentsListDialog.e(this.a.b).getString(2131296650));
                    }
                });
            }

            public void b(CustomAlertDialog customAlertDialog) {
            }
        });
        object.show();
    }

    protected void a(boolean bl, ArrayList<AccountIcon> arrayList) {
        this.B.post(new Runnable(this, bl, arrayList){
            final /* synthetic */ boolean a;
            final /* synthetic */ ArrayList b;
            final /* synthetic */ CommentsListDialog c;
            {
                this.c = commentsListDialog;
                this.a = bl;
                this.b = arrayList;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void run() {
                if (CommentsListDialog.c(this.c) == .a) {
                    if (CommentsListDialog.d((CommentsListDialog)this.c).totalComments > 0) {
                        int n = CommentsListDialog.d((CommentsListDialog)this.c).totalComments;
                        String string2 = CommentsListDialog.e(this.c).getResources().getQuantityString(2131361799, n, new Object[]{CommentsListDialog.f(this.c).a(n, CommentsListDialog.e(this.c).getResources().getInteger(2131623962))});
                        CommentsListDialog.g(this.c).setText((CharSequence)string2);
                    } else {
                        CommentsListDialog.g(this.c).setText(2131297019);
                    }
                    CommentsListDialog.b(this.c).setVisibility(0);
                    CommentsListDialog.g(this.c).setVisibility(0);
                    CommentsListDialog.h(this.c).setVisibility(8);
                    this.c.a.setVisibility(8);
                    CommentsListDialog.i(this.c).notifyDataSetChanged();
                    if (this.a) {
                        CommentsListDialog.b(this.c).setSelection(CommentsListDialog.i(this.c).getCount() - 1);
                    }
                    return;
                }
                CommentsListDialog.b(this.c).setVisibility(8);
                CommentsListDialog.g(this.c).setVisibility(8);
                ArrayList arrayList = this.b;
                if (CommentsListDialog.c(this.c) == .b) {
                    CommentsListDialog.k(this.c).a(CommentsListDialog.j(this.c));
                    arrayList = CommentsListDialog.j(this.c);
                } else {
                    CommentsListDialog.k(this.c).a(arrayList);
                }
                if (arrayList == null) {
                    CommentsListDialog.h(this.c).setVisibility(0);
                    this.c.a.setVisibility(8);
                    if (CommentsListDialog.l(this.c) != null && !CommentsListDialog.m(this.c)) {
                        CommentsListDialog.h(this.c).setAdapter(null);
                        CommentsListDialog.h(this.c).addHeaderView(CommentsListDialog.l(this.c));
                        CommentsListDialog.h(this.c).setAdapter((ListAdapter)CommentsListDialog.k(this.c));
                        CommentsListDialog.a(this.c, true);
                    }
                } else {
                    if (arrayList.size() > 0) {
                        CommentsListDialog.h(this.c).setVisibility(0);
                        this.c.a.setVisibility(8);
                    } else {
                        CommentsListDialog.h(this.c).setVisibility(8);
                        this.c.a.setVisibility(0);
                    }
                    if (CommentsListDialog.l(this.c) != null && CommentsListDialog.m(this.c)) {
                        CommentsListDialog.h(this.c).removeHeaderView(CommentsListDialog.l(this.c));
                        CommentsListDialog.a(this.c, false);
                    }
                }
                CommentsListDialog.k(this.c).notifyDataSetChanged();
            }
        });
    }

    public void b() {
        this.q.runOnUiThread(new Runnable(this){
            final /* synthetic */ CommentsListDialog a;
            {
                this.a = commentsListDialog;
            }

            public void run() {
                if (!CommentsListDialog.n(this.a) && CommentsListDialog.o(this.a) != null) {
                    CommentsListDialog.b(this.a).addHeaderView(CommentsListDialog.o(this.a));
                    CommentsListDialog.b(this.a, true);
                }
            }
        });
    }

    @Override
    public void b(CommentItem object, PerformancePost performancePost) {
        object = new TextAlertDialog((Context)this.q, this.q.getString(2131296647), this.q.getString(2131296646));
        object.a(this.q.getString(2131296677), this.q.getString(2131296672));
        object.a(new CustomAlertDialog.CustomAlertDialogListener(this, performancePost){
            final /* synthetic */ PerformancePost a;
            final /* synthetic */ CommentsListDialog b;
            {
                this.b = commentsListDialog;
                this.a = performancePost;
            }

            public void a(CustomAlertDialog customAlertDialog) {
                com.smule.android.network.core.MagicNetwork.a(new Runnable(this){
                    final /* synthetic */  a;
                    {
                        this.a = var1_1;
                    }

                    public void run() {
                        NetworkResponse networkResponse = com.smule.android.network.managers.PerformanceManager.a().a(CommentsListDialog.d((CommentsListDialog)this.a.b).performanceKey, this.a.a.postKey);
                        this.a.b.a(networkResponse, CommentsListDialog.e(this.a.b).getString(2131296648), CommentsListDialog.e(this.a.b).getString(2131296645));
                        CommentsListDialog.e(this.a.b).runOnUiThread(new Runnable(this, networkResponse){
                            final /* synthetic */ NetworkResponse a;
                            final /* synthetic */ com.smule.singandroid.dialogs.CommentsListDialog$12$1 b;
                            {
                                this.b = var1_1;
                                this.a = networkResponse;
                            }

                            public void run() {
                                CommentsListDialog.a(this.b.a.b, this.a, this.b.a.a);
                            }
                        });
                    }
                });
            }

            public void b(CustomAlertDialog customAlertDialog) {
            }
        });
        object.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c() {
        if (this.k.b() || this.k.c()) {
            return;
        }
        this.b();
        this.k.e();
    }

    @Override
    public void c(CommentItem commentItem, PerformancePost performancePost) {
        this.b(performancePost);
    }

    protected void d() {
        this.k.d();
        this.k.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void d(CommentItem commentItem, PerformancePost performancePost) {
        if (performancePost == null || performancePost.accountIcon == null || this.v == null) {
            return;
        }
        this.v.a(commentItem, performancePost);
    }

    protected void onStart() {
        super.onStart();
        this.k.a(this.l);
        SingAnalytics.e((String)this.g(), (String)this.i());
    }

    protected void onStop() {
        this.k.b(this.l);
    }

}

