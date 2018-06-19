package com.smule.singandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.SearchResultClkContext;
import com.smule.android.logging.Analytics.SearchResultClkValue;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.dialogs.ReportSongDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.UserFollowItem;
import com.smule.singandroid.list_items.UserFollowItem.UserFollowItemListener;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.media_player_service.QueueItem;
import com.smule.singandroid.models.Lyric;
import com.smule.singandroid.pre_sing.PreSingActivity;
import com.smule.singandroid.pre_sing.PreSingActivity.StartupMode;
import com.smule.singandroid.task.SongDownloadTask;
import com.smule.singandroid.task.SongDownloadTask.DownloadListener;
import com.smule.singandroid.utils.AppIndexer;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import com.smule.singandroid.utils.SwipeDismissTouchListener;
import com.smule.singandroid.utils.SwipeDismissTouchListener.DismissCallbacks;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PreviewFragment extends MediaPlayingFragment {
    private static final String f19391T = PreviewFragment.class.getName();
    @FragmentArg
    protected SongbookEntry f19392G;
    @FragmentArg
    protected boolean f19393H;
    @FragmentArg
    protected SearchTarget f19394I;
    @ViewById
    ImageView f19395J;
    @ViewById
    protected ImageView f19396K;
    @ViewById
    protected ImageView f19397L;
    @ViewById
    protected TextView f19398M;
    @ViewById
    protected ProgressBar f19399N;
    @ViewById
    protected TextView f19400O;
    @ViewById
    protected UserFollowItem f19401P;
    @ViewById
    protected View f19402Q;
    @ViewById
    protected ImageView f19403R;
    @ViewById
    protected Button f19404S;
    private AppIndexer f19405U = new AppIndexer();
    private AccountIcon f19406V;

    class C39861 implements OnClickListener {
        final /* synthetic */ PreviewFragment f19381a;

        C39861(PreviewFragment previewFragment) {
            this.f19381a = previewFragment;
        }

        public void onClick(View view) {
            if (this.f19381a.f19393H) {
                this.f19381a.getActivity().onKeyDown(4, null);
            } else {
                this.f19381a.mo6548O();
            }
        }
    }

    class C39872 implements OnClickListener {
        final /* synthetic */ PreviewFragment f19382a;

        C39872(PreviewFragment previewFragment) {
            this.f19382a = previewFragment;
        }

        public void onClick(View view) {
            SingAnalytics.m26133c(this.f19382a.f19392G);
            if (this.f19382a.f19394I != null) {
                Analytics.m17854a(this.f19382a.f19394I, SearchResultClkContext.SING, SearchResultClkValue.NOWPLAYING, SongbookEntryUtils.m26167b(this.f19382a.f19392G), null, Integer.valueOf(0), null, SongbookEntryUtils.m26168c(this.f19382a.f19392G), null, 1, 0);
            }
            PreSingActivity.m24763a(this.f19382a.getActivity()).m24796a(StartupMode.DEFAULT).m24794a(this.f19382a.f19392G).a();
        }
    }

    class C39893 implements OnClickListener {
        final /* synthetic */ PreviewFragment f19384a;

        class C39881 implements Runnable {
            final /* synthetic */ C39893 f19383a;

            C39881(C39893 c39893) {
                this.f19383a = c39893;
            }

            public void run() {
                try {
                    try {
                        this.f19383a.f19384a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=" + URLEncoder.encode(this.f19383a.f19384a.f19392G.mo6291e(), "UTF-8") + "&c=music")));
                    } catch (Exception e) {
                        this.f19383a.f19384a.m19841a("Google Play Store not installed!", Toaster$Duration.LONG);
                    }
                } catch (UnsupportedEncodingException e2) {
                    Log.e(PreviewFragment.f19391T, "UnsupportedEncodingException thrown trying to create Google Play purchase URL");
                }
            }
        }

        C39893(PreviewFragment previewFragment) {
            this.f19384a = previewFragment;
        }

        public void onClick(View view) {
            TextAlertDialog textAlertDialog = new TextAlertDialog(this.f19384a.getActivity(), "GOOGLE PLAY", "Buy \"" + this.f19384a.f19392G.mo6291e() + "\" on Google Play?");
            textAlertDialog.m19804a(new C39881(this));
            textAlertDialog.show();
        }
    }

    class C39904 implements UserFollowItemListener {
        final /* synthetic */ PreviewFragment f19385a;

        C39904(PreviewFragment previewFragment) {
            this.f19385a = previewFragment;
        }

        public void mo6457a(boolean z, boolean z2) {
        }

        public void mo6456a(ProfileFragment profileFragment) {
            this.f19385a.m20946M();
            this.f19385a.m19862m().a(profileFragment, profileFragment.mo6514z());
            this.f19385a.mo6548O();
        }

        public void mo6455a(SearchResultClkContext searchResultClkContext) {
        }
    }

    class C39915 extends SimpleImageLoadingListener {
        final /* synthetic */ PreviewFragment f19386a;

        C39915(PreviewFragment previewFragment) {
            this.f19386a = previewFragment;
        }

        public void mo6155a(String str, View view, Bitmap bitmap) {
            if (bitmap != null && this.f19386a.isAdded()) {
                int width = (int) (((double) bitmap.getWidth()) * 0.025d);
                int height = (int) (((double) bitmap.getHeight()) * 0.025d);
                if (width > 0 && height > 0) {
                    this.f19386a.f19395J.setImageDrawable(new BitmapDrawable(this.f19386a.getResources(), Bitmap.createScaledBitmap(bitmap, width, height, true)));
                }
            }
        }
    }

    class C39926 implements DismissCallbacks {
        final /* synthetic */ PreviewFragment f19387a;

        C39926(PreviewFragment previewFragment) {
            this.f19387a = previewFragment;
        }

        public boolean mo6540a(Object obj) {
            return true;
        }

        public void mo6539a(View view, Object obj) {
            if (this.f19387a.getActivity() != null) {
                ((MediaPlayingActivity) this.f19387a.getActivity()).c(false);
            }
        }
    }

    class C39937 implements OnClickListener {
        final /* synthetic */ PreviewFragment f19388a;

        C39937(PreviewFragment previewFragment) {
            this.f19388a = previewFragment;
        }

        public void onClick(View view) {
            this.f19388a.mo6542G();
        }
    }

    public String mo6383s() {
        return f19391T;
    }

    public static PreviewFragment m20942a(SongbookEntry songbookEntry, boolean z, boolean z2, SearchTarget searchTarget) {
        return PreviewFragment_.m20960S().m20957a(songbookEntry).m20959b(z).m20958a(z2).m20956a(searchTarget).m20955a();
    }

    public String m20944K() {
        return this.f19392G != null ? this.f19392G.mo6289c() : null;
    }

    public boolean m20945L() {
        return this.f19393H;
    }

    protected void m20946M() {
        if (m20945L()) {
            ((MediaPlayingActivity) getActivity()).X();
            this.e.setLeftButtonDrawable(getResources().getDrawable(C1947R.drawable.icn_expand_arrow));
            this.f19393H = false;
        }
    }

    protected boolean mo6543a() {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f19405U.m25803a(getActivity());
    }

    @AfterViews
    protected void m20947N() {
        this.e.setLeftButtonOnClickListener(new C39861(this));
        this.e.m23102a(this.f19392G, null, true);
        this.m.setText(this.f19392G.mo6291e());
        if (this.f19392G.mo6292f() == null) {
            this.n.setVisibility(8);
        } else {
            this.n.setText(this.f19392G.mo6292f());
        }
        this.e.setRightButtonText(getString(C1947R.string.core_sing));
        this.e.setRightButtonOnClickListener(new C39872(this));
        this.f19397L.setOnClickListener(new C39893(this));
        if (this.f19392G.m18772r()) {
            if (((ArrangementVersionLiteEntry) this.f19392G).f17623a.songId == null) {
                this.f19397L.setVisibility(4);
            }
            this.f19406V = ((ArrangementVersionLiteEntry) this.f19392G).f17623a.accountIcon;
            this.f19401P.m24448a(this.f19406V, getActivity(), false, new C39904(this));
        } else {
            this.f19401P.setVisibility(8);
            this.f19402Q.setVisibility(8);
        }
        this.f19404S.setVisibility(8);
        String a = SongbookEntryUtils.m26164a(this.f19392G, null);
        ImageLoadingListener c39915 = new C39915(this);
        ImageUtils.a(a, this.f19396K, C1947R.drawable.icn_default_album_large, c39915);
        ImageUtils.a(a, this.j, C1947R.drawable.icn_default_album_small, c39915);
        if (this.f19393H) {
            this.e.setLeftButtonDrawable(getResources().getDrawable(C1947R.drawable.back_grey));
            mo6542G();
        }
        this.r.setOnTouchListener(new SwipeDismissTouchListener(this.r, null, new C39926(this), new C39937(this)));
        m20394z();
    }

    public void onStart() {
        super.onStart();
        this.f19405U.m25806d(this.f19392G);
    }

    public void onResume() {
        super.onResume();
        if (this.q.m24662c(this.f19392G.mo6289c())) {
            Log.b(f19391T, "Entry with id, " + this.f19392G.mo6289c() + ", is already queued; no more setup required");
        } else {
            this.q.m24653a(new QueueItem(this.f19392G, null, !m20941S()), this.u);
        }
        Activity activity = getActivity();
        if (activity instanceof MasterActivity) {
            ((MasterActivity) activity).F();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        this.f19405U.m25807e(this.f19392G);
        super.onStop();
    }

    public boolean mo6544a(int i, KeyEvent keyEvent) {
        Log.b(f19391T, "onFragmentKeyDown - mIsInFullMode: " + this.A);
        if (i == 4) {
            if (this.f19393H) {
                this.A = false;
                m19847b((BaseFragment) this);
                return true;
            } else if (this.A) {
                mo6548O();
                return true;
            }
        }
        return false;
    }

    @UiThread
    public void mo6548O() {
        if (isAdded()) {
            Log.b(f19391T, "lowerFragment - begin");
            mo6829a(AnimationDirection.LOWER_SHOW_BOTTOM_MENU, null);
        }
    }

    public void mo6542G() {
        int i = 8;
        if (!m20373F()) {
            Log.b(f19391T, "previewSongMiniBar - begin");
            if (this.f19392G.mo6296j()) {
                this.f19399N.setVisibility(0);
                this.f19400O.setVisibility(8);
                this.f19403R.setVisibility(8);
                this.f19398M.setVisibility(8);
                final int i2 = this.d;
                SongDownloadTask songDownloadTask = new SongDownloadTask(getActivity(), this.f19392G, null, new DownloadListener(this) {
                    final /* synthetic */ PreviewFragment f19390b;

                    public void mo6541a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
                        if (z) {
                            this.f19390b.f19392G = songbookEntry;
                        }
                        if (this.f19390b.m19843a(i2)) {
                            int i;
                            if (z) {
                                String str = (String) songbookEntry.mo6297k().get("main");
                                if (str != null) {
                                    String str2 = "";
                                    for (Lyric lyric : SingCoreBridge.getLyricsForMidi(str)) {
                                        if (lyric.f23486e) {
                                            str2 = str2 + "\n";
                                        }
                                        str2 = str2 + lyric.f23482a;
                                    }
                                    this.f19390b.f19398M.setText(str2);
                                    this.f19390b.f19400O.setVisibility(8);
                                    this.f19390b.f19403R.setVisibility(8);
                                    this.f19390b.f19398M.setVisibility(0);
                                } else {
                                    Log.d(PreviewFragment.f19391T, "Downloading resource for role, \"main\" returned a null file.");
                                    this.f19390b.f19400O.setText(this.f19390b.getString(C1947R.string.preview_lyrics_error));
                                    this.f19390b.f19400O.setVisibility(0);
                                    this.f19390b.f19403R.setVisibility(0);
                                    this.f19390b.f19403R.setImageDrawable(this.f19390b.getResources().getDrawable(C1947R.drawable.icn_network_issues));
                                    this.f19390b.f19398M.setVisibility(8);
                                }
                            } else {
                                this.f19390b.f19400O.setText(this.f19390b.getString(C1947R.string.preview_lyrics_error));
                                this.f19390b.f19400O.setVisibility(0);
                                this.f19390b.f19403R.setVisibility(0);
                                this.f19390b.f19403R.setImageDrawable(this.f19390b.getResources().getDrawable(C1947R.drawable.icn_network_issues));
                                this.f19390b.f19398M.setVisibility(8);
                            }
                            this.f19390b.f19399N.setVisibility(8);
                            Button button = this.f19390b.f19404S;
                            if (z && this.f19390b.m20949P()) {
                                i = 0;
                            } else {
                                i = 8;
                            }
                            button.setVisibility(i);
                        }
                    }
                }, null);
                songDownloadTask.m25682a();
                songDownloadTask.execute(new Void[0]);
            } else {
                this.f19399N.setVisibility(8);
                this.f19400O.setVisibility(0);
                this.f19400O.setText(C1947R.string.preview_no_lyrics);
                this.f19403R.setVisibility(0);
                this.f19403R.setImageDrawable(getResources().getDrawable(C1947R.drawable.icn_nolyrics));
                this.f19398M.setVisibility(8);
                Button button = this.f19404S;
                if (m20949P()) {
                    i = 0;
                }
                button.setVisibility(i);
            }
            mo6829a(AnimationDirection.RAISE, null);
        }
    }

    protected boolean m20949P() {
        if (this.f19406V != null) {
            return this.f19392G.m18772r() && !this.f19406V.d();
        } else {
            return this.f19392G.m18772r();
        }
    }

    @Click
    protected void m20950Q() {
        Log.c(f19391T, "Showing report song dialog!");
        new ReportSongDialog(getActivity()).show();
    }

    protected void mo6545e(MediaPlayerServiceController mediaPlayerServiceController, String str) {
        super.mo6545e(mediaPlayerServiceController, str);
        this.f19403R.setVisibility(0);
        this.f19403R.setImageDrawable(getResources().getDrawable(C1947R.drawable.icn_network_issues));
        this.f19398M.setVisibility(8);
    }

    private boolean m20941S() {
        return getActivity() instanceof OnboardingActivity;
    }
}
