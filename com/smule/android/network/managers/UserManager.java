/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.graphics.Bitmap
 *  android.os.Handler
 *  android.os.Looper
 *  android.support.annotation.Nullable
 *  android.support.v4.util.Pair
 *  android.text.TextUtils
 *  com.crashlytics.android.Crashlytics
 *  com.crittercism.app.Crittercism
 *  com.facebook.AccessToken
 *  com.facebook.FacebookRequestError
 *  com.facebook.FacebookRequestError$Category
 *  com.facebook.accountkit.Account
 *  com.facebook.accountkit.AccountKit
 *  com.facebook.accountkit.AccountKitCallback
 *  com.facebook.accountkit.AccountKitError
 *  com.facebook.accountkit.PhoneNumber
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.JsonNode
 *  okhttp3.MediaType
 *  okhttp3.MultipartBody
 *  okhttp3.MultipartBody$Part
 *  okhttp3.RequestBody
 *  retrofit2.BitmapRequestBodyConverter
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.crittercism.app.Crittercism;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.l10n.LocaleSettings;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.UserAPI;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManagerBuilder;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountPreference;
import com.smule.android.network.models.ColorTheme;
import com.smule.android.network.models.ProfileCustomizations;
import com.smule.android.network.models.SingUserProfile;
import com.smule.android.network.models.UserInfo;
import com.smule.android.network.models.UserProfile;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.HashUtil;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.RandomString;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.BitmapRequestBodyConverter;
import retrofit2.Call;

public class UserManager {
    private static RandomString O;
    protected static UserManager a;
    private static final String e;
    private EmailOptIn A;
    private String B;
    private List<String> C;
    private volatile String D = "USER_EXISTENCE_TYPE_UNKNOWN";
    private  E = null;
    private boolean F;
    private String G;
    private String H;
    private String I;
    private String J;
    private int K;
    private String L;
    private  M;
    private ProfileCustomizations N;
    private boolean P;
    private Set<String> Q;
    private HashMap<String, Boolean> R;
    protected com.smule.android.network.api.UserAPI b = MagicNetwork.a().a(com.smule.android.network.api.UserAPI.class);
    protected Context c;
    protected final Handler d = new Handler(Looper.getMainLooper());
    private long f = 0;
    private long g = 0;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private String m = null;
    private String n = null;
    private String o = null;
    private String p = null;
    private String q = null;
    private  r = null;
    private boolean s = false;
    private boolean t = false;
    private com.smule.android.network.core.NetworkResponse u;
    private Long v = 0;
    private int w = 0;
    private String x;
    private String y;
    private String z;

    static {
        e = UserManager.class.getName();
        O = new RandomString(8);
    }

    private UserManager() {
    }

    public static String H() {
        return O.a();
    }

    private void Z() {
        MagicNetwork.a(new Runnable(this){
            final /* synthetic */ UserManager a;
            {
                this.a = userManager;
            }

            public void run() {
                UserManager.a(this.a);
            }
        });
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Pair<String, String> a( object) {
        String string2;
        switch (.a[object.ordinal()]) {
            default: {
                string2 = "device_login";
                object = "snp_error";
                do {
                    return new Pair((Object)string2, object);
                    break;
                } while (true);
            }
            case 2: {
                string2 = "facebook";
                object = "fb_error";
                return new Pair((Object)string2, object);
            }
            case 6: {
                string2 = "gplus";
                object = "gplus_error";
                return new Pair((Object)string2, object);
            }
            case 3: {
                string2 = "goog";
                object = "goog_error";
                return new Pair((Object)string2, object);
            }
            case 7: {
                string2 = "guest_login";
                object = "snp_error";
                return new Pair((Object)string2, object);
            }
            case 1: {
                string2 = "sign_in";
                object = "snp_error";
                return new Pair((Object)string2, object);
            }
            case 8: {
                string2 = "sign_up";
                object = "snp_error";
                return new Pair((Object)string2, object);
            }
            case 5: 
        }
        string2 = "acctkit";
        object = "snp_error";
        return new Pair((Object)string2, object);
    }

    private com.smule.android.network.core.NetworkResponse a(RequestBody object) {
        if ((object = NetworkUtils.a(this.b.uploadPicture(MultipartBody.Part.createFormData((String)"picture", (String)"profile.jpg", (RequestBody)object)))) != null && object.c()) {
            this.a((com.smule.android.network.core.NetworkResponse)((Object)object));
        }
        return object;
    }

    public static UserManager a() {
        return a;
    }

    public static void a(Context context) {
        synchronized (UserManager.class) {
            if (a == null) {
                a = new UserManager();
                UserManager.a.c = context.getApplicationContext();
                a.b();
            }
            return;
        }
    }

    private void a( emailStatus) {
        this.E = emailStatus;
    }

    static /* synthetic */ void a(UserManager userManager) {
        userManager.aa();
    }

    static /* synthetic */ void a(UserManager userManager, UserProfile userProfile) {
        userManager.a(userProfile);
    }

    private void a(UserManagerBuilder userManagerBuilder) {
        this.b(userManagerBuilder);
        this.u();
        if (userManagerBuilder.w != null && LocaleSettings.a(userManagerBuilder.w) && LocaleSettings.b() == null) {
            LocaleSettings.a(this.c, LocaleSettings.a(userManagerBuilder.w, Locale.getDefault()));
        }
    }

    private void a(SingUserProfile singUserProfile) {
        this.a(this.b(singUserProfile));
    }

    private void a(UserProfile userProfile) {
        this.a(this.b(userProfile));
    }

    private void aa() {
        synchronized (this) {
            if (this.d()) {
                this.F();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private com.smule.android.network.core.NetworkResponse ab() {
        Object object = this.L != null ? .h : this.r;
        Log.a(e, "relogin via " + object.name());
        switch (.a[object.ordinal()]) {
            case 1: {
                return NetworkUtils.a(this.b.emailLogin(new SnpRequest(){
                    public boolean automaticLogin;
                    public String email;
                    public String password;
                    public Long playerId;
                    public String preferredLang = LocaleSettings.b();
                    public int pulp = com.smule.android.utils.VorgomUtils.b();
                    public int tzOffset = com.smule.android.utils.TimeUtils.a();
                    public boolean vorgom = com.smule.android.utils.VorgomUtils.c();

                    public UserAPI setAutomaticLogin(boolean bl) {
                        this.automaticLogin = bl;
                        if (bl) {
                            this.preferredLang = null;
                        }
                        return this;
                    }

                    public UserAPI setEmail(String string2) {
                        this.email = string2;
                        return this;
                    }

                    public UserAPI setPassword(String string2) {
                        this.password = string2;
                        return this;
                    }

                    public UserAPI setPlayerId(Long l) {
                        this.playerId = l;
                        return this;
                    }
                }.setEmail(this.i).setPassword(this.j).setPlayerId(this.g).setAutomaticLogin(true)));
            }
            case 2: {
                object = com.smule.android.facebook.MagicFacebook.a().a(this.i, true);
                if (object == null) {
                    Log.e(e, "fb:unable to get user info");
                    return null;
                }
                if (object.a()) {
                    return NetworkUtils.a(this.b.facebookLogin(new UserAPI.FacebookLoginRequest().setAfbId(object.b).setAccessToken(object.a.getToken()).setFirstName(object.h).setLastName(object.i).setGender(object.d).setBirthday(object.e).setMinAge(object.j).setMaxAge(object.k).setRequestedPassword(this.j).setPlayerId(this.g).setAutomaticLogin(true).setTfb(object.f)));
                }
                if (object.l.getCategory() == FacebookRequestError.Category.LOGIN_RECOVERABLE && com.smule.android.facebook.MagicFacebook.a().b() != null) {
                    Log.b(e, "fb:falling back to last known values");
                    return NetworkUtils.a(this.b.facebookLogin(new UserAPI.FacebookLoginRequest().setAfbId(com.smule.android.facebook.MagicFacebook.a().b().getUserId()).setAccessToken(com.smule.android.facebook.MagicFacebook.a().b().getToken()).setFirstName(this.n).setLastName(this.o).setGender(this.p).setBirthday(this.q).setRequestedPassword(this.j).setPlayerId(this.g).setAutomaticLogin(true).setTfb(this.x)));
                }
                return com.smule.android.facebook.MagicFacebook.a(object.l);
            }
            case 3: {
                return NetworkUtils.a(this.b.googleSignInLogin(new SnpRequest(){
                    public String advId;
                    public boolean automaticLogin;
                    public Long playerId;
                    public String preferredLang = LocaleSettings.b();
                    public int pulp = com.smule.android.utils.VorgomUtils.b();
                    public String requestedPassword;
                    public String token;
                    public int tzOffset = com.smule.android.utils.TimeUtils.a();
                    public boolean vorgom = com.smule.android.utils.VorgomUtils.c();

                    public UserAPI setAdvId(String string2) {
                        this.advId = string2;
                        return this;
                    }

                    public UserAPI setAutomaticLogin(boolean bl) {
                        this.automaticLogin = bl;
                        if (bl) {
                            this.preferredLang = null;
                        }
                        return this;
                    }

                    public UserAPI setPlayerId(Long l) {
                        this.playerId = l;
                        return this;
                    }

                    public UserAPI setRequestedPassword(String string2) {
                        this.requestedPassword = string2;
                        return this;
                    }

                    public UserAPI setToken(String string2) {
                        this.token = string2;
                        return this;
                    }
                }.setToken(this.y).setRequestedPassword(this.j).setPlayerId(this.g).setAutomaticLogin(true).setAdvId(MagicNetwork.d().getAdvertisingId(true))));
            }
            case 4: {
                return NetworkUtils.a(this.b.refreshTokenLogin(new SnpRequest(){
                    public String refreshToken;

                    public UserAPI setCommonRequest(UserAPI loginRequestCommonRequest) {
                        this.common = loginRequestCommonRequest;
                        return this;
                    }

                    public UserAPI setRefreshToken(String string2) {
                        this.refreshToken = string2;
                        return this;
                    }
                }.setRefreshToken(this.L).setCommonRequest(new SnpRequest(){
                    public boolean automaticLogin = true;
                    public Long playerId;
                    public String preferredLang = LocaleSettings.b();
                    public int pulp = com.smule.android.utils.VorgomUtils.b();
                    public int tzOffset = com.smule.android.utils.TimeUtils.a();
                    public boolean vorgom = com.smule.android.utils.VorgomUtils.c();

                    public UserAPI setAutomaticLogin(boolean bl) {
                        this.automaticLogin = bl;
                        if (bl) {
                            this.preferredLang = null;
                        }
                        return this;
                    }

                    public UserAPI setPlayerId(Long l) {
                        if (l != null && l != 0) {
                            this.playerId = l;
                        }
                        return this;
                    }
                }.setAutomaticLogin(true).setPlayerId(this.g))));
            }
            case 5: {
                object = AccountKit.getCurrentAccessToken();
                if (object == null) {
                    return null;
                }
                return NetworkUtils.a(this.b.phoneLogin(new SnpRequest(){
                    public String accessToken;
                    public String fbAppId;

                    public UserAPI setAccessToken(String string2) {
                        this.accessToken = string2;
                        return this;
                    }

                    public UserAPI setCommonRequest(UserAPI loginRequestCommonRequest) {
                        this.common = loginRequestCommonRequest;
                        return this;
                    }

                    public UserAPI setFbAppId(String string2) {
                        this.fbAppId = string2;
                        return this;
                    }
                }.setFbAppId(AccountKit.getApplicationId()).setAccessToken(object.getToken()).setCommonRequest(new .setAutomaticLogin(true).setPlayerId(this.g))));
            }
            case 6: {
                if (MagicNetwork.d().allowGooglePlus()) {
                    if (this.y == null || this.m == null) break;
                    return NetworkUtils.a(this.b.googlePlusLogin(new UserAPI.GooglePlusLoginRequest().setId(this.m).setAccessToken(this.y).setEmail(this.i).setGender(this.p).setBirthday(this.q).setFirstName(this.n).setLastName(this.o).setRequestedPassword(this.j).setPlayerId(this.g).setAutomaticLogin(true)));
                }
            }
            default: {
                if (this.h == null || this.h.isEmpty()) break;
                return NetworkUtils.a(this.b.deviceLogin(new SnpRequest(){
                    public boolean automaticLogin;
                    public Long playerId;
                    public String preferredLang = LocaleSettings.b();
                    public int pulp = com.smule.android.utils.VorgomUtils.b();
                    public int tzOffset = com.smule.android.utils.TimeUtils.a();
                    public boolean vorgom = com.smule.android.utils.VorgomUtils.c();

                    public UserAPI setAutomaticLogin(boolean bl) {
                        this.automaticLogin = bl;
                        if (bl) {
                            this.preferredLang = null;
                        }
                        return this;
                    }

                    public UserAPI setPlayerId(Long l) {
                        this.playerId = l;
                        return this;
                    }
                }.setAutomaticLogin(true).setPlayerId(this.g)));
            }
        }
        return null;
    }

    private UserManagerBuilder b(SingUserProfile singUserProfile) {
        UserManagerBuilder userManagerBuilder = this.b(singUserProfile.profile);
        if (singUserProfile.singProfile != null) {
            userManagerBuilder.a(singUserProfile.singProfile);
        }
        return userManagerBuilder;
    }

    private UserManagerBuilder b(UserProfile userProfile) {
        return new UserManagerBuilder().a(userProfile.getHandle()).b(this.i).a(this.f).c(userProfile.getPictureUrl()).b(this.g).d(this.j).e(this.l).f(this.m).g(this.n).h(this.o).i(this.p).j(this.q).a(this.r).a(this.v).a(this.w).m(this.x).k(this.y).l(this.z).a(this.A).n(this.B);
    }

    private void b(UserManagerBuilder userManagerBuilder) {
        synchronized (this) {
            SharedPreferences.Editor editor = this.c.getSharedPreferences("user", 0).edit();
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.d)) {
                editor.putString("email", userManagerBuilder.d);
                this.i = userManagerBuilder.d;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.e)) {
                editor.putString("password", userManagerBuilder.e);
                this.j = userManagerBuilder.e;
            }
            if (userManagerBuilder.a != 0) {
                editor.putLong("account", userManagerBuilder.a);
                this.f = userManagerBuilder.a;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.f)) {
                editor.putString("picUrl", userManagerBuilder.f);
                this.k = userManagerBuilder.f;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.c)) {
                editor.putString("handle", userManagerBuilder.c);
                this.h = userManagerBuilder.c;
            }
            if (userManagerBuilder.b != 0) {
                editor.putLong("player", userManagerBuilder.b);
                this.g = userManagerBuilder.b;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.g)) {
                editor.putString("facebookId", userManagerBuilder.g);
                this.l = userManagerBuilder.g;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.h)) {
                editor.putString("googlePlusId", userManagerBuilder.h);
                this.m = userManagerBuilder.h;
            }
            if (userManagerBuilder.i != null) {
                editor.putString("firstName", userManagerBuilder.i);
                this.n = userManagerBuilder.i;
            }
            if (userManagerBuilder.j != null) {
                editor.putString("lastName", userManagerBuilder.j);
                this.o = userManagerBuilder.j;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.k)) {
                editor.putString("gender", userManagerBuilder.k);
                this.p = userManagerBuilder.k;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.l)) {
                editor.putString("birthday", userManagerBuilder.l);
                this.q = userManagerBuilder.l;
            }
            if (userManagerBuilder.m != null) {
                editor.putInt("login_type", userManagerBuilder.m.ordinal());
                this.r = userManagerBuilder.m;
            }
            if (userManagerBuilder.u != null) {
                editor.putString("profileBlurb", userManagerBuilder.u);
            }
            this.z = userManagerBuilder.u;
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.p)) {
                editor.putString("FB_TOKEN_FOR_BUSINESS", userManagerBuilder.p);
                this.x = userManagerBuilder.p;
            }
            editor.putInt("LOGIN_COUNT", userManagerBuilder.o);
            this.w = userManagerBuilder.o;
            if (userManagerBuilder.B != null) {
                editor.putInt("newsletterOptIn", userManagerBuilder.B.a().intValue());
                this.A = userManagerBuilder.B;
            }
            if (userManagerBuilder.C != null) {
                editor.putString("jid", userManagerBuilder.C);
                this.B = userManagerBuilder.C;
            }
            if (userManagerBuilder.D != null) {
                this.C = userManagerBuilder.D;
                editor.putString("XMPP_HOSTS_KEY", JsonUtils.a(userManagerBuilder.D));
            }
            if (userManagerBuilder.n != null && userManagerBuilder.n != 0) {
                editor.putLong("INSTALL_DATE", userManagerBuilder.n.longValue());
                this.v = userManagerBuilder.n;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.q)) {
                editor.putString("GPLUS_ACCESS_TOKEN", userManagerBuilder.q);
                this.y = userManagerBuilder.q;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.r)) {
                editor.putString("SESSION_TOKEN", userManagerBuilder.r);
                this.J = userManagerBuilder.r;
            }
            if (userManagerBuilder.s > 0) {
                editor.putInt("SESSION_TOKEN_TTL", userManagerBuilder.s);
                this.K = userManagerBuilder.s;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.t)) {
                editor.putString("REFRESH_TOKEN", userManagerBuilder.t);
                this.L = userManagerBuilder.t;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.q)) {
                editor.putString("GPLUS_ACCESS_TOKEN", userManagerBuilder.q);
                this.y = userManagerBuilder.q;
            }
            if (userManagerBuilder.E != null) {
                editor.putString("PROFILE_CUSTOMIZATIONS_KEY", JsonUtils.a(userManagerBuilder.E));
                this.N = userManagerBuilder.E;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.v) && this.N != null) {
                this.N.coverUrl = userManagerBuilder.v;
                editor.putString("PROFILE_CUSTOMIZATIONS_KEY", JsonUtils.a(userManagerBuilder.E));
            }
            editor.putBoolean("REQUIRE_POLICY_UPDATE", userManagerBuilder.x);
            this.F = userManagerBuilder.x;
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.y)) {
                editor.putString("POLICY_VERSION", userManagerBuilder.y);
                this.G = userManagerBuilder.y;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.z)) {
                editor.putString("POLICY_URL", userManagerBuilder.z);
                this.H = userManagerBuilder.z;
            }
            if (!TextUtils.isEmpty((CharSequence)userManagerBuilder.A)) {
                editor.putString("TERM_URL", userManagerBuilder.A);
                this.I = userManagerBuilder.A;
            }
            editor.apply();
            return;
        }
    }

    private void c(com.smule.android.network.core.NetworkResponse networkResponse) {
        Log.c(e, "logOut called");
        this.t = true;
        this.u = networkResponse;
        if (!MagicNetwork.d().shouldEnforceSession()) {
            this.h = null;
            this.f = 0;
            this.g = 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(@Nullable com.smule.android.network.core.NetworkResponse networkResponse) {
        if (networkResponse == null || networkResponse.a != NetworkResponse.a) return;
        if (networkResponse.b == 0) {
            long l = networkResponse.i;
            if (l <= 0) return;
            {
                EventLogger2.a().a(l * 1000);
                return;
            }
        }
        if (networkResponse.b == 72) {
            return;
        }
        MagicNetwork.a(networkResponse);
    }

    private void r(String string2) {
        Log.b(e, "postLoggedInEvent:" + string2);
        this.D = string2;
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", (Object)string2);
    }

    public void A() {
        this.c((com.smule.android.network.core.NetworkResponse)null);
    }

    public boolean B() {
        if (MagicNetwork.d().shouldEnforceSession() && this.t) {
            return true;
        }
        return false;
    }

    public com.smule.android.network.core.NetworkResponse C() {
        return this.u;
    }

    public void D() {
        this.t = false;
    }

    public boolean E() {
        if (this.r == .c) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void F() {
        Object object = new ArrayList<UserAPI.Uuid>();
        object.add((UserAPI.Uuid)new UserAPI.Uuid().setUuid(MagicNetwork.d().getAndroidId()).setUuidType("androidid"));
        String string2 = MagicNetwork.d().getAdvertisingId(false);
        if (string2 != null) {
            object.add((UserAPI.Uuid)new UserAPI.Uuid().setUuid(string2).setUuidType("advertid"));
        }
        object = NetworkUtils.a(this.b.userInit(new SnpRequest(){
            public List<UserAPI.Uuid> uuids;

            public UserAPI setUuids(List<UserAPI.Uuid> list) {
                this.uuids = list;
                return this;
            }

            public static class UserAPI.Uuid {
                public String uuid;
                public String uuidType;

                public UserAPI.Uuid setUuid(String string2) {
                    this.uuid = string2;
                    return this;
                }

                public UserAPI.Uuid setUuidType(String string2) {
                    this.uuidType = string2;
                    return this;
                }
            }

        }.setUuids((List<UserAPI.Uuid>)object)));
        string2 = e;
        StringBuilder stringBuilder = new StringBuilder().append("Response is null: ");
        boolean bl = object == null;
        Log.a(string2, stringBuilder.append(bl).toString());
        if (object != null && object.c()) {
            this.s = true;
            Log.c(e, "userInit succeeded");
            this.c.getSharedPreferences("user", 0).edit().putBoolean("INIT_CALL_SUCCEEDED", true).apply();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public AccountResponse G() {
        if (MagicNetwork.d().useConsolidatedGuestLogin()) {
            MagicNetwork.a().j();
            this.Z();
            do {
                return AccountResponse.a(NetworkUtils.a(this.b.findAccountByDevice(new SnpRequest())));
                break;
            } while (true);
        }
        this.aa();
        return AccountResponse.a(NetworkUtils.a(this.b.findAccountByDevice(new SnpRequest())));
    }

    public void I() {
        Log.b(e, "fastReLogin");
        this.t = false;
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", (Object)true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public com.smule.android.network.core.NetworkResponse J() {
        Object object;
        Log.b(e, "reLogin");
        this.Z();
        com.smule.android.network.core.NetworkResponse networkResponse = null;
        try {
            object = this.ab();
            networkResponse = object;
        }
        catch (RuntimeException runtimeException) {
            Log.d(e, "doReLogin failed with an exception. Assuming a re-login failure.", runtimeException);
        }
        object = new ParsedResponse(networkResponse){
            public String b;
            public int c;
            public String d;
            public long e;
            public long f;
            public String g;
            public String h;
            public String i;
            public Long j;
            public boolean k = true;
            public int l;
            public EmailOptIn m;
            public String n;
            public Boolean o = false;
            public List<String> p;
            public boolean q;
            public  r;
            public String s;
            public Boolean t = false;
            public Boolean u = false;
            public boolean v;
            public String w;
            public String x;
            public String y;
            {
                JsonNode jsonNode;
                this.a = networkResponse;
                if (networkResponse == null) return;
                if (networkResponse.l == null) return;
                networkResponse = networkResponse.l.has("loginResult") ? networkResponse.l.get("loginResult") : networkResponse.l;
                this.b = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "sessionToken");
                this.c = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "sessionTtl", -1);
                this.d = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "refreshToken");
                this.f = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "accountId", 0);
                this.g = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "email");
                this.e = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "playerId", 0);
                this.h = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "handle");
                this.t = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "handleNew", false);
                this.u = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "handlePrefill", false);
                this.l = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "loginCount", 0);
                this.m = EmailOptIn.a(com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "newsletter", -1));
                this.n = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "jid");
                this.o = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "playerNewlyRegistered", false);
                this.q = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "showEmailOpt", true);
                this.s = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "language");
                this.v = com.smule.android.network.core.NetworkResponse.a((JsonNode)networkResponse, "policyAccepted", false);
                this.w = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "policyVersion");
                this.x = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "policyUrl");
                this.y = com.smule.android.network.core.NetworkResponse.b((JsonNode)networkResponse, "termUrl");
                if (networkResponse.get("picUrl") != null) {
                    this.i = networkResponse.get("picUrl").asText();
                }
                if (networkResponse.has("playerStat") && (jsonNode = networkResponse.get("playerStat")).has("installDate")) {
                    this.j = jsonNode.get("installDate").asLong();
                }
                if (networkResponse.has("elControl") && (jsonNode = networkResponse.get("elControl")).has("npt")) {
                    this.k = jsonNode.get("npt").asBoolean(true);
                }
                if (networkResponse.has("hosts") && (jsonNode = networkResponse.get("hosts")).has("xmpp")) {
                    this.p = this.a(jsonNode.get("xmpp"));
                }
                if (!networkResponse.has("launchParam")) return;
                try {
                    this.r = new Object(networkResponse.get("launchParam")){
                        public String a;
                        public Type b;
                        public Feature c;
                        {
                            if (object == null) {
                                throw new IllegalArgumentException("launchParamNode cannot be null");
                            }
                            this.a = com.smule.android.network.core.NetworkResponse.b((JsonNode)object, "id");
                            if (this.a == null) {
                                throw new IllegalArgumentException("id cannot be null");
                            }
                            String string2 = com.smule.android.network.core.NetworkResponse.b((JsonNode)object, "type");
                            if (string2 == null) {
                                throw new IllegalArgumentException("type cannot be null");
                            }
                            this.b = Type.valueOf(string2);
                            if ((object = com.smule.android.network.core.NetworkResponse.b((JsonNode)object, "feature")) == null) {
                                throw new IllegalArgumentException("feature cannot be null");
                            }
                            this.c = Feature.valueOf((String)object);
                        }

                        public static enum Feature {
                            a;
                            

                            private Feature() {
                            }
                        }

                        public static enum Type {
                            a,
                            b;
                            

                            private Type() {
                            }
                        }

                    };
                    return;
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    Log.d(e, "Received invalid launchParam", illegalArgumentException);
                    return;
                }
            }

            private List<String> a(JsonNode jsonNode) {
                if (jsonNode != null) {
                    ArrayList<String> arrayList = new ArrayList<String>(jsonNode.size());
                    for (int i = 0; i < jsonNode.size(); ++i) {
                        arrayList.add(jsonNode.get(i).asText());
                    }
                    return arrayList;
                }
                return new ArrayList<String>(0);
            }
        };
        if (networkResponse != null && networkResponse.c()) {
            this.t = false;
            EventLogger2.a(object.k);
            this.a(object.r);
            this.a(new UserManagerBuilder().a(object.h).b(object.g).a(object.f).c(object.i).b(object.e).g(this.n).h(this.o).i(this.p).j(this.q).a(object.j).a(object.l).a(object.m).n(object.n).a(object.p).p(object.d).r(object.s).a(object.v).s(object.w).t(object.x).u(object.y));
            if (object.t.booleanValue()) {
                NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
            }
            NotificationCenter.a().b("USER_RE_LOGGED_IN_EVENT", new Object[0]);
            this.d.postDelayed(new Runnable(this.r){
                private final  b;
                {
                    this.b = loginType;
                }

                @Override
                public void run() {
                    Log.a(e, "Update external token: " + this.b.name());
                    switch (.a[this.b.ordinal()]) {
                        default: {
                            return;
                        }
                        case 5: 
                    }
                    AccountKit.getCurrentAccount((AccountKitCallback)new AccountKitCallback<Account>(){

                        public void a(Account account) {
                            Log.a(e, "Updated credentials for PHONE login: " + (Object)account.getPhoneNumber());
                        }

                        public void onError(AccountKitError accountKitError) {
                            Log.d(e, "Failed to update credentials for PHONE login: " + (Object)accountKitError);
                        }

                        public /* synthetic */ void onSuccess(Object object) {
                            this.a((Account)object);
                        }
                    });
                }

            }, TimeUnit.SECONDS.toMillis(1));
        } else {
            if (networkResponse != null && networkResponse.b == 76 && this.L != null) {
                Log.a(e, "Refresh token was invalidated; flushing and logging in via primary method");
                this.c();
                return this.J();
            }
            if (networkResponse == null || networkResponse.a == NetworkResponse.a && networkResponse.b != 2000) {
                Log.b(e, "user logged out");
                this.c(networkResponse);
                NotificationCenter.a().a("AUTO_LOGIN_FAILED", (Object)networkResponse);
            } else if (networkResponse.b == 2000) {
                this.t = true;
            } else {
                Log.b(e, "ignoring doReLogin response");
            }
        }
        if (networkResponse == null || !networkResponse.c()) {
            NotificationCenter.a().a("AUTO_LOGIN_FAILED_NEW", (Object)networkResponse);
        }
        this.b(networkResponse, this.r);
        return networkResponse;
    }

    /*
     * Enabled aggressive block sorting
     */
    public  K() {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.deviceLogin(new .setAutomaticLogin(false)));
        ParsedResponse parsedResponse = new ;
        if (networkResponse.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).c(parsedResponse.i).b(parsedResponse.e).a(.d).a(parsedResponse.j).a(parsedResponse.l).a(parsedResponse.m).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            this.r("USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a(networkResponse, .d);
        return parsedResponse;
    }

    @Deprecated
    public com.smule.android.network.core.NetworkResponse L() {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.facebookDisconnect(new SnpRequest()));
        Log.c(e, "connectWithFacebook response : " + networkResponse.j);
        return networkResponse;
    }

    @Deprecated
    public com.smule.android.network.core.NetworkResponse M() {
        this.aa();
        this.m = null;
        this.y = null;
        this.c.getSharedPreferences("user", 0).edit().remove("googlePlusId").remove("GPLUS_ACCESS_TOKEN").apply();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.googlePlusDisconnect(new SnpRequest()));
        Log.c(e, "disconnectWithGooglePlus response : " + networkResponse.j);
        return networkResponse;
    }

    public com.smule.android.network.core.NetworkResponse N() {
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.pictureDelete(new SnpRequest()));
        if (networkResponse != null && networkResponse.c()) {
            this.a(networkResponse);
        }
        return networkResponse;
    }

    public AccountIcon O() {
        AccountIcon accountIcon = new AccountIcon();
        accountIcon.handle = this.h;
        accountIcon.accountId = this.f;
        accountIcon.picUrl = this.k;
        accountIcon.jid = this.B;
        if (SubscriptionManager.a().b()) {
            accountIcon.appsWithSubscription = new HashSet();
            accountIcon.appsWithSubscription.add(MagicNetwork.d().getAppUID());
        }
        return accountIcon;
    }

    public boolean P() {
        if (this.Q == null || this.Q.isEmpty()) {
            return true;
        }
        return false;
    }

    public boolean Q() {
        return this.P;
    }

    public com.smule.android.network.core.NetworkResponse R() {
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.singCoverPhotoDelete(new SnpRequest()));
        if (networkResponse != null && networkResponse.c()) {
            this.a(new UserManagerBuilder().q("").a(this.w));
        }
        return networkResponse;
    }

    public void S() {
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("user", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (sharedPreferences.contains("facebookId")) {
            editor.remove("facebookId");
            this.l = null;
        }
        editor.apply();
    }

    public  T() {
        return this.E;
    }

    public boolean U() {
        return this.F;
    }

    public String V() {
        return this.G;
    }

    public String W() {
        return this.H;
    }

    public String X() {
        return this.I;
    }

    public int a(long l, long l2) throws NoSuchAlgorithmException {
        return HashUtil.a(this.g, l2, l);
    }

    public com.smule.android.network.core.NetworkResponse a(long l) {
        return NetworkUtils.a(this.b.userBlurb(new SnpRequest(){
            public Long accountId;

            public UserAPI setAccountId(Long l) {
                this.accountId = l;
                return this;
            }
        }.setAccountId(l)));
    }

    public com.smule.android.network.core.NetworkResponse a(Bitmap bitmap) {
        return this.a(BitmapRequestBodyConverter.convertTo((Bitmap)bitmap));
    }

    public com.smule.android.network.core.NetworkResponse a(MagicFacebook facebookUserInfo) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.facebookConnect(new SnpRequest(){
            public String accessToken;
            public String afbId;
            public String birthday;
            public String fbAppId = MagicNetwork.d().getFacebookAppId();
            public String fbEmail;
            public String firstName;
            public String gender;
            public String lastName;
            public Integer maxAge;
            public Integer minAge;
            public String tfb;

            public UserAPI setAccessToken(String string2) {
                this.accessToken = string2;
                return this;
            }

            public UserAPI setAfbId(String string2) {
                this.afbId = string2;
                return this;
            }

            public UserAPI setBirthday(String string2) {
                this.birthday = string2;
                return this;
            }

            public UserAPI setFbAppId(String string2) {
                this.fbAppId = string2;
                return this;
            }

            public UserAPI setFbEmail(String string2) {
                this.fbEmail = string2;
                return this;
            }

            public UserAPI setFirstName(String string2) {
                this.firstName = string2;
                return this;
            }

            public UserAPI setGender(String string2) {
                this.gender = string2;
                return this;
            }

            public UserAPI setLastName(String string2) {
                this.lastName = string2;
                return this;
            }

            public UserAPI setMaxAge(Integer n) {
                this.maxAge = n;
                return this;
            }

            public UserAPI setMinAge(Integer n) {
                this.minAge = n;
                return this;
            }

            public UserAPI setTfb(String string2) {
                this.tfb = string2;
                return this;
            }
        }.setAfbId(facebookUserInfo.b).setAccessToken(facebookUserInfo.a.getToken()).setFbEmail(facebookUserInfo.c).setGender(facebookUserInfo.d).setBirthday(facebookUserInfo.e).setMinAge(facebookUserInfo.j).setMaxAge(facebookUserInfo.k).setTfb(facebookUserInfo.f)));
        Log.c(e, "connectWithFacebook response : " + networkResponse.j);
        this.n(facebookUserInfo.b);
        return networkResponse;
    }

    public com.smule.android.network.core.NetworkResponse a(ColorTheme colorTheme, String string2, String string3, Boolean bl) {
        return NetworkUtils.a(this.b.singProfileUpdate(new SnpRequest(){
            public ColorTheme colorTheme;
            public Boolean displayMentions;
            public String displayName;
            public String pinPerformanceKey;

            public UserAPI setColorTheme(ColorTheme colorTheme) {
                this.colorTheme = colorTheme;
                return this;
            }

            public UserAPI setDisplayMentions(Boolean bl) {
                this.displayMentions = bl;
                return this;
            }

            public UserAPI setDisplayName(String string2) {
                this.displayName = string2;
                return this;
            }

            public UserAPI setPinPerformanceKey(String string2) {
                this.pinPerformanceKey = string2;
                return this;
            }
        }.setColorTheme(colorTheme).setDisplayName(string2).setPinPerformanceKey(string3).setDisplayMentions(bl)));
    }

    public com.smule.android.network.core.NetworkResponse a(File file) {
        return this.a(RequestBody.create((MediaType)MediaType.a((String)"image/jpeg"), (File)file));
    }

    public com.smule.android.network.core.NetworkResponse a(String string2, String string3, String string4, EmailOptIn emailOptIn) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.userUpdate(new SnpRequest(){
            public String email;
            public String handle;
            public Integer newsletter;
            public String password;

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }

            public UserAPI setHandle(String string2) {
                this.handle = string2;
                return this;
            }

            public UserAPI setNewsletter(EmailOptIn emailOptIn) {
                if (emailOptIn != null) {
                    this.newsletter = emailOptIn.a();
                }
                return this;
            }

            public UserAPI setPassword(String string2) {
                this.password = string2;
                return this;
            }
        }.setEmail(string3).setHandle(string2).setPassword(string4).setNewsletter(emailOptIn)));
        Log.c(e, "userUpdate response : " + networkResponse.j);
        if (networkResponse.c()) {
            this.a(new UserManagerBuilder().a(string2).b(string3).a(this.f).c(this.k).b(this.g).d(string4).i(this.p).j(this.q).a(this.w).a(emailOptIn).n(this.B));
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
        return networkResponse;
    }

    @Deprecated
    public com.smule.android.network.core.NetworkResponse a(String object, String string2, String string3, String string4, String string5) {
        this.aa();
        object = NetworkUtils.a(this.b.googlePlusConnect(new SnpRequest(){
            public String accessToken;
            public String birthday;
            public String email;
            public String gender;
            public String id;

            public UserAPI setAccessToken(String string2) {
                this.accessToken = string2;
                return this;
            }

            public UserAPI setBirthday(String string2) {
                this.birthday = string2;
                return this;
            }

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }

            public UserAPI setGender(String string2) {
                this.gender = string2;
                return this;
            }

            public UserAPI setId(String string2) {
                this.id = string2;
                return this;
            }
        }.setId((String)object).setAccessToken(string2).setEmail(string3).setGender(string4).setBirthday(string5)));
        Log.c(e, "connectWithGooglePlus response : " + object.j);
        return object;
    }

    public com.smule.android.network.core.NetworkResponse a(List<Long> list, List<Long> list2) {
        return NetworkUtils.a(this.b.blockUnblockUsers(new SnpRequest(){
            public List<Long> add;
            public List<Long> remove;

            public UserAPI setAdd(List<Long> list) {
                this.add = list;
                return this;
            }

            public UserAPI setRemove(List<Long> list) {
                this.remove = list;
                return this;
            }
        }.setAdd(list).setRemove(list2)));
    }

    public AccountIconsResponse a(Collection<Long> collection) {
        if (collection.size() > 25) {
            Log.e(e, "lookupAccountsByIds queried with greater than 25 icons");
        }
        return AccountIconsResponse.a(NetworkUtils.a(this.b.lookupAccounts(new SnpRequest(){
            public Collection<Long> accountIds;

            public UserAPI setAccountIds(Collection<Long> collection) {
                this.accountIds = collection;
                return this;
            }
        }.setAccountIds(collection))));
    }

    /*
     * Enabled aggressive block sorting
     */
    public  a(boolean bl) {
        Object object;
        if (MagicNetwork.d().useConsolidatedGuestLogin()) {
            object = new .setPlayerId(this.g());
            object = new SnpRequest(){
                public boolean forceNewPlayer;
                public boolean lookupAccount;
                public List<String> settingsIds;

                public UserAPI setForceNewPlayer(boolean bl) {
                    this.forceNewPlayer = bl;
                    return this;
                }

                public UserAPI setLoginRequestCommonRequest(UserAPI loginRequestCommonRequest) {
                    this.common = loginRequestCommonRequest;
                    return this;
                }

                public UserAPI setLookupAccount(boolean bl) {
                    this.lookupAccount = bl;
                    return this;
                }

                public UserAPI setSettingsIds(List<String> list) {
                    this.settingsIds = list;
                    return this;
                }
            }.setForceNewPlayer(bl).setLoginRequestCommonRequest((Object)object).setLookupAccount(true);
            object = NetworkUtils.a(this.b.consolidatedGuestLogin((Object)object));
            this.Z();
        } else {
            this.aa();
            object = NetworkUtils.a(this.b.guestLogin(new SnpRequest(){
                public boolean automaticLogin = true;
                public boolean forceNewPlayer;
                public Long playerId;
                public String preferredLang = LocaleSettings.b();
                public int tzOffset = com.smule.android.utils.TimeUtils.a();

                public UserAPI setForceNewPlayer(boolean bl) {
                    this.forceNewPlayer = bl;
                    return this;
                }

                public UserAPI setPlayerId(Long l) {
                    if (l != null && l != 0) {
                        this.playerId = l;
                    }
                    return this;
                }
            }.setForceNewPlayer(bl).setPlayerId(this.g())));
        }
        Object object2 = new Object((com.smule.android.network.core.NetworkResponse)((Object)object)){
            public long a;
            public com.smule.android.network.core.NetworkResponse b;
            public Long c;
            public boolean d = true;
            public int e;
            public  f;
            public String g;
            public boolean h;
            public String i;
            public String j;
            public String k;
            {
                this.b = networkResponse;
                if (networkResponse != null && networkResponse.l != null) {
                    JsonNode jsonNode;
                    if (networkResponse.l.get("playerId") != null) {
                        this.a = networkResponse.l.get("playerId").asLong();
                    }
                    if (networkResponse.l.get("language") != null) {
                        this.g = networkResponse.l.get("language").asText();
                    }
                    if (networkResponse.l.has("playerStat") && (jsonNode = networkResponse.l.get("playerStat")).has("installDate")) {
                        this.c = jsonNode.get("installDate").asLong();
                    }
                    if (networkResponse.l.has("elControl") && (jsonNode = networkResponse.l.get("elControl")).has("npt")) {
                        this.d = jsonNode.get("npt").asBoolean(true);
                    }
                    this.e = com.smule.android.network.core.NetworkResponse.a(networkResponse.l, "loginCount", 0);
                    if (networkResponse.l.has("launchParam")) {
                        try {
                            this.f = new ;
                        }
                        catch (IllegalArgumentException illegalArgumentException) {
                            Log.d(e, "Received invalid launchParam", illegalArgumentException);
                        }
                    }
                    if (networkResponse.l.has("policyAccepted")) {
                        this.h = networkResponse.l.get("policyAccepted").asBoolean();
                    }
                    if (networkResponse.l.has("policyVersion")) {
                        this.i = networkResponse.l.get("policyVersion").asText();
                    }
                    if (networkResponse.l.has("policyUrl")) {
                        this.j = networkResponse.l.get("policyUrl").asText();
                    }
                    if (networkResponse.l.has("termUrl")) {
                        this.k = networkResponse.l.get("termUrl").asText();
                    }
                }
            }
        };
        if (object.c()) {
            this.t = false;
            EventLogger2.a(object2.d);
            this.a(object2.f);
            this.a(new UserManagerBuilder().b(object2.a).a(object2.c).a(object2.e).a(object2.h).s(object2.i).t(object2.j).u(object2.k));
            this.r("USER_EXISTENCE_TYPE_GUEST");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a((com.smule.android.network.core.NetworkResponse)((Object)object), .i);
        return object2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public  a(MagicFacebook facebookUserInfo, String string2, String string3, boolean bl) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.facebookLogin(new UserAPI.FacebookLoginRequest().setAfbId(facebookUserInfo.b).setAccessToken(facebookUserInfo.a.getToken()).setEmail(facebookUserInfo.c, string2).setFirstName(facebookUserInfo.h).setLastName(facebookUserInfo.i).setGender(facebookUserInfo.d).setBirthday(facebookUserInfo.e).setMinAge(facebookUserInfo.j).setMaxAge(facebookUserInfo.k).setRequestedPassword(this.j).setPlayerId(this.g).setAutomaticLogin(bl).setTfb(facebookUserInfo.f)));
        Log.c(e, "loginWithFacebook response : " + networkResponse.j);
        ParsedResponse parsedResponse = new ;
        if (networkResponse.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).c(parsedResponse.i).b(parsedResponse.e).d(string3).e(facebookUserInfo.b).i(facebookUserInfo.d).j(facebookUserInfo.e).a(.c).a(parsedResponse.j).a(parsedResponse.l).a(parsedResponse.m).m(facebookUserInfo.f).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            string2 = parsedResponse.t != false ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING";
            this.r(string2);
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a(networkResponse, .c);
        this.n(facebookUserInfo.b);
        return parsedResponse;
    }

    public  a(MagicFacebook facebookUserInfo, String string2, boolean bl) {
        return this.a(facebookUserInfo, string2, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Deprecated
    public  a(String string2, String string3, String object, String string4, String string5, String string6, String string7, String string8, boolean bl) {
        this.aa();
        object = NetworkUtils.a(this.b.googlePlusLogin(new UserAPI.GooglePlusLoginRequest().setId(string2).setAccessToken(string3).setEmail((String)object).setGender(string4).setBirthday(string5).setFirstName(string6).setLastName(string7).setRequestedPassword(string8).setPlayerId(this.g).setAutomaticLogin(bl)));
        Log.c(e, "loginWithGooglePlus response : " + object.j);
        ParsedResponse parsedResponse = new ;
        if (object.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).c(parsedResponse.i).b(parsedResponse.e).d(string8).f(string2).g(string6).h(string7).i(string4).j(string5).a(.e).a(parsedResponse.j).a(parsedResponse.l).k(string3).a(parsedResponse.m).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            string2 = parsedResponse.t != false ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING";
            this.r(string2);
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a((com.smule.android.network.core.NetworkResponse)((Object)object), .e);
        return parsedResponse;
    }

    /*
     * Enabled aggressive block sorting
     */
    public  a(String string2, String string3, boolean bl) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.googleSignInLogin(new .setToken(string2).setRequestedPassword(string3).setPlayerId(this.g).setAdvId(MagicNetwork.d().getAdvertisingId(true)).setAutomaticLogin(bl)));
        Log.c(e, "loginWithGoogle response : " + networkResponse.j);
        ParsedResponse parsedResponse = new ;
        if (networkResponse.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).c(parsedResponse.i).b(parsedResponse.e).d(string3).a(.f).a(parsedResponse.j).a(parsedResponse.l).k(string2).a(parsedResponse.m).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            string2 = parsedResponse.t != false ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING";
            this.r(string2);
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a(networkResponse, .f);
        return parsedResponse;
    }

    /*
     * Enabled aggressive block sorting
     */
    public RegistrationResponse a(String string2, String string3) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.emailRegister(new SnpRequest(){
            public String email;
            public String password;
            public String preferredLang = LocaleSettings.b();
            public int tzOffset = com.smule.android.utils.TimeUtils.a();

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }

            public UserAPI setPassword(String string2) {
                this.password = string2;
                return this;
            }
        }.setEmail(string2).setPassword(string3)));
        RegistrationResponse registrationResponse = RegistrationResponse.a(networkResponse);
        if (networkResponse.c()) {
            Log.c(e, networkResponse.j);
            this.t = false;
            List<String> list = registrationResponse.serviceHosts != null ? registrationResponse.serviceHosts.get("xmpp") : null;
            this.a(new UserManagerBuilder().a(registrationResponse.handle).b(string2).a(registrationResponse.accountId).c(registrationResponse.picUrl).b(registrationResponse.playerId).d(string3).a(.b).a((Long)System.currentTimeMillis()).a(this.w).a(registrationResponse.b).n(registrationResponse.JID).a(list).a(registrationResponse.policyAccepted).s(registrationResponse.policyVersion).t(registrationResponse.policyUrl).u(registrationResponse.termUrl));
            this.r("USER_EXISTENCE_TYPE_NEW");
            this.d(registrationResponse.policyAccepted);
            this.o(registrationResponse.policyVersion);
            this.p(registrationResponse.policyUrl);
            this.q(registrationResponse.termUrl);
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a(networkResponse, .j);
        return registrationResponse;
    }

    public UserInfo a(String[] object) {
        object = UserInfo.a(NetworkUtils.a(this.b.getEmailStatus(new SnpRequest(){
            public String[] reqInfo;

            public UserAPI setReqInfo(String[] arrstring) {
                this.reqInfo = arrstring;
                return this;
            }
        }.setReqInfo((String[])object))));
        if (object.emailStatus != null) {
            this.a(.valueOf(object.emailStatus));
            return object;
        }
        this.a(.a);
        return object;
    }

    public Future<?> a(long l,  accountIconResponseCallback) {
        if (l == 0) {
            MagicCrittercism.a(new Exception(){});
        }
        return MagicNetwork.a(new Runnable(this, l, accountIconResponseCallback){
            final /* synthetic */ long a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = l;
                this.b = accountIconResponseCallback;
            }

            public void run() {
                AccountIconResponse accountIconResponse = AccountIconResponse.a(NetworkUtils.a(this.c.b.userProfile(new SnpRequest(){
                    public Long accountId;
                    public String email;
                    public String fbId;
                    public String handle;

                    public com.smule.android.network.api.UserAPI$UserProfileRequest setAccountId(Long l) {
                        this.accountId = l;
                        return this;
                    }

                    public com.smule.android.network.api.UserAPI$UserProfileRequest setEmail(String string2) {
                        this.email = string2;
                        return this;
                    }

                    public com.smule.android.network.api.UserAPI$UserProfileRequest setFbId(String string2) {
                        this.fbId = string2;
                        return this;
                    }

                    public com.smule.android.network.api.UserAPI$UserProfileRequest setHandle(String string2) {
                        this.handle = string2;
                        return this;
                    }
                }.setAccountId(this.a))));
                com.smule.android.network.core.CoreUtil.a(this.b, accountIconResponse);
            }
        });
    }

    public Future<?> a(long l,  blockUsersResponseCallback) {
        return this.a(new ArrayList<Long>(Collections.singletonList(l)), blockUsersResponseCallback);
    }

    public Future<?> a(long l,  getUserBlurbResponseCallback) {
        return MagicNetwork.a(new Runnable(this, getUserBlurbResponseCallback, l){
            final /* synthetic */  a;
            final /* synthetic */ long b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = getUserBlurbResponseCallback;
                this.b = l;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, UserBlurbResponse.a(this.c.a(this.b)));
            }
        });
    }

    public Future<?> a(long l,  resendVerificationEmailResponseCallback) {
        return MagicNetwork.a(new Runnable(this, l, resendVerificationEmailResponseCallback){
            final /* synthetic */ long a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = l;
                this.b = resendVerificationEmailResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = this.c.c(this.a);
                com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
            }
        });
    }

    public Future<?> a(long l,  singUserProfileResponseCallback) {
        return MagicNetwork.a(new Runnable(this, l, singUserProfileResponseCallback){
            final /* synthetic */ long a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = l;
                this.b = singUserProfileResponseCallback;
            }

            public void run() {
                SingUserProfile singUserProfile = this.c.b(this.a);
                com.smule.android.network.core.CoreUtil.a(this.b, singUserProfile);
            }
        });
    }

    public Future<?> a( accountResponseCallback) {
        return MagicNetwork.a(new Runnable(this, accountResponseCallback){
            final /* synthetic */  a;
            final /* synthetic */ UserManager b;
            {
                this.b = userManager;
                this.a = accountResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.b.G());
            }
        });
    }

    public Future<?> a( blockedUsersResponseCallback) {
        return MagicNetwork.a(new Runnable(this, blockedUsersResponseCallback){
            final /* synthetic */  a;
            final /* synthetic */ UserManager b;
            {
                this.b = userManager;
                this.a = blockedUsersResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.b.getBlockedUsers(new SnpRequest()));
                com.smule.android.network.core.CoreUtil.a(this.a, BlockedUsersResponse.a(networkResponse));
            }
        });
    }

    public Future<?> a( loginResponseCallback) {
        return MagicNetwork.a(new Runnable(this, loginResponseCallback){
            final /* synthetic */  a;
            final /* synthetic */ UserManager b;
            {
                this.b = userManager;
                this.a = loginResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.b.K());
            }
        });
    }

    public Future<?> a(AccountPreference accountPreference,  updateAccountPreferencesResponseCallback) {
        return this.a(Collections.singletonList(accountPreference), updateAccountPreferencesResponseCallback);
    }

    public Future<?> a(ColorTheme colorTheme, String string2, String string3, Boolean bl, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, colorTheme, string2, string3, bl, networkResponseCallback){
            final /* synthetic */ ColorTheme a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ Boolean d;
            final /* synthetic */ NetworkResponseCallback e;
            final /* synthetic */ UserManager f;
            {
                this.f = userManager;
                this.a = colorTheme;
                this.b = string2;
                this.c = string3;
                this.d = bl;
                this.e = networkResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = this.f.a(this.a, this.b, this.c, this.d);
                com.smule.android.network.core.CoreUtil.a(this.e, networkResponse);
            }
        });
    }

    public Future<?> a(String string2, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.a(new Runnable(this, networkResponseCallback, string2){
            final /* synthetic */ NetworkResponseCallback a;
            final /* synthetic */ String b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = networkResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.c(this.b));
            }
        });
    }

    public Future<?> a(String string2,  accountIconResponseCallback) {
        return MagicNetwork.a(new Runnable(this, accountIconResponseCallback, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = accountIconResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.e(this.b));
            }
        });
    }

    public Future<?> a(String string2,  updateUserBlurbResponseCallback) {
        return MagicNetwork.a(new Runnable(this, string2, updateUserBlurbResponseCallback){
            final /* synthetic */ String a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = string2;
                this.b = updateUserBlurbResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = this.c.d(this.a);
                if (networkResponse != null && networkResponse.c()) {
                    this.c.a(this.a);
                }
                com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
            }
        });
    }

    public Future<?> a(List<String> list,  accountPreferencesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, list, accountPreferencesResponseCallback){
            final /* synthetic */ List a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = list;
                this.b = accountPreferencesResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.c.b.getPreferences(new SnpRequest(){
                    public List<String> names;

                    public com.smule.android.network.api.UserAPI$GetPreferencesRequest setNames(List<String> list) {
                        this.names = list;
                        return this;
                    }
                }.setNames(this.a)));
                com.smule.android.network.core.CoreUtil.a(this.b, AccountPreferencesResponse.a(networkResponse));
            }
        });
    }

    public Future<?> a(List<Long> list,  blockUsersResponseCallback) {
        return MagicNetwork.a(new Runnable(this, list, blockUsersResponseCallback){
            final /* synthetic */ List a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = list;
                this.b = blockUsersResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = this.c.a(this.a, new java.util.LinkedList<Long>());
                com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
            }
        });
    }

    public Future<?> a(List<AccountPreference> list,  updateAccountPreferencesResponseCallback) {
        return MagicNetwork.a(new Runnable(this, list, updateAccountPreferencesResponseCallback){
            final /* synthetic */ List a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = list;
                this.b = updateAccountPreferencesResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.c.b.updatePreferences(new SnpRequest(){
                    public List<AccountPreference> prefs;

                    public com.smule.android.network.api.UserAPI$UpdatePreferencesRequest setPreferences(List<AccountPreference> list) {
                        this.prefs = list;
                        return this;
                    }
                }.setPreferences(this.a)));
                com.smule.android.network.core.CoreUtil.a(this.b, UpdateAccountPreferencesResponse.a(networkResponse, UpdateAccountPreferencesResponse.class));
            }
        });
    }

    public Future<?> a(String[] arrstring,  emailStatusResponseCallback) {
        return MagicNetwork.a(new Runnable(this, arrstring, emailStatusResponseCallback){
            final /* synthetic */ String[] a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = arrstring;
                this.b = emailStatusResponseCallback;
            }

            public void run() {
                UserInfo userInfo = this.c.a(this.a);
                com.smule.android.network.core.CoreUtil.a(this.b, userInfo);
            }
        });
    }

    public void a(com.smule.android.network.core.NetworkResponse object) {
        object = object.l;
        if (object.has("picUrl")) {
            object = object.get("picUrl").asText();
            this.a(new UserManagerBuilder().c((String)object).a(this.w).n(this.B));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public void a(@Nullable com.smule.android.network.core.NetworkResponse networkResponse,  object) {
        Object object2 = this.a((Object)object);
        object = (String)object2.first;
        object2 = (String)object2.second;
        if (networkResponse == null) {
            Analytics.b((String)object, "client_error", "invalid id or token", null);
            return;
        }
        if (networkResponse.a != NetworkResponse.a) {
            Analytics.b((String)object, "snp_error", com.smule.android.network.core.NetworkResponse.b(networkResponse.a), Integer.toString(networkResponse.b));
            return;
        }
        if (networkResponse.b != 0) {
            if (object2.equals("snp_error")) {
                Analytics.b((String)object, (String)object2, com.smule.android.network.core.NetworkResponse.b(networkResponse.a), Integer.toString(networkResponse.b));
            } else {
                Analytics.b((String)object, (String)object2, networkResponse.e, Integer.toString(networkResponse.b));
            }
        }
        this.d(networkResponse);
    }

    public void a( deferredLaunchParam) {
        if (this.M == null) {
            this.M = deferredLaunchParam;
        }
    }

    public void a(Long l) {
        SharedPreferences.Editor editor = this.c.getSharedPreferences("user", 0).edit();
        if (l != null && l != 0) {
            editor.putLong("INSTALL_DATE", l.longValue());
            this.v = l;
        }
        editor.apply();
    }

    public void a(String string2) {
        this.z = string2;
    }

    public com.smule.android.network.core.NetworkResponse b(Bitmap object) {
        if ((object = NetworkUtils.a(this.b.singCoverPhotoUpload(MultipartBody.Part.createFormData((String)"coverPhoto", (String)"coverPhoto.jpg", (RequestBody)BitmapRequestBodyConverter.convertTo((Bitmap)object))))) != null && object.c()) {
            this.b((com.smule.android.network.core.NetworkResponse)((Object)object));
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    public  b(String object, String string2) {
        this.aa();
        object = NetworkUtils.a(this.b.emailLogin(new .setEmail((String)object).setPassword(string2).setPlayerId(this.g).setAutomaticLogin(false)));
        ParsedResponse parsedResponse = new ;
        if (object.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            Log.c(e, object.j);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).c(parsedResponse.i).b(parsedResponse.e).d(string2).a(.b).a(parsedResponse.j).a(parsedResponse.l).a(parsedResponse.m).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            this.r("USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a((com.smule.android.network.core.NetworkResponse)((Object)object), .b);
        return parsedResponse;
    }

    /*
     * Enabled aggressive block sorting
     */
    public  b(boolean bl) {
        this.aa();
        Object object = AccountKit.getCurrentAccessToken();
        if (object == null) {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
            return null;
        }
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.phoneLogin(new .setFbAppId(AccountKit.getApplicationId()).setAccessToken(object.getToken()).setCommonRequest(new .setAutomaticLogin(bl).setPlayerId(this.g))));
        ParsedResponse parsedResponse = new ;
        if (networkResponse.c()) {
            this.t = false;
            EventLogger2.a(parsedResponse.k);
            this.a(parsedResponse.r);
            this.a(new UserManagerBuilder().a(parsedResponse.h).b(parsedResponse.g).a(parsedResponse.f).o(parsedResponse.b).b(parsedResponse.c).p(parsedResponse.d).c(parsedResponse.i).b(parsedResponse.e).a(.g).a(parsedResponse.j).a(parsedResponse.l).a(parsedResponse.m).n(parsedResponse.n).a(parsedResponse.p).r(parsedResponse.s).a(parsedResponse.v).s(parsedResponse.w).t(parsedResponse.x).u(parsedResponse.y));
            object = parsedResponse.t != false ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING";
            this.r((String)object);
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        this.a(networkResponse, .g);
        return parsedResponse;
    }

    public RegistrationResponse b(String string2) {
        return this.a(string2, UserManager.H());
    }

    public SingUserProfile b(long l) {
        SingUserProfile singUserProfile;
        if (l == 0) {
            MagicCrittercism.a(new );
        }
        if ((singUserProfile = SingUserProfile.a(NetworkUtils.a(this.b.singUserProfile(new SnpRequest(){
            public Long accountId;
            public String email;
            public String handle;

            public UserAPI setAccountId(Long l) {
                this.accountId = l;
                return this;
            }

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }

            public UserAPI setHandle(String string2) {
                this.handle = string2;
                return this;
            }
        }.setAccountId(l))))).a() && singUserProfile.profile.b()) {
            this.a(singUserProfile);
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
        return singUserProfile;
    }

    public Future<?> b(long l,  blockUsersResponseCallback) {
        return this.b(new ArrayList<Long>(Collections.singletonList(l)), blockUsersResponseCallback);
    }

    public Future<?> b(String string2,  accountIconResponseCallback) {
        return MagicNetwork.a(new Runnable(this, accountIconResponseCallback, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = accountIconResponseCallback;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.f(this.b));
            }
        });
    }

    public Future<?> b(List<Long> list,  blockUsersResponseCallback) {
        return MagicNetwork.a(new Runnable(this, list, blockUsersResponseCallback){
            final /* synthetic */ List a;
            final /* synthetic */  b;
            final /* synthetic */ UserManager c;
            {
                this.c = userManager;
                this.a = list;
                this.b = blockUsersResponseCallback;
            }

            public void run() {
                com.smule.android.network.core.NetworkResponse networkResponse = this.c.a(new java.util.LinkedList<Long>(), this.a);
                com.smule.android.network.core.CoreUtil.a(this.b, networkResponse);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    protected void b() {
        String string2;
        SharedPreferences sharedPreferences = this.c.getSharedPreferences("user", 0);
        this.h = sharedPreferences.getString("handle", null);
        try {
            this.f = sharedPreferences.getLong("account", 0);
        }
        catch (ClassCastException classCastException) {
            string2 = sharedPreferences.getString("account", null);
            try {
                this.f = Long.parseLong(string2);
            }
            catch (NumberFormatException numberFormatException) {
                this.f = 0;
            }
        }
        try {
            this.g = sharedPreferences.getLong("player", 0);
        }
        catch (ClassCastException classCastException) {
            try {
                this.g = sharedPreferences.getInt("player", 0);
            }
            catch (ClassCastException classCastException2) {
                string2 = sharedPreferences.getString("player", null);
                try {
                    this.g = Long.parseLong(string2);
                }
                catch (NumberFormatException numberFormatException) {
                    this.g = 0;
                }
            }
        }
        this.i = sharedPreferences.getString("email", null);
        this.j = sharedPreferences.getString("password", null);
        this.r = .values()[sharedPreferences.getInt("login_type", 0)];
        this.l = sharedPreferences.getString("facebookId", null);
        this.m = sharedPreferences.getString("googlePlusId", null);
        this.n = sharedPreferences.getString("firstName", null);
        this.o = sharedPreferences.getString("lastName", null);
        this.p = sharedPreferences.getString("gender", null);
        this.q = sharedPreferences.getString("birthday", null);
        this.s = sharedPreferences.getBoolean("INIT_CALL_SUCCEEDED", false);
        this.k = sharedPreferences.getString("picUrl", null);
        this.v = sharedPreferences.getLong("INSTALL_DATE", 0);
        this.w = sharedPreferences.getInt("LOGIN_COUNT", 0);
        this.y = sharedPreferences.getString("GPLUS_ACCESS_TOKEN", null);
        this.z = sharedPreferences.getString("profileBlurb", null);
        this.A = EmailOptIn.a(sharedPreferences.getInt("newsletterOptIn", -1));
        this.x = sharedPreferences.getString("FB_TOKEN_FOR_BUSINESS", null);
        this.B = sharedPreferences.getString("jid", null);
        this.J = sharedPreferences.getString("SESSION_TOKEN", null);
        this.L = sharedPreferences.getString("REFRESH_TOKEN", null);
        this.F = sharedPreferences.getBoolean("REQUIRE_POLICY_UPDATE", false);
        this.G = sharedPreferences.getString("POLICY_VERSION", null);
        this.H = sharedPreferences.getString("POLICY_URL", null);
        this.I = sharedPreferences.getString("TERM_URL", null);
        string2 = sharedPreferences.getString("XMPP_HOSTS_KEY", null);
        if (string2 != null) {
            this.C = JsonUtils.a(string2, new TypeReference<List<String>>(this){
                final /* synthetic */ UserManager a;
                {
                    this.a = userManager;
                }
            });
        }
        if (this.y()) {
            this.D = "USER_EXISTENCE_TYPE_EXISTING";
        }
        this.N = JsonUtils.a(sharedPreferences.getString("PROFILE_CUSTOMIZATIONS_KEY", null), ProfileCustomizations.class);
        Log.b(e, "readPrefs: " + this.D);
    }

    public void b(com.smule.android.network.core.NetworkResponse object) {
        object = object.l;
        if (object.has("coverPhotoUrl")) {
            object = object.get("coverPhotoUrl").asText();
            this.a(new UserManagerBuilder().q((String)object).a(this.w));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @SuppressLint(value={"ValueOfNotAllowedForNumberSubClasses"})
    public void b(@Nullable com.smule.android.network.core.NetworkResponse networkResponse,  object) {
        Object object2 = this.a((Object)object);
        object = (String)object2.first;
        object2 = (String)object2.second;
        if (networkResponse == null) {
            Analytics.c((String)object, "client_error", "invalid id or token", null);
            return;
        }
        if (networkResponse.a != NetworkResponse.a) {
            Analytics.c((String)object, "snp_error", com.smule.android.network.core.NetworkResponse.b(networkResponse.a), Integer.toString(networkResponse.b));
            return;
        }
        if (networkResponse.b != 0) {
            if (object2.equals("snp_error")) {
                Analytics.c((String)object, (String)object2, com.smule.android.network.core.NetworkResponse.b(networkResponse.a), Integer.toString(networkResponse.b));
            } else {
                Analytics.c((String)object, (String)object2, networkResponse.e, Integer.toString(networkResponse.b));
            }
        }
        this.d(networkResponse);
    }

    public com.smule.android.network.core.NetworkResponse c(long l) {
        return NetworkUtils.a(this.b.resendVerificationEmail(new SnpRequest(){
            public long accountId;

            public UserAPI setAccountId(long l) {
                this.accountId = l;
                return this;
            }
        }.setAccountId(l)));
    }

    public com.smule.android.network.core.NetworkResponse c(String string2) {
        return NetworkUtils.a(this.b.passwordReset(new SnpRequest(){
            public String email;

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }
        }.setEmail(string2)));
    }

    public com.smule.android.network.core.NetworkResponse c(String string2, String string3) {
        this.aa();
        com.smule.android.network.core.NetworkResponse networkResponse = NetworkUtils.a(this.b.userPersonalUpdate(new SnpRequest(){
            public String countryCode;
            public String firstName;
            public String lastName;
            public String location;

            public UserAPI setCountryCode(String string2) {
                this.countryCode = string2;
                return this;
            }

            public UserAPI setFirstName(String string2) {
                this.firstName = string2;
                return this;
            }

            public UserAPI setLastName(String string2) {
                this.lastName = string2;
                return this;
            }

            public UserAPI setLocation(String string2) {
                this.location = string2;
                return this;
            }
        }.setFirstName(string2).setLastName(string3)));
        Log.c(e, "userUpdate response : " + networkResponse.j);
        if (networkResponse.c()) {
            this.a(new UserManagerBuilder().a(this.h).b(this.i).a(this.f).c(this.k).b(this.g).d(this.j).i(this.p).j(this.q).a(this.w).a(this.A).g(string2).h(string3).n(this.B));
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
        return networkResponse;
    }

    protected void c() {
        this.c.getSharedPreferences("user", 0).edit().putString("REFRESH_TOKEN", null).apply();
        this.L = null;
    }

    public void c(boolean bl) {
        this.P = bl;
    }

    public com.smule.android.network.core.NetworkResponse d(String string2) {
        return NetworkUtils.a(this.b.updateUserBlurb(new SnpRequest(){
            public String blurb;

            public UserAPI setBlurb(String string2) {
                this.blurb = string2;
                return this;
            }
        }.setBlurb(string2)));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void d(String string2, String string3) {
        if (TextUtils.equals((CharSequence)string2, (CharSequence)this.n) && TextUtils.equals((CharSequence)string3, (CharSequence)this.o)) return;
        boolean bl = true;
        if (!bl) return;
        this.a(new UserManagerBuilder().a(this.h).b(this.i).a(this.f).c(this.k).b(this.g).d(this.j).i(this.p).j(this.q).a(this.w).a(this.A).g(string2).h(string3).n(this.B));
        NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d(boolean bl) {
        boolean bl2 = !bl;
        this.F = bl2;
        this.a(new UserManagerBuilder().a(bl));
    }

    public boolean d() {
        if (!this.s) {
            return true;
        }
        return false;
    }

    public AccountIconResponse e(String string2) {
        return AccountIconResponse.a(NetworkUtils.a(this.b.lookupUser(new SnpRequest(){
            public Long accountId;
            public String email;
            public String handle;

            public UserAPI setAccountId(Long l) {
                this.accountId = l;
                return this;
            }

            public UserAPI setEmail(String string2) {
                this.email = string2;
                return this;
            }

            public UserAPI setHandle(String string2) {
                this.handle = string2;
                return this;
            }
        }.setEmail(string2))));
    }

    public void e() {
        this.s = false;
        this.c.getSharedPreferences("user", 0).edit().putBoolean("INIT_CALL_SUCCEEDED", false).apply();
    }

    public long f() {
        return this.f;
    }

    public AccountIconResponse f(String string2) {
        return AccountIconResponse.a(NetworkUtils.a(this.b.lookupUser(new .setHandle(string2))));
    }

    public long g() {
        return this.g;
    }

    public void g(String string2) {
        if (this.Q == null) {
            this.Q = new HashSet<String>();
        }
        this.Q.add(string2);
        if (!this.Q.isEmpty()) {
            this.P = false;
        }
    }

    public String h() {
        return this.k;
    }

    public void h(String string2) {
        if (this.Q != null) {
            this.Q.remove(string2);
            if (this.Q.isEmpty()) {
                this.P = true;
            }
        }
    }

    public String i() {
        return this.h;
    }

    public boolean i(String string2) {
        if (this.Q != null && this.Q.contains(string2)) {
            return true;
        }
        return false;
    }

    public String j() {
        return this.i;
    }

    public void j(String string2) {
        if (this.R == null) {
            this.R = new HashMap();
        }
        this.R.put(string2, true);
    }

    public String k() {
        return this.n;
    }

    public void k(String string2) {
        if (this.R == null) {
            this.R = new HashMap();
        }
        if (this.R != null) {
            this.R.put(string2, false);
        }
    }

    public String l() {
        return this.o;
    }

    public boolean l(String string2) {
        if (this.R != null && this.R.containsKey(string2)) {
            return true;
        }
        return false;
    }

    public String m() {
        return this.j;
    }

    public boolean m(String string2) {
        if (this.l(string2) && this.R.get(string2).booleanValue()) {
            return true;
        }
        return false;
    }

    public String n() {
        return this.l;
    }

    public void n(String string2) {
        SharedPreferences.Editor editor = this.c.getSharedPreferences("user", 0).edit();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            editor.putString("facebookId", string2);
            this.l = string2;
        }
        editor.apply();
    }

    public String o() {
        return this.B;
    }

    public void o(String string2) {
        this.G = string2;
    }

    public List<String> p() {
        return this.C;
    }

    public void p(String string2) {
        this.H = string2;
    }

    public Long q() {
        return this.v;
    }

    public void q(String string2) {
        this.I = string2;
    }

    public int r() {
        long l = this.q() / 1000;
        return (int)((Long.valueOf(System.currentTimeMillis() / 1000) - Long.valueOf(l)) / 86400);
    }

    public String s() {
        return this.z;
    }

    public EmailOptIn t() {
        return this.A;
    }

    public void u() {
        if (this.f != 0) {
            if (MagicCrittercism.a()) {
                Crittercism.setUsername((String)String.valueOf(this.f));
            }
            if (MagicCrittercism.b()) {
                Crashlytics.setUserIdentifier((String)("" + this.f));
                Crashlytics.setUserName((String)this.h);
                Crashlytics.setUserEmail((String)this.i);
                Crashlytics.setLong((String)"player_id", (long)this.g);
                Crashlytics.setLong((String)"user_id", (long)this.f);
            }
        }
    }

    public ProfileCustomizations v() {
        return this.N;
    }

    public  w() {
        return this.M;
    }

    public boolean x() {
        Log.b(e, "isNewUser:" + this.D);
        return TextUtils.equals((CharSequence)this.D, (CharSequence)"USER_EXISTENCE_TYPE_NEW");
    }

    public boolean y() {
        if (!(MagicNetwork.d().shouldEnforceSession() && this.t || TextUtils.isEmpty((CharSequence)this.h) || this.f == 0)) {
            return true;
        }
        return false;
    }

    public boolean z() {
        if (MagicNetwork.d().shouldEnforceSession() && this.t || this.h == null && this.g != 0) {
            return true;
        }
        return false;
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class AccountIconResponse
    extends ParsedResponse {
        @JsonProperty(value="accountIcon")
        public AccountIcon mAccount;

        static AccountIconResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return AccountIconResponse.a(networkResponse, AccountIconResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class AccountIconsResponse
    extends ParsedResponse {
        @JsonProperty(value="accountIcons")
        public List<AccountIcon> accountIcons;

        static AccountIconsResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return AccountIconsResponse.a(networkResponse, AccountIconsResponse.class);
        }

        public static AccountIconsResponse a(List<AccountIcon> list) {
            com.smule.android.network.core.NetworkResponse networkResponse = new com.smule.android.network.core.NetworkResponse(null);
            networkResponse.a = NetworkResponse.a;
            networkResponse.b = 0;
            AccountIconsResponse accountIconsResponse = new AccountIconsResponse();
            accountIconsResponse.a = networkResponse;
            accountIconsResponse.accountIcons = list;
            return accountIconsResponse;
        }
    }

    public static class AccountPreferencesResponse
    extends ParsedResponse {
        @JsonProperty(value="prefs")
        public List<AccountPreference> preferences;

        static AccountPreferencesResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return AccountPreferencesResponse.a(networkResponse, AccountPreferencesResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class AccountResponse
    extends ParsedResponse {
        @JsonProperty(value="accountId")
        public Long mAccountId;
        @JsonProperty(value="email")
        public String mEmail;
        @JsonProperty(value="handle")
        public String mHandle;
        @JsonProperty(value="picUrl")
        public String mPicUrl;

        public static AccountResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return AccountResponse.a(networkResponse, AccountResponse.class);
        }
    }

    public static class BlockedUsersResponse
    extends ParsedResponse {
        @JsonProperty(value="accountIds")
        public List<Long> accountIds;

        static BlockedUsersResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return BlockedUsersResponse.a(networkResponse, BlockedUsersResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class RegistrationResponse
    extends ParsedResponse {
        @JsonProperty(value="jid")
        public String JID;
        @JsonProperty(value="accountId")
        public long accountId;
        public EmailOptIn b;
        @JsonProperty(value="handle")
        public String handle;
        @JsonProperty(value="language")
        public String language;
        @JsonProperty(value="newsletter")
        public int newsletter;
        @JsonProperty(value="picUrl")
        public String picUrl;
        @JsonProperty(value="playerId")
        public long playerId;
        @JsonProperty(value="policyAccepted")
        public boolean policyAccepted;
        @JsonProperty(value="policyUrl")
        public String policyUrl;
        @JsonProperty(value="policyVersion")
        public String policyVersion;
        @JsonProperty(value="hosts")
        public Map<String, List<String>> serviceHosts;
        @JsonProperty(value="showEmailOpt")
        public boolean showEmailOpt;
        @JsonProperty(value="termUrl")
        public String termUrl;

        public static RegistrationResponse a(com.smule.android.network.core.NetworkResponse object) {
            object = RegistrationResponse.a((com.smule.android.network.core.NetworkResponse)((Object)object), RegistrationResponse.class);
            object.b = EmailOptIn.a(object.newsletter);
            return object;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class UpdateAccountPreferencesResponse
    extends ParsedResponse {
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class UserBlurbResponse
    extends ParsedResponse {
        @JsonProperty(value="blurb")
        public String mBlurb;

        static UserBlurbResponse a(com.smule.android.network.core.NetworkResponse networkResponse) {
            return UserBlurbResponse.a(networkResponse, UserBlurbResponse.class);
        }
    }

}

