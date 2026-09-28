package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuthAttemptGuard.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\n\u001a\n \f*\u0004\u0018\u00010\u000b0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u0010\u000f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005J\u0016\u0010\u0015\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AuthAttemptGuard;", "", "<init>", "()V", "FILE", "", "WINDOW_MS", "", "MAX_ATTEMPTS", "", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "remainingLockout", "identifier", "canAttempt", "", "recordFailure", "", "clear", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AuthAttemptGuard {
    public static final int $stable = 0;
    private static final String FILE = "auth_guard";
    public static final AuthAttemptGuard INSTANCE = new AuthAttemptGuard();
    private static final int MAX_ATTEMPTS = 5;
    private static final long WINDOW_MS = 900000;

    private AuthAttemptGuard() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public final long remainingLockout(Context context, String identifier) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        SharedPreferences sharedPreferencesPrefs = prefs(context);
        String lowerCase = StringsKt.trim((CharSequence) identifier).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return RangesKt.coerceAtLeast(sharedPreferencesPrefs.getLong("lock:" + lowerCase, 0L) - System.currentTimeMillis(), 0L);
    }

    public final boolean canAttempt(Context context, String identifier) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        return remainingLockout(context, identifier) == 0;
    }

    public final void recordFailure(Context context, String identifier) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        String lowerCase = StringsKt.trim((CharSequence) identifier).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        SharedPreferences sharedPreferencesPrefs = prefs(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferencesPrefs.getLong("window:" + lowerCase, 0L);
        long j2 = jCurrentTimeMillis - j;
        int i = j2 <= WINDOW_MS ? 1 + sharedPreferencesPrefs.getInt("count:" + lowerCase, 0) : 1;
        SharedPreferences.Editor editorEdit = sharedPreferencesPrefs.edit();
        String str = "window:" + lowerCase;
        if (j2 > WINDOW_MS) {
            j = jCurrentTimeMillis;
        }
        SharedPreferences.Editor editorPutInt = editorEdit.putLong(str, j).putInt("count:" + lowerCase, i);
        if (i >= 5) {
            editorPutInt.putLong("lock:" + lowerCase, jCurrentTimeMillis + WINDOW_MS);
        }
        editorPutInt.apply();
    }

    public final void clear(Context context, String identifier) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        String lowerCase = StringsKt.trim((CharSequence) identifier).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        prefs(context).edit().remove("window:" + lowerCase).remove("count:" + lowerCase).remove("lock:" + lowerCase).apply();
    }
}
