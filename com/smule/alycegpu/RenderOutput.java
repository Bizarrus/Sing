/*
 * Decompiled with CFR 0_123.
 */
package com.smule.alycegpu;

public final class RenderOutput {
    final boolean mFlipHorizontally;
    final boolean mFlipVertically;
    final int mFramebufferId;
    final int mHeight;
    final int mWidth;

    public RenderOutput(int n, int n2, int n3, boolean bl, boolean bl2) {
        this.mFramebufferId = n;
        this.mWidth = n2;
        this.mHeight = n3;
        this.mFlipVertically = bl;
        this.mFlipHorizontally = bl2;
    }

    public boolean getFlipHorizontally() {
        return this.mFlipHorizontally;
    }

    public boolean getFlipVertically() {
        return this.mFlipVertically;
    }

    public int getFramebufferId() {
        return this.mFramebufferId;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public String toString() {
        return "RenderOutput{mFramebufferId=" + this.mFramebufferId + "," + "mWidth=" + this.mWidth + "," + "mHeight=" + this.mHeight + "," + "mFlipVertically=" + this.mFlipVertically + "," + "mFlipHorizontally=" + this.mFlipHorizontally + "}";
    }
}

