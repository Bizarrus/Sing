package com.smule.singandroid.customviews;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smule.android.network.models.PerformanceV2;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.C1947R;
import com.smule.singandroid.PerformanceListItemContainer;
import com.smule.singandroid.SingServerValues;
import com.smule.singandroid.list_items.PerformanceListItem;
import com.smule.singandroid.utils.LayoutUtils;
import java.util.List;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup
public class StorageWarningView extends RelativeLayout {
    @ViewById
    protected View f22001a;
    @ViewById
    protected LinearLayout f22002b;
    @ViewById
    protected TextView f22003c;
    @ViewById
    protected Button f22004d;
    @ViewById
    protected LinearLayout f22005e;
    @ViewById
    protected FrameLayout f22006f;
    @ViewById
    protected FrameLayout f22007g;
    private Context f22008h;
    private boolean f22009i;
    private OnClickListener f22010j;
    private boolean f22011k;
    private OnGlobalLayoutListener f22012l = new OnGlobalLayoutListener(this, new C44461(this));

    class C44461 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ StorageWarningView f22000a;

        C44461(StorageWarningView storageWarningView) {
            this.f22000a = storageWarningView;
        }

        public void onGlobalLayout() {
            if (this.f22000a.f22002b != null && this.f22000a.f22005e != null && this.f22000a.f22005e.getChildCount() > 0) {
                this.f22000a.m23508f();
            }
        }
    }

    public StorageWarningView(Context context, boolean z, OnClickListener onClickListener) {
        super(context);
        this.f22008h = context;
        this.f22009i = z;
        this.f22010j = onClickListener;
    }

    public static StorageWarningView m23506a(Activity activity, boolean z, OnClickListener onClickListener) {
        return StorageWarningView_.m23516a(activity, z, onClickListener);
    }

    public void setShowFutureWarning(boolean z) {
        this.f22009i = z;
        if (this.f22009i) {
            m23515e();
            m23510a(0);
        }
    }

    public void m23509a() {
        if (this.f22009i) {
            this.f22003c.setText(this.f22008h.getResources().getString(C1947R.string.vip_storage_warning, new Object[]{Integer.valueOf(SingServerValues.m21677O())}));
            return;
        }
        this.f22003c.setText(this.f22008h.getResources().getString(C1947R.string.vip_storage_hidden_recordings));
    }

    @AfterViews
    protected void m23512b() {
        m23509a();
        this.f22004d.setOnClickListener(this.f22010j);
        if (!this.f22011k) {
            LayoutUtils.m25854a((View) this, this.f22012l);
            this.f22011k = true;
        }
    }

    public void m23513c() {
        if (this.f22005e.getChildCount() > 0) {
            this.f22005e.removeViewAt(0);
        }
    }

    public void m23511a(List<PerformanceListItemContainer> list) {
        this.f22005e.removeAllViews();
        for (PerformanceListItemContainer performanceListItemContainer : list) {
            View c = PerformanceListItem.m24362c(this.f22008h);
            PerformanceV2 a = performanceListItemContainer.m20650a();
            c.setRecordingType(a.video);
            c.setPerformance(a);
            c.setIsFirstElement(false);
            c.setOnClickListener(null);
            this.f22005e.addView(c);
        }
    }

    public boolean m23514d() {
        return this.f22005e.getChildCount() > 0;
    }

    private void m23508f() {
        if (this.f22007g.getHeight() == 0) {
            LayoutParams layoutParams = this.f22007g.getLayoutParams();
            layoutParams.height = this.f22002b.getHeight();
            this.f22007g.setLayoutParams(layoutParams);
        }
    }

    public void m23515e() {
        LayoutParams layoutParams = this.f22007g.getLayoutParams();
        layoutParams.height = 0;
        this.f22007g.setLayoutParams(layoutParams);
    }

    public int getWarningLayoutHeight() {
        if (this.f22002b == null) {
            return 0;
        }
        return this.f22002b.getHeight();
    }

    public int getInitialTopPos() {
        if (this.f22001a == null) {
            return 0;
        }
        int[] iArr = new int[2];
        this.f22001a.getLocationOnScreen(iArr);
        return iArr[1];
    }

    public void m23510a(int i) {
        if (this.f22002b != null) {
            this.f22002b.setTranslationY((float) i);
            this.f22006f.setBottom(this.f22001a.getHeight() + i);
        }
    }
}
