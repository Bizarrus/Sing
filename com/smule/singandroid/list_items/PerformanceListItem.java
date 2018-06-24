package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.PerformanceItemInterface.PerformanceItemListener;
import com.smule.singandroid.media_player_service.MediaPlayerServiceController;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerformanceListItem extends VideoUploadingView implements PerformanceItemInterface {
    private static final String f23043a = PerformanceListItem.class.getName();
    private PerformanceItemListener f23044b;
    @ViewById
    protected View f23045c;
    @ViewById
    protected View f23046d;
    @ViewById
    protected TextView f23047e;
    @ViewById
    protected TextView f23048f;
    @ViewById
    protected TextView f23049g;
    @ViewById
    protected TextView f23050h;
    @ViewById
    protected TextView f23051i;
    @ViewById
    protected View f23052j;
    @ViewById
    protected TextView f23053k;
    @ViewById
    ProfileImageWithVIPBadge f23054l;
    @ViewById
    protected TextView f23055m;
    @ViewById
    protected View f23056n;
    private String f23057o = null;
    private boolean f23058p = false;
    private BaseFragment f23059q;
    private LocalizedShortNumberFormatter f23060r;
    private boolean f23061s;

    class C46751 implements OnClickListener {
        final /* synthetic */ PerformanceListItem f23117a;

        C46751(PerformanceListItem performanceListItem) {
            this.f23117a = performanceListItem;
        }

        public void onClick(View view) {
            if (this.f23117a.f23044b != null) {
                this.f23117a.f23044b.mo6473b(this.f23117a, this.f23117a.D);
            }
        }
    }

    class C46762 implements OnClickListener {
        final /* synthetic */ PerformanceListItem f23118a;

        C46762(PerformanceListItem performanceListItem) {
            this.f23118a = performanceListItem;
        }

        public void onClick(View view) {
            if (this.f23118a.f23044b == null) {
                return;
            }
            if (this.f23118a.D != null) {
                this.f23118a.f23044b.mo6472a(this.f23118a, this.f23118a.D);
            } else {
                Log.d(PerformanceListItem.f23043a, "configureSharedClickListeners - mMetadataView - mOpenCall and mPerformance are both null!");
            }
        }
    }

    public static PerformanceListItem m24362c(Context context) {
        return PerformanceListItem_.m24400a(context);
    }

    public PerformanceListItem(Context context) {
        super(context);
    }

    public void setListener(PerformanceItemListener performanceItemListener) {
        this.f23044b = performanceItemListener;
    }

    public void m24365a(boolean z, BaseFragment baseFragment) {
        this.f23058p = z;
        this.f23059q = baseFragment;
    }

    public void setPerformance(PerformanceV2 performanceV2) {
        if (performanceV2 == null) {
            Log.e(f23043a, "setPerformance passed a null performance!");
            return;
        }
        setTag(performanceV2.performanceKey);
        this.D = performanceV2;
        mo6877c();
        Log.b(f23043a, "setPerformance - performance key is: " + performanceV2.performanceKey + ", and loaded mediaKey is: " + MediaPlayerServiceController.m24641a().m24673i());
        if (this.f23054l != null) {
            if (!this.f23058p || this.D.recentTracks.size() <= 0) {
                this.f23054l.setVisibility(8);
            } else {
                AccountIcon accountIcon = ((Track) this.D.recentTracks.get(0)).accountIcon;
                this.f23054l.setVisibility(0);
                this.f23054l.m23396a(this.f23059q, accountIcon);
            }
        }
        if (this.f23055m != null) {
            int i;
            if (performanceV2.seed && performanceV2.d() && performanceV2.childCount > 0) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                this.f23055m.setVisibility(0);
                this.f23055m.setText(getLocalizedFormatter().m18999a((long) performanceV2.childCount, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
            } else {
                this.f23055m.setVisibility(8);
            }
        }
        m23036a(this.D.performanceKey);
        m24364f();
        this.ae.m23378a(m23037a(this.D), (int) C1947R.drawable.noti_filmstrip);
    }

    public void setHeaderTextAndShowHeader(String str) {
        this.f23053k.setText(str);
        this.f23052j.setVisibility(0);
        setIsFirstElement(true);
    }

    public void m24367d() {
        this.f23052j.setVisibility(8);
        setIsFirstElement(this.f23061s);
    }

    public void setIsFirstElement(boolean z) {
        this.f23061s = z;
        this.f23056n.setVisibility(z ? 8 : 0);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.f23060r == null) {
            this.f23060r = new LocalizedShortNumberFormatter(getContext());
        }
        return this.f23060r;
    }

    protected void mo6877c() {
        int i;
        SongV2 a = this.D.songUid != null ? StoreManager.m18378a().m18416a(this.D.songUid) : null;
        this.f23047e.setText(this.D.title != null ? this.D.title : "");
        this.f23048f.setText(PerformanceV2Util.m25933a(getResources(), this.D, true));
        TextView textView = this.f23048f;
        if (this.D.isPrivate) {
            i = C1947R.drawable.icn_private;
        } else {
            i = 0;
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, i, 0);
        if (this.D.coverUrl == null || this.D.coverUrl.isEmpty()) {
            if (a == null || a.googleCoverArtUrl == null || a.googleCoverArtUrl.length() == 0) {
                this.f23057o = null;
                this.ae.f21796a.setImageResource(C1947R.drawable.album_blank);
            } else if (this.f23057o == null || !this.f23057o.equals(a.googleCoverArtUrl)) {
                this.f23057o = a.googleCoverArtUrl;
                ImageUtils.a(this.f23057o, this.ae.f21796a, C1947R.drawable.icn_default_album_small);
            }
        } else if (this.f23057o == null || !this.f23057o.equals(this.D.coverUrl)) {
            this.f23057o = this.D.coverUrl;
            ImageUtils.a(this.f23057o, this.ae.f21796a, C1947R.drawable.icn_default_album_small);
        }
        if (this.f23050h != null) {
            this.f23050h.setText(getLocalizedFormatter().m18999a((long) this.D.totalListens, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        }
        this.f23049g.setText(getLocalizedFormatter().m18999a((long) this.D.totalLoves, (long) getResources().getInteger(C1947R.integer.long_form_threshold)));
        this.f23051i.setText(MiscUtils.m25886a((long) this.D.createdAt, true, false));
    }

    private void m24364f() {
        this.ae.f21796a.setOnClickListener(new C46751(this));
        this.f23045c.setOnClickListener(new C46762(this));
    }
}
