package com.smule.singandroid.chat;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchClkContext;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.ui.SNPImageView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.customviews.ActionBarCustomView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.ActivityIntentBuilder;
import org.androidannotations.api.builder.PostActivityStarter;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatShareInviteActivity_ extends ChatShareInviteActivity implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21023H = new OnViewChangedNotifier();

    class C42971 implements OnClickListener {
        final /* synthetic */ ChatShareInviteActivity_ f21018a;

        C42971(ChatShareInviteActivity_ chatShareInviteActivity_) {
            this.f21018a = chatShareInviteActivity_;
        }

        public void onClick(View view) {
            this.f21018a.mo6751b();
        }
    }

    class C42982 implements OnClickListener {
        final /* synthetic */ ChatShareInviteActivity_ f21019a;

        C42982(ChatShareInviteActivity_ chatShareInviteActivity_) {
            this.f21019a = chatShareInviteActivity_;
        }

        public void onClick(View view) {
            this.f21019a.mo6751b();
        }
    }

    class C42993 implements OnClickListener {
        final /* synthetic */ ChatShareInviteActivity_ f21020a;

        C42993(ChatShareInviteActivity_ chatShareInviteActivity_) {
            this.f21020a = chatShareInviteActivity_;
        }

        public void onClick(View view) {
            this.f21020a.m22666r();
        }
    }

    public static class IntentBuilder_ extends ActivityIntentBuilder<IntentBuilder_> {
        private Fragment f21021d;
        private android.support.v4.app.Fragment f21022e;

        public IntentBuilder_(Context context) {
            super(context, ChatShareInviteActivity_.class);
        }

        public PostActivityStarter m22680a(int i) {
            if (this.f21022e != null) {
                this.f21022e.startActivityForResult(this.c, i);
            } else if (this.f21021d != null) {
                this.f21021d.startActivityForResult(this.c, i, this.a);
            } else if (this.b instanceof Activity) {
                ActivityCompat.startActivityForResult((Activity) this.b, this.c, i, this.a);
            } else {
                this.b.startActivity(this.c, this.a);
            }
            return new PostActivityStarter(this.b);
        }

        public IntentBuilder_ m22676a(PerformanceV2 performanceV2) {
            return (IntentBuilder_) super.a("mPerformance", performanceV2);
        }

        public IntentBuilder_ m22678a(String str) {
            return (IntentBuilder_) super.a("mMessageText", str);
        }

        public IntentBuilder_ m22679a(boolean z) {
            return (IntentBuilder_) super.a("mInvitedFollowers", z);
        }

        public IntentBuilder_ m22677a(Long l) {
            return (IntentBuilder_) super.a("mPromoId", l);
        }

        public IntentBuilder_ m22681b(boolean z) {
            return (IntentBuilder_) super.a("mPromptProfilePicAfterSave", z);
        }

        public IntentBuilder_ m22675a(SearchClkContext searchClkContext) {
            return (IntentBuilder_) super.a("mSearchClkContext", searchClkContext);
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21023H);
        m22683a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
        setContentView((int) C1947R.layout.chat_share_invite);
    }

    private void m22683a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22682A();
        m22685b(bundle);
    }

    public void setContentView(int i) {
        super.setContentView(i);
        this.f21023H.a(this);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.f21023H.a(this);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.f21023H.a(this);
    }

    public void m22687a(HasViews hasViews) {
        this.h = (ActionBarCustomView) hasViews.findViewById(C1947R.id.top_toolbar);
        this.i = hasViews.findViewById(C1947R.id.share_invite_container);
        this.j = hasViews.findViewById(C1947R.id.fragment_content_view);
        this.k = (SNPImageView) hasViews.findViewById(C1947R.id.album_art);
        this.l = (EditText) hasViews.findViewById(C1947R.id.performance_comment_edit_text);
        this.m = (SelectUsersAndChatsView) hasViews.findViewById(C1947R.id.selected_users_and_chats_view);
        this.n = (LinearLayout) hasViews.findViewById(C1947R.id.performance_container);
        this.o = hasViews.findViewById(C1947R.id.root);
        this.p = hasViews.findViewById(C1947R.id.share_upper_overlay);
        this.q = hasViews.findViewById(C1947R.id.share_lower_overlay);
        this.r = hasViews.findViewById(C1947R.id.toolbar_shadow);
        this.s = (TextView) hasViews.findViewById(C1947R.id.invites_hint);
        this.t = hasViews.findViewById(C1947R.id.performance_container_divider);
        if (this.q != null) {
            this.q.setOnClickListener(new C42971(this));
        }
        if (this.p != null) {
            this.p.setOnClickListener(new C42982(this));
        }
        if (this.s != null) {
            this.s.setOnClickListener(new C42993(this));
        }
        c();
    }

    private void m22682A() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.containsKey("mPerformance")) {
                this.u = (PerformanceV2) extras.getParcelable("mPerformance");
            }
            if (extras.containsKey("mMessageText")) {
                this.v = extras.getString("mMessageText");
            }
            if (extras.containsKey("mInvitedFollowers")) {
                this.w = extras.getBoolean("mInvitedFollowers");
            }
            if (extras.containsKey("mPromoId")) {
                this.x = (Long) extras.getSerializable("mPromoId");
            }
            if (extras.containsKey("mPromptProfilePicAfterSave")) {
                this.y = extras.getBoolean("mPromptProfilePicAfterSave");
            }
            if (extras.containsKey("mPostSingBundle")) {
                this.z = (PostSingBundle) extras.getParcelable("mPostSingBundle");
            }
            if (extras.containsKey("mSearchClkContext")) {
                this.A = (SearchClkContext) extras.getSerializable("mSearchClkContext");
            }
        }
    }

    public void setIntent(Intent intent) {
        super.setIntent(intent);
        m22682A();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("mFirstTimePromptCustomizeProfileDialog", this.B);
    }

    private void m22685b(Bundle bundle) {
        if (bundle != null) {
            this.B = bundle.getBoolean("mFirstTimePromptCustomizeProfileDialog");
        }
    }

    public void m22686a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.a(runTimePermissionsRequest, resultCallback);
    }
}
