package com.smule.singandroid.upsell;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LearnMoreFragment_ extends LearnMoreFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f24550g = new OnViewChangedNotifier();
    private View f24551h;

    class C49741 implements OnClickListener {
        final /* synthetic */ LearnMoreFragment_ f24549a;

        C49741(LearnMoreFragment_ learnMoreFragment_) {
            this.f24549a = learnMoreFragment_;
        }

        public void onClick(View view) {
            this.f24549a.m25738z();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, LearnMoreFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24550g);
        m25739a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24551h == null) {
            return null;
        }
        return this.f24551h.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24551h = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24551h == null) {
            this.f24551h = layoutInflater.inflate(C1947R.layout.learn_more_fragment, viewGroup, false);
        }
        return this.f24551h;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24551h = null;
        this.e = null;
        this.f = null;
    }

    private void m25739a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24550g.a(this);
    }

    public void m25742a(HasViews hasViews) {
        this.e = (ImageView) hasViews.findViewById(C1947R.id.learn_more_action_bar_back);
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.learn_more_feature_rows);
        if (this.e != null) {
            this.e.setOnClickListener(new C49741(this));
        }
        m25734a();
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
