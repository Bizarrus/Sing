/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.utils.ColorSelectorDrawableUtil
 *  com.smule.singandroid.utils.ColorSelectorDrawableUtil$Type
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$ColorFilterType
 *  com.smule.singandroid.video.VideoFilterManager
 *  com.smule.singandroid.video.VideoFilterManager$ColorFilterItem
 */
package com.smule.singandroid.adapters.singflow;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.customviews.ColorSelectorItemViewModel;
import com.smule.singandroid.effectpanel.onclicklistners.OnColorFilterItemClickListener;
import com.smule.singandroid.utils.ColorSelectorDrawableUtil;
import com.smule.singandroid.video.VideoEffects;
import com.smule.singandroid.video.VideoFilterManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ColorFilterAdapter
extends RecyclerView.Adapter<ColorSelectorViewHolder> {
    private final List<VideoFilterManager.ColorFilterItem> a = new ArrayList<VideoFilterManager.ColorFilterItem>();
    private Context b;
    private OnColorFilterItemClickListener c;
    private int d = -1;

    public ColorFilterAdapter(Context context, List<VideoFilterManager.ColorFilterItem> list, String string2, OnColorFilterItemClickListener onColorFilterItemClickListener) {
        this.b = context;
        this.a.addAll(list);
        int n = 0;
        do {
            block4 : {
                block3 : {
                    if (n >= list.size()) break block3;
                    if (!list.get((int)n).a.a().equals(string2)) break block4;
                    this.d = n;
                }
                this.c = onColorFilterItemClickListener;
                return;
            }
            ++n;
        } while (true);
    }

    public ColorSelectorViewHolder a(ViewGroup viewGroup, int n) {
        return new ColorSelectorViewHolder(LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903122, viewGroup, false));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(final ColorSelectorViewHolder colorSelectorViewHolder, int n) {
        int n2 = 0;
        final VideoFilterManager.ColorFilterItem colorFilterItem = this.a.get(n);
        int n3 = colorFilterItem.a.c();
        String string2 = colorFilterItem.a.b();
        boolean bl = colorFilterItem.b;
        boolean bl2 = this.d == n;
        ColorSelectorItemViewModel colorSelectorItemViewModel = new ColorSelectorItemViewModel(n3, 2131689728, string2, bl, bl2);
        colorSelectorViewHolder.c.setText((CharSequence)colorSelectorItemViewModel.c());
        string2 = colorSelectorViewHolder.d;
        n = colorSelectorItemViewModel.d() ? n2 : 8;
        string2.setVisibility(n);
        string2 = colorFilterItem.a.a().equals(VideoEffects.ColorFilterType.a.a()) ? ColorSelectorDrawableUtil.Type.b : ColorSelectorDrawableUtil.Type.a;
        colorSelectorViewHolder.b.setImageDrawable(ColorSelectorDrawableUtil.a((Context)this.b, (int)colorSelectorItemViewModel.a(), (int)colorSelectorItemViewModel.b(), (ColorSelectorDrawableUtil.Type)string2));
        bl2 = colorSelectorItemViewModel.e();
        colorSelectorViewHolder.b.setSelected(bl2);
        if (bl2) {
            colorSelectorViewHolder.c.setTextColor(ContextCompat.getColor((Context)this.b, (int)2131689991));
        } else {
            colorSelectorViewHolder.c.setTextColor(ContextCompat.getColor((Context)this.b, (int)2131689726));
        }
        colorSelectorViewHolder.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (ColorFilterAdapter.this.c != null && ColorFilterAdapter.this.d != colorSelectorViewHolder.getAdapterPosition()) {
                    ColorFilterAdapter.this.d = colorSelectorViewHolder.getAdapterPosition();
                    colorSelectorViewHolder.b.setSelected(true);
                    ColorFilterAdapter.this.c.a(colorFilterItem, ColorFilterAdapter.this.d);
                    ColorFilterAdapter.this.notifyDataSetChanged();
                }
            }
        });
        colorSelectorViewHolder.b.setContentDescription((CharSequence)(colorFilterItem.a.a() + ":" + colorSelectorViewHolder.b.isSelected()));
    }

    public int getItemCount() {
        return this.a.size();
    }

    public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int n) {
        this.a((ColorSelectorViewHolder)viewHolder, n);
    }

    public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int n) {
        return this.a(viewGroup, n);
    }

    public class ColorSelectorViewHolder
    extends RecyclerView.ViewHolder {
        public FrameLayout a;
        public ImageView b;
        public TextView c;
        public ImageView d;

        public ColorSelectorViewHolder(View view) {
            super(view);
            this.a = (FrameLayout)view.findViewById(2131755466);
            this.b = (ImageView)view.findViewById(2131755464);
            this.c = (TextView)view.findViewById(2131755176);
            this.d = (ImageView)view.findViewById(2131755465);
        }
    }

}

