/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.common.JoinSectionType;
import com.smule.singandroid.list_items.PerformanceListEmptyListItem_;
import com.smule.singandroid.profile.ProfileUserInfo;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class PerformanceListEmptyListItem
extends LinearLayout {
    @ViewById
    ImageView a;
    @ViewById
    TextView b;
    @ViewById
    Button c;

    public PerformanceListEmptyListItem(Context context) {
        super(context);
    }

    public PerformanceListEmptyListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static PerformanceListEmptyListItem a(Context context) {
        return PerformanceListEmptyListItem_.b(context);
    }

    /*
     * Exception decompiling
     */
    public void a(int var1_1, boolean var2_2, String var3_3, View.OnClickListener var4_4, ProfileUserInfo.ColorSet var5_5) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.CannotPerformDecode: reachable test BLOCK was exited and re-entered.
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.getFarthestReachableInRange(Misc.java:143)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.SwitchReplacer.examineSwitchContiguity(SwitchReplacer.java:385)
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

    public void a(ProfileUserInfo.ColorSet colorSet) {
        Drawable drawable2 = this.getResources().getDrawable(2130838177);
        drawable2.setColorFilter(colorSet.e, PorterDuff.Mode.SRC_ATOP);
        this.c.setBackground(drawable2);
        this.c.setTextColor(-1);
    }

    public void setModeEmptyNotifications(View.OnClickListener onClickListener) {
        this.b.setText(2131296974);
        this.a.setImageResource(2130838008);
        this.c.setVisibility(0);
        this.c.setOnClickListener(onClickListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setModeEmptyOpenCallListFragment(JoinSectionType joinSectionType) {
        switch (joinSectionType) {
            default: {
                this.b.setText(2131297069);
                break;
            }
            case f: {
                this.b.setText(2131297067);
            }
        }
        this.a.setImageResource(2130837967);
        this.c.setVisibility(8);
    }

}

