/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.os.Looper
 *  android.util.AttributeSet
 *  android.util.DisplayMetrics
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewsById
 */
package com.smule.singandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import java.util.Iterator;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewsById;

@EViewGroup
public class SimpleTypeTabs
extends RelativeLayout {
    private static final String e = SimpleTypeTabs.class.getName();
    @ViewsById
    protected List<View> a;
    @ViewsById
    protected List<TextView> b;
    @ViewsById
    protected List<TextView> c;
    @ViewsById
    protected List<ImageView> d;
    private Context f;
    private int g = 0;

    public SimpleTypeTabs(Context context) {
        super(context);
        this.f = context;
    }

    public SimpleTypeTabs(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
    }

    private void d() {
        Object object = this.f.getResources().getDisplayMetrics();
        int n = this.getNumberOfVisibleTabs();
        n = object.widthPixels / n;
        object = this.a.iterator();
        while (object.hasNext()) {
            ((View)object.next()).setMinimumWidth(n);
        }
    }

    private int getNumberOfVisibleTabs() {
        Iterator<View> iterator = this.a.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().getVisibility() != 0) continue;
            ++n;
        }
        return n;
    }

    @AfterViews
    protected void a() {
        for (int i = 0; i < this.a.size(); ++i) {
            this.a.get(i).setTag((Object)i);
        }
    }

    public void a(final int n) {
        this.g = n;
        new Handler(this.f.getMainLooper()).post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                Log.b(e, "Setting performance type to " + n);
                for (View view : SimpleTypeTabs.this.a) {
                    if (n == (Integer)view.getTag()) {
                        view.setBackgroundDrawable(SimpleTypeTabs.this.getResources().getDrawable(2130838189));
                        continue;
                    }
                    view.setBackgroundColor(SimpleTypeTabs.this.getResources().getColor(2131689946));
                }
                int n5 = SimpleTypeTabs.this.getResources().getColor(2131689579);
                int n2 = SimpleTypeTabs.this.getResources().getColor(2131689807);
                int n3 = 0;
                while (n3 < SimpleTypeTabs.this.a.size()) {
                    TextView textView = SimpleTypeTabs.this.b.get(n3);
                    int n4 = n == n3 ? n5 : n2;
                    textView.setTextColor(n4);
                    ++n3;
                }
            }
        });
    }

    public void a(int n, int n2) {
        this.b.get(n).setText(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(int n, boolean bl) {
        for (View view : this.a) {
            if ((Integer)view.getTag() != n) continue;
            n = bl ? 0 : 8;
            view.setVisibility(n);
            break;
        }
        this.d();
    }

    @AfterViews
    protected void b() {
        this.d();
    }

    public void setOnTabClickListener(OnTabClickListener object) {
        object = new View.OnClickListener((OnTabClickListener)object){
            final /* synthetic */ OnTabClickListener a;
            {
                this.a = onTabClickListener;
            }

            public void onClick(View view) {
                int n = (Integer)view.getTag();
                if (SimpleTypeTabs.this.g != n) {
                    SimpleTypeTabs.this.a(n);
                    this.a.b_(n);
                }
            }
        };
        Iterator<View> iterator = this.a.iterator();
        while (iterator.hasNext()) {
            iterator.next().setOnClickListener((View.OnClickListener)object);
        }
    }

    public static interface OnTabClickListener {
        public void b_(int var1);
    }

}

