package com.mohammedalhzmi.masrofmanager.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: AuthSecurity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\t"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AuthSecurity;", "", "<init>", "()V", "hash", "", "password", "verify", "", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AuthSecurity {
    public static final int $stable = 0;
    public static final AuthSecurity INSTANCE = new AuthSecurity();

    private AuthSecurity() {
    }

    static final CharSequence hash$lambda$0(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    public final String hash(String password) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(password, "password");
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.util.AuthSecurity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AuthSecurity.hash$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    public final boolean verify(String password, String hash) {
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(hash, "hash");
        return Intrinsics.areEqual(hash(password), hash);
    }
}
