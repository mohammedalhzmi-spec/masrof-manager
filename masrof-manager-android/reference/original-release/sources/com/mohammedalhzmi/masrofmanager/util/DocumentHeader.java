package com.mohammedalhzmi.masrofmanager.util;

import android.graphics.Bitmap;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: OfficialDocumentPrintAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b*\b\u0087\b\u0018\u00002\u00020\u0001B·\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007\u0012\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007\u0012 \b\u0002\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00120\u0007\u0012\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007\u0012\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007\u0012\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\u0017\u0010/\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007HÆ\u0003J\u0015\u00100\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u0015\u00101\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0003J\u0017\u00102\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007HÆ\u0003J\u0015\u00103\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007HÆ\u0003J\u0015\u00104\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007HÆ\u0003J!\u00105\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00120\u0007HÆ\u0003J\u0015\u00106\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0003J\u0015\u00107\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007HÆ\u0003J\u0015\u00108\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007HÆ\u0003J\u0015\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007HÆ\u0003J¿\u0002\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00072\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u00072\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00072\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u00072\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u00072 \b\u0002\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00120\u00072\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u00072\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u00072\u0014\b\u0002\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u00072\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u00072\u0014\b\u0002\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007HÆ\u0001J\u0013\u0010<\u001a\u00020\u00162\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020\fHÖ\u0001J\t\u0010?\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u001d\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u001f\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010 R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u001d\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010 R)\u0010\u0011\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00120\u0007¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u001d\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010 R\u001d\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b(\u0010 R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u001d\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00160\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010 ¨\u0006@"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/DocumentHeader;", "", "ministry", "", "administration", "branch", "logos", "", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "Landroid/graphics/Bitmap;", "pageSizes", "backgroundColors", "", "backgroundImages", "backgroundOpacity", "", "backgroundScale", "backgroundOffset", "Lkotlin/Pair;", "textColors", "fontFamilies", "textBold", "", "textItalic", "textUnderline", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "getMinistry", "()Ljava/lang/String;", "getAdministration", "getBranch", "getLogos", "()Ljava/util/Map;", "getPageSizes", "getBackgroundColors", "getBackgroundImages", "getBackgroundOpacity", "getBackgroundScale", "getBackgroundOffset", "getTextColors", "getFontFamilies", "getTextBold", "getTextItalic", "getTextUnderline", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "copy", "equals", "other", "hashCode", "toString", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final /* data */ class DocumentHeader {
    public static final int $stable = 8;
    private final String administration;
    private final Map<DocumentType, Integer> backgroundColors;
    private final Map<DocumentType, Bitmap> backgroundImages;
    private final Map<DocumentType, Pair<Float, Float>> backgroundOffset;
    private final Map<DocumentType, Float> backgroundOpacity;
    private final Map<DocumentType, Float> backgroundScale;
    private final String branch;
    private final Map<DocumentType, String> fontFamilies;
    private final Map<DocumentType, Bitmap> logos;
    private final String ministry;
    private final Map<DocumentType, String> pageSizes;
    private final Map<DocumentType, Boolean> textBold;
    private final Map<DocumentType, Integer> textColors;
    private final Map<DocumentType, Boolean> textItalic;
    private final Map<DocumentType, Boolean> textUnderline;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMinistry() {
        return this.ministry;
    }

    public final Map<DocumentType, Pair<Float, Float>> component10() {
        return this.backgroundOffset;
    }

    public final Map<DocumentType, Integer> component11() {
        return this.textColors;
    }

    public final Map<DocumentType, String> component12() {
        return this.fontFamilies;
    }

    public final Map<DocumentType, Boolean> component13() {
        return this.textBold;
    }

    public final Map<DocumentType, Boolean> component14() {
        return this.textItalic;
    }

    public final Map<DocumentType, Boolean> component15() {
        return this.textUnderline;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAdministration() {
        return this.administration;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getBranch() {
        return this.branch;
    }

    public final Map<DocumentType, Bitmap> component4() {
        return this.logos;
    }

    public final Map<DocumentType, String> component5() {
        return this.pageSizes;
    }

    public final Map<DocumentType, Integer> component6() {
        return this.backgroundColors;
    }

    public final Map<DocumentType, Bitmap> component7() {
        return this.backgroundImages;
    }

    public final Map<DocumentType, Float> component8() {
        return this.backgroundOpacity;
    }

    public final Map<DocumentType, Float> component9() {
        return this.backgroundScale;
    }

    public final DocumentHeader copy(String ministry, String administration, String branch, Map<DocumentType, Bitmap> logos, Map<DocumentType, String> pageSizes, Map<DocumentType, Integer> backgroundColors, Map<DocumentType, Bitmap> backgroundImages, Map<DocumentType, Float> backgroundOpacity, Map<DocumentType, Float> backgroundScale, Map<DocumentType, Pair<Float, Float>> backgroundOffset, Map<DocumentType, Integer> textColors, Map<DocumentType, String> fontFamilies, Map<DocumentType, Boolean> textBold, Map<DocumentType, Boolean> textItalic, Map<DocumentType, Boolean> textUnderline) {
        Intrinsics.checkNotNullParameter(ministry, "ministry");
        Intrinsics.checkNotNullParameter(administration, "administration");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(logos, "logos");
        Intrinsics.checkNotNullParameter(pageSizes, "pageSizes");
        Intrinsics.checkNotNullParameter(backgroundColors, "backgroundColors");
        Intrinsics.checkNotNullParameter(backgroundImages, "backgroundImages");
        Intrinsics.checkNotNullParameter(backgroundOpacity, "backgroundOpacity");
        Intrinsics.checkNotNullParameter(backgroundScale, "backgroundScale");
        Intrinsics.checkNotNullParameter(backgroundOffset, "backgroundOffset");
        Intrinsics.checkNotNullParameter(textColors, "textColors");
        Intrinsics.checkNotNullParameter(fontFamilies, "fontFamilies");
        Intrinsics.checkNotNullParameter(textBold, "textBold");
        Intrinsics.checkNotNullParameter(textItalic, "textItalic");
        Intrinsics.checkNotNullParameter(textUnderline, "textUnderline");
        return new DocumentHeader(ministry, administration, branch, logos, pageSizes, backgroundColors, backgroundImages, backgroundOpacity, backgroundScale, backgroundOffset, textColors, fontFamilies, textBold, textItalic, textUnderline);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DocumentHeader)) {
            return false;
        }
        DocumentHeader documentHeader = (DocumentHeader) other;
        return Intrinsics.areEqual(this.ministry, documentHeader.ministry) && Intrinsics.areEqual(this.administration, documentHeader.administration) && Intrinsics.areEqual(this.branch, documentHeader.branch) && Intrinsics.areEqual(this.logos, documentHeader.logos) && Intrinsics.areEqual(this.pageSizes, documentHeader.pageSizes) && Intrinsics.areEqual(this.backgroundColors, documentHeader.backgroundColors) && Intrinsics.areEqual(this.backgroundImages, documentHeader.backgroundImages) && Intrinsics.areEqual(this.backgroundOpacity, documentHeader.backgroundOpacity) && Intrinsics.areEqual(this.backgroundScale, documentHeader.backgroundScale) && Intrinsics.areEqual(this.backgroundOffset, documentHeader.backgroundOffset) && Intrinsics.areEqual(this.textColors, documentHeader.textColors) && Intrinsics.areEqual(this.fontFamilies, documentHeader.fontFamilies) && Intrinsics.areEqual(this.textBold, documentHeader.textBold) && Intrinsics.areEqual(this.textItalic, documentHeader.textItalic) && Intrinsics.areEqual(this.textUnderline, documentHeader.textUnderline);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((this.ministry.hashCode() * 31) + this.administration.hashCode()) * 31) + this.branch.hashCode()) * 31) + this.logos.hashCode()) * 31) + this.pageSizes.hashCode()) * 31) + this.backgroundColors.hashCode()) * 31) + this.backgroundImages.hashCode()) * 31) + this.backgroundOpacity.hashCode()) * 31) + this.backgroundScale.hashCode()) * 31) + this.backgroundOffset.hashCode()) * 31) + this.textColors.hashCode()) * 31) + this.fontFamilies.hashCode()) * 31) + this.textBold.hashCode()) * 31) + this.textItalic.hashCode()) * 31) + this.textUnderline.hashCode();
    }

    public String toString() {
        return "DocumentHeader(ministry=" + this.ministry + ", administration=" + this.administration + ", branch=" + this.branch + ", logos=" + this.logos + ", pageSizes=" + this.pageSizes + ", backgroundColors=" + this.backgroundColors + ", backgroundImages=" + this.backgroundImages + ", backgroundOpacity=" + this.backgroundOpacity + ", backgroundScale=" + this.backgroundScale + ", backgroundOffset=" + this.backgroundOffset + ", textColors=" + this.textColors + ", fontFamilies=" + this.fontFamilies + ", textBold=" + this.textBold + ", textItalic=" + this.textItalic + ", textUnderline=" + this.textUnderline + ")";
    }

    public DocumentHeader(String ministry, String administration, String branch, Map<DocumentType, Bitmap> logos, Map<DocumentType, String> pageSizes, Map<DocumentType, Integer> backgroundColors, Map<DocumentType, Bitmap> backgroundImages, Map<DocumentType, Float> backgroundOpacity, Map<DocumentType, Float> backgroundScale, Map<DocumentType, Pair<Float, Float>> backgroundOffset, Map<DocumentType, Integer> textColors, Map<DocumentType, String> fontFamilies, Map<DocumentType, Boolean> textBold, Map<DocumentType, Boolean> textItalic, Map<DocumentType, Boolean> textUnderline) {
        Intrinsics.checkNotNullParameter(ministry, "ministry");
        Intrinsics.checkNotNullParameter(administration, "administration");
        Intrinsics.checkNotNullParameter(branch, "branch");
        Intrinsics.checkNotNullParameter(logos, "logos");
        Intrinsics.checkNotNullParameter(pageSizes, "pageSizes");
        Intrinsics.checkNotNullParameter(backgroundColors, "backgroundColors");
        Intrinsics.checkNotNullParameter(backgroundImages, "backgroundImages");
        Intrinsics.checkNotNullParameter(backgroundOpacity, "backgroundOpacity");
        Intrinsics.checkNotNullParameter(backgroundScale, "backgroundScale");
        Intrinsics.checkNotNullParameter(backgroundOffset, "backgroundOffset");
        Intrinsics.checkNotNullParameter(textColors, "textColors");
        Intrinsics.checkNotNullParameter(fontFamilies, "fontFamilies");
        Intrinsics.checkNotNullParameter(textBold, "textBold");
        Intrinsics.checkNotNullParameter(textItalic, "textItalic");
        Intrinsics.checkNotNullParameter(textUnderline, "textUnderline");
        this.ministry = ministry;
        this.administration = administration;
        this.branch = branch;
        this.logos = logos;
        this.pageSizes = pageSizes;
        this.backgroundColors = backgroundColors;
        this.backgroundImages = backgroundImages;
        this.backgroundOpacity = backgroundOpacity;
        this.backgroundScale = backgroundScale;
        this.backgroundOffset = backgroundOffset;
        this.textColors = textColors;
        this.fontFamilies = fontFamilies;
        this.textBold = textBold;
        this.textItalic = textItalic;
        this.textUnderline = textUnderline;
    }

    public final String getMinistry() {
        return this.ministry;
    }

    public final String getAdministration() {
        return this.administration;
    }

    public final String getBranch() {
        return this.branch;
    }

    public /* synthetic */ DocumentHeader(String str, String str2, String str3, Map map, Map map2, Map map3, Map map4, Map map5, Map map6, Map map7, Map map8, Map map9, Map map10, Map map11, Map map12, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? MapsKt.emptyMap() : map, (i & 16) != 0 ? MapsKt.emptyMap() : map2, (i & 32) != 0 ? MapsKt.emptyMap() : map3, (i & 64) != 0 ? MapsKt.emptyMap() : map4, (i & 128) != 0 ? MapsKt.emptyMap() : map5, (i & 256) != 0 ? MapsKt.emptyMap() : map6, (i & 512) != 0 ? MapsKt.emptyMap() : map7, (i & 1024) != 0 ? MapsKt.emptyMap() : map8, (i & 2048) != 0 ? MapsKt.emptyMap() : map9, (i & 4096) != 0 ? MapsKt.emptyMap() : map10, (i & 8192) != 0 ? MapsKt.emptyMap() : map11, (i & 16384) != 0 ? MapsKt.emptyMap() : map12);
    }

    public final Map<DocumentType, Bitmap> getLogos() {
        return this.logos;
    }

    public final Map<DocumentType, String> getPageSizes() {
        return this.pageSizes;
    }

    public final Map<DocumentType, Integer> getBackgroundColors() {
        return this.backgroundColors;
    }

    public final Map<DocumentType, Bitmap> getBackgroundImages() {
        return this.backgroundImages;
    }

    public final Map<DocumentType, Float> getBackgroundOpacity() {
        return this.backgroundOpacity;
    }

    public final Map<DocumentType, Float> getBackgroundScale() {
        return this.backgroundScale;
    }

    public final Map<DocumentType, Pair<Float, Float>> getBackgroundOffset() {
        return this.backgroundOffset;
    }

    public final Map<DocumentType, Integer> getTextColors() {
        return this.textColors;
    }

    public final Map<DocumentType, String> getFontFamilies() {
        return this.fontFamilies;
    }

    public final Map<DocumentType, Boolean> getTextBold() {
        return this.textBold;
    }

    public final Map<DocumentType, Boolean> getTextItalic() {
        return this.textItalic;
    }

    public final Map<DocumentType, Boolean> getTextUnderline() {
        return this.textUnderline;
    }
}
