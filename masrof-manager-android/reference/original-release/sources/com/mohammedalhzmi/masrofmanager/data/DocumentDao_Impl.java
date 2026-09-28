package com.mohammedalhzmi.masrofmanager.data;

import androidx.core.app.NotificationCompat;
import androidx.room.EntityDeleteOrUpdateAdapter;
import androidx.room.EntityInsertAdapter;
import androidx.room.RoomDatabase;
import androidx.room.coroutines.FlowUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import io.ktor.http.LinkHeader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: DocumentDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00170\u0016H\u0016J\u0014\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00170\u0016H\u0016J\u0010\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0016H\u0016J\u001e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u001eJ\u001e\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u001eJ\u000e\u0010\"\u001a\u00020\u0013H\u0096@¢\u0006\u0002\u0010#R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfDocument", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/Document;", "__converters", "Lcom/mohammedalhzmi/masrofmanager/data/Converters;", "__deleteAdapterOfDocument", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfDocument", "insert", "", "document", "(Lcom/mohammedalhzmi/masrofmanager/data/Document;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "", "update", "getAllDocuments", "Lkotlinx/coroutines/flow/Flow;", "", "getArchivedDocuments", "getLastDocumentNumber", "", "archive", "id", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "restore", "archiveOlderThan", "cutoff", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class DocumentDao_Impl implements DocumentDao {
    private final Converters __converters;
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<Document> __deleteAdapterOfDocument;
    private final EntityInsertAdapter<Document> __insertAdapterOfDocument;
    private final EntityDeleteOrUpdateAdapter<Document> __updateAdapterOfDocument;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public DocumentDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__converters = new Converters();
        this.__db = __db;
        this.__insertAdapterOfDocument = new EntityInsertAdapter<Document>() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `documents` (`id`,`type`,`documentNumber`,`dateHijri`,`dateGregorian`,`amount`,`amountWords`,`beneficiaryName`,`purpose`,`details`,`notes`,`status`,`attachmentsCount`,`createdAt`,`isArchived`,`archivedAt`,`updatedAt`,`tags`,`financialCategory`,`costCenter`,`fundingSource`,`beneficiaryId`,`submittedBy`,`reviewedBy`,`approvedBy`,`approvedAt`,`paidAt`,`rejectionReason`,`cloudId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, Document entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, DocumentDao_Impl.this.__converters.fromDocumentType(entity.getType()));
                statement.mo7549bindText(3, entity.getDocumentNumber());
                statement.mo7549bindText(4, entity.getDateHijri());
                statement.mo7549bindText(5, entity.getDateGregorian());
                Double amount = entity.getAmount();
                if (amount == null) {
                    statement.mo7548bindNull(6);
                } else {
                    statement.mo7546bindDouble(6, amount.doubleValue());
                }
                String amountWords = entity.getAmountWords();
                if (amountWords == null) {
                    statement.mo7548bindNull(7);
                } else {
                    statement.mo7549bindText(7, amountWords);
                }
                String beneficiaryName = entity.getBeneficiaryName();
                if (beneficiaryName == null) {
                    statement.mo7548bindNull(8);
                } else {
                    statement.mo7549bindText(8, beneficiaryName);
                }
                String purpose = entity.getPurpose();
                if (purpose == null) {
                    statement.mo7548bindNull(9);
                } else {
                    statement.mo7549bindText(9, purpose);
                }
                String details = entity.getDetails();
                if (details == null) {
                    statement.mo7548bindNull(10);
                } else {
                    statement.mo7549bindText(10, details);
                }
                String notes = entity.getNotes();
                if (notes == null) {
                    statement.mo7548bindNull(11);
                } else {
                    statement.mo7549bindText(11, notes);
                }
                statement.mo7549bindText(12, DocumentDao_Impl.this.__converters.fromDocumentStatus(entity.getStatus()));
                statement.mo7547bindLong(13, entity.getAttachmentsCount());
                statement.mo7547bindLong(14, entity.getCreatedAt());
                statement.mo7547bindLong(15, entity.isArchived() ? 1L : 0L);
                Long archivedAt = entity.getArchivedAt();
                if (archivedAt == null) {
                    statement.mo7548bindNull(16);
                } else {
                    statement.mo7547bindLong(16, archivedAt.longValue());
                }
                statement.mo7547bindLong(17, entity.getUpdatedAt());
                statement.mo7549bindText(18, entity.getTags());
                statement.mo7549bindText(19, entity.getFinancialCategory());
                statement.mo7549bindText(20, entity.getCostCenter());
                statement.mo7549bindText(21, entity.getFundingSource());
                statement.mo7549bindText(22, entity.getBeneficiaryId());
                statement.mo7549bindText(23, entity.getSubmittedBy());
                statement.mo7549bindText(24, entity.getReviewedBy());
                statement.mo7549bindText(25, entity.getApprovedBy());
                Long approvedAt = entity.getApprovedAt();
                if (approvedAt == null) {
                    statement.mo7548bindNull(26);
                } else {
                    statement.mo7547bindLong(26, approvedAt.longValue());
                }
                Long paidAt = entity.getPaidAt();
                if (paidAt == null) {
                    statement.mo7548bindNull(27);
                } else {
                    statement.mo7547bindLong(27, paidAt.longValue());
                }
                statement.mo7549bindText(28, entity.getRejectionReason());
                statement.mo7549bindText(29, entity.getCloudId());
            }
        };
        this.__deleteAdapterOfDocument = new EntityDeleteOrUpdateAdapter<Document>() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl.2
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `documents` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Document entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfDocument = new EntityDeleteOrUpdateAdapter<Document>() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `documents` SET `id` = ?,`type` = ?,`documentNumber` = ?,`dateHijri` = ?,`dateGregorian` = ?,`amount` = ?,`amountWords` = ?,`beneficiaryName` = ?,`purpose` = ?,`details` = ?,`notes` = ?,`status` = ?,`attachmentsCount` = ?,`createdAt` = ?,`isArchived` = ?,`archivedAt` = ?,`updatedAt` = ?,`tags` = ?,`financialCategory` = ?,`costCenter` = ?,`fundingSource` = ?,`beneficiaryId` = ?,`submittedBy` = ?,`reviewedBy` = ?,`approvedBy` = ?,`approvedAt` = ?,`paidAt` = ?,`rejectionReason` = ?,`cloudId` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, Document entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, DocumentDao_Impl.this.__converters.fromDocumentType(entity.getType()));
                statement.mo7549bindText(3, entity.getDocumentNumber());
                statement.mo7549bindText(4, entity.getDateHijri());
                statement.mo7549bindText(5, entity.getDateGregorian());
                Double amount = entity.getAmount();
                if (amount == null) {
                    statement.mo7548bindNull(6);
                } else {
                    statement.mo7546bindDouble(6, amount.doubleValue());
                }
                String amountWords = entity.getAmountWords();
                if (amountWords == null) {
                    statement.mo7548bindNull(7);
                } else {
                    statement.mo7549bindText(7, amountWords);
                }
                String beneficiaryName = entity.getBeneficiaryName();
                if (beneficiaryName == null) {
                    statement.mo7548bindNull(8);
                } else {
                    statement.mo7549bindText(8, beneficiaryName);
                }
                String purpose = entity.getPurpose();
                if (purpose == null) {
                    statement.mo7548bindNull(9);
                } else {
                    statement.mo7549bindText(9, purpose);
                }
                String details = entity.getDetails();
                if (details == null) {
                    statement.mo7548bindNull(10);
                } else {
                    statement.mo7549bindText(10, details);
                }
                String notes = entity.getNotes();
                if (notes == null) {
                    statement.mo7548bindNull(11);
                } else {
                    statement.mo7549bindText(11, notes);
                }
                statement.mo7549bindText(12, DocumentDao_Impl.this.__converters.fromDocumentStatus(entity.getStatus()));
                statement.mo7547bindLong(13, entity.getAttachmentsCount());
                statement.mo7547bindLong(14, entity.getCreatedAt());
                statement.mo7547bindLong(15, entity.isArchived() ? 1L : 0L);
                Long archivedAt = entity.getArchivedAt();
                if (archivedAt == null) {
                    statement.mo7548bindNull(16);
                } else {
                    statement.mo7547bindLong(16, archivedAt.longValue());
                }
                statement.mo7547bindLong(17, entity.getUpdatedAt());
                statement.mo7549bindText(18, entity.getTags());
                statement.mo7549bindText(19, entity.getFinancialCategory());
                statement.mo7549bindText(20, entity.getCostCenter());
                statement.mo7549bindText(21, entity.getFundingSource());
                statement.mo7549bindText(22, entity.getBeneficiaryId());
                statement.mo7549bindText(23, entity.getSubmittedBy());
                statement.mo7549bindText(24, entity.getReviewedBy());
                statement.mo7549bindText(25, entity.getApprovedBy());
                Long approvedAt = entity.getApprovedAt();
                if (approvedAt == null) {
                    statement.mo7548bindNull(26);
                } else {
                    statement.mo7547bindLong(26, approvedAt.longValue());
                }
                Long paidAt = entity.getPaidAt();
                if (paidAt == null) {
                    statement.mo7548bindNull(27);
                } else {
                    statement.mo7547bindLong(27, paidAt.longValue());
                }
                statement.mo7549bindText(28, entity.getRejectionReason());
                statement.mo7549bindText(29, entity.getCloudId());
                statement.mo7547bindLong(30, entity.getId());
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object insert(final Document document, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(DocumentDao_Impl.insert$lambda$0(this.f$0, document, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long insert$lambda$0(DocumentDao_Impl documentDao_Impl, Document document, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        return documentDao_Impl.__insertAdapterOfDocument.insertAndReturnId(_connection, document);
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object delete(final Document document, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.delete$lambda$1(this.f$0, document, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit delete$lambda$1(DocumentDao_Impl documentDao_Impl, Document document, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        documentDao_Impl.__deleteAdapterOfDocument.handle(_connection, document);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object update(final Document document, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.update$lambda$2(this.f$0, document, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit update$lambda$2(DocumentDao_Impl documentDao_Impl, Document document, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        documentDao_Impl.__updateAdapterOfDocument.handle(_connection, document);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Flow<List<Document>> getAllDocuments() {
        final String str = "SELECT * FROM documents WHERE isArchived = 0 ORDER BY createdAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"documents"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.getAllDocuments$lambda$3(str, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getAllDocuments$lambda$3(String str, DocumentDao_Impl documentDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentNumber");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "dateHijri");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "dateGregorian");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "amount");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "amountWords");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "beneficiaryName");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "purpose");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "details");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "notes");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "attachmentsCount");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "isArchived");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "archivedAt");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "tags");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "financialCategory");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "costCenter");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fundingSource");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "beneficiaryId");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "submittedBy");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "reviewedBy");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "approvedBy");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "approvedAt");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "paidAt");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rejectionReason");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cloudId");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                int i = columnIndexOrThrow;
                int i2 = columnIndexOrThrow2;
                DocumentType documentType = documentDao_Impl.__converters.toDocumentType(sQLiteStatementPrepare.getText(columnIndexOrThrow2));
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                String text3 = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                Double dValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : Double.valueOf(sQLiteStatementPrepare.getDouble(columnIndexOrThrow6));
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text6 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text7 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text8 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                DocumentStatus documentStatus = documentDao_Impl.__converters.toDocumentStatus(sQLiteStatementPrepare.getText(columnIndexOrThrow12));
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow14);
                int i4 = columnIndexOrThrow15;
                boolean z = ((int) sQLiteStatementPrepare.getLong(i4)) != 0;
                int i5 = columnIndexOrThrow16;
                Long lValueOf = sQLiteStatementPrepare.isNull(i5) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i5));
                int i6 = columnIndexOrThrow17;
                long j3 = sQLiteStatementPrepare.getLong(i6);
                columnIndexOrThrow15 = i4;
                int i7 = columnIndexOrThrow18;
                String text9 = sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow18 = i7;
                int i8 = columnIndexOrThrow19;
                String text10 = sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow19 = i8;
                int i9 = columnIndexOrThrow20;
                String text11 = sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i10 = columnIndexOrThrow21;
                String text12 = sQLiteStatementPrepare.getText(i10);
                columnIndexOrThrow21 = i10;
                int i11 = columnIndexOrThrow22;
                String text13 = sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow22 = i11;
                int i12 = columnIndexOrThrow23;
                String text14 = sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow23 = i12;
                int i13 = columnIndexOrThrow24;
                String text15 = sQLiteStatementPrepare.getText(i13);
                columnIndexOrThrow24 = i13;
                int i14 = columnIndexOrThrow25;
                String text16 = sQLiteStatementPrepare.getText(i14);
                columnIndexOrThrow25 = i14;
                int i15 = columnIndexOrThrow26;
                Long lValueOf2 = sQLiteStatementPrepare.isNull(i15) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i15));
                columnIndexOrThrow26 = i15;
                int i16 = columnIndexOrThrow27;
                Long lValueOf3 = sQLiteStatementPrepare.isNull(i16) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i16));
                columnIndexOrThrow27 = i16;
                int i17 = columnIndexOrThrow28;
                String text17 = sQLiteStatementPrepare.getText(i17);
                columnIndexOrThrow28 = i17;
                int i18 = columnIndexOrThrow29;
                columnIndexOrThrow29 = i18;
                arrayList.add(new Document(j, documentType, text, text2, text3, dValueOf, text4, text5, text6, text7, text8, documentStatus, i3, j2, z, lValueOf, j3, text9, text10, text11, text12, text13, text14, text15, text16, lValueOf2, lValueOf3, text17, sQLiteStatementPrepare.getText(i18)));
                columnIndexOrThrow16 = i5;
                columnIndexOrThrow17 = i6;
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Flow<List<Document>> getArchivedDocuments() {
        final String str = "SELECT * FROM documents WHERE isArchived = 1 ORDER BY archivedAt DESC, updatedAt DESC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"documents"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.getArchivedDocuments$lambda$4(str, this, (SQLiteConnection) obj);
            }
        });
    }

    static final List getArchivedDocuments$lambda$4(String str, DocumentDao_Impl documentDao_Impl, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentNumber");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "dateHijri");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "dateGregorian");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "amount");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "amountWords");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "beneficiaryName");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "purpose");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "details");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "notes");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "attachmentsCount");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "createdAt");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "isArchived");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "archivedAt");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "tags");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "financialCategory");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "costCenter");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fundingSource");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "beneficiaryId");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "submittedBy");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "reviewedBy");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "approvedBy");
            int columnIndexOrThrow26 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "approvedAt");
            int columnIndexOrThrow27 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "paidAt");
            int columnIndexOrThrow28 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rejectionReason");
            int columnIndexOrThrow29 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cloudId");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                int i = columnIndexOrThrow;
                int i2 = columnIndexOrThrow2;
                DocumentType documentType = documentDao_Impl.__converters.toDocumentType(sQLiteStatementPrepare.getText(columnIndexOrThrow2));
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                String text3 = sQLiteStatementPrepare.getText(columnIndexOrThrow5);
                Double dValueOf = sQLiteStatementPrepare.isNull(columnIndexOrThrow6) ? null : Double.valueOf(sQLiteStatementPrepare.getDouble(columnIndexOrThrow6));
                String text4 = sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7);
                String text5 = sQLiteStatementPrepare.isNull(columnIndexOrThrow8) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow8);
                String text6 = sQLiteStatementPrepare.isNull(columnIndexOrThrow9) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow9);
                String text7 = sQLiteStatementPrepare.isNull(columnIndexOrThrow10) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow10);
                String text8 = sQLiteStatementPrepare.isNull(columnIndexOrThrow11) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow11);
                DocumentStatus documentStatus = documentDao_Impl.__converters.toDocumentStatus(sQLiteStatementPrepare.getText(columnIndexOrThrow12));
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow13);
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow14);
                int i4 = columnIndexOrThrow15;
                boolean z = ((int) sQLiteStatementPrepare.getLong(i4)) != 0;
                int i5 = columnIndexOrThrow16;
                Long lValueOf = sQLiteStatementPrepare.isNull(i5) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i5));
                int i6 = columnIndexOrThrow17;
                long j3 = sQLiteStatementPrepare.getLong(i6);
                columnIndexOrThrow15 = i4;
                int i7 = columnIndexOrThrow18;
                String text9 = sQLiteStatementPrepare.getText(i7);
                columnIndexOrThrow18 = i7;
                int i8 = columnIndexOrThrow19;
                String text10 = sQLiteStatementPrepare.getText(i8);
                columnIndexOrThrow19 = i8;
                int i9 = columnIndexOrThrow20;
                String text11 = sQLiteStatementPrepare.getText(i9);
                columnIndexOrThrow20 = i9;
                int i10 = columnIndexOrThrow21;
                String text12 = sQLiteStatementPrepare.getText(i10);
                columnIndexOrThrow21 = i10;
                int i11 = columnIndexOrThrow22;
                String text13 = sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow22 = i11;
                int i12 = columnIndexOrThrow23;
                String text14 = sQLiteStatementPrepare.getText(i12);
                columnIndexOrThrow23 = i12;
                int i13 = columnIndexOrThrow24;
                String text15 = sQLiteStatementPrepare.getText(i13);
                columnIndexOrThrow24 = i13;
                int i14 = columnIndexOrThrow25;
                String text16 = sQLiteStatementPrepare.getText(i14);
                columnIndexOrThrow25 = i14;
                int i15 = columnIndexOrThrow26;
                Long lValueOf2 = sQLiteStatementPrepare.isNull(i15) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i15));
                columnIndexOrThrow26 = i15;
                int i16 = columnIndexOrThrow27;
                Long lValueOf3 = sQLiteStatementPrepare.isNull(i16) ? null : Long.valueOf(sQLiteStatementPrepare.getLong(i16));
                columnIndexOrThrow27 = i16;
                int i17 = columnIndexOrThrow28;
                String text17 = sQLiteStatementPrepare.getText(i17);
                columnIndexOrThrow28 = i17;
                int i18 = columnIndexOrThrow29;
                columnIndexOrThrow29 = i18;
                arrayList.add(new Document(j, documentType, text, text2, text3, dValueOf, text4, text5, text6, text7, text8, documentStatus, i3, j2, z, lValueOf, j3, text9, text10, text11, text12, text13, text14, text15, text16, lValueOf2, lValueOf3, text17, sQLiteStatementPrepare.getText(i18)));
                columnIndexOrThrow16 = i5;
                columnIndexOrThrow17 = i6;
                columnIndexOrThrow = i;
                columnIndexOrThrow2 = i2;
            }
            return arrayList;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Flow<Integer> getLastDocumentNumber() {
        final String str = "SELECT MAX(CAST(documentNumber AS INTEGER)) FROM documents";
        return FlowUtil.createFlow(this.__db, false, new String[]{"documents"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.getLastDocumentNumber$lambda$5(str, (SQLiteConnection) obj);
            }
        });
    }

    static final Integer getLastDocumentNumber$lambda$5(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            Integer numValueOf = null;
            if (sQLiteStatementPrepare.step() && !sQLiteStatementPrepare.isNull(0)) {
                numValueOf = Integer.valueOf((int) sQLiteStatementPrepare.getLong(0));
            }
            return numValueOf;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object archive(final long j, final long j2, Continuation<? super Unit> continuation) {
        final String str = "UPDATE documents SET isArchived = 1, archivedAt = ?, updatedAt = ? WHERE id = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.archive$lambda$6(str, j2, j, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit archive$lambda$6(String str, long j, long j2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            sQLiteStatementPrepare.mo7547bindLong(2, j);
            sQLiteStatementPrepare.mo7547bindLong(3, j2);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object restore(final long j, final long j2, Continuation<? super Unit> continuation) {
        final String str = "UPDATE documents SET isArchived = 0, archivedAt = NULL, updatedAt = ? WHERE id = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.restore$lambda$7(str, j2, j, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit restore$lambda$7(String str, long j, long j2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            sQLiteStatementPrepare.mo7547bindLong(2, j2);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object archiveOlderThan(final long j, final long j2, Continuation<? super Unit> continuation) {
        final String str = "UPDATE documents SET isArchived = 1, archivedAt = ?, updatedAt = ? WHERE isArchived = 0 AND createdAt < ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.archiveOlderThan$lambda$8(str, j2, j, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit archiveOlderThan$lambda$8(String str, long j, long j2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            sQLiteStatementPrepare.mo7547bindLong(2, j);
            sQLiteStatementPrepare.mo7547bindLong(3, j2);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DocumentDao
    public Object deleteAll(Continuation<? super Unit> continuation) {
        final String str = "DELETE FROM documents";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DocumentDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentDao_Impl.deleteAll$lambda$9(str, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteAll$lambda$9(String str, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: compiled from: DocumentDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DocumentDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<KClass<?>> getRequiredConverters() {
            return CollectionsKt.emptyList();
        }
    }
}
