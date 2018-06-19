package com.smule.chat.mam.provider;

import com.smule.chat.mam.packet.MamPacket.MamResultExtension;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MamResultProvider extends ExtensionElementProvider<MamResultExtension> {
    public /* synthetic */ Element m19748b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m19747a(xmlPullParser, i);
    }

    public MamResultExtension m19747a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        Forwarded forwarded = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "queryid");
        String attributeValue2 = xmlPullParser.getAttributeValue(null, "id");
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("forwarded") && xmlPullParser.getNamespace().equals("urn:xmpp:forward:0")) {
                    forwarded = (Forwarded) PacketParserUtils.a("forwarded", "urn:xmpp:forward:0", xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getDepth() == i) {
                return new MamResultExtension(attributeValue, attributeValue2, forwarded);
            }
        }
    }
}
