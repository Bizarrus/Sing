/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.smule.android.network.managers.l10n;

import android.content.Context;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.android.network.managers.l10n.LocalizationObject;
import com.smule.android.network.managers.l10n.LocalizationObjectId;
import com.smule.android.network.managers.l10n.LocalizationProvider;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class StoreLocalizationProvider
implements LocalizationProvider {
    private LocalizationManager a;
    private Context b;
    private final Map<Class, LocalizableObject> c = new HashMap<Class, LocalizableObject>();

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public <T> T a(T t) {
        block13 : {
            if (t != null && this.a != null && !this.a.a()) {
                Object object;
                Object object2;
                Object object3;
                Object object4;
                Class class_ = t.getClass();
                if (!this.c.containsKey(class_)) {
                    object = t.getClass().getAnnotation(LocalizationObject.class);
                    if (object == null) {
                        this.c.put(class_, null);
                        return t;
                    }
                    object3 = object.a();
                    HashMap<String, Field> hashMap = new HashMap<String, Field>();
                    Field[] arrfield = t.getClass().getDeclaredFields();
                    int n = arrfield.length;
                    int n2 = 0;
                    object = null;
                    object4 = null;
                    do {
                        Class<String> class_2;
                        if (n2 >= n) {
                            this.c.put(class_, null);
                            return t;
                        }
                        object2 = arrfield[n2];
                        if (object2.getAnnotation(LocalizationObjectId.class) != null) {
                            object4 = object2.getName();
                            object = object2;
                        }
                        if ((class_2 = object2.getType()) != null && class_2.isAssignableFrom(String.class)) {
                            hashMap.put(object2.getName(), (Field)object2);
                        }
                        ++n2;
                    } while (true);
                }
                object4 = this.c.get(class_);
                if (object4 != null) {
                    object2 = object4.a;
                    try {
                        object = (String)object4.c.get(t);
                        if (object == null || (object = this.a.a((String)object2, (String)object)) == null) break block13;
                        object2 = object.keySet().iterator();
                    }
                    catch (IllegalAccessException illegalAccessException) {
                        return t;
                    }
                    while (object2.hasNext()) {
                        class_ = (String)object2.next();
                        try {
                            object3 = object4.d.get(class_);
                            if (object3 == null) continue;
                            object3.set(t, object.get(class_));
                        }
                        catch (IllegalAccessException illegalAccessException) {}
                    }
                }
            }
        }
        return t;
    }

    @Override
    public String a() {
        return "store";
    }

    @Override
    public void a(Context context) {
        this.b = context;
    }

    @Override
    public void a(LocalizationManager localizationConfig) {
        this.a = localizationConfig;
    }

    private class LocalizableObject {
        public String a;
        public String b;
        public Field c;
        public Map<String, Field> d;

        public LocalizableObject(String string2, String string3, Field field, Map<String, Field> map) {
            this.d = new HashMap<String, Field>();
            this.a = string2;
            this.b = string3;
            this.c = field;
            this.d = map;
        }
    }

}

