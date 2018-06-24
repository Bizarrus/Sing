package com.smule.singandroid.upsell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SKUSelectionView_ extends SKUSelectionView implements HasViews, OnViewChangedListener {
    private boolean f24584m = false;
    private final OnViewChangedNotifier f24585n = new OnViewChangedNotifier();

    public SKUSelectionView_(Context context) {
        super(context);
        m25753a();
    }

    public SKUSelectionView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m25753a();
    }

    public SKUSelectionView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m25753a();
    }

    public static SKUSelectionView m25752a(Context context) {
        SKUSelectionView sKUSelectionView_ = new SKUSelectionView_(context);
        sKUSelectionView_.onFinishInflate();
        return sKUSelectionView_;
    }

    public void onFinishInflate() {
        if (!this.f24584m) {
            this.f24584m = true;
            inflate(getContext(), C1947R.layout.sku_selection_view, this);
            this.f24585n.a(this);
        }
        super.onFinishInflate();
    }

    private void m25753a() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24585n);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m25754a(HasViews hasViews) {
        this.a = (ImageView) hasViews.findViewById(C1947R.id.sku_selection_profile_image_view);
        this.b = hasViews.findViewById(C1947R.id.sku_selection_header);
        this.c = hasViews.findViewById(C1947R.id.sku_selection_audio_fx_header);
        this.d = (TextView) hasViews.findViewById(C1947R.id.sku_selection_audio_fx_title_text_view);
        this.e = (TextView) hasViews.findViewById(C1947R.id.sku_selection_audio_fx_subtitle_text_view);
        this.f = (TextView) hasViews.findViewById(C1947R.id.sku_selection_title_text_view);
        this.g = (TextView) hasViews.findViewById(C1947R.id.sku_selection_subtitle_text_view);
        this.h = (PurchaseButtonV2) hasViews.findViewById(C1947R.id.sku_selection_top_purchase_button);
        this.i = (PurchaseButtonV2) hasViews.findViewById(C1947R.id.sku_selection_middle_purchase_button);
        this.j = (PurchaseButtonV2) hasViews.findViewById(C1947R.id.sku_selection_bottom_purchase_button);
        this.k = (ProgressBar) hasViews.findViewById(C1947R.id.sku_selection_progress_bar);
        this.l = (TextView) hasViews.findViewById(C1947R.id.cannot_connect_textview);
    }
}
