package com.smule.singandroid.video;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.view.Surface;
import com.google.android.gms.common.Scopes;
import com.smule.android.logging.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

@TargetApi(19)
public class VideoEncoderCore {
    private static final String f25426a = (VideoEncoderCore.class.getSimpleName() + ":VIDEO");
    private Surface f25427b;
    private MediaMuxer f25428c;
    private MediaCodec f25429d;
    private BufferInfo f25430e = new BufferInfo();
    private int f25431f;
    private boolean f25432g;
    private int f25433h = 0;
    private long f25434i;

    public VideoEncoderCore(int i, int i2, int i3, int i4, File file) throws IOException {
        MediaFormat createVideoFormat = MediaFormat.createVideoFormat("video/avc", i, i2);
        createVideoFormat.setInteger("color-format", 2130708361);
        createVideoFormat.setInteger("bitrate", i3);
        createVideoFormat.setInteger("frame-rate", i4);
        createVideoFormat.setInteger("i-frame-interval", 2);
        this.f25429d = MediaCodec.createEncoderByType("video/avc");
        for (CodecProfileLevel codecProfileLevel : this.f25429d.getCodecInfo().getCapabilitiesForType("video/avc").profileLevels) {
            if (codecProfileLevel.profile == 8 && codecProfileLevel.level == 64) {
                createVideoFormat.setInteger(Scopes.PROFILE, 8);
                createVideoFormat.setInteger("level", 64);
                Log.b(f25426a, "AVC high@L2.1 selected");
                break;
            }
            if (codecProfileLevel.profile == 8) {
                Log.b(f25426a, "AVC high available:" + codecProfileLevel.level);
            }
        }
        this.f25429d.configure(createVideoFormat, null, null, 1);
        this.f25427b = this.f25429d.createInputSurface();
        this.f25429d.start();
        this.f25428c = new MediaMuxer(file.toString(), 0);
        this.f25431f = -1;
        this.f25432g = false;
    }

    public Surface m26562a() {
        return this.f25427b;
    }

    public int m26564b() {
        return this.f25433h;
    }

    public void m26565c() {
        if (this.f25429d != null) {
            this.f25429d.stop();
            this.f25429d.release();
            this.f25429d = null;
        }
        if (this.f25428c != null) {
            if (this.f25432g) {
                this.f25428c.stop();
            }
            this.f25428c.release();
            this.f25428c = null;
        }
    }

    public void m26563a(boolean z) {
        if (!z || this.f25432g) {
            if (z) {
                this.f25429d.signalEndOfInputStream();
            }
            ByteBuffer[] outputBuffers = this.f25429d.getOutputBuffers();
            while (true) {
                int dequeueOutputBuffer = this.f25429d.dequeueOutputBuffer(this.f25430e, 10000);
                if (dequeueOutputBuffer == -1) {
                    if (!z) {
                        return;
                    }
                } else if (dequeueOutputBuffer == -3) {
                    outputBuffers = this.f25429d.getOutputBuffers();
                } else if (dequeueOutputBuffer == -2) {
                    if (this.f25432g) {
                        throw new RuntimeException("format changed twice");
                    }
                    MediaFormat outputFormat = this.f25429d.getOutputFormat();
                    Log.b(f25426a, "encoder output format changed: " + outputFormat);
                    this.f25431f = this.f25428c.addTrack(outputFormat);
                    this.f25428c.start();
                    this.f25432g = true;
                    this.f25434i = Long.MIN_VALUE;
                } else if (dequeueOutputBuffer < 0) {
                    Log.d(f25426a, "unexpected result from encoder.dequeueOutputBuffer: " + dequeueOutputBuffer);
                } else {
                    ByteBuffer byteBuffer = outputBuffers[dequeueOutputBuffer];
                    if (byteBuffer == null) {
                        throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                    }
                    if ((this.f25430e.flags & 2) != 0) {
                        this.f25430e.size = 0;
                    }
                    if (this.f25430e.size != 0) {
                        if (this.f25432g) {
                            byteBuffer.position(this.f25430e.offset);
                            byteBuffer.limit(this.f25430e.offset + this.f25430e.size);
                            if (this.f25430e.presentationTimeUs >= this.f25434i) {
                                this.f25428c.writeSampleData(this.f25431f, byteBuffer, this.f25430e);
                                this.f25433h++;
                                this.f25434i = this.f25430e.presentationTimeUs;
                            } else {
                                Log.e(f25426a, "PTS in the past.  Dropping encoded frame.");
                            }
                        } else {
                            throw new RuntimeException("muxer hasn't started");
                        }
                    }
                    this.f25429d.releaseOutputBuffer(dequeueOutputBuffer, false);
                    if ((this.f25430e.flags & 4) != 0) {
                        break;
                    }
                }
            }
            if (!z) {
                Log.d(f25426a, "reached end of stream unexpectedly");
                return;
            }
            return;
        }
        Log.c(f25426a, "muxer not started.  Ignoring EOS");
    }
}
