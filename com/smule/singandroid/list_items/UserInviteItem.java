package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserInviteItem extends LinearLayout implements OnClickListener, OnCheckedChangeListener {
    @ViewById
    protected TextView f23305a;
    @ViewById
    protected TextView f23306b;
    @ViewById
    protected ProfileImageWithVIPBadge f23307c;
    @ViewById
    protected CheckBox f23308d;
    @ViewById
    protected TextView f23309e;
    private AccountIcon f23310f;
    private UserInviteItemListener f23311g;

    public interface UserInviteItemListener {
        void m24515a(AccountIcon accountIcon);

        void m24516a(AccountIcon accountIcon, UserInviteItem userInviteItem);
    }

    public UserInviteItem(Context context) {
        super(context);
    }

    public UserInviteItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void m24517a() {
        if (this.f23311g != null && this.f23308d.getVisibility() == 0) {
            if (this.f23308d.isChecked()) {
                this.f23311g.m24516a(this.f23310f, this);
            } else {
                this.f23311g.m24515a(this.f23310f);
            }
        }
    }

    public void setHeader(String str) {
        this.f23305a.setText(str);
    }

    public AccountIcon getAccountIcon() {
        return this.f23310f;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        m24517a();
    }

    public void onClick(View view) {
        if (this.f23308d.getVisibility() == 0) {
            this.f23308d.setChecked(!this.f23308d.isChecked());
            m24517a();
        }
    }

    public void onFinishInflate() {
        if (this.f23308d != null) {
            this.f23308d.setOnCheckedChangeListener(this);
        }
        if (this.f23306b != null) {
            this.f23306b.setOnClickListener(this);
        }
        super.onFinishInflate();
    }

    public void setCheckWithoutTriggeringListener(boolean z) {
        this.f23308d.setOnCheckedChangeListener(null);
        this.f23308d.setChecked(z);
        this.f23308d.setOnCheckedChangeListener(this);
    }
}
