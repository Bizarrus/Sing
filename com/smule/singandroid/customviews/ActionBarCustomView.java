package com.smule.singandroid.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ActionBarCustomView extends CustomToolBarBase {
    protected OnClickListener f21436a;
    @ViewById
    protected View f21437b;
    @ViewById
    protected View f21438c;
    @ViewById
    protected ViewGroup f21439d;
    @ViewById
    protected View f21440e;
    protected final LayoutInflater f21441f;

    public static ActionBarCustomView m23105a(Context context) {
        return ActionBarCustomView_.m23111a(context, null);
    }

    public ActionBarCustomView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionBarCustomView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f21441f = LayoutInflater.from(context);
    }

    public void setDisplayUpButton(boolean z) {
        this.h.setImageResource(z ? C1947R.drawable.action_bar_up_button : C1947R.drawable.mic_gray);
    }

    public void setEnableUpButton(boolean z) {
        this.i.setClickable(z);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f21436a = onClickListener;
    }

    public void setDoneButtonOnClickListener(OnClickListener onClickListener) {
        this.f21437b.setOnClickListener(onClickListener);
        this.f21437b.setVisibility(onClickListener == null ? 8 : 0);
    }

    public ImageView m23107a(Drawable drawable) {
        this.f21440e.setVisibility(0);
        ImageView imageView = (ImageView) this.f21441f.inflate(C1947R.layout.action_bar_custom_icon, this.f21439d, false);
        imageView.setScaleType(ScaleType.CENTER);
        imageView.setImageDrawable(drawable);
        this.f21439d.addView(imageView);
        return imageView;
    }

    public TextView m23108a(CharSequence charSequence) {
        if (this.f21439d.getChildCount() == 0) {
            this.f21440e.setVisibility(8);
        }
        TextView textView = (TextView) this.f21441f.inflate(C1947R.layout.action_bar_text_button, this.f21439d, false);
        textView.setText(charSequence);
        this.f21439d.addView(textView);
        return textView;
    }

    public View m23106a(View view) {
        this.f21439d.addView(view);
        return view;
    }

    public void m23109a() {
        int i = 0;
        while (i < this.f21439d.getChildCount()) {
            if (this.f21439d.getChildAt(i).getId() != C1947R.id.done_button) {
                this.f21439d.removeViewAt(i);
                i--;
            }
            i++;
        }
        this.f21440e.setVisibility(8);
    }

    @Click
    protected void m23110b() {
        if (this.f21436a != null) {
            this.f21436a.onClick(this);
        }
    }
}
