/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcelable
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewParent
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.RadioButton
 *  android.widget.TextView
 */
package com.smule.android.debug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.smule.android.R;
import com.smule.android.debug.RequestRecorder;
import java.io.File;
import java.util.Arrays;

public class RequestRecorderActivity
extends Activity {
    private File a(View view) {
        return (File)((View)view.getParent()).getTag();
    }

    private void a() {
        Object[] arrobject = RequestRecorder.a().g();
        Arrays.sort(arrobject);
        ((ListView)this.findViewById(R.id.list_files)).setAdapter((ListAdapter)new BaseAdapter((File[])arrobject){
            final /* synthetic */ File[] a;
            {
                this.a = arrfile;
            }

            public int getCount() {
                return this.a.length;
            }

            public Object getItem(int n) {
                return this.a[n];
            }

            public long getItemId(int n) {
                return n;
            }

            /*
             * Enabled aggressive block sorting
             */
            public View getView(int n, View view, ViewGroup object) {
                if (view == null) {
                    view = LayoutInflater.from((Context)RequestRecorderActivity.this).inflate(R.layout.request_row, (ViewGroup)object, false);
                }
                object = this.a[n];
                view.setTag(object);
                ((TextView)view.findViewById(R.id.text)).setText((CharSequence)object.getName());
                return view;
            }

            public boolean hasStableIds() {
                return true;
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.request_recorder_activity);
    }

    public void onDelete(View view) {
        this.a(view).delete();
        this.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onRadioButtonClicked(View view) {
        if (!((RadioButton)view).isChecked()) return;
        {
            RequestRecorder requestRecorder = RequestRecorder.a();
            int n = view.getId();
            if (n == R.id.radio_inactive) {
                requestRecorder.a(RequestRecorder.State.a);
                this.a();
                return;
            } else {
                if (n == R.id.radio_recording) {
                    requestRecorder.a(RequestRecorder.State.b);
                    return;
                }
                if (n != R.id.radio_next_launch) return;
                {
                    requestRecorder.a(RequestRecorder.State.c);
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onResume() {
        int n;
        super.onResume();
        switch (.a[RequestRecorder.a().b().ordinal()]) {
            default: {
                n = R.id.radio_inactive;
                break;
            }
            case 2: {
                n = R.id.radio_recording;
                break;
            }
            case 3: {
                n = R.id.radio_next_launch;
            }
        }
        ((RadioButton)this.findViewById(n)).setChecked(true);
        this.a();
    }

    public void onShare(View object) {
        object = this.a((View)object);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", (Parcelable)Uri.fromFile((File)object));
        intent.addFlags(1);
        intent.setType("*/*");
        this.startActivity(Intent.createChooser((Intent)intent, (CharSequence)"Share Recording"));
    }

}

