/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.AnimatorListenerAdapter
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.os.Bundle
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity$StartupMode
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_
 *  com.smule.singandroid.singflow.pre_sing.PreSingActivity_$IntentBuilder_
 *  com.smule.singandroid.task.SongDownloadTask
 *  com.smule.singandroid.task.SongDownloadTask$DownloadListener
 *  com.smule.singandroid.task.SongDownloadTask$DownloadProgressListener
 *  com.smule.singandroid.utils.AppIndexer
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  com.smule.singandroid.utils.SwipeDismissTouchListener
 *  com.smule.singandroid.utils.SwipeDismissTouchListener$DismissCallbacks
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.FragmentArg
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 *  org.androidannotations.api.builder.PostActivityStarter
 */
package com.smule.singandroid.mediaplaying;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.MasterActivity;
import com.smule.singandroid.SingCoreBridge;
import com.smule.singandroid.customviews.CustomToolbar;
import com.smule.singandroid.dialogs.ReportSongDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.UserFollowListItem;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.MediaPlayingFragment;
import com.smule.singandroid.mediaplaying.PreviewFragment_;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.onboarding.OnboardingActivity;
import com.smule.singandroid.profile.ProfileFragment;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity;
import com.smule.singandroid.singflow.pre_sing.PreSingActivity_;
import com.smule.singandroid.task.SongDownloadTask;
import com.smule.singandroid.utils.AppIndexer;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SwipeDismissTouchListener;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.builder.PostActivityStarter;

@EFragment
public class PreviewFragment
extends MediaPlayingFragment {
    private static final String ac = PreviewFragment.class.getName();
    @FragmentArg
    protected SongbookEntry P;
    @FragmentArg
    protected boolean Q;
    @FragmentArg
    protected Analytics R;
    @ViewById
    ImageView S;
    @ViewById
    protected ImageView T;
    @ViewById
    protected ImageView U;
    @ViewById
    protected TextView V;
    @ViewById
    protected ProgressBar W;
    @ViewById
    protected TextView X;
    @ViewById
    protected UserFollowListItem Y;
    @ViewById
    protected View Z;
    @ViewById
    protected ImageView aa;
    @ViewById
    protected Button ab;
    private AppIndexer ad = new AppIndexer();
    private AccountIcon ae;

    public static PreviewFragment a(SongbookEntry songbookEntry, boolean bl, boolean bl2, Analytics searchTarget) {
        return PreviewFragment_.aa().a(songbookEntry).b(bl).a(bl2).a(searchTarget).a();
    }

    private boolean aa() {
        return this.getActivity() instanceof OnboardingActivity;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void L() {
        final int n = 8;
        if (this.K()) {
            return;
        }
        Log.b(ac, "previewSongMiniBar - begin");
        if (this.P.k()) {
            this.W.setVisibility(0);
            this.X.setVisibility(8);
            this.aa.setVisibility(8);
            this.V.setVisibility(8);
            n = this.e;
            SongDownloadTask songDownloadTask = new SongDownloadTask((Context)this.getActivity(), this.P, null, new SongDownloadTask.DownloadListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void a(boolean bl, SongbookEntry object, PerformanceV2 object2) {
                    if (bl) {
                        PreviewFragment.this.P = object;
                    }
                    if (!PreviewFragment.this.a(n)) {
                        return;
                    }
                    if (bl) {
                        object2 = object.l().get("main");
                        if (object2 != null) {
                            object = "";
                            for (Lyric lyric : SingCoreBridge.getLyricsForMidi((String)object2)) {
                                object2 = object;
                                if (lyric.e) {
                                    object2 = (String)object + "\n";
                                }
                                object = (String)object2 + lyric.a;
                            }
                            PreviewFragment.this.V.setText((CharSequence)object);
                            PreviewFragment.this.X.setVisibility(8);
                            PreviewFragment.this.aa.setVisibility(8);
                            PreviewFragment.this.V.setVisibility(0);
                        } else {
                            Log.d(ac, "Downloading resource for role, \"main\" returned a null file.");
                            PreviewFragment.this.X.setText((CharSequence)PreviewFragment.this.getString(2131297189));
                            PreviewFragment.this.X.setVisibility(0);
                            PreviewFragment.this.aa.setVisibility(0);
                            PreviewFragment.this.aa.setImageDrawable(PreviewFragment.this.getResources().getDrawable(2130837955));
                            PreviewFragment.this.V.setVisibility(8);
                        }
                    } else {
                        PreviewFragment.this.X.setText((CharSequence)PreviewFragment.this.getString(2131297189));
                        PreviewFragment.this.X.setVisibility(0);
                        PreviewFragment.this.aa.setVisibility(0);
                        PreviewFragment.this.aa.setImageDrawable(PreviewFragment.this.getResources().getDrawable(2130837955));
                        PreviewFragment.this.V.setVisibility(8);
                    }
                    PreviewFragment.this.W.setVisibility(8);
                    object = PreviewFragment.this.ab;
                    int n2 = bl && PreviewFragment.this.X() ? 0 : 8;
                    object.setVisibility(n2);
                }
            }, null);
            songDownloadTask.a();
            songDownloadTask.execute((Object[])new Void[0]);
        } else {
            this.W.setVisibility(8);
            this.X.setVisibility(0);
            this.X.setText(2131297190);
            this.aa.setVisibility(0);
            this.aa.setImageDrawable(this.getResources().getDrawable(2130837960));
            this.V.setVisibility(8);
            Button button = this.ab;
            if (this.X()) {
                n = 0;
            }
            button.setVisibility(n);
        }
        this.a(MediaPlayingFragment.AnimationDirection.a, null);
    }

    public String S() {
        if (this.P != null) {
            return this.P.c();
        }
        return null;
    }

    public boolean T() {
        return this.Q;
    }

    protected void U() {
        if (this.T()) {
            ((MediaPlayingActivity)this.getActivity()).ag();
            this.g.setLeftButtonDrawable(this.getResources().getDrawable(2130837914));
            this.Q = false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    protected void V() {
        this.g.setLeftButtonOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (!PreviewFragment.this.Q) {
                    PreviewFragment.this.W();
                    return;
                }
                PreviewFragment.this.getActivity().onKeyDown(4, null);
            }
        });
        this.g.a(this.P, null, true);
        String string2 = this.P.e();
        this.t.setText((CharSequence)string2);
        if (this.P.f() == null) {
            this.u.setVisibility(8);
        } else {
            this.u.setText((CharSequence)this.P.f());
        }
        this.g.setRightButtonText(this.getString(2131296720));
        this.g.setRightButtonOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.c((SongbookEntry)PreviewFragment.this.P);
                if (PreviewFragment.this.R != null) {
                    com.smule.android.logging.Analytics.a(PreviewFragment.this.R, Analytics.e, Analytics.c, SongbookEntryUtils.d((SongbookEntry)PreviewFragment.this.P), null, 0, null, SongbookEntryUtils.e((SongbookEntry)PreviewFragment.this.P), null, 1, 0);
                }
                PreSingActivity.a((Context)PreviewFragment.this.getActivity()).a(PreSingActivity.StartupMode.a).a(PreviewFragment.this.P).a();
            }
        });
        this.U.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                object = new TextAlertDialog((Context)PreviewFragment.this.getActivity(), "GOOGLE PLAY", "Buy \"" + PreviewFragment.this.P.e() + "\" on Google Play?");
                object.a(new Runnable(){

                    @Override
                    public void run() {
                        String string2 = URLEncoder.encode(PreviewFragment.this.P.e(), "UTF-8");
                        try {
                            PreviewFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse((String)("http://play.google.com/store/search?q=" + string2 + "&c=music"))));
                            return;
                        }
                        catch (Exception exception) {
                            try {
                                PreviewFragment.this.a("Google Play Store not installed!", Toaster.b);
                                return;
                            }
                            catch (UnsupportedEncodingException unsupportedEncodingException) {
                                Log.e(ac, "UnsupportedEncodingException thrown trying to create Google Play purchase URL");
                                return;
                            }
                        }
                    }
                });
                object.show();
            }

        });
        if (this.P.t()) {
            if (((ArrangementVersionLiteEntry)this.P).a.songId == null) {
                this.U.setVisibility(4);
            }
            this.ae = ((ArrangementVersionLiteEntry)this.P).a.accountIcon;
            this.Y.a(this.ae, this.getActivity(), false, new UserFollowListItem.UserFollowListItemListener(){

                @Override
                public void a(Analytics searchResultClkContext) {
                }

                @Override
                public void a(ProfileFragment profileFragment) {
                    PreviewFragment.this.U();
                    PreviewFragment.this.p().a(profileFragment, profileFragment.t());
                    PreviewFragment.this.W();
                }

                @Override
                public void a(boolean bl, boolean bl2) {
                }
            });
        } else {
            this.Y.setVisibility(8);
            this.Z.setVisibility(8);
        }
        this.ab.setVisibility(8);
        string2 = SongbookEntryUtils.a((SongbookEntry)this.P);
        SimpleImageLoadingListener simpleImageLoadingListener = new SimpleImageLoadingListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void a(String string2, View view, Bitmap bitmap) {
                int n;
                int n2;
                block3 : {
                    block2 : {
                        if (bitmap == null || !PreviewFragment.this.isAdded()) break block2;
                        n2 = (int)((double)bitmap.getWidth() * 0.025);
                        n = (int)((double)bitmap.getHeight() * 0.025);
                        if (n2 > 0 && n > 0) break block3;
                    }
                    return;
                }
                string2 = Bitmap.createScaledBitmap((Bitmap)bitmap, (int)n2, (int)n, (boolean)true);
                PreviewFragment.this.S.setImageDrawable((Drawable)new BitmapDrawable(PreviewFragment.this.getResources(), (Bitmap)string2));
            }
        };
        ImageUtils.a(string2, this.T, 2130837895, (ImageLoadingListener)simpleImageLoadingListener);
        if (this.Q) {
            this.g.setLeftButtonDrawable(this.getResources().getDrawable(2130837604));
            this.L();
        }
        this.y.setOnTouchListener((View.OnTouchListener)new SwipeDismissTouchListener(this.y, (Object)null, new SwipeDismissTouchListener.DismissCallbacks(){

            public void a(View view, Object object) {
                if (PreviewFragment.this.getActivity() != null) {
                    ((MediaPlayingActivity)PreviewFragment.this.getActivity()).c(false);
                }
            }

            public boolean a(Object object) {
                return true;
            }
        }, new View.OnClickListener(){

            public void onClick(View view) {
                PreviewFragment.this.L();
            }
        }));
        this.t();
    }

    @UiThread
    public void W() {
        if (!this.isAdded()) {
            return;
        }
        Log.b(ac, "lowerFragment - begin");
        this.a(MediaPlayingFragment.AnimationDirection.e, null);
    }

    protected boolean X() {
        if (this.ae != null) {
            if (this.P.t() && !this.ae.d()) {
                return true;
            }
            return false;
        }
        return this.P.t();
    }

    @Click
    protected void Y() {
        Log.c(ac, "Showing report song dialog!");
        new ReportSongDialog(this.getActivity()).show();
    }

    @Click
    protected void a(View view) {
        if (this.isAdded()) {
            this.P();
        }
    }

    @Override
    protected boolean a() {
        return false;
    }

    @Override
    public boolean a(int n, KeyEvent keyEvent) {
        Log.b(ac, "onFragmentKeyDown - mIsInFullMode: " + this.J);
        if (n == 4) {
            if (this.Q) {
                this.J = false;
                this.b(this);
                return true;
            }
            if (this.J) {
                this.W();
                return true;
            }
        }
        return false;
    }

    @Override
    protected void e(MediaPlayerServiceController mediaPlayerServiceController, String string2) {
        super.e(mediaPlayerServiceController, string2);
        this.aa.setVisibility(0);
        this.aa.setImageDrawable(this.getResources().getDrawable(2130837955));
        this.V.setVisibility(8);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ad.a((Context)this.getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onResume() {
        Activity activity;
        super.onResume();
        if (this.x.c(this.P.c())) {
            Log.b(ac, "Entry with id, " + this.P.c() + ", is already queued; no more setup required");
        } else {
            SongbookEntry songbookEntry = this.P;
            boolean bl = !this.aa();
            QueueItem queueItem = new QueueItem(songbookEntry, null, bl);
            this.x.a(queueItem, this.B);
        }
        if ((activity = this.getActivity()) instanceof MasterActivity) {
            ((MasterActivity)activity).G();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        this.ad.d(this.P);
    }

    @Override
    public void onStop() {
        this.ad.e(this.P);
        super.onStop();
    }

    @Override
    public String x() {
        return ac;
    }

}

