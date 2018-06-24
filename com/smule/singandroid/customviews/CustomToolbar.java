package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.textviews.AutoResizeTextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class CustomToolbar extends CustomToolBarBase {
    public static final String f21532a = CustomToolbar.class.getName();
    @ViewById
    protected ImageView f21533b;
    @ViewById
    protected AutoResizeTextView f21534c;
    @ViewById
    protected ImageView f21535d;
    protected Drawable f21536e;
    protected boolean f21537f;
    protected Drawable f21538o;
    protected String f21539p;
    private boolean f21540q;

    public CustomToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomToolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21540q = false;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CustomToolbar, i, 0);
        this.f21536e = obtainStyledAttributes.getDrawable(0);
        this.f21537f = obtainStyledAttributes.getBoolean(1, true);
        this.f21538o = obtainStyledAttributes.getDrawable(2);
        this.f21539p = obtainStyledAttributes.getString(3);
        obtainStyledAttributes.recycle();
    }

    @AfterViews
    protected void m23169a() {
        setLeftButtonDrawable(this.f21536e);
        if (this.f21538o != null) {
            setRightButtonDrawable(this.f21538o);
        } else if (this.f21539p != null) {
            setRightButtonText(this.f21539p);
        } else {
            m23171b();
        }
    }

    public void setLeftButtonOnClickListener(OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public void setLeftButtonDrawable(Drawable drawable) {
        if (drawable != null) {
            this.h.setImageDrawable(drawable);
        }
        this.h.setVisibility(this.f21537f ? 0 : 8);
    }

    public void setRightButtonDrawable(Drawable drawable) {
        this.f21533b.setImageDrawable(drawable);
        m23171b();
    }

    public void setRightButtonText(String str) {
        this.f21534c.setText(str);
        m23171b();
    }

    protected void m23171b() {
        if (this.f21534c.getText() == null || this.f21534c.getText().length() <= 0) {
            this.f21534c.setVisibility(8);
            this.f21535d.setVisibility(8);
            if (this.f21533b.getDrawable() == null) {
                this.f21533b.setVisibility(4);
                return;
            } else {
                this.f21533b.setVisibility(0);
                return;
            }
        }
        this.f21533b.setVisibility(8);
        this.f21534c.setVisibility(0);
        if (this.f21540q) {
            this.f21535d.setVisibility(0);
        } else {
            this.f21535d.setVisibility(4);
        }
    }

    public void setRightButtonOnClickListener(OnClickListener onClickListener) {
        this.f21533b.setOnClickListener(onClickListener);
        this.f21534c.setOnClickListener(onClickListener);
    }

    public void m23172c() {
        ImageUtils.a(this.f21534c, getResources().getDrawable(C1947R.drawable.btn_grey));
        this.f21534c.setClickable(false);
    }

    public void mo6782a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        super.mo6782a(songbookEntry, performanceV2);
        boolean z = songbookEntry != null && songbookEntry.mo6293g();
        this.f21540q = z;
    }
}
