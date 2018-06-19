package com.smule.singandroid;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.smule.android.network.managers.PerformanceManager.PerformanceResourceInfo;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.survey.SurveyContext.EntryPoint;
import java.util.ArrayList;

public class PostSingBundle implements Parcelable {
    public static final Creator<PostSingBundle> CREATOR = new C39711();
    public static String f19315a = PostSingBundle.class.getName();
    public SingBundle f19316b;
    public PerformanceV2 f19317c;
    public boolean f19318d;
    public boolean f19319e;
    public String f19320f;
    public EntryPoint f19321g;
    public boolean f19322h;
    public ArrayList<PerformanceResourceInfo> f19323i;

    static class C39711 implements Creator<PostSingBundle> {
        C39711() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m20868a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m20869a(i);
        }

        public PostSingBundle m20868a(Parcel parcel) {
            return new PostSingBundle(parcel);
        }

        public PostSingBundle[] m20869a(int i) {
            return new PostSingBundle[i];
        }
    }

    public int describeContents() {
        return 0;
    }

    public PostSingBundle(SingBundle singBundle) {
        this.f19316b = singBundle;
    }

    protected PostSingBundle(Parcel parcel) {
        boolean z = true;
        this.f19316b = (SingBundle) parcel.readParcelable(SingBundle.class.getClassLoader());
        this.f19317c = (PerformanceV2) parcel.readParcelable(PerformanceV2.class.getClassLoader());
        this.f19318d = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.f19319e = z;
        this.f19320f = parcel.readString();
        String readString = parcel.readString();
        this.f19321g = readString == null ? null : EntryPoint.valueOf(readString);
        this.f19323i = parcel.createTypedArrayList(PerformanceResourceInfo.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeParcelable(this.f19316b, 0);
        parcel.writeParcelable(this.f19317c, 0);
        parcel.writeByte((byte) (this.f19318d ? 1 : 0));
        if (!this.f19319e) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.f19320f);
        parcel.writeString(this.f19321g == null ? null : this.f19321g.name());
        parcel.writeTypedList(this.f19323i);
    }

    public static PostSingBundle m20870a(Intent intent) {
        intent.setExtrasClassLoader(PostSingBundle.class.getClassLoader());
        return (PostSingBundle) intent.getParcelableExtra(f19315a);
    }

    public void m20871b(Intent intent) {
        intent.putExtra(f19315a, this);
    }
}
