package com.smule.android.network.core;

import com.fasterxml.jackson.databind.JsonNode;

class NetworkResponse$4 implements NetworkResponse$ValueGetter<Long> {
    NetworkResponse$4() {
    }

    public Long m18089a(JsonNode jsonNode, Long l) {
        return Long.valueOf(jsonNode.asLong(l.longValue()));
    }
}
