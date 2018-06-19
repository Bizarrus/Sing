package com.smule.singandroid;

import android.os.Bundle;
import com.smule.android.network.models.PerformanceV2;

public class PerformanceSaveFragmentFactory {
    public static PerformanceSaveFragment m20780a(PostSingBundle postSingBundle, Bundle bundle) {
        PerformanceSaveFragment performanceSaveFragment = null;
        if (!(bundle == null || bundle.getString("VIDEO_FILE", "").isEmpty())) {
            performanceSaveFragment = new PerformanceSaveVideoFragment_();
        }
        if (performanceSaveFragment == null) {
            performanceSaveFragment = new PerformanceSaveFragment_();
        }
        performanceSaveFragment.f19188A = postSingBundle;
        performanceSaveFragment.setArguments(bundle);
        return performanceSaveFragment;
    }

    public static PerformanceSaveFragment m20779a(PerformanceV2 performanceV2) {
        PerformanceSaveFragment performanceSaveFragment = null;
        if (performanceV2 != null) {
            String str = performanceV2.videoRenderedUrl;
            if (!(str == null || str.isEmpty())) {
                performanceSaveFragment = new PerformanceSaveVideoFragment_();
            }
        }
        if (performanceSaveFragment == null) {
            performanceSaveFragment = new PerformanceSaveFragment_();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("PERFORMANCE_SAVE_PERFORMANCE_KEY", performanceV2);
        performanceSaveFragment.setArguments(bundle);
        return performanceSaveFragment;
    }
}
