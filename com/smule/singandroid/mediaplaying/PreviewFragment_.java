/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.SeekBar
 *  android.widget.TextView
 *  com.smule.singandroid.utils.FractionalRelativeLayout
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.mediaplaying;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.mediaplaying.PreviewFragment;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreviewFragment_
extends PreviewFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier ac = new OnViewChangedNotifier();
    private View ad;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.ab();
        this.b(bundle);
    }

    public static FragmentBuilder_ aa() {
        return new FragmentBuilder_();
    }

    private void ab() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            if (bundle.containsKey("mAutoPlay")) {
                this.B = bundle.getBoolean("mAutoPlay");
            }
            if (bundle.containsKey("mNavigationMenuShowing")) {
                this.C = bundle.getBoolean("mNavigationMenuShowing");
            }
            if (bundle.containsKey("mMediaPlaybackPresenterPlaylistIndex")) {
                this.D = bundle.getInt("mMediaPlaybackPresenterPlaylistIndex");
            }
            if (bundle.containsKey("mEntry")) {
                this.P = (SongbookEntry)bundle.getParcelable("mEntry");
            }
            if (bundle.containsKey("mHasOriginSource")) {
                this.Q = bundle.getBoolean("mHasOriginSource");
            }
            if (bundle.containsKey("mSearchTarget")) {
                this.R = (Object)bundle.getSerializable("mSearchTarget");
            }
        }
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.B = bundle.getBoolean("mAutoPlay");
        this.C = bundle.getBoolean("mNavigationMenuShowing");
    }

    @Override
    protected void G() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PreviewFragment_.super.G();
            }
        }, (long)0);
    }

    @Override
    protected void H() {
        BackgroundExecutor.a();
        PreviewFragment_.super.H();
    }

    @Override
    public void W() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PreviewFragment_.super.W();
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        PreviewFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.g = (CustomToolbar)hasViews.findViewById(2131755449);
        this.h = (SeekBar)hasViews.findViewById(2131756152);
        this.i = (IconFontView)hasViews.findViewById(2131756278);
        this.j = (IconFontView)hasViews.findViewById(2131756279);
        this.k = (IconFontView)hasViews.findViewById(2131756277);
        this.l = hasViews.findViewById(2131756087);
        this.m = hasViews.findViewById(2131756089);
        this.n = (TextView)hasViews.findViewById(2131756094);
        this.o = hasViews.findViewById(2131756153);
        this.p = (IconFontView)hasViews.findViewById(2131755958);
        this.q = (IconFontView)hasViews.findViewById(2131755953);
        this.r = (IconFontView)hasViews.findViewById(2131755954);
        this.s = hasViews.findViewById(2131755955);
        this.t = (TextView)hasViews.findViewById(2131755956);
        this.u = (TextView)hasViews.findViewById(2131755957);
        this.v = (ProgressBar)hasViews.findViewById(2131755951);
        this.w = (RippleBackground)hasViews.findViewById(2131756366);
        this.y = hasViews.findViewById(2131755950);
        this.z = hasViews.findViewById(2131756082);
        this.A = (FractionalRelativeLayout)hasViews.findViewById(2131756081);
        this.S = (ImageView)hasViews.findViewById(2131755761);
        this.T = (ImageView)hasViews.findViewById(2131756237);
        this.U = (ImageView)hasViews.findViewById(2131756367);
        this.V = (TextView)hasViews.findViewById(2131756373);
        this.W = (ProgressBar)hasViews.findViewById(2131756370);
        this.X = (TextView)hasViews.findViewById(2131756372);
        this.Y = (UserFollowListItem)hasViews.findViewById(2131756368);
        this.Z = hasViews.findViewById(2131756369);
        this.aa = (ImageView)hasViews.findViewById(2131756371);
        this.ab = (Button)hasViews.findViewById(2131756374);
        if ((hasViews = hasViews.findViewById(2131756365)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PreviewFragment_.this.a(view);
                }
            });
        }
        if (this.ab != null) {
            this.ab.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    PreviewFragment_.this.Y();
                }
            });
        }
        this.O();
        this.V();
    }

    @Override
    protected void c(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                PreviewFragment_.super.c(bl);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.ad == null) {
            return null;
        }
        return this.ad.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.ac);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ad = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.ad == null) {
            this.ad = layoutInflater.inflate(2130903370, viewGroup, false);
        }
        return this.ad;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.ad = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.S = null;
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = null;
        this.X = null;
        this.Y = null;
        this.Z = null;
        this.aa = null;
        this.ab = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mAutoPlay", this.B);
        bundle.putBoolean("mNavigationMenuShowing", this.C);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.ac.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, PreviewFragment> {
        public PreviewFragment a() {
            PreviewFragment_ previewFragment_ = new PreviewFragment_();
            previewFragment_.setArguments(this.a);
            return previewFragment_;
        }

        public FragmentBuilder_ a(Analytics searchTarget) {
            this.a.putSerializable("mSearchTarget", (Serializable)((Object)searchTarget));
            return this;
        }

        public FragmentBuilder_ a(SongbookEntry songbookEntry) {
            this.a.putParcelable("mEntry", (Parcelable)songbookEntry);
            return this;
        }

        public FragmentBuilder_ a(boolean bl) {
            this.a.putBoolean("mNavigationMenuShowing", bl);
            return this;
        }

        public FragmentBuilder_ b(boolean bl) {
            this.a.putBoolean("mHasOriginSource", bl);
            return this;
        }
    }

}

