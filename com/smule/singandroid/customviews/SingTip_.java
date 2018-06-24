/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.smule.singandroid.customviews.SingTip;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SingTip_
extends SingTip
implements HasViews,
OnViewChangedListener {
    private boolean d = false;
    private final OnViewChangedNotifier e = new OnViewChangedNotifier();

    public SingTip_(Context context) {
        super(context);
        this.b();
    }

    public static SingTip a(Context object) {
        object = new SingTip_((Context)object);
        object.onFinishInflate();
        return object;
    }

    private void b() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.e);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.a = (TextView)hasViews.findViewById(2131756651);
        if ((hasViews = hasViews.findViewById(2131755224)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SingTip_.this.a(view);
                }
            });
        }
        this.a();
    }

    public void onFinishInflate() {
        if (!this.d) {
            this.d = true;
            SingTip_.inflate((Context)this.getContext(), (int)2130903411, (ViewGroup)this);
            this.e.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

