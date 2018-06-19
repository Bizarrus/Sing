package com.smule.android.network.managers;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.network.managers.ArrangementManager.ArrangementDetailsResponse;

class ArrangementManager$ArrangementDetailsResponse$1 implements Creator<ArrangementDetailsResponse> {
    ArrangementManager$ArrangementDetailsResponse$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18124a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18125a(i);
    }

    public ArrangementDetailsResponse m18124a(Parcel parcel) {
        return new ArrangementDetailsResponse(parcel);
    }

    public ArrangementDetailsResponse[] m18125a(int i) {
        return new ArrangementDetailsResponse[i];
    }
}
