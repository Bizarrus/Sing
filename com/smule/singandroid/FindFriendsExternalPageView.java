/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.annotation.PluralsRes
 *  android.support.annotation.StringRes
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.PluralsRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.ExternalFriendContainer;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsPageView;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.list_items.FindFriendsExternalListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.sections.feed.FeedDataSource;
import java.util.HashSet;
import java.util.concurrent.Future;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class FindFriendsExternalPageView
extends FindFriendsPageView
implements FindFriendsExternalListItem.ContainerDelegate {
    private static final String a = FindFriendsExternalPageView.class.getName();
    protected Mode d;
    protected View e;
    @ViewById
    protected MagicListView f;
    protected MagicAdapter g;
    protected MagicDataSource h;
    protected View i;
    protected int j;
    protected int k;

    public FindFriendsExternalPageView(Context context) {
        super(context);
    }

    public FindFriendsExternalPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = context;
    }

    public FindFriendsExternalPageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.l = context;
    }

    public void a(Context context, BaseFragment baseFragment, Mode mode, @PluralsRes int n, @StringRes int n2) {
        this.l = context;
        this.m = baseFragment;
        this.d = mode;
        this.j = n;
        this.k = n2;
    }

    public void a(String string2) {
        this.m.a(string2, Toaster.b);
    }

    @UiThread
    public void a(boolean bl) {
        if (!this.h() || this.e == null) {
            return;
        }
        this.e.setVisibility(0);
        this.e.setEnabled(bl);
    }

    @UiThread
    protected void d() {
        if (!this.h()) {
            Log.d(a, "refreshListView - Fragment is not added; aborting refresh");
            return;
        }
        if (this.g == null) {
            Log.b(a, "mFriendsAdapter was null, not ready to update follow header");
            return;
        }
        Object object = LayoutInflater.from((Context)this.l);
        if (this.f.getHeaderViewsCount() == 0) {
            this.i = object.inflate(2130903242, null);
            this.i.setBackgroundColor(this.getResources().getColor(2131689991));
            ((TextView)this.i.findViewById(2131755817)).setText(this.k);
            this.e = this.i.findViewById(2131755816);
            this.e.setOnClickListener(new View.OnClickListener(){

                public void onClick(View object) {
                    object = new HashSet();
                    Object object2 = FindFriendsExternalPageView.this.g.a();
                    for (int i = 0; i < object2.k(); ++i) {
                        ExternalFriendContainer externalFriendContainer = (ExternalFriendContainer)object2.a(i);
                        if (FollowManager.a().a(externalFriendContainer.c())) continue;
                        object.add(externalFriendContainer.c());
                    }
                    object2 = new BusyDialog(FindFriendsExternalPageView.this.m.getActivity(), "");
                    object2.a(false);
                    com.smule.android.logging.Analytics.a(Analytics.a, object.size());
                    FollowManager.a().a((HashSet<Long>)object, FindFriendsExternalPageView.this.getSocialContext(), FindFriendsExternalPageView.this.getExternalName(), new FollowManager.FollowListener((BusyDialog)((Object)object2)){
                        final /* synthetic */ BusyDialog a;
                        {
                            this.a = busyDialog;
                        }

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void a(NetworkResponse networkResponse) {
                            if (!FindFriendsExternalPageView.this.h()) {
                                return;
                            }
                            if (networkResponse.c()) {
                                for (int i = 0; i < FindFriendsExternalPageView.this.f.getChildCount(); ++i) {
                                    networkResponse = FindFriendsExternalPageView.this.f.getChildAt(i);
                                    if (!(networkResponse instanceof FindFriendsExternalListItem)) continue;
                                    ((FindFriendsExternalListItem)((Object)networkResponse)).a(true);
                                }
                                FindFriendsExternalPageView.this.a(false);
                                FeedDataSource.a = true;
                            } else {
                                networkResponse = SingApplication.g();
                                FindFriendsExternalPageView.this.a(networkResponse.getString(2131296908));
                            }
                            this.a.dismiss();
                        }
                    });
                }

            });
            if (Build.VERSION.SDK_INT < 19) {
                this.f.setMagicAdapter(null);
            }
            this.f.addHeaderView(this.i);
            if (Build.VERSION.SDK_INT < 19) {
                this.f.setMagicAdapter(this.g);
            }
        }
        object = this.m.getResources().getQuantityString(this.j, this.g.a().k(), new Object[]{this.g.a().k()});
        ((TextView)this.i.findViewById(2131755815)).setText((CharSequence)object);
        this.f();
    }

    @UiThread
    protected void e() {
        if (this.f.getHeaderViewsCount() > 0 && this.i != null) {
            this.f.removeHeaderView(this.i);
        }
    }

    @Override
    public void f() {
        for (int i = 0; i < this.g.a().k(); ++i) {
            ExternalFriendContainer externalFriendContainer = (ExternalFriendContainer)this.g.a().a(i);
            if (FollowManager.a().a(externalFriendContainer.c())) continue;
            this.a(true);
            return;
        }
        this.a(false);
    }

    protected abstract class ExternalFriendsAdapter
    extends MagicAdapter {
        private int c;
        private int e;

        public ExternalFriendsAdapter(MagicDataSource magicDataSource, int n, int n2) {
            super(magicDataSource);
            this.e = n2;
            this.c = n;
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return FindFriendsExternalListItem.a(FindFriendsExternalPageView.this.l, FindFriendsExternalPageView.this.getSocialContext());
        }

        @Override
        public void a(View object, int n, int n2) {
            object = (FindFriendsExternalListItem)((Object)object);
            object.a((ExternalFriendContainer)this.a().a(n), FindFriendsExternalPageView.this, n);
            if (FindFriendsExternalPageView.this.d == Mode.a) {
                object.setOnClickListener(new View.OnClickListener((FindFriendsExternalListItem)((Object)object)){
                    final /* synthetic */ FindFriendsExternalListItem a;
                    {
                        this.a = findFriendsExternalListItem;
                    }

                    public void onClick(View object) {
                        object = this.a.getExternalFriend();
                        final BusyScreenDialog busyScreenDialog = new BusyScreenDialog(FindFriendsExternalPageView.this.l, 2131296698);
                        com.smule.android.network.managers.UserManager.a().a(object.c(), new UserManager(){

                            @Override
                            public void handleResponse(UserManager.AccountIconResponse accountIconResponse) {
                                busyScreenDialog.dismiss();
                                if (accountIconResponse.a()) {
                                    FindFriendsExternalPageView.this.m.a(ProfileFragment.a(accountIconResponse.mAccount));
                                }
                            }
                        });
                    }

                });
            }
        }

        @Override
        public View c(ViewGroup viewGroup) {
            FindFriendsExternalPageView.this.e();
            return LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.c, null);
        }

        @Override
        public void c(MagicDataSource magicDataSource) {
            super.c(magicDataSource);
            FindFriendsExternalPageView.this.d();
        }

        @Override
        public View d(ViewGroup viewGroup) {
            FindFriendsExternalPageView.this.e();
            return LayoutInflater.from((Context)viewGroup.getContext()).inflate(this.e, null);
        }

    }

    public static enum Mode {
        a,
        b;
        

        private Mode() {
        }
    }

    protected static enum ViewState {
        a,
        b,
        c,
        d;
        

        private ViewState() {
        }
    }

}

