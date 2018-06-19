package com.smule.singandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics.PaywallType;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.managers.AppSettingsManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.purchases.V3BillingHelper$SkuDetailsErrorListener;
import com.smule.singandroid.purchases.V3BillingHelper$V3BillingListener;
import com.smule.singandroid.textviews.AutoResizeTextView;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;
import org.json.JSONObject;

@EActivity
public class PostSongUpsellActivity extends BaseActivity {
    private static final String f19362v = PostSongUpsellActivity.class.getSimpleName();
    @ViewById
    AutoResizeTextView f19363g;
    @ViewById
    TextView f19364h;
    @ViewById
    TextView f19365i;
    @ViewById
    TextView f19366j;
    @ViewById
    ProfileImageWithVIPBadge f19367k;
    @ViewById
    protected PurchaseButton f19368l;
    @ViewById
    protected PurchaseButton f19369m;
    @ViewById
    protected Button f19370n;
    @ViewById
    protected ProgressBar f19371o;
    @ViewById
    protected ImageView f19372p;
    @ViewById
    protected ImageView f19373q;
    @ViewById
    protected ImageView f19374r;
    V3BillingHelper f19375s;
    TextAlertDialog f19376t;
    @Extra
    PostSingBundle f19377u;

    class C39811 implements OnClickListener {
        final /* synthetic */ PostSongUpsellActivity f19350a;

        C39811(PostSongUpsellActivity postSongUpsellActivity) {
            this.f19350a = postSongUpsellActivity;
        }

        public void onClick(View view) {
            this.f19350a.finish();
        }
    }

    class C39822 implements V3BillingHelper$V3BillingListener {
        final /* synthetic */ PostSongUpsellActivity f19351a;

        C39822(PostSongUpsellActivity postSongUpsellActivity) {
            this.f19351a = postSongUpsellActivity;
        }

        public void mo6535a() {
            Log.b(PostSongUpsellActivity.f19362v, "report start");
        }

        public void mo6536a(boolean z) {
            Log.b(PostSongUpsellActivity.f19362v, "report end");
            if (z) {
                this.f19351a.finish();
            }
        }
    }

    class C39833 implements V3BillingHelper$SkuDetailsErrorListener {
        final /* synthetic */ PostSongUpsellActivity f19352a;

        C39833(PostSongUpsellActivity postSongUpsellActivity) {
            this.f19352a = postSongUpsellActivity;
        }

        public void mo6537a() {
            this.f19352a.m20919b();
        }
    }

    class C39844 implements CustomAlertDialogListener {
        final /* synthetic */ PostSongUpsellActivity f19353a;

        C39844(PostSongUpsellActivity postSongUpsellActivity) {
            this.f19353a = postSongUpsellActivity;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            if (this.f19353a.f19376t != null) {
                this.f19353a.f19376t.dismiss();
                this.f19353a.f19376t = null;
                this.f19353a.finish();
            }
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            mo6385a(customAlertDialog);
        }
    }

    private enum UserFlow {
        SEED_UPLOAD,
        SEED_NOUPLOAD,
        SOLO_UPLOAD,
        SOLO_NOUPLOAD,
        JOIN_UPLOAD,
        JOIN_NOUPLOAD
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected void m20921d() {
        boolean z;
        super.d();
        Log.b(f19362v, "onViewsCreated()");
        boolean z2 = this.f19377u.f19316b.f20146k;
        boolean z3 = (this.f19377u.f19316b.m21643a() || this.f19377u.f19316b.m21648b()) ? false : true;
        if (z2 || z3) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (this.f19377u.f19319e) {
            z = false;
        } else {
            z = true;
        }
        UserFlow userFlow = z2 ? z ? UserFlow.SEED_UPLOAD : UserFlow.SEED_NOUPLOAD : z3 ? z ? UserFlow.SOLO_UPLOAD : UserFlow.SOLO_NOUPLOAD : z ? UserFlow.JOIN_UPLOAD : UserFlow.JOIN_NOUPLOAD;
        this.f19367k.setLoadImageWithBorder(true);
        this.f19367k.setProfilePicUrl(UserManager.a().h());
        this.f19367k.setVIP(true);
        m20916a(userFlow);
        this.f19368l.setTagVisibility(false);
        this.f19369m.setTagVisibility(false);
        m20918a(new V3BillingHelper());
        this.f19370n.setOnClickListener(new C39811(this));
    }

    public void onPause() {
        super.onPause();
        this.f19375s.b();
    }

    public void onResume() {
        super.onResume();
        this.f19375s.a();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f19375s.c();
    }

    private void m20916a(UserFlow userFlow) {
        CharSequence string = getString(C1947R.string.core_all_access_pass);
        Object obj = "noads";
        CharSequence string2 = getString(C1947R.string.paywall_noads);
        Object obj2 = "music";
        CharSequence string3 = getString(C1947R.string.paywall_sing);
        Object obj3 = "okay";
        CharSequence string4 = getString(C1947R.string.paywall_cancel_anytime);
        try {
            String a;
            switch (userFlow) {
                case SEED_UPLOAD:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "seedUpload", null);
                    break;
                case SEED_NOUPLOAD:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "seedNoUpload", null);
                    break;
                case JOIN_UPLOAD:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "joinUpload", null);
                    break;
                case JOIN_NOUPLOAD:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "joinNoUpload", null);
                    break;
                case SOLO_UPLOAD:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "soloUpload", null);
                    break;
                default:
                    a = AppSettingsManager.a().a("sing_google.songUpsell", "soloNoUpload", null);
                    break;
            }
            if (a != null) {
                JSONObject jSONObject = new JSONObject(a);
                string = jSONObject.getString("title");
                JSONArray jSONArray = jSONObject.getJSONArray("bullets");
                obj = jSONArray.getJSONObject(0).getString("icon");
                string2 = jSONArray.getJSONObject(0).getString("text");
                obj2 = jSONArray.getJSONObject(1).getString("icon");
                string3 = jSONArray.getJSONObject(1).getString("text");
                obj3 = jSONArray.getJSONObject(2).getString("icon");
                string4 = jSONArray.getJSONObject(2).getString("text");
            }
        } catch (Throwable e) {
            MagicCrittercism.a(e);
        }
        this.f19363g.setText(string);
        this.f19364h.setText(string2);
        this.f19365i.setText(string3);
        this.f19366j.setText(string4);
        if (!TextUtils.isEmpty(obj)) {
            this.f19372p.setImageDrawable(m20920c(obj));
        }
        if (!TextUtils.isEmpty(obj2)) {
            this.f19373q.setImageDrawable(m20920c(obj2));
        }
        if (!TextUtils.isEmpty(obj3)) {
            this.f19374r.setImageDrawable(m20920c(obj3));
        }
        this.f19372p.setColorFilter(-1);
        this.f19373q.setColorFilter(-1);
        this.f19374r.setColorFilter(-1);
    }

    private void m20918a(V3BillingHelper v3BillingHelper) {
        this.f19375s = v3BillingHelper;
        this.f19375s.a(this, null, new C39822(this));
        this.f19375s.a(UpsellType.POST_SONG.m25792a());
        this.f19375s.a(this.f19368l, this.f19369m, null, this.f19371o, new C39833(this));
    }

    private void m20919b() {
        this.f19376t = new TextAlertDialog((Context) this, (int) C1947R.string.subscription_general_error, (int) C1947R.string.subscription_cannot_connect_to_google_play, true, false);
        this.f19376t.m19806a("Okay", "");
        this.f19376t.m19803a(new C39844(this));
        if (!h()) {
            this.f19376t.show();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        boolean z = false;
        if (this.f19375s != null) {
            z = this.f19375s.a(i, i2, intent);
        }
        if (!z) {
            super.onActivityResult(i, i2, intent);
        }
    }

    protected void m20922e() {
        super.e();
        SongbookEntry songbookEntry = this.f19377u.f19316b.f20139d;
        SingAnalytics.m26130b(SongbookEntry.m18752b(songbookEntry), SongbookEntry.m18749a(songbookEntry), "post-song", PaywallType.HARD);
        Log.b(f19362v, "callAnalyticsPageView()");
    }

    private Drawable m20920c(String str) {
        Object obj = -1;
        switch (str.hashCode()) {
            case 3412756:
                if (str.equals("okay")) {
                    obj = 1;
                    break;
                }
                break;
            case 104263205:
                if (str.equals("music")) {
                    obj = 2;
                    break;
                }
                break;
            case 104990543:
                if (str.equals("noads")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return ContextCompat.getDrawable(getApplicationContext(), C1947R.drawable.icn_ts_noads);
            case 1:
                return ContextCompat.getDrawable(getApplicationContext(), C1947R.drawable.icn_ts_okay);
            case 2:
                return ContextCompat.getDrawable(getApplicationContext(), C1947R.drawable.icn_ts_music);
            default:
                return null;
        }
    }
}
