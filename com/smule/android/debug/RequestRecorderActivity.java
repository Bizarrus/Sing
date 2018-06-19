package com.smule.android.debug;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.smule.android.C3482R;
import com.smule.android.debug.RequestRecorder.State;
import java.io.File;
import java.util.Arrays;

public class RequestRecorderActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C3482R.layout.request_recorder_activity);
    }

    protected void onResume() {
        int i;
        super.onResume();
        switch (RequestRecorder.m17731a().m17742b()) {
            case RECORDING:
                i = C3482R.id.radio_recording;
                break;
            case START_ON_NEXT_LAUNCH:
                i = C3482R.id.radio_next_launch;
                break;
            default:
                i = C3482R.id.radio_inactive;
                break;
        }
        ((RadioButton) findViewById(i)).setChecked(true);
        m17749a();
    }

    public void onRadioButtonClicked(View view) {
        if (((RadioButton) view).isChecked()) {
            RequestRecorder a = RequestRecorder.m17731a();
            int id = view.getId();
            if (id == C3482R.id.radio_inactive) {
                a.m17741a(State.INACTIVE);
                m17749a();
            } else if (id == C3482R.id.radio_recording) {
                a.m17741a(State.RECORDING);
            } else if (id == C3482R.id.radio_next_launch) {
                a.m17741a(State.START_ON_NEXT_LAUNCH);
            }
        }
    }

    private void m17749a() {
        final File[] g = RequestRecorder.m17731a().m17747g();
        Arrays.sort(g);
        ((ListView) findViewById(C3482R.id.list_files)).setAdapter(new BaseAdapter(this) {
            final /* synthetic */ RequestRecorderActivity f15940b;

            public int getCount() {
                return g.length;
            }

            public boolean hasStableIds() {
                return true;
            }

            public Object getItem(int i) {
                return g[i];
            }

            public long getItemId(int i) {
                return (long) i;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = LayoutInflater.from(this.f15940b).inflate(C3482R.layout.request_row, viewGroup, false);
                }
                File file = g[i];
                view.setTag(file);
                ((TextView) view.findViewById(C3482R.id.text)).setText(file.getName());
                return view;
            }
        });
    }

    private File m17748a(View view) {
        return (File) ((View) view.getParent()).getTag();
    }

    public void onShare(View view) {
        File a = m17748a(view);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(a));
        intent.addFlags(1);
        intent.setType("*/*");
        startActivity(Intent.createChooser(intent, "Share Recording"));
    }

    public void onDelete(View view) {
        m17748a(view).delete();
        m17749a();
    }
}
