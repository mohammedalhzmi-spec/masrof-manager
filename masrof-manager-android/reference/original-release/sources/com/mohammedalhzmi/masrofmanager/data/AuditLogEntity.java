package com.mohammedalhzmi.masrofmanager.data;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JL\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\r¨\u0006$"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "", "id", "", "userId", HintConstants.AUTOFILL_HINT_USERNAME, "", "action", "details", "timestamp", "<init>", "(JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getId", "()J", "getUserId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUsername", "()Ljava/lang/String;", "getAction", "getDetails", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class AuditLogEntity {
    public static final int $stable = 0;
    private final String action;
    private final String details;
    private final long id;
    private final long timestamp;
    private final Long userId;
    private final String username;

    public static /* synthetic */ AuditLogEntity copy$default(AuditLogEntity auditLogEntity, long j, Long l, String str, String str2, String str3, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = auditLogEntity.id;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            l = auditLogEntity.userId;
        }
        Long l2 = l;
        if ((i & 4) != 0) {
            str = auditLogEntity.username;
        }
        String str4 = str;
        if ((i & 8) != 0) {
            str2 = auditLogEntity.action;
        }
        String str5 = str2;
        if ((i & 16) != 0) {
            str3 = auditLogEntity.details;
        }
        return auditLogEntity.copy(j3, l2, str4, str5, str3, (i & 32) != 0 ? auditLogEntity.timestamp : j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDetails() {
        return this.details;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    public final AuditLogEntity copy(long id, Long userId, String username, String action, String details, long timestamp) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(details, "details");
        return new AuditLogEntity(id, userId, username, action, details, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuditLogEntity)) {
            return false;
        }
        AuditLogEntity auditLogEntity = (AuditLogEntity) other;
        return this.id == auditLogEntity.id && Intrinsics.areEqual(this.userId, auditLogEntity.userId) && Intrinsics.areEqual(this.username, auditLogEntity.username) && Intrinsics.areEqual(this.action, auditLogEntity.action) && Intrinsics.areEqual(this.details, auditLogEntity.details) && this.timestamp == auditLogEntity.timestamp;
    }

    public int hashCode() {
        int iHashCode = Long.hashCode(this.id) * 31;
        Long l = this.userId;
        return ((((((((iHashCode + (l == null ? 0 : l.hashCode())) * 31) + this.username.hashCode()) * 31) + this.action.hashCode()) * 31) + this.details.hashCode()) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "AuditLogEntity(id=" + this.id + ", userId=" + this.userId + ", username=" + this.username + ", action=" + this.action + ", details=" + this.details + ", timestamp=" + this.timestamp + ")";
    }

    public AuditLogEntity(long j, Long l, String username, String action, String details, long j2) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(details, "details");
        this.id = j;
        this.userId = l;
        this.username = username;
        this.action = action;
        this.details = details;
        this.timestamp = j2;
    }

    public final long getId() {
        return this.id;
    }

    public final String getAction() {
        return this.action;
    }

    public final String getDetails() {
        return this.details;
    }

    public final Long getUserId() {
        return this.userId;
    }

    public final String getUsername() {
        return this.username;
    }

    public /* synthetic */ AuditLogEntity(long j, Long l, String str, String str2, String str3, long j2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, l, str, str2, str3, (i & 32) != 0 ? System.currentTimeMillis() : j2);
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
