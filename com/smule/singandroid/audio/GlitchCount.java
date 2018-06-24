/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.audio;

public class GlitchCount {
    private int fileGlitches;
    private int inputCalls;
    private int inputGlitches;
    private int ioWarmup;
    private int outputCalls;
    private int outputGlitches;
    private int playbackGlitches;

    public GlitchCount(int n, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.inputGlitches = n;
        this.outputGlitches = n2;
        this.fileGlitches = n3;
        this.inputCalls = n4;
        this.outputCalls = n5;
        this.ioWarmup = n6;
        this.playbackGlitches = n7;
    }

    public int getFileGlitches() {
        return this.fileGlitches;
    }

    public int getInputCalls() {
        return this.inputCalls;
    }

    public int getInputGlitches() {
        return this.inputGlitches;
    }

    public int getIoWarmup() {
        return this.ioWarmup;
    }

    public int getOutputCalls() {
        return this.outputCalls;
    }

    public int getOutputGlitches() {
        return this.outputGlitches;
    }

    public int getPlaybackGlitches() {
        return this.playbackGlitches;
    }
}

