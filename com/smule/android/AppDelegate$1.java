package com.smule.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat.Builder;
import android.view.View;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.smule.android.logging.Log;

class AppDelegate$1 implements Runnable {
    final /* synthetic */ AppDelegate$NotificationParams f15531a;
    final /* synthetic */ ImageSize f15532b;
    final /* synthetic */ Context f15533c;
    final /* synthetic */ Builder f15534d;
    final /* synthetic */ AppDelegate f15535e;

    class C34811 implements ImageLoadingListener {
        final /* synthetic */ AppDelegate$1 f15530a;

        C34811(AppDelegate$1 appDelegate$1) {
            this.f15530a = appDelegate$1;
        }

        public void mo6154a(String str, View view) {
            Log.b(AppDelegate.TAG, "onLoadingStarted");
        }

        public void mo6156a(String str, View view, FailReason failReason) {
            this.f15530a.f15535e.postNotification(this.f15530a.f15533c, this.f15530a.f15535e.getAppUID().hashCode(), this.f15530a.f15534d.build());
        }

        public void mo6155a(String str, View view, Bitmap bitmap) {
            this.f15530a.f15534d.setLargeIcon(bitmap);
            this.f15530a.f15535e.postNotification(this.f15530a.f15533c, this.f15530a.f15535e.getAppUID().hashCode(), this.f15530a.f15534d.build());
        }

        public void mo6157b(String str, View view) {
            this.f15530a.f15535e.postNotification(this.f15530a.f15533c, this.f15530a.f15535e.getAppUID().hashCode(), this.f15530a.f15534d.build());
        }
    }

    AppDelegate$1(AppDelegate appDelegate, AppDelegate$NotificationParams appDelegate$NotificationParams, ImageSize imageSize, Context context, Builder builder) {
        this.f15535e = appDelegate;
        this.f15531a = appDelegate$NotificationParams;
        this.f15532b = imageSize;
        this.f15533c = context;
        this.f15534d = builder;
    }

    public void run() {
        ImageLoader.a().a(this.f15531a.f15544f, this.f15532b, new C34811(this));
    }
}
