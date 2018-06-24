package com.smule.singandroid.audio.exception;

public class UninitializedException extends Exception {
    UninitializedException(String str) {
        super(str);
    }

    UninitializedException(String str, Throwable th) {
        super(str, th);
    }

    UninitializedException(Throwable th) {
        super(th);
    }
}
