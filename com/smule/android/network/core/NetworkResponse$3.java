package com.smule.android.network.core;

import com.fasterxml.jackson.databind.JsonNode;

class NetworkResponse$3 implements NetworkResponse$ValueGetter<Integer> {
    NetworkResponse$3() {
    }

    public Integer m18087a(JsonNode jsonNode, Integer num) {
        return Integer.valueOf(jsonNode.asInt(num.intValue()));
    }
}
