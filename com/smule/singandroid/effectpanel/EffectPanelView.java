/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.animation.TimeInterpolator
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.ColorFilter
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.graphics.drawable.Drawable
 *  android.support.annotation.ColorInt
 *  android.support.design.widget.TabLayout
 *  android.support.design.widget.TabLayout$OnTabSelectedListener
 *  android.support.design.widget.TabLayout$Tab
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.view.PagerAdapter
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  android.text.TextUtils
 *  android.util.AttributeSet
 *  android.util.Pair
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.view.animation.AccelerateDecelerateInterpolator
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.FrameLayout
 *  android.widget.FrameLayout$LayoutParams
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  android.widget.Toast
 *  com.smule.singandroid.utils.LayoutUtils
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$ColorFilterType
 *  com.smule.singandroid.video.VideoEffects$Intensity
 *  com.smule.singandroid.video.VideoEffects$VideoStyleType
 *  com.smule.singandroid.video.VideoFilterManager
 *  com.smule.singandroid.video.VideoFilterManager$ColorFilterItem
 *  com.smule.singandroid.video.VideoFilterManager$ColorFilterItemSorter
 *  com.smule.singandroid.video.VideoFilterManager$VideoStyleItem
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.effectpanel;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.smule.android.utils.EnumUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.UiHandler;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.CustomViewPager;
import com.smule.singandroid.DeviceSettings;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.adapters.singflow.ColorFilterAdapter;
import com.smule.singandroid.adapters.singflow.VideoStylesAdapter;
import com.smule.singandroid.adapters.singflow.VocalEffectsAdapter;
import com.smule.singandroid.common.CenterLayoutManager;
import com.smule.singandroid.customviews.CyclableStateItemViewModel;
import com.smule.singandroid.customviews.QuickSelectControlItemViewModel;
import com.smule.singandroid.effectpanel.AlyceSecondLayerView;
import com.smule.singandroid.effectpanel.AutoTuneControllerView;
import com.smule.singandroid.effectpanel.AutoTuneControllerView_;
import com.smule.singandroid.effectpanel.EffectPanelJoinerVideoTab;
import com.smule.singandroid.effectpanel.EffectPanelJoinerVideoTab_;
import com.smule.singandroid.effectpanel.EffectPanelRecyclerView;
import com.smule.singandroid.effectpanel.EffectPanelRecyclerView_;
import com.smule.singandroid.effectpanel.QuickSelectControlView;
import com.smule.singandroid.effectpanel.VocalEffectPanelRecyclerView;
import com.smule.singandroid.effectpanel.VocalEffectPanelRecyclerView_;
import com.smule.singandroid.effectpanel.VocalMatchControllerView;
import com.smule.singandroid.effectpanel.VocalMatchControllerView_;
import com.smule.singandroid.effectpanel.VolumeControllerView;
import com.smule.singandroid.effectpanel.VolumeControllerView_;
import com.smule.singandroid.effectpanel.onclicklistners.OnColorFilterItemClickListener;
import com.smule.singandroid.effectpanel.onclicklistners.OnIntensityItemClickListener;
import com.smule.singandroid.effectpanel.onclicklistners.OnVideoStyleClickListener;
import com.smule.singandroid.effectpanel.onclicklistners.OnVocalEffectItemClickListener;
import com.smule.singandroid.preference.MagicPreferences;
import com.smule.singandroid.utils.LayoutUtils;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.video.VideoEffects;
import com.smule.singandroid.video.VideoFilterManager;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class EffectPanelView
extends RelativeLayout {
    private QuickSelectControlView A;
    private AutoTuneControllerView B;
    private ViewPhase C;
    private EffectPanelJoinerVideoTab D;
    private boolean E;
    private boolean F;
    private final SingServerValues G = new SingServerValues();
    private int H = -1;
    private EffectPanelPagerAdapter I;
    private String J;
    private final ArrayList<TabType> K = new ArrayList();
    private boolean L;
    private boolean M;
    private boolean N;
    private boolean O;
    private boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean T;
    private String U;
    private int V;
    private int W = 100;
    @ViewById
    protected TabLayout a;
    private int aa = -1;
    private int ab;
    private int ac;
    private AnimatorSet ad;
    private AnimatorSet ae;
    private VideoEffects.Intensity af;
    private String ag;
    private String ah;
    private List<VideoFilterManager.VideoStyleItem> ai;
    private final ArrayList<VideoFilterManager.ColorFilterItem> aj = new ArrayList();
    private VocalEffectsAdapter ak;
    private ArrayList<ParamType> al = new ArrayList();
    private OnVideoStyleClickListener am;
    private OnColorFilterItemClickListener an;
    private OnIntensityItemClickListener ao;
    private VocalEffect ap;
    private Runnable aq;
    private View.OnTouchListener ar;
    private SeekBar.OnSeekBarChangeListener as;
    private SeekBar.OnSeekBarChangeListener at;
    private int au = -1;
    private final DeviceSettings av = new DeviceSettings();
    private Observer aw;
    @ViewById
    protected CustomViewPager b;
    @ViewById
    protected RelativeLayout c;
    @ViewById
    protected RelativeLayout d;
    @ViewById
    protected ImageView e;
    @ViewById
    protected LinearLayout f;
    @ViewById
    protected TextView g;
    @ViewById
    protected RecyclerView h;
    @ViewById
    protected AlyceSecondLayerView i;
    @ViewById
    protected RelativeLayout j;
    @ViewById
    protected TextView k;
    @ViewById
    protected SeekBar l;
    @ViewById
    protected RelativeLayout m;
    @ViewById
    protected TextView n;
    @ViewById
    protected SeekBar o;
    @ViewById
    protected RelativeLayout p;
    @ViewById
    protected TextView q;
    @ViewById
    protected SeekBar r;
    @ViewById
    protected RelativeLayout s;
    @ViewById
    protected TextView t;
    @ViewById
    protected SeekBar u;
    @ViewById
    protected RelativeLayout v;
    @ViewById
    protected TextView w;
    @ViewById
    protected TextView x;
    protected CompoundButton.OnCheckedChangeListener y;
    private OnVocalParamsUpdateListener z;

    public EffectPanelView(Context context) {
        this(context, null);
    }

    public EffectPanelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aw = new Observer(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void update(Observable observable, Object object) {
                if (EffectPanelView.this.an != null && object instanceof Pair) {
                    observable = (Pair)object;
                    object = (String)observable.first;
                    int n = (Integer)observable.second;
                    EffectPanelView.this.A.a(n);
                    if (object.startsWith("VIEW_TYPE_ID_COLOR")) {
                        EffectPanelView.this.i.a(n);
                        EffectPanelView.this.an.a((VideoFilterManager.ColorFilterItem)EffectPanelView.this.aj.get(n), n);
                    } else if (object.startsWith("VIEW_TYPE_ID_INTENSITY")) {
                        EffectPanelView.this.i.b(n);
                        EffectPanelView.this.ao.a(n);
                    }
                    EffectPanelView.this.A.b();
                }
            }
        };
    }

    static /* synthetic */ int a(EffectPanelView effectPanelView, int n) {
        effectPanelView.au = n;
        return n;
    }

    /*
     * Enabled aggressive block sorting
     */
    private Drawable a(TabType tabType, boolean bl, boolean bl2) {
        Object object;
        if (tabType != TabType.c) {
            object = this.getContext();
            int n = bl ? tabType.b() : tabType.c();
            tabType = ContextCompat.getDrawable((Context)object, (int)n);
        } else if (this.C == ViewPhase.d) {
            tabType = this.getContext();
            int n = bl ? 2130838069 : 2130838068;
            tabType = ContextCompat.getDrawable((Context)tabType, (int)n);
        } else {
            tabType = this.getContext();
            int n = bl ? TabType.c.b() : TabType.c.c();
            tabType = ContextCompat.getDrawable((Context)tabType, (int)n);
        }
        object = tabType;
        if (!bl2) return object;
        return tabType.mutate();
    }

    static /* synthetic */ Drawable a(EffectPanelView effectPanelView, TabType tabType, boolean bl, boolean bl2) {
        return effectPanelView.a(tabType, bl, bl2);
    }

    private View a(VocalEffectsAdapter object) {
        VocalEffectPanelRecyclerView vocalEffectPanelRecyclerView = VocalEffectPanelRecyclerView_.a(this.getContext());
        vocalEffectPanelRecyclerView.setAdapter((RecyclerView.Adapter)object);
        VocalEffectPanelRecyclerView vocalEffectPanelRecyclerView2 = vocalEffectPanelRecyclerView;
        if (this.d()) {
            vocalEffectPanelRecyclerView2 = vocalEffectPanelRecyclerView;
            if (this.C == ViewPhase.d) {
                object = object.a();
                this.B = AutoTuneControllerView_.a(this.getContext());
                this.B.a(this.y, object.d(this.getContext()));
                vocalEffectPanelRecyclerView2 = this.a(vocalEffectPanelRecyclerView);
            }
        }
        return vocalEffectPanelRecyclerView2;
    }

    private View a(TabType tabType) {
        return this.findViewWithTag((Object)tabType.a());
    }

    private View a(VocalEffectPanelRecyclerView vocalEffectPanelRecyclerView) {
        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setOrientation(0);
        linearLayout.setLayoutParams((ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setGravity(16);
        linearLayout.addView((View)this.B);
        linearLayout.addView((View)vocalEffectPanelRecyclerView);
        return linearLayout;
    }

    private List<CyclableStateItemViewModel> a(ArrayList<VideoEffects.ColorFilterType> object) {
        ArrayList<CyclableStateItemViewModel> arrayList = new ArrayList<CyclableStateItemViewModel>();
        String string2 = this.getResources().getString(2131296888);
        object = object.iterator();
        while (object.hasNext()) {
            VideoEffects.ColorFilterType colorFilterType = (VideoEffects.ColorFilterType)object.next();
            arrayList.add(new CyclableStateItemViewModel(ContextCompat.getColor((Context)this.getContext(), (int)colorFilterType.c()), 2131297781, string2));
            this.aj.add(new VideoFilterManager.ColorFilterItem(colorFilterType));
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(ParamType paramType, float f, boolean bl) {
        float f2;
        float f3;
        if (this.al.indexOf((Object)paramType) == 0) {
            VocalEffect.a(this.getContext(), this.ap.b(), f);
            f2 = VocalEffect.b(this.getContext(), this.ap.b());
            f3 = f;
        } else {
            VocalEffect.b(this.getContext(), this.ap.b(), f);
            f3 = VocalEffect.a(this.getContext(), this.ap.b());
            f2 = f;
        }
        if (this.z != null) {
            this.z.a(this.ap.b(), f3, f2, bl);
        }
    }

    private void a(VideoEffects.VideoStyleType iterator) {
        this.aj.clear();
        iterator = iterator.d();
        Collections.sort(iterator, new VideoFilterManager.ColorFilterItemSorter());
        iterator = iterator.iterator();
        while (iterator.hasNext()) {
            VideoEffects.ColorFilterType colorFilterType = (VideoEffects.ColorFilterType)iterator.next();
            this.aj.add(new VideoFilterManager.ColorFilterItem(colorFilterType));
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void a(String arrn, List<QuickSelectControlItemViewModel> list, int n) {
        final boolean bl = "VIEW_TYPE_ID_COLOR".equals(arrn);
        arrn = (String)arrn + "." + this.ah;
        this.A.a((String)arrn, list);
        if (this.C == ViewPhase.a || this.C == ViewPhase.b) {
            arrn = new int[2];
            this.getLocationOnScreen(arrn);
            this.A.setY((float)(arrn[1] - this.getResources().getDimensionPixelSize(2131427767)));
        }
        this.A.a(n);
        if (!this.A.a()) {
            this.A.setOnAnimationEndRunnable(new Runnable(){

                @Override
                public void run() {
                    if (bl) {
                        EffectPanelView.this.i.setShowNextIconOnColorSelectorClick(false);
                        return;
                    }
                    EffectPanelView.this.i.setShowNextIconOnIntensitySelectorClick(false);
                }
            });
        }
        if (bl) {
            this.i.setShowNextIconOnColorSelectorClick(true);
            this.i.setShowNextIconOnIntensitySelectorClick(false);
        } else {
            this.i.setShowNextIconOnColorSelectorClick(false);
            this.i.setShowNextIconOnIntensitySelectorClick(true);
        }
        this.A.b();
    }

    private void a(List<QuickSelectControlItemViewModel> object, List<QuickSelectControlItemViewModel> object2) {
        object = new View.OnClickListener((List)object){
            final /* synthetic */ List a;
            {
                this.a = list;
            }

            public void onClick(View view) {
                int n = EffectPanelView.this.i.getColorSelectorPostion();
                EffectPanelView.this.an.a((VideoFilterManager.ColorFilterItem)EffectPanelView.this.aj.get(n), n);
                EffectPanelView.this.a("VIEW_TYPE_ID_COLOR", this.a, n);
            }
        };
        object2 = new View.OnClickListener((List)object2){
            final /* synthetic */ List a;
            {
                this.a = list;
            }

            public void onClick(View view) {
                int n = EffectPanelView.this.i.getIntensitySelectorPosition();
                EffectPanelView.this.ao.a(n);
                EffectPanelView.this.a("VIEW_TYPE_ID_INTENSITY", this.a, n);
            }
        };
        this.i.a((View.OnClickListener)object, (View.OnClickListener)object2);
        this.i.setShowNextIconOnColorSelectorClick(false);
        this.i.setShowNextIconOnIntensitySelectorClick(false);
    }

    static /* synthetic */ boolean a(EffectPanelView effectPanelView, boolean bl) {
        effectPanelView.Q = bl;
        return bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    private List<QuickSelectControlItemViewModel> b(ArrayList<VideoEffects.ColorFilterType> arrayList) {
        ArrayList<QuickSelectControlItemViewModel> arrayList2 = new ArrayList<QuickSelectControlItemViewModel>();
        int n = 0;
        while (n < arrayList.size()) {
            VideoEffects.ColorFilterType colorFilterType = arrayList.get(n);
            int n2 = ContextCompat.getColor((Context)this.getContext(), (int)colorFilterType.c());
            int n3 = colorFilterType == VideoEffects.ColorFilterType.g ? 2131297829 : 2131297826;
            arrayList2.add(new QuickSelectControlItemViewModel(n, n3, n2));
            ++n;
        }
        return arrayList2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void b(VideoEffects.VideoStyleType list) {
        int n;
        List<QuickSelectControlItemViewModel> list2;
        List<CyclableStateItemViewModel> list3;
        block4 : {
            this.aj.clear();
            ArrayList arrayList = list.d();
            list3 = this.a(arrayList);
            list2 = this.b(arrayList);
            if (TextUtils.isEmpty((CharSequence)this.ag)) {
                this.ag = MagicPreferences.a(this.getContext(), this.ah);
            }
            for (n = 0; n < this.aj.size(); ++n) {
                if (!this.aj.get((int)n).a.a().equals(this.ag)) {
                    continue;
                }
                break block4;
            }
            n = 0;
        }
        list = this.c((VideoEffects.VideoStyleType)list);
        List<QuickSelectControlItemViewModel> list4 = this.w();
        if (this.af == null) {
            this.af = EnumUtils.a(VideoEffects.Intensity.class, MagicPreferences.c(this.getContext(), this.ah));
        }
        int n2 = VideoEffects.a((VideoEffects.Intensity)this.af);
        this.i.a(list3, n, list, n2);
        this.a(list2, list4);
    }

    private List<CyclableStateItemViewModel> c(VideoEffects.VideoStyleType videoStyleType) {
        ArrayList<CyclableStateItemViewModel> arrayList = new ArrayList<CyclableStateItemViewModel>();
        Iterator iterator = VideoEffects.a((Context)this.getContext(), (VideoEffects.VideoStyleType)videoStyleType).iterator();
        while (iterator.hasNext()) {
            arrayList.add(new CyclableStateItemViewModel((Integer)iterator.next(), videoStyleType.e(), this.getContext().getString(videoStyleType.f())));
        }
        return arrayList;
    }

    private void c(boolean bl) {
        Drawable drawable2 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837811).mutate();
        Drawable drawable3 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837811).mutate();
        drawable2.setColorFilter(this.ap.d(this.getContext()), PorterDuff.Mode.MULTIPLY);
        if (bl) {
            this.w.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
            this.w.setTextColor(this.ap.d(this.getContext()));
            this.x.setCompoundDrawablesWithIntrinsicBounds(drawable3, null, null, null);
            this.x.setTextColor(-1);
            return;
        }
        this.x.setCompoundDrawablesWithIntrinsicBounds(drawable2, null, null, null);
        this.x.setTextColor(this.ap.d(this.getContext()));
        this.w.setCompoundDrawablesWithIntrinsicBounds(drawable3, null, null, null);
        this.w.setTextColor(-1);
    }

    public static boolean c() {
        boolean bl = new DeviceSettings().n();
        boolean bl2 = MagicPreferences.b(SingApplication.g(), "MONITORING_TAB_IS_FTUX", true);
        if (bl && bl2) {
            return true;
        }
        return false;
    }

    static /* synthetic */ ArrayList e(EffectPanelView effectPanelView) {
        return effectPanelView.K;
    }

    static /* synthetic */ boolean f(EffectPanelView effectPanelView) {
        return effectPanelView.Q;
    }

    static /* synthetic */ int g(EffectPanelView effectPanelView) {
        return effectPanelView.au;
    }

    static /* synthetic */ boolean h(EffectPanelView effectPanelView) {
        return effectPanelView.P;
    }

    static /* synthetic */ boolean i(EffectPanelView effectPanelView) {
        return effectPanelView.q();
    }

    static /* synthetic */ String j(EffectPanelView effectPanelView) {
        return effectPanelView.ah;
    }

    static /* synthetic */ ViewPhase k(EffectPanelView effectPanelView) {
        return effectPanelView.C;
    }

    static /* synthetic */ DeviceSettings l(EffectPanelView effectPanelView) {
        return effectPanelView.av;
    }

    static /* synthetic */ EffectPanelPagerAdapter m(EffectPanelView effectPanelView) {
        return effectPanelView.I;
    }

    private void n() {
        LayoutUtils.a((View)this, (WeakListener.OnGlobalLayoutListener)new WeakListener.OnGlobalLayoutListener((Object)this, new ViewTreeObserver.OnGlobalLayoutListener(){

            public void onGlobalLayout() {
                EffectPanelView.this.o();
                EffectPanelView.this.p();
            }
        }));
    }

    private void o() {
        if (this.ad != null) {
            return;
        }
        float f = this.getWidth();
        this.ad = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)this.c, (String)"translationX", (float[])new float[]{0.0f, - f});
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat((Object)this.d, (String)"translationX", (float[])new float[]{f, 0.0f});
        this.ad.setDuration(300);
        this.ad.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        this.ad.addListener(new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
                this.onAnimationEnd(animator2);
            }

            public void onAnimationEnd(Animator animator2) {
                if (EffectPanelView.this.c != null) {
                    EffectPanelView.this.c.setVisibility(8);
                }
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                if (EffectPanelView.this.d != null) {
                    EffectPanelView.this.d.setVisibility(0);
                }
            }
        });
        this.ad.playTogether(new Animator[]{objectAnimator, objectAnimator2});
    }

    private void p() {
        if (this.ae != null) {
            return;
        }
        float f = this.getWidth();
        this.ae = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)this.c, (String)"translationX", (float[])new float[]{- f, 0.0f});
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat((Object)this.d, (String)"translationX", (float[])new float[]{0.0f, f});
        this.ae.setDuration(300);
        this.ae.setInterpolator((TimeInterpolator)new AccelerateDecelerateInterpolator());
        this.ae.addListener(new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
                this.onAnimationEnd(animator2);
            }

            public void onAnimationEnd(Animator animator2) {
                if (EffectPanelView.this.d != null) {
                    EffectPanelView.this.d.setVisibility(8);
                }
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                if (EffectPanelView.this.c != null) {
                    EffectPanelView.this.c.setVisibility(0);
                }
            }
        });
        this.ae.playTogether(new Animator[]{objectAnimator, objectAnimator2});
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean q() {
        if (this.N && this.M || this.M && !this.G.ak()) {
            return false;
        }
        return true;
    }

    private View r() {
        if (!this.q()) {
            this.D = EffectPanelJoinerVideoTab_.a(this.getContext());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            this.D.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            this.E = true;
            if (this.F) {
                this.j();
            }
            return this.D;
        }
        if (this.ai != null && !this.ai.isEmpty()) {
            VideoStylesAdapter videoStylesAdapter = new VideoStylesAdapter(this.getContext(), this.ai, this.O, this.ah, this.am);
            EffectPanelRecyclerView effectPanelRecyclerView = EffectPanelRecyclerView_.a(this.getContext());
            effectPanelRecyclerView.setAdapter(videoStylesAdapter);
            if (this.G.R().contains(this.ah) || this.O) {
                this.ah = VideoEffects.VideoStyleType.a.a();
                this.af = VideoEffects.Intensity.a;
            }
            return effectPanelRecyclerView;
        }
        return null;
    }

    private void s() {
        if (TextUtils.isEmpty((CharSequence)this.ag)) {
            this.ag = MagicPreferences.a(this.getContext(), this.ah);
        }
        ColorFilterAdapter colorFilterAdapter = new ColorFilterAdapter(this.getContext(), this.aj, this.ag, this.an);
        this.h.setLayoutManager((RecyclerView.LayoutManager)new CenterLayoutManager(this.getContext(), 0, false, CenterLayoutManager.ScrollType.b));
        this.h.setAdapter((RecyclerView.Adapter)colorFilterAdapter);
    }

    private void setPitchCorrectionWithAutoTune(float f) {
        this.setPitchCorrectionSeekBar(this.B.a());
        if (this.B.a()) {
            this.r.setProgress((int)(100.0f * f));
            return;
        }
        this.r.setProgress(0);
    }

    private void setupAdapter(ViewPhase viewPhase) {
        this.I = new EffectPanelPagerAdapter(viewPhase);
        this.b.setAdapter((PagerAdapter)this.I);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setupTabIcons(boolean bl) {
        LinearLayout linearLayout = (LinearLayout)this.a.getChildAt(0);
        int n = 0;
        while (n < linearLayout.getChildCount()) {
            TabLayout.Tab tab = this.a.getTabAt(n);
            TabType tabType = (TabType)((Object)this.I.b().get(n));
            if (tab != null) {
                boolean bl2 = !bl;
                Drawable drawable2 = this.a(tabType, bl2, true);
                if (!this.K.contains((Object)tabType)) {
                    drawable2.setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
                }
                tab.setIcon(drawable2);
            }
            ++n;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private View t() {
        VolumeControllerView volumeControllerView = VolumeControllerView_.a(this.getContext());
        boolean bl = this.C != ViewPhase.d;
        volumeControllerView.a(bl, this.as);
        volumeControllerView.setProgress(this.V);
        this.W = volumeControllerView.getMax();
        if (!TextUtils.isEmpty((CharSequence)this.U)) {
            volumeControllerView.setVolumeControlText(this.U);
        }
        return volumeControllerView;
    }

    private View u() {
        VocalMatchControllerView vocalMatchControllerView = VocalMatchControllerView_.a(this.getContext());
        vocalMatchControllerView.a(this.at);
        vocalMatchControllerView.setMax(this.ac);
        vocalMatchControllerView.setProgress(this.ab);
        if (this.aa == -1) {
            this.aa = this.ab;
        }
        if (this.ab != this.aa) {
            vocalMatchControllerView.a();
        }
        return vocalMatchControllerView;
    }

    private void v() {
        this.S = true;
        this.l.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onProgressChanged(SeekBar object, int n, boolean bl) {
                bl = true;
                EffectPanelView.this.k.setText((CharSequence)String.format(EffectPanelView.this.getResources().getString(2131296644), n));
                object = EffectPanelView.this;
                ParamType paramType = ParamType.b;
                float f = (float)n / 100.0f;
                if (EffectPanelView.this.S) {
                    bl = false;
                }
                ((EffectPanelView)((Object)object)).a(paramType, f, bl);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.o.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onProgressChanged(SeekBar object, int n, boolean bl) {
                bl = true;
                EffectPanelView.this.n.setText((CharSequence)String.format(EffectPanelView.this.getResources().getString(2131296644), n));
                object = EffectPanelView.this;
                ParamType paramType = ParamType.d;
                float f = (float)n / 100.0f;
                if (EffectPanelView.this.S) {
                    bl = false;
                }
                ((EffectPanelView)((Object)object)).a(paramType, f, bl);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.r.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onProgressChanged(SeekBar object, int n, boolean bl) {
                boolean bl2 = true;
                EffectPanelView.this.q.setText((CharSequence)String.format(EffectPanelView.this.getResources().getString(2131296644), n));
                if (bl) {
                    object = EffectPanelView.this;
                    ParamType paramType = ParamType.c;
                    float f = (float)n / 100.0f;
                    bl = !EffectPanelView.this.S ? bl2 : false;
                    ((EffectPanelView)((Object)object)).a(paramType, f, bl);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.u.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onProgressChanged(SeekBar object, int n, boolean bl) {
                boolean bl2 = true;
                EffectPanelView.this.t.setText((CharSequence)String.format(EffectPanelView.this.getResources().getString(2131296644), n));
                if (bl) {
                    object = EffectPanelView.this;
                    ParamType paramType = ParamType.c;
                    float f = (float)n / 100.0f;
                    bl = !EffectPanelView.this.S ? bl2 : false;
                    ((EffectPanelView)((Object)object)).a(paramType, f, bl);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.p.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (!EffectPanelView.this.r.isEnabled()) {
                    Toast.makeText((Context)EffectPanelView.this.getContext(), (int)2131296408, (int)0).show();
                }
            }
        });
        this.w.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                boolean bl = true;
                EffectPanelView.this.c(true);
                object = EffectPanelView.this;
                ParamType paramType = ParamType.a;
                if (EffectPanelView.this.S) {
                    bl = false;
                }
                ((EffectPanelView)((Object)object)).a(paramType, 0.0f, bl);
            }
        });
        this.x.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                boolean bl = false;
                EffectPanelView.this.c(false);
                object = EffectPanelView.this;
                ParamType paramType = ParamType.a;
                if (!EffectPanelView.this.S) {
                    bl = true;
                }
                ((EffectPanelView)((Object)object)).a(paramType, 1.0f, bl);
            }
        });
        this.S = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private List<QuickSelectControlItemViewModel> w() {
        ArrayList<QuickSelectControlItemViewModel> arrayList = new ArrayList<QuickSelectControlItemViewModel>();
        int n = 0;
        while (n < VideoEffects.Intensity.values().length) {
            int n2;
            VideoEffects.Intensity intensity = VideoEffects.Intensity.values()[n];
            switch (.d[intensity.ordinal()]) {
                default: {
                    n2 = 2131297829;
                    break;
                }
                case 1: {
                    n2 = 2131297829;
                    break;
                }
                case 2: {
                    n2 = 2131297827;
                    break;
                }
                case 3: {
                    n2 = 2131297828;
                    break;
                }
                case 4: {
                    n2 = 2131297826;
                }
            }
            arrayList.add(new QuickSelectControlItemViewModel(n, n2, ContextCompat.getColor((Context)this.getContext(), (int)2131689893)));
            ++n;
        }
        return arrayList;
    }

    public View a(View view) {
        int n = ((LinearLayout)view).getChildCount();
        int n2 = 0;
        do {
            View view2 = view;
            if (n2 >= n || (view2 = ((LinearLayout)view).getChildAt(n2)) instanceof EffectPanelRecyclerView) {
                return view2;
            }
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String a(boolean bl, String string2, String string3, boolean bl2, boolean bl3) {
        Object object;
        String string4 = null;
        if (bl && this.av.r()) {
            if (!TextUtils.isEmpty((CharSequence)this.getSelectedColorFilterId())) {
                string3 = this.getSelectedColorFilterId();
            }
            if (!TextUtils.isEmpty((CharSequence)this.getSelectedVideoStyleId())) {
                string2 = this.getSelectedVideoStyleId();
            }
            object = (object = this.getSelectedIntensity()) == null ? VideoEffects.Intensity.a.b() : object.b();
        } else {
            object = null;
            string2 = null;
            string3 = null;
        }
        if (this.ap == null) {
            return SingAnalytics.a((boolean)bl, (String)string2, (String)string3, (String)object, (String)string4, (boolean)bl2, (boolean)bl3);
        }
        string4 = this.ap.b();
        return SingAnalytics.a((boolean)bl, (String)string2, (String)string3, (String)object, (String)string4, (boolean)bl2, (boolean)bl3);
    }

    public void a() {
        this.Q = false;
        this.a.setSelectedTabIndicatorColor(0);
        this.setupTabIcons(true);
    }

    public void a(int n) {
        this.h.smoothScrollToPosition(n);
    }

    public void a(int n, TabType tabType) {
        View view;
        View view2 = view = this.a(tabType);
        if (tabType == TabType.b) {
            view2 = this.a(view);
        }
        if (view2 instanceof EffectPanelRecyclerView) {
            ((EffectPanelRecyclerView)view2).a(n);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(ViewPhase viewPhase, boolean bl, boolean bl2, boolean bl3, String string2, boolean bl4, List<VideoFilterManager.VideoStyleItem> drawable2, String object, String string3, VideoEffects.Intensity intensity, OnVideoStyleClickListener onVideoStyleClickListener, OnColorFilterItemClickListener onColorFilterItemClickListener, OnIntensityItemClickListener onIntensityItemClickListener, OnVocalEffectItemClickListener onVocalEffectItemClickListener, OnVocalParamsUpdateListener onVocalParamsUpdateListener, Runnable runnable, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2, CompoundButton.OnCheckedChangeListener onCheckedChangeListener, QuickSelectControlView quickSelectControlView, String string4, ArrayList<VocalEffect> arrayList) {
        int n;
        this.C = viewPhase;
        this.L = bl;
        this.M = bl2;
        this.N = bl3;
        bl = bl3 && !bl2;
        this.O = bl;
        this.J = string2;
        this.P = bl4;
        this.ai = drawable2;
        this.ah = object;
        this.ag = string3;
        this.af = intensity;
        this.am = onVideoStyleClickListener;
        this.an = onColorFilterItemClickListener;
        this.ao = onIntensityItemClickListener;
        this.z = onVocalParamsUpdateListener;
        this.aq = runnable;
        this.as = onSeekBarChangeListener;
        this.at = onSeekBarChangeListener2;
        this.y = onCheckedChangeListener;
        this.A = quickSelectControlView;
        this.ak = new VocalEffectsAdapter(this.getContext(), arrayList, MagicPreferences.b(this.getContext(), "PREFS_LAST_SELECTED_FX", string4), onVocalEffectItemClickListener);
        this.T = this.G.ar();
        this.f.setOnClickListener(null);
        this.b.setPagingEnabled(false);
        this.setupAdapter(this.C);
        if (this.C != ViewPhase.d) {
            this.a.setSelectedTabIndicatorColor(0);
        }
        for (n = 0; n < this.I.b().size(); ++n) {
            boolean bl5;
            object = (TabType)((Object)this.I.b().get(n));
            string3 = this.a.newTab();
            object.a(this.L);
            boolean bl6 = false;
            if (viewPhase == ViewPhase.d && object == TabType.b) {
                bl5 = true;
                string2 = this.a((TabType)((Object)object), true, false);
                this.b.setCurrentItem(n);
                this.b.setVisibility(0);
            } else {
                drawable2 = this.a((TabType)((Object)object), false, true);
                string2 = drawable2;
                bl5 = bl6;
                if (!this.K.contains(object)) {
                    drawable2.setColorFilter(-7829368, PorterDuff.Mode.MULTIPLY);
                    string2 = drawable2;
                    bl5 = bl6;
                }
            }
            string3.setIcon((Drawable)string2);
            object.a(n);
            this.a.addTab((TabLayout.Tab)string3);
            if (!bl5) continue;
            string3.select();
        }
        if (this.a.getChildCount() == 0) {
            this.setVisibility(8);
        }
        this.a.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                EffectPanelView.this.b.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }
        });
        viewPhase = (LinearLayout)this.a.getChildAt(0);
        n = 0;
        do {
            if (n >= viewPhase.getChildCount()) {
                this.n();
                return;
            }
            string2 = viewPhase.getChildAt(n);
            drawable2 = this.a.getTabAt(n);
            object = (TabType)((Object)this.I.b().get(n));
            string2.setBackground(null);
            string2.setOnTouchListener(new View.OnTouchListener((TabType)((Object)object), (TabLayout.Tab)drawable2, (LinearLayout)viewPhase){
                final /* synthetic */ TabType a;
                final /* synthetic */ TabLayout.Tab b;
                final /* synthetic */ LinearLayout c;
                {
                    this.a = tabType;
                    this.b = tab;
                    this.c = linearLayout;
                }

                /*
                 * Unable to fully structure code
                 * Enabled aggressive block sorting
                 * Lifted jumps to return sites
                 */
                public boolean onTouch(View var1_1, MotionEvent var2_2) {
                    block13 : {
                        if (var2_2.getAction() != 1) return false;
                        if (EffectPanelView.e(EffectPanelView.this).contains((Object)this.a)) {
                            if (!EffectPanelView.f(EffectPanelView.this)) {
                                EffectPanelView.this.a.setSelectedTabIndicatorColor(ContextCompat.getColor((Context)EffectPanelView.this.getContext(), (int)2131689728));
                            }
                            if (this.b != null) {
                                if (EffectPanelView.g(EffectPanelView.this) != this.b.getPosition()) {
                                    if (this.b.getPosition() == 0 && EffectPanelView.h(EffectPanelView.this) && MagicPreferences.b(EffectPanelView.this.getContext(), "VIDEO_STYLE_IS_FTUX", true) && EffectPanelView.i(EffectPanelView.this)) {
                                        var1_1 = VideoEffects.b((String)EffectPanelView.j(EffectPanelView.this));
                                        Toaster.a(EffectPanelView.this.getContext(), EffectPanelView.this.getResources().getString(2131296764, new Object[]{var1_1.c()}));
                                    }
                                    EffectPanelView.a(EffectPanelView.this, this.b.getPosition());
                                    this.b.select();
                                    var3_3 = EffectPanelView.k(EffectPanelView.this) != ViewPhase.d && this.a == TabType.c;
                                    if (var3_3 && EffectPanelView.l(EffectPanelView.this).n()) {
                                        MagicPreferences.a(EffectPanelView.this.getContext(), "MONITORING_TAB_IS_FTUX", false);
                                    }
                                }
                                this.b.setIcon(EffectPanelView.a(EffectPanelView.this, this.a, true, false));
                            }
                            EffectPanelView.this.b.setVisibility(0);
                            EffectPanelView.a(EffectPanelView.this, true);
                            return false;
                        }
                        var1_1 = "";
                        switch (.a[this.a.ordinal()]) {
                            case 1: {
                                if (EffectPanelView.k(EffectPanelView.this) == ViewPhase.c) {
                                    var1_1 = EffectPanelView.this.getContext().getResources().getString(2131296762);
                                    ** break;
                                }
                                var1_1 = EffectPanelView.this.getContext().getResources().getString(2131296761);
                                ** break;
                            }
                            case 2: {
                                var1_1 = EffectPanelView.k(EffectPanelView.this) == ViewPhase.b ? EffectPanelView.this.getContext().getResources().getString(2131296405) : EffectPanelView.this.getContext().getResources().getString(2131296758);
                            }
lbl29: // 4 sources:
                            default: {
                                break block13;
                            }
                            case 3: 
                        }
                        var1_1 = EffectPanelView.k(EffectPanelView.this) == ViewPhase.b ? EffectPanelView.this.getContext().getResources().getString(2131296405) : EffectPanelView.this.getContext().getResources().getString(2131296770);
                    }
                    if (!var1_1.isEmpty()) {
                        Toaster.a(EffectPanelView.this.getContext(), (String)var1_1);
                    }
                    var3_4 = ((TabType)EffectPanelView.e(EffectPanelView.this).get(0)).a();
                    var1_1 = this.c.getChildAt(var3_4);
                    var4_5 = (TabType)EffectPanelPagerAdapter.a(EffectPanelView.m(EffectPanelView.this)).get(var3_4);
                    if (var1_1 == null) return true;
                    if (var4_5 == null) return true;
                    var1_1.dispatchTouchEvent(var2_2);
                    return true;
                }
            });
            ++n;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String string2) {
        VocalEffect vocalEffect;
        if (this.H != -1) {
            vocalEffect = (RelativeLayout.LayoutParams)this.f.getLayoutParams();
            vocalEffect.height = this.H;
            this.f.setLayoutParams((ViewGroup.LayoutParams)vocalEffect);
        }
        if ((vocalEffect = VocalEffect.b(string2)) == null) {
            return;
        }
        this.g.setText((CharSequence)vocalEffect.a(this.getContext()));
        float f = VocalEffect.a(this.getContext(), string2);
        float f2 = VocalEffect.b(this.getContext(), string2);
        string2 = new PorterDuffColorFilter(vocalEffect.d(this.getContext()), PorterDuff.Mode.MULTIPLY);
        Drawable drawable2 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837813);
        Drawable drawable3 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837813);
        Drawable drawable4 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837813);
        this.al.clear();
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        switch (.c[vocalEffect.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.j.setVisibility(0);
                this.m.setVisibility(0);
                this.v.setVisibility(8);
                this.p.setVisibility(8);
                this.s.setVisibility(8);
                this.l.getProgressDrawable().setColorFilter((ColorFilter)string2);
                this.l.setThumb(drawable2);
                this.o.getProgressDrawable().setColorFilter((ColorFilter)string2);
                this.o.setThumb(drawable4);
                this.al.add(ParamType.b);
                this.al.add(ParamType.d);
                this.l.setProgress((int)(f * 100.0f));
                this.o.setProgress((int)(f2 * 100.0f));
                return;
            }
            case 2: {
                this.v.setVisibility(0);
                this.s.setVisibility(0);
                this.p.setVisibility(8);
                this.j.setVisibility(8);
                this.m.setVisibility(8);
                this.u.setThumb(drawable3);
                this.u.getProgressDrawable().setColorFilter((ColorFilter)string2);
                this.al.add(ParamType.a);
                this.al.add(ParamType.c);
                boolean bl = f <= 0.5f;
                this.c(bl);
                this.u.setProgress((int)(f2 * 100.0f));
                return;
            }
            case 3: 
        }
        this.j.setVisibility(0);
        this.p.setVisibility(0);
        this.m.setVisibility(8);
        this.v.setVisibility(8);
        this.s.setVisibility(8);
        this.l.setThumb(drawable2);
        this.l.getProgressDrawable().setColorFilter((ColorFilter)string2);
        if (this.T && this.C == ViewPhase.d) {
            this.setPitchCorrectionWithAutoTune(f2);
        } else {
            this.r.setThumb(drawable3);
            this.r.getProgressDrawable().setColorFilter((ColorFilter)string2);
            this.r.setProgress((int)(f2 * 100.0f));
        }
        this.al.add(ParamType.b);
        this.al.add(ParamType.c);
        this.l.setProgress((int)(f * 100.0f));
    }

    public void a(boolean bl) {
        View view = this.a(TabType.b);
        if (view instanceof RecyclerView) {
            ((VocalEffectsAdapter)((RecyclerView)view).getAdapter()).a(bl);
        }
    }

    public /* varargs */ void a(boolean bl, TabType ... arrtabType) {
        boolean bl2 = false;
        for (TabType tabType : arrtabType) {
            if (this.K.contains((Object)tabType)) continue;
            this.K.add(tabType);
        }
        Collections.sort(this.K);
        if (!bl) {
            bl2 = true;
        }
        this.setupTabIcons(bl2);
    }

    public void b() {
        if (this.K.size() > 0) {
            int n = this.K.get(0).a();
            TabType tabType = (TabType)((Object)this.I.b().get(n));
            TabLayout.Tab tab = this.a.getTabAt(n);
            if (tabType != null && tab != null) {
                tabType = this.a(tabType, true, false);
                tab.select();
                tab.setIcon((Drawable)tabType);
            }
        }
    }

    public void b(int n) {
        this.H = this.b.getMeasuredHeight() + n;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.b.getLayoutParams();
        layoutParams.height = this.H;
        this.b.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    public void b(boolean bl) {
        if (bl) {
            this.B.e();
            return;
        }
        this.B.d();
    }

    /*
     * Enabled aggressive block sorting
     */
    public /* varargs */ void b(boolean bl, TabType ... arrtabType) {
        this.K.removeAll(Arrays.asList(arrtabType));
        boolean bl2 = !bl;
        this.setupTabIcons(bl2);
        if (bl) {
            this.b();
        }
    }

    public void c(int n) {
        this.ab = n;
    }

    public void d(int n) {
        this.ab = n;
        View view = this.a(TabType.d);
        if (view instanceof VocalMatchControllerView) {
            ((VocalMatchControllerView)view).setProgress(n);
        }
    }

    public boolean d() {
        return this.T;
    }

    public boolean e() {
        if (this.d() && this.B != null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void f() {
        block7 : {
            block6 : {
                if (this.A != null) {
                    this.A.a(true);
                }
                if (this.ad.isRunning() || this.ae.isRunning()) break block6;
                if (!this.R && this.ad != null) {
                    this.R = true;
                    this.ad.start();
                    return;
                }
                if (this.R && this.ae != null) break block7;
            }
            return;
        }
        this.R = false;
        if (this.aq != null) {
            new UiHandler((View)this).post(this.aq);
        }
        this.ae.start();
    }

    public boolean g() {
        return this.R;
    }

    public int getCurrentPageIndex() {
        return this.b.getCurrentItem();
    }

    public int getCurrentTabIndex() {
        return this.b.getCurrentItem();
    }

    public String getSelectedColorFilterId() {
        return this.ag;
    }

    public VideoEffects.Intensity getSelectedIntensity() {
        return this.af;
    }

    public String getSelectedVideoStyleId() {
        return this.ah;
    }

    public int getVocalMatchMax() {
        return this.ac;
    }

    public int getVocalMatchProgress() {
        View view = this.a(TabType.d);
        if (view instanceof VocalMatchControllerView) {
            return ((VocalMatchControllerView)view).getProgress();
        }
        return this.ab;
    }

    public int getVolumeControllerProgress() {
        View view = this.a(TabType.c);
        if (view instanceof VolumeControllerView) {
            return ((VolumeControllerView)view).getProgress();
        }
        return this.V;
    }

    public float getVolumeLevel_amplitude() {
        return (float)this.getVolumeControllerProgress() / (float)this.getVolumeMax();
    }

    public int getVolumeMax() {
        return this.W;
    }

    public void h() {
        this.F = true;
    }

    public boolean i() {
        return this.E;
    }

    public void j() {
        VideoEffects.VideoStyleType videoStyleType = VideoEffects.b((String)this.ah);
        this.D.a(this.J, videoStyleType.c());
    }

    /*
     * Enabled aggressive block sorting
     */
    public void k() {
        VideoEffects.VideoStyleType videoStyleType = VideoEffects.b((String)this.ah);
        this.j.setVisibility(8);
        this.m.setVisibility(8);
        this.v.setVisibility(8);
        this.p.setVisibility(8);
        this.s.setVisibility(8);
        this.g.setText((CharSequence)videoStyleType.c());
        if (videoStyleType == VideoEffects.VideoStyleType.a) {
            this.h.setVisibility(0);
            this.i.setVisibility(8);
            this.a(videoStyleType);
            this.s();
        } else {
            this.h.setVisibility(8);
            this.i.setVisibility(0);
            this.b(videoStyleType);
        }
        if (this.H != -1) {
            videoStyleType = (RelativeLayout.LayoutParams)this.f.getLayoutParams();
            videoStyleType.height = this.H;
            this.f.setLayoutParams((ViewGroup.LayoutParams)videoStyleType);
        }
    }

    public void l() {
        View view = this.a(TabType.d);
        if (view instanceof VocalMatchControllerView) {
            ((VocalMatchControllerView)view).a();
        }
    }

    @Click
    protected void m() {
        this.f();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.a().a("NOTIFICATION_QUICK_SELECT", this.aw);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.a().b("NOTIFICATION_QUICK_SELECT", this.aw);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.ar != null) {
            return this.ar.onTouch((View)this, motionEvent);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void setAutoTune(boolean bl) {
        if (bl) {
            this.B.b();
            return;
        }
        this.B.c();
    }

    public void setAutoTuneColor(@ColorInt int n) {
        if (this.e()) {
            this.B.a(n);
        }
    }

    public void setCurrentVocalEffect(VocalEffect vocalEffect) {
        this.ap = vocalEffect;
    }

    public void setPitchCorrectionSeekBar(boolean bl) {
        this.r.setEnabled(bl);
        Drawable drawable2 = ContextCompat.getDrawable((Context)this.getContext(), (int)2130837813);
        if (bl) {
            this.setPitchCorrectionSeekBarEnabled(drawable2);
            return;
        }
        this.setPitchCorrectionSeekBarDisabled(drawable2);
    }

    public void setPitchCorrectionSeekBarDisabled(Drawable drawable2) {
        this.r.getProgressDrawable().setColorFilter((ColorFilter)new PorterDuffColorFilter(this.getResources().getColor(2131689991), PorterDuff.Mode.MULTIPLY));
        drawable2.setColorFilter((ColorFilter)new PorterDuffColorFilter(this.getResources().getColor(2131689493), PorterDuff.Mode.MULTIPLY));
        this.r.setThumb(drawable2);
    }

    public void setPitchCorrectionSeekBarEnabled(Drawable drawable2) {
        this.r.getProgressDrawable().setColorFilter((ColorFilter)new PorterDuffColorFilter(this.getResources().getColor(2131689921), PorterDuff.Mode.MULTIPLY));
        this.r.setThumb(drawable2);
    }

    public void setSelectedColorFilterId(String string2) {
        this.ag = string2;
    }

    public void setSelectedIntensity(VideoEffects.Intensity intensity) {
        this.af = intensity;
    }

    public void setSelectedVideoStyleId(String string2) {
        this.ah = string2;
    }

    public void setTouchInterceptor(View.OnTouchListener onTouchListener) {
        this.ar = onTouchListener;
    }

    public void setVocalMatchMax(int n) {
        this.ac = n;
    }

    public void setVocalMatchText(String string2) {
        View view = this.a(TabType.d);
        if (view instanceof VocalMatchControllerView) {
            ((VocalMatchControllerView)view).setVocalMatchText(string2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setVolumeControlText(float f) {
        BigDecimal bigDecimal;
        if (this.C == ViewPhase.d) {
            bigDecimal = new BigDecimal(f).setScale(1, 6).divide(new BigDecimal(10), RoundingMode.HALF_EVEN);
            this.U = bigDecimal.toString();
            if (bigDecimal.compareTo(new BigDecimal(0)) == 1) {
                this.U = "+" + this.U;
            }
        } else {
            this.U = Integer.toString((int)f);
            this.U = this.U + "%";
        }
        if ((bigDecimal = this.a(TabType.c)) instanceof VolumeControllerView) {
            ((VolumeControllerView)((Object)bigDecimal)).setVolumeControlText(this.U);
        }
    }

    public void setVolumeMax(int n) {
        this.W = n;
        View view = this.a(TabType.c);
        if (view instanceof VolumeControllerView) {
            ((VolumeControllerView)view).setMax(this.W);
        }
    }

    public void setVolumeProgress(int n) {
        this.V = n;
        View view = this.a(TabType.c);
        if (view instanceof VolumeControllerView) {
            ((VolumeControllerView)view).setProgress(this.V);
        }
    }

    public static class Builder {
        private ViewPhase a;
        private boolean b;
        private boolean c;
        private boolean d;
        private String e = "";
        private boolean f;
        private List<VideoFilterManager.VideoStyleItem> g = new ArrayList<VideoFilterManager.VideoStyleItem>();
        private String h = VideoEffects.VideoStyleType.a.a();
        private String i = VideoEffects.ColorFilterType.e.a();
        private VideoEffects.Intensity j = VideoEffects.Intensity.a;
        private OnVideoStyleClickListener k;
        private OnColorFilterItemClickListener l;
        private OnIntensityItemClickListener m;
        private OnVocalEffectItemClickListener n;
        private OnVocalParamsUpdateListener o;
        private Runnable p;
        private SeekBar.OnSeekBarChangeListener q;
        private SeekBar.OnSeekBarChangeListener r;
        private CompoundButton.OnCheckedChangeListener s;
        private QuickSelectControlView t;
        private String u = "";
        private ArrayList<VocalEffect> v = new ArrayList();

        public Builder a(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.s = onCheckedChangeListener;
            return this;
        }

        public Builder a(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
            this.q = onSeekBarChangeListener;
            return this;
        }

        public Builder a(OnVocalParamsUpdateListener onVocalParamsUpdateListener) {
            this.o = onVocalParamsUpdateListener;
            return this;
        }

        public Builder a(ViewPhase viewPhase) {
            this.a = viewPhase;
            return this;
        }

        public Builder a(QuickSelectControlView quickSelectControlView) {
            this.t = quickSelectControlView;
            return this;
        }

        public Builder a(OnColorFilterItemClickListener onColorFilterItemClickListener) {
            this.l = onColorFilterItemClickListener;
            return this;
        }

        public Builder a(OnIntensityItemClickListener onIntensityItemClickListener) {
            this.m = onIntensityItemClickListener;
            return this;
        }

        public Builder a(OnVideoStyleClickListener onVideoStyleClickListener) {
            this.k = onVideoStyleClickListener;
            return this;
        }

        public Builder a(OnVocalEffectItemClickListener onVocalEffectItemClickListener) {
            this.n = onVocalEffectItemClickListener;
            return this;
        }

        public Builder a(VideoEffects.Intensity intensity) {
            this.j = intensity;
            return this;
        }

        public Builder a(Runnable runnable) {
            this.p = runnable;
            return this;
        }

        public Builder a(String string2) {
            this.e = string2;
            return this;
        }

        public Builder a(ArrayList<VocalEffect> arrayList) {
            this.v = arrayList;
            return this;
        }

        public Builder a(List<VideoFilterManager.VideoStyleItem> list) {
            this.g = list;
            return this;
        }

        public Builder a(boolean bl) {
            this.b = bl;
            return this;
        }

        public void a(EffectPanelView effectPanelView) {
            effectPanelView.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.m, this.n, this.o, this.p, this.q, this.r, this.s, this.t, this.u, this.v);
        }

        public Builder b(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
            this.r = onSeekBarChangeListener;
            return this;
        }

        public Builder b(String string2) {
            this.h = string2;
            return this;
        }

        public Builder b(boolean bl) {
            this.c = bl;
            return this;
        }

        public Builder c(String string2) {
            this.i = string2;
            return this;
        }

        public Builder c(boolean bl) {
            this.d = bl;
            return this;
        }

        public Builder d(String string2) {
            this.u = string2;
            return this;
        }

        public Builder d(boolean bl) {
            this.f = bl;
            return this;
        }
    }

    private class EffectPanelPagerAdapter
    extends PagerAdapter {
        private final ArrayList<TabType> b;

        private EffectPanelPagerAdapter(ViewPhase viewPhase) {
            this.b = new ArrayList();
            EffectPanelView.this.C = viewPhase;
            this.a();
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        private void a() {
            block10 : {
                var2_1 = EffectPanelView.l(EffectPanelView.this).n();
                var3_2 = TabType.a;
                var4_3 = TabType.b;
                var5_4 = TabType.c;
                var6_5 = TabType.d;
                var1_6 = EffectPanelView.h(EffectPanelView.this) != false && EffectPanelView.l(EffectPanelView.this).r() != false;
                switch (.b[EffectPanelView.k(EffectPanelView.this).ordinal()]) {
                    case 1: {
                        EffectPanelView.e(EffectPanelView.this).add(var3_2);
                        if (var2_1) {
                            EffectPanelView.e(EffectPanelView.this).add(var4_3);
                            EffectPanelView.e(EffectPanelView.this).add(var5_4);
                            ** break;
                        }
                        ** GOTO lbl22
                    }
                    case 2: {
                        EffectPanelView.e(EffectPanelView.this).add(var3_2);
                        ** break;
                    }
                    case 3: {
                        if (var2_1) {
                            EffectPanelView.e(EffectPanelView.this).add(var4_3);
                            EffectPanelView.e(EffectPanelView.this).add(var5_4);
                        }
                    }
lbl22: // 7 sources:
                    default: {
                        break block10;
                    }
                    case 4: 
                }
                EffectPanelView.e(EffectPanelView.this).add(var3_2);
                EffectPanelView.e(EffectPanelView.this).add(var4_3);
                EffectPanelView.e(EffectPanelView.this).add(var5_4);
                EffectPanelView.e(EffectPanelView.this).add(var6_5);
            }
            Collections.sort(EffectPanelView.e(EffectPanelView.this));
            if (var1_6) {
                this.b.add(var3_2);
            }
            if (EffectPanelView.k(EffectPanelView.this) != ViewPhase.c || EffectPanelView.k(EffectPanelView.this) == ViewPhase.c && var2_1) {
                this.b.add(var4_3);
                this.b.add(var5_4);
            }
            if (EffectPanelView.k(EffectPanelView.this) != ViewPhase.d) return;
            this.b.add(var6_5);
        }

        private ArrayList<TabType> b() {
            return this.b;
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            viewGroup.removeView((View)object);
        }

        public int getCount() {
            return this.b.size();
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            View view;
            switch (.a[this.b.get(n).ordinal()]) {
                default: {
                    return null;
                }
                case 1: {
                    view = EffectPanelView.this.r();
                    break;
                }
                case 2: {
                    view = EffectPanelView.this.a(EffectPanelView.this.ak);
                    EffectPanelView.this.v();
                    break;
                }
                case 3: {
                    view = EffectPanelView.this.t();
                    break;
                }
                case 4: {
                    view = EffectPanelView.this.u();
                }
            }
            if (view == null) {
                return null;
            }
            viewGroup.addView(view);
            view.setTag((Object)n);
            return view;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }
    }

    public static interface OnVocalParamsUpdateListener {
        public void a(String var1, float var2, float var3, boolean var4);
    }

    private static enum ParamType {
        a,
        b,
        c,
        d;
        

        private ParamType() {
        }
    }

    public static enum TabType {
        a(2130838056, 2130838057),
        b(2130837869, 2130837870),
        c(2130838072, 2130838070),
        d(2130838063, 2130838064);
        
        private final int e;
        private final int f;
        private boolean g;
        private int h;

        private TabType(int n2, int n3) {
            this.e = n2;
            this.f = n3;
        }

        public int a() {
            return this.h;
        }

        public void a(int n) {
            this.h = n;
        }

        public void a(boolean bl) {
            this.g = bl;
        }

        public int b() {
            if (this == c) {
                if (EffectPanelView.c() && !this.g) {
                    return this.f;
                }
                return 2130838069;
            }
            return this.f;
        }

        public int c() {
            if (this == c) {
                if (EffectPanelView.c() && !this.g) {
                    return this.e;
                }
                return 2130838068;
            }
            return this.e;
        }
    }

    public static enum ViewPhase {
        a,
        b,
        c,
        d;
        

        private ViewPhase() {
        }
    }

}

