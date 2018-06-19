package com.smule.android.magicui.lists;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.magicui.lists.adapters.MagicDataSource.DataSourceObserverObject;
import com.smule.android.magicui.lists.adapters.RecycledViewPool;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.WeakHashMap;

class MagicViewPagerAdapterWrapper extends PagerAdapter {
    MagicAdapter f16406a;
    protected WeakHashMap<View, Integer> f16407b = new WeakHashMap();
    protected RecycledViewPool f16408c;
    private Parcelable f16409d;

    class C35351 extends DataSourceObserverObject {
        final /* synthetic */ MagicViewPagerAdapterWrapper f16404a;

        C35351(MagicViewPagerAdapterWrapper magicViewPagerAdapterWrapper) {
            this.f16404a = magicViewPagerAdapterWrapper;
        }

        public void mo6254c(MagicDataSource magicDataSource) {
            this.f16404a.notifyDataSetChanged();
        }
    }

    public MagicViewPagerAdapterWrapper(MagicAdapter magicAdapter) {
        this.f16406a = magicAdapter;
        this.f16406a.mo6858a(new C35351(this));
    }

    public int getCount() {
        return (this.f16406a.m18047c() ? 1 : 0) + this.f16406a.m18048d();
    }

    public void m18022a(RecycledViewPool recycledViewPool) {
        this.f16408c = recycledViewPool;
    }

    public final View m18019a(ViewGroup viewGroup, int i) {
        View a;
        switch (this.f16406a.m18026a().m17659i()) {
            case NONE:
                throw new RuntimeException("DataState was NONE");
            case HAS_DATA:
                if (i >= this.f16406a.m18048d()) {
                    a = this.f16406a.mo6857a(viewGroup);
                    a.setTag("loading");
                    return a;
                }
                break;
            case LOADING_FIRST_PAGE:
                return this.f16406a.mo6464b(viewGroup);
            case LOADING_FIRST_PAGE_FAILED:
                return this.f16406a.mo6459c(viewGroup);
            case FIRST_PAGE_EMPTY:
                return this.f16406a.mo6460d(viewGroup);
        }
        int c = this.f16406a.mo6441c(i);
        a = null;
        if (this.f16408c != null) {
            a = this.f16408c.m18076a(c);
        }
        a = this.f16406a.m18025a(viewGroup, a, i);
        if (a == null) {
            return a;
        }
        m18016a(a, i, c);
        return a;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a = m18019a(viewGroup, i);
        if (a != null) {
            viewGroup.addView(a);
            this.f16407b.put(a, Integer.valueOf(i));
        }
        return a;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj != null) {
            View view = (View) obj;
            viewGroup.removeView(view);
            this.f16407b.remove(view);
            if (i < this.f16406a.m18048d()) {
                int c = this.f16406a.mo6441c(i);
                if (this.f16408c != null) {
                    this.f16408c.m18077a(view, c);
                }
            }
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m18017b();
    }

    public String m18020a(int i) {
        return "page" + i;
    }

    private void m18017b() {
        List<Entry> arrayList = new ArrayList();
        for (Entry entry : this.f16407b.entrySet()) {
            int intValue = ((Integer) entry.getValue()).intValue();
            if (intValue < this.f16406a.m18048d()) {
                m18016a((View) entry.getKey(), ((Integer) entry.getValue()).intValue(), this.f16406a.mo6441c(intValue));
            } else if (!this.f16406a.m18047c()) {
                arrayList.add(entry);
            }
        }
        for (Entry entry2 : arrayList) {
            destroyItem((ViewGroup) ((View) entry2.getKey()).getParent(), ((Integer) entry2.getValue()).intValue(), entry2.getKey());
        }
        if (arrayList.size() != 0) {
            notifyDataSetChanged();
        }
    }

    private void m18016a(View view, int i, int i2) {
        this.f16406a.mo6419a(view, i, i2);
        view.setTag(m18020a(i));
    }

    public void m18021a(Parcelable parcelable) {
        this.f16409d = parcelable;
    }

    public Parcelable m18018a() {
        return this.f16409d;
    }
}
