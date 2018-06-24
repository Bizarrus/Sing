/*
 * Decompiled with CFR 0_123.
 */
package com.smule.alycegpu;

import com.smule.alycegpu.ColorFilter;
import com.smule.alycegpu.ParticleIntensity;
import com.smule.alycegpu.SmoothingEffectType;
import com.smule.alycegpu.TimedLayoutType;
import com.smule.alycegpu.VideoStyle;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class RendererState {
    public static native RendererState instantiate();

    public abstract void addTimedLayout(TimedLayoutType var1, float var2);

    public abstract void clearTimedLayouts();

    public abstract void convertAllLayoutsToDuet();

    public abstract ColorFilter getColorFilter();

    public abstract float getCurrentTime();

    public abstract int getLayoutCount();

    public abstract ParticleIntensity getParticleIntensity();

    public abstract float getPendingSmoothingEffectAnimationDuration();

    public abstract float getPendingSmoothingEffectParticleAlpha();

    public abstract int getProcessingWidth();

    public abstract boolean getRenderOnlyColorFilter();

    public abstract SmoothingEffectType getSmoothingEffectType();

    public abstract int getUserInputIndex();

    public abstract VideoStyle getVideoStyle();

    public abstract float getVocalsIntensity();

    public abstract void setColorFilter(ColorFilter var1);

    public abstract void setCurrentTime(float var1);

    public abstract void setParticleIntensity(ParticleIntensity var1);

    public abstract void setPendingSmoothingEffectAnimation(float var1, float var2);

    public abstract void setProcessingWidth(int var1);

    public abstract void setRenderOnlyColorFilter(boolean var1);

    public abstract void setSmoothingEffectType(SmoothingEffectType var1);

    public abstract void setUserInputIndex(int var1);

    public abstract void setVideoStyle(VideoStyle var1);

    public abstract void setVocalsIntensity(float var1);

    public abstract void setupLoopingTimedLayouts(boolean var1);

    private static final class CppProxy
    extends RendererState {
        static final /* synthetic */ boolean $assertionsDisabled;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        /*
         * Enabled aggressive block sorting
         */
        static {
            boolean bl = !RendererState.class.desiredAssertionStatus();
            $assertionsDisabled = bl;
        }

        private CppProxy(long l) {
            if (l == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = l;
        }

        private native void nativeDestroy(long var1);

        private native void native_addTimedLayout(long var1, TimedLayoutType var3, float var4);

        private native void native_clearTimedLayouts(long var1);

        private native void native_convertAllLayoutsToDuet(long var1);

        private native ColorFilter native_getColorFilter(long var1);

        private native float native_getCurrentTime(long var1);

        private native int native_getLayoutCount(long var1);

        private native ParticleIntensity native_getParticleIntensity(long var1);

        private native float native_getPendingSmoothingEffectAnimationDuration(long var1);

        private native float native_getPendingSmoothingEffectParticleAlpha(long var1);

        private native int native_getProcessingWidth(long var1);

        private native boolean native_getRenderOnlyColorFilter(long var1);

        private native SmoothingEffectType native_getSmoothingEffectType(long var1);

        private native int native_getUserInputIndex(long var1);

        private native VideoStyle native_getVideoStyle(long var1);

        private native float native_getVocalsIntensity(long var1);

        private native void native_setColorFilter(long var1, ColorFilter var3);

        private native void native_setCurrentTime(long var1, float var3);

        private native void native_setParticleIntensity(long var1, ParticleIntensity var3);

        private native void native_setPendingSmoothingEffectAnimation(long var1, float var3, float var4);

        private native void native_setProcessingWidth(long var1, int var3);

        private native void native_setRenderOnlyColorFilter(long var1, boolean var3);

        private native void native_setSmoothingEffectType(long var1, SmoothingEffectType var3);

        private native void native_setUserInputIndex(long var1, int var3);

        private native void native_setVideoStyle(long var1, VideoStyle var3);

        private native void native_setVocalsIntensity(long var1, float var3);

        private native void native_setupLoopingTimedLayouts(long var1, boolean var3);

        @Override
        public void addTimedLayout(TimedLayoutType timedLayoutType, float f) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_addTimedLayout(this.nativeRef, timedLayoutType, f);
        }

        @Override
        public void clearTimedLayouts() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_clearTimedLayouts(this.nativeRef);
        }

        @Override
        public void convertAllLayoutsToDuet() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_convertAllLayoutsToDuet(this.nativeRef);
        }

        public void destroy() {
            if (!this.destroyed.getAndSet(true)) {
                this.nativeDestroy(this.nativeRef);
            }
        }

        protected void finalize() throws Throwable {
            this.destroy();
            Object.super.finalize();
        }

        @Override
        public ColorFilter getColorFilter() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getColorFilter(this.nativeRef);
        }

        @Override
        public float getCurrentTime() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getCurrentTime(this.nativeRef);
        }

        @Override
        public int getLayoutCount() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getLayoutCount(this.nativeRef);
        }

        @Override
        public ParticleIntensity getParticleIntensity() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getParticleIntensity(this.nativeRef);
        }

        @Override
        public float getPendingSmoothingEffectAnimationDuration() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getPendingSmoothingEffectAnimationDuration(this.nativeRef);
        }

        @Override
        public float getPendingSmoothingEffectParticleAlpha() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getPendingSmoothingEffectParticleAlpha(this.nativeRef);
        }

        @Override
        public int getProcessingWidth() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getProcessingWidth(this.nativeRef);
        }

        @Override
        public boolean getRenderOnlyColorFilter() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getRenderOnlyColorFilter(this.nativeRef);
        }

        @Override
        public SmoothingEffectType getSmoothingEffectType() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getSmoothingEffectType(this.nativeRef);
        }

        @Override
        public int getUserInputIndex() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getUserInputIndex(this.nativeRef);
        }

        @Override
        public VideoStyle getVideoStyle() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getVideoStyle(this.nativeRef);
        }

        @Override
        public float getVocalsIntensity() {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_getVocalsIntensity(this.nativeRef);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setColorFilter(this.nativeRef, colorFilter);
        }

        @Override
        public void setCurrentTime(float f) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setCurrentTime(this.nativeRef, f);
        }

        @Override
        public void setParticleIntensity(ParticleIntensity particleIntensity) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setParticleIntensity(this.nativeRef, particleIntensity);
        }

        @Override
        public void setPendingSmoothingEffectAnimation(float f, float f2) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setPendingSmoothingEffectAnimation(this.nativeRef, f, f2);
        }

        @Override
        public void setProcessingWidth(int n) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setProcessingWidth(this.nativeRef, n);
        }

        @Override
        public void setRenderOnlyColorFilter(boolean bl) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setRenderOnlyColorFilter(this.nativeRef, bl);
        }

        @Override
        public void setSmoothingEffectType(SmoothingEffectType smoothingEffectType) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setSmoothingEffectType(this.nativeRef, smoothingEffectType);
        }

        @Override
        public void setUserInputIndex(int n) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setUserInputIndex(this.nativeRef, n);
        }

        @Override
        public void setVideoStyle(VideoStyle videoStyle) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setVideoStyle(this.nativeRef, videoStyle);
        }

        @Override
        public void setVocalsIntensity(float f) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setVocalsIntensity(this.nativeRef, f);
        }

        @Override
        public void setupLoopingTimedLayouts(boolean bl) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_setupLoopingTimedLayouts(this.nativeRef, bl);
        }
    }

}

