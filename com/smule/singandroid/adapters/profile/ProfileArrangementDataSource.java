package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.FetchDataCallback;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.ArrangementManager$ArrangementVersionLiteListCallback;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionLiteListResponse;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ProfileFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;

public class ProfileArrangementDataSource extends MagicDataSource<ArrangementVersionLiteEntry> {
    ProfileFragment f20528a;
    private int f20529b;

    public ProfileArrangementDataSource(ProfileFragment profileFragment) {
        super(ProfileArrangementDataSource.class.getSimpleName() + ":" + profileFragment.m21064A().accountId);
        this.f20528a = profileFragment;
    }

    protected void mo6684a(Parcel parcel) {
        super.mo6684a(parcel);
        parcel.writeInt(this.f20529b);
    }

    protected void mo6685b(Parcel parcel) {
        super.mo6685b(parcel);
        this.f20529b = parcel.readInt();
    }

    public int mo6264d() {
        return this.f20529b;
    }

    public int mo6242a() {
        return 10;
    }

    public Future<?> mo6243a(int i, int i2, final FetchDataCallback<ArrangementVersionLiteEntry> fetchDataCallback) {
        final int I = this.f20528a.m21072I();
        return ArrangementManager.a().a(this.f20528a.m21064A().accountId, i, i2, new ArrangementManager$ArrangementVersionLiteListCallback(this) {
            final /* synthetic */ ProfileArrangementDataSource f20527c;

            public void handleResponse(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
                if (this.f20527c.f20528a != null && this.f20527c.f20528a.m19843a(I)) {
                    if (arrangementVersionLiteListResponse.a()) {
                        List arrayList = new ArrayList();
                        Iterator it = arrangementVersionLiteListResponse.mArrangementVersionLites.iterator();
                        while (it.hasNext()) {
                            arrayList.add((ArrangementVersionLiteEntry) SongbookEntry.m18745a((ArrangementVersionLite) it.next(), true));
                        }
                        if (arrangementVersionLiteListResponse.mArrCount != null) {
                            this.f20527c.f20529b = arrangementVersionLiteListResponse.mArrCount.intValue();
                        }
                        fetchDataCallback.mo6257a(arrayList, arrangementVersionLiteListResponse.mNext.intValue());
                        return;
                    }
                    fetchDataCallback.mo6256a();
                    this.f20527c.f20528a.m19846b((int) C1947R.string.profile_update_error);
                }
            }
        });
    }
}
