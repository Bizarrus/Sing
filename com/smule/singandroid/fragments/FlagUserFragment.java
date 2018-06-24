package com.smule.singandroid.fragments;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
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
import android.view.View.OnClickListener;
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
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MediaPlayingActivity;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalytics.FlagUserType;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.ShareUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;

@EFragment
public class FlagUserFragment extends BaseFragment {
    public static String f22348e = FlagUserFragment.class.getName();
    @ViewById
    protected LinearLayout f22349f;
    @ViewById
    protected LinearLayout f22350g;
    @ViewById
    protected EditText f22351h;
    @InstanceState
    AccountIcon f22352i;
    @InstanceState
    FlagUserType f22353j = FlagUserType.HARASSMENT;
    @InstanceState
    protected Intent f22354k;

    class C45241 implements TextWatcher {
        final /* synthetic */ FlagUserFragment f22345a;

        C45241(FlagUserFragment flagUserFragment) {
            this.f22345a = flagUserFragment;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @SuppressLint({"SetTextI18n"})
        public void afterTextChanged(Editable editable) {
            this.f22345a.m23733C();
        }
    }

    class C45252 implements OnClickListener {
        final /* synthetic */ FlagUserFragment f22346a;

        C45252(FlagUserFragment flagUserFragment) {
            this.f22346a = flagUserFragment;
        }

        public void onClick(View view) {
            this.f22346a.m23734D();
        }
    }

    class C45263 implements OnClickListener {
        final /* synthetic */ FlagUserFragment f22347a;

        C45263(FlagUserFragment flagUserFragment) {
            this.f22347a = flagUserFragment;
        }

        public void onClick(View view) {
            this.f22347a.m19846b((int) C1947R.string.chat_flag_error_too_few_characters);
        }
    }

    public String mo6383s() {
        return f22348e;
    }

    public static FlagUserFragment m23737a(AccountIcon accountIcon) {
        FlagUserFragment flagUserFragment_ = new FlagUserFragment_();
        flagUserFragment_.f22352i = accountIcon;
        return flagUserFragment_;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19842a(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(C1947R.menu.group_name_menu, menu);
        MenuItem findItem = menu.findItem(C1947R.id.action_done);
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(C1947R.layout.action_bar_icon_button, null, false);
        ((ImageView) inflate.findViewById(C1947R.id.button_next)).setImageResource(C1947R.drawable.icn_checkmark_white);
        findItem.setActionView(inflate);
        m19850c((int) C1947R.string.chat_flag_profile);
        findItem.setVisible(false);
        this.f22350g.setVisibility(8);
        this.f22349f.setVisibility(0);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1947R.id.action_done:
                m23734D();
                break;
        }
        return false;
    }

    @AfterViews
    protected void m23742a() {
        getActivity().getWindow().setSoftInputMode(16);
        m23733C();
        WeakListener.m19083a(this.f22351h, new C45241(this));
    }

    private void m23733C() {
        if (m19845b() != null && m19845b().findItem(C1947R.id.action_done) != null) {
            if (this.f22351h.getText().toString().length() >= 10) {
                m19845b().findItem(C1947R.id.action_done).setEnabled(true);
                m19845b().findItem(C1947R.id.action_done).getActionView().setOnClickListener(new C45252(this));
                return;
            }
            m19845b().findItem(C1947R.id.action_done).setEnabled(false);
            m19845b().findItem(C1947R.id.action_done).getActionView().setOnClickListener(new C45263(this));
        }
    }

    public boolean mo6400c() {
        if (this.f22349f.getVisibility() == 0) {
            return super.mo6400c();
        }
        m23736F();
        return true;
    }

    private void m23734D() {
        long blockSize;
        String string = getResources().getString(this.f22353j.m22371b());
        ChatAnalytics.m22378a(this.f22352i.accountId, this.f22353j);
        String string2 = getResources().getString(C1947R.string.chat_flag_title, new Object[]{string});
        String string3 = getResources().getString(C1947R.string.chat_flag_body, new Object[]{this.f22352i.handle, Long.valueOf(this.f22352i.accountId), string, UserManager.a().i(), Long.valueOf(UserManager.a().f())});
        String str = "4.4.9";
        String str2 = Build.MANUFACTURER;
        String str3 = Build.MANUFACTURER + Build.MODEL;
        String str4 = "android " + VERSION.RELEASE;
        String c = MiscUtils.m25900c();
        String a = MagicDevice.m19003a(getActivity());
        String a2 = MagicDevice.m19004a(getActivity(), true);
        String b = MiscUtils.m25898b(getActivity());
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService("activity");
        String glEsVersion = activityManager.getDeviceConfigurationInfo().getGlEsVersion();
        String a3 = MiscUtils.m25888a(getActivity());
        long j = this.f22352i.accountId;
        String str5 = AccessToken.getCurrentAccessToken() != null ? "YES" : "NO";
        String c2 = MagicNetwork.c();
        MemoryInfo memoryInfo = new MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        long j2 = memoryInfo.availMem;
        StatFs statFs = new StatFs(Environment.getRootDirectory().getAbsolutePath());
        if (VERSION.SDK_INT < 18) {
            blockSize = (long) (statFs.getBlockSize() * statFs.getAvailableBlocks());
        } else {
            blockSize = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        string = MagicNotifications.a().c();
        string = getResources().getString(C1947R.string.chat_flag_body_debug, new Object[]{str, str2, str3, str4, c, a, a2, b, glEsVersion, a3, Long.valueOf(j), str5, c2, Long.valueOf(j2), Long.valueOf(blockSize), string});
        if (this.f22354k == null) {
            this.f22354k = ShareUtils.m25998a(string2, "");
        } else {
            this.f22354k.putExtra("android.intent.extra.SUBJECT", string2);
        }
        if (this.f22354k != null) {
            this.f22354k.putExtra("android.intent.extra.TEXT", this.f22351h.getText().toString() + "\n\n" + Html.fromHtml(string3).toString() + Html.fromHtml(string).toString());
            this.f22354k.putExtra("android.intent.extra.EMAIL", new String[]{getString(C1947R.string.support_email)});
            getActivity().startActivity(this.f22354k);
        } else {
            Log.d(f22348e, "email intent not found");
        }
        if (getActivity() instanceof MediaPlayingActivity) {
            ((MediaPlayingActivity) getActivity()).c(false);
        }
    }

    private void m23735E() {
        m19850c(this.f22353j.m22371b());
        m19845b().findItem(C1947R.id.action_done).setVisible(true);
        this.f22350g.setVisibility(0);
        this.f22351h.setText("");
        this.f22349f.setVisibility(8);
        this.f22351h.requestFocus();
        MiscUtils.m25890a(getActivity(), this.f22351h);
    }

    private void m23736F() {
        m19850c((int) C1947R.string.chat_flag_profile);
        m19845b().findItem(C1947R.id.action_done).setVisible(false);
        this.f22350g.setVisibility(8);
        this.f22349f.setVisibility(0);
        MiscUtils.m25894a(this.f22351h, false);
    }

    @Click
    protected void m23745z() {
        this.f22353j = FlagUserType.CHAT;
        m23735E();
    }

    @Click
    protected void m23740A() {
        this.f22353j = FlagUserType.SEXUAL_CONTENT;
        m23735E();
    }

    @Click
    protected void m23741B() {
        this.f22353j = FlagUserType.HARASSMENT;
        m23735E();
    }
}
