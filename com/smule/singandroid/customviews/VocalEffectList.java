package com.smule.singandroid.customviews;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.list_items.VocalEffectListItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class VocalEffectList extends HorizontalScrollView {
    private int f22080a = -1;
    private boolean f22081b;
    private ArrayList<VocalEffect> f22082c = new ArrayList();
    private ArrayList<VocalEffectListItem> f22083d = new ArrayList();
    private OnItemClickListener f22084e;
    private int f22085f;
    private int f22086g;
    private float f22087h;
    private float f22088i;
    private boolean f22089j;

    public static abstract class OnItemClickListener {
        public abstract void mo6578a(String str, float f, float f2, boolean z);

        public abstract void mo6579a(String str, int i, float f, float f2, boolean z);
    }

    private class ScrollAnimation extends Animation {
        final /* synthetic */ VocalEffectList f22076a;
        private int f22077b;
        private int f22078c;
        private int f22079d;

        public ScrollAnimation(VocalEffectList vocalEffectList, int i) {
            this.f22076a = vocalEffectList;
            this.f22078c = i;
            this.f22077b = vocalEffectList.getScrollX();
            this.f22079d = vocalEffectList.getScrollY();
            setDuration(200);
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            this.f22076a.smoothScrollTo((int) ((((float) (this.f22078c - this.f22077b)) * f) + ((float) this.f22077b)), this.f22079d);
        }
    }

    public VocalEffectList(Context context) {
        super(context);
    }

    public VocalEffectList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public String m23545a(LinearLayout linearLayout, Collection<VocalEffect> collection, String str) {
        float f;
        float f2 = 0.0f;
        VocalEffect b = VocalEffect.m21979b(str);
        if (b == null || !b.m21984b()) {
            f = 0.0f;
        } else {
            f = VocalEffect.m21974a(getContext(), str);
            f2 = VocalEffect.m21978b(getContext(), str);
        }
        return m23543a(linearLayout, collection, str, null, f, f2);
    }

    private String m23543a(LinearLayout linearLayout, Collection<VocalEffect> collection, String str, String str2, float f, float f2) {
        Object b;
        this.f22082c = VocalEffect.m21981d();
        if (collection != null) {
            this.f22082c.removeAll(collection);
        }
        if (str2 != null) {
            b = VocalEffect.m21979b(str2);
        } else {
            b = null;
        }
        if (b != null) {
            this.f22082c.add(0, b);
        }
        VocalEffect vocalEffect = null;
        if (!TextUtils.isEmpty(str)) {
            vocalEffect = VocalEffect.m21979b(str);
            if (!(vocalEffect == null || collection == null || !collection.contains(vocalEffect))) {
                vocalEffect = null;
            }
        }
        if (vocalEffect == null) {
            vocalEffect = VocalEffect.m21975a();
        }
        if (vocalEffect == null) {
            for (int i = 0; i < this.f22082c.size(); i++) {
                VocalEffect vocalEffect2 = (VocalEffect) this.f22082c.get(i);
                if (!vocalEffect2.m21987e() && !vocalEffect2.equals(VocalEffect.NONE)) {
                    Object obj = vocalEffect2;
                    break;
                }
            }
        }
        VocalEffect vocalEffect3 = vocalEffect;
        this.f22087h = f;
        this.f22088i = f2;
        for (int i2 = 0; i2 < this.f22082c.size(); i2++) {
            VocalEffect vocalEffect4 = (VocalEffect) this.f22082c.get(i2);
            View a = VocalEffectListItem.m24525a(getContext());
            if (i2 != 0 || b == null) {
                a.m24543a(vocalEffect4, i2, this, false, this.f22087h, this.f22088i);
            } else {
                a.m24543a(vocalEffect4, i2, this, true, this.f22087h, this.f22088i);
                this.f22086g = i2;
            }
            this.f22083d.add(a);
            linearLayout.addView(a, i2);
            if (vocalEffect4.equals(obj) && str2 == null) {
                this.f22086g = i2;
            }
        }
        boolean z = this.f22086g == 0 && b != null;
        this.f22089j = z;
        setSmoothScrollingEnabled(true);
        this.f22085f = (int) getResources().getDimension(C1947R.dimen.review_fx_size);
        return ((VocalEffect) this.f22082c.get(this.f22086g)).toString();
    }

    public int getNumFX() {
        return this.f22082c.size();
    }

    public void m23547a(int i, float f, float f2, boolean z) {
        if (this.f22084e != null) {
            this.f22084e.mo6578a(m23544b(i).m21986c(), f, f2, z);
        }
    }

    public void setClickable(boolean z) {
        super.setClickable(z);
        Iterator it = this.f22083d.iterator();
        while (it.hasNext()) {
            ((VocalEffectListItem) it.next()).setClickable(z);
        }
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        Iterator it = this.f22083d.iterator();
        while (it.hasNext()) {
            ((VocalEffectListItem) it.next()).setEnabled(z);
        }
    }

    public void m23549a(boolean z) {
        m23542a(this.f22080a).setBusy(z);
    }

    public void m23546a(int i, float f, float f2) {
        int i2;
        if (i == -1) {
            i2 = this.f22086g;
        } else {
            i2 = i;
        }
        m23548a(i2, f, f2, this.f22089j, false);
        this.f22080a = i2;
    }

    public void m23548a(int i, float f, float f2, boolean z, boolean z2) {
        if (isEnabled() && this.f22080a != i) {
            Animation animation = getAnimation();
            if (animation == null || animation.hasEnded()) {
                int dimension;
                boolean z3 = this.f22081b;
                this.f22081b = z;
                int i2 = this.f22080a;
                Animation animationSet = new AnimationSet(true);
                int scrollX = getScrollX();
                int i3 = 0;
                Object obj = (i2 == -1 || i2 >= i || !m23544b(i2).m21984b() || z3) ? null : 1;
                Object obj2 = (!m23544b(i).m21984b() || z) ? null : 1;
                if (obj != null) {
                    i3 = ((int) getContext().getResources().getDimension(getContext().getResources().getIdentifier("review_fx_" + m23544b(i2).m21986c() + "_width", "dimen", getContext().getPackageName()))) - this.f22085f;
                }
                if (obj2 != null) {
                    dimension = ((int) getContext().getResources().getDimension(getContext().getResources().getIdentifier("review_fx_" + m23544b(i).m21986c() + "_width", "dimen", getContext().getPackageName()))) - this.f22085f;
                } else {
                    dimension = 0;
                }
                int dimension2 = (int) getResources().getDimension(C1947R.dimen.review_fx_size);
                int width = getWidth();
                i2 = m23542a(i).getLeft();
                int right = m23542a(i).getRight();
                int i4 = i2 - i3;
                dimension += right - i3;
                i3 = getScrollX();
                int scrollX2 = getScrollX() + width;
                dimension = (i2 < i3 || i4 < i3) ? i4 - (dimension2 / 4) : (right > scrollX2 || dimension > scrollX2) ? (dimension - width) + (dimension2 / 4) : scrollX;
                if (dimension != getScrollX()) {
                    animationSet.addAnimation(new ScrollAnimation(this, dimension));
                }
                if (this.f22080a != -1) {
                    animation = m23542a(this.f22080a).m24544b();
                    if (animation != null) {
                        animationSet.addAnimation(animation);
                    }
                }
                animation = m23542a(i).m24542a();
                if (animation != null) {
                    animationSet.addAnimation(animation);
                }
                this.f22080a = i;
                animationSet.setDuration(200);
                animationSet.setInterpolator(new DecelerateInterpolator());
                startAnimation(animationSet);
                if (this.f22084e != null) {
                    this.f22084e.mo6579a(m23544b(i).m21986c(), i, f, f2, z2);
                }
            }
        }
    }

    private VocalEffectListItem m23542a(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.f22083d.size()) {
            i = this.f22083d.size() - 1;
        }
        return (VocalEffectListItem) this.f22083d.get(i);
    }

    private VocalEffect m23544b(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i >= this.f22082c.size()) {
            i = this.f22082c.size() - 1;
        }
        return (VocalEffect) this.f22082c.get(i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.f22084e = onItemClickListener;
    }
}
