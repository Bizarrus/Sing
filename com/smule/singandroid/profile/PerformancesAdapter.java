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
import android.widget.TextView;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.profile.BasePerformancesAdapter;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.profile.PinnedPerformanceListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.profile.WhatsPinnedDialog;

public class PerformancesAdapter
extends BasePerformancesAdapter {
    public static final String c = PerformancesAdapter.class.getName();
    private final PerformanceV2 d;
    private ProfileListView e;
    private boolean f;

    /*
     * Enabled aggressive block sorting
     */
    PerformancesAdapter(ProfileListView object, MagicDataSource<PerformanceListItemContainer, MagicDataSource.OffsetPaginationTracker> magicDataSource) {
        boolean bl = false;
        super((ProfileListView)object, magicDataSource);
        this.e = object;
        object = this.e.e.X().d();
        this.d = object != null ? object.pinPerformanceIcon : null;
        if (this.d != null) {
            this.f = true;
            if (this.e.e.O()) {
                MagicPreferences.a(this.e.getContext(), "PINNED_PERFORMANCE_CTA_CLOSED", true);
            }
            return;
        }
        if (!this.e.e.K() || !this.e.e.Y() || MagicPreferences.b(this.e.getContext(), "PINNED_PERFORMANCE_CTA_CLOSED", false)) {
            bl = true;
        }
        this.f = bl;
    }

    @Override
    public View a(ViewGroup object, int n) {
        boolean bl = false;
        LayoutInflater layoutInflater = LayoutInflater.from((Context)this.e.getContext());
        switch (n) {
            default: {
                boolean bl2 = this.e.e.O();
                object = this.e.getContext();
                if (this.d != null) {
                    bl = true;
                }
                return PerformanceListItem.a((Context)object, bl2, bl);
            }
            case 1: {
                object = layoutInflater.inflate(2130903353, (ViewGroup)object, false);
                object.setTag((Object)"CTA_VIEW");
                layoutInflater = (TextView)object.findViewById(2131756262);
                final View view = object.findViewById(2131756263);
                layoutInflater.setTextColor(this.e.e.ar());
                layoutInflater.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view2) {
                        ((MasterActivity)PerformancesAdapter.a((PerformancesAdapter)PerformancesAdapter.this).e.getActivity()).O();
                        view.performClick();
                    }
                });
                view.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        PerformancesAdapter.this.f = true;
                        MagicPreferences.a(PerformancesAdapter.this.e.getContext(), "PINNED_PERFORMANCE_CTA_CLOSED", true);
                        PerformancesAdapter.this.b.notifyChanged();
                    }
                });
                return object;
            }
            case 2: 
        }
        bl = SubscriptionManager.a().b();
        boolean bl3 = this.e.e.O();
        object = new WhatsPinnedDialog(this.e.e, bl);
        return PinnedPerformanceListItem.a(this.e.getContext(), bl3, (WhatsPinnedDialog)((Object)object));
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(View object, int n, int n2) {
        block13 : {
            block8 : {
                boolean bl;
                boolean bl2;
                PerformanceListItem performanceListItem;
                block10 : {
                    block12 : {
                        block11 : {
                            block9 : {
                                bl2 = true;
                                if (n2 == 1) break block8;
                                performanceListItem = (PerformanceListItem)object;
                                if (n2 != 2) break block9;
                                object = this.d;
                                break block10;
                            }
                            if (!this.f) break block11;
                            n2 = n;
                            if (this.d == null) break block12;
                        }
                        n2 = n - 1;
                    }
                    object = ((PerformanceListItemContainer)this.a(n2)).a();
                    bl = n2 == 0;
                    performanceListItem.setIsFirstElement(bl);
                    if (bl) {
                        performanceListItem.setHeaderTextAndShowHeader(this.j());
                    } else {
                        performanceListItem.e();
                    }
                }
                FileUploaderService.VideoUploadStatus videoUploadStatus = object != null ? this.e.e.c(object.performanceKey) : FileUploaderService.VideoUploadStatus.e;
                bl = object != null && object.video ? bl2 : false;
                performanceListItem.setRecordingType(bl);
                performanceListItem.setPerformance((PerformanceV2)object);
                performanceListItem.setVideoStatus(videoUploadStatus);
                performanceListItem.setListener(this.e.e.am());
                performanceListItem.setCancelVideoUploadHandler(this.e.e.a((PerformanceV2)object));
                this.a((PerformanceV2)object, videoUploadStatus);
                if (videoUploadStatus == FileUploaderService.VideoUploadStatus.d) break block13;
            }
            return;
        }
        this.e.e.b((PerformanceV2)object);
    }

    boolean b(String string2) {
        boolean bl = false;
        int n = 0;
        do {
            block4 : {
                boolean bl2;
                block3 : {
                    bl2 = bl;
                    if (n >= this.k()) break block3;
                    if (!((PerformanceListItemContainer)this.a((int)this.e.f)).a().performanceKey.equals(string2)) break block4;
                    bl2 = true;
                }
                return bl2;
            }
            ++n;
        } while (true);
    }

    @Override
    public int c(int n) {
        if (n == 0) {
            if (this.d != null) {
                return 2;
            }
            if (!this.f) {
                return 1;
            }
        }
        return 3;
    }

    @Override
    public int d() {
        int n;
        block3 : {
            int n2;
            block2 : {
                n2 = super.d();
                if (this.d != null) break block2;
                n = n2;
                if (this.f) break block3;
            }
            n = n2 + 1;
        }
        return n;
    }

    @Override
    public View d(ViewGroup viewGroup) {
        return this.a(viewGroup, this.e.j);
    }

    @Override
    public int e() {
        return 3;
    }

    @Override
    protected int h() {
        return 0;
    }

    @Override
    protected String j() {
        return this.e.getResources().getQuantityString(2131361835, this.e.e.R(), new Object[]{this.e.e.ak().a(this.e.e.R(), this.e.getResources().getInteger(2131623962))});
    }

    int k() {
        return super.d();
    }

    View l() {
        if (!this.f) {
            return this.e.findViewWithTag((Object)"CTA_VIEW");
        }
        return null;
    }

}

