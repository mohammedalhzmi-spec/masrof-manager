package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006%"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/UserEntity;", "", "id", "", HintConstants.AUTOFILL_HINT_USERNAME, "", "passwordHash", "fullName", "role", "active", "", "createdAt", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJ)V", "getId", "()J", "getUsername", "()Ljava/lang/String;", "getPasswordHash", "getFullName", "getRole", "getActive", "()Z", "getCreatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class UserEntity {
    public static final int $stable = 0;
    private final boolean active;
    private final long createdAt;
    private final String fullName;
    private final long id;
    private final String passwordHash;
    private final String role;
    private final String username;

    public static /* synthetic */ UserEntity copy$default(UserEntity userEntity, long j, String str, String str2, String str3, String str4, boolean z, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = userEntity.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            str = userEntity.username;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = userEntity.passwordHash;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = userEntity.fullName;
        }
        return userEntity.copy(j3, str5, str6, str3, (i & 16) != 0 ? userEntity.role : str4, (i & 32) != 0 ? userEntity.active : z, (i & 64) != 0 ? userEntity.createdAt : j2);
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
    public final String getPasswordHash() {
        return this.passwordHash;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getFullName() {
        return this.fullName;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getRole() {
        return this.role;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getActive() {
        return this.active;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final UserEntity copy(long id, String username, String passwordHash, String fullName, String role, boolean active, long createdAt) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(passwordHash, "passwordHash");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        return new UserEntity(id, username, passwordHash, fullName, role, active, createdAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) other;
        return this.id == userEntity.id && Intrinsics.areEqual(this.username, userEntity.username) && Intrinsics.areEqual(this.passwordHash, userEntity.passwordHash) && Intrinsics.areEqual(this.fullName, userEntity.fullName) && Intrinsics.areEqual(this.role, userEntity.role) && this.active == userEntity.active && this.createdAt == userEntity.createdAt;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.username.hashCode()) * 31) + this.passwordHash.hashCode()) * 31) + this.fullName.hashCode()) * 31) + this.role.hashCode()) * 31) + Boolean.hashCode(this.active)) * 31) + Long.hashCode(this.createdAt);
    }

    public String toString() {
        return "UserEntity(id=" + this.id + ", username=" + this.username + ", passwordHash=" + this.passwordHash + ", fullName=" + this.fullName + ", role=" + this.role + ", active=" + this.active + ", createdAt=" + this.createdAt + ")";
    }

    public UserEntity(long j, String username, String passwordHash, String fullName, String role, boolean z, long j2) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(passwordHash, "passwordHash");
        Intrinsics.checkNotNullParameter(fullName, "fullName");
        Intrinsics.checkNotNullParameter(role, "role");
        this.id = j;
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.role = role;
        this.active = z;
        this.createdAt = j2;
    }

    public final long getId() {
        return this.id;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getPasswordHash() {
        return this.passwordHash;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final String getRole() {
        return this.role;
    }

    public final boolean getActive() {
        return this.active;
    }

    public /* synthetic */ UserEntity(long j, String str, String str2, String str3, String str4, boolean z, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, str2, str3, str4, (i & 32) != 0 ? true : z, (i & 64) != 0 ? System.currentTimeMillis() : j2);
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }
}
