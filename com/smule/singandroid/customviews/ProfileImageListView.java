/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.annotation.Nullable
 *  android.support.v7.widget.LinearLayoutManager
 *  android.support.v7.widget.RecyclerView
 *  android.support.v7.widget.RecyclerView$Adapter
 *  android.support.v7.widget.RecyclerView$LayoutManager
 *  android.support.v7.widget.RecyclerView$ViewHolder
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 */
package com.smule.singandroid.customviews;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import java.util.ArrayList;
import java.util.List;

public class ProfileImageListView
extends RecyclerView {
    private static final String a = ProfileImageListView.class.getSimpleName();
    private Adapter b;
    private ProfileImageListViewListener c;

    public ProfileImageListView(Context context) {
        this(context, null);
    }

    public ProfileImageListView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProfileImageListView(Context context, @Nullable AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.b = new Adapter();
        this.setLayoutManager((RecyclerView.LayoutManager)new LinearLayoutManager(this.getContext(), 0, false));
        this.setAdapter((RecyclerView.Adapter)this.b);
    }

    public void setAccountIcons(List<AccountIcon> list) {
        this.b.a(list);
        this.b.notifyDataSetChanged();
    }

    public void setListener(ProfileImageListViewListener profileImageListViewListener) {
        this.c = profileImageListViewListener;
    }

    public class Adapter
    extends RecyclerView.Adapter<ViewHolder> {
        private List<AccountIcon> b;

        public Adapter() {
            this.b = new ArrayList<AccountIcon>();
        }

        public ViewHolder a(ViewGroup viewGroup, int n) {
            return new ViewHolder(LayoutInflater.from((Context)viewGroup.getContext()).inflate(2130903447, viewGroup, false));
        }

        /*
         * Enabled aggressive block sorting
         */
        public void a(ViewHolder viewHolder, int n) {
            final AccountIcon accountIcon = this.b.get(n);
            View view = viewHolder.a;
            n = n == 0 ? 0 : 8;
            view.setVisibility(n);
            viewHolder.b.setAccount(accountIcon);
            viewHolder.b.a(new View.OnClickListener(){

                public void onClick(View view) {
                    if (ProfileImageListView.this.c != null) {
                        ProfileImageListView.this.c.a(accountIcon);
                    }
                }
            });
        }

        public void a(List<AccountIcon> list) {
            this.b = list;
        }

        public int getItemCount() {
            return this.b.size();
        }

        public /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int n) {
            this.a((ViewHolder)viewHolder, n);
        }

        public /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int n) {
            return this.a(viewGroup, n);
        }

        public class ViewHolder
        extends RecyclerView.ViewHolder {
            public View a;
            public ProfileImageWithVIPBadge b;

            public ViewHolder(View view) {
                super(view);
                this.a = view.findViewById(2131756757);
                this.b = (ProfileImageWithVIPBadge)view.findViewById(2131756758);
            }
        }

    }

    public static interface ProfileImageListViewListener {
        public void a(AccountIcon var1);
    }

}

