/*
 * Decompiled with CFR 0_123.
 */
package com.smule.android.network.managers.l10n;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface LocalizationObject {
    public String a() default "";
}

