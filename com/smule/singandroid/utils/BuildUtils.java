/*
 * Decompiled with CFR 0_123.
 */
package com.smule.singandroid.utils;

public class BuildUtils {

    public static enum Flavor {
        a("Int"),
        b("Stg"),
        c("ProdBeta"),
        d("Prod");
        
        String e;

        private Flavor(String string3) {
            this.e = string3;
        }

        public boolean a() {
            return "Prod".equals(this.e);
        }
    }

}

