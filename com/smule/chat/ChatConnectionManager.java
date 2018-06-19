package com.smule.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.smule.android.logging.EventLogger2;
import com.smule.android.logging.EventLogger2$ErrorDomain;
import com.smule.android.logging.Log;
import com.smule.android.logging.MagicCrittercism;
import com.smule.android.network.core.MagicNetwork;
import com.smule.chat.mam.MamManager;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SmackException.ConnectionException;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.filter.OrFilter;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.filter.StanzaTypeFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.packet.XMPPError;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.sasl.SASLError;
import org.jivesoftware.smack.sasl.SASLErrorException;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;
import org.jivesoftware.smack.util.dns.HostAddress;
import org.jivesoftware.smackx.delay.packet.DelayInformation;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;
import org.jivesoftware.smackx.time.EntityTimeManager;
import org.jivesoftware.smackx.time.packet.Time;
import org.jxmpp.util.XmppStringUtils;
import twitter4j.HttpResponseCode;

public class ChatConnectionManager implements ConnectionListener, PingFailedListener {
    private static final String f17999n = ChatConnectionManager.class.getName();
    private ChatManager$ConnectionStatus f18000a = ChatManager$ConnectionStatus.DISCONNECTED;
    private SmuleXMPPTCPConnection f18001b;
    private ChatStanzaQueue f18002c;
    private final Object f18003d = new Object();
    private OutgoingMessageTracker f18004e;
    private String f18005f;
    private boolean f18006g;
    private boolean f18007h;
    private long f18008i;
    private boolean f18009j;
    private boolean f18010k;
    private long f18011l;
    private ChatManager f18012m;

    class C36881 extends BroadcastReceiver {
        final /* synthetic */ ChatConnectionManager f17989a;

        C36881(ChatConnectionManager chatConnectionManager) {
            this.f17989a = chatConnectionManager;
        }

        public void onReceive(Context context, Intent intent) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            boolean z = activeNetworkInfo == null || !activeNetworkInfo.isConnected();
            this.f17989a.m19247c(z);
        }
    }

    class C36902 extends BroadcastReceiver {
        final /* synthetic */ ChatConnectionManager f17991a;

        class C36891 implements Runnable {
            final /* synthetic */ C36902 f17990a;

            C36891(C36902 c36902) {
                this.f17990a = c36902;
            }

            public void run() {
                if (this.f17990a.f17991a.m19256n()) {
                    this.f17990a.f17991a.m19264v();
                }
            }
        }

        C36902(ChatConnectionManager chatConnectionManager) {
            this.f17991a = chatConnectionManager;
        }

        public void onReceive(Context context, Intent intent) {
            PriorityExecutor.f18318a.execute(new C36891(this));
        }
    }

    class C36913 implements Runnable {
        final /* synthetic */ ChatConnectionManager f17992a;

        C36913(ChatConnectionManager chatConnectionManager) {
            this.f17992a = chatConnectionManager;
        }

        public void run() {
            this.f17992a.m19255m();
        }
    }

    class C36924 implements Runnable {
        final /* synthetic */ ChatConnectionManager f17993a;

        C36924(ChatConnectionManager chatConnectionManager) {
            this.f17993a = chatConnectionManager;
        }

        public void run() {
            synchronized (this.f17993a.f18003d) {
                this.f17993a.f18009j = false;
            }
            this.f17993a.m19254l();
        }
    }

    class C36945 implements StanzaListener {
        final /* synthetic */ ChatConnectionManager f17996a;

        C36945(ChatConnectionManager chatConnectionManager) {
            this.f17996a = chatConnectionManager;
        }

        public void m19239a(final Stanza stanza) {
            if ((stanza instanceof Message) && DelayInformation.a(stanza) == null) {
                Date date = new Date();
                date.setTime(date.getTime() + this.f17996a.f18008i);
                stanza.a(new DelayInformation(date));
            }
            this.f17996a.f18012m.b(new Runnable(this) {
                final /* synthetic */ C36945 f17995b;

                public void run() {
                    this.f17995b.f17996a.f18002c.m19414a(stanza);
                }
            });
        }
    }

    public ChatConnectionManager(ChatManager chatManager, Context context) {
        this.f18012m = chatManager;
        this.f18004e = new OutgoingMessageTracker(this, chatManager);
        this.f18002c = new ChatStanzaQueue(chatManager);
        context.registerReceiver(new C36881(this), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        context.registerReceiver(new C36902(this), new IntentFilter("android.intent.action.TIME_SET"));
    }

    public ChatManager$ConnectionStatus m19265a() {
        return this.f18006g ? ChatManager$ConnectionStatus.NO_NETWORK : this.f18000a;
    }

    public int m19275b() {
        return this.f18001b != null ? this.f18001b.D() : -1;
    }

    public String m19278c() {
        return this.f18005f;
    }

    public void m19274a(boolean z) {
        this.f18002c.m19415a(z);
    }

    public long m19279d() {
        return this.f18008i;
    }

    public String m19280e() {
        String g = this.f18001b.g();
        if (g != null) {
            return g;
        }
        List d = this.f18012m.d();
        if (d.size() > 0) {
            return (String) d.get(d.size() - 1);
        }
        return g;
    }

    public void m19277b(boolean z) {
        synchronized (this.f18003d) {
            if (z != this.f18007h) {
                this.f18007h = z;
                m19254l();
            }
        }
    }

    public boolean m19281f() {
        boolean z;
        synchronized (this.f18003d) {
            z = this.f18007h;
        }
        return z;
    }

    private boolean m19253k() {
        return (this.f18012m.i() == null || MagicNetwork.a().k() == null) ? false : true;
    }

    private void m19254l() {
        PriorityExecutor.f18318a.execute(new C36913(this));
    }

    private void m19255m() {
        synchronized (this.f18003d) {
            this.f18010k = true;
        }
        if (m19281f()) {
            m19258p();
        } else {
            m19260r();
        }
        synchronized (this.f18003d) {
            this.f18010k = false;
        }
    }

    private boolean m19256n() {
        boolean z;
        synchronized (this.f18003d) {
            z = this.f18001b != null && this.f18001b.l() && this.f18001b.m();
        }
        return z;
    }

    private boolean m19257o() {
        return m19265a() == ChatManager$ConnectionStatus.NO_HOSTS;
    }

    private void m19258p() {
        if (!m19256n() && m19261s()) {
            m19241a(ChatManager$ConnectionStatus.CONNECTING);
            if (m19253k()) {
                long nanoTime = System.nanoTime();
                Exception exception = null;
                Log.c(f17999n, "connecting");
                m19262t();
                try {
                    if (!this.f18001b.l()) {
                        this.f18001b.m19678a();
                    }
                } catch (Exception e) {
                    exception = e;
                    Log.c(f17999n, "failed to connect", exception);
                }
                if (this.f18001b.l() && !this.f18001b.m()) {
                    try {
                        this.f18001b.j();
                    } catch (SASLErrorException e2) {
                        exception = e2;
                        if (exception.a().a() == SASLError.j) {
                            Log.c(f17999n, "login failed; attempting relogin");
                            MagicNetwork.a().a(true);
                            try {
                                this.f18001b.j();
                            } catch (Exception e3) {
                                exception = e3;
                                Log.c(f17999n, "login failed after relogin", exception);
                            }
                        }
                    } catch (Exception e4) {
                        exception = e4;
                        Log.c(f17999n, "login failed", exception);
                    }
                }
                if (m19256n()) {
                    if (this.f18005f == null) {
                        try {
                            List a = ServiceDiscoveryManager.a(this.f18001b).a("http://jabber.org/protocol/muc", true, true);
                            if (!a.isEmpty()) {
                                this.f18005f = (String) a.get(0);
                            }
                        } catch (Throwable e5) {
                            Log.c(f17999n, "Network timeout", e5);
                        } catch (Throwable e52) {
                            Log.d(f17999n, "disco failure", e52);
                        }
                    }
                    m19264v();
                    m19241a(ChatManager$ConnectionStatus.CONNECTED);
                    return;
                }
                this.f18001b.v();
                m19242a(exception, (long) (((double) (System.nanoTime() - nanoTime)) / 1000000.0d));
                m19259q();
                return;
            }
            Log.e(f17999n, "can't connect - no session");
            m19259q();
        }
    }

    private void m19242a(Exception exception, long j) {
        Exception c;
        String condition;
        EventLogger2$ErrorDomain eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.NONE;
        int i = 0;
        String str = null;
        String str2 = null;
        String e = m19280e();
        if (exception instanceof ConnectionException) {
            List a = ((ConnectionException) exception).a();
            if (a.size() > 0) {
                HostAddress hostAddress = (HostAddress) a.get(a.size() - 1);
                e = hostAddress.a();
                if (hostAddress.c() != null) {
                    c = hostAddress.c();
                    if (c instanceof XMPPErrorException) {
                        XMPPError a2;
                        a2 = ((XMPPErrorException) c).a();
                        eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.CLIENT;
                        i = HttpResponseCode.MULTIPLE_CHOICES;
                        condition = a2.a().toString();
                        if (a2.c() != null) {
                            condition = condition + " (" + a2.c() + ")";
                        }
                        str2 = condition;
                    } else if (!(c instanceof SocketTimeoutException) || (c instanceof NoResponseException)) {
                        eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.CLIENT;
                        i = 100;
                        str2 = c.getLocalizedMessage();
                    } else if (c != null) {
                        eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.PLATFORM;
                        i = 0;
                        str = c.getClass().getName();
                        if (c.getLocalizedMessage() != null) {
                            str2 = c.getLocalizedMessage();
                        }
                    }
                    EventLogger2.a("xmpp", e, "/connect", j, 0, 0, eventLogger2$ErrorDomain, i, str, str2, null, false);
                }
            }
        }
        c = exception;
        if (c instanceof XMPPErrorException) {
            a2 = ((XMPPErrorException) c).a();
            eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.CLIENT;
            i = HttpResponseCode.MULTIPLE_CHOICES;
            condition = a2.a().toString();
            if (a2.c() != null) {
                condition = condition + " (" + a2.c() + ")";
            }
            str2 = condition;
        } else {
            if (c instanceof SocketTimeoutException) {
            }
            eventLogger2$ErrorDomain = EventLogger2$ErrorDomain.CLIENT;
            i = 100;
            str2 = c.getLocalizedMessage();
        }
        EventLogger2.a("xmpp", e, "/connect", j, 0, 0, eventLogger2$ErrorDomain, i, str, str2, null, false);
    }

    private void m19259q() {
        synchronized (this.f18003d) {
            if (!this.f18009j) {
                this.f18009j = true;
                Log.c(f17999n, "retrying connect in 15000");
                this.f18012m.a(15000, new C36924(this));
            }
        }
    }

    private void m19260r() {
        m19241a(ChatManager$ConnectionStatus.DISCONNECTED);
        if (m19256n()) {
            Log.c(f17999n, "disconnecting");
            this.f18001b.v();
        }
    }

    private boolean m19261s() {
        if (m19257o()) {
            return false;
        }
        List d = this.f18012m.d();
        if (d != null && d.size() != 0) {
            return true;
        }
        Log.e(f17999n, "no XMPP hosts configured");
        MagicCrittercism.a(new Exception("no XMPP hosts configured"));
        m19241a(ChatManager$ConnectionStatus.NO_HOSTS);
        m19277b(false);
        return false;
    }

    private void m19262t() {
        synchronized (this.f18003d) {
            if (this.f18001b == null) {
                String i = this.f18012m.i();
                String k = MagicNetwork.a().k();
                CharSequence a = XmppStringUtils.a(i);
                this.f18001b = new SmuleXMPPTCPConnection(((Builder) ((Builder) ((Builder) ((Builder) XMPPTCPConnectionConfiguration.t().a(a, k)).b(MagicNetwork.n())).a(XmppStringUtils.b(i))).a(true).a(SecurityMode.a)).c(), this.f18012m);
                this.f18001b.c(true);
                this.f18001b.d(true);
                this.f18001b.d(this.f18004e);
                this.f18001b.a(10000);
                this.f18001b.a(new C36945(this), new OrFilter(new StanzaFilter[]{StanzaTypeFilter.b, StanzaTypeFilter.a}));
                this.f18001b.a(this);
                MamManager.m19721a(this.f18001b);
                Roster.a(this.f18001b).a(false);
                EntityTimeManager.a(this.f18001b).b();
                PingManager a2 = PingManager.a(this.f18001b);
                a2.a(60);
                a2.a(this);
                ReconnectionManager.a(false);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m19241a(com.smule.chat.ChatManager$ConnectionStatus r20) {
        /*
        r19 = this;
        r0 = r19;
        r0 = r0.f18003d;
        r18 = r0;
        monitor-enter(r18);
        r0 = r19;
        r2 = r0.f18000a;	 Catch:{ all -> 0x0019 }
        r0 = r20;
        if (r0 != r2) goto L_0x0011;
    L_0x000f:
        monitor-exit(r18);	 Catch:{ all -> 0x0019 }
    L_0x0010:
        return;
    L_0x0011:
        r2 = r19.m19257o();	 Catch:{ all -> 0x0019 }
        if (r2 == 0) goto L_0x001c;
    L_0x0017:
        monitor-exit(r18);	 Catch:{ all -> 0x0019 }
        goto L_0x0010;
    L_0x0019:
        r2 = move-exception;
        monitor-exit(r18);	 Catch:{ all -> 0x0019 }
        throw r2;
    L_0x001c:
        r0 = r20;
        r1 = r19;
        r1.f18000a = r0;	 Catch:{ all -> 0x0019 }
        r2 = com.smule.chat.ChatManager$ConnectionStatus.CONNECTING;	 Catch:{ all -> 0x0019 }
        r0 = r20;
        if (r0 != r2) goto L_0x0042;
    L_0x0028:
        r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0019 }
        r0 = r19;
        r0.f18011l = r2;	 Catch:{ all -> 0x0019 }
    L_0x0030:
        monitor-exit(r18);	 Catch:{ all -> 0x0019 }
        r0 = r19;
        r2 = r0.f18012m;
        r3 = new com.smule.chat.ChatConnectionManager$6;
        r0 = r19;
        r1 = r20;
        r3.<init>(r0, r1);
        r2.b(r3);
        goto L_0x0010;
    L_0x0042:
        r2 = com.smule.chat.ChatManager$ConnectionStatus.CONNECTED;	 Catch:{ all -> 0x0019 }
        r0 = r20;
        if (r0 != r2) goto L_0x0030;
    L_0x0048:
        r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0019 }
        r0 = r19;
        r4 = r0.f18011l;	 Catch:{ all -> 0x0019 }
        r2 = r2 - r4;
        r2 = (double) r2;	 Catch:{ all -> 0x0019 }
        r4 = 4696837146684686336; // 0x412e848000000000 float:0.0 double:1000000.0;
        r2 = r2 / r4;
        r6 = (long) r2;	 Catch:{ all -> 0x0019 }
        r3 = "xmpp";
        r4 = r19.m19280e();	 Catch:{ all -> 0x0019 }
        r5 = "/connect";
        r8 = 0;
        r10 = 0;
        r12 = com.smule.android.logging.EventLogger2$ErrorDomain.NONE;	 Catch:{ all -> 0x0019 }
        r13 = 0;
        r14 = 0;
        r15 = 0;
        r16 = 0;
        r17 = 0;
        com.smule.android.logging.EventLogger2.a(r3, r4, r5, r6, r8, r10, r12, r13, r14, r15, r16, r17);	 Catch:{ all -> 0x0019 }
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.chat.ChatConnectionManager.a(com.smule.chat.ChatManager$ConnectionStatus):void");
    }

    private void m19247c(boolean z) {
        if (z != this.f18006g) {
            this.f18006g = z;
            if (this.f18001b != null) {
                this.f18012m.a(m19265a());
            }
        }
    }

    private void m19263u() {
        synchronized (this.f18003d) {
            if (this.f18010k) {
                return;
            }
            m19241a(ChatManager$ConnectionStatus.DISCONNECTED);
            m19254l();
        }
    }

    private void m19264v() {
        try {
            IQ time = new Time();
            time.g(XmppStringUtils.b(this.f18012m.i()));
            Time time2 = (Time) this.f18001b.a(time).d();
            if (time2 != null && time2.a() != null) {
                this.f18008i = time2.a().getTime() - new Date().getTime();
                Log.c(f17999n, "skew=" + this.f18008i + " ms");
            }
        } catch (Exception e) {
        }
    }

    public void m19273a(Stanza stanza) throws NotConnectedException {
        synchronized (this.f18003d) {
            if (this.f18001b == null) {
                throw new NotConnectedException();
            }
            this.f18001b.b(stanza);
        }
    }

    public void m19269a(Chat chat, ChatMessage chatMessage, Message message) {
        try {
            m19273a((Stanza) message);
            this.f18004e.m19641a(chat, chatMessage, message);
        } catch (NotConnectedException e) {
            chat.m19194a(chatMessage, ChatStatus.DELIVERY_FAILED);
        }
    }

    public PacketCollector m19267a(IQ iq) throws NotConnectedException {
        PacketCollector a;
        synchronized (this.f18003d) {
            if (this.f18001b == null) {
                throw new NotConnectedException();
            }
            a = this.f18001b.a(iq);
        }
        return a;
    }

    public PacketCollector m19266a(StanzaFilter stanzaFilter, Stanza stanza) throws NotConnectedException {
        PacketCollector a;
        synchronized (this.f18003d) {
            if (this.f18001b == null) {
                throw new NotConnectedException();
            }
            a = this.f18001b.a(stanzaFilter, stanza);
        }
        return a;
    }

    public MamManager m19282g() throws NotConnectedException {
        MamManager a;
        synchronized (this.f18003d) {
            if (this.f18001b == null) {
                throw new NotConnectedException();
            }
            a = MamManager.m19721a(this.f18001b);
        }
        return a;
    }

    public void m19271a(XMPPConnection xMPPConnection) {
    }

    public void m19272a(XMPPConnection xMPPConnection, boolean z) {
    }

    public void m19283h() {
        m19263u();
    }

    public void m19270a(Exception exception) {
        m19263u();
    }

    public void m19284i() {
    }

    public void m19268a(int i) {
    }

    public void m19276b(Exception exception) {
    }

    public void m19285j() {
        Log.d(f17999n, "ping failed - reconnecting");
        synchronized (this.f18003d) {
            this.f18010k = true;
            m19260r();
            m19254l();
            this.f18010k = false;
        }
    }
}
