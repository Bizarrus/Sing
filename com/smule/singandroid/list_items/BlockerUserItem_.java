package com.smule.singandroid.list_items;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class BlockerUserItem_ extends BlockerUserItem implements HasViews, OnViewChangedListener {
    private boolean f22800h = false;
    private final OnViewChangedNotifier f22801i = new OnViewChangedNotifier();

    class C46231 implements OnClickListener {
        final /* synthetic */ BlockerUserItem_ f22799a;

        C46231(BlockerUserItem_ blockerUserItem_) {
            this.f22799a = blockerUserItem_;
        }

        public void onClick(View view) {
            this.f22799a.m24239b();
        }
    }

    public BlockerUserItem_(Context context) {
        super(context);
        m24241c();
    }

    public static BlockerUserItem m24240a(Context context) {
        BlockerUserItem blockerUserItem_ = new BlockerUserItem_(context);
        blockerUserItem_.onFinishInflate();
        return blockerUserItem_;
    }

    public void onFinishInflate() {
        if (!this.f22800h) {
            this.f22800h = true;
            inflate(getContext(), C1947R.layout.blocked_user_item, this);
            this.f22801i.a(this);
        }
        super.onFinishInflate();
    }

    private void m24241c() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f22801i);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m24242a(HasViews hasViews) {
        this.a = (TextView) hasViews.findViewById(C1947R.id.username);
        this.b = (ProfileImageWithVIPBadge) hasViews.findViewById(C1947R.id.profile_image_view);
        this.c = (Button) hasViews.findViewById(C1947R.id.block_button);
        this.d = hasViews.findViewById(C1947R.id.spinner);
        if (this.a != null) {
            this.a.setOnClickListener(new C46231(this));
        }
        m24237a();
    }
}
