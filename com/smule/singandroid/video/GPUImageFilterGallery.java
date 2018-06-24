package com.smule.singandroid.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.video.VideoFilterDatabase.FilterType;
import com.smule.singandroid.video.VideoFilterManager.VFXItem;
import com.smule.singandroid.video.VideoFilterManager.VFXItemSorter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFrameBuffer;
import jp.co.cyberagent.android.gpuimage.GPUImageVerticalSplitCombineFilter;

public class GPUImageFilterGallery extends GPUImageFilter {
    public static final String f25332a = GPUImageFilterGallery.class.getSimpleName();
    private int f25333A;
    private float f25334B;
    private Context f25335C;
    private List<VFXItem> f25336D;
    private GPUImageFilter f25337E;
    private GPUImageFilter f25338F;
    private GPUImageVerticalSplitCombineFilter f25339G;
    public boolean f25340b;
    public boolean f25341c;
    private FilterType[] f25342x;
    private HashMap<FilterType, GPUImageFilter> f25343y;
    private GPUImageFilter f25344z;

    public GPUImageFilterGallery(Context context, FilterType[] filterTypeArr, String str) {
        this(context, filterTypeArr);
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < this.f25336D.size(); i++) {
                if (((VFXItem) this.f25336D.get(i)).f25457a.equals(str)) {
                    this.f25333A = i;
                    return;
                }
            }
        }
    }

    public GPUImageFilterGallery(Context context, FilterType[] filterTypeArr) {
        this.f25343y = new HashMap();
        this.f25340b = false;
        this.f25341c = false;
        this.f25335C = context;
        this.f25342x = m26466a(filterTypeArr);
        this.f25336D = new ArrayList();
        for (FilterType vFXItem : this.f25342x) {
            this.f25336D.add(new VFXItem(vFXItem));
        }
        this.f25339G = new GPUImageVerticalSplitCombineFilter();
        this.f25333A = 0;
        this.f25334B = 0.0f;
    }

    public void mo6993a(GPUImageFilter gPUImageFilter) {
        this.f25339G.mo6993a(gPUImageFilter);
    }

    public void mo6994a(GPUImageFilter gPUImageFilter, int i) {
        this.f25339G.mo6994a(gPUImageFilter, i);
    }

    public void mo6991a() {
        Log.b(f25332a, "onInit");
        this.f25339G.m26456j();
        m26467t();
    }

    public void mo6996b() {
        this.f25339G.m26457k();
        for (GPUImageFilter k : this.f25343y.values()) {
            k.m26457k();
        }
        this.f25343y.clear();
        this.f25337E = null;
        this.f25338F = null;
        if (this.f25344z != null) {
            this.f25344z.m26457k();
            this.f25344z = null;
        }
        super.mo6996b();
    }

    public void mo6992a(int i, int i2) {
        super.mo6992a(i, i2);
        for (GPUImageFilter a : this.f25343y.values()) {
            a.mo6992a(i, i2);
        }
        if (this.f25344z != null) {
            this.f25344z.mo6992a(i, i2);
        }
    }

    public void mo6997b(int i, int i2) {
        super.mo6997b(i, i2);
        for (GPUImageFilter b : this.f25343y.values()) {
            b.mo6997b(i, i2);
        }
        if (this.f25344z != null) {
            this.f25344z.mo6997b(i, i2);
        }
    }

    public void mo6995a(GPUImageFrameBuffer gPUImageFrameBuffer, int i) {
        m26467t();
        if (this.f25337E != null && this.f25338F != null) {
            if (!this.f25340b || this.f25344z == null) {
                this.f25337E.mo6995a(gPUImageFrameBuffer, i);
                this.f25338F.mo6995a(gPUImageFrameBuffer, i);
                return;
            }
            this.f25344z.mo6995a(gPUImageFrameBuffer, i);
        }
    }

    public void mo6998c() {
        if (this.f25337E != null && this.f25338F != null) {
            if (!this.f25340b || this.f25344z == null) {
                this.f25337E.mo6998c();
                this.f25338F.mo6998c();
                return;
            }
            this.f25344z.mo6998c();
        }
    }

    @SuppressLint({"WrongCall"})
    public void mo6999d() {
        m26459m();
    }

    private void m26467t() {
        float f;
        float min = Math.min(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, Math.max(-1.0f, this.f25334B));
        int e = m26481e();
        int i = this.f25333A;
        int i2 = ((i + e) - 1) % e;
        int i3 = (i + 1) % e;
        float f2 = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - min;
        if (min < 0.0f) {
            i3 = i;
            f = -min;
            e = i2;
        } else {
            float f3 = f2;
            e = i;
            f = f3;
        }
        FilterType filterType = this.f25342x[e];
        if (!this.f25343y.containsKey(filterType)) {
            GPUImageFilter a = VideoFilterDatabase.m26567a(this.f25335C, filterType);
            this.f25343y.put(filterType, a);
            a.m26456j();
            a.mo6992a(this.l, this.m);
            a.mo6997b(this.j, this.k);
        }
        this.f25337E = (GPUImageFilter) this.f25343y.get(filterType);
        this.f25337E.mo7128r();
        this.f25337E.mo6994a(this.f25339G, 0);
        filterType = this.f25342x[i3];
        if (!this.f25343y.containsKey(filterType)) {
            GPUImageFilter a2 = VideoFilterDatabase.m26567a(this.f25335C, filterType);
            this.f25343y.put(filterType, a2);
            a2.mo6994a(this.f25339G, 1);
            a2.m26456j();
            a2.mo6992a(this.l, this.m);
            a2.mo6997b(this.j, this.k);
        }
        this.f25338F = (GPUImageFilter) this.f25343y.get(filterType);
        this.f25338F.mo7128r();
        this.f25338F.mo6994a(this.f25339G, 1);
        if (this.f25344z == null && this.f25341c) {
            this.f25344z = VideoFilterDatabase.m26570b();
            this.f25344z.m26456j();
            this.f25344z.mo6992a(this.l, this.m);
            this.f25344z.mo6997b(this.l, this.m);
        }
        m26468u();
        this.f25339G.m27479a(f);
    }

    private void m26468u() {
        if (this.f25344z != null && this.f25337E != null && this.f25338F != null) {
            this.f25344z.mo7128r();
            this.f25344z.mo6993a(this.f25337E);
            this.f25344z.mo6993a(this.f25338F);
        }
    }

    public void m26476a(boolean z) {
        this.f25340b = z;
    }

    public int m26481e() {
        return this.f25342x.length;
    }

    public void mo7000f() {
        this.f25333A = (this.f25333A + 1) % m26481e();
        this.f25334B = 0.0f;
    }

    public void mo7001g() {
        this.f25333A = ((this.f25333A + m26481e()) - 1) % m26481e();
        this.f25334B = 0.0f;
    }

    public void m26471a(float f) {
        this.f25334B = f;
    }

    public List<VFXItem> m26484h() {
        List<VFXItem> arrayList = new ArrayList();
        for (VFXItem vFXItem : this.f25336D) {
            if (VideoFilterManager.m26578c(vFXItem.f25457a)) {
                arrayList.add(vFXItem);
            }
        }
        return arrayList;
    }

    private FilterType[] m26466a(FilterType[] filterTypeArr) {
        ArrayList arrayList = new ArrayList();
        for (FilterType filterType : filterTypeArr) {
            if (VideoFilterManager.m26578c(VideoFilterManager.m26574a(filterType))) {
                arrayList.add(filterType);
            }
        }
        if (arrayList.isEmpty()) {
            return new FilterType[]{FilterType.NORMAL};
        }
        Collections.sort(arrayList, new VFXItemSorter());
        return (FilterType[]) arrayList.toArray(new FilterType[arrayList.size()]);
    }

    public int m26469a(String str) {
        for (int i = 0; i < this.f25342x.length; i++) {
            if (VideoFilterManager.m26574a(this.f25342x[i]).equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public boolean m26485i() {
        return this.f25342x.length > 1;
    }
}
