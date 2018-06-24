package com.smule.singandroid.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatAnalytics.FlagUserType;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class FlagUserFragment_ extends FlagUserFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f22358l = new OnViewChangedNotifier();
    private View f22359m;

    class C45271 implements OnClickListener {
        final /* synthetic */ FlagUserFragment_ f22355a;

        C45271(FlagUserFragment_ flagUserFragment_) {
            this.f22355a = flagUserFragment_;
        }

        public void onClick(View view) {
            this.f22355a.m23745z();
        }
    }

    class C45282 implements OnClickListener {
        final /* synthetic */ FlagUserFragment_ f22356a;

        C45282(FlagUserFragment_ flagUserFragment_) {
            this.f22356a = flagUserFragment_;
        }

        public void onClick(View view) {
            this.f22356a.m23740A();
        }
    }

    class C45293 implements OnClickListener {
        final /* synthetic */ FlagUserFragment_ f22357a;

        C45293(FlagUserFragment_ flagUserFragment_) {
            this.f22357a = flagUserFragment_;
        }

        public void onClick(View view) {
            this.f22357a.m23741B();
        }
    }

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, FlagUserFragment> {
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22358l);
        m23746a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f22359m == null) {
            return null;
        }
        return this.f22359m.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f22359m = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f22359m == null) {
            this.f22359m = layoutInflater.inflate(C1947R.layout.flag_user_fragment, viewGroup, false);
        }
        return this.f22359m;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f22359m = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    private void m23746a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m23748b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f22358l.a(this);
    }

    public void m23750a(HasViews hasViews) {
        this.f = (LinearLayout) hasViews.findViewById(C1947R.id.menu_layout);
        this.g = (LinearLayout) hasViews.findViewById(C1947R.id.details_layout);
        this.h = (EditText) hasViews.findViewById(C1947R.id.details_edittext);
        View findViewById = hasViews.findViewById(C1947R.id.mOffensiveChatMessageButton);
        View findViewById2 = hasViews.findViewById(C1947R.id.mSexualContent);
        View findViewById3 = hasViews.findViewById(C1947R.id.mHarassment);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C45271(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C45282(this));
        }
        if (findViewById3 != null) {
            findViewById3.setOnClickListener(new C45293(this));
        }
        m23742a();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mAccountIcon", this.i);
        bundle.putSerializable("mFlagUserType", this.j);
        bundle.putParcelable("mEmailIntent", this.k);
    }

    private void m23748b(Bundle bundle) {
        if (bundle != null) {
            this.i = (AccountIcon) bundle.getParcelable("mAccountIcon");
            this.j = (FlagUserType) bundle.getSerializable("mFlagUserType");
            this.k = (Intent) bundle.getParcelable("mEmailIntent");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
