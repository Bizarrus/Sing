/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$OnClickListener
 *  android.widget.FrameLayout
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.annotations.ViewsById
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.network.managers.NotificationBadgeManager;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FeedFragment;
import com.smule.singandroid.NotificationsFragment;
import com.smule.singandroid.R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SongbookFragment;
import com.smule.singandroid.fragments.ListenFragment;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class BottomNavView
extends FrameLayout
implements View.OnClickListener {
    @ViewsById
    protected List<ImageButton> a;
    @ViewById
    protected View b;
    @ViewById
    protected TextView c;
    @ViewById
    protected View d;
    @ViewById
    protected View e;
    @ViewById
    protected View f;
    @ViewById
    protected View g;
    @ViewById
    protected ImageView h;
    protected boolean i;
    protected Tab j;
    protected  k;
    protected ChatManager l;
    protected boolean m = true;
    protected boolean n;

    public BottomNavView(Context context) {
        this(context, null);
    }

    public BottomNavView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public BottomNavView(Context object, AttributeSet attributeSet, int n) {
        super((Context)object, attributeSet, n);
        attributeSet = object.obtainStyledAttributes(attributeSet, R.BottomNavView);
        n = attributeSet.getInt(0, Tab.c.f);
        object = n < Tab.values().length ? Tab.values()[n] : Tab.c;
        this.j = object;
        attributeSet.recycle();
        object = !this.isInEditMode() && ChatUtils.a() ? SingApplication.k() : null;
        this.l = object;
    }

    @AfterViews
    protected void a() {
        this.a(this.j, true, true);
        Iterator<ImageButton> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOnClickListener((View.OnClickListener)this);
        }
    }

    public void a(Tab tab, boolean bl) {
        this.a(tab, bl, this.n);
        this.n = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(Tab tab, boolean bl, boolean bl2) {
        block6 : {
            block5 : {
                if (this.a == null || this.a.size() < Tab.values().length || tab == this.j && !bl2) break block5;
                for (Tab tab2 : Tab.values()) {
                    ImageButton imageButton = this.a.get(tab2.f);
                    bl2 = tab2 == tab;
                    imageButton.setSelected(bl2);
                }
                this.j = tab;
                if (tab == Tab.a) {
                    this.i = true;
                    this.b();
                }
                if (this.k != null) break block6;
            }
            return;
        }
        this.k.a(this.j, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    public void b() {
        View view = this.b;
        int n = !MagicPreferences.a(this.getContext()) && !this.i ? 0 : 8;
        view.setVisibility(n);
    }

    @UiThread
    public void c() {
        if (this.m) {
            this.d();
            return;
        }
        this.e();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void d() {
        View view;
        int n = 0;
        this.c.setVisibility(8);
        boolean bl = ChatUtils.a() && SingApplication.k().c(Chat.Bucket.a) > 0;
        boolean bl2 = NotificationBadgeManager.a().c() > 0;
        boolean bl3 = NotificationBadgeManager.a().b() > 0;
        boolean bl4 = ChatUtils.a() && SingApplication.k().c(Chat.Bucket.b) > 0;
        int n2 = bl ? 1 : 0;
        int n3 = n2;
        if (bl2) {
            n3 = n2 + 1;
        }
        n2 = n3;
        if (bl3) {
            n2 = n3 + 1;
        }
        if (n2 == 1) {
            this.h.setVisibility(0);
            n3 = bl ? 2130837948 : (bl2 ? 2130837850 : 2130837935);
            this.h.setImageResource(n3);
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.f.setVisibility(8);
        } else {
            this.h.setVisibility(8);
            view = this.d;
            n3 = bl ? 0 : 8;
            view.setVisibility(n3);
            view = this.e;
            n3 = bl2 ? 0 : 8;
            view.setVisibility(n3);
            view = this.f;
            n3 = bl3 ? 0 : 8;
            view.setVisibility(n3);
        }
        view = this.g;
        n3 = n2 == 0 && bl4 ? n : 8;
        view.setVisibility(n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void e() {
        int n = 8;
        this.h.setVisibility(8);
        this.g.setVisibility(8);
        String string2 = LayoutUtils.a((int)(NotificationBadgeManager.a().c() + NotificationBadgeManager.a().b()));
        this.c.setText((CharSequence)string2);
        TextView textView = this.c;
        if (!string2.isEmpty()) {
            n = 0;
        }
        textView.setVisibility(n);
    }

    public Tab getSelectedTab() {
        return this.j;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View object) {
        switch (object.getId()) {
            default: {
                object = Tab.c;
                break;
            }
            case 2131755327: {
                object = Tab.a;
                this.i = true;
                break;
            }
            case 2131755330: {
                object = Tab.b;
                break;
            }
            case 2131755331: {
                object = Tab.c;
                break;
            }
            case 2131755332: {
                object = Tab.d;
                break;
            }
            case 2131755340: {
                object = Tab.e;
            }
        }
        if (object != this.j) {
            this.a((Tab)((Object)object), true);
        }
    }

    protected void onRestoreInstanceState(Parcelable object) {
        if (!(object instanceof BottomNavViewState)) {
            super.onRestoreInstanceState((Parcelable)object);
            return;
        }
        object = (BottomNavViewState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        this.i = object.a;
        this.a(Tab.a(object.b), true);
        this.c();
        this.b();
    }

    protected Parcelable onSaveInstanceState() {
        return new BottomNavViewState(super.onSaveInstanceState(), this);
    }

    public void setOnTabChangedListener( onTabChangedListener) {
        this.k = onTabChangedListener;
        this.n = true;
    }

    public void setUseNotificationIcons(boolean bl) {
        this.m = bl;
        this.c();
    }

    private static class BottomNavViewState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<BottomNavViewState> CREATOR = new Parcelable.Creator<BottomNavViewState>(){

            public BottomNavViewState a(Parcel parcel) {
                return new BottomNavViewState(parcel);
            }

            public BottomNavViewState[] a(int n) {
                return new BottomNavViewState[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        final boolean a;
        final int b;

        /*
         * Enabled aggressive block sorting
         */
        public BottomNavViewState(Parcel parcel) {
            super(parcel);
            boolean bl = parcel.readInt() != 0;
            this.a = bl;
            this.b = parcel.readInt();
        }

        public BottomNavViewState(Parcelable parcelable, BottomNavView bottomNavView) {
            super(parcelable);
            this.a = bottomNavView.i;
            this.b = bottomNavView.j.ordinal();
        }

        /*
         * Enabled aggressive block sorting
         */
        public void writeToParcel(Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            n = this.a ? 1 : 0;
            parcel.writeInt(n);
            parcel.writeInt(this.b);
        }

    }

    public static interface NavBarFragmentFactory {
        public BaseFragment a();
    }

    public static enum Tab {
        a(0, FeedFragment.g, new NavBarFragmentFactory(){

            @Override
            public BaseFragment a() {
                return FeedFragment.a();
            }
        }),
        b(1, ListenFragment.g, new NavBarFragmentFactory(){

            @Override
            public BaseFragment a() {
                return ListenFragment.a();
            }
        }),
        c(2, SongbookFragment.g, new NavBarFragmentFactory(){

            @Override
            public BaseFragment a() {
                return SongbookFragment.t();
            }
        }),
        d(3, NotificationsFragment.g, new NavBarFragmentFactory(){

            @Override
            public BaseFragment a() {
                return NotificationsFragment.a();
            }
        }),
        e(4, ProfileFragment.g, new NavBarFragmentFactory(){

            @Override
            public BaseFragment a() {
                return ProfileFragment.a();
            }
        });
        
        public final int f;
        public final String g;
        public final NavBarFragmentFactory h;

        private Tab(int n2, String string3, NavBarFragmentFactory navBarFragmentFactory) {
            this.f = n2;
            this.g = string3;
            this.h = navBarFragmentFactory;
        }

        public static Tab a(int n) {
            for (Tab tab : Tab.values()) {
                if (tab.f != n) continue;
                return tab;
            }
            return null;
        }

    }

}

