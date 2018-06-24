/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.provider.ExtensionElementProvider
 *  org.jivesoftware.smack.util.PacketParserUtils
 *  org.jivesoftware.smackx.forward.packet.Forwarded
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.smule.chat.mam.provider;

import com.smule.chat.mam.packet.MamPacket;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.PacketParserUtils;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MamResultProvider
extends ExtensionElementProvider<MamPacket.MamResultExtension> {
    public MamPacket.MamResultExtension a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        Forwarded forwarded = null;
        String string2 = xmlPullParser.getAttributeValue(null, "queryid");
        String string3 = xmlPullParser.getAttributeValue(null, "id");
        do {
            int n2;
            if ((n2 = xmlPullParser.next()) == 2) {
                if (!xmlPullParser.getName().equals("forwarded") || !xmlPullParser.getNamespace().equals("urn:xmpp:forward:0")) continue;
                forwarded = (Forwarded)PacketParserUtils.a((String)"forwarded", (String)"urn:xmpp:forward:0", (XmlPullParser)xmlPullParser);
                continue;
            }
            if (n2 == 3 && xmlPullParser.getDepth() == n) break;
        } while (true);
        return new MamPacket.MamResultExtension(string2, string3, forwarded);
    }

    public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        return this.a(xmlPullParser, n);
    }
}

