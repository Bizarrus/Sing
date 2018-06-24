/*
 * Decompiled with CFR 0_123.
 */
package com.smule.chat.smerialization;

import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.VerifiedUrl;
import com.smule.chat.smerialization.SmerializableInputStream;
import com.smule.chat.smerialization.SmerializableOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SmerializableUtils {
    public static Date a(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.d()) {
            return new Date(smerializableInputStream.readLong());
        }
        return null;
    }

    public static void a(SmerializableOutputStream smerializableOutputStream, AccountIcon object) throws IOException {
        smerializableOutputStream.writeInt(1);
        smerializableOutputStream.writeLong(object.accountId);
        smerializableOutputStream.a(object.picUrl);
        smerializableOutputStream.a(object.handle);
        smerializableOutputStream.a(object.blurb);
        smerializableOutputStream.a(object.jid);
        smerializableOutputStream.writeFloat(object.latitude);
        smerializableOutputStream.writeFloat(object.longitude);
        smerializableOutputStream.writeInt(object.appsWithSubscription.size());
        Iterator<String> iterator = object.appsWithSubscription.iterator();
        while (iterator.hasNext()) {
            smerializableOutputStream.a(iterator.next());
        }
        smerializableOutputStream.writeInt(object.platformsWithSmulePass.size());
        iterator = object.platformsWithSmulePass.iterator();
        while (iterator.hasNext()) {
            smerializableOutputStream.a(iterator.next());
        }
        smerializableOutputStream.writeInt(object.verifiedUrls.size());
        object = object.verifiedUrls.iterator();
        while (object.hasNext()) {
            SmerializableUtils.a(smerializableOutputStream, (VerifiedUrl)object.next());
        }
    }

    public static void a(SmerializableOutputStream smerializableOutputStream, VerifiedUrl verifiedUrl) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.a(verifiedUrl.type);
        smerializableOutputStream.a(verifiedUrl.desc);
        smerializableOutputStream.a(verifiedUrl.url);
    }

    public static void a(SmerializableOutputStream smerializableOutputStream, Date date) throws IOException {
        smerializableOutputStream.a(date);
        if (date != null) {
            smerializableOutputStream.writeLong(date.getTime());
        }
    }

    public static VerifiedUrl b(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        VerifiedUrl verifiedUrl = new VerifiedUrl();
        verifiedUrl.type = smerializableInputStream.b();
        verifiedUrl.desc = smerializableInputStream.b();
        verifiedUrl.url = smerializableInputStream.b();
        return verifiedUrl;
    }

    public static AccountIcon c(SmerializableInputStream smerializableInputStream) throws IOException {
        int n;
        Object object;
        int n2 = 0;
        if (smerializableInputStream.readInt() != 1) {
            throw new InvalidClassException("bad version");
        }
        AccountIcon accountIcon = new AccountIcon();
        accountIcon.accountId = smerializableInputStream.readLong();
        accountIcon.picUrl = smerializableInputStream.b();
        accountIcon.handle = smerializableInputStream.b();
        accountIcon.blurb = smerializableInputStream.b();
        accountIcon.jid = smerializableInputStream.b();
        accountIcon.latitude = smerializableInputStream.readFloat();
        accountIcon.longitude = smerializableInputStream.readFloat();
        int n3 = smerializableInputStream.readInt();
        for (n = 0; n < n3; ++n) {
            object = smerializableInputStream.b();
            accountIcon.appsWithSubscription.add((String)object);
        }
        n3 = smerializableInputStream.readInt();
        for (n = 0; n < n3; ++n) {
            object = smerializableInputStream.b();
            accountIcon.platformsWithSmulePass.add((String)object);
        }
        n3 = smerializableInputStream.readInt();
        for (n = n2; n < n3; ++n) {
            object = SmerializableUtils.b(smerializableInputStream);
            accountIcon.verifiedUrls.add((VerifiedUrl)object);
        }
        return accountIcon;
    }
}

