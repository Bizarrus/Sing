/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.support.v4.view.ViewCompat
 *  android.view.View
 *  android.view.Window
 */
package com.smule.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import java.lang.ref.WeakReference;

public abstract class UiComponent {
    public static UiComponent a(Activity activity) {
        return new ActivityComponent(activity);
    }

    public static UiComponent a(Fragment fragment) {
        return new FragmentComponent(fragment);
    }

    public static UiComponent a(View view) {
        return new ViewComponent(view);
    }

    public abstract boolean a();

    private static class ActivityComponent
    extends UiComponent {
        private final WeakReference<Activity> a;

        protected ActivityComponent(Activity activity) {
            this.a = new WeakReference<Activity>(activity);
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        @Override
        public boolean a() {
            Activity activity = this.a.get();
            if (activity == null) {
                return false;
            }
            if (activity.getWindow() == null) return false;
            if (activity.getWindow().getDecorView() == null) return false;
            if (!activity.getWindow().getDecorView().isShown()) return false;
            boolean bl = true;
            if (!bl) return false;
            if (activity.isFinishing()) return false;
            return true;
        }
    }

    private static class FragmentComponent
    extends UiComponent {
        private final WeakReference<Fragment> a;

        protected FragmentComponent(Fragment fragment) {
            this.a = new WeakReference<Fragment>(fragment);
        }

        @Override
        public boolean a() {
            Fragment fragment = this.a.get();
            if (fragment != null && fragment.isAdded()) {
                return true;
            }
            return false;
        }
    }

    private static class ViewComponent
    extends UiComponent {
        private final WeakReference<View> a;

        protected ViewComponent(View view) {
            this.a = new WeakReference<View>(view);
        }

        @Override
        public boolean a() {
            View view = this.a.get();
            if (view != null && ViewCompat.isAttachedToWindow((View)view)) {
                return true;
            }
            return false;
        }
    }

}

