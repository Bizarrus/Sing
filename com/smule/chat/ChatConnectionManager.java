/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.content.BroadcastReceiver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.util.Base64
 *  org.jivesoftware.smack.AbstractXMPPConnection
 *  org.jivesoftware.smack.ConnectionConfiguration
 *  org.jivesoftware.smack.ConnectionConfiguration$Builder
 *  org.jivesoftware.smack.ConnectionConfiguration$SecurityMode
 *  org.jivesoftware.smack.ConnectionListener
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.ReconnectionManager
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$ConnectionException
 *  org.jivesoftware.smack.SmackException$NoResponseException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.StanzaListener
 *  org.jivesoftware.smack.XMPPConnection
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$XMPPErrorException
 *  org.jivesoftware.smack.filter.OrFilter
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.filter.StanzaTypeFilter
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smack.packet.XMPPError
 *  org.jivesoftware.smack.packet.XMPPError$Condition
 *  org.jivesoftware.smack.roster.Roster
 *  org.jivesoftware.smack.sasl.SASLError
 *  org.jivesoftware.smack.sasl.SASLErrorException
 *  org.jivesoftware.smack.sasl.packet.SaslStreamElements
 *  org.jivesoftware.smack.sasl.packet.SaslStreamElements$SASLFailure
 *  org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
 *  org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration$Builder
 *  org.jivesoftware.smack.util.dns.HostAddress
 *  org.jivesoftware.smackx.delay.packet.DelayInformation
 *  org.jivesoftware.smackx.disco.ServiceDiscoveryManager
 *  org.jivesoftware.smackx.ping.PingFailedListener
 *  org.jivesoftware.smackx.ping.PingManager
 *  org.jivesoftware.smackx.time.EntityTimeManager
 *  org.jivesoftware.smackx.time.packet.Time
 *  org.jxmpp.util.XmppStringUtils
 */
package com.smule.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.chat.Chat;
import com.smule.chat.ChatManager;
import com.smule.chat.ChatMessage;
import com.smule.chat.ChatStanzaQueue;
import com.smule.chat.ChatStatus;
import com.smule.chat.OutgoingMessageTracker;
import com.smule.chat.PriorityExecutor;
import com.smule.chat.SmuleXMPPTCPConnection;
import com.smule.chat.XMPPDelegate;
import com.smule.chat.mam.MamManager;
import java.net.SocketTimeoutException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.sasl.SASLError;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.sasl.packet.SaslStreamElements;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;
import org.jivesoftware.smackx.time.EntityTimeManager;
import org.jivesoftware.smackx.time.packet.Time;
import org.jxmpp.util.XmppStringUtils;

public class ChatConnectionManager
implements ConnectionListener,
PingFailedListener {
    private static final String n = ChatConnectionManager.class.getName();
    private ChatManager a;
    private SmuleXMPPTCPConnection b;
    private ChatStanzaQueue c;
    private final Object d;
    private OutgoingMessageTracker e;
    private String f;
    private boolean g;
    private boolean h;
    private long i;
    private boolean j;
    private boolean k;
    private long l;
    private ChatManager m;

    ChatConnectionManager(ChatManager chatManager, Context context) {
        this.m = chatManager;
        this.d = new Object();
        this.a = ChatManager.c;
        this.e = new OutgoingMessageTracker(this, chatManager);
        this.c = new ChatStanzaQueue(chatManager);
        context.registerReceiver(new BroadcastReceiver(){

            /*
             * Enabled aggressive block sorting
             */
            public void onReceive(Context context, Intent intent) {
                if ((context = (ConnectivityManager)context.getSystemService("connectivity")) != null) {
                    boolean bl = (context = context.getActiveNetworkInfo()) == null || !context.isConnected();
                    ChatConnectionManager.this.c(bl);
                }
            }
        }, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        context.registerReceiver(new BroadcastReceiver(){

            public void onReceive(Context context, Intent intent) {
                PriorityExecutor.a.execute(new Runnable(){

                    @Override
                    public void run() {
                        if (ChatConnectionManager.this.n()) {
                            ChatConnectionManager.this.w();
                        }
                    }
                });
            }

        }, new IntentFilter("android.intent.action.TIME_SET"));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void a(final ChatManager connectionStatus) {
        Object object = this.d;
        synchronized (object) {
            if (connectionStatus == this.a) {
                return;
            }
            if (this.o()) {
                return;
            }
            this.a = connectionStatus;
            if (connectionStatus == ChatManager.d) {
                this.l = System.nanoTime();
            } else if (connectionStatus == ChatManager.e) {
                long l = (long)((double)(System.nanoTime() - this.l) / 1000000.0);
                com.smule.android.logging.EventLogger2.a("xmpp", this.e(), "/connect", l, 0, 0, EventLogger2.a, 0, null, null, null, false);
            }
        }
        this.m.b(new Runnable(){

            @Override
            public void run() {
                ChatConnectionManager.this.m.a(connectionStatus);
            }
        });
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(Exception var1_1, long var2_2) {
        var11_3 = EventLogger2.a;
        var4_4 = 0;
        var9_5 = null;
        var10_6 = null;
        var7_7 = this.e();
        var6_8 = var7_7;
        if (!(var1_1 instanceof SmackException.ConnectionException)) ** GOTO lbl-1000
        var8_9 = ((SmackException.ConnectionException)var1_1).a();
        var6_8 = var7_7;
        if (var8_9.size() <= 0) ** GOTO lbl-1000
        var8_9 = (HostAddress)var8_9.get(var8_9.size() - 1);
        var7_7 = var8_9.a();
        var6_8 = var7_7;
        if (var8_9.c() != null) {
            var8_9 = var8_9.c();
        } else lbl-1000: // 3 sources:
        {
            var8_9 = var1_1;
            var7_7 = var6_8;
        }
        if (var8_9 instanceof XMPPException.XMPPErrorException) {
            var10_6 = ((XMPPException.XMPPErrorException)var8_9).a();
            var8_9 = EventLogger2.d;
            var4_4 = 300;
            var1_1 = var6_8 = var10_6.a().toString();
            if (var10_6.c() != null) {
                var1_1 = (String)var6_8 + " (" + var10_6.c() + ")";
            }
            var6_8 = var1_1;
            var1_1 = var8_9;
        } else if (var8_9 instanceof SocketTimeoutException || var8_9 instanceof SmackException.NoResponseException) {
            var1_1 = EventLogger2.d;
            var4_4 = 100;
            var6_8 = var8_9.getLocalizedMessage();
        } else {
            var1_1 = var11_3;
            var6_8 = var10_6;
            if (var8_9 != null) {
                var11_3 = EventLogger2.e;
                var5_10 = 0;
                var12_11 = var8_9.getClass().getName();
                var1_1 = var11_3;
                var4_4 = var5_10;
                var9_5 = var12_11;
                var6_8 = var10_6;
                if (var8_9.getLocalizedMessage() != null) {
                    var6_8 = var8_9.getLocalizedMessage();
                    var1_1 = var11_3;
                    var4_4 = var5_10;
                    var9_5 = var12_11;
                }
            }
        }
        com.smule.android.logging.EventLogger2.a("xmpp", var7_7, "/connect", var2_2, 0, 0, var1_1, var4_4, var9_5, (String)var6_8, null, false);
    }

    private void c(boolean bl) {
        if (bl != this.g) {
            this.g = bl;
            if (this.b != null) {
                this.m.a(this.a());
            }
        }
    }

    private boolean k() {
        if (this.m.i() != null && MagicNetwork.a().k() != null) {
            return true;
        }
        return false;
    }

    private void l() {
        PriorityExecutor.a.execute(new Runnable(){

            @Override
            public void run() {
                ChatConnectionManager.this.m();
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void m() {
        Object object = this.d;
        // MONITORENTER : object
        this.k = true;
        // MONITOREXIT : object
        if (this.f()) {
            this.p();
        } else {
            this.r();
        }
        object = this.d;
        // MONITORENTER : object
        this.k = false;
        // MONITOREXIT : object
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean n() {
        Object object = this.d;
        synchronized (object) {
            if (this.b == null) return false;
            if (!this.b.l()) return false;
            if (!this.b.m()) return false;
            return true;
        }
    }

    private boolean o() {
        if (this.a() == ChatManager.a) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void p() {
        if (this.n() || !this.s()) {
            return;
        }
        this.a(ChatManager.d);
        if (!this.k()) {
            Log.e(n, "can't connect - no session");
            this.q();
            return;
        }
        long l = System.nanoTime();
        Object object = null;
        Log.c(n, "connecting");
        this.t();
        ServiceDiscoveryManager serviceDiscoveryManager = object;
        try {
            if (!this.b.l()) {
                this.b.a();
                serviceDiscoveryManager = object;
            }
        }
        catch (Exception exception) {
            Log.c(n, "failed to connect", exception);
        }
        object = serviceDiscoveryManager;
        if (this.b.l()) {
            object = serviceDiscoveryManager;
            if (!this.b.m()) {
                try {
                    this.b.j();
                    object = serviceDiscoveryManager;
                }
                catch (SASLErrorException sASLErrorException) {
                    object = sASLErrorException;
                    if (sASLErrorException.a().a() == SASLError.j) {
                        Log.c(n, "login failed; attempting relogin");
                        MagicNetwork.a().a(true);
                        try {
                            this.b.j();
                            object = sASLErrorException;
                        }
                        catch (Exception exception) {
                            Log.c(n, "login failed after relogin", exception);
                        }
                    }
                }
                catch (Exception exception) {
                    Log.c(n, "login failed", exception);
                }
            }
        }
        if (!this.n()) {
            this.b.v();
            this.a((Exception)object, (long)((double)(System.nanoTime() - l) / 1000000.0));
            this.q();
            return;
        }
        if (this.f == null) {
            object = ServiceDiscoveryManager.a((XMPPConnection)this.b);
            try {
                object = object.a("http://jabber.org/protocol/muc", true, true);
                if (!object.isEmpty()) {
                    this.f = (String)object.get(0);
                }
            }
            catch (SmackException.NoResponseException noResponseException) {
                Log.c(n, "Network timeout", (Throwable)noResponseException);
            }
            catch (Exception exception) {
                Log.d(n, "disco failure", exception);
            }
        }
        this.w();
        this.a(ChatManager.e);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void q() {
        Object object = this.d;
        synchronized (object) {
            if (!this.j) {
                this.j = true;
                Log.c(n, "retrying connect in 15000");
                Runnable runnable = new Runnable(){

                    /*
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     */
                    @Override
                    public void run() {
                        Object object = ChatConnectionManager.this.d;
                        synchronized (object) {
                            ChatConnectionManager.this.j = false;
                        }
                        ChatConnectionManager.this.l();
                    }
                };
                this.m.a(15000, runnable);
            }
            return;
        }
    }

    private void r() {
        this.a(ChatManager.c);
        if (!this.n()) {
            return;
        }
        Log.c(n, "disconnecting");
        this.b.v();
    }

    private boolean s() {
        if (this.o()) {
            return false;
        }
        List<String> list = this.m.d();
        if (list == null || list.size() == 0) {
            Log.e(n, "no XMPP hosts configured");
            MagicCrittercism.a(new Exception("no XMPP hosts configured"));
            this.a(ChatManager.a);
            this.b(false);
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void t() {
        Object object = this.d;
        synchronized (object) {
            if (this.b == null) {
                String string2 = this.m.i();
                String string3 = MagicNetwork.a().k();
                String string4 = XmppStringUtils.a((String)string2);
                string2 = XmppStringUtils.b((String)string2);
                this.b = new SmuleXMPPTCPConnection(((XMPPTCPConnectionConfiguration.Builder)((XMPPTCPConnectionConfiguration.Builder)((XMPPTCPConnectionConfiguration.Builder)((XMPPTCPConnectionConfiguration.Builder)XMPPTCPConnectionConfiguration.t().a((CharSequence)string4, string3)).b(this.u())).a(string2)).a(true).a(ConnectionConfiguration.SecurityMode.a)).c(), this.m);
                this.b.c(true);
                this.b.d(true);
                this.b.d((StanzaListener)this.e);
                this.b.a(10000);
                this.b.a(new StanzaListener(){

                    public void a(final Stanza stanza) {
                        if (stanza instanceof Message && DelayInformation.a((Stanza)stanza) == null) {
                            Date date = new Date();
                            date.setTime(date.getTime() + ChatConnectionManager.this.i);
                            stanza.a((ExtensionElement)new DelayInformation(date));
                        }
                        ChatConnectionManager.this.m.b(new Runnable(){

                            @Override
                            public void run() {
                                ChatConnectionManager.this.c.a(stanza);
                            }
                        });
                    }

                }, (StanzaFilter)new OrFilter(new StanzaFilter[]{StanzaTypeFilter.b, StanzaTypeFilter.a}));
                this.b.a((ConnectionListener)this);
                MamManager.a((XMPPConnection)this.b);
                Roster.a((XMPPConnection)this.b).a(false);
                EntityTimeManager.a((XMPPConnection)this.b).b();
                string3 = PingManager.a((XMPPConnection)this.b);
                string3.a(60);
                string3.a((PingFailedListener)this);
                ReconnectionManager.a((boolean)false);
            }
            return;
        }
    }

    private String u() {
        SharedPreferences sharedPreferences = this.m.m();
        byte[] arrby = sharedPreferences.getString("chat.resource", null);
        Object object = arrby;
        if (arrby == null) {
            object = new byte[16];
            new SecureRandom().nextBytes((byte[])object);
            object = Base64.encodeToString((byte[])object, (int)3);
            sharedPreferences.edit().putString("chat.resource", (String)object).apply();
        }
        return object;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void v() {
        Object object = this.d;
        synchronized (object) {
            if (this.k) {
                return;
            }
        }
        this.a(ChatManager.c);
        this.l();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void w() {
        Time time;
        try {
            time = new Time();
            time.g(XmppStringUtils.b((String)this.m.i()));
            time = (Time)this.b.a((IQ)time).d();
            if (time == null) return;
        }
        catch (Exception exception) {
            return;
        }
        if (time.a() == null) return;
        this.i = time.a().getTime() - new Date().getTime();
        Log.c(n, "skew=" + this.i + " ms");
    }

    public ChatManager a() {
        if (this.g) {
            return ChatManager.b;
        }
        return this.a;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public PacketCollector a(StanzaFilter stanzaFilter, Stanza stanza) throws SmackException.NotConnectedException {
        Object object = this.d;
        synchronized (object) {
            if (this.b != null) return this.b.a(stanzaFilter, stanza);
            throw new SmackException.NotConnectedException();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public PacketCollector a(IQ iQ) throws SmackException.NotConnectedException {
        Object object = this.d;
        synchronized (object) {
            if (this.b != null) return this.b.a(iQ);
            throw new SmackException.NotConnectedException();
        }
    }

    public void a(int n) {
    }

    public void a(Chat chat, ChatMessage chatMessage, Message message) {
        try {
            this.a((Stanza)message);
            this.e.a(chat, chatMessage, message);
            return;
        }
        catch (SmackException.NotConnectedException notConnectedException) {
            chat.a(chatMessage, ChatStatus.h);
            return;
        }
    }

    public void a(Exception exception) {
        this.v();
    }

    public void a(XMPPConnection xMPPConnection) {
    }

    public void a(XMPPConnection xMPPConnection, boolean bl) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void a(Stanza stanza) throws SmackException.NotConnectedException {
        Object object = this.d;
        synchronized (object) {
            if (this.b == null) {
                throw new SmackException.NotConnectedException();
            }
            this.b.b(stanza);
            return;
        }
    }

    public void a(boolean bl) {
        this.c.a(bl);
    }

    public int b() {
        if (this.b != null) {
            return this.b.D();
        }
        return -1;
    }

    public void b(Exception exception) {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void b(boolean bl) {
        Object object = this.d;
        synchronized (object) {
            if (bl != this.h) {
                this.h = bl;
                this.l();
            }
            return;
        }
    }

    public String c() {
        return this.f;
    }

    public long d() {
        return this.i;
    }

    public String e() {
        String string2;
        String string3 = string2 = this.b.g();
        if (string2 == null) {
            List<String> list = this.m.d();
            string3 = string2;
            if (list.size() > 0) {
                string3 = list.get(list.size() - 1);
            }
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean f() {
        Object object = this.d;
        synchronized (object) {
            return this.h;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public MamManager g() throws SmackException.NotConnectedException {
        Object object = this.d;
        synchronized (object) {
            if (this.b != null) return MamManager.a((XMPPConnection)this.b);
            throw new SmackException.NotConnectedException();
        }
    }

    public void h() {
        this.v();
    }

    public void i() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void j() {
        Log.d(n, "ping failed - reconnecting");
        Object object = this.d;
        synchronized (object) {
            this.k = true;
            this.r();
            this.l();
            this.k = false;
            return;
        }
    }

}

