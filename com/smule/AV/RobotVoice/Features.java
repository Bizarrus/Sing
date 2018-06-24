/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.google.flatbuffers.FlatBufferBuilder
 *  com.google.flatbuffers.Table
 */
package com.smule.AV.RobotVoice;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;

public final class Features
extends Table {
    public static void a(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.e(3);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, byte by) {
        flatBufferBuilder.a(0, by, 0);
    }

    public static void a(FlatBufferBuilder flatBufferBuilder, int n) {
        flatBufferBuilder.b(1, n, 0);
    }

    public static int b(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.e();
    }

    public static void b(FlatBufferBuilder flatBufferBuilder, byte by) {
        flatBufferBuilder.a(2, by, 0);
    }

    public byte a() {
        int n = this.a(4);
        if (n != 0) {
            return this.c.get(n + this.b);
        }
        return 0;
    }

    public Table a(Table table) {
        int n = this.a(6);
        if (n != 0) {
            return this.a(table, n);
        }
        return null;
    }

    public void a(int n, ByteBuffer byteBuffer) {
        this.b = n;
        this.c = byteBuffer;
    }

    public byte b() {
        int n = this.a(8);
        if (n != 0) {
            return this.c.get(n + this.b);
        }
        return 0;
    }

    public Features b(int n, ByteBuffer byteBuffer) {
        this.a(n, byteBuffer);
        return this;
    }
}

