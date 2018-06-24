/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.chat;

import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.UserManager;
import com.smule.chat.Chat;
import com.smule.chat.ChatListener;
import com.smule.chat.ChatListenerAdapter;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatManagerListener;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupChat;
import com.smule.chat.GroupInvitationChatMessage;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.ChatAnalytics;
import com.smule.singandroid.chat.ChatAnalyticsMonitor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatAnalyticsMonitor {
    static ChatAnalyticsMonitor a;
    static ChatMessage.Type[] e;
    HashMap<String, > b = new HashMap();
    ChatManagerListener c;
    ChatListener d;

    static {
        e = new ChatMessage.Type[]{ChatMessage.Type.b, ChatMessage.Type.f, ChatMessage.Type.c, null, ChatMessage.Type.g};
    }

    public ChatAnalyticsMonitor() {
        this.c = new ChatManagerListener(this){
            final /* synthetic */ ChatAnalyticsMonitor a;
            {
                this.a = chatAnalyticsMonitor;
            }

            public void a() {
            }

            public void a(com.smule.chat.ChatManager$ConnectionStatus connectionStatus) {
            }

            public void a(GroupChat groupChat) {
                if (groupChat.v()) {
                    return;
                }
                ChatAnalytics.a(groupChat);
            }

            public void e(Chat chat) {
            }

            public void f(Chat chat) {
            }

            /*
             * Enabled aggressive block sorting
             */
            public void g(Chat chat) {
                if (chat.v() || !(chat instanceof GroupChat)) {
                    return;
                }
                ChatAnalytics.a(chat.c());
            }

            public void h(Chat chat) {
                if (chat.v()) {
                    return;
                }
                ChatAnalytics.e(chat);
            }
        };
        this.d = new ChatListenerAdapter(this){
            final /* synthetic */ ChatAnalyticsMonitor a;
            {
                this.a = chatAnalyticsMonitor;
            }

            /*
             * Enabled aggressive block sorting
             */
            public void a(Chat chat, ChatMessage chatMessage, boolean bl) {
                if (bl || chat.v() || UserManager.a().f() != chatMessage.b()) {
                    return;
                }
                ChatAnalyticsMonitor.a(this.a, chat, true).a(chat, chatMessage);
            }

            /*
             * Enabled aggressive block sorting
             */
            public void c(Chat chat, ChatMessage chatMessage) {
                 chatMonitor;
                if (chat.v() || (chatMonitor = ChatAnalyticsMonitor.a(this.a, chat, false)) == null) {
                    return;
                }
                chatMonitor.b(chat, chatMessage);
            }
        };
    }

    private  a(Chat object, boolean bl) {
        String string2 = object.c();
         chatMonitor = this.b.get(string2);
        object = chatMonitor;
        if (chatMonitor == null) {
            object = chatMonitor;
            if (bl) {
                object = new Object(){
                    Map<Long, > a;
                    {
                        this.a = new HashMap<Long, >();
                    }

                    private  a(Chat chat, Long l) {
                         messagesSentCounter;
                         messagesSentCounter2 = messagesSentCounter = this.a.get(l);
                        if (messagesSentCounter == null) {
                            messagesSentCounter2 = new (chat, l){
                                Long e;
                                HashMap<ChatMessage.Type, Integer> f;
                                HashMap<ChatMessage.Type, Integer> g;
                                {
                                    super(chat);
                                    this.f = new HashMap();
                                    this.g = new HashMap();
                                    this.e = l;
                                }

                                @Override
                                protected void b() {
                                    if (this.f.isEmpty() && this.g.isEmpty()) {
                                        return;
                                    }
                                    String string2 = this.a(this.f);
                                    String string3 = this.a(this.g);
                                    ChatAnalytics.a(this.c, this.a, this.e, this.b, string2, string3);
                                }

                                @Override
                                protected void b( eventType, ChatMessage.Type type) {
                                    if (eventType == .a) {
                                        this.a(this.f, type);
                                        return;
                                    }
                                    if (eventType == .c) {
                                        this.a(this.g, type);
                                        return;
                                    }
                                    throw new RuntimeException("recordEvent with bad type");
                                }

                                @Override
                                protected void c() {
                                    this.f = new HashMap();
                                    this.g = new HashMap();
                                }
                            };
                            this.a.put(l, messagesSentCounter2);
                        }
                        return messagesSentCounter2;
                    }

                    private Set<> a(Chat chat) {
                        if (chat.a() == Chat.Type.a) {
                            return Collections.singleton(this.a(chat, ((PeerChat)chat).f()));
                        }
                        if (chat.a() == Chat.Type.b) {
                            Object object = ((GroupChat)chat).R();
                            HashSet<> hashSet = new HashSet<>();
                            long l = UserManager.a().f();
                            object = object.iterator();
                            while (object.hasNext()) {
                                Long l2 = (Long)object.next();
                                if (l2 == l) continue;
                                hashSet.add(this.a(chat, l2));
                            }
                            if (hashSet.isEmpty()) {
                                return Collections.singleton(this.a(chat, (Long)null));
                            }
                            return hashSet;
                        }
                        throw new RuntimeException("Invalid group type" + (Object)((Object)chat.a()));
                    }

                    private void b() {
                        Iterator<> iterator = this.a.values().iterator();
                        while (iterator.hasNext()) {
                            iterator.next().a();
                        }
                    }

                    void a() {
                        this.b();
                    }

                    void a(Chat object, ChatMessage chatMessage) {
                        if (chatMessage instanceof GroupInvitationChatMessage) {
                            ChatAnalytics.a((Chat)object, object.f());
                        }
                        object = this.a((Chat)object).iterator();
                        while (object.hasNext()) {
                            (object.next()).a(.a, chatMessage.a());
                        }
                    }

                    void b(Chat object, ChatMessage chatMessage) {
                        object = this.a((Chat)object).iterator();
                        while (object.hasNext()) {
                            (object.next()).a(.c, chatMessage.a());
                        }
                    }
                };
                if (this.b.size() > 1) {
                    Log.e("odietest", "chatmonitors.size: " + this.b.size());
                }
                this.b.put(string2, object);
            }
        }
        return object;
    }

    static /* synthetic */  a(ChatAnalyticsMonitor chatAnalyticsMonitor, Chat chat, boolean bl) {
        return chatAnalyticsMonitor.a(chat, bl);
    }

    public static ChatAnalyticsMonitor a() {
        if (a == null) {
            a = new ChatAnalyticsMonitor();
        }
        return a;
    }

    public void a(ChatManager chatManager) {
        chatManager.a(this.c);
        chatManager.a(this.d);
    }

    public void b() {
        Iterator<> iterator = this.b.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().a();
        }
        this.b.clear();
    }

}

