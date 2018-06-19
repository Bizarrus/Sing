package com.smule.android.network.managers.l10n;

import android.content.Context;
import com.smule.android.network.managers.LocalizationManager$LocalizationConfig;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class StoreLocalizationProvider implements LocalizationProvider {
    private LocalizationManager$LocalizationConfig f17387a;
    private Context f17388b;
    private final Map<Class, LocalizableObject> f17389c = new HashMap();

    private class LocalizableObject {
        public String f17382a;
        public String f17383b;
        public Field f17384c;
        public Map<String, Field> f17385d = new HashMap();
        final /* synthetic */ StoreLocalizationProvider f17386e;

        public LocalizableObject(StoreLocalizationProvider storeLocalizationProvider, String str, String str2, Field field, Map<String, Field> map) {
            this.f17386e = storeLocalizationProvider;
            this.f17382a = str;
            this.f17383b = str2;
            this.f17384c = field;
            this.f17385d = map;
        }
    }

    public String mo6278a() {
        return "store";
    }

    public void mo6279a(Context context) {
        this.f17388b = context;
    }

    public void mo6280a(LocalizationManager$LocalizationConfig localizationManager$LocalizationConfig) {
        this.f17387a = localizationManager$LocalizationConfig;
    }

    public <T> T mo6277a(T t) {
        if (!(t == null || this.f17387a == null || this.f17387a.m18250a())) {
            String a;
            Class cls = t.getClass();
            if (!this.f17389c.containsKey(cls)) {
                LocalizationObject localizationObject = (LocalizationObject) t.getClass().getAnnotation(LocalizationObject.class);
                if (localizationObject == null) {
                    this.f17389c.put(cls, null);
                } else {
                    a = localizationObject.a();
                    Map hashMap = new HashMap();
                    Field field = null;
                    String str = null;
                    for (Field field2 : t.getClass().getDeclaredFields()) {
                        if (((LocalizationObjectId) field2.getAnnotation(LocalizationObjectId.class)) != null) {
                            str = field2.getName();
                            field = field2;
                        }
                        Class type = field2.getType();
                        if (type != null && type.isAssignableFrom(String.class)) {
                            hashMap.put(field2.getName(), field2);
                        }
                    }
                    if (str == null) {
                        this.f17389c.put(cls, null);
                    } else {
                        this.f17389c.put(cls, new LocalizableObject(this, a, str, field, hashMap));
                    }
                }
            }
            LocalizableObject localizableObject = (LocalizableObject) this.f17389c.get(cls);
            if (localizableObject != null) {
                String str2;
                a = localizableObject.f17382a;
                try {
                    str2 = (String) localizableObject.f17384c.get(t);
                } catch (IllegalAccessException e) {
                    str2 = null;
                }
                if (str2 != null) {
                    Map a2 = this.f17387a.m18248a(a, str2);
                    if (a2 != null) {
                        for (String str22 : a2.keySet()) {
                            try {
                                Field field3 = (Field) localizableObject.f17385d.get(str22);
                                if (field3 != null) {
                                    field3.set(t, a2.get(str22));
                                }
                            } catch (IllegalAccessException e2) {
                            }
                        }
                    }
                }
            }
        }
        return t;
    }
}
