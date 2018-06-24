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

public final class FlattenedFeatures
extends Table {
    public static int a(FlatBufferBuilder flatBufferBuilder, float[] arrf) {
        flatBufferBuilder.a(4, arrf.length, 4);
        for (int i = arrf.length - 1; i >= 0; --i) {
            flatBufferBuilder.b(arrf[i]);
        }
        return flatBufferBuilder.b();
    }

    public ByteBuffer a() {
        return this.a(4, 4);
    }
}

