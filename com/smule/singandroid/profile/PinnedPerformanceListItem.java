/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.MiscUtils
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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.profile.PinnedPerformanceListItem_;
import com.smule.singandroid.profile.WhatsPinnedDialog;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.concurrent.Future;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PinnedPerformanceListItem
extends PerformanceListItem {
    @ViewById
    protected ImageView a;
    @ViewById
    protected TextView b;
    private String p;
    private boolean q;
    private WhatsPinnedDialog r;

    public PinnedPerformanceListItem(Context context) {
        super(context);
    }

    public static PinnedPerformanceListItem a(Context object, boolean bl, WhatsPinnedDialog whatsPinnedDialog) {
        object = PinnedPerformanceListItem_.a((Context)object);
        object.q = bl;
        object.r = whatsPinnedDialog;
        return object;
    }

    @Override
    protected void d() {
    }

    @Override
    protected void f() {
    }

    @Override
    protected void j() {
        if (this.D.coverUrl != null && !this.D.coverUrl.isEmpty()) {
            if (this.p == null || !this.p.equals(this.D.coverUrl)) {
                this.p = this.D.coverUrl;
                this.o.a(this.p, this.a, 2130837860);
            }
            return;
        }
        this.p = null;
        this.ae.a.setImageResource(2130837600);
    }

    @Click
    @Override
    protected void k() {
        if (this.q) {
            OptionsMenu.Builder builder = new OptionsMenu.Builder();
            builder.a(new MenuOption(this.getContext(), 2131297618, new UnpinOnClickListener()));
            builder.a(new MenuOption(this.getContext(), 2131296672, new MenuOption.OnClickListener(){

                @Override
                public void a(OptionsMenu optionsMenu) {
                    optionsMenu.b();
                }
            }));
            builder.a(this.getContext()).a();
            return;
        }
        this.r.show();
    }

    @Override
    public void setPerformance(PerformanceV2 performanceV2) {
        super.setPerformance(performanceV2);
        if (this.D.message != null && this.D.message.trim().length() > 0) {
            this.b.setVisibility(0);
            this.b.setText((CharSequence)this.D.message.trim());
            return;
        }
        this.b.setVisibility(8);
    }

    private class UnpinOnClickListener
    implements MenuOption.OnClickListener {
        final String a;

        private UnpinOnClickListener() {
            this.a = "";
        }

        @Override
        public void a(OptionsMenu object) {
            SingAnalytics.a((String)null, (String)null, (SingAnalytics.ProfileCustomizationFeature[])new SingAnalytics.ProfileCustomizationFeature[0]);
            object = new BusyDialog((Activity)((MediaPlayingActivity)MiscUtils.a((View)PinnedPerformanceListItem.this)), PinnedPerformanceListItem.this.getResources().getString(2131297373));
            object.show();
            UserManager.a().a(null, null, "", null, new NetworkResponseCallback((BusyDialog)((Object)object)){
                final /* synthetic */ BusyDialog a;
                {
                    this.a = busyDialog;
                }

                @Override
                public void handleResponse(NetworkResponse object) {
                    if (!object.c()) {
                        object = new TextAlertDialog(PinnedPerformanceListItem.this.getContext(), 2131297620, 2131297619, true, false);
                        object.a(2131296705, 0);
                        object.show();
                    }
                    this.a.dismiss();
                    NotificationCenter.a().b("PERFORMANCE_UPDATED_NOTIFICATION", "PINNED_PERFORMANCE", null);
                }
            });
        }

    }

}

