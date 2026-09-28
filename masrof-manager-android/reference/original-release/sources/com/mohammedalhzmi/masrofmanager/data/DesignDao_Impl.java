package com.mohammedalhzmi.masrofmanager.data;

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

/* JADX INFO: compiled from: DesignDao_Impl.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(m913d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0096@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u0014J\u0016\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\nH\u0096@¢\u0006\u0002\u0010\u0014J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001d2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u001f0\u001d2\u0006\u0010 \u001a\u00020\u000fH\u0016J\u001c\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\u0006\u0010 \u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0096@¢\u0006\u0002\u0010\u001bJ\u0016\u0010$\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\"J\u001e\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020(H\u0096@¢\u0006\u0002\u0010)R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DesignDao_Impl;", "Lcom/mohammedalhzmi/masrofmanager/data/DesignDao;", "__db", "Landroidx/room/RoomDatabase;", "<init>", "(Landroidx/room/RoomDatabase;)V", "__insertAdapterOfDocumentDesignEntity", "Landroidx/room/EntityInsertAdapter;", "Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;", "__insertAdapterOfDesignElementEntity", "Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;", "__deleteAdapterOfDesignElementEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "__updateAdapterOfDesignElementEntity", "upsertDesign", "", "design", "(Lcom/mohammedalhzmi/masrofmanager/data/DocumentDesignEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertElement", "element", "(Lcom/mohammedalhzmi/masrofmanager/data/DesignElementEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteElement", "", "updateElement", "getDesign", LinkHeader.Parameters.Type, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "observeDesign", "Lkotlinx/coroutines/flow/Flow;", "observeElements", "", "designId", "getElements", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteDesign", "deleteElements", "setLayer", "elementId", "zIndex", "", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
public final class DesignDao_Impl implements DesignDao {
    private final RoomDatabase __db;
    private final EntityDeleteOrUpdateAdapter<DesignElementEntity> __deleteAdapterOfDesignElementEntity;
    private final EntityInsertAdapter<DesignElementEntity> __insertAdapterOfDesignElementEntity;
    private final EntityInsertAdapter<DocumentDesignEntity> __insertAdapterOfDocumentDesignEntity;
    private final EntityDeleteOrUpdateAdapter<DesignElementEntity> __updateAdapterOfDesignElementEntity;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public DesignDao_Impl(RoomDatabase __db) {
        Intrinsics.checkNotNullParameter(__db, "__db");
        this.__db = __db;
        this.__insertAdapterOfDocumentDesignEntity = new EntityInsertAdapter<DocumentDesignEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl.1
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `document_designs` (`id`,`documentType`,`name`,`pageWidth`,`pageHeight`,`backgroundColor`,`backgroundImageUri`,`updatedAt`,`orientation`,`marginLeft`,`marginTop`,`marginRight`,`marginBottom`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, DocumentDesignEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7549bindText(2, entity.getDocumentType());
                statement.mo7549bindText(3, entity.getName());
                statement.mo7546bindDouble(4, entity.getPageWidth());
                statement.mo7546bindDouble(5, entity.getPageHeight());
                statement.mo7549bindText(6, entity.getBackgroundColor());
                String backgroundImageUri = entity.getBackgroundImageUri();
                if (backgroundImageUri == null) {
                    statement.mo7548bindNull(7);
                } else {
                    statement.mo7549bindText(7, backgroundImageUri);
                }
                statement.mo7547bindLong(8, entity.getUpdatedAt());
                statement.mo7549bindText(9, entity.getOrientation());
                statement.mo7546bindDouble(10, entity.getMarginLeft());
                statement.mo7546bindDouble(11, entity.getMarginTop());
                statement.mo7546bindDouble(12, entity.getMarginRight());
                statement.mo7546bindDouble(13, entity.getMarginBottom());
            }
        };
        this.__insertAdapterOfDesignElementEntity = new EntityInsertAdapter<DesignElementEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl.2
            @Override // androidx.room.EntityInsertAdapter
            protected String createQuery() {
                return "INSERT OR REPLACE INTO `design_elements` (`id`,`designId`,`type`,`content`,`x`,`y`,`width`,`height`,`rotation`,`opacity`,`zIndex`,`fontFamily`,`fontSize`,`textColor`,`bold`,`italic`,`underline`,`fillColor`,`strokeColor`,`strokeWidth`,`locked`,`visible`,`textAlign`,`lineSpacing`,`cornerRadius`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityInsertAdapter
            public void bind(SQLiteStatement statement, DesignElementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7547bindLong(2, entity.getDesignId());
                statement.mo7549bindText(3, entity.getType());
                statement.mo7549bindText(4, entity.getContent());
                statement.mo7546bindDouble(5, entity.getX());
                statement.mo7546bindDouble(6, entity.getY());
                statement.mo7546bindDouble(7, entity.getWidth());
                statement.mo7546bindDouble(8, entity.getHeight());
                statement.mo7546bindDouble(9, entity.getRotation());
                statement.mo7546bindDouble(10, entity.getOpacity());
                statement.mo7547bindLong(11, entity.getZIndex());
                statement.mo7549bindText(12, entity.getFontFamily());
                statement.mo7546bindDouble(13, entity.getFontSize());
                statement.mo7549bindText(14, entity.getTextColor());
                statement.mo7547bindLong(15, entity.getBold() ? 1L : 0L);
                statement.mo7547bindLong(16, entity.getItalic() ? 1L : 0L);
                statement.mo7547bindLong(17, entity.getUnderline() ? 1L : 0L);
                statement.mo7549bindText(18, entity.getFillColor());
                statement.mo7549bindText(19, entity.getStrokeColor());
                statement.mo7546bindDouble(20, entity.getStrokeWidth());
                statement.mo7547bindLong(21, entity.getLocked() ? 1L : 0L);
                statement.mo7547bindLong(22, entity.getVisible() ? 1L : 0L);
                statement.mo7549bindText(23, entity.getTextAlign());
                statement.mo7546bindDouble(24, entity.getLineSpacing());
                statement.mo7546bindDouble(25, entity.getCornerRadius());
            }
        };
        this.__deleteAdapterOfDesignElementEntity = new EntityDeleteOrUpdateAdapter<DesignElementEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl.3
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "DELETE FROM `design_elements` WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, DesignElementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
            }
        };
        this.__updateAdapterOfDesignElementEntity = new EntityDeleteOrUpdateAdapter<DesignElementEntity>() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl.4
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            protected String createQuery() {
                return "UPDATE OR ABORT `design_elements` SET `id` = ?,`designId` = ?,`type` = ?,`content` = ?,`x` = ?,`y` = ?,`width` = ?,`height` = ?,`rotation` = ?,`opacity` = ?,`zIndex` = ?,`fontFamily` = ?,`fontSize` = ?,`textColor` = ?,`bold` = ?,`italic` = ?,`underline` = ?,`fillColor` = ?,`strokeColor` = ?,`strokeWidth` = ?,`locked` = ?,`visible` = ?,`textAlign` = ?,`lineSpacing` = ?,`cornerRadius` = ? WHERE `id` = ?";
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.room.EntityDeleteOrUpdateAdapter
            public void bind(SQLiteStatement statement, DesignElementEntity entity) {
                Intrinsics.checkNotNullParameter(statement, "statement");
                Intrinsics.checkNotNullParameter(entity, "entity");
                statement.mo7547bindLong(1, entity.getId());
                statement.mo7547bindLong(2, entity.getDesignId());
                statement.mo7549bindText(3, entity.getType());
                statement.mo7549bindText(4, entity.getContent());
                statement.mo7546bindDouble(5, entity.getX());
                statement.mo7546bindDouble(6, entity.getY());
                statement.mo7546bindDouble(7, entity.getWidth());
                statement.mo7546bindDouble(8, entity.getHeight());
                statement.mo7546bindDouble(9, entity.getRotation());
                statement.mo7546bindDouble(10, entity.getOpacity());
                statement.mo7547bindLong(11, entity.getZIndex());
                statement.mo7549bindText(12, entity.getFontFamily());
                statement.mo7546bindDouble(13, entity.getFontSize());
                statement.mo7549bindText(14, entity.getTextColor());
                statement.mo7547bindLong(15, entity.getBold() ? 1L : 0L);
                statement.mo7547bindLong(16, entity.getItalic() ? 1L : 0L);
                statement.mo7547bindLong(17, entity.getUnderline() ? 1L : 0L);
                statement.mo7549bindText(18, entity.getFillColor());
                statement.mo7549bindText(19, entity.getStrokeColor());
                statement.mo7546bindDouble(20, entity.getStrokeWidth());
                statement.mo7547bindLong(21, entity.getLocked() ? 1L : 0L);
                statement.mo7547bindLong(22, entity.getVisible() ? 1L : 0L);
                statement.mo7549bindText(23, entity.getTextAlign());
                statement.mo7546bindDouble(24, entity.getLineSpacing());
                statement.mo7546bindDouble(25, entity.getCornerRadius());
                statement.mo7547bindLong(26, entity.getId());
            }
        };
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object upsertDesign(final DocumentDesignEntity documentDesignEntity, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(DesignDao_Impl.upsertDesign$lambda$0(this.f$0, documentDesignEntity, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long upsertDesign$lambda$0(DesignDao_Impl designDao_Impl, DocumentDesignEntity documentDesignEntity, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        return designDao_Impl.__insertAdapterOfDocumentDesignEntity.insertAndReturnId(_connection, documentDesignEntity);
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object insertElement(final DesignElementEntity designElementEntity, Continuation<? super Long> continuation) {
        return DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Long.valueOf(DesignDao_Impl.insertElement$lambda$1(this.f$0, designElementEntity, (SQLiteConnection) obj));
            }
        }, continuation);
    }

    static final long insertElement$lambda$1(DesignDao_Impl designDao_Impl, DesignElementEntity designElementEntity, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        return designDao_Impl.__insertAdapterOfDesignElementEntity.insertAndReturnId(_connection, designElementEntity);
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object deleteElement(final DesignElementEntity designElementEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.deleteElement$lambda$2(this.f$0, designElementEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteElement$lambda$2(DesignDao_Impl designDao_Impl, DesignElementEntity designElementEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        designDao_Impl.__deleteAdapterOfDesignElementEntity.handle(_connection, designElementEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object updateElement(final DesignElementEntity designElementEntity, Continuation<? super Unit> continuation) {
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.updateElement$lambda$3(this.f$0, designElementEntity, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit updateElement$lambda$3(DesignDao_Impl designDao_Impl, DesignElementEntity designElementEntity, SQLiteConnection _connection) throws Exception {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        designDao_Impl.__updateAdapterOfDesignElementEntity.handle(_connection, designElementEntity);
        return Unit.INSTANCE;
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object getDesign(final String str, Continuation<? super DocumentDesignEntity> continuation) {
        final String str2 = "SELECT * FROM document_designs WHERE documentType = ? LIMIT 1";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.getDesign$lambda$4(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final DocumentDesignEntity getDesign$lambda$4(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7549bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentType");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "name");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pageWidth");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pageHeight");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "backgroundColor");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "backgroundImageUri");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "orientation");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginLeft");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginTop");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginRight");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginBottom");
            DocumentDesignEntity documentDesignEntity = null;
            if (sQLiteStatementPrepare.step()) {
                documentDesignEntity = new DocumentDesignEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow4), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow5), sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getLong(columnIndexOrThrow8), sQLiteStatementPrepare.getText(columnIndexOrThrow9), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow10), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow11), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow12), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow13));
            }
            return documentDesignEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Flow<DocumentDesignEntity> observeDesign(final String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        final String str = "SELECT * FROM document_designs WHERE documentType = ? LIMIT 1";
        return FlowUtil.createFlow(this.__db, false, new String[]{"document_designs"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.observeDesign$lambda$5(str, type, (SQLiteConnection) obj);
            }
        });
    }

    static final DocumentDesignEntity observeDesign$lambda$5(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7549bindText(1, str2);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "documentType");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "name");
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pageWidth");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "pageHeight");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "backgroundColor");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "backgroundImageUri");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "updatedAt");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "orientation");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginLeft");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginTop");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginRight");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "marginBottom");
            DocumentDesignEntity documentDesignEntity = null;
            if (sQLiteStatementPrepare.step()) {
                documentDesignEntity = new DocumentDesignEntity(sQLiteStatementPrepare.getLong(columnIndexOrThrow), sQLiteStatementPrepare.getText(columnIndexOrThrow2), sQLiteStatementPrepare.getText(columnIndexOrThrow3), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow4), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow5), sQLiteStatementPrepare.getText(columnIndexOrThrow6), sQLiteStatementPrepare.isNull(columnIndexOrThrow7) ? null : sQLiteStatementPrepare.getText(columnIndexOrThrow7), sQLiteStatementPrepare.getLong(columnIndexOrThrow8), sQLiteStatementPrepare.getText(columnIndexOrThrow9), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow10), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow11), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow12), (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow13));
            }
            return documentDesignEntity;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Flow<List<DesignElementEntity>> observeElements(final long designId) {
        final String str = "SELECT * FROM design_elements WHERE designId = ? ORDER BY zIndex ASC, id ASC";
        return FlowUtil.createFlow(this.__db, false, new String[]{"design_elements"}, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.observeElements$lambda$6(str, designId, (SQLiteConnection) obj);
            }
        });
    }

    static final List observeElements$lambda$6(String str, long j, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "designId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "content");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "x");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "y");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "width");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "height");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rotation");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "opacity");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "zIndex");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fontFamily");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fontSize");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "textColor");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "bold");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "italic");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "underline");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fillColor");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "strokeColor");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "strokeWidth");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "locked");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "visible");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "textAlign");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lineSpacing");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cornerRadius");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i = columnIndexOrThrow;
                int i2 = columnIndexOrThrow2;
                float f = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow5);
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow6);
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                float f4 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                float f5 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow9);
                float f6 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow10);
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                String text3 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                float f7 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow13);
                String text4 = sQLiteStatementPrepare.getText(columnIndexOrThrow14);
                int i4 = columnIndexOrThrow15;
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                boolean z = ((int) sQLiteStatementPrepare.getLong(i4)) != 0;
                int i7 = columnIndexOrThrow16;
                int i8 = columnIndexOrThrow5;
                boolean z2 = ((int) sQLiteStatementPrepare.getLong(i7)) != 0;
                int i9 = columnIndexOrThrow17;
                boolean z3 = ((int) sQLiteStatementPrepare.getLong(i9)) != 0;
                int i10 = columnIndexOrThrow18;
                String text5 = sQLiteStatementPrepare.getText(i10);
                int i11 = columnIndexOrThrow19;
                String text6 = sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow18 = i10;
                int i12 = columnIndexOrThrow20;
                float f8 = (float) sQLiteStatementPrepare.getDouble(i12);
                int i13 = columnIndexOrThrow21;
                boolean z4 = ((int) sQLiteStatementPrepare.getLong(i13)) != 0;
                int i14 = columnIndexOrThrow22;
                int i15 = columnIndexOrThrow23;
                int i16 = columnIndexOrThrow24;
                columnIndexOrThrow17 = i9;
                int i17 = columnIndexOrThrow25;
                arrayList.add(new DesignElementEntity(j2, j3, text, text2, f, f2, f3, f4, f5, f6, i3, text3, f7, text4, z, z2, z3, text5, text6, f8, z4, ((int) sQLiteStatementPrepare.getLong(i14)) != 0, sQLiteStatementPrepare.getText(i15), (float) sQLiteStatementPrepare.getDouble(i16), (float) sQLiteStatementPrepare.getDouble(i17)));
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow15 = i4;
                columnIndexOrThrow = i;
                columnIndexOrThrow4 = i6;
                columnIndexOrThrow5 = i8;
                columnIndexOrThrow16 = i7;
                columnIndexOrThrow19 = i11;
                columnIndexOrThrow20 = i12;
                columnIndexOrThrow21 = i13;
                columnIndexOrThrow22 = i14;
                columnIndexOrThrow23 = i15;
                columnIndexOrThrow24 = i16;
                columnIndexOrThrow25 = i17;
                columnIndexOrThrow2 = i2;
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object getElements(final long j, Continuation<? super List<DesignElementEntity>> continuation) {
        final String str = "SELECT * FROM design_elements WHERE designId = ? ORDER BY zIndex ASC, id ASC";
        return DBUtil.performSuspending(this.__db, true, false, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.getElements$lambda$7(str, j, (SQLiteConnection) obj);
            }
        }, continuation);
    }

    static final List getElements$lambda$7(String str, long j, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "id");
            int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "designId");
            int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, LinkHeader.Parameters.Type);
            int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "content");
            int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "x");
            int columnIndexOrThrow6 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "y");
            int columnIndexOrThrow7 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "width");
            int columnIndexOrThrow8 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "height");
            int columnIndexOrThrow9 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "rotation");
            int columnIndexOrThrow10 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "opacity");
            int columnIndexOrThrow11 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "zIndex");
            int columnIndexOrThrow12 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fontFamily");
            int columnIndexOrThrow13 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fontSize");
            int columnIndexOrThrow14 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "textColor");
            int columnIndexOrThrow15 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "bold");
            int columnIndexOrThrow16 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "italic");
            int columnIndexOrThrow17 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "underline");
            int columnIndexOrThrow18 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "fillColor");
            int columnIndexOrThrow19 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "strokeColor");
            int columnIndexOrThrow20 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "strokeWidth");
            int columnIndexOrThrow21 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "locked");
            int columnIndexOrThrow22 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "visible");
            int columnIndexOrThrow23 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "textAlign");
            int columnIndexOrThrow24 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "lineSpacing");
            int columnIndexOrThrow25 = SQLiteStatementUtil.getColumnIndexOrThrow(sQLiteStatementPrepare, "cornerRadius");
            ArrayList arrayList = new ArrayList();
            while (sQLiteStatementPrepare.step()) {
                long j2 = sQLiteStatementPrepare.getLong(columnIndexOrThrow);
                long j3 = sQLiteStatementPrepare.getLong(columnIndexOrThrow2);
                String text = sQLiteStatementPrepare.getText(columnIndexOrThrow3);
                String text2 = sQLiteStatementPrepare.getText(columnIndexOrThrow4);
                int i = columnIndexOrThrow;
                int i2 = columnIndexOrThrow2;
                float f = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow5);
                float f2 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow6);
                float f3 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow7);
                float f4 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow8);
                float f5 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow9);
                float f6 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow10);
                int i3 = (int) sQLiteStatementPrepare.getLong(columnIndexOrThrow11);
                String text3 = sQLiteStatementPrepare.getText(columnIndexOrThrow12);
                float f7 = (float) sQLiteStatementPrepare.getDouble(columnIndexOrThrow13);
                String text4 = sQLiteStatementPrepare.getText(columnIndexOrThrow14);
                int i4 = columnIndexOrThrow15;
                int i5 = columnIndexOrThrow3;
                int i6 = columnIndexOrThrow4;
                boolean z = ((int) sQLiteStatementPrepare.getLong(i4)) != 0;
                int i7 = columnIndexOrThrow16;
                int i8 = columnIndexOrThrow5;
                boolean z2 = ((int) sQLiteStatementPrepare.getLong(i7)) != 0;
                int i9 = columnIndexOrThrow17;
                boolean z3 = ((int) sQLiteStatementPrepare.getLong(i9)) != 0;
                int i10 = columnIndexOrThrow18;
                String text5 = sQLiteStatementPrepare.getText(i10);
                int i11 = columnIndexOrThrow19;
                String text6 = sQLiteStatementPrepare.getText(i11);
                columnIndexOrThrow18 = i10;
                int i12 = columnIndexOrThrow20;
                float f8 = (float) sQLiteStatementPrepare.getDouble(i12);
                int i13 = columnIndexOrThrow21;
                boolean z4 = ((int) sQLiteStatementPrepare.getLong(i13)) != 0;
                int i14 = columnIndexOrThrow22;
                int i15 = columnIndexOrThrow23;
                int i16 = columnIndexOrThrow24;
                columnIndexOrThrow17 = i9;
                int i17 = columnIndexOrThrow25;
                arrayList.add(new DesignElementEntity(j2, j3, text, text2, f, f2, f3, f4, f5, f6, i3, text3, f7, text4, z, z2, z3, text5, text6, f8, z4, ((int) sQLiteStatementPrepare.getLong(i14)) != 0, sQLiteStatementPrepare.getText(i15), (float) sQLiteStatementPrepare.getDouble(i16), (float) sQLiteStatementPrepare.getDouble(i17)));
                columnIndexOrThrow3 = i5;
                columnIndexOrThrow15 = i4;
                columnIndexOrThrow = i;
                columnIndexOrThrow4 = i6;
                columnIndexOrThrow5 = i8;
                columnIndexOrThrow16 = i7;
                columnIndexOrThrow19 = i11;
                columnIndexOrThrow20 = i12;
                columnIndexOrThrow21 = i13;
                columnIndexOrThrow22 = i14;
                columnIndexOrThrow23 = i15;
                columnIndexOrThrow24 = i16;
                columnIndexOrThrow25 = i17;
                columnIndexOrThrow2 = i2;
            }
            sQLiteStatementPrepare.close();
            return arrayList;
        } catch (Throwable th) {
            sQLiteStatementPrepare.close();
            throw th;
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object deleteDesign(final String str, Continuation<? super Unit> continuation) {
        final String str2 = "DELETE FROM document_designs WHERE documentType = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.deleteDesign$lambda$8(str2, str, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteDesign$lambda$8(String str, String str2, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7549bindText(1, str2);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object deleteElements(final long j, Continuation<? super Unit> continuation) {
        final String str = "DELETE FROM design_elements WHERE designId = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.deleteElements$lambda$9(str, j, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit deleteElements$lambda$9(String str, long j, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, j);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    @Override // com.mohammedalhzmi.masrofmanager.data.DesignDao
    public Object setLayer(final long j, final int i, Continuation<? super Unit> continuation) {
        final String str = "UPDATE design_elements SET zIndex = ? WHERE id = ?";
        Object objPerformSuspending = DBUtil.performSuspending(this.__db, false, true, new Function1() { // from class: com.mohammedalhzmi.masrofmanager.data.DesignDao_Impl$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DesignDao_Impl.setLayer$lambda$10(str, i, j, (SQLiteConnection) obj);
            }
        }, continuation);
        return objPerformSuspending == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objPerformSuspending : Unit.INSTANCE;
    }

    static final Unit setLayer$lambda$10(String str, int i, long j, SQLiteConnection _connection) {
        Intrinsics.checkNotNullParameter(_connection, "_connection");
        SQLiteStatement sQLiteStatementPrepare = _connection.prepare(str);
        try {
            sQLiteStatementPrepare.mo7547bindLong(1, i);
            sQLiteStatementPrepare.mo7547bindLong(2, j);
            sQLiteStatementPrepare.step();
            return Unit.INSTANCE;
        } finally {
            sQLiteStatementPrepare.close();
        }
    }

    /* JADX INFO: compiled from: DesignDao_Impl.kt */
    @Metadata(m913d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005¨\u0006\u0007"}, m914d2 = {"Lcom/mohammedalhzmi/masrofmanager/data/DesignDao_Impl$Companion;", "", "<init>", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app"}, m915k = 1, m916mv = {2, 2, 0}, m918xi = 48)
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
