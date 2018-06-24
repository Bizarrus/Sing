/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.res.ColorStateList
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.util.DisplayMetrics
 *  android.widget.ImageView
 *  android.widget.ImageView$ScaleType
 *  com.squareup.picasso.Transformation
 */
package com.smule.android.ui.roundedimageview;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import com.smule.android.ui.roundedimageview.RoundedDrawable;
import com.squareup.picasso.Transformation;
import java.util.Arrays;

public final class RoundedTransformationBuilder {
    private final DisplayMetrics a = Resources.getSystem().getDisplayMetrics();
    private float[] b = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
    private boolean c = false;
    private float d = 0.0f;
    private ColorStateList e = ColorStateList.valueOf((int)-16777216);
    private ImageView.ScaleType f = ImageView.ScaleType.FIT_CENTER;

}

