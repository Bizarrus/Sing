package com.smule.android.network.managers;

import android.os.Handler;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;

public class ResourceManager {
    static final String f16999a = ResourceManager.class.getName();
    private static Handler f17000b = new Handler();

    public interface ResourceFetchListener {
        void mo6891a();

        void mo6892a(ResourceV2 resourceV2);
    }

    public static void m18313a(String str, String str2, ResourceFetchListener resourceFetchListener) {
        SongV2 a = StoreManager.m18378a().m18416a(str);
        if (a == null) {
            Log.d(f16999a, "Tried to get resource for unknown song: " + str + " Attempting to fetch song details.");
            a = new SongV2();
            a.songId = str;
            a.eTag = "NULL";
        }
        m18312a(a, str2, resourceFetchListener);
    }

    public static void m18312a(final SongV2 songV2, final String str, final ResourceFetchListener resourceFetchListener) {
        final ResourceV2 a = songV2.a(str);
        if (a != null) {
            f17000b.post(new Runnable() {
                public void run() {
                    resourceFetchListener.mo6892a(a);
                }
            });
        } else {
            MagicNetwork.a(new Runnable() {
                public void run() {
                    SongV2 a = StoreManager.m18378a().m18415a(songV2);
                    final ResourceV2 a2 = a != null ? a.a(str) : null;
                    ResourceManager.f17000b.post(new Runnable(this) {
                        final /* synthetic */ C35972 f16995b;

                        public void run() {
                            if (a2 != null) {
                                resourceFetchListener.mo6892a(a2);
                            } else {
                                resourceFetchListener.mo6891a();
                            }
                        }
                    });
                }
            });
        }
    }
}
