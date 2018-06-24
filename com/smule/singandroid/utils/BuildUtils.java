package com.smule.singandroid.utils;

public class BuildUtils {

    public enum Flavor {
        Int("Int"),
        Stg("Stg"),
        ProdBeta("ProdBeta"),
        Prod("Prod");
        
        String f7176e;

        private Flavor(String str) {
            this.f7176e = str;
        }

        public boolean m8891a() {
            return "Prod".equals(this.f7176e);
        }
    }
}
