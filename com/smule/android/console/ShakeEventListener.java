/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 */
package com.smule.android.console;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ShakeEventListener
implements SensorEventListener {
    private long a = 0;
    private long b;
    private int c = 0;
    private float d = 0.0f;
    private float e = 0.0f;
    private float f = 0.0f;
    private OnShakeListener g;

    private void a() {
        this.a = 0;
        this.c = 0;
        this.b = 0;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
    }

    public void onAccuracyChanged(Sensor sensor, int n) {
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        block6 : {
            block5 : {
                float f = sensorEvent.values[0];
                float f2 = sensorEvent.values[1];
                float f3 = sensorEvent.values[2];
                if (Math.abs(f + f2 + f3 - this.d - this.e - this.f) <= 10.0f) break block5;
                long l = System.currentTimeMillis();
                if (this.a == 0) {
                    this.a = l;
                    this.b = l;
                }
                if (l - this.b >= 200) break block6;
                this.b = l;
                ++this.c;
                this.d = f;
                this.e = f2;
                this.f = f3;
                if (this.c >= 5 && l - this.a < 700) {
                    this.g.a();
                    this.a();
                }
            }
            return;
        }
        this.a();
    }

    public static interface OnShakeListener {
        public void a();
    }

}

