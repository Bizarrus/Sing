package com.smule.singandroid.upsell;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.upsell.LearnMoreFeatureRowView.FeatureType;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class LearnMoreFeatureRowView_ extends LearnMoreFeatureRowView implements HasViews, OnViewChangedListener {
    private boolean f24544f = false;
    private final OnViewChangedNotifier f24545g = new OnViewChangedNotifier();

    public LearnMoreFeatureRowView_(Context context, FeatureType featureType) {
        super(context, featureType);
        m25732b();
    }

    public static LearnMoreFeatureRowView m25731a(Context context, FeatureType featureType) {
        LearnMoreFeatureRowView learnMoreFeatureRowView_ = new LearnMoreFeatureRowView_(context, featureType);
        learnMoreFeatureRowView_.onFinishInflate();
        return learnMoreFeatureRowView_;
    }

    public void onFinishInflate() {
        if (!this.f24544f) {
            this.f24544f = true;
            inflate(getContext(), C1947R.layout.learn_more_feature_row_view, this);
            this.f24545g.a(this);
        }
        super.onFinishInflate();
    }

    private void m25732b() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24545g);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m25733a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.learn_more_feature_row_icon);
        this.b = (TextView) hasViews.findViewById(C1947R.id.learn_more_feature_row_title);
        this.c = hasViews.findViewById(C1947R.id.learn_more_divider);
        this.d = (TextView) hasViews.findViewById(C1947R.id.learn_more_feature_free_text);
        this.e = (TextView) hasViews.findViewById(C1947R.id.learn_more_feature_vip_text);
        m25730a();
    }
}
