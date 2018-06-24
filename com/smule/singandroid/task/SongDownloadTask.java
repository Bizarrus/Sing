package com.smule.singandroid.task;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Analytics.UserPath;
import com.smule.android.logging.Log;
import com.smule.android.network.api.ResourceDownloader;
import com.smule.android.network.api.ResourceDownloader.DownloadResult;
import com.smule.android.network.core.NetworkUtils.ProgressListener;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.ArrangementManager.ArrangementVersionResponse;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.PerformanceManager.PerformanceResponse;
import com.smule.android.network.managers.StoreManager;
import com.smule.android.network.models.ArrangementVersion;
import com.smule.android.network.models.ArrangementVersion.Resource;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.network.models.ResourceV2;
import com.smule.android.network.models.SongV2;
import com.smule.android.songbook.ArrangementVersionLiteEntry;
import com.smule.android.songbook.ListingEntry;
import com.smule.android.songbook.SongbookEntry;
import com.smule.singandroid.utils.SingAnalytics;
import com.smule.singandroid.utils.SingAnalytics.SongDownloadFileType;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;

public class SongDownloadTask extends AsyncTask<Void, Integer, Boolean> {
    private static final String f24464b = SongDownloadTask.class.getName();
    private long f24465a;
    private Context f24466c;
    private SongbookEntry f24467d;
    private PerformanceV2 f24468e;
    private long f24469f;
    private DownloadListener f24470g;
    private DownloadProgressListener f24471h;
    private NetworkListener f24472i;
    private boolean f24473j;
    private boolean f24474k;
    private boolean f24475l;
    private boolean f24476m;
    private boolean f24477n;
    private boolean f24478o;
    private SongDownloadFileType f24479p;
    private boolean f24480q;
    private SongDownloadFileType f24481r;
    private UserPath f24482s;
    private boolean f24483t;
    private boolean f24484u;

    public interface DownloadListener {
        void mo6541a(boolean z, SongbookEntry songbookEntry, PerformanceV2 performanceV2);
    }

    public interface DownloadProgressListener {
        void mo6818a(int i);
    }

    private class NetworkListener implements ProgressListener {
        long f24457a;
        long f24458b;
        long f24459c;
        long f24460d;
        int f24461e;
        boolean f24462f;
        final /* synthetic */ SongDownloadTask f24463g;

        private NetworkListener(SongDownloadTask songDownloadTask) {
            this.f24463g = songDownloadTask;
        }

        public void mo6954a(long j) {
            this.f24457a += j;
            if (this.f24462f) {
                this.f24459c = j;
                SongDownloadTask songDownloadTask = this.f24463g;
                Integer[] numArr = new Integer[1];
                numArr[0] = Integer.valueOf(this.f24459c > 0 ? 0 : -1);
                songDownloadTask.publishProgress(numArr);
            }
        }

        public void mo6955b(long j) {
            this.f24458b += j;
            if (this.f24462f) {
                this.f24460d += j;
                this.f24461e = (int) ((((float) this.f24460d) / ((float) this.f24459c)) * 100.0f);
            }
        }

        public void mo6953a() {
            if (this.f24462f && this.f24457a > 0) {
                this.f24463g.publishProgress(new Integer[]{Integer.valueOf(this.f24461e)});
            }
        }

        void m25668a(boolean z) {
            this.f24462f = z;
            this.f24461e = 0;
            long j = (long) null;
            this.f24460d = j;
            this.f24459c = j;
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m25681a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m25685a((Boolean) obj);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        m25686a((Integer[]) objArr);
    }

    public SongDownloadTask(Context context, SongbookEntry songbookEntry, UserPath userPath) {
        this(context, songbookEntry, null, null);
        this.f24482s = userPath;
    }

    public SongDownloadTask(Context context, SongbookEntry songbookEntry, DownloadListener downloadListener, DownloadProgressListener downloadProgressListener) {
        this.f24472i = new NetworkListener();
        this.f24473j = false;
        this.f24474k = false;
        this.f24475l = false;
        this.f24476m = false;
        this.f24477n = false;
        this.f24478o = false;
        this.f24480q = false;
        this.f24482s = UserPath.OTHER;
        this.f24483t = true;
        this.f24484u = false;
        this.f24471h = downloadProgressListener;
        m25670a(context, songbookEntry, downloadListener, null);
    }

    public SongDownloadTask(Context context, SongbookEntry songbookEntry, PerformanceV2 performanceV2, DownloadListener downloadListener, DownloadProgressListener downloadProgressListener) {
        this.f24472i = new NetworkListener();
        this.f24473j = false;
        this.f24474k = false;
        this.f24475l = false;
        this.f24476m = false;
        this.f24477n = false;
        this.f24478o = false;
        this.f24480q = false;
        this.f24482s = UserPath.OTHER;
        this.f24483t = true;
        this.f24484u = false;
        this.f24471h = downloadProgressListener;
        m25670a(context, songbookEntry, downloadListener, performanceV2);
    }

    private void m25670a(Context context, SongbookEntry songbookEntry, DownloadListener downloadListener, PerformanceV2 performanceV2) {
        this.f24466c = context;
        this.f24467d = songbookEntry;
        this.f24468e = performanceV2;
        this.f24470g = downloadListener;
    }

    public void m25683a(DownloadListener downloadListener) {
        synchronized (this) {
            this.f24470g = downloadListener;
            if (this.f24473j) {
                this.f24470g.mo6541a(this.f24474k, this.f24467d, this.f24468e);
            }
        }
    }

    public void m25684a(DownloadProgressListener downloadProgressListener) {
        this.f24471h = downloadProgressListener;
        this.f24472i.mo6953a();
    }

    public void m25682a() {
        this.f24475l = true;
    }

    public void m25688b() {
        m25682a();
        this.f24476m = true;
    }

    public void m25689c() {
        this.f24477n = true;
    }

    protected Boolean m25681a(Void... voidArr) {
        this.f24465a = System.currentTimeMillis();
        boolean z = false;
        if (this.f24467d != null && (this.f24475l || m25674f())) {
            z = this.f24467d.m18773s() ? m25676h() : m25675g();
        }
        this.f24469f = System.currentTimeMillis() - this.f24465a;
        return Boolean.valueOf(z);
    }

    protected void m25686a(Integer... numArr) {
        super.onProgressUpdate(numArr);
        if (this.f24471h != null && numArr.length > 0) {
            this.f24471h.mo6818a(numArr[0].intValue());
        }
    }

    private boolean m25674f() {
        if (this.f24468e == null) {
            return true;
        }
        if (this.f24468e.r() && this.f24468e.arrangementVersion == null) {
            PerformanceResponse c = PerformanceManager.a().c(this.f24468e.performanceKey);
            if (c.a()) {
                this.f24468e = c.mPerformance;
            } else {
                Log.d(f24464b, "Failed to get performance details.");
                return false;
            }
        }
        if (isCancelled()) {
            m25680l();
            return false;
        } else if (!m25677i()) {
            return false;
        } else {
            if (this.f24468e.video) {
                Log.c(f24464b, "In downloadOpenCall() metadata was " + (m25678j() ? "" : "not") + " downloaded");
            }
            if (!isCancelled()) {
                return true;
            }
            m25680l();
            return false;
        }
    }

    private boolean m25675g() {
        if (isCancelled()) {
            m25680l();
            return false;
        }
        if (this.f24468e == null || this.f24468e.arrangementVersion == null) {
            ArrangementVersionLiteEntry arrangementVersionLiteEntry = (ArrangementVersionLiteEntry) this.f24467d;
            if (arrangementVersionLiteEntry.f17623a.arrangementVersion == null) {
                ArrangementVersionResponse b = ArrangementManager.a().b(arrangementVersionLiteEntry.mo6289c());
                if (b.mArrangementVersion != null) {
                    arrangementVersionLiteEntry.f17623a.a(b.mArrangementVersion);
                } else {
                    this.f24478o = true;
                    return false;
                }
            }
            if (!m25672a(arrangementVersionLiteEntry.f17623a.arrangementVersion)) {
                return false;
            }
        } else if (!m25672a(this.f24468e.arrangementVersion)) {
            return false;
        }
        if (!isCancelled()) {
            return true;
        }
        m25680l();
        return false;
    }

    private boolean m25676h() {
        if (this.f24467d == null) {
            return false;
        }
        SongV2 songV2;
        if (this.f24467d.m18773s()) {
            songV2 = ((ListingEntry) this.f24467d).f17626a.song;
        } else {
            songV2 = StoreManager.m18378a().m18416a(((ArrangementVersionLiteEntry) this.f24467d).f17623a.songId);
        }
        if (songV2 != null && (this.f24468e == null || !this.f24468e.r())) {
            SongV2 a = StoreManager.m18378a().m18415a(songV2);
            if (a != null) {
                songV2.a(a);
            } else if (songV2.resources == null || songV2.resources.size() == 0) {
                this.f24478o = true;
                return false;
            }
            if (isCancelled()) {
                m25680l();
                return false;
            } else if (!m25679k()) {
                return false;
            } else {
                if (songV2.a() && !this.f24475l) {
                    songV2.eTag = songV2.newEtag;
                    songV2.newEtag = null;
                }
                if (isCancelled()) {
                    m25680l();
                    return false;
                }
            }
        }
        return true;
    }

    public void m25690d() {
        this.f24469f = System.currentTimeMillis() - this.f24465a;
        m25680l();
        super.cancel(true);
    }

    @SuppressLint({"StandardSubStringNotAllowed"})
    private boolean m25677i() {
        if (this.f24468e.shortTermRenderedUrl != null) {
            String str = this.f24468e.shortTermRenderedUrl;
            str = str.substring(str.lastIndexOf("/") + 1);
            this.f24479p = SongDownloadFileType.M4A;
            this.f24472i.m25668a(true);
            DownloadResult downloadFileFromURL = ResourceDownloader.downloadFileFromURL(this.f24468e.shortTermRenderedUrl, str, this.f24466c, this.f24472i);
            if (!downloadFileFromURL.isSuccess()) {
                this.f24480q = true;
                this.f24481r = this.f24479p;
            } else if (!downloadFileFromURL.isCached()) {
                this.f24483t = false;
            }
            this.f24468e.backgroundTrackFileAbsolutePath = downloadFileFromURL.mFile;
            this.f24472i.m25668a(false);
        }
        if (this.f24468e.backgroundTrackFileAbsolutePath != null) {
            return true;
        }
        return false;
    }

    @SuppressLint({"StandardSubStringNotAllowed"})
    private boolean m25678j() {
        if (this.f24468e.origTrackMetaUrl != null) {
            String str = this.f24468e.origTrackMetaUrl;
            DownloadResult downloadFileFromURL = ResourceDownloader.downloadFileFromURL(this.f24468e.origTrackMetaUrl, str.substring(str.lastIndexOf("/") + 1), this.f24466c, this.f24472i);
            if (!downloadFileFromURL.isCached()) {
                this.f24483t = false;
            }
            this.f24468e.metadataFile = downloadFileFromURL.mFile;
        }
        if (this.f24468e.metadataFile != null) {
            return true;
        }
        return false;
    }

    private boolean m25672a(ArrangementVersion arrangementVersion) {
        boolean z = true;
        if (arrangementVersion == null) {
            Log.d(f24464b, "downloadArrangementResources - ArrangementVersion is null; aborting download of resources");
            return false;
        }
        for (Resource resource : arrangementVersion.resources) {
            if (resource == null) {
                Log.e(f24464b, "Resource is null");
                return false;
            } else if (resource.role == null) {
                Log.e(f24464b, "Resource: " + resource.uid + " has no role!");
                return false;
            } else {
                if (arrangementVersion.resourceFilePaths == null) {
                    Log.d(f24464b, "The song's resourceFilePaths HashMap was null; creating a new one and rolling with it.");
                    arrangementVersion.resourceFilePaths = new ConcurrentHashMap();
                }
                DownloadResult downloadFileFromURL;
                File file;
                if (resource.role.equals("main") && !this.f24477n) {
                    this.f24479p = SongDownloadFileType.MID;
                    downloadFileFromURL = ResourceDownloader.downloadFileFromURL(resource.url, resource.uid, this.f24466c, this.f24472i);
                    if (!downloadFileFromURL.isSuccess()) {
                        this.f24480q = true;
                        this.f24481r = this.f24479p;
                    } else if (!downloadFileFromURL.isCached()) {
                        this.f24483t = false;
                    }
                    file = downloadFileFromURL.mFile;
                    if (file != null) {
                        arrangementVersion.resourceFilePaths.put("main", file.getAbsolutePath());
                    } else {
                        Log.d(f24464b, "Downloading resource for role, \"main\" returned a null file.");
                    }
                    if (this.f24475l) {
                        boolean z2;
                        if (file != null) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        return z2;
                    }
                } else if (!this.f24475l && resource.role.equals("background") && this.f24468e == null) {
                    this.f24479p = SongDownloadFileType.M4A;
                    this.f24472i.m25668a(true);
                    downloadFileFromURL = ResourceDownloader.downloadFileFromURL(resource.url, resource.uid, this.f24466c, this.f24472i);
                    if (!downloadFileFromURL.isSuccess()) {
                        this.f24480q = true;
                        this.f24481r = this.f24479p;
                    } else if (!downloadFileFromURL.isCached()) {
                        this.f24483t = false;
                    }
                    file = downloadFileFromURL.mFile;
                    this.f24472i.m25668a(false);
                    if (file != null) {
                        arrangementVersion.resourceFilePaths.put("background", file.getAbsolutePath());
                    } else {
                        Log.d(f24464b, "Downloading resource for role, \"background\" returned a null file.");
                    }
                }
            }
        }
        if (!(arrangementVersion.resourceFilePaths.containsKey("main") || this.f24477n) || (!arrangementVersion.resourceFilePaths.containsKey("background") && this.f24468e == null)) {
            z = false;
        }
        return z;
    }

    private boolean m25679k() {
        boolean z = true;
        if (this.f24467d == null) {
            return false;
        }
        SongV2 songV2;
        if (this.f24467d.m18773s()) {
            songV2 = ((ListingEntry) this.f24467d).f17626a.song;
        } else {
            songV2 = StoreManager.m18378a().m18416a(((ArrangementVersionLiteEntry) this.f24467d).f17623a.songId);
        }
        if (songV2 == null) {
            Log.d(f24464b, "downloadResources - song is null; aborting download of resources");
            return false;
        } else if (songV2.resources == null) {
            Log.d(f24464b, "downloadResources - song.resources is null; aborting download of resources");
            return false;
        } else {
            for (ResourceV2 resourceV2 : songV2.resources) {
                if (resourceV2 == null) {
                    Log.e(f24464b, "Resource is null");
                    return false;
                } else if (resourceV2.role == null) {
                    Log.e(f24464b, "Resource: " + resourceV2.uid + " for song: " + songV2.title + " has no role!");
                    return false;
                } else {
                    if (songV2.resourceFilePaths == null) {
                        Log.d(f24464b, "The song's resourceFilePaths HashMap was null; creating a new one and rolling with it.");
                        songV2.resourceFilePaths = new ConcurrentHashMap();
                    }
                    DownloadResult downloadFileFromURL;
                    File file;
                    if (resourceV2.role.equals("main") && !this.f24477n) {
                        this.f24479p = SongDownloadFileType.MID;
                        downloadFileFromURL = ResourceDownloader.downloadFileFromURL(resourceV2.url, resourceV2.uid, this.f24466c, this.f24472i);
                        if (!downloadFileFromURL.isSuccess()) {
                            this.f24480q = true;
                            this.f24481r = this.f24479p;
                        } else if (!downloadFileFromURL.isCached()) {
                            this.f24483t = false;
                        }
                        file = downloadFileFromURL.mFile;
                        if (file != null) {
                            this.f24467d.mo6287a("main", file.getAbsolutePath());
                        } else {
                            Log.d(f24464b, "Downloading resource for role, \"main\" returned a null file.");
                        }
                        if (this.f24475l) {
                            boolean z2;
                            if (file != null) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            return z2;
                        }
                    } else if (!this.f24475l && resourceV2.role.equals("background") && this.f24468e == null) {
                        this.f24479p = SongDownloadFileType.M4A;
                        this.f24472i.m25668a(true);
                        downloadFileFromURL = ResourceDownloader.downloadFileFromURL(resourceV2.url, resourceV2.uid, this.f24466c, this.f24472i);
                        if (!downloadFileFromURL.isSuccess()) {
                            this.f24480q = true;
                            this.f24481r = this.f24479p;
                        } else if (!downloadFileFromURL.isCached()) {
                            this.f24483t = false;
                        }
                        file = downloadFileFromURL.mFile;
                        this.f24472i.m25668a(false);
                        if (file != null) {
                            this.f24467d.mo6287a("background", file.getAbsolutePath());
                        } else {
                            Log.d(f24464b, "Downloading resource for role, \"background\" returned a null file.");
                        }
                    }
                }
            }
            if (!(this.f24467d.mo6288a("main") || this.f24477n) || (!this.f24467d.mo6288a("background") && this.f24468e == null)) {
                z = false;
            }
            return z;
        }
    }

    private void m25680l() {
        if (!this.f24484u) {
            this.f24484u = true;
            if (!this.f24475l) {
                SingAnalytics.m26093a(Analytics.m17830a(this.f24467d), this.f24469f, this.f24468e != null ? this.f24468e.performanceKey : null, this.f24482s, this.f24472i.f24458b, this.f24472i.f24457a, Analytics.m17882b(this.f24467d));
            }
        }
    }

    protected void m25685a(Boolean bool) {
        boolean z = true;
        Log.b(f24464b, "onPostExecute - called with success: " + bool);
        if (isCancelled()) {
            m25680l();
        } else if (this.f24475l) {
            if (this.f24476m && this.f24480q) {
                r1 = Analytics.m17830a(this.f24467d);
                r2 = this.f24469f;
                if (this.f24468e != null) {
                    r4 = this.f24468e.performanceKey;
                } else {
                    r4 = null;
                }
                SingAnalytics.m26128b(r1, r2, r4, this.f24482s, this.f24472i.f24458b, this.f24472i.f24457a, this.f24481r, Analytics.m17882b(this.f24467d));
            }
        } else if (bool.booleanValue()) {
            String a = Analytics.m17830a(this.f24467d);
            long j = this.f24469f;
            String str = this.f24468e != null ? this.f24468e.performanceKey : null;
            if (this.f24468e == null) {
                z = false;
            }
            SingAnalytics.m26094a(a, j, str, z, this.f24482s, this.f24483t ? 1 : this.f24472i.f24457a, Analytics.m17882b(this.f24467d));
            if (this.f24480q) {
                r1 = Analytics.m17830a(this.f24467d);
                r2 = this.f24469f;
                if (this.f24468e != null) {
                    r4 = this.f24468e.performanceKey;
                } else {
                    r4 = null;
                }
                SingAnalytics.m26128b(r1, r2, r4, this.f24482s, this.f24472i.f24458b, this.f24472i.f24457a, this.f24481r, Analytics.m17882b(this.f24467d));
            }
        } else if (!this.f24478o) {
            SingAnalytics.m26092a(Analytics.m17830a(this.f24467d), this.f24469f, this.f24468e != null ? this.f24468e.performanceKey : null, this.f24482s, this.f24472i.f24458b, this.f24472i.f24457a, this.f24479p, Analytics.m17882b(this.f24467d));
        }
        synchronized (this) {
            this.f24473j = true;
            this.f24474k = bool.booleanValue();
            if (this.f24470g != null) {
                this.f24470g.mo6541a(bool.booleanValue(), this.f24467d, this.f24468e);
            }
        }
    }

    public boolean m25687a(SongbookEntry songbookEntry) {
        return this.f24467d != null && this.f24467d == songbookEntry && this.f24468e == null;
    }

    public boolean m25691e() {
        return this.f24474k;
    }
}
