/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.ClipData
 *  android.content.ClipboardManager
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Typeface
 *  android.text.method.LinkMovementMethod
 *  android.text.method.MovementMethod
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnLongClickListener
 *  android.view.ViewGroup
 *  android.widget.Button
 *  android.widget.ProgressBar
 *  android.widget.TextView
 *  com.smule.singandroid.textviews.EllipsizingTextView
 *  com.smule.singandroid.utils.CustomTypefaceSpan
 *  com.smule.singandroid.utils.MiscUtils
 *  com.smule.singandroid.utils.PerformanceV2Util
 *  com.smule.singandroid.utils.StyleReplacer
 *  com.smule.singandroid.utils.TypefaceUtils
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.Track;
import com.smule.android.utils.LocalizedShortNumberFormatter;
import com.smule.android.utils.Toaster;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.PerformanceChatMessage;
import com.smule.singandroid.chat.message_views.ChatMessageBaseListItem;
import com.smule.singandroid.customviews.PlayableItemDetailsView;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.list_items.PerformanceItemInterface;
import com.smule.singandroid.textviews.EllipsizingTextView;
import com.smule.singandroid.utils.CustomTypefaceSpan;
import com.smule.singandroid.utils.MiscUtils;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.StyleReplacer;
import com.smule.singandroid.utils.TypefaceUtils;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class ChatMessagePerformanceBody
extends PlayableItemDetailsView
implements ChatMessageBaseListItem.ChatMessageBodyViewInterface,
MediaPlayingViewInterface,
PerformanceItemInterface {
    public static final String a = ChatMessagePerformanceBody.class.getName();
    @ViewById
    protected View b;
    @ViewById
    protected ProfileImageWithVIPBadge c;
    @ViewById
    protected EllipsizingTextView d;
    @ViewById
    protected View e;
    @ViewById
    protected View f;
    @ViewById
    protected ViewGroup g;
    @ViewById
    protected Button h;
    @ViewById
    protected View i;
    @ViewById
    protected TextView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected View l;
    @ViewById
    protected ProgressBar m;
    protected PerformanceChatMessage n;
    protected Chat o;
    protected PerformanceItemInterface.PerformanceItemListener p;
    private LocalizedShortNumberFormatter w;
    private View.OnClickListener x;

    public ChatMessagePerformanceBody(Context context) {
        super(context);
        this.x = new View.OnClickListener(){

            public void onClick(View view) {
                if (ChatMessagePerformanceBody.this.o != null) {
                    ChatMessagePerformanceBody.this.o.b(ChatMessagePerformanceBody.this.n);
                }
            }
        };
    }

    public ChatMessagePerformanceBody(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.x = new ;
    }

    public ChatMessagePerformanceBody(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.x = new ;
    }

    private void a(PerformanceErrorType performanceErrorType) {
        this.k.setVisibility(0);
        this.m.setVisibility(8);
        this.g();
        switch (performanceErrorType) {
            default: {
                this.k.setText(2131296577);
                this.setOnClickListener(this.x);
                return;
            }
            case a: 
        }
        this.k.setText(2131296576);
        this.setOnClickListener(false);
    }

    static /* synthetic */ PerformanceV2 d(ChatMessagePerformanceBody chatMessagePerformanceBody) {
        return chatMessagePerformanceBody.v;
    }

    static /* synthetic */ PerformanceV2 e(ChatMessagePerformanceBody chatMessagePerformanceBody) {
        return chatMessagePerformanceBody.v;
    }

    private void f() {
        this.k.setVisibility(8);
        this.m.setVisibility(0);
        this.g();
        this.setOnClickListener(false);
    }

    private void g() {
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.l.setVisibility(4);
        this.g.setVisibility(8);
    }

    private LocalizedShortNumberFormatter getLocalizedFormatter() {
        if (this.w == null) {
            this.w = new LocalizedShortNumberFormatter(this.getContext());
        }
        return this.w;
    }

    private void h() {
        ((ClipboardManager)this.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText((CharSequence)a, (CharSequence)this.v.webUrl));
        com.smule.android.utils.Toaster.a(this.getContext(), this.getContext().getResources().getString(2131296476), Toaster.a);
    }

    private void setOnClickListener(boolean bl) {
        if (bl) {
            this.setOnAlbumArtClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessagePerformanceBody.this.p.b(ChatMessagePerformanceBody.this, ChatMessagePerformanceBody.this.v);
                }
            });
            this.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessagePerformanceBody.this.p.a((MediaPlayingViewInterface)ChatMessagePerformanceBody.this, ChatMessagePerformanceBody.this.v);
                }
            });
            this.setOnLongClickListener(new View.OnLongClickListener(){

                public boolean onLongClick(View view) {
                    ChatMessagePerformanceBody.this.h();
                    return true;
                }
            });
            return;
        }
        this.setOnClickListener(null);
    }

    @Override
    public void a(PerformanceV2 performanceV2, boolean bl) {
        this.setPerformance(performanceV2);
    }

    public void a(ChatMessage chatMessage, boolean bl, Chat chat) {
        if (this.p == null) {
            throw new RuntimeException("Make sure to call ChatMessagePerformanceBody.init first");
        }
        this.n = (PerformanceChatMessage)chatMessage;
        this.o = chat;
        if (this.n.p()) {
            this.a(PerformanceErrorType.a);
            return;
        }
        if (this.n.d() == ChatMessage.State.b && this.n.o() != null) {
            this.setPerformance(this.n.o());
            return;
        }
        if (this.n.d() == ChatMessage.State.d) {
            this.a(PerformanceErrorType.b);
            return;
        }
        this.f();
    }

    @AfterViews
    protected void c() {
        this.d.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void d() {
        String string2;
        this.l.setVisibility(0);
        this.k.setVisibility(8);
        this.m.setVisibility(8);
        boolean bl = this.v.accountIcon.accountId == this.n.b();
        if (!(this.v.p() || this.v.e() && this.v.seed)) {
            this.b.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        if (bl) {
            if (this.v.message != null && !this.v.message.isEmpty()) {
                string2 = this.getResources().getString(2131296580);
            } else {
                if (!this.v.p()) {
                    this.b.setVisibility(8);
                    this.e.setVisibility(8);
                    return;
                }
                string2 = this.getResources().getString(2131296578);
            }
            this.c.setVisibility(8);
        } else {
            string2 = this.v.message != null && !this.v.message.isEmpty() ? this.getResources().getString(2131296579) : "%1$s";
            this.c.setVisibility(0);
            this.c.a(this.v.accountIcon, new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessagePerformanceBody.this.p.a((MediaPlayingViewInterface)ChatMessagePerformanceBody.this, ChatMessagePerformanceBody.d((ChatMessagePerformanceBody)ChatMessagePerformanceBody.this).accountIcon);
                }
            });
        }
        this.b.setVisibility(0);
        this.e.setVisibility(0);
        if (!string2.isEmpty()) {
            View.OnClickListener onClickListener = new View.OnClickListener(){

                public void onClick(View view) {
                    ChatMessagePerformanceBody.this.p.a((MediaPlayingViewInterface)ChatMessagePerformanceBody.this, ChatMessagePerformanceBody.e((ChatMessagePerformanceBody)ChatMessagePerformanceBody.this).accountIcon);
                }
            };
            View.OnClickListener onClickListener2 = new View.OnClickListener(){

                public void onClick(View view) {
                    Log.b("odietest", "defaultOnClickListener");
                }
            };
            CustomTypefaceSpan customTypefaceSpan = new CustomTypefaceSpan(this.getContext(), this.d.getTextSize(), 2131689547, TypefaceUtils.c((Context)this.getContext()));
            CustomTypefaceSpan customTypefaceSpan2 = new CustomTypefaceSpan(this.getContext(), this.d.getTextSize(), 2131689547, TypefaceUtils.a((Context)this.getContext()));
            string2 = new StyleReplacer(string2, (TextView)this.d, (Object)customTypefaceSpan2);
            string2.a(onClickListener2);
            string2.a("%1$s", this.v.accountIcon.handle, (Object)customTypefaceSpan, onClickListener);
            string2.a("%2$s", this.v.message, (Object)null);
            string2.a();
            return;
        }
        this.d.setText((CharSequence)"");
    }

    protected void e() {
        if (this.v.e() && this.v.seed) {
            this.g.setVisibility(0);
            if (this.v.p()) {
                this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2130837903, 0);
                this.j.setText(2131296695);
                this.h.setEnabled(true);
                return;
            }
            this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.j.setText(2131297119);
            this.h.setEnabled(false);
            return;
        }
        if (this.v.p()) {
            this.g.setVisibility(0);
            this.h.setEnabled(true);
            this.j.setCompoundDrawablesWithIntrinsicBounds(0, 0, 2130837932, 0);
            this.j.setText(2131296695);
            return;
        }
        this.g.setVisibility(8);
    }

    public PerformanceV2 getPerformance() {
        return this.v;
    }

    @Override
    public void setListener(PerformanceItemInterface.PerformanceItemListener performanceItemListener) {
        this.p = performanceItemListener;
    }

    public void setOnJoinListener(View.OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public void setOnMoreListener(View.OnClickListener onClickListener) {
        this.i.setOnClickListener(onClickListener);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setPerformance(PerformanceV2 performanceV2) {
        super.a(performanceV2, false);
        if (performanceV2.p()) {
            this.u.setText((CharSequence)this.getResources().getString(2131296603, new Object[]{MiscUtils.a((long)this.v.expireAt, (boolean)false, (boolean)false)}));
        } else if (performanceV2.e() && performanceV2.seed) {
            this.u.setText((CharSequence)this.getResources().getString(2131296603, new Object[]{this.getResources().getQuantityString(2131361841, 0, new Object[]{0})}));
        } else if (performanceV2.e() && !performanceV2.recentTracks.isEmpty()) {
            this.u.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.v, (boolean)true));
        } else if (performanceV2.f()) {
            this.u.setText((CharSequence)PerformanceV2Util.a((Resources)this.getResources(), (PerformanceV2)this.v, (boolean)true));
        } else {
            this.u.setText((CharSequence)this.getResources().getString(2131296605, new Object[]{performanceV2.accountIcon.handle}));
        }
        this.d();
        this.e();
        this.setOnClickListener(true);
    }

    private static enum PerformanceErrorType {
        a,
        b;
        

        private PerformanceErrorType() {
        }
    }

}

