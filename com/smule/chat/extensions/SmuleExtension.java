/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.SmackException
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.provider.ExtensionElementProvider
 *  org.jivesoftware.smack.util.XmlStringBuilder
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class SmuleExtension
implements ExtensionElement {
    public CharSequence c() {
        XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement)this);
        xmlStringBuilder.b();
        return xmlStringBuilder;
    }

    public String d() {
        return "urn:x-smule:xmpp";
    }

    public static class SimpleProvider<EE extends ExtensionElement>
    extends ExtensionElementProvider<EE> {
        private Class<EE> a;

        public SimpleProvider(Class<EE> class_) {
            this.a = class_;
        }

        public EE a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
            ExtensionElement extensionElement;
            while (xmlPullParser.next() != 3 || xmlPullParser.getDepth() != n) {
            }
            try {
                extensionElement = (ExtensionElement)this.a.newInstance();
            }
            catch (Exception exception) {
                throw new XmlPullParserException("couldn't instantiate " + this.a, xmlPullParser, (Throwable)exception);
            }
            return (EE)extensionElement;
        }

        public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
            return this.a(xmlPullParser, n);
        }
    }

}

