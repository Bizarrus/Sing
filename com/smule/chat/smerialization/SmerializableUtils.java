package com.smule.chat.smerialization;

import com.smule.android.network.models.AccountIcon;
import com.smule.android.network.models.VerifiedUrl;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;
import java.util.Iterator;

public class SmerializableUtils {
    public static void m19761a(SmerializableOutputStream smerializableOutputStream, Date date) throws IOException {
        smerializableOutputStream.m19756a((Object) date);
        if (date != null) {
            smerializableOutputStream.writeLong(date.getTime());
        }
    }

    public static Date m19758a(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.m19753d()) {
            return new Date(smerializableInputStream.readLong());
        }
        return null;
    }

    public static void m19760a(SmerializableOutputStream smerializableOutputStream, VerifiedUrl verifiedUrl) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.m19757a(verifiedUrl.type);
        smerializableOutputStream.m19757a(verifiedUrl.desc);
        smerializableOutputStream.m19757a(verifiedUrl.url);
    }

    public static VerifiedUrl m19762b(SmerializableInputStream smerializableInputStream) throws IOException {
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        VerifiedUrl verifiedUrl = new VerifiedUrl();
        verifiedUrl.type = smerializableInputStream.m19751b();
        verifiedUrl.desc = smerializableInputStream.m19751b();
        verifiedUrl.url = smerializableInputStream.m19751b();
        return verifiedUrl;
    }

    public static void m19759a(SmerializableOutputStream smerializableOutputStream, AccountIcon accountIcon) throws IOException {
        smerializableOutputStream.writeInt(0);
        smerializableOutputStream.writeLong(accountIcon.accountId);
        smerializableOutputStream.m19757a(accountIcon.picUrl);
        smerializableOutputStream.m19757a(accountIcon.handle);
        smerializableOutputStream.m19757a(accountIcon.jid);
        smerializableOutputStream.writeFloat(accountIcon.latitude);
        smerializableOutputStream.writeFloat(accountIcon.longitude);
        smerializableOutputStream.writeInt(accountIcon.appsWithSubscription.size());
        Iterator it = accountIcon.appsWithSubscription.iterator();
        while (it.hasNext()) {
            smerializableOutputStream.m19757a((String) it.next());
        }
        smerializableOutputStream.writeInt(accountIcon.platformsWithSmulePass.size());
        it = accountIcon.platformsWithSmulePass.iterator();
        while (it.hasNext()) {
            smerializableOutputStream.m19757a((String) it.next());
        }
        smerializableOutputStream.writeInt(accountIcon.verifiedUrls.size());
        for (VerifiedUrl a : accountIcon.verifiedUrls) {
            m19760a(smerializableOutputStream, a);
        }
    }

    public static AccountIcon m19763c(SmerializableInputStream smerializableInputStream) throws IOException {
        int i = 0;
        if (smerializableInputStream.readInt() != 0) {
            throw new InvalidClassException("bad version");
        }
        int i2;
        AccountIcon accountIcon = new AccountIcon();
        accountIcon.accountId = smerializableInputStream.readLong();
        accountIcon.picUrl = smerializableInputStream.m19751b();
        accountIcon.handle = smerializableInputStream.m19751b();
        accountIcon.jid = smerializableInputStream.m19751b();
        accountIcon.latitude = smerializableInputStream.readFloat();
        accountIcon.longitude = smerializableInputStream.readFloat();
        int readInt = smerializableInputStream.readInt();
        for (i2 = 0; i2 < readInt; i2++) {
            accountIcon.appsWithSubscription.add(smerializableInputStream.m19751b());
        }
        readInt = smerializableInputStream.readInt();
        for (i2 = 0; i2 < readInt; i2++) {
            accountIcon.platformsWithSmulePass.add(smerializableInputStream.m19751b());
        }
        i2 = smerializableInputStream.readInt();
        while (i < i2) {
            accountIcon.verifiedUrls.add(m19762b(smerializableInputStream));
            i++;
        }
        return accountIcon;
    }
}
