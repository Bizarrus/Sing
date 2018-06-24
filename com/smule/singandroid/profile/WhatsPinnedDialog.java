/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.TableRow
 *  android.widget.TableRow$LayoutParams
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 */
package com.smule.singandroid.profile;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;

public class WhatsPinnedDialog
extends SmuleDialog {
    private TextView a;
    private TextView b;
    private View c;

    public WhatsPinnedDialog(final BaseFragment baseFragment, final boolean bl) {
        super((Context)baseFragment.getActivity(), 2131493244);
        this.setContentView(2130903470);
        this.a = (TextView)this.findViewById(2131756827);
        this.b = (TextView)this.findViewById(2131756829);
        this.c = this.findViewById(2131756828);
        this.a.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                WhatsPinnedDialog.this.dismiss();
            }
        });
        this.b.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (!bl) {
                    baseFragment.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (String)SongbookManager.b().d(), (String)null, (UpsellType)UpsellType.l));
                }
                WhatsPinnedDialog.this.dismiss();
            }
        });
        if (bl) {
            this.a();
        }
    }

    private void a() {
        this.a.setVisibility(8);
        this.c.setVisibility(8);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0, this.b.getLayoutParams().height, 1.0f);
        this.b.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.b.setText((CharSequence)this.getContext().getResources().getString(2131296691));
    }

}

