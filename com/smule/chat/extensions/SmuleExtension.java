package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public abstract class SmuleExtension implements ExtensionElement {

    public static class SimpleProvider<EE extends ExtensionElement> extends ExtensionElementProvider<EE> {
        private Class<EE> f18356a;

        public /* synthetic */ Element m19716b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m19715a(xmlPullParser, i);
        }

        public SimpleProvider(Class<EE> cls) {
            this.f18356a = cls;
        }

        public EE m19715a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            while (true) {
                if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == i) {
                    try {
                        return (ExtensionElement) this.f18356a.newInstance();
                    } catch (Throwable e) {
                        throw new XmlPullParserException("couldn't instantiate " + this.f18356a, xmlPullParser, e);
                    }
                }
            }
        }
    }

    public String m19694d() {
        return "urn:x-smule:xmpp";
    }

    public CharSequence mo6368c() {
        CharSequence xmlStringBuilder = new XmlStringBuilder(this);
        xmlStringBuilder.b();
        return xmlStringBuilder;
    }
}
