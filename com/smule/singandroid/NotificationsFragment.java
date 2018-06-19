package com.smule.singandroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.logging.Log;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.NotificationListItem$DetailedType;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.singandroid.BaseFragment.BaseFragmentResponder;
import com.smule.singandroid.chat.MessageCenterFragment;
import com.smule.singandroid.customviews.NotificationsListView;
import com.smule.singandroid.customviews.NotificationsListView.NotificationsBaseAdapter;
import com.smule.singandroid.customviews.SingTabLayoutHelper;
import com.smule.singandroid.songbook_search.SearchFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer;
import com.smule.singandroid.utils.QuickReturnListViewMenuSyncer.ActionBarHighlightMode;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.NotificationFilterType;
import com.smule.singandroid.utils.SingAnalytics.NotificationScreenType;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NotificationsFragment extends BaseFragment {
    public static final String f18952e = NotificationsFragment.class.getName();
    private OnClickListener f18953A = new C38812(this);
    @InstanceState
    protected boolean f18954f;
    @InstanceState
    protected boolean f18955g;
    @ViewById
    protected TabLayout f18956h;
    @ViewById
    protected CustomViewPager f18957i;
    protected SingTabLayoutHelper f18958j;
    protected ChatManager f18959k;
    protected MenuItem f18960l;
    protected TextView f18961m;
    protected ImageView f18962n;
    protected ChatListener f18963o;
    @FragmentArg
    protected ArrayList<String> f18964p;
    @FragmentArg
    protected boolean f18965q;
    @FragmentArg
    protected NotificationListItem$DetailedType f18966r;
    OnPageChangeListener f18967s = new C38844(this);
    @InstanceState
    protected Parcelable f18968t;
    @InstanceState
    protected Parcelable f18969u;
    private OnGlobalLayoutListener f18970v;
    private NotificationsBaseAdapter f18971w;
    private NotificationsBaseAdapter f18972x;
    private int[] f18973y = new int[2];
    private OnClickListener f18974z = new C38801(this);

    class C38801 implements OnClickListener {
        final /* synthetic */ NotificationsFragment f18937a;

        C38801(NotificationsFragment notificationsFragment) {
            this.f18937a = notificationsFragment;
        }

        public void onClick(View view) {
            if (this.f18937a.isAdded()) {
                this.f18937a.mo6487a(MessageCenterFragment.m22811a());
            }
        }
    }

    class C38812 implements OnClickListener {
        final /* synthetic */ NotificationsFragment f18938a;

        C38812(NotificationsFragment notificationsFragment) {
            this.f18938a = notificationsFragment;
        }

        public void onClick(View view) {
            if (this.f18938a.isAdded()) {
                ((MasterActivity) this.f18938a.getActivity()).y();
            }
        }
    }

    class C38823 extends ChatListenerAdapter {
        final /* synthetic */ NotificationsFragment f18939a;

        C38823(NotificationsFragment notificationsFragment) {
            this.f18939a = notificationsFragment;
        }

        public void m20395d(Chat chat) {
            this.f18939a.m20409a(this.f18939a.f18959k.c(Bucket.INBOX), this.f18939a.f18959k.c(Bucket.OTHER) > 0);
        }
    }

    class C38844 implements OnPageChangeListener {
        final /* synthetic */ NotificationsFragment f18942a;

        C38844(NotificationsFragment notificationsFragment) {
            this.f18942a = notificationsFragment;
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            final NotificationsBaseAdapter a = i == 0 ? this.f18942a.f18971w : this.f18942a.f18972x;
            if (a != null) {
                ActionBarHighlightMode actionBarHighlightMode = this.f18942a.m20423A() == null ? ActionBarHighlightMode.ALWAYS : ActionBarHighlightMode.NEVER;
                this.f18942a.mo6488n();
                this.f18942a.mo6485a(a.m23261k(), actionBarHighlightMode, new OnScrollListener(this) {
                    final /* synthetic */ C38844 f18941b;

                    public void onScrollStateChanged(AbsListView absListView, int i) {
                    }

                    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                        boolean z = false;
                        if (a.m23261k() == null || a.m23261k().getChildCount() <= 0 || a.m23261k().getFirstVisiblePosition() != 0) {
                            a.m23262l().setEnabled(false);
                            return;
                        }
                        int top = a.m23261k().getChildAt(0).getTop();
                        SwipeRefreshLayout l = a.m23262l();
                        if (top >= 0) {
                            z = true;
                        }
                        l.setEnabled(z);
                    }
                });
            }
        }

        public void onPageScrollStateChanged(int i) {
        }
    }

    class C38855 extends PagerAdapter {
        final /* synthetic */ NotificationsFragment f18943a;

        C38855(NotificationsFragment notificationsFragment) {
            this.f18943a = notificationsFragment;
        }

        public CharSequence getPageTitle(int i) {
            return i == 0 ? this.f18943a.getResources().getString(C1947R.string.core_activity) : this.f18943a.getResources().getString(C1947R.string.core_invites);
        }

        public int getCount() {
            return this.f18943a.f18964p == null ? 2 : 1;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View a = NotificationsListView.m23295a(this.f18943a.getActivity(), this.f18943a, i, this.f18943a.f18957i.getCurrentItem() == i);
            a.setOrientation(1);
            a.setLayoutParams(new LayoutParams(-1, -1));
            viewGroup.addView(a);
            return a;
        }
    }

    class C38866 implements OnTabSelectedListener {
        final /* synthetic */ NotificationsFragment f18944a;

        C38866(NotificationsFragment notificationsFragment) {
            this.f18944a = notificationsFragment;
        }

        public void onTabSelected(Tab tab) {
            this.f18944a.m20410a(tab);
            this.f18944a.f18954f = tab.getPosition() == 0;
            this.f18944a.f18957i.setCurrentItem(tab.getPosition());
            SingAnalytics.m26082a(this.f18944a.f18954f ? NotificationFilterType.ALL : NotificationFilterType.INVITE);
            this.f18944a.m20400J();
            this.f18944a.mo6489B();
        }

        public void onTabUnselected(Tab tab) {
            this.f18944a.m20400J();
            this.f18944a.mo6489B();
            this.f18944a.m20416b(tab);
        }

        public void onTabReselected(Tab tab) {
            this.f18944a.m20410a(tab);
        }
    }

    class C38877 implements OnClickListener {
        final /* synthetic */ NotificationsFragment f18945a;

        C38877(NotificationsFragment notificationsFragment) {
            this.f18945a = notificationsFragment;
        }

        public void onClick(View view) {
            this.f18945a.mo6487a(MessageCenterFragment.m22811a());
        }
    }

    class C38899 implements Runnable {
        final /* synthetic */ NotificationsFragment f18948a;

        C38899(NotificationsFragment notificationsFragment) {
            this.f18948a = notificationsFragment;
        }

        public void run() {
            if (this.f18948a.isAdded()) {
                int i;
                View findViewById = this.f18948a.getActivity().findViewById(C1947R.id.action_message_center);
                findViewById.getLocationOnScreen(this.f18948a.f18973y);
                int width = this.f18948a.f18973y[0] + (findViewById.getWidth() / 2);
                if (this.f18948a.f18973y[1] > 0) {
                    i = this.f18948a.f18973y[1];
                } else {
                    i = this.f18948a.getResources().getDimensionPixelSize(C1947R.dimen.app_bar_height) / 2;
                }
                ((MasterActivity) this.f18948a.getActivity()).a(this.f18948a, width, i, this.f18948a.f18974z, this.f18948a.f18953A);
            }
        }
    }

    public interface NotificationItemInterface {
        void mo6864a();

        void mo6865a(NotificationsFragment notificationsFragment, BaseFragmentResponder baseFragmentResponder, NotificationListItem notificationListItem, NotificationScreenType notificationScreenType, Runnable runnable, boolean z, boolean z2);
    }

    public enum Tabs {
        ACTIVITY,
        INVITES
    }

    public static NotificationsFragment m20405a() {
        return m20406a(null, null);
    }

    public static NotificationsFragment m20406a(List<String> list, NotificationListItem$DetailedType notificationListItem$DetailedType) {
        return m20407a((List) list, false, notificationListItem$DetailedType);
    }

    public static NotificationsFragment m20407a(List<String> list, boolean z, NotificationListItem$DetailedType notificationListItem$DetailedType) {
        return NotificationsFragment_.m20451I().m20449a(list != null ? new ArrayList(list) : null).m20450a(z).m20448a(notificationListItem$DetailedType).m20447a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SharedPreferences sharedPreferences = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0);
        this.f18954f = sharedPreferences.getBoolean("INITIAL_TAB", false);
        this.f18955g = sharedPreferences.getBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", false);
        m19831a(BaseFragment.ActionBarHighlightMode.ALWAYS);
        setHasOptionsMenu(true);
        if (ChatUtils.a()) {
            this.f18959k = SingApplication.j();
            this.f18963o = new C38823(this);
        }
    }

    public boolean mo6445g() {
        return true;
    }

    public boolean mo6446i() {
        return false;
    }

    private void m20399I() {
        this.f18957i.addOnPageChangeListener(this.f18967s);
        this.f18957i.setAdapter(new C38855(this));
    }

    private void m20410a(Tab tab) {
        this.f18958j.m23479a(true, tab);
    }

    private void m20416b(Tab tab) {
        this.f18958j.m23479a(false, tab);
    }

    @AfterViews
    protected void m20446z() {
        m20399I();
        if (this.f18964p == null) {
            this.f18956h.setSelectedTabIndicatorColor(getResources().getColor(C1947R.color.button_text_inverse));
            this.f18956h.setSelectedTabIndicatorHeight(getResources().getDimensionPixelOffset(C1947R.dimen.margin_extra_tiny));
            this.f18958j = new SingTabLayoutHelper(this.f18956h, this.f18957i);
            this.f18958j.m11744a(false);
            this.f18958j.m11740a(new C38866(this));
        } else {
            this.f18956h.setVisibility(8);
        }
        if (!this.f18954f) {
            m20401K();
        } else if (!this.f18955g) {
            m20401K();
        }
    }

    private void m20400J() {
        if (this.f18954f && this.f18971w != null) {
            this.f18971w.m23264n();
        } else if (!this.f18954f && this.f18972x != null) {
            this.f18972x.m23264n();
        }
    }

    private void m20401K() {
        ((MasterActivity) getActivity()).w();
    }

    public ArrayList<String> m20423A() {
        return this.f18964p;
    }

    private void m20414a(String str, int i) {
        TextView textView = (TextView) this.f18956h.getTabAt(i).getCustomView().findViewById(C1947R.id.tab_badge);
        ImageView imageView = (ImageView) this.f18956h.getTabAt(i).getCustomView().findViewById(C1947R.id.tab_badge_empty);
        if (str == null) {
            textView.setVisibility(8);
            imageView.setVisibility(8);
        } else if (str.isEmpty()) {
            textView.setVisibility(8);
            imageView.setVisibility(0);
        } else {
            textView.setText(str);
            textView.setVisibility(0);
            imageView.setVisibility(8);
        }
    }

    @UiThread
    public void mo6489B() {
        String str = null;
        if (isAdded() && this.f18956h.getVisibility() == 0 && this.f18971w != null && this.f18972x != null) {
            String a = this.f18971w.m23263m() > 0 ? LayoutUtils.m25850a(this.f18971w.m23263m()) : null;
            if (this.f18972x.m23263m() > 0) {
                str = LayoutUtils.m25850a(this.f18972x.m23263m());
            }
            m20414a(a, 0);
            m20414a(str, 1);
            ((MasterActivity) getActivity()).A();
        }
    }

    public void onStart() {
        int i = C1947R.string.core_notifications;
        super.onStart();
        if (this.f18959k != null) {
            this.f18959k.a(this.f18963o);
        }
        if (this.f18966r == null) {
            if (this.f18964p != null) {
                i = C1947R.string.core_notification;
            }
            m19850c(i);
            return;
        }
        CharSequence charSequence;
        switch (this.f18966r) {
            case LOVE_NON_SUPPRESSED:
                charSequence = "Loves";
                break;
            case LOVE_SUPPRESSED:
                charSequence = "Loves";
                break;
            case FAVORITE_NON_SUPPRESSED:
                charSequence = "Favorites";
                break;
            case FAVORITE_SUPPRESSED:
                charSequence = "Favorites";
                break;
            case COMMENT_AGGREGATED:
                charSequence = "Comments";
                break;
            case COMMENT_NON_AGGREGATED:
                charSequence = "Comments";
                break;
            case MENTION_ACCOUNT:
                charSequence = "Mentions";
                break;
            case MENTION_PERFORMANCE:
                charSequence = "Mentions";
                break;
            case FOLLOW:
                charSequence = "Follows";
                break;
            case INVITE:
                charSequence = "Invites";
                break;
            case JOIN_GROUP:
                charSequence = "Group Joins";
                break;
            case JOIN_DUET:
                charSequence = "Duet Joins";
                break;
            case FOLLOW_FB:
                charSequence = "Facebook Friends Follows";
                break;
            case CONNECT_FB:
                charSequence = "Facebook Friends";
                break;
            case RENDER:
                charSequence = "Videos";
                break;
            default:
                charSequence = "";
                break;
        }
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = getString(C1947R.string.core_notifications);
        }
        mo6861a(charSequence);
    }

    public void onResume() {
        super.onResume();
        int i = (this.f18954f || this.f18964p != null) ? 0 : 1;
        if (this.f18956h.getTabCount() > i) {
            this.f18956h.getTabAt(i).select();
        }
    }

    public void mo6449u() {
        super.mo6449u();
    }

    public void onPause() {
        super.onPause();
        Editor edit = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        edit.putBoolean("INITIAL_TAB", this.f18954f);
        edit.putBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", false);
        edit.apply();
    }

    public void onStop() {
        super.onStop();
        if (this.f18959k != null) {
            this.f18959k.b(this.f18963o);
        }
        m20402L();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f18957i.removeOnPageChangeListener(this.f18967s);
        this.f18971w = null;
        this.f18972x = null;
        this.f18958j = null;
    }

    private void m20402L() {
        if (!(this.f18971w == null || this.f18971w.m23261k() == null)) {
            m20430a(0, this.f18971w.m23261k().onSaveInstanceState());
        }
        if (this.f18972x != null && this.f18972x.m23261k() != null) {
            m20430a(1, this.f18972x.m23261k().onSaveInstanceState());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        m20402L();
        super.onSaveInstanceState(bundle);
    }

    protected void mo6420v() {
        SingAnalytics.m26156t();
    }

    protected boolean mo6450x() {
        return false;
    }

    public boolean mo6444d() {
        return false;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        super.onCreateOptionsMenu(menu, menuInflater);
        if (this.f18959k != null && this.f18964p == null && ChatUtils.a()) {
            Log.b(f18952e, "onCreateOptionsMenu");
            if (menu.findItem(C1947R.id.action_message_center) != null) {
                m20403M();
                return;
            }
            int c;
            menuInflater.inflate(C1947R.menu.notifications_fragment_menu, menu);
            this.f18960l = menu.findItem(C1947R.id.action_message_center);
            final View actionView = this.f18960l.getActionView();
            this.f18961m = (TextView) actionView.findViewById(C1947R.id.message_count);
            this.f18962n = (ImageView) actionView.findViewById(C1947R.id.message_others);
            actionView.setOnClickListener(new C38877(this));
            if (this.f18970v != null) {
                LayoutUtils.m25859b(actionView, this.f18970v);
            }
            this.f18970v = new OnGlobalLayoutListener(this, new ViewTreeObserver.OnGlobalLayoutListener(this) {
                final /* synthetic */ NotificationsFragment f18947b;

                public void onGlobalLayout() {
                    LayoutUtils.m25859b(actionView, this.f18947b.f18970v);
                    this.f18947b.f18970v = null;
                    if (this.f18947b.isAdded() && actionView.getHeight() < (this.f18947b.getResources().getDimensionPixelSize(C1947R.dimen.app_bar_height) * 9) / 10) {
                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f18947b.f18962n.getLayoutParams();
                        layoutParams.setMargins(0, 0, 0, 0);
                        layoutParams.gravity = GravityCompat.END;
                        this.f18947b.f18962n.setLayoutParams(layoutParams);
                        layoutParams = (FrameLayout.LayoutParams) this.f18947b.f18961m.getLayoutParams();
                        layoutParams.setMargins(0, 0, 0, 0);
                        this.f18947b.f18961m.setLayoutParams(layoutParams);
                    }
                }
            });
            LayoutUtils.m25854a(actionView, this.f18970v);
            m20403M();
            if (this.f18959k != null) {
                c = this.f18959k.c(Bucket.INBOX);
            } else {
                c = 0;
            }
            if (this.f18959k != null && this.f18959k.c(Bucket.OTHER) > 0) {
                z = true;
            }
            m20409a(c, z);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_search:
                Analytics.m17848a(SearchClkContext.NOTIFICATION);
                mo6487a(SearchFragment.m25251A());
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu();
        this.f18960l = null;
        this.f18961m = null;
        this.f18962n = null;
    }

    public void m20430a(int i, Parcelable parcelable) {
        if (i == 0) {
            this.f18968t = parcelable;
        } else {
            this.f18969u = parcelable;
        }
    }

    public Parcelable m20436d(int i) {
        if (i == 0) {
            return this.f18968t;
        }
        return this.f18969u;
    }

    public void m20434a(NotificationsBaseAdapter notificationsBaseAdapter) {
        this.f18971w = notificationsBaseAdapter;
    }

    public void m20435b(NotificationsBaseAdapter notificationsBaseAdapter) {
        this.f18972x = notificationsBaseAdapter;
    }

    public void mo6486a(PerformanceV2 performanceV2, boolean z, boolean z2) {
        super.mo6486a(performanceV2, z, z2);
    }

    public void mo6485a(AbsListView absListView, ActionBarHighlightMode actionBarHighlightMode, OnScrollListener onScrollListener) {
        super.mo6485a(absListView, actionBarHighlightMode, onScrollListener);
    }

    public void mo6488n() {
        super.mo6488n();
    }

    public int m20425C() {
        return this.d;
    }

    public QuickReturnListViewMenuSyncer m20426D() {
        return this.a;
    }

    private void m20409a(int i, boolean z) {
        if (this.f18961m == null) {
            return;
        }
        if (i > 0) {
            this.f18961m.setText(MiscUtils.m25884a(i));
            this.f18961m.setVisibility(0);
            this.f18962n.setVisibility(8);
        } else if (z) {
            this.f18961m.setVisibility(8);
            this.f18962n.setVisibility(0);
        } else {
            this.f18961m.setVisibility(8);
            this.f18962n.setVisibility(8);
        }
    }

    private void m20403M() {
        new Handler().post(new C38899(this));
    }

    public static void m20411a(Tabs tabs) {
        boolean z = false;
        Editor edit = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        String str = "INITIAL_TAB";
        if (tabs == Tabs.ACTIVITY) {
            z = true;
        }
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void m20398E() {
        Editor edit = SingApplication.f().getSharedPreferences(MasterActivity.class.getName(), 0).edit();
        edit.putBoolean("INITIAL_TAB", true);
        edit.putBoolean("SUPPRESS_CHAT_TOOLTIP_ON_ACTIVITY_TAB", true);
        edit.apply();
    }

    public String mo6383s() {
        return f18952e;
    }

    public void mo6487a(BaseFragment baseFragment) {
        if (baseFragment instanceof NotificationsFragment) {
            mo6929a(baseFragment, f18952e + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + baseFragment.hashCode());
        } else {
            super.mo6487a(baseFragment);
        }
    }

    public NotificationScreenType m20427F() {
        return this.f18964p == null ? NotificationScreenType.INITIAL : NotificationScreenType.EXPANDED;
    }

    public boolean m20428G() {
        return this.f18965q;
    }

    protected boolean mo6447j() {
        return true;
    }

    public int m20429H() {
        return this.f18956h.getSelectedTabPosition();
    }
}
