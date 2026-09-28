package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthSecurity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0005J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/RememberedLogin;", "", "<init>", "()V", "FILE", "", "USERNAME", "ENABLED", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "enabled", "", "save", "", HintConstants.AUTOFILL_HINT_USERNAME, "clear", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class RememberedLogin {
    public static final int $stable = 0;
    private static final String ENABLED = "remember_login";
    private static final String FILE = "masrof_preferences";
    public static final RememberedLogin INSTANCE = new RememberedLogin();
    private static final String USERNAME = "remembered_username";

    private RememberedLogin() {
    }

    private final SharedPreferences prefs(Context context) {
        return context.getSharedPreferences(FILE, 0);
    }

    public final boolean enabled(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getBoolean(ENABLED, true);
    }

    public final void save(Context context, String username) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(username, "username");
        prefs(context).edit().putBoolean(ENABLED, true).putString(USERNAME, username).apply();
    }

    public final void clear(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        prefs(context).edit().remove(USERNAME).putBoolean(ENABLED, false).apply();
    }

    public final String username(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return prefs(context).getString(USERNAME, null);
    }
}
