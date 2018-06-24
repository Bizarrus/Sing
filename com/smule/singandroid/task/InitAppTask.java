package com.smule.singandroid.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.smule.android.ads.AdsSettingsManager;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.BalanceManager;
import com.smule.android.network.managers.DeviceSettingsManager;
import com.smule.android.network.managers.EntitlementsManager;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.FolloweesResponse;
import com.smule.android.network.managers.FollowManager.FolloweesResponseCallback;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.PurchasesManager;
import com.smule.android.network.managers.RecommendationManager;
import com.smule.android.network.managers.RecommendationManager.CacheDuration;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.managers.StoreManager.StoreManagerRequiredMethodsDelegate;
import com.smule.android.network.managers.SubscriptionsRestoreHelper;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.l10n.SNPSettingsLocalizationProvider;
import com.smule.android.notifications.MagicNotifications;
import com.smule.android.twitter.MagicTwitter;
import com.smule.android.uploader.FileUploaderService;
import com.smule.android.utils.DiskUsageRunnable;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OperationLoader;
import com.smule.android.utils.OperationLoader.Operation;
import com.smule.android.utils.OperationLoader.Operation.OperationStatus;
import com.smule.android.utils.ResourceUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SubscriptionTrackerHelper;
import com.smule.singandroid.ads.AdUtils;
import com.smule.singandroid.ads.AdVendorManagerConfig;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.utils.CacheUtils;
import com.smule.singandroid.utils.ChatUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class InitAppTask extends AsyncTask<Void, Void, Void> {
    static Operation f24444a = new Operation("InitAppTask.OP_USER_LOGGED_IN", null) {
        boolean f24438a = true;

        public void m25632a(@NonNull List<OperationStatus> list) {
            if (this.f24438a && !NetworkUtils.m18113a(SingApplication.f())) {
                InitAppTask.m25645g();
            }
            this.f24438a = false;
        }
    };
    private static final String f24445b = InitAppTask.class.getName();
    private Activity f24446c;

    static class C49581 implements StoreManagerRequiredMethodsDelegate {
        C49581() {
        }

        public String mo6952a() {
            return "store.sing_google";
        }
    }

    static class C49592 implements Runnable {
        C49592() {
        }

        public void run() {
            MagicNetwork.a().a(true);
        }
    }

    static class C49613 extends Operation {
        boolean f24437a = true;

        C49613() {
        }

        public void m25631a(final OperationLoader operationLoader) {
            if (!NetworkUtils.m18113a(SingApplication.f()) && this.f24437a) {
                operationLoader.c(this.g);
            }
            this.f24437a = false;
            NetworkUtils.m18117b(SingApplication.f(), new Runnable(this) {
                final /* synthetic */ C49613 f24436b;

                public void run() {
                    operationLoader.c(this.f24436b.g);
                }
            });
        }
    }

    static class C49635 implements Observer {
        C49635() {
        }

        public void update(Observable observable, Object obj) {
            if (UserManager.a().z()) {
                SingApplication.f = true;
                SingApplication.a(UserManager.a().s());
                SingApplication.b(UserManager.a().t());
            }
            InitAppTask.m25645g();
        }
    }

    static class C49646 extends Operation {
        C49646() {
        }

        public void m25633a(OperationLoader operationLoader) {
            if (UserManager.a().z()) {
                SubscriptionTrackerHelper.m21933a().m21934b();
                operationLoader.c(this.g);
            }
        }
    }

    static class C49667 extends Operation {

        class C49651 implements Runnable {
            final /* synthetic */ C49667 f24439a;

            C49651(C49667 c49667) {
                this.f24439a = c49667;
            }

            public void run() {
                if (StoreManager.m18378a().m18433g().size() <= 0) {
                    StoreManager.m18378a().m18425c();
                }
                if (UserManager.a().z() && !UserManager.a().y()) {
                    RecommendationManager.m18285a().m18295a(CacheDuration.LONG, null);
                }
                this.f24439a.a(true);
            }
        }

        C49667() {
        }

        public void m25635a(OperationLoader operationLoader) {
            if (UserManager.a().A()) {
                a(false);
            } else {
                StoreManager.m18378a().m18421a(new C49651(this));
            }
        }
    }

    static class C49688 extends Operation {
        C49688() {
        }

        public void m25636a(final OperationLoader operationLoader) {
            if (UserManager.a().z()) {
                LocalizationManager.a().a(new Runnable(this) {
                    final /* synthetic */ C49688 f24441b;

                    public void run() {
                        operationLoader.c(this.f24441b.g);
                    }
                });
            } else {
                operationLoader.c(this.g);
            }
        }
    }

    static class C49709 extends Operation {
        C49709() {
        }

        public void m25637a(final OperationLoader operationLoader) {
            DeviceSettingsManager.m18151a().m18162b();
            AppSettingsManager.a(SingApplication.f()).a(new Runnable(this) {
                final /* synthetic */ C49709 f24443b;

                public void run() {
                    operationLoader.c(this.f24443b.g);
                }
            });
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25657a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25658a((Void) obj);
    }

    public InitAppTask(Activity activity) {
        this.f24446c = activity;
    }

    public static void m25639a() {
        Log.c(f24445b, "preInit called");
        StoreManager.m18378a().m18424b();
        StoreManager.m18378a().m18419a(SingApplication.d(), true, new C49581());
        String appVersion = MagicNetwork.d().getAppVersion();
        EntitlementsManager.m18163a().m18178a(SingApplication.f(), false, EntitlementsManager.m18164a(SingApplication.f()).equals(appVersion), true);
        BalanceManager.a().a(SingApplication.f());
        LocalizationManager.a().a(new SNPSettingsLocalizationProvider());
        MagicNotifications.a().b();
        m25640b();
        m25643e();
        m25649k();
        m25652n();
        m25647i();
        m25648j();
        m25646h();
        m25650l();
        m25651m();
        m25653o();
        m25654p();
        m25642d();
        m25655q();
        m25656r();
        MagicNetwork.a(new C49592());
    }

    protected void onPreExecute() {
        long currentTimeMillis = System.currentTimeMillis();
        if (UserManager.a().z()) {
            Log.b(f24445b, "Beginning network initialization");
        } else {
            Log.e(f24445b, "Trying to initialize app before user is logged in!");
        }
        SingApplication.e = true;
        MagicFacebook.a();
        Log.b(f24445b, "onPreExecute - passed MagicFacebook.getInstance at: " + (System.currentTimeMillis() - currentTimeMillis));
        PurchasesManager.m18267a().m18274a(this.f24446c);
        Log.b(f24445b, "onPreExecute - passed PurchasesManager.init at: " + (System.currentTimeMillis() - currentTimeMillis));
        AdVendorManagerConfig.m22233a();
        Log.b(f24445b, "onPreExecute - passed AdVendorManagerConfig.init at: " + (System.currentTimeMillis() - currentTimeMillis));
        AdUtils.m22221a(this.f24446c, false);
        this.f24446c.startService(FileUploaderService.m18902a(this.f24446c));
        Log.b(f24445b, "onPreExecute - passed StoreManager.init at: " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static void m25640b() {
        SingApplication.d().a("InitAppTask.OP_NETWORK_CONNECTED", null, new C49613()).a();
        m25641c();
    }

    private static synchronized void m25645g() {
        synchronized (InitAppTask.class) {
            SingApplication.d().c(f24444a);
        }
    }

    public static void m25641c() {
        SingApplication.d().a(f24444a);
        Observer c49635 = new C49635();
        NotificationCenter.m19011a().m19014a("USER_LOGGED_IN_EVENT", c49635);
        NotificationCenter.m19011a().m19014a("USER_RE_LOGGED_IN_EVENT", c49635);
    }

    public static void m25642d() {
        SingApplication.d().a("InitAppTask.OP_SUB_STATUS_INIT", Collections.singletonList("InitAppTask.OP_LOAD_SETTINGS"), new C49646()).a();
    }

    public static void m25643e() {
        SingApplication.d().a("InitAppTask.OP_RELOAD_SONGBOOK", Arrays.asList(new String[]{"StoreManager.loadStore", "InitAppTask.OP_USER_LOGGED_IN"}), new C49667()).a();
    }

    private static void m25646h() {
        SingApplication.d().a("InitAppTask.OP_LOCALIZE_SETTINGS", Collections.singleton("InitAppTask.OP_LOAD_SETTINGS"), new C49688()).a();
    }

    private static void m25647i() {
        SingApplication.d().a("InitAppTask.OP_LOAD_SETTINGS", Arrays.asList(new String[]{"InitAppTask.OP_NETWORK_CONNECTED", "InitAppTask.OP_USER_LOGGED_IN"}), new C49709()).a();
    }

    private static void m25648j() {
        SingApplication.d().a("InitAppTask.OP_USER_ACTUALLY_LOGGED_IN", Arrays.asList(new String[]{"InitAppTask.OP_USER_LOGGED_IN", "InitAppTask.OP_LOAD_SETTINGS"}), new Operation() {
            protected boolean m25621b() {
                return UserManager.a().z() && UserManager.a().o() != null;
            }

            public void m25620a(final OperationLoader operationLoader) {
                if (m25621b()) {
                    SingAppboy.m23096a().m23099b();
                    operationLoader.c(this.g);
                    return;
                }
                Observer c49491 = new Observer(this) {
                    final /* synthetic */ AnonymousClass10 f24418b;

                    public void update(Observable observable, Object obj) {
                        if (this.f24418b.m25621b()) {
                            SingAppboy.m23096a().m23099b();
                            NotificationCenter.m19011a().m19016b("USER_LOGGED_IN_EVENT", (Observer) this);
                            NotificationCenter.m19011a().m19016b("USER_RE_LOGGED_IN_EVENT", (Observer) this);
                            operationLoader.c(this.f24418b.g);
                        }
                    }
                };
                NotificationCenter.m19011a().m19014a("USER_LOGGED_IN_EVENT", c49491);
                NotificationCenter.m19011a().m19014a("USER_RE_LOGGED_IN_EVENT", c49491);
            }
        });
    }

    private static void m25649k() {
        SingApplication.d().a("InitAppTask.OP_ENTITLEMENTS_LOADED", Arrays.asList(new String[]{"InitAppTask.OP_USER_LOGGED_IN", "InitAppTask.OP_NETWORK_CONNECTED"}), new Operation() {
            public void m25622a(final OperationLoader operationLoader) {
                if (UserManager.a().z()) {
                    EntitlementsManager.m18163a().m18179a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 f24420b;

                        public void run() {
                            operationLoader.c(this.f24420b.g);
                        }
                    });
                } else {
                    operationLoader.c(this.g);
                }
            }
        }).a();
    }

    private static void m25650l() {
        SingApplication.d().a("InitAppTask.OP_FOLLOWEES_AND_FOLLOWERS_LOADED", Arrays.asList(new String[]{"InitAppTask.OP_NETWORK_CONNECTED", "InitAppTask.OP_USER_LOGGED_IN"}), new Operation() {
            public void m25623a(final OperationLoader operationLoader) {
                if (!UserManager.a().z() || FollowManager.m18204a().m18223b() > 0) {
                    operationLoader.c(this.g);
                } else {
                    FollowManager.m18204a().m18214a(new FolloweesResponseCallback(this) {
                        final /* synthetic */ AnonymousClass12 f24422b;

                        public void handleResponse(FolloweesResponse followeesResponse) {
                            operationLoader.c(this.f24422b.g);
                        }
                    });
                }
            }
        }).a();
    }

    private static void m25651m() {
        SingApplication.d().a("InitAppTask.OP_REFRESH_BALANCE", Arrays.asList(new String[]{"InitAppTask.OP_NETWORK_CONNECTED", "InitAppTask.OP_USER_LOGGED_IN"}), new Operation() {
            public void m25624a(final OperationLoader operationLoader) {
                if (UserManager.a().z()) {
                    BalanceManager.a().a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass13 f24424b;

                        public void run() {
                            operationLoader.c(this.f24424b.g);
                        }
                    }, false);
                } else {
                    operationLoader.c(this.g);
                }
            }
        }).a();
    }

    private static void m25652n() {
        SingApplication.d().a("InitAppTask.OP_TRIM_CACHE", null, new Operation() {
            public void m25625a(OperationLoader operationLoader) {
                CacheUtils.m25808a(ResourceUtils.m19032b(SingApplication.f()), 25, 0);
                Thread thread = new Thread(new DiskUsageRunnable(SingApplication.f()));
                thread.setPriority(1);
                thread.setName(DiskUsageRunnable.f17755a);
                thread.start();
                operationLoader.c(this.g);
            }
        }).a();
    }

    private static void m25653o() {
        final Context f = SingApplication.f();
        SingApplication.d().a("InitAppTask.OP_SOCIAL_CONNECTED", Collections.singletonList("InitAppTask.OP_USER_LOGGED_IN"), new Operation() {
            public void m25626a(final OperationLoader operationLoader) {
                MagicNetwork.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass15 f24426b;

                    public void run() {
                        MagicTwitter.m18839a(f, f.getString(C1947R.string.twitter_consumer_key), f.getString(C1947R.string.twitter_consumer_secret));
                        if (MagicFacebook.a().c()) {
                            MagicFacebook.a().a(null, true);
                        }
                        operationLoader.c(this.f24426b.g);
                    }
                });
            }
        }).a();
    }

    private static void m25654p() {
        SingApplication.d().a("InitAppTask.OP_CHAT_INIT", Collections.singletonList("InitAppTask.OP_USER_ACTUALLY_LOGGED_IN"), new Operation() {
            public void m25627a(final OperationLoader operationLoader) {
                MagicNetwork.g().post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass16 f24430b;

                    class C49541 implements Runnable {
                        final /* synthetic */ C49551 f24428a;

                        C49541(C49551 c49551) {
                            this.f24428a = c49551;
                        }

                        public void run() {
                            operationLoader.c(this.f24428a.f24430b.g);
                        }
                    }

                    public void run() {
                        if (ChatUtils.a()) {
                            SingApplication.j().a(new C49541(this));
                        }
                    }
                });
            }
        }).a();
    }

    private static void m25655q() {
        SingApplication.d().a("InitAppTask.OP_ADS_INIT", Collections.singletonList("InitAppTask.OP_LOAD_SETTINGS"), new Operation() {

            class C49561 implements Runnable {
                final /* synthetic */ AnonymousClass17 f24431a;

                C49561(AnonymousClass17 anonymousClass17) {
                    this.f24431a = anonymousClass17;
                }

                public void run() {
                    AdsSettingsManager.m17419a().m17422a("sing_google");
                }
            }

            public void m25628a(OperationLoader operationLoader) {
                MagicNetwork.g().post(new C49561(this));
            }
        }).a();
    }

    private static void m25656r() {
        SingApplication.d().a("InitAppTask.OP_APPBOY_INIT", Collections.singletonList("InitAppTask.OP_LOAD_SETTINGS"), new Runnable() {
            public void run() {
                SingAppboy.m23096a().m23100c();
            }
        });
    }

    protected Void m25657a(Void... voidArr) {
        SingApplication.d().a("InitAppTask.OP_RESTORE_PURCHASES", Collections.singletonList("InitAppTask.OP_LOCALIZE_SETTINGS"), new Operation(this) {
            final /* synthetic */ InitAppTask f24434a;

            {
                this.f24434a = r1;
            }

            public void m25629a(final OperationLoader operationLoader) {
                this.f24434a.f24446c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass19 f24433b;

                    public void run() {
                        SingApplication.d().a("InitAppTask.OP_RESTORE_PURCHASES");
                        SubscriptionsRestoreHelper.m18452a().m18457a(this.f24433b.f24434a.f24446c, true);
                        operationLoader.c(this.f24433b.g);
                    }
                });
            }
        }).a();
        SingApplication.d = true;
        return null;
    }

    protected void m25658a(Void voidR) {
        Log.b(f24445b, "network initialization complete.");
        SingApplication.e = false;
    }

    protected void onCancelled() {
        Log.b(f24445b, "network initialization error!");
        SingApplication.e = false;
    }
}
