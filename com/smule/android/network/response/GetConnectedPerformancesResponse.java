package com.smule.android.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smule.android.network.core.ParsedResponse;
import com.smule.android.network.models.PerformanceV2;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetConnectedPerformancesResponse extends ParsedResponse implements Parcelable {
    public static final Creator<GetConnectedPerformancesResponse> CREATOR = new 1();
    @JsonProperty("performanceIcons")
    public List<PerformanceV2> performanceIcons = new ArrayList();

    public GetConnectedPerformancesResponse(Parcel parcel) {
        this.performanceIcons = parcel.readArrayList(GetConnectedPerformancesResponse.class.getClassLoader());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("status=").append(m7677a() ? "ok" : "fail").append(' ');
        stringBuilder.append("performanceIcons=[");
        Object obj = 1;
        for (PerformanceV2 performanceV2 : this.performanceIcons) {
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(performanceV2.toString());
            obj = null;
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.performanceIcons);
    }
}
