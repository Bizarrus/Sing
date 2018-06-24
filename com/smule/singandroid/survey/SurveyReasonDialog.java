package com.smule.singandroid.survey;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ListView;
import com.smule.android.logging.Log;
import com.smule.android.utils.NotificationCenter;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import java.util.Observable;
import java.util.Observer;

public class SurveyReasonDialog extends CustomAlertDialog {
    private static final String f24335c = SurveyReasonDialog.class.getName();
    private SurveyConfig f24336d;

    class C49381 implements CustomAlertDialogListener {
        final /* synthetic */ SurveyReasonDialog f24331a;

        C49381(SurveyReasonDialog surveyReasonDialog) {
            this.f24331a = surveyReasonDialog;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            this.f24331a.m19810b(false);
            SurveyPresenter.m25508a().m25552d();
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
            this.f24331a.m19812c(false);
            SurveyPresenter.m25508a().m25553e();
        }
    }

    class C49392 implements Observer {
        final /* synthetic */ SurveyReasonDialog f24332a;

        C49392(SurveyReasonDialog surveyReasonDialog) {
            this.f24332a = surveyReasonDialog;
        }

        public void update(Observable observable, Object obj) {
            if (obj instanceof ReasonInterface) {
                SurveyPresenter.m25508a().m25545a((ReasonInterface) obj);
            } else {
                Log.e(SurveyReasonDialog.f24335c, "SurveyReasonAdapter.SELECTION_CHANGED should only broadcast ReasonInterface");
            }
        }
    }

    public SurveyReasonDialog(@NonNull Context context, @NonNull SurveyConfig surveyConfig, @Nullable CustomAlertDialog customAlertDialog) {
        super(context, C1947R.layout.rating_reasons_contents, true, true, true, customAlertDialog);
        this.f24336d = surveyConfig;
        ListView listView = (ListView) findViewById(C1947R.id.reason_list);
        m19805a(this.f24336d.mo6945g());
        listView.setAdapter(new SurveyReasonAdapter(context, C1947R.layout.rating_reason_item, this.f24336d.mo6947i()));
        m19806a(context.getResources().getString(C1947R.string.core_ok), this.f24336d.mo6946h());
        m19810b(false);
        m19803a(new C49381(this));
        final Observer c49392 = new C49392(this);
        NotificationCenter.m19011a().m19014a(SurveyReasonAdapter.f24329b, c49392);
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ SurveyReasonDialog f24334b;

            public void onDismiss(DialogInterface dialogInterface) {
                NotificationCenter.m19011a().m19016b(SurveyReasonAdapter.f24329b, c49392);
            }
        });
    }
}
