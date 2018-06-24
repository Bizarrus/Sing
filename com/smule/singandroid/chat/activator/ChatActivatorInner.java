package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountIconsResponseCallback;
import com.smule.android.network.managers.UserManager.AccountIconsResponse;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.utils.Toaster;
import com.smule.chat.AccountIconCache;
import com.smule.chat.Chat;
import com.smule.chat.Chat.ChatState;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManager$ChatCallback;
import com.smule.chat.ChatManager$ConnectionStatus;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatManagerListenerAdapter;
import com.smule.chat.ChatStatus;
import com.smule.chat.Completion;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupMemberStatus;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.chat.activator.ChatActivator.ChatActivatorInterface;
import com.smule.singandroid.utils.ChatUtils;

class ChatActivatorInner {
    protected boolean f21290a;
    protected Chat f21291b;
    protected String f21292c;
    protected String f21293d;
    protected ChatManager f21294e;
    protected boolean f21295f;
    protected ChatActivatorInterface f21296g;
    ChatManagerListener f21297h = new C43462(this);
    ChatListener f21298i = new C43473(this);
    ChatManager$ChatCallback f21299j = new C43484(this);
    private final String f21300k = ChatActivatorInner.class.getName();
    private Context f21301l;
    private Handler f21302m = new Handler(Looper.getMainLooper());
    private boolean f21303n;
    private boolean f21304o = false;

    class C43462 extends ChatManagerListenerAdapter {
        final /* synthetic */ ChatActivatorInner f21286a;

        C43462(ChatActivatorInner chatActivatorInner) {
            this.f21286a = chatActivatorInner;
        }

        public void mo6320a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
            if (this.f21286a.f21290a && this.f21286a.f21294e.b() == ChatManager$ConnectionStatus.CONNECTED) {
                this.f21286a.m22961e();
            }
        }
    }

    class C43473 extends ChatListenerAdapter {
        final /* synthetic */ ChatActivatorInner f21287a;

        C43473(ChatActivatorInner chatActivatorInner) {
            this.f21287a = chatActivatorInner;
        }

        public void m22950c(Chat chat) {
            if (this.f21287a.f21290a && (chat instanceof GroupChat) && ((GroupChat) chat).m19541b(UserManager.a().f()) == GroupMemberStatus.PENDING && this.f21287a.f21290a) {
                Log.b(this.f21287a.f21300k, "onMembersChanged rejoining");
                if (this.f21287a.f21291b != null) {
                    this.f21287a.f21291b.m19205b(this.f21287a.f21298i);
                    this.f21287a.f21291b.m19229q();
                    this.f21287a.f21291b = null;
                    this.f21287a.f21295f = false;
                }
                this.f21287a.m22970d();
            }
        }

        public void m22949a(Chat chat, ChatState chatState) {
            if (this.f21287a.f21304o && chatState != ChatState.LOADING) {
                Log.b(this.f21287a.f21300k, "onChatReady onChatStateChanged: " + chatState);
                this.f21287a.f21304o = false;
                this.f21287a.m22963f();
            }
        }
    }

    class C43484 implements ChatManager$ChatCallback {
        final /* synthetic */ ChatActivatorInner f21288a;

        C43484(ChatActivatorInner chatActivatorInner) {
            this.f21288a = chatActivatorInner;
        }

        public void mo6326a(Chat chat, ChatStatus chatStatus) {
            Log.b(this.f21288a.f21300k, "onChatReady chatStatus: " + chatStatus);
            if (!this.f21288a.f21290a) {
                return;
            }
            if (chatStatus != ChatStatus.OK) {
                ChatUtils.a(this.f21288a.f21301l, chatStatus);
                this.f21288a.f21296g.mo6551a(chatStatus);
                return;
            }
            this.f21288a.m22955a(chat);
        }
    }

    class C43495 implements Completion<ChatStatus> {
        final /* synthetic */ ChatActivatorInner f21289a;

        C43495(ChatActivatorInner chatActivatorInner) {
            this.f21289a = chatActivatorInner;
        }

        public void m22952a(ChatStatus chatStatus) {
            Log.b(this.f21289a.f21300k, "enterChat status: " + chatStatus);
            if (!this.f21289a.f21290a) {
                return;
            }
            if (chatStatus != ChatStatus.OK) {
                ChatUtils.a(this.f21289a.f21301l, C1947R.string.chat_load_error, chatStatus);
                this.f21289a.f21296g.mo6550a(this.f21289a.f21291b, chatStatus);
                return;
            }
            if (!this.f21289a.f21295f) {
                this.f21289a.f21296g.mo6553c(this.f21289a.f21291b);
            }
            this.f21289a.f21295f = true;
        }
    }

    public ChatActivatorInner(Context context, Chat chat, String str, String str2, ChatActivatorInterface chatActivatorInterface) {
        this.f21301l = context;
        this.f21291b = chat;
        this.f21292c = str;
        this.f21293d = str2;
        this.f21296g = chatActivatorInterface;
        this.f21294e = SingApplication.j();
    }

    public void m22965a() {
        this.f21294e.a(this.f21297h);
    }

    public void m22967b() {
        this.f21294e.b(this.f21297h);
        if (this.f21291b != null) {
            this.f21291b.m19205b(this.f21298i);
            this.f21291b.m19229q();
        }
        this.f21303n = false;
    }

    public void m22969c() {
        this.f21290a = false;
    }

    public void m22970d() {
        Log.b(this.f21300k, "activateChat");
        this.f21290a = true;
        this.f21303n = false;
        this.f21296g.s_();
        if (this.f21294e.a() && this.f21294e.b() == ChatManager$ConnectionStatus.CONNECTED) {
            m22961e();
            return;
        }
        this.f21295f = false;
        if (this.f21292c != null) {
            this.f21294e.b(this.f21292c);
        } else if (this.f21293d != null) {
            this.f21294e.b(this.f21293d);
        }
        this.f21294e.a(true);
    }

    private void m22961e() {
        Log.b(this.f21300k, "activateChatManagerComplete mActivateChatManagerComplete: " + this.f21303n);
        if (!this.f21303n) {
            this.f21303n = true;
            this.f21296g.t_();
            if (this.f21291b != null && this.f21295f) {
                this.f21299j.mo6326a(this.f21291b, this.f21291b.m19214e());
            } else if (this.f21292c != null) {
                m22966a(this.f21292c);
            } else if (this.f21293d != null) {
                m22968b(this.f21293d);
            }
        }
    }

    protected void m22966a(String str) {
        Log.b(this.f21300k, "loadPeerJID - " + str);
        final ChatManager j = SingApplication.j();
        final long c = j.c(str);
        AccountIconCache.m19106a().m19108a(c, new UserManager$AccountIconsResponseCallback(this) {
            final /* synthetic */ ChatActivatorInner f21285c;

            public void handleResponse(final AccountIconsResponse accountIconsResponse) {
                this.f21285c.f21302m.post(new Runnable(this) {
                    final /* synthetic */ C43451 f21282b;

                    public void run() {
                        if (this.f21282b.f21285c.f21290a) {
                            if (accountIconsResponse.a()) {
                                for (AccountIcon accountIcon : accountIconsResponse.accountIcons) {
                                    if (accountIcon.accountId == c) {
                                        j.a(accountIcon, this.f21282b.f21285c.f21299j);
                                        return;
                                    }
                                }
                            }
                            Toaster.a(this.f21282b.f21285c.f21301l, C1947R.string.chat_load_error);
                            this.f21282b.f21285c.f21296g.mo6551a(ChatStatus.NETWORK_ERROR);
                        }
                    }
                });
            }
        });
    }

    protected void m22968b(String str) {
        Log.b(this.f21300k, "loadGroupJID: " + str);
        SingApplication.j().a(str, this.f21299j);
    }

    private void m22955a(Chat chat) {
        Log.b(this.f21300k, "lockChatAndAddListeners mChatAlreadyEntered: " + this.f21295f);
        this.f21291b = chat;
        this.f21291b.m19228p();
        this.f21291b.m19205b(this.f21298i);
        this.f21291b.m19192a(this.f21298i);
        this.f21296g.a_(chat);
        if (this.f21291b.m19212d() == ChatState.LOADING) {
            this.f21304o = true;
        } else {
            m22963f();
        }
    }

    private void m22963f() {
        Log.b(this.f21300k, "enterChat");
        Object obj;
        if (!(this.f21291b instanceof GroupChat) || ((GroupChat) this.f21291b).m19541b(UserManager.a().f()) == GroupMemberStatus.JOINED) {
            obj = null;
        } else {
            obj = 1;
        }
        if (this.f21295f && r0 == null) {
            this.f21296g.mo6553c(this.f21291b);
        }
        this.f21291b.m19197a(new C43495(this));
    }
}
