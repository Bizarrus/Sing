/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.FragmentTransaction
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.support.annotation.NonNull
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.SingAnalytics
 *  com.smule.singandroid.utils.SingAnalytics$ContactsCtaType
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.UiThread
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.datasources.ContactsDataSource;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.api.ContactsAPI;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.managers.ContactsManager;
import com.smule.android.runtimepermissions.RunTimePermissionsRequest;
import com.smule.android.runtimepermissions.RunTimePermissionsRequester;
import com.smule.android.utils.ReferenceMonitor;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.FindFriendsContactsPageView_;
import com.smule.singandroid.FindFriendsExternalPageView;
import com.smule.singandroid.InviteFriendsFragment;
import com.smule.singandroid.customviews.IconFontView;
import com.smule.singandroid.runtimepermissions.SingPermissionExplanationFullscreenDialogCreator;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.Set;
import java.util.concurrent.Future;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class FindFriendsContactsPageView
extends FindFriendsExternalPageView {
    private static final String n = FindFriendsContactsPageView.class.getName();
    @ViewById
    protected LinearLayout a;
    @ViewById
    protected LinearLayout b;
    @ViewById
    protected LinearLayout c;
    private boolean o = false;

    public FindFriendsContactsPageView(Context context) {
        super(context);
    }

    public FindFriendsContactsPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public FindFriendsContactsPageView(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
    }

    public static FindFriendsContactsPageView a(Context context, BaseFragment baseFragment, FindFriendsExternalPageView.Mode mode) {
        FindFriendsContactsPageView findFriendsContactsPageView = FindFriendsContactsPageView_.a(context);
        findFriendsContactsPageView.a(context, baseFragment, mode, 2131361803, 2131296811);
        ReferenceMonitor.a().a(findFriendsContactsPageView);
        return findFriendsContactsPageView;
    }

    private boolean g() {
        Log.b(n, "connecting to Contacts...");
        if (ContactsManager.a().a(this.l)) {
            this.b();
            return true;
        }
        this.a(FindFriendsExternalPageView.ViewState.c);
        this.i();
        this.d();
        return false;
    }

    private void i() {
        final Runnable runnable = new Runnable(){

            @Override
            public void run() {
                ContactsManager.a().a(ContactsAPI.CONTACT_LIST, ContactsAPI.AGREE, new ContactsManager.UpdateConsentCallback(){

                    @Override
                    public void handleResponse(NetworkResponse networkResponse) {
                        FindFriendsContactsPageView.this.b();
                    }
                });
            }

        };
        final Runnable runnable2 = new Runnable(){

            @Override
            public void run() {
                ContactsManager.a().a(ContactsAPI.CONTACT_LIST, ContactsAPI.DISAGREE, null);
                FindFriendsContactsPageView.this.a(FindFriendsExternalPageView.ViewState.d);
            }
        };
        final Runnable runnable3 = new Runnable(){

            @Override
            public void run() {
                com.smule.android.logging.Analytics.a("contacts_permission_clk", null, Analytics.b, Analytics.b, null);
                ContactsManager.a().a(ContactsAPI.CONTACT_LIST, ContactsAPI.DISAGREE, null);
                ((Activity)FindFriendsContactsPageView.this.l).findViewById(2131756820).callOnClick();
            }
        };
        if (this.d == FindFriendsExternalPageView.Mode.a) {
            this.m.a(SingPermissionRequests.g, new RunTimePermissionsRequester.ResultCallback(){

                @Override
                public void a(boolean bl, @NonNull Set<String> set) {
                    if (!FindFriendsContactsPageView.this.h()) {
                        return;
                    }
                    if (bl) {
                        runnable.run();
                        return;
                    }
                    runnable2.run();
                }
            });
            return;
        }
        this.m.a(SingPermissionRequests.f, new RunTimePermissionsRequester.ResultCallback(){

            @Override
            public void a(boolean bl, @NonNull Set<String> set) {
                if (!FindFriendsContactsPageView.this.h()) {
                    return;
                }
                if (bl) {
                    if (Build.VERSION.SDK_INT < 23) {
                        SingPermissionRequests.e.a(FindFriendsContactsPageView.this.l, new RunTimePermissionsRequest.ExplanationDialogListener(){

                            @Override
                            public void a(Dialog dialog) {
                                runnable3.run();
                            }

                            @Override
                            public void b(Dialog dialog) {
                                com.smule.android.logging.Analytics.a("contacts_permission_clk", null, Analytics.b, Analytics.a, null);
                                runnable.run();
                            }
                        }).show();
                        return;
                    }
                    runnable.run();
                    return;
                }
                runnable3.run();
            }

        });
    }

    private void j() {
        FragmentTransaction fragmentTransaction = this.m.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(2131755458, (Fragment)InviteFriendsFragment.a(), InviteFriendsFragment.t());
        fragmentTransaction.addToBackStack(InviteFriendsFragment.t());
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void a() {
        Log.b(n, "setting up page");
        this.a.findViewById(2131755641).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.a((SingAnalytics.ContactsCtaType)SingAnalytics.ContactsCtaType.a);
                FindFriendsContactsPageView.this.g();
            }
        });
        this.b.findViewById(2131755795).setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                SingAnalytics.a((SingAnalytics.ContactsCtaType)SingAnalytics.ContactsCtaType.c);
                view = new Intent();
                view.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                view.setData(Uri.parse((String)("package:" + FindFriendsContactsPageView.this.l.getPackageName())));
                FindFriendsContactsPageView.this.l.startActivity((Intent)view);
            }
        });
        if (this.o) {
            Log.b(n, "   FB login error flag, displaying login error screen to user");
            this.a(FindFriendsExternalPageView.ViewState.d);
            return;
        }
        if (ContactsManager.a().a(this.l)) {
            Log.b(n, "   READ_CONTACTS already granted");
            this.b();
            return;
        }
        Log.b(n, "   READ_CONTACTS  needed, prompting user");
        this.a(FindFriendsExternalPageView.ViewState.b);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void a(FindFriendsExternalPageView.ViewState viewState) {
        this.f.setVisibility(8);
        this.a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        switch (.a[viewState.ordinal()]) {
            case 1: {
                this.f.setVisibility(0);
                return;
            }
            case 2: {
                this.a.setVisibility(0);
                if (this.d == FindFriendsExternalPageView.Mode.b) {
                    this.g();
                    return;
                }
            }
            default: {
                return;
            }
            case 3: {
                this.b.setVisibility(0);
                return;
            }
            case 4: 
        }
        this.c.setVisibility(0);
    }

    @UiThread
    protected void b() {
        if (!this.h()) {
            return;
        }
        Log.b(n, "contactsConnected()");
        this.h = new ContactsDataSource();
        ((ContactsDataSource)this.h).c();
        this.g = new ContactsFriendsAdapter(this.h);
        this.f.setMagicAdapter(this.g);
        this.d();
        this.a(FindFriendsExternalPageView.ViewState.a);
    }

    @Override
    public void c() {
        SingAnalytics.S();
    }

    @Override
    public String getExternalName() {
        return null;
    }

    @Override
    public String getSocialContext() {
        if (this.d == FindFriendsExternalPageView.Mode.a) {
            return "CO";
        }
        return "CO_ONBRD";
    }

    private class ContactsFriendsAdapter
    extends FindFriendsExternalPageView.ExternalFriendsAdapter {
        public ContactsFriendsAdapter(MagicDataSource magicDataSource) {
            super(FindFriendsContactsPageView.this, magicDataSource, 2130903232, 2130903234);
        }

        @Override
        public View c(ViewGroup viewGroup) {
            viewGroup = super.c(viewGroup);
            viewGroup.findViewById(2131755795).setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    FindFriendsContactsPageView.this.g();
                }
            });
            return viewGroup;
        }

        @Override
        public View d(ViewGroup viewGroup) {
            viewGroup = super.d(viewGroup);
            TextView textView = (TextView)viewGroup.findViewById(2131755795);
            if (FindFriendsContactsPageView.this.d == FindFriendsExternalPageView.Mode.b) {
                textView.setText(2131296700);
            }
            if (((ContactsDataSource)this.a()).l_()) {
                ((IconFontView)viewGroup.findViewById(2131755175)).setText(2131297884);
                ((TextView)viewGroup.findViewById(2131755218)).setText(2131296807);
                ((TextView)viewGroup.findViewById(2131755797)).setText(2131296806);
            }
            textView.setOnClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                public void onClick(View view) {
                    if (((ContactsDataSource)ContactsFriendsAdapter.this.a()).l_()) {
                        SingAnalytics.a((SingAnalytics.ContactsCtaType)SingAnalytics.ContactsCtaType.d);
                    } else {
                        SingAnalytics.a((SingAnalytics.ContactsCtaType)SingAnalytics.ContactsCtaType.b);
                    }
                    switch (com.smule.singandroid.FindFriendsContactsPageView$8.b[FindFriendsContactsPageView.this.d.ordinal()]) {
                        default: {
                            return;
                        }
                        case 1: {
                            FindFriendsContactsPageView.this.j();
                            return;
                        }
                        case 2: 
                    }
                    ((Activity)FindFriendsContactsPageView.this.l).findViewById(2131756820).callOnClick();
                }
            });
            return viewGroup;
        }

    }

}

