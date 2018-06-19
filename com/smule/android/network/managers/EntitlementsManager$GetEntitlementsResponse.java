package com.smule.android.network.managers;

import android.support.annotation.NonNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.EntityKey;
import com.smule.android.network.models.EntityKey.EntityType;
import java.util.ArrayList;
import java.util.Iterator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntitlementsManager$GetEntitlementsResponse extends ParsedResponse {
    @JsonProperty("products")
    public ArrayList<EntityKey> mProducts;

    static EntitlementsManager$GetEntitlementsResponse m7938a(NetworkResponse networkResponse) {
        return (EntitlementsManager$GetEntitlementsResponse) ParsedResponse.m7676a(networkResponse, EntitlementsManager$GetEntitlementsResponse.class);
    }

    public String toString() {
        return "GetEntitlementsResponse{mProducts=" + this.mProducts + '}';
    }

    @NonNull
    public ArrayList<String> getArrKeys() {
        ArrayList<String> arrayList = new ArrayList();
        if (this.mProducts == null) {
            return arrayList;
        }
        Iterator it = this.mProducts.iterator();
        while (it.hasNext()) {
            EntityKey entityKey = (EntityKey) it.next();
            if (entityKey.entityType == EntityType.p) {
                arrayList.add(entityKey.entityId);
            }
        }
        return arrayList;
    }
}
