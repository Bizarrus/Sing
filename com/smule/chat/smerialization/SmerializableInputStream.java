package com.smule.chat.smerialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;

public class SmerializableInputStream extends DataInputStream {
    private SmerializationConfiguration f18371a;

    public SmerializableInputStream(SmerializationConfiguration smerializationConfiguration, InputStream inputStream) {
        super(inputStream);
        this.f18371a = smerializationConfiguration;
    }

    public Boolean m19750a() throws IOException {
        if (m19753d()) {
            return Boolean.valueOf(readBoolean());
        }
        return null;
    }

    public String m19751b() throws IOException {
        if (m19753d()) {
            return readUTF();
        }
        return null;
    }

    public Smerializable m19752c() throws IOException {
        if (!m19753d()) {
            return null;
        }
        String readUTF = readUTF();
        Class cls = (Class) this.f18371a.f18373a.get(readUTF);
        if (cls == null) {
            throw new InvalidObjectException("bad signature: " + readUTF);
        }
        try {
            Smerializable smerializable = (Smerializable) cls.newInstance();
            smerializable.mo6316a(this);
            return smerializable;
        } catch (Throwable e) {
            throw new IOException(e);
        }
    }

    public boolean m19753d() throws IOException {
        return readBoolean();
    }

    public int m19749a(int i, int i2) throws IOException {
        int readInt = readInt();
        if (readInt >= i && readInt <= i2) {
            return readInt;
        }
        throw new InvalidClassException("bad version: " + readInt + " out of range (" + i + ", " + i2 + ")");
    }
}
