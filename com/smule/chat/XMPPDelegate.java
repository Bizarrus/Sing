package com.smule.chat;

import com.smule.android.network.models.AccountIcon;
import com.smule.chat.Chat.Bucket;
import com.smule.chat.Chat.Options;
import com.smule.chat.ChatManager.ChatCallback;
import com.smule.chat.mam.MamManager.MamQueryResult;
import com.smule.chat.smerialization.Smerializable;
import java.util.Collection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;

public interface XMPPDelegate {
    long mo4052a(Message message);

    MamQueryResult mo4053a(Chat chat, int i, String str) throws Exception;

    PacketCollector mo4054a(StanzaFilter stanzaFilter, Stanza stanza) throws NotConnectedException;

    PacketCollector mo4055a(IQ iq) throws NotConnectedException;

    void mo4056a(long j, Runnable runnable);

    void mo4057a(Options options, ChatCallback chatCallback);

    void mo4058a(Chat chat);

    void mo4059a(Chat chat, Bucket bucket);

    void mo4060a(Chat chat, ChatMessage chatMessage, Message message);

    void mo4061a(Smerializable smerializable);

    void mo4062a(Collection<AccountIcon> collection);

    void mo4063a(Stanza stanza) throws NotConnectedException;

    void mo4064b(Smerializable smerializable);

    void mo4065b(Runnable runnable);

    long mo4066c(String str);

    GroupInfo mo4067d(String str);

    Bucket mo4068e();

    boolean mo4069g();

    String mo4070i();

    long mo4071j();

    long mo4072k();
}
