package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import com.mohammedalhzmi.masrofmanager.data.Document;
import com.mohammedalhzmi.masrofmanager.data.DocumentDesignEntity;
import com.mohammedalhzmi.masrofmanager.data.DocumentType;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: OfficialDocumentPrintAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ4\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J3\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u001dH\u0016¢\u0006\u0002\u0010\u001eR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/OfficialDocumentPrintAdapter;", "Landroid/print/PrintDocumentAdapter;", "documents", "", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "header", "Lcom/mohammedalhzmi/masrofmanager/util/DocumentHeader;", "context", "Landroid/content/Context;", "<init>", "(Ljava/util/List;Lcom/mohammedalhzmi/masrofmanager/util/DocumentHeader;Landroid/content/Context;)V", "attributes", "Landroid/print/PrintAttributes;", "onLayout", "", "oldAttributes", "newAttributes", "cancellationSignal", "Landroid/os/CancellationSignal;", "callback", "Landroid/print/PrintDocumentAdapter$LayoutResultCallback;", "extras", "Landroid/os/Bundle;", "onWrite", "pages", "", "Landroid/print/PageRange;", "destination", "Landroid/os/ParcelFileDescriptor;", "Landroid/print/PrintDocumentAdapter$WriteResultCallback;", "([Landroid/print/PageRange;Landroid/os/ParcelFileDescriptor;Landroid/os/CancellationSignal;Landroid/print/PrintDocumentAdapter$WriteResultCallback;)V", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class OfficialDocumentPrintAdapter extends PrintDocumentAdapter {
    public static final int $stable = 8;
    private PrintAttributes attributes;
    private final Context context;
    private final List<Document> documents;
    private final DocumentHeader header;

    public OfficialDocumentPrintAdapter(List<Document> documents, DocumentHeader header, Context context) {
        Intrinsics.checkNotNullParameter(documents, "documents");
        Intrinsics.checkNotNullParameter(header, "header");
        this.documents = documents;
        this.header = header;
        this.context = context;
    }

    public /* synthetic */ OfficialDocumentPrintAdapter(List list, DocumentHeader documentHeader, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? new DocumentHeader("وزارة الإدارة والتنمية المحلية والريفية", "صندوق النظافة والتحسين", "فرع المديرية", null, null, null, null, null, null, null, null, null, null, null, null, 32760, null) : documentHeader, (i & 4) != 0 ? null : context);
    }

    @Override // android.print.PrintDocumentAdapter
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, PrintDocumentAdapter.LayoutResultCallback callback, Bundle extras) {
        Intrinsics.checkNotNullParameter(newAttributes, "newAttributes");
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.attributes = newAttributes;
        if (cancellationSignal.isCanceled()) {
            return;
        }
        callback.onLayoutFinished(new PrintDocumentInfo.Builder("masrof-official-documents.pdf").setContentType(0).setPageCount(RangesKt.coerceAtLeast(this.documents.size(), 1)).build(), oldAttributes == null || !Intrinsics.areEqual(oldAttributes, newAttributes));
    }

    @Override // android.print.PrintDocumentAdapter
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, PrintDocumentAdapter.WriteResultCallback callback) {
        int pageWidth;
        Intrinsics.checkNotNullParameter(pages, "pages");
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(cancellationSignal, "cancellationSignal");
        Intrinsics.checkNotNullParameter(callback, "callback");
        PdfDocument pdfDocument = new PdfDocument();
        try {
            try {
                Iterator<T> it = this.documents.iterator();
                int i = 0;
                while (true) {
                    boolean z = true;
                    if (it.hasNext()) {
                        Object next = it.next();
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        Document document = (Document) next;
                        if (!cancellationSignal.isCanceled()) {
                            if (!Intrinsics.areEqual(this.header.getPageSizes().get(document.getType()), "HALF_A4") && (document.getType() != DocumentType.ORDER || this.header.getPageSizes().containsKey(document.getType()))) {
                                z = false;
                            }
                            DocumentDesignEntity documentDesignEntityDesign = this.context != null ? DesignRenderLoader.INSTANCE.design(this.context, document.getType()) : null;
                            boolean zAreEqual = Intrinsics.areEqual(documentDesignEntityDesign != null ? documentDesignEntityDesign.getOrientation() : null, "LANDSCAPE");
                            int pageHeight = 595;
                            if (!z) {
                                if (documentDesignEntityDesign != null) {
                                    pageWidth = (int) documentDesignEntityDesign.getPageWidth();
                                } else {
                                    pageWidth = zAreEqual ? 842 : 595;
                                }
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
                            officialDocumentRenderer.render(canvas, document, this.header, this.context != null ? DesignRenderLoader.INSTANCE.elements(this.context, document.getType()) : CollectionsKt.emptyList(), this.context, documentDesignEntityDesign);
                            pdfDocument.finishPage(pageStartPage);
                            i = i2;
                        } else {
                            pdfDocument.close();
                            return;
                        }
                    } else {
                        FileOutputStream fileOutputStream = new FileOutputStream(destination.getFileDescriptor());
                        try {
                            pdfDocument.writeTo(fileOutputStream);
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileOutputStream, null);
                            callback.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                            pdfDocument.close();
                            return;
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
            } catch (Exception e) {
                callback.onWriteFailed(e.getMessage());
                pdfDocument.close();
            }
        } catch (Throwable th3) {
            pdfDocument.close();
            throw th3;
        }
    }
}
