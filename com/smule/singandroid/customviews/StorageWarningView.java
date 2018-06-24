/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.LinearLayout
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.LayoutUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.customviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.customviews.StorageWarningView_;
import com.smule.singandroid.profile.PerformanceListItem;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class StorageWarningView
extends RelativeLayout {
    @ViewById
    protected View a;
    @ViewById
    protected LinearLayout b;
    @ViewById
    protected TextView c;
    @ViewById
    protected Button d;
    @ViewById
    protected LinearLayout e;
    @ViewById
    protected FrameLayout f;
    @ViewById
    protected FrameLayout g;
    private Context h;
    private boolean i;
    private View.OnClickListener j;
    private boolean k;
    private WeakListener.OnGlobalLayoutListener l;

    public StorageWarningView(Context context, boolean bl, View.OnClickListener onClickListener) {
        super(context);
        this.l = new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onGlobalLayout() {
                if (StorageWarningView.this.b == null || StorageWarningView.this.e == null || StorageWarningView.this.e.getChildCount() <= 0) {
                    return;
                }
                StorageWarningView.this.f();
            }
        });
        this.h = context;
        this.i = bl;
        this.j = onClickListener;
    }

    public static StorageWarningView a(Activity activity, boolean bl, View.OnClickListener onClickListener) {
        return StorageWarningView_.a((Context)activity, bl, onClickListener);
    }

    private void f() {
        if (this.g.getHeight() == 0) {
            ViewGroup.LayoutParams layoutParams = this.g.getLayoutParams();
            layoutParams.height = this.b.getHeight();
            this.g.setLayoutParams(layoutParams);
        }
    }

    public void a() {
        if (this.i) {
            this.c.setText((CharSequence)this.h.getResources().getString(2131297592, new Object[]{new SingServerValues().ab()}));
            return;
        }
        this.c.setText((CharSequence)this.h.getResources().getString(2131297591));
    }

    public void a(int n) {
        if (this.b == null) {
            return;
        }
        this.b.setTranslationY((float)n);
        this.f.setBottom(this.a.getHeight() + n);
    }

    public void a(List<PerformanceListItemContainer> object) {
        this.e.removeAllViews();
        object = object.iterator();
        while (object.hasNext()) {
            Object object2 = (PerformanceListItemContainer)object.next();
            PerformanceListItem performanceListItem = PerformanceListItem.c(this.h);
            object2 = object2.a();
            performanceListItem.setRecordingType(object2.video);
            performanceListItem.setPerformance((PerformanceV2)object2);
            performanceListItem.setIsFirstElement(false);
            performanceListItem.setOnClickListener(null);
            this.e.addView((View)performanceListItem);
        }
    }

    @AfterViews
    protected void b() {
        this.a();
        this.d.setOnClickListener(this.j);
        if (!this.k) {
            LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)this.l);
            this.k = true;
        }
    }

    public void c() {
        if (this.e.getChildCount() > 0) {
            this.e.removeViewAt(0);
        }
    }

    public boolean d() {
        if (this.e.getChildCount() > 0) {
            return true;
        }
        return false;
    }

    public void e() {
        ViewGroup.LayoutParams layoutParams = this.g.getLayoutParams();
        layoutParams.height = 0;
        this.g.setLayoutParams(layoutParams);
    }

    public int getInitialTopPos() {
        if (this.a == null) {
            return 0;
        }
        int[] arrn = new int[2];
        this.a.getLocationOnScreen(arrn);
        return arrn[1];
    }

    public int getWarningLayoutHeight() {
        if (this.b == null) {
            return 0;
        }
        return this.b.getHeight();
    }

    public void setShowFutureWarning(boolean bl) {
        this.i = bl;
        if (this.i) {
            this.e();
            this.a(0);
        }
    }

}

