/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  com.fasterxml.jackson.annotation.JsonIgnoreProperties
 *  com.fasterxml.jackson.annotation.JsonProperty
 */
package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Invite;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationItemAggObject;
import com.smule.android.network.models.NotificationItemObject;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.network.models.PerformanceV2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationListItem
implements Parcelable {
    public static final Parcelable.Creator<NotificationListItem> CREATOR;
    private static final String b;
    public  a;
    @JsonProperty(value="aggObject")
    public NotificationItemAggObject aggObject;
    @JsonProperty(value="content")
    public String content;
    @JsonProperty(value="count")
    public int count;
    @JsonProperty(value="entity")
    public String entity;
    @JsonProperty(value="object")
    public NotificationItemObject object;
    @JsonProperty(value="subjects")
    public List<AccountIcon> subjects = new ArrayList<AccountIcon>();
    @JsonProperty(value="time")
    public long time;
    @JsonProperty(value="type")
    public String type;

    static {
        b = NotificationListItem.class.getSimpleName();
        CREATOR = new Parcelable.Creator<NotificationListItem>(){

            public NotificationListItem a(Parcel parcel) {
                return new NotificationListItem(parcel);
            }

            public NotificationListItem[] a(int n) {
                return new NotificationListItem[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public NotificationListItem() {
    }

    private NotificationListItem(Parcel parcel) {
        parcel.readTypedList(this.subjects, AccountIcon.CREATOR);
        this.type = parcel.readString();
        this.entity = parcel.readString();
        this.content = parcel.readString();
        this.count = parcel.readInt();
        this.time = parcel.readLong();
        this.object = (NotificationItemObject)parcel.readParcelable(NotificationItemObject.class.getClassLoader());
        this.aggObject = (NotificationItemAggObject)parcel.readParcelable(NotificationItemAggObject.class.getClassLoader());
    }

    public NotificationListItem(Invite invite) {
        this.count = 1;
        this.type = Notification.f.toString();
        this.subjects = new ArrayList<AccountIcon>();
        this.subjects.add(invite.performance.accountIcon);
        this.time = invite.invitedAt;
        this.object = new NotificationItemObject();
        this.object.performanceIcon = invite.performance;
        this.entity = Notification.a.toString();
        this.content = invite.performance.message;
    }

    /*
     * Enabled aggressive block sorting
     */
    public NotificationListItem(Notification notification) {
        this.content = notification.content;
        this.object = notification.object;
        this.count = 1;
        this.entity = notification.entity;
        this.time = notification.time;
        this.subjects = notification.subject != null ? Collections.singletonList(notification.subject) : new ArrayList<AccountIcon>();
        this.type = notification.type;
    }

    public NotificationListItem(PerformanceV2 performanceV2) {
        this.count = 1;
        this.type = Notification.f.toString();
        this.subjects = new ArrayList<AccountIcon>();
        this.subjects.add(performanceV2.accountIcon);
        this.time = performanceV2.createdAt;
        this.object = new NotificationItemObject();
        this.object.performanceIcon = performanceV2;
        this.entity = Notification.a.toString();
        this.content = performanceV2.message;
    }

    public Notification a() {
        for (Notification type : Notification.values()) {
            if (!type.name().toUpperCase().equals(this.type)) continue;
            return type;
        }
        Log.e(b, "Notification type, " + this.type + ", not found in Notification.Type enum! Did you forget to add it there? Or is it a header?");
        return null;
    }

    public void a( detailedType) {
        this.a = detailedType;
    }

    public  b() {
        return this.a;
    }

    public Notification c() {
        for (Notification entityType : Notification.values()) {
            if (!entityType.name().toUpperCase().equals(this.entity)) continue;
            return entityType;
        }
        Log.e(b, "Notification entity type, " + this.entity + ", not found in Notification.EntityType enum! Did you forget to add it there?");
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "NotificationListItem{subjects=" + this.subjects + ", type='" + this.type + '\'' + ", entity='" + this.entity + '\'' + ", content='" + this.content + '\'' + ", count=" + this.count + ", time=" + this.time + ", object=" + this.object + ", aggObject=" + this.aggObject + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeTypedList(this.subjects);
        parcel.writeString(this.type);
        parcel.writeString(this.entity);
        parcel.writeString(this.content);
        parcel.writeInt(this.count);
        parcel.writeLong(this.time);
        parcel.writeParcelable((Parcelable)this.object, 0);
        parcel.writeParcelable((Parcelable)this.aggObject, 0);
    }

}

