package com.smule.singandroid;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.android.camera.CropImageActivity;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.internal.ShareConstants;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.UserManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.OnSingleClickListener;
import com.smule.android.utils.ResourceUtils;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.dialogs.PhotoTakingDialog;
import com.smule.singandroid.dialogs.PhotoTakingDialog.PhotoTakingListener;
import com.smule.singandroid.utils.ImageToDiskUtils;
import java.io.File;
import java.io.IOException;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

@EFragment
public class PhotoTakingFragment extends BaseFragment {
    private static final String f19179e = PhotoTakingFragment.class.getName();
    private int f19180f = 0;
    private int f19181g = 0;
    private File f19182h;
    private Uri f19183i;
    private String f19184j;
    private String f19185k;
    private String f19186l;

    class C39661 extends SimpleImageLoadingListener {
        final /* synthetic */ PhotoTakingFragment f19300a;

        C39661(PhotoTakingFragment photoTakingFragment) {
            this.f19300a = photoTakingFragment;
        }

        public void mo6155a(String str, View view, Bitmap bitmap) {
            this.f19300a.m20707a(bitmap, null);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mImageCaptureUriString", this.f19184j);
        bundle.putString("mImageCaptureUriPath", this.f19185k);
        bundle.putString("mCropTempFilePath", this.f19186l);
    }

    public String mo6383s() {
        return f19179e;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f19184j = bundle.getString("mImageCaptureUriString");
            this.f19185k = bundle.getString("mImageCaptureUriPath");
            this.f19186l = bundle.getString("mCropTempFilePath");
        }
    }

    private void m20705a(Uri uri) {
        Context activity = getActivity();
        Intent intent = new Intent(activity, CropImageActivity.class);
        intent.setType("image/*");
        if (uri == null || uri.toString().isEmpty()) {
            uri = Uri.parse(this.f19184j);
        }
        if (uri == null) {
            m19841a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
            Log.e(f19179e, "Bad imageURI passed to cropImage");
            return;
        }
        intent.setData(uri);
        intent.putExtra("uriString", this.f19184j);
        intent.putExtra("uriPath", this.f19185k);
        intent.putExtra("outputX", this.f19180f);
        intent.putExtra("outputY", this.f19181g);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        try {
            File createTempFile = File.createTempFile("ptf", "", activity.getCacheDir());
            this.f19186l = createTempFile.getAbsolutePath();
            intent.putExtra("output", FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", createTempFile));
            intent.putExtra("outputFormat", "JPEG");
            startActivityForResult(intent, 2202);
        } catch (IOException e) {
            m19841a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
            Log.e(f19179e, "Could not create temporary file");
        }
    }

    private File mo6510a() {
        File c = ResourceUtils.m19033c(getActivity());
        if (c != null) {
            return c;
        }
        Log.e(f19179e, "Error creating picture file");
        return null;
    }

    protected boolean m20710a(int i, int i2) {
        this.f19180f = i;
        this.f19181g = i2;
        if (mo6514z()) {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", this.f19183i);
            intent.putExtra("outputFormat", "JPEG");
            intent.putExtra("return-data", false);
            if (VERSION.SDK_INT >= 21) {
                intent.addFlags(2);
            } else {
                intent.setClipData(ClipData.newUri(getActivity().getContentResolver(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, this.f19183i));
                intent.addFlags(2);
            }
            try {
                startActivityForResult(intent, 2201);
                return true;
            } catch (ActivityNotFoundException e) {
                m19841a(getString(C1947R.string.photo_no_app_error), Toaster$Duration.LONG);
                return false;
            }
        }
        m19841a(getString(C1947R.string.photo_create_error), Toaster$Duration.LONG);
        return false;
    }

    private boolean mo6514z() {
        this.f19182h = mo6510a();
        if (this.f19182h == null) {
            Log.e(f19179e, "Null file returned from createJPGFile");
            return false;
        }
        this.f19183i = FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", this.f19182h);
        if (this.f19183i == null) {
            Log.e(f19179e, "Null URI from picture file returned from createJPGFile");
            return false;
        }
        this.f19184j = this.f19183i.toString();
        this.f19185k = this.f19183i.getPath();
        return true;
    }

    protected void m20712b(int i, int i2) {
        this.f19180f = i;
        this.f19181g = i2;
        mo6514z();
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2203);
    }

    protected void m20709a(ImageView imageView) {
        String n = UserManager.a().n();
        if (n == null || n.isEmpty()) {
            m19841a(getString(C1947R.string.photo_no_fb_error), Toaster$Duration.LONG);
            return;
        }
        ImageUtils.a("http://graph.facebook.com/" + n + "/picture?type=large", imageView, C1947R.drawable.icn_default_profile_large, true, -1, getResources().getDimensionPixelSize(C1947R.dimen.margin_extra_tiny), new C39661(this), false);
    }

    @UiThread
    protected void mo6522c(NetworkResponse networkResponse) {
        NotificationCenter.m19011a().m19017b("PROFILE_UPDATED_NOTIFICATION", new Object[0]);
        if (!isAdded()) {
            Log.d(f19179e, "processUploadPhotoResponse - fragment not added; aborting");
        } else if (networkResponse == null || !networkResponse.c()) {
            if (getActivity() != null) {
                m19841a(getString(C1947R.string.photo_save_error), Toaster$Duration.LONG);
            }
        } else if (getActivity() != null) {
            m19841a(getString(C1947R.string.photo_saved), Toaster$Duration.LONG);
        }
    }

    protected void m20707a(final Bitmap bitmap, final Runnable runnable) {
        MagicNetwork.a(new Runnable(this) {
            final /* synthetic */ PhotoTakingFragment f19303c;

            public void run() {
                this.f19303c.mo6522c(UserManager.a().a(bitmap));
                if (runnable != null) {
                    runnable.run();
                }
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 0) {
            Log.b(f19179e, "Cancelled result code, " + i2 + ", returned for request code: " + i);
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
            m19841a(string, Toaster$Duration.LONG);
            Log.e(f19179e, "Bad result code, " + i2 + ", returned for request code: " + i);
        } else {
            switch (i) {
                case 2201:
                    m20705a(this.f19183i);
                    return;
                case 2203:
                    if (intent == null) {
                        m19841a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
                        Log.e(f19179e, "Null data returned when picking a photo");
                        return;
                    }
                    Uri a = ImageToDiskUtils.m25832a(getActivity(), intent);
                    if (a == null) {
                        m19841a(getString(C1947R.string.photo_import_error), Toaster$Duration.LONG);
                        Log.e(f19179e, "Null data returned when getting photo uri");
                        return;
                    }
                    m20705a(a);
                    return;
                default:
                    return;
            }
        }
    }

    protected Bitmap m20711b(Intent intent) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(this.f19186l)) {
            Log.e(f19179e, "getCropResult:path empty");
        } else {
            File file = new File(this.f19186l);
            if (file.isFile() && file.canRead()) {
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                intent.getExtras().putParcelable(ShareConstants.WEB_DIALOG_PARAM_DATA, bitmap);
                if (!file.delete()) {
                    Log.e(f19179e, "Couldn't delete temporary file after crop");
                }
            }
        }
        return bitmap;
    }

    protected void m20708a(View view, ImageView imageView, boolean z, int i, int i2, RunTimePermissionsRequest runTimePermissionsRequest) {
        final boolean z2 = z;
        final RunTimePermissionsRequest runTimePermissionsRequest2 = runTimePermissionsRequest;
        final int i3 = i;
        final int i4 = i2;
        final ImageView imageView2 = imageView;
        view.setOnClickListener(new OnSingleClickListener(this) {
            final /* synthetic */ PhotoTakingFragment f19310f;

            class C39681 implements PhotoTakingListener {
                final /* synthetic */ C39693 f19304a;

                C39681(C39693 c39693) {
                    this.f19304a = c39693;
                }

                public void mo6532a(int i) {
                    this.f19304a.m19018a();
                    if (this.f19304a.f19310f.isAdded()) {
                        switch (i) {
                            case 1:
                                this.f19304a.f19310f.m20710a(i3, i4);
                                return;
                            case 2:
                                this.f19304a.f19310f.m20712b(i3, i4);
                                return;
                            case 3:
                                this.f19304a.f19310f.m20709a(imageView2);
                                return;
                            default:
                                return;
                        }
                    }
                }
            }

            public void mo6533a(View view) {
                boolean z;
                boolean z2 = false;
                if (z2) {
                    String n = UserManager.a().n();
                    if (!(n == null || n.isEmpty())) {
                        z2 = true;
                    }
                    z = z2;
                } else {
                    z = false;
                }
                PhotoTakingDialog.m23674a((BaseActivity) this.f19310f.getActivity(), runTimePermissionsRequest2, z, new C39681(this));
            }
        });
    }
}
