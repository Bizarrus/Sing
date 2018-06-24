package com.smule.singandroid.list_items;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.network.models.ListingV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.utils.ImageUtils$ImageViewLoadOptimizer;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.OnboardingActivity;
import com.smule.singandroid.utils.SongbookEntryUtils;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ListingListItem extends MediaPlayingListItem {
    public static Integer f23071n = Integer.valueOf(1234);
    private static final String f23072o = ListingListItem.class.getName();
    @ViewById
    protected LinearLayout f23073a;
    @ViewById
    protected TextView f23074b;
    @ViewById
    protected ViewGroup f23075c;
    @ViewById
    protected View f23076d;
    @ViewById
    protected TextView f23077e;
    @ViewById
    protected TextView f23078f;
    @ViewById
    protected TextView f23079g;
    @ViewById
    protected ImageView f23080h;
    @ViewById
    protected TextView f23081i;
    @ViewById
    protected TextView f23082j;
    @ViewById
    protected TextView f23083k;
    @ViewById
    protected View f23084l;
    @ViewById
    protected TextView f23085m;
    private SongbookEntry f23086p;
    private LocalizedShortNumberFormatter f23087q;
    private ImageUtils$ImageViewLoadOptimizer f23088r = new ImageUtils$ImageViewLoadOptimizer();
    private boolean f23089s;

    public static ListingListItem m24377a(Context context) {
        return ListingListItem_.m24384b(context);
    }

    public ListingListItem(Context context) {
        super(context);
        this.f23087q = new LocalizedShortNumberFormatter(context);
        setTag(C1947R.id.listing_list_item_tag, f23071n);
    }

    public void m24380a(SongbookEntry songbookEntry, boolean z) {
        if (songbookEntry.m18772r()) {
            m24378a((ArrangementVersionLiteEntry) songbookEntry, z);
        } else {
            m24379a((ListingEntry) songbookEntry, z);
        }
    }

    public void m24379a(ListingEntry listingEntry, boolean z) {
        if (listingEntry == null) {
            Log.e(f23072o, "setListing - listingEntry is null!");
            return;
        }
        int i;
        this.f23086p = listingEntry;
        ListingV2 listingV2 = ((ListingEntry) this.f23086p).f17626a;
        if (listingV2.song == null) {
            if (listingV2.productId != null) {
                Log.d(f23072o, "setListing - listing is not null, but listing.song is. listing.productId is: " + listingV2.productId);
            } else {
                Log.d(f23072o, "setListing - listing is not null, but listing.song is. listing.productId is also null");
            }
        }
        this.f23082j.setVisibility(8);
        this.f23083k.setVisibility(8);
        this.f23081i.setVisibility(8);
        if (listingV2.song != null) {
            this.f23077e.setText(listingV2.song.title != null ? listingV2.song.title : "");
            this.f23077e.setVisibility(0);
            this.f23078f.setText(listingV2.song.artist != null ? listingV2.song.artist : "");
            this.f23078f.setVisibility(0);
        }
        if (TextUtils.isEmpty(this.f23086p.mo6295i())) {
            this.ae.f21796a.setImageResource(C1947R.drawable.icn_default_album_small);
        } else {
            this.f23088r.m18975a(this.f23086p.mo6295i(), this.ae.f21796a, C1947R.drawable.icn_default_album_small);
        }
        if (!OnboardingActivity.f18978h) {
            this.f23078f.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(C1947R.drawable.icn_rings_songbook), null, null, null);
        }
        m23036a(listingV2.productId);
        if (!this.f23086p.mo6293g() || OnboardingActivity.f18978h) {
            this.f23080h.setVisibility(4);
        } else {
            this.f23080h.setVisibility(0);
        }
        if (listingV2.song != null && listingV2.song.partnerArtist != null && !OnboardingActivity.f18978h) {
            this.f23079g.setVisibility(0);
            this.f23079g.setText(C1947R.string.listing_tag_featured);
        } else if (!listingV2.isNew || OnboardingActivity.f18978h) {
            this.f23079g.setVisibility(8);
        } else {
            this.f23079g.setVisibility(0);
            this.f23079g.setText(C1947R.string.listing_tag_new);
        }
        View view = this.f23076d;
        if (z) {
            i = 8;
        } else {
            i = 0;
        }
        view.setVisibility(i);
    }

    public void m24378a(ArrangementVersionLiteEntry arrangementVersionLiteEntry, boolean z) {
        int i = 0;
        if (arrangementVersionLiteEntry == null) {
            Log.e(f23072o, "setArrangementVersionLite - arrEntry is null!");
            return;
        }
        int i2;
        SongV2 songV2;
        Drawable drawable;
        this.f23086p = arrangementVersionLiteEntry;
        ArrangementVersionLite arrangementVersionLite = arrangementVersionLiteEntry.f17623a;
        boolean a = arrangementVersionLite.a();
        ListingV2 e = !a ? StoreManager.m18378a().m18428e(arrangementVersionLite.songId) : null;
        CharSequence e2 = arrangementVersionLiteEntry.mo6291e();
        if (a || e == null) {
            this.f23077e.setText(e2);
            if (arrangementVersionLite.artist == null) {
                this.f23078f.setVisibility(8);
            } else {
                this.f23078f.setVisibility(0);
                this.f23078f.setText(arrangementVersionLite.artist);
            }
            TextView textView = this.f23081i;
            if (arrangementVersionLiteEntry.mo6296j()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            textView.setVisibility(i2);
        } else {
            this.f23077e.setText(e2);
            this.f23078f.setVisibility(0);
            this.f23078f.setText(e.song.artist);
            if (arrangementVersionLiteEntry.mo6296j()) {
                this.f23081i.setVisibility(8);
            } else {
                this.f23081i.setVisibility(0);
            }
        }
        this.f23078f.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        if (e == null || e.song == null) {
            songV2 = null;
        } else {
            songV2 = e.song;
        }
        String a2 = SongbookEntryUtils.m26164a((SongbookEntry) arrangementVersionLiteEntry, songV2);
        if (a2 != null) {
            this.f23088r.m18975a(a2, this.ae.f21796a, C1947R.drawable.icn_default_album_small);
        } else {
            this.ae.f21796a.setImageResource(C1947R.drawable.icn_default_album_small);
        }
        m23036a(arrangementVersionLite.key);
        this.f23080h.setVisibility(4);
        if (arrangementVersionLite.rating == null || arrangementVersionLite.totalVotes < 3) {
            this.f23083k.setText(C1947R.string.songbook_no_ratings);
            drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_gray);
            i2 = getContext().getResources().getColor(C1947R.color.body_text_light_grey);
        } else {
            this.f23083k.setText(((int) (arrangementVersionLite.rating.floatValue() * 100.0f)) + "% (" + this.f23087q.m18998a((long) arrangementVersionLite.totalVotes) + ")");
            if (arrangementVersionLite.highlyRated) {
                drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_green);
                i2 = getContext().getResources().getColor(C1947R.color.cccp_highly_rated);
            } else {
                drawable = getResources().getDrawable(C1947R.drawable.icn_thumbs_up_gray);
                i2 = getContext().getResources().getColor(C1947R.color.body_text_light_grey);
            }
        }
        this.f23083k.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        this.f23083k.setTextColor(i2);
        this.f23083k.setVisibility(0);
        this.f23082j.setCompoundDrawablesWithIntrinsicBounds(arrangementVersionLite.accountIcon.c() ? C1947R.drawable.icn_verified : C1947R.drawable.icn_user, 0, 0, 0);
        this.f23082j.setCompoundDrawablePadding(arrangementVersionLite.accountIcon.c() ? 0 : getResources().getDimensionPixelOffset(C1947R.dimen.margin_compound_drawable));
        this.f23082j.setText(arrangementVersionLite.accountIcon.handle);
        this.f23082j.setVisibility(0);
        this.f23079g.setVisibility(8);
        View view = this.f23076d;
        if (z) {
            i = 8;
        }
        view.setVisibility(i);
        if (OnboardingActivity.f18978h) {
            this.f23082j.setVisibility(8);
            this.f23083k.setVisibility(8);
        }
    }

    public void setAlbumArtClickListener(OnClickListener onClickListener) {
        if (this.ae.f21796a != null) {
            this.ae.f21796a.setOnClickListener(onClickListener);
        } else {
            Log.e(f23072o, "setAlbumArtClickListener - mAlbumArtImageView is null");
        }
    }

    public void setHeaderTextAndShowHeader(String str) {
        this.f23085m.setText(str);
        this.f23084l.setVisibility(0);
    }

    public void m24382c() {
        this.f23084l.setVisibility(8);
    }

    public void setLastItemBottomPaddingVisibility(boolean z) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f23073a.getLayoutParams();
        marginLayoutParams.bottomMargin = z ? getResources().getDimensionPixelOffset(C1947R.dimen.row_single_height) : 0;
        this.f23073a.setLayoutParams(marginLayoutParams);
    }

    public void setShowListHeader(boolean z) {
        this.f23074b.setVisibility(z ? 0 : 8);
    }

    public void setListHeaderText(String str) {
        this.f23074b.setText(str);
    }

    public void setFastScrollEnabled(boolean z) {
        this.f23089s = z;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f23075c.setOnTouchListener(onTouchListener);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f23075c.setOnClickListener(onClickListener);
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        return m24381a(motionEvent) || super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return m24381a(motionEvent) || super.onInterceptTouchEvent(motionEvent);
    }

    protected boolean m24381a(MotionEvent motionEvent) {
        if (this.f23089s && motionEvent.getActionMasked() == 0) {
            if (motionEvent.getX() > ((float) (getMeasuredWidth() - getResources().getDimensionPixelSize(C1947R.dimen.fast_scroll_touch_width)))) {
                return true;
            }
        }
        return false;
    }

    public void m24383d() {
        this.ae.f21796a.setImageDrawable(null);
        this.f23088r.m18973a();
        setOnTouchListener(null);
        setOnClickListener(null);
    }
}
