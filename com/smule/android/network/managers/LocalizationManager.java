/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.databind.JsonNode
 *  retrofit2.Call
 */
package com.smule.android.network.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.smule.android.annotations.NetworkThread;
import com.smule.android.logging.Log;
import com.smule.android.network.api.LocalizationAPI;
import com.smule.android.network.api.ResourceDownloader;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.core.SnpRequest;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.l10n.LocalizationProvider;
import com.smule.android.network.managers.l10n.SNPSettingsLocalizationProvider;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.SimpleBarrier;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import retrofit2.Call;

public class LocalizationManager {
    private static final String a = LocalizationManager.class.getName();
    private static final long b = TimeUnit.HOURS.toMillis(1);
    private static LocalizationManager c = null;
    private Context d;
    private com.smule.android.network.api.LocalizationAPI e;
    private Map<String, LocalizationProvider> f;
    private final LinkedList<Runnable> g = new LinkedList();
    private AtomicBoolean h = new AtomicBoolean(false);
    private long i = 0;
    private long j = 0;

    private LocalizationManager(Context context) {
        this.d = context;
        this.e = MagicNetwork.a().a(com.smule.android.network.api.LocalizationAPI.class);
        this.f = new HashMap<String, LocalizationProvider>();
    }

    static /* synthetic */ long a(LocalizationManager localizationManager, long l) {
        localizationManager.i = l;
        return l;
    }

    public static LocalizationManager a() {
        return c;
    }

    static /* synthetic */ AtomicBoolean a(LocalizationManager localizationManager) {
        return localizationManager.h;
    }

    public static void a(Context context) {
        c = new LocalizationManager(context);
    }

    private static boolean a(long l) {
        long l2 = UserManager.a().f();
        if (l == 0 && l2 == 0 || l != 0 && l == l2) {
            return true;
        }
        return false;
    }

    static /* synthetic */ long b(LocalizationManager localizationManager, long l) {
        localizationManager.j = l;
        return l;
    }

    static /* synthetic */ void b(LocalizationManager localizationManager) {
        localizationManager.d();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private String c(String charSequence) {
        int n;
        Object object = this.d.getResources().getConfiguration().locale;
        String string2 = object.toString();
        if ((object = object.getLanguage()) == null) return null;
        if (object.isEmpty()) return null;
        if (string2 == null) return null;
        if (string2.isEmpty()) return null;
        object = object.toLowerCase();
        string2 = string2.toLowerCase();
        charSequence = charSequence.replace('-', '_');
        string2 = charSequence + "_" + string2;
        int n2 = n = this.d.getResources().getIdentifier(string2, "raw", this.d.getPackageName());
        if (n == 0) {
            object = charSequence + "_" + (String)object;
            n2 = this.d.getResources().getIdentifier((String)object, "raw", this.d.getPackageName());
        }
        n = n2;
        if (n2 == 0) {
            charSequence = charSequence + "_en";
            n = this.d.getResources().getIdentifier(charSequence, "raw", this.d.getPackageName());
        }
        if (n == 0) return null;
        object = this.d.getResources().openRawResource(n);
        if (object == null) {
            return null;
        }
        try {
            charSequence = new StringBuilder(2048);
            object = new BufferedReader(new InputStreamReader((InputStream)object));
            while ((string2 = object.readLine()) != null) {
                charSequence.append(string2);
            }
            return charSequence.toString();
        }
        catch (IOException iOException) {
            Log.d(a, "Unable to load default settings", iOException);
            return null;
        }
    }

    private boolean c() {
        if (System.currentTimeMillis() < this.i + b && LocalizationManager.a(this.j)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void d() {
        LinkedList<Runnable> linkedList = this.g;
        synchronized (linkedList) {
            while (this.g.size() > 0) {
                this.g.remove(0).run();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void d(String object) {
        Object object2 = this.d.getSharedPreferences("localized_" + (String)object, 0);
        try {
            String string2 = object2.getString("config", "{}");
            object2 = string2;
            if (string2.equals("{}")) {
                string2 = this.c((String)object);
                object2 = string2;
                if (string2 == null) {
                    object2 = "{}";
                }
            }
            object2 = object2.replace("\ufeff", "");
            Log.b(a, "Loading localized " + (String)object);
            object2 = new Object(JsonUtils.a().readTree((String)object2)){
                private Map<String, Map<String, Map<String, String>>> a = new HashMap<String, Map<String, Map<String, String>>>();
                {
                    this();
                    object = object.fields();
                    while (object.hasNext()) {
                        Map.Entry entry = (Map.Entry)object.next();
                        String string2 = (String)entry.getKey();
                        this.a(string2.substring(0, string2.indexOf(46)), string2.substring(string2.indexOf(46) + 1, string2.lastIndexOf(46)), string2.substring(string2.lastIndexOf(46) + 1), ((JsonNode)entry.getValue()).textValue());
                    }
                }

                public Map<String, Map<String, String>> a(String string2) {
                    return this.a.get(string2);
                }

                public Map<String, String> a(String object, String string2) {
                    if ((object = this.a.get(object)) != null) {
                        return (Map)object.get(string2);
                    }
                    return null;
                }

                void a(String object, String string2, String string3, String string4) {
                    if (!this.a.containsKey(object)) {
                        this.a.put((String)object, new HashMap());
                    }
                    if (!(object = this.a.get(object)).containsKey(string2)) {
                        object.put(string2, new HashMap());
                    }
                    ((Map)object.get(string2)).put(string3, string4);
                }

                public boolean a() {
                    if (this.a == null || this.a.isEmpty()) {
                        return true;
                    }
                    return false;
                }

                public String b() {
                    Object object = new HashMap();
                    for (Map.Entry<String, Map<String, Map<String, String>>> entry : this.a.entrySet()) {
                        for (Map.Entry<String, Map<String, String>> entry2 : entry.getValue().entrySet()) {
                            for (Map.Entry<String, String> entry3 : entry2.getValue().entrySet()) {
                                object.put(entry.getKey() + "." + entry2.getKey() + "." + entry3.getKey(), entry3.getValue());
                            }
                        }
                    }
                    try {
                        object = JsonUtils.a().writeValueAsString(object);
                        return object;
                    }
                    catch (JsonProcessingException jsonProcessingException) {
                        Log.d(a, "Failed to serialize config", (Throwable)jsonProcessingException);
                        return null;
                    }
                }
            };
        }
        catch (Exception exception) {
            Log.d(a, "Failed to load localized songbook", exception);
            object2 = new ;
        }
        if ((object = this.f.get(object)) != null) {
            object.a(object2);
        }
    }

    public GetLocalizationPackageResponse a(String string2) {
        return GetLocalizationPackageResponse.a(NetworkUtils.a(this.e.getPackage(new SnpRequest(){
            public String locPackageId;

            public LocalizationAPI setLocPackageId(String string2) {
                this.locPackageId = string2;
                return this;
            }
        }.setLocPackageId(string2))));
    }

    public <T> T a(String string2, T t) {
        LocalizationProvider localizationProvider = this.f.get(string2);
        string2 = t;
        if (localizationProvider != null) {
            string2 = localizationProvider.a(t);
        }
        return (T)string2;
    }

    public String a(String string2, String string3) {
        LocalizationProvider localizationProvider = this.f.get(SNPSettingsLocalizationProvider.a);
        if (localizationProvider != null && localizationProvider instanceof SNPSettingsLocalizationProvider) {
            return ((SNPSettingsLocalizationProvider)localizationProvider).a(string2, string3, null);
        }
        return null;
    }

    public String a(String string2, String string3, String string4) {
        LocalizationProvider localizationProvider = this.f.get(SNPSettingsLocalizationProvider.a);
        if (localizationProvider != null && localizationProvider instanceof SNPSettingsLocalizationProvider) {
            return ((SNPSettingsLocalizationProvider)localizationProvider).a(string2, string3, string4);
        }
        return null;
    }

    public Future<?> a(String string2,  getLocalizationPackageListener) {
        return MagicNetwork.a(new Runnable(this, getLocalizationPackageListener, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ LocalizationManager c;
            {
                this.c = localizationManager;
                this.a = getLocalizationPackageListener;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.a(this.b));
            }
        });
    }

    public Future<?> a(String string2,  setPreferredLanguageListener) {
        return MagicNetwork.a(new Runnable(this, setPreferredLanguageListener, string2){
            final /* synthetic */  a;
            final /* synthetic */ String b;
            final /* synthetic */ LocalizationManager c;
            {
                this.c = localizationManager;
                this.a = setPreferredLanguageListener;
                this.b = string2;
            }

            public void run() {
                com.smule.android.network.core.CoreUtil.a(this.a, this.c.b(this.b));
            }
        });
    }

    public void a(LocalizationProvider localizationProvider) {
        String string2 = localizationProvider.a();
        localizationProvider.a(this.d);
        this.f.put(string2, localizationProvider);
        this.d(string2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Runnable object) {
        Object object2;
        if (object != null) {
            object2 = this.g;
            synchronized (object2) {
                this.g.addLast((Runnable)object);
            }
        }
        if (this.c()) {
            Log.b(a, "reloadAll: already fetched");
            this.d();
            return;
        } else {
            if (this.h.getAndSet(true)) {
                Log.b(a, "reloadAll: pending");
                return;
            }
            object = new SimpleBarrier(this.f.size(), new Runnable(this){
                final /* synthetic */ LocalizationManager a;
                {
                    this.a = localizationManager;
                }

                public void run() {
                    LocalizationManager.a(this.a, System.currentTimeMillis());
                    LocalizationManager.b(this.a, UserManager.a().f());
                    Log.b(LocalizationManager.b(), "reloadAll: done");
                    LocalizationManager.a(this.a).set(false);
                    LocalizationManager.b(this.a);
                }
            });
            object2 = this.f.keySet().iterator();
            while (object2.hasNext()) {
                this.a((String)object2.next(), object.b());
            }
        }
    }

    public void a(final String string2, final Runnable runnable) {
        Log.b(a, "Reload " + string2);
        final LocalizationProvider localizationProvider = this.f.get(string2);
        if (localizationProvider == null) {
            if (runnable != null) {
                runnable.run();
            }
            return;
        }
        this.a(string2, new (){

            @NetworkThread
            @Override
            public void handleResponse(GetLocalizationPackageResponse object) {
                if ((object = object.getConfig()) != null) {
                    localizationProvider.a(object);
                    object = object.b();
                    LocalizationManager.this.d.getSharedPreferences("localized_" + string2, 0).edit().putString("config", (String)object).apply();
                    Log.b(a, "Saved " + string2);
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public SetPreferredLanguageResponse b(String string2) {
        return SetPreferredLanguageResponse.a(NetworkUtils.a(this.e.setPreferredLanguage(new SnpRequest(){
            public String preferredLang;

            public LocalizationAPI setPreferredLanguage(String string2) {
                this.preferredLang = string2;
                return this;
            }
        }.setPreferredLanguage(string2))));
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class GetLocalizationPackageResponse
    extends ParsedResponse {
        @JsonProperty(value="baseUrl")
        public String mBaseUrl;
        @JsonProperty(value="locPackageId")
        public String mLocPackageId;
        @JsonProperty(value="url")
        public String mUrl;

        static GetLocalizationPackageResponse a(NetworkResponse networkResponse) {
            return GetLocalizationPackageResponse.a(networkResponse, GetLocalizationPackageResponse.class);
        }

        private String a(String string2, String string3) {
            try {
                String string4 = new URI(string2).getPath().replaceAll("/", "_");
                return string4;
            }
            catch (URISyntaxException uRISyntaxException) {
                Log.d(a, "Wrong syntax of locale resource url" + string2, uRISyntaxException);
                return string3;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        public  getConfig() {
            String string2;
            Log.b(a, "LogPackageID=" + this.mLocPackageId + " URL=" + this.mUrl + " BaseURL=" + this.mBaseUrl);
            if (!this.a()) {
                MagicNetwork.a(this.a);
                return null;
            }
            if (this.mLocPackageId == null) return null;
            if (this.mUrl != null) {
                string2 = this.mUrl;
            } else {
                if (this.mBaseUrl == null) return null;
                string2 = this.mBaseUrl;
            }
            if (string2 == null) return null;
            Object object = this.a(string2, this.mLocPackageId);
            Log.b(a, "Saving url " + string2 + " to " + (String)object);
            try {
                object = com.smule.android.network.api.ResourceDownloader.downloadFileFromURL((String)string2, (String)object, (Context)MagicNetwork.d().getApplicationContext()).mFile;
                return new ;
            }
            catch (Exception exception) {
                Log.d(a, "Failed to parse base localization package from " + string2, exception);
                return null;
            }
        }
    }

    @JsonIgnoreProperties(ignoreUnknown=true)
    public static class SetPreferredLanguageResponse
    extends ParsedResponse {
        static SetPreferredLanguageResponse a(NetworkResponse networkResponse) {
            return SetPreferredLanguageResponse.a(networkResponse, SetPreferredLanguageResponse.class);
        }
    }

}

