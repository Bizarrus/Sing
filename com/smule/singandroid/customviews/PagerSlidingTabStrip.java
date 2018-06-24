package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.Locale;

public class PagerSlidingTabStrip extends HorizontalScrollView {
    private static final int[] f21694b = new int[]{16842901, 16842904};
    public OnPageChangeListener f21695a;
    private LayoutParams f21696c;
    private LayoutParams f21697d;
    private final PageListener f21698e;
    private LinearLayout f21699f;
    private ViewPager f21700g;
    private int f21701h;
    private int f21702i;
    private float f21703j;
    private Paint f21704k;
    private Paint f21705l;
    private int f21706m;
    private int f21707n;
    private int f21708o;
    private boolean f21709p;
    private int f21710q;
    private int f21711r;
    private int f21712s;
    private int f21713t;
    private int f21714u;
    private int f21715v;
    private int f21716w;
    private int f21717x;
    private Locale f21718y;
    private OnGlobalLayoutListener f21719z;

    class C44171 extends DataSetObserver {
        final /* synthetic */ PagerSlidingTabStrip f21688a;

        C44171(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.f21688a = pagerSlidingTabStrip;
        }

        public void onChanged() {
            this.f21688a.m23328a();
        }
    }

    class C44182 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ PagerSlidingTabStrip f21689a;

        C44182(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.f21689a = pagerSlidingTabStrip;
        }

        public void onGlobalLayout() {
            LayoutUtils.m25859b(this.f21689a, this.f21689a.f21719z);
            this.f21689a.f21702i = this.f21689a.f21700g.getCurrentItem();
            this.f21689a.m23325b(this.f21689a.f21702i, 0);
        }
    }

    public interface IconTabProvider {
        int m23313a(int i);
    }

    private class PageListener implements OnPageChangeListener {
        final /* synthetic */ PagerSlidingTabStrip f21692a;

        private PageListener(PagerSlidingTabStrip pagerSlidingTabStrip) {
            this.f21692a = pagerSlidingTabStrip;
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (this.f21692a.f21699f.getChildCount() != 0) {
                this.f21692a.f21702i = i;
                this.f21692a.f21703j = f;
                this.f21692a.m23325b(i, (int) (((float) this.f21692a.f21699f.getChildAt(i).getWidth()) * f));
                this.f21692a.invalidate();
                if (this.f21692a.f21695a != null) {
                    this.f21692a.f21695a.onPageScrolled(i, f, i2);
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                this.f21692a.m23325b(this.f21692a.f21700g.getCurrentItem(), 0);
            }
            if (this.f21692a.f21695a != null) {
                this.f21692a.f21695a.onPageScrollStateChanged(i);
            }
        }

        public void onPageSelected(int i) {
            for (int i2 = 0; i2 < this.f21692a.f21699f.getChildCount(); i2++) {
                boolean z;
                TextView textView = (TextView) this.f21692a.f21699f.getChildAt(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                textView.setSelected(z);
            }
            if (this.f21692a.f21695a != null) {
                this.f21692a.f21695a.onPageSelected(i);
            }
        }
    }

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new C44201();
        int f21693a;

        static class C44201 implements Creator<SavedState> {
            C44201() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m23314a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m23315a(i);
            }

            public SavedState m23314a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] m23315a(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f21693a = parcel.readInt();
        }

        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f21693a);
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21698e = new PageListener();
        this.f21702i = 0;
        this.f21703j = 0.0f;
        this.f21706m = -10066330;
        this.f21707n = 436207616;
        this.f21708o = 436207616;
        this.f21709p = false;
        this.f21710q = 52;
        this.f21711r = 8;
        this.f21712s = 2;
        this.f21713t = 12;
        this.f21714u = 24;
        this.f21715v = 1;
        this.f21716w = 0;
        this.f21717x = 0;
        setFillViewport(true);
        setWillNotDraw(false);
        this.f21699f = new LinearLayout(context);
        this.f21699f.setOrientation(0);
        this.f21699f.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.f21699f);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f21710q = (int) TypedValue.applyDimension(1, (float) this.f21710q, displayMetrics);
        this.f21711r = (int) TypedValue.applyDimension(1, (float) this.f21711r, displayMetrics);
        this.f21712s = (int) TypedValue.applyDimension(1, (float) this.f21712s, displayMetrics);
        this.f21713t = (int) TypedValue.applyDimension(1, (float) this.f21713t, displayMetrics);
        this.f21715v = (int) TypedValue.applyDimension(1, (float) this.f21715v, displayMetrics);
        this.f21714u = (int) TypedValue.applyDimension(1, (float) this.f21714u, displayMetrics);
        context.obtainStyledAttributes(attributeSet, f21694b).recycle();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PagerSlidingTabStrip);
        this.f21706m = obtainStyledAttributes.getColor(0, this.f21706m);
        this.f21707n = obtainStyledAttributes.getColor(1, this.f21707n);
        this.f21708o = obtainStyledAttributes.getColor(2, this.f21708o);
        this.f21711r = obtainStyledAttributes.getDimensionPixelSize(3, this.f21711r);
        this.f21712s = obtainStyledAttributes.getDimensionPixelSize(4, this.f21712s);
        this.f21713t = obtainStyledAttributes.getDimensionPixelSize(5, this.f21713t);
        this.f21715v = obtainStyledAttributes.getDimensionPixelSize(6, this.f21715v);
        this.f21714u = obtainStyledAttributes.getDimensionPixelSize(7, this.f21714u);
        this.f21709p = obtainStyledAttributes.getBoolean(9, this.f21709p);
        this.f21710q = obtainStyledAttributes.getDimensionPixelSize(8, this.f21710q);
        this.f21716w = obtainStyledAttributes.getResourceId(10, this.f21716w);
        obtainStyledAttributes.recycle();
        this.f21704k = new Paint();
        this.f21704k.setAntiAlias(true);
        this.f21704k.setStyle(Style.FILL);
        this.f21705l = new Paint();
        this.f21705l.setAntiAlias(true);
        this.f21705l.setStrokeWidth((float) this.f21715v);
        this.f21696c = new LayoutParams(-2, -1);
        this.f21697d = new LayoutParams(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        if (this.f21718y == null) {
            this.f21718y = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.f21700g = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.f21698e);
        viewPager.getAdapter().registerDataSetObserver(new C44171(this));
        m23328a();
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f21695a = onPageChangeListener;
    }

    public void m23328a() {
        this.f21699f.removeAllViews();
        this.f21701h = this.f21700g.getAdapter().getCount();
        for (int i = 0; i < this.f21701h; i++) {
            if (this.f21700g.getAdapter() instanceof IconTabProvider) {
                m23319a(i, ((IconTabProvider) this.f21700g.getAdapter()).m23313a(i));
            } else {
                m23321a(i, this.f21700g.getAdapter().getPageTitle(i).toString());
            }
        }
        m23324b();
        if (this.f21719z != null) {
            LayoutUtils.m25859b(this, this.f21719z);
        }
        this.f21719z = new OnGlobalLayoutListener(this, new C44182(this));
        LayoutUtils.m25854a((View) this, this.f21719z);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f21719z != null) {
            LayoutUtils.m25859b(this, this.f21719z);
            this.f21719z = null;
        }
    }

    private void m23321a(int i, String str) {
        View view = (TextView) LayoutInflater.from(getContext()).inflate(C1947R.layout.songbook_tab, null);
        view.setText(str);
        m23320a(i, view);
    }

    private void m23319a(int i, int i2) {
        View imageButton = new ImageButton(getContext());
        imageButton.setImageResource(i2);
        m23320a(i, imageButton);
    }

    private void m23320a(final int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ PagerSlidingTabStrip f21691b;

            public void onClick(View view) {
                this.f21691b.f21700g.setCurrentItem(i);
            }
        });
        view.setPadding(this.f21714u, 0, this.f21714u, 0);
        this.f21699f.addView(view, i, this.f21709p ? this.f21697d : this.f21696c);
    }

    private void m23324b() {
    }

    private void m23325b(int i, int i2) {
        if (this.f21701h != 0) {
            int left = this.f21699f.getChildAt(i).getLeft() + i2;
            if (i > 0 || i2 > 0) {
                left -= this.f21710q;
            }
            if (left != this.f21717x) {
                this.f21717x = left;
                scrollTo(left, 0);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isInEditMode() && this.f21701h != 0) {
            int height = getHeight();
            this.f21704k.setColor(this.f21706m);
            View childAt = this.f21699f.getChildAt(this.f21702i);
            float left = (float) childAt.getLeft();
            float right = (float) childAt.getRight();
            if (this.f21703j > 0.0f && this.f21702i < this.f21701h - 1) {
                childAt = this.f21699f.getChildAt(this.f21702i + 1);
                float left2 = (float) childAt.getLeft();
                left = (left * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.f21703j)) + (left2 * this.f21703j);
                right = (((float) childAt.getRight()) * this.f21703j) + ((DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - this.f21703j) * right);
            }
            canvas.drawRect(left, (float) (height - this.f21711r), right, (float) height, this.f21704k);
            if (this.f21712s != 0) {
                this.f21704k.setColor(this.f21707n);
                canvas.drawRect(0.0f, (float) (height - this.f21712s), (float) this.f21699f.getWidth(), (float) height, this.f21704k);
            }
            if (this.f21715v != 0) {
                this.f21705l.setColor(this.f21708o);
                for (int i = 0; i < this.f21701h - 1; i++) {
                    childAt = this.f21699f.getChildAt(i);
                    canvas.drawLine((float) childAt.getRight(), (float) this.f21713t, (float) childAt.getRight(), (float) (height - this.f21713t), this.f21705l);
                }
            }
        }
    }

    public View getCurrentTab() {
        return this.f21699f.getChildAt(this.f21702i);
    }

    public void setIndicatorColor(int i) {
        this.f21706m = i;
        invalidate();
    }

    public void setIndicatorColorResource(int i) {
        this.f21706m = getResources().getColor(i);
        invalidate();
    }

    public int getIndicatorColor() {
        return this.f21706m;
    }

    public void setIndicatorHeight(int i) {
        this.f21711r = i;
        invalidate();
    }

    public int getIndicatorHeight() {
        return this.f21711r;
    }

    public void setUnderlineColor(int i) {
        this.f21707n = i;
        invalidate();
    }

    public void setUnderlineColorResource(int i) {
        this.f21707n = getResources().getColor(i);
        invalidate();
    }

    public int getUnderlineColor() {
        return this.f21707n;
    }

    public void setDividerColor(int i) {
        this.f21708o = i;
        invalidate();
    }

    public void setDividerColorResource(int i) {
        this.f21708o = getResources().getColor(i);
        invalidate();
    }

    public int getDividerColor() {
        return this.f21708o;
    }

    public void setUnderlineHeight(int i) {
        this.f21712s = i;
        invalidate();
    }

    public int getUnderlineHeight() {
        return this.f21712s;
    }

    public void setDividerPadding(int i) {
        this.f21713t = i;
        invalidate();
    }

    public int getDividerPadding() {
        return this.f21713t;
    }

    public void setScrollOffset(int i) {
        this.f21710q = i;
        invalidate();
    }

    public int getScrollOffset() {
        return this.f21710q;
    }

    public void setShouldExpand(boolean z) {
        this.f21709p = z;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return this.f21709p;
    }

    public void setTabPaddingLeftRight(int i) {
        this.f21714u = i;
        m23324b();
    }

    public int getTabPaddingLeftRight() {
        return this.f21714u;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f21702i = savedState.f21693a;
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.f21693a = this.f21702i;
        return savedState;
    }
}
