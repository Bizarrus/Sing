/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  org.jivesoftware.smack.packet.ExtensionElement
 *  org.jivesoftware.smack.packet.IQ
 *  org.jivesoftware.smack.packet.IQ$IQChildElementXmlStringBuilder
 *  org.jivesoftware.smack.util.XmlStringBuilder
 *  org.jivesoftware.smackx.xdata.FormField
 *  org.jivesoftware.smackx.xdata.packet.DataForm
 */
package com.smule.chat.mam.packet;

import java.util.List;
import org.jivesoftware.smack.packet.ExtensionElement;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.util.XmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class MamQueryIQ
extends IQ {
    private final String c;
    private final String d;

    private MamQueryIQ(String string2, String string3, DataForm dataForm) {
        super("query", "urn:xmpp:mam:0");
        this.c = string2;
        this.d = string3;
        if (dataForm != null) {
            string2 = dataForm.j();
            if (string2 == null) {
                throw new IllegalArgumentException("If a data form is given it must posses a hidden form type field");
            }
            if (!((String)string2.h().get(0)).equals("urn:xmpp:mam:0")) {
                throw new IllegalArgumentException("Value of the hidden form type field must be 'urn:xmpp:mam:0'");
            }
            this.a((ExtensionElement)dataForm);
        }
    }

    public MamQueryIQ(String string2, DataForm dataForm) {
        this(string2, null, dataForm);
    }

    public String a() {
        return this.c;
    }

    protected IQ.IQChildElementXmlStringBuilder a(IQ.IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.e("queryid", this.c).e("node", this.d).c();
        return iQChildElementXmlStringBuilder;
    }
}

