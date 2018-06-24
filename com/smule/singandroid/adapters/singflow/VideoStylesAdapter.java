/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.v4.content.ContextCompat
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.TextView
 *  com.smule.singandroid.video.VideoEffects
 *  com.smule.singandroid.video.VideoEffects$VideoStyleType
 *  com.smule.singandroid.video.VideoFilterManager
 *  com.smule.singandroid.video.VideoFilterManager$VideoStyleItem
 */
package com.smule.singandroid.adapters.singflow;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.smule.singandroid.adapters.singflow.EffectsBaseAdapter;
import com.smule.singandroid.effectpanel.ButtonState;
import com.smule.singandroid.effectpanel.EffectPanelButtonIcon;
import com.smule.singandroid.effectpanel.VideoStyleListItem;
import com.smule.singandroid.effectpanel.onclicklistners.OnVideoStyleClickListener;
import com.smule.singandroid.video.VideoEffects;
import com.smule.singandroid.video.VideoFilterManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VideoStylesAdapter
extends EffectsBaseAdapter {
    private final List<VideoFilterManager.VideoStyleItem> a = new ArrayList<VideoFilterManager.VideoStyleItem>();
    private Context b;
    private OnVideoStyleClickListener c;
    private boolean d;
    private int e = -1;

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public VideoStylesAdapter(Context context, List<VideoFilterManager.VideoStyleItem> list, boolean bl, String string2, OnVideoStyleClickListener onVideoStyleClickListener) {
        this.b = context;
        this.a.addAll(list);
        this.d = bl;
        this.c = onVideoStyleClickListener;
        if (this.d) {
            this.e = 0;
            return;
        }
        int n = 0;
        while (n < this.a.size()) {
            if (this.a.get((int)n).a.a().equals(string2)) {
                this.e = n;
                return;
            }
            ++n;
        }
    }

    public int getItemCount() {
        return this.a.size();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int n) {
        int n2 = 0;
        VideoStylesViewHolder videoStylesViewHolder = (VideoStylesViewHolder)viewHolder;
        final VideoFilterManager.VideoStyleItem videoStyleItem = this.a.get(n);
        boolean bl = this.e == n;
        final VideoStyleListItem videoStyleListItem = new VideoStyleListItem(true, bl);
        bl = VideoFilterManager.a((String)videoStyleItem.a.a());
        videoStylesViewHolder.c.setText((CharSequence)videoStyleItem.a.c());
        videoStylesViewHolder.a.a(videoStyleItem, this.d);
        ImageView imageView = videoStylesViewHolder.e;
        n = bl ? n2 : 8;
        imageView.setVisibility(n);
        videoStylesViewHolder.b.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                if (VideoStylesAdapter.this.c != null) {
                    boolean bl = !videoStyleItem.c && (!VideoStylesAdapter.this.d || viewHolder.getAdapterPosition() == 0);
                    VideoStylesAdapter.this.c.a(viewHolder.getAdapterPosition(), videoStyleItem, videoStyleListItem.b());
                    if (bl) {
                        VideoStylesAdapter.this.e = viewHolder.getAdapterPosition();
                        videoStyleListItem.c();
                        VideoStylesAdapter.this.notifyDataSetChanged();
                    }
                }
            }
        });
        if (videoStyleListItem.a()) {
            videoStylesViewHolder.c();
            return;
        }
        videoStylesViewHolder.a();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int n) {
        return new VideoStylesViewHolder(LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903218, viewGroup, false));
    }

    public class VideoStylesViewHolder
    extends EffectsBaseAdapter.EffectsViewHolder {
        public VideoStylesViewHolder(View view) {
            super(view);
        }

        @Override
        protected void a() {
            this.a.setButtonState(ButtonState.a);
            this.c.setTextColor(ContextCompat.getColor((Context)VideoStylesAdapter.this.b, (int)2131689726));
        }

        @Override
        protected void b() {
            this.a.setButtonState(ButtonState.b);
            this.c.setTextColor(ContextCompat.getColor((Context)VideoStylesAdapter.this.b, (int)2131689991));
        }

        @Override
        protected void c() {
            this.a.setButtonState(ButtonState.c);
            this.c.setTextColor(ContextCompat.getColor((Context)VideoStylesAdapter.this.b, (int)2131689991));
        }
    }

}

