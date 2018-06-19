package com.smule.android.network.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class PartnerArtistV2$1 implements Creator<PartnerArtistV2> {
    PartnerArtistV2$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18572a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18573a(i);
    }

    public PartnerArtistV2 m18572a(Parcel parcel) {
        return new PartnerArtistV2(parcel);
    }

    public PartnerArtistV2[] m18573a(int i) {
        return new PartnerArtistV2[i];
    }
}
