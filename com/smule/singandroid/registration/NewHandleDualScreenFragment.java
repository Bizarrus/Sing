/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.v4.content.ContextCompat
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.registration;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.ui.TextDrawable;
import com.smule.singandroid.registration.NewHandleFragment;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.utils.TypefaceUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class NewHandleDualScreenFragment
extends NewHandleFragment {
    private static final String G = NewHandleDualScreenFragment.class.getName();
    @ViewById
    protected LinearLayout g;
    @ViewById
    protected FrameLayout h;
    @ViewById
    protected FrameLayout i;
    @ViewById
    protected ImageView j;
    @ViewById
    protected ImageView k;
    @ViewById
    protected ImageView l;
    @ViewById
    protected LinearLayout m;
    @ViewById
    protected TextView n;
    @ViewById
    protected TextView o;
    @ViewById
    protected TextView p;
    @ViewById
    protected TextView q;
    @InstanceState
    protected SplitScreenState r;

    /*
     * Enabled aggressive block sorting
     */
    private void K() {
        String string2 = this.r == null ? null : this.r.name();
        RegistrationContext.a(string2);
    }

    @Override
    public void F() {
        this.J();
    }

    @Override
    public void G() {
        this.r = SplitScreenState.c;
        this.K();
    }

    protected void H() {
        this.h.setVisibility(8);
        this.i.setVisibility(0);
        this.p.setVisibility(0);
        this.o.setVisibility(8);
        this.q.setVisibility(8);
    }

    protected void I() {
        this.g.setVisibility(8);
        this.m.setVisibility(0);
    }

    protected void J() {
        this.g.setVisibility(8);
        this.m.setVisibility(0);
        this.r = SplitScreenState.b;
        this.K();
        com.smule.android.logging.Analytics.p();
    }

    @AfterViews
    @Override
    protected void a() {
        super.a();
        this.n.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewHandleDualScreenFragment.this.M();
            }
        });
        this.t();
        this.b(this.k);
        this.b(this.l);
        this.o.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewHandleDualScreenFragment.this.z.callOnClick();
            }
        });
        this.p.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                com.smule.android.logging.Analytics.a(Analytics.a, NewHandleDualScreenFragment.this.E);
                ((NewHandleFragment.UserUpdateListener)NewHandleDualScreenFragment.this.getActivity()).C_();
            }
        });
        this.q.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                com.smule.android.logging.Analytics.a(Analytics.a, NewHandleDualScreenFragment.this.E);
                ((NewHandleFragment.UserUpdateListener)NewHandleDualScreenFragment.this.getActivity()).C_();
            }
        });
    }

    protected void b(ImageView imageView) {
        Resources resources = this.getActivity().getResources();
        int n = resources.getDimensionPixelSize(2131427408);
        imageView.setBackground((Drawable)TextDrawable.a().b().a(TypefaceUtils.d((Context)this.getActivity())).a(n).b(n).d(n).c(ContextCompat.getColor((Context)this.getActivity(), (int)2131689991)).a().a(resources.getString(2131297749), ContextCompat.getColor((Context)this.getActivity(), (int)2131689745)));
    }

    @Override
    public void onCreate(Bundle object) {
        super.onCreate((Bundle)object);
        this.r = SplitScreenState.a;
        object = RegistrationContext.g();
        if (object != null) {
            this.r = SplitScreenState.valueOf((String)object);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.r == SplitScreenState.c) {
            this.H();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.r == SplitScreenState.b || this.r == SplitScreenState.c) {
            this.I();
        }
    }

    protected void t() {
        Object object = this.getActivity().getResources();
        int n = object.getDimensionPixelSize(2131427422);
        int n2 = object.getDimensionPixelSize(2131427433);
        object = TextDrawable.a().b().a(TypefaceUtils.d((Context)this.getActivity())).a(n2).b(n2).d(n).c(ContextCompat.getColor((Context)this.getActivity(), (int)2131689991)).a(object.getDimension(2131427392)).a(true).a().a(object.getString(2131297776), ContextCompat.getColor((Context)this.getActivity(), (int)2131689744));
        this.j.setBackground((Drawable)object);
        this.j.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NewHandleDualScreenFragment.this.z.callOnClick();
            }
        });
    }

    protected static enum SplitScreenState {
        a,
        b,
        c;
        

        private SplitScreenState() {
        }
    }

}

