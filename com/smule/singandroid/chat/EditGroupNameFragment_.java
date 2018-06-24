package com.smule.singandroid.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester.ResultCallback;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.activator.ChatActivator;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.builder.FragmentBuilder;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class EditGroupNameFragment_ extends EditGroupNameFragment implements HasViews, OnViewChangedListener {
    private final OnViewChangedNotifier f21054k = new OnViewChangedNotifier();
    private View f21055l;

    public static class FragmentBuilder_ extends FragmentBuilder<FragmentBuilder_, EditGroupNameFragment> {
        public EditGroupNameFragment m22723a() {
            EditGroupNameFragment editGroupNameFragment_ = new EditGroupNameFragment_();
            editGroupNameFragment_.setArguments(this.a);
            return editGroupNameFragment_;
        }
    }

    public void onCreate(Bundle bundle) {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21054k);
        m22725a(bundle);
        super.onCreate(bundle);
        OnViewChangedNotifier.a(a);
    }

    public View findViewById(int i) {
        if (this.f21055l == null) {
            return null;
        }
        return this.f21055l.findViewById(i);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f21055l = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.f21055l == null) {
            this.f21055l = layoutInflater.inflate(C1947R.layout.chat_edit_group_chat_name_layout, viewGroup, false);
        }
        return this.f21055l;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.f21055l = null;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    private void m22725a(Bundle bundle) {
        OnViewChangedNotifier.a(this);
        m22727b(bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.f21054k.a(this);
    }

    public static FragmentBuilder_ m22724D() {
        return new FragmentBuilder_();
    }

    public void m22729a(HasViews hasViews) {
        this.f = (EditText) hasViews.findViewById(C1947R.id.edit_text);
        this.g = (TextView) hasViews.findViewById(C1947R.id.char_limit_text);
        this.h = hasViews.findViewById(C1947R.id.cancel_button);
        m22722z();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("mChatActivator", this.J);
    }

    private void m22727b(Bundle bundle) {
        if (bundle != null) {
            this.J = (ChatActivator) bundle.getParcelable("mChatActivator");
        }
    }

    public void mo6406a(@NonNull RunTimePermissionsRequest runTimePermissionsRequest, @Nullable ResultCallback resultCallback) {
        BackgroundExecutor.a();
        super.mo6406a(runTimePermissionsRequest, resultCallback);
    }
}
