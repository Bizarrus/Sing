package com.smule.android.utils;

import android.content.Context;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import java.io.File;
import java.io.FileFilter;

public class DiskUsageRunnable implements Runnable {
    public static final String f17755a = DiskUsageRunnable.class.getSimpleName();
    private final File f17756b;
    private final File f17757c;
    private final File f17758d;
    private final File f17759e;
    private final FileFilter f17760f = new C36651(this);

    class C36651 implements FileFilter {
        final /* synthetic */ DiskUsageRunnable f17754a;

        C36651(DiskUsageRunnable diskUsageRunnable) {
            this.f17754a = diskUsageRunnable;
        }

        public boolean accept(File file) {
            return file.toString().endsWith(".mp4");
        }
    }

    public DiskUsageRunnable(Context context) {
        this.f17756b = new File(context.getApplicationInfo().dataDir);
        this.f17757c = new File(ResourceUtils.m19032b(context));
        this.f17758d = StorageUtils.m17339b(context);
        this.f17759e = new File(ResourceUtils.m19029a(context) + File.separator + "upload_queue");
    }

    private long m18959a(File file) {
        return m18960a(file, true);
    }

    private long m18960a(File file, boolean z) {
        return m18961a(file, z, null);
    }

    private long m18961a(File file, boolean z, FileFilter fileFilter) {
        long j = 0;
        if (fileFilter != null) {
            try {
                File[] listFiles = file.listFiles(fileFilter);
            } catch (Exception e) {
                return -1;
            }
        }
        listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                j += file2.length();
            } else if (file2.isDirectory() && z) {
                j += m18961a(file2, z, fileFilter);
            }
        }
        return j;
    }

    private long m18962b(File file) {
        try {
            return file.getFreeSpace();
        } catch (Exception e) {
            return -1;
        }
    }

    public void run() {
        Log.b(f17755a, "start");
        long b = m18962b(this.f17756b);
        long b2 = m18962b(this.f17757c);
        long b3 = m18962b(this.f17758d);
        long a = m18959a(this.f17756b);
        long a2 = m18959a(this.f17757c);
        long a3 = m18959a(this.f17758d);
        long a4 = m18960a(this.f17757c, false);
        long a5 = m18960a(this.f17759e, true);
        long a6 = m18961a(this.f17757c, false, this.f17760f);
        a5 += a6;
        a4 -= a6;
        Log.b(f17755a, "data dir:" + this.f17756b.getAbsolutePath() + ":" + b + " " + a);
        Log.b(f17755a, "cache dir:" + this.f17757c.getAbsolutePath() + ":" + b2 + " " + a2);
        Log.b(f17755a, "image cache dir:" + this.f17758d.getAbsolutePath() + ":" + b3 + " " + a3);
        Log.b(f17755a, "snp:" + a4);
        Log.b(f17755a, "pending upload:" + a5);
        Analytics.m17836a(b, a, a4, a3, a5);
    }
}
