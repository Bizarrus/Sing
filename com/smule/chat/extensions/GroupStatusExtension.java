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

public class GroupStatusExtension {

    public static class Invite
    extends SmuleExtension {
        private final String a;

        public Invite(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return "invite";
        }

        @Override
        public CharSequence c() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement)this);
            if (this.a != null) {
                xmlStringBuilder.d("jid", this.a);
            }
            xmlStringBuilder.b();
            return xmlStringBuilder;
        }

        public static class Provider
        extends ExtensionElementProvider<Invite> {
            public Invite a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                String string2 = xmlPullParser.getAttributeValue(null, "jid");
                while (xmlPullParser.next() != 3 || xmlPullParser.getDepth() != n) {
                }
                return new Invite(string2);
            }

            public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                return this.a(xmlPullParser, n);
            }
        }

    }

    public static class Join
    extends SmuleExtension {
        public String b() {
            return "join";
        }
    }

    public static class Leave
    extends SmuleExtension {
        public String b() {
            return "leave";
        }
    }

    public static class Remove
    extends SmuleExtension {
        private final String a;

        public Remove(String string2) {
            this.a = string2;
        }

        public String a() {
            return this.a;
        }

        public String b() {
            return "remove";
        }

        @Override
        public CharSequence c() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement)this);
            if (this.a != null) {
                xmlStringBuilder.d("jid", this.a);
            }
            xmlStringBuilder.b();
            return xmlStringBuilder;
        }

        public static class Provider
        extends ExtensionElementProvider<Remove> {
            public Remove a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                String string2 = xmlPullParser.getAttributeValue(null, "jid");
                while (xmlPullParser.next() != 3 || xmlPullParser.getDepth() != n) {
                }
                return new Remove(string2);
            }

            public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                return this.a(xmlPullParser, n);
            }
        }

    }

    public static class Rename
    extends SmuleExtension {
        private final String a;
        private final String b;

        public Rename(String string2, String string3) {
            this.a = string2;
            this.b = string3;
        }

        public String a() {
            return this.b;
        }

        public String b() {
            return "rename";
        }

        @Override
        public CharSequence c() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder((ExtensionElement)this);
            xmlStringBuilder.c();
            xmlStringBuilder.b("old", this.a);
            xmlStringBuilder.b("new", this.b);
            xmlStringBuilder.b((NamedElement)this);
            return xmlStringBuilder;
        }

        public static class Provider
        extends ExtensionElementProvider<Rename> {
            public Rename a(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                String string2 = "";
                String string3 = "";
                do {
                    int n2;
                    if ((n2 = xmlPullParser.next()) == 2) {
                        if (xmlPullParser.getName().equals("old")) {
                            if (xmlPullParser.next() != 4) continue;
                            string2 = xmlPullParser.getText();
                            continue;
                        }
                        if (!xmlPullParser.getName().equals("new") || xmlPullParser.next() != 4) continue;
                        string3 = xmlPullParser.getText();
                        continue;
                    }
                    if (n2 == 3 && xmlPullParser.getDepth() == n) break;
                } while (true);
                return new Rename(string2, string3);
            }

            public /* synthetic */ Element b(XmlPullParser xmlPullParser, int n) throws XmlPullParserException, IOException, SmackException {
                return this.a(xmlPullParser, n);
            }
        }

    }

}

