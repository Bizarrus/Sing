/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.res.Resources
 *  android.support.annotation.NonNull
 *  android.support.annotation.Nullable
 */
package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.common.OptionsMenu.MenuOption;
import com.smule.singandroid.common.OptionsMenu.OptionsMenu;
import java.util.Set;

public class PhotoTakingDialog {
    private PhotoTakingDialog(Activity activity, boolean bl, boolean bl2, @Nullable Integer n, PhotoTakingListener photoTakingListener) {
        Resources resources = activity.getResources();
        ClickRunnable clickRunnable = new ClickRunnable(0, photoTakingListener);
        OptionsMenu.Builder builder = new OptionsMenu.Builder();
        if (n != null) {
            builder.a(resources.getString(n.intValue()));
        }
        builder.a(new MenuOption(resources.getString(2131297150), new ClickRunnable(1, photoTakingListener)));
        builder.a(new MenuOption(resources.getString(2131297135), new ClickRunnable(2, photoTakingListener)));
        if (bl) {
            builder.a(new MenuOption(resources.getString(2131297142), new ClickRunnable(3, photoTakingListener)));
        }
        if (bl2) {
            builder.a(new MenuOption(resources.getString(2131297145), new ClickRunnable(4, photoTakingListener)));
        }
        builder.a(new MenuOption(resources.getString(2131296672), clickRunnable));
        builder.a(clickRunnable);
        builder.a((Context)activity).a();
    }

    public static void a(final BaseActivity baseActivity, RunTimePermissionsRequest runTimePermissionsRequest, final boolean bl, final boolean bl2, final @Nullable Integer n, final PhotoTakingListener photoTakingListener) {
        baseActivity.a(runTimePermissionsRequest, new RunTimePermissionsRequester.ResultCallback(){

            @Override
            public void a(boolean bl3, @NonNull Set<String> set) {
                if (bl3) {
                    new PhotoTakingDialog((Activity)baseActivity, bl, bl2, n, photoTakingListener);
                    return;
                }
                photoTakingListener.a(0);
            }
        });
    }

    private static class ClickRunnable
    implements MenuOption.OnClickListener {
        private final int a;
        private final PhotoTakingListener b;

        ClickRunnable(int n, PhotoTakingListener photoTakingListener) {
            this.a = n;
            this.b = photoTakingListener;
        }

        @Override
        public void a(OptionsMenu optionsMenu) {
            this.b.a(this.a);
        }
    }

    public static interface PhotoTakingListener {
        public void a(int var1);
    }

}

