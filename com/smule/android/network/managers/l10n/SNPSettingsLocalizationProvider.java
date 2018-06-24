/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.Nullable
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.node.TextNode
 */
package com.smule.android.network.managers.l10n;

import android.content.Context;
import android.support.annotation.Nullable;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.AppSettingsModel;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.l10n.LocalizationProvider;
import com.smule.android.utils.NotificationCenter;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SNPSettingsLocalizationProvider
implements LocalizationProvider {
    public static final String a;
    private static final String b;
    private LocalizationManager c;

    static {
        b = SNPSettingsLocalizationProvider.class.getName();
        a = MagicNetwork.d().getSettingsAppName() + "-" + "settings";
    }

    public SNPSettingsLocalizationProvider() {
        AppSettingsManager.a().a(new AppSettingsModel(){

            @Override
            public void a() {
                SNPSettingsLocalizationProvider.this.b();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(JsonNode object, Map<String, String> object2) {
        if (object.isTextual()) {
            String string2 = object.textValue();
            if (!object2.containsKey(string2)) return;
            object2 = (String)object2.get(string2);
            try {
                if (!(object instanceof TextNode)) return;
                Field field = TextNode.class.getDeclaredField("_value");
                field.setAccessible(true);
                field.set(object, object2);
                return;
            }
            catch (Exception exception) {
                Log.d(b, "Failed to replace [" + string2 + "] with [" + (String)object2 + "]", exception);
                return;
            }
        }
        if (object.isArray()) {
            object = object.iterator();
            while (object.hasNext()) {
                this.a((JsonNode)object.next(), (Map<String, String>)object2);
            }
            return;
        } else {
            if (!object.isContainerNode()) return;
            {
                object = object.fields();
                while (object.hasNext()) {
                    this.a((JsonNode)((Map.Entry)object.next()).getValue(), (Map<String, String>)object2);
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void b() {
        synchronized (this) {
            if (this.c == null) {
                Log.e(b, "localizeSNPSettings: configuration not set");
            } else {
                Log.b(b, "Start localizing " + this.c);
                Map<String, Map<String, String>> map = this.c.a(a);
                if (map != null) {
                    AppSettingsManager appSettingsManager = AppSettingsManager.a();
                    synchronized (appSettingsManager) {
                        Iterator<String> iterator = map.keySet().iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            Map<String, String> map2 = map.get(object);
                            object = AppSettingsManager.a().a(MagicNetwork.d().getSettingsAppName() + "." + (String)object);
                            if (object == null) continue;
                            object = object.values().iterator();
                            while (object.hasNext()) {
                                this.a((JsonNode)object.next(), map2);
                            }
                        }
                    }
                }
            }
            return;
        }
    }

    @Override
    public <T> T a(T t) {
        throw new UnsupportedOperationException("SNPSettingsLocalizationProvider doesn't support this operation");
    }

    @Override
    public String a() {
        return a;
    }

    public String a(String object, String string2, @Nullable String string3) {
        Map<String, Map<String, String>> map = this.c.a(a);
        if (map != null && (object = map.get(object)) != null) {
            string3 = (String)object.get(string2 + "-" + string3);
            if (string3 == null) {
                return (String)object.get(string2);
            }
            return string3;
        }
        return null;
    }

    @Override
    public void a(Context context) {
    }

    @Override
    public void a(LocalizationManager localizationConfig) {
        this.c = localizationConfig;
        AppSettingsManager.a().g();
        NotificationCenter.a().b("APP_SETTINGS_LOADED_EVENT", new Object[0]);
    }

}

