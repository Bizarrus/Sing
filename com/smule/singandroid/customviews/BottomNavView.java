package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.ChatManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.FeedFragment;
import com.smule.singandroid.MagicPreferences;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.ProfileFragment;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.fragments.ListenFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class BottomNavView extends FrameLayout implements OnClickListener {
    @ViewsById
    protected List<ImageButton> f21463a;
    @ViewById
    protected View f21464b;
    @ViewById
    protected TextView f21465c;
    @ViewById
    protected View f21466d;
    @ViewById
    protected View f21467e;
    @ViewById
    protected View f21468f;
    @ViewById
    protected View f21469g;
    @ViewById
    protected ImageView f21470h;
    protected boolean f21471i;
    protected Tab f21472j;
    protected OnTabChangedListener f21473k;
    protected ChatManager f21474l;
    protected boolean f21475m;
    protected boolean f21476n;

    private static class BottomNavViewState extends BaseSavedState {
        public static final Creator<BottomNavViewState> CREATOR = new C43761();
        final boolean f21452a;
        final int f21453b;

        static class C43761 implements Creator<BottomNavViewState> {
            C43761() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m23118a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m23119a(i);
            }

            public BottomNavViewState m23118a(Parcel parcel) {
                return new BottomNavViewState(parcel);
            }

            public BottomNavViewState[] m23119a(int i) {
                return new BottomNavViewState[i];
            }
        }

        public BottomNavViewState(Parcelable parcelable, BottomNavView bottomNavView) {
            super(parcelable);
            this.f21452a = bottomNavView.f21471i;
            this.f21453b = bottomNavView.f21472j.ordinal();
        }

        public BottomNavViewState(Parcel parcel) {
            super(parcel);
            this.f21452a = parcel.readInt() != 0;
            this.f21453b = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f21452a ? 1 : 0);
            parcel.writeInt(this.f21453b);
        }
    }

    public interface NavBarFragmentFactory {
        BaseFragment mo6779a();
    }

    public enum Tab {
        FEED(0, FeedFragment.f18666e, new C43771()),
        FEATURED(1, ListenFragment.f22388e, new C43782()),
        SONGBOOK(2, SongbookFragment.f20306e, new C43793()),
        NOTIFICATIONS(3, NotificationsFragment.f18952e, new C43804()),
        PROFILE(4, ProfileFragment.f19477e, new C43815());
        
        public final int f21460f;
        public final String f21461g;
        public final NavBarFragmentFactory f21462h;

        static class C43771 implements NavBarFragmentFactory {
            C43771() {
            }

            public BaseFragment mo6779a() {
                return FeedFragment.m20104a();
            }
        }

        static class C43782 implements NavBarFragmentFactory {
            C43782() {
            }

            public BaseFragment mo6779a() {
                return ListenFragment.m23792a();
            }
        }

        static class C43793 implements NavBarFragmentFactory {
            C43793() {
            }

            public BaseFragment mo6779a() {
                return SongbookFragment.m21826a();
            }
        }

        static class C43804 implements NavBarFragmentFactory {
            C43804() {
            }

            public BaseFragment mo6779a() {
                return NotificationsFragment.m20405a();
            }
        }

        static class C43815 implements NavBarFragmentFactory {
            C43815() {
            }

            public BaseFragment mo6779a() {
                return ProfileFragment.m21035a();
            }
        }

        private Tab(int i, String str, NavBarFragmentFactory navBarFragmentFactory) {
            this.f21460f = i;
            this.f21461g = str;
            this.f21462h = navBarFragmentFactory;
        }

        public static Tab m23126a(int i) {
            for (Tab tab : values()) {
                if (tab.f21460f == i) {
                    return tab;
                }
            }
            return null;
        }
    }

    public BottomNavView(Context context) {
        this(context, null);
    }

    public BottomNavView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomNavView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21475m = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomNavView);
        int i2 = obtainStyledAttributes.getInt(0, Tab.SONGBOOK.f21460f);
        this.f21472j = i2 < Tab.values().length ? Tab.values()[i2] : Tab.SONGBOOK;
        obtainStyledAttributes.recycle();
        ChatManager j = (isInEditMode() || !ChatUtils.a()) ? null : SingApplication.j();
        this.f21474l = j;
    }

    @AfterViews
    protected void m23127a() {
        m23129a(this.f21472j, true, true);
        for (ImageButton onClickListener : this.f21463a) {
            onClickListener.setOnClickListener(this);
        }
    }

    public void setOnTabChangedListener(OnTabChangedListener onTabChangedListener) {
        this.f21473k = onTabChangedListener;
        this.f21476n = true;
    }

    public void m23128a(Tab tab, boolean z) {
        m23129a(tab, z, this.f21476n);
        this.f21476n = false;
    }

    protected void m23129a(Tab tab, boolean z, boolean z2) {
        if (this.f21463a != null && this.f21463a.size() >= Tab.values().length) {
            if (tab != this.f21472j || z2) {
                for (Tab tab2 : Tab.values()) {
                    boolean z3;
                    ImageButton imageButton = (ImageButton) this.f21463a.get(tab2.f21460f);
                    if (tab2 == tab) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    imageButton.setSelected(z3);
                }
                this.f21472j = tab;
                if (tab == Tab.FEED) {
                    this.f21471i = true;
                    mo6780b();
                }
                if (this.f21473k != null) {
                    this.f21473k.a(this.f21472j, z);
                }
            }
        }
    }

    public Tab getSelectedTab() {
        return this.f21472j;
    }

    public void setUseNotificationIcons(boolean z) {
        this.f21475m = z;
        mo6781c();
    }

    @UiThread
    public void mo6780b() {
        View view = this.f21464b;
        int i = (MagicPreferences.m20307a(getContext()) || this.f21471i) ? 8 : 0;
        view.setVisibility(i);
    }

    @UiThread
    public void mo6781c() {
        if (this.f21475m) {
            m23132d();
        } else {
            m23133e();
        }
    }

    protected void m23132d() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        this.f21465c.setVisibility(8);
        int i5 = (!ChatUtils.a() || SingApplication.j().c(Bucket.INBOX) <= 0) ? 0 : 1;
        if (SingApplication.l() > 0) {
            i = 1;
        } else {
            i = 0;
        }
        if (SingApplication.m() > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i6;
        if (!ChatUtils.a() || SingApplication.j().c(Bucket.OTHER) <= 0) {
            i6 = 0;
        } else {
            i6 = 1;
        }
        if (i5 != 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i != 0) {
            i3++;
        }
        if (i2 != 0) {
            i3++;
        }
        if (i3 == 1) {
            this.f21470h.setVisibility(0);
            if (i5 != 0) {
                i5 = C1947R.drawable.icn_message_badge_white;
            } else if (i != 0) {
                i5 = C1947R.drawable.icn_activity_badge_white;
            } else {
                i5 = C1947R.drawable.icn_invite_badge_white;
            }
            this.f21470h.setImageResource(i5);
            this.f21466d.setVisibility(8);
            this.f21467e.setVisibility(8);
            this.f21468f.setVisibility(8);
        } else {
            this.f21470h.setVisibility(8);
            View view = this.f21466d;
            if (i5 != 0) {
                i5 = 0;
            } else {
                i5 = 8;
            }
            view.setVisibility(i5);
            view = this.f21467e;
            if (i != 0) {
                i5 = 0;
            } else {
                i5 = 8;
            }
            view.setVisibility(i5);
            view = this.f21468f;
            if (i2 != 0) {
                i5 = 0;
            } else {
                i5 = 8;
            }
            view.setVisibility(i5);
        }
        View view2 = this.f21469g;
        if (i3 != 0 || r7 == 0) {
            i4 = 8;
        }
        view2.setVisibility(i4);
    }

    protected void m23133e() {
        int i = 8;
        this.f21470h.setVisibility(8);
        this.f21469g.setVisibility(8);
        Object a = LayoutUtils.m25850a(SingApplication.l() + SingApplication.m());
        this.f21465c.setText(a);
        TextView textView = this.f21465c;
        if (!a.isEmpty()) {
            i = 0;
        }
        textView.setVisibility(i);
    }

    protected Parcelable onSaveInstanceState() {
        return new BottomNavViewState(super.onSaveInstanceState(), this);
    }

    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof BottomNavViewState) {
            BottomNavViewState bottomNavViewState = (BottomNavViewState) parcelable;
            super.onRestoreInstanceState(bottomNavViewState.getSuperState());
            this.f21471i = bottomNavViewState.f21452a;
            m23128a(Tab.m23126a(bottomNavViewState.f21453b), true);
            mo6781c();
            mo6780b();
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    public void onClick(View view) {
        Tab tab;
        switch (view.getId()) {
            case C1947R.id.bottom_nav_feed:
                tab = Tab.FEED;
                this.f21471i = true;
                break;
            case C1947R.id.bottom_nav_featured:
                tab = Tab.FEATURED;
                break;
            case C1947R.id.bottom_nav_sing:
                tab = Tab.SONGBOOK;
                break;
            case C1947R.id.bottom_nav_notifications:
                tab = Tab.NOTIFICATIONS;
                break;
            case C1947R.id.bottom_nav_profile:
                tab = Tab.PROFILE;
                break;
            default:
                tab = Tab.SONGBOOK;
                break;
        }
        if (tab != this.f21472j) {
            m23128a(tab, true);
        }
    }
}
