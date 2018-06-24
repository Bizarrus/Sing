package com.smule.singandroid.upsell;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.managers.LocalizationManager;
import com.smule.singandroid.C1947R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class LearnMoreFeatureRowView extends RelativeLayout {
    @ViewById
    protected ImageView f24537a;
    @ViewById
    protected TextView f24538b;
    @ViewById
    protected View f24539c;
    @ViewById
    protected TextView f24540d;
    @ViewById
    protected TextView f24541e;
    private Context f24542f;
    private FeatureType f24543g;

    public enum FeatureType {
        UNLIMITED_SONGS(C1947R.string.learn_more_row_unlimited_songs, "basic_song_text", "vip_song_title", "vip_song_subtitle", C1947R.drawable.icn_unlimited_songs, C1947R.color.learn_more_unlimited_songs),
        AD_FREE(C1947R.string.learn_more_row_ad_free, "basic_ads_text", "vip_ads_title", "vip_ads_subtitle", C1947R.drawable.icn_ad_free, C1947R.color.learn_more_ad_free),
        ADVANCED_FILTERS(C1947R.string.learn_more_row_advanced_filters, "basic_fx_text", "vip_fx_title", "vip_fx_subtitle", C1947R.drawable.icn_advanced_filters, C1947R.color.learn_more_advanced_filters),
        STORAGE(C1947R.string.learn_more_row_archive, "basic_profile_storage_text", "vip_profile_storage_title", "vip_profile_storage_subtitle", C1947R.drawable.icn_storage, C1947R.color.learn_more_storage);
        
        private final int f24531e;
        private final String f24532f;
        private final String f24533g;
        private final String f24534h;
        private final int f24535i;
        private final int f24536j;

        private FeatureType(int i, String str, String str2, String str3, int i2, int i3) {
            this.f24531e = i;
            this.f24532f = str;
            this.f24533g = str2;
            this.f24534h = str3;
            this.f24535i = i2;
            this.f24536j = i3;
        }

        public int m25720a() {
            return this.f24531e;
        }

        public String m25721b() {
            return this.f24532f;
        }

        public String m25722c() {
            return this.f24533g;
        }

        public String m25723d() {
            return this.f24534h;
        }

        public int m25724e() {
            return this.f24535i;
        }

        public int m25725f() {
            return this.f24536j;
        }
    }

    public LearnMoreFeatureRowView(Context context, FeatureType featureType) {
        super(context);
        this.f24542f = context;
        this.f24543g = featureType;
    }

    @AfterViews
    protected void m25730a() {
        setFeatureType(this.f24543g);
    }

    private void setFeatureType(FeatureType featureType) {
        this.f24537a.setBackground(m25726a(featureType.m25724e()));
        this.f24539c.setBackgroundColor(m25729b(featureType.m25725f()));
        this.f24538b.setText(this.f24542f.getString(featureType.m25720a()));
        this.f24538b.setTextColor(m25729b(featureType.m25725f()));
        this.f24540d.setText(m25727a(featureType.m25721b()));
        this.f24541e.setText(m25728a(featureType.m25722c(), featureType.m25723d()));
    }

    private Drawable m25726a(int i) {
        return ContextCompat.getDrawable(this.f24542f, i);
    }

    private int m25729b(int i) {
        return ContextCompat.getColor(this.f24542f, i);
    }

    private String m25727a(String str) {
        return LocalizationManager.a().a("learn_more", str);
    }

    private String m25728a(String str, String str2) {
        return m25727a(str) + " " + m25727a(str2);
    }
}
