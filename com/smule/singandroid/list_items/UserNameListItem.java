package com.smule.singandroid.list_items;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class UserNameListItem extends LinearLayout {
    @ViewById
    protected TextView f23314a;
    protected String f23315b;

    public UserNameListItem(Context context) {
        super(context);
    }

    public UserNameListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setUserName(String str) {
        this.f23314a.setText(str);
        this.f23315b = str;
    }

    public String getUserName() {
        return this.f23315b;
    }
}
