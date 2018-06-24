/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Color
 *  android.widget.SeekBar
 *  android.widget.SeekBar$OnSeekBarChangeListener
 *  android.widget.TextView
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.content.res.Resources;
import android.graphics.Color;
import android.widget.SeekBar;
import android.widget.TextView;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.customviews.IconFontView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class IconFontDebugFragment
extends BaseFragment {
    @ViewById
    protected SeekBar g;
    @ViewById
    protected TextView h;
    @ViewById
    protected TextView i;
    @ViewById
    protected SeekBar j;
    @ViewById
    protected IconFontView k;

    @AfterViews
    protected void a() {
        String[] arrstring = this.getResources().getStringArray(2131230729);
        StringBuilder stringBuilder = new StringBuilder();
        int n = arrstring.length;
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(arrstring[i]);
        }
        this.k.setText((CharSequence)stringBuilder.toString());
        this.g.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                if (IconFontDebugFragment.this.h != null && IconFontDebugFragment.this.k != null) {
                    IconFontDebugFragment.this.h.setText((CharSequence)("Icon font size: " + n));
                    IconFontDebugFragment.this.k.setTextSize(1, (float)n);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.j.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onProgressChanged(SeekBar seekBar, int n, boolean bl) {
                int n2 = 0;
                if (IconFontDebugFragment.this.i == null) return;
                if (IconFontDebugFragment.this.k != null && bl) {
                    int n3;
                    if (n < 256) {
                        int n4 = 0;
                        n3 = n;
                        n = n4;
                    } else if (n < 512) {
                        n3 = 256 - n % 256;
                        int n5 = 0;
                        n2 = n % 256;
                        n = n5;
                    } else if (n < 768) {
                        n3 = n % 256;
                        n = 0;
                        n2 = 255;
                    } else if (n < 1024) {
                        n3 = n % 256;
                        n2 = 256 - n % 256;
                        int n6 = 256 - n % 256;
                        n = n3;
                        n3 = n6;
                    } else if (n < 1280) {
                        n3 = n % 256;
                        n = 255;
                    } else if (n < 1536) {
                        n2 = n % 256;
                        n3 = 256 - n % 256;
                        n = 255;
                    } else if (n < 1792) {
                        n3 = n % 256;
                        n2 = 255;
                        n = 255;
                    } else {
                        n3 = 0;
                        n = 0;
                    }
                    n = Color.argb((int)255, (int)n, (int)n2, (int)n3);
                    IconFontDebugFragment.this.i.setTextColor(n);
                    IconFontDebugFragment.this.k.setTextColor(n);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        this.a((CharSequence)"All the Icon Fonts");
    }

    @Override
    public String x() {
        return IconFontDebugFragment.class.getName();
    }

}

