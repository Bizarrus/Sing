/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat.smerialization;

import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;

public interface Smerializable {
    public String O();

    public void a(SmerializableInputStream var1) throws IOException;

    public void a(SmerializableOutputStream var1) throws IOException;
}

