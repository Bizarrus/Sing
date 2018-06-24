/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.smule.android.logging.Analytics;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.MediaListView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.datasource.OnboardingSongsDataSource;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.ListingListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.onboarding.OnboardingActivity;
import com.smule.singandroid.onboarding.OnboardingSongsFragment_;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingSongsFragment
extends BaseFragment
implements MagicDataSource.DataSourceObserver {
    public static final String g = OnboardingSongsFragment.class.getName();
    @ViewById
    protected MediaListView h;
    @ViewById
    protected View i;
    @ViewById
    protected LinearLayout j;
    @ViewById
    protected ProfileImageWithVIPBadge k;
    @ViewById
    protected View l;
    @ViewById
    protected View m;
    @ViewById
    protected View n;
    @ViewById
    protected View o;
    @ViewById
    protected View p;
    @ViewById
    protected LinearLayout q;
    @ViewById
    protected View r;
    private OnboardingSongsAdapter s;
    private OnboardingSongsAdapterInterface t;
    private ArrayList<Integer> u;
    private boolean v;
    private TextAlertDialog w;
    private SongbookEntry x;
    private ConcurrentHashMap<String, Boolean> y;

    public OnboardingSongsFragment() {
        this.t = new OnboardingSongsAdapterInterface();
        this.u = new ArrayList();
        this.x = null;
        this.y = new ConcurrentHashMap();
    }

    private void G() {
        this.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                OnboardingSongsFragment.this.H();
                OnboardingSongsFragment.this.a("NONE", "NONE");
                ((OnboardingActivity)OnboardingSongsFragment.this.getActivity()).a((SongbookEntry)null);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
            }
        });
    }

    private void H() {
        ((OnboardingActivity)this.getActivity()).t();
    }

    private void I() {
        OnboardingSongsDataSource onboardingSongsDataSource = new OnboardingSongsDataSource(this.u);
        this.s = new OnboardingSongsAdapter(new OnboardingSongsDataSource(this.u), this.t);
        this.h.setMagicAdapter(this.s);
        this.s.a(this);
        if (onboardingSongsDataSource.i() == MagicDataSource.DataState.b) {
            this.d(true);
        }
    }

    private void J() {
        if (this.v) {
            this.r.setVisibility(0);
            this.o.setVisibility(8);
            this.m.setVisibility(8);
            this.l.setVisibility(8);
            this.p.setVisibility(8);
            ((RelativeLayout.LayoutParams)this.i.getLayoutParams()).addRule(3, this.r.getId());
            return;
        }
        this.m.setVisibility(8);
        this.l.setVisibility(0);
        this.o.setVisibility(0);
    }

    private void a(final String string2, final String string3) {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                RecommendationManager.a().a(string2, string3, true);
            }
        });
    }

    private void a(boolean bl, ArrangementVersionLiteEntry arrangementVersionLiteEntry, ListingListItem listingListItem) {
        this.y.put(arrangementVersionLiteEntry.c(), bl);
        if (bl) {
            MediaPlayerServiceController.a().b(arrangementVersionLiteEntry.c());
            this.a(listingListItem);
            return;
        }
        com.smule.android.logging.Analytics.a("pickasong", SongbookEntry.b(arrangementVersionLiteEntry), arrangementVersionLiteEntry.i(), arrangementVersionLiteEntry.r(), null, SongbookEntry.a(arrangementVersionLiteEntry));
        this.a(arrangementVersionLiteEntry);
    }

    public static OnboardingSongsFragment c(boolean bl) {
        OnboardingSongsFragment_ onboardingSongsFragment_ = new OnboardingSongsFragment_();
        Bundle bundle = new Bundle();
        bundle.putBoolean("TOPIC_SELECTED", bl);
        onboardingSongsFragment_.setArguments(bundle);
        return onboardingSongsFragment_;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(boolean bl) {
        if (!this.isAdded()) {
            return;
        }
        LinearLayout linearLayout = this.j;
        int n = bl ? 8 : 0;
        linearLayout.setVisibility(n);
    }

    @Override
    protected void A() {
        com.smule.android.logging.Analytics.q();
    }

    @Click
    protected void F() {
        com.smule.android.logging.Analytics.a();
        ((OnboardingActivity)this.getActivity()).b();
    }

    @AfterViews
    void a() {
        this.J();
        this.d(false);
        this.I();
        SingApplication.d().a("OnboardingActivity.OP_WAIT_OPERATIONS", Collections.singletonList("InitAppTask.OP_LOCALIZE_SETTINGS"), new OperationLoader.Operation(){

            @Override
            public void a(OperationLoader operationLoader) {
                SingApplication.d().a("OnboardingActivity.OP_WAIT_OPERATIONS");
                operationLoader.c(this.f);
            }
        }).a();
    }

    @Override
    public void a(MagicDataSource magicDataSource) {
    }

    @Override
    public void a(MagicDataSource magicDataSource, List<Object> list) {
    }

    @UiThread
    protected void a(CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        if (!this.isAdded()) {
            return;
        }
        this.w = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296667), this.getString(2131297035));
        this.w.a(this.getString(2131296733), this.getString(2131296701));
        this.w.a(customAlertDialogListener);
        this.w.show();
    }

    protected void a(ListingListItem object) {
        if (!this.isAdded()) {
            return;
        }
        object.setAlbumArtClickedState(false);
        object = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296404), MessageFormat.format(this.getString(2131296403), this.getString(2131297940)), true, false);
        object.a(this.getString(2131296705), null);
        object.show();
    }

    @Override
    public void b(MagicDataSource magicDataSource) {
    }

    @UiThread
    protected void b(CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        if (!this.isAdded()) {
            return;
        }
        this.w = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296667), this.getString(2131297036));
        this.w.a(this.getString(2131296733), this.getString(2131296701));
        this.w.a(customAlertDialogListener);
        this.w.show();
    }

    @Override
    public void c(MagicDataSource magicDataSource) {
        if (magicDataSource.i() == MagicDataSource.DataState.c) {
            return;
        }
        if (magicDataSource.i() == MagicDataSource.DataState.b) {
            this.d(true);
            return;
        }
        if (magicDataSource.i() == MagicDataSource.DataState.e) {
            ((OnboardingActivity)this.getActivity()).a((SongbookEntry)null);
            return;
        }
        this.c(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                OnboardingSongsFragment.this.s.f();
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                ((OnboardingActivity)OnboardingSongsFragment.this.getActivity()).a((SongbookEntry)null);
            }
        });
    }

    @UiThread
    protected void c(CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener) {
        if (!this.isAdded()) {
            return;
        }
        this.w = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297043), this.getString(2131297041));
        this.w.setCanceledOnTouchOutside(false);
        this.w.a(this.getString(2131297042), null);
        this.w.a(customAlertDialogListener);
        this.w.show();
    }

    @Override
    public void d(MagicDataSource magicDataSource) {
    }

    @Override
    public boolean d() {
        this.G();
        return true;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReferenceMonitor.a().a(this);
        SingAppboy.a().d();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.u = this.getArguments().getIntegerArrayList("BUNDLE_TOPIC_IDS");
        this.v = this.getArguments().getBoolean("BUNDLE_SEARCH_ENABLED");
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Click
    public void t() {
        this.G();
    }

    @Override
    public String x() {
        return g;
    }

    public static interface AdapterInterface {
        public Context a();

        public void a(ListingListItem var1, SongbookEntry var2, int var3);
    }

    public class OnboardingSongsAdapter
    extends MagicAdapter {
        AdapterInterface c;

        public OnboardingSongsAdapter(MagicDataSource magicDataSource, AdapterInterface adapterInterface) {
            super(magicDataSource);
            this.c = adapterInterface;
        }

        @Override
        public View a(ViewGroup viewGroup, int n) {
            return ListingListItem.a(this.c.a());
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public void a(View object, int n, int n2) {
            object = (ListingListItem)object;
            SongbookEntry songbookEntry = (SongbookEntry)this.a(n);
            boolean bl = n == 0;
            object.a(songbookEntry, bl);
            object.d();
            this.c.a((ListingListItem)object, songbookEntry, n);
        }
    }

    private class OnboardingSongsAdapterInterface
    implements AdapterInterface {
        private OnboardingSongsAdapterInterface() {
        }

        @Override
        public Context a() {
            return OnboardingSongsFragment.this.getActivity();
        }

        @Override
        public void a(ListingListItem listingListItem, SongbookEntry object, int n) {
            View.OnClickListener onClickListener = new View.OnClickListener((SongbookEntry)object, n){
                final /* synthetic */ SongbookEntry a;
                final /* synthetic */ int b;
                {
                    this.a = songbookEntry;
                    this.b = n;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View object) {
                    OnboardingSongsFragment.this.x = this.a;
                    SingAnalytics.a((SongbookEntry)this.a, Analytics.c);
                    SingAnalytics.a((int)this.b, (SongbookEntry)this.a);
                    String string2 = this.a.c();
                    object = this.a instanceof ArrangementVersionLiteEntry ? "ARR" : "SONG";
                    OnboardingSongsFragment.this.a(string2, (String)object);
                    OnboardingSongsFragment.this.d(false);
                    OnboardingSongsFragment.this.H();
                    ((OnboardingActivity)OnboardingSongsFragment.this.getActivity()).a(OnboardingSongsFragment.this.x);
                }
            };
            object = new View.OnClickListener((SongbookEntry)object, listingListItem){
                final /* synthetic */ SongbookEntry a;
                final /* synthetic */ ListingListItem b;
                {
                    this.a = songbookEntry;
                    this.b = listingListItem;
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View view) {
                    boolean bl = true;
                    if (!(this.a instanceof ArrangementVersionLiteEntry)) {
                        com.smule.android.logging.Analytics.a("pickasong", SongbookEntry.b(this.a), this.a.i(), this.a.r(), null, SongbookEntry.a(this.a));
                        OnboardingSongsFragment.this.a(this.a);
                        return;
                    }
                    if (this.b.t()) {
                        return;
                    }
                    this.b.setAlbumArtClickedState(true);
                    if (OnboardingSongsFragment.this.y == null) {
                        OnboardingSongsFragment.this.y = new ConcurrentHashMap();
                    }
                    if (OnboardingSongsFragment.this.y.containsKey(this.a.c())) {
                        OnboardingSongsFragment.this.a((Boolean)OnboardingSongsFragment.this.y.get(this.a.c()), (ArrangementVersionLiteEntry)this.a, this.b);
                        return;
                    }
                    int n = ((ArrangementVersionLiteEntry)this.a).a().removalCode;
                    if (n < 40 || n > 49) {
                        bl = false;
                    }
                    OnboardingSongsFragment.this.y.put(this.a.c(), bl);
                    OnboardingSongsFragment.this.a(bl, (ArrangementVersionLiteEntry)this.a, this.b);
                }
            };
            listingListItem.setOnClickListener(onClickListener);
            listingListItem.setAlbumArtClickListener((View.OnClickListener)object);
        }

    }

}

