package com.smule.chat.mam.packet;

import com.google.android.gms.actions.SearchIntents;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smack.packet.IQ.IQChildElementXmlStringBuilder;
import org.jivesoftware.smackx.xdata.FormField;
import org.jivesoftware.smackx.xdata.packet.DataForm;

public class MamQueryIQ extends IQ {
    private final String f18369c;
    private final String f18370d;

    private MamQueryIQ(String str, String str2, DataForm dataForm) {
        super(SearchIntents.EXTRA_QUERY, "urn:xmpp:mam:0");
        this.f18369c = str;
        this.f18370d = str2;
        if (dataForm != null) {
            FormField j = dataForm.j();
            if (j == null) {
                throw new IllegalArgumentException("If a data form is given it must posses a hidden form type field");
            } else if (((String) j.h().get(0)).equals("urn:xmpp:mam:0")) {
                a(dataForm);
            } else {
                throw new IllegalArgumentException("Value of the hidden form type field must be 'urn:xmpp:mam:0'");
            }
        }
    }

    public MamQueryIQ(String str, DataForm dataForm) {
        this(str, null, dataForm);
    }

    public String m19743a() {
        return this.f18369c;
    }

    protected IQChildElementXmlStringBuilder m19744a(IQChildElementXmlStringBuilder iQChildElementXmlStringBuilder) {
        iQChildElementXmlStringBuilder.e("queryid", this.f18369c).e("node", this.f18370d).c();
        return iQChildElementXmlStringBuilder;
    }
}
