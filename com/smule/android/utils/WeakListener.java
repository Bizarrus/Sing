package com.smule.android.utils;

import android.annotation.SuppressLint;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public class WeakListener {

    public static class WeakListenerTemplate<T> {
        WeakHashMap<Object, T> f15662a = new WeakHashMap();

        public WeakListenerTemplate(Object obj, T t) {
            this.f15662a.put(obj, t);
        }

        protected T m17483a() {
            Iterator it = this.f15662a.entrySet().iterator();
            if (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry != null) {
                    return entry.getValue();
                }
            }
            return null;
        }
    }

    public static class OnClickListener extends WeakListenerTemplate<android.view.View.OnClickListener> implements android.view.View.OnClickListener {
        public OnClickListener(Object obj, android.view.View.OnClickListener onClickListener) {
            super(obj, onClickListener);
        }

        public void onClick(View view) {
            android.view.View.OnClickListener onClickListener = (android.view.View.OnClickListener) m17483a();
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public static class OnEditorActionListener extends WeakListenerTemplate<android.widget.TextView.OnEditorActionListener> implements android.widget.TextView.OnEditorActionListener {
        public OnEditorActionListener(Object obj, android.widget.TextView.OnEditorActionListener onEditorActionListener) {
            super(obj, onEditorActionListener);
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            android.widget.TextView.OnEditorActionListener onEditorActionListener = (android.widget.TextView.OnEditorActionListener) m17483a();
            return onEditorActionListener != null && onEditorActionListener.onEditorAction(textView, i, keyEvent);
        }
    }

    public static class OnGlobalLayoutListener extends WeakListenerTemplate<android.view.ViewTreeObserver.OnGlobalLayoutListener> implements android.view.ViewTreeObserver.OnGlobalLayoutListener {
        public OnGlobalLayoutListener(Object obj, android.view.ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
            super(obj, onGlobalLayoutListener);
            ReferenceMonitor.a().a(this);
        }

        public void onGlobalLayout() {
            android.view.ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = (android.view.ViewTreeObserver.OnGlobalLayoutListener) m17483a();
            if (onGlobalLayoutListener != null) {
                onGlobalLayoutListener.onGlobalLayout();
            }
        }
    }

    public static class OnTouchListener extends WeakListenerTemplate<android.view.View.OnTouchListener> implements android.view.View.OnTouchListener {
        public OnTouchListener(Object obj, android.view.View.OnTouchListener onTouchListener) {
            super(obj, onTouchListener);
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            android.view.View.OnTouchListener onTouchListener = (android.view.View.OnTouchListener) m17483a();
            return onTouchListener != null && onTouchListener.onTouch(view, motionEvent);
        }
    }

    public static class TextWatcher extends WeakListenerTemplate<android.text.TextWatcher> implements android.text.TextWatcher {
        public TextWatcher(Object obj, android.text.TextWatcher textWatcher) {
            super(obj, textWatcher);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher) m17483a();
            if (textWatcher != null) {
                textWatcher.beforeTextChanged(charSequence, i, i2, i3);
            }
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher) m17483a();
            if (textWatcher != null) {
                textWatcher.onTextChanged(charSequence, i, i2, i3);
            }
        }

        public void afterTextChanged(Editable editable) {
            android.text.TextWatcher textWatcher = (android.text.TextWatcher) m17483a();
            if (textWatcher != null) {
                textWatcher.afterTextChanged(editable);
            }
        }
    }

    @SuppressLint({"addTextChangedListenerNotAllowed"})
    public static void m19083a(EditText editText, android.text.TextWatcher textWatcher) {
        editText.addTextChangedListener(new TextWatcher(editText.getParent(), textWatcher));
    }
}
