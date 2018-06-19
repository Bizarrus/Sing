package com.smule.android.uploader;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.smule.android.uploader.UploadJob.UploadResourceInfo;

class UploadJob$UploadResourceInfo$1 implements Creator<UploadResourceInfo> {
    UploadJob$UploadResourceInfo$1() {
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m18944a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m18945a(i);
    }

    public UploadResourceInfo m18944a(Parcel parcel) {
        return new UploadResourceInfo(parcel);
    }

    public UploadResourceInfo[] m18945a(int i) {
        return new UploadResourceInfo[i];
    }
}
