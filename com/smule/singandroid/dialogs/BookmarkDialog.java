package com.smule.singandroid.dialogs;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PointerIconCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.adapters.MagicDataSource;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.models.PerformanceV2;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.adapters.profile.ProfileFavoritesDataSource;
import com.smule.singandroid.adapters.profile.ProfileOpenCallDataSource;
import com.smule.singandroid.bookmark.BookmarkDialogCallback;
import com.smule.singandroid.utils.PerformanceV2Util;
import com.smule.singandroid.utils.SingAnalytics;
import java.util.ArrayList;
import java.util.List;

public class BookmarkDialog extends SmuleDialog {
    private static final String f22116k = BookmarkDialog.class.getName();
    TextView f22117a;
    TextView f22118b;
    View f22119c;
    View f22120d;
    View f22121e;
    View f22122f;
    boolean f22123g;
    boolean f22124h;
    PerformanceV2 f22125i;
    BookmarkDialogCallback f22126j;
    private boolean f22127l = false;
    private boolean f22128m = false;
    private boolean f22129n;
    private boolean f22130o;

    class C44541 implements OnClickListener {
        final /* synthetic */ BookmarkDialog f22109a;

        C44541(BookmarkDialog bookmarkDialog) {
            this.f22109a = bookmarkDialog;
        }

        public void onClick(View view) {
            this.f22109a.dismiss();
        }
    }

    class C44552 implements OnClickListener {
        final /* synthetic */ BookmarkDialog f22110a;

        C44552(BookmarkDialog bookmarkDialog) {
            this.f22110a = bookmarkDialog;
        }

        public void onClick(View view) {
            this.f22110a.dismiss();
        }
    }

    class C44573 implements OnClickListener {
        final /* synthetic */ BookmarkDialog f22112a;

        class C44561 implements NetworkResponseCallback {
            final /* synthetic */ C44573 f22111a;

            C44561(C44573 c44573) {
                this.f22111a = c44573;
            }

            public void handleResponse(NetworkResponse networkResponse) {
                boolean z = false;
                if (networkResponse.c()) {
                    String string;
                    if (this.f22111a.f22112a.f22124h) {
                        UserManager.a().g(this.f22111a.f22112a.f22125i.performanceKey);
                        if (this.f22111a.f22112a.f22126j != null) {
                            this.f22111a.f22112a.f22126j.mo6429b(this.f22111a.f22112a.f22125i);
                        }
                    } else {
                        UserManager.a().h(this.f22111a.f22112a.f22125i.performanceKey);
                        if (this.f22111a.f22112a.f22126j != null) {
                            this.f22111a.f22112a.f22126j.mo6431d(this.f22111a.f22112a.f22125i);
                        }
                    }
                    BookmarkDialog bookmarkDialog = this.f22111a.f22112a;
                    Context context = this.f22111a.f22112a.getContext();
                    if (this.f22111a.f22112a.f22124h) {
                        string = this.f22111a.f22112a.getContext().getResources().getString(C1947R.string.favorite_added);
                    } else {
                        string = this.f22111a.f22112a.getContext().getResources().getString(C1947R.string.favorite_removed);
                    }
                    bookmarkDialog.m19790a(context, string);
                    BookmarkDialog bookmarkDialog2 = this.f22111a.f22112a;
                    if (!this.f22111a.f22112a.f22124h) {
                        z = true;
                    }
                    bookmarkDialog2.f22124h = z;
                    MagicDataSource.m17632a(ProfileFavoritesDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                } else if (networkResponse.b == PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW) {
                    new TextAlertDialog(this.f22111a.f22112a.getContext(), (int) C1947R.string.favorite_max_reached_title, (int) C1947R.string.favorite_max_reached_subtitle, true, false).show();
                } else {
                    new TextAlertDialog(this.f22111a.f22112a.getContext(), (int) C1947R.string.favorite_error_title, (int) C1947R.string.login_cannot_connect_to_smule, true, false).show();
                }
                this.f22111a.f22112a.f22130o = true;
                this.f22111a.f22112a.f22120d.setVisibility(8);
                this.f22111a.f22112a.dismiss();
            }
        }

        C44573(BookmarkDialog bookmarkDialog) {
            this.f22112a = bookmarkDialog;
        }

        public void onClick(View view) {
            if (!this.f22112a.f22127l) {
                this.f22112a.f22127l = true;
                List arrayList = new ArrayList();
                List arrayList2 = new ArrayList();
                if (this.f22112a.f22124h) {
                    arrayList.add(this.f22112a.f22125i.performanceKey);
                } else {
                    arrayList2.add(this.f22112a.f22125i.performanceKey);
                }
                PerformanceManager.a().a(arrayList, arrayList2, new C44561(this));
                this.f22112a.m23564b();
            }
        }
    }

    class C44594 implements OnClickListener {
        final /* synthetic */ BookmarkDialog f22114a;

        class C44581 implements NetworkResponseCallback {
            final /* synthetic */ C44594 f22113a;

            C44581(C44594 c44594) {
                this.f22113a = c44594;
            }

            public void handleResponse(NetworkResponse networkResponse) {
                boolean z = false;
                if (networkResponse.c()) {
                    String string;
                    if (this.f22113a.f22114a.f22123g) {
                        UserManager.a().j(this.f22113a.f22114a.f22125i.performanceKey);
                        if (this.f22113a.f22114a.f22126j != null) {
                            this.f22113a.f22114a.f22126j.mo6428a(this.f22113a.f22114a.f22125i);
                        }
                    } else {
                        UserManager.a().k(this.f22113a.f22114a.f22125i.performanceKey);
                        if (this.f22113a.f22114a.f22126j != null) {
                            this.f22113a.f22114a.f22126j.mo6430c(this.f22113a.f22114a.f22125i);
                        }
                    }
                    BookmarkDialog bookmarkDialog = this.f22113a.f22114a;
                    Context context = this.f22113a.f22114a.getContext();
                    if (this.f22113a.f22114a.f22123g) {
                        string = this.f22113a.f22114a.getContext().getResources().getString(C1947R.string.bookmark_added);
                    } else {
                        string = this.f22113a.f22114a.getContext().getResources().getString(C1947R.string.bookmark_removed);
                    }
                    bookmarkDialog.m19790a(context, string);
                    BookmarkDialog bookmarkDialog2 = this.f22113a.f22114a;
                    if (!this.f22113a.f22114a.f22123g) {
                        z = true;
                    }
                    bookmarkDialog2.f22123g = z;
                    MagicDataSource.m17632a(ProfileOpenCallDataSource.class.getSimpleName() + ":" + UserManager.a().f());
                } else if (networkResponse.b == PointerIconCompat.TYPE_VERTICAL_DOUBLE_ARROW) {
                    new TextAlertDialog(this.f22113a.f22114a.getContext(), (int) C1947R.string.bookmark_max_reached_title, (int) C1947R.string.bookmark_max_reached_subtitle, true, false).show();
                } else if (networkResponse.b == PointerIconCompat.TYPE_GRABBING || networkResponse.b == PointerIconCompat.TYPE_NO_DROP) {
                    Log.d(BookmarkDialog.f22116k, "Attempting to bookmark non-joinable performance: " + this.f22113a.f22114a.f22125i.performanceKey, new BookmarkNonSeedException("perfKey: " + this.f22113a.f22114a.f22125i.performanceKey).fillInStackTrace());
                    TextAlertDialog textAlertDialog = new TextAlertDialog(this.f22113a.f22114a.getContext(), "", this.f22113a.f22114a.getContext().getResources().getString(C1947R.string.bookmark_error_expired), true, false);
                    textAlertDialog.m19806a(this.f22113a.f22114a.getContext().getString(C1947R.string.core_ok), "");
                    textAlertDialog.show();
                } else {
                    new TextAlertDialog(this.f22113a.f22114a.getContext(), (int) C1947R.string.favorite_error_title, (int) C1947R.string.login_cannot_connect_to_smule, true, false).show();
                }
                this.f22113a.f22114a.f22130o = true;
                this.f22113a.f22114a.f22120d.setVisibility(8);
                this.f22113a.f22114a.dismiss();
            }
        }

        C44594(BookmarkDialog bookmarkDialog) {
            this.f22114a = bookmarkDialog;
        }

        public void onClick(View view) {
            if (!this.f22114a.f22128m) {
                this.f22114a.f22128m = true;
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                if (this.f22114a.f22123g) {
                    arrayList.add(this.f22114a.f22125i.performanceKey);
                    SingAnalytics.m26131b(this.f22114a.f22125i.performanceKey, PerformanceV2Util.m25947e(this.f22114a.f22125i), PerformanceV2Util.m25950h(this.f22114a.f22125i), this.f22114a.f22125i.video);
                } else {
                    arrayList2.add(this.f22114a.f22125i.performanceKey);
                    SingAnalytics.m26138c(this.f22114a.f22125i.performanceKey, PerformanceV2Util.m25947e(this.f22114a.f22125i), PerformanceV2Util.m25950h(this.f22114a.f22125i), this.f22114a.f22125i.video);
                }
                PerformanceManager.a().a(arrayList, arrayList2, new C44581(this));
                this.f22114a.m23564b();
            }
        }
    }

    class C44605 implements Runnable {
        final /* synthetic */ BookmarkDialog f22115a;

        C44605(BookmarkDialog bookmarkDialog) {
            this.f22115a = bookmarkDialog;
        }

        public void run() {
            if (this.f22115a.isShowing() && !this.f22115a.f22130o) {
                this.f22115a.f22121e.setVisibility(8);
                this.f22115a.f22120d.setVisibility(0);
            }
        }
    }

    public static class BookmarkNonSeedException extends Exception {
        public BookmarkNonSeedException(String str) {
            super(str);
        }
    }

    public BookmarkDialog(Activity activity, boolean z, boolean z2, PerformanceV2 performanceV2, boolean z3, BookmarkDialogCallback bookmarkDialogCallback, boolean z4) {
        super(activity, C1947R.style.MagicModal);
        setContentView(C1947R.layout.bookmark_dialog_layout);
        this.f22117a = (TextView) findViewById(C1947R.id.mBookmarkButton);
        this.f22118b = (TextView) findViewById(C1947R.id.mFavoriteButton);
        this.f22119c = findViewById(C1947R.id.mCancelButton);
        this.f22120d = findViewById(C1947R.id.mSpinnerLayout);
        this.f22121e = findViewById(C1947R.id.mMenuLayout);
        this.f22122f = findViewById(C1947R.id.bookmark_container);
        this.f22126j = bookmarkDialogCallback;
        this.f22129n = z4;
        if (z3) {
            this.f22120d.setVisibility(0);
            this.f22121e.setVisibility(8);
        } else {
            this.f22120d.setVisibility(8);
            this.f22121e.setVisibility(0);
        }
        this.f22122f.setOnClickListener(new C44541(this));
        this.f22119c.setOnClickListener(new C44552(this));
        this.f22118b.setOnClickListener(new C44573(this));
        this.f22117a.setOnClickListener(new C44594(this));
        if (!z3) {
            m23570a(z, z2, performanceV2);
        }
    }

    public void m23570a(boolean z, boolean z2, PerformanceV2 performanceV2) {
        boolean z3 = true;
        this.f22123g = !z;
        if (z2) {
            z3 = false;
        }
        this.f22124h = z3;
        this.f22125i = performanceV2;
        if (!this.f22125i.n() || this.f22129n || this.f22125i.l()) {
            this.f22117a.setVisibility(8);
        } else {
            this.f22117a.setVisibility(0);
        }
        this.f22120d.setVisibility(8);
        this.f22121e.setVisibility(0);
        this.f22117a.setText(this.f22123g ? C1947R.string.core_bookmark_invite : C1947R.string.core_bookmark_remove);
        this.f22118b.setText(this.f22124h ? C1947R.string.core_favorite : C1947R.string.core_unfavorite);
    }

    private void m23564b() {
        new Handler().postDelayed(new C44605(this), 500);
    }
}
