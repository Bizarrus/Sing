/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class EntityKey {
    @JsonProperty(value="entityId")
    public String entityId;
    @JsonProperty(value="entityType")
    public EntityType entityType;

    public String toString() {
        return "EntityKey{entityId=" + this.entityId + ", entityType=" + (Object)((Object)this.entityType) + '}';
    }

    public static enum EntityType {
        a,
        b,
        c,
        d,
        e,
        f,
        g,
        h,
        i,
        j,
        k,
        l,
        m,
        n,
        o,
        p;
        

        private EntityType() {
        }
    }

}

