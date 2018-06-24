/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.ActivityManager$MemoryInfo
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ConfigurationInfo
 *  android.content.res.Resources
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Environment
 *  android.os.StatFs
 *  android.text.Editable
 *  android.text.Html
 *  android.text.TextWatcher
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.Window
 *  android.widget.EditText
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  com.facebook.AccessToken
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.ShareUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.InstanceState
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ConfigurationInfo;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.notifications.MagicNotifications;
import com.smule.android.utils.MagicDevice;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.fragments.FlagUserFragment_;
import com.smule.singandroid.mediaplaying.MediaPlayingActivity;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.ShareUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FlagUserFragment
extends BaseFragment {
    public static String g = FlagUserFragment.class.getName();
    @ViewById
    protected LinearLayout h;
    @ViewById
    protected LinearLayout i;
    @ViewById
    protected EditText j;
    @InstanceState
    AccountIcon k;
    @InstanceState
    ChatAnalytics.FlagUserType l = ChatAnalytics.FlagUserType.c;
    @InstanceState
    protected Intent m;

    private void H() {
        block3 : {
            block2 : {
                if (this.c() == null || this.c().findItem(2131756844) == null) break block2;
                if (this.j.getText().toString().length() < 10) break block3;
                this.c().findItem(2131756844).setEnabled(true);
                this.c().findItem(2131756844).getActionView().setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        FlagUserFragment.this.I();
                    }
                });
            }
            return;
        }
        this.c().findItem(2131756844).setEnabled(false);
        this.c().findItem(2131756844).getActionView().setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                FlagUserFragment.this.b(2131296512);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void I() {
        long l;
        String string2 = this.getResources().getString(this.l.b());
        ChatAnalytics.a(this.k.accountId, this.l);
        String string3 = this.getResources().getString(2131296521, new Object[]{string2});
        String string4 = this.getResources().getString(2131297682, new Object[]{this.k.handle, this.k.accountId, string2, UserManager.a().i(), UserManager.a().f()});
        String string5 = Build.MANUFACTURER;
        String string6 = Build.MANUFACTURER + Build.MODEL;
        String string7 = "android " + Build.VERSION.RELEASE;
        String string8 = MiscUtils.c();
        String string9 = MagicDevice.a((Context)this.getActivity());
        String string10 = MagicDevice.a((Context)this.getActivity(), true);
        String string11 = MiscUtils.b((Context)this.getActivity());
        Object object = (ActivityManager)this.getActivity().getSystemService("activity");
        String string12 = object.getDeviceConfigurationInfo().getGlEsVersion();
        String string13 = MiscUtils.a((Context)this.getActivity());
        long l2 = this.k.accountId;
        string2 = AccessToken.getCurrentAccessToken() != null ? "YES" : "NO";
        String string14 = MagicNetwork.c();
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        object.getMemoryInfo(memoryInfo);
        long l3 = memoryInfo.availMem;
        object = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        if (Build.VERSION.SDK_INT < 18) {
            int n = object.getAvailableBlocks();
            l = object.getBlockSize() * n;
        } else {
            l = object.getAvailableBlocksLong() * object.getBlockSizeLong();
        }
        object = MagicNotifications.a().c();
        string2 = this.getResources().getString(2131297683, new Object[]{"5.7.5", string5, string6, string7, string8, string9, string10, string11, string12, string13, l2, string2, string14, l3, l, object});
        if (this.m == null) {
            this.m = ShareUtils.a((String)string3, (String)"");
        } else {
            this.m.putExtra("android.intent.extra.SUBJECT", string3);
        }
        if (this.m != null) {
            this.m.putExtra("android.intent.extra.TEXT", this.j.getText().toString() + "\n\n" + Html.fromHtml((String)string4).toString() + Html.fromHtml((String)string2).toString());
            this.m.putExtra("android.intent.extra.EMAIL", new String[]{this.getString(2131297961)});
            this.getActivity().startActivity(this.m);
        } else {
            Log.d(g, "email intent not found");
        }
        if (this.getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity)this.getActivity()).c(false);
        }
    }

    private void J() {
        this.c(this.l.b());
        this.c().findItem(2131756844).setVisible(true);
        this.i.setVisibility(0);
        this.j.setText((CharSequence)"");
        this.h.setVisibility(8);
        this.j.requestFocus();
        MiscUtils.a((Activity)this.getActivity(), (EditText)this.j);
    }

    private void K() {
        this.c(2131296519);
        this.c().findItem(2131756844).setVisible(false);
        this.i.setVisibility(8);
        this.h.setVisibility(0);
        MiscUtils.a((View)this.j, (boolean)false);
    }

    public static FlagUserFragment a(AccountIcon accountIcon) {
        FlagUserFragment_ flagUserFragment_ = new FlagUserFragment_();
        flagUserFragment_.k = accountIcon;
        return flagUserFragment_;
    }

    @Click
    protected void F() {
        this.l = ChatAnalytics.FlagUserType.b;
        this.J();
    }

    @Click
    protected void G() {
        this.l = ChatAnalytics.FlagUserType.c;
        this.J();
    }

    @AfterViews
    protected void a() {
        this.getActivity().getWindow().setSoftInputMode(16);
        this.H();
        WeakListener.a(this.j, new TextWatcher(){

            @SuppressLint(value={"SetTextI18n"})
            public void afterTextChanged(Editable editable) {
                FlagUserFragment.this.H();
            }

            public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }

            public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            }
        });
    }

    @Override
    public boolean d() {
        if (this.h.getVisibility() != 0) {
            this.K();
            return true;
        }
        return super.d();
    }

    @Override
    public boolean f() {
        return false;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b_(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu2, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu2, menuInflater);
        menuInflater.inflate(2131820550, menu2);
        menu2 = menu2.findItem(2131756844);
        menuInflater = ((LayoutInflater)this.getActivity().getSystemService("layout_inflater")).inflate(2130903071, null, false);
        ((ImageView)menuInflater.findViewById(2131755235)).setImageResource(2130837888);
        menu2.setActionView((View)menuInflater);
        this.c(2131296519);
        menu2.setVisible(false);
        this.i.setVisibility(8);
        this.h.setVisibility(0);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                do {
                    return false;
                    break;
                } while (true);
            }
            case 2131756844: 
        }
        this.I();
        return false;
    }

    @Click
    protected void t() {
        this.l = ChatAnalytics.FlagUserType.a;
        this.J();
    }

    @Override
    public String x() {
        return g;
    }

}

