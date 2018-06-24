package com.smule.singandroid.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.smule.singandroid.R$styleable;
import com.smule.singandroid.SingApplication;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PlayableItemView extends FrameLayout {
    @ViewById
    public ImageView f21796a;
    @ViewById
    public View f21797b;
    @ViewById
    public ImageView f21798c;
    @ViewById
    public View f21799d;
    @ViewById
    public View f21800e;
    @ViewById
    public ProgressBar f21801f;
    @ViewById
    public ImageView f21802g;
    @ViewById
    protected View f21803h;
    @ViewById
    public ImageButton f21804i;
    @ViewById
    public ImageButton f21805j;
    @ViewById
    public ImageView f21806k;
    @ViewById
    public ImageView f21807l;
    @ViewById
    public ImageView f21808m;
    private Drawable f21809n;
    private Drawable f21810o;
    private Drawable f21811p;
    private boolean f21812q;
    private boolean f21813r = false;
    private boolean f21814s;

    public PlayableItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23376a(context, attributeSet);
    }

    protected void m23376a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PlayableItemView);
        this.f21809n = obtainStyledAttributes.getDrawable(0);
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            this.f21810o = drawable;
        }
        drawable = obtainStyledAttributes.getDrawable(2);
        if (drawable != null) {
            this.f21811p = drawable;
        }
        this.f21812q = obtainStyledAttributes.getBoolean(3, false);
        obtainStyledAttributes.recycle();
    }

    @AfterViews
    protected void m23375a() {
        int i = 0;
        this.f21808m.setVisibility(8);
        if (this.f21809n != null) {
            this.f21796a.setImageDrawable(this.f21809n);
        }
        if (this.f21810o != null) {
            this.f21798c.setImageDrawable(this.f21810o);
        }
        if (this.f21811p != null) {
            this.f21802g.setImageDrawable(this.f21811p);
        }
        this.f21803h.setVisibility(this.f21812q ? 0 : 8);
        View view = this.f21800e;
        if (!this.f21812q) {
            i = 8;
        }
        view.setVisibility(i);
        m23379b();
    }

    public void m23377a(boolean z) {
        this.f21813r = z;
    }

    public void m23379b() {
        this.f21797b.setVisibility(8);
        this.f21799d.setVisibility(8);
        this.f21801f.setVisibility(8);
        this.f21802g.setVisibility(0);
        this.f21803h.setVisibility(8);
        this.f21800e.setVisibility(8);
    }

    public void m23380c() {
        int i;
        int i2 = 0;
        this.f21799d.setVisibility(this.f21813r ? 8 : 0);
        this.f21801f.setVisibility(8);
        this.f21802g.setVisibility(8);
        View view = this.f21803h;
        if (!this.f21812q || this.f21814s) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
        View view2 = this.f21800e;
        if (!this.f21812q || this.f21814s) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    public void m23381d() {
        this.f21797b.setVisibility(8);
        this.f21799d.setVisibility(8);
        this.f21801f.setVisibility(0);
        this.f21802g.setVisibility(8);
        this.f21803h.setVisibility(8);
        this.f21800e.setVisibility(8);
    }

    public void m23378a(boolean z, int i) {
        int i2;
        int i3 = 0;
        int i4 = (z && SingApplication.o()) ? 1 : 0;
        this.f21806k.setImageResource(i);
        ImageView imageView = this.f21806k;
        if (i4 != 0) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        this.f21807l.setImageResource(i);
        ImageView imageView2 = this.f21807l;
        if (i4 == 0) {
            i3 = 8;
        }
        imageView2.setVisibility(i3);
    }
}
