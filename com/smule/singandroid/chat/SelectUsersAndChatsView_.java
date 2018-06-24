/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v7.widget.RecyclerView
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.EditText
 *  android.widget.ImageView
 *  com.foound.widget.AmazingListView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.foound.widget.AmazingListView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.chat.SelectUsersAndChatsView;
import java.util.List;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SelectUsersAndChatsView_
extends SelectUsersAndChatsView
implements HasViews,
OnViewChangedListener {
    private boolean C = false;
    private final OnViewChangedNotifier D = new OnViewChangedNotifier();

    public SelectUsersAndChatsView_(Context context) {
        super(context);
        this.m();
    }

    public SelectUsersAndChatsView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m();
    }

    public SelectUsersAndChatsView_(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.m();
    }

    private void m() {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.D);
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    public void a(HasViews hasViews) {
        this.c = (ViewGroup)hasViews.findViewById(2131755221);
        this.d = (ViewGroup)hasViews.findViewById(2131755240);
        this.e = (ViewGroup)hasViews.findViewById(2131755370);
        this.f = (ViewGroup)hasViews.findViewById(2131756507);
        this.g = (EditText)hasViews.findViewById(2131756157);
        this.h = hasViews.findViewById(2131756509);
        this.i = hasViews.findViewById(2131756510);
        this.j = hasViews.findViewById(2131756516);
        this.k = (RecyclerView)hasViews.findViewById(2131756511);
        this.l = (ViewGroup)hasViews.findViewById(2131756514);
        this.m = (AmazingListView)hasViews.findViewById(2131755303);
        this.n = (ViewGroup)hasViews.findViewById(2131756512);
        this.o = (ImageView)hasViews.findViewById(2131756508);
        this.p = hasViews.findViewById(2131756517);
        this.q = (ViewGroup)hasViews.findViewById(2131756518);
        View view = hasViews.findViewById(2131756513);
        hasViews = hasViews.findViewById(2131756519);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SelectUsersAndChatsView_.this.b();
                }
            });
        }
        if (this.i != null) {
            this.i.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SelectUsersAndChatsView_.this.i();
                }
            });
        }
        if (this.p != null) {
            this.p.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SelectUsersAndChatsView_.this.j();
                }
            });
        }
        if (hasViews != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    SelectUsersAndChatsView_.this.k();
                }
            });
        }
        this.a();
    }

    @Override
    protected void b(List<AccountIcon> list) {
        BackgroundExecutor.a();
        SelectUsersAndChatsView_.super.b(list);
    }

    public void onFinishInflate() {
        if (!this.C) {
            this.C = true;
            SelectUsersAndChatsView_.inflate((Context)this.getContext(), (int)2130903404, (ViewGroup)this);
            this.D.a((HasViews)this);
        }
        super.onFinishInflate();
    }

}

