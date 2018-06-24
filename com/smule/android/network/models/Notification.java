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
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification;
import com.smule.android.network.models.NotificationItemObject;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Notification
implements Parcelable {
    public static final Parcelable.Creator<Notification> CREATOR = new Parcelable.Creator<Notification>(){

        public Notification a(Parcel parcel) {
            return new Notification(parcel);
        }

        public Notification[] a(int n) {
            return new Notification[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="content")
    public String content;
    @JsonProperty(value="entity")
    public String entity;
    @JsonProperty(value="notificationKey")
    public String notificationKey;
    @JsonProperty(value="object")
    public NotificationItemObject object;
    @JsonProperty(value="subject")
    public AccountIcon subject;
    @JsonProperty(value="time")
    public long time;
    @JsonProperty(value="type")
    public String type;

    public Notification() {
    }

    private Notification(Parcel parcel) {
        this.notificationKey = parcel.readString();
        this.subject = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.type = parcel.readString();
        this.entity = parcel.readString();
        this.content = parcel.readString();
        this.time = parcel.readLong();
        this.object = (NotificationItemObject)parcel.readParcelable(NotificationItemObject.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Notification{notificationKey='" + this.notificationKey + '\'' + ", subject=" + this.subject + ", type='" + this.type + '\'' + ", entity=" + this.entity + ", content=" + this.content + ", time=" + this.time + ", object=" + this.object + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.notificationKey);
        parcel.writeParcelable((Parcelable)this.subject, 0);
        parcel.writeString(this.type);
        parcel.writeString(this.entity);
        parcel.writeString(this.content);
        parcel.writeLong(this.time);
        parcel.writeParcelable((Parcelable)this.object, 0);
    }

}

