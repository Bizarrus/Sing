package com.smule.singandroid.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.Html.TagHandler;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.MetricAffectingSpan;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.smule.android.logging.Log;
import com.smule.android.magicui.lists.MagicListView;
import com.smule.android.magicui.lists.adapters.MagicAdapter;
import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.Notification$Type;
import com.smule.android.network.models.NotificationListItem;
import com.smule.android.utils.WeakListener.OnGlobalLayoutListener;
import com.smule.singandroid.BaseFragment;
import com.smule.singandroid.C1947R;
import java.lang.reflect.Method;
import org.xml.sax.XMLReader;

public class LayoutUtils {
    static final String f24766a = LayoutUtils.class.getName();

    private static class DefaultClickableSpan extends ClickableSpan {
        private OnClickListener f24763a;

        DefaultClickableSpan(OnClickListener onClickListener) {
            this.f24763a = onClickListener;
        }

        public void onClick(View view) {
            if (this.f24763a != null) {
                this.f24763a.onClick(view);
            }
        }

        public void updateDrawState(TextPaint textPaint) {
        }
    }

    public static class SmuleHtmlTagHandler implements TagHandler {
        Context f24764a;

        public SmuleHtmlTagHandler(Context context) {
            this.f24764a = context;
        }

        public void handleTag(boolean z, String str, Editable editable, XMLReader xMLReader) {
            if (str.startsWith("sm-")) {
                m25840a(z, editable, str);
            }
        }

        private void m25840a(boolean z, Editable editable, String str) {
            int length = editable.length();
            if (z) {
                editable.setSpan(new SmuleTagSpan(str), length, length, 17);
                return;
            }
            Object a = m25839a(editable, SmuleTagSpan.class);
            int spanStart = editable.getSpanStart(a);
            editable.removeSpan(a);
            if (spanStart != length) {
                editable.setSpan(new SmuleTagSpan(str), spanStart, length, 33);
            }
        }

        private Object m25839a(Editable editable, Class cls) {
            Object[] spans = editable.getSpans(0, editable.length(), cls);
            if (spans.length == 0) {
                return null;
            }
            for (int length = spans.length; length > 0; length--) {
                if (editable.getSpanFlags(spans[length - 1]) == 17) {
                    return spans[length - 1];
                }
            }
            return null;
        }
    }

    public static class SmuleTagSpan extends MetricAffectingSpan {
        private String f24765a = "";

        public SmuleTagSpan(String str) {
            this.f24765a = str;
        }

        public void updateMeasureState(TextPaint textPaint) {
        }

        public void updateDrawState(TextPaint textPaint) {
        }

        public static SmuleTagSpan m25842a(SmuleTagSpan[] smuleTagSpanArr, String str) {
            for (SmuleTagSpan smuleTagSpan : smuleTagSpanArr) {
                if (smuleTagSpan.f24765a.equals(str)) {
                    return smuleTagSpan;
                }
            }
            return null;
        }

        public static SmuleTagSpan m25841a(SpannableString spannableString, String str) {
            return m25842a((SmuleTagSpan[]) spannableString.getSpans(0, spannableString.length(), SmuleTagSpan.class), str);
        }
    }

    public static int m25843a(int i, Context context) {
        return (int) ((context.getResources().getDisplayMetrics().density * ((float) i)) + 0.5f);
    }

    public static boolean m25858a(Context context) {
        return context.getResources().getBoolean(C1947R.bool.is_tablet);
    }

    public static int m25845a(Notification$Type notification$Type) {
        switch (notification$Type) {
            case FAVORITE:
                return C1947R.drawable.icn_timeline_favorited;
            case COMMENT:
                return C1947R.drawable.icn_timeline_comments;
            case MENTION:
                return C1947R.drawable.icn_timeline_mentions;
            case FOLLOW:
                return C1947R.drawable.icn_timeline_follows;
            case INVITE:
                return C1947R.drawable.icn_timeline_invites;
            case JOIN:
                return C1947R.drawable.icn_timeline_joins;
            case FOLLOW_FB:
                return C1947R.drawable.icn_timeline_follows;
            case CONNECT_FB:
                return C1947R.drawable.icn_timeline_follows;
            case RENDER:
                return C1947R.drawable.icn_timeline_joins;
            default:
                return C1947R.drawable.icn_timeline_loves;
        }
    }

    private static String m25851a(AccountIcon accountIcon) {
        return (accountIcon.c() ? "*" : "") + accountIcon.handle;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.text.Spanned m25849a(com.smule.android.network.models.NotificationListItem r10, android.content.Context r11) {
        /*
        r9 = 2;
        r8 = 1;
        r7 = 0;
        r3 = "";
        r4 = r11.getResources();
        r0 = com.smule.android.network.models.PerformanceV2$PerformanceType.SOLO;
        r1 = r10.object;
        if (r1 == 0) goto L_0x0482;
    L_0x000f:
        r0 = r10.object;
        r0 = r0.performanceIcon;
        r0 = r0.i();
        r1 = r0;
    L_0x0018:
        r2 = "";
        r0 = r10.content;
        if (r0 == 0) goto L_0x047f;
    L_0x001e:
        r5 = com.smule.android.utils.JsonUtils.m18984a();	 Catch:{ IOException -> 0x0065 }
        r6 = com.fasterxml.jackson.databind.JsonNode.class;
        r0 = r5.readValue(r0, r6);	 Catch:{ IOException -> 0x0065 }
        r0 = (com.fasterxml.jackson.databind.JsonNode) r0;	 Catch:{ IOException -> 0x0065 }
        r5 = "name";
        r6 = "";
        r0 = com.smule.android.utils.JsonUtils.m18986a(r0, r5, r6);	 Catch:{ IOException -> 0x0065 }
    L_0x0032:
        r2 = com.smule.singandroid.utils.LayoutUtils.C49932.f24761a;
        r5 = r10.a();
        r5 = r5.ordinal();
        r2 = r2[r5];
        switch(r2) {
            case 1: goto L_0x0068;
            case 2: goto L_0x00dc;
            case 3: goto L_0x0151;
            case 4: goto L_0x01d0;
            case 5: goto L_0x0280;
            case 6: goto L_0x02cc;
            case 7: goto L_0x0302;
            case 8: goto L_0x0391;
            case 9: goto L_0x03e9;
            case 10: goto L_0x0441;
            default: goto L_0x0041;
        };
    L_0x0041:
        r0 = r3;
    L_0x0042:
        r1 = "<b>";
        r1 = java.util.regex.Pattern.quote(r1);
        r2 = "<font color=\"#3d3839\"><b>";
        r0 = r0.replaceAll(r1, r2);
        r1 = "</b>";
        r1 = java.util.regex.Pattern.quote(r1);
        r2 = "</b></font>";
        r0 = r0.replaceAll(r1, r2);
        r1 = 0;
        r2 = new com.smule.singandroid.utils.LayoutUtils$SmuleHtmlTagHandler;
        r2.<init>(r11);
        r0 = android.text.Html.fromHtml(r0, r1, r2);
        return r0;
    L_0x0065:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0032;
    L_0x0068:
        r0 = r10.count;
        if (r0 != r8) goto L_0x00a0;
    L_0x006c:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x0098;
            case 2: goto L_0x009c;
            default: goto L_0x0077;
        };
    L_0x0077:
        r0 = 2131296860; // 0x7f09025c float:1.8211649E38 double:1.0530005596E-314;
    L_0x007a:
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r8];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = java.lang.String.format(r1, r2);
    L_0x0092:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.LOVE_NON_SUPPRESSED;
        r10.a(r1);
        goto L_0x0042;
    L_0x0098:
        r0 = 2131296858; // 0x7f09025a float:1.8211645E38 double:1.0530005586E-314;
        goto L_0x007a;
    L_0x009c:
        r0 = 2131296859; // 0x7f09025b float:1.8211647E38 double:1.053000559E-314;
        goto L_0x007a;
    L_0x00a0:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x00d2;
            case 2: goto L_0x00d7;
            default: goto L_0x00ab;
        };
    L_0x00ab:
        r0 = 2131361824; // 0x7f0a0020 float:1.8343411E38 double:1.053032656E-314;
        r1 = r0;
    L_0x00af:
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x0092;
    L_0x00d2:
        r0 = 2131361822; // 0x7f0a001e float:1.8343407E38 double:1.053032655E-314;
        r1 = r0;
        goto L_0x00af;
    L_0x00d7:
        r0 = 2131361823; // 0x7f0a001f float:1.834341E38 double:1.0530326556E-314;
        r1 = r0;
        goto L_0x00af;
    L_0x00dc:
        r0 = r10.count;
        if (r0 != r8) goto L_0x0115;
    L_0x00e0:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x010d;
            case 2: goto L_0x0111;
            default: goto L_0x00eb;
        };
    L_0x00eb:
        r0 = 2131296833; // 0x7f090241 float:1.8211594E38 double:1.0530005463E-314;
    L_0x00ee:
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r8];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = java.lang.String.format(r1, r2);
    L_0x0106:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.FAVORITE_NON_SUPPRESSED;
        r10.a(r1);
        goto L_0x0042;
    L_0x010d:
        r0 = 2131296831; // 0x7f09023f float:1.821159E38 double:1.0530005453E-314;
        goto L_0x00ee;
    L_0x0111:
        r0 = 2131296832; // 0x7f090240 float:1.8211592E38 double:1.053000546E-314;
        goto L_0x00ee;
    L_0x0115:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x0147;
            case 2: goto L_0x014c;
            default: goto L_0x0120;
        };
    L_0x0120:
        r0 = 2131361811; // 0x7f0a0013 float:1.8343385E38 double:1.0530326497E-314;
        r1 = r0;
    L_0x0124:
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x0106;
    L_0x0147:
        r0 = 2131361809; // 0x7f0a0011 float:1.834338E38 double:1.0530326487E-314;
        r1 = r0;
        goto L_0x0124;
    L_0x014c:
        r0 = 2131361810; // 0x7f0a0012 float:1.8343383E38 double:1.053032649E-314;
        r1 = r0;
        goto L_0x0124;
    L_0x0151:
        r0 = r10.count;
        if (r0 != r8) goto L_0x018e;
    L_0x0155:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x0186;
            case 2: goto L_0x018a;
            default: goto L_0x0160;
        };
    L_0x0160:
        r0 = 2131296829; // 0x7f09023d float:1.8211586E38 double:1.0530005443E-314;
    L_0x0163:
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r9];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = r10.content;
        r2[r8] = r0;
        r0 = java.lang.String.format(r1, r2);
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.COMMENT_NON_AGGREGATED;
        r10.a(r1);
        goto L_0x0042;
    L_0x0186:
        r0 = 2131296827; // 0x7f09023b float:1.8211582E38 double:1.0530005433E-314;
        goto L_0x0163;
    L_0x018a:
        r0 = 2131296828; // 0x7f09023c float:1.8211584E38 double:1.053000544E-314;
        goto L_0x0163;
    L_0x018e:
        r0 = com.smule.singandroid.utils.LayoutUtils.C49932.f24762b;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x01c6;
            case 2: goto L_0x01cb;
            default: goto L_0x0199;
        };
    L_0x0199:
        r0 = 2131361808; // 0x7f0a0010 float:1.8343379E38 double:1.053032648E-314;
        r1 = r0;
    L_0x019d:
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.COMMENT_AGGREGATED;
        r10.a(r1);
        goto L_0x0042;
    L_0x01c6:
        r0 = 2131361806; // 0x7f0a000e float:1.8343375E38 double:1.053032647E-314;
        r1 = r0;
        goto L_0x019d;
    L_0x01cb:
        r0 = 2131361807; // 0x7f0a000f float:1.8343377E38 double:1.0530326477E-314;
        r1 = r0;
        goto L_0x019d;
    L_0x01d0:
        r0 = r10.c();
        r1 = com.smule.android.network.models.Notification$EntityType.PERFORMANCE;
        if (r0 != r1) goto L_0x0228;
    L_0x01d8:
        r0 = r10.count;
        if (r0 != r8) goto L_0x0202;
    L_0x01dc:
        r0 = 2131296862; // 0x7f09025e float:1.8211653E38 double:1.0530005606E-314;
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r9];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = r10.content;
        r2[r8] = r0;
        r0 = java.lang.String.format(r1, r2);
    L_0x01fb:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.MENTION_PERFORMANCE;
        r10.a(r1);
        goto L_0x0042;
    L_0x0202:
        r1 = 2131361826; // 0x7f0a0022 float:1.8343415E38 double:1.053032657E-314;
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x01fb;
    L_0x0228:
        r0 = r10.c();
        r1 = com.smule.android.network.models.Notification$EntityType.ACCOUNT;
        if (r0 != r1) goto L_0x0041;
    L_0x0230:
        r0 = r10.count;
        if (r0 != r8) goto L_0x025a;
    L_0x0234:
        r0 = 2131296861; // 0x7f09025d float:1.821165E38 double:1.05300056E-314;
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r9];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = r10.content;
        r2[r8] = r0;
        r0 = java.lang.String.format(r1, r2);
    L_0x0253:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.MENTION_ACCOUNT;
        r10.a(r1);
        goto L_0x0042;
    L_0x025a:
        r1 = 2131361825; // 0x7f0a0021 float:1.8343413E38 double:1.0530326566E-314;
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x0253;
    L_0x0280:
        r0 = r10.count;
        if (r0 != r8) goto L_0x02a6;
    L_0x0284:
        r0 = 2131296852; // 0x7f090254 float:1.8211632E38 double:1.0530005557E-314;
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r8];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = java.lang.String.format(r1, r2);
    L_0x029f:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.FOLLOW;
        r10.a(r1);
        goto L_0x0042;
    L_0x02a6:
        r1 = 2131361818; // 0x7f0a001a float:1.83434E38 double:1.053032653E-314;
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x029f;
    L_0x02cc:
        r0 = r10.count;
        if (r0 != r8) goto L_0x02ed;
    L_0x02d0:
        r0 = 2131296854; // 0x7f090256 float:1.8211636E38 double:1.0530005567E-314;
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r8];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = java.lang.String.format(r1, r2);
        goto L_0x0042;
    L_0x02ed:
        r0 = 2131361819; // 0x7f0a001b float:1.8343401E38 double:1.0530326536E-314;
        r1 = r10.count;
        r2 = new java.lang.Object[r8];
        r3 = r10.count;
        r3 = java.lang.Integer.valueOf(r3);
        r2[r7] = r3;
        r0 = r4.getQuantityString(r0, r1, r2);
        goto L_0x0042;
    L_0x0302:
        r0 = r10.count;
        if (r0 != r8) goto L_0x0335;
    L_0x0306:
        r0 = com.smule.android.network.models.PerformanceV2$PerformanceType.DUET;
        if (r1 != r0) goto L_0x032c;
    L_0x030a:
        r0 = 2131296855; // 0x7f090257 float:1.8211638E38 double:1.053000557E-314;
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.JOIN_DUET;
        r10.a(r1);
    L_0x0312:
        r1 = r4.getString(r0);
        r2 = new java.lang.Object[r8];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r7] = r0;
        r0 = java.lang.String.format(r1, r2);
        goto L_0x0042;
    L_0x032c:
        r0 = 2131296856; // 0x7f090258 float:1.821164E38 double:1.0530005576E-314;
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.JOIN_GROUP;
        r10.a(r1);
        goto L_0x0312;
    L_0x0335:
        r0 = com.smule.android.network.models.PerformanceV2$PerformanceType.DUET;
        if (r1 != r0) goto L_0x0365;
    L_0x0339:
        r1 = 2131361820; // 0x7f0a001c float:1.8343403E38 double:1.053032654E-314;
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r7] = r0;
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.JOIN_DUET;
        r10.a(r1);
        goto L_0x0042;
    L_0x0365:
        r1 = 2131361821; // 0x7f0a001d float:1.8343405E38 double:1.0530326546E-314;
        r0 = r10.count;
        r2 = r0 + -1;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.JOIN_GROUP;
        r10.a(r1);
        goto L_0x0042;
    L_0x0391:
        r1 = r10.count;
        if (r1 != 0) goto L_0x03a3;
    L_0x0395:
        r0 = 2131296835; // 0x7f090243 float:1.8211598E38 double:1.0530005473E-314;
        r0 = r4.getString(r0);
    L_0x039c:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.FOLLOW_FB;
        r10.a(r1);
        goto L_0x0042;
    L_0x03a3:
        r1 = r10.count;
        if (r1 != r8) goto L_0x03c5;
    L_0x03a7:
        r1 = 2131296834; // 0x7f090242 float:1.8211596E38 double:1.053000547E-314;
        r1 = r4.getString(r1);
        r2 = new java.lang.Object[r9];
        r2[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r8] = r0;
        r0 = java.lang.String.format(r1, r2);
        goto L_0x039c;
    L_0x03c5:
        r1 = 2131361812; // 0x7f0a0014 float:1.8343387E38 double:1.05303265E-314;
        r2 = r10.count;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x039c;
    L_0x03e9:
        r1 = r10.count;
        if (r1 != 0) goto L_0x03fb;
    L_0x03ed:
        r0 = 2131296837; // 0x7f090245 float:1.8211602E38 double:1.0530005483E-314;
        r0 = r4.getString(r0);
    L_0x03f4:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.CONNECT_FB;
        r10.a(r1);
        goto L_0x0042;
    L_0x03fb:
        r1 = r10.count;
        if (r1 != r8) goto L_0x041d;
    L_0x03ff:
        r1 = 2131296836; // 0x7f090244 float:1.82116E38 double:1.053000548E-314;
        r1 = r4.getString(r1);
        r2 = new java.lang.Object[r9];
        r2[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r2[r8] = r0;
        r0 = java.lang.String.format(r1, r2);
        goto L_0x03f4;
    L_0x041d:
        r1 = 2131361813; // 0x7f0a0015 float:1.8343389E38 double:1.0530326507E-314;
        r2 = r10.count;
        r3 = new java.lang.Object[r9];
        r0 = r10.count;
        r0 = r0 + -1;
        r0 = java.lang.Integer.valueOf(r0);
        r3[r7] = r0;
        r0 = r10.subjects;
        r0 = r0.get(r7);
        r0 = (com.smule.android.network.models.AccountIcon) r0;
        r0 = m25851a(r0);
        r3[r8] = r0;
        r0 = r4.getQuantityString(r1, r2, r3);
        goto L_0x03f4;
    L_0x0441:
        r0 = r10.count;
        if (r0 != r8) goto L_0x046b;
    L_0x0445:
        r0 = r10.object;
        r0 = r0.performanceIcon;
        r0 = r0.i();
        r1 = com.smule.android.network.models.PerformanceV2$PerformanceType.SOLO;
        r0 = r0.equals(r1);
        if (r0 == 0) goto L_0x0463;
    L_0x0455:
        r0 = 2131297279; // 0x7f0903ff float:1.8212498E38 double:1.0530007666E-314;
        r0 = r4.getString(r0);
    L_0x045c:
        r1 = com.smule.android.network.models.NotificationListItem$DetailedType.RENDER;
        r10.a(r1);
        goto L_0x0042;
    L_0x0463:
        r0 = 2131296691; // 0x7f0901b3 float:1.8211306E38 double:1.053000476E-314;
        r0 = r4.getString(r0);
        goto L_0x045c;
    L_0x046b:
        r0 = 2131361849; // 0x7f0a0039 float:1.8343462E38 double:1.0530326684E-314;
        r1 = r10.count;
        r2 = new java.lang.Object[r8];
        r3 = r10.count;
        r3 = java.lang.Integer.valueOf(r3);
        r2[r7] = r3;
        r0 = r4.getQuantityString(r0, r1, r2);
        goto L_0x045c;
    L_0x047f:
        r0 = r2;
        goto L_0x0032;
    L_0x0482:
        r1 = r0;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.smule.singandroid.utils.LayoutUtils.a(com.smule.android.network.models.NotificationListItem, android.content.Context):android.text.Spanned");
    }

    public static void m25855a(View view, boolean z) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (z) {
            layoutParams.addRule(15);
        } else {
            layoutParams.addRule(15, 0);
        }
        view.setLayoutParams(layoutParams);
    }

    public static void m25857a(NotificationListItem notificationListItem, Context context, BaseFragment baseFragment, TextView textView, boolean z, OnClickListener onClickListener, OnClickListener onClickListener2, Runnable runnable) {
        CharSequence a = m25849a(notificationListItem, context);
        if (notificationListItem.subjects.size() < 1) {
            textView.setText(a);
            if (notificationListItem.a() == Notification$Type.RENDER) {
                textView.setOnClickListener(onClickListener);
                return;
            }
            return;
        }
        int i;
        textView.setOnClickListener(null);
        SpannableString spannableString = new SpannableString(a);
        SmuleTagSpan a2 = SmuleTagSpan.m25841a(spannableString, "sm-agg");
        int i2 = 0;
        ClickableSpan clickableSpan = null;
        if (a2 == null || runnable == null) {
            i = 0;
        } else {
            i2 = spannableString.getSpanStart(a2);
            int spanEnd = spannableString.getSpanEnd(a2);
            final Runnable runnable2 = runnable;
            clickableSpan = new ClickableSpan() {
                public void onClick(View view) {
                    runnable2.run();
                }

                public void updateDrawState(TextPaint textPaint) {
                }
            };
            i = spanEnd;
        }
        AccountIcon accountIcon = (AccountIcon) notificationListItem.subjects.get(0);
        if (accountIcon != null) {
            String str = accountIcon.handle;
            spanEnd = a.toString().indexOf(str);
            if (spanEnd < 0) {
                textView.setText(a);
                return;
            }
            if (spanEnd != 0 && accountIcon.c()) {
                spannableString.setSpan(PerformanceV2Util.m25937a(context.getResources()), spanEnd - 1, spanEnd, 17);
            }
            m25852a(spannableString, spanEnd, str.length() + spanEnd, new DefaultClickableSpan(onClickListener2), i2, i + 3, clickableSpan, onClickListener);
        }
        textView.setText(spannableString, BufferType.SPANNABLE);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private static void m25852a(SpannableString spannableString, int i, int i2, ClickableSpan clickableSpan, int i3, int i4, ClickableSpan clickableSpan2, OnClickListener onClickListener) {
        if (i < i3) {
            m25853a(spannableString, 0, i, new DefaultClickableSpan(onClickListener));
            m25853a(spannableString, i, i2, clickableSpan);
            m25853a(spannableString, i2, i3, new DefaultClickableSpan(onClickListener));
            m25853a(spannableString, i3, i4, clickableSpan2);
            m25853a(spannableString, i4, spannableString.length(), new DefaultClickableSpan(onClickListener));
            return;
        }
        m25853a(spannableString, 0, i3, new DefaultClickableSpan(onClickListener));
        m25853a(spannableString, i3, i4, clickableSpan2);
        m25853a(spannableString, i4, i, new DefaultClickableSpan(onClickListener));
        m25853a(spannableString, i, i2, clickableSpan);
        m25853a(spannableString, i2, spannableString.length(), new DefaultClickableSpan(onClickListener));
    }

    private static void m25853a(SpannableString spannableString, int i, int i2, Object obj) {
        if (i2 > i) {
            spannableString.setSpan(obj, i, i2, 33);
        }
    }

    public static String m25850a(int i) {
        if (i <= 0) {
            return "";
        }
        if (i > 99) {
            return "99+";
        }
        return Integer.toString(i);
    }

    public static int m25844a(TextView textView) {
        Display defaultDisplay = ((WindowManager) textView.getContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        textView.measure(MeasureSpec.makeMeasureSpec(point.x, Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(0, 0));
        return textView.getMeasuredHeight();
    }

    public static Point m25848a(View view, View view2) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int[] iArr2 = new int[2];
        view2.getLocationInWindow(iArr2);
        Point point = new Point();
        point.x = iArr[0] - iArr2[0];
        point.y = iArr[1] - iArr2[1];
        return point;
    }

    @SuppressLint({"addOnGlobalLayoutListenerNotAllowed"})
    public static void m25854a(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            Log.e(f24766a, "addOnGlobalLayoutListener ViewTreeObserver not available");
        } else {
            viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void m25859b(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
            Log.e(f24766a, "removeOnGlobalLayoutListener ViewTreeObserver not available");
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static Point m25846a(Activity activity) {
        return m25847a(activity.getWindowManager().getDefaultDisplay());
    }

    public static Point m25847a(Display display) {
        if (display == null) {
            return new Point(0, 0);
        }
        int i;
        int i2;
        if (VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else if (VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                i = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(display, new Object[0])).intValue();
            } catch (Exception e) {
                i = display.getWidth();
                i2 = display.getHeight();
                Log.e("Display Info", "Couldn't use reflection to get the real display metrics.");
            }
        } else {
            i = display.getWidth();
            i2 = display.getHeight();
        }
        return new Point(i, i2);
    }

    public static void m25856a(MagicListView magicListView) {
        MagicAdapter wrappedAdapter = magicListView.getWrappedAdapter();
        if (wrappedAdapter != null) {
            int paddingTop = magicListView.getPaddingTop() + magicListView.getPaddingBottom();
            for (int i = 0; i < wrappedAdapter.m18026a().m17661k(); i++) {
                View a = wrappedAdapter.mo6418a(null, 0);
                wrappedAdapter.mo6419a(a, i, wrappedAdapter.mo6441c(i));
                a.measure(0, 0);
                paddingTop += a.getMeasuredHeight();
            }
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) magicListView.getLayoutParams();
            layoutParams.height = ((layoutParams.topMargin + layoutParams.bottomMargin) + paddingTop) + (magicListView.getDividerHeight() * (wrappedAdapter.m18048d() - 1));
            magicListView.setLayoutParams(layoutParams);
        }
    }
}
