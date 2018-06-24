/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Fragment
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Log;

public abstract class LifecycleLoggingFragment
extends Fragment {
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Log.b(this.x(), "onActivityCreated called");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.b(this.x(), "onAttach called");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.b(this.x(), "onCreate called");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        Log.b(this.x(), "onCreateView called");
        return layoutInflater;
    }

    public void onDestroy() {
        super.onDestroy();
        Log.b(this.x(), "onDestroy called");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.b(this.x(), "onDestroyView called");
    }

    public void onDetach() {
        super.onDetach();
        Log.b(this.x(), "onDetach called");
    }

    public void onPause() {
        super.onPause();
        Log.b(this.x(), "onPause called");
    }

    public void onResume() {
        super.onResume();
        Log.b(this.x(), "onResume called");
    }

    public void onStart() {
        super.onStart();
        Log.b(this.x(), "onStart called");
    }

    public void onStop() {
        super.onStop();
        Log.b(this.x(), "onStop called");
    }

    protected abstract String x();
}

