package com.smule.android.utils;

import android.content.Context;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import com.nostra13.universalimageloader.core.assist.ContentLengthInputStream;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader$Scheme;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

class ImageUtils$ImageUtilsImageDownloader implements ImageDownloader {
    final ImageDownloader f17772a;

    private ImageUtils$ImageUtilsImageDownloader(Context context) {
        this.f17772a = DefaultConfigurationFactory.m17081a(context);
    }

    public InputStream m18972a(String str, Object obj) throws IOException {
        switch (ImageUtils$3.f17771a[ImageDownloader$Scheme.m17283a(str).ordinal()]) {
            case 1:
            case 2:
                long currentTimeMillis = System.currentTimeMillis();
                try {
                    InputStream a = this.f17772a.a(str, obj);
                    if (!(a instanceof ContentLengthInputStream)) {
                        return a;
                    }
                    synchronized (ImageUtils.b()) {
                        ImageUtils.b().put(str, new Pair(Long.valueOf(currentTimeMillis), Integer.valueOf(((ContentLengthInputStream) a).available())));
                    }
                    return a;
                } catch (SocketTimeoutException e) {
                    EventLogger2.a(str, System.currentTimeMillis() - currentTimeMillis, 0, 0, EventLogger2$ErrorDomain.CLIENT, 100, "ImageUtils", null, null, false);
                    throw e;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    String iOException2 = iOException.toString();
                    int i = 0;
                    EventLogger2$ErrorDomain eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.PLATFORM;
                    if (iOException2 != null && iOException2.startsWith("Image request failed with response code")) {
                        Object replaceFirst = iOException2.replaceFirst("Image request failed with response code", "");
                        if (!TextUtils.isEmpty(replaceFirst)) {
                            replaceFirst = replaceFirst.trim();
                        }
                        if (!TextUtils.isEmpty(replaceFirst)) {
                            try {
                                i = Integer.parseInt(replaceFirst);
                                eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.HTTP;
                            } catch (NumberFormatException e3) {
                                i = 0;
                                eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.PLATFORM;
                            }
                        }
                    }
                    EventLogger2.a(str, currentTimeMillis2 - currentTimeMillis, 0, 0, eventLogger2$ErrorDomain, i, "ImageUtils", iOException2, null, false);
                    throw iOException;
                }
            default:
                return this.f17772a.a(str, obj);
        }
    }
}
