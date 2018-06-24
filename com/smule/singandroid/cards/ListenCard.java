package com.smule.singandroid.cards;

import android.content.Context;
import android.widget.FrameLayout;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.SquarePerformanceItem;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ListenCard extends FrameLayout implements MediaPlayingViewInterface {
    private static final String f20707b = ListenCard.class.getName();
    @ViewById
    protected SquarePerformanceItem f20708a;

    public static ListenCard m22310a(Context context) {
        return ListenCard_.m22312b(context);
    }

    public ListenCard(Context context) {
        super(context);
    }

    public SquarePerformanceItem getSquarePerformanceItem() {
        return this.f20708a;
    }

    public void u_() {
        this.f20708a.u_();
    }

    public void mo6724b() {
    }

    public String getMediaKey() {
        return this.f20708a.getMediaKey();
    }
}
