/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.AnimationSet
 *  android.view.animation.TranslateAnimation
 *  android.widget.LinearLayout
 *  android.widget.TextView
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EViewGroup
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.ChatDate;
import com.smule.singandroid.chat.ChatFragment;
import com.smule.singandroid.chat.message_aggregation.ChatMessageAggregator;
import com.smule.singandroid.chat.message_views.ChatMessageListItemInterface;
import com.smule.singandroid.chat.message_views.MessageTimestampStatus;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.utils.ChatUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class ChatMessageBaseListItem
extends LinearLayout
implements ChatMessageListItemInterface,
MediaPlayingViewInterface {
    public static final String a = ChatMessageBaseListItem.class.getName();
    @ViewById
    protected View b;
    @ViewById
    protected LinearLayout c;
    @ViewById
    protected LinearLayout d;
    @ViewById
    protected TextView e;
    @ViewById
    protected View f;
    @ViewById
    protected ProfileImageWithVIPBadge g;
    @ViewById
    TextView h;
    @ViewById
    protected MessageTimestampStatus i;
    protected ChatFragment.ChatAdapter j;
    protected boolean k;
    protected ChatMessage l;
    boolean m;
    protected ChatMessageViewListener n;

    public ChatMessageBaseListItem(Context context) {
        super(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private ChatMessage a(int n) {
        if (this.isInEditMode() || n == 0) {
            return null;
        }
        return this.j.o().get(n - 1);
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(Chat.Type object, ChatMessage chatMessage, int n) {
        if (this.isInEditMode()) {
            if (this.m) {
                object = Calendar.getInstance();
                object.add(5, -1);
                this.a(new ChatDate(object.getTime(), this.getContext()));
                return this.m;
            }
            this.d();
            return this.m;
        }
        this.a(n);
        object = this.b(chatMessage, n);
        if (object != null) {
            this.a((ChatDate)object);
        } else {
            this.d();
        }
        if (object == null) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    private int g(Chat chat, ChatMessage chatMessage, int n) {
        ChatMessage chatMessage2 = this.a(n);
        int n2 = this.a(chat, chatMessage, chatMessage2);
        boolean bl = this.a(chatMessage, n);
        if (bl) {
            n2 = chat.a() == Chat.Type.b ? this.getResources().getDimensionPixelOffset(2131428174) : this.getResources().getDimensionPixelOffset(2131428167);
        }
        int n3 = n2;
        if (chatMessage2 == null) return n3;
        if (chatMessage2.a() == ChatMessage.Type.g) {
            if (chatMessage.a() == ChatMessage.Type.g) return this.getResources().getDimensionPixelOffset(2131428182);
            if (this.a(chatMessage, n)) return this.getResources().getDimensionPixelOffset(2131428182);
            return this.getResources().getDimensionPixelOffset(2131428173);
        }
        n3 = n2;
        if (bl) return n3;
        if (chatMessage.a() == ChatMessage.Type.g) {
            return this.getResources().getDimensionPixelOffset(2131428174);
        }
        if (chatMessage.b() != chatMessage2.b()) {
            if (chat.a() != Chat.Type.b) return this.getResources().getDimensionPixelOffset(2131428167);
            return this.getResources().getDimensionPixelOffset(2131428167);
        }
        n3 = n2;
        if (!this.e(chat, chatMessage2, n - 1)) return n3;
        n3 = n2;
        if (this.a(chatMessage2, n - 1)) return n3;
        if (chat.a() != Chat.Type.b) return this.getResources().getDimensionPixelOffset(2131428167);
        if (!ChatUtils.b(chatMessage)) return this.getResources().getDimensionPixelOffset(2131428167);
        return 0;
    }

    protected int a(Chat chat, ChatMessage chatMessage) {
        if (chatMessage.a() == ChatMessage.Type.g) {
            return this.getResources().getDimensionPixelOffset(2131428182);
        }
        return this.getResources().getDimensionPixelOffset(2131428173);
    }

    protected int a(Chat chat, ChatMessage chatMessage, ChatMessage chatMessage2) {
        return this.getResources().getDimensionPixelOffset(2131428177);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void a(Chat chat, ChatMessage chatMessage, int n) {
        this.l = chatMessage;
        this.k = ChatUtils.b(chatMessage);
        int n2 = this.k ? 5 : 3;
        if (this.c != null) {
            this.c.setGravity(n2);
        }
        if (this.d != null) {
            this.d.setGravity(n2);
        }
        if (this.isInEditMode()) {
            this.a(Chat.Type.a, chatMessage, n);
        } else {
            this.a(chat.a(), chatMessage, n);
        }
        this.c(chat, chatMessage, n);
        this.a(chat, chatMessage, n, false);
        this.d(chat, chatMessage, n);
        this.f(chat, chatMessage, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Chat object, ChatMessage chatMessage, int n, boolean bl) {
        boolean bl2;
        boolean bl3 = false;
        if (!this.k) {
            if (this.i != null) {
                this.i.a();
            }
            return;
        }
        n = object != null && object.l() == chatMessage ? 1 : 0;
        if (object instanceof PeerChat) {
            PeerChat peerChat = (PeerChat)object;
            bl2 = chatMessage.f().equals(peerChat.S()) && ChatUtils.a(object.l());
        } else {
            bl2 = false;
        }
        if (n != 0 || bl2) {
            bl3 = true;
        }
        object = this.l.e() == ChatStatus.f ? ChatMessage.State.b : (this.l.a() == ChatMessage.Type.f && !this.l.e().a() ? ChatMessage.State.b : chatMessage.d());
        this.i.a((ChatMessage.State)((Object)object), bl2, bl, bl3, this.l.e());
    }

    protected void a(ChatDate chatDate) {
        this.e.setText((CharSequence)chatDate.a(true, true));
        this.e.setVisibility(0);
    }

    @Override
    public void a(ChatMessageViewListener chatMessageViewListener, ChatFragment.ChatAdapter chatAdapter) {
        this.n = chatMessageViewListener;
        this.j = chatAdapter;
    }

    protected boolean a(ChatMessage chatMessage, int n) {
        if (this.b(chatMessage, n) != null) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected ChatDate b(ChatMessage object, int n) {
        ChatMessage chatMessage = this.a(n);
        object = new ChatDate(object.c(), this.getContext());
        if (chatMessage == null || !new ChatDate(chatMessage.c(), this.getContext()).a((ChatDate)object)) {
            return object;
        }
        return null;
    }

    @Override
    public void b(Chat chat, ChatMessage chatMessage, int n) {
        this.c(chat, chatMessage, n);
        this.a(chat, chatMessage, n, true);
    }

    @Click
    protected void c() {
        if (!this.k) {
            return;
        }
        this.n.a(this.l);
    }

    public void c(Chat object, ChatMessage chatMessage, int n) {
        if (this.i == null) {
            return;
        }
        if (this.e((Chat)object, chatMessage, n)) {
            object = new ChatDate(chatMessage.c(), this.getContext());
            this.i.setVisibility(0);
            this.i.setText((CharSequence)object.b());
            return;
        }
        this.i.setVisibility(8);
    }

    protected void d() {
        this.e.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void d(Chat object, ChatMessage chatMessage, int n) {
        boolean bl;
        int n2;
        ChatMessage chatMessage2;
        block19 : {
            boolean bl2;
            boolean bl3;
            block17 : {
                block18 : {
                    bl2 = true;
                    n2 = 0;
                    if (this.g == null) return;
                    boolean bl4 = this.e.getVisibility() == 0;
                    chatMessage2 = this.a(n);
                    bl3 = chatMessage2 != null && chatMessage2.b() != chatMessage.b();
                    bl = !this.k;
                    if (!bl4) break block17;
                    if (this.k) break block18;
                    bl = bl2;
                    break block19;
                }
                if (!bl) break block19;
            }
            if (chatMessage2 != null && chatMessage2.a() != ChatMessage.Type.g) {
                bl = bl2;
                if (!bl3) {
                    bl = bl2;
                    if (!this.e((Chat)object, chatMessage2, n - 1)) {
                        bl = false;
                    }
                }
            }
        }
        if (!bl) {
            this.g.setVisibility(4);
            n = object.a() == Chat.Type.b && chatMessage2 != null && (chatMessage.b() != chatMessage2.b() || this.e((Chat)object, chatMessage2, n - 1)) ? 4 : 8;
            this.h.setVisibility(n);
            return;
        }
        this.g.setVisibility(0);
        if (!this.isInEditMode()) {
            chatMessage2 = this.h;
            n = object.a() == Chat.Type.b ? n2 : 8;
            chatMessage2.setVisibility(n);
        }
        if (this.j != null) {
            object = this.j.a(chatMessage.b());
            if (object == null) return;
            {
                if (this.g != null) {
                    this.g.a((AccountIcon)object, new View.OnClickListener((AccountIcon)object){
                        final /* synthetic */ AccountIcon a;
                        {
                            this.a = accountIcon;
                        }

                        public void onClick(View view) {
                            ChatMessageBaseListItem.this.n.a(this.a);
                        }
                    });
                }
                this.h.setText((CharSequence)object.handle);
                return;
            }
        }
        if (!this.isInEditMode()) {
            return;
        }
        object = BitmapFactory.decodeResource((Resources)this.getResources(), (int)2130837837);
        this.g.setBitmap((Bitmap)object);
        this.h.setText((CharSequence)this.getResources().getString(2131296570));
    }

    @Override
    public void e() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(500);
        animationSet.addAnimation((Animation)translateAnimation);
        animationSet.addAnimation((Animation)alphaAnimation);
        animationSet.setFillAfter(true);
        this.startAnimation((Animation)animationSet);
    }

    protected boolean e(Chat chat, ChatMessage chatMessage, int n) {
        ChatMessage chatMessage2;
        if (this.isInEditMode()) {
            return true;
        }
        if (this.j == null || chatMessage == null) {
            return false;
        }
        if (n == this.j.getCount() - 1) {
            return true;
        }
        if (this.k) {
            if (this.l.d() != ChatMessage.State.b && this.l.e() != ChatStatus.f) {
                return true;
            }
            if (chat instanceof PeerChat) {
                chatMessage2 = (PeerChat)chat;
                if (chatMessage.f().equals(chatMessage2.S())) {
                    return true;
                }
            }
        }
        chatMessage2 = this.j.o().get(n + 1);
        if (chat.a() == Chat.Type.b && chatMessage.b() != chatMessage2.b()) {
            return true;
        }
        if (!ChatMessageAggregator.a(chatMessage, chatMessage2) && this.e.getVisibility() != 0) {
            return true;
        }
        return false;
    }

    protected void f(Chat chat, ChatMessage chatMessage, int n) {
        this.f.getLayoutParams().height = this.g(chat, chatMessage, n);
        ((ViewGroup.MarginLayoutParams)this.e.getLayoutParams()).bottomMargin = this.a(chat, chatMessage);
    }

    public String getMediaKey() {
        return null;
    }

    @Override
    public ChatMessage getMessage() {
        return this.l;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (this.isInEditMode() && this.l != null) {
            this.a((Chat)null, this.l, 0);
        }
    }

    @Override
    public void r_() {
    }

    @Override
    public void s_() {
    }

    public static interface ChatMessageBodyViewInterface {
    }

    public static interface ChatMessageViewListener {
        public void a(AccountIcon var1);

        public void a(ChatMessage var1);

        public void a(GroupInvitationChatMessage var1);
    }

}

