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
 *  android.widget.EditText
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.ActivityIntentBuilder
 *  org.androidannotations.api.builder.IntentBuilder
 *  org.androidannotations.api.builder.PostActivityStarter
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import com.smule.singandroid.customviews.ActionBarCustomView;
import java.io.Serializable;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.IntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

@SuppressLint(value={"Registered"})
public final class ChatShareInviteActivity_
extends ChatShareInviteActivity
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier H = new OnViewChangedNotifier();

    private void E() {
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("mPerformance")) {
                this.u = (PerformanceV2)bundle.getParcelable("mPerformance");
            }
            if (bundle.containsKey("mMessageText")) {
                this.v = bundle.getString("mMessageText");
            }
            if (bundle.containsKey("mInvitedFollowers")) {
                this.w = bundle.getBoolean("mInvitedFollowers");
            }
            if (bundle.containsKey("mPromoId")) {
                this.x = (Long)bundle.getSerializable("mPromoId");
            }
            if (bundle.containsKey("mPromptProfilePicAfterSave")) {
                this.y = bundle.getBoolean("mPromptProfilePicAfterSave");
            }
            if (bundle.containsKey("mPostSingBundle")) {
                this.z = (PostSingBundle)bundle.getParcelable("mPostSingBundle");
            }
            if (bundle.containsKey("mSearchClkContext")) {
                this.A = (Object)bundle.getSerializable("mSearchClkContext");
            }
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.E();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.B = bundle.getBoolean("mFirstTimePromptCustomizeProfileDialog");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        ChatShareInviteActivity_.super.a(runTimePermissionsRequest, resultCallback);
    }

    @Override
    public void a(HasViews hasViews) {
        this.h = (ActionBarCustomView)hasViews.findViewById(2131755449);
        this.i = hasViews.findViewById(2131755452);
        this.j = hasViews.findViewById(2131755458);
        this.k = (SNPImageView)hasViews.findViewById(2131755281);
        this.l = (EditText)hasViews.findViewById(2131755454);
        this.m = (SelectUsersAndChatsView)hasViews.findViewById(2131755456);
        this.n = (LinearLayout)hasViews.findViewById(2131755453);
        this.o = hasViews.findViewById(2131755221);
        this.p = hasViews.findViewById(2131755450);
        this.q = hasViews.findViewById(2131755457);
        this.r = hasViews.findViewById(2131755223);
        this.s = (TextView)hasViews.findViewById(2131755451);
        this.t = hasViews.findViewById(2131755455);
        if (this.q != null) {
            this.q.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatShareInviteActivity_.this.b();
                }
            });
        }
        if (this.p != null) {
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatShareInviteActivity_.this.b();
                }
            });
        }
        if (this.s != null) {
            this.s.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatShareInviteActivity_.this.u();
                }
            });
        }
        this.e();
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.H);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
        this.setContentView(2130903119);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mFirstTimePromptCustomizeProfileDialog", this.B);
    }

    public void setContentView(int n) {
        super.setContentView(n);
        this.H.a((HasViews)this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.H.a((HasViews)this);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.H.a((HasViews)this);
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        this.E();
    }

    public static class IntentBuilder_
    extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment d;
        private android.support.v4.app.Fragment e;

        public IntentBuilder_(Context context) {
            super(context, ChatShareInviteActivity_.class);
        }

        public IntentBuilder_ a(Analytics searchClkContext) {
            return (IntentBuilder_)super.a("mSearchClkContext", (Serializable)((Object)searchClkContext));
        }

        public IntentBuilder_ a(PerformanceV2 performanceV2) {
            return (IntentBuilder_)super.a("mPerformance", (Parcelable)performanceV2);
        }

        public IntentBuilder_ a(Long l) {
            return (IntentBuilder_)super.a("mPromoId", (Serializable)l);
        }

        public IntentBuilder_ a(String string2) {
            return (IntentBuilder_)super.a("mMessageText", string2);
        }

        public IntentBuilder_ a(boolean bl) {
            return (IntentBuilder_)super.a("mInvitedFollowers", bl);
        }

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

        public IntentBuilder_ b(boolean bl) {
            return (IntentBuilder_)super.a("mPromptProfilePicAfterSave", bl);
        }
    }

}

