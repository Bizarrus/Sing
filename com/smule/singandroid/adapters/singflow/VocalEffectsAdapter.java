/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.text.TextUtils
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 */
package com.smule.singandroid.adapters.singflow;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.singandroid.VocalEffect;
import com.smule.singandroid.adapters.singflow.EffectsBaseAdapter;
import com.smule.singandroid.effectpanel.ButtonState;
import com.smule.singandroid.effectpanel.EffectPanelButtonIcon;
import com.smule.singandroid.effectpanel.VocalEffectListItem;
import com.smule.singandroid.effectpanel.onclicklistners.OnVocalEffectItemClickListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VocalEffectsAdapter
extends EffectsBaseAdapter {
    private final List<VocalEffect> a;
    private final List<VocalEffect> b;
    private Context c;
    private OnVocalEffectItemClickListener d;
    private int e;
    private boolean f;

    /*
     * Enabled aggressive block sorting
     */
    public VocalEffectsAdapter(Context object, List<VocalEffect> object2, String string2, OnVocalEffectItemClickListener onVocalEffectItemClickListener) {
        int n;
        Object var7_5 = null;
        int n2 = 0;
        this.a = new ArrayList<VocalEffect>();
        this.b = new ArrayList<VocalEffect>();
        this.e = -1;
        this.c = object;
        this.a.addAll(VocalEffect.d());
        if (object2 != null) {
            this.b.addAll((Collection<VocalEffect>)object2);
        }
        if (this.b.size() > 0) {
            this.a.removeAll(this.b);
        }
        object = var7_5;
        if (!TextUtils.isEmpty((CharSequence)string2) && (object = VocalEffect.b(string2)) != null && this.b.contains(object)) {
            object = var7_5;
        }
        if (object == null) {
            for (n = 0; n < this.a.size(); ++n) {
                object2 = this.a.get(n);
                if (object2.e() || object2.equals((Object)VocalEffect.a)) continue;
                object = object2;
                n = n2;
                break;
            }
        } else {
            n = n2;
        }
        do {
            block11 : {
                block10 : {
                    if (n >= this.a.size()) break block10;
                    if (!this.a.get(n).equals(object)) break block11;
                    this.e = n;
                }
                this.d = onVocalEffectItemClickListener;
                return;
            }
            ++n;
        } while (true);
    }

    public VocalEffect a() {
        return this.a.get(this.e);
    }

    public void a(boolean bl) {
        this.f = bl;
        this.notifyItemChanged(this.e);
    }

    public int getItemCount() {
        return this.a.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int n) {
        float f;
        int n2 = 0;
        float f2 = 0.0f;
        VocalEffectsViewHolder vocalEffectsViewHolder = (VocalEffectsViewHolder)viewHolder;
        final VocalEffect vocalEffect = this.a.get(n);
        if (vocalEffect.a()) {
            f = VocalEffect.a(this.c, vocalEffect.b());
            f2 = VocalEffect.b(this.c, vocalEffect.b());
            vocalEffectsViewHolder.d();
        } else {
            f = 0.0f;
        }
        boolean bl = vocalEffect.a();
        boolean bl2 = this.e == n;
        final VocalEffectListItem vocalEffectListItem = new VocalEffectListItem(f, f2, bl, bl2);
        vocalEffectsViewHolder.c.setText((CharSequence)vocalEffect.a(this.c));
        vocalEffectsViewHolder.a.setVocalEffect(vocalEffect);
        ImageView imageView = vocalEffectsViewHolder.e;
        n = vocalEffect.e() ? n2 : 8;
        imageView.setVisibility(n);
        vocalEffectsViewHolder.b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (VocalEffectsAdapter.this.d != null) {
                    VocalEffectsAdapter.this.e = viewHolder.getAdapterPosition();
                    VocalEffectsAdapter.this.d.a(vocalEffect.b(), viewHolder.getAdapterPosition(), vocalEffectListItem.d(), vocalEffectListItem.e(), true, vocalEffectListItem.b());
                    vocalEffectListItem.c();
                    VocalEffectsAdapter.this.notifyDataSetChanged();
                }
            }
        });
        vocalEffectsViewHolder.a(this.f);
        if (!vocalEffectListItem.a()) {
            vocalEffectsViewHolder.a();
            return;
        }
        if (vocalEffect.a()) {
            vocalEffectsViewHolder.c();
            return;
        }
        vocalEffectsViewHolder.b();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int n) {
        return new VocalEffectsViewHolder(LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903218, viewGroup, false));
    }

    public class VocalEffectsViewHolder
    extends EffectsBaseAdapter.EffectsViewHolder {
        public VocalEffectsViewHolder(View view) {
            super(view);
        }

        private void d() {
            int n = VocalEffectsAdapter.this.c.getResources().getDimensionPixelOffset(2131427521);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams)this.e.getLayoutParams();
            marginLayoutParams.bottomMargin += n;
            marginLayoutParams.rightMargin = n + marginLayoutParams.rightMargin;
            this.e.setLayoutParams((ViewGroup.LayoutParams)marginLayoutParams);
        }

        @Override
        protected void a() {
            this.a.setButtonState(ButtonState.a);
            this.c.setTextColor(ContextCompat.getColor((Context)VocalEffectsAdapter.this.c, (int)2131689726));
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(boolean bl) {
            ProgressBar progressBar = this.d;
            int n = bl ? 0 : 8;
            progressBar.setVisibility(n);
        }

        @Override
        protected void b() {
            this.a.setButtonState(ButtonState.b);
            this.c.setTextColor(ContextCompat.getColor((Context)VocalEffectsAdapter.this.c, (int)2131689991));
        }

        @Override
        protected void c() {
            this.a.setButtonState(ButtonState.c);
            this.c.setTextColor(ContextCompat.getColor((Context)VocalEffectsAdapter.this.c, (int)2131689991));
        }
    }

}

