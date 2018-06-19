package com.smule.android.network.core;

import com.fasterxml.jackson.databind.JsonNode;

class NetworkResponse$2 implements NetworkResponse$ValueGetter<String> {
    NetworkResponse$2() {
    }

    public String m18086a(JsonNode jsonNode, String str) {
        return jsonNode.asText(str);
    }
}
