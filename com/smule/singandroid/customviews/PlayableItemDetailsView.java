package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.list_items.MediaPlayingListItem;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PlayableItemDetailsView extends MediaPlayingListItem implements MediaPlayingViewInterface {
    private ImageUtils$ImageViewLoadOptimizer f21378a = new ImageUtils$ImageViewLoadOptimizer();
    private LocalizedShortNumberFormatter f21379b;
    @ViewById
    protected View f21380q;
    @ViewById
    protected TextView f21381r;
    @ViewById
    protected TextView f21382s;
    @ViewById
    protected TextView f21383t;
    @ViewById
    protected TextView f21384u;
    protected PerformanceV2 f21385v;

    public PlayableItemDetailsView(Context context) {
        super(context);
    }

    public PlayableItemDetailsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PlayableItemDetailsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23049a(boolean z) {
        this.f21380q.setVisibility(z ? 0 : 8);
    }

    public void mo6775a(PerformanceV2 performanceV2, boolean z) {
        this.f21385v = performanceV2;
        this.f21383t.setText(this.f21385v.title);
        this.f21384u.setText(PerformanceV2Util.m25933a(getResources(), this.f21385v, z));
        this.f21381r.setText(getLocalizedFormatter().m18999a((long) this.f21385v.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        this.f21382s.setText(getLocalizedFormatter().m18999a((long) this.f21385v.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        if (this.f21385v.coverUrl == null || this.f21385v.coverUrl.isEmpty()) {
            this.ae.f21796a.setImageResource(C1947R.drawable.icn_album_cover_play_large);
        } else {
            this.f21378a.m18975a(this.f21385v.coverUrl, this.ae.f21796a, C1947R.drawable.icn_album_cover_play_large);
        }
        m23036a(this.f21385v.performanceKey);
        this.ae.m23378a(m23037a(this.f21385v), (int) C1947R.drawable.noti_filmstrip);
    }

    public void setOnAlbumArtClickListener(OnClickListener onClickListener) {
        this.ae.setOnClickListener(onClickListener);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f21379b == null) {
            this.f21379b = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f21379b;
    }
}
