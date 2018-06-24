package com.smule.singandroid.customviews;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SingTip extends FrameLayout {
    @ViewById
    protected TextView f21958a;
    protected String f21959b = null;
    protected OnClickListener f21960c;

    public static SingTip m23481a(Context context, String str) {
        SingTip a = SingTip_.m23484a(context);
        a.setTipText(str);
        return a;
    }

    public SingTip(Context context) {
        super(context);
    }

    @AfterViews
    protected void m23482a() {
        setTipText(this.f21959b);
    }

    @Click
    protected void m23483a(View view) {
        if (this.f21960c != null) {
            this.f21960c.onClick(view);
        }
    }

    public void setListener(OnClickListener onClickListener) {
        this.f21960c = onClickListener;
    }

    public void setTipText(String str) {
        if (str != null) {
            this.f21958a.setText(str);
            this.f21959b = str;
        }
    }
}
