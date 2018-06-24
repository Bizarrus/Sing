/*
 * Decompiled with CFR 0_123.
 */
package com.smule.alycegpu;

public final class RenderInput {
    final boolean mFlipHorizontally;
    final boolean mFlipVertically;
    final int mHeight;
    final int mTextureId;
    final float mTimestampInSeconds;
    final int mWidth;

    public RenderInput(int n, int n2, int n3, boolean bl, boolean bl2, float f) {
        this.mTextureId = n;
        this.mWidth = n2;
        this.mHeight = n3;
        this.mFlipVertically = bl;
        this.mFlipHorizontally = bl2;
        this.mTimestampInSeconds = f;
    }

    public boolean getFlipHorizontally() {
        return this.mFlipHorizontally;
    }

    public boolean getFlipVertically() {
        return this.mFlipVertically;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getTextureId() {
        return this.mTextureId;
    }

    public float getTimestampInSeconds() {
        return this.mTimestampInSeconds;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public String toString() {
        return "RenderInput{mTextureId=" + this.mTextureId + "," + "mWidth=" + this.mWidth + "," + "mHeight=" + this.mHeight + "," + "mFlipVertically=" + this.mFlipVertically + "," + "mFlipHorizontally=" + this.mFlipHorizontally + "," + "mTimestampInSeconds=" + this.mTimestampInSeconds + "}";
    }
}

