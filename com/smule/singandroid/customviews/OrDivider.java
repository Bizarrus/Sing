package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class OrDivider extends BetweenRelativeLayout {
    @ViewById
    protected View f21679b;
    @ViewById
    protected TextView f21680c;
    @ViewById
    protected View f21681d;

    public OrDivider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public OrDivider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23308a() {
        this.f21679b.setBackgroundColor(-1);
        this.f21681d.setBackgroundColor(-1);
        this.f21680c.setTextColor(-1);
    }
}
