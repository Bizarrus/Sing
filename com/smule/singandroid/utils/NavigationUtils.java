package com.smule.singandroid.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.smule.android.logging.Analytics;
import com.smule.android.logging.Log;
import com.smule.android.network.core.MagicNetwork;
import com.smule.android.network.core.NetworkResponse;
import com.smule.android.network.core.NetworkResponse$Status;
import com.smule.android.network.managers.PerformanceManager;
import com.smule.android.network.managers.UserManager;
import com.smule.android.network.managers.UserManager$AccountResponseCallback;
import com.smule.android.network.managers.UserManager.AccountResponse;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.ImageUtils;
import com.smule.android.utils.NotificationCenter;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.android.utils.WeakListener;
import com.smule.singandroid.BaseActivity;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.MasterActivity_;
import com.smule.singandroid.SingBundle.PerformanceType;
import com.smule.singandroid.dialogs.BusyDialog;
import com.smule.singandroid.dialogs.BusyDialog.BusyDialogListener;
import com.smule.singandroid.dialogs.DeleteRecordingConfirmationDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.registration.RegistrationContext;
import com.smule.singandroid.registration.RegistrationEntryActivity;

public class NavigationUtils {
    private static final String f24813a = NavigationUtils.class.getName();

    static class C50013 implements BusyDialogListener {
        C50013() {
        }

        public void mo6373a() {
            Log.b(NavigationUtils.f24813a, "checkingDialog: onCancel");
        }
    }

    public static Dialog m25903a(Context context, String str, final OnClickListener onClickListener, final OnClickListener onClickListener2) {
        final Dialog dialog = new Dialog(context, C1947R.style.MagicModal);
        dialog.requestWindowFeature(1);
        dialog.setCancelable(true);
        View inflate = ((Activity) context).getLayoutInflater().inflate(C1947R.layout.no_account_for_email_dialog, null, false);
        if (str != null) {
            ((TextView) inflate.findViewById(C1947R.id.email)).setText(context.getString(C1947R.string.login_no_account_for_email, new Object[]{str}));
        } else {
            Log.e(f24813a, "no email set for dialog!");
        }
        ((Button) inflate.findViewById(C1947R.id.try_another)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                onClickListener.onClick(view);
            }
        });
        ((Button) inflate.findViewById(C1947R.id.new_account_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                onClickListener2.onClick(view);
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
        return dialog;
    }

    public static void m25908a(final Activity activity, Runnable runnable, final Runnable runnable2) {
        if (!UserManager.a().z()) {
            RegistrationContext.a();
            RegistrationContext.a(runnable);
            final BusyDialog busyDialog = new BusyDialog(activity, activity.getString(C1947R.string.login_checking));
            busyDialog.m23579a(new C50013());
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    if (!activity.isFinishing()) {
                        busyDialog.show();
                    }
                }
            });
            UserManager.a().a(new UserManager$AccountResponseCallback() {
                public void handleResponse(AccountResponse accountResponse) {
                    if (!activity.isFinishing()) {
                        if (accountResponse.a.a != NetworkResponse$Status.OK) {
                            if (runnable2 == null) {
                                busyDialog.m23576a(2, activity.getString(C1947R.string.login_cannot_connect_to_smule), true);
                                return;
                            }
                            busyDialog.dismiss();
                            runnable2.run();
                        } else if (accountResponse.a.c()) {
                            busyDialog.dismiss();
                            activity.startActivity(RegistrationEntryActivity.m25166a(activity, true, false, accountResponse.mHandle, accountResponse.mEmail, accountResponse.mPicUrl));
                        } else if (accountResponse.a.b == 65) {
                            busyDialog.dismiss();
                            activity.startActivity(RegistrationEntryActivity.m25166a(activity, false, false, null, null, null));
                        } else if (accountResponse.a.b == 2000) {
                            busyDialog.dismiss();
                        } else if (runnable2 == null) {
                            busyDialog.m23576a(2, activity.getString(C1947R.string.login_error_with_servers), true);
                            MagicNetwork.a(accountResponse.a);
                        } else {
                            busyDialog.dismiss();
                            runnable2.run();
                        }
                    }
                }
            });
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public static void m25909a(EditText editText, Button button) {
        m25910a(editText, null, button);
    }

    public static void m25910a(final EditText editText, final EditText editText2, final Button button) {
        WeakListener.m19083a(editText, new TextWatcher() {
            public void afterTextChanged(Editable editable) {
                NavigationUtils.m25912c(editText, editText2, button);
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        if (editText2 != null) {
            WeakListener.m19083a(editText2, new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                    NavigationUtils.m25912c(editText, editText2, button);
                }

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }
            });
        }
        OnEditorActionListener c50068 = new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i != 2 && (i != 0 || keyEvent.getAction() != 0)) || !button.isEnabled()) {
                    return false;
                }
                button.performClick();
                return true;
            }
        };
        if (editText2 != null) {
            editText2.setOnEditorActionListener(c50068);
        } else {
            editText.setOnEditorActionListener(c50068);
        }
        m25912c(editText, editText2, button);
    }

    private static void m25912c(EditText editText, EditText editText2, Button button) {
        button.setClickable(true);
        if (editText.getText().toString().length() <= 0 || (editText2 != null && editText2.getText().toString().length() <= 0)) {
            button.setEnabled(false);
        } else {
            button.setEnabled(true);
        }
    }

    public static Dialog m25904a(Context context, String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2) {
        Dialog dialog = new Dialog(context, C1947R.style.MagicModal);
        dialog.requestWindowFeature(1);
        View inflate = ((Activity) context).getLayoutInflater().inflate(C1947R.layout.is_this_you_dialog, null, false);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.show();
        TextView textView = (TextView) inflate.findViewById(C1947R.id.handle);
        ImageUtils.a(str3, (ImageView) inflate.findViewById(C1947R.id.profile_pic), C1947R.drawable.icn_default_profile_large, true);
        if (str == null) {
            textView.setVisibility(4);
        } else if (str2 == null || str2.isEmpty()) {
            textView.setText(context.getString(C1947R.string.login_found_account_details_no_email, new Object[]{str}));
        } else {
            textView.setText(context.getString(C1947R.string.login_found_account_details, new Object[]{str, str2}));
        }
        ((Button) inflate.findViewById(C1947R.id.nope_button)).setOnClickListener(onClickListener2);
        ((Button) inflate.findViewById(C1947R.id.yes_button)).setOnClickListener(onClickListener);
        return dialog;
    }

    public static void m25906a(Activity activity) {
        activity.startActivity(new Intent(activity, MasterActivity_.class));
        activity.finish();
    }

    public static void m25907a(Activity activity, PerformanceV2 performanceV2, Runnable runnable, Runnable runnable2, Runnable runnable3, boolean z) {
        final Handler handler = new Handler();
        final PerformanceV2 performanceV22 = performanceV2;
        final Activity activity2 = activity;
        final Runnable runnable4 = runnable;
        final Runnable runnable5 = runnable2;
        final Runnable runnable6 = runnable3;
        Runnable c50099 = new Runnable() {
            public void run() {
                Log.b(NavigationUtils.f24813a, "Beginning delete performance");
                Analytics.m17868a(performanceV22.performanceKey, performanceV22.songUid, PerformanceType.m21630a(performanceV22.ensembleType).m21631a());
                final BusyDialog busyDialog = new BusyDialog(activity2, activity2.getString(C1947R.string.performance_deleting));
                busyDialog.show();
                MagicNetwork.a(new Runnable(this) {
                    final /* synthetic */ C50099 f24806b;

                    public void run() {
                        if (runnable4 != null) {
                            runnable4.run();
                        }
                        final NetworkResponse f = PerformanceManager.a().f(performanceV22.performanceKey);
                        handler.post(new Runnable(this) {
                            final /* synthetic */ C50081 f24804b;

                            public void run() {
                                boolean c = f.c();
                                busyDialog.dismiss();
                                Toaster.a(activity2, c ? C1947R.string.performance_delete_success : C1947R.string.performance_delete_error, Toaster$Duration.LONG);
                                if (c) {
                                    NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "DELETED_PERFORMANCE", performanceV22);
                                    if (UserManager.a().i(performanceV22.performanceKey)) {
                                        UserManager.a().h(performanceV22.performanceKey);
                                        NotificationCenter.m19011a().m19017b("PERFORMANCE_UPDATED_NOTIFICATION", "FAVORITED_PERF_DELETED_PERFORMANCE", performanceV22);
                                    }
                                    if (runnable5 != null) {
                                        runnable5.run();
                                    }
                                } else if (f.e()) {
                                    ((BaseActivity) activity2).a(f.f, true, runnable6);
                                } else if (runnable6 != null) {
                                    runnable6.run();
                                }
                            }
                        });
                    }
                });
            }
        };
        if (z) {
            TextAlertDialog deleteRecordingConfirmationDialog = new DeleteRecordingConfirmationDialog(activity);
            deleteRecordingConfirmationDialog.m19809b(c50099);
            deleteRecordingConfirmationDialog.show();
            return;
        }
        c50099.run();
    }
}
