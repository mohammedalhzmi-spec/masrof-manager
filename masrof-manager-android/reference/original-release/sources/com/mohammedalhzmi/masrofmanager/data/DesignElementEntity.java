package com.mohammedalhzmi.masrofmanager.data;

import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DesignModels.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\bI\b\u0087\b\u0018\u00002\u00020\u0001Bý\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001a\u001a\u00020\t\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u001e\u001a\u00020\t\u0012\b\b\u0002\u0010\u001f\u001a\u00020\t¢\u0006\u0004\b \u0010!J\t\u0010@\u001a\u00020\u0003HÆ\u0003J\t\u0010A\u001a\u00020\u0003HÆ\u0003J\t\u0010B\u001a\u00020\u0006HÆ\u0003J\t\u0010C\u001a\u00020\u0006HÆ\u0003J\t\u0010D\u001a\u00020\tHÆ\u0003J\t\u0010E\u001a\u00020\tHÆ\u0003J\t\u0010F\u001a\u00020\tHÆ\u0003J\t\u0010G\u001a\u00020\tHÆ\u0003J\t\u0010H\u001a\u00020\tHÆ\u0003J\t\u0010I\u001a\u00020\tHÆ\u0003J\t\u0010J\u001a\u00020\u0010HÆ\u0003J\t\u0010K\u001a\u00020\u0006HÆ\u0003J\t\u0010L\u001a\u00020\tHÆ\u0003J\t\u0010M\u001a\u00020\u0006HÆ\u0003J\t\u0010N\u001a\u00020\u0015HÆ\u0003J\t\u0010O\u001a\u00020\u0015HÆ\u0003J\t\u0010P\u001a\u00020\u0015HÆ\u0003J\t\u0010Q\u001a\u00020\u0006HÆ\u0003J\t\u0010R\u001a\u00020\u0006HÆ\u0003J\t\u0010S\u001a\u00020\tHÆ\u0003J\t\u0010T\u001a\u00020\u0015HÆ\u0003J\t\u0010U\u001a\u00020\u0015HÆ\u0003J\t\u0010V\u001a\u00020\u0006HÆ\u0003J\t\u0010W\u001a\u00020\tHÆ\u0003J\t\u0010X\u001a\u00020\tHÆ\u0003J\u0083\u0002\u0010Y\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\u001b\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u00152\b\b\u0002\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\t2\b\b\u0002\u0010\u001f\u001a\u00020\tHÆ\u0001J\u0013\u0010Z\u001a\u00020\u00152\b\u0010[\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\\\u001a\u00020\u0010HÖ\u0001J\t\u0010]\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b+\u0010)R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b,\u0010)R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b-\u0010)R\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010)R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b1\u0010&R\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b2\u0010)R\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b3\u0010&R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0016\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b6\u00105R\u0011\u0010\u0017\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b7\u00105R\u0011\u0010\u0018\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b9\u0010&R\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b:\u0010)R\u0011\u0010\u001b\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b;\u00105R\u0011\u0010\u001c\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b<\u00105R\u0011\u0010\u001d\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b=\u0010&R\u0011\u0010\u001e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b>\u0010)R\u0011\u0010\u001f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b?\u0010)¨\u0006^"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "", "id", "", "designId", LinkHeader.Parameters.Type, "", "content", "x", "", "y", "width", "height", "rotation", "opacity", "zIndex", "", "fontFamily", "fontSize", "textColor", "bold", "", "italic", "underline", "fillColor", "strokeColor", "strokeWidth", "locked", "visible", "textAlign", "lineSpacing", "cornerRadius", "<init>", "(JJLjava/lang/String;Ljava/lang/String;FFFFFFILjava/lang/String;FLjava/lang/String;ZZZLjava/lang/String;Ljava/lang/String;FZZLjava/lang/String;FF)V", "getId", "()J", "getDesignId", "getType", "()Ljava/lang/String;", "getContent", "getX", "()F", "getY", "getWidth", "getHeight", "getRotation", "getOpacity", "getZIndex", "()I", "getFontFamily", "getFontSize", "getTextColor", "getBold", "()Z", "getItalic", "getUnderline", "getFillColor", "getStrokeColor", "getStrokeWidth", "getLocked", "getVisible", "getTextAlign", "getLineSpacing", "getCornerRadius", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "copy", "equals", "other", "hashCode", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class DesignElementEntity {
    public static final int $stable = 0;
    private final boolean bold;
    private final String content;
    private final float cornerRadius;
    private final long designId;
    private final String fillColor;
    private final String fontFamily;
    private final float fontSize;
    private final float height;
    private final long id;
    private final boolean italic;
    private final float lineSpacing;
    private final boolean locked;
    private final float opacity;
    private final float rotation;
    private final String strokeColor;
    private final float strokeWidth;
    private final String textAlign;
    private final String textColor;
    private final String type;
    private final boolean underline;
    private final boolean visible;
    private final float width;
    private final float x;
    private final float y;
    private final int zIndex;

    public static /* synthetic */ DesignElementEntity copy$default(DesignElementEntity designElementEntity, long j, long j2, String str, String str2, float f, float f2, float f3, float f4, float f5, float f6, int i, String str3, float f7, String str4, boolean z, boolean z2, boolean z3, String str5, String str6, float f8, boolean z4, boolean z5, String str7, float f9, float f10, int i2, Object obj) {
        float f11;
        float f12;
        long j3 = (i2 & 1) != 0 ? designElementEntity.id : j;
        long j4 = (i2 & 2) != 0 ? designElementEntity.designId : j2;
        String str8 = (i2 & 4) != 0 ? designElementEntity.type : str;
        String str9 = (i2 & 8) != 0 ? designElementEntity.content : str2;
        float f13 = (i2 & 16) != 0 ? designElementEntity.x : f;
        float f14 = (i2 & 32) != 0 ? designElementEntity.y : f2;
        float f15 = (i2 & 64) != 0 ? designElementEntity.width : f3;
        float f16 = (i2 & 128) != 0 ? designElementEntity.height : f4;
        float f17 = (i2 & 256) != 0 ? designElementEntity.rotation : f5;
        float f18 = (i2 & 512) != 0 ? designElementEntity.opacity : f6;
        int i3 = (i2 & 1024) != 0 ? designElementEntity.zIndex : i;
        String str10 = (i2 & 2048) != 0 ? designElementEntity.fontFamily : str3;
        long j5 = j3;
        float f19 = (i2 & 4096) != 0 ? designElementEntity.fontSize : f7;
        String str11 = (i2 & 8192) != 0 ? designElementEntity.textColor : str4;
        float f20 = f19;
        boolean z6 = (i2 & 16384) != 0 ? designElementEntity.bold : z;
        boolean z7 = (i2 & 32768) != 0 ? designElementEntity.italic : z2;
        boolean z8 = (i2 & 65536) != 0 ? designElementEntity.underline : z3;
        String str12 = (i2 & 131072) != 0 ? designElementEntity.fillColor : str5;
        String str13 = (i2 & 262144) != 0 ? designElementEntity.strokeColor : str6;
        float f21 = (i2 & 524288) != 0 ? designElementEntity.strokeWidth : f8;
        boolean z9 = (i2 & 1048576) != 0 ? designElementEntity.locked : z4;
        boolean z10 = (i2 & 2097152) != 0 ? designElementEntity.visible : z5;
        String str14 = (i2 & 4194304) != 0 ? designElementEntity.textAlign : str7;
        float f22 = (i2 & 8388608) != 0 ? designElementEntity.lineSpacing : f9;
        if ((i2 & 16777216) != 0) {
            f12 = f22;
            f11 = designElementEntity.cornerRadius;
        } else {
            f11 = f10;
            f12 = f22;
        }
        return designElementEntity.copy(j5, j4, str8, str9, f13, f14, f15, f16, f17, f18, i3, str10, f20, str11, z6, z7, z8, str12, str13, f21, z9, z10, str14, f12, f11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final float getOpacity() {
        return this.opacity;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getZIndex() {
        return this.zIndex;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getFontFamily() {
        return this.fontFamily;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final float getFontSize() {
        return this.fontSize;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getTextColor() {
        return this.textColor;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final boolean getBold() {
        return this.bold;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final boolean getItalic() {
        return this.italic;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getUnderline() {
        return this.underline;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getFillColor() {
        return this.fillColor;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getStrokeColor() {
        return this.strokeColor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getDesignId() {
        return this.designId;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final float getStrokeWidth() {
        return this.strokeWidth;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getLocked() {
        return this.locked;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final boolean getVisible() {
        return this.visible;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getTextAlign() {
        return this.textAlign;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final float getLineSpacing() {
        return this.lineSpacing;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getContent() {
        return this.content;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getX() {
        return this.x;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final float getY() {
        return this.y;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final float getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final float getHeight() {
        return this.height;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final float getRotation() {
        return this.rotation;
    }

    public final DesignElementEntity copy(long id, long designId, String type, String content, float x, float y, float width, float height, float rotation, float opacity, int zIndex, String fontFamily, float fontSize, String textColor, boolean bold, boolean italic, boolean underline, String fillColor, String strokeColor, float strokeWidth, boolean locked, boolean visible, String textAlign, float lineSpacing, float cornerRadius) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(fontFamily, "fontFamily");
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(fillColor, "fillColor");
        Intrinsics.checkNotNullParameter(strokeColor, "strokeColor");
        Intrinsics.checkNotNullParameter(textAlign, "textAlign");
        return new DesignElementEntity(id, designId, type, content, x, y, width, height, rotation, opacity, zIndex, fontFamily, fontSize, textColor, bold, italic, underline, fillColor, strokeColor, strokeWidth, locked, visible, textAlign, lineSpacing, cornerRadius);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DesignElementEntity)) {
            return false;
        }
        DesignElementEntity designElementEntity = (DesignElementEntity) other;
        return this.id == designElementEntity.id && this.designId == designElementEntity.designId && Intrinsics.areEqual(this.type, designElementEntity.type) && Intrinsics.areEqual(this.content, designElementEntity.content) && Float.compare(this.x, designElementEntity.x) == 0 && Float.compare(this.y, designElementEntity.y) == 0 && Float.compare(this.width, designElementEntity.width) == 0 && Float.compare(this.height, designElementEntity.height) == 0 && Float.compare(this.rotation, designElementEntity.rotation) == 0 && Float.compare(this.opacity, designElementEntity.opacity) == 0 && this.zIndex == designElementEntity.zIndex && Intrinsics.areEqual(this.fontFamily, designElementEntity.fontFamily) && Float.compare(this.fontSize, designElementEntity.fontSize) == 0 && Intrinsics.areEqual(this.textColor, designElementEntity.textColor) && this.bold == designElementEntity.bold && this.italic == designElementEntity.italic && this.underline == designElementEntity.underline && Intrinsics.areEqual(this.fillColor, designElementEntity.fillColor) && Intrinsics.areEqual(this.strokeColor, designElementEntity.strokeColor) && Float.compare(this.strokeWidth, designElementEntity.strokeWidth) == 0 && this.locked == designElementEntity.locked && this.visible == designElementEntity.visible && Intrinsics.areEqual(this.textAlign, designElementEntity.textAlign) && Float.compare(this.lineSpacing, designElementEntity.lineSpacing) == 0 && Float.compare(this.cornerRadius, designElementEntity.cornerRadius) == 0;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((Long.hashCode(this.id) * 31) + Long.hashCode(this.designId)) * 31) + this.type.hashCode()) * 31) + this.content.hashCode()) * 31) + Float.hashCode(this.x)) * 31) + Float.hashCode(this.y)) * 31) + Float.hashCode(this.width)) * 31) + Float.hashCode(this.height)) * 31) + Float.hashCode(this.rotation)) * 31) + Float.hashCode(this.opacity)) * 31) + Integer.hashCode(this.zIndex)) * 31) + this.fontFamily.hashCode()) * 31) + Float.hashCode(this.fontSize)) * 31) + this.textColor.hashCode()) * 31) + Boolean.hashCode(this.bold)) * 31) + Boolean.hashCode(this.italic)) * 31) + Boolean.hashCode(this.underline)) * 31) + this.fillColor.hashCode()) * 31) + this.strokeColor.hashCode()) * 31) + Float.hashCode(this.strokeWidth)) * 31) + Boolean.hashCode(this.locked)) * 31) + Boolean.hashCode(this.visible)) * 31) + this.textAlign.hashCode()) * 31) + Float.hashCode(this.lineSpacing)) * 31) + Float.hashCode(this.cornerRadius);
    }

    public String toString() {
        return "DesignElementEntity(id=" + this.id + ", designId=" + this.designId + ", type=" + this.type + ", content=" + this.content + ", x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", rotation=" + this.rotation + ", opacity=" + this.opacity + ", zIndex=" + this.zIndex + ", fontFamily=" + this.fontFamily + ", fontSize=" + this.fontSize + ", textColor=" + this.textColor + ", bold=" + this.bold + ", italic=" + this.italic + ", underline=" + this.underline + ", fillColor=" + this.fillColor + ", strokeColor=" + this.strokeColor + ", strokeWidth=" + this.strokeWidth + ", locked=" + this.locked + ", visible=" + this.visible + ", textAlign=" + this.textAlign + ", lineSpacing=" + this.lineSpacing + ", cornerRadius=" + this.cornerRadius + ")";
    }

    public DesignElementEntity(long j, long j2, String type, String content, float f, float f2, float f3, float f4, float f5, float f6, int i, String fontFamily, float f7, String textColor, boolean z, boolean z2, boolean z3, String fillColor, String strokeColor, float f8, boolean z4, boolean z5, String textAlign, float f9, float f10) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(fontFamily, "fontFamily");
        Intrinsics.checkNotNullParameter(textColor, "textColor");
        Intrinsics.checkNotNullParameter(fillColor, "fillColor");
        Intrinsics.checkNotNullParameter(strokeColor, "strokeColor");
        Intrinsics.checkNotNullParameter(textAlign, "textAlign");
        this.id = j;
        this.designId = j2;
        this.type = type;
        this.content = content;
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
        this.rotation = f5;
        this.opacity = f6;
        this.zIndex = i;
        this.fontFamily = fontFamily;
        this.fontSize = f7;
        this.textColor = textColor;
        this.bold = z;
        this.italic = z2;
        this.underline = z3;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.strokeWidth = f8;
        this.locked = z4;
        this.visible = z5;
        this.textAlign = textAlign;
        this.lineSpacing = f9;
        this.cornerRadius = f10;
    }

    public final long getId() {
        return this.id;
    }

    public final long getDesignId() {
        return this.designId;
    }

    public final String getType() {
        return this.type;
    }

    public /* synthetic */ DesignElementEntity(long j, long j2, String str, String str2, float f, float f2, float f3, float f4, float f5, float f6, int i, String str3, float f7, String str4, boolean z, boolean z2, boolean z3, String str5, String str6, float f8, boolean z4, boolean z5, String str7, float f9, float f10, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, j2, str, (i2 & 8) != 0 ? "" : str2, (i2 & 16) != 0 ? 40.0f : f, (i2 & 32) != 0 ? 40.0f : f2, (i2 & 64) != 0 ? 180.0f : f3, (i2 & 128) != 0 ? 48.0f : f4, (i2 & 256) != 0 ? 0.0f : f5, (i2 & 512) != 0 ? 1.0f : f6, (i2 & 1024) != 0 ? 0 : i, (i2 & 2048) != 0 ? "SANS" : str3, (i2 & 4096) != 0 ? 18.0f : f7, (i2 & 8192) != 0 ? "#000000" : str4, (i2 & 16384) != 0 ? false : z, (32768 & i2) != 0 ? false : z2, (65536 & i2) != 0 ? false : z3, (131072 & i2) != 0 ? "#FFFFFF" : str5, (262144 & i2) != 0 ? "#123B5D" : str6, (524288 & i2) != 0 ? 2.0f : f8, (1048576 & i2) != 0 ? false : z4, (2097152 & i2) != 0 ? true : z5, (4194304 & i2) != 0 ? "START" : str7, (8388608 & i2) != 0 ? 1.0f : f9, (i2 & 16777216) != 0 ? 0.0f : f10);
    }

    public final String getContent() {
        return this.content;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getHeight() {
        return this.height;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final float getOpacity() {
        return this.opacity;
    }

    public final int getZIndex() {
        return this.zIndex;
    }

    public final String getFontFamily() {
        return this.fontFamily;
    }

    public final float getFontSize() {
        return this.fontSize;
    }

    public final String getTextColor() {
        return this.textColor;
    }

    public final boolean getBold() {
        return this.bold;
    }

    public final boolean getItalic() {
        return this.italic;
    }

    public final boolean getUnderline() {
        return this.underline;
    }

    public final String getFillColor() {
        return this.fillColor;
    }

    public final String getStrokeColor() {
        return this.strokeColor;
    }

    public final float getStrokeWidth() {
        return this.strokeWidth;
    }

    public final boolean getLocked() {
        return this.locked;
    }

    public final boolean getVisible() {
        return this.visible;
    }

    public final String getTextAlign() {
        return this.textAlign;
    }

    public final float getLineSpacing() {
        return this.lineSpacing;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }
}
