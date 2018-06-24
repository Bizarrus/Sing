/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.support.v4.content.ContextCompat
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.AutoResizeTextView
 *  com.smule.singandroid.upsell.UpsellType
 *  com.smule.singandroid.utils.SingAnalytics
 *  org.androidannotations.annotations.EActivity
 *  org.androidannotations.annotations.Extra
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.PostSingBundle;
import com.smule.singandroid.SingBundle;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.customviews.PurchaseButton;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.purchases.V3BillingHelper;
import com.smule.singandroid.textviews.AutoResizeTextView;
import com.smule.singandroid.upsell.UpsellType;
import com.smule.singandroid.utils.SingAnalytics;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@SuppressLint(value={"Registered"})
@EActivity
public class PostSongUpsellActivity
extends BaseActivity {
    private static final String v = PostSongUpsellActivity.class.getSimpleName();
    @ViewById
    AutoResizeTextView g;
    @ViewById
    TextView h;
    @ViewById
    TextView i;
    @ViewById
    TextView j;
    @ViewById
    ProfileImageWithVIPBadge k;
    @ViewById
    protected PurchaseButton l;
    @ViewById
    protected PurchaseButton m;
    @ViewById
    protected Button n;
    @ViewById
    protected ProgressBar o;
    @ViewById
    protected ImageView p;
    @ViewById
    protected ImageView q;
    @ViewById
    protected ImageView r;
    V3BillingHelper s;
    TextAlertDialog t;
    @Extra
    PostSingBundle u;

    /*
     * Exception decompiling
     */
    private void a(UserFlow var1_1) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: First case is not immediately after switch.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:366)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.replaceRawSwitches(SwitchReplacer.java:65)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:423)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:217)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:162)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:95)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:357)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:769)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:701)
        // org.benf.cfr.reader.Main.doJar(Main.java:134)
        // org.benf.cfr.reader.Main.main(Main.java:189)
        throw new IllegalStateException("Decompilation failed");
    }

    private void a(V3BillingHelper v3BillingHelper) {
        this.s = v3BillingHelper;
        this.s.a((Activity)this, null, new V3BillingHelper(){

            @Override
            public void a() {
                Log.b(v, "report start");
            }

            @Override
            public void a(boolean bl) {
                Log.b(v, "report end");
                if (bl) {
                    PostSongUpsellActivity.this.finish();
                }
            }
        });
        this.s.a(UpsellType.h.a());
        this.s.a((View)this.l, (View)this.m, null, (View)this.o, new V3BillingHelper(){

            @Override
            public void a() {
                PostSongUpsellActivity.this.b();
            }
        });
    }

    private void b() {
        this.t = new TextAlertDialog((Context)this, 2131297548, 2131297547, true, false);
        this.t.a("Okay", "");
        this.t.a(new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog customAlertDialog) {
                if (PostSongUpsellActivity.this.t != null) {
                    PostSongUpsellActivity.this.t.dismiss();
                    PostSongUpsellActivity.this.t = null;
                    PostSongUpsellActivity.this.finish();
                }
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
                this.a(customAlertDialog);
            }
        });
        if (!this.j()) {
            this.t.show();
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private Drawable c(String var1_1) {
        block13 : {
            var2_2 = -1;
            switch (var1_1.hashCode()) {
                case 104990543: {
                    if (var1_1.equals("noads")) {
                        var2_2 = 0;
                        ** break;
                    }
                    ** GOTO lbl11
                }
                case 3412756: {
                    if (var1_1.equals("okay")) {
                        var2_2 = 1;
                    }
                }
lbl11: // 6 sources:
                default: {
                    break block13;
                }
                case 104263205: 
            }
            if (var1_1.equals("music")) {
                var2_2 = 2;
            }
        }
        switch (var2_2) {
            default: {
                return null;
            }
            case 0: {
                return ContextCompat.getDrawable((Context)this.getApplicationContext(), (int)2130838046);
            }
            case 1: {
                return ContextCompat.getDrawable((Context)this.getApplicationContext(), (int)2130838048);
            }
            case 2: 
        }
        return ContextCompat.getDrawable((Context)this.getApplicationContext(), (int)2130838044);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected void f() {
        super.f();
        Log.b(v, "onViewsCreated()");
        boolean bl = this.u.b.k;
        boolean bl2 = !this.u.b.c() && !this.u.b.d();
        boolean bl3 = !bl && !bl2;
        boolean bl4 = !this.u.e;
        UserFlow userFlow = bl3 ? (bl4 ? UserFlow.a : UserFlow.b) : (bl2 ? (bl4 ? UserFlow.c : UserFlow.d) : (bl4 ? UserFlow.e : UserFlow.f));
        this.k.setLoadImageWithBorder(true);
        this.k.setProfilePicUrl(UserManager.a().h());
        this.k.setVIP(true);
        this.a(userFlow);
        this.l.setTagVisibility(false);
        this.m.setTagVisibility(false);
        this.a(new V3BillingHelper());
        this.n.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                PostSongUpsellActivity.this.finish();
            }
        });
    }

    @Override
    protected void g() {
        super.g();
        SongbookEntry songbookEntry = this.u.b.d;
        SingAnalytics.b((String)SongbookEntry.b(songbookEntry), (String)SongbookEntry.a(songbookEntry), (String)"post-song", Analytics.a);
        Log.b(v, "callAnalyticsPageView()");
    }

    public void onActivityResult(int n, int n2, Intent intent) {
        boolean bl = false;
        if (this.s != null) {
            bl = this.s.a(n, n2, intent);
        }
        if (!bl) {
            super.onActivityResult(n, n2, intent);
        }
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.s.c();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.s.b();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.s.a();
    }

    private static enum UserFlow {
        a,
        b,
        c,
        d,
        e,
        f;
        

        private UserFlow() {
        }
    }

}

