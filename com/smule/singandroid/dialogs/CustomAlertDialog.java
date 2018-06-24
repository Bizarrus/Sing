package com.smule.singandroid.dialogs;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import com.smule.android.logging.Log;
import com.smule.singandroid.C1947R;

public abstract class CustomAlertDialog extends SmuleDialog {
    private static final String f18446c = CustomAlertDialog.class.getName();
    protected View f18447a;
    protected View f18448b;
    private TextView f18449d;
    private ImageView f18450e;
    private Button f18451f;
    private Button f18452g;
    private Runnable f18453h;
    private Runnable f18454i;
    private ViewGroup f18455j;
    private LinearLayout f18456k;
    private CustomAlertDialogListener f18457l;
    private OnPreDrawListener f18458m;
    private CustomAlertDialog f18459n;
    private boolean f18460o;
    private boolean f18461p;
    private AnimatorListener f18462q;

    public interface CustomAlertDialogListener {
        void mo6385a(CustomAlertDialog customAlertDialog);

        void mo6386b(CustomAlertDialog customAlertDialog);
    }

    class C44821 implements AnimatorListener {
        final /* synthetic */ CustomAlertDialog f22237a;

        C44821(CustomAlertDialog customAlertDialog) {
            this.f22237a = customAlertDialog;
        }

        public void onAnimationStart(Animator animator) {
            this.f22237a.m19817g();
        }

        public void onAnimationEnd(Animator animator) {
            if (this.f22237a.isShowing() && this.f22237a.f18461p) {
                this.f22237a.f18456k.setAlpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
        }

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }
    }

    class C44832 implements OnClickListener {
        final /* synthetic */ CustomAlertDialog f22238a;

        C44832(CustomAlertDialog customAlertDialog) {
            this.f22238a = customAlertDialog;
        }

        public void onClick(View view) {
            this.f22238a.mo6374a();
        }
    }

    class C44843 implements OnClickListener {
        final /* synthetic */ CustomAlertDialog f22239a;

        C44843(CustomAlertDialog customAlertDialog) {
            this.f22239a = customAlertDialog;
        }

        public void onClick(View view) {
            this.f22239a.m19808b();
        }
    }

    class C44864 implements OnPreDrawListener {
        final /* synthetic */ CustomAlertDialog f22241a;

        class C44851 implements Runnable {
            final /* synthetic */ C44864 f22240a;

            C44851(C44864 c44864) {
                this.f22240a = c44864;
            }

            public void run() {
                this.f22240a.f22241a.f18455j.animate().scaleY(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).scaleX(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(400).setInterpolator(new DecelerateInterpolator(1.5f)).setListener(this.f22240a.f22241a.f18462q);
            }
        }

        C44864(CustomAlertDialog customAlertDialog) {
            this.f22241a = customAlertDialog;
        }

        public boolean onPreDraw() {
            if (!(this.f22241a.f18455j.getHeight() == 0 || this.f22241a.f18460o)) {
                this.f22241a.f18460o = true;
                float e = (float) this.f22241a.f18459n.m19815e();
                float width = (float) this.f22241a.f18456k.getWidth();
                this.f22241a.f18455j.setScaleY(((float) this.f22241a.f18459n.m19813d()) / ((float) this.f22241a.f18456k.getHeight()));
                this.f22241a.f18455j.setScaleX(e / width);
                this.f22241a.f18456k.setAlpha(0.0f);
                new Handler().postDelayed(new C44851(this), 50);
            }
            return true;
        }
    }

    class C44875 implements OnClickListener {
        final /* synthetic */ CustomAlertDialog f22242a;

        C44875(CustomAlertDialog customAlertDialog) {
            this.f22242a = customAlertDialog;
        }

        public void onClick(View view) {
            this.f22242a.m19808b();
        }
    }

    class C44886 implements OnClickListener {
        final /* synthetic */ CustomAlertDialog f22243a;

        C44886(CustomAlertDialog customAlertDialog) {
            this.f22243a = customAlertDialog;
        }

        public void onClick(View view) {
            this.f22243a.m19808b();
        }
    }

    public CustomAlertDialog(Context context, int i, boolean z, boolean z2, boolean z3) {
        this(context, i, z, z2, z3, null);
    }

    public CustomAlertDialog(Context context, int i, boolean z, boolean z2, boolean z3, CustomAlertDialog customAlertDialog) {
        int i2 = 0;
        super(context, C1947R.style.Theme.Transparent, z3);
        this.f18460o = false;
        this.f18462q = new C44821(this);
        LayoutInflater from = LayoutInflater.from(context);
        this.f18455j = (ViewGroup) from.inflate(C1947R.layout.custom_alert_dialog, null, false);
        from.inflate(i, (LinearLayout) this.f18455j.findViewById(C1947R.id.modal_container));
        setContentView(this.f18455j);
        this.f18456k = (LinearLayout) findViewById(C1947R.id.full_modal);
        this.f18447a = this.f18455j.findViewById(C1947R.id.background);
        this.f18448b = this.f18455j.findViewById(C1947R.id.foreground_frame);
        setCanceledOnTouchOutside(true);
        this.f18449d = (TextView) this.f18455j.findViewById(C1947R.id.title);
        this.f18450e = (ImageView) this.f18455j.findViewById(C1947R.id.image);
        this.f18451f = (Button) this.f18455j.findViewById(C1947R.id.yesButton);
        this.f18452g = (Button) this.f18455j.findViewById(C1947R.id.noButton);
        this.f18451f.setVisibility(z ? 0 : 8);
        this.f18451f.setOnClickListener(new C44832(this));
        Button button = this.f18452g;
        if (!z2) {
            i2 = 8;
        }
        button.setVisibility(i2);
        this.f18452g.setOnClickListener(new C44843(this));
        if (customAlertDialog != null) {
            this.f18459n = customAlertDialog;
            this.f18458m = new C44864(this);
            this.f18456k.getViewTreeObserver().addOnPreDrawListener(this.f18458m);
        }
    }

    public void m19803a(CustomAlertDialogListener customAlertDialogListener) {
        this.f18457l = customAlertDialogListener;
    }

    public void m19800a(int i, int i2) {
        String string;
        String str = null;
        if (i > 0) {
            string = getContext().getString(i);
        } else {
            string = null;
        }
        if (i2 > 0) {
            str = getContext().getString(i2);
        }
        m19806a(string, str);
    }

    public void m19807a(boolean z) {
        if (this.f18448b == null) {
            return;
        }
        if (z) {
            this.f18448b.setOnClickListener(new C44875(this));
        } else {
            this.f18448b.setOnClickListener(null);
        }
    }

    public void setCanceledOnTouchOutside(boolean z) {
        if (z) {
            this.f18447a.setOnClickListener(new C44886(this));
        } else {
            this.f18447a.setOnClickListener(null);
        }
    }

    public void m19805a(String str) {
        if (TextUtils.isEmpty(str)) {
            this.f18449d.setVisibility(8);
        } else {
            this.f18449d.setText(str);
        }
    }

    public void setTitle(int i) {
        this.f18449d.setText(getContext().getResources().getString(i));
    }

    public void m19806a(String str, String str2) {
        if (str != null) {
            this.f18451f.setText(str);
        } else {
            this.f18451f.setVisibility(8);
        }
        if (str2 != null) {
            this.f18452g.setText(str2);
        } else {
            this.f18452g.setVisibility(4);
        }
    }

    public void m19801a(int i, boolean z) {
        Drawable drawable = getContext().getResources().getDrawable(i);
        if (drawable != null) {
            this.f18450e.setImageDrawable(drawable);
        }
        if (z) {
            this.f18450e.setVisibility(0);
        } else {
            this.f18450e.setVisibility(8);
        }
    }

    public void m19799a(int i) {
        this.f18450e.setVisibility(8);
        FrameLayout frameLayout = (FrameLayout) this.f18455j.findViewById(C1947R.id.header_container);
        LayoutInflater.from(getContext()).inflate(i, frameLayout);
        frameLayout.setVisibility(0);
    }

    public void m19810b(boolean z) {
        this.f18451f.setClickable(z);
        this.f18451f.setEnabled(z);
    }

    public void m19812c(boolean z) {
        this.f18452g.setClickable(z);
        this.f18452g.setEnabled(z);
    }

    public void m19814d(boolean z) {
        if (z) {
            this.f18452g.setVisibility(8);
        } else {
            this.f18452g.setVisibility(this.f18452g.getVisibility() == 0 ? 0 : 4);
        }
    }

    public void m19804a(Runnable runnable) {
        this.f18453h = runnable;
    }

    public void m19809b(Runnable runnable) {
        this.f18454i = runnable;
    }

    protected void mo6374a() {
        if (!(this.f18457l == null || this.f18453h == null)) {
            Log.e(f18446c, "handleOK - both the listener and runnable are not null. Preferring listener over the runnable");
        }
        if (this.f18457l != null) {
            this.f18457l.mo6385a(this);
        } else if (this.f18453h != null) {
            this.f18453h.run();
        }
        dismiss();
    }

    protected void m19808b() {
        if (this.f18457l != null) {
            this.f18457l.mo6386b(this);
        } else if (this.f18454i != null) {
            this.f18454i.run();
        }
        dismiss();
    }

    protected void m19811c() {
        final LinearLayout linearLayout = (LinearLayout) findViewById(C1947R.id.button_layout);
        linearLayout.animate().setDuration(500).alpha(0.0f).setListener(new AnimatorListener(this) {
            final /* synthetic */ CustomAlertDialog f22245b;

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (this.f22245b.isShowing() && this.f22245b.f18461p) {
                    linearLayout.setVisibility(4);
                }
            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
    }

    public void onBackPressed() {
        m19808b();
    }

    public int m19813d() {
        return this.f18456k.getHeight();
    }

    public int m19815e() {
        return this.f18456k.getWidth();
    }

    public View m19816f() {
        return this.f18456k;
    }

    public void m19802a(AnimatorListener animatorListener) {
        this.f18462q = animatorListener;
    }

    public void m19817g() {
        if (this.f18459n != null) {
            this.f18459n.dismiss();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f18458m != null) {
            this.f18456k.getViewTreeObserver().removeOnPreDrawListener(this.f18458m);
        }
        this.f18461p = false;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f18461p = true;
    }
}
