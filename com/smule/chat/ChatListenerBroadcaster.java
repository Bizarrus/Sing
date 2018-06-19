package com.smule.chat;

import com.smule.chat.Chat.ChatState;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class ChatListenerBroadcaster implements ChatListener, ChatManagerListener {
    private ArrayList<WeakReference<ChatListener>> f18018a = new ArrayList();

    ChatListenerBroadcaster() {
    }

    void m19312a(ChatListener chatListener) {
        this.f18018a.add(new WeakReference(chatListener));
    }

    void m19317b(ChatListener chatListener) {
        for (int i = 0; i < this.f18018a.size(); i++) {
            if (((WeakReference) this.f18018a.get(i)).get() == chatListener) {
                this.f18018a.remove(i);
                return;
            }
        }
    }

    private List<WeakReference<ChatListener>> m19306b() {
        return new ArrayList(this.f18018a);
    }

    public void mo6319a() {
    }

    public void mo6322e(Chat chat) {
        chat.m19192a((ChatListener) this);
    }

    public void mo6323f(Chat chat) {
        chat.m19205b((ChatListener) this);
    }

    public void mo6320a(ChatManager$ConnectionStatus chatManager$ConnectionStatus) {
    }

    public void mo6324g(Chat chat) {
    }

    public void mo6325h(Chat chat) {
    }

    public void mo6321a(GroupChat groupChat) {
    }

    public void m19311a(Chat chat, ChatMessage chatMessage, boolean z) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(chat, chatMessage, z);
            }
        }
    }

    public void m19310a(Chat chat, ChatMessage chatMessage) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(chat, chatMessage);
            }
        }
    }

    public void m19316b(Chat chat, ChatMessage chatMessage) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.b(chat, chatMessage);
            }
        }
    }

    public void m19319c(Chat chat, ChatMessage chatMessage) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.c(chat, chatMessage);
            }
        }
    }

    public void m19308a(Chat chat) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(chat);
            }
        }
    }

    public void m19315b(Chat chat) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.b(chat);
            }
        }
    }

    public void m19318c(Chat chat) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.c(chat);
            }
        }
    }

    public void m19320d(Chat chat) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.d(chat);
            }
        }
    }

    public void m19309a(Chat chat, ChatState chatState) {
        for (WeakReference weakReference : m19306b()) {
            ChatListener chatListener = (ChatListener) weakReference.get();
            if (chatListener != null) {
                chatListener.a(chat, chatState);
            }
        }
    }
}
