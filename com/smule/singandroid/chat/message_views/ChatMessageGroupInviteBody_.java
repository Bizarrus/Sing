package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadgeAndPendingGreyDotView;
import java.util.ArrayList;
import java.util.List;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class ChatMessageGroupInviteBody_ extends ChatMessageGroupInviteBody implements HasViews, OnViewChangedListener {
    private boolean f21353n = false;
    private final OnViewChangedNotifier f21354o = new OnViewChangedNotifier();

    class C43541 implements OnClickListener {
        final /* synthetic */ ChatMessageGroupInviteBody_ f21351a;

        C43541(ChatMessageGroupInviteBody_ chatMessageGroupInviteBody_) {
            this.f21351a = chatMessageGroupInviteBody_;
        }

        public void onClick(View view) {
            this.f21351a.m23019b();
        }
    }

    class C43552 implements OnClickListener {
        final /* synthetic */ ChatMessageGroupInviteBody_ f21352a;

        C43552(ChatMessageGroupInviteBody_ chatMessageGroupInviteBody_) {
            this.f21352a = chatMessageGroupInviteBody_;
        }

        public void onClick(View view) {
            this.f21352a.m23019b();
        }
    }

    public ChatMessageGroupInviteBody_(Context context) {
        super(context);
        m23020c();
    }

    public ChatMessageGroupInviteBody_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m23020c();
    }

    public ChatMessageGroupInviteBody_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m23020c();
    }

    public void onFinishInflate() {
        if (!this.f21353n) {
            this.f21353n = true;
            inflate(getContext(), C1947R.layout.chat_message_body_view_group_invite, this);
            this.f21354o.a(this);
        }
        super.onFinishInflate();
    }

    private void m23020c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21354o);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m23021a(HasViews hasViews) {
        this.b = (TextView) hasViews.findViewById(C1947R.id.title_text);
        this.d = (Button) hasViews.findViewById(C1947R.id.accept_button);
        this.e = (LinearLayout) hasViews.findViewById(C1947R.id.portraits_container);
        this.f = (ImageView) hasViews.findViewById(C1947R.id.empty_profile);
        this.g = hasViews.findViewById(C1947R.id.root);
        List arrayList = new ArrayList();
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.profile_pic_1);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView2 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.profile_pic_2);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView3 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.profile_pic_3);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView4 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.profile_pic_4);
        ProfileImageWithVIPBadgeAndPendingGreyDotView profileImageWithVIPBadgeAndPendingGreyDotView5 = (ProfileImageWithVIPBadgeAndPendingGreyDotView) hasViews.findViewById(C1947R.id.profile_pic_5);
        if (profileImageWithVIPBadgeAndPendingGreyDotView != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView2 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView2);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView3 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView3);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView4 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView4);
        }
        if (profileImageWithVIPBadgeAndPendingGreyDotView5 != null) {
            arrayList.add(profileImageWithVIPBadgeAndPendingGreyDotView5);
        }
        this.c = arrayList;
        if (this.d != null) {
            this.d.setOnClickListener(new C43541(this));
        }
        if (this.g != null) {
            this.g.setOnClickListener(new C43552(this));
        }
    }
}
