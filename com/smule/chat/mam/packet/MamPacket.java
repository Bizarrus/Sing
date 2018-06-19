package com.smule.chat.mam.packet;

import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.forward.packet.Forwarded;
import org.jivesoftware.smackx.rsm.packet.RSMSet;

public class MamPacket {

    public static abstract class AbstractMamExtension implements ExtensionElement {
        public final String f18363a;

        protected AbstractMamExtension(String str) {
            this.f18363a = str;
        }

        public final String m19731a() {
            return this.f18363a;
        }

        public final String m19732d() {
            return "urn:xmpp:mam:0";
        }
    }

    public static class MamFinExtension extends AbstractMamExtension {
        private final RSMSet f18364b;
        private final boolean f18365c;
        private final boolean f18366d;

        public /* synthetic */ CharSequence m19735c() {
            return m19738g();
        }

        public MamFinExtension(String str, RSMSet rSMSet, boolean z, boolean z2) {
            super(str);
            if (rSMSet == null) {
                throw new IllegalArgumentException("rsmSet must not be null");
            }
            this.f18364b = rSMSet;
            this.f18365c = z;
            this.f18366d = z2;
        }

        public RSMSet m19736e() {
            return this.f18364b;
        }

        public boolean m19737f() {
            return this.f18365c;
        }

        public String m19734b() {
            return "fin";
        }

        public XmlStringBuilder m19738g() {
            XmlStringBuilder xmlStringBuilder = new XmlStringBuilder();
            xmlStringBuilder.a(this);
            xmlStringBuilder.e("queryid", this.a);
            xmlStringBuilder.a("complete", this.f18365c);
            xmlStringBuilder.a("stable", this.f18366d);
            if (this.f18364b == null) {
                xmlStringBuilder.b();
            } else {
                xmlStringBuilder.c();
                xmlStringBuilder.a(this.f18364b);
                xmlStringBuilder.b(this);
            }
            return xmlStringBuilder;
        }

        public static MamFinExtension m19733a(Message message) {
            return (MamFinExtension) message.c("fin", "urn:xmpp:mam:0");
        }
    }

    public static class MamResultExtension extends AbstractMamExtension {
        private final String f18367b;
        private final Forwarded f18368c;

        public MamResultExtension(String str, String str2, Forwarded forwarded) {
            super(str);
            if (!StringUtils.a(str2)) {
                throw new IllegalArgumentException("id must not be null or empty");
            } else if (forwarded == null) {
                throw new IllegalArgumentException("forwarded must no be null");
            } else {
                this.f18367b = str2;
                this.f18368c = forwarded;
            }
        }

        public Forwarded m19742e() {
            return this.f18368c;
        }

        public String m19740b() {
            return "result";
        }

        public CharSequence m19741c() {
            return null;
        }

        public static MamResultExtension m19739a(Message message) {
            return (MamResultExtension) message.c("result", "urn:xmpp:mam:0");
        }
    }
}
