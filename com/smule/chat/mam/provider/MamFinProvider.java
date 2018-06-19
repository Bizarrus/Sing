package com.smule.chat.mam.provider;

import com.smule.chat.mam.packet.MamPacket.MamFinExtension;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MamFinProvider extends ExtensionElementProvider<MamFinExtension> {
    public /* synthetic */ Element m19746b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m19745a(xmlPullParser, i);
    }

    public MamFinExtension m19745a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        RSMSet rSMSet = null;
        String attributeValue = xmlPullParser.getAttributeValue(null, "queryid");
        boolean parseBoolean = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, "complete"));
        boolean parseBoolean2 = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, "stable"));
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("set") && xmlPullParser.getNamespace().equals("http://jabber.org/protocol/rsm")) {
                    rSMSet = (RSMSet) PacketParserUtils.a("set", "http://jabber.org/protocol/rsm", xmlPullParser);
                }
            } else if (next == 3 && xmlPullParser.getDepth() == i) {
                return new MamFinExtension(attributeValue, rSMSet, parseBoolean, parseBoolean2);
            }
        }
    }
}
