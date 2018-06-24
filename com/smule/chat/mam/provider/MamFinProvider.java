/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.provider.ExtensionElementProvider
 *  org.jivesoftware.smack.util.PacketParserUtils
 *  org.jivesoftware.smackx.rsm.packet.RSMSet
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
import org.jivesoftware.smackx.rsm.packet.RSMSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class MamFinProvider
extends ExtensionElementProvider<MamPacket.MamFinExtension> {
    public MamPacket.MamFinExtension a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        RSMSet rSMSet = null;
        String string2 = xmlPullParser.getAttributeValue(null, "queryid");
        boolean bl = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, "complete"));
        boolean bl2 = Boolean.parseBoolean(xmlPullParser.getAttributeValue(null, "stable"));
        do {
            int n2;
            if ((n2 = xmlPullParser.next()) == 2) {
                if (!xmlPullParser.getName().equals("set") || !xmlPullParser.getNamespace().equals("http://jabber.org/protocol/rsm")) continue;
                rSMSet = (RSMSet)PacketParserUtils.a((String)"set", (String)"http://jabber.org/protocol/rsm", (XmlPullParser)xmlPullParser);
                continue;
            }
            if (n2 == 3 && xmlPullParser.getDepth() == n) break;
        } while (true);
        return new MamPacket.MamFinExtension(string2, rSMSet, bl, bl2);
    }

    public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        return this.a(xmlPullParser, n);
    }
}

