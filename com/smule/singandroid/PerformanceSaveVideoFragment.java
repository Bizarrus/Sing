/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.Rect
 *  android.media.MediaMetadataRetriever
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.text.Editable
 *  android.view.KeyEvent
 *  android.view.TextureView
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceCreateUtil
 *  com.smule.singandroid.utils.PerformanceCreateUtil$Creator
 *  com.smule.singandroid.utils.PerformanceCreateUtil$PerformanceCreateListener
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.video.ExoPlayerWrapper
 *  com.smule.singandroid.video.ExoPlayerWrapper$ExoPlayerInternalErrorListener
 *  com.smule.singandroid.video.ExoPlayerWrapper$ExoPlayerStateChangeListener
 *  com.smule.singandroid.video.ExoPlayerWrapperBuilder
 *  com.smule.singandroid.video.GetAudioTimeCallback
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$ColorFilterType
 *  com.smule.singandroid.video.VideoEffects$Intensity
 *  com.smule.singandroid.video.VideoEffects$VideoStyleType
 *  com.smule.singandroid.video.VideoFilterManager
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.video.ExoPlayerWrapper;
import com.smule.singandroid.video.ExoPlayerWrapperBuilder;
import com.smule.singandroid.video.GetAudioTimeCallback;
import com.smule.singandroid.video.VideoEffects;
import com.smule.singandroid.video.VideoFilterManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PerformanceSaveVideoFragment
extends PerformanceSaveFragment
implements SeekBar.OnSeekBarChangeListener {
    private static final String aI = PerformanceSaveVideoFragment.class.getName();
    @ViewById
    protected ViewGroup aA;
    @ViewById
    protected ViewGroup aB;
    @ViewById
    protected ViewGroup aC;
    @ViewById
    protected TextureView aD;
    @InstanceState
    protected String aE;
    protected WeakListener.OnGlobalLayoutListener aF;
    protected ExoPlayerWrapper aG;
    private MediaMetadataRetriever aJ;
    private float aK;
    private float aL;
    @ViewById
    protected SeekBar aw;
    @ViewById
    protected RelativeLayout ax;
    @ViewById
    protected View ay;
    @ViewById
    protected View az;

    public PerformanceSaveVideoFragment() {
        this.aF = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                float f;
                Rect rect;
                block5 : {
                    float f2;
                    block4 : {
                        block6 : {
                            block2 : {
                                block3 : {
                                    f = 0.0f;
                                    if (!PerformanceSaveVideoFragment.this.isAdded()) break block2;
                                    rect = new Rect();
                                    PerformanceSaveVideoFragment.this.aC.getWindowVisibleDisplayFrame(rect);
                                    if ((float)(PerformanceSaveVideoFragment.this.aC.getRootView().getHeight() - rect.height()) <= (float)(PerformanceSaveVideoFragment.this.aC.getRootView().getHeight() / 4)) break block3;
                                    Log.b(aI, "kbd up");
                                    if (PerformanceSaveVideoFragment.this.az.getVisibility() == 0) break block2;
                                    f2 = PerformanceSaveVideoFragment.this.aA.getBottom() - PerformanceSaveVideoFragment.this.ax.getBottom();
                                    f2 = (float)(rect.height() - PerformanceSaveVideoFragment.this.aB.getHeight()) - f2;
                                    if (f2 >= 0.0f) break block4;
                                    break block5;
                                }
                                Log.b(aI, "kbd down");
                                if (PerformanceSaveVideoFragment.this.az.getVisibility() != 8) break block6;
                            }
                            return;
                        }
                        PerformanceSaveVideoFragment.this.az.setVisibility(8);
                        rect = (RelativeLayout.LayoutParams)PerformanceSaveVideoFragment.this.aA.getLayoutParams();
                        rect.addRule(3, 2131756258);
                        PerformanceSaveVideoFragment.this.aA.setLayoutParams((ViewGroup.LayoutParams)rect);
                        return;
                    }
                    f = f2;
                }
                rect = PerformanceSaveVideoFragment.this.az.getLayoutParams();
                rect.height = (int)f;
                PerformanceSaveVideoFragment.this.az.setLayoutParams((ViewGroup.LayoutParams)rect);
                PerformanceSaveVideoFragment.this.az.setVisibility(0);
                rect = (RelativeLayout.LayoutParams)PerformanceSaveVideoFragment.this.aA.getLayoutParams();
                rect.addRule(3, 2131756260);
                PerformanceSaveVideoFragment.this.aA.setLayoutParams((ViewGroup.LayoutParams)rect);
            }
        });
    }

    private void a(Context context, Bitmap bitmap) {
        if (bitmap != null) {
            ImageToDiskUtils.b((Context)context, (String)"SOLO_VIDEO_ALBUM_ART_BITMAP");
            ImageToDiskUtils.a((Context)context, (String)"SOLO_VIDEO_ALBUM_ART_BITMAP", (Bitmap)bitmap);
        }
    }

    @Override
    protected boolean K() {
        if (this.aE != null && !this.aE.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    protected void L() {
        if (this.ag || this.ac()) {
            super.L();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void W() {
        boolean bl = false;
        String string2 = null;
        if (this.F) {
            return;
        }
        String string3 = this.l.getText().toString();
        if (this.ap) {
            if (!this.ag && string3.length() == 0) {
                this.b(this.getResources().getString(2131297101));
                return;
            }
        } else if (string3.length() == 0) {
            string3 = this.I();
        }
        Object object = this.J();
        Analytics userPath = this.an.o ? Analytics.c : Analytics.d;
        AudioDefs.HeadphonesType headphonesType = AudioDefs.HeadphonesType.a(this.L, this.M);
        String string4 = this.P;
        boolean bl2 = this.ag;
        Analytics ensemble = this.an.b.a();
        String string5 = this.V();
        Object object2 = this.an.f != null ? Boolean.valueOf(this.an.f.video) : null;
        SingAnalytics.a((String)object, userPath, (AudioDefs.HeadphonesType)headphonesType, (String)string4, (boolean)bl2, ensemble, (String)string5, (Boolean)object2, (boolean)this.K());
        this.U();
        userPath = this.aq == null && this.J != null ? ImageToDiskUtils.a((Context)this.getActivity(), (String)this.J) : (this.aq != null ? this.aq : null);
        this.a((Context)this.getActivity(), (Bitmap)userPath);
        Log.b(aI, "createPerformance - performance title is: " + string3);
        if (this.an.x != null) {
            object2 = this.an.x;
        } else {
            MagicCrittercism.a(new Exception("noMetaDataFoundException4"));
            object2 = null;
        }
        object = new PerformanceCreateUtil.PerformanceCreateListener(){

            public void a(NetworkResponse networkResponse) {
                PerformanceSaveVideoFragment.this.a(networkResponse);
            }

            public void a(PerformanceV2 performanceV2, String string2, String string3) {
                PerformanceSaveVideoFragment.this.a(performanceV2);
            }

            public void a(ArrayList<PerformanceManager.PerformanceResourceInfo> arrayList) {
                PerformanceSaveVideoFragment.this.C.i = arrayList;
                PerformanceSaveVideoFragment.this.X();
            }

            public void b(NetworkResponse networkResponse) {
                PerformanceSaveVideoFragment.this.b(networkResponse);
            }
        };
        headphonesType = new PerformanceCreateUtil.Creator();
        string4 = headphonesType.a(this.ao).a(this.getActivity()).a(this.an.e()).b(this.an.c()).c(this.an.d()).a(this.an.g);
        if (this.ae.t()) {
            string2 = this.ae.c();
        }
        string2 = string4.a(string2);
        int n = this.ae.t() ? ((ArrangementVersionLiteEntry)this.ae).a.version : 0;
        userPath = string2.b(n).b(this.an.j).c(string3).c(this.ab).a(this.P, this.S).a(this.W).b(this.X).a(this.ac).d(this.K).e(this.L).d(this.o.getText().toString()).a((Bitmap)userPath).e(this.aE).a((Metadata)object2).f(this.an.o).f(this.an.h()).g(this.an.i()).h(this.an.j().b()).g(this.an.k()).h(this.an.l()).i(this.an.m()).j(this.Y).k(this.M);
        bl2 = bl;
        if (this.an.f != null) {
            bl2 = bl;
            if (this.an.f.boost) {
                bl2 = true;
            }
        }
        userPath.l(bl2).m(this.an.b()).a((PerformanceCreateUtil.PerformanceCreateListener)object);
        headphonesType.a(this.ar);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void a() {
        Object object;
        super.a();
        LayoutUtils.a((View)this.aC, (WeakListener.OnGlobalLayoutListener)this.aF);
        if (!this.ag) {
            String string2;
            String string3;
            long l;
            boolean bl;
            this.aw.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener)this);
            if (this.ad == PerformanceSaveFragment.Mode.a) {
                this.o.setOnEditorActionListener(new TextView.OnEditorActionListener(){

                    public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
                        if (n == 6) {
                            PerformanceSaveVideoFragment.this.ab();
                        }
                        return false;
                    }
                });
            }
            object = this.aE;
            if (this.ad == PerformanceSaveFragment.Mode.b) {
                object = this.N.videoRenderedUrl;
                bl = this.N.i();
            } else {
                bl = this.an.k();
            }
            VideoEffects.Intensity intensity = VideoEffects.Intensity.a;
            boolean bl2 = new DeviceSettings().r();
            boolean bl3 = new SingServerValues().M().isEmpty();
            if (this.ad == PerformanceSaveFragment.Mode.a && bl2 && !bl3) {
                string3 = this.an.i();
                string2 = this.an.h();
                intensity = this.an.j();
            } else {
                string2 = null;
                string3 = null;
            }
            if (string2 != null && VideoEffects.b((String)string2) != VideoEffects.VideoStyleType.a && (l = MagicPreferences.b((Context)this.getActivity(), "VIDEO_STYLE_SHOW_BANNER_COUNT", 0)) < 2) {
                MagicPreferences.a((Context)this.getActivity(), "VIDEO_STYLE_SHOW_BANNER_COUNT", l + 1);
            }
            string3 = VideoEffects.a((String)string3);
            string2 = VideoEffects.b((String)string2);
            this.aG = new ExoPlayerWrapper(new ExoPlayerWrapperBuilder((Context)this.getActivity(), this.aD, new Handler(Looper.getMainLooper()), (String)object, new GetAudioTimeCallback(){

                public float a() {
                    return 0.0f;
                }
            }, Float.MAX_VALUE, 0.0f, null, new ExoPlayerWrapper.ExoPlayerStateChangeListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void a(int n) {
                    if (!PerformanceSaveVideoFragment.this.isAdded() || n != 4 || PerformanceSaveVideoFragment.this.aD.getAlpha() != 1.0f) {
                        return;
                    }
                    PerformanceSaveVideoFragment.this.aG.c();
                    PerformanceSaveVideoFragment.this.a(new Runnable(){

                        @Override
                        public void run() {
                            if (!PerformanceSaveVideoFragment.this.isAdded()) {
                                return;
                            }
                            PerformanceSaveVideoFragment.this.aq = PerformanceSaveVideoFragment.this.aD.getBitmap(400, 400);
                        }
                    }, 300);
                }

            }).a((VideoEffects.VideoStyleType)string2).a((VideoEffects.ColorFilterType)string3).a(intensity).a(VideoFilterManager.b()).b(bl));
            this.onProgressChanged(this.aw, 0, true);
        }
        if (this.an != null && this.an.b()) {
            this.o.setText((CharSequence)this.getResources().getString(2131297742));
        }
        if (this.ad == PerformanceSaveFragment.Mode.a) {
            this.m.setAlpha(0.0f);
            this.aD.setAlpha(1.0f);
            return;
        }
        object = this.m;
        float f = this.ac() ? 1.0f : 0.0f;
        object.setAlpha(f);
        object = this.aD;
        f = this.ac() ? 0.0f : 1.0f;
        object.setAlpha(f);
    }

    @Override
    protected void a(PerformanceV2 performanceV2) {
        super.a(performanceV2);
        if (this.aE != null && !this.aE.isEmpty()) {
            SingAnalytics.a((String)this.D, (float)this.an.b("VIDEO_STATS_CAMERA_FPS", 0.0f), (float)this.an.b("VIDEO_STATS_ENCODER_FPS", 0.0f), (int)this.an.b("VIDEO_STATS_CAMERA_TO_ENCODER_DROPS", 0), (float)this.an.b("VIDEO_STATS_MUXER_FPS", 0.0f), (int)this.an.b("VIDEO_STATS_ENCODER_TO_MUXER_DROPS", 0));
        }
    }

    @Override
    protected void aa() {
        this.m.setAlpha(1.0f);
        this.aD.setAlpha(0.0f);
    }

    @Click
    protected void ab() {
        MiscUtils.a((View)this.o, (boolean)true);
    }

    protected boolean ac() {
        if (this.N != null && this.N.coverUrl != null) {
            return true;
        }
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.aE = this.ah.getString("VIDEO_FILE");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.aJ != null) {
            this.aJ.release();
            this.aJ = null;
        }
        if (this.aC != null) {
            LayoutUtils.b((View)this.aC, (WeakListener.OnGlobalLayoutListener)this.aF);
        }
        if (this.aG != null) {
            this.aG.d();
            this.aG = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (this.aG != null) {
            this.aG.a(this.aL);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
        this.m.setAlpha(0.0f);
        this.aD.setAlpha(1.0f);
        this.aK = this.aG.i();
        this.aL = (float)n / (float)this.aw.getMax() * this.aK;
        this.aG.b(this.aL);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.aG != null) {
            this.aG.e();
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

}

