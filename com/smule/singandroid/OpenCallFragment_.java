package com.smule.singandroid;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class OpenCallFragment_ extends OpenCallFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f19110v = new OnViewChangedNotifier();
    private View f19111w;

    class C39201 implements OnClickListener {
        final /* synthetic */ OpenCallFragment_ f19109a;

        C39201(OpenCallFragment_ openCallFragment_) {
            this.f19109a = openCallFragment_;
        }

        public void onClick(View view) {
            this.f19109a.m20612B();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, OpenCallFragment> {
        public OpenCallFragment m20639a() {
            OpenCallFragment openCallFragment_ = new OpenCallFragment_();
            openCallFragment_.setArguments(this.a);
            return openCallFragment_;
        }

        public FragmentBuilder_ m20640a(SingBundle singBundle) {
            this.a.putParcelable("mSingBundle", singBundle);
            return this;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f19110v);
        m20642a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f19111w == null) {
            return null;
        }
        return this.f19111w.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f19111w = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f19111w == null) {
            this.f19111w = layoutInflater.inflate(C1947R.layout.open_call_fragment, viewGroup, false);
        }
        return this.f19111w;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f19111w = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.e = null;
    }

    private void m20642a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m20641C();
        m20644b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f19110v.a(this);
    }

    public void m20646a(HasViews hasViews) {
        this.k = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.l = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.m = hasViews.findViewById(C1947R.id.mOpenCallListContainerView);
        this.n = hasViews.findViewById(C1947R.id.mNoOpenCallsView);
        this.o = (ListView) hasViews.findViewById(C1947R.id.mListView);
        this.p = hasViews.findViewById(C1947R.id.mOpenCallListLoadingView);
        this.q = (PerformanceListEmptyListItem) hasViews.findViewById(C1947R.id.mPerformanceListEmptyListItem);
        this.e = (SimpleTypeTabs) hasViews.findViewById(C1947R.id.opencall_type_tab_layout);
        View findViewById = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C39201(this));
        }
        m20611A();
        m20632a();
    }

    private void m20641C() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey("mSingBundle")) {
            this.f = (SingBundle) arguments.getParcelable("mSingBundle");
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("mAllOpenCalls", this.r);
        bundle.putParcelableArrayList("mVideoOpenCalls", this.s);
        bundle.putInt("mCurrentItem", this.t);
        bundle.putBoolean("mIsSongVIPOnly", this.u);
        bundle.putParcelable("mSingBundle", this.f);
        bundle.putParcelable("mEntry", this.g);
    }

    private void m20644b(Bundle bundle) {
        if (bundle != null) {
            this.r = bundle.getParcelableArrayList("mAllOpenCalls");
            this.s = bundle.getParcelableArrayList("mVideoOpenCalls");
            this.t = bundle.getInt("mCurrentItem");
            this.u = bundle.getBoolean("mIsSongVIPOnly");
            this.f = (SingBundle) bundle.getParcelable("mSingBundle");
            this.g = (SongbookEntry) bundle.getParcelable("mEntry");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
