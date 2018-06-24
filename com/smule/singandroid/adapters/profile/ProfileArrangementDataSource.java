/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 */
package com.smule.singandroid.adapters.profile;

import android.os.Parcel;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.ResponseInterface;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.CursorModel;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ParcelUtils;
import com.smule.singandroid.profile.ProfileArrangementContainer;
import com.smule.singandroid.profile.ProfileFragment;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProfileArrangementDataSource
extends MagicDataSource<ProfileArrangementContainer, MagicDataSource.HybridPaginationTracker> {
    private static final String b = ProfileArrangementDataSource.class.getName();
    ProfileFragment a;
    private int l;
    private int m;
    private AtomicBoolean n;

    /*
     * Enabled aggressive block sorting
     */
    public ProfileArrangementDataSource(ProfileFragment profileFragment) {
        String string2 = ProfileArrangementDataSource.class.getSimpleName() + ":" + profileFragment.F().accountId;
        MagicDataSource.HybridPaginationTracker hybridPaginationTracker = profileFragment.F().d() ? new MagicDataSource.HybridPaginationTracker(CursorModel.a()) : new MagicDataSource.HybridPaginationTracker(0);
        super(string2, hybridPaginationTracker);
        this.a = profileFragment;
        if (this.n == null) {
            this.n = new AtomicBoolean(false);
        }
        if (!this.c) {
            this.n.set(false);
        }
    }

    private void a(MagicDataSource.CursorPaginationTracker cursorPaginationTracker, final int n, final MagicDataSource.FetchDataCallback<ProfileArrangementContainer, MagicDataSource.HybridPaginationTracker> fetchDataCallback) {
        final int n2 = this.a.P();
        com.smule.android.network.managers.ArrangementManager.a().a(cursorPaginationTracker.a().next, n, new ResponseInterface<ArrangementManager.SongBookmarkListResponse>(){

            @Override
            public void handleResponse(ArrangementManager.SongBookmarkListResponse songBookmarkListResponse) {
                if (ProfileArrangementDataSource.this.a == null || !ProfileArrangementDataSource.this.a.a(n2)) {
                    fetchDataCallback.a();
                    return;
                }
                if (!songBookmarkListResponse.a()) {
                    fetchDataCallback.a();
                    ProfileArrangementDataSource.this.a.b(2131297210);
                    return;
                }
                if (songBookmarkListResponse.totalCount != null) {
                    ProfileArrangementDataSource.this.m = songBookmarkListResponse.totalCount;
                }
                ArrayList<ProfileArrangementContainer> arrayList = new ArrayList<ProfileArrangementContainer>();
                Iterator<ArrangementVersionLite> iterator = songBookmarkListResponse.songs.iterator();
                while (iterator.hasNext()) {
                    arrayList.add(new ProfileArrangementContainer((ArrangementVersionLiteEntry)SongbookEntry.a(iterator.next(), true), ProfileArrangementContainer.Type.a));
                }
                if (!songBookmarkListResponse.cursor.hasNext) {
                    ProfileArrangementDataSource.this.n.set(true);
                    if (arrayList.isEmpty()) {
                        ProfileArrangementDataSource.this.a(new MagicDataSource.OffsetPaginationTracker(), n, fetchDataCallback);
                        return;
                    }
                    fetchDataCallback.a(arrayList, new MagicDataSource.HybridPaginationTracker(0));
                    return;
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.HybridPaginationTracker(songBookmarkListResponse.cursor));
            }
        });
    }

    private void a(MagicDataSource.OffsetPaginationTracker offsetPaginationTracker, int n, final MagicDataSource.FetchDataCallback<ProfileArrangementContainer, MagicDataSource.HybridPaginationTracker> fetchDataCallback) {
        final int n2 = this.a.P();
        com.smule.android.network.managers.ArrangementManager.a().a(this.a.F().accountId, (int)offsetPaginationTracker.a(), n, new ArrangementManager(){

            @Override
            public void handleResponse(ArrangementManager.ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
                if (ProfileArrangementDataSource.this.a == null || !ProfileArrangementDataSource.this.a.a(n2)) {
                    return;
                }
                if (!arrangementVersionLiteListResponse.a()) {
                    fetchDataCallback.a();
                    ProfileArrangementDataSource.this.a.b(2131297210);
                    return;
                }
                ArrayList<ProfileArrangementContainer> arrayList = new ArrayList<ProfileArrangementContainer>();
                Iterator<ArrangementVersionLite> iterator = arrangementVersionLiteListResponse.mArrangementVersionLites.iterator();
                while (iterator.hasNext()) {
                    arrayList.add(new ProfileArrangementContainer((ArrangementVersionLiteEntry)SongbookEntry.a(iterator.next(), true), ProfileArrangementContainer.Type.b));
                }
                if (arrangementVersionLiteListResponse.mArrCount != null) {
                    ProfileArrangementDataSource.this.l = arrangementVersionLiteListResponse.mArrCount;
                }
                fetchDataCallback.a(arrayList, new MagicDataSource.HybridPaginationTracker(arrangementVersionLiteListResponse.mNext));
            }
        });
    }

    @Override
    public int a() {
        return 10;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public Future<?> a(MagicDataSource.HybridPaginationTracker hybridPaginationTracker, int n, MagicDataSource.FetchDataCallback<ProfileArrangementContainer, MagicDataSource.HybridPaginationTracker> fetchDataCallback) {
        if (this.a.F().d() && !this.n.get()) {
            this.a(new MagicDataSource.CursorPaginationTracker((CursorModel)hybridPaginationTracker.d()), n, fetchDataCallback);
            do {
                return null;
                break;
            } while (true);
        }
        this.a(new MagicDataSource.OffsetPaginationTracker((int)((Integer)hybridPaginationTracker.d())), n, fetchDataCallback);
        return null;
    }

    @Override
    protected void a(Parcel parcel) {
        super.a(parcel);
        parcel.writeInt(this.l);
        parcel.writeInt(this.m);
        ParcelUtils.a(parcel, this.n.get());
    }

    @Override
    protected void b(Parcel parcel) {
        super.b(parcel);
        this.l = parcel.readInt();
        this.m = parcel.readInt();
        if (this.n == null) {
            this.n = new AtomicBoolean(false);
        }
        this.n.set(ParcelUtils.a(parcel));
    }

    public int e() {
        return this.l;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void r() {
        super.r();
        if (this.a.F().d()) {
            com.smule.android.network.managers.ArrangementManager.a().a(false);
            this.j = new MagicDataSource.HybridPaginationTracker(CursorModel.a());
        } else {
            this.j = new MagicDataSource.HybridPaginationTracker(0);
        }
        this.n.set(false);
    }

    public int w() {
        return this.m;
    }

}

