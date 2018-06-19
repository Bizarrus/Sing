package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SNPChat implements Parcelable {
    public static final Creator<SNPChat> CREATOR = new 1();
    @JsonProperty("name")
    public String jid;
    @JsonProperty("type")
    public String type;

    public SNPChat(Parcel parcel) {
        this.jid = parcel.readString();
        this.type = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.jid);
        parcel.writeString(this.type);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SNPChat sNPChat = (SNPChat) obj;
        if (this.jid.equals(sNPChat.jid) && this.type.equals(sNPChat.type)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.jid.hashCode() * 31) + this.type.hashCode();
    }
}
