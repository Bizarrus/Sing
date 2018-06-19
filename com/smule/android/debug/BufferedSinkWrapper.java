package com.smule.android.debug;

import java.io.IOException;
import java.io.OutputStream;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

public class BufferedSinkWrapper implements BufferedSink {
    private BufferedSink f15895a;

    BufferedSinkWrapper(BufferedSink bufferedSink) {
        this.f15895a = bufferedSink;
    }

    public Buffer m17688a() {
        return this.f15895a.a();
    }

    public BufferedSink m17692a(ByteString byteString) throws IOException {
        this.f15895a.a(byteString);
        return this;
    }

    public BufferedSink m17693a(byte[] bArr) throws IOException {
        this.f15895a.a(bArr);
        return this;
    }

    public BufferedSink mo6246a(byte[] bArr, int i, int i2) throws IOException {
        this.f15895a.a(bArr, i, i2);
        return this;
    }

    public long m17687a(Source source) throws IOException {
        return this.f15895a.a(source);
    }

    public BufferedSink m17691a(String str) throws IOException {
        this.f15895a.a(str);
        return this;
    }

    public BufferedSink m17689a(int i) throws IOException {
        this.f15895a.a(i);
        return this;
    }

    public BufferedSink m17697b(int i) throws IOException {
        this.f15895a.b(i);
        return this;
    }

    public BufferedSink m17700c(int i) throws IOException {
        this.f15895a.c(i);
        return this;
    }

    public BufferedSink m17702d(int i) throws IOException {
        this.f15895a.d(i);
        return this;
    }

    public BufferedSink m17690a(long j) throws IOException {
        this.f15895a.a(j);
        return this;
    }

    public BufferedSink m17698b(long j) throws IOException {
        this.f15895a.b(j);
        return this;
    }

    public BufferedSink m17696b() throws IOException {
        this.f15895a.b();
        return this;
    }

    public BufferedSink m17699c() throws IOException {
        this.f15895a.c();
        return this;
    }

    public OutputStream m17701d() {
        return this.f15895a.d();
    }

    public void m17695a(Buffer buffer, long j) throws IOException {
        this.f15895a.a(buffer, j);
    }

    public void flush() throws IOException {
        this.f15895a.flush();
    }

    public Timeout timeout() {
        return this.f15895a.timeout();
    }

    public void close() throws IOException {
        this.f15895a.close();
    }
}
