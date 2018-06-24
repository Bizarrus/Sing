/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.support.design.widget.BaseTransientBottomBar
 *  android.support.design.widget.BaseTransientBottomBar$ContentViewCallback
 *  android.support.design.widget.Snackbar
 *  android.support.design.widget.Snackbar$SnackbarLayout
 *  android.support.v4.view.ViewCompat
 *  android.support.v4.view.ViewPropertyAnimatorCompat
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.TextView
 */
package com.smule.singandroid.common.snackbar;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BottomSnackbar
extends BaseTransientBottomBar<BottomSnackbar> {
    private BottomSnackbar(ViewGroup viewGroup, View view, ContentViewCallback contentViewCallback) {
        super(viewGroup, view, (BaseTransientBottomBar.ContentViewCallback)contentViewCallback);
    }

    public static BottomSnackbar a(ViewGroup object, String string2, int n) {
        View view = LayoutInflater.from((Context)object.getContext()).inflate(2130903095, (ViewGroup)object, false);
        object = new BottomSnackbar((ViewGroup)object, view, new ContentViewCallback(view));
        object.setDuration(n);
        object.a(string2);
        ((Snackbar.SnackbarLayout)object.getView()).setPadding(0, 0, 0, 0);
        return object;
    }

    public BottomSnackbar a(int n, final View.OnClickListener onClickListener) {
        TextView textView = (TextView)this.getView().findViewById(2131755343);
        textView.setText(n);
        textView.setVisibility(0);
        textView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                onClickListener.onClick(view);
                BottomSnackbar.this.dismiss();
            }
        });
        return this;
    }

    public BottomSnackbar a(String string2) {
        ((TextView)this.getView().findViewById(2131755342)).setText((CharSequence)string2);
        return this;
    }

    private static class ContentViewCallback
    implements BaseTransientBottomBar.ContentViewCallback {
        private View a;

        public ContentViewCallback(View view) {
            this.a = view;
        }

        public void animateContentIn(int n, int n2) {
            ViewCompat.setScaleY((View)this.a, (float)0.0f);
            ViewCompat.animate((View)this.a).scaleY(1.0f).setDuration((long)n2).setStartDelay((long)n);
        }

        public void animateContentOut(int n, int n2) {
            ViewCompat.setScaleY((View)this.a, (float)1.0f);
            ViewCompat.animate((View)this.a).scaleY(0.0f).setDuration((long)n2).setStartDelay((long)n);
        }
    }

}

