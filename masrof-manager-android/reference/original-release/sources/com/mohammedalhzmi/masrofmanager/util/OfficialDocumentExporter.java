package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import com.example.C2530R;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import io.ktor.http.LinkHeader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: OfficialDocumentExporter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0016\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\nJ\u0016\u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\nJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012J\u0018\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0018\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J \u0010\u001c\u001a\n \u001e*\u0004\u0018\u00010\u001d0\u001d2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 H\u0002¨\u0006!"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/OfficialDocumentExporter;", "", "<init>", "()V", "exportPdf", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "documents", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "exportPng", "document", "exportDocx", "share", "", "uri", LinkHeader.Parameters.Title, "", "shareUri", "file", "Ljava/io/File;", "docxXml", "d", "hasLogo", "", "header", "Lcom/mohammedalhzmi/masrofmanager/util/DocumentHeader;", "loadBackground", "Landroid/graphics/Bitmap;", "kotlin.jvm.PlatformType", LinkHeader.Parameters.Type, "Lcom/mohammedalhzmi/masrofmanager/data/DocumentType;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class OfficialDocumentExporter {
    public static final int $stable = 0;
    public static final OfficialDocumentExporter INSTANCE = new OfficialDocumentExporter();

    /* JADX INFO: compiled from: OfficialDocumentExporter.kt */
    @Metadata(m915k = 3, m916mv = {2, 2, 0}, m918xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DocumentType.values().length];
            try {
                iArr[DocumentType.ORDER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DocumentType.REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DocumentType.RECEIPT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private OfficialDocumentExporter() {
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0091  */
    public final Uri exportPdf(Context context, List<Document> documents) {
        int pageWidth;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(documents, "documents");
        File file = new File(context.getFilesDir(), "masrof-official-" + System.currentTimeMillis() + ".pdf");
        PdfDocument pdfDocument = new PdfDocument();
        Iterator<T> it = documents.iterator();
        int i = 0;
        while (true) {
            if (it.hasNext()) {
                Object next = it.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Document document = (Document) next;
                DocumentDesignEntity documentDesignEntityDesign = DesignRenderLoader.INSTANCE.design(context, document.getType());
                String strPageSize = AppPreferences.INSTANCE.pageSize(context, document.getType());
                boolean z = Intrinsics.areEqual(strPageSize, "HALF_A4") || (document.getType() == DocumentType.ORDER && StringsKt.isBlank(strPageSize));
                boolean zAreEqual = Intrinsics.areEqual(documentDesignEntityDesign != null ? documentDesignEntityDesign.getOrientation() : null, "LANDSCAPE");
                int pageHeight = 595;
                if (z) {
                    pageWidth = 842;
                } else if (documentDesignEntityDesign != null) {
                    pageWidth = (int) documentDesignEntityDesign.getPageWidth();
                } else if (zAreEqual) {
                    pageWidth = 842;
                } else {
                    pageWidth = 595;
                }
                if (!z) {
                    if (documentDesignEntityDesign != null) {
                        pageHeight = (int) documentDesignEntityDesign.getPageHeight();
                    } else if (!zAreEqual) {
                        pageHeight = 842;
                    }
                }
                PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i2).create());
                OfficialDocumentRenderer officialDocumentRenderer = OfficialDocumentRenderer.INSTANCE;
                Canvas canvas = pageStartPage.getCanvas();
                Intrinsics.checkNotNullExpressionValue(canvas, "getCanvas(...)");
                officialDocumentRenderer.render(canvas, document, INSTANCE.header(context), DesignRenderLoader.INSTANCE.elements(context, document.getType()), context, documentDesignEntityDesign);
                pdfDocument.finishPage(pageStartPage);
                i = i2;
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                try {
                    pdfDocument.writeTo(fileOutputStream);
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    pdfDocument.close();
                    return shareUri(context, file);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(fileOutputStream, th);
                        throw th2;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0071  */
    public final Uri exportPng(Context context, Document document) {
        int pageWidth;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(document, "document");
        File file = new File(context.getFilesDir(), "masrof-" + document.getDocumentNumber() + ".png");
        DocumentDesignEntity documentDesignEntityDesign = DesignRenderLoader.INSTANCE.design(context, document.getType());
        String strPageSize = AppPreferences.INSTANCE.pageSize(context, document.getType());
        boolean z = Intrinsics.areEqual(strPageSize, "HALF_A4") || (document.getType() == DocumentType.ORDER && StringsKt.isBlank(strPageSize));
        boolean zAreEqual = Intrinsics.areEqual(documentDesignEntityDesign != null ? documentDesignEntityDesign.getOrientation() : null, "LANDSCAPE");
        int pageHeight = 595;
        if (z) {
            pageWidth = 842;
        } else if (documentDesignEntityDesign != null) {
            pageWidth = (int) documentDesignEntityDesign.getPageWidth();
        } else if (zAreEqual) {
            pageWidth = 842;
        } else {
            pageWidth = 595;
        }
        if (!z) {
            if (documentDesignEntityDesign != null) {
                pageHeight = (int) documentDesignEntityDesign.getPageHeight();
            } else if (!zAreEqual) {
                pageHeight = 842;
            }
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(pageWidth * 2, pageHeight * 2, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.scale(2.0f, 2.0f);
        OfficialDocumentRenderer.INSTANCE.render(canvas, document, header(context), DesignRenderLoader.INSTANCE.elements(context, document.getType()), context, documentDesignEntityDesign);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            bitmapCreateBitmap.recycle();
            return shareUri(context, file);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(fileOutputStream, th);
                throw th2;
            }
        }
    }

    public final Uri exportDocx(Context context, Document document) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(document, "document");
        File file = new File(context.getFilesDir(), "masrof-" + document.getDocumentNumber() + ".docx");
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file));
        try {
            ZipOutputStream zipOutputStream2 = zipOutputStream;
            Bitmap bitmapLoadLogo = AppPreferences.INSTANCE.loadLogo(context, document.getType());
            if (bitmapLoadLogo == null) {
                bitmapLoadLogo = BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.official_emblem);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapLoadLogo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            exportDocx$lambda$6$entry(zipOutputStream2, "[Content_Types].xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\"><Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/><Default Extension=\"xml\" ContentType=\"application/xml\"/><Default Extension=\"png\" ContentType=\"image/png\"/><Override PartName=\"/word/document.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml\"/></Types>");
            exportDocx$lambda$6$entry(zipOutputStream2, "_rels/.rels", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"word/document.xml\"/></Relationships>");
            exportDocx$lambda$6$entry(zipOutputStream2, "word/_rels/document.xml.rels", byteArray != null ? "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId2\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/image\" Target=\"media/logo.png\"/></Relationships>" : "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"/>");
            if (byteArray != null) {
                exportDocx$lambda$6$bytesEntry(zipOutputStream2, "word/media/logo.png", byteArray);
            }
            exportDocx$lambda$6$entry(zipOutputStream2, "word/document.xml", INSTANCE.docxXml(document, byteArray != null));
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(zipOutputStream, null);
            return shareUri(context, file);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(zipOutputStream, th);
                throw th2;
            }
        }
    }

    private static final void exportDocx$lambda$6$entry(ZipOutputStream zipOutputStream, String str, String str2) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(str));
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        zipOutputStream.write(bytes);
        zipOutputStream.closeEntry();
    }

    private static final void exportDocx$lambda$6$bytesEntry(ZipOutputStream zipOutputStream, String str, byte[] bArr) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(str));
        zipOutputStream.write(bArr);
        zipOutputStream.closeEntry();
    }

    public final void share(Context context, Uri uri, String title) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(title, "title");
        Intent intent = new Intent("android.intent.action.SEND");
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        if (StringsKt.endsWith$default(string, ".png", false, 2, (Object) null)) {
            str = "image/png";
        } else {
            String string2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "toString(...)");
            str = StringsKt.endsWith$default(string2, ".docx", false, 2, (Object) null) ? "application/vnd.openxmlformats-officedocument.wordprocessingml.document" : "application/pdf";
        }
        intent.setType(str);
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.addFlags(1);
        context.startActivity(Intent.createChooser(intent, title));
    }

    private final Uri shareUri(Context context, File file) {
        Uri uriForFile = FileProvider.getUriForFile(context, context.getPackageName() + ".files", file);
        Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(...)");
        return uriForFile;
    }

    private static final String docxXml$esc(String str) {
        return StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(str, "&", "&amp;", false, 4, (Object) null), "<", "&lt;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null), "\"", "&quot;", false, 4, (Object) null), "'", "&apos;", false, 4, (Object) null);
    }

    private static final String docxXml$p(String str, String str2) {
        return "<w:p><w:pPr><w:jc w:val=\"right\"/></w:pPr><w:r><w:rPr><w:rFonts w:ascii=\"Amiri\" w:hAnsi=\"Amiri\"/></w:rPr><w:t>" + docxXml$esc(str) + docxXml$esc(str2) + "</w:t></w:r></w:p>";
    }

    private final String docxXml(Document d, boolean hasLogo) {
        String strName;
        String str;
        int i = WhenMappings.$EnumSwitchMapping$0[d.getType().ordinal()];
        if (i == 1) {
            strName = "أمر صرف";
        } else if (i != 2) {
            strName = i != 3 ? d.getType().name() : "ورقة استلام";
        } else {
            strName = "ورقة تقديم طلب";
        }
        String str2 = hasLogo ? "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:drawing><wp:inline xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\" xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><wp:extent cx=\"900000\" cy=\"900000\"/><wp:docPr id=\"1\" name=\"Official logo\"/><a:graphic><a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\"><pic:pic><pic:blipFill><a:blip r:embed=\"rId2\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/><a:stretch><a:fillRect/></a:stretch></pic:blipFill><pic:spPr><a:prstGeom prst=\"rect\"><a:avLst/></a:prstGeom></pic:spPr></pic:pic></a:graphicData></a:graphic></wp:inline></w:drawing></w:r></w:p>" : "";
        if (d.getType() == DocumentType.REQUEST) {
            String beneficiaryName = d.getBeneficiaryName();
            if (beneficiaryName == null) {
                beneficiaryName = "";
            }
            str = docxXml$p("مقدم الطلب: ", beneficiaryName) + docxXml$p("التوقيع: ", "................................");
        } else {
            str = docxXml$p("مدير الفرع: ", "رياض أحمد محمد") + docxXml$p("المدير المالي للفرع: ", "................................");
        }
        String strDocxXml$esc = docxXml$esc(strName);
        String strDocxXml$p = docxXml$p("الرقم: ", d.getDocumentNumber());
        String strDocxXml$p2 = docxXml$p("التاريخ: ", d.getDateHijri());
        String strDocxXml$p3 = docxXml$p("الموافق: ", d.getDateGregorian());
        String strDocxXml$p4 = docxXml$p("المرفقات: ", String.valueOf(d.getAttachmentsCount()));
        String strDocxXml$p5 = docxXml$p("الحالة: ", d.getStatus().name());
        String strDocxXml$p6 = docxXml$p("البند المالي: ", d.getFinancialCategory());
        String strDocxXml$p7 = docxXml$p("مركز التكلفة: ", d.getCostCenter());
        String strDocxXml$p8 = docxXml$p("مصدر التمويل: ", d.getFundingSource());
        String beneficiaryName2 = d.getBeneficiaryName();
        if (beneficiaryName2 == null) {
            beneficiaryName2 = "";
        }
        String strDocxXml$p9 = docxXml$p("اسم المستفيد: ", beneficiaryName2);
        Double amount = d.getAmount();
        String string = amount != null ? amount.toString() : null;
        if (string == null) {
            string = "";
        }
        String strDocxXml$p10 = docxXml$p("المبلغ: ", string);
        String amountWords = d.getAmountWords();
        if (amountWords == null) {
            amountWords = "";
        }
        String strDocxXml$p11 = docxXml$p("المبلغ كتابة: ", amountWords);
        String purpose = d.getPurpose();
        if (purpose == null) {
            purpose = "";
        }
        String strDocxXml$p12 = docxXml$p("الغرض: ", purpose);
        String details = d.getDetails();
        if (details == null) {
            details = "";
        }
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><w:document xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"><w:body>" + str2 + "<w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:rPr><w:b/><w:rFonts w:ascii=\"Amiri\" w:hAnsi=\"Amiri\"/></w:rPr><w:t>الجمهورية اليمنية - وزارة الإدارة والتنمية المحلية والريفية - صندوق النظافة والتحسين م/إب - فرع مديرية الحزم</w:t></w:r></w:p><w:p><w:pPr><w:jc w:val=\"center\"/></w:pPr><w:r><w:rPr><w:b/></w:rPr><w:t>" + strDocxXml$esc + "</w:t></w:r></w:p>" + strDocxXml$p + strDocxXml$p2 + strDocxXml$p3 + strDocxXml$p4 + strDocxXml$p5 + strDocxXml$p6 + strDocxXml$p7 + strDocxXml$p8 + strDocxXml$p9 + strDocxXml$p10 + strDocxXml$p11 + strDocxXml$p12 + docxXml$p("التفاصيل: ", details) + str + docxXml$p("ملاحظة: ", "طبع بواسطة نظام مالية فرع صندوق النظافة والتحسين مديرية الحزم") + "<w:sectPr><w:pgSz w:w=\"11906\" w:h=\"16838\"/><w:pgMar w:top=\"720\" w:right=\"720\" w:bottom=\"720\" w:left=\"720\"/></w:sectPr></w:body></w:document>";
    }

    private final DocumentHeader header(Context context) {
        Object objM7781constructorimpl;
        Object objM7781constructorimpl2;
        Object objM7781constructorimpl3;
        Object objM7781constructorimpl4;
        int i;
        Object objM7781constructorimpl5;
        Object objM7781constructorimpl6;
        String strMinistry = AppPreferences.INSTANCE.ministry(context);
        String strAdministration = AppPreferences.INSTANCE.administration(context);
        String strBranch = AppPreferences.INSTANCE.branch(context);
        Map mapMapOf = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.loadLogo(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.loadLogo(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.loadLogo(context, DocumentType.RECEIPT)));
        Map mapMapOf2 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.pageSize(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.pageSize(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.pageSize(context, DocumentType.RECEIPT)));
        Pair[] pairArr = new Pair[3];
        DocumentType documentType = DocumentType.ORDER;
        try {
            Result.Companion companion = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter = this;
            objM7781constructorimpl = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.ORDER))));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl)) {
            objM7781constructorimpl = -1;
        }
        pairArr[0] = TuplesKt.m921to(documentType, objM7781constructorimpl);
        DocumentType documentType2 = DocumentType.REQUEST;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter2 = this;
            objM7781constructorimpl2 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.REQUEST))));
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            objM7781constructorimpl2 = Result.m7781constructorimpl(ResultKt.createFailure(th2));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl2)) {
            objM7781constructorimpl2 = -1;
        }
        pairArr[1] = TuplesKt.m921to(documentType2, objM7781constructorimpl2);
        DocumentType documentType3 = DocumentType.RECEIPT;
        try {
            Result.Companion companion5 = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter3 = this;
            objM7781constructorimpl3 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.backgroundColor(context, DocumentType.RECEIPT))));
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            objM7781constructorimpl3 = Result.m7781constructorimpl(ResultKt.createFailure(th3));
        }
        if (Result.m7787isFailureimpl(objM7781constructorimpl3)) {
            objM7781constructorimpl3 = -1;
        }
        pairArr[2] = TuplesKt.m921to(documentType3, objM7781constructorimpl3);
        Map mapMapOf3 = MapsKt.mapOf(pairArr);
        Map mapMapOf4 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, loadBackground(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, loadBackground(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, loadBackground(context, DocumentType.RECEIPT)));
        Map mapMapOf5 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.ORDER))), TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.REQUEST))), TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundOpacity(context, DocumentType.RECEIPT))));
        Map mapMapOf6 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.ORDER))), TuplesKt.m921to(DocumentType.REQUEST, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.REQUEST))), TuplesKt.m921to(DocumentType.RECEIPT, Float.valueOf(AppPreferences.INSTANCE.backgroundScale(context, DocumentType.RECEIPT))));
        Map mapMapOf7 = MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.ORDER)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.ORDER)))), TuplesKt.m921to(DocumentType.REQUEST, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.REQUEST)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.REQUEST)))), TuplesKt.m921to(DocumentType.RECEIPT, TuplesKt.m921to(Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetX(context, DocumentType.RECEIPT)), Float.valueOf(AppPreferences.INSTANCE.backgroundOffsetY(context, DocumentType.RECEIPT)))));
        Pair[] pairArr2 = new Pair[3];
        DocumentType documentType4 = DocumentType.ORDER;
        try {
            Result.Companion companion7 = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter4 = this;
            objM7781constructorimpl4 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.ORDER))));
        } catch (Throwable th4) {
            Result.Companion companion8 = Result.INSTANCE;
            objM7781constructorimpl4 = Result.m7781constructorimpl(ResultKt.createFailure(th4));
        }
        Integer numValueOf = Integer.valueOf(ViewCompat.MEASURED_STATE_MASK);
        if (Result.m7787isFailureimpl(objM7781constructorimpl4)) {
            objM7781constructorimpl4 = numValueOf;
        }
        pairArr2[0] = TuplesKt.m921to(documentType4, objM7781constructorimpl4);
        DocumentType documentType5 = DocumentType.REQUEST;
        try {
            Result.Companion companion9 = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter5 = this;
            i = -16777216;
            try {
                objM7781constructorimpl5 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.REQUEST))));
            } catch (Throwable th5) {
                th = th5;
                Result.Companion companion10 = Result.INSTANCE;
                objM7781constructorimpl5 = Result.m7781constructorimpl(ResultKt.createFailure(th));
            }
        } catch (Throwable th6) {
            th = th6;
            i = -16777216;
        }
        Integer numValueOf2 = Integer.valueOf(i);
        if (Result.m7787isFailureimpl(objM7781constructorimpl5)) {
            objM7781constructorimpl5 = numValueOf2;
        }
        pairArr2[1] = TuplesKt.m921to(documentType5, objM7781constructorimpl5);
        DocumentType documentType6 = DocumentType.RECEIPT;
        try {
            Result.Companion companion11 = Result.INSTANCE;
            OfficialDocumentExporter officialDocumentExporter6 = this;
            objM7781constructorimpl6 = Result.m7781constructorimpl(Integer.valueOf(Color.parseColor(AppPreferences.INSTANCE.textColor(context, DocumentType.RECEIPT))));
        } catch (Throwable th7) {
            Result.Companion companion12 = Result.INSTANCE;
            objM7781constructorimpl6 = Result.m7781constructorimpl(ResultKt.createFailure(th7));
        }
        Integer numValueOf3 = Integer.valueOf(i);
        if (Result.m7787isFailureimpl(objM7781constructorimpl6)) {
            objM7781constructorimpl6 = numValueOf3;
        }
        pairArr2[2] = TuplesKt.m921to(documentType6, objM7781constructorimpl6);
        return new DocumentHeader(strMinistry, strAdministration, strBranch, mapMapOf, mapMapOf2, mapMapOf3, mapMapOf4, mapMapOf5, mapMapOf6, mapMapOf7, MapsKt.mapOf(pairArr2), MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, AppPreferences.INSTANCE.fontFamily(context, DocumentType.ORDER)), TuplesKt.m921to(DocumentType.REQUEST, AppPreferences.INSTANCE.fontFamily(context, DocumentType.REQUEST)), TuplesKt.m921to(DocumentType.RECEIPT, AppPreferences.INSTANCE.fontFamily(context, DocumentType.RECEIPT))), MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.ORDER))), TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.REQUEST))), TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textBold(context, DocumentType.RECEIPT)))), MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.ORDER))), TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.REQUEST))), TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textItalic(context, DocumentType.RECEIPT)))), MapsKt.mapOf(TuplesKt.m921to(DocumentType.ORDER, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.ORDER))), TuplesKt.m921to(DocumentType.REQUEST, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.REQUEST))), TuplesKt.m921to(DocumentType.RECEIPT, Boolean.valueOf(AppPreferences.INSTANCE.textUnderline(context, DocumentType.RECEIPT)))));
    }

    private final Bitmap loadBackground(Context context, DocumentType type) {
        Object objM7781constructorimpl;
        String strBackgroundImageUri = AppPreferences.INSTANCE.backgroundImageUri(context, type);
        if (strBackgroundImageUri != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(Uri.parse(strBackgroundImageUri));
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                    CloseableKt.closeFinally(inputStreamOpenInputStream, null);
                    objM7781constructorimpl = Result.m7781constructorimpl(bitmapDecodeStream);
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        CloseableKt.closeFinally(inputStreamOpenInputStream, th);
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                Result.Companion companion2 = Result.INSTANCE;
                objM7781constructorimpl = Result.m7781constructorimpl(ResultKt.createFailure(th3));
            }
            Bitmap bitmap = (Bitmap) (Result.m7787isFailureimpl(objM7781constructorimpl) ? null : objM7781constructorimpl);
            if (bitmap != null) {
                return bitmap;
            }
        }
        return BitmapFactory.decodeResource(context.getResources(), C2530R.drawable.cleaning_fund_watermark);
    }
}
