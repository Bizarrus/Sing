/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.profile.BasePerformancesAdapter;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView;

public class FavoritesAdapter
extends BasePerformancesAdapter {
    private final String c = FavoritesAdapter.class.getSimpleName();
    private ProfileListView d;

    public FavoritesAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
        super(profileListView, magicDataSource);
        this.d = profileListView;
        this.e(2130903273);
    }

    static /* synthetic */ ProfileListView a(FavoritesAdapter favoritesAdapter) {
        return favoritesAdapter.d;
    }

    @Override
    public View a(ViewGroup viewGroup, int n) {
        return PerformanceListItem.c(this.d.getContext());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(View object, int n, int n2) {
        PerformanceListItem performanceListItem = (PerformanceListItem)object;
        PerformanceV2 performanceV2 = ((PerformanceListItemContainer)this.a(n)).a();
        object = performanceV2 != null ? this.d.e.c(performanceV2.performanceKey) : FileUploaderService.VideoUploadStatus.e;
        boolean bl = n == 0;
        boolean bl2 = performanceV2 != null && performanceV2.video;
        performanceListItem.setRecordingType(bl2);
        performanceListItem.setPerformance(performanceV2);
        performanceListItem.setVideoStatus((FileUploaderService.VideoUploadStatus)((Object)object));
        performanceListItem.setIsFirstElement(bl);
        performanceListItem.setListener(this.d.e.am());
        performanceListItem.setCancelVideoUploadHandler(this.d.e.a(performanceV2));
        if (bl) {
            performanceListItem.setHeaderTextAndShowHeader(this.j());
        } else {
            performanceListItem.e();
        }
        this.a(performanceV2, (FileUploaderService.VideoUploadStatus)((Object)object));
        if (object == FileUploaderService.VideoUploadStatus.d) {
            this.d.e.b(performanceV2);
        }
    }

    @Override
    protected void a(View view, int n, boolean bl) {
        super.a(view, n, bl);
    }

    @Override
    public View d(ViewGroup viewGroup) {
        return this.a(null, new View.OnClickListener(){

            public void onClick(View view) {
                ((MasterActivity)FavoritesAdapter.a((FavoritesAdapter)FavoritesAdapter.this).e.getActivity()).v();
            }
        });
    }

    @Override
    protected int h() {
        return 3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected boolean i() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.h() != 1) return bl2;
        int n = 0;
        do {
            bl2 = bl;
            if (n >= this.a().k()) return bl2;
            if (!((PerformanceListItemContainer)this.a((int)n)).d) {
                return true;
            }
            ++n;
        } while (true);
    }

    @Override
    protected String j() {
        return this.d.getResources().getQuantityString(2131361802, this.d.e.Q(), new Object[]{this.d.e.ak().a(this.d.e.Q(), this.d.getResources().getInteger(2131623962))});
    }

}

