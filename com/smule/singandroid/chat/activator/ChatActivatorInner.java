/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Looper
 */
package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerListenerAdapter;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.activator.ChatActivator;
import com.smule.singandroid.utils.ChatUtils;
import java.util.List;

class ChatActivatorInner {
    protected boolean a;
    protected Chat b;
    protected String c;
    protected String d;
    protected com.smule.chat.ChatManager e;
    protected boolean f;
    protected ChatActivator.ChatActivatorInterface g;
    ChatManagerListener h;
    ChatListener i;
    ChatManager j;
    private final String k = ChatActivatorInner.class.getName();
    private Context l;
    private Handler m = new Handler(Looper.getMainLooper());
    private boolean n;
    private boolean o = false;

    public ChatActivatorInner(Context context, Chat chat, String string2, String string3, ChatActivator.ChatActivatorInterface chatActivatorInterface) {
        this.h = new ChatManagerListenerAdapter(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void a(ChatManager connectionStatus) {
                if (!ChatActivatorInner.this.a || ChatActivatorInner.this.e.b() != ChatManager.e) {
                    return;
                }
                ChatActivatorInner.this.e();
            }
        };
        this.i = new ChatListenerAdapter(){

            @Override
            public void a(Chat chat, Chat.ChatState chatState) {
                if (ChatActivatorInner.this.o && chatState != Chat.ChatState.a) {
                    Log.b(ChatActivatorInner.this.k, "onChatReady onChatStateChanged: " + (Object)((Object)chatState));
                    ChatActivatorInner.this.o = false;
                    ChatActivatorInner.this.f();
                }
            }

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void c(Chat chat) {
                if (!(ChatActivatorInner.this.a && chat instanceof GroupChat && ((GroupChat)chat).b(com.smule.android.network.managers.UserManager.a().f()) == GroupMemberStatus.b && ChatActivatorInner.this.a)) {
                    return;
                }
                Log.b(ChatActivatorInner.this.k, "onMembersChanged rejoining");
                if (ChatActivatorInner.this.b != null) {
                    ChatActivatorInner.this.b.b(ChatActivatorInner.this.i);
                    ChatActivatorInner.this.b.q();
                    ChatActivatorInner.this.b = null;
                    ChatActivatorInner.this.f = false;
                }
                ChatActivatorInner.this.d();
            }
        };
        this.j = new ChatManager(){

            @Override
            public void a(Chat chat, ChatStatus chatStatus) {
                Log.b(ChatActivatorInner.this.k, "onChatReady chatStatus: " + (Object)((Object)chatStatus));
                if (!ChatActivatorInner.this.a) {
                    return;
                }
                if (chatStatus != ChatStatus.a) {
                    ChatUtils.a(ChatActivatorInner.this.l, chatStatus);
                    ChatActivatorInner.this.g.a(chatStatus);
                    return;
                }
                ChatActivatorInner.this.a(chat);
            }
        };
        this.l = context;
        this.b = chat;
        this.c = string2;
        this.d = string3;
        this.g = chatActivatorInterface;
        this.e = SingApplication.k();
    }

    private void a(Chat chat) {
        Log.b(this.k, "lockChatAndAddListeners mChatAlreadyEntered: " + this.f);
        this.b = chat;
        this.b.p();
        this.b.b(this.i);
        this.b.a(this.i);
        this.g.b(chat);
        if (this.b.d() == Chat.ChatState.a) {
            this.o = true;
            return;
        }
        this.f();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void e() {
        block6 : {
            block5 : {
                Log.b(this.k, "activateChatManagerComplete mActivateChatManagerComplete: " + this.n);
                if (this.n) break block5;
                this.n = true;
                this.g.y_();
                if (this.b != null && this.f) {
                    this.j.a(this.b, this.b.e());
                    return;
                }
                if (this.c != null) {
                    this.a(this.c);
                    return;
                }
                if (this.d != null) break block6;
            }
            return;
        }
        this.b(this.d);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void f() {
        Log.b(this.k, "enterChat");
        boolean bl = this.b instanceof GroupChat && ((GroupChat)this.b).b(com.smule.android.network.managers.UserManager.a().f()) != GroupMemberStatus.c;
        if (this.f && !bl) {
            this.g.c(this.b);
        }
        this.b.a(new Completion<ChatStatus>(){

            @Override
            public void a(ChatStatus chatStatus) {
                Log.b(ChatActivatorInner.this.k, "enterChat status: " + (Object)((Object)chatStatus));
                if (!ChatActivatorInner.this.a) {
                    return;
                }
                if (chatStatus != ChatStatus.a) {
                    ChatUtils.a(ChatActivatorInner.this.l, 2131296564, chatStatus);
                    ChatActivatorInner.this.g.a(ChatActivatorInner.this.b, chatStatus);
                    return;
                }
                if (!ChatActivatorInner.this.f) {
                    ChatActivatorInner.this.g.c(ChatActivatorInner.this.b);
                }
                ChatActivatorInner.this.f = true;
            }
        });
    }

    public void a() {
        this.e.a(this.h);
    }

    protected void a(String string2) {
        Log.b(this.k, "loadPeerJID - " + string2);
        final com.smule.chat.ChatManager chatManager = SingApplication.k();
        final long l = chatManager.c(string2);
        AccountIconCache.a().a(l, new UserManager(){

            @Override
            public void handleResponse(final UserManager.AccountIconsResponse accountIconsResponse) {
                ChatActivatorInner.this.m.post(new Runnable(){

                    @Override
                    public void run() {
                        if (!ChatActivatorInner.this.a) {
                            return;
                        }
                        if (accountIconsResponse.a()) {
                            for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                                if (accountIcon.accountId != l) continue;
                                chatManager.a(accountIcon, ChatActivatorInner.this.j);
                                return;
                            }
                        }
                        Toaster.a(ChatActivatorInner.this.l, 2131296564);
                        ChatActivatorInner.this.g.a(ChatStatus.b);
                    }
                });
            }

        });
    }

    public void b() {
        this.e.b(this.h);
        if (this.b != null) {
            this.b.b(this.i);
            this.b.q();
        }
        this.n = false;
    }

    protected void b(String string2) {
        Log.b(this.k, "loadGroupJID: " + string2);
        SingApplication.k().a(string2, this.j);
    }

    public void c() {
        this.a = false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d() {
        Log.b(this.k, "activateChat");
        this.a = true;
        this.n = false;
        this.g.w_();
        if (this.e.a() && this.e.b() == ChatManager.e) {
            this.e();
            return;
        }
        this.f = false;
        if (this.c != null) {
            this.e.b(this.c);
        } else if (this.d != null) {
            this.e.b(this.d);
        }
        this.e.a(true);
    }

}

