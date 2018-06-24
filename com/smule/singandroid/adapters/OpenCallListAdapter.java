package com.smule.singandroid.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager$PerformancesResponseCallback;
import com.smule.android.network.managers.PerformanceManager.PerformancesResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class OpenCallListAdapter extends BaseAdapter {
    private static final String f20512j = OpenCallListAdapter.class.getName();
    int f20513a = 1;
    int f20514b = 1;
    int f20515c = 10;
    boolean f20516d = false;
    HasMorePagesListener f20517e;
    SongbookEntry f20518f;
    boolean f20519g = false;
    String f20520h;
    boolean f20521i = false;
    private ArrayList<PerformanceV2> f20522k;
    private OpenCallListAdapterUIDelegate f20523l;
    private PerformanceManager$PerformancesResponseCallback f20524m = new C42111(this);

    public interface OpenCallListAdapterUIDelegate {
        View mo6501a(View view, ViewGroup viewGroup, PerformanceV2 performanceV2, boolean z);
    }

    class C42111 implements PerformanceManager$PerformancesResponseCallback {
        final /* synthetic */ OpenCallListAdapter f20508a;

        C42111(OpenCallListAdapter openCallListAdapter) {
            this.f20508a = openCallListAdapter;
        }

        public void handleResponse(PerformancesResponse performancesResponse) {
            if (performancesResponse.a()) {
                int i;
                if (this.f20508a.f20518f.m18772r()) {
                    Iterator it = performancesResponse.mPerformances.iterator();
                    i = 0;
                    while (it.hasNext()) {
                        int i2;
                        PerformanceV2 performanceV2 = (PerformanceV2) it.next();
                        if (performanceV2.n()) {
                            this.f20508a.f20522k.add(performanceV2);
                            i2 = i + 1;
                        } else {
                            i2 = i;
                        }
                        i = i2;
                    }
                } else {
                    this.f20508a.f20522k.addAll(performancesResponse.mPerformances);
                    i = performancesResponse.mPerformances.size();
                }
                this.f20508a.notifyDataSetChanged();
                if (i <= 0) {
                    this.f20508a.m22045d();
                } else {
                    this.f20508a.m22043b();
                }
            } else {
                this.f20508a.m22045d();
            }
            this.f20508a.f20521i = false;
        }
    }

    class C42122 implements Runnable {
        final /* synthetic */ ListView f20509a;
        final /* synthetic */ Collection f20510b;
        final /* synthetic */ OpenCallListAdapter f20511c;

        public void run() {
            int childCount = this.f20509a.getChildCount();
            Log.b(OpenCallListAdapter.f20512j, "notifyPlaybackChanged - performanceKeysToRefresh = " + Arrays.toString(this.f20510b.toArray()));
            for (int i = 0; i < childCount; i++) {
                View childAt = this.f20509a.getChildAt(i);
                if (childAt != null) {
                    int positionForView = this.f20509a.getPositionForView(childAt);
                    if (positionForView >= this.f20511c.f20522k.size()) {
                        Log.d(OpenCallListAdapter.f20512j, "notifyPlaybackChanged - adapterId exceeds mOpenCalls bounds; continuing to next element");
                    } else {
                        String str = ((PerformanceV2) this.f20511c.getItem(positionForView)).performanceKey;
                        for (String str2 : this.f20510b) {
                            if (str2 == null || !str2.equals(str)) {
                                Log.b(OpenCallListAdapter.f20512j, "At view (i = " + i + ") with performanceKey = " + str + ", does not match any key to invalidate.");
                            } else {
                                Log.b(OpenCallListAdapter.f20512j, "Refreshing view (i = " + i + ") with performanceKey = " + str);
                                this.f20511c.getView(positionForView, childAt, this.f20509a);
                            }
                        }
                    }
                }
            }
        }
    }

    public interface HasMorePagesListener {
        void mo6851a();

        void mo6852b();
    }

    public void m22041a(HasMorePagesListener hasMorePagesListener) {
        this.f20517e = hasMorePagesListener;
    }

    public OpenCallListAdapter(OpenCallListAdapterUIDelegate openCallListAdapterUIDelegate, ArrayList<PerformanceV2> arrayList) {
        if (openCallListAdapterUIDelegate == null) {
            Log.e(f20512j, "No UI delegate set on PerformanceListAdapter");
        }
        this.f20523l = openCallListAdapterUIDelegate;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        this.f20522k = arrayList;
        m22039a((this.f20522k.size() / this.f20513a) + 1);
        m22038a();
    }

    public void m22040a(SongbookEntry songbookEntry) {
        this.f20518f = songbookEntry;
        this.f20520h = songbookEntry.mo6289c();
    }

    public void m22042a(boolean z) {
        this.f20519g = z;
    }

    public void m22039a(int i) {
        this.f20514b = i;
    }

    public void m22038a() {
        this.f20513a = this.f20514b;
    }

    public void m22043b() {
        this.f20513a++;
    }

    public int getCount() {
        if (this.f20522k != null) {
            return this.f20522k.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        if (i < this.f20522k.size()) {
            return this.f20522k.get(i);
        }
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == getCount() - 1 && this.f20516d) {
            m22036b(this.f20513a);
        }
        return this.f20523l.mo6501a(view, viewGroup, (PerformanceV2) getItem(i), i == 0);
    }

    private void m22036b(int i) {
        if (!this.f20521i) {
            m22046e();
            this.f20521i = true;
            if (this.f20518f.m18772r()) {
                PerformanceManager.a().b(this.f20520h, null, Integer.valueOf((i - 1) * this.f20515c), Integer.valueOf(this.f20515c), Boolean.valueOf(this.f20519g), this.f20524m);
            } else {
                PerformanceManager.a().a(this.f20520h, null, Integer.valueOf((i - 1) * this.f20515c), Integer.valueOf(this.f20515c), Boolean.valueOf(this.f20519g), this.f20524m);
            }
        }
    }

    public void m22044c() {
        m22038a();
        m22036b(this.f20513a);
    }

    public void m22045d() {
        this.f20516d = false;
        if (this.f20517e != null) {
            this.f20517e.mo6851a();
        }
    }

    public void m22046e() {
        this.f20516d = true;
        if (this.f20517e != null) {
            this.f20517e.mo6852b();
        }
    }
}
