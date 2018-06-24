package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.location.Location;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Analytics.VideoStatusType;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancePostsResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancePostsResponse;
import com.smule.android.network.managers.SearchManager;
import com.smule.android.network.managers.SearchManager.SASearchResponse;
import com.smule.android.network.managers.SearchManager.SearchResultType;
import com.smule.android.network.managers.SearchManager.SearchSortOption;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountIcon$AccountIconComparatorByHandle;
import com.smule.android.network.models.PerformancePost;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.LocationUtils;
import com.smule.android.utils.SoftInputBehaviorListener;
import com.smule.android.utils.SoftInputBehaviorListener.OnSoftInputBehaveListener;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.EditTextCursorWatcher;
import com.smule.singandroid.customviews.EditTextCursorWatcher.OnSelectionChangedListener;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.list_items.CommentItem;
import com.smule.singandroid.list_items.CommentItem.CommentItemListener;
import com.smule.singandroid.list_items.UserSearchItem;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CommentsListDialog extends SmuleDialog implements CommentItemListener {
    private static final String f22206b = CommentsListDialog.class.getSimpleName();
    private Handler f22207A;
    private String f22208B;
    private long f22209C;
    private Runnable f22210D;
    private ArrayList<AccountIcon> f22211E;
    protected TextView f22212a;
    private View f22213c;
    private CustomToolbar f22214d;
    private ListView f22215e;
    private TextView f22216f;
    private ProfileImageWithVIPBadge f22217g;
    private EditTextCursorWatcher f22218h;
    private ListView f22219i;
    private PerformanceV2 f22220j;
    private CommentsAdapter f22221k;
    private String f22222l;
    private ArrayList<PerformancePost> f22223m;
    private LocalizedShortNumberFormatter f22224n;
    private SuggestionMode f22225o;
    private Activity f22226p;
    private View f22227q;
    private View f22228r;
    private boolean f22229s;
    private boolean f22230t;
    private CommentsListDialogListener f22231u;
    private UserSearchAdapter f22232v;
    private OnGlobalLayoutListener f22233w;
    private long f22234x;
    private int f22235y;
    private int f22236z;

    class C44691 implements OnTouchListener {
        final /* synthetic */ CommentsListDialog f22176a;

        C44691(CommentsListDialog commentsListDialog) {
            this.f22176a = commentsListDialog;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            MiscUtils.m25894a(this.f22176a.getCurrentFocus(), true);
            return false;
        }
    }

    class C44702 implements OnSoftInputBehaveListener {
        final /* synthetic */ CommentsListDialog f22177a;

        C44702(CommentsListDialog commentsListDialog) {
            this.f22177a = commentsListDialog;
        }

        public void mo6810a() {
            if (this.f22177a.f22234x != -1) {
                this.f22177a.f22215e.setSelection(this.f22177a.m23608a(this.f22177a.f22234x));
                this.f22177a.f22234x = -1;
            }
        }

        public void mo6811b() {
        }
    }

    class C44713 implements OnClickListener {
        final /* synthetic */ CommentsListDialog f22178a;

        C44713(CommentsListDialog commentsListDialog) {
            this.f22178a = commentsListDialog;
        }

        public void onClick(View view) {
            this.f22178a.dismiss();
        }
    }

    class C44724 implements Runnable {
        final /* synthetic */ CommentsListDialog f22179a;

        C44724(CommentsListDialog commentsListDialog) {
            this.f22179a = commentsListDialog;
        }

        public void run() {
            this.f22179a.m23662b();
        }
    }

    class C44746 implements Runnable {
        final /* synthetic */ CommentsListDialog f22183a;

        C44746(CommentsListDialog commentsListDialog) {
            this.f22183a = commentsListDialog;
        }

        public void run() {
            if (!this.f22183a.f22230t && this.f22183a.f22227q != null) {
                this.f22183a.f22215e.addHeaderView(this.f22183a.f22227q);
                this.f22183a.f22230t = true;
            }
        }
    }

    class C44757 implements OnClickListener {
        final /* synthetic */ CommentsListDialog f22184a;

        C44757(CommentsListDialog commentsListDialog) {
            this.f22184a = commentsListDialog;
        }

        public void onClick(View view) {
            this.f22184a.f22218h.setCursorVisible(true);
        }
    }

    class C44768 implements OnSelectionChangedListener {
        final /* synthetic */ CommentsListDialog f22185a;

        C44768(CommentsListDialog commentsListDialog) {
            this.f22185a = commentsListDialog;
        }

        public void mo6812a(int i, int i2) {
            Object obj = 1;
            this.f22185a.f22207A.removeCallbacks(this.f22185a.f22210D);
            SuggestionMode a = this.f22185a.m23654a(this.f22185a.f22218h.getText().toString());
            if (this.f22185a.f22225o != a) {
                this.f22185a.f22225o = a;
                obj = null;
                this.f22185a.m23662b();
            }
            if (this.f22185a.f22225o == SuggestionMode.DeepSearch) {
                if (obj != null) {
                    this.f22185a.m23662b();
                }
                this.f22185a.f22207A.postDelayed(this.f22185a.f22210D, 500);
            }
        }
    }

    class C44799 implements OnEditorActionListener {
        final /* synthetic */ CommentsListDialog f22190a;

        C44799(CommentsListDialog commentsListDialog) {
            this.f22190a = commentsListDialog;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (!this.f22190a.isShowing()) {
                return false;
            }
            if (i == 4 || (i == 0 && keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0)) {
                Object obj = null;
                if (!(textView == null || textView.getText() == null)) {
                    obj = textView.getText().toString().trim();
                }
                if (obj != null && TextUtils.getTrimmedLength(obj) > 0) {
                    MagicNetwork.a(new Runnable(this) {
                        final /* synthetic */ C44799 f22189b;

                        public void run() {
                            Location a = LocationUtils.m19000a();
                            final NetworkResponse a2 = PerformanceManager.a().a(this.f22189b.f22190a.f22220j.performanceKey, obj, (float) a.getLatitude(), (float) a.getLongitude());
                            if (a2.b == 0) {
                                Analytics.m17869a(this.f22189b.f22190a.f22220j.performanceKey, this.f22189b.f22190a.m23627f(), PerformanceType.m21630a(this.f22189b.f22190a.f22220j.ensembleType).m21631a(), null, this.f22189b.f22190a.m23633i(), this.f22189b.f22190a.f22220j.video ? VideoStatusType.f16320a : VideoStatusType.NOT_VIDEO);
                                this.f22189b.f22190a.f22226p.runOnUiThread(new Runnable(this) {
                                    final /* synthetic */ C44781 f22187b;

                                    public void run() {
                                        if (this.f22187b.f22189b.f22190a.isShowing()) {
                                            this.f22187b.f22189b.f22190a.f22218h.setText("");
                                            PerformanceV2 j = this.f22187b.f22189b.f22190a.f22220j;
                                            j.totalComments++;
                                            PerformancePost performancePost = new PerformancePost();
                                            performancePost.postKey = a2.f().get("postKey").asText();
                                            performancePost.accountIcon = UserManager.a().O();
                                            performancePost.message = obj;
                                            performancePost.time = System.currentTimeMillis();
                                            this.f22187b.f22189b.f22190a.m23657a(performancePost);
                                            this.f22187b.f22189b.f22190a.f22218h.setCursorVisible(false);
                                        }
                                    }
                                });
                            } else if (a2.e()) {
                                ((BaseActivity) this.f22189b.f22190a.f22226p).a(a2.f, true, null);
                            } else {
                                this.f22189b.f22190a.m23656a(a2, this.f22189b.f22190a.f22226p.getString(C1947R.string.comment_post_success), this.f22189b.f22190a.f22226p.getString(C1947R.string.comment_post_failed));
                            }
                        }
                    });
                    ((InputMethodManager) this.f22190a.f22226p.getSystemService("input_method")).hideSoftInputFromWindow(this.f22190a.f22218h.getApplicationWindowToken(), 2);
                    this.f22190a.f22218h.setCursorVisible(false);
                    return true;
                }
            }
            return false;
        }
    }

    private class CommentsAdapter extends BaseAdapter {
        final /* synthetic */ CommentsListDialog f22192a;
        private List<PerformancePost> f22193b = new ArrayList();
        private int f22194c;
        private String f22195d;
        private boolean f22196e = false;
        private boolean f22197f = false;

        class C44801 implements PerformanceManager$PerformancePostsResponseCallback {
            final /* synthetic */ CommentsAdapter f22191a;

            C44801(CommentsAdapter commentsAdapter) {
                this.f22191a = commentsAdapter;
            }

            public void handleResponse(PerformancePostsResponse performancePostsResponse) {
                int i = 0;
                this.f22191a.f22196e = false;
                if (performancePostsResponse.a() && this.f22191a.f22192a.isShowing()) {
                    int b;
                    Collections.reverse(performancePostsResponse.mPerformancePosts);
                    if (this.f22191a.f22192a.f22223m == null || this.f22191a.f22192a.f22223m.size() == 0) {
                        this.f22191a.f22192a.f22223m = performancePostsResponse.mPerformancePosts;
                        this.f22191a.f22192a.mo6374a();
                        this.f22191a.f22192a.m23635j();
                    }
                    int firstVisiblePosition = this.f22191a.f22192a.f22215e.getFirstVisiblePosition();
                    View childAt = this.f22191a.f22192a.f22215e.getChildAt(0);
                    if (childAt != null) {
                        i = childAt.getTop();
                    }
                    int size = performancePostsResponse.mPerformancePosts.size();
                    performancePostsResponse.mPerformancePosts.addAll(this.f22191a.f22193b);
                    this.f22191a.f22193b = performancePostsResponse.mPerformancePosts;
                    CommentsAdapter commentsAdapter = this.f22191a;
                    if (performancePostsResponse.mNext == null) {
                        b = this.f22191a.f22194c + 25;
                    } else {
                        b = performancePostsResponse.mNext.intValue();
                    }
                    commentsAdapter.f22194c = b;
                    if (this.f22191a.f22194c == -1 || size <= 0) {
                        this.f22191a.f22197f = true;
                        this.f22191a.f22192a.m23664c();
                    }
                    this.f22191a.notifyDataSetChanged();
                    if (this.f22191a.f22192a.f22234x != -1) {
                        return;
                    }
                    if (this.f22191a.f22193b.size() == size) {
                        this.f22191a.f22192a.f22215e.setSelection(this.f22191a.f22193b.size() - 1);
                    } else {
                        this.f22191a.f22192a.f22215e.setSelectionFromTop(firstVisiblePosition + size, i);
                    }
                }
            }
        }

        CommentsAdapter(CommentsListDialog commentsListDialog, Activity activity, String str) {
            this.f22192a = commentsListDialog;
            this.f22195d = str;
            m23600c();
        }

        public List<PerformancePost> m23597a() {
            return this.f22193b;
        }

        public int getCount() {
            if (this.f22193b != null) {
                return this.f22193b.size();
            }
            return 0;
        }

        void m23599b() {
            this.f22194c = 0;
            this.f22197f = false;
            this.f22193b.clear();
            notifyDataSetChanged();
            m23600c();
        }

        void m23598a(PerformancePost performancePost) {
            this.f22193b.remove(performancePost);
            notifyDataSetChanged();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View a;
            boolean z;
            boolean z2 = true;
            if (view == null || !(view instanceof CommentItem)) {
                a = CommentItem.m24244a(this.f22192a.f22226p);
            } else {
                a = view;
            }
            CommentItem commentItem = (CommentItem) a;
            PerformancePost performancePost = (PerformancePost) this.f22193b.get(i);
            boolean z3 = UserManager.a().f() == performancePost.accountIcon.accountId;
            if (this.f22192a.f22220j.accountIcon.accountId == performancePost.accountIcon.accountId) {
                z = true;
            } else {
                z = false;
            }
            if (UserManager.a().f() != this.f22192a.f22220j.accountIcon.accountId) {
                z2 = false;
            }
            commentItem.mo6868a(null, performancePost, z3, z, z2);
            commentItem.setListener(this.f22192a);
            if (i == 0 && !this.f22197f) {
                m23600c();
            }
            return a;
        }

        protected void m23600c() {
            if (!this.f22196e) {
                this.f22196e = true;
                if (!this.f22197f) {
                    this.f22192a.m23666d();
                }
                PerformanceManager.a().a(this.f22195d, Integer.valueOf(this.f22194c), Integer.valueOf(25), new C44801(this));
            }
        }
    }

    public interface CommentsListDialogListener {
        void mo6828a(CommentItem commentItem, PerformancePost performancePost);
    }

    private static final class DroidSing9192 extends Exception {
        private DroidSing9192(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }

    protected enum SuggestionMode {
        NoSuggestion,
        CreatorJoiners,
        DeepSearch
    }

    private class UserSearchAdapter extends BaseAdapter {
        ArrayList<AccountIcon> f22204a;
        final /* synthetic */ CommentsListDialog f22205b;

        private UserSearchAdapter(CommentsListDialog commentsListDialog) {
            this.f22205b = commentsListDialog;
            this.f22204a = null;
        }

        public void m23602a(ArrayList<AccountIcon> arrayList) {
            this.f22204a = arrayList;
        }

        public int getCount() {
            if (this.f22204a != null) {
                return this.f22204a.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            View a;
            if (view == null || !(view instanceof UserSearchItem)) {
                a = UserSearchItem.m24520a(this.f22205b.f22226p);
            } else {
                a = view;
            }
            UserSearchItem userSearchItem = (UserSearchItem) a;
            userSearchItem.m24521a((AccountIcon) this.f22204a.get(i));
            userSearchItem.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ UserSearchAdapter f22203b;

                public void onClick(View view) {
                    UserSearchItem userSearchItem = (UserSearchItem) view;
                    Editable editableText = this.f22203b.f22205b.f22218h.getEditableText();
                    SearchClkContext searchClkContext = SearchClkContext.COMMENT;
                    int count = this.f22203b.getCount();
                    int i = i;
                    String d = this.f22203b.f22205b.f22225o == SuggestionMode.CreatorJoiners ? "" : this.f22203b.f22205b.f22208B.isEmpty() ? null : this.f22203b.f22205b.f22208B;
                    Analytics.m17849a(searchClkContext, count, i, d, this.f22203b.f22205b.f22225o == SuggestionMode.CreatorJoiners ? 0 : this.f22203b.f22205b.f22209C, "@" + userSearchItem.getAccountIcon().handle, null, Integer.valueOf(i));
                    if (this.f22203b.f22205b.f22225o == SuggestionMode.CreatorJoiners) {
                        editableText.insert(this.f22203b.f22205b.f22235y + 1, userSearchItem.getAccountIcon().handle);
                    } else {
                        editableText.replace(this.f22203b.f22205b.f22235y + 1, this.f22203b.f22205b.f22236z + 1, userSearchItem.getAccountIcon().handle);
                    }
                    this.f22203b.f22205b.f22225o = SuggestionMode.NoSuggestion;
                    this.f22203b.f22205b.m23662b();
                }
            });
            return a;
        }
    }

    private String m23627f() {
        if (this.f22220j != null) {
            return this.f22220j.songUid;
        }
        return "-";
    }

    private LocalizedShortNumberFormatter m23629g() {
        if (this.f22224n == null) {
            this.f22224n = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f22224n;
    }

    public CommentsListDialog(BaseFragment baseFragment, PerformanceV2 performanceV2, String str, long j) {
        this(baseFragment, performanceV2, str);
        this.f22234x = j;
    }

    public CommentsListDialog(BaseFragment baseFragment, PerformanceV2 performanceV2, String str) {
        super(baseFragment.getActivity(), C1947R.style.MagicModal);
        this.f22225o = SuggestionMode.NoSuggestion;
        this.f22229s = false;
        this.f22230t = false;
        this.f22231u = null;
        this.f22232v = new UserSearchAdapter();
        this.f22234x = -1;
        this.f22235y = -1;
        this.f22236z = -1;
        this.f22207A = new Handler();
        this.f22210D = new Runnable(this) {
            final /* synthetic */ CommentsListDialog f22161a;

            {
                this.f22161a = r1;
            }

            public void run() {
                if (this.f22161a.isShowing()) {
                    this.f22161a.f22208B = this.f22161a.m23637k();
                    Log.b(CommentsListDialog.f22206b, "Running auto-complete search with term: " + this.f22161a.f22208B);
                    if (this.f22161a.f22208B.length() != 0) {
                        final long currentTimeMillis = System.currentTimeMillis();
                        MagicNetwork.a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass11 f22160b;

                            public void run() {
                                Log.b(CommentsListDialog.f22206b, "Running deep search with term: " + this.f22160b.f22161a.f22208B);
                                SASearchResponse a = SearchManager.m18331a().m18334a(this.f22160b.f22161a.f22208B, SearchResultType.ACCOUNT, 0, 25, SearchSortOption.POPULAR);
                                this.f22160b.f22161a.f22209C = System.currentTimeMillis() - currentTimeMillis;
                                HashMap hashMap = new HashMap();
                                if (a != null && a.a()) {
                                    Iterator it = a.mAccts.iterator();
                                    while (it.hasNext()) {
                                        AccountIcon accountIcon = (AccountIcon) it.next();
                                        if (AccountIcon.a(accountIcon)) {
                                            hashMap.put(Long.valueOf(accountIcon.accountId), accountIcon);
                                        } else {
                                            Log.d(CommentsListDialog.f22206b, "Invalid account icon parsed in doneSearching");
                                        }
                                    }
                                    ArrayList arrayList = new ArrayList(hashMap.values());
                                    Collections.sort(arrayList, new AccountIcon$AccountIconComparatorByHandle());
                                    this.f22160b.f22161a.m23661a(false, arrayList);
                                }
                            }
                        });
                        return;
                    }
                    return;
                }
                Log.d(CommentsListDialog.f22206b, "mExecuteSearchRunnable - fragment not added; aborting");
            }
        };
        this.f22211E = null;
        Log.c(f22206b, "CommentsListDialog created");
        this.f22223m = null;
        this.f22226p = baseFragment.getActivity();
        setContentView(this.f22226p.getLayoutInflater().inflate(C1947R.layout.comments_list_dialog, null, false));
        setCanceledOnTouchOutside(false);
        this.f22220j = performanceV2;
        this.f22213c = findViewById(C1947R.id.root);
        this.f22214d = (CustomToolbar) findViewById(C1947R.id.comments_top_toolbar);
        this.f22215e = (ListView) findViewById(C1947R.id.comments_list_view);
        this.f22216f = (TextView) findViewById(C1947R.id.comments_view_comments_count_text_view);
        this.f22217g = (ProfileImageWithVIPBadge) findViewById(C1947R.id.comment_view_user_pic_view);
        this.f22218h = (EditTextCursorWatcher) findViewById(C1947R.id.comments_view_comment_edit_text_view);
        this.f22219i = (ListView) findViewById(C1947R.id.suggestion_list_view);
        this.f22212a = (TextView) findViewById(C1947R.id.suggestion_empty_text_view);
        this.f22227q = this.f22226p.getLayoutInflater().inflate(C1947R.layout.list_loading_view, null);
        this.f22228r = this.f22226p.getLayoutInflater().inflate(C1947R.layout.list_loading_view, null);
        this.f22225o = SuggestionMode.NoSuggestion;
        getWindow().setSoftInputMode(16);
        this.f22215e.setTranscriptMode(1);
        this.f22215e.setOnTouchListener(new C44691(this));
        this.f22233w = new OnGlobalLayoutListener(this, new SoftInputBehaviorListener(this.f22213c, new C44702(this)));
        LayoutUtils.m25854a(this.f22213c, this.f22233w);
        if (str != null) {
            this.f22218h.setText(str);
            this.f22218h.requestFocus();
            this.f22218h.setSelection(str.length());
        }
        InputMethodManager inputMethodManager = (InputMethodManager) this.f22226p.getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(this.f22218h, 1);
        }
        this.f22221k = new CommentsAdapter(this, this.f22226p, this.f22220j.performanceKey);
        m23666d();
        m23631h();
        this.f22214d.setLeftButtonOnClickListener(new C44713(this));
        this.f22214d.mo6782a(null, this.f22220j);
        this.f22218h.setEnabled(false);
        m23639l();
        new StyleReplacer(this.f22226p.getString(C1947R.string.username_suggestion_empty), this.f22212a, this.f22212a.getTextSize(), C1947R.color.gray_80, TypefaceUtils.m26192b(this.f22226p)).m26174a();
    }

    protected void onStart() {
        super.onStart();
        SingAnalytics.m26145d(m23627f(), m23633i());
    }

    public void m23658a(CommentsListDialogListener commentsListDialogListener) {
        this.f22231u = commentsListDialogListener;
    }

    private void m23631h() {
        View view = new View(this.f22226p);
        this.f22215e.addHeaderView(view);
        this.f22215e.setAdapter(this.f22221k);
        this.f22215e.removeHeaderView(view);
        this.f22217g.setAccount(UserManager.a().O());
    }

    protected void mo6374a() {
        this.f22207A.post(new C44724(this));
    }

    protected void m23662b() {
        m23660a(false);
    }

    protected void m23660a(boolean z) {
        m23661a(z, null);
    }

    protected void m23661a(final boolean z, final ArrayList<AccountIcon> arrayList) {
        this.f22226p.runOnUiThread(new Runnable(this) {
            final /* synthetic */ CommentsListDialog f22182c;

            public void run() {
                if (!this.f22182c.isShowing()) {
                    return;
                }
                if (this.f22182c.f22225o == SuggestionMode.NoSuggestion) {
                    if (this.f22182c.f22220j.totalComments > 0) {
                        int i = this.f22182c.f22220j.totalComments;
                        this.f22182c.f22216f.setText(this.f22182c.f22226p.getResources().getQuantityString(C1947R.plurals.comments_count, i, new Object[]{this.f22182c.m23629g().m18999a((long) i, (long) this.f22182c.f22226p.getResources().getInteger(C1947R.integer.long_form_threshold))}));
                    } else {
                        this.f22182c.f22216f.setText(C1947R.string.now_playing_be_the_first_to_comment);
                    }
                    this.f22182c.f22215e.setVisibility(0);
                    this.f22182c.f22216f.setVisibility(0);
                    this.f22182c.f22219i.setVisibility(8);
                    this.f22182c.f22212a.setVisibility(8);
                    this.f22182c.f22221k.notifyDataSetChanged();
                    if (z) {
                        this.f22182c.f22215e.setSelection(this.f22182c.f22221k.getCount() - 1);
                        return;
                    }
                    return;
                }
                this.f22182c.f22215e.setVisibility(8);
                this.f22182c.f22216f.setVisibility(8);
                ArrayList arrayList = arrayList;
                if (this.f22182c.f22225o == SuggestionMode.CreatorJoiners) {
                    this.f22182c.f22232v.m23602a(this.f22182c.f22211E);
                    arrayList = this.f22182c.f22211E;
                } else {
                    this.f22182c.f22232v.m23602a(arrayList);
                }
                if (arrayList == null) {
                    this.f22182c.f22219i.setVisibility(0);
                    this.f22182c.f22212a.setVisibility(8);
                    if (!(this.f22182c.f22228r == null || this.f22182c.f22229s)) {
                        this.f22182c.f22219i.setAdapter(null);
                        this.f22182c.f22219i.addHeaderView(this.f22182c.f22228r);
                        this.f22182c.f22219i.setAdapter(this.f22182c.f22232v);
                        this.f22182c.f22229s = true;
                    }
                } else {
                    if (arrayList.size() > 0) {
                        this.f22182c.f22219i.setVisibility(0);
                        this.f22182c.f22212a.setVisibility(8);
                    } else {
                        this.f22182c.f22219i.setVisibility(8);
                        this.f22182c.f22212a.setVisibility(0);
                    }
                    if (this.f22182c.f22228r != null && this.f22182c.f22229s) {
                        this.f22182c.f22219i.removeHeaderView(this.f22182c.f22228r);
                        this.f22182c.f22229s = false;
                    }
                }
                this.f22182c.f22232v.notifyDataSetChanged();
            }
        });
    }

    public void m23664c() {
        if (this.f22227q != null) {
            this.f22215e.removeHeaderView(this.f22227q);
        }
        this.f22230t = false;
    }

    public void m23666d() {
        this.f22226p.runOnUiThread(new C44746(this));
    }

    private String m23633i() {
        return SingAnalytics.m26140d(this.f22220j);
    }

    private void m23635j() {
        this.f22218h.setOnClickListener(new C44757(this));
        this.f22218h.setOnSelectionChangedListener(new C44768(this));
        this.f22218h.setOnEditorActionListener(new C44799(this));
        this.f22218h.setCustomSelectionActionModeCallback(new Callback(this) {
            final /* synthetic */ CommentsListDialog f22158a;

            {
                this.f22158a = r1;
            }

            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public void onDestroyActionMode(ActionMode actionMode) {
            }

            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }
        });
        this.f22218h.setFilters(new InputFilter[]{new LengthFilter(140)});
        this.f22218h.setEnabled(true);
    }

    SuggestionMode m23654a(String str) {
        if (str.length() == 0) {
            return SuggestionMode.NoSuggestion;
        }
        int selectionStart = this.f22218h.getSelectionStart();
        int i = selectionStart - 1;
        while (i >= 0 && str.charAt(i) != ' ' && str.charAt(i) != '@') {
            i--;
        }
        if (i < 0) {
            return SuggestionMode.NoSuggestion;
        }
        if (str.charAt(i) != '@') {
            return SuggestionMode.NoSuggestion;
        }
        this.f22235y = i;
        if (selectionStart >= str.length()) {
            selectionStart = str.length() - 1;
        } else {
            while (selectionStart < str.length() && str.charAt(selectionStart) != ' ' && str.charAt(selectionStart) != '@') {
                selectionStart++;
            }
            selectionStart--;
        }
        this.f22236z = selectionStart;
        if (this.f22235y == this.f22236z) {
            return SuggestionMode.CreatorJoiners;
        }
        return SuggestionMode.DeepSearch;
    }

    public void mo6814a(CommentItem commentItem, final PerformancePost performancePost) {
        TextAlertDialog textAlertDialog = new TextAlertDialog(this.f22226p, this.f22226p.getString(C1947R.string.comment_flag_prompt), this.f22226p.getString(C1947R.string.comment_flag_msg));
        textAlertDialog.m19806a(this.f22226p.getString(C1947R.string.comment_flag), this.f22226p.getString(C1947R.string.core_cancel));
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ CommentsListDialog f22164b;

            class C44661 implements Runnable {
                final /* synthetic */ AnonymousClass12 f22162a;

                C44661(AnonymousClass12 anonymousClass12) {
                    this.f22162a = anonymousClass12;
                }

                public void run() {
                    this.f22162a.f22164b.m23656a(PerformanceManager.a().b(this.f22162a.f22164b.f22220j.performanceKey, performancePost.postKey), this.f22162a.f22164b.f22226p.getString(C1947R.string.comment_flag_success), this.f22162a.f22164b.f22226p.getString(C1947R.string.comment_flag_failed));
                }
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                MagicNetwork.a(new C44661(this));
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
            }
        });
        textAlertDialog.show();
    }

    public void mo6815b(CommentItem commentItem, final PerformancePost performancePost) {
        TextAlertDialog textAlertDialog = new TextAlertDialog(this.f22226p, this.f22226p.getString(C1947R.string.comment_delete_prompt), this.f22226p.getString(C1947R.string.comment_delete_msg));
        textAlertDialog.m19806a(this.f22226p.getString(C1947R.string.core_delete), this.f22226p.getString(C1947R.string.core_cancel));
        textAlertDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ CommentsListDialog f22169b;

            class C44681 implements Runnable {
                final /* synthetic */ AnonymousClass13 f22167a;

                C44681(AnonymousClass13 anonymousClass13) {
                    this.f22167a = anonymousClass13;
                }

                public void run() {
                    final NetworkResponse a = PerformanceManager.a().a(this.f22167a.f22169b.f22220j.performanceKey, performancePost.postKey);
                    this.f22167a.f22169b.m23656a(a, this.f22167a.f22169b.f22226p.getString(C1947R.string.comment_delete_success), this.f22167a.f22169b.f22226p.getString(C1947R.string.comment_delete_failed));
                    this.f22167a.f22169b.f22226p.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ C44681 f22166b;

                        public void run() {
                            this.f22166b.f22167a.f22169b.m23614a(a, performancePost);
                        }
                    });
                }
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                MagicNetwork.a(new C44681(this));
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
            }
        });
        textAlertDialog.show();
    }

    public void mo6816c(CommentItem commentItem, PerformancePost performancePost) {
        m23619b(performancePost);
    }

    public void mo6817d(CommentItem commentItem, PerformancePost performancePost) {
        if (performancePost != null && performancePost.accountIcon != null && this.f22231u != null) {
            this.f22231u.mo6828a(commentItem, performancePost);
        }
    }

    private void m23619b(PerformancePost performancePost) {
        this.f22222l = "@" + performancePost.accountIcon.handle + " ";
        if (this.f22218h != null) {
            this.f22218h.setText(this.f22222l);
            this.f22218h.setSelection(this.f22222l.length());
            this.f22222l = null;
        }
    }

    private String m23637k() {
        if (this.f22225o == SuggestionMode.NoSuggestion) {
            return "";
        }
        if (this.f22235y < 0 || this.f22236z < 0) {
            return "";
        }
        if (this.f22236z < this.f22235y) {
            return "";
        }
        if (this.f22235y >= this.f22218h.getText().length()) {
            return "";
        }
        if (this.f22236z > this.f22218h.getText().length()) {
            return "";
        }
        try {
            return this.f22218h.getText().toString().substring(this.f22235y + 1, this.f22236z + 1);
        } catch (Throwable e) {
            MagicCrittercism.a(new DroidSing9192(this.f22218h.getText().toString(), e));
            return "";
        }
    }

    private void m23614a(NetworkResponse networkResponse, PerformancePost performancePost) {
        if (networkResponse.b == 0) {
            this.f22223m.remove(performancePost);
            this.f22221k.m23598a(performancePost);
            PerformanceV2 performanceV2 = this.f22220j;
            performanceV2.totalComments--;
            m23662b();
        }
    }

    protected void m23657a(final PerformancePost performancePost) {
        this.f22226p.runOnUiThread(new Runnable(this) {
            final /* synthetic */ CommentsListDialog f22171b;

            public void run() {
                this.f22171b.f22223m.add(performancePost);
                this.f22171b.m23660a(true);
                this.f22171b.f22221k.m23599b();
            }
        });
    }

    protected void m23656a(final NetworkResponse networkResponse, final String str, final String str2) {
        this.f22226p.runOnUiThread(new Runnable(this) {
            final /* synthetic */ CommentsListDialog f22175d;

            public void run() {
                if (!this.f22175d.isShowing()) {
                    return;
                }
                if (networkResponse.b == 0) {
                    this.f22175d.m19790a(this.f22175d.f22226p, str);
                } else {
                    this.f22175d.m19790a(this.f22175d.f22226p, str2);
                }
            }
        });
    }

    private void m23639l() {
        HashMap hashMap = new HashMap();
        hashMap.put(Long.valueOf(this.f22220j.accountIcon.accountId), this.f22220j.accountIcon);
        for (Track track : this.f22220j.recentTracks) {
            hashMap.put(Long.valueOf(track.accountIcon.accountId), track.accountIcon);
        }
        this.f22211E = new ArrayList();
        this.f22211E.addAll(hashMap.values());
        this.f22219i.setAdapter(this.f22232v);
    }

    private int m23608a(long j) {
        for (int i = 0; i < this.f22221k.m23597a().size(); i++) {
            if (((PerformancePost) this.f22221k.m23597a().get(i)).time == 1000 * j) {
                return i;
            }
        }
        return this.f22221k.m23597a().size() - 1;
    }
}
