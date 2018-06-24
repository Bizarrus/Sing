/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.AnimatorInflater
 *  android.animation.AnimatorSet
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.ActivityNotFoundException
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.Intent
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.pm.PackageManager
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Color
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.facebook.CallbackManager
 *  com.facebook.CallbackManager$Factory
 *  com.facebook.FacebookCallback
 *  com.facebook.FacebookException
 *  com.facebook.login.LoginManager
 *  com.facebook.login.LoginResult
 *  com.facebook.share.Sharer
 *  com.facebook.share.Sharer$Result
 *  com.facebook.share.model.ShareVideo
 *  com.facebook.share.model.ShareVideo$Builder
 *  com.facebook.share.model.ShareVideoContent
 *  com.facebook.share.model.ShareVideoContent$Builder
 *  com.facebook.share.widget.ShareDialog
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.ShareUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ShareExtClkContext
 *  com.smule.singandroid.utils.SingAnalytics$ShareModuleType
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.Extra
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  twitter4j.StatusUpdate
 */
package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.Toaster;
import com.smule.chat.Chat;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.ShareActivity_;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.TwitterOAuthActivity_;
import com.smule.singandroid.chat.ChatShareInviteActivity;
import com.smule.singandroid.customviews.SquareSNPImageView;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.ChatUtils;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.ShareUtils;
import com.smule.singandroid.utils.SingAnalytics;
import java.io.File;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import twitter4j.StatusUpdate;

@SuppressLint(value={"Registered"})
@EActivity
public class ShareActivity
extends BaseActivity {
    public static final String g = ShareActivity.class.getName();
    @ViewById
    protected RelativeLayout A;
    @ViewById
    protected TextView B;
    @ViewById
    protected ProgressBar C;
    @ViewById
    protected TextView D;
    @InstanceState
    protected boolean E;
    @InstanceState
    protected Intent F;
    @InstanceState
    protected Intent G;
    @InstanceState
    protected Intent H;
    @InstanceState
    protected Intent I;
    @InstanceState
    protected Intent J;
    @InstanceState
    protected Intent K;
    @InstanceState
    protected Intent L;
    @InstanceState
    protected Intent M;
    protected MagicTwitter.TwitterOnPostCallback N;
    protected MagicFacebook O;
    @Extra
    @InstanceState
    protected PerformanceV2 P;
    @Extra
    @InstanceState
    protected String Q;
    @Extra
    @InstanceState
    protected ArrangementVersionLite R;
    @Extra
    @InstanceState
    protected String S;
    @Extra
    @InstanceState
    protected Analytics T;
    @Extra
    @InstanceState
    protected PostSingBundle U;
    @InstanceState
    protected boolean V;
    @InstanceState
    protected Analytics W;
    @InstanceState
    protected boolean X = false;
    private SingServerValues Y = new SingServerValues();
    private CallbackManager Z;
    private ShareDialog aa;
    private boolean ab;
    private TracksManager.VideoDownloader ac;
    private List<> ad = new ArrayList<>();
    private Set<String> ae = new HashSet<String>();
    @ViewById
    protected TextView h;
    @ViewById
    protected SquareSNPImageView i;
    @ViewById
    protected ImageView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected TextView l;
    @ViewById
    protected ViewGroup m;
    @ViewById
    protected View n;
    @ViewById
    protected View o;
    @ViewById
    protected View p;
    @ViewById
    protected View q;
    @ViewById
    protected View r;
    @ViewById
    protected View s;
    @ViewById
    protected View t;
    @ViewById
    protected View u;
    @ViewById
    protected View v;
    @ViewById
    protected View w;
    @ViewById
    protected View x;
    @ViewById
    protected ToggleButton y;
    @ViewById
    protected ToggleButton z;

    private boolean A() {
        if (this.P == null && this.R != null) {
            return true;
        }
        return false;
    }

    private void B() {
        if (this.b(Analytics.c)) {
            if (this.C()) {
                if (this.E()) {
                    View.OnClickListener onClickListener = new View.OnClickListener(){

                        public void onClick(View object) {
                            object = ShareActivity.this.getResources().getString(2131296772);
                            ShareActivity.this.d((String)object);
                            ShareActivity.this.a(Analytics.c, SingAnalytics.ShareExtClkContext.c);
                        }
                    };
                    this.a(this.t, onClickListener);
                }
                return;
            }
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    ShareActivity.this.F();
                    ShareActivity.this.a(Analytics.c, SingAnalytics.ShareExtClkContext.b);
                }
            };
            this.a(this.t, onClickListener);
            return;
        }
        this.m.removeView(this.t);
    }

    private boolean C() {
        return this.P.songDShare;
    }

    private boolean D() {
        if (this.P != null && this.P.video) {
            return true;
        }
        return false;
    }

    private boolean E() {
        if (this.D() && (this.P.videoRenderedMp4Url == null || !this.ab)) {
            return true;
        }
        return false;
    }

    private void F() {
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this, "", this.getResources().getString(2131297421), true, false);
        textAlertDialog.a(this.getString(2131296705), null);
        textAlertDialog.show();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private List<> G() {
        List<> list = this.getSharedPreferences("sing_prefs", 0).getString("share_buttons_rank", "");
        try {
            list = JsonUtils.a((String)((Object)list), new TypeReference<List<>>(){});
        }
        catch (Exception exception) {
            list = new ArrayList();
        }
        int n = this.m.getChildCount();
        if (list.size() != n) {
            return this.H();
        }
        Collections.sort(list);
        Iterator iterator = list.iterator();
        do {
            Object object = list;
            if (!iterator.hasNext()) return object;
            object = iterator.next();
        } while (!object.tag.equals("chat"));
        object.nice = Integer.MIN_VALUE;
        return list;
    }

    private List<> H() {
        ArrayList<> arrayList = new ArrayList<>();
        int n = this.m.getChildCount();
        for (int i = 0; i < n; ++i) {
            Comparable<> comparable = new Comparable<>((String)this.m.getChildAt(i).getTag(), i * 10){
                @JsonProperty(value="nice")
                public int nice;
                @JsonProperty(value="tag")
                public String tag;
                {
                    this.tag = string2;
                    this.nice = n;
                }

                public int a(@NonNull  buttonRank) {
                    if (this.nice < buttonRank.nice) {
                        return -1;
                    }
                    if (this.nice == buttonRank.nice) {
                        return 0;
                    }
                    return 1;
                }

                @Override
                public /* synthetic */ int compareTo(@NonNull Object object) {
                    return this.a(object);
                }
            };
            if (comparable.tag.equals("chat")) {
                comparable.nice = Integer.MIN_VALUE;
            }
            arrayList.add(comparable);
        }
        return arrayList;
    }

    private void I() {
        Object object;
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        int n = this.m.getChildCount();
        for (int i = 0; i < n; ++i) {
            object = this.m.getChildAt(i);
            hashMap.put((String)object.getTag(), object);
        }
        this.m.removeAllViews();
        object = this.ad.iterator();
        while (object.hasNext()) {
            View view = (View)hashMap.get((object.next()).tag);
            if (view == null) continue;
            this.m.addView(view);
        }
    }

    private void J() {
        for ( buttonRank : this.ad) {
            if (!this.ae.contains(buttonRank.tag)) continue;
            buttonRank.nice -= 11;
        }
        Collections.sort(this.ad);
        try {
            String string2 = JsonUtils.a().writeValueAsString(this.ad);
            this.getSharedPreferences("sing_prefs", 0).edit().putString("share_buttons_rank", string2).apply();
            return;
        }
        catch (JsonProcessingException jsonProcessingException) {
            Log.d(g, "Failed to save rankings", (Throwable)jsonProcessingException);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Intent a(Context context, PerformanceV2 performanceV2, String string2, ArrangementVersionLite arrangementVersionLite, Long l, Analytics searchClkContext) {
        String string3 = g;
        StringBuilder stringBuilder = new StringBuilder().append("generateIntent -- is context null ? ");
        boolean bl = context == null;
        Log.b(string3, stringBuilder.append(bl).toString());
        context = new Intent(context, ShareActivity_.class);
        context.putExtra("PERFORMANCE_KEY", (Parcelable)performanceV2);
        context.putExtra("ARRANGEMENT_KEY", (Parcelable)arrangementVersionLite);
        context.putExtra("OPENCALL_KEY", string2);
        context.putExtra("PROMO_ID_KEY", l.toString());
        context.putExtra("SEARCHCLK_CONTEXT_KEY", (Serializable)((Object)searchClkContext));
        return context;
    }

    private void a(Uri object) {
        this.M = ShareUtils.a((Uri)object);
        if (this.M != null && this.b(Analytics.l)) {
            if (this.E()) {
                object = new View.OnClickListener(){

                    public void onClick(View object) {
                        object = ShareActivity.this.getResources().getString(2131297396);
                        ShareActivity.this.d((String)object);
                        ShareActivity.this.a(Analytics.l, SingAnalytics.ShareExtClkContext.c);
                    }
                };
                this.a(this.v, (View.OnClickListener)object);
            }
            return;
        }
        this.m.removeView(this.v);
    }

    private void a(View view, View.OnClickListener onClickListener) {
        ((TextView)view).getCompoundDrawables()[1].mutate().setAlpha(51);
        int n = ((TextView)view).getCurrentTextColor();
        ((TextView)view).setTextColor(Color.argb((int)61, (int)Color.red((int)n), (int)Color.green((int)n), (int)Color.blue((int)n)));
        view.setOnClickListener(onClickListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(final Analytics socialChannel) {
        Intent intent;
        if (!this.X) {
            this.B.setText((CharSequence)"0%");
            this.C.setProgress(0);
            this.A.setVisibility(0);
            this.ac = TracksManager.a().a(this.getApplicationContext(), this.P.videoRenderedMp4Url, new TracksManager.VideoDownloadCallback(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void a(int n) {
                    ShareActivity.this.ac = null;
                    if (ShareActivity.this.isFinishing()) {
                        return;
                    }
                    switch (n) {
                        default: {
                            TextAlertDialog textAlertDialog = new TextAlertDialog((Context)ShareActivity.this, null, ShareActivity.this.getResources().getString(2131297523));
                            textAlertDialog.a(null, ShareActivity.this.getString(2131296705));
                            textAlertDialog.a(new Runnable(){

                                @Override
                                public void run() {
                                    ShareActivity.this.A.setVisibility(4);
                                }
                            });
                            textAlertDialog.b(new Runnable(){

                                @Override
                                public void run() {
                                    ShareActivity.this.A.setVisibility(4);
                                }
                            });
                            textAlertDialog.show();
                            ShareActivity.this.A.setVisibility(4);
                            return;
                        }
                        case 0: {
                            Intent intent;
                            ShareActivity.this.A.setVisibility(4);
                            ShareActivity.this.X = true;
                            ShareActivity.this.z();
                            if (socialChannel == Analytics.c) {
                                ShareActivity.this.c(ShareActivity.this.getString(2131297393));
                                ShareActivity.this.y();
                                return;
                            }
                            if (socialChannel == Analytics.l) {
                                ShareActivity.this.c(ShareActivity.this.getString(2131297397));
                                intent = ShareActivity.this.M;
                            } else {
                                if (socialChannel != Analytics.d) {
                                    Log.e(ShareActivity.g, "onResult - Unsupported social channel: " + socialChannel);
                                    return;
                                }
                                intent = ShareActivity.this.L;
                            }
                            ShareActivity.this.W = socialChannel;
                            ShareActivity.this.startActivityForResult(intent, 42405);
                            return;
                        }
                        case 1: 
                    }
                    TextAlertDialog textAlertDialog = new TextAlertDialog((Context)ShareActivity.this, 2131297423, 2131297422);
                    textAlertDialog.a(null, ShareActivity.this.getString(2131296705));
                    textAlertDialog.a(new Runnable(){

                        @Override
                        public void run() {
                            ShareActivity.this.A.setVisibility(4);
                        }
                    });
                    textAlertDialog.b(new Runnable(){

                        @Override
                        public void run() {
                            ShareActivity.this.A.setVisibility(4);
                        }
                    });
                    textAlertDialog.show();
                    ShareActivity.this.A.setVisibility(4);
                }

                @Override
                public void a(Long l, Long l2) {
                    if (ShareActivity.this.isFinishing()) {
                        return;
                    }
                    int n = (int)((float)l.longValue() / (float)l2.longValue() * 100.0f);
                    ShareActivity.this.B.setText((CharSequence)("" + n + "%"));
                    ShareActivity.this.C.setProgress(n);
                }

            });
            return;
        }
        if (socialChannel == Analytics.c) {
            this.c(this.getString(2131297393));
            this.a(2131297413);
            this.y();
            return;
        }
        if (socialChannel == Analytics.l) {
            this.c(this.getString(2131297397));
            this.a(2131297413);
            intent = this.M;
        } else {
            if (socialChannel != Analytics.d) {
                Log.e(g, "Unsupported social channel: " + socialChannel);
                return;
            }
            intent = this.L;
        }
        this.W = socialChannel;
        this.startActivityForResult(intent, 42405);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(Analytics socialChannel, SingAnalytics.ShareExtClkContext shareExtClkContext) {
        Analytics videoStatusType = null;
        Analytics share = socialChannel == Analytics.n ? Analytics.c : Analytics.a;
        String string2 = this.R == null ? SingAnalytics.d((PerformanceV2)this.P) : this.R.key;
        String string3 = this.P == null ? null : this.P.performanceKey;
        Analytics performanceStatus = this.P == null ? null : SingAnalytics.e((PerformanceV2)this.P);
        Analytics ensemble = this.P == null ? null : SingAnalytics.a((PerformanceV2)this.P);
        if (this.P != null) {
            videoStatusType = this.P.video ? Analytics.a : Analytics.b;
        }
        if (socialChannel == Analytics.f) {
            SingAnalytics.a((String)string3, performanceStatus, ensemble, (String)string2, videoStatusType, socialChannel);
            return;
        }
        if (this.P != null) {
            SingAnalytics.a((String)string3, (String)string2, (SingAnalytics.ShareExtClkContext)shareExtClkContext, socialChannel, share, performanceStatus, ensemble, videoStatusType);
            return;
        }
        SingAnalytics.a((String)string2, (SingAnalytics.ShareExtClkContext)shareExtClkContext, socialChannel, share);
    }

    private void b(Uri object) {
        this.L = ShareUtils.a((Context)this, (Uri)object);
        if (this.L != null && this.b(Analytics.d)) {
            if (this.E()) {
                object = new View.OnClickListener(){

                    public void onClick(View object) {
                        object = ShareActivity.this.getResources().getString(2131297441);
                        ShareActivity.this.d((String)object);
                        ShareActivity.this.a(Analytics.d, SingAnalytics.ShareExtClkContext.c);
                    }
                };
                this.a(this.u, (View.OnClickListener)object);
            }
            return;
        }
        this.m.removeView(this.u);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean b(Analytics socialChannel) {
        boolean bl;
        boolean bl2 = bl = true;
        switch (.a[socialChannel.ordinal()]) {
            default: {
                bl2 = false;
            }
            case 2: 
            case 3: 
            case 4: {
                return bl2;
            }
            case 1: {
                if (!this.Y.ad()) return false;
                {
                    bl2 = bl;
                    if (ShareDialog.canShow(ShareVideoContent.class)) return bl2;
                }
                return false;
            }
        }
    }

    private void c(Analytics socialChannel) {
        this.a(socialChannel, SingAnalytics.ShareExtClkContext.a);
    }

    private void c(String string2) {
        ((ClipboardManager)this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)g, (CharSequence)string2));
    }

    private void c(final boolean bl) {
        new Handler(Looper.getMainLooper()).post(new Runnable(){

            @Override
            public void run() {
                ShareActivity.this.d(bl);
            }
        });
    }

    private void d(String object) {
        object = new TextAlertDialog((Context)this, this.getResources().getString(2131297425, new Object[]{object}), this.getResources().getString(2131297424, new Object[]{object}), true, false);
        object.a(this.getString(2131296705), null);
        object.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(boolean bl) {
        Log.b(g, "refreshFacebookToggle. isLoggedIn=" + com.smule.android.facebook.MagicFacebook.a().c() + " hasPublishPermission=" + com.smule.android.facebook.MagicFacebook.a().h());
        ToggleButton toggleButton = this.y;
        bl = com.smule.android.facebook.MagicFacebook.a().c() && com.smule.android.facebook.MagicFacebook.a().h() && bl;
        toggleButton.setChecked(bl);
        if (com.smule.android.facebook.MagicFacebook.a().c() && this.E) {
            com.smule.android.facebook.MagicFacebook.a().g();
            com.smule.android.facebook.MagicFacebook.a().a((Activity)this);
            this.E = false;
        }
    }

    private void e(String string2) {
        this.ae.add(string2);
    }

    private void y() {
        Uri uri = Uri.fromFile((File)new File(this.getExternalFilesDir(null).toString() + "/" + "sing_video" + "/" + "video"));
        uri = new ShareVideo.Builder().setLocalUrl(uri).build();
        if (this.aa.canShow((Object)(uri = new ShareVideoContent.Builder().setVideo((ShareVideo)uri).setContentDescription(this.getString(2131297393)).build()))) {
            this.aa.show((Object)uri);
        }
    }

    private void z() {
        MediaPlayerServiceController mediaPlayerServiceController = MediaPlayerServiceController.a();
        if (mediaPlayerServiceController.j() || mediaPlayerServiceController.n() || mediaPlayerServiceController.l()) {
            MediaPlayerServiceController.a().c();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void a() {
        block6 : {
            block5 : {
                if (SingApplication.h.booleanValue()) break block5;
                if (this.y.isChecked()) {
                    this.c(Analytics.b);
                }
                if (!com.smule.android.facebook.MagicFacebook.a().c()) {
                    this.y.setChecked(false);
                    this.E = true;
                    LoginManager.getInstance().logInWithReadPermissions((Activity)this, MagicNetwork.d().getFacebookReadPermissions());
                    return;
                }
                if (!com.smule.android.facebook.MagicFacebook.a().h()) break block6;
            }
            return;
        }
        this.y.setChecked(false);
        com.smule.android.facebook.MagicFacebook.a().a((Activity)this);
    }

    @Click
    protected void a(View view) {
        if (this.F != null) {
            this.c(Analytics.f);
            this.startActivityForResult(this.F, 42405);
        }
    }

    protected void a(List<String> list) {
    }

    @Click
    protected void b() {
        this.y.performClick();
    }

    @Click
    protected void b(View view) {
        this.e((String)view.getTag());
        this.c(Analytics.c);
        this.D.setVisibility(0);
        this.a(Analytics.c);
    }

    @Click
    protected void c(View view) {
        this.e((String)view.getTag());
        this.c(Analytics.l);
        this.D.setVisibility(0);
        this.a(Analytics.l);
    }

    @Click
    protected void d(View view) {
        if (this.L != null) {
            this.e((String)view.getTag());
            this.c(Analytics.d);
            this.D.setVisibility(8);
            long l = MagicPreferences.b((Context)this, "SHARE_YOUTUBE_WARNING_COUNT_KEY", 0);
            if (l < 3) {
                this.a_(this.getString(2131297430, new Object[]{this.getString(2131297441)}));
                MagicPreferences.a((Context)this, "SHARE_YOUTUBE_WARNING_COUNT_KEY", l + 1);
            }
            this.a(Analytics.d);
        }
    }

    @Click
    protected void e(View view) {
        if (this.I != null) {
            this.e((String)view.getTag());
            this.c(Analytics.g);
            this.startActivity(this.I);
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    protected void f() {
        block12 : {
            super.f();
            if (this.P == null && this.R == null) {
                this.finish();
                return;
            }
            var2_1 = this.i == null;
            this.ab = var2_1;
            if (this.R != null) {
                this.h.setText((CharSequence)this.getString(2131297436));
            }
            if (this.ab) break block12;
            if (this.P == null) ** GOTO lbl-1000
            if (this.P.d()) {
                var3_2 = "SOLO_VIDEO_ALBUM_ART_BITMAP";
            } else if (this.P.e()) {
                var3_2 = "duetjoincompositebitmap";
            } else lbl-1000: // 2 sources:
            {
                var3_2 = null;
            }
            var4_3 = var3_2 != null ? ImageToDiskUtils.a((Context)this, (String)var3_2) : null;
            if (var4_3 != null) {
                this.i.setImageBitmap((Bitmap)var4_3);
            } else {
                var5_4 = new Object(){
                    public String a;

                    public void a() {
                        this.a = null;
                    }

                    public void a(ImageView imageView) {
                        com.nostra13.universalimageloader.core.ImageLoader.a().a(imageView);
                    }

                    public boolean a(String string2, ImageView imageView, int n) {
                        return this.a(string2, imageView, n, false);
                    }

                    public boolean a(String string2, ImageView imageView, int n, boolean bl) {
                        if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                            com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl);
                            this.a = string2;
                            return true;
                        }
                        return false;
                    }

                    public boolean a(String string2, ImageView imageView, int n, boolean bl, int n2) {
                        if (string2 != null && !string2.isEmpty() && !string2.equals(this.a)) {
                            com.smule.android.utils.ImageUtils.a(string2, imageView, n, bl, -1, n2, null, true);
                            this.a = string2;
                            return true;
                        }
                        return false;
                    }
                };
                var4_3 = this.P != null ? this.P.coverUrl : this.R.coverUrl;
                var5_4.a((String)var4_3, (ImageView)this.i, 2130837898);
            }
            ImageToDiskUtils.b((Context)this, (String)var3_2);
            var4_3 = this.k;
            var3_2 = this.P != null ? this.P.title : this.R.name;
            var4_3.setText((CharSequence)var3_2);
            this.l.setText((CharSequence)this.getString(2131297387, new Object[]{UserManager.a().i()}));
        }
        if (this.P != null) {
            if (this.ab) {
                var3_2 = this.h;
                var1_5 = this.P.e() != false || this.P.f() != false ? 2131297390 : 2131297391;
                var3_2.setText((CharSequence)this.getString(var1_5));
            } else {
                var3_2 = this.h;
                var1_6 = this.P.e() != false || this.P.f() != false ? 2131297438 : 2131297439;
                var3_2.setText((CharSequence)this.getString(var1_6));
            }
        }
        this.x();
        if (this.P != null) {
            var5_4 = PerformanceV2Util.f((PerformanceV2)this.P);
            var6_7 = this.P.performanceKey;
            var7_8 = SingAnalytics.e((PerformanceV2)this.P);
            var8_9 = SingAnalytics.a((PerformanceV2)this.P);
            var9_10 = SingAnalytics.d((PerformanceV2)this.P);
            var3_2 = this.P.video != false ? Analytics.a : Analytics.b;
            var4_3 = this.ab != false ? SingAnalytics.ShareModuleType.b : SingAnalytics.ShareModuleType.a;
            SingAnalytics.a((String)var5_4, (String)var6_7, var7_8, var8_9, (String)var9_10, var3_2, (SingAnalytics.ShareModuleType)var4_3);
        }
        this.A.setOnClickListener(null);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Click
    protected void f(View view) {
        if (this.G == null) return;
        this.e((String)view.getTag());
        this.c(Analytics.h);
        try {
            this.startActivity(this.G);
            return;
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            Log.c(g, "Messenger activity not found", (Throwable)activityNotFoundException);
            return;
        }
    }

    @Click
    protected void g(View view) {
        if (this.H != null) {
            this.e((String)view.getTag());
            this.c(Analytics.o);
            this.startActivity(this.H);
        }
    }

    @Click
    protected void h(View view) {
        if (this.J != null) {
            this.e((String)view.getTag());
            this.c(Analytics.i);
            this.startActivity(this.J);
        }
    }

    @Click
    protected void i(View view) {
        if (this.K != null) {
            this.e((String)view.getTag());
            this.c(Analytics.j);
            this.startActivity(this.K);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void j(View object) {
        this.e((String)object.getTag());
        this.c(Analytics.m);
        ClipboardManager clipboardManager = (ClipboardManager)this.getSystemService("clipboard");
        object = this.P != null ? this.P.title : this.R.name;
        String string2 = this.P != null ? this.P.y() : this.R.b();
        clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence)object, (CharSequence)string2));
        Toaster.a((Context)this, this.getString(2131297386));
    }

    @Click
    protected void k(View view) {
        this.e((String)view.getTag());
        this.c(Analytics.n);
        if (this.P != null) {
            ShareUtils.e((Context)this, (PerformanceV2)this.P);
            return;
        }
        ShareUtils.e((Context)this, (ArrangementVersionLite)this.R);
    }

    public void onActivityResult(int n, int n2, Intent object) {
        super.onActivityResult(n, n2, (Intent)object);
        if (ChatUtils.a() && n == 42405 && n2 == -1) {
            Object object2;
            LinkedList<String> linkedList = new LinkedList<String>();
            if (object.hasExtra("RESULT.SELECTED_ACCOUNTS")) {
                object2 = object.getParcelableArrayListExtra("RESULT.SELECTED_ACCOUNTS").iterator();
                while (object2.hasNext()) {
                    linkedList.add(((AccountIcon)object2.next()).handle);
                }
            }
            object2 = SingApplication.k();
            if (object.hasExtra("RESULT.SELECTED_CHATS")) {
                object = object.getStringArrayListExtra("RESULT.SELECTED_CHATS").iterator();
                while (object.hasNext()) {
                    Object object3 = object2.a((String)object.next());
                    if (object3 == null) continue;
                    if (object3.a() == Chat.Type.a) {
                        if ((object3 = ((PeerChat)object3).R()) == null) continue;
                        linkedList.add(object3.handle);
                        continue;
                    }
                    linkedList.add(((GroupChat)object3).S());
                }
            }
            if (!linkedList.isEmpty()) {
                this.a(linkedList);
            }
            return;
        }
        this.Z.onActivityResult(n, n2, (Intent)object);
    }

    @Override
    public void onBackPressed() {
        if (this.ac != null && !this.ac.isCancelled()) {
            this.ac.cancel(true);
            this.A.setVisibility(4);
            return;
        }
        this.w();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.Z = CallbackManager.Factory.create();
        this.aa = new ShareDialog((Activity)this);
        this.aa.registerCallback(this.Z, (FacebookCallback)new FacebookCallback<Sharer.Result>(){

            /*
             * Enabled aggressive block sorting
             */
            public void a(Sharer.Result object) {
                String string2 = ShareActivity.this.P.performanceKey;
                Analytics socialChannel = Analytics.c;
                Analytics performanceStatus = SingAnalytics.e((PerformanceV2)ShareActivity.this.P);
                Analytics ensemble = SingAnalytics.a((PerformanceV2)ShareActivity.this.P);
                String string3 = SingAnalytics.d((PerformanceV2)ShareActivity.this.P);
                object = ShareActivity.this.P.video ? Analytics.a : Analytics.b;
                SingAnalytics.a((String)string2, socialChannel, performanceStatus, ensemble, (String)string3, object);
            }

            public void onCancel() {
            }

            public void onError(FacebookException facebookException) {
                Toaster.a((Context)ShareActivity.this, ShareActivity.this.getString(2131297395));
            }

            public /* synthetic */ void onSuccess(Object object) {
                this.a((Sharer.Result)object);
            }
        });
        LoginManager.getInstance().registerCallback(this.Z, (FacebookCallback)new FacebookCallback<LoginResult>(){

            public void a(LoginResult loginResult) {
                ShareActivity.this.c(true);
            }

            public void onCancel() {
                ShareActivity.this.c(true);
            }

            public void onError(FacebookException facebookException) {
                Log.b(ShareActivity.g, "Facebook Error", (Throwable)facebookException);
                ShareActivity.this.c(true);
            }

            public /* synthetic */ void onSuccess(Object object) {
                this.a((LoginResult)object);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.J();
        this.m = null;
        this.o = null;
        this.r = null;
        this.y = null;
        this.k = null;
        this.s = null;
        this.l = null;
        this.j = null;
        this.i = null;
        this.z = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onResume() {
        String string2;
        Object object;
        Analytics socialChannel;
        super.onResume();
        MiscUtils.a((Activity)this, (boolean)false);
        if (!this.ab && !this.V) {
            object = new AnimatorSet();
            string2 = AnimatorInflater.loadAnimator((Context)this, (int)2131034116);
            string2.setStartDelay(250);
            string2.setTarget((Object)this.j);
            socialChannel = AnimatorInflater.loadAnimator((Context)this, (int)2131034113);
            socialChannel.setTarget((Object)this.i);
            object.playTogether(new Animator[]{string2, socialChannel});
            object.start();
            this.V = true;
        }
        if (this.W != null) {
            string2 = this.P.performanceKey;
            socialChannel = this.W;
            Analytics performanceStatus = SingAnalytics.e((PerformanceV2)this.P);
            Analytics ensemble = SingAnalytics.a((PerformanceV2)this.P);
            String string3 = SingAnalytics.d((PerformanceV2)this.P);
            object = this.P.video ? Analytics.a : Analytics.b;
            SingAnalytics.a((String)string2, socialChannel, performanceStatus, ensemble, (String)string3, object);
            this.W = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void onStart() {
        boolean bl = true;
        super.onStart();
        SharedPreferences sharedPreferences = this.getSharedPreferences("sing_prefs", 0);
        boolean bl2 = sharedPreferences.getBoolean("facebook.enabled", true);
        boolean bl3 = sharedPreferences.getBoolean("twitter.enabled", true);
        Log.c(g, "onStart : " + com.smule.android.facebook.MagicFacebook.a().c() + " / " + this.E);
        this.d(bl2);
        sharedPreferences = this.z;
        if (!MiscUtils.b() || !bl3) {
            bl = false;
        }
        sharedPreferences.setChecked(bl);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = this.getSharedPreferences("sing_prefs", 0).edit();
        if (com.smule.android.facebook.MagicFacebook.a().c() && com.smule.android.facebook.MagicFacebook.a().h()) {
            editor.putBoolean("facebook.enabled", this.y.isChecked());
        }
        if (MiscUtils.b()) {
            editor.putBoolean("twitter.enabled", this.z.isChecked());
        }
        editor.apply();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void t() {
        block5 : {
            block4 : {
                if (SingApplication.h.booleanValue()) break block4;
                if (this.z.isChecked()) {
                    this.c(Analytics.e);
                }
                if (!MiscUtils.b()) break block5;
            }
            return;
        }
        this.z.setChecked(false);
        this.startActivity(new Intent((Context)this, TwitterOAuthActivity_.class));
    }

    @Click
    protected void u() {
        this.z.performClick();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Click
    protected void v() {
        Analytics performanceStatus;
        String string2;
        Analytics socialChannel;
        Object object;
        Analytics ensemble;
        Object object2;
        Log.b(g, "Facebook toggle checked: " + this.y.isChecked());
        if (!SingApplication.h.booleanValue() && this.y.isChecked()) {
            if (this.P != null) {
                object = this.P.performanceKey;
                socialChannel = Analytics.b;
                performanceStatus = SingAnalytics.e((PerformanceV2)this.P);
                ensemble = SingAnalytics.a((PerformanceV2)this.P);
                string2 = SingAnalytics.d((PerformanceV2)this.P);
                object2 = this.P.video ? Analytics.a : Analytics.b;
                SingAnalytics.a((String)object, socialChannel, performanceStatus, ensemble, (String)string2, object2);
            } else {
                SingAnalytics.a(Analytics.b, (String)this.R.key);
            }
            object = com.smule.android.facebook.MagicFacebook.a();
            object2 = this.P != null ? this.P.y() : this.R.b();
            object.a((String)object2, this.O);
        }
        Log.b(g, "Twitter toggle checked: " + this.z.isChecked());
        if (!SingApplication.h.booleanValue() && this.z.isChecked()) {
            if (this.P != null) {
                object = this.P.performanceKey;
                socialChannel = Analytics.e;
                performanceStatus = SingAnalytics.e((PerformanceV2)this.P);
                ensemble = SingAnalytics.a((PerformanceV2)this.P);
                string2 = SingAnalytics.d((PerformanceV2)this.P);
                object2 = this.P.video ? Analytics.a : Analytics.b;
                SingAnalytics.a((String)object, socialChannel, performanceStatus, ensemble, (String)string2, object2);
            } else {
                SingAnalytics.a(Analytics.e, (String)this.R.key);
            }
            object2 = this.P != null ? ShareUtils.b((Context)this, (PerformanceV2)this.P) : ShareUtils.a((Context)this, (ArrangementVersionLite)this.R);
            object2 = this.P != null ? new StatusUpdate(MessageFormat.format((String)object2, this.P.title, this.P.y())) : new StatusUpdate(MessageFormat.format((String)object2, this.R.name, this.R.b()));
            object = MiscUtils.a();
            if (object != null) {
                object.a((StatusUpdate)object2, this.N);
            }
        }
        this.w();
    }

    protected void w() {
        this.finish();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void x() {
        if (this.isFinishing()) {
            return;
        }
        this.N = new MagicTwitter.TwitterOnPostCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a() {
                ShareActivity shareActivity = ShareActivity.this;
                ShareActivity shareActivity2 = ShareActivity.this;
                int n = ShareActivity.this.A() ? 2131297435 : 2131297434;
                Toaster.a((Context)shareActivity, shareActivity2.getString(n));
            }

            @Override
            public void a(Exception exception) {
                Toaster.a((Context)ShareActivity.this, ShareActivity.this.getString(2131297395));
            }
        };
        this.O = new MagicFacebook(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a() {
                ShareActivity shareActivity = ShareActivity.this;
                ShareActivity shareActivity2 = ShareActivity.this;
                int n = ShareActivity.this.A() ? 2131297435 : 2131297434;
                Toaster.a((Context)shareActivity, shareActivity2.getString(n));
            }

            @Override
            public void b() {
                Toaster.a((Context)ShareActivity.this, ShareActivity.this.getString(2131297395));
            }
        };
        Object object = this.getPackageManager();
        if (ChatUtils.a() && this.P != null) {
            this.F = ChatShareInviteActivity.a((Context)this, this.P, this.T);
        }
        if (this.F == null) {
            this.n.setVisibility(8);
        }
        if (object != null) {
            String string2;
            String string3;
            object = this.P != null ? ShareUtils.a((Context)this, (PerformanceV2)this.P) : ShareUtils.b((Context)this, (ArrangementVersionLite)this.R);
            this.G = ShareUtils.c((String)object);
            if (this.G == null) {
                this.m.removeView(this.p);
            }
            this.I = ShareUtils.d((String)object);
            if (this.I == null) {
                this.m.removeView(this.o);
            }
            this.J = ShareUtils.e((String)object);
            if (this.J == null) {
                this.m.removeView(this.r);
            }
            if (this.P != null) {
                string2 = ShareUtils.c((Context)this, (PerformanceV2)this.P);
                string3 = ShareUtils.d((Context)this, (PerformanceV2)this.P);
            } else {
                string2 = ShareUtils.c((Context)this, (ArrangementVersionLite)this.R);
                string3 = ShareUtils.d((Context)this, (ArrangementVersionLite)this.R);
            }
            this.K = ShareUtils.a((String)string2, (String)string3);
            if (this.K == null) {
                this.m.removeView(this.s);
            }
            this.H = ShareUtils.b((String)object);
            if (this.H == null || !this.b(Analytics.o)) {
                this.m.removeView(this.q);
            }
            if (this.D()) {
                this.B();
                object = ShareUtils.f((Context)this);
                this.a((Uri)object);
                this.b((Uri)object);
            } else {
                this.m.removeView(this.t);
                this.m.removeView(this.v);
                this.m.removeView(this.u);
            }
        }
        this.ad = this.G();
        this.I();
    }

}

