package com.smule.singandroid;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.ImageView;
import com.android.camera.CropImageActivity;
import com.facebook.internal.AnalyticsEvents;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.OnSingleClickListener;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.dialogs.PhotoTakingDialog;
import com.smule.singandroid.dialogs.PhotoTakingDialog.PhotoTakingListener;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.ImageToDiskUtils;
import java.io.File;
import java.io.IOException;

public class PhotoTakingActivity extends BaseActivity {
    private static final String f19294g = PhotoTakingActivity.class.getName();
    private int f19295h = 0;
    private int f19296i = 0;
    private Uri f19297j;
    private String f19298k;
    private String f19299l;

    class C39611 extends SimpleImageLoadingListener {
        final /* synthetic */ PhotoTakingActivity f19282a;

        C39611(PhotoTakingActivity photoTakingActivity) {
            this.f19282a = photoTakingActivity;
        }

        public void mo6155a(String str, View view, Bitmap bitmap) {
            this.f19282a.mo6750a(bitmap, null);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mImageCaptureUriString", this.f19298k);
        bundle.putString("mImageCaptureUriPath", this.f19299l);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f19298k = bundle.getString("mImageCaptureUriString");
            this.f19299l = bundle.getString("mImageCaptureUriPath");
        }
    }

    private void m20851a(Uri uri) {
        Intent intent = new Intent(this, CropImageActivity.class);
        intent.setType("image/*");
        if (uri == null || uri.toString().isEmpty()) {
            uri = Uri.parse(this.f19298k);
        }
        if (uri == null) {
            a(C1947R.string.photo_import_error, Toaster$Duration.LONG);
            Log.e(f19294g, "Bad imageURI passed to cropImage");
            return;
        }
        intent.setData(uri);
        intent.putExtra("uriString", this.f19298k);
        intent.putExtra("uriPath", this.f19299l);
        intent.putExtra("outputX", this.f19295h);
        intent.putExtra("outputY", this.f19296i);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        try {
            intent.putExtra("output", FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", File.createTempFile("ptf", "", getCacheDir())));
            intent.putExtra("outputFormat", "JPEG");
            startActivityForResult(intent, 2202);
        } catch (IOException e) {
            a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
            Log.e(f19294g, "Could not create temporary file");
        }
    }

    private File mo6612a() {
        File c = ResourceUtils.m19033c(this);
        if (c != null) {
            return c;
        }
        Log.e(f19294g, "Error creating picture file");
        return null;
    }

    protected boolean m20858a(int i, int i2) {
        this.f19295h = i;
        this.f19296i = i2;
        if (mo6751b()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", this.f19297j);
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("return-data", false);
            if (VERSION.SDK_INT >= 21) {
                intent.addFlags(2);
            } else {
                intent.setClipData(ClipData.newUri(getContentResolver(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, this.f19297j));
                intent.addFlags(2);
            }
            try {
                startActivityForResult(intent, 2201);
                return true;
            } catch (ActivityNotFoundException e) {
                a(getString(C1947R.string.photo_no_app_error), Toaster$Duration.LONG);
                return false;
            }
        }
        a(getString(C1947R.string.photo_create_error), Toaster$Duration.LONG);
        return false;
    }

    private boolean mo6751b() {
        File a = mo6612a();
        if (a == null) {
            Log.e(f19294g, "Null file returned from createJPGFile");
            return false;
        }
        this.f19297j = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", a);
        if (this.f19297j == null) {
            Log.e(f19294g, "Null URI from picture file returned from createJPGFile");
            return false;
        }
        this.f19298k = this.f19297j.toString();
        this.f19299l = this.f19297j.getPath();
        return true;
    }

    protected void m20859b(int i, int i2) {
        this.f19295h = i;
        this.f19296i = i2;
        mo6751b();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, getString(C1947R.string.profile_select_photo)), 2203);
    }

    protected void m20853a(Bitmap bitmap, ImageView imageView) {
        m20854a(bitmap, imageView, Color.parseColor("#222222"));
    }

    protected void m20854a(Bitmap bitmap, ImageView imageView, int i) {
        if (bitmap != null) {
            ImageUtils.a(imageView, bitmap, i, false);
        }
    }

    protected void m20857a(ImageView imageView) {
        String n = UserManager.a().n();
        if (n == null || n.isEmpty()) {
            a(C1947R.string.photo_no_fb_error, Toaster$Duration.LONG);
            return;
        }
        ImageUtils.a("http://graph.facebook.com/" + n + "/picture?type=large", imageView, C1947R.drawable.icn_default_profile_large, true, -1, getResources().getDimensionPixelSize(C1947R.dimen.margin_extra_tiny), new C39611(this), false);
    }

    protected void mo6750a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PhotoTakingActivity f19287c;

            public void run() {
                final NetworkResponse a = UserManager.a().a(bitmap);
                this.f19287c.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ C39632 f19284b;

                    public void run() {
                        if (a.c()) {
                            this.f19284b.f19287c.a(this.f19284b.f19287c.getString(C1947R.string.photo_saved), Toaster$Duration.LONG);
                        } else {
                            this.f19284b.f19287c.a(this.f19284b.f19287c.getString(C1947R.string.photo_save_error), Toaster$Duration.LONG);
                        }
                    }
                });
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            Log.b(f19294g, "Cancelled result code, " + i2 + ", returned for request code: " + i);
        } else if (i2 != -1) {
            String string = getString(C1947R.string.photo_generic_error);
            switch (i) {
                case 2201:
                    string = getString(C1947R.string.photo_generic_error);
                    break;
                case 2202:
                    string = getString(C1947R.string.photo_resize_error);
                    break;
                case 2203:
                    string = getString(C1947R.string.photo_import_error);
                    break;
            }
            a(string, Toaster$Duration.LONG);
            Log.e(f19294g, "Bad result code, " + i2 + ", returned for request code: " + i);
        } else {
            switch (i) {
                case 2201:
                    m20851a(this.f19297j);
                    return;
                case 2203:
                    if (intent == null) {
                        a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
                        Log.e(f19294g, "Null data returned when picking a photo");
                        return;
                    }
                    Uri a = ImageToDiskUtils.m25832a((Context) this, intent);
                    if (a == null) {
                        a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
                        Log.e(f19294g, "Null data returned when getting photo uri");
                        return;
                    }
                    m20851a(a);
                    return;
                default:
                    return;
            }
        }
    }

    protected void m20856a(View view, ImageView imageView, boolean z, int i, int i2) {
        final boolean z2 = z;
        final int i3 = i;
        final int i4 = i2;
        final ImageView imageView2 = imageView;
        view.setOnClickListener(new OnSingleClickListener(this) {
            final /* synthetic */ PhotoTakingActivity f19293e;

            class C39641 implements PhotoTakingListener {
                final /* synthetic */ C39653 f19288a;

                C39641(C39653 c39653) {
                    this.f19288a = c39653;
                }

                public void mo6532a(int i) {
                    this.f19288a.m19018a();
                    if (!this.f19288a.f19293e.isFinishing()) {
                        switch (i) {
                            case 1:
                                this.f19288a.f19293e.m20858a(i3, i4);
                                return;
                            case 2:
                                this.f19288a.f19293e.m20859b(i3, i4);
                                return;
                            case 3:
                                this.f19288a.f19293e.m20857a(imageView2);
                                return;
                            default:
                                return;
                        }
                    }
                }
            }

            public void mo6533a(View view) {
                PhotoTakingDialog.m23674a(this.f19293e, SingPermissionRequests.f23948b, z2, new C39641(this));
            }
        });
    }
}
