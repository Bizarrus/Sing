package com.smule.chat.extensions;

import java.io.IOException;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.provider.ExtensionElementProvider;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class GroupStatusExtension {

    public static class Invite extends SmuleExtension {
        private final String f18351a;

        public static class Provider extends ExtensionElementProvider<Invite> {
            public /* synthetic */ Element m19692b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                return m19691a(xmlPullParser, i);
            }

            public Invite m19691a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                String attributeValue = xmlPullParser.getAttributeValue(null, "jid");
                while (true) {
                    if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == i) {
                        return new Invite(attributeValue);
                    }
                }
            }
        }

        public Invite(String str) {
            this.f18351a = str;
        }

        public String m19695a() {
            return this.f18351a;
        }

        public String m19696b() {
            return "invite";
        }

        public CharSequence mo6368c() {
            CharSequence xmlStringBuilder = new XmlStringBuilder(this);
            if (this.f18351a != null) {
                xmlStringBuilder.d("jid", this.f18351a);
            }
            xmlStringBuilder.b();
            return xmlStringBuilder;
        }
    }

    public static class Join extends SmuleExtension {
        public String m19698b() {
            return "join";
        }
    }

    public static class Leave extends SmuleExtension {
        public String m19699b() {
            return "leave";
        }
    }

    public static class Remove extends SmuleExtension {
        private final String f18352a;

        public static class Provider extends ExtensionElementProvider<Remove> {
            public /* synthetic */ Element m19701b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                return m19700a(xmlPullParser, i);
            }

            public Remove m19700a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                String attributeValue = xmlPullParser.getAttributeValue(null, "jid");
                while (true) {
                    if (xmlPullParser.next() == 3 && xmlPullParser.getDepth() == i) {
                        return new Remove(attributeValue);
                    }
                }
            }
        }

        public Remove(String str) {
            this.f18352a = str;
        }

        public String m19702a() {
            return this.f18352a;
        }

        public String m19703b() {
            return "remove";
        }

        public CharSequence mo6368c() {
            CharSequence xmlStringBuilder = new XmlStringBuilder(this);
            if (this.f18352a != null) {
                xmlStringBuilder.d("jid", this.f18352a);
            }
            xmlStringBuilder.b();
            return xmlStringBuilder;
        }
    }

    public static class Rename extends SmuleExtension {
        private final String f18353a;
        private final String f18354b;

        public static class Provider extends ExtensionElementProvider<Rename> {
            public /* synthetic */ Element m19706b(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                return m19705a(xmlPullParser, i);
            }

            public Rename m19705a(XmlPullParser xmlPullParser, int i) throws XmlPullParserException, IOException, SmackException {
                String str = "";
                String str2 = "";
                while (true) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        if (xmlPullParser.getName().equals("old")) {
                            if (xmlPullParser.next() == 4) {
                                str = xmlPullParser.getText();
                            }
                        } else if (xmlPullParser.getName().equals("new") && xmlPullParser.next() == 4) {
                            str2 = xmlPullParser.getText();
                        }
                    } else if (next == 3 && xmlPullParser.getDepth() == i) {
                        return new Rename(str, str2);
                    }
                }
            }
        }

        public Rename(String str, String str2) {
            this.f18353a = str;
            this.f18354b = str2;
        }

        public String m19707a() {
            return this.f18354b;
        }

        public String m19708b() {
            return "rename";
        }

        public CharSequence mo6368c() {
            CharSequence xmlStringBuilder = new XmlStringBuilder(this);
            xmlStringBuilder.c();
            xmlStringBuilder.b("old", this.f18353a);
            xmlStringBuilder.b("new", this.f18354b);
            xmlStringBuilder.b(this);
            return xmlStringBuilder;
        }
    }
}
