package com.smule.singandroid.chat.message_views;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.Chat.Type;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatMessage.State;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.PeerChat;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.chat.ChatDate;
import com.smule.singandroid.chat.ChatFragment.ChatAdapter;
import com.smule.singandroid.chat.message_aggregation.ChatMessageAggregator;
import com.smule.singandroid.customviews.ProfileImageWithVIPBadge;
import com.smule.singandroid.list_items.MediaPlayingViewInterface;
import com.smule.singandroid.utils.ChatUtils;
import java.util.Calendar;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public abstract class ChatMessageBaseListItem extends LinearLayout implements ChatMessageListItemInterface, MediaPlayingViewInterface {
    public static final String f21321a = ChatMessageBaseListItem.class.getName();
    @ViewById
    protected View f21322b;
    @ViewById
    protected LinearLayout f21323c;
    @ViewById
    protected LinearLayout f21324d;
    @ViewById
    protected TextView f21325e;
    @ViewById
    protected View f21326f;
    @ViewById
    protected ProfileImageWithVIPBadge f21327g;
    @ViewById
    TextView f21328h;
    @ViewById
    protected MessageTimestampStatus f21329i;
    protected ChatAdapter f21330j;
    protected boolean f21331k;
    protected ChatMessage f21332l;
    boolean f21333m;
    protected ChatMessageViewListener f21334n;

    public interface ChatMessageViewListener {
        void mo6746a(AccountIcon accountIcon);

        void mo6747a(ChatMessage chatMessage);

        void mo6748a(GroupInvitationChatMessage groupInvitationChatMessage);
    }

    public interface ChatMessageBodyViewInterface {
    }

    public ChatMessageBaseListItem(Context context) {
        super(context);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode() && this.f21332l != null) {
            mo6769a(null, this.f21332l, 0);
        }
    }

    public void mo6770a(ChatMessageViewListener chatMessageViewListener, ChatAdapter chatAdapter) {
        this.f21334n = chatMessageViewListener;
        this.f21330j = chatAdapter;
    }

    public ChatMessage getMessage() {
        return this.f21332l;
    }

    public void mo6769a(Chat chat, ChatMessage chatMessage, int i) {
        this.f21332l = chatMessage;
        this.f21331k = ChatUtils.b(chatMessage);
        int i2 = this.f21331k ? 5 : 3;
        if (this.f21323c != null) {
            this.f21323c.setGravity(i2);
        }
        if (this.f21324d != null) {
            this.f21324d.setGravity(i2);
        }
        if (isInEditMode()) {
            m22993a(Type.PEER, chatMessage, i);
        } else {
            m22993a(chat.mo6335a(), chatMessage, i);
        }
        m23006c(chat, chatMessage, i);
        m22998a(chat, chatMessage, i, false);
        m23008d(chat, chatMessage, i);
        m23011f(chat, chatMessage, i);
    }

    public void mo6771b(Chat chat, ChatMessage chatMessage, int i) {
        m23006c(chat, chatMessage, i);
        m22998a(chat, chatMessage, i, true);
    }

    public void m23006c(Chat chat, ChatMessage chatMessage, int i) {
        if (this.f21329i != null) {
            if (m23010e(chat, chatMessage, i)) {
                ChatDate chatDate = new ChatDate(chatMessage.mo6767c(), getContext());
                this.f21329i.setVisibility(0);
                this.f21329i.setText(chatDate.m22434b());
                return;
            }
            this.f21329i.setVisibility(8);
        }
    }

    public void m22998a(Chat chat, ChatMessage chatMessage, int i, boolean z) {
        boolean z2 = false;
        if (this.f21331k) {
            boolean z3;
            boolean z4;
            State state;
            if (chat == null || chat.m19224l() != chatMessage) {
                z3 = false;
            } else {
                z3 = true;
            }
            if (chat instanceof PeerChat) {
                boolean z5 = chatMessage.m19389f().equals(((PeerChat) chat).mo6334S()) && ChatUtils.a(chat.m19224l());
                z4 = z5;
            } else {
                z4 = false;
            }
            if (z3 || z4) {
                z2 = true;
            }
            if (this.f21332l.m19388e() == ChatStatus.QUEUE_FULL) {
                state = State.READY;
            } else if (this.f21332l.mo6360a() != ChatMessage.Type.GROUP_INVITATION || this.f21332l.m19388e().m19417a()) {
                state = chatMessage.m19387d();
            } else {
                state = State.READY;
            }
            this.f21329i.m23092a(state, z4, z, z2, this.f21332l.m19388e());
        } else if (this.f21329i != null) {
            this.f21329i.m23089a();
        }
    }

    @Click
    protected void m23005c() {
        if (this.f21331k) {
            this.f21334n.mo6747a(this.f21332l);
        }
    }

    protected void m23008d(Chat chat, ChatMessage chatMessage, int i) {
        int i2 = 1;
        int i3 = 0;
        if (this.f21327g != null) {
            int i4;
            int i5;
            int i6 = this.f21325e.getVisibility() == 0 ? 1 : 0;
            ChatMessage a = m22992a(i);
            if (a == null || a.m19385b() == chatMessage.m19385b()) {
                i4 = 0;
            } else {
                i4 = 1;
            }
            if (this.f21331k) {
                i5 = 0;
            } else {
                i5 = 1;
            }
            if (i6 == 0 || this.f21331k) {
                if (i5 == 0 || a == null || a.mo6360a() == ChatMessage.Type.GROUP_STATUS) {
                    i2 = i5;
                } else if (i4 == 0 && !m23010e(chat, a, i - 1)) {
                    i2 = 0;
                }
            }
            if (i2 == 0) {
                this.f21327g.setVisibility(4);
                if (chat.mo6335a() != Type.GROUP || a == null || (chatMessage.m19385b() == a.m19385b() && !m23010e(chat, a, i - 1))) {
                    i6 = 8;
                } else {
                    i6 = 4;
                }
                this.f21328h.setVisibility(i6);
                return;
            }
            this.f21327g.setVisibility(0);
            if (!isInEditMode()) {
                TextView textView = this.f21328h;
                if (chat.mo6335a() != Type.GROUP) {
                    i3 = 8;
                }
                textView.setVisibility(i3);
            }
            if (this.f21330j != null) {
                final AccountIcon a2 = this.f21330j.m22535a(chatMessage.m19385b());
                if (a2 != null) {
                    if (this.f21327g != null) {
                        this.f21327g.m23395a(a2, new OnClickListener(this) {
                            final /* synthetic */ ChatMessageBaseListItem f21320b;

                            public void onClick(View view) {
                                this.f21320b.f21334n.mo6746a(a2);
                            }
                        });
                    }
                    this.f21328h.setText(a2.handle);
                }
            } else if (isInEditMode()) {
                this.f21327g.setBitmap(BitmapFactory.decodeResource(getResources(), C1947R.drawable.ic_launcher));
                this.f21328h.setText(getResources().getString(C1947R.string.chat_message_unknown_user));
            }
        }
    }

    protected boolean m23010e(Chat chat, ChatMessage chatMessage, int i) {
        if (isInEditMode()) {
            return true;
        }
        if (this.f21330j == null || chatMessage == null) {
            return false;
        }
        if (i == this.f21330j.getCount() - 1) {
            return true;
        }
        if (this.f21331k) {
            if (this.f21332l.m19387d() != State.READY && this.f21332l.m19388e() != ChatStatus.QUEUE_FULL) {
                return true;
            }
            if (chat instanceof PeerChat) {
                if (chatMessage.m19389f().equals(((PeerChat) chat).mo6334S())) {
                    return true;
                }
            }
        }
        ChatMessage chatMessage2 = (ChatMessage) this.f21330j.m22542l().get(i + 1);
        if (chat.mo6335a() == Type.GROUP && chatMessage.m19385b() != chatMessage2.m19385b()) {
            return true;
        }
        if (ChatMessageAggregator.m22985a(chatMessage, chatMessage2) || this.f21325e.getVisibility() == 0) {
            return false;
        }
        return true;
    }

    private ChatMessage m22992a(int i) {
        if (isInEditMode() || i == 0) {
            return null;
        }
        return (ChatMessage) this.f21330j.m22542l().get(i - 1);
    }

    protected void m23011f(Chat chat, ChatMessage chatMessage, int i) {
        this.f21326f.getLayoutParams().height = m22994g(chat, chatMessage, i);
        ((MarginLayoutParams) this.f21325e.getLayoutParams()).bottomMargin = m22995a(chat, chatMessage);
    }

    private int m22994g(Chat chat, ChatMessage chatMessage, int i) {
        ChatMessage a = m22992a(i);
        int a2 = m22996a(chat, chatMessage, a);
        boolean a3 = m23001a(chatMessage, i);
        if (a3) {
            if (chat.mo6335a() == Type.GROUP) {
                a2 = getResources().getDimensionPixelOffset(C1947R.dimen.margin_damn);
            } else {
                a2 = getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
            }
        }
        if (a == null) {
            return a2;
        }
        if (a.mo6360a() == ChatMessage.Type.GROUP_STATUS) {
            if (chatMessage.mo6360a() == ChatMessage.Type.GROUP_STATUS || m23001a(chatMessage, i)) {
                return getResources().getDimensionPixelOffset(C1947R.dimen.margin_small);
            }
            return getResources().getDimensionPixelOffset(C1947R.dimen.margin_huge);
        } else if (a3) {
            return a2;
        } else {
            if (chatMessage.mo6360a() == ChatMessage.Type.GROUP_STATUS) {
                return getResources().getDimensionPixelOffset(C1947R.dimen.margin_damn);
            }
            if (chatMessage.m19385b() != a.m19385b()) {
                if (chat.mo6335a() == Type.GROUP) {
                    return getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
                }
                return getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
            } else if (!m23010e(chat, a, i - 1) || m23001a(a, i - 1)) {
                return a2;
            } else {
                if (chat.mo6335a() == Type.GROUP && ChatUtils.b(chatMessage)) {
                    return 0;
                }
                return getResources().getDimensionPixelOffset(C1947R.dimen.margin_medium);
            }
        }
    }

    protected int m22996a(Chat chat, ChatMessage chatMessage, ChatMessage chatMessage2) {
        return getResources().getDimensionPixelOffset(C1947R.dimen.margin_tiny);
    }

    protected int m22995a(Chat chat, ChatMessage chatMessage) {
        if (chatMessage.mo6360a() == ChatMessage.Type.GROUP_STATUS) {
            return getResources().getDimensionPixelOffset(C1947R.dimen.margin_small);
        }
        return getResources().getDimensionPixelOffset(C1947R.dimen.margin_huge);
    }

    private boolean m22993a(Type type, ChatMessage chatMessage, int i) {
        if (isInEditMode()) {
            if (this.f21333m) {
                Calendar instance = Calendar.getInstance();
                instance.add(5, -1);
                m22999a(new ChatDate(instance.getTime(), getContext()));
            } else {
                m23007d();
            }
            return this.f21333m;
        }
        m22992a(i);
        ChatDate b = m23002b(chatMessage, i);
        if (b != null) {
            m22999a(b);
        } else {
            m23007d();
        }
        return b != null;
    }

    protected boolean m23001a(ChatMessage chatMessage, int i) {
        return m23002b(chatMessage, i) != null;
    }

    protected ChatDate m23002b(ChatMessage chatMessage, int i) {
        ChatMessage a = m22992a(i);
        ChatDate chatDate = new ChatDate(chatMessage.mo6767c(), getContext());
        return (a != null && new ChatDate(a.mo6767c(), getContext()).m22432a(chatDate)) ? null : chatDate;
    }

    protected void m22999a(ChatDate chatDate) {
        this.f21325e.setText(chatDate.m22431a(true, true));
        this.f21325e.setVisibility(0);
    }

    protected void m23007d() {
        this.f21325e.setVisibility(8);
    }

    public void mo6772e() {
        Animation animationSet = new AnimationSet(true);
        Animation alphaAnimation = new AlphaAnimation(0.0f, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        alphaAnimation.setDuration(500);
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT, 1, 0.0f);
        translateAnimation.setDuration(500);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        startAnimation(animationSet);
    }

    public void u_() {
    }

    public void mo6724b() {
    }

    public String getMediaKey() {
        return null;
    }
}
