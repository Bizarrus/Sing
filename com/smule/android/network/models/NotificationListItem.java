package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.models.Notification.EntityType;
import com.smule.android.network.models.Notification.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NotificationListItem implements Parcelable {
    public static final Creator<NotificationListItem> CREATOR = new 1();
    private static final String f6888b = NotificationListItem.class.getSimpleName();
    public DetailedType f6889a;
    @JsonProperty("aggObject")
    public NotificationItemAggObject aggObject;
    @JsonProperty("content")
    public String content;
    @JsonProperty("count")
    public int count;
    @JsonProperty("entity")
    public String entity;
    @JsonProperty("object")
    public NotificationItemObject object;
    @JsonProperty("subjects")
    public List<AccountIcon> subjects;
    @JsonProperty("time")
    public long time;
    @JsonProperty("type")
    public String type;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.subjects);
        parcel.writeString(this.type);
        parcel.writeString(this.entity);
        parcel.writeString(this.content);
        parcel.writeInt(this.count);
        parcel.writeLong(this.time);
        parcel.writeParcelable(this.object, 0);
        parcel.writeParcelable(this.aggObject, 0);
    }

    public NotificationListItem() {
        this.subjects = new ArrayList();
    }

    public NotificationListItem(Notification notification) {
        this.subjects = new ArrayList();
        this.content = notification.content;
        this.object = notification.object;
        this.count = 1;
        this.entity = notification.entity;
        this.time = notification.time;
        if (notification.subject != null) {
            this.subjects = Collections.singletonList(notification.subject);
        } else {
            this.subjects = new ArrayList();
        }
        this.type = notification.type;
    }

    public NotificationListItem(Invite invite) {
        this.subjects = new ArrayList();
        this.count = 1;
        this.type = Type.f.toString();
        this.subjects = new ArrayList();
        this.subjects.add(invite.performance.accountIcon);
        this.time = invite.invitedAt;
        this.object = new NotificationItemObject();
        this.object.performanceIcon = invite.performance;
        this.entity = EntityType.a.toString();
        this.content = invite.performance.message;
    }

    public NotificationListItem(PerformanceV2 performanceV2) {
        this.subjects = new ArrayList();
        this.count = 1;
        this.type = Type.f.toString();
        this.subjects = new ArrayList();
        this.subjects.add(performanceV2.accountIcon);
        this.time = (long) performanceV2.createdAt;
        this.object = new NotificationItemObject();
        this.object.performanceIcon = performanceV2;
        this.entity = EntityType.a.toString();
        this.content = performanceV2.message;
    }

    private NotificationListItem(Parcel parcel) {
        this.subjects = new ArrayList();
        parcel.readTypedList(this.subjects, AccountIcon.CREATOR);
        this.type = parcel.readString();
        this.entity = parcel.readString();
        this.content = parcel.readString();
        this.count = parcel.readInt();
        this.time = parcel.readLong();
        this.object = (NotificationItemObject) parcel.readParcelable(NotificationItemObject.class.getClassLoader());
        this.aggObject = (NotificationItemAggObject) parcel.readParcelable(NotificationItemAggObject.class.getClassLoader());
    }

    public Type m8260a() {
        for (Type type : Type.values()) {
            if (type.name().toUpperCase().equals(this.type)) {
                return type;
            }
        }
        Log.m7776e(f6888b, "Notification type, " + this.type + ", not found in Notification.Type enum! Did you forget to add it there? Or is it a header?");
        return null;
    }

    public DetailedType m8262b() {
        return this.f6889a;
    }

    public void m8261a(DetailedType detailedType) {
        this.f6889a = detailedType;
    }

    public EntityType m8263c() {
        for (EntityType entityType : EntityType.values()) {
            if (entityType.name().toUpperCase().equals(this.entity)) {
                return entityType;
            }
        }
        Log.m7776e(f6888b, "Notification entity type, " + this.entity + ", not found in Notification.EntityType enum! Did you forget to add it there?");
        return null;
    }

    public String toString() {
        return "NotificationListItem{subjects=" + this.subjects + ", type='" + this.type + '\'' + ", entity='" + this.entity + '\'' + ", content='" + this.content + '\'' + ", count=" + this.count + ", time=" + this.time + ", object=" + this.object + ", aggObject=" + this.aggObject + '}';
    }
}
