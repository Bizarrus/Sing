/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.StateListDrawable
 *  android.support.v4.content.ContextCompat
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  com.smule.singandroid.utils.LayoutUtils
 */
package com.smule.singandroid.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.boost.BoostOpenCallListItemView;
import com.smule.singandroid.list_items.OpenCallListItem;
import com.smule.singandroid.profile.BasePerformancesAdapter;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.profile.ProfileListView;
import com.smule.singandroid.profile.ProfileUserInfo;
import com.smule.singandroid.profile.Theme;
import com.smule.singandroid.utils.LayoutUtils;

public class InvitesAdapter
extends BasePerformancesAdapter {
    private final String c = InvitesAdapter.class.getSimpleName();
    private ProfileListView d;
    private int e;

    public InvitesAdapter(ProfileListView profileListView, MagicDataSource magicDataSource) {
        super(profileListView, magicDataSource);
        this.d = profileListView;
        this.e = ContextCompat.getColor((Context)profileListView.getContext(), (int)Theme.a.b());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean j(int n) {
        boolean bl = true;
        boolean bl2 = ((PerformanceListItemContainer)this.a((int)n)).d;
        if (n == 0) {
            if (bl2) return false;
            return true;
        }
        if (!((PerformanceListItemContainer)this.a((int)(n - 1))).d) return false;
        if (bl2) return false;
        return bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String l() {
        int n;
        Resources resources = this.d.getResources();
        if (this.d.e.O()) {
            n = 2131361831;
            do {
                return resources.getQuantityString(n, this.d.e.S(), new Object[]{this.d.e.ak().a(this.d.e.S(), this.d.getResources().getInteger(2131623962))});
                break;
            } while (true);
        }
        n = 2131361830;
        return resources.getQuantityString(n, this.d.e.S(), new Object[]{this.d.e.ak().a(this.d.e.S(), this.d.getResources().getInteger(2131623962))});
    }

    private String m() {
        return this.d.getResources().getQuantityString(2131361792, this.d.e.T(), new Object[]{this.d.e.ak().a(this.d.e.T(), this.d.getResources().getInteger(2131623962))});
    }

    @Override
    public View a(ViewGroup viewGroup, int n) {
        if (n == 2) {
            return BoostOpenCallListItemView.a(this.d.getContext());
        }
        return OpenCallListItem.c(this.d.getContext());
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(View object, int n, int n2) {
        boolean bl = true;
        OpenCallListItem openCallListItem = (OpenCallListItem)object;
        PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer)this.a(n);
        openCallListItem.setBackgroundColor(-1);
        PerformanceV2 performanceV2 = performanceListItemContainer.a();
        object = performanceV2 != null ? this.d.e.c(performanceV2.performanceKey) : FileUploaderService.VideoUploadStatus.e;
        boolean bl2 = this.d.e.O() && !performanceListItemContainer.d;
        openCallListItem.setHideBookmarkOption(bl2);
        bl2 = performanceV2 != null && performanceV2.video;
        openCallListItem.setRecordingType(bl2);
        bl2 = n == 0 || this.j(n);
        openCallListItem.a(performanceV2, bl2);
        openCallListItem.setVideoStatus((FileUploaderService.VideoUploadStatus)((Object)object));
        openCallListItem.setExpandedPerformanceListener(this.d.e.an());
        openCallListItem.setCancelVideoUploadHandler(this.d.e.a(performanceV2));
        bl2 = this.d.e.F().accountId == UserManager.a().f() && performanceListItemContainer.a().p() && performanceListItemContainer.a().n() ? bl : false;
        openCallListItem.a(bl2);
        openCallListItem.k();
        this.a(performanceV2, (FileUploaderService.VideoUploadStatus)((Object)object));
        if (object == FileUploaderService.VideoUploadStatus.d) {
            this.d.e.b(performanceV2);
        }
        openCallListItem.setIsBookmarkItem(performanceListItemContainer.d);
        if (n == 0 && performanceListItemContainer.d) {
            openCallListItem.a(this.m(), -1, 2130837973);
        } else if (this.j(n)) {
            object = this.l();
            n = n == 0 ? -1 : this.d.getResources().getColor(2131689770);
            openCallListItem.a((String)object, n, 0);
        } else {
            openCallListItem.v();
        }
        openCallListItem.a(this.k(), this.k());
        openCallListItem.a(this.d.e.u.e());
    }

    @Override
    public int c(int n) {
        PerformanceListItemContainer performanceListItemContainer = (PerformanceListItemContainer)this.a(n);
        boolean bl = new SingServerValues().ac();
        if (performanceListItemContainer == null || performanceListItemContainer.a() == null || !performanceListItemContainer.a().boost || !bl) {
            return 1;
        }
        return 2;
    }

    @Override
    public View d(ViewGroup viewGroup) {
        return this.a(null, this.d.j);
    }

    @Override
    public int e() {
        return 2;
    }

    @Override
    protected int h() {
        return 1;
    }

    @Override
    protected String j() {
        Log.e(this.c, "getHeaderText called in InviteAdapter");
        return null;
    }

    public Drawable k() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float)LayoutUtils.a((int)3, (Context)this.d.getContext()));
        gradientDrawable.setColor(this.e);
        Drawable drawable2 = ContextCompat.getDrawable((Context)this.d.getContext(), (int)2130837676);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842910, 16843518}, drawable2);
        stateListDrawable.addState(new int[]{16842910}, (Drawable)gradientDrawable);
        stateListDrawable.addState(new int[]{-16842910}, drawable2);
        return stateListDrawable.mutate();
    }
}

