package com.smule.android.network.managers;

import android.content.Context;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.annotations.NetworkThread;
import com.smule.android.logging.Log;
import com.smule.android.network.api.LocalizationAPI;
import com.smule.android.network.api.LocalizationAPI.GetPackageRequest;
import com.smule.android.network.api.ResourceDownloader;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.managers.l10n.LocalizationProvider;
import com.smule.android.network.managers.l10n.SNPSettingsLocalizationProvider;
import com.smule.android.utils.JsonUtils;
import com.smule.android.utils.SimpleBarrier;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class LocalizationManager {
    private static final String f6805a = LocalizationManager.class.getName();
    private static final long f6806b = TimeUnit.HOURS.toMillis(1);
    private static LocalizationManager f6807c = null;
    private Context f6808d;
    private LocalizationAPI f6809e;
    private Map<String, LocalizationProvider> f6810f;
    private final LinkedList<Runnable> f6811g = new LinkedList();
    private AtomicBoolean f6812h = new AtomicBoolean(false);
    private long f6813i = 0;
    private long f6814j = 0;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GetLocalizationPackageResponse extends ParsedResponse {
        @JsonProperty("baseUrl")
        public String mBaseUrl;
        @JsonProperty("locPackageId")
        public String mLocPackageId;
        @JsonProperty("url")
        public String mUrl;

        static GetLocalizationPackageResponse m7942a(NetworkResponse networkResponse) {
            return (GetLocalizationPackageResponse) ParsedResponse.m7676a(networkResponse, GetLocalizationPackageResponse.class);
        }

        public LocalizationConfig getConfig() {
            Log.m7770b(LocalizationManager.f6805a, "LogPackageID=" + this.mLocPackageId + " URL=" + this.mUrl + " BaseURL=" + this.mBaseUrl);
            if (!m7677a()) {
                MagicNetwork.m7795a(this.a);
                return null;
            } else if (this.mLocPackageId == null) {
                return null;
            } else {
                String str;
                if (this.mUrl != null) {
                    str = this.mUrl;
                } else if (this.mBaseUrl != null) {
                    str = this.mBaseUrl;
                } else {
                    str = null;
                }
                if (str == null) {
                    return null;
                }
                String a = m7943a(str, this.mLocPackageId);
                Log.m7770b(LocalizationManager.f6805a, "Saving url " + str + " to " + a);
                try {
                    return new LocalizationConfig(JsonUtils.a().readTree(ResourceDownloader.downloadFileFromURL(str, a, MagicNetwork.m7804d().getApplicationContext()).mFile));
                } catch (Throwable e) {
                    Log.m7775d(LocalizationManager.f6805a, "Failed to parse base localization package from " + str, e);
                    return null;
                }
            }
        }

        private String m7943a(String str, String str2) {
            try {
                str2 = new URI(str).getPath().replaceAll("/", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            } catch (Throwable e) {
                Log.m7775d(LocalizationManager.f6805a, "Wrong syntax of locale resource url" + str, e);
            }
            return str2;
        }
    }

    private LocalizationManager(Context context) {
        this.f6808d = context;
        this.f6809e = (LocalizationAPI) MagicNetwork.m7789a().m7817a(LocalizationAPI.class);
        this.f6810f = new HashMap();
    }

    public static LocalizationManager m7945a() {
        return f6807c;
    }

    public static void m7947a(Context context) {
        f6807c = new LocalizationManager(context);
    }

    public void m7962a(LocalizationProvider localizationProvider) {
        String a = localizationProvider.a();
        localizationProvider.a(this.f6808d);
        this.f6810f.put(a, localizationProvider);
        m7954c(a);
    }

    public <T> T m7958a(String str, T t) {
        LocalizationProvider localizationProvider = (LocalizationProvider) this.f6810f.get(str);
        if (localizationProvider != null) {
            return localizationProvider.a(t);
        }
        return t;
    }

    public String m7959a(String str, String str2) {
        LocalizationProvider localizationProvider = (LocalizationProvider) this.f6810f.get(SNPSettingsLocalizationProvider.a);
        if (localizationProvider == null || !(localizationProvider instanceof SNPSettingsLocalizationProvider)) {
            return null;
        }
        return ((SNPSettingsLocalizationProvider) localizationProvider).a(str, str2, null);
    }

    public String m7960a(String str, String str2, String str3) {
        LocalizationProvider localizationProvider = (LocalizationProvider) this.f6810f.get(SNPSettingsLocalizationProvider.a);
        if (localizationProvider == null || !(localizationProvider instanceof SNPSettingsLocalizationProvider)) {
            return null;
        }
        return ((SNPSettingsLocalizationProvider) localizationProvider).a(str, str2, str3);
    }

    public GetLocalizationPackageResponse m7957a(String str) {
        return GetLocalizationPackageResponse.m7942a(NetworkUtils.a(this.f6809e.getPackage(new GetPackageRequest().setLocPackageId(str))));
    }

    public Future<?> m7961a(String str, GetLocalizationPackageListener getLocalizationPackageListener) {
        return MagicNetwork.m7790a(new 1(this, getLocalizationPackageListener, str));
    }

    private boolean m7955c() {
        return System.currentTimeMillis() < this.f6813i + f6806b && m7948a(this.f6814j);
    }

    private static boolean m7948a(long j) {
        long f = UserManager.m8111a().m8203f();
        return (j == 0 && f == 0) || (j != 0 && j == f);
    }

    private void m7956d() {
        synchronized (this.f6811g) {
            while (this.f6811g.size() > 0) {
                ((Runnable) this.f6811g.remove(0)).run();
            }
        }
    }

    public void m7963a(Runnable runnable) {
        if (runnable != null) {
            synchronized (this.f6811g) {
                this.f6811g.addLast(runnable);
            }
        }
        if (m7955c()) {
            Log.m7770b(f6805a, "reloadAll: already fetched");
            m7956d();
        } else if (this.f6812h.getAndSet(true)) {
            Log.m7770b(f6805a, "reloadAll: pending");
        } else {
            SimpleBarrier simpleBarrier = new SimpleBarrier(this.f6810f.size(), new 2(this));
            for (String a : this.f6810f.keySet()) {
                m7964a(a, simpleBarrier.b());
            }
        }
    }

    public void m7964a(final String str, final Runnable runnable) {
        Log.m7770b(f6805a, "Reload " + str);
        final LocalizationProvider localizationProvider = (LocalizationProvider) this.f6810f.get(str);
        if (localizationProvider != null) {
            m7961a(str, new GetLocalizationPackageListener(this) {
                final /* synthetic */ LocalizationManager f6804d;

                @NetworkThread
                public void handleResponse(GetLocalizationPackageResponse getLocalizationPackageResponse) {
                    LocalizationConfig config = getLocalizationPackageResponse.getConfig();
                    if (config != null) {
                        localizationProvider.a(config);
                        this.f6804d.f6808d.getSharedPreferences("localized_" + str, 0).edit().putString("config", config.b()).apply();
                        Log.m7770b(LocalizationManager.f6805a, "Saved " + str);
                    }
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            });
        } else if (runnable != null) {
            runnable.run();
        }
    }

    private String m7951b(String str) {
        String str2 = null;
        Locale locale = this.f6808d.getResources().getConfiguration().locale;
        String locale2 = locale.toString();
        String language = locale.getLanguage();
        if (!(language == null || language.isEmpty() || locale2 == null || locale2.isEmpty())) {
            String toLowerCase = language.toLowerCase();
            language = locale2.toLowerCase();
            locale2 = str.replace('-', '_');
            int identifier = this.f6808d.getResources().getIdentifier(locale2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + language, "raw", this.f6808d.getPackageName());
            if (identifier == 0) {
                identifier = this.f6808d.getResources().getIdentifier(locale2 + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + toLowerCase, "raw", this.f6808d.getPackageName());
            }
            if (identifier == 0) {
                identifier = this.f6808d.getResources().getIdentifier(locale2 + "_en", "raw", this.f6808d.getPackageName());
            }
            if (identifier != 0) {
                InputStream openRawResource = this.f6808d.getResources().openRawResource(identifier);
                if (openRawResource != null) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder(2048);
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource));
                        while (true) {
                            language = bufferedReader.readLine();
                            if (language == null) {
                                break;
                            }
                            stringBuilder.append(language);
                        }
                        str2 = stringBuilder.toString();
                    } catch (Throwable e) {
                        Log.m7775d(f6805a, "Unable to load default settings", e);
                    }
                }
            }
        }
        return str2;
    }

    private void m7954c(String str) {
        LocalizationConfig localizationConfig;
        try {
            String string = this.f6808d.getSharedPreferences("localized_" + str, 0).getString("config", "{}");
            if (string.equals("{}")) {
                string = m7951b(str);
                if (string == null) {
                    string = "{}";
                }
            }
            string = string.replace("﻿", "");
            Log.m7770b(f6805a, "Loading localized " + str);
            localizationConfig = new LocalizationConfig(JsonUtils.a().readTree(string));
        } catch (Throwable e) {
            Log.m7775d(f6805a, "Failed to load localized songbook", e);
            localizationConfig = new LocalizationConfig(null);
        }
        LocalizationProvider localizationProvider = (LocalizationProvider) this.f6810f.get(str);
        if (localizationProvider != null) {
            localizationProvider.a(localizationConfig);
        }
    }
}
