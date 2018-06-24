package com.smule.singandroid.survey;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.C1947R;
import java.util.List;

public class SurveyReasonAdapter extends ArrayAdapter<ReasonInterface> {
    public static final String f24328a = SurveyReasonAdapter.class.getName();
    public static final String f24329b = (f24328a + "SELECTION_CHANGED");
    private int f24330c = -1;

    class C49371 implements OnClickListener {
        final /* synthetic */ SurveyReasonAdapter f24327a;

        C49371(SurveyReasonAdapter surveyReasonAdapter) {
            this.f24327a = surveyReasonAdapter;
        }

        public void onClick(View view) {
            this.f24327a.f24330c = ((Integer) view.getTag()).intValue();
            NotificationCenter.m19011a().m19012a(SurveyReasonAdapter.f24329b, this.f24327a.getItem(this.f24327a.f24330c));
            this.f24327a.notifyDataSetInvalidated();
        }
    }

    public SurveyReasonAdapter(@NonNull Context context, int i, List<ReasonInterface> list) {
        super(context, i, list);
    }

    @NonNull
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        boolean z = false;
        if (view == null) {
            view = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C1947R.layout.rating_reason_item, viewGroup, false);
        }
        RadioButton radioButton = (RadioButton) view.findViewById(C1947R.id.radio_button);
        if (i == this.f24330c) {
            z = true;
        }
        radioButton.setChecked(z);
        view.setTag(Integer.valueOf(i));
        view.setOnClickListener(new C49371(this));
        TextView textView = (TextView) view.findViewById(C1947R.id.reason_text);
        if (textView != null) {
            ReasonInterface reasonInterface = (ReasonInterface) getItem(i);
            if (reasonInterface != null) {
                textView.setText(getContext().getResources().getString(reasonInterface.mo6951b()));
            }
        }
        return view;
    }
}
