/*
 * Decompiled with CFR 0_123.
 */
package com.smule.alycegpu;

import com.smule.alycegpu.RenderInput;
import com.smule.alycegpu.RenderOutput;
import com.smule.alycegpu.RendererState;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ClientPreviewRenderer {
    public static native ClientPreviewRenderer instantiate();

    public abstract void render(Object var1, RendererState var2, RenderInput var3, RenderInput var4, RenderOutput var5);

    public abstract String setup(Object var1, String var2);

    private static final class CppProxy
    extends ClientPreviewRenderer {
        static final /* synthetic */ boolean $assertionsDisabled;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        /*
         * Enabled aggressive block sorting
         */
        static {
            boolean bl = !ClientPreviewRenderer.class.desiredAssertionStatus();
            $assertionsDisabled = bl;
        }

        private CppProxy(long l) {
            if (l == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = l;
        }

        private native void nativeDestroy(long var1);

        private native void native_render(long var1, Object var3, RendererState var4, RenderInput var5, RenderInput var6, RenderOutput var7);

        private native String native_setup(long var1, Object var3, String var4);

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
        public void render(Object object, RendererState rendererState, RenderInput renderInput, RenderInput renderInput2, RenderOutput renderOutput) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            this.native_render(this.nativeRef, object, rendererState, renderInput, renderInput2, renderOutput);
        }

        @Override
        public String setup(Object object, String string2) {
            if (!$assertionsDisabled && this.destroyed.get()) {
                throw new AssertionError((Object)"trying to use a destroyed object");
            }
            return this.native_setup(this.nativeRef, object, string2);
        }
    }

}

