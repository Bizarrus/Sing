/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.ContextWrapper
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.support.v4.view.PagerAdapter
 *  android.support.v4.view.ViewPager
 *  android.support.v4.view.ViewPager$OnPageChangeListener
 *  android.support.v4.view.ViewPager$SimpleOnPageChangeListener
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 */
package com.smule.singandroid.boost;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.boost.LearnHowToBoostTabIndicator;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.mediaplaying.NowPlayingFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;

public class LearnHowToBoostDialog
extends Dialog {
    public static final String a = LearnHowToBoostDialog.class.getSimpleName();
    private final LearnHowToBoostTabIndicator b;
    private final HelpTipsAdapter c;
    private final ViewPager d;
    private final ViewPager.SimpleOnPageChangeListener e;
    private boolean f;
    private PerformanceV2 g;

    public LearnHowToBoostDialog(Context context) {
        super(context, 2131493338);
        View view = LayoutInflater.from((Context)context).inflate(2130903267, null, false);
        this.setContentView(view);
        this.b = (LearnHowToBoostTabIndicator)view.findViewById(2131755911);
        this.b.a(context, b.length, 0);
        this.c = new HelpTipsAdapter();
        this.d = (ViewPager)view.findViewById(2131755910);
        this.d.setAdapter((PagerAdapter)this.c);
        this.e = new ViewPager.SimpleOnPageChangeListener(){

            public void onPageSelected(int n) {
                LearnHowToBoostDialog.this.b.setSelection(n);
                LearnHowToBoostDialog.this.a(n);
            }
        };
        this.d.addOnPageChangeListener((ViewPager.OnPageChangeListener)this.e);
        view.findViewById(2131755294).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                LearnHowToBoostDialog.this.dismiss();
            }
        });
        view.findViewById(2131755681).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (LearnHowToBoostDialog.this.d.getCurrentItem() != b.length - 1) {
                    Log.b(LearnHowToBoostDialog.a, "curItem:" + LearnHowToBoostDialog.this.d.getCurrentItem());
                    LearnHowToBoostDialog.this.d.setCurrentItem(LearnHowToBoostDialog.this.d.getCurrentItem() + 1);
                }
            }
        });
    }

    private void a(int n) {
        if (n != this.c.getCount() - 1) {
            return;
        }
        if (this.f) {
            this.findViewById(2131755294).setVisibility(8);
            Button button = (Button)this.findViewById(2131755681);
            button.setText((CharSequence)this.getContext().getString(2131296705));
            button.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    LearnHowToBoostDialog.this.dismiss();
                }
            });
            return;
        }
        Button button = (Button)this.findViewById(2131755681);
        button.setText((CharSequence)this.getContext().getString(2131296727));
        button.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View object) {
                Context context = LearnHowToBoostDialog.this.getContext();
                object = context;
                if (context instanceof ContextWrapper) {
                    object = ((ContextWrapper)context).getBaseContext();
                }
                if (object instanceof MediaPlayingActivity) {
                    long l = (object = (MediaPlayingActivity)object).ad() != null ? object.ad().ah() : -1;
                    object.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (PerformanceV2)LearnHowToBoostDialog.this.g, (Long)l, (UpsellType)UpsellType.k));
                } else {
                    Log.d(LearnHowToBoostDialog.a, "unknown host activity", new RuntimeException());
                }
                LearnHowToBoostDialog.this.dismiss();
            }
        });
    }

    public LearnHowToBoostDialog a(PerformanceV2 performanceV2) {
        this.g = performanceV2;
        return this;
    }

    public LearnHowToBoostDialog a(boolean bl) {
        this.f = bl;
        this.c.a(this.f);
        return this;
    }

    public void cancel() {
        super.cancel();
        this.d.removeOnPageChangeListener((ViewPager.OnPageChangeListener)this.e);
    }

    public void dismiss() {
        super.dismiss();
        this.d.removeOnPageChangeListener((ViewPager.OnPageChangeListener)this.e);
    }

    private static class HelpTipsAdapter
    extends PagerAdapter {
        private static final Entry[] b = new Entry[]{new Entry(2130838079, 2131296448, 2131296447, 2131296447), new Entry(2130838080, 2131296450, 2131296449, 2131296449), new Entry(2130838081, 2131296446, 2131296444, 2131296445)};
        private boolean a;

        private HelpTipsAdapter() {
        }

        void a(boolean bl) {
            this.a = bl;
        }

        public void destroyItem(ViewGroup viewGroup, int n, Object object) {
            viewGroup.removeView((View)object);
        }

        public int getCount() {
            return b.length;
        }

        /*
         * Enabled aggressive block sorting
         */
        public Object instantiateItem(ViewGroup viewGroup, int n) {
            Context context = viewGroup.getContext();
            View view = LayoutInflater.from((Context)context).inflate(2130903266, null);
            ((ImageView)view.findViewById(2131755908)).setImageDrawable(ContextCompat.getDrawable((Context)context, (int)HelpTipsAdapter.b[n].a));
            ((TextView)view.findViewById(2131755176)).setText((CharSequence)context.getString(HelpTipsAdapter.b[n].b));
            if (this.a) {
                ((TextView)view.findViewById(2131755909)).setText(HelpTipsAdapter.b[n].d);
            } else {
                ((TextView)view.findViewById(2131755909)).setText(HelpTipsAdapter.b[n].c);
            }
            viewGroup.addView(view);
            return view;
        }

        public boolean isViewFromObject(View view, Object object) {
            if (view == object) {
                return true;
            }
            return false;
        }

        static class Entry {
            int a;
            int b;
            int c;
            int d;

            Entry(int n, int n2, int n3, int n4) {
                this.a = n;
                this.b = n2;
                this.c = n3;
                this.d = n4;
            }
        }

    }

}

