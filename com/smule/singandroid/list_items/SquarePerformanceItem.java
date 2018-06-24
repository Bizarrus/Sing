package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.StringCacheManager;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class SquarePerformanceItem extends MediaPlayingListItem implements PerformanceItemInterface {
    private static final String f23270f = SquarePerformanceItem.class.getName();
    @ViewById
    protected TextView f23271a;
    @ViewById
    protected TextView f23272b;
    @ViewById
    protected TextView f23273c;
    @ViewById
    protected TextView f23274d;
    ImageUtils$ImageViewLoadOptimizer f23275e = new ImageUtils$ImageViewLoadOptimizer();
    private PerformanceV2 f23276g;
    private String f23277h;
    private PerformanceItemListener f23278i;
    private SquarePerformanceItemListener f23279j;
    private LocalizedShortNumberFormatter f23280k;

    public interface SquarePerformanceItemListener {
        void mo6821a();

        void mo6822b();
    }

    class C47001 implements OnClickListener {
        final /* synthetic */ SquarePerformanceItem f23266a;

        C47001(SquarePerformanceItem squarePerformanceItem) {
            this.f23266a = squarePerformanceItem;
        }

        public void onClick(View view) {
            switch (this.f23266a.af) {
                case UNKNOWN:
                case IDLE:
                    if (this.f23266a.f23278i != null) {
                        this.f23266a.f23278i.mo6472a(this.f23266a, this.f23266a.f23276g);
                        return;
                    }
                    return;
                case PLAYING:
                    this.f23266a.mo6881u();
                    return;
                default:
                    return;
            }
        }
    }

    class C47012 implements OnClickListener {
        final /* synthetic */ SquarePerformanceItem f23267a;

        C47012(SquarePerformanceItem squarePerformanceItem) {
            this.f23267a = squarePerformanceItem;
        }

        public void onClick(View view) {
            if (this.f23267a.f23279j != null) {
                this.f23267a.f23279j.mo6821a();
            }
        }
    }

    class C47023 implements OnClickListener {
        final /* synthetic */ SquarePerformanceItem f23268a;

        C47023(SquarePerformanceItem squarePerformanceItem) {
            this.f23268a = squarePerformanceItem;
        }

        public void onClick(View view) {
            if (this.f23268a.f23279j != null) {
                this.f23268a.f23279j.mo6822b();
            }
        }
    }

    public SquarePerformanceItem(Context context) {
        super(context);
    }

    public SquarePerformanceItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SquarePerformanceItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setListener(PerformanceItemListener performanceItemListener) {
        this.f23278i = performanceItemListener;
    }

    public void setSquarePerformanceItemListener(SquarePerformanceItemListener squarePerformanceItemListener) {
        this.f23279j = squarePerformanceItemListener;
    }

    public void setRecId(String str) {
        this.f23277h = str;
    }

    public String getRecId() {
        return this.f23277h;
    }

    public void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(f23270f, "setPerformance passed a null performance!");
            return;
        }
        this.f23276g = performanceV2;
        m24498c();
        this.f23273c.setText(getLocalizedFormatter().m18999a((long) this.f23276g.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        this.f23274d.setText(getLocalizedFormatter().m18999a((long) this.f23276g.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        setOnClickListener(new C47001(this));
        this.ae.m23378a(m23037a(this.f23276g), (int) C1947R.drawable.explore_filmstrip);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23280k == null) {
            this.f23280k = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23280k;
    }

    private void m24498c() {
        SongV2 songV2 = null;
        if (this.f23276g.songUid != null) {
            songV2 = StoreManager.m18378a().m18416a(this.f23276g.songUid);
        }
        this.f23271a.setText(this.f23276g.title != null ? this.f23276g.title : "");
        this.f23272b.setText(PerformanceV2Util.m25933a(getResources(), this.f23276g, true));
        m24499d();
        if (this.f23276g.coverUrl != null && !this.f23276g.coverUrl.isEmpty()) {
            this.f23275e.m18975a(this.f23276g.coverUrl, this.ae.f21796a, C1947R.drawable.icn_default_album_medium);
        } else if (songV2 == null || songV2.googleCoverArtUrl == null || songV2.googleCoverArtUrl.length() == 0) {
            this.ae.f21796a.setImageResource(C1947R.drawable.icn_default_album_medium);
        } else {
            this.f23275e.m18975a(songV2.googleCoverArtUrl, this.ae.f21796a, C1947R.drawable.icn_default_album_medium);
        }
    }

    public String getPlaySongKey() {
        if (this.f23276g != null) {
            return this.f23276g.performanceKey;
        }
        return null;
    }

    private void m24499d() {
        m23036a(getPlaySongKey());
        this.ae.f21804i.setOnClickListener(new C47012(this));
        if (!this.f23276g.hasBeenLoved) {
            this.f23276g.hasBeenLoved = StringCacheManager.a().b(this.f23276g.performanceKey);
        }
        if (this.f23276g.hasBeenLoved) {
            this.ae.f21805j.setImageDrawable(getResources().getDrawable(C1947R.drawable.icn_love_blue));
            return;
        }
        this.ae.f21805j.setImageDrawable(getResources().getDrawable(C1947R.drawable.icn_love_white));
        this.ae.f21805j.setOnClickListener(new C47023(this));
    }

    public PlayableItemView getPlayableItemView() {
        return this.ae;
    }
}
