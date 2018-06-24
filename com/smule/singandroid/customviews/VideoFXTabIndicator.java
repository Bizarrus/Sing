package com.smule.singandroid.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup.MarginLayoutParams;
import com.smule.android.network.managers.SubscriptionManager;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.ReviewActivity;
import com.smule.singandroid.video.VideoFilterManager;
import com.smule.singandroid.video.VideoFilterManager.VFXItem;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.annotations.EViewGroup;

@EViewGroup
public class VideoFXTabIndicator extends CustomTabIndicator {
    public static final String f22046m = VideoFXTabIndicator.class.getName();
    private List<VFXItem> f22047n;

    public VideoFXTabIndicator(Context context) {
        this(context, null, 0);
    }

    public VideoFXTabIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoFXTabIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void m23529b(List<VFXItem> list, int i) {
        if (list.isEmpty()) {
            m23164b();
            this.g = true;
            return;
        }
        this.d.setHeight((int) getResources().getDimension(C1947R.dimen.review_fx_vip_icn_size));
        this.f22047n = list;
        List arrayList = new ArrayList();
        for (VFXItem vFXItem : list) {
            arrayList.add(vFXItem.f25458b);
        }
        super.m23162a(arrayList, i);
    }

    public void m23528a(String str) {
        if (this.l instanceof ReviewActivity) {
            m23159a(C1947R.dimen.review_headphones_required_group_video_height);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.d.getLayoutParams();
            marginLayoutParams.topMargin += (int) getResources().getDimension(C1947R.dimen.margin_large);
            this.d.setLayoutParams(marginLayoutParams);
        }
        VFXItem vFXItem = new VFXItem();
        vFXItem.f25457a = str;
        vFXItem.f25458b = VideoFilterManager.m26575a(str);
        this.d.setText(getResources().getString(C1947R.string.video_fx_group_join_title, new Object[]{vFXItem.f25458b}));
        this.f22047n = new ArrayList();
        this.f22047n.add(vFXItem);
        m23167e();
    }

    protected void setTitleText(String str) {
        if (!this.g) {
            if (((VFXItem) this.f22047n.get(this.i)).f25459c) {
                super.m23161a(str, (int) C1947R.drawable.icn_vip);
                if (!(this.l instanceof ReviewActivity) || SubscriptionManager.a().b()) {
                    this.e.setText("");
                    return;
                } else {
                    this.e.setText(getResources().getString(C1947R.string.video_fx_vip_only_filter));
                    return;
                }
            }
            super.setTitleText(str);
            this.e.setText("");
        }
    }

    public String getCurrentItemId() {
        if (this.g || this.f22047n == null || this.f22047n.get(this.i) == null) {
            return null;
        }
        return ((VFXItem) this.f22047n.get(this.i)).f25457a;
    }

    protected String getCurrentItemTitle() {
        if (this.g) {
            return null;
        }
        return ((VFXItem) this.f22047n.get(this.i)).f25458b;
    }
}
