package com.mohammedalhzmi.masrofmanager.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DesignModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b*\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\b\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\bHÆ\u0003J\t\u0010(\u001a\u00020\bHÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\bHÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\t\u00100\u001a\u00020\bHÆ\u0003J\u008d\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\b2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\bHÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0011\u0010\u000f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\u0011\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001a¨\u00068"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "", "id", "", "documentType", "", "name", "pageWidth", "", "pageHeight", "backgroundColor", "backgroundImageUri", "updatedAt", "orientation", "marginLeft", "marginTop", "marginRight", "marginBottom", "<init>", "(JLjava/lang/String;Ljava/lang/String;FFLjava/lang/String;Ljava/lang/String;JLjava/lang/String;FFFF)V", "getId", "()J", "getDocumentType", "()Ljava/lang/String;", "getName", "getPageWidth", "()F", "getPageHeight", "getBackgroundColor", "getBackgroundImageUri", "getUpdatedAt", "getOrientation", "getMarginLeft", "getMarginTop", "getMarginRight", "getMarginBottom", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class DocumentDesignEntity {
    public static final int $stable = 0;
    private final String backgroundColor;
    private final String backgroundImageUri;
    private final String documentType;
    private final long id;
    private final float marginBottom;
    private final float marginLeft;
    private final float marginRight;
    private final float marginTop;
    private final String name;
    private final String orientation;
    private final float pageHeight;
    private final float pageWidth;
    private final long updatedAt;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getMarginLeft() {
        return this.marginLeft;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final float getMarginTop() {
        return this.marginTop;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final float getMarginRight() {
        return this.marginRight;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final float getMarginBottom() {
        return this.marginBottom;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDocumentType() {
        return this.documentType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getPageWidth() {
        return this.pageWidth;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getPageHeight() {
        return this.pageHeight;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getBackgroundImageUri() {
        return this.backgroundImageUri;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getOrientation() {
        return this.orientation;
    }

    public final DocumentDesignEntity copy(long id, String documentType, String name, float pageWidth, float pageHeight, String backgroundColor, String backgroundImageUri, long updatedAt, String orientation, float marginLeft, float marginTop, float marginRight, float marginBottom) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        return new DocumentDesignEntity(id, documentType, name, pageWidth, pageHeight, backgroundColor, backgroundImageUri, updatedAt, orientation, marginLeft, marginTop, marginRight, marginBottom);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentDesignEntity)) {
            return false;
        }
        DocumentDesignEntity documentDesignEntity = (DocumentDesignEntity) other;
        return this.id == documentDesignEntity.id && Intrinsics.areEqual(this.documentType, documentDesignEntity.documentType) && Intrinsics.areEqual(this.name, documentDesignEntity.name) && Float.compare(this.pageWidth, documentDesignEntity.pageWidth) == 0 && Float.compare(this.pageHeight, documentDesignEntity.pageHeight) == 0 && Intrinsics.areEqual(this.backgroundColor, documentDesignEntity.backgroundColor) && Intrinsics.areEqual(this.backgroundImageUri, documentDesignEntity.backgroundImageUri) && this.updatedAt == documentDesignEntity.updatedAt && Intrinsics.areEqual(this.orientation, documentDesignEntity.orientation) && Float.compare(this.marginLeft, documentDesignEntity.marginLeft) == 0 && Float.compare(this.marginTop, documentDesignEntity.marginTop) == 0 && Float.compare(this.marginRight, documentDesignEntity.marginRight) == 0 && Float.compare(this.marginBottom, documentDesignEntity.marginBottom) == 0;
    }

    public int hashCode() {
        int iHashCode = ((((((((((Long.hashCode(this.id) * 31) + this.documentType.hashCode()) * 31) + this.name.hashCode()) * 31) + Float.hashCode(this.pageWidth)) * 31) + Float.hashCode(this.pageHeight)) * 31) + this.backgroundColor.hashCode()) * 31;
        String str = this.backgroundImageUri;
        return ((((((((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Long.hashCode(this.updatedAt)) * 31) + this.orientation.hashCode()) * 31) + Float.hashCode(this.marginLeft)) * 31) + Float.hashCode(this.marginTop)) * 31) + Float.hashCode(this.marginRight)) * 31) + Float.hashCode(this.marginBottom);
    }

    public String toString() {
        return "DocumentDesignEntity(id=" + this.id + ", documentType=" + this.documentType + ", name=" + this.name + ", pageWidth=" + this.pageWidth + ", pageHeight=" + this.pageHeight + ", backgroundColor=" + this.backgroundColor + ", backgroundImageUri=" + this.backgroundImageUri + ", updatedAt=" + this.updatedAt + ", orientation=" + this.orientation + ", marginLeft=" + this.marginLeft + ", marginTop=" + this.marginTop + ", marginRight=" + this.marginRight + ", marginBottom=" + this.marginBottom + ")";
    }

    public DocumentDesignEntity(long j, String documentType, String name, float f, float f2, String backgroundColor, String str, long j2, String orientation, float f3, float f4, float f5, float f6) {
        Intrinsics.checkNotNullParameter(documentType, "documentType");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
        Intrinsics.checkNotNullParameter(orientation, "orientation");
        this.id = j;
        this.documentType = documentType;
        this.name = name;
        this.pageWidth = f;
        this.pageHeight = f2;
        this.backgroundColor = backgroundColor;
        this.backgroundImageUri = str;
        this.updatedAt = j2;
        this.orientation = orientation;
        this.marginLeft = f3;
        this.marginTop = f4;
        this.marginRight = f5;
        this.marginBottom = f6;
    }

    public final long getId() {
        return this.id;
    }

    public final String getDocumentType() {
        return this.documentType;
    }

    public final String getName() {
        return this.name;
    }

    public final float getPageWidth() {
        return this.pageWidth;
    }

    public final float getPageHeight() {
        return this.pageHeight;
    }

    public /* synthetic */ DocumentDesignEntity(long j, String str, String str2, float f, float f2, String str3, String str4, long j2, String str5, float f3, float f4, float f5, float f6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, str2, (i & 8) != 0 ? 595.0f : f, (i & 16) != 0 ? 842.0f : f2, (i & 32) != 0 ? "#FFFFFF" : str3, (i & 64) != 0 ? null : str4, (i & 128) != 0 ? System.currentTimeMillis() : j2, (i & 256) != 0 ? "PORTRAIT" : str5, (i & 512) != 0 ? 25.0f : f3, (i & 1024) != 0 ? 25.0f : f4, (i & 2048) != 0 ? 25.0f : f5, (i & 4096) != 0 ? 25.0f : f6);
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final String getBackgroundImageUri() {
        return this.backgroundImageUri;
    }

    public final long getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getOrientation() {
        return this.orientation;
    }

    public final float getMarginLeft() {
        return this.marginLeft;
    }

    public final float getMarginTop() {
        return this.marginTop;
    }

    public final float getMarginRight() {
        return this.marginRight;
    }

    public final float getMarginBottom() {
        return this.marginBottom;
    }
}
