package com.smule.singandroid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Callable;

public class CallableQueueHelper {
    public static final String f18506a = CallableQueueHelper.class.getName();
    private HashMap<String, Callable<CallableReturnCode>> f18507b = new HashMap();
    private HashSet<String> f18508c = new HashSet();
    private boolean f18509d = false;

    public enum CallableReturnCode {
        CALLABLE_SUCCESS,
        CALLABLE_FAILURE,
        CALLABLE_THREW_EXCEPTION_ON_CALL,
        CALLABLE_ERROR_UNKNOWN
    }
}
