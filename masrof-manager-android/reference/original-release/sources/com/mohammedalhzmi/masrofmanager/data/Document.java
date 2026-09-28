package com.mohammedalhzmi.masrofmanager.data;

import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Models.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\bZ\b\u0087\b\u0018\u00002\u00020\u0001B¥\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0007\u0012\b\b\u0002\u0010 \u001a\u00020\u0007\u0012\b\b\u0002\u0010!\u001a\u00020\u0007\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010$\u001a\u00020\u0007\u0012\b\b\u0002\u0010%\u001a\u00020\u0007¢\u0006\u0004\b&\u0010'J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\u0007HÆ\u0003J\t\u0010Q\u001a\u00020\u0007HÆ\u0003J\t\u0010R\u001a\u00020\u0007HÆ\u0003J\u0010\u0010S\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u00101J\u000b\u0010T\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010W\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010X\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010Y\u001a\u00020\u0012HÆ\u0003J\t\u0010Z\u001a\u00020\u0014HÆ\u0003J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\t\u0010\\\u001a\u00020\u0017HÆ\u0003J\u0010\u0010]\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0007HÆ\u0003J\t\u0010`\u001a\u00020\u0007HÆ\u0003J\t\u0010a\u001a\u00020\u0007HÆ\u0003J\t\u0010b\u001a\u00020\u0007HÆ\u0003J\t\u0010c\u001a\u00020\u0007HÆ\u0003J\t\u0010d\u001a\u00020\u0007HÆ\u0003J\t\u0010e\u001a\u00020\u0007HÆ\u0003J\t\u0010f\u001a\u00020\u0007HÆ\u0003J\u0010\u0010g\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\u0010\u0010h\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010?J\t\u0010i\u001a\u00020\u0007HÆ\u0003J\t\u0010j\u001a\u00020\u0007HÆ\u0003JÂ\u0002\u0010k\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001f\u001a\u00020\u00072\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010!\u001a\u00020\u00072\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010$\u001a\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010lJ\u0013\u0010m\u001a\u00020\u00172\b\u0010n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010o\u001a\u00020\u0014HÖ\u0001J\t\u0010p\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b/\u0010-R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u00102\u001a\u0004\b0\u00101R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b3\u0010-R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b4\u0010-R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b5\u0010-R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010-R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u0010-R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b<\u0010)R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010=R\u0015\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\b>\u0010?R\u0011\u0010\u0019\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bA\u0010)R\u0011\u0010\u001a\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bB\u0010-R\u0011\u0010\u001b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bC\u0010-R\u0011\u0010\u001c\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bD\u0010-R\u0011\u0010\u001d\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bE\u0010-R\u0011\u0010\u001e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bF\u0010-R\u0011\u0010\u001f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bG\u0010-R\u0011\u0010 \u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bH\u0010-R\u0011\u0010!\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bI\u0010-R\u0015\u0010\"\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bJ\u0010?R\u0015\u0010#\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010@\u001a\u0004\bK\u0010?R\u0011\u0010$\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bL\u0010-R\u0011\u0010%\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bM\u0010-¨\u0006q"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/Document;", "", "id", "", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "documentNumber", "", "dateHijri", "dateGregorian", "amount", "", "amountWords", "beneficiaryName", "purpose", "details", "notes", NotificationCompat.CATEGORY_STATUS, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "attachmentsCount", "", "createdAt", "isArchived", "", "archivedAt", "updatedAt", "tags", "financialCategory", "costCenter", "fundingSource", "beneficiaryId", "submittedBy", "reviewedBy", "approvedBy", "approvedAt", "paidAt", "rejectionReason", "cloudId", "<init>", "(JLcom/mohammedalhzmi/masrofmanager/data/DocumentType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;IJZLjava/lang/Long;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getType", "()Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "getDocumentNumber", "()Ljava/lang/String;", "getDateHijri", "getDateGregorian", "getAmount", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getAmountWords", "getBeneficiaryName", "getPurpose", "getDetails", "getNotes", "getStatus", "()Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "getAttachmentsCount", "()I", "getCreatedAt", "()Z", "getArchivedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getUpdatedAt", "getTags", "getFinancialCategory", "getCostCenter", "getFundingSource", "getBeneficiaryId", "getSubmittedBy", "getReviewedBy", "getApprovedBy", "getApprovedAt", "getPaidAt", "getRejectionReason", "getCloudId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "copy", "(JLcom/mohammedalhzmi/masrofmanager/data/DocumentType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;IJZLjava/lang/Long;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;)Lcom/mohammedalhzmi/masrofmanager/data/Document;", "equals", "other", "hashCode", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class Document {
    public static final int $stable = 0;
    private final Double amount;
    private final String amountWords;
    private final Long approvedAt;
    private final String approvedBy;
    private final Long archivedAt;
    private final int attachmentsCount;
    private final String beneficiaryId;
    private final String beneficiaryName;
    private final String cloudId;
    private final String costCenter;
    private final long createdAt;
    private final String dateGregorian;
    private final String dateHijri;
    private final String details;
    private final String documentNumber;
    private final String financialCategory;
    private final String fundingSource;
    private final long id;
    private final boolean isArchived;
    private final String notes;
    private final Long paidAt;
    private final String purpose;
    private final String rejectionReason;
    private final String reviewedBy;
    private final DocumentStatus status;
    private final String submittedBy;
    private final String tags;
    private final DocumentType type;
    private final long updatedAt;

    public static /* synthetic */ Document copy$default(Document document, long j, DocumentType documentType, String str, String str2, String str3, Double d, String str4, String str5, String str6, String str7, String str8, DocumentStatus documentStatus, int i, long j2, boolean z, Long l, long j3, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, Long l2, Long l3, String str17, String str18, int i2, Object obj) {
        String str19;
        String str20;
        long j4 = (i2 & 1) != 0 ? document.id : j;
        DocumentType documentType2 = (i2 & 2) != 0 ? document.type : documentType;
        String str21 = (i2 & 4) != 0 ? document.documentNumber : str;
        String str22 = (i2 & 8) != 0 ? document.dateHijri : str2;
        String str23 = (i2 & 16) != 0 ? document.dateGregorian : str3;
        Double d2 = (i2 & 32) != 0 ? document.amount : d;
        String str24 = (i2 & 64) != 0 ? document.amountWords : str4;
        String str25 = (i2 & 128) != 0 ? document.beneficiaryName : str5;
        String str26 = (i2 & 256) != 0 ? document.purpose : str6;
        String str27 = (i2 & 512) != 0 ? document.details : str7;
        String str28 = (i2 & 1024) != 0 ? document.notes : str8;
        DocumentStatus documentStatus2 = (i2 & 2048) != 0 ? document.status : documentStatus;
        int i3 = (i2 & 4096) != 0 ? document.attachmentsCount : i;
        long j5 = j4;
        long j6 = (i2 & 8192) != 0 ? document.createdAt : j2;
        boolean z2 = (i2 & 16384) != 0 ? document.isArchived : z;
        Long l4 = (32768 & i2) != 0 ? document.archivedAt : l;
        boolean z3 = z2;
        long j7 = (i2 & 65536) != 0 ? document.updatedAt : j3;
        String str29 = (i2 & 131072) != 0 ? document.tags : str9;
        String str30 = (i2 & 262144) != 0 ? document.financialCategory : str10;
        String str31 = str29;
        String str32 = (i2 & 524288) != 0 ? document.costCenter : str11;
        String str33 = (i2 & 1048576) != 0 ? document.fundingSource : str12;
        String str34 = (i2 & 2097152) != 0 ? document.beneficiaryId : str13;
        String str35 = (i2 & 4194304) != 0 ? document.submittedBy : str14;
        String str36 = (i2 & 8388608) != 0 ? document.reviewedBy : str15;
        String str37 = (i2 & 16777216) != 0 ? document.approvedBy : str16;
        Long l5 = (i2 & 33554432) != 0 ? document.approvedAt : l2;
        Long l6 = (i2 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? document.paidAt : l3;
        String str38 = (i2 & 134217728) != 0 ? document.rejectionReason : str17;
        if ((i2 & 268435456) != 0) {
            str20 = str38;
            str19 = document.cloudId;
        } else {
            str19 = str18;
            str20 = str38;
        }
        return document.copy(j5, documentType2, str21, str22, str23, d2, str24, str25, str26, str27, str28, documentStatus2, i3, j6, z3, l4, j7, str31, str30, str32, str33, str34, str35, str36, str37, l5, l6, str20, str19);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getDetails() {
        return this.details;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getNotes() {
        return this.notes;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final DocumentStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getAttachmentsCount() {
        return this.attachmentsCount;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final long getCreatedAt() {
        return this.createdAt;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getIsArchived() {
        return this.isArchived;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final Long getArchivedAt() {
        return this.archivedAt;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getTags() {
        return this.tags;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getFinancialCategory() {
        return this.financialCategory;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final DocumentType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getCostCenter() {
        return this.costCenter;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final String getFundingSource() {
        return this.fundingSource;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getBeneficiaryId() {
        return this.beneficiaryId;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getSubmittedBy() {
        return this.submittedBy;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getReviewedBy() {
        return this.reviewedBy;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getApprovedBy() {
        return this.approvedBy;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final Long getApprovedAt() {
        return this.approvedAt;
    }

    /* JADX INFO: renamed from: component27, reason: from getter */
    public final Long getPaidAt() {
        return this.paidAt;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final String getRejectionReason() {
        return this.rejectionReason;
    }

    /* JADX INFO: renamed from: component29, reason: from getter */
    public final String getCloudId() {
        return this.cloudId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDocumentNumber() {
        return this.documentNumber;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDateHijri() {
        return this.dateHijri;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getDateGregorian() {
        return this.dateGregorian;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Double getAmount() {
        return this.amount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAmountWords() {
        return this.amountWords;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getPurpose() {
        return this.purpose;
    }

    public final Document copy(long id, DocumentType type, String documentNumber, String dateHijri, String dateGregorian, Double amount, String amountWords, String beneficiaryName, String purpose, String details, String notes, DocumentStatus status, int attachmentsCount, long createdAt, boolean isArchived, Long archivedAt, long updatedAt, String tags, String financialCategory, String costCenter, String fundingSource, String beneficiaryId, String submittedBy, String reviewedBy, String approvedBy, Long approvedAt, Long paidAt, String rejectionReason, String cloudId) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(documentNumber, "documentNumber");
        Intrinsics.checkNotNullParameter(dateHijri, "dateHijri");
        Intrinsics.checkNotNullParameter(dateGregorian, "dateGregorian");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(financialCategory, "financialCategory");
        Intrinsics.checkNotNullParameter(costCenter, "costCenter");
        Intrinsics.checkNotNullParameter(fundingSource, "fundingSource");
        Intrinsics.checkNotNullParameter(beneficiaryId, "beneficiaryId");
        Intrinsics.checkNotNullParameter(submittedBy, "submittedBy");
        Intrinsics.checkNotNullParameter(reviewedBy, "reviewedBy");
        Intrinsics.checkNotNullParameter(approvedBy, "approvedBy");
        Intrinsics.checkNotNullParameter(rejectionReason, "rejectionReason");
        Intrinsics.checkNotNullParameter(cloudId, "cloudId");
        return new Document(id, type, documentNumber, dateHijri, dateGregorian, amount, amountWords, beneficiaryName, purpose, details, notes, status, attachmentsCount, createdAt, isArchived, archivedAt, updatedAt, tags, financialCategory, costCenter, fundingSource, beneficiaryId, submittedBy, reviewedBy, approvedBy, approvedAt, paidAt, rejectionReason, cloudId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Document)) {
            return false;
        }
        Document document = (Document) other;
        return this.id == document.id && this.type == document.type && Intrinsics.areEqual(this.documentNumber, document.documentNumber) && Intrinsics.areEqual(this.dateHijri, document.dateHijri) && Intrinsics.areEqual(this.dateGregorian, document.dateGregorian) && Intrinsics.areEqual((Object) this.amount, (Object) document.amount) && Intrinsics.areEqual(this.amountWords, document.amountWords) && Intrinsics.areEqual(this.beneficiaryName, document.beneficiaryName) && Intrinsics.areEqual(this.purpose, document.purpose) && Intrinsics.areEqual(this.details, document.details) && Intrinsics.areEqual(this.notes, document.notes) && this.status == document.status && this.attachmentsCount == document.attachmentsCount && this.createdAt == document.createdAt && this.isArchived == document.isArchived && Intrinsics.areEqual(this.archivedAt, document.archivedAt) && this.updatedAt == document.updatedAt && Intrinsics.areEqual(this.tags, document.tags) && Intrinsics.areEqual(this.financialCategory, document.financialCategory) && Intrinsics.areEqual(this.costCenter, document.costCenter) && Intrinsics.areEqual(this.fundingSource, document.fundingSource) && Intrinsics.areEqual(this.beneficiaryId, document.beneficiaryId) && Intrinsics.areEqual(this.submittedBy, document.submittedBy) && Intrinsics.areEqual(this.reviewedBy, document.reviewedBy) && Intrinsics.areEqual(this.approvedBy, document.approvedBy) && Intrinsics.areEqual(this.approvedAt, document.approvedAt) && Intrinsics.areEqual(this.paidAt, document.paidAt) && Intrinsics.areEqual(this.rejectionReason, document.rejectionReason) && Intrinsics.areEqual(this.cloudId, document.cloudId);
    }

    public int hashCode() {
        int iHashCode = ((((((((Long.hashCode(this.id) * 31) + this.type.hashCode()) * 31) + this.documentNumber.hashCode()) * 31) + this.dateHijri.hashCode()) * 31) + this.dateGregorian.hashCode()) * 31;
        Double d = this.amount;
        int iHashCode2 = (iHashCode + (d == null ? 0 : d.hashCode())) * 31;
        String str = this.amountWords;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.beneficiaryName;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.purpose;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.details;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.notes;
        int iHashCode7 = (((((((((iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.status.hashCode()) * 31) + Integer.hashCode(this.attachmentsCount)) * 31) + Long.hashCode(this.createdAt)) * 31) + Boolean.hashCode(this.isArchived)) * 31;
        Long l = this.archivedAt;
        int iHashCode8 = (((((((((((((((((((iHashCode7 + (l == null ? 0 : l.hashCode())) * 31) + Long.hashCode(this.updatedAt)) * 31) + this.tags.hashCode()) * 31) + this.financialCategory.hashCode()) * 31) + this.costCenter.hashCode()) * 31) + this.fundingSource.hashCode()) * 31) + this.beneficiaryId.hashCode()) * 31) + this.submittedBy.hashCode()) * 31) + this.reviewedBy.hashCode()) * 31) + this.approvedBy.hashCode()) * 31;
        Long l2 = this.approvedAt;
        int iHashCode9 = (iHashCode8 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.paidAt;
        return ((((iHashCode9 + (l3 != null ? l3.hashCode() : 0)) * 31) + this.rejectionReason.hashCode()) * 31) + this.cloudId.hashCode();
    }

    public String toString() {
        return "Document(id=" + this.id + ", type=" + this.type + ", documentNumber=" + this.documentNumber + ", dateHijri=" + this.dateHijri + ", dateGregorian=" + this.dateGregorian + ", amount=" + this.amount + ", amountWords=" + this.amountWords + ", beneficiaryName=" + this.beneficiaryName + ", purpose=" + this.purpose + ", details=" + this.details + ", notes=" + this.notes + ", status=" + this.status + ", attachmentsCount=" + this.attachmentsCount + ", createdAt=" + this.createdAt + ", isArchived=" + this.isArchived + ", archivedAt=" + this.archivedAt + ", updatedAt=" + this.updatedAt + ", tags=" + this.tags + ", financialCategory=" + this.financialCategory + ", costCenter=" + this.costCenter + ", fundingSource=" + this.fundingSource + ", beneficiaryId=" + this.beneficiaryId + ", submittedBy=" + this.submittedBy + ", reviewedBy=" + this.reviewedBy + ", approvedBy=" + this.approvedBy + ", approvedAt=" + this.approvedAt + ", paidAt=" + this.paidAt + ", rejectionReason=" + this.rejectionReason + ", cloudId=" + this.cloudId + ")";
    }

    public Document(long j, DocumentType type, String documentNumber, String dateHijri, String dateGregorian, Double d, String str, String str2, String str3, String str4, String str5, DocumentStatus status, int i, long j2, boolean z, Long l, long j3, String tags, String financialCategory, String costCenter, String fundingSource, String beneficiaryId, String submittedBy, String reviewedBy, String approvedBy, Long l2, Long l3, String rejectionReason, String cloudId) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(documentNumber, "documentNumber");
        Intrinsics.checkNotNullParameter(dateHijri, "dateHijri");
        Intrinsics.checkNotNullParameter(dateGregorian, "dateGregorian");
        Intrinsics.checkNotNullParameter(status, "status");
        Intrinsics.checkNotNullParameter(tags, "tags");
        Intrinsics.checkNotNullParameter(financialCategory, "financialCategory");
        Intrinsics.checkNotNullParameter(costCenter, "costCenter");
        Intrinsics.checkNotNullParameter(fundingSource, "fundingSource");
        Intrinsics.checkNotNullParameter(beneficiaryId, "beneficiaryId");
        Intrinsics.checkNotNullParameter(submittedBy, "submittedBy");
        Intrinsics.checkNotNullParameter(reviewedBy, "reviewedBy");
        Intrinsics.checkNotNullParameter(approvedBy, "approvedBy");
        Intrinsics.checkNotNullParameter(rejectionReason, "rejectionReason");
        Intrinsics.checkNotNullParameter(cloudId, "cloudId");
        this.id = j;
        this.type = type;
        this.documentNumber = documentNumber;
        this.dateHijri = dateHijri;
        this.dateGregorian = dateGregorian;
        this.amount = d;
        this.amountWords = str;
        this.beneficiaryName = str2;
        this.purpose = str3;
        this.details = str4;
        this.notes = str5;
        this.status = status;
        this.attachmentsCount = i;
        this.createdAt = j2;
        this.isArchived = z;
        this.archivedAt = l;
        this.updatedAt = j3;
        this.tags = tags;
        this.financialCategory = financialCategory;
        this.costCenter = costCenter;
        this.fundingSource = fundingSource;
        this.beneficiaryId = beneficiaryId;
        this.submittedBy = submittedBy;
        this.reviewedBy = reviewedBy;
        this.approvedBy = approvedBy;
        this.approvedAt = l2;
        this.paidAt = l3;
        this.rejectionReason = rejectionReason;
        this.cloudId = cloudId;
    }

    public final long getId() {
        return this.id;
    }

    public final String getDateGregorian() {
        return this.dateGregorian;
    }

    public final String getDateHijri() {
        return this.dateHijri;
    }

    public final String getDocumentNumber() {
        return this.documentNumber;
    }

    public final DocumentType getType() {
        return this.type;
    }

    public final Double getAmount() {
        return this.amount;
    }

    public final String getAmountWords() {
        return this.amountWords;
    }

    public final String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public final String getPurpose() {
        return this.purpose;
    }

    public final int getAttachmentsCount() {
        return this.attachmentsCount;
    }

    public final String getDetails() {
        return this.details;
    }

    public final String getNotes() {
        return this.notes;
    }

    public final DocumentStatus getStatus() {
        return this.status;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ Document(long j, DocumentType documentType, String str, String str2, String str3, Double d, String str4, String str5, String str6, String str7, String str8, DocumentStatus documentStatus, int i, long j2, boolean z, Long l, long j3, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, Long l2, Long l3, String str17, String str18, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        long j4 = (i2 & 1) != 0 ? 0L : j;
        int i3 = (i2 & 4096) != 0 ? 0 : i;
        long jCurrentTimeMillis = (i2 & 8192) != 0 ? System.currentTimeMillis() : j2;
        this(j4, documentType, str, str2, str3, d, str4, str5, str6, str7, str8, documentStatus, i3, jCurrentTimeMillis, (i2 & 16384) != 0 ? false : z, (32768 & i2) != 0 ? null : l, (65536 & i2) != 0 ? jCurrentTimeMillis : j3, (131072 & i2) != 0 ? "" : str9, (262144 & i2) != 0 ? "" : str10, (524288 & i2) != 0 ? "" : str11, (1048576 & i2) != 0 ? "" : str12, (2097152 & i2) != 0 ? "" : str13, (4194304 & i2) != 0 ? "" : str14, (8388608 & i2) != 0 ? "" : str15, (16777216 & i2) != 0 ? "" : str16, (33554432 & i2) != 0 ? null : l2, (67108864 & i2) != 0 ? null : l3, (134217728 & i2) != 0 ? "" : str17, (i2 & 268435456) != 0 ? "" : str18);
    }

    public final Long getArchivedAt() {
        return this.archivedAt;
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final boolean isArchived() {
        return this.isArchived;
    }

    public final String getTags() {
        return this.tags;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getCostCenter() {
        return this.costCenter;
    }

    public final String getFinancialCategory() {
        return this.financialCategory;
    }

    public final String getFundingSource() {
        return this.fundingSource;
    }

    public final String getBeneficiaryId() {
        return this.beneficiaryId;
    }

    public final String getReviewedBy() {
        return this.reviewedBy;
    }

    public final String getSubmittedBy() {
        return this.submittedBy;
    }

    public final Long getApprovedAt() {
        return this.approvedAt;
    }

    public final String getApprovedBy() {
        return this.approvedBy;
    }

    public final Long getPaidAt() {
        return this.paidAt;
    }

    public final String getRejectionReason() {
        return this.rejectionReason;
    }

    public final String getCloudId() {
        return this.cloudId;
    }
}
