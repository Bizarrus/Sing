package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class PerformanceExtension extends SmuleExtension {
    private final String f18355a;

    public static class Provider extends ExtensionElementProvider<PerformanceExtension> {
        public /* synthetic */ Element m19711b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            return m19710a(xmlPullParser, i);
        }

        public PerformanceExtension m19710a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
            String str = "";
            while (true) {
                int next = xmlPullParser.next();
                if (next == 2) {
                    if (xmlPullParser.getName().equals("key") && xmlPullParser.next() == 4) {
                        str = xmlPullParser.getText();
                    }
                } else if (next == 3 && xmlPullParser.getDepth() == i) {
                    return new PerformanceExtension(str);
                }
            }
        }
    }

    public PerformanceExtension(String str) {
        this.f18355a = str;
    }

    public String m19713b() {
        return "performance";
    }

    public String m19712a() {
        return this.f18355a;
    }

    public CharSequence mo6368c() {
        CharSequence xmlStringBuilder = new XmlStringBuilder(this);
        xmlStringBuilder.c();
        xmlStringBuilder.b("key", this.f18355a);
        xmlStringBuilder.b(this);
        return xmlStringBuilder;
    }
}
