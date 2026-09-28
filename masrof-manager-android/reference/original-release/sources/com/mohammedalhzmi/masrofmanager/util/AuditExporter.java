package com.mohammedalhzmi.masrofmanager.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import com.mohammedalhzmi.masrofmanager.data.AuditLogEntity;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p012io.CloseableKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AuditExporter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0002J$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007J$\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007J$\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/util/AuditExporter;", "", "<init>", "()V", "dateFormat", "Ljava/text/SimpleDateFormat;", "row", "", "", "kotlin.jvm.PlatformType", "log", "Lcom/mohammedalhzmi/masrofmanager/data/AuditLogEntity;", "exportCsv", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "logs", "exportWord", "exportPdf", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class AuditExporter {
    public static final AuditExporter INSTANCE = new AuditExporter();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static final int $stable = 8;

    private AuditExporter() {
    }

    private final List<String> row(AuditLogEntity log) {
        return CollectionsKt.listOf((Object[]) new String[]{String.valueOf(log.getId()), dateFormat.format(new Date(log.getTimestamp())), log.getUsername(), log.getAction(), log.getDetails()});
    }

    static final CharSequence exportCsv$lambda$2$lambda$1$lambda$0(String str) {
        Intrinsics.checkNotNull(str);
        return "\"" + StringsKt.replace$default(str, "\"", "\"\"", false, 4, (Object) null) + "\"";
    }

    public final void exportCsv(Context context, Uri uri, List<AuditLogEntity> logs) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(logs, "logs");
        StringBuilder sb = new StringBuilder("ID,التاريخ,المستخدم,العملية,التفاصيل\n");
        Iterator<T> it = logs.iterator();
        while (it.hasNext()) {
            sb.append(CollectionsKt.joinToString$default(INSTANCE.row((AuditLogEntity) it.next()), ",", null, null, 0, null, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.util.AuditExporter$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AuditExporter.exportCsv$lambda$2$lambda$1$lambda$0((String) obj);
                }
            }, 30, null)).append('\n');
        }
        String string = sb.toString();
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
        if (outputStreamOpenOutputStream != null) {
            OutputStream outputStream = outputStreamOpenOutputStream;
            try {
                OutputStream outputStream2 = outputStream;
                outputStream2.write(new byte[]{-17, -69, -65});
                byte[] bytes = string.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                outputStream2.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStream, th);
                    throw th2;
                }
            }
        }
    }

    static final CharSequence exportWord$lambda$6$lambda$5$lambda$4(String str) {
        Intrinsics.checkNotNull(str);
        return "<td>" + StringsKt.replace$default(StringsKt.replace$default(str, "&", "&amp;", false, 4, (Object) null), "<", "&lt;", false, 4, (Object) null) + "</td>";
    }

    public final void exportWord(Context context, Uri uri, List<AuditLogEntity> logs) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(logs, "logs");
        StringBuilder sb = new StringBuilder("<html dir=\"rtl\"><head><meta charset=\"UTF-8\"></head><body><h1>سجل العمليات والتدقيق</h1><table border=\"1\" cellspacing=\"0\" cellpadding=\"6\"><tr><th>الرقم</th><th>التاريخ</th><th>المستخدم</th><th>العملية</th><th>التفاصيل</th></tr>");
        Iterator<T> it = logs.iterator();
        while (it.hasNext()) {
            sb.append("<tr>" + CollectionsKt.joinToString$default(INSTANCE.row((AuditLogEntity) it.next()), "", null, null, 0, null, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.util.AuditExporter$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AuditExporter.exportWord$lambda$6$lambda$5$lambda$4((String) obj);
                }
            }, 30, null) + "</tr>");
        }
        sb.append("</table></body></html>");
        String string = sb.toString();
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
        if (outputStreamOpenOutputStream != null) {
            OutputStream outputStream = outputStreamOpenOutputStream;
            try {
                byte[] bytes = string.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                outputStream.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStream, th);
                    throw th2;
                }
            }
        }
    }

    public final void exportPdf(Context context, Uri uri, List<AuditLogEntity> logs) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(logs, "logs");
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.Page pageStartPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder(842, 595, 1).create());
        Paint paint = new Paint(1);
        paint.setTextSize(10.0f);
        pageStartPage.getCanvas().drawText("Audit Log / سجل العمليات", 40.0f, 35.0f, paint);
        float f = 60.0f;
        for (AuditLogEntity auditLogEntity : CollectionsKt.take(logs, 42)) {
            pageStartPage.getCanvas().drawText(auditLogEntity.getId() + " | " + dateFormat.format(new Date(auditLogEntity.getTimestamp())) + " | " + auditLogEntity.getUsername() + " | " + auditLogEntity.getAction() + " | " + auditLogEntity.getDetails(), 40.0f, f, paint);
            f += 12.0f;
        }
        pdfDocument.finishPage(pageStartPage);
        OutputStream outputStreamOpenOutputStream = context.getContentResolver().openOutputStream(uri);
        if (outputStreamOpenOutputStream != null) {
            OutputStream outputStream = outputStreamOpenOutputStream;
            try {
                pdfDocument.writeTo(outputStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(outputStream, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(outputStream, th);
                    throw th2;
                }
            }
        }
        pdfDocument.close();
    }
}
