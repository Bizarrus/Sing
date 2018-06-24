/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat.smerialization;

import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializationConfiguration;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class SmerializableOutputStream
extends DataOutputStream {
    private SmerializationConfiguration a;

    public SmerializableOutputStream(SmerializationConfiguration smerializationConfiguration, OutputStream outputStream) {
        super(outputStream);
        this.a = smerializationConfiguration;
    }

    public void a(Smerializable smerializable) throws IOException {
        this.a((Object)smerializable);
        if (smerializable != null) {
            String string2 = this.a.b.get(smerializable.getClass());
            if (string2 == null) {
                throw new IOException("no signature for class " + smerializable.getClass());
            }
            this.writeUTF(string2);
            smerializable.a(this);
        }
    }

    public void a(Boolean bl) throws IOException {
        this.a((Object)bl);
        if (bl != null) {
            this.writeBoolean(bl);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(Object object) throws IOException {
        boolean bl = object != null;
        this.writeBoolean(bl);
    }

    public void a(String string2) throws IOException {
        this.a((Object)string2);
        if (string2 != null) {
            this.writeUTF(string2);
        }
    }
}

