package com.smule.singandroid;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smule.android.logging.Log;

public abstract class LifecycleLoggingFragment extends Fragment {
    protected abstract String mo6383s();

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.b(mo6383s(), "onAttach called");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.b(mo6383s(), "onCreate called");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        Log.b(mo6383s(), "onCreateView called");
        return onCreateView;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Log.b(mo6383s(), "onActivityCreated called");
    }

    public void onStart() {
        super.onStart();
        Log.b(mo6383s(), "onStart called");
    }

    public void onResume() {
        super.onResume();
        Log.b(mo6383s(), "onResume called");
    }

    public void onPause() {
        super.onPause();
        Log.b(mo6383s(), "onPause called");
    }

    public void onStop() {
        super.onStop();
        Log.b(mo6383s(), "onStop called");
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.b(mo6383s(), "onDestroyView called");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.b(mo6383s(), "onDestroy called");
    }

    public void onDetach() {
        super.onDetach();
        Log.b(mo6383s(), "onDetach called");
    }
}
