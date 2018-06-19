package com.smule.android.network.managers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.crashlytics.android.Crashlytics;
import com.crittercism.app.Crittercism;
import com.facebook.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.facebook.MagicFacebook.FacebookUserInfo;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.api.UserAPI;
import com.smule.android.network.api.UserAPI.AccountsLookupRequest;
import com.smule.android.network.api.UserAPI.BlockUnblockRequest;
import com.smule.android.network.api.UserAPI.ConsolidatedGuestLoginRequest;
import com.smule.android.network.api.UserAPI.DeviceLoginRequest;
import com.smule.android.network.api.UserAPI.EmailLoginRequest;
import com.smule.android.network.api.UserAPI.EmailRegisterRequest;
import com.smule.android.network.api.UserAPI.FacebookConnectRequest;
import com.smule.android.network.api.UserAPI.FacebookLoginRequest;
import com.smule.android.network.api.UserAPI.GoogleLoginRequest;
import com.smule.android.network.api.UserAPI.GooglePlusConnectRequest;
import com.smule.android.network.api.UserAPI.GooglePlusLoginRequest;
import com.smule.android.network.api.UserAPI.GuestLoginRequest;
import com.smule.android.network.api.UserAPI.LoginPhoneRequest;
import com.smule.android.network.api.UserAPI.LoginRequestCommonRequest;
import com.smule.android.network.api.UserAPI.PasswordResetRequest;
import com.smule.android.network.api.UserAPI.UpdateUserBlurbRequest;
import com.smule.android.network.api.UserAPI.UserBlurbRequest;
import com.smule.android.network.api.UserAPI.UserInitRequest;
import com.smule.android.network.api.UserAPI.UserInitRequest.Uuid;
import com.smule.android.network.api.UserAPI.UserLookupRequest;
import com.smule.android.network.api.UserAPI.UserPersonalUpdateRequest;
import com.smule.android.network.api.UserAPI.UserUpdateRequest;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse.Status;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.AccountPreference;
import com.smule.android.utils.EmailOptIn;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.RandomString;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import okhttp3.MultipartBody.Part;
import retrofit2.BitmapRequestBodyConverter;

public class UserManager {
    private static RandomString f6837K = new RandomString(8);
    protected static UserManager f6838a;
    private static final String f6839e = UserManager.class.getName();
    private EmailOptIn f6840A;
    private int f6841B = 0;
    private String f6842C;
    private List<String> f6843D;
    private int f6844E = 0;
    private volatile String f6845F = "USER_EXISTENCE_TYPE_UNKNOWN";
    private String f6846G;
    private int f6847H;
    private String f6848I;
    private DeferredLaunchParam f6849J;
    private boolean f6850L;
    private Set<String> f6851M;
    private HashMap<String, Boolean> f6852N;
    protected UserAPI f6853b = ((UserAPI) MagicNetwork.m7789a().m7817a(UserAPI.class));
    protected Context f6854c;
    protected final Handler f6855d = new Handler(Looper.getMainLooper());
    private long f6856f = 0;
    private long f6857g = 0;
    private String f6858h = null;
    private String f6859i = null;
    private String f6860j = null;
    private String f6861k = null;
    private String f6862l = null;
    private String f6863m = null;
    private String f6864n = null;
    private String f6865o = null;
    private String f6866p = null;
    private String f6867q = null;
    private LoginType f6868r = null;
    private boolean f6869s = false;
    private boolean f6870t = false;
    private NetworkResponse f6871u;
    private Long f6872v = Long.valueOf(0);
    private int f6873w = 0;
    private String f6874x;
    private String f6875y;
    private String f6876z;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountIconResponse extends ParsedResponse {
        @JsonProperty("accountIcon")
        public AccountIcon mAccount;

        static AccountIconResponse m8097a(NetworkResponse networkResponse) {
            return (AccountIconResponse) ParsedResponse.m7676a(networkResponse, AccountIconResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountIconsResponse extends ParsedResponse {
        @JsonProperty("accountIcons")
        public List<AccountIcon> accountIcons;

        static AccountIconsResponse m8098a(NetworkResponse networkResponse) {
            return (AccountIconsResponse) ParsedResponse.m7676a(networkResponse, AccountIconsResponse.class);
        }

        public static AccountIconsResponse m8099a(List<AccountIcon> list) {
            NetworkResponse networkResponse = new NetworkResponse(null);
            networkResponse.f6755a = Status.a;
            networkResponse.f6756b = 0;
            AccountIconsResponse accountIconsResponse = new AccountIconsResponse();
            accountIconsResponse.a = networkResponse;
            accountIconsResponse.accountIcons = list;
            return accountIconsResponse;
        }
    }

    public static class AccountPreferencesResponse extends ParsedResponse {
        @JsonProperty("prefs")
        public List<AccountPreference> preferences;

        static AccountPreferencesResponse m8100a(NetworkResponse networkResponse) {
            return (AccountPreferencesResponse) ParsedResponse.m7676a(networkResponse, AccountPreferencesResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AccountResponse extends ParsedResponse {
        @JsonProperty("accountId")
        public Long mAccountId;
        @JsonProperty("email")
        public String mEmail;
        @JsonProperty("handle")
        public String mHandle;
        @JsonProperty("picUrl")
        public String mPicUrl;

        public static AccountResponse m8101a(NetworkResponse networkResponse) {
            return (AccountResponse) ParsedResponse.m7676a(networkResponse, AccountResponse.class);
        }
    }

    public static class BlockedUsersResponse extends ParsedResponse {
        @JsonProperty("accountIds")
        public List<Long> accountIds;

        static BlockedUsersResponse m8102a(NetworkResponse networkResponse) {
            return (BlockedUsersResponse) ParsedResponse.m7676a(networkResponse, BlockedUsersResponse.class);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RegistrationResponse extends ParsedResponse {
        @JsonProperty("jid")
        public String JID;
        @JsonProperty("accountId")
        public long accountId;
        public EmailOptIn f6836b;
        @JsonProperty("handle")
        public String handle;
        @JsonProperty("newsletter")
        public int newsletter;
        @JsonProperty("picUrl")
        public String picUrl;
        @JsonProperty("playerId")
        public long playerId;
        @JsonProperty("hosts")
        public Map<String, List<String>> serviceHosts;
        @JsonProperty("showEmailOpt")
        public boolean showEmailOpt;

        public static RegistrationResponse m8103a(NetworkResponse networkResponse) {
            RegistrationResponse registrationResponse = (RegistrationResponse) ParsedResponse.m7676a(networkResponse, RegistrationResponse.class);
            registrationResponse.f6836b = EmailOptIn.a(registrationResponse.newsletter);
            return registrationResponse;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UpdateAccountPreferencesResponse extends ParsedResponse {
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserBlurbResponse extends ParsedResponse {
        @JsonProperty("blurb")
        public String mBlurb;

        static UserBlurbResponse m8104a(NetworkResponse networkResponse) {
            return (UserBlurbResponse) ParsedResponse.m7676a(networkResponse, UserBlurbResponse.class);
        }
    }

    public static synchronized void m8112a(Context context) {
        synchronized (UserManager.class) {
            if (f6838a == null) {
                f6838a = new UserManager();
                f6838a.f6854c = context.getApplicationContext();
                f6838a.m8192b();
            }
        }
    }

    public static UserManager m8111a() {
        return f6838a;
    }

    private UserManager() {
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    protected void m8192b() {
        SharedPreferences sharedPreferences = this.f6854c.getSharedPreferences("user", 0);
        this.f6858h = sharedPreferences.getString("handle", null);
        try {
            this.f6856f = sharedPreferences.getLong("account", 0);
        } catch (ClassCastException e) {
            try {
                this.f6856f = Long.parseLong(sharedPreferences.getString("account", null));
            } catch (NumberFormatException e2) {
                this.f6856f = 0;
            }
        }
        try {
            this.f6857g = sharedPreferences.getLong("player", 0);
        } catch (ClassCastException e3) {
            try {
                this.f6857g = (long) sharedPreferences.getInt("player", 0);
            } catch (ClassCastException e4) {
                try {
                    this.f6857g = Long.parseLong(sharedPreferences.getString("player", null));
                } catch (NumberFormatException e5) {
                    this.f6857g = 0;
                }
            }
        }
        this.f6859i = sharedPreferences.getString("email", null);
        this.f6860j = sharedPreferences.getString("password", null);
        this.f6868r = LoginType.values()[sharedPreferences.getInt("login_type", 0)];
        this.f6862l = sharedPreferences.getString("facebookId", null);
        this.f6863m = sharedPreferences.getString("googlePlusId", null);
        this.f6864n = sharedPreferences.getString("firstName", null);
        this.f6865o = sharedPreferences.getString("lastName", null);
        this.f6866p = sharedPreferences.getString("gender", null);
        this.f6867q = sharedPreferences.getString("birthday", null);
        this.f6869s = sharedPreferences.getBoolean("INIT_CALL_SUCCEEDED", false);
        this.f6861k = sharedPreferences.getString("picUrl", null);
        this.f6872v = Long.valueOf(sharedPreferences.getLong("INSTALL_DATE", 0));
        this.f6873w = sharedPreferences.getInt("LOGIN_COUNT", 0);
        this.f6875y = sharedPreferences.getString("GPLUS_ACCESS_TOKEN", null);
        this.f6876z = sharedPreferences.getString("profileBlurb", null);
        this.f6840A = EmailOptIn.a(sharedPreferences.getInt("newsletterOptIn", -1));
        this.f6874x = sharedPreferences.getString("FB_TOKEN_FOR_BUSINESS", null);
        this.f6842C = sharedPreferences.getString("jid", null);
        this.f6846G = sharedPreferences.getString("SESSION_TOKEN", null);
        this.f6848I = sharedPreferences.getString("REFRESH_TOKEN", null);
        String string = sharedPreferences.getString("XMPP_HOSTS_KEY", null);
        if (string != null) {
            this.f6843D = JsonUtils.a(string, new 1(this));
        }
        if (m8231z()) {
            this.f6845F = "USER_EXISTENCE_TYPE_EXISTING";
        }
        Log.m7770b(f6839e, "readPrefs: " + this.f6845F);
    }

    protected void m8196c() {
        this.f6854c.getSharedPreferences("user", 0).edit().putString("REFRESH_TOKEN", null).apply();
        this.f6848I = null;
    }

    public boolean m8200d() {
        return !this.f6869s;
    }

    public void m8202e() {
        this.f6869s = false;
        this.f6854c.getSharedPreferences("user", 0).edit().putBoolean("INIT_CALL_SUCCEEDED", false).apply();
    }

    public long m8203f() {
        return this.f6856f;
    }

    public long m8205g() {
        return this.f6857g;
    }

    public String m8207h() {
        return this.f6861k;
    }

    public String m8209i() {
        return this.f6858h;
    }

    public String m8211j() {
        return this.f6859i;
    }

    public String m8213k() {
        return this.f6864n;
    }

    public String m8215l() {
        return this.f6865o;
    }

    public String m8217m() {
        return this.f6860j;
    }

    public String m8219n() {
        return this.f6862l;
    }

    public String m8220o() {
        return this.f6842C;
    }

    public List<String> m8221p() {
        return this.f6843D;
    }

    public Long m8222q() {
        return this.f6872v;
    }

    public int m8223r() {
        return (int) ((Long.valueOf(System.currentTimeMillis() / 1000).longValue() - Long.valueOf(m8222q().longValue() / 1000).longValue()) / 86400);
    }

    public int m8224s() {
        return this.f6841B;
    }

    public int m8225t() {
        return this.f6844E;
    }

    public String m8226u() {
        return this.f6876z;
    }

    public void m8185a(String str) {
        this.f6876z = str;
    }

    public EmailOptIn m8227v() {
        return this.f6840A;
    }

    public void m8228w() {
        if (this.f6856f != 0) {
            if (MagicCrittercism.m7781a()) {
                Crittercism.setUsername(String.valueOf(this.f6856f));
            }
            if (MagicCrittercism.m7783b()) {
                Crashlytics.setUserIdentifier("" + this.f6856f);
                Crashlytics.setUserName(this.f6858h);
                Crashlytics.setUserEmail(this.f6859i);
                Crashlytics.setBool("VIP", true);
                Crashlytics.setLong("player_id", this.f6857g);
                Crashlytics.setLong(AccessToken.USER_ID_KEY, this.f6856f);
            }
        }
    }

    public DeferredLaunchParam m8229x() {
        return this.f6849J;
    }

    public void m8183a(DeferredLaunchParam deferredLaunchParam) {
        if (this.f6849J == null) {
            this.f6849J = deferredLaunchParam;
        }
    }

    public boolean m8230y() {
        Log.m7770b(f6839e, "isNewUser:" + this.f6845F);
        return TextUtils.equals(this.f6845F, "USER_EXISTENCE_TYPE_NEW");
    }

    public boolean m8231z() {
        return ((MagicNetwork.m7804d().shouldEnforceSession() && this.f6870t) || TextUtils.isEmpty(this.f6858h) || this.f6856f == 0) ? false : true;
    }

    public boolean m8138A() {
        return (MagicNetwork.m7804d().shouldEnforceSession() && this.f6870t) || (this.f6858h == null && this.f6857g != 0);
    }

    public void m8139B() {
        m8117b(null);
    }

    private void m8117b(NetworkResponse networkResponse) {
        Log.m7772c(f6839e, "logOut called");
        this.f6870t = true;
        this.f6871u = networkResponse;
        if (!MagicNetwork.m7804d().shouldEnforceSession()) {
            this.f6858h = null;
            this.f6856f = 0;
            this.f6857g = 0;
        }
    }

    public boolean m8140C() {
        return MagicNetwork.m7804d().shouldEnforceSession() && this.f6870t;
    }

    public NetworkResponse m8141D() {
        return this.f6871u;
    }

    public void m8142E() {
        this.f6870t = false;
    }

    public boolean m8143F() {
        return this.f6868r == LoginType.c;
    }

    private void m8107S() {
        MagicNetwork.m7790a(new 2(this));
    }

    private synchronized void m8108T() {
        if (m8200d()) {
            m8144G();
        }
    }

    public void m8144G() {
        List arrayList = new ArrayList();
        arrayList.add(new Uuid().setUuid(MagicNetwork.m7804d().getAndroidId()).setUuidType("androidid"));
        String advertisingId = MagicNetwork.m7804d().getAdvertisingId(false);
        if (advertisingId != null) {
            arrayList.add(new Uuid().setUuid(advertisingId).setUuidType("advertid"));
        }
        NetworkResponse a = NetworkUtils.a(this.f6853b.userInit(new UserInitRequest().setUuids(arrayList)));
        Log.m7767a(f6839e, "Response is null: " + (a == null));
        if (a != null && a.m7850c()) {
            this.f6869s = true;
            Log.m7772c(f6839e, "userInit succeeded");
            this.f6854c.getSharedPreferences("user", 0).edit().putBoolean("INIT_CALL_SUCCEEDED", true).apply();
        }
    }

    public AccountResponse m8145H() {
        if (MagicNetwork.m7804d().useConsolidatedGuestLogin()) {
            MagicNetwork.m7789a().m7823j();
            m8107S();
        } else {
            m8108T();
        }
        return AccountResponse.m8101a(NetworkUtils.a(this.f6853b.findAccountByDevice(new SnpRequest())));
    }

    public Future<?> m8171a(AccountResponseCallback accountResponseCallback) {
        return MagicNetwork.m7790a(new 3(this, accountResponseCallback));
    }

    public static String m8105I() {
        return f6837K.a();
    }

    public RegistrationResponse m8188b(String str) {
        return m8166a(str, m8105I());
    }

    public RegistrationResponse m8166a(String str, String str2) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.emailRegister(new EmailRegisterRequest().setEmail(str).setPassword(str2)));
        RegistrationResponse a2 = RegistrationResponse.m8103a(a);
        if (a.m7850c()) {
            Log.m7772c(f6839e, a.f6764j);
            this.f6870t = false;
            m8115a(new UserManagerBuilder().a(a2.handle).b(str).a(a2.accountId).c(a2.picUrl).b(a2.playerId).d(str2).a(LoginType.b).a(Long.valueOf(System.currentTimeMillis())).a(this.f6873w).a(a2.f6836b).n(a2.JID).a(a2.serviceHosts != null ? (List) a2.serviceHosts.get("xmpp") : null));
            m8132n("USER_EXISTENCE_TYPE_NEW");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.j);
        return a2;
    }

    public GuestLoginResponse m8161a(boolean z) {
        NetworkResponse a;
        if (MagicNetwork.m7804d().useConsolidatedGuestLogin()) {
            a = NetworkUtils.a(this.f6853b.consolidatedGuestLogin(new ConsolidatedGuestLoginRequest().setForceNewPlayer(z).setLoginRequestCommonRequest(new LoginRequestCommonRequest().setPlayerId(Long.valueOf(m8205g()))).setLookupAccount(true)));
            m8107S();
        } else {
            m8108T();
            a = NetworkUtils.a(this.f6853b.guestLogin(new GuestLoginRequest().setForceNewPlayer(z).setPlayerId(Long.valueOf(m8205g()))));
        }
        GuestLoginResponse guestLoginResponse = new GuestLoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = guestLoginResponse.d;
            this.f6841B = guestLoginResponse.f;
            this.f6844E = guestLoginResponse.g;
            m8183a(guestLoginResponse.h);
            m8115a(new UserManagerBuilder().b(guestLoginResponse.a).a(guestLoginResponse.c).a(guestLoginResponse.e));
            m8132n("USER_EXISTENCE_TYPE_GUEST");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.i);
        return guestLoginResponse;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.smule.android.network.core.NetworkResponse m8109U() {
        /*
        r7 = this;
        r1 = 0;
        r6 = 1;
        r0 = r7.f6848I;
        if (r0 == 0) goto L_0x0059;
    L_0x0006:
        r0 = com.smule.android.network.managers.UserManager.LoginType.h;
    L_0x0008:
        r2 = f6839e;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = "relogin via ";
        r3 = r3.append(r4);
        r4 = r0.name();
        r3 = r3.append(r4);
        r3 = r3.toString();
        com.smule.android.logging.Log.m7767a(r2, r3);
        r2 = com.smule.android.network.managers.UserManager.30.a;
        r0 = r0.ordinal();
        r0 = r2[r0];
        switch(r0) {
            case 1: goto L_0x005c;
            case 2: goto L_0x0086;
            case 3: goto L_0x0187;
            case 4: goto L_0x01be;
            case 5: goto L_0x01ec;
            case 6: goto L_0x022d;
            default: goto L_0x002f;
        };
    L_0x002f:
        r0 = r7.f6858h;
        if (r0 == 0) goto L_0x028e;
    L_0x0033:
        r0 = r7.f6858h;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x028e;
    L_0x003b:
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$DeviceLoginRequest;
        r1.<init>();
        r1 = r1.setAutomaticLogin(r6);
        r2 = r7.f6857g;
        r2 = java.lang.Long.valueOf(r2);
        r1 = r1.setPlayerId(r2);
        r0 = r0.deviceLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
    L_0x0058:
        return r0;
    L_0x0059:
        r0 = r7.f6868r;
        goto L_0x0008;
    L_0x005c:
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$EmailLoginRequest;
        r1.<init>();
        r2 = r7.f6859i;
        r1 = r1.setEmail(r2);
        r2 = r7.f6860j;
        r1 = r1.setPassword(r2);
        r2 = r7.f6857g;
        r2 = java.lang.Long.valueOf(r2);
        r1 = r1.setPlayerId(r2);
        r1 = r1.setAutomaticLogin(r6);
        r0 = r0.emailLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x0086:
        r0 = com.smule.android.facebook.MagicFacebook.m7682a();
        r2 = r7.f6859i;
        r0 = r0.m7689a(r2, r6);
        if (r0 == 0) goto L_0x017d;
    L_0x0092:
        r1 = r0.a();
        if (r1 == 0) goto L_0x00f7;
    L_0x0098:
        r1 = r7.f6853b;
        r2 = new com.smule.android.network.api.UserAPI$FacebookLoginRequest;
        r2.<init>();
        r3 = r0.b;
        r2 = r2.setAfbId(r3);
        r3 = r0.a;
        r3 = r3.getToken();
        r2 = r2.setAccessToken(r3);
        r3 = r0.h;
        r2 = r2.setFirstName(r3);
        r3 = r0.i;
        r2 = r2.setLastName(r3);
        r3 = r0.d;
        r2 = r2.setGender(r3);
        r3 = r0.e;
        r2 = r2.setBirthday(r3);
        r3 = r0.j;
        r2 = r2.setMinAge(r3);
        r3 = r0.k;
        r2 = r2.setMaxAge(r3);
        r3 = r7.f6860j;
        r2 = r2.setRequestedPassword(r3);
        r4 = r7.f6857g;
        r3 = java.lang.Long.valueOf(r4);
        r2 = r2.setPlayerId(r3);
        r2 = r2.setAutomaticLogin(r6);
        r0 = r0.f;
        r0 = r2.setTfb(r0);
        r0 = r1.facebookLogin(r0);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x00f7:
        r1 = r0.l;
        r1 = r1.getCategory();
        r2 = com.facebook.FacebookRequestError.Category.LOGIN_RECOVERABLE;
        if (r1 != r2) goto L_0x0175;
    L_0x0101:
        r1 = com.smule.android.facebook.MagicFacebook.m7682a();
        r1 = r1.m7697b();
        if (r1 == 0) goto L_0x0175;
    L_0x010b:
        r0 = f6839e;
        r1 = "fb:falling back to last known values";
        com.smule.android.logging.Log.m7770b(r0, r1);
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$FacebookLoginRequest;
        r1.<init>();
        r2 = com.smule.android.facebook.MagicFacebook.m7682a();
        r2 = r2.m7697b();
        r2 = r2.getUserId();
        r1 = r1.setAfbId(r2);
        r2 = com.smule.android.facebook.MagicFacebook.m7682a();
        r2 = r2.m7697b();
        r2 = r2.getToken();
        r1 = r1.setAccessToken(r2);
        r2 = r7.f6864n;
        r1 = r1.setFirstName(r2);
        r2 = r7.f6865o;
        r1 = r1.setLastName(r2);
        r2 = r7.f6866p;
        r1 = r1.setGender(r2);
        r2 = r7.f6867q;
        r1 = r1.setBirthday(r2);
        r2 = r7.f6860j;
        r1 = r1.setRequestedPassword(r2);
        r2 = r7.f6857g;
        r2 = java.lang.Long.valueOf(r2);
        r1 = r1.setPlayerId(r2);
        r1 = r1.setAutomaticLogin(r6);
        r2 = r7.f6874x;
        r1 = r1.setTfb(r2);
        r0 = r0.facebookLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x0175:
        r0 = r0.l;
        r0 = com.smule.android.facebook.MagicFacebook.m7683a(r0);
        goto L_0x0058;
    L_0x017d:
        r0 = f6839e;
        r2 = "fb:unable to get user info";
        com.smule.android.logging.Log.m7776e(r0, r2);
        r0 = r1;
        goto L_0x0058;
    L_0x0187:
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$GoogleLoginRequest;
        r1.<init>();
        r2 = r7.f6875y;
        r1 = r1.setToken(r2);
        r2 = r7.f6860j;
        r1 = r1.setRequestedPassword(r2);
        r2 = r7.f6857g;
        r2 = java.lang.Long.valueOf(r2);
        r1 = r1.setPlayerId(r2);
        r1 = r1.setAutomaticLogin(r6);
        r2 = com.smule.android.network.core.MagicNetwork.m7804d();
        r2 = r2.getAdvertisingId(r6);
        r1 = r1.setAdvId(r2);
        r0 = r0.googleSignInLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x01be:
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$LoginTokenRequest;
        r1.<init>();
        r2 = r7.f6848I;
        r1 = r1.setRefreshToken(r2);
        r2 = new com.smule.android.network.api.UserAPI$LoginRequestCommonRequest;
        r2.<init>();
        r2 = r2.setAutomaticLogin(r6);
        r4 = r7.f6857g;
        r3 = java.lang.Long.valueOf(r4);
        r2 = r2.setPlayerId(r3);
        r1 = r1.setCommonRequest(r2);
        r0 = r0.refreshTokenLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x01ec:
        r0 = com.facebook.accountkit.AccountKit.getCurrentAccessToken();
        if (r0 != 0) goto L_0x01f5;
    L_0x01f2:
        r0 = r1;
        goto L_0x0058;
    L_0x01f5:
        r1 = r7.f6853b;
        r2 = new com.smule.android.network.api.UserAPI$LoginPhoneRequest;
        r2.<init>();
        r3 = com.facebook.accountkit.AccountKit.getApplicationId();
        r2 = r2.setFbAppId(r3);
        r0 = r0.getToken();
        r0 = r2.setAccessToken(r0);
        r2 = new com.smule.android.network.api.UserAPI$LoginRequestCommonRequest;
        r2.<init>();
        r2 = r2.setAutomaticLogin(r6);
        r4 = r7.f6857g;
        r3 = java.lang.Long.valueOf(r4);
        r2 = r2.setPlayerId(r3);
        r0 = r0.setCommonRequest(r2);
        r0 = r1.phoneLogin(r0);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x022d:
        r0 = com.smule.android.network.core.MagicNetwork.m7804d();
        r0 = r0.allowGooglePlus();
        if (r0 == 0) goto L_0x002f;
    L_0x0237:
        r0 = r7.f6875y;
        if (r0 == 0) goto L_0x028e;
    L_0x023b:
        r0 = r7.f6863m;
        if (r0 == 0) goto L_0x028e;
    L_0x023f:
        r0 = r7.f6853b;
        r1 = new com.smule.android.network.api.UserAPI$GooglePlusLoginRequest;
        r1.<init>();
        r2 = r7.f6863m;
        r1 = r1.setId(r2);
        r2 = r7.f6875y;
        r1 = r1.setAccessToken(r2);
        r2 = r7.f6859i;
        r1 = r1.setEmail(r2);
        r2 = r7.f6866p;
        r1 = r1.setGender(r2);
        r2 = r7.f6867q;
        r1 = r1.setBirthday(r2);
        r2 = r7.f6864n;
        r1 = r1.setFirstName(r2);
        r2 = r7.f6865o;
        r1 = r1.setLastName(r2);
        r2 = r7.f6860j;
        r1 = r1.setRequestedPassword(r2);
        r2 = r7.f6857g;
        r2 = java.lang.Long.valueOf(r2);
        r1 = r1.setPlayerId(r2);
        r1 = r1.setAutomaticLogin(r6);
        r0 = r0.googlePlusLogin(r1);
        r0 = com.smule.android.network.core.NetworkUtils.a(r0);
        goto L_0x0058;
    L_0x028e:
        r0 = r1;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.android.network.managers.UserManager.U():com.smule.android.network.core.NetworkResponse");
    }

    public void m8146J() {
        Log.m7770b(f6839e, "fastReLogin");
        this.f6870t = false;
        NotificationCenter.a().a("USER_RE_LOGGED_IN_EVENT", Boolean.valueOf(true));
    }

    public NetworkResponse m8147K() {
        Log.m7770b(f6839e, "reLogin");
        m8107S();
        NetworkResponse networkResponse = null;
        try {
            networkResponse = m8109U();
        } catch (Throwable e) {
            Log.m7775d(f6839e, "doReLogin failed with an exception. Assuming a re-login failure.", e);
        }
        LoginResponse loginResponse = new LoginResponse(networkResponse);
        if (networkResponse != null && networkResponse.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).g(this.f6864n).h(this.f6865o).i(this.f6866p).j(this.f6867q).a(loginResponse.j).a(loginResponse.l).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r).p(loginResponse.d));
            if (loginResponse.u.booleanValue()) {
                NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
            }
            NotificationCenter.a().b("USER_RE_LOGGED_IN_EVENT", new Object[0]);
            this.f6855d.postDelayed(new UpdateExternalTokens(this, this.f6868r), TimeUnit.SECONDS.toMillis(1));
        } else if (networkResponse != null && networkResponse.f6756b == 76 && this.f6848I != null) {
            Log.m7767a(f6839e, "Refresh token was invalidated; flushing and logging in via primary method");
            m8196c();
            return m8147K();
        } else if (networkResponse == null || (networkResponse.f6755a == Status.a && networkResponse.f6756b != 2000)) {
            Log.m7770b(f6839e, "user logged out");
            m8117b(networkResponse);
            NotificationCenter.a().a("AUTO_LOGIN_FAILED", networkResponse);
        } else if (networkResponse.f6756b == 2000) {
            this.f6870t = true;
        } else {
            Log.m7770b(f6839e, "ignoring doReLogin response");
        }
        if (networkResponse == null || !networkResponse.m7850c()) {
            NotificationCenter.a().a("AUTO_LOGIN_FAILED_NEW", networkResponse);
        }
        m8193b(networkResponse, this.f6868r);
        return networkResponse;
    }

    public LoginResponse m8148L() {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.deviceLogin(new DeviceLoginRequest().setAutomaticLogin(false)));
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).a(LoginType.d).a(loginResponse.j).a(loginResponse.l).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r));
            m8132n("USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.d);
        return loginResponse;
    }

    public Future<?> m8173a(LoginResponseCallback loginResponseCallback) {
        return MagicNetwork.m7790a(new 6(this, loginResponseCallback));
    }

    public LoginResponse m8186b(String str, String str2) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.emailLogin(new EmailLoginRequest().setEmail(str).setPassword(str2).setPlayerId(Long.valueOf(this.f6857g)).setAutomaticLogin(false)));
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            Log.m7772c(f6839e, a.f6764j);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).d(str2).a(LoginType.b).a(loginResponse.j).a(loginResponse.l).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r));
            m8132n("USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.b);
        return loginResponse;
    }

    public LoginResponse m8163a(FacebookUserInfo facebookUserInfo, String str, boolean z) {
        return m8163a(facebookUserInfo, str, z);
    }

    public LoginResponse m8162a(FacebookUserInfo facebookUserInfo, String str, String str2, boolean z) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.facebookLogin(new FacebookLoginRequest().setAfbId(facebookUserInfo.b).setAccessToken(facebookUserInfo.a.getToken()).setEmail(facebookUserInfo.c, str).setFirstName(facebookUserInfo.h).setLastName(facebookUserInfo.i).setGender(facebookUserInfo.d).setBirthday(facebookUserInfo.e).setMinAge(facebookUserInfo.j).setMaxAge(facebookUserInfo.k).setRequestedPassword(this.f6860j).setPlayerId(Long.valueOf(this.f6857g)).setAutomaticLogin(z).setTfb(facebookUserInfo.f)));
        Log.m7772c(f6839e, "loginWithFacebook response : " + a.f6764j);
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).d(str2).e(facebookUserInfo.b).i(facebookUserInfo.d).j(facebookUserInfo.e).a(LoginType.c).a(loginResponse.j).a(loginResponse.l).a(loginResponse.m).m(facebookUserInfo.f).n(loginResponse.o).a(loginResponse.r));
            m8132n(loginResponse.u.booleanValue() ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.c);
        return loginResponse;
    }

    public NetworkResponse m8156a(FacebookUserInfo facebookUserInfo) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.facebookConnect(new FacebookConnectRequest().setAfbId(facebookUserInfo.b).setAccessToken(facebookUserInfo.a.getToken()).setFbEmail(facebookUserInfo.c).setGender(facebookUserInfo.d).setBirthday(facebookUserInfo.e).setMinAge(facebookUserInfo.j).setMaxAge(facebookUserInfo.k).setTfb(facebookUserInfo.f)));
        Log.m7772c(f6839e, "connectWithFacebook response : " + a.f6764j);
        return a;
    }

    @Deprecated
    public NetworkResponse m8149M() {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.facebookDisconnect(new SnpRequest()));
        Log.m7772c(f6839e, "connectWithFacebook response : " + a.f6764j);
        return a;
    }

    public LoginResponse m8187b(boolean z) {
        m8108T();
        com.facebook.accountkit.AccessToken currentAccessToken = AccountKit.getCurrentAccessToken();
        if (currentAccessToken == null) {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
            return null;
        }
        NetworkResponse a = NetworkUtils.a(this.f6853b.phoneLogin(new LoginPhoneRequest().setFbAppId(AccountKit.getApplicationId()).setAccessToken(currentAccessToken.getToken()).setCommonRequest(new LoginRequestCommonRequest().setAutomaticLogin(z).setPlayerId(Long.valueOf(this.f6857g)))));
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).o(loginResponse.b).b(loginResponse.c).p(loginResponse.d).c(loginResponse.i).b(loginResponse.e).a(LoginType.g).a(loginResponse.j).a(loginResponse.l).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r));
            m8132n(loginResponse.u.booleanValue() ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.g);
        return loginResponse;
    }

    public LoginResponse m8165a(String str, String str2, boolean z) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.googleSignInLogin(new GoogleLoginRequest().setToken(str).setRequestedPassword(str2).setPlayerId(Long.valueOf(this.f6857g)).setAdvId(MagicNetwork.m7804d().getAdvertisingId(true)).setAutomaticLogin(z)));
        Log.m7772c(f6839e, "loginWithGoogle response : " + a.f6764j);
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).d(str2).a(LoginType.f).a(loginResponse.j).a(loginResponse.l).k(str).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r));
            m8132n(loginResponse.u.booleanValue() ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.f);
        return loginResponse;
    }

    @Deprecated
    public LoginResponse m8164a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.googlePlusLogin(new GooglePlusLoginRequest().setId(str).setAccessToken(str2).setEmail(str3).setGender(str4).setBirthday(str5).setFirstName(str6).setLastName(str7).setRequestedPassword(str8).setPlayerId(Long.valueOf(this.f6857g)).setAutomaticLogin(z)));
        Log.m7772c(f6839e, "loginWithGooglePlus response : " + a.f6764j);
        LoginResponse loginResponse = new LoginResponse(a);
        if (a.m7850c()) {
            this.f6870t = false;
            EventLogger2.f6702b = loginResponse.k;
            this.f6841B = loginResponse.n;
            this.f6844E = loginResponse.q;
            m8183a(loginResponse.t);
            m8115a(new UserManagerBuilder().a(loginResponse.h).b(loginResponse.g).a(loginResponse.f).c(loginResponse.i).b(loginResponse.e).d(str8).f(str).g(str6).h(str7).i(str4).j(str5).a(LoginType.e).a(loginResponse.j).a(loginResponse.l).k(str2).a(loginResponse.m).n(loginResponse.o).a(loginResponse.r));
            m8132n(loginResponse.u.booleanValue() ? "USER_EXISTENCE_TYPE_NEW" : "USER_EXISTENCE_TYPE_EXISTING");
        } else {
            NotificationCenter.a().b("USER_LOGIN_FAILED", new Object[0]);
        }
        m8182a(a, LoginType.e);
        return loginResponse;
    }

    @Deprecated
    public NetworkResponse m8158a(String str, String str2, String str3, String str4, String str5) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.googlePlusConnect(new GooglePlusConnectRequest().setId(str).setAccessToken(str2).setEmail(str3).setGender(str4).setBirthday(str5)));
        Log.m7772c(f6839e, "connectWithGooglePlus response : " + a.f6764j);
        return a;
    }

    @Deprecated
    public NetworkResponse m8150N() {
        m8108T();
        this.f6863m = null;
        this.f6875y = null;
        this.f6854c.getSharedPreferences("user", 0).edit().remove("googlePlusId").remove("GPLUS_ACCESS_TOKEN").apply();
        NetworkResponse a = NetworkUtils.a(this.f6853b.googlePlusDisconnect(new SnpRequest()));
        Log.m7772c(f6839e, "disconnectWithGooglePlus response : " + a.f6764j);
        return a;
    }

    private void m8115a(UserManagerBuilder userManagerBuilder) {
        m8118b(userManagerBuilder);
        m8228w();
    }

    private synchronized void m8118b(UserManagerBuilder userManagerBuilder) {
        Editor edit = this.f6854c.getSharedPreferences("user", 0).edit();
        if (!TextUtils.isEmpty(userManagerBuilder.d)) {
            edit.putString("email", userManagerBuilder.d);
            this.f6859i = userManagerBuilder.d;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.e)) {
            edit.putString("password", userManagerBuilder.e);
            this.f6860j = userManagerBuilder.e;
        }
        if (userManagerBuilder.a != 0) {
            edit.putLong("account", userManagerBuilder.a);
            this.f6856f = userManagerBuilder.a;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.f)) {
            edit.putString("picUrl", userManagerBuilder.f);
            this.f6861k = userManagerBuilder.f;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.c)) {
            edit.putString("handle", userManagerBuilder.c);
            this.f6858h = userManagerBuilder.c;
        }
        if (userManagerBuilder.b != 0) {
            edit.putLong("player", userManagerBuilder.b);
            this.f6857g = userManagerBuilder.b;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.g)) {
            edit.putString("facebookId", userManagerBuilder.g);
            this.f6862l = userManagerBuilder.g;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.h)) {
            edit.putString("googlePlusId", userManagerBuilder.h);
            this.f6863m = userManagerBuilder.h;
        }
        if (userManagerBuilder.i != null) {
            edit.putString("firstName", userManagerBuilder.i);
            this.f6864n = userManagerBuilder.i;
        }
        if (userManagerBuilder.j != null) {
            edit.putString("lastName", userManagerBuilder.j);
            this.f6865o = userManagerBuilder.j;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.k)) {
            edit.putString("gender", userManagerBuilder.k);
            this.f6866p = userManagerBuilder.k;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.l)) {
            edit.putString("birthday", userManagerBuilder.l);
            this.f6867q = userManagerBuilder.l;
        }
        if (userManagerBuilder.m != null) {
            edit.putInt("login_type", userManagerBuilder.m.ordinal());
            this.f6868r = userManagerBuilder.m;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.u)) {
            edit.putString("profileBlurb", userManagerBuilder.u);
            this.f6876z = userManagerBuilder.u;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.p)) {
            edit.putString("FB_TOKEN_FOR_BUSINESS", userManagerBuilder.p);
            this.f6874x = userManagerBuilder.p;
        }
        edit.putInt("LOGIN_COUNT", userManagerBuilder.o);
        this.f6873w = userManagerBuilder.o;
        if (userManagerBuilder.v != null) {
            edit.putInt("newsletterOptIn", userManagerBuilder.v.a());
            this.f6840A = userManagerBuilder.v;
        }
        if (userManagerBuilder.w != null) {
            edit.putString("jid", userManagerBuilder.w);
            this.f6842C = userManagerBuilder.w;
        }
        if (userManagerBuilder.x != null) {
            this.f6843D = userManagerBuilder.x;
            edit.putString("XMPP_HOSTS_KEY", JsonUtils.a(userManagerBuilder.x));
        }
        if (!(userManagerBuilder.n == null || userManagerBuilder.n.longValue() == 0)) {
            edit.putLong("INSTALL_DATE", userManagerBuilder.n.longValue());
            this.f6872v = userManagerBuilder.n;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.q)) {
            edit.putString("GPLUS_ACCESS_TOKEN", userManagerBuilder.q);
            this.f6875y = userManagerBuilder.q;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.r)) {
            edit.putString("SESSION_TOKEN", userManagerBuilder.r);
            this.f6846G = userManagerBuilder.r;
        }
        if (userManagerBuilder.s > 0) {
            edit.putInt("SESSION_TOKEN_TTL", userManagerBuilder.s);
            this.f6847H = userManagerBuilder.s;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.t)) {
            edit.putString("REFRESH_TOKEN", userManagerBuilder.t);
            this.f6848I = userManagerBuilder.t;
        }
        if (!TextUtils.isEmpty(userManagerBuilder.q)) {
            edit.putString("GPLUS_ACCESS_TOKEN", userManagerBuilder.q);
            this.f6875y = userManagerBuilder.q;
        }
        edit.apply();
    }

    private Pair<String, String> m8110a(LoginType loginType) {
        Object obj;
        Object obj2;
        switch (30.a[loginType.ordinal()]) {
            case 1:
                obj = "sign_in";
                obj2 = "snp_error";
                break;
            case 2:
                obj = "facebook";
                obj2 = "fb_error";
                break;
            case 3:
                obj = "goog";
                obj2 = "goog_error";
                break;
            case 5:
                obj = "acctkit";
                obj2 = "snp_error";
                break;
            case 6:
                obj = "gplus";
                obj2 = "gplus_error";
                break;
            case 7:
                obj = "guest_login";
                obj2 = "snp_error";
                break;
            case 8:
                obj = "sign_up";
                obj2 = "snp_error";
                break;
            default:
                obj = "device_login";
                obj2 = "snp_error";
                break;
        }
        return new Pair(obj, obj2);
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public void m8182a(@Nullable NetworkResponse networkResponse, LoginType loginType) {
        Pair a = m8110a(loginType);
        String str = (String) a.first;
        String str2 = (String) a.second;
        if (networkResponse == null) {
            Analytics.b(str, "client_error", "invalid id or token", null);
        } else if (networkResponse.f6755a != Status.a) {
            Analytics.b(str, "snp_error", NetworkResponse.m7842b(networkResponse.f6755a), Integer.toString(networkResponse.f6756b));
        } else {
            if (networkResponse.f6756b != 0) {
                if (str2.equals("snp_error")) {
                    Analytics.b(str, str2, NetworkResponse.m7842b(networkResponse.f6755a), Integer.toString(networkResponse.f6756b));
                } else {
                    Analytics.b(str, str2, networkResponse.f6759e, Integer.toString(networkResponse.f6756b));
                }
            }
            m8120c(networkResponse);
        }
    }

    @SuppressLint({"ValueOfNotAllowedForNumberSubClasses"})
    public void m8193b(@Nullable NetworkResponse networkResponse, LoginType loginType) {
        Pair a = m8110a(loginType);
        String str = (String) a.first;
        String str2 = (String) a.second;
        if (networkResponse == null) {
            Analytics.c(str, "client_error", "invalid id or token", null);
        } else if (networkResponse.f6755a != Status.a) {
            Analytics.c(str, "snp_error", NetworkResponse.m7842b(networkResponse.f6755a), Integer.toString(networkResponse.f6756b));
        } else {
            if (networkResponse.f6756b != 0) {
                if (str2.equals("snp_error")) {
                    Analytics.c(str, str2, NetworkResponse.m7842b(networkResponse.f6755a), Integer.toString(networkResponse.f6756b));
                } else {
                    Analytics.c(str, str2, networkResponse.f6759e, Integer.toString(networkResponse.f6756b));
                }
            }
            m8120c(networkResponse);
        }
    }

    private void m8120c(@Nullable NetworkResponse networkResponse) {
        if (networkResponse != null && networkResponse.f6755a == Status.a) {
            if (networkResponse.f6756b == 0) {
                long j = networkResponse.f6763i;
                if (j > 0) {
                    EventLogger2.m7727a().m7753a(j * 1000);
                }
            } else if (networkResponse.f6756b != 72) {
                MagicNetwork.m7795a(networkResponse);
            }
        }
    }

    private void m8132n(String str) {
        Log.m7770b(f6839e, "postLoggedInEvent:" + str);
        this.f6845F = str;
        NotificationCenter.a().a("USER_LOGGED_IN_EVENT", str);
    }

    public NetworkResponse m8155a(Bitmap bitmap) {
        NetworkResponse a = NetworkUtils.a(this.f6853b.uploadPicture(Part.createFormData("picture", "profile.jpg", BitmapRequestBodyConverter.convertTo(bitmap))));
        if (a != null && a.m7850c()) {
            m8181a(a);
        }
        return a;
    }

    public void m8181a(NetworkResponse networkResponse) {
        JsonNode jsonNode = networkResponse.f6766l;
        if (jsonNode.has("picUrl")) {
            m8115a(new UserManagerBuilder().c(jsonNode.get("picUrl").asText()).a(this.f6873w).n(this.f6842C));
        }
    }

    public NetworkResponse m8157a(String str, String str2, String str3, EmailOptIn emailOptIn) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.userUpdate(new UserUpdateRequest().setEmail(str2).setHandle(str).setPassword(str3).setNewsletter(emailOptIn)));
        Log.m7772c(f6839e, "userUpdate response : " + a.f6764j);
        if (a.m7850c()) {
            m8115a(new UserManagerBuilder().a(str).b(str2).a(this.f6856f).c(this.f6861k).b(this.f6857g).d(str3).i(this.f6866p).j(this.f6867q).a(this.f6873w).a(emailOptIn).n(this.f6842C));
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
        return a;
    }

    public NetworkResponse m8195c(String str, String str2) {
        m8108T();
        NetworkResponse a = NetworkUtils.a(this.f6853b.userPersonalUpdate(new UserPersonalUpdateRequest().setFirstName(str).setLastName(str2)));
        Log.m7772c(f6839e, "userUpdate response : " + a.f6764j);
        if (a.m7850c()) {
            m8115a(new UserManagerBuilder().a(this.f6858h).b(this.f6859i).a(this.f6856f).c(this.f6861k).b(this.f6857g).d(this.f6860j).i(this.f6866p).j(this.f6867q).a(this.f6873w).a(this.f6840A).g(str).h(str2).n(this.f6842C));
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
        return a;
    }

    public void m8199d(String str, String str2) {
        int i = (TextUtils.equals(str, this.f6864n) && TextUtils.equals(str2, this.f6865o)) ? 0 : 1;
        if (i != 0) {
            m8115a(new UserManagerBuilder().a(this.f6858h).b(this.f6859i).a(this.f6856f).c(this.f6861k).b(this.f6857g).d(this.f6860j).i(this.f6866p).j(this.f6867q).a(this.f6873w).a(this.f6840A).g(str).h(str2).n(this.f6842C));
            NotificationCenter.a().b("PROFILE_UPDATED_EVENT", new Object[0]);
        }
    }

    public NetworkResponse m8194c(String str) {
        return NetworkUtils.a(this.f6853b.passwordReset(new PasswordResetRequest().setEmail(str)));
    }

    public Future<?> m8175a(String str, NetworkResponseCallback networkResponseCallback) {
        return MagicNetwork.m7790a(new 17(this, networkResponseCallback, str));
    }

    public NetworkResponse m8154a(long j) {
        return NetworkUtils.a(this.f6853b.userBlurb(new UserBlurbRequest().setAccountId(Long.valueOf(j))));
    }

    public Future<?> m8169a(long j, GetUserBlurbResponseCallback getUserBlurbResponseCallback) {
        return MagicNetwork.m7790a(new 18(this, getUserBlurbResponseCallback, j));
    }

    public NetworkResponse m8198d(String str) {
        return NetworkUtils.a(this.f6853b.updateUserBlurb(new UpdateUserBlurbRequest().setBlurb(str)));
    }

    public Future<?> m8177a(String str, UpdateUserBlurbResponseCallback updateUserBlurbResponseCallback) {
        return MagicNetwork.m7790a(new 19(this, str, updateUserBlurbResponseCallback));
    }

    public AccountIconResponse m8201e(String str) {
        return AccountIconResponse.m8097a(NetworkUtils.a(this.f6853b.lookupUser(new UserLookupRequest().setEmail(str))));
    }

    public Future<?> m8176a(String str, AccountIconResponseCallback accountIconResponseCallback) {
        return MagicNetwork.m7790a(new 20(this, accountIconResponseCallback, str));
    }

    public AccountIconResponse m8204f(String str) {
        return AccountIconResponse.m8097a(NetworkUtils.a(this.f6853b.lookupUser(new UserLookupRequest().setHandle(str))));
    }

    public Future<?> m8190b(String str, AccountIconResponseCallback accountIconResponseCallback) {
        return MagicNetwork.m7790a(new 21(this, accountIconResponseCallback, str));
    }

    public AccountIconsResponse m8160a(Collection<Long> collection) {
        if (collection.size() > 25) {
            Log.m7776e(f6839e, "lookupAccountsByIds queried with greater than 25 icons");
        }
        return AccountIconsResponse.m8098a(NetworkUtils.a(this.f6853b.lookupAccounts(new AccountsLookupRequest().setAccountIds(collection))));
    }

    public Future<?> m8167a(long j, AccountIconResponseCallback accountIconResponseCallback) {
        if (j == 0) {
            MagicCrittercism.m7780a(new DroidSing10036Exception(this));
        }
        return MagicNetwork.m7790a(new 23(this, j, accountIconResponseCallback));
    }

    public Future<?> m8170a(long j, UserProfileResponseCallback userProfileResponseCallback) {
        if (j == 0) {
            MagicCrittercism.m7780a(new DroidSing10036Exception(this));
        }
        return MagicNetwork.m7790a(new 24(this, j, userProfileResponseCallback));
    }

    public Future<?> m8172a(BlockedUsersResponseCallback blockedUsersResponseCallback) {
        return MagicNetwork.m7790a(new 25(this, blockedUsersResponseCallback));
    }

    public NetworkResponse m8159a(List<Long> list, List<Long> list2) {
        return NetworkUtils.a(this.f6853b.blockUnblockUsers(new BlockUnblockRequest().setAdd(list).setRemove(list2)));
    }

    public Future<?> m8168a(long j, BlockUsersResponseCallback blockUsersResponseCallback) {
        return m8179a(new ArrayList(Collections.singletonList(Long.valueOf(j))), blockUsersResponseCallback);
    }

    public Future<?> m8179a(List<Long> list, BlockUsersResponseCallback blockUsersResponseCallback) {
        return MagicNetwork.m7790a(new 26(this, list, blockUsersResponseCallback));
    }

    public Future<?> m8189b(long j, BlockUsersResponseCallback blockUsersResponseCallback) {
        return m8191b(new ArrayList(Collections.singletonList(Long.valueOf(j))), blockUsersResponseCallback);
    }

    public Future<?> m8191b(List<Long> list, BlockUsersResponseCallback blockUsersResponseCallback) {
        return MagicNetwork.m7790a(new 27(this, list, blockUsersResponseCallback));
    }

    public Future<?> m8178a(List<String> list, AccountPreferencesResponseCallback accountPreferencesResponseCallback) {
        return MagicNetwork.m7790a(new 28(this, list, accountPreferencesResponseCallback));
    }

    public Future<?> m8174a(AccountPreference accountPreference, UpdateAccountPreferencesResponseCallback updateAccountPreferencesResponseCallback) {
        return m8180a(Collections.singletonList(accountPreference), updateAccountPreferencesResponseCallback);
    }

    public Future<?> m8180a(List<AccountPreference> list, UpdateAccountPreferencesResponseCallback updateAccountPreferencesResponseCallback) {
        return MagicNetwork.m7790a(new 29(this, list, updateAccountPreferencesResponseCallback));
    }

    public AccountIcon m8151O() {
        AccountIcon accountIcon = new AccountIcon();
        accountIcon.handle = this.f6858h;
        accountIcon.accountId = this.f6856f;
        accountIcon.picUrl = this.f6861k;
        accountIcon.jid = this.f6842C;
        if (SubscriptionManager.m8066a().m8087b()) {
            accountIcon.appsWithSubscription = new HashSet();
            accountIcon.appsWithSubscription.add(MagicNetwork.m7804d().getAppUID());
        }
        return accountIcon;
    }

    public void m8206g(String str) {
        if (this.f6851M == null) {
            this.f6851M = new HashSet();
        }
        this.f6851M.add(str);
        if (!this.f6851M.isEmpty()) {
            this.f6850L = false;
        }
    }

    public void m8208h(String str) {
        if (this.f6851M != null) {
            this.f6851M.remove(str);
            if (this.f6851M.isEmpty()) {
                this.f6850L = true;
            }
        }
    }

    public void m8197c(boolean z) {
        this.f6850L = z;
    }

    public boolean m8210i(String str) {
        return this.f6851M != null && this.f6851M.contains(str);
    }

    public boolean m8152P() {
        return this.f6851M == null || this.f6851M.isEmpty();
    }

    public boolean m8153Q() {
        return this.f6850L;
    }

    public void m8212j(String str) {
        if (this.f6852N == null) {
            this.f6852N = new HashMap();
        }
        this.f6852N.put(str, Boolean.valueOf(true));
    }

    public void m8214k(String str) {
        if (this.f6852N == null) {
            this.f6852N = new HashMap();
        }
        if (this.f6852N != null) {
            this.f6852N.put(str, Boolean.valueOf(false));
        }
    }

    public boolean m8216l(String str) {
        return this.f6852N != null && this.f6852N.containsKey(str);
    }

    public boolean m8218m(String str) {
        return m8216l(str) && ((Boolean) this.f6852N.get(str)).booleanValue();
    }

    public void m8184a(Long l) {
        Editor edit = this.f6854c.getSharedPreferences("user", 0).edit();
        if (!(l == null || l.longValue() == 0)) {
            edit.putLong("INSTALL_DATE", l.longValue());
            this.f6872v = l;
        }
        edit.apply();
    }
}
