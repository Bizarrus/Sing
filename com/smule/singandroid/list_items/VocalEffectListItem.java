package com.smule.singandroid.list_items;

import android.content.Context;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.customviews.VocalEffectList;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.ArrayList;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class VocalEffectListItem extends LinearLayout {
    private static final String f23341u = VocalEffectListItem.class.getName();
    private ColorFilter f23342A;
    private VocalEffectList f23343B;
    private ArrayList<Param> f23344C = new ArrayList();
    private float f23345D;
    private float f23346E;
    private boolean f23347F;
    private boolean f23348G = false;
    @ViewById
    protected View f23349a;
    @ViewById
    protected View f23350b;
    @ViewById
    protected View f23351c;
    @ViewById
    protected TextView f23352d;
    @ViewById
    protected View f23353e;
    @ViewById
    protected ImageView f23354f;
    @ViewById
    protected ProgressBar f23355g;
    @ViewById
    protected View f23356h;
    @ViewById
    protected View f23357i;
    @ViewById
    protected View f23358j;
    @ViewById
    protected View f23359k;
    @ViewById
    protected ToggleButton f23360l;
    @ViewById
    protected SeekBar f23361m;
    @ViewById
    protected SeekBar f23362n;
    @ViewById
    protected SeekBar f23363o;
    @ViewById
    protected TextView f23364p;
    @ViewById
    protected TextView f23365q;
    @ViewById
    protected TextView f23366r;
    @ViewById
    protected TextView f23367s;
    @ViewById
    protected TextView f23368t;
    private VocalEffect f23369v;
    private boolean f23370w = false;
    private int f23371x;
    private int f23372y;
    private int f23373z;

    class C47091 implements OnClickListener {
        final /* synthetic */ VocalEffectListItem f23323a;

        C47091(VocalEffectListItem vocalEffectListItem) {
            this.f23323a = vocalEffectListItem;
        }

        public void onClick(View view) {
            Log.b(VocalEffectListItem.f23341u, "Vocal effect image button clicked");
            if (!this.f23323a.f23370w && this.f23323a.isClickable()) {
                this.f23323a.f23343B.m23548a(this.f23323a.f23371x, this.f23323a.f23345D, this.f23323a.f23346E, this.f23323a.f23347F, true);
            }
        }
    }

    class C47102 implements OnClickListener {
        final /* synthetic */ VocalEffectListItem f23324a;

        C47102(VocalEffectListItem vocalEffectListItem) {
            this.f23324a = vocalEffectListItem;
        }

        public void onClick(View view) {
        }
    }

    class C47113 implements OnCheckedChangeListener {
        final /* synthetic */ VocalEffectListItem f23325a;

        C47113(VocalEffectListItem vocalEffectListItem) {
            this.f23325a = vocalEffectListItem;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f23325a.f23364p.setTextColor(z ? this.f23325a.getResources().getColor(C1947R.color.body_text_dark_grey) : this.f23325a.f23369v.m21985c(this.f23325a.getContext()));
            this.f23325a.f23365q.setTextColor(z ? this.f23325a.f23369v.m21985c(this.f23325a.getContext()) : this.f23325a.getResources().getColor(C1947R.color.body_text_dark_grey));
            this.f23325a.f23364p.setTypeface(z ? TypefaceUtils.m26188a() : TypefaceUtils.m26191b());
            this.f23325a.f23365q.setTypeface(z ? TypefaceUtils.m26191b() : TypefaceUtils.m26188a());
            this.f23325a.m24526a(Param.HARMONY, z ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : 0.0f, !this.f23325a.f23348G);
        }
    }

    class C47124 implements OnSeekBarChangeListener {
        final /* synthetic */ VocalEffectListItem f23326a;

        C47124(VocalEffectListItem vocalEffectListItem) {
            this.f23326a = vocalEffectListItem;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            boolean z2 = true;
            this.f23326a.f23366r.setText(String.format(this.f23326a.getResources().getString(C1947R.string.cm_percentage), new Object[]{Integer.valueOf(i)}));
            VocalEffectListItem vocalEffectListItem = this.f23326a;
            Param param = Param.REVERB;
            float f = ((float) i) / 100.0f;
            if (this.f23326a.f23348G) {
                z2 = false;
            }
            vocalEffectListItem.m24526a(param, f, z2);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C47135 implements OnSeekBarChangeListener {
        final /* synthetic */ VocalEffectListItem f23327a;

        C47135(VocalEffectListItem vocalEffectListItem) {
            this.f23327a = vocalEffectListItem;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            boolean z2 = true;
            this.f23327a.f23367s.setText(String.format(this.f23327a.getResources().getString(C1947R.string.cm_percentage), new Object[]{Integer.valueOf(i)}));
            VocalEffectListItem vocalEffectListItem = this.f23327a;
            Param param = Param.PITCH_CORRECTION;
            float f = ((float) i) / 100.0f;
            if (this.f23327a.f23348G) {
                z2 = false;
            }
            vocalEffectListItem.m24526a(param, f, z2);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C47146 implements OnSeekBarChangeListener {
        final /* synthetic */ VocalEffectListItem f23328a;

        C47146(VocalEffectListItem vocalEffectListItem) {
            this.f23328a = vocalEffectListItem;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            boolean z2 = true;
            this.f23328a.f23368t.setText(String.format(this.f23328a.getResources().getString(C1947R.string.cm_percentage), new Object[]{Integer.valueOf(i)}));
            VocalEffectListItem vocalEffectListItem = this.f23328a;
            Param param = Param.ROOM_SIZE;
            float f = ((float) i) / 100.0f;
            if (this.f23328a.f23348G) {
                z2 = false;
            }
            vocalEffectListItem.m24526a(param, f, z2);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    class C47157 implements OnTouchListener {
        final /* synthetic */ VocalEffectListItem f23329a;

        C47157(VocalEffectListItem vocalEffectListItem) {
            this.f23329a = vocalEffectListItem;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f23329a.f23343B.requestDisallowInterceptTouchEvent(true);
            return false;
        }
    }

    private class FadeExpandAnimation extends Animation {
        final /* synthetic */ VocalEffectListItem f23331a;
        private View f23332b;
        private View f23333c;
        private float f23334d;
        private float f23335e;

        public FadeExpandAnimation(VocalEffectListItem vocalEffectListItem, boolean z) {
            this.f23331a = vocalEffectListItem;
            if (z) {
                this.f23334d = (float) vocalEffectListItem.f23372y;
                this.f23335e = (float) vocalEffectListItem.f23373z;
                this.f23332b = vocalEffectListItem.f23352d;
                this.f23333c = vocalEffectListItem.f23353e;
                return;
            }
            this.f23334d = (float) vocalEffectListItem.f23373z;
            this.f23335e = (float) vocalEffectListItem.f23372y;
            this.f23332b = vocalEffectListItem.f23353e;
            this.f23333c = vocalEffectListItem.f23352d;
        }

        public boolean willChangeBounds() {
            return true;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            super.applyTransformation(f, transformation);
            if (f == 0.0f) {
                this.f23333c.setVisibility(0);
            }
            this.f23332b.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - f);
            this.f23333c.setAlpha(f);
            if (f == DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
                this.f23332b.setVisibility(4);
            }
            this.f23331a.f23351c.getLayoutParams().width = (int) (((this.f23335e - this.f23334d) * f) + this.f23334d);
            this.f23331a.f23351c.requestLayout();
        }
    }

    public enum Param {
        HARMONY,
        REVERB,
        PITCH_CORRECTION,
        ROOM_SIZE
    }

    public static VocalEffectListItem m24525a(Context context) {
        return VocalEffectListItem_.m24545b(context);
    }

    public VocalEffectListItem(Context context) {
        super(context);
    }

    public void setBusy(boolean z) {
        this.f23355g.setVisibility(z ? 0 : 8);
    }

    public void m24543a(VocalEffect vocalEffect, int i, VocalEffectList vocalEffectList, boolean z, float f, float f2) {
        boolean z2 = true;
        Log.b(f23341u, "Configuring with vocal effect: " + vocalEffect.toString());
        this.f23369v = vocalEffect;
        this.f23371x = i;
        this.f23343B = vocalEffectList;
        this.f23347F = z;
        if (z) {
            this.f23352d.setText(getResources().getString(C1947R.string.fx_match_fx));
            this.f23352d.setTextColor(getResources().getColor(C1947R.color.review_fx_match_fx));
        } else {
            this.f23352d.setText(vocalEffect.m21982a(getContext()));
            this.f23352d.setTextColor(this.f23369v.m21985c(getContext()));
        }
        m24535e();
        if (vocalEffect.m21984b()) {
            if (z) {
                this.f23345D = f;
                this.f23346E = f2;
            } else {
                this.f23351c.setBackgroundResource(C1947R.drawable.review_fx_square);
                m24534d();
            }
        }
        if (z) {
            setColor(getResources().getColor(C1947R.color.review_fx_match_fx));
        } else {
            setColor(vocalEffect.m21985c(getContext()));
        }
        if (!vocalEffect.m21987e() || z) {
            this.f23354f.setVisibility(8);
        } else {
            this.f23354f.setVisibility(0);
        }
        this.f23351c.setOnClickListener(new C47091(this));
        this.f23353e.setOnClickListener(new C47102(this));
        boolean z3 = i == 0;
        if (i != vocalEffectList.getNumFX() - 1) {
            z2 = false;
        }
        m24528a(z3, z2);
    }

    private void m24534d() {
        boolean z = true;
        this.f23348G = true;
        OnCheckedChangeListener c47113 = new C47113(this);
        this.f23360l.setOnCheckedChangeListener(c47113);
        this.f23361m.setOnSeekBarChangeListener(new C47124(this));
        this.f23362n.setOnSeekBarChangeListener(new C47135(this));
        this.f23363o.setOnSeekBarChangeListener(new C47146(this));
        this.f23345D = VocalEffect.m21974a(getContext(), this.f23369v.m21986c());
        this.f23346E = VocalEffect.m21978b(getContext(), this.f23369v.m21986c());
        switch (this.f23369v) {
            case SUPER_HARMONY:
                this.f23372y = (int) getResources().getDimension(C1947R.dimen.review_fx_size);
                this.f23373z = (int) getResources().getDimension(C1947R.dimen.review_fx_super_harmonizer_width);
                this.f23356h.setVisibility(0);
                this.f23357i.setVisibility(8);
                this.f23358j.setVisibility(0);
                this.f23359k.setVisibility(8);
                this.f23344C.add(Param.HARMONY);
                this.f23344C.add(Param.PITCH_CORRECTION);
                if (this.f23345D <= 0.5f) {
                    z = false;
                }
                this.f23360l.setChecked(z);
                c47113.onCheckedChanged(this.f23360l, z);
                this.f23362n.setProgress((int) (this.f23346E * 100.0f));
                break;
            case SUPER_POP:
                this.f23372y = (int) getResources().getDimension(C1947R.dimen.review_fx_size);
                this.f23373z = (int) getResources().getDimension(C1947R.dimen.review_fx_super_pop_width);
                this.f23356h.setVisibility(8);
                this.f23357i.setVisibility(0);
                this.f23358j.setVisibility(0);
                this.f23359k.setVisibility(8);
                this.f23344C.add(Param.REVERB);
                this.f23344C.add(Param.PITCH_CORRECTION);
                this.f23361m.setProgress((int) (this.f23345D * 100.0f));
                this.f23362n.setProgress((int) (this.f23346E * 100.0f));
                break;
            case SUPER_STUDIO:
                this.f23372y = (int) getResources().getDimension(C1947R.dimen.review_fx_size);
                this.f23373z = (int) getResources().getDimension(C1947R.dimen.review_fx_super_studio_width);
                this.f23356h.setVisibility(8);
                this.f23357i.setVisibility(0);
                this.f23358j.setVisibility(8);
                this.f23359k.setVisibility(0);
                this.f23344C.add(Param.REVERB);
                this.f23344C.add(Param.ROOM_SIZE);
                this.f23361m.setProgress((int) (this.f23345D * 100.0f));
                this.f23363o.setProgress((int) (this.f23346E * 100.0f));
                break;
        }
        OnTouchListener c47157 = new C47157(this);
        this.f23361m.setOnTouchListener(c47157);
        this.f23362n.setOnTouchListener(c47157);
        this.f23363o.setOnTouchListener(c47157);
        this.f23348G = false;
    }

    private void setColor(int i) {
        this.f23342A = new PorterDuffColorFilter(i, Mode.MULTIPLY);
        this.f23351c.getBackground().setColorFilter(this.f23342A);
        this.f23360l.getBackground().setColorFilter(this.f23342A);
        this.f23361m.getProgressDrawable().setColorFilter(this.f23342A);
        this.f23362n.getProgressDrawable().setColorFilter(this.f23342A);
        this.f23363o.getProgressDrawable().setColorFilter(this.f23342A);
        Drawable drawable = getResources().getDrawable(C1947R.drawable.thumb_circle_white);
        Drawable drawable2 = getResources().getDrawable(C1947R.drawable.thumb_circle_white);
        Drawable drawable3 = getResources().getDrawable(C1947R.drawable.thumb_circle_white);
        drawable.setColorFilter(this.f23342A);
        drawable2.setColorFilter(this.f23342A);
        drawable3.setColorFilter(this.f23342A);
        this.f23361m.setThumb(drawable);
        this.f23362n.setThumb(drawable2);
        this.f23363o.setThumb(drawable3);
    }

    private void m24526a(Param param, float f, boolean z) {
        if (this.f23344C.indexOf(param) == 0) {
            this.f23345D = f;
            VocalEffect.m21976a(getContext(), this.f23369v.m21986c(), f);
        } else {
            this.f23346E = f;
            VocalEffect.m21980b(getContext(), this.f23369v.m21986c(), f);
        }
        this.f23343B.m23547a(this.f23371x, this.f23345D, this.f23346E, z);
    }

    private void m24528a(boolean z, boolean z2) {
        int i;
        int i2 = 0;
        View view = this.f23349a;
        if (z) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.f23350b;
        if (!z2) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    private void m24535e() {
        this.f23354f.setVisibility(4);
        this.f23352d.setVisibility(0);
        this.f23356h.setVisibility(8);
        this.f23357i.setVisibility(8);
        this.f23358j.setVisibility(8);
        this.f23359k.setVisibility(8);
    }

    public Animation m24542a() {
        Animation animation = null;
        if (!this.f23369v.m21984b() || this.f23347F) {
            this.f23351c.setBackgroundResource(C1947R.drawable.review_fx_circle_filled);
            this.f23351c.getBackground().setColorFilter(this.f23342A);
            this.f23352d.setTextColor(getResources().getColor(C1947R.color.background_white));
        } else {
            animation = new FadeExpandAnimation(this, true);
        }
        this.f23370w = true;
        return animation;
    }

    public Animation m24544b() {
        Animation animation;
        if (!this.f23369v.m21984b() || this.f23347F) {
            this.f23351c.setBackgroundResource(C1947R.drawable.review_fx_circle_unfilled);
            this.f23351c.getBackground().setColorFilter(this.f23342A);
            this.f23352d.setTextColor(this.f23347F ? getResources().getColor(C1947R.color.review_fx_match_fx) : this.f23369v.m21985c(getContext()));
            animation = null;
        } else {
            animation = new FadeExpandAnimation(this, false);
        }
        this.f23370w = false;
        return animation;
    }
}
