package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.ListViewMediaPlayerObserver;

public class MediaListView extends MagicListView {
    public MediaListView(Context context) {
        this(context, null);
    }

    public MediaListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C1947R.attr.magicListViewStyle);
    }

    public MediaListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            ListViewMediaPlayerObserver.m25861a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (!isInEditMode()) {
            ListViewMediaPlayerObserver.m25862b(this);
        }
    }
}
