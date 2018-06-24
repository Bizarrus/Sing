package com.smule.singandroid.utils;

import com.smule.chat.ChatStatus;
import com.smule.chat.GroupStatusChatMessage.Status;

/* synthetic */ class ChatUtils$6 {
    static final /* synthetic */ int[] f24675a = new int[Status.values().length];
    static final /* synthetic */ int[] f24676b = new int[ChatStatus.values().length];

    static {
        try {
            f24676b[ChatStatus.OK.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f24676b[ChatStatus.DELIVERY_FAILED.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f24676b[ChatStatus.NETWORK_ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f24676b[ChatStatus.NON_MEMBER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f24676b[ChatStatus.CHAT_NOT_FOUND.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f24676b[ChatStatus.BAD_REQUEST.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
        try {
            f24675a[Status.JOINED.ordinal()] = 1;
        } catch (NoSuchFieldError e7) {
        }
        try {
            f24675a[Status.LEFT.ordinal()] = 2;
        } catch (NoSuchFieldError e8) {
        }
        try {
            f24675a[Status.REMOVED.ordinal()] = 3;
        } catch (NoSuchFieldError e9) {
        }
        try {
            f24675a[Status.INVITED.ordinal()] = 4;
        } catch (NoSuchFieldError e10) {
        }
        try {
            f24675a[Status.RENAMED.ordinal()] = 5;
        } catch (NoSuchFieldError e11) {
        }
    }
}
