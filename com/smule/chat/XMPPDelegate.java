/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.SharedPreferences
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 */
package com.smule.chat;

import android.content.Context;
import android.content.SharedPreferences;
import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.GroupInfo;
import com.smule.chat.mam.MamManager;
import com.smule.chat.smerialization.Smerializable;
import java.util.Collection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public interface XMPPDelegate {
    public long a(Message var1);

    public MamManager.MamQueryResult a(Chat var1, int var2, String var3) throws Exception;

    public PacketCollector a(StanzaFilter var1, Stanza var2) throws SmackException.NotConnectedException;

    public PacketCollector a(IQ var1) throws SmackException.NotConnectedException;

    public void a(long var1, Runnable var3);

    public void a(Chat.Options var1, ChatManager var2);

    public void a(Chat var1);

    public void a(Chat var1, Chat.Bucket var2);

    public void a(Chat var1, ChatMessage var2, Message var3);

    public void a(Smerializable var1);

    public void a(Collection<AccountIcon> var1);

    public void a(Stanza var1) throws SmackException.NotConnectedException;

    public void b(Smerializable var1);

    public void b(Runnable var1);

    public long c(String var1);

    public GroupInfo d(String var1);

    public Chat.Bucket e();

    public boolean g();

    public String i();

    public long j();

    public long k();

    public Context l();

    public SharedPreferences m();
}

