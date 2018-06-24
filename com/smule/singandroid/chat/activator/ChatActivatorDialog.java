/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.os.Parcelable
 */
package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.dialogs.BusyScreenDialog;

public class ChatActivatorDialog
extends BusyScreenDialog {
    private ChatActivator a = new ChatActivator();
    private ChatActivator.ChatActivatorInterface e;
    private boolean f = true;
    private boolean g = true;
    private final ChatActivator.ChatActivatorInterface h;

    public ChatActivatorDialog(Context context, int n) {
        this(context, context.getResources().getString(n));
    }

    public ChatActivatorDialog(Context context, String string2) {
        this(context, string2, null);
    }

    public ChatActivatorDialog(Context context, String string2, String string3) {
        super(context, string2, string3);
        this.h = new ChatActivator.ChatActivatorInterface(){

            @Override
            public void a(Chat chat, ChatStatus chatStatus) {
                if (ChatActivatorDialog.this.g) {
                    ChatActivatorDialog.this.dismiss();
                }
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.a(chat, chatStatus);
                }
            }

            @Override
            public void a(ChatStatus chatStatus) {
                if (ChatActivatorDialog.this.g) {
                    ChatActivatorDialog.this.dismiss();
                }
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.a(chatStatus);
                }
            }

            @Override
            public void b(Chat chat) {
                if (ChatActivatorDialog.this.f) {
                    ChatActivatorDialog.this.dismiss();
                }
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.b(chat);
                }
            }

            @Override
            public void c(Chat chat) {
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.c(chat);
                }
            }

            @Override
            public void w_() {
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.w_();
                }
            }

            @Override
            public void y_() {
                if (ChatActivatorDialog.this.a.c() == null && ChatActivatorDialog.this.f) {
                    ChatActivatorDialog.this.dismiss();
                }
                if (ChatActivatorDialog.this.e != null) {
                    ChatActivatorDialog.this.e.y_();
                }
            }
        };
    }

    public void a(AccountIcon accountIcon, ChatActivator.ChatActivatorInterface chatActivatorInterface) {
        AccountIconCache.a().a(accountIcon);
        this.a(accountIcon.jid, chatActivatorInterface);
    }

    public void a(Chat chat, ChatActivator.ChatActivatorInterface chatActivatorInterface) {
        if (this.isShowing()) {
            throw new RuntimeException("Must call setChat before showing dialog.");
        }
        this.a = ChatActivator.a(chat);
        this.e = chatActivatorInterface;
    }

    public void a(ChatActivator.ChatActivatorInterface chatActivatorInterface) {
        if (this.isShowing()) {
            throw new RuntimeException("Must call setChatActivatorListener before showing dialog.");
        }
        this.e = chatActivatorInterface;
    }

    public void a(String string2, ChatActivator.ChatActivatorInterface chatActivatorInterface) {
        if (this.isShowing()) {
            throw new RuntimeException("Must call setPeerChatJID before showing dialog.");
        }
        this.e = chatActivatorInterface;
        this.a = ChatActivator.a(string2);
    }

    public void a(boolean bl) {
        this.f = bl;
    }

    public void dismiss() {
        super.dismiss();
        this.a.a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.a = (ChatActivator)bundle.getParcelable("ChatActivatorDialog.CHAT_ACTIVATOR");
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = super.onSaveInstanceState();
        bundle.putParcelable("ChatActivatorDialog.CHAT_ACTIVATOR", (Parcelable)this.a);
        return bundle;
    }

    protected void onStart() {
        super.onStart();
        this.a.a(this.getContext(), this.h);
        this.a.b();
    }

    protected void onStop() {
        super.onStop();
        this.a.a();
    }

}

