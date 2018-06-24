/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.ConnectionCreationListener
 *  org.jivesoftware.smack.Manager
 *  org.jivesoftware.smack.PacketCollector
 *  org.jivesoftware.smack.PacketCollector$Configuration
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.SmackException$NoResponseException
 *  org.jivesoftware.smack.SmackException$NotConnectedException
 *  org.jivesoftware.smack.XMPPConnection
 *  org.jivesoftware.smack.XMPPConnectionRegistry
 *  org.jivesoftware.smack.XMPPException
 *  org.jivesoftware.smack.XMPPException$XMPPErrorException
 *  org.jivesoftware.smack.filter.StanzaFilter
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.IQ$Type
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.Stanza
 *  org.jivesoftware.smackx.disco.ServiceDiscoveryManager
 *  org.jivesoftware.smackx.forward.packet.Forwarded
 *  org.jivesoftware.smackx.rsm.packet.RSMSet
 *  org.jivesoftware.smackx.rsm.packet.RSMSet$PageDirection
 *  org.jivesoftware.smackx.xdata.FormField
 *  org.jivesoftware.smackx.xdata.FormField$Type
 *  org.jivesoftware.smackx.xdata.packet.DataForm
 *  org.jivesoftware.smackx.xdata.packet.DataForm$Type
 *  org.jxmpp.util.XmppDateTime
 */
package com.smule.chat.mam;

import com.smule.chat.mam.filter.MamMessageFinFilter;
import com.smule.chat.mam.filter.MamMessageResultFilter;
import com.smule.chat.mam.packet.MamPacket;
import com.smule.chat.mam.packet.MamQueryIQ;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.Manager;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppDateTime;

public class MamManager
extends Manager {
    private static final Map<XMPPConnection, MamManager> b;

    static {
        XMPPConnectionRegistry.a((ConnectionCreationListener)new ConnectionCreationListener(){

            public void a(XMPPConnection xMPPConnection) {
                MamManager.a(xMPPConnection);
            }
        });
        b = new WeakHashMap<XMPPConnection, MamManager>();
    }

    private MamManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        ServiceDiscoveryManager.a((XMPPConnection)xMPPConnection).b("urn:xmpp:mam:0");
    }

    private MamQueryResult a(MamQueryIQ mamQueryIQ, long l) throws SmackException.NoResponseException, XMPPException.XMPPErrorException, SmackException.NotConnectedException {
        Object object;
        Object object2;
        block5 : {
            Message message;
            if (l < 0) {
                throw new IllegalArgumentException("extra timeout must be zero or positive");
            }
            object2 = this.a();
            object = object2.a((StanzaFilter)new MamMessageFinFilter(mamQueryIQ));
            PacketCollector packetCollector = object2.a(PacketCollector.f().a((StanzaFilter)new MamMessageResultFilter(mamQueryIQ)).a((PacketCollector)object));
            object2.a((IQ)mamQueryIQ).d();
            object2 = MamPacket.MamFinExtension.a((Message)object.b(object2.y() + l));
            object = new ArrayList(packetCollector.e());
            while ((message = (Message)packetCollector.c()) != null) {
                object.add(MamPacket.MamResultExtension.a(message).e());
            }
            break block5;
            finally {
                packetCollector.a();
                object.a();
            }
        }
        return new MamQueryResult((List)object, (MamPacket.MamFinExtension)object2, DataForm.a((Stanza)mamQueryIQ));
    }

    public static MamManager a(XMPPConnection xMPPConnection) {
        synchronized (MamManager.class) {
            MamManager mamManager;
            block5 : {
                MamManager mamManager2;
                mamManager = mamManager2 = b.get((Object)xMPPConnection);
                if (mamManager2 != null) break block5;
                mamManager = new MamManager(xMPPConnection);
                b.put(xMPPConnection, mamManager);
            }
            return mamManager;
            finally {
            }
        }
    }

    private static DataForm b() {
        FormField formField = new FormField("FORM_TYPE");
        formField.a(FormField.Type.c);
        formField.c("urn:xmpp:mam:0");
        DataForm dataForm = new DataForm(DataForm.Type.b);
        dataForm.a(formField);
        return dataForm;
    }

    public MamQueryResult a(String string2, Integer n, Date object, Date date, String string3, RSMSet.PageDirection pageDirection, String string4, long l) throws SmackException.NoResponseException, XMPPException.XMPPErrorException, SmackException.NotConnectedException {
        DataForm dataForm = null;
        String string5 = UUID.randomUUID().toString();
        if (object != null || date != null || string3 != null) {
            DataForm dataForm2 = MamManager.b();
            if (object != null) {
                dataForm = new FormField("start");
                dataForm.c(XmppDateTime.a((Date)object));
                dataForm2.a((FormField)dataForm);
            }
            if (date != null) {
                object = new FormField("end");
                object.c(XmppDateTime.a((Date)date));
                dataForm2.a((FormField)object);
            }
            dataForm = dataForm2;
            if (string3 != null) {
                object = new FormField("with");
                object.c(string3);
                dataForm2.a((FormField)object);
                dataForm = dataForm2;
            }
        }
        object = new MamQueryIQ(string5, dataForm);
        object.a(IQ.Type.b);
        object.g(string2);
        if (n != null) {
            object.a((ExtensionElement)new RSMSet(n.intValue(), string4, pageDirection));
        }
        return this.a((MamQueryIQ)((Object)object), l);
    }

    public static class MamQueryResult {
        public final List<Forwarded> a;
        public final MamPacket.MamFinExtension b;
        private final DataForm c;

        private MamQueryResult(List<Forwarded> list, MamPacket.MamFinExtension mamFinExtension, DataForm dataForm) {
            this.a = list;
            this.b = mamFinExtension;
            this.c = dataForm;
        }
    }

}

