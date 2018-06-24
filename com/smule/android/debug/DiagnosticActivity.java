/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.BaseAdapter
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  android.widget.TextView
 */
package com.smule.android.debug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.R;
import com.smule.android.debug.FacebookDebugActivity;
import com.smule.android.debug.RequestRecorderActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiagnosticActivity
extends Activity {
    public static final String a = DiagnosticActivity.class.getName();
    public static final String b = DiagnosticActivity.class.getName() + ".actions";
    private List<Intent> c;

    private List<Intent> a() {
        ArrayList<Intent> arrayList = new ArrayList<Intent>();
        Object object = new Intent((Context)this, RequestRecorderActivity.class);
        object.putExtra("android.intent.extra.TITLE", "HTTP Request Recorder");
        arrayList.add((Intent)object);
        object = new Intent((Context)this, FacebookDebugActivity.class);
        object.putExtra("android.intent.extra.TITLE", "Facebook Debugger");
        arrayList.add((Intent)object);
        object = this.getIntent().getParcelableArrayListExtra(b);
        if (object != null) {
            arrayList.addAll((Collection<Intent>)object);
        }
        return arrayList;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this.a();
        this.setContentView(R.layout.diagnostic_activity);
    }

    protected void onResume() {
        super.onResume();
        final ListView listView = (ListView)this.findViewById(R.id.diagnostic_list_view);
        listView.setAdapter((ListAdapter)new BaseAdapter(){

            public int getCount() {
                return DiagnosticActivity.this.c.size();
            }

            public Object getItem(int n) {
                return DiagnosticActivity.this.c.get(n);
            }

            public long getItemId(int n) {
                return n;
            }

            /*
             * Enabled aggressive block sorting
             */
            public View getView(int n, View view, ViewGroup viewGroup) {
                view = view != null && view instanceof TextView ? (TextView)view : (TextView)LayoutInflater.from((Context)DiagnosticActivity.this).inflate(R.layout.diagnostic_label, (ViewGroup)listView, false);
                view.setText((CharSequence)((Intent)DiagnosticActivity.this.c.get(n)).getStringExtra("android.intent.extra.TITLE"));
                view.setTag((Object)n);
                return view;
            }

            public boolean hasStableIds() {
                return true;
            }
        });
    }

    public void onTap(View view) {
        int n = (Integer)view.getTag();
        this.startActivity(this.c.get(n));
    }

}

