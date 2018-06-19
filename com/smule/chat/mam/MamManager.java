package com.smule.chat.mam;

import com.smule.chat.mam.filter.MamMessageFinFilter;
import com.smule.chat.mam.filter.MamMessageResultFilter;
import com.smule.chat.mam.packet.MamPacket.MamFinExtension;
import com.smule.chat.mam.packet.MamPacket.MamResultExtension;
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
import org.jivesoftware.smack.PacketCollector.Configuration;
import org.jivesoftware.smack.SmackException.NoResponseException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPConnectionRegistry;
import org.jivesoftware.smack.XMPPException.XMPPErrorException;
import org.jivesoftware.smack.packet.IQ.Type;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.disco.ServiceDiscoveryManager;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.jivesoftware.smackx.rsm.packet.RSMSet.PageDirection;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;
import org.jxmpp.util.XmppDateTime;

public class MamManager extends Manager {
    private static final Map<XMPPConnection, MamManager> f18360b = new WeakHashMap();

    static class C37651 implements ConnectionCreationListener {
        C37651() {
        }

        public void m19719a(XMPPConnection xMPPConnection) {
            MamManager.m19721a(xMPPConnection);
        }
    }

    public static class MamQueryResult {
        public final List<Forwarded> f18357a;
        public final MamFinExtension f18358b;
        private final DataForm f18359c;

        private MamQueryResult(List<Forwarded> list, MamFinExtension mamFinExtension, DataForm dataForm) {
            this.f18357a = list;
            this.f18358b = mamFinExtension;
            this.f18359c = dataForm;
        }
    }

    static {
        XMPPConnectionRegistry.a(new C37651());
    }

    public static synchronized MamManager m19721a(XMPPConnection xMPPConnection) {
        MamManager mamManager;
        synchronized (MamManager.class) {
            mamManager = (MamManager) f18360b.get(xMPPConnection);
            if (mamManager == null) {
                mamManager = new MamManager(xMPPConnection);
                f18360b.put(xMPPConnection, mamManager);
            }
        }
        return mamManager;
    }

    private MamManager(XMPPConnection xMPPConnection) {
        super(xMPPConnection);
        ServiceDiscoveryManager.a(xMPPConnection).b("urn:xmpp:mam:0");
    }

    public MamQueryResult m19723a(String str, Integer num, Date date, Date date2, String str2, PageDirection pageDirection, String str3, long j) throws NoResponseException, XMPPErrorException, NotConnectedException {
        DataForm dataForm = null;
        String uuid = UUID.randomUUID().toString();
        if (!(date == null && date2 == null && str2 == null)) {
            FormField formField;
            dataForm = m19722b();
            if (date != null) {
                formField = new FormField("start");
                formField.c(XmppDateTime.a(date));
                dataForm.a(formField);
            }
            if (date2 != null) {
                formField = new FormField("end");
                formField.c(XmppDateTime.a(date2));
                dataForm.a(formField);
            }
            if (str2 != null) {
                formField = new FormField("with");
                formField.c(str2);
                dataForm.a(formField);
            }
        }
        MamQueryIQ mamQueryIQ = new MamQueryIQ(uuid, dataForm);
        mamQueryIQ.a(Type.b);
        mamQueryIQ.g(str);
        if (num != null) {
            mamQueryIQ.a(new RSMSet(num.intValue(), str3, pageDirection));
        }
        return m19720a(mamQueryIQ, j);
    }

    private MamQueryResult m19720a(MamQueryIQ mamQueryIQ, long j) throws NoResponseException, XMPPErrorException, NotConnectedException {
        if (j < 0) {
            throw new IllegalArgumentException("extra timeout must be zero or positive");
        }
        XMPPConnection a = a();
        PacketCollector a2 = a.a(new MamMessageFinFilter(mamQueryIQ));
        Configuration f = PacketCollector.f();
        MamFinExtension mamMessageResultFilter = new MamMessageResultFilter(mamQueryIQ);
        PacketCollector a3 = a.a(f.a(mamMessageResultFilter).a(a2));
        try {
            a.a(mamQueryIQ).d();
            mamMessageResultFilter = MamFinExtension.m19733a((Message) a2.b(a.y() + j));
            List arrayList = new ArrayList(a3.e());
            while (true) {
                Message message = (Message) a3.c();
                if (message == null) {
                    return new MamQueryResult(arrayList, mamMessageResultFilter, DataForm.a(mamQueryIQ));
                }
                arrayList.add(MamResultExtension.m19739a(message).m19742e());
            }
        } finally {
            a3.a();
            a2.a();
        }
    }

    private static DataForm m19722b() {
        FormField formField = new FormField("FORM_TYPE");
        formField.a(FormField.Type.c);
        formField.c("urn:xmpp:mam:0");
        DataForm dataForm = new DataForm(DataForm.Type.b);
        dataForm.a(formField);
        return dataForm;
    }
}
