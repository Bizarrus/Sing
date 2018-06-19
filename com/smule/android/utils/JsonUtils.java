package com.smule.android.utils;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smule.android.logging.Log;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonUtils {
    public static final String f17783a = JsonUtils.class.getName();
    private static ObjectMapper f17784b = new ObjectMapper();

    static {
        f17784b.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        f17784b.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper m18984a() {
        return f17784b;
    }

    public static <T> T m18985a(JsonNode jsonNode, Class<T> cls) {
        try {
            return m18984a().treeToValue(jsonNode, cls);
        } catch (Throwable e) {
            Log.d(f17783a, "Failed to parse JSON entity " + cls.getSimpleName(), e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> m18989a(JsonNode jsonNode, TypeReference<List<T>> typeReference) {
        try {
            return (List) m18984a().readValue(jsonNode.traverse(), typeReference);
        } catch (Throwable e) {
            Log.d(f17783a, "Failed to parse JSON list " + typeReference.getType(), e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> m18990a(String str, TypeReference<List<T>> typeReference) {
        if (str == null) {
            Log.e(f17783a, "parseJsonList - jsonString is null; returning empty list");
            return new ArrayList();
        }
        try {
            JsonNode jsonNode = (JsonNode) m18984a().readValue(str, JsonNode.class);
            if (jsonNode != null) {
                return m18989a(jsonNode, (TypeReference) typeReference);
            }
        } catch (IOException e) {
            Log.e(f17783a, "parseJsonList - IOException thrown parsing JSON");
        }
        Log.e(f17783a, "parseJsonList - got to end of method without returning properly parsed list");
        return new ArrayList();
    }

    public static <K, V> Map<K, V> m18993b(JsonNode jsonNode, TypeReference<Map<K, V>> typeReference) {
        try {
            return (Map) m18984a().readValue(jsonNode.traverse(), typeReference);
        } catch (Throwable e) {
            Log.d(f17783a, "Failed to parse JSON map " + typeReference.getType(), e);
            throw new RuntimeException(e);
        }
    }

    public static String m18986a(JsonNode jsonNode, String str, String str2) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.asText();
        }
        return str2;
    }

    public static String m18992b(JsonNode jsonNode, String str, String str2) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.textValue();
        }
        return str2;
    }

    public static long m18983a(JsonNode jsonNode, String str, long j) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.asLong();
        }
        return j;
    }

    public static int m18982a(JsonNode jsonNode, String str, int i) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.asInt();
        }
        return i;
    }

    public static boolean m18991a(JsonNode jsonNode, String str, boolean z) {
        JsonNode jsonNode2 = jsonNode.get(str);
        if (jsonNode2 != null) {
            return jsonNode2.asBoolean();
        }
        return z;
    }

    public static String m18988a(Map<String, JsonNode> map) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = map.size();
        int i = 0;
        for (Entry entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue().toString());
            if (i != size - 1) {
                stringBuilder.append('\n');
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static <T> String m18987a(T t) {
        try {
            return f17784b.writeValueAsString(t);
        } catch (Throwable e) {
            Log.d(f17783a, "Error serializing object to JSON", e);
            return null;
        }
    }
}
