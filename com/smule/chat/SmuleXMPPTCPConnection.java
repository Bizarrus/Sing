/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.AbstractConnectionListener
 *  org.jivesoftware.smack.AbstractXMPPConnection
 *  org.jivesoftware.smack.ConnectionListener
 *  org.jivesoftware.smack.SmackConfiguration
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$StreamErrorException
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Mechanisms
 *  org.jivesoftware.smack.packet.StreamError
 *  org.jivesoftware.smack.packet.StreamError$Condition
 *  org.jivesoftware.smack.sm.StreamManagementException
 *  org.jivesoftware.smack.sm.StreamManagementException$StreamManagementCounterError
 *  org.jivesoftware.smack.tcp.XMPPTCPConnection
 *  org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration
 *  org.jivesoftware.smack.util.dns.HostAddress
 */
package com.smule.chat;

import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.chat.ChatManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Mechanisms;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.sm.StreamManagementException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.util.dns.HostAddress;

public class SmuleXMPPTCPConnection
extends XMPPTCPConnection {
    private static final String x = SmuleXMPPTCPConnection.class.getName();
    private ChatManager y;
    private Mechanisms z;

    public SmuleXMPPTCPConnection(XMPPTCPConnectionConfiguration xMPPTCPConnectionConfiguration, ChatManager chatManager) {
        super(xMPPTCPConnectionConfiguration);
        if (!SmackConfiguration.a().startsWith("4.1.5 ")) {
            throw new IllegalStateException("check workarounds!");
        }
        this.y = chatManager;
        this.a((ConnectionListener)new AbstractConnectionListener(){

            public void a(Exception exception) {
                if (exception instanceof StreamManagementException.StreamManagementCounterError) {
                    Log.d(x, "resetting SM state due to " + exception);
                    SmuleXMPPTCPConnection.this.R();
                }
            }
        });
    }

    private void R() {
        this.a((Exception)new XMPPException.StreamErrorException(new StreamError(StreamError.Condition.p, null, null, null)));
    }

    public AbstractXMPPConnection a() throws SmackException, IOException, XMPPException {
        synchronized (this) {
            this.s = false;
            AbstractXMPPConnection abstractXMPPConnection = super.a();
            return abstractXMPPConnection;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public <F extends ExtensionElement> F a(String string2, String string3) {
        ExtensionElement extensionElement = super.a(string2, string3);
        if (!string2.equals("mechanisms") || !string3.equals("urn:ietf:params:xml:ns:xmpp-sasl")) return (F)extensionElement;
        {
            if (extensionElement instanceof Mechanisms) {
                this.z = (Mechanisms)extensionElement;
                return (F)extensionElement;
            } else {
                if (extensionElement != null) return (F)extensionElement;
                return (F)this.z;
            }
        }
    }

    protected void a(String string2, String string3, String string4) throws XMPPException, SmackException, IOException {
        synchronized (this) {
            super.a(string2, MagicNetwork.a().k(), string4);
            return;
        }
    }

    protected List<HostAddress> b() {
        Object object = this.y.d();
        this.t = new ArrayList(object.size());
        object = object.iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            this.t.add(new HostAddress(string2));
        }
        return new ArrayList<HostAddress>();
    }

}

