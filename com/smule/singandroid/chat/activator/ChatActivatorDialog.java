package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.os.Bundle;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorInterface;
import com.smule.singandroid.dialogs.BusyScreenDialog;

public class ChatActivatorDialog extends BusyScreenDialog {
    private ChatActivator f21276a;
    private ChatActivatorInterface f21277e;
    private boolean f21278f;
    private boolean f21279g;
    private final ChatActivatorInterface f21280h;

    class C43431 implements ChatActivatorInterface {
        final /* synthetic */ ChatActivatorDialog f21275a;

        C43431(ChatActivatorDialog chatActivatorDialog) {
            this.f21275a = chatActivatorDialog;
        }

        public void s_() {
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.s_();
            }
        }

        public void mo6551a(ChatStatus chatStatus) {
            if (this.f21275a.f21279g) {
                this.f21275a.dismiss();
            }
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.mo6551a(chatStatus);
            }
        }

        public void a_(Chat chat) {
            if (this.f21275a.f21278f) {
                this.f21275a.dismiss();
            }
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.a_(chat);
            }
        }

        public void mo6553c(Chat chat) {
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.mo6553c(chat);
            }
        }

        public void mo6550a(Chat chat, ChatStatus chatStatus) {
            if (this.f21275a.f21279g) {
                this.f21275a.dismiss();
            }
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.mo6550a(chat, chatStatus);
            }
        }

        public void t_() {
            if (this.f21275a.f21276a.m22935c() == null && this.f21275a.f21278f) {
                this.f21275a.dismiss();
            }
            if (this.f21275a.f21277e != null) {
                this.f21275a.f21277e.t_();
            }
        }
    }

    public ChatActivatorDialog(Context context, int i) {
        this(context, context.getResources().getString(i));
    }

    public ChatActivatorDialog(Context context, String str) {
        this(context, str, null);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.f21276a = (ChatActivator) bundle.getParcelable("ChatActivatorDialog.CHAT_ACTIVATOR");
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle onSaveInstanceState = super.onSaveInstanceState();
        onSaveInstanceState.putParcelable("ChatActivatorDialog.CHAT_ACTIVATOR", this.f21276a);
        return onSaveInstanceState;
    }

    public ChatActivatorDialog(Context context, String str, String str2) {
        super(context, str, str2);
        this.f21276a = new ChatActivator();
        this.f21278f = true;
        this.f21279g = true;
        this.f21280h = new C43431(this);
    }

    public void m22944a(Chat chat, ChatActivatorInterface chatActivatorInterface) {
        if (isShowing()) {
            throw new RuntimeException("Must call setChat before showing dialog.");
        }
        this.f21276a = ChatActivator.m22929a(chat);
        this.f21277e = chatActivatorInterface;
    }

    public void m22943a(AccountIcon accountIcon, ChatActivatorInterface chatActivatorInterface) {
        AccountIconCache.m19106a().m19109a(accountIcon);
        m22946a(accountIcon.jid, chatActivatorInterface);
    }

    public void m22946a(String str, ChatActivatorInterface chatActivatorInterface) {
        if (isShowing()) {
            throw new RuntimeException("Must call setPeerChatJID before showing dialog.");
        }
        this.f21277e = chatActivatorInterface;
        this.f21276a = ChatActivator.m22930a(str);
    }

    public void m22945a(ChatActivatorInterface chatActivatorInterface) {
        if (isShowing()) {
            throw new RuntimeException("Must call setChatActivatorListener before showing dialog.");
        }
        this.f21277e = chatActivatorInterface;
    }

    public void m22947a(boolean z) {
        this.f21278f = z;
    }

    protected void onStart() {
        super.onStart();
        this.f21276a.m22933a(getContext(), this.f21280h);
        this.f21276a.m22934b();
    }

    protected void onStop() {
        super.onStop();
        this.f21276a.m22932a();
    }

    public void dismiss() {
        super.dismiss();
        this.f21276a.m22932a();
    }
}
