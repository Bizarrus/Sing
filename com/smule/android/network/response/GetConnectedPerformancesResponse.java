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
package com.smule.android.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.response.GetConnectedPerformancesResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GetConnectedPerformancesResponse
extends ParsedResponse
implements Parcelable {
    public static final Parcelable.Creator<GetConnectedPerformancesResponse> CREATOR = new Parcelable.Creator<GetConnectedPerformancesResponse>(){

        public GetConnectedPerformancesResponse a(Parcel parcel) {
            return new GetConnectedPerformancesResponse(parcel);
        }

        public GetConnectedPerformancesResponse[] a(int n) {
            return new GetConnectedPerformancesResponse[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    @JsonProperty(value="performanceIcons")
    public List<PerformanceV2> performanceIcons = new ArrayList<PerformanceV2>();

    public GetConnectedPerformancesResponse() {
    }

    public GetConnectedPerformancesResponse(Parcel parcel) {
        this.performanceIcons = parcel.readArrayList(GetConnectedPerformancesResponse.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Object object = stringBuilder.append("status=");
        Object object2 = this.a() ? "ok" : "fail";
        object.append((String)object2).append(' ');
        stringBuilder.append("performanceIcons=[");
        object2 = this.performanceIcons.iterator();
        boolean bl = true;
        do {
            if (!object2.hasNext()) {
                stringBuilder.append(']');
                return stringBuilder.toString();
            }
            object = (PerformanceV2)object2.next();
            if (!bl) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(object.toString());
            bl = false;
        } while (true);
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeList(this.performanceIcons);
    }
}

