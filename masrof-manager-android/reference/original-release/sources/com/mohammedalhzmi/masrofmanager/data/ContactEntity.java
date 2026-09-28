package com.mohammedalhzmi.masrofmanager.data;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/ContactEntity;", "", "id", "", "name", "", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "<init>", "(JLjava/lang/String;Lcom/mohammedalhzmi/masrofmanager/data/ContactType;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getType", "()Lcom/mohammedalhzmi/masrofmanager/data/ContactType;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class ContactEntity {
    public static final int $stable = 0;
    private final long id;
    private final String name;
    private final ContactType type;

    public static /* synthetic */ ContactEntity copy$default(ContactEntity contactEntity, long j, String str, ContactType contactType, int i, Object obj) {
        if ((i & 1) != 0) {
            j = contactEntity.id;
        }
        if ((i & 2) != 0) {
            str = contactEntity.name;
        }
        if ((i & 4) != 0) {
            contactType = contactEntity.type;
        }
        return contactEntity.copy(j, str, contactType);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ContactType getType() {
        return this.type;
    }

    public final ContactEntity copy(long id, String name, ContactType type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        return new ContactEntity(id, name, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContactEntity)) {
            return false;
        }
        ContactEntity contactEntity = (ContactEntity) other;
        return this.id == contactEntity.id && Intrinsics.areEqual(this.name, contactEntity.name) && this.type == contactEntity.type;
    }

    public int hashCode() {
        return (((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.type.hashCode();
    }

    public String toString() {
        return "ContactEntity(id=" + this.id + ", name=" + this.name + ", type=" + this.type + ")";
    }

    public ContactEntity(long j, String name, ContactType type) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(type, "type");
        this.id = j;
        this.name = name;
        this.type = type;
    }

    public /* synthetic */ ContactEntity(long j, String str, ContactType contactType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, contactType);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final ContactType getType() {
        return this.type;
    }
}
