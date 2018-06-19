package com.smule.android.network.managers.l10n;

import android.content.Context;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.AppSettingsManager$OnSettingsUpdatedListener;
import com.smule.android.network.managers.LocalizationManager$LocalizationConfig;
import com.smule.android.utils.NotificationCenter;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

public class SNPSettingsLocalizationProvider implements LocalizationProvider {
    public static final String f17379a = (MagicNetwork.d().getSettingsAppName() + "-" + "settings");
    private static final String f17380b = SNPSettingsLocalizationProvider.class.getName();
    private LocalizationManager$LocalizationConfig f17381c;

    class C36341 implements AppSettingsManager$OnSettingsUpdatedListener {
        final /* synthetic */ SNPSettingsLocalizationProvider f17378a;

        C36341(SNPSettingsLocalizationProvider sNPSettingsLocalizationProvider) {
            this.f17378a = sNPSettingsLocalizationProvider;
        }

        public void mo6276a() {
            this.f17378a.m18515b();
        }
    }

    public SNPSettingsLocalizationProvider() {
        AppSettingsManager.a().a(new C36341(this));
    }

    public String mo6278a() {
        return f17379a;
    }

    public void mo6279a(Context context) {
    }

    public void mo6280a(LocalizationManager$LocalizationConfig localizationManager$LocalizationConfig) {
        this.f17381c = localizationManager$LocalizationConfig;
        AppSettingsManager.a().g();
        NotificationCenter.m19011a().m19017b("APP_SETTINGS_LOADED_EVENT", new Object[0]);
    }

    private synchronized void m18515b() {
        if (this.f17381c == null) {
            Log.e(f17380b, "localizeSNPSettings: configuration not set");
        } else {
            Log.b(f17380b, "Start localizing " + this.f17381c);
            Map a = this.f17381c.m18247a(f17379a);
            if (a != null) {
                synchronized (AppSettingsManager.a()) {
                    for (String str : a.keySet()) {
                        Map map = (Map) a.get(str);
                        Map a2 = AppSettingsManager.a().a(MagicNetwork.d().getSettingsAppName() + "." + str);
                        if (a2 != null) {
                            for (JsonNode a3 : a2.values()) {
                                m18513a(a3, map);
                            }
                        }
                    }
                }
            }
        }
    }

    private void m18513a(JsonNode jsonNode, Map<String, String> map) {
        if (jsonNode.isTextual()) {
            String textValue = jsonNode.textValue();
            if (map.containsKey(textValue)) {
                String str = (String) map.get(textValue);
                try {
                    if (jsonNode instanceof TextNode) {
                        Field declaredField = TextNode.class.getDeclaredField("_value");
                        declaredField.setAccessible(true);
                        declaredField.set(jsonNode, str);
                    }
                } catch (Throwable e) {
                    Log.d(f17380b, "Failed to replace [" + textValue + "] with [" + str + "]", e);
                }
            }
        } else if (jsonNode.isArray()) {
            r1 = jsonNode.iterator();
            while (r1.hasNext()) {
                m18513a((JsonNode) r1.next(), map);
            }
        } else if (jsonNode.isContainerNode()) {
            r1 = jsonNode.fields();
            while (r1.hasNext()) {
                m18513a((JsonNode) ((Entry) r1.next()).getValue(), map);
            }
        }
    }

    public String m18518a(String str, String str2, @Nullable String str3) {
        Map a = this.f17381c.m18247a(f17379a);
        if (a != null) {
            a = (Map) a.get(str);
            if (a != null) {
                String str4 = (String) a.get(str2 + "-" + str3);
                if (str4 == null) {
                    return (String) a.get(str2);
                }
                return str4;
            }
        }
        return null;
    }

    public <T> T mo6277a(T t) {
        throw new UnsupportedOperationException("SNPSettingsLocalizationProvider doesn't support this operation");
    }
}
