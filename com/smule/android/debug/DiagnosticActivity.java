package com.smule.android.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.smule.android.C3482R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DiagnosticActivity extends Activity {
    public static final String f15898a = (DiagnosticActivity.class.getName() + ".actions");
    private List<Intent> f15899b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f15899b = m17703a();
        setContentView(C3482R.layout.diagnostic_activity);
    }

    protected void onResume() {
        super.onResume();
        final ListView listView = (ListView) findViewById(C3482R.id.list_view);
        listView.setAdapter(new BaseAdapter(this) {
            final /* synthetic */ DiagnosticActivity f15897b;

            public boolean hasStableIds() {
                return true;
            }

            public int getCount() {
                return this.f15897b.f15899b.size();
            }

            public Object getItem(int i) {
                return this.f15897b.f15899b.get(i);
            }

            public long getItemId(int i) {
                return (long) i;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null || !(view instanceof TextView)) {
                    view = (TextView) LayoutInflater.from(this.f15897b).inflate(C3482R.layout.diagnostic_label, listView, false);
                } else {
                    view = (TextView) view;
                }
                view.setText(((Intent) this.f15897b.f15899b.get(i)).getStringExtra("android.intent.extra.TITLE"));
                view.setTag(Integer.valueOf(i));
                return view;
            }
        });
    }

    private List<Intent> m17703a() {
        List<Intent> arrayList = new ArrayList();
        Intent intent = new Intent(this, RequestRecorderActivity.class);
        intent.putExtra("android.intent.extra.TITLE", "HTTP Request Recorder");
        arrayList.add(intent);
        Collection parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(f15898a);
        if (parcelableArrayListExtra != null) {
            arrayList.addAll(parcelableArrayListExtra);
        }
        return arrayList;
    }

    public void onTap(View view) {
        startActivity((Intent) this.f15899b.get(((Integer) view.getTag()).intValue()));
    }
}
