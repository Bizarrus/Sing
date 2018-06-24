package com.smule.singandroid.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import com.foound.widget.AmazingListView;
import com.smule.android.network.models.AccountIcon;
import com.smule.singandroid.C1947R;
import java.util.List;
import org.androidannotations.api.BackgroundExecutor;
import org.androidannotations.api.view.HasViews;
import org.androidannotations.api.view.OnViewChangedListener;
import org.androidannotations.api.view.OnViewChangedNotifier;

public final class SelectUsersAndChatsView_ extends SelectUsersAndChatsView implements HasViews, OnViewChangedListener {
    private boolean f21249C = false;
    private final OnViewChangedNotifier f21250D = new OnViewChangedNotifier();

    class C43361 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView_ f21245a;

        C43361(SelectUsersAndChatsView_ selectUsersAndChatsView_) {
            this.f21245a = selectUsersAndChatsView_;
        }

        public void onClick(View view) {
            this.f21245a.m22885b();
        }
    }

    class C43372 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView_ f21246a;

        C43372(SelectUsersAndChatsView_ selectUsersAndChatsView_) {
            this.f21246a = selectUsersAndChatsView_;
        }

        public void onClick(View view) {
            this.f21246a.m22898i();
        }
    }

    class C43383 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView_ f21247a;

        C43383(SelectUsersAndChatsView_ selectUsersAndChatsView_) {
            this.f21247a = selectUsersAndChatsView_;
        }

        public void onClick(View view) {
            this.f21247a.m22899j();
        }
    }

    class C43394 implements OnClickListener {
        final /* synthetic */ SelectUsersAndChatsView_ f21248a;

        C43394(SelectUsersAndChatsView_ selectUsersAndChatsView_) {
            this.f21248a = selectUsersAndChatsView_;
        }

        public void onClick(View view) {
            this.f21248a.m22900k();
        }
    }

    public SelectUsersAndChatsView_(Context context) {
        super(context);
        m22903m();
    }

    public SelectUsersAndChatsView_(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m22903m();
    }

    public SelectUsersAndChatsView_(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m22903m();
    }

    public void onFinishInflate() {
        if (!this.f21249C) {
            this.f21249C = true;
            inflate(getContext(), C1947R.layout.select_users_and_chats_view, this);
            this.f21250D.a(this);
        }
        super.onFinishInflate();
    }

    private void m22903m() {
        OnViewChangedNotifier a = OnViewChangedNotifier.a(this.f21250D);
        OnViewChangedNotifier.a(this);
        OnViewChangedNotifier.a(a);
    }

    public void m22904a(HasViews hasViews) {
        this.c = (ViewGroup) hasViews.findViewById(C1947R.id.root);
        this.d = (ViewGroup) hasViews.findViewById(C1947R.id.content_view);
        this.e = (ViewGroup) hasViews.findViewById(C1947R.id.loading_view);
        this.f = (ViewGroup) hasViews.findViewById(C1947R.id.search_area);
        this.g = (EditText) hasViews.findViewById(C1947R.id.search_edit_text);
        this.h = hasViews.findViewById(C1947R.id.search_edit_line);
        this.i = hasViews.findViewById(C1947R.id.search_cancel_button);
        this.j = hasViews.findViewById(C1947R.id.search_shadow);
        this.k = (RecyclerView) hasViews.findViewById(C1947R.id.invited_portraits_list);
        this.l = (ViewGroup) hasViews.findViewById(C1947R.id.no_results_view);
        this.m = (AmazingListView) hasViews.findViewById(C1947R.id.user_list);
        this.n = (ViewGroup) hasViews.findViewById(C1947R.id.no_friends_view);
        this.o = (ImageView) hasViews.findViewById(C1947R.id.back_button);
        this.p = hasViews.findViewById(C1947R.id.search_overlay);
        this.q = (ViewGroup) hasViews.findViewById(C1947R.id.timeout_view);
        View findViewById = hasViews.findViewById(C1947R.id.find_friends_button);
        View findViewById2 = hasViews.findViewById(C1947R.id.retry);
        if (findViewById != null) {
            findViewById.setOnClickListener(new C43361(this));
        }
        if (this.i != null) {
            this.i.setOnClickListener(new C43372(this));
        }
        if (this.p != null) {
            this.p.setOnClickListener(new C43383(this));
        }
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new C43394(this));
        }
        m22878a();
    }

    protected void mo6762b(List<AccountIcon> list) {
        BackgroundExecutor.a();
        super.mo6762b((List) list);
    }
}
