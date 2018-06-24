package com.smule.singandroid.adapters.songbook;

import android.content.res.Resources;
import android.widget.SectionIndexer;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.ArrangementManager$ArrangementVersionLiteListCallback;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionLiteListResponse;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.adapters.songbook.SongbookAmazingAdapter.AdapterInterface;
import com.smule.singandroid.customviews.SongbookSortSelector.SortType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

public class SongbookCommunityAdapter extends SongbookPaginatedAdapter {
    static long f20597g;
    boolean f20598f = false;
    final long f20599h = 600000;
    private AtomicBoolean f20600r = new AtomicBoolean(false);
    private volatile int f20601s;
    private AtomicBoolean f20602t;

    class MostRecentSectionIndexer implements SectionIndexer {
        String[] f20578a;
        final /* synthetic */ SongbookCommunityAdapter f20579b;
        private final int f20580c = 0;
        private final int f20581d = 1;
        private final int f20582e = Integer.MAX_VALUE;
        private long f20583f = ((System.currentTimeMillis() / 1000) - ((long) (this.f20586i * 60)));
        private int f20584g = Integer.MAX_VALUE;
        private boolean f20585h;
        private int f20586i = SingServerValues.m21693n();

        public MostRecentSectionIndexer(SongbookCommunityAdapter songbookCommunityAdapter) {
            this.f20579b = songbookCommunityAdapter;
            m22169a();
            m22170b();
        }

        protected void m22169a() {
            String quantityString;
            if (this.f20584g <= 0) {
                this.f20578a = null;
            }
            String[] strArr = new String[2];
            Resources resources = this.f20579b.o.mo6659a().getResources();
            if (this.f20586i < 60) {
                int i = this.f20586i;
                if (i == 0) {
                    i = 1;
                }
                quantityString = resources.getQuantityString(C1947R.plurals.songbook_community_new_songs_header_minute_plural, i, new Object[]{Integer.valueOf(i)});
            } else if (this.f20586i < 1440) {
                quantityString = resources.getQuantityString(C1947R.plurals.songbook_community_new_songs_header_hour_plural, this.f20586i / 60, new Object[]{Integer.valueOf(this.f20586i / 60)});
            } else {
                quantityString = resources.getQuantityString(C1947R.plurals.songbook_community_new_songs_header_day_plural, this.f20586i / 1440, new Object[]{Integer.valueOf(this.f20586i / 1440)});
            }
            strArr[0] = quantityString;
            strArr[1] = resources.getString(C1947R.string.core_all);
            this.f20578a = strArr;
        }

        public void m22170b() {
            if (this.f20584g == Integer.MAX_VALUE && this.f20579b.l != null) {
                int i = 0;
                while (i < this.f20579b.l.size()) {
                    if (((ArrangementVersionLiteEntry) this.f20579b.l.get(i)).f17623a.arrCreatedAt >= this.f20583f) {
                        this.f20585h = true;
                        i++;
                    } else if (i == 0) {
                        this.f20584g = Integer.MAX_VALUE;
                        this.f20585h = false;
                        return;
                    } else {
                        this.f20584g = i;
                        return;
                    }
                }
            }
        }

        public Object[] getSections() {
            return this.f20585h ? this.f20578a : null;
        }

        public int getPositionForSection(int i) {
            if (i == 0) {
                return 0;
            }
            return this.f20584g;
        }

        public int getSectionForPosition(int i) {
            if (i >= this.f20584g) {
                return 1;
            }
            return 0;
        }
    }

    public SongbookCommunityAdapter(AdapterInterface adapterInterface) {
        super(adapterInterface);
    }

    protected void mo6710r() {
        super.mo6710r();
        this.j.f23512a = new ArrayList();
        this.e = null;
    }

    protected void mo6708c(final boolean z) {
        if (SingApplication.f) {
            int i;
            if (z) {
                i = 0;
            } else {
                i = c() * 10;
            }
            if (!this.f20600r.get() || i != this.f20601s) {
                if (this.l == null) {
                    this.l = new ArrayList();
                }
                if (this.f20598f || this.j.f23512a == null || this.j.f23512a.size() <= 0 || f20597g + 600000 <= System.currentTimeMillis()) {
                    this.f20598f = true;
                    if (z || this.l.isEmpty()) {
                        m22198t();
                    }
                    if (this.f20602t != null) {
                        synchronized (this.f20602t) {
                            this.f20602t.set(true);
                        }
                    }
                    final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                    this.f20602t = atomicBoolean;
                    this.f20600r.set(true);
                    this.f20601s = i;
                    ArrangementManager.a().a(Integer.valueOf(i), Integer.valueOf(10), this.m.m23491a(), new ArrangementManager$ArrangementVersionLiteListCallback(this) {
                        final /* synthetic */ SongbookCommunityAdapter f20577d;

                        public void handleResponse(ArrangementVersionLiteListResponse arrangementVersionLiteListResponse) {
                            synchronized (atomicBoolean) {
                                this.f20577d.f20600r.set(false);
                                if (atomicBoolean.get()) {
                                } else if (arrangementVersionLiteListResponse.a()) {
                                    if (z) {
                                        this.f20577d.l.clear();
                                        this.f20577d.m22172a();
                                        this.f20577d.f();
                                    }
                                    Iterator it = arrangementVersionLiteListResponse.mArrangementVersionLites.iterator();
                                    while (it.hasNext()) {
                                        this.f20577d.l.add(SongbookEntry.m18745a((ArrangementVersionLite) it.next(), true));
                                    }
                                    if (arrangementVersionLiteListResponse.mArrangementVersionLites.size() <= 0) {
                                        this.f20577d.i = true;
                                    }
                                    if (i == 0) {
                                        SongbookCommunityAdapter.f20597g = System.currentTimeMillis();
                                    }
                                    this.f20577d.j.f23512a = this.f20577d.l;
                                    this.f20577d.b();
                                    if (this.f20577d.i) {
                                        this.f20577d.e();
                                    } else {
                                        this.f20577d.notifyDataSetChanged();
                                    }
                                    this.f20577d.m22200v();
                                } else {
                                    this.f20577d.m22199u();
                                }
                            }
                        }
                    });
                    return;
                }
                this.f20598f = true;
                this.l = this.j.f23512a;
                do {
                    b();
                } while (c() < this.l.size() / 10);
                notifyDataSetChanged();
            }
        }
    }

    public void notifyDataSetChanged() {
        if (this.m == SortType.MOST_RECENT && (this.e instanceof MostRecentSectionIndexer)) {
            ((MostRecentSectionIndexer) this.e).m22170b();
        }
        super.notifyDataSetChanged();
    }

    public void mo6694a(SortType sortType) {
        super.mo6694a(sortType);
        if (sortType == SortType.MOST_RECENT) {
            this.e = new MostRecentSectionIndexer(this);
        } else {
            this.e = null;
        }
    }
}
