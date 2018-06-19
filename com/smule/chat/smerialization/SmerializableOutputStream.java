package com.smule.chat.smerialization;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class SmerializableOutputStream extends DataOutputStream {
    private SmerializationConfiguration f18372a;

    public SmerializableOutputStream(SmerializationConfiguration smerializationConfiguration, OutputStream outputStream) {
        super(outputStream);
        this.f18372a = smerializationConfiguration;
    }

    public void m19755a(Boolean bool) throws IOException {
        m19756a((Object) bool);
        if (bool != null) {
            writeBoolean(bool.booleanValue());
        }
    }

    public void m19757a(String str) throws IOException {
        m19756a((Object) str);
        if (str != null) {
            writeUTF(str);
        }
    }

    public void m19754a(Smerializable smerializable) throws IOException {
        m19756a((Object) smerializable);
        if (smerializable != null) {
            String str = (String) this.f18372a.f18374b.get(smerializable.getClass());
            if (str == null) {
                throw new IOException("no signature for class " + smerializable.getClass());
            }
            writeUTF(str);
            smerializable.mo6317a(this);
        }
    }

    public void m19756a(Object obj) throws IOException {
        writeBoolean(obj != null);
    }
}
