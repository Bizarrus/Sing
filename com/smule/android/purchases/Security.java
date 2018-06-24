/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 */
package com.smule.android.purchases;

import android.text.TextUtils;
import com.smule.android.logging.Log;
import com.smule.android.purchases.Base64;
import com.smule.android.purchases.Base64DecoderException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Security {
    public static PublicKey a(String object) {
        try {
            object = Base64.a((String)object);
            object = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec((byte[])object));
            return object;
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException(noSuchAlgorithmException);
        }
        catch (InvalidKeySpecException invalidKeySpecException) {
            Log.e("IABUtil/Security", "Invalid key specification.");
            throw new IllegalArgumentException(invalidKeySpecException);
        }
        catch (Base64DecoderException base64DecoderException) {
            Log.e("IABUtil/Security", "Base64 decoding failed.");
            throw new IllegalArgumentException(base64DecoderException);
        }
    }

    public static boolean a(String string2, String string3, String string4) {
        if (TextUtils.isEmpty((CharSequence)string3) || TextUtils.isEmpty((CharSequence)string2) || TextUtils.isEmpty((CharSequence)string4)) {
            Log.e("IABUtil/Security", "Purchase verification failed: missing data.");
            return false;
        }
        return Security.a(Security.a(string2), string3, string4);
    }

    public static boolean a(PublicKey publicKey, String string2, String string3) {
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(publicKey);
            signature.update(string2.getBytes());
            if (!signature.verify(Base64.a(string3))) {
                Log.e("IABUtil/Security", "Signature verification failed.");
                return false;
            }
            return true;
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            Log.e("IABUtil/Security", "NoSuchAlgorithmException.");
            return false;
        }
        catch (InvalidKeyException invalidKeyException) {
            Log.e("IABUtil/Security", "Invalid key specification.");
            return false;
        }
        catch (SignatureException signatureException) {
            Log.e("IABUtil/Security", "Signature exception.");
            return false;
        }
        catch (Base64DecoderException base64DecoderException) {
            Log.e("IABUtil/Security", "Base64 decoding failed.");
            return false;
        }
    }
}

