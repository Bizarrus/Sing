package com.smule.singandroid.songbook_search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Analytics.SearchExecuteContext;
import com.smule.android.logging.Analytics.SearchTarget;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MediaListView;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchShowAllFragment_ extends SearchShowAllFragment implements HasViews, OnViewChangedListener {
    private View f24205A;
    private final OnViewChangedNotifier f24206z = new OnViewChangedNotifier();

    class C49091 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment_ f24202a;

        C49091(SearchShowAllFragment_ searchShowAllFragment_) {
            this.f24202a = searchShowAllFragment_;
        }

        public void onClick(View view) {
            this.f24202a.m25394B();
        }
    }

    class C49102 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment_ f24203a;

        C49102(SearchShowAllFragment_ searchShowAllFragment_) {
            this.f24203a = searchShowAllFragment_;
        }

        public void onClick(View view) {
            this.f24203a.m25395C();
        }
    }

    class C49113 implements OnClickListener {
        final /* synthetic */ SearchShowAllFragment_ f24204a;

        C49113(SearchShowAllFragment_ searchShowAllFragment_) {
            this.f24204a = searchShowAllFragment_;
        }

        public void onClick(View view) {
            this.f24204a.m25396F();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SearchShowAllFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24206z);
        m25410a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24205A == null) {
            return null;
        }
        return this.f24205A.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24205A = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24205A == null) {
            this.f24205A = layoutInflater.inflate(C1947R.layout.songbook_search_showall_fragment_layout, viewGroup, false);
        }
        return this.f24205A;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24205A = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    private void m25410a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25412b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24206z.a(this);
    }

    public void m25414a(HasViews hasViews) {
        this.f = (MediaListView) hasViews.findViewById(C1947R.id.search_showall_listview);
        this.g = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        this.h = (TextView) hasViews.findViewById(C1947R.id.sub_title_text_view);
        this.i = (ImageView) hasViews.findViewById(C1947R.id.icn_sort);
        this.j = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.k = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.l = (SwipeRefreshLayout) hasViews.findViewById(C1947R.id.mSwipeLayout);
        View findViewById = hasViews.findViewById(C1947R.id.toolbar_root_view);
        View findViewById2 = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C49091(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C49102(this));
        }
        if (this.i != null) {
            this.i.setOnClickListener(new C49113(this));
        }
        m25393A();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("mViewType", this.n);
        bundle.putString("mSearchTerm", this.o);
        bundle.putString("mSearchInputText", this.t);
        bundle.putSerializable("mSearchExecuteContext", this.u);
        bundle.putSerializable("mSearchTarget", this.v);
        bundle.putBoolean("mIsShowingNewOption", this.w);
        bundle.putBoolean("mShouldFireBackSearchExecute", this.y);
    }

    private void m25412b(Bundle bundle) {
        if (bundle != null) {
            this.n = bundle.getInt("mViewType");
            this.o = bundle.getString("mSearchTerm");
            this.t = bundle.getString("mSearchInputText");
            this.u = (SearchExecuteContext) bundle.getSerializable("mSearchExecuteContext");
            this.v = (SearchTarget) bundle.getSerializable("mSearchTarget");
            this.w = bundle.getBoolean("mIsShowingNewOption");
            this.y = bundle.getBoolean("mShouldFireBackSearchExecute");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
