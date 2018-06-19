package com.smule.android.utils;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.View;
import java.lang.ref.WeakReference;

public abstract class UiComponent {

    private static class ActivityComponent extends UiComponent {
        private final WeakReference<Activity> f17877a;

        protected ActivityComponent(Activity activity) {
            this.f17877a = new WeakReference(activity);
        }

        public boolean mo6305a() {
            Activity activity = (Activity) this.f17877a.get();
            if (activity == null) {
                return false;
            }
            boolean z;
            boolean z2;
            if (activity.getWindow() == null || activity.getWindow().getDecorView() == null || !activity.getWindow().getDecorView().isShown()) {
                z = false;
            } else {
                z = true;
            }
            if (!z || activity.isFinishing()) {
                z2 = false;
            } else {
                z2 = true;
            }
            return z2;
        }
    }

    private static class FragmentComponent extends UiComponent {
        private final WeakReference<Fragment> f17878a;

        protected FragmentComponent(Fragment fragment) {
            this.f17878a = new WeakReference(fragment);
        }

        public boolean mo6305a() {
            Fragment fragment = (Fragment) this.f17878a.get();
            return fragment != null && fragment.isAdded();
        }
    }

    private static class ViewComponent extends UiComponent {
        private final WeakReference<View> f17879a;

        protected ViewComponent(View view) {
            this.f17879a = new WeakReference(view);
        }

        public boolean mo6305a() {
            View view = (View) this.f17879a.get();
            return view != null && ViewCompat.isAttachedToWindow(view);
        }
    }

    public abstract boolean mo6305a();

    public static UiComponent m19069a(Activity activity) {
        return new ActivityComponent(activity);
    }

    public static UiComponent m19070a(Fragment fragment) {
        return new FragmentComponent(fragment);
    }

    public static UiComponent m19071a(View view) {
        return new ViewComponent(view);
    }
}
