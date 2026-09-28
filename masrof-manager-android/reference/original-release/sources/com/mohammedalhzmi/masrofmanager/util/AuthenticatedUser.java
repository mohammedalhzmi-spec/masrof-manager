package com.mohammedalhzmi.masrofmanager.util;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AuthSecurity.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AuthenticatedUser;", "", "id", "", HintConstants.AUTOFILL_HINT_USERNAME, "", "fullName", "role", "Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "<init>", "(JLjava/lang/String;Ljava/lang/String;Lcom/mohammedalhzmi/masrofmanager/util/AppRole;)V", "getId", "()J", "getUsername", "()Ljava/lang/String;", "getFullName", "getRole", "()Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class AuthenticatedUser {
    public static final int $stable = 0;
    private final String fullName;
    private final long id;
    private final AppRole role;
    private final String username;

    public static /* synthetic */ AuthenticatedUser copy$default(AuthenticatedUser authenticatedUser, long j, String str, String str2, AppRole appRole, int i, Object obj) {
        if ((i & 1) != 0) {
            j = authenticatedUser.id;
        }
        long j2 = j;
        if ((i & 2) != 0) {
            str = authenticatedUser.username;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = authenticatedUser.fullName;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            appRole = authenticatedUser.role;
        }
        return authenticatedUser.copy(j2, str3, str4, appRole);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getFullName() {
        return this.fullName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final AppRole getRole() {
        return this.role;
    }

    public final AuthenticatedUser copy(long id, String username, String fullName, AppRole role) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        return new AuthenticatedUser(id, username, fullName, role);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthenticatedUser)) {
            return false;
        }
        AuthenticatedUser authenticatedUser = (AuthenticatedUser) other;
        return this.id == authenticatedUser.id && Intrinsics.areEqual(this.username, authenticatedUser.username) && Intrinsics.areEqual(this.fullName, authenticatedUser.fullName) && this.role == authenticatedUser.role;
    }

    public int hashCode() {
        return (((((Long.hashCode(this.id) * 31) + this.username.hashCode()) * 31) + this.fullName.hashCode()) * 31) + this.role.hashCode();
    }

    public String toString() {
        return "AuthenticatedUser(id=" + this.id + ", username=" + this.username + ", fullName=" + this.fullName + ", role=" + this.role + ")";
    }

    public AuthenticatedUser(long j, String username, String fullName, AppRole role) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        this.id = j;
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final long getId() {
        return this.id;
    }

    public final AppRole getRole() {
        return this.role;
    }

    public final String getUsername() {
        return this.username;
    }
}
