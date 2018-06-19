package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.IQProvider;
import org.jivesoftware.smackx.time.packet.Time;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SmuleTimeProvider extends IQProvider<Time> {
    public /* synthetic */ Element m19718b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m19717a(xmlPullParser, i);
    }

    public Time m19717a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        String str = null;
        String str2 = null;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("tzo")) {
                    if (xmlPullParser.next() == 4) {
                        str = xmlPullParser.getText();
                    }
                } else if (xmlPullParser.getName().equals("utc") && xmlPullParser.next() == 4) {
                    str2 = xmlPullParser.getText();
                }
            } else if (next == 3 && xmlPullParser.getDepth() == i) {
                Time time = new Time();
                time.a(str2);
                time.b(str);
                return time;
            }
        }
    }
}
