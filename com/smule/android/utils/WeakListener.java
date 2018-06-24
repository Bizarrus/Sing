/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.annotation.SuppressLint
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewParent
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnGlobalLayoutListener
 *  android.widget.EditText
 *  android.widget.TextView
 *  android.widget.TextView$OnEditorActionListener
 */
package com.smule.android.utils;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.TextView;
import com.smule.android.utils.ReferenceMonitor;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakListener {
    @SuppressLint(value={"addTextChangedListenerNotAllowed"})
    public static void a(EditText editText, android.text.TextWatcher textWatcher) {
        editText.addTextChangedListener((android.text.TextWatcher)new TextWatcher((Object)editText.getParent(), textWatcher));
    }

    public static class OnClickListener
    extends WeakListenerTemplate<View.OnClickListener>
    implements View.OnClickListener {
        public OnClickListener(Object object, View.OnClickListener onClickListener) {
            super(object, onClickListener);
        }

        public void onClick(View view) {
            View.OnClickListener onClickListener = (View.OnClickListener)this.a();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public static class OnEditorActionListener
    extends WeakListenerTemplate<TextView.OnEditorActionListener>
    implements TextView.OnEditorActionListener {
        public OnEditorActionListener(Object object, TextView.OnEditorActionListener onEditorActionListener) {
            super(object, onEditorActionListener);
        }

        public boolean onEditorAction(TextView textView, int n, KeyEvent keyEvent) {
            TextView.OnEditorActionListener onEditorActionListener = (TextView.OnEditorActionListener)this.a();
            if (onEditorActionListener != null && onEditorActionListener.onEditorAction(textView, n, keyEvent)) {
                return true;
            }
            return false;
        }
    }

    public static class OnGlobalLayoutListener
    extends WeakListenerTemplate<ViewTreeObserver.OnGlobalLayoutListener>
    implements ViewTreeObserver.OnGlobalLayoutListener {
        public OnGlobalLayoutListener(Object object, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            super(object, onGlobalLayoutListener);
            ReferenceMonitor.a().a(this);
        }

        public void onGlobalLayout() {
            ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = (ViewTreeObserver.OnGlobalLayoutListener)this.a();
            if (onGlobalLayoutListener != null) {
                onGlobalLayoutListener.onGlobalLayout();
            }
        }
    }

    public static class OnTouchListener
    extends WeakListenerTemplate<View.OnTouchListener>
    implements View.OnTouchListener {
        public OnTouchListener(Object object, View.OnTouchListener onTouchListener) {
            super(object, onTouchListener);
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            View.OnTouchListener onTouchListener = (View.OnTouchListener)this.a();
            if (onTouchListener != null && onTouchListener.onTouch(view, motionEvent)) {
                return true;
            }
            return false;
        }
    }

    public static class TextWatcher
    extends WeakListenerTemplate<android.text.TextWatcher>
    implements android.text.TextWatcher {
        public TextWatcher(Object object, android.text.TextWatcher textWatcher) {
            super(object, textWatcher);
        }

        public void afterTextChanged(Editable editable) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher)this.a();
            if (textWatcher != null) {
                textWatcher.afterTextChanged(editable);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher)this.a();
            if (textWatcher != null) {
                textWatcher.beforeTextChanged(charSequence, n, n2, n3);
            }
        }

        public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher)this.a();
            if (textWatcher != null) {
                textWatcher.onTextChanged(charSequence, n, n2, n3);
            }
        }
    }

    public static class WeakListenerTemplate<T> {
        WeakHashMap<Object, T> a = new WeakHashMap();

        public WeakListenerTemplate(Object object, T t) {
            this.a.put(object, t);
        }

        protected T a() {
            Iterator<Map.Entry<Object, T>> iterator = this.a.entrySet().iterator();
            if (iterator.hasNext() && (iterator = iterator.next()) != null) {
                return (T)iterator.getValue();
            }
            return null;
        }
    }

}

