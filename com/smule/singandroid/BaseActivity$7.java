package com.smule.singandroid;

import android.os.Bundle;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.managers.UserManager;
import com.smule.android.uploader.FileUploaderService.VideoUploadStatus;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.adapters.profile.ProfilePerformanceDataSource;
import java.util.Observable;
import java.util.Observer;

class BaseActivity$7 implements Observer {
    final /* synthetic */ BaseActivity f18438a;

    class C37721 implements Runnable {
        final /* synthetic */ BaseActivity$7 f18437a;

        C37721(BaseActivity$7 baseActivity$7) {
            this.f18437a = baseActivity$7;
        }

        public void run() {
            MagicDataSource.m17632a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
            MagicDataSource.m17632a(ProfilePerformanceDataSource.class.getSimpleName() + ":" + UserManager.a().f());
        }
    }

    BaseActivity$7(BaseActivity baseActivity) {
        this.f18438a = baseActivity;
    }

    public void update(Observable observable, Object obj) {
        if (((VideoUploadStatus) ((Bundle) obj).get("FILE_UPLOAD_STATUS")) != VideoUploadStatus.UPLOADING) {
            this.f18438a.runOnUiThread(new C37721(this));
        }
    }
}
