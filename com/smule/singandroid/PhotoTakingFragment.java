/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.ActivityNotFoundException
 *  android.content.ClipData
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.support.annotation.Nullable
 *  android.support.v4.content.FileProvider
 *  android.text.TextUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.ImageView
 *  com.android.camera.CropImageActivity
 *  com.nostra13.universalimageloader.core.listener.ImageLoadingListener
 *  com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener
 *  com.smule.singandroid.utils.ImageToDiskUtils
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.UiThread
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
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
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OnSingleClickListener;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.ThreadUtils;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.dialogs.PhotoTakingDialog;
import com.smule.singandroid.utils.ImageToDiskUtils;
import java.io.File;
import java.io.IOException;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment
public class PhotoTakingFragment
extends BaseFragment {
    private static final String g = PhotoTakingFragment.class.getName();
    protected boolean aH;
    private int h = 0;
    private int i = 0;
    private File j;
    private Uri k;
    private String l;
    private String m;
    private String n;
    private int o = 0;

    private File a() {
        File file;
        File file2 = file = ResourceUtils.c((Context)this.getActivity());
        if (file == null) {
            Log.e(g, "Error creating picture file");
            file2 = null;
        }
        return file2;
    }

    private void a(Uri object) {
        Intent intent;
        Activity activity;
        Uri uri;
        block7 : {
            block6 : {
                activity = this.getActivity();
                intent = new Intent((Context)activity, CropImageActivity.class);
                intent.setType("image/*");
                if (object == null) break block6;
                uri = object;
                if (!object.toString().isEmpty()) break block7;
            }
            uri = Uri.parse((String)this.l);
        }
        if (uri == null) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(g, "Bad imageURI passed to cropImage");
            return;
        }
        intent.setData(uri);
        intent.putExtra("uriString", this.l);
        intent.putExtra("uriPath", this.m);
        intent.putExtra("outputX", this.h);
        intent.putExtra("outputY", this.i);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        try {
            object = File.createTempFile("ptf", "", activity.getCacheDir());
            this.n = object.getAbsolutePath();
            intent.putExtra("output", (Parcelable)FileProvider.getUriForFile((Context)this.getActivity(), (String)(this.getActivity().getApplicationContext().getPackageName() + ".provider"), (File)object));
            intent.putExtra("outputFormat", "JPEG");
        }
        catch (IOException iOException) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(g, "Could not create temporary file");
            return;
        }
        this.startActivityForResult(intent, 2202);
    }

    private boolean t() {
        this.j = this.a();
        if (this.j == null) {
            Log.e(g, "Null file returned from createJPGFile");
            return false;
        }
        this.k = FileProvider.getUriForFile((Context)this.getActivity(), (String)(this.getActivity().getApplicationContext().getPackageName() + ".provider"), (File)this.j);
        if (this.k == null) {
            Log.e(g, "Null URI from picture file returned from createJPGFile");
            return false;
        }
        this.l = this.k.toString();
        this.m = this.k.getPath();
        return true;
    }

    protected void a(final Bitmap bitmap) {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = UserManager.a().b(bitmap);
                PhotoTakingFragment.this.c(networkResponse);
            }
        });
    }

    protected void a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = UserManager.a().a(bitmap);
                PhotoTakingFragment.this.c(networkResponse);
                if (runnable != null) {
                    ThreadUtils.a(runnable);
                }
            }
        });
    }

    protected void a(View view) {
    }

    protected void a(final View view, final ImageView imageView, final boolean bl, final boolean bl2, final int n, final int n2, final @Nullable Integer n3, final RunTimePermissionsRequest runTimePermissionsRequest) {
        view.setOnClickListener((View.OnClickListener)new OnSingleClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(View view2) {
                boolean bl3;
                boolean bl22 = false;
                PhotoTakingFragment.this.ah();
                if (bl) {
                    String string2 = UserManager.a().n();
                    bl3 = bl22;
                    if (string2 != null) {
                        bl3 = bl22;
                        if (!string2.isEmpty()) {
                            bl3 = true;
                        }
                    }
                } else {
                    bl3 = false;
                }
                PhotoTakingFragment.this.o = view2.getId();
                PhotoTakingDialog.a((BaseActivity)PhotoTakingFragment.this.getActivity(), runTimePermissionsRequest, bl3, bl2, n3, new PhotoTakingDialog.PhotoTakingListener(){

                    /*
                     * Enabled force condition propagation
                     * Lifted jumps to return sites
                     */
                    @Override
                    public void a(int n) {
                        6.this.a();
                        if (!PhotoTakingFragment.this.isAdded()) {
                            do {
                                return;
                                break;
                            } while (true);
                        }
                        switch (n) {
                            case 0: {
                                return;
                            }
                            default: {
                                return;
                            }
                            case 1: {
                                PhotoTakingFragment.this.aH = false;
                                PhotoTakingFragment.this.a(n, n2);
                                return;
                            }
                            case 2: {
                                PhotoTakingFragment.this.aH = false;
                                PhotoTakingFragment.this.b(n, n2);
                                return;
                            }
                            case 3: {
                                PhotoTakingFragment.this.aH = true;
                                PhotoTakingFragment.this.a(imageView);
                                return;
                            }
                            case 4: 
                        }
                        PhotoTakingFragment.this.aH = false;
                        PhotoTakingFragment.this.a(view);
                    }
                });
            }

        });
    }

    protected void a(ImageView imageView) {
        String string2 = UserManager.a().n();
        if (string2 != null && !string2.isEmpty()) {
            ImageUtils.a(MagicFacebook.a(string2), imageView, 2130837899, true, -1, this.getResources().getDimensionPixelSize(2131428171), (ImageLoadingListener)new SimpleImageLoadingListener(){

                public void a(String string2, View view, Bitmap bitmap) {
                    PhotoTakingFragment.this.a(bitmap, null);
                }
            }, false);
            return;
        }
        this.a(this.getString(2131297144), Toaster.b);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected boolean a(int n, int n2) {
        this.h = n;
        this.i = n2;
        if (!this.t()) {
            this.a(this.getString(2131297137), Toaster.b);
            return false;
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", (Parcelable)this.k);
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("return-data", false);
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(2);
        } else {
            intent.setClipData(ClipData.newUri((ContentResolver)this.getActivity().getContentResolver(), (CharSequence)"photo", (Uri)this.k));
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

    protected void ae() {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = UserManager.a().N();
                PhotoTakingFragment.this.d(networkResponse);
            }
        });
    }

    protected void af() {
        MagicNetwork.a(new Runnable(){

            @Override
            public void run() {
                NetworkResponse networkResponse = UserManager.a().R();
                PhotoTakingFragment.this.d(networkResponse);
            }
        });
    }

    protected int ag() {
        return this.o;
    }

    protected void ah() {
    }

    protected void ai() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected Bitmap b(Intent intent) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty((CharSequence)this.n)) {
            Log.e(g, "getCropResult:path empty");
            return bitmap;
        }
        File file = new File(this.n);
        Bitmap bitmap2 = bitmap;
        if (!file.isFile()) return bitmap2;
        bitmap2 = bitmap;
        if (!file.canRead()) return bitmap2;
        bitmap = BitmapFactory.decodeFile((String)file.getAbsolutePath());
        intent.getExtras().putParcelable("data", (Parcelable)bitmap);
        bitmap2 = bitmap;
        if (file.delete()) return bitmap2;
        Log.e(g, "Couldn't delete temporary file after crop");
        return bitmap;
    }

    protected void b(int n, int n2) {
        this.h = n;
        this.i = n2;
        this.t();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        this.startActivityForResult(Intent.createChooser((Intent)intent, (CharSequence)"Select Picture"), 2203);
    }

    @UiThread
    protected void c(NetworkResponse networkResponse) {
        NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        if (!this.isAdded() || this.getActivity() == null) {
            Log.d(g, "processUploadPhotoResponse - fragment not added; aborting");
            return;
        }
        if (networkResponse != null && networkResponse.c()) {
            this.a(this.getString(2131297148), Toaster.b);
            this.ai();
            return;
        }
        this.a(this.getString(2131297147), Toaster.b);
    }

    /*
     * Enabled aggressive block sorting
     */
    @UiThread
    protected void d(NetworkResponse networkResponse) {
        NotificationCenter.a().b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        if (!this.isAdded() || this.getActivity() == null) {
            Log.d(g, "processDeletePhotoResponse - fragment not added; aborting");
            return;
        } else {
            if (networkResponse != null && networkResponse.c() || this.getActivity() == null) return;
            {
                this.a(this.getString(2131297140), Toaster.b);
                return;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onActivityResult(int var1_1, int var2_2, Intent var3_3) {
        block12 : {
            block13 : {
                super.onActivityResult(var1_1, var2_2, (Intent)var3_3);
                if (var2_2 == 0) {
                    Log.b(PhotoTakingFragment.g, "Cancelled result code, " + var2_2 + ", returned for request code: " + var1_1);
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
            Log.e(PhotoTakingFragment.g, "Bad result code, " + var2_2 + ", returned for request code: " + var1_1);
            return;
        }
        switch (var1_1) {
            default: {
                return;
            }
            case 2201: {
                this.a(this.k);
                return;
            }
            case 2203: 
        }
        if (var3_3 == null) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(PhotoTakingFragment.g, "Null data returned when picking a photo");
            return;
        }
        var3_3 = ImageToDiskUtils.a((Context)this.getActivity(), (Intent)var3_3);
        if (var3_3 == null) {
            this.a(this.getString(2131297141), Toaster.b);
            Log.e(PhotoTakingFragment.g, "Null data returned when getting photo uri");
            return;
        }
        this.a((Uri)var3_3);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            return;
        }
        this.l = bundle.getString("mImageCaptureUriString");
        this.m = bundle.getString("mImageCaptureUriPath");
        this.n = bundle.getString("mCropTempFilePath");
        this.o = bundle.getInt("mButtonIdClickedForPhoto");
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mImageCaptureUriString", this.l);
        bundle.putString("mImageCaptureUriPath", this.m);
        bundle.putString("mCropTempFilePath", this.n);
        bundle.putInt("mButtonIdClickedForPhoto", this.o);
    }

    @Override
    public String x() {
        return g;
    }

}

