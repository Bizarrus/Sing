package com.smule.singandroid.hashtag;

import android.text.Layout;
import android.text.NoCopySpan.Concrete;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

public class CustomLinkMovementMethod extends ScrollingMovementMethod {
    private static Object f22683b = new Concrete();
    TextClickedListener f22684a = null;

    public interface TextClickedListener {
        void mo6871a();
    }

    public void m24186a(TextClickedListener textClickedListener) {
        this.f22684a = textClickedListener;
    }

    public boolean onKeyDown(TextView textView, Spannable spannable, int i, KeyEvent keyEvent) {
        switch (i) {
            case 23:
            case 66:
                if (keyEvent.getRepeatCount() == 0 && m24185a(1, textView, spannable)) {
                    return true;
                }
        }
        return super.onKeyDown(textView, spannable, i, keyEvent);
    }

    protected boolean up(TextView textView, Spannable spannable) {
        if (m24185a(2, textView, spannable)) {
            return true;
        }
        return super.up(textView, spannable);
    }

    protected boolean down(TextView textView, Spannable spannable) {
        if (m24185a(3, textView, spannable)) {
            return true;
        }
        return super.down(textView, spannable);
    }

    protected boolean left(TextView textView, Spannable spannable) {
        if (m24185a(2, textView, spannable)) {
            return true;
        }
        return super.left(textView, spannable);
    }

    protected boolean right(TextView textView, Spannable spannable) {
        if (m24185a(3, textView, spannable)) {
            return true;
        }
        return super.right(textView, spannable);
    }

    private boolean m24185a(int i, TextView textView, Spannable spannable) {
        Layout layout = textView.getLayout();
        int totalPaddingTop = textView.getTotalPaddingTop() + textView.getTotalPaddingBottom();
        int scrollY = textView.getScrollY();
        totalPaddingTop = (textView.getHeight() + scrollY) - totalPaddingTop;
        scrollY = layout.getLineForVertical(scrollY);
        totalPaddingTop = layout.getLineForVertical(totalPaddingTop);
        int lineStart = layout.getLineStart(scrollY);
        int lineEnd = layout.getLineEnd(totalPaddingTop);
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(lineStart, lineEnd, ClickableSpan.class);
        totalPaddingTop = Selection.getSelectionStart(spannable);
        int selectionEnd = Selection.getSelectionEnd(spannable);
        scrollY = Math.min(totalPaddingTop, selectionEnd);
        totalPaddingTop = Math.max(totalPaddingTop, selectionEnd);
        if (scrollY < 0 && spannable.getSpanStart(f22683b) >= 0) {
            totalPaddingTop = spannable.length();
            scrollY = totalPaddingTop;
        }
        if (scrollY > lineEnd) {
            totalPaddingTop = Integer.MAX_VALUE;
            scrollY = Integer.MAX_VALUE;
        }
        if (totalPaddingTop < lineStart) {
            lineEnd = -1;
            selectionEnd = -1;
        } else {
            lineEnd = totalPaddingTop;
            selectionEnd = scrollY;
        }
        int i2;
        switch (i) {
            case 1:
                if (selectionEnd == lineEnd) {
                    return false;
                }
                clickableSpanArr = (ClickableSpan[]) spannable.getSpans(selectionEnd, lineEnd, ClickableSpan.class);
                if (clickableSpanArr.length != 1) {
                    return false;
                }
                clickableSpanArr[0].onClick(textView);
                return false;
            case 2:
                totalPaddingTop = -1;
                int i3 = -1;
                for (i2 = 0; i2 < clickableSpanArr.length; i2++) {
                    scrollY = spannable.getSpanEnd(clickableSpanArr[i2]);
                    if ((scrollY < lineEnd || selectionEnd == lineEnd) && scrollY > totalPaddingTop) {
                        i3 = spannable.getSpanStart(clickableSpanArr[i2]);
                        totalPaddingTop = scrollY;
                    }
                }
                if (i3 < 0) {
                    return false;
                }
                Selection.setSelection(spannable, totalPaddingTop, i3);
                return true;
            case 3:
                totalPaddingTop = Integer.MAX_VALUE;
                scrollY = Integer.MAX_VALUE;
                for (i2 = 0; i2 < clickableSpanArr.length; i2++) {
                    lineStart = spannable.getSpanStart(clickableSpanArr[i2]);
                    if ((lineStart > selectionEnd || selectionEnd == lineEnd) && lineStart < scrollY) {
                        totalPaddingTop = spannable.getSpanEnd(clickableSpanArr[i2]);
                        scrollY = lineStart;
                    }
                }
                if (totalPaddingTop >= Integer.MAX_VALUE) {
                    return false;
                }
                Selection.setSelection(spannable, scrollY, totalPaddingTop);
                return true;
            default:
                return false;
        }
    }

    public boolean onKeyUp(TextView textView, Spannable spannable, int i, KeyEvent keyEvent) {
        return false;
    }

    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 0) {
            int x = (((int) motionEvent.getX()) - textView.getTotalPaddingLeft()) + textView.getScrollX();
            int y = (((int) motionEvent.getY()) - textView.getTotalPaddingTop()) + textView.getScrollY();
            Layout layout = textView.getLayout();
            x = layout.getOffsetForHorizontal(layout.getLineForVertical(y), (float) x);
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) spannable.getSpans(x, x, ClickableSpan.class);
            if (clickableSpanArr.length != 0) {
                if (action == 1) {
                    clickableSpanArr[0].onClick(textView);
                } else if (action == 0) {
                    Selection.setSelection(spannable, spannable.getSpanStart(clickableSpanArr[0]), spannable.getSpanEnd(clickableSpanArr[0]));
                }
                return true;
            }
            Selection.removeSelection(spannable);
            if (action == 1 && this.f22684a != null) {
                this.f22684a.mo6871a();
            }
        }
        return super.onTouchEvent(textView, spannable, motionEvent);
    }

    public void initialize(TextView textView, Spannable spannable) {
        Selection.removeSelection(spannable);
        spannable.removeSpan(f22683b);
    }

    public void onTakeFocus(TextView textView, Spannable spannable, int i) {
        Selection.removeSelection(spannable);
        if ((i & 1) != 0) {
            spannable.setSpan(f22683b, 0, 0, 34);
        } else {
            spannable.removeSpan(f22683b);
        }
    }
}
