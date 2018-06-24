/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.animation.Animator
 *  android.animation.Animator$AnimatorListener
 *  android.animation.TimeInterpolator
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.support.annotation.StringRes
 *  android.text.TextUtils
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewPropertyAnimator
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  android.view.animation.DecelerateInterpolator
 *  android.widget.Button
 *  android.widget.FrameLayout
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.TextView
 */
package com.smule.singandroid.dialogs;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smule.android.logging.Log;
import com.smule.android.ui.dialogs.SmuleDialog;

public abstract class CustomAlertDialog
extends SmuleDialog {
    private static final String c = CustomAlertDialog.class.getName();
    protected View a;
    protected View b;
    private TextView d;
    private ImageView e;
    private Button f;
    private Button g;
    private Runnable h;
    private Runnable i;
    private ViewGroup j;
    private LinearLayout k;
    private CustomAlertDialogListener l;
    private ViewTreeObserver.OnPreDrawListener m;
    private CustomAlertDialog n;
    private boolean o;
    private boolean p;
    private Animator.AnimatorListener q;

    /*
     * Enabled aggressive block sorting
     */
    public CustomAlertDialog(Context context, int n, int n2, int n3, boolean bl, boolean bl2, boolean bl3, CustomAlertDialog customAlertDialog) {
        int n4 = 0;
        int n5 = n2;
        if (n2 == 0) {
            n5 = 2131493570;
        }
        super(context, n5, bl3);
        this.o = false;
        this.q = new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
                if (CustomAlertDialog.this.isShowing() && CustomAlertDialog.this.p) {
                    CustomAlertDialog.this.k.setAlpha(1.0f);
                }
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
                CustomAlertDialog.this.i();
            }
        };
        context = LayoutInflater.from((Context)context);
        n2 = n;
        if (n == 0) {
            n2 = 2130903194;
        }
        this.j = (ViewGroup)context.inflate(n2, null, false);
        context.inflate(n3, (ViewGroup)((LinearLayout)this.j.findViewById(2131755635)));
        this.setContentView((View)this.j);
        this.k = (LinearLayout)this.findViewById(2131755633);
        this.a = this.j.findViewById(2131755416);
        this.b = this.j.findViewById(2131755632);
        this.setCanceledOnTouchOutside(true);
        this.d = (TextView)this.j.findViewById(2131755176);
        this.e = (ImageView)this.j.findViewById(2131755172);
        this.f = (Button)this.j.findViewById(2131755638);
        this.g = (Button)this.j.findViewById(2131755637);
        context = this.f;
        n = bl ? 0 : 8;
        context.setVisibility(n);
        this.f.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                CustomAlertDialog.this.c();
            }
        });
        context = this.g;
        n = bl2 ? n4 : 8;
        context.setVisibility(n);
        this.g.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                CustomAlertDialog.this.d();
            }
        });
        if (customAlertDialog != null) {
            this.n = customAlertDialog;
            this.m = new ViewTreeObserver.OnPreDrawListener(){

                public boolean onPreDraw() {
                    if (CustomAlertDialog.this.j.getHeight() != 0 && !CustomAlertDialog.this.o) {
                        CustomAlertDialog.this.o = true;
                        float f = CustomAlertDialog.this.n.f();
                        float f2 = CustomAlertDialog.this.n.g();
                        float f3 = CustomAlertDialog.this.k.getHeight();
                        float f4 = CustomAlertDialog.this.k.getWidth();
                        CustomAlertDialog.this.j.setScaleY(f / f3);
                        CustomAlertDialog.this.j.setScaleX(f2 / f4);
                        CustomAlertDialog.this.k.setAlpha(0.0f);
                        new Handler().postDelayed(new Runnable(){

                            @Override
                            public void run() {
                                CustomAlertDialog.this.j.animate().scaleY(1.0f).scaleX(1.0f).setDuration(400).setInterpolator((TimeInterpolator)new DecelerateInterpolator(1.5f)).setListener(CustomAlertDialog.this.q);
                            }
                        }, 50);
                    }
                    return true;
                }

            };
            this.k.getViewTreeObserver().addOnPreDrawListener(this.m);
        }
    }

    public CustomAlertDialog(Context context, int n, int n2, boolean bl, boolean bl2, boolean bl3, CustomAlertDialog customAlertDialog) {
        this(context, n, 0, n2, bl, bl2, bl3, customAlertDialog);
    }

    public CustomAlertDialog(Context context, int n, boolean bl, boolean bl2, boolean bl3) {
        this(context, n, bl, bl2, bl3, null);
    }

    public CustomAlertDialog(Context context, int n, boolean bl, boolean bl2, boolean bl3, CustomAlertDialog customAlertDialog) {
        this(context, 0, n, bl, bl2, bl3, customAlertDialog);
    }

    public void a() {
        this.j.findViewById(2131755635).setVisibility(8);
    }

    public void a(int n) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(n, n, n, 0);
        layoutParams.gravity = 1;
        this.e.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(@StringRes int n, @StringRes int n2) {
        String string2 = null;
        String string3 = n > 0 ? this.getContext().getString(n) : null;
        if (n2 > 0) {
            string2 = this.getContext().getString(n2);
        }
        this.a(string3, string2);
    }

    public void a(int n, boolean bl) {
        Drawable drawable2 = this.getContext().getResources().getDrawable(n);
        if (drawable2 != null) {
            this.e.setImageDrawable(drawable2);
        }
        if (bl) {
            this.e.setVisibility(0);
            return;
        }
        this.e.setVisibility(8);
    }

    public void a(Animator.AnimatorListener animatorListener) {
        this.q = animatorListener;
    }

    public void a(CustomAlertDialogListener customAlertDialogListener) {
        this.l = customAlertDialogListener;
    }

    public void a(Runnable runnable) {
        this.h = runnable;
    }

    public void a(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            this.d.setVisibility(8);
            return;
        }
        this.d.setText((CharSequence)string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void a(String string2, String string3) {
        if (string2 != null) {
            this.f.setText((CharSequence)string2);
        } else {
            this.f.setVisibility(8);
        }
        if (string3 != null) {
            this.g.setText((CharSequence)string3);
            return;
        }
        this.g.setVisibility(4);
    }

    public void a(boolean bl) {
        block3 : {
            block2 : {
                if (this.b == null) break block2;
                if (!bl) break block3;
                this.b.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View view) {
                        CustomAlertDialog.this.d();
                    }
                });
            }
            return;
        }
        this.b.setOnClickListener(null);
    }

    public void b() {
        this.d.setGravity(1);
    }

    public void b(int n) {
        this.e.setVisibility(8);
        LayoutInflater layoutInflater = LayoutInflater.from((Context)this.getContext());
        FrameLayout frameLayout = (FrameLayout)this.j.findViewById(2131755634);
        layoutInflater.inflate(n, (ViewGroup)frameLayout);
        frameLayout.setVisibility(0);
    }

    public void b(Runnable runnable) {
        this.i = runnable;
    }

    public void b(boolean bl) {
        this.f.setClickable(bl);
        this.f.setEnabled(bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void c() {
        if (this.l != null && this.h != null) {
            Log.e(c, "handleOK - both the listener and runnable are not null. Preferring listener over the runnable");
        }
        if (this.l != null) {
            this.l.a(this);
        } else if (this.h != null) {
            this.h.run();
        }
        this.dismiss();
    }

    public void c(boolean bl) {
        this.g.setClickable(bl);
        this.g.setEnabled(bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void d() {
        if (this.l != null) {
            this.l.b(this);
        } else if (this.i != null) {
            this.i.run();
        }
        this.dismiss();
    }

    /*
     * Enabled aggressive block sorting
     */
    public void d(boolean bl) {
        if (bl) {
            this.g.setVisibility(8);
            return;
        }
        Button button = this.g;
        int n = this.g.getVisibility() == 0 ? 0 : 4;
        button.setVisibility(n);
    }

    protected void e() {
        final LinearLayout linearLayout = (LinearLayout)this.findViewById(2131755636);
        linearLayout.animate().setDuration(500).alpha(0.0f).setListener(new Animator.AnimatorListener(){

            public void onAnimationCancel(Animator animator2) {
            }

            public void onAnimationEnd(Animator animator2) {
                if (CustomAlertDialog.this.isShowing() && CustomAlertDialog.this.p) {
                    linearLayout.setVisibility(4);
                }
            }

            public void onAnimationRepeat(Animator animator2) {
            }

            public void onAnimationStart(Animator animator2) {
            }
        });
    }

    public int f() {
        return this.k.getHeight();
    }

    public int g() {
        return this.k.getWidth();
    }

    public View h() {
        return this.k;
    }

    public void i() {
        if (this.n != null) {
            this.n.dismiss();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.p = true;
    }

    public void onBackPressed() {
        this.d();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.m != null) {
            this.k.getViewTreeObserver().removeOnPreDrawListener(this.m);
        }
        this.p = false;
    }

    public void setCanceledOnTouchOutside(boolean bl) {
        if (bl) {
            this.a.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    CustomAlertDialog.this.d();
                }
            });
            return;
        }
        this.a.setOnClickListener(null);
    }

    public void setTitle(int n) {
        this.d.setText((CharSequence)this.getContext().getResources().getString(n));
    }

    public static interface CustomAlertDialogListener {
        public void a(CustomAlertDialog var1);

        public void b(CustomAlertDialog var1);
    }

}

