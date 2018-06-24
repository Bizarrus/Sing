/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.provider.IQProvider
 *  org.jivesoftware.smackx.time.packet.Time
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SmuleTimeProvider
extends IQProvider<Time> {
    public Time a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        String string2 = null;
        String string3 = null;
        do {
            int n2;
            if ((n2 = xmlPullParser.next()) == 2) {
                if (xmlPullParser.getName().equals("tzo")) {
                    if (xmlPullParser.next() != 4) continue;
                    string2 = xmlPullParser.getText();
                    continue;
                }
                if (!xmlPullParser.getName().equals("utc") || xmlPullParser.next() != 4) continue;
                string3 = xmlPullParser.getText();
                continue;
            }
            if (n2 == 3 && xmlPullParser.getDepth() == n) break;
        } while (true);
        xmlPullParser = new Time();
        xmlPullParser.a(string3);
        xmlPullParser.b(string2);
        return xmlPullParser;
    }

    public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
        return this.a(xmlPullParser, n);
    }
}

