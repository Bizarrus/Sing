package com.smule.android.network.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityKey {
    @JsonProperty("entityId")
    public String entityId;
    @JsonProperty("entityType")
    public EntityType entityType;

    public String toString() {
        return "EntityKey{entityId=" + this.entityId + ", entityType=" + this.entityType + '}';
    }
}
