package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ViewCompat;
import com.example.C2530R;
import com.google.firebase.firestore.model.Values;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.mohammedalhzmi.masrofmanager.data.DesignElementEntity;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentStatus;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.LinkHeader;
import io.ktor.sse.ServerSentEventKt;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OfficialDocumentPrintAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0013\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0019J*\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J@\u0010 \u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020#H\u0002J\u0018\u0010(\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0010H\u0002J\u0018\u0010*\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0010H\u0002J\u0018\u0010+\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0010H\u0002J\u0018\u0010,\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0010H\u0002J\u0010\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020/H\u0002J0\u00100\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J0\u00101\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J2\u00105\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u00152\u0006\u00104\u001a\u00020\u00072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002J \u00107\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0015H\u0002J\u0018\u00108\u001a\u00020\u001d2\u0006\u00106\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u0010H\u0002J0\u00109\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u00104\u001a\u00020\u0007H\u0002J0\u0010:\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u00104\u001a\u00020\u0007H\u0002J0\u0010;\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u00104\u001a\u00020\u0007H\u0002J8\u0010<\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u00020#2\u0006\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020#H\u0002J0\u0010A\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u00104\u001a\u00020\u0007H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/OfficialDocumentRenderer;", "", "<init>", "()V", "navy", "", "titlePaint", "Landroid/graphics/Paint;", "bodyPaint", "boldPaint", "linePaint", "render", "", "canvas", "Landroid/graphics/Canvas;", "document", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "header", "Lcom/mohammedalhzmi/masrofmanager/util/DocumentHeader;", "elements", "", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "context", "Landroid/content/Context;", "design", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "drawHeader", "c", "statusTitle", "", NotificationCompat.CATEGORY_STATUS, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentStatus;", "drawFittedRight", "text", "x", "", "y", "paint", "maxWidth", "minSize", "renderOrder", "d", "renderOrderPortrait", "renderRequest", "renderReceipt", LinkHeader.Parameters.Title, LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "renderElements", "drawTable", "content", "e", "p", "drawRichText", Values.VECTOR_MAP_VECTORS_KEY, "drawQr", "resolve", "drawCentered", "drawRight", "drawLeft", "drawBoxed", "l", "t", "r", "b", "drawParagraph", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class OfficialDocumentRenderer {
    public static final int $stable;
    public static final OfficialDocumentRenderer INSTANCE = new OfficialDocumentRenderer();
    private static final Paint bodyPaint;
    private static final Paint boldPaint;
    private static final Paint linePaint;
    private static final int navy = -15582371;
    private static final Paint titlePaint;

    /* JADX INFO: compiled from: OfficialDocumentPrintAdapter.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.ORDER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DocumentStatus.values().length];
            try {
                iArr2[DocumentStatus.DRAFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[DocumentStatus.SUBMITTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[DocumentStatus.APPROVED_FINANCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[DocumentStatus.APPROVED_BRANCH.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[DocumentStatus.APPROVED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[DocumentStatus.PAID.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[DocumentStatus.RECEIVED.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[DocumentStatus.CANCELLED.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private OfficialDocumentRenderer() {
    }

    static {
        Paint paint = new Paint(1);
        paint.setColor(navy);
        paint.setTextSize(22.0f);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        titlePaint = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setTextSize(14.0f);
        paint2.setTypeface(Typeface.DEFAULT);
        bodyPaint = paint2;
        Paint paint3 = new Paint(1);
        paint3.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint3.setTextSize(15.0f);
        paint3.setTypeface(Typeface.DEFAULT_BOLD);
        boldPaint = paint3;
        Paint paint4 = new Paint(1);
        paint4.setColor(navy);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(2.0f);
        linePaint = paint4;
        $stable = 8;
    }

    public final void render(Canvas canvas, Document document, DocumentHeader header, List<DesignElementEntity> elements, Context context, DocumentDesignEntity design) {
        Object objM7781constructorimpl;
        int i;
        Typeface typeface;
        int i2;
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(document, "document");
        Intrinsics.checkNotNullParameter(header, "header");
        Intrinsics.checkNotNullParameter(elements, "elements");
        float width = canvas.getWidth();
        float height = canvas.getHeight();
        boolean z = width > height;
        float f = z ? 842.0f : 595.0f;
        float f2 = z ? 595.0f : 842.0f;
        canvas.save();
        canvas.scale(width / f, height / f2);
        int iIntValue = -1;
        if (design != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(design.getBackgroundColor())));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                objM7781constructorimpl = -1;
            }
            iIntValue = ((Number) objM7781constructorimpl).intValue();
        } else {
            Integer num = header.getBackgroundColors().get(document.getType());
            if (num != null) {
                iIntValue = num.intValue();
            }
        }
        canvas.drawColor(iIntValue);
        Bitmap bitmap = header.getBackgroundImages().get(document.getType());
        if (bitmap != null) {
            Float f3 = header.getBackgroundScale().get(document.getType());
            float fMin = Math.min(f, f2) * 0.58f * (f3 != null ? f3.floatValue() : 1.0f);
            Pair<Float, Float> pairM921to = header.getBackgroundOffset().get(document.getType());
            if (pairM921to == null) {
                pairM921to = TuplesKt.m921to(Float.valueOf(0.0f), Float.valueOf(0.0f));
            }
            i = 0;
            RectF rectF = new RectF(((f - fMin) / 2.0f) + pairM921to.getFirst().floatValue(), ((f2 - fMin) / 2.0f) + pairM921to.getSecond().floatValue(), ((f + fMin) / 2.0f) + pairM921to.getFirst().floatValue(), ((fMin + f2) / 2.0f) + pairM921to.getSecond().floatValue());
            Paint paint = new Paint(1);
            Float f4 = header.getBackgroundOpacity().get(document.getType());
            paint.setAlpha((int) ((f4 != null ? f4.floatValue() : 0.24f) * 255.0f));
            canvas.drawBitmap(bitmap, (Rect) null, rectF, paint);
        } else {
            i = 0;
        }
        Integer num2 = header.getTextColors().get(document.getType());
        int iIntValue2 = num2 != null ? num2.intValue() : ViewCompat.MEASURED_STATE_MASK;
        String str = header.getFontFamilies().get(document.getType());
        if (Intrinsics.areEqual(str, "SERIF")) {
            typeface = Typeface.SERIF;
        } else {
            typeface = Intrinsics.areEqual(str, "MONOSPACE") ? Typeface.MONOSPACE : Typeface.SANS_SERIF;
        }
        if (Intrinsics.areEqual((Object) header.getTextBold().get(document.getType()), (Object) true) && Intrinsics.areEqual((Object) header.getTextItalic().get(document.getType()), (Object) true)) {
            i2 = 3;
        } else if (Intrinsics.areEqual((Object) header.getTextBold().get(document.getType()), (Object) true)) {
            i2 = 1;
        } else {
            i2 = Intrinsics.areEqual((Object) header.getTextItalic().get(document.getType()), (Object) true) ? 2 : i;
        }
        Paint paint2 = bodyPaint;
        paint2.setColor(iIntValue2);
        paint2.setTypeface(Typeface.create(typeface, i2));
        paint2.setUnderlineText(Intrinsics.areEqual((Object) header.getTextUnderline().get(document.getType()), (Object) true));
        Paint paint3 = boldPaint;
        paint3.setColor(iIntValue2);
        paint3.setTypeface(Typeface.create(typeface, 1));
        Paint paint4 = linePaint;
        float f5 = f2;
        canvas.drawRect(18.0f, 18.0f, f - 18.0f, f2 - 18.0f, paint4);
        canvas.drawRect(25.0f, 25.0f, f - 25.0f, f5 - 25.0f, paint4);
        drawHeader(canvas, header, document, context);
        DocumentType[] documentTypeArr = new DocumentType[3];
        documentTypeArr[i] = DocumentType.ORDER;
        documentTypeArr[1] = DocumentType.REQUEST;
        documentTypeArr[2] = DocumentType.RECEIPT;
        Set of = SetsKt.setOf((Object[]) documentTypeArr);
        if (!elements.isEmpty() && !of.contains(document.getType())) {
            renderElements(canvas, document, elements, context);
        } else if (z) {
            renderOrder(canvas, document);
        } else {
            int i3 = WhenMappings.$EnumSwitchMapping$0[document.getType().ordinal()];
            if (i3 == 1) {
                renderRequest(canvas, document);
            } else if (i3 != 2) {
                renderOrderPortrait(canvas, document);
            } else {
                renderReceipt(canvas, document);
            }
        }
        if (design != null) {
            float marginLeft = design.getMarginLeft();
            float marginTop = design.getMarginTop();
            float marginRight = f - design.getMarginRight();
            float marginBottom = f5 - design.getMarginBottom();
            Paint paint5 = new Paint(1);
            paint5.setColor(1429418803);
            paint5.setStyle(Paint.Style.STROKE);
            paint5.setStrokeWidth(1.0f);
            Unit unit = Unit.INSTANCE;
            canvas.drawRect(marginLeft, marginTop, marginRight, marginBottom, paint5);
        }
        drawCentered(canvas, "طبع بواسطة نظام مالية فرع صندوق النظافةوالتحسين مديرية الحزم", f / 2.0f, f5 - 28.0f, paint2);
        canvas.restore();
    }

    private final void drawHeader(Canvas c, DocumentHeader header, Document document, Context context) {
        String str;
        Canvas canvas;
        OfficialDocumentRenderer officialDocumentRenderer;
        DocumentType type = document.getType();
        float width = c.getWidth();
        float f = width / 2.0f;
        Paint paint = new Paint(bodyPaint);
        paint.setTextSize(11.0f);
        paint.setTextAlign(Paint.Align.LEFT);
        Paint paint2 = new Paint(boldPaint);
        paint2.setTextSize(11.5f);
        paint2.setTextAlign(Paint.Align.RIGHT);
        if (type == DocumentType.RECEIPT) {
            String documentNumber = document.getDocumentNumber();
            if (StringsKt.isBlank(documentNumber)) {
                documentNumber = "................";
            }
            drawLeft(c, "الرقم : " + ((Object) documentNumber), 42.0f, 48.0f, paint);
        } else if (type != DocumentType.ORDER) {
            String documentNumber2 = document.getDocumentNumber();
            if (StringsKt.isBlank(documentNumber2)) {
                documentNumber2 = "....";
            }
            drawLeft(c, "NO: " + ((Object) documentNumber2), 42.0f, 48.0f, paint);
        }
        String dateHijri = document.getDateHijri();
        if (StringsKt.isBlank(dateHijri)) {
            dateHijri = "       /       /   144 هـ";
        }
        drawLeft(c, "التاريخ : " + ((Object) dateHijri), 42.0f, 68.0f, paint);
        String dateGregorian = document.getDateGregorian();
        if (StringsKt.isBlank(dateGregorian)) {
            dateGregorian = "       /       /     2026 م";
        }
        drawLeft(c, "الموافق : " + ((Object) dateGregorian), 42.0f, 88.0f, paint);
        if (document.getAttachmentsCount() > 0) {
            str = "( " + document.getAttachmentsCount() + " )";
        } else {
            str = "(         )";
        }
        drawLeft(c, "المرفقات : " + str, 42.0f, 108.0f, paint);
        float f2 = 42.0f;
        float f3 = width - 34.0f;
        float f4 = 0.34f * width;
        drawFittedRight(c, "الجمهورية اليمنية", f3, 46.0f, paint2, f4, 9.0f);
        drawFittedRight(c, "وزارة الإدارة والتنمية المحلية والريفية", f3, 65.0f, paint2, f4, 8.0f);
        drawFittedRight(c, "صندوق النظافة والتحسين م/إب", f3, 84.0f, paint2, f4, 9.0f);
        drawFittedRight(c, "فرع مديرية الحزم", f3, 103.0f, paint, f4, 9.0f);
        drawCentered(c, "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيمِ", f, 24.0f, paint);
        Bitmap bitmapDecodeResource = header.getLogos().get(type);
        if (bitmapDecodeResource == null) {
            bitmapDecodeResource = context != null ? BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.official_emblem) : null;
        }
        if (bitmapDecodeResource != null) {
            canvas = c;
            canvas.drawBitmap(bitmapDecodeResource, (Rect) null, new RectF(f - 34.0f, 32.0f, f + 34.0f, 106.0f), (Paint) null);
        } else {
            canvas = c;
        }
        Paint paint3 = linePaint;
        canvas.drawLine(30.0f, 123.0f, width - 30.0f, 123.0f, paint3);
        if (type == DocumentType.RECEIPT) {
            String documentNumber3 = document.getDocumentNumber();
            String str2 = "NO: " + ((Object) (StringsKt.isBlank(documentNumber3) ? "...." : documentNumber3));
            Paint paint4 = new Paint(paint2);
            paint4.setColor(SupportMenu.CATEGORY_MASK);
            paint4.setTextSize(17.0f);
            paint4.setTypeface(Typeface.DEFAULT_BOLD);
            Unit unit = Unit.INSTANCE;
            officialDocumentRenderer = this;
            officialDocumentRenderer.drawLeft(c, str2, 42.0f, 151.0f, paint4);
        } else {
            String documentNumber4 = document.getDocumentNumber();
            String str3 = StringsKt.isBlank(documentNumber4) ? "...." : documentNumber4;
            officialDocumentRenderer = this;
            officialDocumentRenderer.drawLeft(c, "NO: " + ((Object) str3), 42.0f, 151.0f, paint2);
            f2 = 42.0f;
        }
        officialDocumentRenderer.drawCentered(c, officialDocumentRenderer.title(type), f, 157.0f, titlePaint);
        OfficialDocumentRenderer officialDocumentRenderer2 = officialDocumentRenderer;
        c.drawRect(f - 112.0f, 134.0f, f + 112.0f, 170.0f, paint3);
        officialDocumentRenderer2.drawRight(c, "الحالة: " + officialDocumentRenderer2.statusTitle(document.getStatus()), width - 42.0f, 193.0f, paint);
        if (StringsKt.isBlank(document.getFinancialCategory())) {
            return;
        }
        drawLeft(c, "البند: " + document.getFinancialCategory(), f2, 193.0f, paint);
    }

    private final String statusTitle(DocumentStatus status) {
        switch (WhenMappings.$EnumSwitchMapping$1[status.ordinal()]) {
            case 1:
                return "مسودة";
            case 2:
                return "قيد المراجعة";
            case 3:
                return "اعتماد المدير المالي";
            case 4:
                return "اعتماد مدير الفرع";
            case 5:
                return "معتمد";
            case 6:
                return "تم الصرف";
            case 7:
                return "تم الاستلام";
            case 8:
                return "ملغى";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    private final void drawFittedRight(Canvas c, String text, float x, float y, Paint paint, float maxWidth, float minSize) {
        float textSize = paint.getTextSize();
        paint.setTextAlign(Paint.Align.RIGHT);
        paint.setTextSize(textSize);
        while (paint.measureText(text) > maxWidth && paint.getTextSize() > minSize) {
            paint.setTextSize(paint.getTextSize() - 0.5f);
        }
        c.drawText(text, x, y, paint);
        paint.setTextSize(textSize);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    private final void renderOrder(Canvas c, Document d) {
        String string;
        float width = c.getWidth();
        float f = width - 55.0f;
        float height = c.getHeight();
        Paint paint = bodyPaint;
        Paint paint2 = new Paint(paint);
        paint2.setTextSize(18.0f);
        Paint paint3 = new Paint(boldPaint);
        paint3.setTextSize(19.0f);
        drawRight(c, "الاخ أمين الصندوق", f, 224.0f, paint3);
        drawRight(c, "المحترم", f, 258.0f, paint2);
        drawRight(c, "بعد التوجيه يتم صرف مبلغ وقدره", f, 314.0f, paint2);
        String amountWords = d.getAmountWords();
        if (amountWords == null) {
            amountWords = "";
        }
        String str = amountWords;
        if (StringsKt.isBlank(str)) {
            str = "................................................";
        }
        drawRight(c, str, f - 220.0f, 314.0f, paint2);
        Double amount = d.getAmount();
        if (amount == null || (string = amount.toString()) == null) {
            string = "................";
        }
        drawBoxed(c, string, 70.0f, 288.0f, 238.0f, 332.0f);
        drawRight(c, "وذلك للأخ /ـوه:", f, 370.0f, paint2);
        String beneficiaryName = d.getBeneficiaryName();
        if (beneficiaryName == null) {
            beneficiaryName = "";
        }
        String str2 = beneficiaryName;
        if (StringsKt.isBlank(str2)) {
            str2 = "............................................";
        }
        drawRight(c, str2, f - 165.0f, 370.0f, paint2);
        drawRight(c, "وذلك مقابل:", f, 422.0f, paint2);
        String purpose = d.getPurpose();
        String str3 = purpose != null ? purpose : "";
        drawRight(c, StringsKt.isBlank(str3) ? "................................................" : str3, f - 130.0f, 422.0f, paint2);
        drawCentered(c, "ولكم خالص الشكر والتقدير", width / 2.0f, 500.0f, paint3);
        float f2 = height - 112.0f;
        drawLeft(c, "مدير الفرع صندوق النظافة", 70.0f, f2, paint3);
        float f3 = height - 82.0f;
        drawLeft(c, "رياض احمد محمد", 70.0f, f3, paint2);
        float f4 = height - 52.0f;
        drawLeft(c, "تــ/ ............", 70.0f, f4, paint2);
        drawRight(c, "المدير المالي", f, f2, paint3);
        drawRight(c, "الاسم: ............", f, f3, paint2);
        drawRight(c, "تــ/ ............", f, f4, paint2);
        drawLeft(c, "المرفقات: " + d.getAttachmentsCount(), 55.0f, height - 24.0f, paint);
    }

    private final void renderOrderPortrait(Canvas c, Document d) {
        renderOrder(c, d);
    }

    private final void renderRequest(Canvas c, Document d) {
        float width = c.getWidth() - 55.0f;
        float height = c.getHeight();
        Paint paint = boldPaint;
        drawRight(c, "إلى الأخ / مدير فرع صندوق النظافة والتحسين", width, 220.0f, paint);
        Paint paint2 = bodyPaint;
        drawRight(c, "المحترم", width, 246.0f, paint2);
        drawRight(c, "نتكرم بالتوجيه بصرف / اعتماد الطلب الموضح أدناه:", width, 292.0f, paint2);
        c.drawRect(55.0f, 310.0f, width, 455.0f, linePaint);
        String details = d.getDetails();
        if (details == null) {
            details = "................................................................................................";
        }
        drawParagraph(c, details, width - 12.0f, 338.0f, paint2);
        drawRight(c, "وتكرموا مشكورين بالتوجيه", width, 510.0f, paint);
        String beneficiaryName = d.getBeneficiaryName();
        if (beneficiaryName == null) {
            beneficiaryName = "";
        }
        drawRight(c, "اسم مقدم الطلب: " + beneficiaryName, width, height - 190.0f, paint2);
        drawLeft(c, "مقدم الطلب", 58.0f, height - 112.0f, paint);
        String beneficiaryName2 = d.getBeneficiaryName();
        drawLeft(c, "الاسم: " + (beneficiaryName2 != null ? beneficiaryName2 : ""), 58.0f, height - 84.0f, paint2);
        drawLeft(c, "التوقيع: ................................", 58.0f, height - 56.0f, paint2);
        drawLeft(c, "المرفقات: " + d.getAttachmentsCount(), 55.0f, height - 24.0f, paint2);
    }

    private final void renderReceipt(Canvas c, Document d) {
        String string;
        float width = c.getWidth() - 52.0f;
        float height = c.getHeight();
        Paint paint = bodyPaint;
        Paint paint2 = new Paint(paint);
        paint2.setTextSize(18.0f);
        paint2.setTypeface(Typeface.DEFAULT_BOLD);
        Paint paint3 = new Paint(paint);
        paint3.setTextSize(13.0f);
        drawRight(c, "أنا الموقع أدناه", width, 222.0f, paint2);
        String beneficiaryName = d.getBeneficiaryName();
        if (beneficiaryName == null) {
            beneficiaryName = "";
        }
        String str = beneficiaryName;
        if (StringsKt.isBlank(str)) {
            str = "................................................";
        }
        float f = width - 170.0f;
        drawRight(c, str, f, 222.0f, paint2);
        drawRight(c, "وأعمل بوظيفة", width, 266.0f, paint2);
        drawRight(c, "................................................", f, 266.0f, paint2);
        drawRight(c, "استلمت مبلغ وقدره", width, 314.0f, paint2);
        Double amount = d.getAmount();
        if (amount == null || (string = amount.toString()) == null) {
            string = "................";
        }
        drawRight(c, string, width - 190.0f, 314.0f, paint2);
        drawRight(c, "رقماً", width - 390.0f, 314.0f, paint2);
        drawRight(c, "................................", width - 465.0f, 314.0f, paint2);
        drawRight(c, "من فرع صندوق النظافة والتحسين مديرية الحزم", width, 366.0f, paint2);
        drawRight(c, "وذلك مقابل", width, 414.0f, paint2);
        String purpose = d.getPurpose();
        if (purpose == null) {
            purpose = "";
        }
        String str2 = purpose;
        drawRight(c, StringsKt.isBlank(str2) ? "................................................" : str2, width - 150.0f, 414.0f, paint2);
        drawRight(c, "لشهر: ................ سنة: 144 هـ     202 م", width, 466.0f, paint2);
        drawCentered(c, "وأقر بأنني استلمت المبلغ كاملاً دون نقص وإبهامي شاهدة على ذلك", c.getWidth() / 2.0f, 520.0f, paint2);
        drawRight(c, "المستلم: ................................", width, 584.0f, paint2);
        drawRight(c, "بصمة المستلم: ........................", width, 620.0f, paint2);
        String documentNumber = d.getDocumentNumber();
        Double amount2 = d.getAmount();
        DesignElementEntity designElementEntity = new DesignElementEntity(0L, 0L, "QR", documentNumber + "|" + (amount2 != null ? amount2 : ""), 38.0f, height - 170.0f, 72.0f, 72.0f, 0.0f, 0.0f, 0, null, 0.0f, null, false, false, false, null, null, 0.0f, false, false, null, 0.0f, 0.0f, 33554177, null);
        drawQr(c, designElementEntity.getContent(), designElementEntity);
        float f2 = height - 106.0f;
        drawCentered(c, "أمين الصندوق", c.getWidth() - 88.0f, f2, paint3);
        float f3 = height - 78.0f;
        drawCentered(c, "التوقيع: .................", c.getWidth() - 88.0f, f3, paint3);
        drawCentered(c, "مدير فرع صندوق النظافة", 98.0f, f2, paint3);
        drawCentered(c, "رياض احمد", 98.0f, f3, paint3);
        float f4 = height - 50.0f;
        drawCentered(c, "التوقيع: .................", 98.0f, f4, paint3);
        float f5 = width - 18.0f;
        drawCentered(c, "المدير المالي للفرع", f5, f2, paint3);
        drawCentered(c, "الاسم: .................", f5, f3, paint3);
        drawCentered(c, "التوقيع: .................", f5, f4, paint3);
        drawLeft(c, "المرفقات: (       )", 38.0f, 106.0f, paint3);
    }

    private final String title(DocumentType type) {
        int i = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i == 1) {
            return "ورقة تقديم طلب";
        }
        if (i != 2) {
            return i != 3 ? type.name() : "أمر صرف";
        }
        return "ورقة استلام";
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void renderElements(Canvas c, Document d, List<DesignElementEntity> elements, Context context) {
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        Object objM7781constructorimpl3;
        Object objM7781constructorimpl4;
        Object objM7781constructorimpl5;
        Object objM7781constructorimpl6;
        Object objM7781constructorimpl7;
        Object objM7781constructorimpl8;
        ArrayList arrayList = new ArrayList();
        for (Object obj : elements) {
            if (((DesignElementEntity) obj).getVisible()) {
                arrayList.add(obj);
            }
        }
        for (DesignElementEntity designElementEntity : CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.mohammedalhzmi.masrofmanager.util.OfficialDocumentRenderer$renderElements$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((DesignElementEntity) t).getZIndex()), Integer.valueOf(((DesignElementEntity) t2).getZIndex()));
            }
        })) {
            Paint paint = new Paint(1);
            paint.setAlpha((int) (RangesKt.coerceIn(designElementEntity.getOpacity(), 0.0f, 1.0f) * 255.0f));
            c.save();
            c.rotate(designElementEntity.getRotation(), designElementEntity.getX() + (designElementEntity.getWidth() / 2.0f), designElementEntity.getY() + (designElementEntity.getHeight() / 2.0f));
            String type = designElementEntity.getType();
            switch (type.hashCode()) {
                case -1172269795:
                    if (type.equals("STICKER")) {
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getTextColor())));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
                        }
                        Integer numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
                        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
                            objM7781constructorimpl = numValueOf;
                        }
                        paint.setColor(((Number) objM7781constructorimpl).intValue());
                        paint.setTextSize(designElementEntity.getHeight() * 0.75f);
                        paint.setTextAlign(Paint.Align.CENTER);
                        c.drawText(INSTANCE.resolve(designElementEntity.getContent(), d), designElementEntity.getX() + (designElementEntity.getWidth() / 2.0f), designElementEntity.getY() + (designElementEntity.getHeight() * 0.75f), paint);
                        paint.setTextAlign(Paint.Align.LEFT);
                        break;
                    }
                    break;
                case 2593:
                    if (type.equals("QR")) {
                        OfficialDocumentRenderer officialDocumentRenderer = INSTANCE;
                        officialDocumentRenderer.drawQr(c, officialDocumentRenderer.resolve(designElementEntity.getContent(), d), designElementEntity);
                    }
                    break;
                case 2336756:
                    if (type.equals("LINE")) {
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(designElementEntity.getStrokeWidth());
                        try {
                            Result.Companion companion3 = Result.INSTANCE;
                            objM7781constructorimpl2 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getStrokeColor())));
                        } catch (Throwable th2) {
                            Result.Companion companion4 = Result.INSTANCE;
                            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl2)) {
                            objM7781constructorimpl2 = -12303292;
                        }
                        paint.setColor(((Number) objM7781constructorimpl2).intValue());
                        c.drawLine(designElementEntity.getX(), designElementEntity.getY() + (designElementEntity.getHeight() / 2.0f), designElementEntity.getX() + designElementEntity.getWidth(), (designElementEntity.getHeight() / 2.0f) + designElementEntity.getY(), paint);
                        break;
                    }
                    break;
                case 2511332:
                    if (type.equals("RECT")) {
                        paint.setStyle(Paint.Style.FILL);
                        try {
                            Result.Companion companion5 = Result.INSTANCE;
                            objM7781constructorimpl3 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getFillColor())));
                        } catch (Throwable th3) {
                            Result.Companion companion6 = Result.INSTANCE;
                            objM7781constructorimpl3 = Result.m7781constructorimpl(ResultKt.createFailure(th3));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl3)) {
                            objM7781constructorimpl3 = 0;
                        }
                        paint.setColor(((Number) objM7781constructorimpl3).intValue());
                        c.drawRoundRect(designElementEntity.getX(), designElementEntity.getY(), designElementEntity.getX() + designElementEntity.getWidth(), designElementEntity.getY() + designElementEntity.getHeight(), designElementEntity.getCornerRadius(), designElementEntity.getCornerRadius(), paint);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(designElementEntity.getStrokeWidth());
                        try {
                            Result.Companion companion7 = Result.INSTANCE;
                            objM7781constructorimpl4 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getStrokeColor())));
                        } catch (Throwable th4) {
                            Result.Companion companion8 = Result.INSTANCE;
                            objM7781constructorimpl4 = Result.m7781constructorimpl(ResultKt.createFailure(th4));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl4)) {
                            objM7781constructorimpl4 = -12303292;
                        }
                        paint.setColor(((Number) objM7781constructorimpl4).intValue());
                        c.drawRoundRect(designElementEntity.getX(), designElementEntity.getY(), designElementEntity.getX() + designElementEntity.getWidth(), designElementEntity.getY() + designElementEntity.getHeight(), designElementEntity.getCornerRadius(), designElementEntity.getCornerRadius(), paint);
                        break;
                    }
                    break;
                case 2571565:
                    if (type.equals("TEXT")) {
                        OfficialDocumentRenderer officialDocumentRenderer2 = INSTANCE;
                        officialDocumentRenderer2.drawRichText(c, officialDocumentRenderer2.resolve(designElementEntity.getContent(), d), designElementEntity, paint, context);
                    }
                    break;
                case 62553065:
                    if (type.equals("ARROW")) {
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(designElementEntity.getStrokeWidth());
                        try {
                            Result.Companion companion9 = Result.INSTANCE;
                            objM7781constructorimpl5 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getStrokeColor())));
                        } catch (Throwable th5) {
                            Result.Companion companion10 = Result.INSTANCE;
                            objM7781constructorimpl5 = Result.m7781constructorimpl(ResultKt.createFailure(th5));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
                            objM7781constructorimpl5 = -12303292;
                        }
                        paint.setColor(((Number) objM7781constructorimpl5).intValue());
                        float y = designElementEntity.getY() + (designElementEntity.getHeight() / 2.0f);
                        c.drawLine(designElementEntity.getX(), y, (designElementEntity.getX() + designElementEntity.getWidth()) - 14.0f, y, paint);
                        c.drawLine((designElementEntity.getX() + designElementEntity.getWidth()) - 28.0f, y - 12.0f, designElementEntity.getWidth() + designElementEntity.getX(), y, paint);
                        c.drawLine((designElementEntity.getX() + designElementEntity.getWidth()) - 28.0f, y + 12.0f, designElementEntity.getX() + designElementEntity.getWidth(), y, paint);
                        break;
                    }
                    break;
                case 69775675:
                    if (!type.equals("IMAGE") || context == null) {
                        break;
                    } else {
                        try {
                            Result.Companion companion11 = Result.INSTANCE;
                            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(designElementEntity.getContent()));
                            try {
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                                CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                                objM7781constructorimpl6 = Result.m7781constructorimpl(bitmapDecodeStream);
                                if (Result.m7787isFailureimpl(objM7781constructorimpl6)) {
                                    objM7781constructorimpl6 = null;
                                }
                                Bitmap bitmap = (Bitmap) objM7781constructorimpl6;
                                if (bitmap != null) {
                                    c.drawBitmap(bitmap, (Rect) null, new RectF(designElementEntity.getX(), designElementEntity.getY(), designElementEntity.getX() + designElementEntity.getWidth(), designElementEntity.getY() + designElementEntity.getHeight()), paint);
                                }
                                break;
                            } catch (Throwable th6) {
                                try {
                                    throw th6;
                                } catch (Throwable th7) {
                                    CloseableKt.closeFinally(inputStreamOpenInputStream, th6);
                                    throw th7;
                                }
                            }
                        } catch (Throwable th8) {
                            Result.Companion companion12 = Result.INSTANCE;
                            objM7781constructorimpl6 = Result.m7781constructorimpl(ResultKt.createFailure(th8));
                        }
                    }
                    break;
                case 79578030:
                    if (type.equals("TABLE")) {
                        OfficialDocumentRenderer officialDocumentRenderer3 = INSTANCE;
                        officialDocumentRenderer3.drawTable(c, officialDocumentRenderer3.resolve(designElementEntity.getContent(), d), designElementEntity, paint, d);
                    }
                    break;
                case 1988079824:
                    if (type.equals("CIRCLE")) {
                        paint.setStyle(Paint.Style.FILL);
                        try {
                            Result.Companion companion13 = Result.INSTANCE;
                            objM7781constructorimpl7 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getFillColor())));
                        } catch (Throwable th9) {
                            Result.Companion companion14 = Result.INSTANCE;
                            objM7781constructorimpl7 = Result.m7781constructorimpl(ResultKt.createFailure(th9));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl7)) {
                            objM7781constructorimpl7 = 0;
                        }
                        paint.setColor(((Number) objM7781constructorimpl7).intValue());
                        c.drawOval(designElementEntity.getX(), designElementEntity.getY(), designElementEntity.getWidth() + designElementEntity.getX(), designElementEntity.getHeight() + designElementEntity.getY(), paint);
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(designElementEntity.getStrokeWidth());
                        try {
                            Result.Companion companion15 = Result.INSTANCE;
                            objM7781constructorimpl8 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(designElementEntity.getStrokeColor())));
                        } catch (Throwable th10) {
                            Result.Companion companion16 = Result.INSTANCE;
                            objM7781constructorimpl8 = Result.m7781constructorimpl(ResultKt.createFailure(th10));
                        }
                        if (Result.m7787isFailureimpl(objM7781constructorimpl8)) {
                            objM7781constructorimpl8 = -12303292;
                        }
                        paint.setColor(((Number) objM7781constructorimpl8).intValue());
                        c.drawOval(designElementEntity.getX(), designElementEntity.getY(), designElementEntity.getX() + designElementEntity.getWidth(), designElementEntity.getY() + designElementEntity.getHeight(), paint);
                        break;
                    }
                    break;
            }
            c.restore();
        }
    }

    /* JADX WARN: Code duplicated, block: B:66:0x0205  */
    private final void drawTable(Canvas c, String content, DesignElementEntity e, Paint p, Document document) {
        List listEmptyList;
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        Paint paint;
        Object objM7781constructorimpl3;
        Integer intOrNull;
        Integer intOrNull2;
        Paint paint2 = p;
        List listSplit$default = StringsKt.split$default((CharSequence) content, new String[]{"|"}, false, 3, 2, (Object) null);
        String str = (String) CollectionsKt.getOrNull(listSplit$default, 0);
        int iCoerceIn = 3;
        int iCoerceIn2 = (str == null || (intOrNull2 = StringsKt.toIntOrNull(str)) == null) ? 3 : RangesKt.coerceIn(intOrNull2.intValue(), 1, 20);
        String str2 = (String) CollectionsKt.getOrNull(listSplit$default, 1);
        if (str2 != null && (intOrNull = StringsKt.toIntOrNull(str2)) != null) {
            iCoerceIn = RangesKt.coerceIn(intOrNull.intValue(), 1, 10);
        }
        int i = iCoerceIn;
        String str3 = (String) CollectionsKt.getOrNull(listSplit$default, 2);
        if (str3 == null || (listEmptyList = StringsKt.split$default((CharSequence) str3, new String[]{"§"}, false, 0, 6, (Object) null)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list = listEmptyList;
        float width = e.getWidth() / i;
        float height = e.getHeight() / iCoerceIn2;
        paint2.setStyle(Paint.Style.FILL);
        try {
            Result.Companion companion = Result.INSTANCE;
            OfficialDocumentRenderer officialDocumentRenderer = this;
            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(e.getFillColor())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = -1;
        }
        paint2.setColor(((Number) objM7781constructorimpl).intValue());
        c.drawRect(e.getX(), e.getY(), e.getX() + e.getWidth(), e.getY() + e.getHeight(), paint2);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(e.getStrokeWidth());
        try {
            Result.Companion companion3 = Result.INSTANCE;
            OfficialDocumentRenderer officialDocumentRenderer2 = this;
            objM7781constructorimpl2 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(e.getStrokeColor())));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl2)) {
            objM7781constructorimpl2 = -12303292;
        }
        paint2.setColor(((Number) objM7781constructorimpl2).intValue());
        if (iCoerceIn2 >= 0) {
            int i2 = 0;
            while (true) {
                float f = i2 * height;
                c.drawLine(e.getX(), e.getY() + f, e.getX() + e.getWidth(), e.getY() + f, paint2);
                if (i2 == iCoerceIn2) {
                    break;
                }
                i2++;
                paint2 = p;
            }
        }
        if (i >= 0) {
            int i3 = 0;
            while (true) {
                float f2 = i3 * width;
                paint = p;
                c.drawLine(e.getX() + f2, e.getY(), e.getX() + f2, e.getHeight() + e.getY(), paint);
                if (i3 == i) {
                    break;
                } else {
                    i3++;
                }
            }
        } else {
            paint = p;
        }
        paint.setStyle(Paint.Style.FILL);
        try {
            Result.Companion companion5 = Result.INSTANCE;
            OfficialDocumentRenderer officialDocumentRenderer3 = this;
            objM7781constructorimpl3 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(e.getTextColor())));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM7781constructorimpl3 = Result.m7781constructorimpl(ResultKt.createFailure(th3));
        }
        Integer numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        if (Result.m7787isFailureimpl(objM7781constructorimpl3)) {
            objM7781constructorimpl3 = numValueOf;
        }
        paint.setColor(((Number) objM7781constructorimpl3).intValue());
        paint.setTextSize(Math.min(12.0f, 0.32f * height));
        paint.setTextAlign(Paint.Align.RIGHT);
        for (int i4 = 0; i4 < iCoerceIn2; i4++) {
            for (int i5 = 0; i5 < i; i5++) {
                String str4 = (String) CollectionsKt.getOrNull(list, (i4 * i) + i5);
                if (str4 != null) {
                    if (StringsKt.isBlank(str4)) {
                        str4 = null;
                    }
                    if (str4 != null) {
                        c.drawText(INSTANCE.resolve(str4, document), (e.getX() + ((i5 + 1) * width)) - 4.0f, e.getY() + (i4 * height) + paint.getTextSize() + 4.0f, paint);
                    }
                }
            }
        }
        paint.setTextAlign(Paint.Align.LEFT);
    }

    /* JADX WARN: Code duplicated, block: B:106:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:107:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:109:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:110:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:114:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:116:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:126:0x01db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x0137  */
    /* JADX WARN: Code duplicated, block: B:90:0x0143  */
    /* JADX WARN: Code duplicated, block: B:91:0x0146  */
    /* JADX WARN: Code duplicated, block: B:93:0x014e  */
    /* JADX WARN: Code duplicated, block: B:94:0x0151  */
    /* JADX WARN: Failed to find 'out' block for switch in B:25:0x0072. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void drawRichText(Canvas c, String value, DesignElementEntity e, Paint p, Context context) {
        Object objM7781constructorimpl;
        int i;
        String fontFamily;
        Typeface typeface;
        Typeface typefaceCreate;
        float x;
        float x2;
        float width;
        String textAlign;
        Paint.Align align;
        Object objM7781constructorimpl2;
        String str;
        Typeface typefaceCreateFromAsset;
        try {
            Result.Companion companion = Result.INSTANCE;
            OfficialDocumentRenderer officialDocumentRenderer = this;
            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(e.getTextColor())));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        Integer numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = numValueOf;
        }
        p.setColor(((Number) objM7781constructorimpl).intValue());
        p.setTextSize(e.getFontSize());
        int i2 = 0;
        if (e.getBold() && e.getItalic()) {
            i = 3;
        } else if (e.getBold()) {
            i = 1;
        } else {
            i = e.getItalic() ? 2 : 0;
        }
        if (context != null) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                AssetManager assets = context.getAssets();
                String fontFamily2 = e.getFontFamily();
                switch (fontFamily2.hashCode()) {
                    case -865942608:
                        if (fontFamily2.equals("EL_MESSIRI")) {
                            str = e.getBold() ? "el_messiri_bold.ttf" : "el_messiri_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case -827844642:
                        if (fontFamily2.equals("TAJAWAL")) {
                            str = e.getBold() ? "tajawal_bold.ttf" : "tajawal_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case 62395540:
                        if (fontFamily2.equals("AMIRI")) {
                            str = e.getBold() ? "amiri_bold.ttf" : "amiri_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case 63885096:
                        if (fontFamily2.equals("CAIRO")) {
                            str = e.getBold() ? "cairo_bold.ttf" : "cairo_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case 588189200:
                        if (fontFamily2.equals("NOTO_KUFI")) {
                            str = e.getBold() ? "noto_kufi_bold.ttf" : "noto_kufi_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case 714799649:
                        if (fontFamily2.equals("SCHEHERAZADE")) {
                            str = e.getBold() ? "scheherazade_bold.ttf" : "scheherazade_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    case 1056183386:
                        if (fontFamily2.equals("NOTO_NASKH")) {
                            str = e.getBold() ? "noto_naskh_bold.ttf" : "noto_naskh_regular.ttf";
                            typefaceCreateFromAsset = Typeface.createFromAsset(assets, "fonts/".concat(str));
                        } else {
                            typefaceCreateFromAsset = null;
                        }
                        break;
                    default:
                        typefaceCreateFromAsset = null;
                        break;
                }
                objM7781constructorimpl2 = Result.m7781constructorimpl(typefaceCreateFromAsset);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
            }
            typefaceCreate = (Typeface) (Result.m7787isFailureimpl(objM7781constructorimpl2) ? null : objM7781constructorimpl2);
            if (typefaceCreate == null) {
                fontFamily = e.getFontFamily();
                if (Intrinsics.areEqual(fontFamily, "SERIF")) {
                    typeface = Typeface.SERIF;
                } else if (Intrinsics.areEqual(fontFamily, "MONOSPACE")) {
                    typeface = Typeface.MONOSPACE;
                } else {
                    typeface = Typeface.SANS_SERIF;
                }
                typefaceCreate = Typeface.create(typeface, i);
            }
        } else {
            fontFamily = e.getFontFamily();
            if (Intrinsics.areEqual(fontFamily, "SERIF")) {
                typeface = Typeface.SERIF;
            } else if (Intrinsics.areEqual(fontFamily, "MONOSPACE")) {
                typeface = Typeface.MONOSPACE;
            } else {
                typeface = Typeface.SANS_SERIF;
            }
            typefaceCreate = Typeface.create(typeface, i);
        }
        p.setTypeface(typefaceCreate);
        p.setUnderlineText(e.getUnderline());
        List listSplit$default = StringsKt.split$default((CharSequence) value, new String[]{"\n"}, false, 0, 6, (Object) null);
        float fontSize = e.getFontSize() * e.getLineSpacing();
        String textAlign2 = e.getTextAlign();
        if (!Intrinsics.areEqual(textAlign2, "CENTER")) {
            if (Intrinsics.areEqual(textAlign2, "END")) {
                x2 = e.getX();
                width = e.getWidth();
            } else {
                x = e.getX();
            }
            textAlign = e.getTextAlign();
            if (Intrinsics.areEqual(textAlign, "CENTER")) {
                align = Paint.Align.CENTER;
            } else if (Intrinsics.areEqual(textAlign, "END")) {
                align = Paint.Align.RIGHT;
            } else {
                align = Paint.Align.LEFT;
            }
            p.setTextAlign(align);
            for (Object obj : listSplit$default) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                c.drawText((String) obj, x, e.getY() + e.getFontSize() + (i2 * fontSize), p);
                i2 = i3;
            }
            p.setTextAlign(Paint.Align.LEFT);
        }
        x2 = e.getX();
        width = e.getWidth() / 2.0f;
        x = x2 + width;
        textAlign = e.getTextAlign();
        if (Intrinsics.areEqual(textAlign, "CENTER")) {
            align = Paint.Align.CENTER;
        } else if (Intrinsics.areEqual(textAlign, "END")) {
            align = Paint.Align.RIGHT;
        } else {
            align = Paint.Align.LEFT;
        }
        p.setTextAlign(align);
        while (r10.hasNext()) {
            int i4 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            c.drawText((String) obj, x, e.getY() + e.getFontSize() + (i2 * fontSize), p);
            i2 = i4;
        }
        p.setTextAlign(Paint.Align.LEFT);
    }

    private final void drawQr(Canvas c, String value, DesignElementEntity e) {
        try {
            Result.Companion companion = Result.INSTANCE;
            OfficialDocumentRenderer officialDocumentRenderer = this;
            BitMatrix bitMatrixEncode = new MultiFormatWriter().encode(value, BarcodeFormat.QR_CODE, RangesKt.coerceAtLeast((int) e.getWidth(), 64), RangesKt.coerceAtLeast((int) e.getHeight(), 64));
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitMatrixEncode.getWidth(), bitMatrixEncode.getHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
            int width = bitMatrixEncode.getWidth();
            for (int i = 0; i < width; i++) {
                int height = bitMatrixEncode.getHeight();
                for (int i2 = 0; i2 < height; i2++) {
                    bitmapCreateBitmap.setPixel(i, i2, bitMatrixEncode.get(i, i2) ? ViewCompat.MEASURED_STATE_MASK : -1);
                }
            }
            c.drawBitmap(bitmapCreateBitmap, (Rect) null, new RectF(e.getX(), e.getY(), e.getX() + e.getWidth(), e.getY() + e.getHeight()), (Paint) null);
            bitmapCreateBitmap.recycle();
            Result.m7781constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final String resolve(String value, Document d) {
        String strReplace$default = StringsKt.replace$default(value, "{رقم المستند}", d.getDocumentNumber(), false, 4, (Object) null);
        String beneficiaryName = d.getBeneficiaryName();
        String strReplace$default2 = StringsKt.replace$default(strReplace$default, "{اسم المستفيد}", beneficiaryName == null ? "" : beneficiaryName, false, 4, (Object) null);
        Double amount = d.getAmount();
        String string = amount != null ? amount.toString() : null;
        String strReplace$default3 = StringsKt.replace$default(strReplace$default2, "{المبلغ}", string == null ? "" : string, false, 4, (Object) null);
        String purpose = d.getPurpose();
        String strReplace$default4 = StringsKt.replace$default(strReplace$default3, "{الغرض}", purpose == null ? "" : purpose, false, 4, (Object) null);
        String amountWords = d.getAmountWords();
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(strReplace$default4, "{المبلغ كتابة}", amountWords == null ? "" : amountWords, false, 4, (Object) null), "{التاريخ الهجري}", d.getDateHijri(), false, 4, (Object) null), "{التاريخ الميلادي}", d.getDateGregorian(), false, 4, (Object) null), "{البند المالي}", d.getFinancialCategory(), false, 4, (Object) null), "{مركز التكلفة}", d.getCostCenter(), false, 4, (Object) null), "{مصدر التمويل}", d.getFundingSource(), false, 4, (Object) null);
    }

    private final void drawCentered(Canvas c, String text, float x, float y, Paint p) {
        p.setTextAlign(Paint.Align.CENTER);
        c.drawText(text, x, y, p);
    }

    private final void drawRight(Canvas c, String text, float x, float y, Paint p) {
        p.setTextAlign(Paint.Align.RIGHT);
        c.drawText(text, x, y, p);
        p.setTextAlign(Paint.Align.CENTER);
    }

    private final void drawLeft(Canvas c, String text, float x, float y, Paint p) {
        p.setTextAlign(Paint.Align.LEFT);
        c.drawText(text, x, y, p);
        p.setTextAlign(Paint.Align.CENTER);
    }

    private final void drawBoxed(Canvas c, String text, float l, float t, float r, float b) {
        c.drawRoundRect(l, t, r, b, 8.0f, 8.0f, linePaint);
        drawCentered(c, text, (l + r) / 2.0f, 5.0f + ((t + b) / 2.0f), bodyPaint);
    }

    private final void drawParagraph(Canvas c, String text, float x, float y, Paint p) {
        float fCoerceAtLeast = RangesKt.coerceAtLeast(x - 70.0f, 180.0f);
        List<String> listSplit = new Regex("\\s+").split(StringsKt.trim((CharSequence) text).toString(), 0);
        ArrayList<String> arrayList = new ArrayList();
        for (Object obj : listSplit) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        float textSize = y;
        String str = "";
        for (String str2 : arrayList) {
            String str3 = str;
            String str4 = StringsKt.isBlank(str3) ? str2 : str + ServerSentEventKt.SPACE + str2;
            if (StringsKt.isBlank(str3) || p.measureText(str4) <= fCoerceAtLeast) {
                this = this;
                c = c;
                x = x;
                p = p;
                str = str4;
            } else {
                OfficialDocumentRenderer officialDocumentRenderer = this;
                Canvas canvas = c;
                Paint paint = p;
                officialDocumentRenderer.drawRight(canvas, str, x, textSize, paint);
                textSize += paint.getTextSize() * 1.55f;
                str = str2;
                this = officialDocumentRenderer;
                c = canvas;
            }
        }
        OfficialDocumentRenderer officialDocumentRenderer2 = this;
        Canvas canvas2 = c;
        float f = x;
        Paint paint2 = p;
        if (StringsKt.isBlank(str)) {
            return;
        }
        officialDocumentRenderer2.drawRight(canvas2, str, f, textSize, paint2);
    }
}
