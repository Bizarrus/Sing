/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.core.JsonParser
 *  com.fasterxml.jackson.core.JsonParser$Feature
 *  com.fasterxml.jackson.core.JsonProcessingException
 *  com.fasterxml.jackson.core.TreeNode
 *  com.fasterxml.jackson.core.type.TypeReference
 *  com.fasterxml.jackson.databind.DeserializationFeature
 *  com.fasterxml.jackson.databind.JsonNode
 *  com.fasterxml.jackson.databind.ObjectMapper
 */
package com.smule.android.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smule.android.logging.Log;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtils {
    public static final String a = JsonUtils.class.getName();
    private static ObjectMapper b = new ObjectMapper();

    static {
        b.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        b.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static int a(JsonNode jsonNode, String string2, int n) {
        if ((jsonNode = jsonNode.get(string2)) != null) {
            n = jsonNode.asInt();
        }
        return n;
    }

    public static long a(JsonNode jsonNode, String string2, long l) {
        if ((jsonNode = jsonNode.get(string2)) != null) {
            l = jsonNode.asLong();
        }
        return l;
    }

    public static ObjectMapper a() {
        return b;
    }

    public static <T> T a(JsonNode object, Class<T> class_) {
        try {
            object = JsonUtils.a().treeToValue((TreeNode)object, class_);
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to parse JSON entity " + class_.getSimpleName(), iOException);
            throw new RuntimeException(iOException);
        }
        return (T)object;
    }

    public static <T> T a(String string2, Class<T> class_) {
        Object object;
        block5 : {
            if (string2 == null) {
                return null;
            }
            try {
                object = (JsonNode)JsonUtils.a().readValue(string2, JsonNode.class);
                if (object != null) break block5;
            }
            catch (Exception exception) {
                Log.d(a, "Could not parse " + class_.getSimpleName() + " from String: " + string2, exception);
                return null;
            }
            Log.e(a, "Could not parse " + class_.getSimpleName() + " from String: " + string2);
            return null;
        }
        object = JsonUtils.a((JsonNode)object, class_);
        return (T)object;
    }

    public static String a(JsonNode jsonNode, String string2, String string3) {
        if ((jsonNode = jsonNode.get(string2)) != null) {
            string3 = jsonNode.asText();
        }
        return string3;
    }

    public static <T> String a(T object) {
        try {
            object = b.writeValueAsString(object);
            return object;
        }
        catch (JsonProcessingException jsonProcessingException) {
            Log.d(a, "Error serializing object to JSON", (Throwable)jsonProcessingException);
            return null;
        }
    }

    public static String a(Map<String, JsonNode> object) {
        StringBuilder stringBuilder = new StringBuilder();
        int n = object.size();
        object = object.entrySet().iterator();
        int n2 = 0;
        while (object.hasNext()) {
            Map.Entry entry = (Map.Entry)object.next();
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue().toString());
            if (n2 != n - 1) {
                stringBuilder.append('\n');
            }
            ++n2;
        }
        return stringBuilder.toString();
    }

    public static <T> List<T> a(JsonNode object, TypeReference<List<T>> typeReference) {
        try {
            object = (List)JsonUtils.a().readValue(object.traverse(), typeReference);
            return object;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to parse JSON list " + typeReference.getType(), iOException);
            throw new RuntimeException(iOException);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static <T> List<T> a(String list, TypeReference<List<T>> typeReference) {
        if (list == null) {
            Log.e(a, "parseJsonList - jsonString is null; returning empty list");
            return new ArrayList();
        }
        try {
            list = (JsonNode)JsonUtils.a().readValue((String)((Object)list), JsonNode.class);
            if (list != null) {
                return JsonUtils.a((JsonNode)list, typeReference);
            }
        }
        catch (IOException iOException) {
            Log.e(a, "parseJsonList - IOException thrown parsing JSON");
        }
        Log.e(a, "parseJsonList - got to end of method without returning properly parsed list");
        return new ArrayList();
    }

    public static boolean a(JsonNode jsonNode, String string2, boolean bl) {
        if ((jsonNode = jsonNode.get(string2)) != null) {
            bl = jsonNode.asBoolean();
        }
        return bl;
    }

    public static String b(JsonNode jsonNode, String string2, String string3) {
        if ((jsonNode = jsonNode.get(string2)) != null) {
            string3 = jsonNode.textValue();
        }
        return string3;
    }

    public static <K, V> Map<K, V> b(JsonNode object, TypeReference<Map<K, V>> typeReference) {
        try {
            object = (Map)JsonUtils.a().readValue(object.traverse(), typeReference);
            return object;
        }
        catch (IOException iOException) {
            Log.d(a, "Failed to parse JSON map " + typeReference.getType(), iOException);
            throw new RuntimeException(iOException);
        }
    }
}

