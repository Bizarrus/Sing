package com.smule.singandroid.pre_sing;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.customviews.MasterToolbar;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PreSingActivity_ extends PreSingActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f23573v = new OnViewChangedNotifier();

    class C47401 implements OnClickListener {
        final /* synthetic */ PreSingActivity_ f23570a;

        C47401(PreSingActivity_ preSingActivity_) {
            this.f23570a = preSingActivity_;
        }

        public void onClick(View view) {
            this.f23570a.m24790u();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f23571d;
        private android.support.v4.app.Fragment f23572e;

        public IntentBuilder_(Context context) {
            super(context, PreSingActivity_.class);
        }

        public PostActivityStarter m24799a(int i) {
            if (this.f23572e != null) {
                this.f23572e.startActivityForResult(this.c, i);
            } else if (this.f23571d != null) {
                this.f23571d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }

        public IntentBuilder_ m24796a(StartupMode startupMode) {
            return (IntentBuilder_) super.a("mStartupMode", startupMode);
        }

        public IntentBuilder_ m24794a(SongbookEntry songbookEntry) {
            return (IntentBuilder_) super.a("mEntry", songbookEntry);
        }

        public IntentBuilder_ m24797a(String str) {
            return (IntentBuilder_) super.a("mSectionIdReferrer", str);
        }

        public IntentBuilder_ m24792a(long j) {
            return (IntentBuilder_) super.a("mPromoId", j);
        }

        public IntentBuilder_ m24795a(SingBundle singBundle) {
            return (IntentBuilder_) super.a("mSingBundle", singBundle);
        }

        public IntentBuilder_ m24793a(PerformanceV2 performanceV2) {
            return (IntentBuilder_) super.a("mOpenCallPerformance", performanceV2);
        }

        public IntentBuilder_ m24798a(boolean z) {
            return (IntentBuilder_) super.a("mUseUserGivenParams", z);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23573v);
        m24800a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.presing_activity);
    }

    private void m24800a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24803w();
        m24802b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f23573v.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f23573v.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f23573v.a(this);
    }

    public void m24805a(HasViews hasViews) {
        this.n = (RelativeLayout) hasViews.findViewById(C1947R.id.root_view);
        this.o = (MasterToolbar) hasViews.findViewById(C1947R.id.top_toolbar);
        this.p = hasViews.findViewById(C1947R.id.now_playing_bar_layout);
        this.q = (RelativeLayout) hasViews.findViewById(C1947R.id.now_playing_overlay_view);
        this.s = (ProgressBar) hasViews.findViewById(C1947R.id.top_progress_bar);
        if (this.q != null) {
            this.q.setOnClickListener(new C47401(this));
        }
        c();
    }

    private void m24803w() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("mStartupMode")) {
                this.g = (StartupMode) extras.getSerializable("mStartupMode");
            }
            if (extras.containsKey("mEntry")) {
                this.h = (SongbookEntry) extras.getParcelable("mEntry");
            }
            if (extras.containsKey("mSectionIdReferrer")) {
                this.i = extras.getString("mSectionIdReferrer");
            }
            if (extras.containsKey("mPromoId")) {
                this.j = extras.getLong("mPromoId");
            }
            if (extras.containsKey("mSingBundle")) {
                this.k = (SingBundle) extras.getParcelable("mSingBundle");
            }
            if (extras.containsKey("mOpenCallPerformance")) {
                this.l = (PerformanceV2) extras.getParcelable("mOpenCallPerformance");
            }
            if (extras.containsKey("mUseUserGivenParams")) {
                this.m = extras.getBoolean("mUseUserGivenParams");
            }
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m24803w();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mShouldhideActionBar", this.r);
    }

    private void m24802b(Bundle bundle) {
        if (bundle != null) {
            this.r = bundle.getBoolean("mShouldhideActionBar");
        }
    }

    public void m24804a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
