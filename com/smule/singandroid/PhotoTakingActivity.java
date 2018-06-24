/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ActivityNotFoundException
 *  android.content.ClipData
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.v4.content.FileProvider
 *  android.view.View
 *  android.widget.ImageView
 *  com.android.camera.CropImageActivity
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.utils.ImageToDiskUtils
 */
package com.smule.singandroid;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import com.android.camera.CropImageActivity;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.facebook.MagicFacebook;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.OnSingleClickListener;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.dialogs.PhotoTakingDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.ImageToDiskUtils;
import java.io.File;
import java.io.IOException;

public class PhotoTakingActivity
extends BaseActivity {
    private static final String g = PhotoTakingActivity.class.getName();
    private int h = 0;
    private int i = 0;
    private Uri j;
    private String k;
    private String l;

    private File a() {
        File file;
        File file2 = file = ResourceUtils.c((Context)this);
        if (file == null) {
            Log.e(g, "Error creating picture file");
            file2 = null;
        }
        return file2;
    }

    private void a(Uri object) {
        Uri uri;
        Intent intent;
        block7 : {
            block6 : {
                intent = new Intent((Context)this, CropImageActivity.class);
                intent.setType("image/*");
                if (object == null) break block6;
                uri = object;
                if (!object.toString().isEmpty()) break block7;
            }
            uri = Uri.parse((String)this.k);
        }
        if (uri == null) {
            this.a(2131297141, Toaster.b);
            Log.e(g, "Bad imageURI passed to cropImage");
            return;
        }
        intent.setData(uri);
        intent.putExtra("uriString", this.k);
        intent.putExtra("uriPath", this.l);
        intent.putExtra("outputX", this.h);
        intent.putExtra("outputY", this.i);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        try {
            object = File.createTempFile("ptf", "", this.getCacheDir());
            intent.putExtra("output", (Parcelable)FileProvider.getUriForFile((Context)this, (String)(this.getApplicationContext().getPackageName() + ".provider"), (File)object));
            intent.putExtra("outputFormat", "JPEG");
        }
        catch (IOException iOException) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(g, "Could not create temporary file");
            return;
        }
        this.startActivityForResult(intent, 2202);
    }

    private boolean b() {
        File file = this.a();
        if (file == null) {
            Log.e(g, "Null file returned from createJPGFile");
            return false;
        }
        this.j = FileProvider.getUriForFile((Context)this, (String)(this.getApplicationContext().getPackageName() + ".provider"), (File)file);
        if (this.j == null) {
            Log.e(g, "Null URI from picture file returned from createJPGFile");
            return false;
        }
        this.k = this.j.toString();
        this.l = this.j.getPath();
        return true;
    }

    protected void a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                final NetworkResponse networkResponse = UserManager.a().a(bitmap);
                PhotoTakingActivity.this.runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        if (networkResponse.c()) {
                            PhotoTakingActivity.this.a(PhotoTakingActivity.this.getString(2131297148), Toaster.b);
                            return;
                        }
                        PhotoTakingActivity.this.a(PhotoTakingActivity.this.getString(2131297147), Toaster.b);
                    }
                });
                if (runnable != null) {
                    runnable.run();
                }
            }

        });
    }

    protected void a(ImageView imageView) {
        String string2 = UserManager.a().n();
        if (string2 != null && !string2.isEmpty()) {
            ImageUtils.a(MagicFacebook.a(string2), imageView, 2130837899, true, -1, this.getResources().getDimensionPixelSize(2131428171), (ImageLoadingListener)new SimpleImageLoadingListener(){

                public void a(String string2, View view, Bitmap bitmap) {
                    PhotoTakingActivity.this.a(bitmap, null);
                }
            }, false);
            return;
        }
        this.a(2131297144, Toaster.b);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected boolean a(int n, int n2) {
        this.h = n;
        this.i = n2;
        if (!this.b()) {
            this.a(this.getString(2131297137), Toaster.b);
            return false;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", (Parcelable)this.j);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("return-data", false);
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(2);
        } else {
            intent.setClipData(ClipData.newUri((ContentResolver)this.getContentResolver(), (CharSequence)"photo", (Uri)this.j));
            intent.addFlags(2);
        }
        try {
            this.startActivityForResult(intent, 2201);
            return true;
        }
        catch (ActivityNotFoundException activityNotFoundException) {
            this.a(this.getString(2131297143), Toaster.b);
            return false;
        }
    }

    protected void b(int n, int n2) {
        this.h = n;
        this.i = n2;
        this.b();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)this.getString(2131297208)), 2203);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void onActivityResult(int var1_1, int var2_2, Intent var3_3) {
        block12 : {
            block13 : {
                super.onActivityResult(var1_1, var2_2, (Intent)var3_3);
                if (var2_2 == 0) {
                    Log.b(PhotoTakingActivity.g, "Cancelled result code, " + var2_2 + ", returned for request code: " + var1_1);
                    return;
                }
                if (var2_2 == -1) break block12;
                var3_3 = this.getString(2131297140);
                switch (var1_1) {
                    case 2201: {
                        var3_3 = this.getString(2131297140);
                        ** break;
                    }
                    case 2203: {
                        var3_3 = this.getString(2131297141);
                    }
lbl13: // 3 sources:
                    default: {
                        break block13;
                    }
                    case 2202: 
                }
                var3_3 = this.getString(2131297146);
            }
            this.a((String)var3_3, Toaster.b);
            Log.e(PhotoTakingActivity.g, "Bad result code, " + var2_2 + ", returned for request code: " + var1_1);
            return;
        }
        switch (var1_1) {
            default: {
                return;
            }
            case 2201: {
                this.a(this.j);
                return;
            }
            case 2203: 
        }
        if (var3_3 == null) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(PhotoTakingActivity.g, "Null data returned when picking a photo");
            return;
        }
        if ((var3_3 = ImageToDiskUtils.a((Context)this, (Intent)var3_3)) == null) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(PhotoTakingActivity.g, "Null data returned when getting photo uri");
            return;
        }
        this.a((Uri)var3_3);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            return;
        }
        this.k = bundle.getString("mImageCaptureUriString");
        this.l = bundle.getString("mImageCaptureUriPath");
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mImageCaptureUriString", this.k);
        bundle.putString("mImageCaptureUriPath", this.l);
    }

}

