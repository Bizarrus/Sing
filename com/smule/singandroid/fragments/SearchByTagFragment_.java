package com.smule.singandroid.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MediaListView;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchByTagFragment_ extends SearchByTagFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22681t = new OnViewChangedNotifier();
    private View f22682u;

    class C45991 implements OnClickListener {
        final /* synthetic */ SearchByTagFragment_ f22679a;

        C45991(SearchByTagFragment_ searchByTagFragment_) {
            this.f22679a = searchByTagFragment_;
        }

        public void onClick(View view) {
            this.f22679a.m24167B();
        }
    }

    class C46002 implements OnClickListener {
        final /* synthetic */ SearchByTagFragment_ f22680a;

        C46002(SearchByTagFragment_ searchByTagFragment_) {
            this.f22680a = searchByTagFragment_;
        }

        public void onClick(View view) {
            this.f22680a.m24168C();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SearchByTagFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22681t);
        m24179a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22682u == null) {
            return null;
        }
        return this.f22682u.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22682u = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22682u == null) {
            this.f22682u = layoutInflater.inflate(C1947R.layout.search_by_tag_fragment, viewGroup, false);
        }
        return this.f22682u;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22682u = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
    }

    private void m24179a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m24181b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22681t.a(this);
    }

    public void m24183a(HasViews hasViews) {
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.empty_layout);
        this.g = (TextView) hasViews.findViewById(C1947R.id.search_by_tag_empty_text);
        this.h = (MediaListView) hasViews.findViewById(C1947R.id.search_list_view);
        this.i = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.j = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.k = (TextView) hasViews.findViewById(C1947R.id.title_text_view);
        View findViewById = hasViews.findViewById(C1947R.id.toolbar_root_view);
        View findViewById2 = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C45991(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C46002(this));
        }
        m24166A();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable("mResultList", this.l);
        bundle.putString("mTag", this.m);
        bundle.putBoolean("mEmpty", this.n);
    }

    private void m24181b(Bundle bundle) {
        if (bundle != null) {
            this.l = (ArrayList) bundle.getSerializable("mResultList");
            this.m = bundle.getString("mTag");
            this.n = bundle.getBoolean("mEmpty");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
