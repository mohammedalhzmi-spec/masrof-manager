package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/SessionManager;", "", "<init>", "()V", "FILE", "", "LAST_ACTIVE", "TIMEOUT_MS", "", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "markActive", "", "expired", "", "clear", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class SessionManager {
    public static final int $stable = 0;
    private static final String FILE = "masrof_session";
    public static final SessionManager INSTANCE = new SessionManager();
    private static final String LAST_ACTIVE = "last_active";
    private static final long TIMEOUT_MS = 1800000;

    private SessionManager() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public final void markActive(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().putLong(LAST_ACTIVE, System.currentTimeMillis()).apply();
    }

    public final boolean expired(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        long j = prefs(context).getLong(LAST_ACTIVE, 0L);
        return j == 0 || System.currentTimeMillis() - j >= TIMEOUT_MS;
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().remove(LAST_ACTIVE).apply();
    }
}
