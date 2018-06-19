package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification implements Parcelable {
    public static final Creator<Notification> CREATOR = new 1();
    @JsonProperty("content")
    public String content;
    @JsonProperty("entity")
    public String entity;
    @JsonProperty("notificationKey")
    public String notificationKey;
    @JsonProperty("object")
    public NotificationItemObject object;
    @JsonProperty("subject")
    public AccountIcon subject;
    @JsonProperty("time")
    public long time;
    @JsonProperty("type")
    public String type;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.notificationKey);
        parcel.writeParcelable(this.subject, 0);
        parcel.writeString(this.type);
        parcel.writeString(this.entity);
        parcel.writeString(this.content);
        parcel.writeLong(this.time);
        parcel.writeParcelable(this.object, 0);
    }

    private Notification(Parcel parcel) {
        this.notificationKey = parcel.readString();
        this.subject = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.type = parcel.readString();
        this.entity = parcel.readString();
        this.content = parcel.readString();
        this.time = parcel.readLong();
        this.object = (NotificationItemObject) parcel.readParcelable(NotificationItemObject.class.getClassLoader());
    }

    public String toString() {
        return "Notification{notificationKey='" + this.notificationKey + '\'' + ", subject=" + this.subject + ", type='" + this.type + '\'' + ", entity=" + this.entity + ", content=" + this.content + ", time=" + this.time + ", object=" + this.object + '}';
    }
}
