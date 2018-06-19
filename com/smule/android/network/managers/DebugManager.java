package com.smule.android.network.managers;

import android.support.annotation.NonNull;
import com.smule.android.network.api.DebugAPI;
import com.smule.android.network.api.DebugAPI$PreuploadRequest;
import com.smule.android.network.api.DebugAPI$UploadRequest;
import com.smule.android.network.core.CoreUtil;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkUtils;
import com.smule.android.network.core.ResponseInterface;
import java.io.File;
import java.util.concurrent.Future;
import okhttp3.MultipartBody.Part;
import retrofit2.FileRequestBody;

public class DebugManager {
    private static final String f16584a = DebugManager.class.getName();
    private static DebugManager f16585b = null;
    private DebugAPI f16586c = ((DebugAPI) MagicNetwork.a().a(DebugAPI.class));

    public interface PreuploadResponseCallback extends ResponseInterface<PreuploadResponse> {
        void handleResponse(PreuploadResponse preuploadResponse);
    }

    public interface UploadResponseCallback extends ResponseInterface<NetworkResponse> {
        void handleResponse(NetworkResponse networkResponse);
    }

    private DebugManager() {
    }

    public static DebugManager m18144a() {
        if (f16585b == null) {
            f16585b = new DebugManager();
        }
        return f16585b;
    }

    public PreuploadResponse m18146a(@NonNull String str) {
        return PreuploadResponse.a(NetworkUtils.m18104a(this.f16586c.preupload(new DebugAPI$PreuploadRequest().setResourceType(str))));
    }

    public Future<?> m18148a(@NonNull final String str, final PreuploadResponseCallback preuploadResponseCallback) {
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ DebugManager f16578c;

            public void run() {
                CoreUtil.m18079a(preuploadResponseCallback, this.f16578c.m18146a(str));
            }
        });
    }

    public NetworkResponse m18145a(File file, String str, long j) {
        return NetworkUtils.m18104a(this.f16586c.upload(Part.createFormData("debug", file.getName(), new FileRequestBody(file)), new DebugAPI$UploadRequest().setResourceType(str).setResourceId(j)));
    }

    public Future<?> m18147a(File file, String str, long j, UploadResponseCallback uploadResponseCallback) {
        final UploadResponseCallback uploadResponseCallback2 = uploadResponseCallback;
        final File file2 = file;
        final String str2 = str;
        final long j2 = j;
        return MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ DebugManager f16583e;

            public void run() {
                CoreUtil.m18079a(uploadResponseCallback2, this.f16583e.m18145a(file2, str2, j2));
            }
        });
    }
}
