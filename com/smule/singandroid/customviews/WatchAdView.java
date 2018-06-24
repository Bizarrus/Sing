package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingServerValues;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class WatchAdView extends LinearLayout {
    @ViewById
    protected TextView f22090a;
    @ViewById
    protected TextView f22091b;
    @ViewById
    protected Button f22092c;
    private Context f22093d;

    public WatchAdView(Context context) {
        super(context);
        this.f22093d = context;
    }

    public void setTitleText(String str) {
        this.f22090a.setText(str);
    }

    public void setSubtitleText(String str) {
        this.f22091b.setText(str);
    }

    public void setButtonText(String str) {
        this.f22092c.setText(str);
    }

    public void m23551a(OnClickListener onClickListener) {
        this.f22092c.setOnClickListener(onClickListener);
    }

    @AfterViews
    protected void m23550a() {
        if (SingServerValues.m21676N()) {
            this.f22090a.setTextColor(-1);
            this.f22091b.setTextColor(-1);
            this.f22092c.setTextColor(ContextCompat.getColor(this.f22093d, C1947R.color.upsell_sky_indigo_end));
            this.f22092c.setBackground(ContextCompat.getDrawable(this.f22093d, C1947R.drawable.button_white));
            LayoutParams layoutParams = this.f22092c.getLayoutParams();
            layoutParams.height = this.f22093d.getResources().getDimensionPixelOffset(C1947R.dimen.purchase_button_v2_height);
            this.f22092c.setLayoutParams(layoutParams);
        }
    }
}
