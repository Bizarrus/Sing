/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.TypedArray
 *  android.database.DataSetObserver
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$Style
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.support.annotation.NonNull
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$BaseSavedState
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.HorizontalScrollView
 *  android.widget.ImageButton
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.R;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.Locale;

public class PagerSlidingTabStrip
extends HorizontalScrollView {
    private static final int[] h = new int[]{16842901, 16842904};
    protected LinearLayout.LayoutParams a;
    protected LinearLayout.LayoutParams b;
    public ViewPager.OnPageChangeListener c;
    protected LinearLayout d;
    protected ViewPager e;
    protected boolean f;
    protected int g;
    private final PageListener i;
    private int j;
    private int k;
    private float l;
    private Paint m;
    private Paint n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private int x;
    private Locale y;
    private WeakListener.OnGlobalLayoutListener z;

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.i = new PageListener();
        this.k = 0;
        this.l = 0.0f;
        this.o = -10066330;
        this.p = 436207616;
        this.q = 436207616;
        this.f = false;
        this.r = 52;
        this.s = 8;
        this.t = 2;
        this.u = 12;
        this.g = 24;
        this.v = 1;
        this.w = 0;
        this.x = 0;
        this.setFillViewport(true);
        this.setWillNotDraw(false);
        this.d = new LinearLayout(context);
        this.d.setOrientation(0);
        this.d.setLayoutParams((ViewGroup.LayoutParams)new FrameLayout.LayoutParams(-1, -1));
        this.addView((View)this.d);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        this.r = (int)TypedValue.applyDimension((int)1, (float)this.r, (DisplayMetrics)displayMetrics);
        this.s = (int)TypedValue.applyDimension((int)1, (float)this.s, (DisplayMetrics)displayMetrics);
        this.t = (int)TypedValue.applyDimension((int)1, (float)this.t, (DisplayMetrics)displayMetrics);
        this.u = (int)TypedValue.applyDimension((int)1, (float)this.u, (DisplayMetrics)displayMetrics);
        this.v = (int)TypedValue.applyDimension((int)1, (float)this.v, (DisplayMetrics)displayMetrics);
        this.g = (int)TypedValue.applyDimension((int)1, (float)this.g, (DisplayMetrics)displayMetrics);
        context.obtainStyledAttributes(attributeSet, h).recycle();
        context = context.obtainStyledAttributes(attributeSet, R.PagerSlidingTabStrip);
        this.o = context.getColor(0, this.o);
        this.p = context.getColor(1, this.p);
        this.q = context.getColor(2, this.q);
        this.s = context.getDimensionPixelSize(3, this.s);
        this.t = context.getDimensionPixelSize(4, this.t);
        this.u = context.getDimensionPixelSize(5, this.u);
        this.v = context.getDimensionPixelSize(6, this.v);
        this.g = context.getDimensionPixelSize(7, this.g);
        this.f = context.getBoolean(9, this.f);
        this.r = context.getDimensionPixelSize(8, this.r);
        this.w = context.getResourceId(10, this.w);
        context.recycle();
        this.m = new Paint();
        this.m.setAntiAlias(true);
        this.m.setStyle(Paint.Style.FILL);
        this.n = new Paint();
        this.n.setAntiAlias(true);
        this.n.setStrokeWidth((float)this.v);
        this.a = new LinearLayout.LayoutParams(-2, -1);
        this.b = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.y == null) {
            this.y = this.getResources().getConfiguration().locale;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(final int n, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                PagerSlidingTabStrip.this.e.setCurrentItem(n);
            }
        });
        view.setPadding(this.g, 0, this.g, 0);
        LinearLayout linearLayout = this.d;
        LinearLayout.LayoutParams layoutParams = this.f ? this.b : this.a;
        linearLayout.addView(view, n, (ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(int n, int n2) {
        block7 : {
            block4 : {
                block6 : {
                    int n3;
                    block5 : {
                        if (this.j == 0) break block4;
                        n3 = this.d.getChildAt(n).getLeft() + n2;
                        if (n > 0) break block5;
                        n = n3;
                        if (n2 <= 0) break block6;
                    }
                    n = n3 - this.r;
                }
                if (n != this.x) break block7;
            }
            return;
        }
        this.x = n;
        this.scrollTo(n, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a() {
        this.d.removeAllViews();
        this.j = this.e.getAdapter().getCount();
        for (int i = 0; i < this.j; ++i) {
            if (this.e.getAdapter() instanceof IconTabProvider) {
                this.a(i, ((IconTabProvider)this.e.getAdapter()).a(i));
                continue;
            }
            this.a(i, this.e.getAdapter().getPageTitle(i).toString());
        }
        if (this.z != null) {
            LayoutUtils.b((View)this, (WeakListener.OnGlobalLayoutListener)this.z);
        }
        this.z = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                LayoutUtils.b((View)PagerSlidingTabStrip.this, (WeakListener.OnGlobalLayoutListener)PagerSlidingTabStrip.this.z);
                PagerSlidingTabStrip.this.k = PagerSlidingTabStrip.this.e.getCurrentItem();
                PagerSlidingTabStrip.this.b(PagerSlidingTabStrip.this.k, 0);
            }
        });
        LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)this.z);
    }

    protected void a(int n, int n2) {
        ImageButton imageButton = new ImageButton(this.getContext());
        imageButton.setImageResource(n2);
        this.a(n, (View)imageButton);
    }

    protected void a(int n, String string2) {
        TextView textView = (TextView)LayoutInflater.from((Context)this.getContext()).inflate(2130903433, null);
        textView.setText((CharSequence)string2);
        this.a(n, (View)textView);
    }

    public View getCurrentTab() {
        return this.d.getChildAt(this.k);
    }

    public int getDividerColor() {
        return this.q;
    }

    public int getDividerPadding() {
        return this.u;
    }

    public int getIndicatorColor() {
        return this.o;
    }

    public int getIndicatorHeight() {
        return this.s;
    }

    public int getScrollOffset() {
        return this.r;
    }

    public boolean getShouldExpand() {
        return this.f;
    }

    public int getTabPaddingLeftRight() {
        return this.g;
    }

    public int getUnderlineColor() {
        return this.p;
    }

    public int getUnderlineHeight() {
        return this.t;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.z != null) {
            LayoutUtils.b((View)this, (WeakListener.OnGlobalLayoutListener)this.z);
            this.z = null;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onDraw(Canvas canvas) {
        int n;
        View view;
        block8 : {
            block7 : {
                super.onDraw(canvas);
                if (this.isInEditMode() || this.j == 0) break block7;
                n = this.getHeight();
                this.m.setColor(this.o);
                view = this.d.getChildAt(this.k);
                float f = view.getLeft();
                float f2 = view.getRight();
                float f3 = f;
                float f4 = f2;
                if (this.l > 0.0f) {
                    f3 = f;
                    f4 = f2;
                    if (this.k < this.j - 1) {
                        view = this.d.getChildAt(this.k + 1);
                        f3 = view.getLeft();
                        f4 = view.getRight();
                        float f5 = this.l;
                        f3 = f * (1.0f - this.l) + f3 * f5;
                        f4 = f4 * this.l + (1.0f - this.l) * f2;
                    }
                }
                canvas.drawRect(f3, (float)(n - this.s), f4, (float)n, this.m);
                if (this.t != 0) {
                    this.m.setColor(this.p);
                    canvas.drawRect(0.0f, (float)(n - this.t), (float)this.d.getWidth(), (float)n, this.m);
                }
                if (this.v != 0) break block8;
            }
            return;
        }
        this.n.setColor(this.q);
        int n2 = 0;
        while (n2 < this.j - 1) {
            view = this.d.getChildAt(n2);
            canvas.drawLine((float)view.getRight(), (float)this.u, (float)view.getRight(), (float)(n - this.u), this.n);
            ++n2;
        }
    }

    public void onRestoreInstanceState(Parcelable object) {
        object = (SavedState)((Object)object);
        super.onRestoreInstanceState(object.getSuperState());
        this.k = object.a;
        this.requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = this.k;
        return savedState;
    }

    public void setDividerColor(int n) {
        this.q = n;
        this.invalidate();
    }

    public void setDividerColorResource(int n) {
        this.q = this.getResources().getColor(n);
        this.invalidate();
    }

    public void setDividerPadding(int n) {
        this.u = n;
        this.invalidate();
    }

    public void setIndicatorColor(int n) {
        this.o = n;
        this.invalidate();
    }

    public void setIndicatorColorResource(int n) {
        this.o = this.getResources().getColor(n);
        this.invalidate();
    }

    public void setIndicatorHeight(int n) {
        this.s = n;
        this.invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.c = onPageChangeListener;
    }

    public void setScrollOffset(int n) {
        this.r = n;
        this.invalidate();
    }

    public void setShouldExpand(boolean bl) {
        this.f = bl;
        this.requestLayout();
    }

    public void setTabPaddingLeftRight(int n) {
        this.g = n;
    }

    public void setUnderlineColor(int n) {
        this.p = n;
        this.invalidate();
    }

    public void setUnderlineColorResource(int n) {
        this.p = this.getResources().getColor(n);
        this.invalidate();
    }

    public void setUnderlineHeight(int n) {
        this.t = n;
        this.invalidate();
    }

    public void setViewPager(ViewPager viewPager) {
        this.e = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener((ViewPager.OnPageChangeListener)this.i);
        viewPager.getAdapter().registerDataSetObserver(new DataSetObserver(){

            public void onChanged() {
                PagerSlidingTabStrip.this.a();
            }
        });
        this.a();
    }

    public static interface IconTabProvider {
        public int a(int var1);
    }

    private class PageListener
    implements ViewPager.OnPageChangeListener {
        private PageListener() {
        }

        public void onPageScrollStateChanged(int n) {
            if (n == 0) {
                PagerSlidingTabStrip.this.b(PagerSlidingTabStrip.this.e.getCurrentItem(), 0);
            }
            if (PagerSlidingTabStrip.this.c != null) {
                PagerSlidingTabStrip.this.c.onPageScrollStateChanged(n);
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onPageScrolled(int n, float f, int n2) {
            block3 : {
                block2 : {
                    if (PagerSlidingTabStrip.this.d.getChildCount() == 0) break block2;
                    PagerSlidingTabStrip.this.k = n;
                    PagerSlidingTabStrip.this.l = f;
                    PagerSlidingTabStrip.this.b(n, (int)((float)PagerSlidingTabStrip.this.d.getChildAt(n).getWidth() * f));
                    PagerSlidingTabStrip.this.invalidate();
                    if (PagerSlidingTabStrip.this.c != null) break block3;
                }
                return;
            }
            PagerSlidingTabStrip.this.c.onPageScrolled(n, f, n2);
        }

        /*
         * Enabled aggressive block sorting
         */
        public void onPageSelected(int n) {
            for (int i = 0; i < PagerSlidingTabStrip.this.d.getChildCount(); ++i) {
                View view = PagerSlidingTabStrip.this.d.getChildAt(i);
                boolean bl = i == n;
                view.setSelected(bl);
            }
            if (PagerSlidingTabStrip.this.c != null) {
                PagerSlidingTabStrip.this.c.onPageSelected(n);
            }
        }
    }

    static class SavedState
    extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>(){

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int n) {
                return new SavedState[n];
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return this.a(parcel);
            }

            public /* synthetic */ Object[] newArray(int n) {
                return this.a(n);
            }
        };
        int a;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(@NonNull Parcel parcel, int n) {
            super.writeToParcel(parcel, n);
            parcel.writeInt(this.a);
        }

    }

}

