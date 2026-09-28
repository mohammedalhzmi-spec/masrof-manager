package com.mohammedalhzmi.masrofmanager.util;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AppRoles.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AppRole;", "", LinkHeader.Parameters.Title, "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getTitle", "()Ljava/lang/String;", "ADMIN", "FINANCE_MANAGER", "ACCOUNTANT", "USER", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public enum AppRole {
    ADMIN("مدير النظام"),
    FINANCE_MANAGER("المدير المالي"),
    ACCOUNTANT("المحاسب"),
    USER("مستخدم");

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final String title;

    public static EnumEntries<AppRole> getEntries() {
        return $ENTRIES;
    }

    AppRole(String str) {
        this.title = str;
    }

    public final String getTitle() {
        return this.title;
    }
}
