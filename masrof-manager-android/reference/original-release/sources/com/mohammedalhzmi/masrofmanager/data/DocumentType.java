package com.mohammedalhzmi.masrofmanager.data;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0011\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "", "<init>", "(Ljava/lang/String;I)V", "REQUEST", "ORDER", "RECEIPT", "RECEIPT_PAPER", "FINANCIAL_MEMO", "PURCHASE_ORDER", "SUPPLY_PERMIT", "RECEIPT_MINUTES", "FINANCIAL_CLAIM", "CUSTODY_SETTLEMENT", "ADVANCE_PERMIT", "EXPENSE_STATEMENT", "OFFICIAL_FINANCIAL_LETTER", "BOOK", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public enum DocumentType {
    REQUEST,
    ORDER,
    RECEIPT,
    RECEIPT_PAPER,
    FINANCIAL_MEMO,
    PURCHASE_ORDER,
    SUPPLY_PERMIT,
    RECEIPT_MINUTES,
    FINANCIAL_CLAIM,
    CUSTODY_SETTLEMENT,
    ADVANCE_PERMIT,
    EXPENSE_STATEMENT,
    OFFICIAL_FINANCIAL_LETTER,
    BOOK;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<DocumentType> getEntries() {
        return $ENTRIES;
    }
}
