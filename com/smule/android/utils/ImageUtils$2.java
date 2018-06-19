package com.smule.android.utils;

import android.graphics.Bitmap;
import android.support.v4.util.Pair;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;

class ImageUtils$2 implements BitmapDisplayer {
    final String f17768a = this.f17770c;
    final BitmapDisplayer f17769b = DefaultConfigurationFactory.m17087c();
    final /* synthetic */ String f17770c;

    ImageUtils$2(String str) {
        this.f17770c = str;
    }

    public void mo6164a(Bitmap bitmap, ImageAware imageAware, LoadedFrom loadedFrom) {
        this.f17769b.mo6164a(bitmap, imageAware, loadedFrom);
        if (loadedFrom == LoadedFrom.NETWORK) {
            Pair pair;
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (ImageUtils.b()) {
                pair = (Pair) ImageUtils.b().get(this.f17768a);
            }
            if (!(pair == null || pair.first == null || pair.second == null)) {
                EventLogger2.a(this.f17768a, currentTimeMillis - ((Long) pair.first).longValue(), 0, (long) ((Integer) pair.second).intValue(), EventLogger2$ErrorDomain.NONE, 0, null, null, null, false);
            }
            ImageUtils.a(this.f17768a, false);
            return;
        }
        ImageUtils.a(this.f17768a, true);
    }
}
