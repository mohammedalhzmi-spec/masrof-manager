package com.mohammedalhzmi.masrofmanager.util;

import kotlin.Metadata;

/* JADX INFO: compiled from: AuthSecurity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/UserSession;", "", "<init>", "()V", "current", "Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;", "getCurrent", "()Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;", "setCurrent", "(Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;)V", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class UserSession {
    private static AuthenticatedUser current;
    public static final UserSession INSTANCE = new UserSession();
    public static final int $stable = 8;

    private UserSession() {
    }

    public final AuthenticatedUser getCurrent() {
        return current;
    }

    public final void setCurrent(AuthenticatedUser authenticatedUser) {
        current = authenticatedUser;
    }
}
