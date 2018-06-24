/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.text.Html
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ProfileCustomizationFeature
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.list_items.VideoUploadingView;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.PerformanceListItem_;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerformanceListItem
extends VideoUploadingView
implements PerformanceItemInterface {
    private static final String a = PerformanceListItem.class.getName();
    private PerformanceItemInterface.PerformanceItemListener b;
    @ViewById
    protected View c;
    @ViewById
    protected TextView d;
    @ViewById
    protected TextView e;
    @ViewById
    protected TextView f;
    @ViewById
    protected TextView g;
    @ViewById
    protected TextView h;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ProfileImageWithVIPBadge k;
    @ViewById
    protected TextView l;
    @ViewById
    protected View m;
    @ViewById
    protected View n;
    protected ImageUtils o = new Object(){
        public String a;

        public void a() {
            this.a = null;
        }

        public void a(ImageView imageView) {
            com.nostra13.universalimageloader.core.ImageLoader.a().a(imageView);
        }

        public boolean a(String string2, ImageView imageView, int n) {
            return this.a(string2, imageView, n, false);
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl);
                this.a = string2;
                return true;
            }
            return false;
        }

        public boolean a(String string2, ImageView imageView, int n, boolean bl, int n2) {
            if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl, -1, n2, null, true);
                this.a = string2;
                return true;
            }
            return false;
        }
    };
    private String p = null;
    private boolean q = false;
    private BaseFragment r;
    private LocalizedShortNumberFormatter s;
    private boolean t;
    private boolean u;
    private boolean v;

    public PerformanceListItem(Context context) {
        super(context);
    }

    public static PerformanceListItem a(Context object, boolean bl, boolean bl2) {
        object = PerformanceListItem_.a((Context)object);
        object.u = bl;
        object.v = bl2;
        return object;
    }

    public static PerformanceListItem c(Context context) {
        return PerformanceListItem.a(context, false, false);
    }

    static /* synthetic */ PerformanceV2 e(PerformanceListItem performanceListItem) {
        return performanceListItem.D;
    }

    static /* synthetic */ PerformanceV2 g(PerformanceListItem performanceListItem) {
        return performanceListItem.D;
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.s == null) {
            this.s = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.s;
    }

    static /* synthetic */ PerformanceV2 h(PerformanceListItem performanceListItem) {
        return performanceListItem.D;
    }

    static /* synthetic */ PerformanceV2 i(PerformanceListItem performanceListItem) {
        return performanceListItem.D;
    }

    private void v() {
        this.ae.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (PerformanceListItem.this.b != null) {
                    PerformanceListItem.this.b.b(PerformanceListItem.this, PerformanceListItem.this.D);
                }
            }
        });
        this.c.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                block3 : {
                    block2 : {
                        if (PerformanceListItem.this.b == null) break block2;
                        if (PerformanceListItem.this.D == null) break block3;
                        PerformanceListItem.this.b.a((MediaPlayingViewInterface)PerformanceListItem.this, PerformanceListItem.this.D);
                    }
                    return;
                }
                Log.d(a, "configureSharedClickListeners - mOpenCall and mPerformance are both null!");
            }
        });
    }

    public void a(boolean bl, BaseFragment baseFragment) {
        this.q = bl;
        this.r = baseFragment;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void b(PerformanceV2 performanceV2) {
        int n = 0;
        if (this.l == null) return;
        int n2 = performanceV2.seed && performanceV2.e() && performanceV2.childCount > 0 ? 1 : 0;
        if (n2 != 0) {
            this.l.setVisibility(0);
            this.l.setText((CharSequence)this.getLocalizedFormatter().a(performanceV2.childCount, this.getResources().getInteger(2131623962)));
        } else {
            this.l.setVisibility(8);
        }
        performanceV2 = this.n;
        n2 = this.u && n2 == 0 ? n : 8;
        performanceV2.setVisibility(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c() {
        TextView textView = this.d;
        String string2 = this.D.title != null ? this.D.title : "";
        textView.setText((CharSequence)string2);
        this.f();
        this.j();
        this.i();
        this.h();
        this.g();
    }

    protected void d() {
        block3 : {
            block2 : {
                if (this.k == null) break block2;
                if (!this.q || this.D.recentTracks.size() <= 0) break block3;
                AccountIcon accountIcon = this.D.recentTracks.get((int)0).accountIcon;
                this.k.setVisibility(0);
                this.k.a(this.r, accountIcon);
            }
            return;
        }
        this.k.setVisibility(8);
    }

    public void e() {
        this.i.setVisibility(8);
        this.setIsFirstElement(this.t);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void f() {
        this.e.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.D, (boolean)true));
        TextView textView = this.e;
        int n = this.D.isPrivate ? 2130837973 : 0;
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, n, 0);
    }

    protected void g() {
        this.h.setText((CharSequence)MiscUtils.a((long)this.D.createdAt, (boolean)true, (boolean)false));
    }

    protected void h() {
        this.f.setText((CharSequence)this.getLocalizedFormatter().a(this.D.totalLoves, this.getResources().getInteger(2131623962)));
    }

    protected void i() {
        if (this.g != null) {
            this.g.setText((CharSequence)this.getLocalizedFormatter().a(this.D.totalListens, this.getResources().getInteger(2131623962)));
        }
    }

    protected void j() {
        if (this.D.coverUrl != null && !this.D.coverUrl.isEmpty()) {
            if (this.p == null || !this.p.equals(this.D.coverUrl)) {
                this.p = this.D.coverUrl;
                com.smule.android.utils.ImageUtils.a(this.p, this.ae.a, 2130837897);
            }
            return;
        }
        this.p = null;
        this.ae.a.setImageResource(2130837600);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void k() {
        final MediaPlayingActivity mediaPlayingActivity = (MediaPlayingActivity)MiscUtils.a((View)this);
        OptionsMenu.Builder builder = new OptionsMenu.Builder();
        if (SubscriptionManager.a().b()) {
            builder.a(new MenuOption(this.getContext(), 2131297610, new PinOnClickListener()));
        } else {
            builder.a(new MenuOption(this.getContext(), 2131297617, new MenuOption.OnClickListener(){

                @Override
                public void a(OptionsMenu optionsMenu) {
                    mediaPlayingActivity.a(UpsellManager.a());
                    optionsMenu.b();
                }
            }));
        }
        builder.a(new MenuOption(this.getContext(), 2131296672, new MenuOption.OnClickListener(){

            @Override
            public void a(OptionsMenu optionsMenu) {
                optionsMenu.b();
            }
        }));
        builder.a(this.getContext()).a();
    }

    public void setHeaderTextAndShowHeader(String string2) {
        this.j.setText((CharSequence)string2);
        this.i.setVisibility(0);
        this.setIsFirstElement(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setIsFirstElement(boolean bl) {
        this.t = bl;
        View view = this.m;
        int n = bl ? 8 : 0;
        view.setVisibility(n);
    }

    @Override
    public void setListener(PerformanceItemInterface.PerformanceItemListener performanceItemListener) {
        this.b = performanceItemListener;
    }

    @Override
    public void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(a, "setPerformance passed a null performance!");
            return;
        }
        this.setTag((Object)performanceV2.performanceKey);
        this.D = performanceV2;
        this.c();
        Log.b(a, "setPerformance - performance key is: " + performanceV2.performanceKey + ", and loaded mediaKey is: " + MediaPlayerServiceController.a().i());
        this.d();
        this.b(performanceV2);
        this.a(this.D.performanceKey);
        this.v();
        this.ae.a(this.a(this.D), 2130838138);
    }

    private class PinOnClickListener
    implements MenuOption.OnClickListener {
        private PinOnClickListener() {
        }

        private void a() {
            Object object = Html.fromHtml((String)PerformanceListItem.this.getResources().getString(2131297601));
            object = new TextAlertDialog(PerformanceListItem.this.getContext(), null, (CharSequence)object, true, false);
            object.a(2131296691, 0);
            object.show();
        }

        private void b() {
            Object object = Html.fromHtml((String)PerformanceListItem.this.getResources().getString(2131297613));
            object = new TextAlertDialog(PerformanceListItem.this.getContext(), null, (CharSequence)object, true, true);
            object.a(2131297612, 2131296672);
            object.a(new CustomAlertDialog.CustomAlertDialogListener(){

                @Override
                public void a(CustomAlertDialog customAlertDialog) {
                    PinOnClickListener.this.c();
                }

                @Override
                public void b(CustomAlertDialog customAlertDialog) {
                }
            });
            object.show();
        }

        private void c() {
            SingAnalytics.a((String)PerformanceListItem.g((PerformanceListItem)PerformanceListItem.this).performanceKey, (String)PerformanceListItem.h((PerformanceListItem)PerformanceListItem.this).songUid, (SingAnalytics.ProfileCustomizationFeature[])new SingAnalytics.ProfileCustomizationFeature[0]);
            final BusyDialog busyDialog = new BusyDialog((Activity)((MediaPlayingActivity)MiscUtils.a((View)PerformanceListItem.this)), PerformanceListItem.this.getResources().getString(2131297373));
            busyDialog.show();
            UserManager.a().a(null, null, PerformanceListItem.i((PerformanceListItem)PerformanceListItem.this).performanceKey, null, new NetworkResponseCallback(){

                @Override
                public void handleResponse(NetworkResponse object) {
                    if (!object.c()) {
                        object = new TextAlertDialog(PerformanceListItem.this.getContext(), 2131297620, 2131297619, true, false);
                        object.a(2131296705, 0);
                        object.show();
                    }
                    busyDialog.dismiss();
                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "PINNED_PERFORMANCE", PerformanceListItem.this.D);
                }
            });
        }

        @Override
        public void a(OptionsMenu optionsMenu) {
            optionsMenu.b();
            if (PerformanceListItem.e((PerformanceListItem)PerformanceListItem.this).isPrivate) {
                this.a();
                return;
            }
            if (PerformanceListItem.this.v) {
                this.b();
                return;
            }
            this.c();
        }

    }

}

