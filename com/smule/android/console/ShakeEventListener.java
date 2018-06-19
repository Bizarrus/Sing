package com.smule.android.console;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class ShakeEventListener implements SensorEventListener {
    private long f15767a = 0;
    private long f15768b;
    private int f15769c = 0;
    private float f15770d = 0.0f;
    private float f15771e = 0.0f;
    private float f15772f = 0.0f;
    private OnShakeListener f15773g;

    public interface OnShakeListener {
        void m17566a();
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float f = sensorEvent.values[0];
        float f2 = sensorEvent.values[1];
        float f3 = sensorEvent.values[2];
        if (Math.abs(((((f + f2) + f3) - this.f15770d) - this.f15771e) - this.f15772f) > 10.0f) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.f15767a == 0) {
                this.f15767a = currentTimeMillis;
                this.f15768b = currentTimeMillis;
            }
            if (currentTimeMillis - this.f15768b < 200) {
                this.f15768b = currentTimeMillis;
                this.f15769c++;
                this.f15770d = f;
                this.f15771e = f2;
                this.f15772f = f3;
                if (this.f15769c >= 5 && currentTimeMillis - this.f15767a < 700) {
                    this.f15773g.m17566a();
                    m17567a();
                    return;
                }
                return;
            }
            m17567a();
        }
    }

    private void m17567a() {
        this.f15767a = 0;
        this.f15769c = 0;
        this.f15768b = 0;
        this.f15770d = 0.0f;
        this.f15771e = 0.0f;
        this.f15772f = 0.0f;
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
