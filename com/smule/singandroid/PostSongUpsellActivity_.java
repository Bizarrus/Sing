package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class PostSongUpsellActivity_ extends PostSongUpsellActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19380v = new OnViewChangedNotifier();

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f19378d;
        private android.support.v4.app.Fragment f19379e;

        public IntentBuilder_(Context context) {
            super(context, PostSongUpsellActivity_.class);
        }

        public PostActivityStarter m20924a(int i) {
            if (this.f19379e != null) {
                this.f19379e.startActivityForResult(this.c, i);
            } else if (this.f19378d != null) {
                this.f19378d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }

        public IntentBuilder_ m20923a(PostSingBundle postSingBundle) {
            return (IntentBuilder_) super.a("mPostSingBundle", postSingBundle);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19380v);
        m20925a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.post_song_upsell_activity);
    }

    private void m20925a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20927b();
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f19380v.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f19380v.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f19380v.a(this);
    }

    public void m20929a(HasViews hasViews) {
        this.g = (AutoResizeTextView) hasViews.findViewById(C1947R.id.all_access);
        this.h = (TextView) hasViews.findViewById(C1947R.id.post_song_upsell_textitem_top);
        this.i = (TextView) hasViews.findViewById(C1947R.id.post_song_upsell_textitem_middle);
        this.j = (TextView) hasViews.findViewById(C1947R.id.post_song_upsell_textitem_bottom);
        this.k = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.user_image);
        this.l = (PurchaseButton) hasViews.findViewById(C1947R.id.subscription_purchase_top_purchase_button);
        this.m = (PurchaseButton) hasViews.findViewById(C1947R.id.subscription_purchase_bottom_purchase_button);
        this.n = (Button) hasViews.findViewById(C1947R.id.cancel_button);
        this.o = (ProgressBar) hasViews.findViewById(C1947R.id.subscription_purchase_progress_bar);
        this.p = (ImageView) hasViews.findViewById(C1947R.id.icn_ts_top);
        this.q = (ImageView) hasViews.findViewById(C1947R.id.icn_ts_middle);
        this.r = (ImageView) hasViews.findViewById(C1947R.id.icn_ts_bottom);
        c();
    }

    private void m20927b() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("mPostSingBundle")) {
            this.u = (PostSingBundle) extras.getParcelable("mPostSingBundle");
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m20927b();
    }

    public void m20928a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
