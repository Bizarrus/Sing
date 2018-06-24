/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.Element
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.Message
 *  org.jivesoftware.smack.packet.NamedElement
 *  org.jivesoftware.smack.util.StringUtils
 *  org.jivesoftware.smack.util.XmlStringBuilder
 *  org.jivesoftware.smackx.forward.packet.Forwarded
 *  org.jivesoftware.smackx.rsm.packet.RSMSet
 */
package com.smule.chat.mam.packet;

import org.jivesoftware.smack.packet.Element;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.NamedElement;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class MamPacket {

    public static abstract class AbstractMamExtension
    implements ExtensionElement {
        public final String a;

        protected AbstractMamExtension(String string2) {
            this.a = string2;
        }

        public final String a() {
            return this.a;
        }

        public final String d() {
            return "urn:xmpp:mam:0";
        }
    }

    public static class MamFinExtension
    extends AbstractMamExtension {
        private final RSMSet b;
        private final boolean c;
        private final boolean d;

        public MamFinExtension(String string2, RSMSet rSMSet, boolean bl, boolean bl2) {
            super(string2);
            if (rSMSet == null) {
                throw new IllegalArgumentException("rsmSet must not be null");
            }
            this.b = rSMSet;
            this.c = bl;
            this.d = bl2;
        }

        public static MamFinExtension a(Message message) {
            return (MamFinExtension)message.c("fin", "urn:xmpp:mam:0");
        }

        public String b() {
            return "fin";
        }

        public /* synthetic */ CharSequence c() {
            return this.g();
        }

        public RSMSet e() {
            return this.b;
        }

        public boolean f() {
            return this.c;
        }

        public XmlStringBuilder g() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.a((NamedElement)this);
            xmlStringBuilder.e("queryid", this.a);
            xmlStringBuilder.a("complete", this.c);
            xmlStringBuilder.a("stable", this.d);
            if (this.b == null) {
                xmlStringBuilder.b();
                return xmlStringBuilder;
            }
            xmlStringBuilder.c();
            xmlStringBuilder.a((Element)this.b);
            xmlStringBuilder.b((NamedElement)this);
            return xmlStringBuilder;
        }
    }

    public static class MamResultExtension
    extends AbstractMamExtension {
        private final String b;
        private final Forwarded c;

        public MamResultExtension(String string2, String string3, Forwarded forwarded) {
            super(string2);
            if (!StringUtils.a((CharSequence)string3)) {
                throw new IllegalArgumentException("id must not be null or empty");
            }
            if (forwarded == null) {
                throw new IllegalArgumentException("forwarded must no be null");
            }
            this.b = string3;
            this.c = forwarded;
        }

        public static MamResultExtension a(Message message) {
            return (MamResultExtension)message.c("result", "urn:xmpp:mam:0");
        }

        public String b() {
            return "result";
        }

        public CharSequence c() {
            return null;
        }

        public Forwarded e() {
            return this.c;
        }
    }

}

