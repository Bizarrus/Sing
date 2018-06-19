package com.smule.singandroid;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.network.managers.TopicsManager;
import com.smule.android.network.managers.TopicsManager.GetTopicOptionsResponse;
import com.smule.android.network.managers.TopicsManager.GetTopicOptionsResponseListener;
import com.smule.android.network.models.Topic;
import com.smule.android.utils.Toaster;
import com.smule.android.utils.Toaster$Duration;
import com.smule.singandroid.customviews.PaginatedAdapter;
import com.smule.singandroid.dialogs.CustomAlertDialog;
import com.smule.singandroid.dialogs.CustomAlertDialog.CustomAlertDialogListener;
import com.smule.singandroid.dialogs.TextAlertDialog;
import com.smule.singandroid.list_items.TopicItem;
import com.smule.singandroid.runtimepermissions.SingPermissionRequests;
import com.smule.singandroid.utils.AnimatorEndListener;
import com.smule.singandroid.utils.SingAnalytics;
import in.srain.cube.views.GridViewWithHeaderAndFooter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class OnboardingTopicsFragment extends BaseFragment {
    private static final String f19000j = OnboardingTopicsFragment.class.getName();
    @ViewById
    protected GridViewWithHeaderAndFooter f19001e;
    @ViewById
    protected TextView f19002f;
    @ViewById
    protected TextView f19003g;
    @ViewById
    protected ProgressBar f19004h;
    protected RelativeLayout f19005i;
    private OnboardingTopicsAdapter f19006k;

    class C38921 implements OnItemClickListener {
        final /* synthetic */ OnboardingTopicsFragment f18986a;

        class C38911 extends AnimatorEndListener {
            final /* synthetic */ C38921 f18985a;

            C38911(C38921 c38921) {
                this.f18985a = c38921;
            }

            public void onAnimationEnd(Animator animator) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f18985a.f18986a.f19002f, "scaleX", new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f18985a.f18986a.f19002f, "scaleY", new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT});
                ofFloat.setDuration(250);
                ofFloat2.setDuration(250);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                animatorSet.start();
            }
        }

        C38921(OnboardingTopicsFragment onboardingTopicsFragment) {
            this.f18986a = onboardingTopicsFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (view instanceof TopicItem) {
                TopicItem topicItem = (TopicItem) view;
                int b = this.f18986a.f19006k.m20495b();
                if (b < 4) {
                    if (b == 0) {
                        this.f18986a.f19002f.setClickable(true);
                        this.f18986a.f19002f.setTextColor(this.f18986a.getResources().getColor(C1947R.color.button_text_inverse));
                    }
                    this.f18986a.f19006k.m20496b(i);
                    topicItem.m24505a(this.f18986a.f19006k.m20498c(i));
                    b = this.f18986a.f19006k.m20495b();
                    if (b == 0) {
                        this.f18986a.f19002f.setClickable(false);
                        this.f18986a.f19002f.setTextColor(this.f18986a.getResources().getColor(C1947R.color.button_text_disabled));
                    }
                    if (b == 4) {
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f18986a.f19002f, "scaleX", new float[]{1.35f});
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f18986a.f19002f, "scaleY", new float[]{1.35f});
                        ofFloat.setDuration(250);
                        ofFloat2.setDuration(250);
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.addListener(new C38911(this));
                        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                        animatorSet.start();
                        this.f18986a.f19006k.m20494a(true);
                    }
                } else if (this.f18986a.f19006k.m20498c(i)) {
                    this.f18986a.f19006k.m20493a(i, false);
                    topicItem.m24505a(false);
                    this.f18986a.f19006k.m20494a(false);
                    Toaster.a();
                } else {
                    Toaster.a(this.f18986a.getActivity(), this.f18986a.getResources().getString(C1947R.string.onboarding_selected_max), Toaster$Duration.LONG);
                }
            }
        }
    }

    class C38932 implements CustomAlertDialogListener {
        final /* synthetic */ OnboardingTopicsFragment f18987a;

        C38932(OnboardingTopicsFragment onboardingTopicsFragment) {
            this.f18987a = onboardingTopicsFragment;
        }

        public void mo6385a(CustomAlertDialog customAlertDialog) {
            SingAnalytics.m26162z();
            ArrayList arrayList = new ArrayList();
            this.f18987a.m20508a(arrayList);
            ((OnboardingActivity) this.f18987a.getActivity()).m20472a(false, arrayList);
        }

        public void mo6386b(CustomAlertDialog customAlertDialog) {
        }
    }

    class C38943 implements Runnable {
        final /* synthetic */ OnboardingTopicsFragment f18988a;

        C38943(OnboardingTopicsFragment onboardingTopicsFragment) {
            this.f18988a = onboardingTopicsFragment;
        }

        public void run() {
            this.f18988a.f19006k.m20491a();
        }
    }

    class C38954 implements OnKeyListener {
        final /* synthetic */ OnboardingTopicsFragment f18989a;

        C38954(OnboardingTopicsFragment onboardingTopicsFragment) {
            this.f18989a = onboardingTopicsFragment;
        }

        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (i != 4 || keyEvent.getAction() != 1 || keyEvent.isCanceled()) {
                return false;
            }
            dialogInterface.cancel();
            this.f18989a.m20501C();
            return true;
        }
    }

    private final class OnboardingTopicsAdapter extends PaginatedAdapter {
        public HashSet<Integer> f18993a = new HashSet();
        final /* synthetic */ OnboardingTopicsFragment f18994b;
        private ArrayList<Topic> f18995h = new ArrayList();
        private final int f18996i;
        private int f18997j;
        private boolean f18998k = false;
        private boolean f18999l = false;

        public OnboardingTopicsAdapter(OnboardingTopicsFragment onboardingTopicsFragment, int i) {
            this.f18994b = onboardingTopicsFragment;
            this.f18996i = i;
        }

        public void m20491a() {
            this.f18998k = false;
            this.f18995h.clear();
            this.f18997j = 0;
            d(0);
            f();
            m20492a(0);
            notifyDataSetChanged();
        }

        protected void m20492a(final int i) {
            if (!this.f18999l && this.f18994b.isAdded() && !this.f18998k) {
                i();
                this.f18999l = true;
                if (i == 0) {
                    this.f18994b.m20513c(true);
                } else {
                    this.f18994b.m20511b(true);
                    SingAnalytics.m26056A();
                }
                Log.d(c, "Request topics from " + this.f18997j + " (current size=" + this.f18995h.size() + ")");
                final int i2 = this.f18994b.d;
                TopicsManager.m18458a().m18462a(Integer.valueOf(this.f18997j), Integer.valueOf(this.f18996i), new GetTopicOptionsResponseListener(this) {
                    final /* synthetic */ OnboardingTopicsAdapter f18992c;

                    public void handleResponse(GetTopicOptionsResponse getTopicOptionsResponse) {
                        if (i == 0) {
                            this.f18992c.f18994b.m20513c(false);
                        } else {
                            this.f18992c.f18994b.m20511b(false);
                        }
                        if (getTopicOptionsResponse.a() && this.f18992c.f18994b.m19843a(i2)) {
                            Log.i(PaginatedAdapter.c, "handleResponse : [" + getTopicOptionsResponse.options.size() + "]");
                            Iterator it = getTopicOptionsResponse.options.iterator();
                            while (it.hasNext()) {
                                this.f18992c.f18995h.add((Topic) it.next());
                            }
                            this.f18992c.notifyDataSetChanged();
                            if (getTopicOptionsResponse.next == -1) {
                                this.f18992c.f18998k = true;
                                this.f18992c.h();
                                this.f18992c.f18994b.m20503E();
                            } else {
                                this.f18992c.i();
                            }
                            this.f18992c.f18999l = false;
                            this.f18992c.f18997j = this.f18992c.f18997j + this.f18992c.f18996i;
                            this.f18992c.g();
                            return;
                        }
                        this.f18992c.f18999l = false;
                        if (i == 0) {
                            this.f18992c.f18994b.m20502D();
                        }
                    }
                });
            }
        }

        public int getCount() {
            return this.f18995h != null ? this.f18995h.size() : 0;
        }

        public Object getItem(int i) {
            return (this.f18995h == null || i >= this.f18995h.size()) ? null : (Topic) this.f18995h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View m20490a(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = TopicItem.m24502a(this.f18994b.getActivity());
            } else {
                TopicItem topicItem = (TopicItem) view;
            }
            view.m24504a((Topic) this.f18995h.get(i), this.f18993a.contains(Integer.valueOf(i)));
            return view;
        }

        public void m20496b(int i) {
            m20493a(i, !m20498c(i));
        }

        public void m20493a(int i, boolean z) {
            if (z) {
                this.f18993a.add(Integer.valueOf(i));
            } else {
                this.f18993a.remove(Integer.valueOf(i));
            }
        }

        public boolean m20498c(int i) {
            return this.f18993a.contains(Integer.valueOf(i));
        }

        public int m20495b() {
            return this.f18993a.size();
        }

        public ArrayList<Integer> m20497c() {
            ArrayList<Integer> arrayList = new ArrayList();
            Iterator it = this.f18993a.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((Topic) this.f18995h.get(((Integer) it.next()).intValue())).id));
            }
            return arrayList;
        }

        public String m20499d() {
            String str = "";
            Iterator it = this.f18993a.iterator();
            while (it.hasNext()) {
                str = str + ((Topic) this.f18995h.get(((Integer) it.next()).intValue())).displayName;
                if (it.hasNext()) {
                    str = str + ",";
                }
            }
            return str;
        }

        public String m20500e() {
            String str = "";
            Iterator it = this.f18993a.iterator();
            while (it.hasNext()) {
                str = str + ((Topic) this.f18995h.get(((Integer) it.next()).intValue())).id;
                if (it.hasNext()) {
                    str = str + ",";
                }
            }
            return str;
        }

        public void m20494a(boolean z) {
            TopicItem.f23283f = z;
            notifyDataSetChanged();
        }
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        com.smule.android.logging.Log.b(f19000j, "Begin of onCreate()");
        TopicItem.f23283f = false;
        if (bundle != null) {
            z = true;
        }
        if (!z) {
            SingPermissionRequests.m25196a((BaseFragment) this, null);
        }
        com.smule.android.logging.Log.b(f19000j, "End of onCreate()");
    }

    private void m20511b(boolean z) {
        ProgressBar progressBar = (ProgressBar) this.f19005i.findViewById(C1947R.id.loading_spinner);
        if (z) {
            progressBar.setVisibility(0);
        } else {
            progressBar.setVisibility(4);
        }
    }

    protected void mo6420v() {
        SingAnalytics.m26161y();
    }

    @AfterViews
    public void m20517a() {
        this.f19006k = new OnboardingTopicsAdapter(this, 8);
        this.f19001e.m26737a(getActivity().getLayoutInflater().inflate(C1947R.layout.topics_header, null));
        this.f19005i = (RelativeLayout) getActivity().getLayoutInflater().inflate(C1947R.layout.topics_footer, null);
        this.f19001e.m26739b(this.f19005i);
        this.f19001e.setOnItemClickListener(new C38921(this));
        this.f19002f.setClickable(false);
        this.f19001e.setAdapter(this.f19006k);
        this.f19001e.setChoiceMode(2);
        this.f19003g.setVisibility(8);
        this.f19006k.m20491a();
    }

    @Click
    public void m20521z() {
    }

    @Click
    public void m20515A() {
        ArrayList c = this.f19006k.m20497c();
        SingAnalytics.m26063a(c.size(), this.f19006k.m20499d(), this.f19006k.m20500e());
        m20508a(c);
        ((OnboardingActivity) getActivity()).m20472a(true, c);
    }

    private void m20508a(ArrayList<Integer> arrayList) {
        int i = 0;
        Editor edit = SingApplication.f().getSharedPreferences("sing_prefs", 0).edit();
        StringBuilder stringBuilder = new StringBuilder();
        while (i < arrayList.size()) {
            stringBuilder.append(arrayList.get(i)).append(",");
            i++;
        }
        edit.putString("ONBOARD_TOPICS_KEY", stringBuilder.toString());
        edit.apply();
    }

    @Click
    public void m20516B() {
        m20501C();
    }

    public boolean mo6400c() {
        m20501C();
        return true;
    }

    private void m20501C() {
        m20507a(new C38932(this));
    }

    private void m20507a(CustomAlertDialogListener customAlertDialogListener) {
        TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.core_are_you_sure), getString(C1947R.string.onboarding_skip_content));
        textAlertDialog.m19806a(getString(C1947R.string.core_yes), getString(C1947R.string.core_no));
        textAlertDialog.m19803a(customAlertDialogListener);
        textAlertDialog.show();
    }

    private void m20502D() {
        if (isAdded()) {
            TextAlertDialog textAlertDialog = new TextAlertDialog(getActivity(), getString(C1947R.string.onboarding_network_failure_title), getString(C1947R.string.onboarding_network_failure_body));
            textAlertDialog.m19812c(false);
            textAlertDialog.m19806a(getString(C1947R.string.onboarding_network_failure_retry), null);
            textAlertDialog.m19804a(new C38943(this));
            textAlertDialog.setOnKeyListener(new C38954(this));
            textAlertDialog.show();
        }
    }

    public String mo6383s() {
        return f19000j;
    }

    private void m20503E() {
        if (isAdded()) {
            this.f19005i.findViewById(C1947R.id.loading_spinner).setVisibility(8);
            this.f19005i.findViewById(C1947R.id.footer_spacer).setVisibility(0);
        }
    }

    private void m20513c(boolean z) {
        int i = 0;
        if (isAdded()) {
            int i2;
            RelativeLayout relativeLayout = this.f19005i;
            if (z) {
                i2 = 4;
            } else {
                i2 = 0;
            }
            relativeLayout.setVisibility(i2);
            ProgressBar progressBar = this.f19004h;
            if (!z) {
                i = 4;
            }
            progressBar.setVisibility(i);
        }
    }
}
