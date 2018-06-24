package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.utils.MiscUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class CustomToolBarBase extends LinearLayout {
    @ViewById
    protected View f21428g;
    @ViewById
    protected ImageView f21429h;
    @ViewById
    protected TextView f21430i;
    @ViewById
    protected TextView f21431j;
    @ViewById
    protected TextView f21432k;
    @ViewById
    protected ImageView f21433l;
    @ViewById
    protected TextView f21434m;
    protected TitleType f21435n = TitleType.Default;

    public enum TitleType {
        Default,
        Center
    }

    public CustomToolBarBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomToolBarBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CharSequence getTitle() {
        return this.f21430i.getText();
    }

    public void setTitle(CharSequence charSequence) {
        m23104a(charSequence, null);
    }

    public void m23104a(CharSequence charSequence, CharSequence charSequence2) {
        if (this.f21435n == TitleType.Default) {
            this.f21430i.setText(charSequence);
            setSubTitle(charSequence2);
            return;
        }
        this.f21432k.setText(charSequence);
    }

    public void setTitleType(TitleType titleType) {
        this.f21435n = titleType;
        m23103a(titleType);
    }

    public ImageView getLeftButton() {
        return this.f21429h;
    }

    public ImageView getPreSearchLeftButton() {
        return this.f21433l;
    }

    public TextView getTitleView() {
        return this.f21430i;
    }

    public TextView getPreSearchTitleView() {
        return this.f21434m;
    }

    public void setTitleCompoundDrawable(int i) {
        this.f21430i.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
    }

    public CharSequence getSubTitle() {
        return this.f21431j.getText();
    }

    protected void setSubTitle(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            this.f21431j.setVisibility(8);
            return;
        }
        this.f21431j.setVisibility(0);
        this.f21431j.setTextColor(getResources().getColor(C1947R.color.body_text_grey));
        this.f21431j.setText(charSequence);
    }

    public void mo6782a(SongbookEntry songbookEntry, PerformanceV2 performanceV2) {
        m23102a(songbookEntry, performanceV2, false);
    }

    public void m23102a(SongbookEntry songbookEntry, PerformanceV2 performanceV2, boolean z) {
        if (songbookEntry == null && performanceV2 == null) {
            throw new IllegalArgumentException("Entry and/or performance required");
        }
        Pair a = MiscUtils.m25881a(songbookEntry, performanceV2, z);
        m23104a((CharSequence) a.first, (CharSequence) a.second);
    }

    protected void m23103a(TitleType titleType) {
        int i;
        int i2 = 4;
        int i3 = 0;
        int i4 = titleType == TitleType.Default ? 1 : 0;
        TextView textView = this.f21430i;
        if (i4 != 0) {
            i = 0;
        } else {
            i = 4;
        }
        textView.setVisibility(i);
        textView = this.f21431j;
        if (i4 != 0) {
            i = 0;
        } else {
            i = 4;
        }
        textView.setVisibility(i);
        TextView textView2 = this.f21432k;
        if (i4 == 0) {
            i2 = 0;
        }
        textView2.setVisibility(i2);
        ImageView imageView = this.f21429h;
        if (i4 == 0) {
            i3 = 8;
        }
        imageView.setVisibility(i3);
    }
}
