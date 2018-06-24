/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnShowListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.text.Editable
 *  android.text.Html
 *  android.text.TextUtils
 *  android.text.TextWatcher
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.widget.Button
 *  android.widget.CompoundButton
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.TextView
 *  android.widget.ToggleButton
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.NavigationUtils
 *  com.smule.singandroid.utils.PerformanceCreateUtil
 *  com.smule.singandroid.utils.PerformanceCreateUtil$Creator
 *  com.smule.singandroid.utils.PerformanceCreateUtil$PerformanceCreateListener
 *  com.smule.singandroid.utils.PerformanceCreateUtil$ResourceCompressionListener
 *  com.smule.singandroid.utils.PerformanceCreateUtil$ResourceUploadListener
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$AudioCompletionContext
 *  com.smule.singandroid.utils.SingAnalytics$InviteType
 *  com.smule.singandroid.utils.SingAnalytics$ReviewStepsType
 *  com.smule.singandroid.utils.SongbookEntryUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.CheckedChange
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.SupposeUiThread
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.android.audio.AudioDefs;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.InviteManager;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.TracksManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.SingUserProfile;
import com.smule.android.network.models.UserProfile;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.StringCacheManager;
import com.smule.android.utils.UiAwareRunnable;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.PerformanceSaveActivity;
import com.smule.singandroid.PerformanceSaveVideoFragment;
import com.smule.singandroid.PhotoTakingFragment;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.audio.AudioPower;
import com.smule.singandroid.audio.Metadata;
import com.smule.singandroid.customviews.BubbleTooltipViewWithDropShadow;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyScreenDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.ProgressBarDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.ImageToDiskUtils;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.NavigationUtils;
import com.smule.singandroid.utils.PerformanceCreateUtil;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SongbookEntryUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.CheckedChange;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.SupposeUiThread;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment
public class PerformanceSaveFragment
extends PhotoTakingFragment {
    public static final String g = PerformanceSaveFragment.class.getName();
    @ViewById
    protected BubbleTooltipViewWithDropShadow A;
    @ViewById
    protected BubbleTooltipViewWithDropShadow B;
    @InstanceState
    protected PostSingBundle C;
    @InstanceState
    protected String D = null;
    @InstanceState
    protected boolean E = false;
    @InstanceState
    protected boolean F = false;
    @InstanceState
    protected String G = null;
    @InstanceState
    protected boolean H = false;
    @InstanceState
    protected Long I = null;
    @InstanceState
    protected String J = null;
    @InstanceState
    protected boolean K = false;
    @InstanceState
    protected boolean L = false;
    @InstanceState
    protected boolean M = false;
    @InstanceState
    protected PerformanceV2 N;
    @InstanceState
    protected boolean O = false;
    @InstanceState
    protected String P;
    @InstanceState
    protected String Q;
    @InstanceState
    protected String R;
    @InstanceState
    protected int S;
    @InstanceState
    protected String T;
    @InstanceState
    protected String U;
    @InstanceState
    protected String V;
    @InstanceState
    protected Float W;
    @InstanceState
    protected Float X;
    @InstanceState
    protected boolean Y;
    @InstanceState
    protected String Z;
    private boolean aA = false;
    private BusyScreenDialog aB;
    private boolean aC;
    private final PerformanceCreateUtil.ResourceCompressionListener aD;
    private final PerformanceCreateUtil.ResourceUploadListener aE;
    private boolean aF;
    @InstanceState
    protected boolean aa;
    @InstanceState
    protected int ab;
    @InstanceState
    protected float ac;
    @InstanceState
    protected Mode ad;
    @InstanceState
    protected SongbookEntry ae;
    @InstanceState
    protected boolean af = false;
    @InstanceState
    protected boolean ag;
    @InstanceState
    protected Bundle ah;
    @InstanceState
    protected boolean ai;
    @InstanceState
    protected boolean aj;
    @InstanceState
    protected Integer ak = null;
    @InstanceState
    protected boolean al = false;
    @InstanceState
    protected String am = null;
    protected SingBundle an;
    protected Future<PerformanceManager.PreuploadResponse> ao;
    protected boolean ap;
    protected Bitmap aq;
    protected PerformanceCreateUtil ar;
    protected WeakListener.OnGlobalLayoutListener as;
    protected final Runnable at;
    protected final AreYouSureDialogListener au;
    protected final CustomAlertDialog.CustomAlertDialogListener av;
    private BusyDialog aw;
    private TextAlertDialog ax;
    private ProgressBarDialog ay;
    private TextAlertDialog az;
    @ViewById
    protected Button h;
    @ViewById
    protected ImageView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected ViewGroup k;
    @ViewById
    protected EditText l;
    @ViewById
    protected ImageView m;
    @ViewById
    protected ImageView n;
    @ViewById
    protected EditText o;
    @ViewById
    protected ViewGroup p;
    @ViewById
    protected TextView q;
    @ViewById
    protected TextView r;
    @ViewById
    protected ToggleButton s;
    @ViewById
    protected ViewGroup t;
    @ViewById
    protected ViewGroup u;
    @ViewById
    protected ToggleButton v;
    @ViewById
    protected TextView w;
    @ViewById
    protected View x;
    @ViewById
    protected TextView y;
    @ViewById
    protected TextView z;

    public PerformanceSaveFragment() {
        this.as = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                if (!PerformanceSaveFragment.this.isAdded() || PerformanceSaveFragment.this.ad != Mode.a) {
                    return;
                }
                PerformanceSaveFragment.this.A.setAnchoringView((View)PerformanceSaveFragment.this.s);
                PerformanceSaveFragment.this.B.setAnchoringView((View)PerformanceSaveFragment.this.v);
            }
        });
        this.at = new Runnable(){

            @Override
            public void run() {
                DeviceSettings deviceSettings = new DeviceSettings();
                SingAnalytics.a((int)PerformanceSaveFragment.this.an.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.b, (Float)Float.valueOf(PerformanceSaveFragment.this.an.u), (String)null, (String)PerformanceSaveFragment.this.ar(), (String)null, (String)PerformanceSaveFragment.this.T, (String)PerformanceSaveFragment.this.U, (String)PerformanceSaveFragment.this.V, (AudioDefs.MonitoringMode)deviceSettings.o(), (int)deviceSettings.i(), (Integer)PerformanceSaveFragment.this.ak);
                PerformanceSaveFragment.this.C.e = true;
                PerformanceSaveFragment.this.c(false);
            }
        };
        this.au = new AreYouSureDialogListener();
        this.av = new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                PerformanceSaveFragment.this.d("showProgressBarDialog - onBackOrCancelButton");
            }
        };
        this.aD = new PerformanceCreateUtil.ResourceCompressionListener(){

            public void a() {
                if (PerformanceSaveFragment.this.ay != null) {
                    PerformanceSaveFragment.this.ay.setTitle((CharSequence)PerformanceSaveFragment.this.getString(2131296716));
                }
            }

            public void a(String string2) {
                PerformanceSaveFragment.this.am = string2;
                PerformanceSaveFragment.this.ax();
            }

            public void b() {
                PerformanceSaveFragment.this.aw();
            }

            public void c() {
                PerformanceSaveFragment.this.av();
            }
        };
        this.aE = new PerformanceCreateUtil.ResourceUploadListener(){

            public void a() {
                if (PerformanceSaveFragment.this.ay != null) {
                    PerformanceSaveFragment.this.ay.setTitle((CharSequence)PerformanceSaveFragment.this.getString(2131296716));
                    PerformanceSaveFragment.this.ay.a(10);
                }
            }

            public void a(long l) {
                PerformanceSaveFragment.this.I = l;
                PerformanceSaveFragment.this.ax();
            }

            public void b() {
                PerformanceSaveFragment.this.aw();
            }
        };
    }

    private com.smule.android.network.core.NetworkResponse a(ArrayList<PerformanceManager.PerformanceResourceInfo> object) {
        object = PerformanceCreateUtil.a(object, PerformanceManager.PerformanceResourceInfo.d);
        com.smule.android.network.core.NetworkResponse networkResponse = TracksManager.a().a(this.D, (PerformanceManager.PerformanceResourceInfo)object, this.aq);
        object = networkResponse;
        if (networkResponse.c()) {
            object = null;
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(com.smule.android.network.core.NetworkResponse networkResponse, Runnable runnable) {
        if (!this.isAdded()) {
            return;
        }
        this.d((Runnable)null);
        if (networkResponse.e()) {
            ((BaseActivity)this.getActivity()).a(networkResponse.f, true, null);
        } else if (networkResponse.a == NetworkResponse.b) {
            ((BaseActivity)this.getActivity()).a(runnable, new Runnable(){

                @Override
                public void run() {
                    PerformanceSaveFragment.this.d();
                }
            });
        } else {
            this.b(this.getString(2131297077));
        }
        this.aA = false;
    }

    private void a(final boolean bl, final boolean bl2, final String string2, final String string3) {
        MagicNetwork.a(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            @Override
            public void run() {
                Object object;
                if (bl) {
                    boolean bl3 = PerformanceSaveFragment.this instanceof PerformanceSaveVideoFragment;
                    object = com.smule.android.network.managers.PerformanceManager.a().b(PerformanceSaveFragment.this.D, bl3);
                    if (!object.a()) {
                        PerformanceSaveFragment.this.e(object.a);
                        PerformanceSaveFragment.this.M();
                        return;
                    }
                    object = object.mResources;
                    if (object == null) {
                        PerformanceSaveFragment.this.b(2131297108);
                        ((PerformanceSaveActivity)PerformanceSaveFragment.this.getActivity()).a();
                        PerformanceSaveFragment.this.M();
                        return;
                    }
                    if ((object = PerformanceSaveFragment.this.a(object)) != null) {
                        PerformanceSaveFragment.this.e((com.smule.android.network.core.NetworkResponse)((Object)object));
                        PerformanceSaveFragment.this.M();
                        return;
                    }
                }
                if (bl2) {
                    object = PerformanceSaveFragment.this.a(string2, string3);
                    if (object == null) return;
                    PerformanceSaveFragment.this.e(object);
                    PerformanceSaveFragment.this.M();
                    return;
                }
                PerformanceSaveFragment.this.M();
                PerformanceSaveFragment.this.a(6803, null);
            }
        });
    }

    private void ab() {
        Object object = Html.fromHtml((String)this.getResources().getString(2131297602));
        object = new TextAlertDialog((Context)this.getActivity(), null, (CharSequence)object, true, false);
        object.a(2131296691, 0);
        object.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void ac() {
        this.q.setText((CharSequence)this.getResources().getString(2131297097));
        int n = !this.al ? 2131297511 : 2131296744;
        this.r.setText((CharSequence)this.getResources().getString(n));
        this.K = true;
        this.aj();
    }

    private boolean ad() {
        if (this.an != null && (this.an.c() || this.an.d()) && !this.an.e()) {
            return true;
        }
        return false;
    }

    private void aj() {
        new UiHandler((View)this.s).b(new UiAwareRunnable(){

            @Override
            public void a(boolean bl) {
                if (bl && PerformanceSaveFragment.this.ad()) {
                    PerformanceSaveFragment.this.A.a();
                    PerformanceSaveFragment.this.B.b();
                }
            }
        });
    }

    private void ak() {
        new UiHandler((View)this.s).b(new UiAwareRunnable(){

            @Override
            public void a(boolean bl) {
                if (bl && PerformanceSaveFragment.this.ad()) {
                    PerformanceSaveFragment.this.A.b();
                    if (!PerformanceSaveFragment.this.v.isChecked()) {
                        PerformanceSaveFragment.this.B.a();
                    }
                }
            }
        });
    }

    private void al() {
        new UiHandler((View)this.s).b(new UiAwareRunnable(){

            @Override
            public void a(boolean bl) {
                if (bl && PerformanceSaveFragment.this.ad()) {
                    PerformanceSaveFragment.this.B.a();
                }
            }
        });
    }

    private void am() {
        new UiHandler((View)this.s).b(new UiAwareRunnable(){

            @Override
            public void a(boolean bl) {
                if (bl && PerformanceSaveFragment.this.ad()) {
                    PerformanceSaveFragment.this.B.b();
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void an() {
        boolean bl = true;
        float f = 1.0f;
        if (this.N != null) {
            ToggleButton toggleButton = this.s;
            boolean bl2 = !this.K;
            toggleButton.setChecked(bl2);
            bl2 = this.N.n();
            this.s.setEnabled(bl2);
            toggleButton = this.p;
            float f2 = bl2 ? 1.0f : 0.5f;
            toggleButton.setAlpha(f2);
            if (this.N.m()) {
                this.ai = StringCacheManager.a().f(this.N.performanceKey);
                this.v.setChecked(this.ai);
                toggleButton = this.v;
                bl2 = !this.ai ? bl : false;
                toggleButton.setEnabled(bl2);
                toggleButton = this.w;
                int n = this.ai ? 2131296859 : 2131296860;
                toggleButton.setText((CharSequence)this.getString(n));
                toggleButton = this.u;
                f2 = this.v.isEnabled() ? f : 0.5f;
                toggleButton.setAlpha(f2);
            } else {
                this.t.setVisibility(8);
            }
        }
        this.a(null, this.s.isChecked());
        this.b(null, this.v.isChecked());
    }

    private void ao() {
        super.a((View)this.m, this.m, false, false, 400, 400, null, SingPermissionRequests.c);
    }

    private void ap() {
        this.z.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NavigationUtils.a((Activity)PerformanceSaveFragment.this.getActivity(), (PerformanceV2)PerformanceSaveFragment.this.N, (Runnable)null, (Runnable)new Runnable(){

                    @Override
                    public void run() {
                        PerformanceSaveFragment.this.a(6802, PerformanceSaveFragment.this.N);
                    }
                }, (Runnable)null, (boolean)true);
            }

        });
        this.i.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                PerformanceSaveFragment.this.a(6804, PerformanceSaveFragment.this.N);
            }
        });
    }

    private void aq() {
        this.z.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                PerformanceSaveFragment.this.ax = new TextAlertDialog((Context)PerformanceSaveFragment.this.getActivity(), PerformanceSaveFragment.this.getString(2131296667), PerformanceSaveFragment.this.getString(2131297082));
                PerformanceSaveFragment.this.ax.a(PerformanceSaveFragment.this.getString(2131297093), PerformanceSaveFragment.this.getString(2131296672));
                PerformanceSaveFragment.this.ax.a(new Runnable(){

                    @Override
                    public void run() {
                        PerformanceSaveFragment.this.c(PerformanceSaveFragment.this.getResources().getString(2131296626));
                        com.smule.android.network.managers.PerformanceManager.a().a(PerformanceSaveFragment.this.N.performanceKey, null, null, null, (Boolean)true, new PerformanceManager(){

                            /*
                             * Enabled aggressive block sorting
                             */
                            @Override
                            public void handleResponse(PerformanceManager.PerformanceResponse object) {
                                PerformanceSaveFragment.this.M();
                                boolean bl = object.a();
                                if (object.a.e()) {
                                    ((BaseActivity)PerformanceSaveFragment.this.getActivity()).a(object.a.f, true, null);
                                    return;
                                } else {
                                    PerformanceSaveFragment performanceSaveFragment = PerformanceSaveFragment.this;
                                    object = bl ? PerformanceSaveFragment.this.getString(2131297086) : PerformanceSaveFragment.this.getString(2131297087);
                                    performanceSaveFragment.b((String)object);
                                    if (!bl) return;
                                    {
                                        PerformanceSaveFragment.this.a(6801, PerformanceSaveFragment.this.N);
                                        return;
                                    }
                                }
                            }
                        });
                    }

                });
                PerformanceSaveFragment.this.ax.show();
            }

        });
        this.i.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                PerformanceSaveFragment.this.a(6804, PerformanceSaveFragment.this.N);
            }
        });
    }

    private String ar() {
        return "" + null + "," + this.Q + "," + this.P;
    }

    private void as() {
        this.Q();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void at() {
        boolean bl;
        String string2;
        String string3;
        boolean bl2;
        block9 : {
            block8 : {
                boolean bl3 = false;
                string3 = this.o.getText().toString();
                if (this.ap) {
                    String string4;
                    string2 = string4 = this.l.getText().toString();
                    if (TextUtils.getTrimmedLength((CharSequence)string4) == 0) {
                        this.b(this.getResources().getString(2131297101));
                        return;
                    }
                } else {
                    string2 = this.N.title;
                }
                bl2 = this.aq != null;
                if (!string2.equals(this.N.title) || !string3.equals(this.N.message) || this.K != this.N.isPrivate) break block8;
                bl = bl3;
                if (!this.N.m()) break block9;
                bl = bl3;
                if (this.ai == this.v.isChecked()) break block9;
            }
            bl = true;
        }
        if (!bl2 && !bl) {
            this.a(6804, this.N);
            return;
        }
        this.c(this.getString(2131296716));
        this.a(bl2, bl, string2, string3);
    }

    private void au() {
        if (this.H) {
            return;
        }
        if (this.ad != Mode.a) {
            Log.e(g, "Call to prepareResourceFile but the mode of the activity is not Mode.Create!");
        }
        this.aA = true;
        this.ar.a(this.getActivity(), this.Z, this.ah, this.aD);
    }

    private void av() {
        Log.d(g, "onCompressionAllocationFail");
        this.S();
        this.d((Runnable)null);
        this.aA = false;
    }

    private void aw() {
        Log.d(g, "prepareResourceFail");
        this.R();
        this.d((Runnable)null);
        this.aA = false;
    }

    private void ax() {
        Log.b(g, "prepareResourceDone");
        this.H = true;
        this.aA = true;
        this.d(new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment.this.W();
            }
        });
    }

    private boolean b(PerformanceV2 performanceV2) {
        if (performanceV2 != null && performanceV2.performanceKey.equals(this.D)) {
            return true;
        }
        return false;
    }

    private String c(PerformanceV2 performanceV2) {
        return SingAnalytics.d((PerformanceV2)performanceV2);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(String object) {
        Log.b(g, "savePerformance - called from source: " + (String)object);
        if (!this.aj) {
            String string2 = this.J();
            object = this.an.o ? Analytics.c : Analytics.d;
            AudioDefs.HeadphonesType headphonesType = AudioDefs.HeadphonesType.a(this.L, this.M);
            String string3 = this.P;
            boolean bl = this.ag;
            Analytics ensemble = this.an.b.a();
            String string4 = this.V();
            Boolean bl2 = this.an.f != null ? Boolean.valueOf(this.an.f.video) : null;
            SingAnalytics.b((String)string2, object, (AudioDefs.HeadphonesType)headphonesType, (String)string3, (boolean)bl, ensemble, (String)string4, (Boolean)bl2, (boolean)this.K());
            this.aj = true;
        }
        object = this.l.getText().toString();
        if (this.ap && !this.ag && TextUtils.getTrimmedLength((CharSequence)object) == 0) {
            this.b(this.getResources().getString(2131297101));
            return;
        }
        if (this.H) {
            this.W();
            return;
        }
        if (!this.aA) {
            this.au();
            this.N();
            return;
        }
        this.N();
    }

    private void e(@NonNull com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse.e()) {
            ((BaseActivity)this.getActivity()).a(networkResponse.f, true, null);
            return;
        }
        this.b(this.getString(2131297108));
    }

    @Click
    protected void F() {
        this.s.toggle();
    }

    @Click
    protected void G() {
        if (!this.ai) {
            this.v.toggle();
        }
    }

    @Click
    protected void H() {
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        this.as();
    }

    protected String I() {
        if (this.ae != null) {
            return this.ae.e();
        }
        return "";
    }

    protected String J() {
        return SongbookEntry.b(this.ae);
    }

    protected boolean K() {
        return false;
    }

    protected void L() {
        if (this.J != null) {
            this.aq = ImageToDiskUtils.a((Context)this.getActivity(), (String)this.J);
            if (this.aq != null) {
                this.m.setImageBitmap(this.aq);
                return;
            }
            ImageUtils.a(this.G, this.m, 2130837897);
            return;
        }
        ImageUtils.a(this.G, this.m, 2130837897);
    }

    @UiThread
    protected void M() {
        if (this.aw != null) {
            this.aw.dismiss();
        }
    }

    protected void N() {
        final long l = System.currentTimeMillis();
        this.ay = new ProgressBarDialog(this.getActivity(), this.getString(2131296716), new ProgressBarDialog.ProgressBarDialogInterface(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a() {
                Boolean bl = null;
                if (PerformanceSaveFragment.this.ay == null) return;
                PerformanceSaveFragment.this.ay.dismiss();
                PerformanceSaveFragment.this.ay = null;
                int n = Math.round((float)(System.currentTimeMillis() - l) / 1000.0f);
                String string2 = PerformanceSaveFragment.this.J();
                Analytics userPath = PerformanceSaveFragment.this.an.o ? Analytics.c : Analytics.d;
                long l2 = PerformanceSaveFragment.this.ar.a();
                String string3 = PerformanceSaveFragment.this.c(PerformanceSaveFragment.this.N);
                Boolean bl2 = bl;
                if (PerformanceSaveFragment.this.ag) {
                    bl2 = bl;
                    if (PerformanceSaveFragment.this.an != null) {
                        bl2 = bl;
                        if (PerformanceSaveFragment.this.an.f != null) {
                            bl2 = PerformanceSaveFragment.this.an.f.video;
                        }
                    }
                }
                SingAnalytics.a((String)string2, userPath, (int)n, (long)l2, (String)string3, (Boolean)bl2, (boolean)PerformanceSaveFragment.this.K(), (boolean)false);
                if (PerformanceSaveFragment.this.ag) {
                    PerformanceSaveFragment.this.P();
                }
            }
        });
        this.ay.b(5);
        this.ay.show();
    }

    @SupposeUiThread
    protected void O() {
        if (!this.isAdded()) {
            return;
        }
        this.n.setVisibility(8);
        this.s.setVisibility(8);
        this.o.setEnabled(false);
        this.l.setEnabled(false);
        this.d((Runnable)null);
    }

    @UiThread
    protected void P() {
        if (!this.isAdded()) {
            return;
        }
        this.ax = new DeleteRecordingConfirmationDialog(this.getActivity());
        this.au.a(true);
        this.ax.a(this.au);
        this.ax.show();
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void Q() {
        if (!this.isAdded()) {
            return;
        }
        String string2 = this.J();
        Analytics userPath = this.an.o ? Analytics.c : Analytics.d;
        AudioDefs.HeadphonesType headphonesType = AudioDefs.HeadphonesType.a(this.L, this.M);
        String string3 = this.P;
        boolean bl = this.ag;
        Analytics ensemble = this.an.b.a();
        SingAnalytics.ReviewStepsType reviewStepsType = SingAnalytics.ReviewStepsType.b;
        String string4 = this.c(this.N);
        Boolean bl2 = this.ag && this.an.f != null ? Boolean.valueOf(this.an.f.video) : null;
        SingAnalytics.a((String)string2, userPath, (AudioDefs.HeadphonesType)headphonesType, (String)string3, (boolean)bl, ensemble, (SingAnalytics.ReviewStepsType)reviewStepsType, (String)string4, (Boolean)bl2, (boolean)this.K());
        this.ax = new DeleteRecordingConfirmationDialog(this.getActivity());
        this.au.a(false);
        this.ax.a(this.au);
        this.ax.show();
    }

    @UiThread
    protected void R() {
        if (!this.isAdded()) {
            Log.d(g, "showFailUploadDialog - not added to fragment; aborting");
            return;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297110));
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment.this.au();
            }
        });
        textAlertDialog.show();
    }

    @UiThread
    protected void S() {
        if (!this.isAdded()) {
            Log.d(g, "showAllocationFailDialog - not added to fragment; aborting");
            return;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), null, this.getString(2131297639), true, false);
        textAlertDialog.a(this.getString(2131296705), "");
        textAlertDialog.setCanceledOnTouchOutside(false);
        textAlertDialog.setOnShowListener(new DialogInterface.OnShowListener(){

            public void onShow(DialogInterface dialogInterface) {
                SingAnalytics.c((String)PerformanceSaveFragment.this.D, (String)PerformanceSaveFragment.this.V(), (String)"performance", (boolean)false);
            }
        });
        textAlertDialog.show();
    }

    @UiThread
    protected void T() {
        if (!this.isAdded()) {
            return;
        }
        this.F = true;
        this.O();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void U() {
        if (this.ay != null) {
            this.ay.a(this.getString(2131297085));
            this.ay.a((ProgressBarDialog.ProgressBarDialogInterface)null);
            this.ay.b();
        } else {
            this.ay = new ProgressBarDialog(this.getActivity(), this.getString(2131296716), null);
            this.ay.b(5);
            this.ay.show();
        }
        this.ay.a();
        this.ay.a(5);
    }

    protected String V() {
        if (this.N != null) {
            return SingAnalytics.d((PerformanceV2)this.N);
        }
        return SingAnalytics.d((SongbookEntry)this.ae);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void W() {
        boolean bl = false;
        String string2 = null;
        if (this.F) {
            return;
        }
        String string3 = this.l.getText().toString();
        if (this.ap) {
            if (!this.ag && TextUtils.getTrimmedLength((CharSequence)string3) == 0) {
                this.b(this.getResources().getString(2131297101));
                return;
            }
        } else if (TextUtils.getTrimmedLength((CharSequence)string3) == 0) {
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
        Log.b(g, "createPerformance - performance title is: " + string3);
        if (this.an.x != null) {
            this.an.x.audioPower = null;
            object2 = this.an.x;
        } else {
            MagicCrittercism.a(new Exception("noMetaDataFoundException3"));
            object2 = null;
        }
        object = new PerformanceCreateUtil.PerformanceCreateListener(){

            public void a(com.smule.android.network.core.NetworkResponse networkResponse) {
                PerformanceSaveFragment.this.a(networkResponse);
            }

            public void a(PerformanceV2 performanceV2, String string2, String string3) {
                PerformanceSaveFragment.this.a(performanceV2);
            }

            public void a(ArrayList<PerformanceManager.PerformanceResourceInfo> arrayList) {
                PerformanceSaveFragment.this.C.i = arrayList;
                PerformanceSaveFragment.this.X();
            }

            public void b(com.smule.android.network.core.NetworkResponse networkResponse) {
                PerformanceSaveFragment.this.b(networkResponse);
            }
        };
        headphonesType = new PerformanceCreateUtil.Creator();
        string4 = headphonesType.a(this.ao).a(this.getActivity()).a(this.an.e()).b(this.an.c()).c(this.an.d()).a(this.an.g);
        if (this.ae.t()) {
            string2 = this.ae.c();
        }
        string2 = string4.a(string2);
        int n = this.ae.t() ? ((ArrangementVersionLiteEntry)this.ae).a.version : 0;
        userPath = string2.b(n).b(this.an.j).c(string3).c(this.ab).a(this.P, this.S).a(this.W).b(this.X).a(this.ac).d(this.K).e(this.L).d(this.o.getText().toString()).a((Bitmap)userPath).a((Metadata)object2).f(this.an.o).j(this.Y).h(this.an.l()).k(this.M);
        bl2 = bl;
        if (this.an.f != null) {
            bl2 = bl;
            if (this.an.f.boost) {
                bl2 = true;
            }
        }
        userPath.l(bl2).a((PerformanceCreateUtil.PerformanceCreateListener)object);
        headphonesType.a(this.ar);
    }

    protected void X() {
        this.aA = true;
    }

    protected void Y() {
        com.smule.android.network.managers.UserManager.a().a((ColorTheme)null, (String)null, this.D, (Boolean)null, (NetworkResponseCallback)null);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void Z() {
        boolean bl = !this.ag && (this.N.e() || this.N.f());
        if (bl && this.s.isChecked() && this.v.isChecked() && !this.ai) {
            this.a(new InviteCompleteCallback(){

                @Override
                public void a() {
                    this.a(true);
                }

                public void a(boolean bl) {
                    PerformanceSaveFragment.this.C.h = bl;
                    PerformanceSaveFragment.this.c(true);
                }

                @Override
                public void b() {
                    this.a(false);
                }
            });
            return;
        }
        this.c(true);
    }

    protected com.smule.android.network.core.NetworkResponse a(String object, String string2) {
        object = com.smule.android.network.managers.PerformanceManager.a().a(this.N.performanceKey, (String)object, string2, this.K, null);
        if (object.a()) {
            object = object.mPerformance;
            this.G = object.coverUrl;
            this.b(this.getString(2131297109));
            com.smule.android.network.managers.PerformanceManager.a().a((PerformanceV2)object);
            this.M();
            if (object.m() && this.s.isChecked() && this.v.isChecked() && !this.ai) {
                this.a(new InviteCompleteCallback((PerformanceV2)object){
                    final /* synthetic */ PerformanceV2 a;
                    {
                        this.a = performanceV2;
                    }

                    @Override
                    public void a() {
                        PerformanceSaveFragment.this.a(6803, this.a);
                    }

                    @Override
                    public void b() {
                        PerformanceSaveFragment.this.a(6803, this.a);
                    }
                });
                return null;
            }
            this.a(6803, (PerformanceV2)object);
            return null;
        }
        return object.a;
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    @AfterViews
    protected void a() {
        Object object;
        block27 : {
            block26 : {
                this.getActivity().getWindow().setSoftInputMode(48);
                this.ao();
                this.x.setVisibility(8);
                this.L();
                if (!this.H) {
                    this.au();
                }
                if (this.ap) {
                    WeakListener.a(this.l, new TextWatcher(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        public void afterTextChanged(Editable object) {
                            object = PerformanceSaveFragment.this.l.getText().toString();
                            Button button = PerformanceSaveFragment.this.h;
                            float f = object.length() == 0 ? 0.5f : 1.0f;
                            button.setAlpha(f);
                        }

                        public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                        }

                        public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
                        }
                    });
                    object = this.l.getText().toString();
                    Button button = this.h;
                    float f = object.length() == 0 ? 0.5f : 1.0f;
                    button.setAlpha(f);
                } else {
                    this.k.setVisibility(8);
                }
                this.an();
                object = this.al ? this.getString(2131297105) : this.getString(2131297106);
                this.o.setHint((CharSequence)object);
                if (this.ad == Mode.a) {
                    if (this.ap) {
                        this.l.requestFocus();
                        MiscUtils.a((Activity)this.getActivity(), (EditText)this.l);
                    } else {
                        this.o.requestFocus();
                        MiscUtils.a((Activity)this.getActivity(), (EditText)this.o);
                    }
                    this.j.setText(2131297080);
                    this.A.setAnchoringView((View)this.s);
                    this.A.setColor(this.getResources().getColor(2131689602));
                    this.A.setText(2131297103);
                    this.A.setSharedPreferenceKey("TOOLTIP_PRIVACY");
                    this.B.setAnchoringView((View)this.v);
                    this.B.setColor(this.getResources().getColor(2131689602));
                    this.B.setText(2131297102);
                    this.B.setSharedPreferenceKey("TOOLTIP_INVITE");
                    LayoutUtils.a((View)this.o, (WeakListener.OnGlobalLayoutListener)this.as);
                    if (this.ad()) {
                        this.o.setHint(2131297094);
                    }
                    if (this.ag) {
                        Log.b(g, "updateFollowingViewBinding: Create mode, is a join");
                        this.n.setVisibility(4);
                        this.s.setClickable(false);
                        this.s.setOnTouchListener(null);
                        this.s.setAlpha(0.5f);
                        this.o.setEnabled(false);
                        this.o.setHint((CharSequence)"");
                        this.l.setEnabled(false);
                        object = this.an.f;
                        if (object != null) {
                            this.l.setText((CharSequence)object.title);
                        } else if (this.ae != null) {
                            this.l.setText((CharSequence)this.I());
                        }
                        this.d("updateFollowingViewBinding: auto-create for joins");
                    } else {
                        Log.b(g, "updateFollowingViewBinding: Create mode, is not a join");
                        this.n.setVisibility(0);
                        this.o.setEnabled(true);
                        if (this.an.s >= 0) {
                            object = MagicPreferences.b((Context)this.getActivity(), "LAST_PROMOTION_HASHTAG_PAIR", null);
                            if (object == null) {
                                Log.d(g, "Hashtag was lost somehow.  Shouldn't happen, but can proceed without it.");
                            } else {
                                long l = Long.parseLong((object = object.split(","))[0]);
                                if (this.an.s.equals(l)) {
                                    this.o.setText((CharSequence)("#" + (String)object[1] + " "));
                                    this.o.setSelection(this.o.getText().length());
                                }
                            }
                        }
                        if (this.ap) {
                            this.l.setEnabled(true);
                            this.l.setHint((CharSequence)this.getString(2131297095));
                        } else {
                            this.l.setText((CharSequence)this.I());
                            this.l.setEnabled(false);
                        }
                    }
                    if (this.F) {
                        this.O();
                    }
                }
                if (this.N == null) break block26;
                this.x.setVisibility(0);
                object = this.getString(2131297100, new Object[]{MiscUtils.a((long)this.N.createdAt, (boolean)false, (boolean)false)});
                if (this.N.seed) break block27;
                this.ap();
                this.l.setText((CharSequence)this.N.title);
                this.o.setText((CharSequence)this.N.message);
                this.y.setText((CharSequence)object);
                this.z.setText((CharSequence)this.getString(2131296742));
            }
            return;
        }
        Log.b(g, "updateFollowingViewBinding - for seed, performance is closed: " + this.N.closed);
        if (this.N.closed) {
            this.y.setText((CharSequence)object);
            if (PerformanceV2Util.a((PerformanceV2)this.N)) {
                this.z.setText((CharSequence)this.getString(2131296742));
            } else {
                this.z.setVisibility(4);
            }
            this.ap();
        } else {
            this.y.setText((CharSequence)this.getString(2131297054, new Object[]{MiscUtils.a((long)this.N.expireAt, (boolean)false, (boolean)false)}));
            this.z.setText((CharSequence)this.getString(2131297093));
            this.aq();
        }
        this.l.setText((CharSequence)this.N.title);
        this.o.setText((CharSequence)this.N.message);
    }

    @UiThread
    protected void a(int n, PerformanceV2 performanceV2) {
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        Intent intent = new Intent();
        intent.putExtra("CHANGE_MADE_CODE", n);
        intent.putExtra("CHANGE_MADE_PERFORMANCE", (Parcelable)performanceV2);
        super.a(intent);
    }

    /*
     * Enabled aggressive block sorting
     */
    @CheckedChange
    protected void a(CompoundButton object, boolean bl) {
        int n;
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        if (bl) {
            this.q.setText((CharSequence)this.getResources().getString(2131297098));
            n = !this.al ? 2131297099 : 2131296745;
            this.r.setText((CharSequence)this.getResources().getString(n));
            this.K = false;
            this.ak();
        } else {
            object = com.smule.android.network.managers.UserManager.a();
            ProfileCustomizations profileCustomizations = object.v();
            if (SubscriptionManager.a().b() && profileCustomizations == null) {
                if (this.aB == null) {
                    this.aB = new BusyScreenDialog((Context)this.getActivity(), "");
                }
                this.aB.show();
                com.smule.android.network.managers.UserManager.a().a(object.f(), new UserManager(){

                    @Override
                    public void handleResponse(SingUserProfile object) {
                        if (!PerformanceSaveFragment.this.isAdded()) {
                            return;
                        }
                        if (PerformanceSaveFragment.this.aB != null && PerformanceSaveFragment.this.aB.isShowing()) {
                            PerformanceSaveFragment.this.aB.dismiss();
                        }
                        if (!object.a() || object.profile == null || object.profile.accountIcon == null) {
                            object = new TextAlertDialog((Context)PerformanceSaveFragment.this.getActivity(), PerformanceSaveFragment.this.getResources().getString(2131297544));
                            object.a(null, PerformanceSaveFragment.this.getString(2131296705));
                            object.show();
                            PerformanceSaveFragment.this.s.setChecked(true);
                            Log.e(PerformanceSaveFragment.g, "User account not retrieved");
                            return;
                        }
                        object = object.singProfile.pinPerformanceIcon;
                        if (PerformanceSaveFragment.this.b((PerformanceV2)object)) {
                            PerformanceSaveFragment.this.ab();
                            PerformanceSaveFragment.this.s.setChecked(true);
                            return;
                        }
                        PerformanceSaveFragment.this.ac();
                    }
                });
            } else {
                object = profileCustomizations == null ? null : profileCustomizations.pinPerformanceIcon;
                if (this.b((PerformanceV2)object)) {
                    this.ab();
                    this.s.setChecked(true);
                    return;
                }
                this.ac();
            }
        }
        object = this.t;
        n = this.al && bl ? 0 : 8;
        object.setVisibility(n);
    }

    protected void a(com.smule.android.network.core.NetworkResponse networkResponse) {
        this.a(networkResponse, new Runnable((PerformanceSaveActivity)this.getActivity()){
            final /* synthetic */ PerformanceSaveActivity a;
            {
                this.a = performanceSaveActivity;
            }

            @Override
            public void run() {
                this.a.a();
                PerformanceSaveFragment.this.W();
            }
        });
    }

    protected void a(PerformanceV2 object) {
        if (!this.isAdded()) {
            return;
        }
        if (this.ay != null) {
            this.ay.a(this.getString(2131296679));
            this.ay.c();
        }
        this.aA = false;
        this.N = object;
        this.D = object.performanceKey;
        Log.b(g, "Performance creation completed!");
        object = new DeviceSettings();
        SingAnalytics.a((int)this.an.t, (SingAnalytics.AudioCompletionContext)SingAnalytics.AudioCompletionContext.c, (Float)Float.valueOf(this.an.u), (String)this.D, (String)this.ar(), (String)null, (String)this.T, (String)this.U, (String)this.V, (AudioDefs.MonitoringMode)object.o(), (int)object.i(), (Integer)this.ak);
        this.T();
        if (this.an.y && this.s.isChecked()) {
            this.Y();
        }
        this.Z();
    }

    protected void a(final InviteCompleteCallback inviteCompleteCallback) {
        if (StringCacheManager.a().f(this.N.performanceKey)) {
            return;
        }
        this.c(this.getString(2131296870));
        InviteManager.a().a(this.D, new NetworkResponseCallback(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void handleResponse(com.smule.android.network.core.NetworkResponse object) {
                PerformanceSaveFragment.this.M();
                if (PerformanceSaveFragment.this.aF) {
                    MagicCrittercism.a(new RuntimeException("Duplicated invites to followers").fillInStackTrace());
                }
                if (object.c() || object.b == 1026) {
                    PerformanceSaveFragment.this.aF = true;
                    StringCacheManager.a().e(PerformanceSaveFragment.this.N.performanceKey);
                    SingAnalytics.a((String)PerformanceSaveFragment.this.D, Analytics.a, (String)PerformanceV2Util.f((PerformanceV2)PerformanceSaveFragment.this.N), SingAnalytics.a((PerformanceV2)PerformanceSaveFragment.this.N), (SingAnalytics.InviteType)SingAnalytics.InviteType.a, (String)PerformanceV2Util.h((PerformanceV2)PerformanceSaveFragment.this.N), (boolean)PerformanceSaveFragment.this.N.video);
                    if (inviteCompleteCallback == null) return;
                    {
                        new Handler(Looper.getMainLooper()).post(new Runnable(){

                            @Override
                            public void run() {
                                inviteCompleteCallback.a();
                            }
                        });
                    }
                    return;
                }
                Activity activity = PerformanceSaveFragment.this.getActivity();
                if (activity == null) return;
                {
                    if (object.e()) {
                        ((BaseActivity)activity).a(object.f, true, null);
                        return;
                    }
                }
                object = new CustomAlertDialog.CustomAlertDialogListener(){

                    @Override
                    public void a(CustomAlertDialog customAlertDialog) {
                        PerformanceSaveFragment.this.a(inviteCompleteCallback);
                    }

                    @Override
                    public void b(CustomAlertDialog customAlertDialog) {
                        inviteCompleteCallback.b();
                    }
                };
                if (PerformanceSaveFragment.this.az == null) {
                    PerformanceSaveFragment.this.az = new TextAlertDialog((Context)activity, 2131296867, 2131296866);
                    PerformanceSaveFragment.this.az.a(2131296863, 2131296710);
                    PerformanceSaveFragment.this.az.c(true);
                    PerformanceSaveFragment.this.az.a((CustomAlertDialog.CustomAlertDialogListener)object);
                }
                PerformanceSaveFragment.this.az.show();
            }

        });
    }

    public void a(Future<PerformanceManager.PreuploadResponse> future) {
        this.ao = future;
    }

    protected void aa() {
    }

    @CheckedChange
    protected void b(CompoundButton compoundButton, boolean bl) {
        MiscUtils.a((Activity)this.getActivity(), (boolean)true);
        if (bl) {
            this.am();
            return;
        }
        this.al();
    }

    protected void b(com.smule.android.network.core.NetworkResponse networkResponse) {
        this.a(networkResponse, new Runnable(){

            @Override
            public void run() {
                PerformanceSaveFragment.this.d("onPerformanceCreationFailed");
            }
        });
    }

    @UiThread
    protected void c(String string2) {
        this.aw = new BusyDialog(this.getActivity(), string2);
        this.aw.show();
    }

    protected void c(boolean bl) {
        ((PerformanceSaveActivity)this.getActivity()).a(this.C, this.N, bl);
    }

    @UiThread
    protected void d(Runnable runnable) {
        if (this.ay != null) {
            this.ay.dismiss();
            this.ay = null;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override
    public boolean d() {
        if (this.ad == Mode.a && !this.F) {
            this.as();
            return true;
        }
        this.a(6804, this.N);
        return true;
    }

    @Override
    public boolean k() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onActivityResult(int n, int n2, Intent intent) {
        super.onActivityResult(n, n2, intent);
        if (n2 != -1) {
            Log.e(g, "Bad result code, " + n2 + ", returned for request code: " + n);
            return;
        }
        switch (n) {
            default: {
                return;
            }
            case 2202: 
        }
        if (intent.getExtras() == null) return;
        {
            if ((intent = this.b(intent)) != null) {
                this.m.setImageBitmap((Bitmap)intent);
                String string2 = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                string2 = "IMG_" + string2 + ".jpg";
                ImageToDiskUtils.a((Context)this.getActivity(), (String)string2, (Bitmap)intent);
                this.aq = intent;
                this.J = string2;
                this.O = true;
                this.aa();
                return;
            }
        }
        Log.e(g, "Null bitmap returned from CROP_PHOTO_INTENT_CODE");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public void onCreate(Bundle object) {
        boolean bl = false;
        super.onCreate((Bundle)object);
        if (this.C != null) {
            this.an = this.C.b;
        }
        this.ar = new PerformanceCreateUtil(this.I, this.am);
        if (object == null) {
            Log.b(g, "onCreate - no saved instance state");
            Bundle bundle = this.getArguments();
            this.N = (PerformanceV2)bundle.getParcelable("PERFORMANCE_SAVE_PERFORMANCE_KEY");
            object = this.N != null ? this.N.performanceKey : null;
            this.D = object;
            object = this.N == null ? Mode.a : Mode.b;
            this.ad = object;
            this.Z = bundle.getString("RECORDING_FILE_EXTRA_KEY");
            this.aa = bundle.getBoolean("PITCH_CORRECT_EXTRA_KEY", false);
            this.ab = bundle.getInt("SCORE_EXTRA_KEY", 0);
            this.ac = bundle.getFloat("USER_GAIN_DB", 1.0f);
            this.L = bundle.getBoolean("HEADTHING_ONLY", false);
            this.M = bundle.getBoolean("HEADPHONE_HAD_MIC", false);
            this.ah = bundle;
        } else {
            Log.b(g, "onCreate - restoring from saved instance state");
        }
        if (this.ad == Mode.a) {
            Log.b(g, this.an.toString());
            this.ae = this.an.d;
            this.ap = PerformanceV2Util.a((String)this.ae.c());
            this.ag = this.an.k;
            bl = this.an.c() || this.an.d();
            this.al = bl;
            Log.b(g, "Performance was a join: " + this.ag);
            this.P = this.getArguments().getString("EFFECT_PRESET");
            this.Q = this.getArguments().getString("FX_INITIAL");
            this.R = this.getArguments().getString("FX_SELECTED");
            this.S = this.getArguments().getInt("FX_SELECTED_VERSION", 0);
            this.T = this.getArguments().getString("FXS_UNIQUE_REVIEW");
            this.U = this.getArguments().getString("ADJUSTED_SLIDER");
            this.V = this.getArguments().getString("PLAY_PAUSE_COUNT");
            if (this.P == null || this.P.isEmpty()) {
                this.P = "****";
            }
            this.W = Float.valueOf(this.getArguments().getFloat("META_PARAM_1", -1.0f));
            this.X = Float.valueOf(this.getArguments().getFloat("META_PARAM_2", -1.0f));
            if (this.W.floatValue() == -1.0f) {
                this.W = null;
            }
            if (this.X.floatValue() == -1.0f) {
                this.X = null;
            }
            this.Y = this.getArguments().getBoolean("PRESET_VIP_EXTRA_KEY");
            try {
                this.ak = (Integer)this.getArguments().get("ESTIMATED_LATENCY_MS");
            }
            catch (ClassCastException classCastException) {
                Log.d(g, "I tried to get ESTIMATED_LATENCY_MS from arguments, but it wasn't an Integer", classCastException);
                this.ak = null;
            }
            if (this.G == null || this.G.isEmpty()) {
                this.G = SongbookEntryUtils.a((SongbookEntry)this.ae);
            }
            return;
        }
        this.H = true;
        this.K = this.N.isPrivate;
        if (this.N.e() || this.N.f()) {
            bl = true;
        }
        this.al = bl;
        this.G = this.N.coverUrl;
        this.ap = PerformanceV2Util.a((String)this.N.arrKey);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LayoutUtils.b((View)this.o, (WeakListener.OnGlobalLayoutListener)this.as);
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onStart() {
        super.onStart();
        boolean bl = (this.getActivity().getWindow().getAttributes().flags & 1024) != 0;
        this.aC = bl;
        if (!this.aC) {
            this.getActivity().getWindow().setFlags(1024, 1024);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (!this.aC) {
            this.getActivity().getWindow().clearFlags(1024);
        }
    }

    @Click
    protected void t() {
        switch (.a[this.ad.ordinal()]) {
            default: {
                throw new RuntimeException("Unhandled mode: " + (Object)((Object)this.ad));
            }
            case 1: {
                this.d("mSavePerformanceButton - View.OnClickListener");
                return;
            }
            case 2: 
        }
        this.at();
    }

    class AreYouSureDialogListener
    implements CustomAlertDialog.CustomAlertDialogListener {
        private boolean b;

        AreYouSureDialogListener() {
        }

        @Override
        public void a(CustomAlertDialog customAlertDialog) {
            if (this.b) {
                PerformanceSaveFragment.this.d("mAreYouSureDialogListener: onOkButton");
            }
        }

        public void a(boolean bl) {
            this.b = bl;
        }

        @Override
        public void b(CustomAlertDialog customAlertDialog) {
            PerformanceSaveFragment.this.at.run();
        }
    }

    private static interface InviteCompleteCallback {
        public void a();

        public void b();
    }

    public static enum Mode {
        a,
        b;
        

        private Mode() {
        }
    }

}

