/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.NamedElement
 *  org.jivesoftware.smack.provider.ExtensionElementProvider
 *  org.jivesoftware.smack.util.XmlStringBuilder
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.smule.chat.extensions;

import com.smule.chat.extensions.SmuleExtension;
import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PerformanceExtension
extends SmuleExtension {
    private final String a;

    public PerformanceExtension(String string2) {
        this.a = string2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return "performance";
    }

    @Override
    public CharSequence c() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement)this);
        xmlStringBuilder.c();
        xmlStringBuilder.b("key", this.a);
        xmlStringBuilder.b((NamedElement)this);
        return xmlStringBuilder;
    }

    public static class Provider
    extends ExtensionElementProvider<PerformanceExtension> {
        public PerformanceExtension a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
            String string2 = "";
            do {
                int n2;
                if ((n2 = xmlPullParser.next()) == 2) {
                    if (!xmlPullParser.getName().equals("key") || xmlPullParser.next() != 4) continue;
                    string2 = xmlPullParser.getText();
                    continue;
                }
                if (n2 == 3 && xmlPullParser.getDepth() == n) break;
            } while (true);
            return new PerformanceExtension(string2);
        }

        public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
            return this.a(xmlPullParser, n);
        }
    }

}

