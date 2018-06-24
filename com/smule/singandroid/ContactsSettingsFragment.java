/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.support.annotation.NonNull
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnTouchListener
 *  android.widget.ToggleButton
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ToggleButton;
import com.smule.android.network.api.ContactsAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.ContactsManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.ContactsSettingsFragment_;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import java.util.Set;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class ContactsSettingsFragment
extends BaseFragment {
    private static final String h = ContactsSettingsFragment.class.getName();
    @ViewById
    protected ToggleButton g;

    public static ContactsSettingsFragment a() {
        return ContactsSettingsFragment_.F().a();
    }

    private void a(ContactsAPI consentState) {
        ContactsManager.a().a(ContactsAPI.CONTACT_LIST, consentState, new ContactsManager.UpdateConsentCallback(){

            @Override
            public void handleResponse(NetworkResponse networkResponse) {
                if (ContactsSettingsFragment.this.isAdded() && networkResponse.c()) {
                    ContactsSettingsFragment.this.g.toggle();
                }
            }
        });
    }

    @AfterViews
    protected void t() {
        this.c(2131296843);
        this.g.setChecked(ContactsManager.a().a((Context)this.getActivity()));
        this.g.setOnTouchListener(new View.OnTouchListener(){

            public boolean onTouch(View object, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    if (ContactsSettingsFragment.this.g.isChecked()) {
                        object = new TextAlertDialog((Context)ContactsSettingsFragment.this.getActivity(), 2131296814, 2131296813);
                        object.a(2131296812, 2131296672);
                        object.a(new Runnable(){

                            @Override
                            public void run() {
                                ContactsSettingsFragment.this.a(ContactsAPI.DISAGREE);
                            }
                        });
                        object.show();
                        return true;
                    }
                    ContactsSettingsFragment.this.a(SingPermissionRequests.h, new RunTimePermissionsRequester.ResultCallback(){

                        /*
                         * Enabled aggressive block sorting
                         */
                        @Override
                        public void a(boolean bl, @NonNull Set<String> set) {
                            if (!ContactsSettingsFragment.this.isAdded() || !bl) {
                                return;
                            }
                            ContactsSettingsFragment.this.a(ContactsAPI.AGREE);
                        }
                    });
                    return true;
                }
                return false;
            }

        });
    }

    @Override
    public String x() {
        return h;
    }

}

