package com.mohammedalhzmi.masrofmanager.util;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: AppLockPreferences.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/LockType;", "", "<init>", "(Ljava/lang/String;I)V", "PIN", "PASSWORD", "PATTERN", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public enum LockType {
    PIN,
    PASSWORD,
    PATTERN;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<LockType> getEntries() {
        return $ENTRIES;
    }
}
