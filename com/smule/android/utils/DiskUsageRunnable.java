/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.pm.ApplicationInfo
 *  com.nostra13.universalimageloader.utils.StorageUtils
 */
package com.smule.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.utils.ResourceUtils;
import java.io.File;
import java.io.FileFilter;

public class DiskUsageRunnable
implements Runnable {
    public static final String a = DiskUsageRunnable.class.getSimpleName();
    private final File b;
    private final File c;
    private final File d;
    private final File e;
    private final FileFilter f;

    public DiskUsageRunnable(Context context) {
        this.f = new FileFilter(){

            @Override
            public boolean accept(File file) {
                return file.toString().endsWith(".mp4");
            }
        };
        this.b = new File(context.getApplicationInfo().dataDir);
        this.c = new File(ResourceUtils.b(context));
        this.d = StorageUtils.b((Context)context);
        this.e = new File(ResourceUtils.a(context) + File.separator + "upload_queue");
    }

    private long a(File file) {
        return this.a(file, true);
    }

    private long a(File file, boolean bl) {
        return this.a(file, bl, null);
    }

    /*
     * Exception decompiling
     */
    private long a(File var1_1, boolean var2_3, FileFilter var3_4) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 5[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:419)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:471)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2880)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:837)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doJar(Main.java:134)
        // org.benf.cfr.reader.Main.main(Main.java:189)
        throw new IllegalStateException("Decompilation failed");
    }

    private long b(File file) {
        try {
            long l = file.getFreeSpace();
            return l;
        }
        catch (Exception exception) {
            return -1;
        }
    }

    @Override
    public void run() {
        Log.b(a, "start");
        long l = this.b(this.b);
        long l2 = this.b(this.c);
        long l3 = this.b(this.d);
        long l4 = this.a(this.b);
        long l5 = this.a(this.c);
        long l6 = this.a(this.d);
        long l7 = this.a(this.c, false);
        long l8 = this.a(this.e, true);
        long l9 = this.a(this.c, false, this.f);
        Log.b(a, "data dir:" + this.b.getAbsolutePath() + ":" + l + " " + l4);
        Log.b(a, "cache dir:" + this.c.getAbsolutePath() + ":" + l2 + " " + l5);
        Log.b(a, "image cache dir:" + this.d.getAbsolutePath() + ":" + l3 + " " + l6);
        Log.b(a, "snp:" + (l7 -= l9));
        Log.b(a, "pending upload:" + (l8 += l9));
        Analytics.a(l, l4, l7, l6, l8);
    }

}

