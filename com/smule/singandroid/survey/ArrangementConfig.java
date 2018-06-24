package com.smule.singandroid.survey;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.smule.android.network.api.ArrangementAPI$Vote;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponseCallback;
import com.smule.android.network.managers.ArrangementManager;
import com.smule.android.network.managers.FollowManager;
import com.smule.android.network.managers.FollowManager.ToggleFollowStateListener;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.ArrangementVersionLite;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.utils.SingAnalytics;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ArrangementConfig implements SurveyConfig {
    protected Activity f24249a;
    protected SurveyContext f24250b;

    public ArrangementConfig(Activity activity, SurveyContext surveyContext) {
        this.f24249a = activity;
        this.f24250b = surveyContext;
    }

    public String mo6937a() {
        return this.f24249a.getResources().getString(C1947R.string.performance_rating_cta);
    }

    public RatingInterface mo6939b() {
        return ArrangementRating.GOOD;
    }

    public RatingInterface mo6941c() {
        return ArrangementRating.BAD;
    }

    public boolean mo6942d() {
        return true;
    }

    public int mo6943e() {
        return C1947R.layout.arrangement_ratings_dialog_contents;
    }

    public SurveyRatingDialog mo6944f() {
        return new ArrangementRatingDialog(this.f24249a, this.f24250b, this);
    }

    public String mo6945g() {
        return this.f24249a.getResources().getString(C1947R.string.performance_rating_issue);
    }

    public String mo6946h() {
        return this.f24249a.getResources().getString(C1947R.string.core_skip);
    }

    public List<ReasonInterface> mo6947i() {
        List<ReasonInterface> arrayList = new ArrayList();
        for (ArrangementReason arrangementReason : ArrangementReason.values()) {
            switch (arrangementReason) {
                case DUET_PARTS:
                    if (!this.f24250b.f24290d) {
                        break;
                    }
                    arrayList.add(arrangementReason);
                    break;
                case GROUP_PARTS:
                    if (!this.f24250b.f24289c) {
                        break;
                    }
                    arrayList.add(arrangementReason);
                    break;
                default:
                    arrayList.add(arrangementReason);
                    break;
            }
        }
        return arrayList;
    }

    public SurveyReasonDialog mo6936a(SurveyRatingDialog surveyRatingDialog) {
        return new SurveyReasonDialog(this.f24249a, this, surveyRatingDialog);
    }

    public Runnable mo6940b(@NonNull final SurveyRatingDialog surveyRatingDialog) {
        return new Runnable(this) {
            final /* synthetic */ ArrangementConfig f24240b;

            public void run() {
                AccountIcon accountIcon = this.f24240b.f24250b.m25507a().accountIcon;
                if (FollowManager.m18204a().m18222a(accountIcon.accountId)) {
                    surveyRatingDialog.m25492h();
                    return;
                }
                FollowArrangerDialog a = this.f24240b.m25470a(accountIcon, surveyRatingDialog);
                surveyRatingDialog.m25493i();
                a.show();
            }
        };
    }

    public String mo6948j() {
        return this.f24249a.getResources().getString(C1947R.string.performance_rating_thanks);
    }

    public void mo6938a(@NonNull RatingInterface ratingInterface, @Nullable ReasonInterface reasonInterface) {
        String name;
        Integer num;
        final ArrangementVersionLite a = this.f24250b.m25507a();
        Integer valueOf = reasonInterface == null ? null : Integer.valueOf(reasonInterface.mo6950a());
        if (ratingInterface == mo6939b()) {
            name = ArrangementAPI$Vote.UP.name();
            num = null;
        } else {
            name = ArrangementAPI$Vote.DOWN.name();
            num = Integer.valueOf(reasonInterface == null ? -1 : reasonInterface.mo6950a());
        }
        ArrangementManager.a().a(a.key, a.version, valueOf, name, new NetworkResponseCallback(this) {
            final /* synthetic */ ArrangementConfig f24243c;

            public void handleResponse(NetworkResponse networkResponse) {
                SurveyPresenter.m25508a().m25546a(a.key);
                if (networkResponse.c()) {
                    this.f24243c.m25472a(a, num);
                }
            }
        });
    }

    private void m25472a(ArrangementVersionLite arrangementVersionLite, Integer num) {
        String str = null;
        String str2 = arrangementVersionLite.a() ? "-" : arrangementVersionLite.songId;
        String num2 = num == null ? null : num.toString();
        if (this.f24250b.f24292f != null) {
            str = this.f24250b.f24292f.performanceKey;
        }
        SingAnalytics.m26119a(str, num2, str2, this.f24250b.f24293g, arrangementVersionLite.key);
    }

    private FollowArrangerDialog m25470a(final AccountIcon accountIcon, CustomAlertDialog customAlertDialog) {
        final FollowArrangerDialog followArrangerDialog = new FollowArrangerDialog(this.f24249a, accountIcon, customAlertDialog);
        followArrangerDialog.m19803a(new CustomAlertDialogListener(this) {
            final /* synthetic */ ArrangementConfig f24247c;

            class C49181 implements ToggleFollowStateListener {
                final /* synthetic */ C49193 f24244a;

                C49181(C49193 c49193) {
                    this.f24244a = c49193;
                }

                public void mo6399a(boolean z, boolean z2, boolean z3) {
                    Context f = SingApplication.f();
                    if (z3) {
                        Toaster.a(f, f.getString(C1947R.string.profile_follow_limit_reached));
                    } else if (!z) {
                        Toaster.a(f, f.getString(C1947R.string.profile_update_error));
                    } else if (z2) {
                        Toaster.a(f, MessageFormat.format(f.getString(C1947R.string.profile_follow_format), new Object[]{accountIcon.handle}));
                    } else {
                        Toaster.a(f, MessageFormat.format(f.getString(C1947R.string.profile_unfollow_format), new Object[]{accountIcon.handle}));
                    }
                }
            }

            public void mo6385a(CustomAlertDialog customAlertDialog) {
                FollowManager.m18204a().m18215a(Long.valueOf(accountIcon.accountId), new C49181(this));
                followArrangerDialog.dismiss();
                SurveyPresenter.m25508a().m25554f();
            }

            public void mo6386b(CustomAlertDialog customAlertDialog) {
                followArrangerDialog.dismiss();
                SurveyPresenter.m25508a().m25555g();
            }
        });
        return followArrangerDialog;
    }
}
