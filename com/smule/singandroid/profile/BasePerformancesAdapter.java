/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.DataSetObservable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObservable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.PerformancesAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView;
import com.smule.singandroid.profile.ProfileUserInfo;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

public abstract class BasePerformancesAdapter
extends MagicAdapter {
    private final ProfileListView c;
    private final String d = BasePerformancesAdapter.class.getSimpleName();
    private Set<String> e = new HashSet<String>();

    public BasePerformancesAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
        super(magicDataSource);
        this.c = profileListView;
        this.b();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected View a(ViewGroup viewGroup, View.OnClickListener object) {
        int n = !this.c.e.K() || !this.c.e.Y() || MagicPreferences.b(this.c.getContext(), "PINNED_PERFORMANCE_CTA_CLOSED", false) ? 1 : 0;
        viewGroup = viewGroup != null && n == 0 ? this.e(viewGroup) : null;
        PerformanceListEmptyListItem performanceListEmptyListItem = PerformanceListEmptyListItem.a((Context)this.c.e.getActivity());
        n = this.h();
        boolean bl = this.c.e.F().accountId == UserManager.a().f();
        performanceListEmptyListItem.a(n, bl, this.c.e.F().handle, (View.OnClickListener)object, this.c.e.u.e());
        object = new LinearLayout.LayoutParams(-1, -2);
        n = this.c.getResources().getDimensionPixelOffset(2131428173);
        performanceListEmptyListItem.setPadding(0, n, 0, this.c.getResources().getDimensionPixelOffset(2131427818) + n);
        performanceListEmptyListItem.setLayoutParams((ViewGroup.LayoutParams)object);
        object = performanceListEmptyListItem;
        if (viewGroup != null) {
            object = new LinearLayout(this.c.getContext());
            object.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
            object.setOrientation(1);
            object.addView((View)viewGroup);
            object.addView((View)performanceListEmptyListItem);
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(PerformanceV2 object, FileUploaderService.VideoUploadStatus object2) {
        if (object == null || object2 != FileUploaderService.VideoUploadStatus.b || System.currentTimeMillis() / 1000 - (long)object.createdAt <= 120 || this.e.contains(object.performanceKey)) {
            return;
        }
        Log.b(this.d, "triggerRerender");
        this.e.add(object.performanceKey);
        object2 = PerformanceManager.a();
        String string2 = object.performanceKey;
        object = object.video ? PerformancesAPI.VIDEO : PerformancesAPI.MAIN;
        object2.a(string2, (Object)object, new NetworkResponseCallback(){

            @Override
            public void handleResponse(NetworkResponse networkResponse) {
            }
        });
    }

    @Override
    public boolean a(View view) {
        return true;
    }

    @Override
    public void b() {
        super.b();
        if (this.c != null && this.c.e != null && this.c.e.isAdded() && this.a().i() != MagicDataSource.DataState.c) {
            this.c.e.a(this.h(), this.i());
        }
    }

    @Override
    public void c(MagicDataSource magicDataSource) {
        super.c(magicDataSource);
        if (this.c.e != null && this.c.e.isAdded() && this.a().i() != MagicDataSource.DataState.c) {
            this.c.e.a(this.h(), this.i());
        }
    }

    protected View e(ViewGroup viewGroup) {
        viewGroup = LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903353, viewGroup, false);
        viewGroup.setTag((Object)"CTA_VIEW");
        TextView textView = (TextView)viewGroup.findViewById(2131756262);
        final View view = viewGroup.findViewById(2131756263);
        textView.setTextColor(this.c.e.ar());
        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view2) {
                ((MasterActivity)BasePerformancesAdapter.a((BasePerformancesAdapter)BasePerformancesAdapter.this).e.getActivity()).O();
                view.performClick();
            }
        });
        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                MagicPreferences.a(BasePerformancesAdapter.this.c.getContext(), "PINNED_PERFORMANCE_CTA_CLOSED", true);
                BasePerformancesAdapter.this.b.notifyChanged();
            }
        });
        return viewGroup;
    }

    protected abstract int h();

    protected boolean i() {
        if (this.d() > 0) {
            return true;
        }
        return false;
    }

    protected abstract String j();

}

