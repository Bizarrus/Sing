package com.smule.chat;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smackx.muc.MUCAffiliation;
import org.jivesoftware.smackx.muc.MUCRole;
import org.jivesoftware.smackx.muc.packet.MUCItem;
import org.jivesoftware.smackx.muc.packet.MUCUser;
import org.jivesoftware.smackx.muc.packet.MUCUser.Decline;
import org.jivesoftware.smackx.muc.packet.MUCUser.Invite;
import org.jivesoftware.smackx.muc.packet.MUCUser.Status;
import org.jivesoftware.smackx.muc.provider.MUCParserUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class SmuleMUCUserProvider extends ExtensionElementProvider<MUCUser> {
    public /* synthetic */ Element m19673b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
        return m19672a(xmlPullParser, i);
    }

    public MUCUser m19672a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException {
        MUCUser mUCUser = new MUCUser();
        while (true) {
            switch (xmlPullParser.next()) {
                case 2:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -1183699191:
                            if (name.equals("invite")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -892481550:
                            if (name.equals("status")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3242771:
                            if (name.equals("item")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1216985755:
                            if (name.equals("password")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1542349558:
                            if (name.equals("decline")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1557372922:
                            if (name.equals("destroy")) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            mUCUser.a(m19670c(xmlPullParser));
                            break;
                        case 1:
                            mUCUser.a(m19669a(xmlPullParser));
                            break;
                        case 2:
                            mUCUser.a(xmlPullParser.nextText());
                            break;
                        case 3:
                            mUCUser.a(Status.a(xmlPullParser.getAttributeValue("", "code")));
                            break;
                        case 4:
                            mUCUser.a(m19671d(xmlPullParser));
                            break;
                        case 5:
                            mUCUser.a(MUCParserUtils.b(xmlPullParser));
                            break;
                        default:
                            break;
                    }
                case 3:
                    if (xmlPullParser.getDepth() != i) {
                        break;
                    }
                    return mUCUser;
                default:
                    break;
            }
        }
    }

    private static Invite m19670c(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj = null;
        Invite invite = new Invite();
        invite.a(xmlPullParser.getAttributeValue("", "from"));
        invite.c(xmlPullParser.getAttributeValue("", "to"));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    invite.b(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("invite")) {
                obj = 1;
            }
        }
        return invite;
    }

    private static Decline m19671d(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Object obj = null;
        Decline decline = new Decline();
        decline.a(xmlPullParser.getAttributeValue("", "from"));
        decline.c(xmlPullParser.getAttributeValue("", "to"));
        while (obj == null) {
            int next = xmlPullParser.next();
            if (next == 2) {
                if (xmlPullParser.getName().equals("reason")) {
                    decline.b(xmlPullParser.nextText());
                }
            } else if (next == 3 && xmlPullParser.getName().equals("decline")) {
                obj = 1;
            }
        }
        return decline;
    }

    public static MUCItem m19669a(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = null;
        int depth = xmlPullParser.getDepth();
        MUCAffiliation a = MUCAffiliation.a(xmlPullParser.getAttributeValue("", "affiliation"));
        String attributeValue = xmlPullParser.getAttributeValue("", "nick");
        MUCRole a2 = MUCRole.a(xmlPullParser.getAttributeValue("", "role"));
        String attributeValue2 = xmlPullParser.getAttributeValue("", "jid");
        String str2 = null;
        while (true) {
            switch (xmlPullParser.next()) {
                case 2:
                    String name = xmlPullParser.getName();
                    Object obj = -1;
                    switch (name.hashCode()) {
                        case -934964668:
                            if (name.equals("reason")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 92645877:
                            if (name.equals("actor")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            str2 = xmlPullParser.getAttributeValue("", "nick");
                            if (str2 != null) {
                                break;
                            }
                            str2 = xmlPullParser.getAttributeValue("", "jid");
                            break;
                        case 1:
                            str = xmlPullParser.nextText();
                            break;
                        default:
                            break;
                    }
                case 3:
                    if (xmlPullParser.getDepth() != depth) {
                        break;
                    }
                    return new MUCItem(a, a2, str2, str, attributeValue2, attributeValue);
                default:
                    break;
            }
        }
    }
}
