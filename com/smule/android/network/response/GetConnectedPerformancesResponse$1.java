package com.smule.android.network.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class GetConnectedPerformancesResponse$1 implements Creator<GetConnectedPerformancesResponse> {
    GetConnectedPerformancesResponse$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18608a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18609a(i);
    }

    public GetConnectedPerformancesResponse m18608a(Parcel parcel) {
        return new GetConnectedPerformancesResponse(parcel);
    }

    public GetConnectedPerformancesResponse[] m18609a(int i) {
        return new GetConnectedPerformancesResponse[i];
    }
}
