package com.smule.singandroid.songbook_search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.MediaListView;
import java.util.ArrayList;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SearchFragment_ extends SearchFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f24122T = new OnViewChangedNotifier();
    private View f24123U;

    class C48861 implements OnClickListener {
        final /* synthetic */ SearchFragment_ f24119a;

        C48861(SearchFragment_ searchFragment_) {
            this.f24119a = searchFragment_;
        }

        public void onClick(View view) {
            this.f24119a.m25302F();
        }
    }

    class C48872 implements OnClickListener {
        final /* synthetic */ SearchFragment_ f24120a;

        C48872(SearchFragment_ searchFragment_) {
            this.f24120a = searchFragment_;
        }

        public void onClick(View view) {
            this.f24120a.m25303G();
        }
    }

    class C48883 implements OnClickListener {
        final /* synthetic */ SearchFragment_ f24121a;

        C48883(SearchFragment_ searchFragment_) {
            this.f24121a = searchFragment_;
        }

        public void onClick(View view) {
            this.f24121a.m25304H();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, SearchFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f24122T);
        m25327a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f24123U == null) {
            return null;
        }
        return this.f24123U.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f24123U = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f24123U == null) {
            this.f24123U = layoutInflater.inflate(C1947R.layout.songbook_search_layout, viewGroup, false);
        }
        return this.f24123U;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f24123U = null;
        this.f = null;
        this.g = null;
        this.w = null;
        this.G = null;
        this.H = null;
        this.I = null;
    }

    private void m25327a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m25329b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f24122T.a(this);
    }

    public void m25331a(HasViews hasViews) {
        this.f = (RelativeLayout) hasViews.findViewById(C1947R.id.root);
        this.g = (EditText) hasViews.findViewById(C1947R.id.search_edit_text);
        this.w = (MediaListView) hasViews.findViewById(C1947R.id.search_mix_result_listview);
        this.G = hasViews.findViewById(C1947R.id.bookmark_banner);
        this.H = (TextView) hasViews.findViewById(C1947R.id.bookmark_banner_title);
        this.I = hasViews.findViewById(C1947R.id.app_bar_clear_button);
        View findViewById = hasViews.findViewById(C1947R.id.app_bar_back_button);
        View findViewById2 = hasViews.findViewById(C1947R.id.bookmark_banner_ok_button);
        if (this.I != null) {
            this.I.setOnClickListener(new C48861(this));
        }
        if (findViewById != null) {
            findViewById.setOnClickListener(new C48872(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C48883(this));
        }
        m25299B();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("mQueryText", this.h);
        bundle.putString("mSearchInputText", this.i);
        bundle.putSerializable("mResultList", this.z);
        bundle.putStringArrayList("mAutocompleteList", this.A);
        bundle.putBoolean("mIsInstantSearch", this.B);
        bundle.putBoolean("mIsFetchError", this.C);
        bundle.putBoolean("mIsRecent", this.D);
        bundle.putInt("mMixedCount", this.E);
        bundle.putInt("mPerfCount", this.F);
        bundle.putString("mPreviousSearchBarExitClearText", this.L);
        bundle.putString("mPreviousSearchBarExitText", this.M);
        bundle.putInt("mAutoCompleteSearchDelay", this.N);
        bundle.putInt("mInstantSearchDelay", this.O);
        bundle.putInt("mRecordingsToFetch", this.P);
    }

    private void m25329b(Bundle bundle) {
        if (bundle != null) {
            this.h = bundle.getString("mQueryText");
            this.i = bundle.getString("mSearchInputText");
            this.z = (ArrayList) bundle.getSerializable("mResultList");
            this.A = bundle.getStringArrayList("mAutocompleteList");
            this.B = bundle.getBoolean("mIsInstantSearch");
            this.C = bundle.getBoolean("mIsFetchError");
            this.D = bundle.getBoolean("mIsRecent");
            this.E = bundle.getInt("mMixedCount");
            this.F = bundle.getInt("mPerfCount");
            this.L = bundle.getString("mPreviousSearchBarExitClearText");
            this.M = bundle.getString("mPreviousSearchBarExitText");
            this.N = bundle.getInt("mAutoCompleteSearchDelay");
            this.O = bundle.getInt("mInstantSearchDelay");
            this.P = bundle.getInt("mRecordingsToFetch");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
