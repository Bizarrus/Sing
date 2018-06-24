package com.smule.singandroid.upsell;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.upsell.LearnMoreFeatureRowView.FeatureType;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class LearnMoreFragment extends BaseFragment {
    @ViewById
    protected ImageView f24546e;
    @ViewById
    protected LinearLayout f24547f;
    private FeatureType[] f24548g = FeatureType.values();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public String mo6383s() {
        return null;
    }

    protected void mo6420v() {
        SingAnalytics.m26057B();
    }

    public boolean mo6511h() {
        return false;
    }

    @AfterViews
    protected void m25734a() {
        for (FeatureType a : this.f24548g) {
            this.f24547f.addView(LearnMoreFeatureRowView_.m25731a(getActivity(), a));
        }
    }

    @Click
    protected void m25738z() {
        getActivity().onBackPressed();
    }
}
