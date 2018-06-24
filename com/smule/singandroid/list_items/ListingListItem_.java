package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.PlayableItemView;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ListingListItem_ extends ListingListItem implements HasViews, OnViewChangedListener {
    private boolean f23090o = false;
    private final OnViewChangedNotifier f23091p = new OnViewChangedNotifier();

    public ListingListItem_(Context context) {
        super(context);
        m24385e();
    }

    public static ListingListItem m24384b(Context context) {
        ListingListItem listingListItem_ = new ListingListItem_(context);
        listingListItem_.onFinishInflate();
        return listingListItem_;
    }

    public void onFinishInflate() {
        if (!this.f23090o) {
            this.f23090o = true;
            inflate(getContext(), C1947R.layout.listing_list_item, this);
            this.f23091p.a(this);
        }
        super.onFinishInflate();
    }

    private void m24385e() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23091p);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24386a(HasViews hasViews) {
        this.ae = (PlayableItemView) hasViews.findViewById(C1947R.id.album_art_container_view);
        this.a = (LinearLayout) hasViews.findViewById(C1947R.id.root);
        this.b = (TextView) hasViews.findViewById(C1947R.id.listing_list_item_amazing_header);
        this.c = (ViewGroup) hasViews.findViewById(C1947R.id.list_item_container);
        this.d = hasViews.findViewById(C1947R.id.divider_line);
        this.e = (TextView) hasViews.findViewById(C1947R.id.song_title_textview);
        this.f = (TextView) hasViews.findViewById(C1947R.id.artist_textview);
        this.g = (TextView) hasViews.findViewById(C1947R.id.featured_label_textview);
        this.h = (ImageView) hasViews.findViewById(C1947R.id.vip_only_image_view);
        this.i = (TextView) hasViews.findViewById(C1947R.id.no_lyrics_textview);
        this.j = (TextView) hasViews.findViewById(C1947R.id.arranger_textview);
        this.k = (TextView) hasViews.findViewById(C1947R.id.ratings_textview);
        this.l = hasViews.findViewById(C1947R.id.header);
        this.m = (TextView) hasViews.findViewById(C1947R.id.mHeaderTextView);
        m23042p();
    }
}
