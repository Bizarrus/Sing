/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.TextView
 *  com.smule.singandroid.upsell.UpsellManager
 *  com.smule.singandroid.upsell.UpsellType
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.smule.android.network.managers.SongbookManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.android.ui.dialogs.SmuleDialog;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.fragments.AboutAdsFragment;
import com.smule.singandroid.upsell.UpsellManager;
import com.smule.singandroid.upsell.UpsellType;

public class NativeAdsMoreDialog
extends SmuleDialog {
    public NativeAdsMoreDialog(final BaseFragment baseFragment) {
        super((Context)baseFragment.getActivity(), 2131493244);
        this.setContentView(2130903294);
        View view = this.findViewById(2131755972);
        TextView textView = (TextView)this.findViewById(2131755973);
        TextView textView2 = (TextView)this.findViewById(2131755974);
        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                NativeAdsMoreDialog.this.dismiss();
            }
        });
        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View object) {
                object = SongbookManager.b().c();
                baseFragment.a(UpsellManager.a((boolean)false, (SongbookEntry)null, (String)object, (String)null, (UpsellType)UpsellType.d));
                NativeAdsMoreDialog.this.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                baseFragment.a(AboutAdsFragment.a());
                NativeAdsMoreDialog.this.dismiss();
            }
        });
    }

}

