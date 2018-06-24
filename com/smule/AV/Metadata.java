/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.google.flatbuffers.FlatBufferBuilder
 *  com.google.flatbuffers.Table
 */
package com.smule.AV;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import com.smule.AV.RobotVoice.Features;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class Metadata
extends Table {
    public static Metadata a(ByteBuffer byteBuffer) {
        return Metadata.a(byteBuffer, new Metadata());
    }

    public static Metadata a(ByteBuffer byteBuffer, Metadata metadata) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadata.b(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.e(2);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int n) {
        flatBufferBuilder.b(0, n, 0);
    }

    public static int b(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.e();
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, int n) {
        flatBufferBuilder.b(1, n, 0);
    }

    public Features a(Features features) {
        int n = this.a(6);
        if (n != 0) {
            return features.b(this.b(n + this.b), this.c);
        }
        return null;
    }

    public String a() {
        int n = this.a(4);
        if (n != 0) {
            return this.c(n + this.b);
        }
        return null;
    }

    public void a(int n, ByteBuffer byteBuffer) {
        this.b = n;
        this.c = byteBuffer;
    }

    public Metadata b(int n, ByteBuffer byteBuffer) {
        this.a(n, byteBuffer);
        return this;
    }

    public Features b() {
        return this.a(new Features());
    }
}

