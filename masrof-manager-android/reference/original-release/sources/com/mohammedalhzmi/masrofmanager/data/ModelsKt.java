package com.mohammedalhzmi.masrofmanager.data;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, m914d2 = {"displayName", "", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "app"}, m915k = 2, m916mv = {2, 2, 0}, m918xi = 48)
public final class ModelsKt {

    /* JADX INFO: compiled from: Models.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.ORDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DocumentType.RECEIPT_PAPER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[DocumentType.FINANCIAL_MEMO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[DocumentType.PURCHASE_ORDER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[DocumentType.SUPPLY_PERMIT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[DocumentType.RECEIPT_MINUTES.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[DocumentType.FINANCIAL_CLAIM.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[DocumentType.CUSTODY_SETTLEMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[DocumentType.ADVANCE_PERMIT.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[DocumentType.EXPENSE_STATEMENT.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[DocumentType.OFFICIAL_FINANCIAL_LETTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[DocumentType.BOOK.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String displayName(DocumentType documentType) {
        Intrinsics.checkNotNullParameter(documentType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[documentType.ordinal()]) {
            case 1:
                return "طلب صرف";
            case 2:
                return "أمر صرف";
            case 3:
                return "ورقة استلام";
            case 4:
                return "سند قبض";
            case 5:
                return "مذكرة مالية";
            case 6:
                return "طلب شراء";
            case 7:
                return "إذن توريد أو استلام";
            case 8:
                return "محضر استلام";
            case 9:
                return "مطالبة مالية";
            case 10:
                return "تسوية عهدة";
            case 11:
                return "إذن سلفة";
            case 12:
                return "كشف مصروفات";
            case 13:
                return "خطاب رسمي مالي";
            case 14:
                return "دفتر مستندات";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
