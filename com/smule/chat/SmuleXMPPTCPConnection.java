package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.packet.StreamError.Condition;
import org.jivesoftware.smack.sm.StreamManagementException.StreamManagementCounterError;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.dns.HostAddress;

public class SmuleXMPPTCPConnection extends XMPPTCPConnection {
    private static final String f18347x = SmuleXMPPTCPConnection.class.getName();
    private ChatManager f18348y;
    private Mechanisms f18349z;

    class C37641 extends AbstractConnectionListener {
        final /* synthetic */ SmuleXMPPTCPConnection f18346a;

        C37641(SmuleXMPPTCPConnection smuleXMPPTCPConnection) {
            this.f18346a = smuleXMPPTCPConnection;
        }

        public void m19674a(Exception exception) {
            if (exception instanceof StreamManagementCounterError) {
                Log.d(SmuleXMPPTCPConnection.f18347x, "resetting SM state due to " + exception);
                this.f18346a.m19675R();
            }
        }
    }

    public SmuleXMPPTCPConnection(XMPPTCPConnectionConfiguration xMPPTCPConnectionConfiguration, ChatManager chatManager) {
        super(xMPPTCPConnectionConfiguration);
        if (SmackConfiguration.a().startsWith("4.1.5 ")) {
            this.f18348y = chatManager;
            a(new C37641(this));
            return;
        }
        throw new IllegalStateException("check workarounds!");
    }

    protected synchronized void m19680a(String str, String str2, String str3) throws XMPPException, SmackException, IOException {
        super.a(str, MagicNetwork.a().k(), str3);
    }

    public synchronized AbstractXMPPConnection m19678a() throws SmackException, IOException, XMPPException {
        this.s = false;
        return super.a();
    }

    public <F extends ExtensionElement> F m19679a(String str, String str2) {
        F a = super.a(str, str2);
        if (!str.equals("mechanisms") || !str2.equals("urn:ietf:params:xml:ns:xmpp-sasl")) {
            return a;
        }
        if (a instanceof Mechanisms) {
            this.f18349z = (Mechanisms) a;
            return a;
        } else if (a == null) {
            return this.f18349z;
        } else {
            return a;
        }
    }

    private void m19675R() {
        a(new StreamErrorException(new StreamError(Condition.p, null, null, null)));
    }

    protected List<HostAddress> m19681b() {
        List<String> d = this.f18348y.d();
        this.t = new ArrayList(d.size());
        for (String hostAddress : d) {
            this.t.add(new HostAddress(hostAddress));
        }
        return new ArrayList();
    }
}
