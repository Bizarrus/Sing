package com.smule.singandroid.task;

import android.os.AsyncTask;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;
import com.smule.android.songbook.ArrangementVersionLiteEntry;

public class PreDownloadArrangementTask extends AsyncTask<Void, Void, ArrangementVersionResponse> {
    ArrangementVersionLiteEntry f24451a;
    DownloadListener f24452b;

    public interface DownloadListener {
        void mo6894a(ArrangementVersionResponse arrangementVersionResponse);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25662a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25663a((ArrangementVersionResponse) obj);
    }

    public PreDownloadArrangementTask(ArrangementVersionLiteEntry arrangementVersionLiteEntry, DownloadListener downloadListener) {
        this.f24451a = arrangementVersionLiteEntry;
        this.f24452b = downloadListener;
    }

    protected ArrangementVersionResponse m25662a(Void... voidArr) {
        return ArrangementManager.a().b(this.f24451a.mo6289c());
    }

    protected void m25663a(ArrangementVersionResponse arrangementVersionResponse) {
        if (this.f24452b != null) {
            this.f24452b.mo6894a(arrangementVersionResponse);
        }
    }
}
