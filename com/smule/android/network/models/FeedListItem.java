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
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.FeedListItem;
import com.smule.android.network.models.FeedListItemObject;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FeedListItem
implements Parcelable {
    public static final Parcelable.Creator<FeedListItem> CREATOR;
    private static final String a;
    @JsonProperty(value="action")
    public String action;
    @JsonProperty(value="actionTime")
    public long actionTime;
    @JsonProperty(value="content")
    public String content;
    @JsonProperty(value="count")
    public int count;
    @JsonProperty(value="object")
    public FeedListItemObject object;
    @JsonProperty(value="recId")
    public String recId;
    @JsonProperty(value="source")
    public String source;
    @JsonProperty(value="subject")
    public AccountIcon subject;

    static {
        a = FeedListItem.class.getSimpleName();
        CREATOR = new Parcelable.Creator<FeedListItem>(){

            public FeedListItem a(Parcel parcel) {
                return new FeedListItem(parcel);
            }

            public FeedListItem[] a(int n) {
                return new FeedListItem[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
    }

    public FeedListItem() {
    }

    private FeedListItem(Parcel parcel) {
        this.recId = parcel.readString();
        this.subject = (AccountIcon)parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.action = parcel.readString();
        this.content = parcel.readString();
        this.count = parcel.readInt();
        this.actionTime = parcel.readLong();
        this.source = parcel.readString();
        this.object = (FeedListItemObject)parcel.readParcelable(FeedListItemObject.class.getClassLoader());
    }

    public  a() {
        for ( actionType : .values()) {
            if (!actionType.name().toUpperCase().equals(this.action)) continue;
            return actionType;
        }
        if (this.b() == .a) {
            Log.e(a, "Action type " + this.action + ", not found in ActionType enum!");
        }
        return null;
    }

    public  b() {
        for ( sourceType : .values()) {
            if (!sourceType.name().toUpperCase().equals(this.source)) continue;
            return sourceType;
        }
        Log.e(a, "Source type " + this.source + ", not found in SourceType enum!");
        return null;
    }

    public boolean c() {
        if (this.object.arrVersionLite != null) {
            return true;
        }
        return false;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "FeedListItem{recId='" + this.recId + '\'' + ", subject=" + this.subject + ", action='" + this.action + '\'' + ", content='" + this.content + '\'' + ", count=" + this.count + ", actionTime=" + this.actionTime + ", source='" + this.source + '\'' + ", object=" + this.object + '}';
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.recId);
        parcel.writeParcelable((Parcelable)this.subject, 0);
        parcel.writeString(this.action);
        parcel.writeString(this.content);
        parcel.writeInt(this.count);
        parcel.writeLong(this.actionTime);
        parcel.writeString(this.source);
        parcel.writeParcelable((Parcelable)this.object, 0);
    }

}

