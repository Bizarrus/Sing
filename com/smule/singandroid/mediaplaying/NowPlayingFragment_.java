/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.design.widget.AppBarLayout
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageButton
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.ListView
 *  android.widget.ProgressBar
 *  android.widget.SeekBar
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.smule.singandroid.utils.FractionalRelativeLayout
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.mediaplaying;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.RippleBackground;
import com.smule.singandroid.customviews.SquareTextureView;
import com.smule.singandroid.hashtag.Hashtag;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.utils.FractionalRelativeLayout;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class NowPlayingFragment_
extends NowPlayingFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier bh = new OnViewChangedNotifier();
    private View bi;

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.ak();
        this.b(bundle);
    }

    public static FragmentBuilder_ aj() {
        return new FragmentBuilder_();
    }

    private void ak() {
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
            if (bundle.containsKey("mPerformance")) {
                this.aV = (PerformanceV2)bundle.getParcelable("mPerformance");
            }
            if (bundle.containsKey("mHasCustomMenu")) {
                this.aZ = bundle.getBoolean("mHasCustomMenu");
            }
            if (bundle.containsKey("mSearchTarget")) {
                this.ba = (Object)bundle.getSerializable("mSearchTarget");
            }
            if (bundle.containsKey("mMenuRes")) {
                this.bb = bundle.getInt("mMenuRes");
            }
        }
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.B = bundle.getBoolean("mAutoPlay");
        this.C = bundle.getBoolean("mNavigationMenuShowing");
        this.aW = bundle.getBoolean("mShowAllComments");
        this.aX = bundle.getBoolean("mLovedPerformance");
        this.aY = bundle.getString("mShareWebURL");
        this.bc = bundle.getBoolean("mSongIsVIPOnly");
        this.bd = bundle.getBoolean("mCloseAllOnBack");
    }

    @Override
    protected void G() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.G();
            }
        }, (long)0);
    }

    @Override
    protected void H() {
        BackgroundExecutor.a();
        NowPlayingFragment_.super.H();
    }

    @Override
    protected void W() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.W();
            }
        }, (long)0);
    }

    @Override
    public void Y() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.Y();
            }
        }, (long)0);
    }

    @Override
    protected void Z() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.Z();
            }
        }, (long)0);
    }

    @Override
    protected void a(final Activity activity, final String string2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.a(activity, string2);
            }
        }, (long)0);
    }

    @Override
    protected void a(final View view, final float f, final float f2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.a(view, f, f2);
            }
        }, (long)0);
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        NowPlayingFragment_.super.a(runTimePermissionsRequest, resultCallback);
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
        this.P = hasViews.findViewById(2131755305);
        this.Q = (TextView)hasViews.findViewById(2131755306);
        this.R = hasViews.findViewById(2131756103);
        this.S = hasViews.findViewById(2131756083);
        this.T = (TextView)hasViews.findViewById(2131756104);
        this.U = (ProgressBar)hasViews.findViewById(2131756105);
        this.V = (SNPImageView)hasViews.findViewById(2131755320);
        this.W = hasViews.findViewById(2131756090);
        this.X = (TextView)hasViews.findViewById(2131756091);
        this.Y = (TextView)hasViews.findViewById(2131756092);
        this.Z = hasViews.findViewById(2131756120);
        this.aa = hasViews.findViewById(2131756121);
        this.ab = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756122);
        this.ac = (TextView)hasViews.findViewById(2131756123);
        this.ad = hasViews.findViewById(2131756124);
        this.ae = hasViews.findViewById(2131756125);
        this.af = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756126);
        this.ag = (TextView)hasViews.findViewById(2131756127);
        this.ah = (LinearLayout)hasViews.findViewById(2131756119);
        this.ai = (TextView)hasViews.findViewById(2131756128);
        this.aj = (TextView)hasViews.findViewById(2131756129);
        this.ak = (ImageView)hasViews.findViewById(2131756118);
        this.al = hasViews.findViewById(2131756132);
        this.am = (TextView)hasViews.findViewById(2131756139);
        this.an = (TextView)hasViews.findViewById(2131756140);
        this.ao = (TextView)hasViews.findViewById(2131756141);
        this.ap = (TextView)hasViews.findViewById(2131756133);
        this.aq = (TextView)hasViews.findViewById(2131756134);
        this.ar = (TextView)hasViews.findViewById(2131756135);
        this.as = (TextView)hasViews.findViewById(2131756136);
        this.at = hasViews.findViewById(2131756144);
        this.au = hasViews.findViewById(2131756145);
        this.av = (ToggleButton)hasViews.findViewById(2131756147);
        this.ax = (AppBarLayout)hasViews.findViewById(2131756084);
        this.ay = (TextView)hasViews.findViewById(2131755325);
        this.az = (IconFontView)hasViews.findViewById(2131756113);
        this.aA = (IconFontView)hasViews.findViewById(2131756111);
        this.aB = (IconFontView)hasViews.findViewById(2131756108);
        this.aC = (IconFontView)hasViews.findViewById(2131756116);
        this.aD = (TextView)hasViews.findViewById(2131756109);
        this.aE = (TextView)hasViews.findViewById(2131756112);
        this.aF = (TextView)hasViews.findViewById(2131756114);
        this.aG = hasViews.findViewById(2131756095);
        this.aH = (CustomToolbar)hasViews.findViewById(2131756096);
        this.aI = (ListView)hasViews.findViewById(2131756098);
        this.aJ = (TextView)hasViews.findViewById(2131756097);
        this.aK = (ImageButton)hasViews.findViewById(2131756100);
        this.aL = hasViews.findViewById(2131755881);
        this.aM = (CustomToolbar)hasViews.findViewById(2131756102);
        this.aN = (ListView)hasViews.findViewById(2131755883);
        this.aO = (TextView)hasViews.findViewById(2131755882);
        this.aP = (ProfileImageWithVIPBadge)hasViews.findViewById(2131756099);
        this.aQ = hasViews.findViewById(2131756106);
        this.aR = hasViews.findViewById(2131756093);
        this.aS = (ProgressBar)hasViews.findViewById(2131756101);
        this.be = hasViews.findViewById(2131756085);
        this.bf = (SquareTextureView)hasViews.findViewById(2131756086);
        this.bg = hasViews.findViewById(2131756088);
        hasViews = hasViews.findViewById(2131755307);
        if (this.be != null) {
            this.be.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NowPlayingFragment_.this.a(view);
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    NowPlayingFragment_.this.ad();
                }
            });
        }
        this.O();
        this.ae();
    }

    @Override
    protected void a(final boolean bl, final int n) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.a(bl, n);
            }
        }, (long)0);
    }

    @Override
    public void a(final boolean bl, final boolean bl2, final Hashtag.HashtagCallback hashtagCallback) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.a(bl, bl2, hashtagCallback);
            }
        }, (long)0);
    }

    @Override
    public void aa() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.aa();
            }
        }, (long)0);
    }

    @Override
    protected void ac() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                if (NowPlayingFragment_.this.getActivity() != null) {
                    NowPlayingFragment_.super.ac();
                }
            }
        }, (long)0);
    }

    @Override
    public void b(final boolean bl, final boolean bl2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.b(bl, bl2);
            }
        }, (long)0);
    }

    @Override
    protected void c(final String string2) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.c(string2);
            }
        }, (long)0);
    }

    @Override
    protected void c(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.c(bl);
            }
        }, (long)0);
    }

    public View findViewById(int n) {
        if (this.bi == null) {
            return null;
        }
        return this.bi.findViewById(n);
    }

    @Override
    public void i(final boolean bl) {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                NowPlayingFragment_.super.i(bl);
            }
        }, (long)0);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.bh);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.bi = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.bi == null) {
            this.bi = layoutInflater.inflate(2130903329, viewGroup, false);
        }
        return this.bi;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.bi = null;
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
        this.P = null;
        this.Q = null;
        this.R = null;
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
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.aA = null;
        this.aB = null;
        this.aC = null;
        this.aD = null;
        this.aE = null;
        this.aF = null;
        this.aG = null;
        this.aH = null;
        this.aI = null;
        this.aJ = null;
        this.aK = null;
        this.aL = null;
        this.aM = null;
        this.aN = null;
        this.aO = null;
        this.aP = null;
        this.aQ = null;
        this.aR = null;
        this.aS = null;
        this.be = null;
        this.bf = null;
        this.bg = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mAutoPlay", this.B);
        bundle.putBoolean("mNavigationMenuShowing", this.C);
        bundle.putBoolean("mShowAllComments", this.aW);
        bundle.putBoolean("mLovedPerformance", this.aX);
        bundle.putString("mShareWebURL", this.aY);
        bundle.putBoolean("mSongIsVIPOnly", this.bc);
        bundle.putBoolean("mCloseAllOnBack", this.bd);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.bh.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, NowPlayingFragment> {
        public NowPlayingFragment a() {
            NowPlayingFragment_ nowPlayingFragment_ = new NowPlayingFragment_();
            nowPlayingFragment_.setArguments(this.a);
            return nowPlayingFragment_;
        }

        public FragmentBuilder_ a(int n) {
            this.a.putInt("mMediaPlaybackPresenterPlaylistIndex", n);
            return this;
        }

        public FragmentBuilder_ a(Analytics searchTarget) {
            this.a.putSerializable("mSearchTarget", (Serializable)((Object)searchTarget));
            return this;
        }

        public FragmentBuilder_ a(PerformanceV2 performanceV2) {
            this.a.putParcelable("mPerformance", (Parcelable)performanceV2);
            return this;
        }

        public FragmentBuilder_ a(boolean bl) {
            this.a.putBoolean("mAutoPlay", bl);
            return this;
        }

        public FragmentBuilder_ b(int n) {
            this.a.putInt("mMenuRes", n);
            return this;
        }

        public FragmentBuilder_ b(boolean bl) {
            this.a.putBoolean("mNavigationMenuShowing", bl);
            return this;
        }

        public FragmentBuilder_ c(boolean bl) {
            this.a.putBoolean("mHasCustomMenu", bl);
            return this;
        }
    }

}

