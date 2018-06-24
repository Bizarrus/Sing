/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.CountDownTimer
 *  android.view.View
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.SingAnalytics
 */
package com.smule.singandroid.boost;

import android.content.Context;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import com.smule.android.core.exception.IError;
import com.smule.android.core.exception.SmuleException;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.BoostManager;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.purchases.ServerPurchaseExecutor;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.boost.BoostContext;
import com.smule.singandroid.boost.BoostCountdownDialog;
import com.smule.singandroid.boost.BoostError;
import com.smule.singandroid.boost.BoostParameterType;
import com.smule.singandroid.boost.BoostStateCommander;
import com.smule.singandroid.boost.BoostStateMachine;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.SingAnalytics;
import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BoostPresenter {
    private static final String a = BoostPresenter.class.getName();
    private static BoostPresenter b;
    private static final MessageFormat e;
    private BoostStateCommander c;
    private BoostContext d;
    private WeakReference<MediaPlayingActivity> f;

    static {
        e = new MessageFormat("{0,number,00}:{1,number,00}:{2,number,00}");
    }

    private BoostPresenter() {
        this.d();
        this.e();
    }

    public static BoostPresenter a() {
        if (b == null) {
            b = new BoostPresenter();
        }
        return b;
    }

    public static /* varargs */ void a(BoostContext boostContext, Enum enum_, Object ... arrobject) {
        BoostPresenter.a(boostContext, BoostStateMachine.a(enum_), arrobject);
    }

    public static /* varargs */ void a(BoostContext boostContext, String string2, Object ... arrobject) {
        if (!BoostStateCommander.d().contains(string2)) {
            Log.e(a, "Notification name not declared in BoostStateCommander.getNotificationNames(): " + string2);
        }
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("BOOST_CONTEXT", boostContext);
        hashMap.put("BOOST_PERFORMANCE", boostContext.d);
        hashMap.put("BOOST_ARRAY_DATA", arrobject);
        NotificationCenter.a().a(string2, (Object)hashMap);
    }

    private void d() {
        try {
            this.c = new BoostStateCommander();
            return;
        }
        catch (SmuleException smuleException) {
            Log.d(a, "This should only ever be caused when our BoostStateMachine is configured incorrectly.", smuleException);
            return;
        }
    }

    private void e() {
        NotificationCenter.a().a(BoostStateMachine.State.d.a(), new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                BoostPresenter.this.f();
            }
        });
        NotificationCenter.a().a(BoostStateMachine.State.g.a(), new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                BoostPresenter.this.h();
            }
        });
        NotificationCenter.a().a(BoostStateMachine.State.h.a(), new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                BoostPresenter.this.i();
            }
        });
        NotificationCenter.a().a(BoostStateMachine.State.i.a(), new Observer(){

            @Override
            public void update(Observable observable, Object object) {
                BoostPresenter.this.j();
            }
        });
    }

    private void f() {
        final TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.f.get(), 2131296667, 2131296440, true, true);
        textAlertDialog.a(2131296733, 2131296701);
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                BoostPresenter.this.g();
            }
        });
        textAlertDialog.b(new Runnable(){

            @Override
            public void run() {
                textAlertDialog.dismiss();
                try {
                    BoostPresenter.this.c.c();
                    return;
                }
                catch (SmuleException smuleException) {
                    smuleException.printStackTrace();
                    return;
                }
            }
        });
        textAlertDialog.show();
    }

    private void g() {
        this.d.e = Analytics.a;
        com.smule.android.network.managers.BoostManager.a().a(com.smule.android.network.managers.BoostManager.a().e(), this.d.e, null, new BoostManager(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public void handleResponse(BoostManager object) {
                int n = 2131296681;
                if (object == null) return;
                if (object.a == null) return;
                try {
                    BoostPresenter.this.c.a(object.a.b);
                    return;
                }
                catch (SmuleException smuleException) {
                    int n2;
                    if (smuleException.a() != BoostError.e) return;
                    Log.b(a, "Boost failed because of error code: " + object.a.b);
                    switch ((Integer)smuleException.c().get(BoostParameterType.a.name())) {
                        default: {
                            n2 = 2131296453;
                            break;
                        }
                        case 1036: {
                            n = 2131296437;
                            n2 = 2131296436;
                            break;
                        }
                        case 1021: {
                            n2 = 2131296451;
                        }
                    }
                    object = new TextAlertDialog((Context)BoostPresenter.this.f.get(), n, n2, true, false);
                    object.a(2131296705, 0);
                    object.show();
                    Log.b(a, "Boost failed");
                    try {
                        BoostPresenter.this.c.c();
                        return;
                    }
                    catch (SmuleException smuleException2) {
                        smuleException2.printStackTrace();
                        return;
                    }
                }
            }
        });
    }

    private void h() {
        this.d.d.boost = true;
        SingAnalytics.a((PerformanceV2)this.d.d, this.d.e);
        Toaster.a((Context)this.f.get(), 2131296438);
        Log.b(a, "Boost successful");
        try {
            this.c.c();
            return;
        }
        catch (SmuleException smuleException) {
            smuleException.printStackTrace();
            return;
        }
    }

    private void i() {
        Log.b(a, "Boost failed");
        try {
            this.c.c();
            return;
        }
        catch (SmuleException smuleException) {
            smuleException.printStackTrace();
            return;
        }
    }

    private void j() {
        this.d = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(MediaPlayingActivity object, BoostContext object2) {
        this.f = new WeakReference<Object>(object);
        this.d = object2;
        try {
            this.c.a(this.d);
            this.c.b();
            return;
        }
        catch (SmuleException smuleException) {
            if (smuleException.a() == BoostError.b) {
                BoostPresenter.a(this.d, BoostError.b, new Object[0]);
                long l = -1;
                if (this.f.get().ad() != null) {
                    l = object.ad().ah();
                }
                NotificationCenter.a().a(ServerPurchaseExecutor.b, new Observer((BoostContext)object2, (MediaPlayingActivity)object){
                    final /* synthetic */ BoostContext a;
                    final /* synthetic */ MediaPlayingActivity b;
                    {
                        this.a = boostContext;
                        this.b = mediaPlayingActivity;
                    }

                    @Override
                    public void update(Observable observable, Object object) {
                        if (((Boolean)((Map)object).get("result")).booleanValue()) {
                            this.a.a = SubscriptionManager.a().b();
                            com.smule.android.network.managers.BoostManager.a().a(new BoostManager(){
                                final BoostContext a;
                                {
                                    this.a = 5.this.a;
                                }

                                @Override
                                public void handleResponse(BoostManager.BoostAvailabilityResponse boostAvailabilityResponse) {
                                    this.a.b = com.smule.android.network.managers.BoostManager.a().b();
                                    BoostPresenter.this.c.a(this.a);
                                    try {
                                        BoostPresenter.this.c.b();
                                        return;
                                    }
                                    catch (SmuleException smuleException) {
                                        smuleException.printStackTrace();
                                        return;
                                    }
                                }
                            });
                            return;
                        }
                        Toaster.a((Context)this.b, this.b.getResources().getString(2131297549));
                    }

                });
                this.f.get().a(UpsellManager.a((boolean)false, (SongbookEntry)null, (PerformanceV2)this.d.d, (Long)l, (UpsellType)UpsellType.j));
            }
            if (smuleException.a() == BoostError.a) {
                object2 = new TextAlertDialog((Context)this.f.get(), 2131296437, 2131296436);
                object2.d(true);
                object2.a(2131296705, 0);
                object2.show();
                try {
                    this.c.c();
                }
                catch (SmuleException smuleException2) {
                    smuleException2.printStackTrace();
                }
            }
            if (smuleException.a() == BoostError.c) {
                object = new BoostCountdownDialog((Context)object, 2130903092, true, false, true);
                object.a(2131296705, 0);
                object.setTitle(2131296442);
                object.show();
                SingAnalytics.I();
                object = (TextView)object.findViewById(2131755314);
                new CountDownTimer(com.smule.android.network.managers.BoostManager.a().c(), 1000, (TextView)object){
                    final /* synthetic */ TextView a;
                    {
                        this.a = textView;
                        super(l, l2);
                    }

                    public void onFinish() {
                        this.a.setText((CharSequence)e.format(new Object[]{0, 0, 0}));
                    }

                    public void onTick(long l) {
                        long l2 = TimeUnit.MILLISECONDS.toHours(l);
                        long l3 = TimeUnit.MILLISECONDS.toMinutes(l);
                        long l4 = TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l));
                        long l5 = TimeUnit.MILLISECONDS.toSeconds(l);
                        l = TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l));
                        this.a.setText((CharSequence)e.format(new Object[]{l2, l3 - l4, l5 - l}));
                    }
                }.start();
            }
            try {
                this.c.c();
                return;
            }
            catch (SmuleException smuleException3) {
                smuleException3.printStackTrace();
                return;
            }
        }
    }

}

