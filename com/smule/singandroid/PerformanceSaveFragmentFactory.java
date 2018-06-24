/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.os.Parcelable
 */
package com.smule.singandroid;

import android.os.Bundle;
import android.os.Parcelable;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.PerformanceSaveFragment;
import com.smule.singandroid.PerformanceSaveFragment_;
import com.smule.singandroid.PerformanceSaveVideoFragment_;
import com.smule.singandroid.PostSingBundle;

public class PerformanceSaveFragmentFactory {
    public static PerformanceSaveFragment a(PerformanceV2 performanceV2) {
        PerformanceSaveFragment_ performanceSaveFragment_;
        PerformanceSaveFragment performanceSaveFragment = performanceSaveFragment_ = null;
        if (performanceV2 != null) {
            String string2 = performanceV2.videoRenderedUrl;
            performanceSaveFragment = performanceSaveFragment_;
            if (string2 != null) {
                performanceSaveFragment = performanceSaveFragment_;
                if (!string2.isEmpty()) {
                    performanceSaveFragment = new PerformanceSaveVideoFragment_();
                }
            }
        }
        performanceSaveFragment_ = performanceSaveFragment;
        if (performanceSaveFragment == null) {
            performanceSaveFragment_ = new PerformanceSaveFragment_();
        }
        performanceSaveFragment = new Bundle();
        performanceSaveFragment.putParcelable("PERFORMANCE_SAVE_PERFORMANCE_KEY", (Parcelable)performanceV2);
        performanceSaveFragment_.setArguments((Bundle)performanceSaveFragment);
        return performanceSaveFragment_;
    }

    public static PerformanceSaveFragment a(PostSingBundle postSingBundle, Bundle bundle) {
        PerformanceSaveFragment_ performanceSaveFragment_;
        PerformanceSaveFragment performanceSaveFragment = performanceSaveFragment_ = null;
        if (bundle != null) {
            performanceSaveFragment = performanceSaveFragment_;
            if (!bundle.getString("VIDEO_FILE", "").isEmpty()) {
                performanceSaveFragment = new PerformanceSaveVideoFragment_();
            }
        }
        performanceSaveFragment_ = performanceSaveFragment;
        if (performanceSaveFragment == null) {
            performanceSaveFragment_ = new PerformanceSaveFragment_();
        }
        performanceSaveFragment_.C = postSingBundle;
        performanceSaveFragment_.setArguments(bundle);
        return performanceSaveFragment_;
    }
}

