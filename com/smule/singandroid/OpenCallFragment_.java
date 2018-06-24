/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.ListView
 *  android.widget.TextView
 *  org.androidannotations.api.BackgroundExecutor
 *  org.androidannotations.api.builder.FragmentBuilder
 *  org.androidannotations.api.view.HasViews
 *  org.androidannotations.api.view.OnViewChangedListener
 *  org.androidannotations.api.view.OnViewChangedNotifier
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.OpenCallFragment;
import com.smule.singandroid.SimpleTypeTabs;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OpenCallFragment_
extends OpenCallFragment
implements HasViews,
OnViewChangedListener {
    private final OnViewChangedNotifier A = new OnViewChangedNotifier();
    private View B;

    private void K() {
        Bundle bundle = this.getArguments();
        if (bundle != null && bundle.containsKey("mSingBundle")) {
            this.h = (SingBundle)bundle.getParcelable("mSingBundle");
        }
    }

    private void a(Bundle bundle) {
        OnViewChangedNotifier.a((OnViewChangedListener)this);
        this.K();
        this.b(bundle);
    }

    private void b(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.v = bundle.getParcelableArrayList("mAllOpenCalls");
        this.w = bundle.getParcelableArrayList("mVideoOpenCalls");
        this.x = bundle.getParcelableArrayList("mHotOpenCalls");
        this.y = bundle.getInt("mCurrentItem");
        this.z = bundle.getBoolean("mIsSongVIPOnly");
        this.h = (SingBundle)bundle.getParcelable("mSingBundle");
        this.i = (SongbookEntry)bundle.getParcelable("mEntry");
    }

    @Override
    public void a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable RunTimePermissionsRequester.ResultCallback resultCallback) {
        BackgroundExecutor.a();
        OpenCallFragment_.super.a(runTimePermissionsRequest, resultCallback);
    }

    public void a(HasViews hasViews) {
        this.o = hasViews.findViewById(2131755305);
        this.p = (TextView)hasViews.findViewById(2131755306);
        this.q = hasViews.findViewById(2131756213);
        this.r = hasViews.findViewById(2131756217);
        this.s = (ListView)hasViews.findViewById(2131756214);
        this.t = hasViews.findViewById(2131756215);
        this.u = (PerformanceListEmptyListItem)hasViews.findViewById(2131756218);
        this.g = (SimpleTypeTabs)hasViews.findViewById(2131756202);
        if ((hasViews = hasViews.findViewById(2131755307)) != null) {
            hasViews.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    OpenCallFragment_.this.J();
                }
            });
        }
        this.I();
        this.a();
    }

    public View findViewById(int n) {
        if (this.B == null) {
            return null;
        }
        return this.B.findViewById(n);
    }

    @Override
    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier onViewChangedNotifier = OnViewChangedNotifier.a((OnViewChangedNotifier)this.A);
        this.a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a((OnViewChangedNotifier)onViewChangedNotifier);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.B == null) {
            this.B = layoutInflater.inflate(2130903341, viewGroup, false);
        }
        return this.B;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.B = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.g = null;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("mAllOpenCalls", this.v);
        bundle.putParcelableArrayList("mVideoOpenCalls", this.w);
        bundle.putParcelableArrayList("mHotOpenCalls", this.x);
        bundle.putInt("mCurrentItem", this.y);
        bundle.putBoolean("mIsSongVIPOnly", this.z);
        bundle.putParcelable("mSingBundle", (Parcelable)this.h);
        bundle.putParcelable("mEntry", (Parcelable)this.i);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.A.a((HasViews)this);
    }

    public static class FragmentBuilder_
    extends FragmentBuilder<FragmentBuilder_, OpenCallFragment> {
        public OpenCallFragment a() {
            OpenCallFragment_ openCallFragment_ = new OpenCallFragment_();
            openCallFragment_.setArguments(this.a);
            return openCallFragment_;
        }

        public FragmentBuilder_ a(SingBundle singBundle) {
            this.a.putParcelable("mSingBundle", (Parcelable)singBundle);
            return this;
        }
    }

}

