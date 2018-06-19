package com.smule.android.network.api;

import com.smule.android.network.core.SnpRequest;
import java.util.List;

public class UserAPI$UserInitRequest extends SnpRequest {
    public List<Uuid> uuids;

    public static class Uuid {
        public String uuid;
        public String uuidType;

        public Uuid setUuid(String str) {
            this.uuid = str;
            return this;
        }

        public Uuid setUuidType(String str) {
            this.uuidType = str;
            return this;
        }
    }

    public UserAPI$UserInitRequest setUuids(List<Uuid> list) {
        this.uuids = list;
        return this;
    }
}
