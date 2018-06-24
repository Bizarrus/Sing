/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.AnimatorSet
 *  android.animation.ObjectAnimator
 *  android.app.Activity
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnKeyListener
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.content.res.Resources
 *  android.os.Bundle
 *  android.util.Log
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AdapterView
 *  android.widget.AdapterView$OnItemClickListener
 *  android.widget.ListAdapter
 *  android.widget.ProgressBar
 *  android.widget.RelativeLayout
 *  android.widget.TextView
 *  com.smule.singandroid.utils.AnimatorEndListener
 *  com.smule.singandroid.utils.SingAnalytics
 *  in.srain.cube.views.GridViewWithHeaderAndFooter
 *  org.androidannotations.annotations.AfterViews
 *  org.androidannotations.annotations.Click
 *  org.androidannotations.annotations.EFragment
 *  org.androidannotations.annotations.ViewById
 */
package com.smule.singandroid.onboarding;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.network.managers.TopicsManager;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.Toaster;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.SingApplication;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.crm.SingAppboy;
import com.smule.singandroid.customviews.PaginatedAdapter;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.TopicItem;
import com.smule.singandroid.onboarding.OnboardingActivity;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.SingAnalytics;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingTopicsFragment
extends BaseFragment {
    public static final String g = OnboardingTopicsFragment.class.getName();
    @ViewById
    protected GridViewWithHeaderAndFooter h;
    @ViewById
    protected TextView i;
    @ViewById
    protected TextView j;
    @ViewById
    protected TextView k;
    @ViewById
    protected ProgressBar l;
    protected RelativeLayout m;
    private OnboardingTopicsAdapter n;
    private SingServerValues o = new SingServerValues();

    private void H() {
        CustomAlertDialog.CustomAlertDialogListener customAlertDialogListener = new CustomAlertDialog.CustomAlertDialogListener(){

            @Override
            public void a(CustomAlertDialog object) {
                SingAnalytics.E();
                object = new ArrayList();
                OnboardingTopicsFragment.this.a((ArrayList)object);
                ((OnboardingActivity)OnboardingTopicsFragment.this.getActivity()).a(false, (ArrayList<Integer>)object);
            }

            @Override
            public void b(CustomAlertDialog customAlertDialog) {
            }
        };
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131296667), this.getString(2131297046));
        textAlertDialog.a(this.getString(2131296733), this.getString(2131296701));
        textAlertDialog.a(customAlertDialogListener);
        textAlertDialog.show();
    }

    private void I() {
        if (!this.isAdded()) {
            return;
        }
        TextAlertDialog textAlertDialog = new TextAlertDialog((Context)this.getActivity(), this.getString(2131297043), this.getString(2131297041));
        textAlertDialog.c(false);
        textAlertDialog.a(this.getString(2131297042), null);
        textAlertDialog.a(new Runnable(){

            @Override
            public void run() {
                OnboardingTopicsFragment.this.n.e();
            }
        });
        textAlertDialog.setOnKeyListener(new DialogInterface.OnKeyListener(){

            public boolean onKey(DialogInterface dialogInterface, int n, KeyEvent keyEvent) {
                boolean bl = OnboardingTopicsFragment.this.o.ag();
                if (n == 4 && keyEvent.getAction() == 1 && !keyEvent.isCanceled() && bl) {
                    dialogInterface.cancel();
                    OnboardingTopicsFragment.this.H();
                    return true;
                }
                return false;
            }
        });
        textAlertDialog.show();
    }

    private void J() {
        if (this.isAdded()) {
            this.m.findViewById(2131756336).setVisibility(8);
            this.m.findViewById(2131756762).setVisibility(0);
        }
    }

    private void a(ArrayList<Integer> arrayList) {
        SharedPreferences.Editor editor = SingApplication.g().getSharedPreferences("sing_prefs", 0).edit();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayList.size(); ++i) {
            stringBuilder.append(arrayList.get(i)).append(",");
        }
        editor.putString("ONBOARD_TOPICS_KEY", stringBuilder.toString());
        editor.apply();
    }

    private void c(boolean bl) {
        ProgressBar progressBar = (ProgressBar)this.m.findViewById(2131756336);
        if (bl) {
            progressBar.setVisibility(0);
            return;
        }
        progressBar.setVisibility(4);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void d(boolean bl) {
        int n = 0;
        if (!this.isAdded()) return;
        RelativeLayout relativeLayout = this.m;
        int n2 = bl ? 4 : 0;
        relativeLayout.setVisibility(n2);
        relativeLayout = this.l;
        n2 = bl ? n : 4;
        relativeLayout.setVisibility(n2);
    }

    @Override
    protected void A() {
        SingAnalytics.D();
    }

    @Click
    public void F() {
        ArrayList<Integer> arrayList = this.n.g();
        this.a(arrayList);
        SingAnalytics.a((int)arrayList.size(), (String)this.n.h(), (String)this.n.i());
        ((OnboardingActivity)this.getActivity()).a(true, arrayList);
    }

    @Click
    public void G() {
        this.H();
    }

    /*
     * Enabled aggressive block sorting
     */
    @AfterViews
    public void a() {
        this.n = new OnboardingTopicsAdapter(8);
        View view = this.getActivity().getLayoutInflater().inflate(2130903450, null);
        this.h.a(view);
        this.m = (RelativeLayout)this.getActivity().getLayoutInflater().inflate(2130903449, null);
        this.h.b((View)this.m);
        this.h.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> object, View view, int n, long l) {
                block8 : {
                    block7 : {
                        if (!(view instanceof TopicItem)) break block7;
                        object = (TopicItem)view;
                        int n2 = OnboardingTopicsFragment.this.n.f();
                        if (n2 >= 4) break block8;
                        if (n2 == 0) {
                            OnboardingTopicsFragment.this.i.setClickable(true);
                            OnboardingTopicsFragment.this.i.setTextColor(OnboardingTopicsFragment.this.getResources().getColor(2131689579));
                        }
                        OnboardingTopicsFragment.this.n.c(n);
                        object.a(OnboardingTopicsFragment.this.n.d(n));
                        n = OnboardingTopicsFragment.this.n.f();
                        if (n == 0) {
                            OnboardingTopicsFragment.this.i.setClickable(false);
                            OnboardingTopicsFragment.this.i.setTextColor(OnboardingTopicsFragment.this.getResources().getColor(2131689578));
                        }
                        if (n == 4) {
                            object = ObjectAnimator.ofFloat((Object)OnboardingTopicsFragment.this.i, (String)"scaleX", (float[])new float[]{1.35f});
                            view = ObjectAnimator.ofFloat((Object)OnboardingTopicsFragment.this.i, (String)"scaleY", (float[])new float[]{1.35f});
                            object.setDuration(250);
                            view.setDuration(250);
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.addListener((Animator.AnimatorListener)new AnimatorEndListener(){

                                public void onAnimationEnd(Animator animator2) {
                                    animator2 = ObjectAnimator.ofFloat((Object)OnboardingTopicsFragment.this.i, (String)"scaleX", (float[])new float[]{1.0f});
                                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat((Object)OnboardingTopicsFragment.this.i, (String)"scaleY", (float[])new float[]{1.0f});
                                    animator2.setDuration(250);
                                    objectAnimator.setDuration(250);
                                    AnimatorSet animatorSet = new AnimatorSet();
                                    animatorSet.playTogether(new Animator[]{animator2, objectAnimator});
                                    animatorSet.start();
                                }
                            });
                            animatorSet.playTogether(new Animator[]{object, view});
                            animatorSet.start();
                            OnboardingTopicsFragment.this.n.a(true);
                        }
                    }
                    return;
                }
                if (OnboardingTopicsFragment.this.n.d(n)) {
                    OnboardingTopicsFragment.this.n.a(n, false);
                    object.a(false);
                    OnboardingTopicsFragment.this.n.a(false);
                    com.smule.android.utils.Toaster.a();
                    return;
                }
                com.smule.android.utils.Toaster.a((Context)OnboardingTopicsFragment.this.getActivity(), OnboardingTopicsFragment.this.getResources().getString(2131297045), Toaster.b);
            }

        });
        this.i.setClickable(false);
        this.h.setAdapter((ListAdapter)this.n);
        this.h.setChoiceMode(2);
        this.j.setVisibility(8);
        boolean bl = this.o.ag();
        view = this.k;
        int n = bl ? 0 : 8;
        view.setVisibility(n);
        this.n.e();
    }

    @Override
    public boolean d() {
        if (this.o.ag()) {
            this.H();
        }
        return true;
    }

    @Override
    public void onCreate(Bundle bundle) {
        boolean bl = false;
        super.onCreate(bundle);
        Log.b(g, "Begin of onCreate()");
        TopicItem.f = false;
        SingAppboy.a().d();
        if (bundle != null) {
            bl = true;
        }
        if (!bl) {
            SingPermissionRequests.a(this, null);
        }
        Log.b(g, "End of onCreate()");
    }

    @Click
    public void t() {
    }

    @Override
    public String x() {
        return g;
    }

    private final class OnboardingTopicsAdapter
    extends PaginatedAdapter {
        public HashSet<Integer> f;
        private ArrayList<Topic> h;
        private final int i;
        private int j;
        private boolean k;
        private boolean l;

        public OnboardingTopicsAdapter(int n) {
            this.h = new ArrayList();
            this.f = new HashSet();
            this.k = false;
            this.l = false;
            this.i = n;
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        public View a(int n, View object, ViewGroup viewGroup) {
            object = object == null ? TopicItem.a((Context)OnboardingTopicsFragment.this.getActivity()) : (TopicItem)object;
            object.a(this.h.get(n), this.f.contains(n));
            return object;
        }

        public void a(int n, boolean bl) {
            if (bl) {
                this.f.add(n);
                return;
            }
            this.f.remove(n);
        }

        public void a(boolean bl) {
            TopicItem.f = bl;
            this.notifyDataSetChanged();
        }

        /*
         * Enabled aggressive block sorting
         */
        @Override
        protected void b(final int n) {
            if (this.l || !OnboardingTopicsFragment.this.isAdded() || this.k) {
                return;
            }
            this.d();
            this.l = true;
            if (n == 0) {
                OnboardingTopicsFragment.this.d(true);
            } else {
                OnboardingTopicsFragment.this.c(true);
                SingAnalytics.F();
            }
            android.util.Log.d((String)a, (String)("Request topics from " + this.j + " (current size=" + this.h.size() + ")"));
            final int n2 = OnboardingTopicsFragment.this.e;
            com.smule.android.network.managers.TopicsManager.a().a(this.j, this.i, new TopicsManager.GetTopicOptionsResponseListener(){

                /*
                 * Enabled aggressive block sorting
                 */
                @Override
                public void handleResponse(TopicsManager getTopicOptionsResponse) {
                    if (n == 0) {
                        OnboardingTopicsFragment.this.d(false);
                    } else {
                        OnboardingTopicsFragment.this.c(false);
                    }
                    if (!getTopicOptionsResponse.a() || !OnboardingTopicsFragment.this.a(n2)) {
                        OnboardingTopicsAdapter.this.l = false;
                        if (n == 0) {
                            OnboardingTopicsFragment.this.I();
                        }
                        return;
                    }
                    android.util.Log.i((String)PaginatedAdapter.a, (String)("handleResponse : [" + getTopicOptionsResponse.options.size() + "]"));
                    for (Topic topic : getTopicOptionsResponse.options) {
                        OnboardingTopicsAdapter.this.h.add(topic);
                    }
                    OnboardingTopicsAdapter.this.notifyDataSetChanged();
                    if (getTopicOptionsResponse.next == -1) {
                        OnboardingTopicsAdapter.this.k = true;
                        OnboardingTopicsAdapter.this.c();
                        OnboardingTopicsFragment.this.J();
                    } else {
                        OnboardingTopicsAdapter.this.d();
                    }
                    OnboardingTopicsAdapter.this.l = false;
                    OnboardingTopicsAdapter.this.j = OnboardingTopicsAdapter.this.j + OnboardingTopicsAdapter.this.i;
                    OnboardingTopicsAdapter.this.b();
                }
            });
        }

        /*
         * Enabled aggressive block sorting
         */
        public void c(int n) {
            boolean bl = !this.d(n);
            this.a(n, bl);
        }

        public boolean d(int n) {
            return this.f.contains(n);
        }

        public void e() {
            this.k = false;
            this.h.clear();
            this.j = 0;
            this.a(0);
            this.a();
            this.b(0);
            this.notifyDataSetChanged();
        }

        public int f() {
            return this.f.size();
        }

        public ArrayList<Integer> g() {
            ArrayList<Integer> arrayList = new ArrayList<Integer>();
            for (Integer n : this.f) {
                arrayList.add(this.h.get((int)n.intValue()).id);
            }
            return arrayList;
        }

        public int getCount() {
            if (this.h != null) {
                return this.h.size();
            }
            return 0;
        }

        public Object getItem(int n) {
            if (this.h != null && n < this.h.size()) {
                return this.h.get(n);
            }
            return null;
        }

        public long getItemId(int n) {
            return n;
        }

        public String h() {
            String string2 = "";
            Iterator<Integer> iterator = this.f.iterator();
            while (iterator.hasNext()) {
                String string3;
                string2 = string3 = string2 + this.h.get((int)iterator.next().intValue()).displayName;
                if (!iterator.hasNext()) continue;
                string2 = string3 + ",";
            }
            return string2;
        }

        public String i() {
            String string2 = "";
            Iterator<Integer> iterator = this.f.iterator();
            while (iterator.hasNext()) {
                String string3;
                string2 = string3 = string2 + this.h.get((int)iterator.next().intValue()).id;
                if (!iterator.hasNext()) continue;
                string2 = string3 + ",";
            }
            return string2;
        }

    }

}

