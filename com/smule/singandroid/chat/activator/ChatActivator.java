package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;

public class ChatActivator implements Parcelable {
    public static final Creator<ChatActivator> CREATOR = new C43421();
    protected Chat f21271a;
    protected String f21272b;
    protected String f21273c;
    private ChatActivatorInner f21274d;

    public interface ChatActivatorInterface {
        void mo6550a(Chat chat, ChatStatus chatStatus);

        void mo6551a(ChatStatus chatStatus);

        void a_(Chat chat);

        void mo6553c(Chat chat);

        void s_();

        void t_();
    }

    public static class ChatActivatorListener implements ChatActivatorInterface {
        public void s_() {
        }

        public void mo6551a(ChatStatus chatStatus) {
        }

        public void a_(Chat chat) {
        }

        public void mo6553c(Chat chat) {
        }

        public void mo6550a(Chat chat, ChatStatus chatStatus) {
        }

        public void t_() {
        }
    }

    static class C43421 implements Creator<ChatActivator> {
        C43421() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m22927a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m22928a(i);
        }

        public ChatActivator m22927a(Parcel parcel) {
            return new ChatActivator(parcel);
        }

        public ChatActivator[] m22928a(int i) {
            return new ChatActivator[i];
        }
    }

    static ChatActivator m22929a(Chat chat) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.f21271a = chat;
        if (chat instanceof PeerChat) {
            chatActivator.f21272b = chat.m19209c();
        } else if (chat instanceof GroupChat) {
            chatActivator.f21273c = chat.m19209c();
        }
        return chatActivator;
    }

    static ChatActivator m22930a(String str) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.f21272b = str;
        return chatActivator;
    }

    static ChatActivator m22931b(String str) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.f21273c = str;
        return chatActivator;
    }

    protected ChatActivator(Parcel parcel) {
        this.f21272b = parcel.readString();
        this.f21273c = parcel.readString();
    }

    public void m22933a(Context context, ChatActivatorInterface chatActivatorInterface) {
        this.f21274d = new ChatActivatorInner(context, this.f21271a, this.f21272b, this.f21273c, chatActivatorInterface);
        this.f21274d.m22965a();
    }

    public void m22932a() {
        if (this.f21274d != null) {
            this.f21274d.m22969c();
            this.f21274d.m22967b();
            this.f21274d = null;
        }
    }

    public void m22934b() {
        this.f21274d.m22970d();
    }

    public String m22935c() {
        return this.f21272b != null ? this.f21272b : this.f21273c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f21272b);
        parcel.writeString(this.f21273c);
    }

    public int describeContents() {
        return 0;
    }
}
