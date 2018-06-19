package com.smule.android.utils;

import android.graphics.Bitmap;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;

class ImageUtils$1 implements ImageLoadingListener {
    ImageUtils$1() {
    }

    public void mo6154a(String str, View view) {
    }

    public void mo6156a(String str, View view, FailReason failReason) {
        Log.d(ImageUtils.a(), "Image Loading Failed. Url: " + str);
    }

    public void mo6155a(String str, View view, Bitmap bitmap) {
    }

    public void mo6157b(String str, View view) {
        Log.b(ImageUtils.a(), "Image Loading Cancelled. Url: " + str);
    }
}
