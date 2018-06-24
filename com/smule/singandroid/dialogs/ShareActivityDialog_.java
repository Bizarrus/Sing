/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.Fragment
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.support.v4.app.ActivityCompat
 *  android.support.v4.app.Fragment
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.UiThreadExecutor
 *  org.androidannotations.api.builder.ActivityIntentBuilder
 *  org.androidannotations.api.builder.PostActivityStarter
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.customviews.SquareSNPImageView;
import com.smule.singandroid.dialogs.ShareActivityDialog;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.UiThreadExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class ShareActivityDialog_
extends ShareActivityDialog
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier Y = new OnViewChangedNotifier();

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.y();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.E = bundle.getBoolean("mConnectAndRequestPublish");
        this.F = (Intent)bundle.getParcelable("mChatIntent");
        this.G = (Intent)bundle.getParcelable("mMessengerIntent");
        this.H = (Intent)bundle.getParcelable("mWhatsAppShareIntent");
        this.I = (Intent)bundle.getParcelable("mLineIntent");
        this.J = (Intent)bundle.getParcelable("mSmsShareIntent");
        this.K = (Intent)bundle.getParcelable("mEmailShareIntent");
        this.L = (Intent)bundle.getParcelable("mYouTubeShareIntent");
        this.M = (Intent)bundle.getParcelable("mInstagramShareIntent");
        this.P = (PerformanceV2)bundle.getParcelable("mPerformance");
        this.Q = bundle.getString("mOpenCallKey");
        this.R = (ArrangementVersionLite)bundle.getParcelable("mArrVesionLite");
        this.S = bundle.getString("mPromoId");
        this.T = (Object)bundle.getSerializable("mSearchClkContext");
        this.U = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
        this.V = bundle.getBoolean("mHasAnimated");
        this.W = (Object)bundle.getSerializable("mSocialChannelClicked");
        this.X = bundle.getBoolean("mVideoDownloaded");
    }

    private void y() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("PERFORMANCE_KEY")) {
                this.P = (PerformanceV2)bundle.getParcelable("PERFORMANCE_KEY");
            }
            if (bundle.containsKey("OPENCALL_KEY")) {
                this.Q = bundle.getString("OPENCALL_KEY");
            }
            if (bundle.containsKey("ARRANGEMENT_KEY")) {
                this.R = (ArrangementVersionLite)bundle.getParcelable("ARRANGEMENT_KEY");
            }
            if (bundle.containsKey("PROMO_ID_KEY")) {
                this.S = bundle.getString("PROMO_ID_KEY");
            }
            if (bundle.containsKey("SEARCHCLK_CONTEXT_KEY")) {
                this.T = (Object)bundle.getSerializable("SEARCHCLK_CONTEXT_KEY");
            }
            if (bundle.containsKey("mPostSingBundle")) {
                this.U = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
            }
        }
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        ShareActivityDialog_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(HasViews hasViews) {
        this.h = (TextView)hasViews.findViewById(2131755857);
        this.i = (SquareSNPImageView)hasViews.findViewById(2131755281);
        this.j = (ImageView)hasViews.findViewById(2131755855);
        this.k = (TextView)hasViews.findViewById(2131756036);
        this.l = (TextView)hasViews.findViewById(2131756037);
        this.m = (ViewGroup)hasViews.findViewById(2131755863);
        this.n = hasViews.findViewById(2131756044);
        this.o = hasViews.findViewById(2131755864);
        this.p = hasViews.findViewById(2131755865);
        this.q = hasViews.findViewById(2131755866);
        this.r = hasViews.findViewById(2131755867);
        this.s = hasViews.findViewById(2131755868);
        this.t = hasViews.findViewById(2131756046);
        this.u = hasViews.findViewById(2131756047);
        this.v = hasViews.findViewById(2131756045);
        this.w = hasViews.findViewById(2131755869);
        this.x = hasViews.findViewById(2131755870);
        this.y = (ToggleButton)hasViews.findViewById(2131756041);
        this.z = (ToggleButton)hasViews.findViewById(2131755862);
        this.A = (RelativeLayout)hasViews.findViewById(2131756048);
        this.B = (TextView)hasViews.findViewById(2131756051);
        this.C = (ProgressBar)hasViews.findViewById(2131756050);
        this.D = (TextView)hasViews.findViewById(2131756053);
        View view = hasViews.findViewById(2131756038);
        View view2 = hasViews.findViewById(2131755861);
        hasViews = hasViews.findViewById(2131755233);
        if (this.y != null) {
            this.y.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.a();
                }
            });
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.b();
                }
            });
        }
        if (this.z != null) {
            this.z.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.t();
                }
            });
        }
        if (view2 != null) {
            view2.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.u();
                }
            });
        }
        if (this.n != null) {
            this.n.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.a(view);
                }
            });
        }
        if (this.t != null) {
            this.t.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.b(view);
                }
            });
        }
        if (this.v != null) {
            this.v.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.c(view);
                }
            });
        }
        if (this.u != null) {
            this.u.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.d(view);
                }
            });
        }
        if (this.o != null) {
            this.o.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.e(view);
                }
            });
        }
        if (this.p != null) {
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.f(view);
                }
            });
        }
        if (this.q != null) {
            this.q.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.g(view);
                }
            });
        }
        if (this.r != null) {
            this.r.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.h(view);
                }
            });
        }
        if (this.s != null) {
            this.s.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.i(view);
                }
            });
        }
        if (this.w != null) {
            this.w.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.j(view);
                }
            });
        }
        if (this.x != null) {
            this.x.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.k(view);
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivityDialog_.this.v();
                }
            });
        }
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.Y);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903406);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mConnectAndRequestPublish", this.E);
        bundle.putParcelable("mChatIntent", (Parcelable)this.F);
        bundle.putParcelable("mMessengerIntent", (Parcelable)this.G);
        bundle.putParcelable("mWhatsAppShareIntent", (Parcelable)this.H);
        bundle.putParcelable("mLineIntent", (Parcelable)this.I);
        bundle.putParcelable("mSmsShareIntent", (Parcelable)this.J);
        bundle.putParcelable("mEmailShareIntent", (Parcelable)this.K);
        bundle.putParcelable("mYouTubeShareIntent", (Parcelable)this.L);
        bundle.putParcelable("mInstagramShareIntent", (Parcelable)this.M);
        bundle.putParcelable("mPerformance", (Parcelable)this.P);
        bundle.putString("mOpenCallKey", this.Q);
        bundle.putParcelable("mArrVesionLite", (Parcelable)this.R);
        bundle.putString("mPromoId", this.S);
        bundle.putSerializable("mSearchClkContext", (Serializable)((Object)this.T));
        bundle.putParcelable("mPostSingBundle", (Parcelable)this.U);
        bundle.putBoolean("mHasAnimated", this.V);
        bundle.putSerializable("mSocialChannelClicked", (Serializable)((Object)this.W));
        bundle.putBoolean("mVideoDownloaded", this.X);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.Y.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.Y.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.Y.a((HasViews)this);
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        this.y();
    }

    @Override
    protected void x() {
        UiThreadExecutor.a((String)"", (Runnable)new Runnable(){

            @Override
            public void run() {
                ShareActivityDialog_.super.x();
            }
        }, (long)0);
    }

    public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment d;
        private android.support.v4.app.Fragment e;

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public PostActivityStarter a(int n) {
            if (this.e != null) {
                this.e.startActivityForResult(this.c, n);
                do {
                    return new PostActivityStarter(this.b);
                    break;
                } while (true);
            }
            if (this.d != null) {
                this.d.startActivityForResult(this.c, n, this.a);
                return new PostActivityStarter(this.b);
            }
            if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity)((Activity)this.b), (Intent)this.c, (int)n, (Bundle)this.a);
                return new PostActivityStarter(this.b);
            }
            this.b.startActivity(this.c, this.a);
            return new PostActivityStarter(this.b);
        }
    }

}

