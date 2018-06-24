/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserInviteItem
extends LinearLayout
implements View.OnClickListener,
CompoundButton.OnCheckedChangeListener {
    @ViewById
    protected TextView a;
    @ViewById
    protected TextView b;
    @ViewById
    protected ProfileImageWithVIPBadge c;
    @ViewById
    protected CheckBox d;
    @ViewById
    protected TextView e;
    private AccountIcon f;
    private UserInviteItemListener g;

    public UserInviteItem(Context context) {
        super(context);
    }

    public UserInviteItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        if (this.g == null || this.d.getVisibility() != 0) {
            return;
        }
        if (this.d.isChecked()) {
            this.g.a(this.f, this);
            return;
        }
        this.g.a(this.f);
    }

    public AccountIcon getAccountIcon() {
        return this.f;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClick(View view) {
        if (this.d.getVisibility() != 0) {
            return;
        }
        view = this.d;
        boolean bl = !this.d.isChecked();
        view.setChecked(bl);
        this.a();
    }

    public void onFinishInflate() {
        if (this.d != null) {
            this.d.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)this);
        }
        if (this.b != null) {
            this.b.setOnClickListener((View.OnClickListener)this);
        }
        super.onFinishInflate();
    }

    public void setCheckWithoutTriggeringListener(boolean bl) {
        this.d.setOnCheckedChangeListener(null);
        this.d.setChecked(bl);
        this.d.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener)this);
    }

    public void setHeader(String string2) {
        this.a.setText((CharSequence)string2);
    }

    public static interface UserInviteItemListener {
        public void a(AccountIcon var1);

        public void a(AccountIcon var1, UserInviteItem var2);
    }

}

