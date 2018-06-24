package com.smule.singandroid.upsell;

import com.smule.singandroid.C1947R;

public enum UpsellType {
    VIP_SONG("vip_song", "vip_song_title", "vip_song_subtitle", C1947R.color.upsell_sky_indigo_end, C1947R.drawable.icn_unlimited_songs_upsell),
    BANNER("vip_banner", "vip_banner_title", "vip_banner_subtitle", C1947R.color.upsell_sky_indigo_end, C1947R.drawable.icn_unlimited_songs_upsell),
    NATIVE_ADS_OVERFLOW("native_overflow", "native_overflow_title", "native_overflow_subtitle", C1947R.color.upsell_sky_indigo_end, C1947R.drawable.icn_ad_free_upsell),
    NATIVE_ADS_ABOUT("native_about", "native_about_title", "native_about_subtitle", C1947R.color.upsell_sky_indigo_end, C1947R.drawable.icn_ad_free_upsell),
    AUDIO_FX("vip_fx", "vip_fx_title", "vip_fx_subtitle", C1947R.color.upsell_pink_purple_end, -1),
    PRE_ROLL("song", null, null, C1947R.color.upsell_sky_indigo_end, -1),
    POST_SONG("post-song", null, null, -1, -1),
    STORAGE("profile_storage", "profile_storage_title", "profile_storage_subtitle", C1947R.color.upsell_blue_red_end, C1947R.drawable.icn_storage_upsell),
    BOOST_OVERFLOW("promoted_overflow", null, null, -1, -1),
    BOOST_ABOUT("promoted_about", null, null, -1, -1),
    CUSTOM_PROFILE("profile_edit", "profile_storage_title", "profile_storage_subtitle", C1947R.color.upsell_blue_red_end, C1947R.drawable.icn_unlimited_songs_upsell);
    
    private final String f24643l;
    private final String f24644m;
    private final String f24645n;
    private final int f24646o;
    private final int f24647p;

    private UpsellType(String str, String str2, String str3, int i, int i2) {
        this.f24643l = str;
        this.f24644m = str2;
        this.f24645n = str3;
        this.f24646o = i;
        this.f24647p = i2;
    }

    public String m25792a() {
        return this.f24643l;
    }

    public String m25793b() {
        return this.f24644m;
    }

    public String m25794c() {
        return this.f24645n;
    }

    public int m25795d() {
        return this.f24646o;
    }

    public int m25796e() {
        return this.f24647p;
    }
}
