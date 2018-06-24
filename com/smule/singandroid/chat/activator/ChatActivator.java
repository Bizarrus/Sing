/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 */
package com.smule.singandroid.chat.activator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.smule.chat.Chat;
import com.smule.chat.ChatStatus;
import com.smule.chat.GroupChat;
import com.smule.chat.PeerChat;
import com.smule.singandroid.chat.activator.ChatActivatorInner;

public class ChatActivator
implements Parcelable {
    public static final Parcelable.Creator<ChatActivator> CREATOR = new Parcelable.Creator<ChatActivator>(){

        public ChatActivator a(Parcel parcel) {
            return new ChatActivator(parcel);
        }

        public ChatActivator[] a(int n) {
            return new ChatActivator[n];
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return this.a(parcel);
        }

        public /* synthetic */ Object[] newArray(int n) {
            return this.a(n);
        }
    };
    protected Chat a;
    protected String b;
    protected String c;
    private ChatActivatorInner d;

    public ChatActivator() {
    }

    protected ChatActivator(Parcel parcel) {
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    /*
     * Enabled aggressive block sorting
     */
    static ChatActivator a(Chat chat) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.a = chat;
        if (chat instanceof PeerChat) {
            chatActivator.b = chat.c();
            return chatActivator;
        } else {
            if (!(chat instanceof GroupChat)) return chatActivator;
            {
                chatActivator.c = chat.c();
                return chatActivator;
            }
        }
    }

    static ChatActivator a(String string2) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.b = string2;
        return chatActivator;
    }

    static ChatActivator b(String string2) {
        ChatActivator chatActivator = new ChatActivator();
        chatActivator.c = string2;
        return chatActivator;
    }

    public void a() {
        if (this.d != null) {
            this.d.c();
            this.d.b();
            this.d = null;
        }
    }

    public void a(Context context, ChatActivatorInterface chatActivatorInterface) {
        this.d = new ChatActivatorInner(context, this.a, this.b, this.c, chatActivatorInterface);
        this.d.a();
    }

    public void b() {
        this.d.d();
    }

    public String c() {
        if (this.b != null) {
            return this.b;
        }
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    public static interface ChatActivatorInterface {
        public void a(Chat var1, ChatStatus var2);

        public void a(ChatStatus var1);

        public void b(Chat var1);

        public void c(Chat var1);

        public void w_();

        public void y_();
    }

    public static class ChatActivatorListener
    implements ChatActivatorInterface {
        @Override
        public void a(Chat chat, ChatStatus chatStatus) {
        }

        @Override
        public void a(ChatStatus chatStatus) {
        }

        @Override
        public void b(Chat chat) {
        }

        @Override
        public void c(Chat chat) {
        }

        @Override
        public void w_() {
        }

        @Override
        public void y_() {
        }
    }

}

