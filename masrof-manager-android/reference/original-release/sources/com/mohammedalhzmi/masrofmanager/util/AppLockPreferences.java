package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.ExponentialBackoff;
import io.ktor.http.LinkHeader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: AppLockPreferences.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0019J\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0011J\u0016\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0005J\u000e\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010 \u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0005J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010%\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006&"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AppLockPreferences;", "", "<init>", "()V", "FILE", "", "ENABLED", CredentialProviderBaseController.TYPE_TAG, "SECRET", "BIOMETRIC", "TIMEOUT", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "enabled", "", "biometricEnabled", "setBiometricEnabled", "", Values.VECTOR_MAP_VECTORS_KEY, LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/util/LockType;", "timeoutMinutes", "", "setTimeoutMinutes", "setEnabled", "setType", "setSecret", "secret", "hasSecret", "verify", "shouldRelock", "now", "", "markUnlocked", "digest", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AppLockPreferences {
    public static final int $stable = 0;
    private static final String BIOMETRIC = "app_lock_biometric";
    private static final String ENABLED = "app_lock_enabled";
    private static final String FILE = "masrof_preferences";
    public static final AppLockPreferences INSTANCE = new AppLockPreferences();
    private static final String SECRET = "app_lock_secret_digest";
    private static final String TIMEOUT = "app_lock_timeout_minutes";
    private static final String TYPE = "app_lock_type";

    private AppLockPreferences() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public final boolean enabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean(ENABLED, false);
    }

    public final boolean biometricEnabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean(BIOMETRIC, true);
    }

    public final void setBiometricEnabled(Context context, boolean value) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean(BIOMETRIC, value).apply();
    }

    public final LockType type(Context context) {
        Object objM7781constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            AppLockPreferences appLockPreferences = this;
            String string = prefs(context).getString(TYPE, "PIN");
            Intrinsics.checkNotNull(string);
            objM7781constructorimpl = Result.m7781constructorimpl(LockType.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        LockType lockType = LockType.PIN;
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = lockType;
        }
        return (LockType) objM7781constructorimpl;
    }

    public final int timeoutMinutes(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getInt(TIMEOUT, 5);
    }

    public final void setTimeoutMinutes(Context context, int value) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putInt(TIMEOUT, RangesKt.coerceIn(value, 0, 60)).apply();
    }

    public final void setEnabled(Context context, boolean value) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putBoolean(ENABLED, value).apply();
    }

    public final void setType(Context context, LockType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        prefs(context).edit().putString(TYPE, type.name()).apply();
    }

    public final void setSecret(Context context, String secret) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(secret, "secret");
        prefs(context).edit().putString(SECRET, digest(secret)).apply();
    }

    public final boolean hasSecret(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).contains(SECRET);
    }

    public final boolean verify(Context context, String secret) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(secret, "secret");
        return Intrinsics.areEqual(digest(secret), prefs(context).getString(SECRET, null));
    }

    public static /* synthetic */ boolean shouldRelock$default(AppLockPreferences appLockPreferences, Context context, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = System.currentTimeMillis();
        }
        return appLockPreferences.shouldRelock(context, j);
    }

    public final boolean shouldRelock(Context context, long now) {
        Intrinsics.checkNotNullParameter(context, "context");
        long j = prefs(context).getLong("app_last_unlocked", 0L);
        int iTimeoutMinutes = timeoutMinutes(context);
        return iTimeoutMinutes == 0 || j == 0 || now - j >= ((long) iTimeoutMinutes) * ExponentialBackoff.DEFAULT_BACKOFF_MAX_DELAY_MS;
    }

    public final void markUnlocked(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putLong("app_last_unlocked", System.currentTimeMillis()).apply();
    }

    private final String digest(String value) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = value.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        Intrinsics.checkNotNullExpressionValue(bArrDigest, "digest(...)");
        return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.util.AppLockPreferences$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppLockPreferences.digest$lambda$1(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence digest$lambda$1(byte b) {
        String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }
}
