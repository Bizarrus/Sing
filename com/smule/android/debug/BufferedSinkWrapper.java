/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  okio.Buffer
 *  okio.BufferedSink
 *  okio.ByteString
 *  okio.Source
 *  okio.Timeout
 */
package com.smule.android.debug;

import java.io.IOException;
import java.io.OutputStream;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public class BufferedSinkWrapper
implements BufferedSink {
    private BufferedSink a;

    BufferedSinkWrapper(BufferedSink bufferedSink) {
        this.a = bufferedSink;
    }

    public long a(Source source) throws IOException {
        return this.a.a(source);
    }

    public Buffer a() {
        return this.a.a();
    }

    public BufferedSink a(int n) throws IOException {
        this.a.a(n);
        return this;
    }

    public BufferedSink a(long l) throws IOException {
        this.a.a(l);
        return this;
    }

    public BufferedSink a(String string2) throws IOException {
        this.a.a(string2);
        return this;
    }

    public BufferedSink a(ByteString byteString) throws IOException {
        this.a.a(byteString);
        return this;
    }

    public BufferedSink a(byte[] arrby) throws IOException {
        this.a.a(arrby);
        return this;
    }

    public BufferedSink a(byte[] arrby, int n, int n2) throws IOException {
        this.a.a(arrby, n, n2);
        return this;
    }

    public void a(Buffer buffer, long l) throws IOException {
        this.a.a(buffer, l);
    }

    public BufferedSink b() throws IOException {
        this.a.b();
        return this;
    }

    public BufferedSink b(int n) throws IOException {
        this.a.b(n);
        return this;
    }

    public BufferedSink b(long l) throws IOException {
        this.a.b(l);
        return this;
    }

    public BufferedSink c() throws IOException {
        this.a.c();
        return this;
    }

    public BufferedSink c(int n) throws IOException {
        this.a.c(n);
        return this;
    }

    public void close() throws IOException {
        this.a.close();
    }

    public OutputStream d() {
        return this.a.d();
    }

    public BufferedSink d(int n) throws IOException {
        this.a.d(n);
        return this;
    }

    public void flush() throws IOException {
        this.a.flush();
    }

    public Timeout timeout() {
        return this.a.timeout();
    }
}

