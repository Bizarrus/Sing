package com.smule.singandroid.list_items;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.smule.singandroid.C1947R;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class VocalEffectListItem_ extends VocalEffectListItem implements HasViews, OnViewChangedListener {
    private boolean f23374u = false;
    private final OnViewChangedNotifier f23375v = new OnViewChangedNotifier();

    public VocalEffectListItem_(Context context) {
        super(context);
        m24546d();
    }

    public static VocalEffectListItem m24545b(Context context) {
        VocalEffectListItem vocalEffectListItem_ = new VocalEffectListItem_(context);
        vocalEffectListItem_.onFinishInflate();
        return vocalEffectListItem_;
    }

    public void onFinishInflate() {
        if (!this.f23374u) {
            this.f23374u = true;
            inflate(getContext(), C1947R.layout.vocal_effect_list_item, this);
            this.f23375v.a(this);
        }
        super.onFinishInflate();
    }

    private void m24546d() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f23375v);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24547a(HasViews hasViews) {
        this.a = hasViews.findViewById(C1947R.id.vocal_effect_leading_space);
        this.b = hasViews.findViewById(C1947R.id.vocal_effect_trailing_space);
        this.c = hasViews.findViewById(C1947R.id.vocal_effect_base);
        this.d = (TextView) hasViews.findViewById(C1947R.id.vocal_effect_text);
        this.e = hasViews.findViewById(C1947R.id.vocal_effect_expanded);
        this.f = (ImageView) hasViews.findViewById(C1947R.id.vocal_effect_vip_badge_image_view);
        this.g = (ProgressBar) hasViews.findViewById(C1947R.id.spinner);
        this.h = hasViews.findViewById(C1947R.id.fx_param_harmony);
        this.i = hasViews.findViewById(C1947R.id.fx_param_reverb);
        this.j = hasViews.findViewById(C1947R.id.fx_param_pitch_correction);
        this.k = hasViews.findViewById(C1947R.id.fx_param_room_size);
        this.l = (ToggleButton) hasViews.findViewById(C1947R.id.harmony_toggle);
        this.m = (SeekBar) hasViews.findViewById(C1947R.id.reverb_seekbar);
        this.n = (SeekBar) hasViews.findViewById(C1947R.id.pitch_correction_seekbar);
        this.o = (SeekBar) hasViews.findViewById(C1947R.id.room_size_seekbar);
        this.p = (TextView) hasViews.findViewById(C1947R.id.toggle_entire_song_option);
        this.q = (TextView) hasViews.findViewById(C1947R.id.toggle_chorus_only_option);
        this.r = (TextView) hasViews.findViewById(C1947R.id.reverb_percentage);
        this.s = (TextView) hasViews.findViewById(C1947R.id.pitch_correction_percentage);
        this.t = (TextView) hasViews.findViewById(C1947R.id.room_size_percentage);
    }
}
