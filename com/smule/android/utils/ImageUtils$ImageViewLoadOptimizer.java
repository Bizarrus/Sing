package com.smule.android.utils;

import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageUtils$ImageViewLoadOptimizer {
    public String f17773a;

    public boolean m18975a(String str, ImageView imageView, int i) {
        return m18976a(str, imageView, i, false);
    }

    public boolean m18976a(String str, ImageView imageView, int i, boolean z) {
        if (str == null || str.isEmpty() || str.equals(this.f17773a)) {
            return false;
        }
        ImageUtils.a(str, imageView, i, z);
        this.f17773a = str;
        return true;
    }

    public boolean m18977a(String str, ImageView imageView, int i, boolean z, int i2) {
        if (str == null || str.isEmpty() || str.equals(this.f17773a)) {
            return false;
        }
        ImageUtils.a(str, imageView, i, z, -1, i2, null, true);
        this.f17773a = str;
        return true;
    }

    public void m18974a(ImageView imageView) {
        ImageLoader.a().a(imageView);
    }

    public void m18973a() {
        this.f17773a = null;
    }
}
