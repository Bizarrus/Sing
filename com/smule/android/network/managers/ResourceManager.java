/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.os.Handler
 */
package com.smule.android.network.managers;

import android.os.Handler;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;

public class ResourceManager {
    static final String a = ResourceManager.class.getName();
    private static Handler b = new Handler();

    public static interface ResourceFetchListener {
        public void a();

        public void a(ResourceV2 var1);
    }

}

