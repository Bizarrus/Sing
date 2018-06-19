package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.logging.Log;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeedListItem implements Parcelable {
    public static final Creator<FeedListItem> CREATOR = new 1();
    private static final String f6886a = FeedListItem.class.getSimpleName();
    @JsonProperty("action")
    public String action;
    @JsonProperty("actionTime")
    public long actionTime;
    @JsonProperty("content")
    public String content;
    @JsonProperty("count")
    public int count;
    @JsonProperty("object")
    public FeedListItemObject object;
    @JsonProperty("recId")
    public String recId;
    @JsonProperty("source")
    public String source;
    @JsonProperty("subject")
    public AccountIcon subject;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.recId);
        parcel.writeParcelable(this.subject, 0);
        parcel.writeString(this.action);
        parcel.writeString(this.content);
        parcel.writeInt(this.count);
        parcel.writeLong(this.actionTime);
        parcel.writeString(this.source);
        parcel.writeParcelable(this.object, 0);
    }

    public ActionType m8253a() {
        for (ActionType actionType : ActionType.values()) {
            if (actionType.name().toUpperCase().equals(this.action)) {
                return actionType;
            }
        }
        if (m8254b() == SourceType.a) {
            Log.m7776e(f6886a, "Action type " + this.action + ", not found in ActionType enum!");
        }
        return null;
    }

    public SourceType m8254b() {
        for (SourceType sourceType : SourceType.values()) {
            if (sourceType.name().toUpperCase().equals(this.source)) {
                return sourceType;
            }
        }
        Log.m7776e(f6886a, "Source type " + this.source + ", not found in SourceType enum!");
        return null;
    }

    private FeedListItem(Parcel parcel) {
        this.recId = parcel.readString();
        this.subject = (AccountIcon) parcel.readParcelable(AccountIcon.class.getClassLoader());
        this.action = parcel.readString();
        this.content = parcel.readString();
        this.count = parcel.readInt();
        this.actionTime = parcel.readLong();
        this.source = parcel.readString();
        this.object = (FeedListItemObject) parcel.readParcelable(FeedListItemObject.class.getClassLoader());
    }

    public String toString() {
        return "FeedListItem{recId='" + this.recId + '\'' + ", subject=" + this.subject + ", action='" + this.action + '\'' + ", content='" + this.content + '\'' + ", count=" + this.count + ", actionTime=" + this.actionTime + ", source='" + this.source + '\'' + ", object=" + this.object + '}';
    }

    public boolean m8255c() {
        return this.object.arrVersionLite != null;
    }
}
