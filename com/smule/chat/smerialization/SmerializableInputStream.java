/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat.smerialization;

import com.smule.chat.smerialization.Smerializable;
import com.smule.chat.smerialization.SmerializationConfiguration;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.InvalidObjectException;
import java.util.Map;

public class SmerializableInputStream
extends DataInputStream {
    private SmerializationConfiguration a;

    public SmerializableInputStream(SmerializationConfiguration smerializationConfiguration, InputStream inputStream) {
        super(inputStream);
        this.a = smerializationConfiguration;
    }

    public int a(int n, int n2) throws IOException {
        int n3 = this.readInt();
        if (n3 < n || n3 > n2) {
            throw new InvalidClassException("bad version: " + n3 + " out of range (" + n + ", " + n2 + ")");
        }
        return n3;
    }

    public Boolean a() throws IOException {
        if (this.d()) {
            return this.readBoolean();
        }
        return null;
    }

    public String b() throws IOException {
        if (this.d()) {
            return this.readUTF();
        }
        return null;
    }

    public Smerializable c() throws IOException {
        if (!this.d()) {
            return null;
        }
        Object object = this.readUTF();
        Class class_ = this.a.a.get(object);
        if (class_ == null) {
            throw new InvalidObjectException("bad signature: " + (String)object);
        }
        try {
            object = (Smerializable)class_.newInstance();
        }
        catch (Exception exception) {
            throw new IOException(exception);
        }
        object.a(this);
        return object;
    }

    public boolean d() throws IOException {
        return this.readBoolean();
    }
}

